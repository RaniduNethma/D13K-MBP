package com.d13k_mbp.service_api.service.implementation;

import com.d13k_mbp.service_api.dto.request.RequestProductDTO;
import com.d13k_mbp.service_api.dto.response.ResponseProductDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ProductPaginateResponseDTO;
import com.d13k_mbp.service_api.entity.ProductEntity;
import com.d13k_mbp.service_api.exception.EntryNotFoundException;
import com.d13k_mbp.service_api.repository.ProductCategoryRepository;
import com.d13k_mbp.service_api.repository.ProductRepository;
import com.d13k_mbp.service_api.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createProduct(RequestProductDTO dto) throws SQLException {
        productRepository.save(toProduct(dto));
    }

    @Override
    public void updateProduct(RequestProductDTO dto, String productId) throws SQLException {
        ProductEntity selectedProduct = productRepository.findById(productId).orElseThrow(()->new EntryNotFoundException("Product Not Found!"));
        selectedProduct.setProductCategoryEntity(productCategoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(()-> new EntryNotFoundException("Product Category Not Found!")));
        selectedProduct.setProductName(dto.getProductName());
        selectedProduct.setProductDescription(byteCodeHandler.stringToBlob(dto.getProductDescription()));
        selectedProduct.setActive(dto.isActive());
        selectedProduct.setQuantity(dto.getQuantity());
        selectedProduct.setWeight(dto.getWeight());
        selectedProduct.setUpdatedAt(LocalDateTime.now());
        productRepository.save(selectedProduct);
    }

    @Override
    public void deleteProduct(String productId) throws SQLException {
        productRepository.findById(productId).orElseThrow(()->new EntryNotFoundException("Product Not Found!"));
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public ResponseProductDTO findProductById(String productId) throws SQLException {
        ProductEntity selectedProduct = productRepository.findById(productId).orElseThrow(()->new EntryNotFoundException("Product Not Found!"));
        return toResponseProductDTO(selectedProduct);
    }

    @Override
    @Transactional
    public ProductPaginateResponseDTO findAllProducts(int page, int size, String searchText) throws SQLException {
        return ProductPaginateResponseDTO.builder()
                .productDataCount(productRepository.countAllProducts(searchText))
                .productDataList(productRepository.searchAllProducts(searchText, PageRequest.of(page, size))
                        .stream().map(e-> {
                            try {
                                return toResponseProductDTO(e);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }).collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional
    public ProductPaginateResponseDTO findAllProductsByCategoryId(int page, int size, String categoryId, String searchText) throws SQLException {
        return ProductPaginateResponseDTO.builder()
                .productDataCount(productRepository.countAllProductsByCategoryId(categoryId, searchText))
                .productDataList(productRepository.searchAllProductsByCategoryId(categoryId, searchText,
                        PageRequest.of(page, size)).stream().map(e-> {
                    try {
                        return toResponseProductDTO(e);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList()))
                .build();
    }

    private ProductEntity toProduct(RequestProductDTO dto) throws SQLException{
        return dto == null ? null :
                ProductEntity.builder()
                        .productId(UUID.randomUUID().toString())
                        .productCategoryEntity(productCategoryRepository
                                .findById(dto.getCategoryId())
                                .orElseThrow(()->new EntryNotFoundException("Category Not Found!")))
                        .productName(dto.getProductName())
                        .productDescription(byteCodeHandler.stringToBlob(dto.getProductDescription()))
                        .isActive(true)
                        .quantity(dto.getQuantity())
                        .weight(dto.getWeight())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
    }

    private ResponseProductDTO toResponseProductDTO(ProductEntity selectedProduct) throws SQLException {
        return selectedProduct == null ? null :
                ResponseProductDTO.builder()
                        .productId(selectedProduct.getProductId())
                        .productName(selectedProduct.getProductName())
                        .productDescription(byteCodeHandler.blobToString(selectedProduct.getProductDescription()))
                        .isActive(selectedProduct.isActive())
                        .quantity(selectedProduct.getQuantity())
                        .weight(selectedProduct.getWeight())
                        .createdAt(selectedProduct.getCreatedAt())
                        .updatedAt(selectedProduct.getUpdatedAt())
                        .build();
    }
}
