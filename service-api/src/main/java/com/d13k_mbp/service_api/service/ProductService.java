package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestProductDTO;
import com.d13k_mbp.service_api.dto.response.ResponseProductDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ProductPaginateResponseDTO;
import java.sql.SQLException;

public interface ProductService {
    public void createProduct(RequestProductDTO dto) throws SQLException;
    public void updateProduct(RequestProductDTO dto, String productId) throws SQLException;
    public void deleteProduct(String productId) throws SQLException;
    public ResponseProductDTO findProductById(String productId) throws SQLException;
    public ProductPaginateResponseDTO findAllProducts(int page,
                                                      int size,
                                                      String searchText) throws SQLException;
    public ProductPaginateResponseDTO findAllProductsByCategoryId(int page,
                                                                  int size,
                                                                  String categoryId,
                                                                  String searchText) throws SQLException;
}
