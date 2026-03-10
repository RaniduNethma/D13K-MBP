package com.d13k_mbp.service_api.service.implementation;

import com.d13k_mbp.service_api.dto.request.RequestPricelistDTO;
import com.d13k_mbp.service_api.dto.response.ResponsePricelistDTO;
import com.d13k_mbp.service_api.dto.response.ResponsePricelistItemDTO;
import com.d13k_mbp.service_api.dto.response.paginate.PricelistPaginateResponseDTO;
import com.d13k_mbp.service_api.entity.PricelistEntity;
import com.d13k_mbp.service_api.entity.PricelistItemEntity;
import com.d13k_mbp.service_api.entity.ProductEntity;
import com.d13k_mbp.service_api.exception.EntryNotFoundException;
import com.d13k_mbp.service_api.repository.PricelistItemRepository;
import com.d13k_mbp.service_api.repository.PricelistRepository;
import com.d13k_mbp.service_api.repository.ProductRepository;
import com.d13k_mbp.service_api.service.PricelistService;
import com.d13k_mbp.service_api.service.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricelistServiceImpl implements PricelistService {
    private final PricelistRepository pricelistRepository;
    private final PricelistItemRepository pricelistItemRepository;
    private final ProductRepository productRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createPricelist(RequestPricelistDTO pricelistDTO) throws SQLException {
        PricelistEntity pricelist = pricelistDTO == null ? null : PricelistEntity.builder()
                .pricelistId(UUID.randomUUID().toString())
                .pricelistName(pricelistDTO.getPricelistName())
                .pricelistDescription(byteCodeHandler.stringToBlob(pricelistDTO.getPricelistDescription()))
                .isActive(pricelistDTO.isActive())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        assert pricelist != null;
        pricelistRepository.save(pricelist);

        toPricelistItem(pricelistDTO, pricelist);
    }

    @Override
    public void updatePricelist(RequestPricelistDTO dto, String pricelistId) throws SQLException {
        PricelistEntity selectedPricelist = pricelistRepository.findById(pricelistId)
                .orElseThrow(()-> new EntryNotFoundException("Pricelist Not Found!"));

        selectedPricelist.setPricelistName(dto.getPricelistName());
        selectedPricelist.setPricelistDescription(byteCodeHandler.stringToBlob(dto.getPricelistDescription()));
        selectedPricelist.setActive(dto.isActive());
        selectedPricelist.setUpdatedAt(LocalDateTime.now());
        pricelistRepository.save(selectedPricelist);

        pricelistItemRepository.deleteAllByPricelistId(pricelistId);

        toPricelistItem(dto, selectedPricelist);
    }

    @Override
    public void deletePricelist(String pricelistId) throws SQLException {
        pricelistRepository.findById(pricelistId)
                .orElseThrow(()-> new EntryNotFoundException("Pricelist Not Found!"));
        pricelistItemRepository.deleteAllByPricelistId(pricelistId);
        pricelistRepository.deleteById(pricelistId);
    }

    @Override
    @Transactional
    public ResponsePricelistDTO findPricelistById(String pricelistId) throws SQLException {
        PricelistEntity selectedPricelist = pricelistRepository.findById(pricelistId)
                .orElseThrow(()-> new EntryNotFoundException("Pricelist Not Found!"));
        return toResponsePricelistDTO(selectedPricelist);
    }

    @Override
    @Transactional
    public PricelistPaginateResponseDTO findAllPricelists(int page, int size, String searchText) throws SQLException {
        return PricelistPaginateResponseDTO.builder()
                .pricelistDataCount(pricelistRepository.countAllPricelists(searchText))
                .pricelistDataList(pricelistRepository.searchAllPricelists(searchText, PageRequest.of(page, size))
                        .stream().map(e-> {
                            try {
                                return toResponsePricelistDTO(e);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }).collect(Collectors.toList()))
                .build();
    }

    private void toPricelistItem(RequestPricelistDTO pricelistDTO, PricelistEntity pricelist) {
        if(pricelistDTO.getPricelistItems() != null && !pricelistDTO.getPricelistItems().isEmpty()){
            pricelistDTO.getPricelistItems().forEach(pricelistItemDTO -> {
                ProductEntity product = productRepository.findById(pricelistItemDTO.getProductId())
                        .orElseThrow(()-> new EntryNotFoundException("Product Not Found!"));

                PricelistItemEntity pricelistItem = PricelistItemEntity.builder()
                        .pricelistItemId(UUID.randomUUID().toString())
                        .productEntity(product)
                        .pricelistEntity(pricelist)
                        .price(pricelistItemDTO.getPrice())
                        .createdAt(LocalDateTime.now())
                        .build();
                pricelistItemRepository.save(pricelistItem);
            });
        }
    }

    @SneakyThrows
    private ResponsePricelistDTO toResponsePricelistDTO(
            PricelistEntity selectedPricelist) throws SQLException{
        return selectedPricelist == null ? null :
                ResponsePricelistDTO.builder()
                        .pricelistId(selectedPricelist.getPricelistId())
                        .pricelistName(selectedPricelist.getPricelistName())
                        .pricelistDescription(byteCodeHandler.blobToString(selectedPricelist.getPricelistDescription()))
                        .isActive(selectedPricelist.isActive())
                        .createdAt(selectedPricelist.getCreatedAt())
                        .updatedAt(selectedPricelist.getUpdatedAt())
                        .pricelistItems(selectedPricelist.getPricelistItems()
                                .stream().map(this::toResponsePricelistItemDTO)
                                .collect(Collectors.toList()))
                        .build();
    }

    private ResponsePricelistItemDTO toResponsePricelistItemDTO(
            PricelistItemEntity pricelistItem){
        return pricelistItem == null ? null :
                ResponsePricelistItemDTO.builder()
                        .pricelistItemId(pricelistItem.getPricelistItemId())
                        .productId(pricelistItem.getProductEntity().getProductId())
                        .pricelistId(pricelistItem.getPricelistEntity().getPricelistId())
                        .price(pricelistItem.getPrice())
                        .createdAt(pricelistItem.getCreatedAt())
                        .build();
    }
}
