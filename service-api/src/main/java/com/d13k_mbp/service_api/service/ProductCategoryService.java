package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestProductCategoryDTO;
import com.d13k_mbp.service_api.dto.response.ResponseProductCategoryDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ProductCategoryPaginateResponseDTO;
import java.sql.SQLException;

public interface ProductCategoryService {
    public void createProductCategory(RequestProductCategoryDTO dto) throws SQLException;
    public void updateProductCategory(RequestProductCategoryDTO dto, String productCategoryId) throws SQLException;
    public void deleteProductCategory(String productCategoryId) throws SQLException;
    public ResponseProductCategoryDTO findProductCategoryById(String productCategoryId) throws SQLException;
    public ProductCategoryPaginateResponseDTO findAllProductCategories(int page,
                                                                       int size,
                                                                       String searchText) throws SQLException;
}
