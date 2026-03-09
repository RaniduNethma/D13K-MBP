package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestOrderDTO;
import com.d13k_mbp.service_api.dto.response.ResponseOrderDTO;
import com.d13k_mbp.service_api.dto.response.paginate.OrderPaginateResponseDTO;
import java.sql.SQLException;

public interface OrderService {
    public void createOrder(RequestOrderDTO dto) throws SQLException;
    public void updateOrder(RequestOrderDTO dto, String orderId) throws SQLException;
    public void deleteOrder(String orderId) throws SQLException;
    public ResponseOrderDTO findOrderById(String orderId) throws SQLException;
    public OrderPaginateResponseDTO findAllOrders(int page,
                                                  int size,
                                                  String searchText) throws SQLException;
}
