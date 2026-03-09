package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestPricelistItemDTO;
import com.d13k_mbp.service_api.dto.response.ResponsePricelistItemDTO;
import com.d13k_mbp.service_api.dto.response.paginate.PricelistItemPaginateResponseDTO;
import java.sql.SQLException;

public interface PricelistItemService {
    public void createPricelistItem(RequestPricelistItemDTO dto) throws SQLException;
    public void updatePricelistItem(RequestPricelistItemDTO dto) throws SQLException;
    public void deletePricelistItem(String pricelistItemId) throws SQLException;
    public ResponsePricelistItemDTO findPricelistItemById(String pricelistItemId) throws SQLException;
    public PricelistItemPaginateResponseDTO findAllPricelistItems(int page,
                                                                  int size,
                                                                  String searchText) throws SQLException;
    public PricelistItemPaginateResponseDTO findAllPricelistItemsByProductId(int page,
                                                                             int size,
                                                                             String productId,
                                                                             String searchText) throws SQLException;
    public PricelistItemPaginateResponseDTO findAllPricelistItemsByPricelistId(int page,
                                                                               int size,
                                                                               String pricelistId,
                                                                               String searchText) throws SQLException;
}
