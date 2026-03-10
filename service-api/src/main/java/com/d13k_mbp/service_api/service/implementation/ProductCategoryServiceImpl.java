package com.d13k_mbp.service_api.service.implementation;

import com.d13k_mbp.service_api.dto.request.RequestProductCategoryDTO;
import com.d13k_mbp.service_api.dto.response.ResponseProductCategoryDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ProductCategoryPaginateResponseDTO;
import com.d13k_mbp.service_api.entity.ProductCategoryEntity;
import com.d13k_mbp.service_api.exception.EntryNotFoundException;
import com.d13k_mbp.service_api.repository.ProductCategoryRepository;
import com.d13k_mbp.service_api.service.ProductCategoryService;
import com.d13k_mbp.service_api.service.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createProductCategory(RequestProductCategoryDTO dto) throws SQLException {
        productCategoryRepository.save(toProductCategory(dto));
    }

    @Override
    public void updateProductCategory(RequestProductCategoryDTO dto, String productCategoryId) throws SQLException {
        ProductCategoryEntity selectedProductCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(()-> new EntryNotFoundException("Product Category Not Found!"));
        selectedProductCategory.setCategoryName(dto.getCategoryName());
        selectedProductCategory.setCategoryDescription(byteCodeHandler.stringToBlob(dto.getCategoryDescription()));
        selectedProductCategory.setActive(dto.isActive());
        selectedProductCategory.setUpdatedAt(LocalDateTime.now());
        productCategoryRepository.save(selectedProductCategory);
    }

    @Override
    public void deleteProductCategory(String productCategoryId) throws SQLException {
        productCategoryRepository.findById(productCategoryId)
                .orElseThrow(()-> new EntryNotFoundException("Product Category Not Found!"));
        productCategoryRepository.deleteById(productCategoryId);
    }

    @Override
    @Transactional
    public ResponseProductCategoryDTO findProductCategoryById(String productCategoryId) throws SQLException {
        ProductCategoryEntity selectedProductCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(()-> new EntryNotFoundException("Product Category Not Found!"));
        return toResponseProductCategoryDTO(selectedProductCategory);
    }

    @Override
    @Transactional
    public ProductCategoryPaginateResponseDTO findAllProductCategories(int page, int size, String searchText) throws SQLException {
        return ProductCategoryPaginateResponseDTO.builder()
                .productCategoryDataCount(productCategoryRepository.countAllProductCategories(searchText))
                .productCategoryDataList(productCategoryRepository.searchAllProductCategories(searchText, PageRequest.of(page, size))
                        .stream().map(e-> {
                            try {
                                return toResponseProductCategoryDTO(e);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }).collect(Collectors.toList()))
                .build();
    }

    private ProductCategoryEntity toProductCategory(RequestProductCategoryDTO dto) throws SQLException{
        return dto == null ? null :
                ProductCategoryEntity.builder()
                        .categoryId(UUID.randomUUID().toString())
                        .categoryName(dto.getCategoryName())
                        .categoryDescription(byteCodeHandler.stringToBlob(dto.getCategoryDescription()))
                        .isActive(dto.isActive())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
    }

    private ResponseProductCategoryDTO toResponseProductCategoryDTO(
            ProductCategoryEntity selectedProductCategory) throws SQLException{
        return selectedProductCategory == null ? null :
                ResponseProductCategoryDTO.builder()
                        .categoryId(selectedProductCategory.getCategoryId())
                        .categoryName(selectedProductCategory.getCategoryName())
                        .categoryDescription(byteCodeHandler.blobToString(selectedProductCategory.getCategoryDescription()))
                        .isActive(selectedProductCategory.isActive())
                        .createdAt(selectedProductCategory.getCreatedAt())
                        .updatedAt(selectedProductCategory.getUpdatedAt())
                        .build();
    }
}
