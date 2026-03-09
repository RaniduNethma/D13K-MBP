package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestOrderItemDTO;
import com.d13k_mbp.service_api.dto.response.ResponseOrderItemDTO;
import com.d13k_mbp.service_api.dto.response.paginate.OrderItemPaginateResponseDTO;
import java.sql.SQLException;

public interface OrderItemService {
    public void createOrderItem(RequestOrderItemDTO dto) throws SQLException;
    public void updateOrderItem(RequestOrderItemDTO dto, String orderItemId) throws SQLException;
    public void deleteOrderItem(String orderItemId) throws SQLException;
    public ResponseOrderItemDTO findOrderItemById(String orderItemId) throws SQLException;
    public OrderItemPaginateResponseDTO findAllOrderItems(int page,
                                                          int size,
                                                          String searchText) throws SQLException;
    public OrderItemPaginateResponseDTO findOrderItemsByProductId(int page,
                                                                  int size,
                                                                  String productId,
                                                                  String searchText) throws SQLException;
    public OrderItemPaginateResponseDTO findOrderItemsByOrderId(int page,
                                                                int size,
                                                                String orderId,
                                                                String searchText) throws SQLException;
    public OrderItemPaginateResponseDTO findOrderItemsByPricelistId(int page,
                                                                    int size,
                                                                    String pricelistId,
                                                                    String searchText) throws SQLException;
}
