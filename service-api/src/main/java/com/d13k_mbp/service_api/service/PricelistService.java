package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestPricelistDTO;
import com.d13k_mbp.service_api.dto.request.RequestPricelistItemDTO;
import com.d13k_mbp.service_api.dto.response.ResponsePricelistDTO;
import com.d13k_mbp.service_api.dto.response.paginate.PricelistPaginateResponseDTO;
import java.sql.SQLException;

public interface PricelistService {
    public void createPricelist(RequestPricelistDTO pricelistDTO) throws SQLException;
    public void updatePricelist(RequestPricelistDTO dto, String pricelistId) throws SQLException;
    public void deletePricelist(String pricelistId) throws SQLException;
    public ResponsePricelistDTO findPricelistById(String pricelistId) throws SQLException;
    public PricelistPaginateResponseDTO findAllPricelists(int page,
                                                          int size,
                                                          String searchText) throws SQLException;
}
