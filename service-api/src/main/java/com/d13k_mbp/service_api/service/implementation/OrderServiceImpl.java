package com.d13k_mbp.service_api.service.implementation;

import com.d13k_mbp.service_api.dto.request.RequestOrderDTO;
import com.d13k_mbp.service_api.dto.response.ResponseOrderDTO;
import com.d13k_mbp.service_api.dto.response.ResponseOrderItemDTO;
import com.d13k_mbp.service_api.dto.response.paginate.OrderPaginateResponseDTO;
import com.d13k_mbp.service_api.entity.*;
import com.d13k_mbp.service_api.exception.EntryNotFoundException;
import com.d13k_mbp.service_api.repository.*;
import com.d13k_mbp.service_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final PricelistRepository pricelistRepository;
    private final PricelistItemRepository pricelistItemRepository;

    @Override
    public void createOrder(RequestOrderDTO orderDTO) throws SQLException {
        OrderEntity order = orderDTO == null ? null : OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .orderStatus(orderDTO.getOrderStatus())
                .subTotal(BigDecimal.ZERO)
                .grandTotal(BigDecimal.ZERO)
                .orderDiscount(orderDTO.getOrderDiscount())
                .paymentStatus(orderDTO.getPaymentStatus())
                .paymentMethod(orderDTO.getPaymentMethod())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        assert order != null;
        orderRepository.save(order);

        List<OrderItemEntity> savedItems = toOrderItem(orderDTO, order);
        calculateAndSetTotals(order, orderDTO, savedItems);
    }

    @Override
    public void updateOrder(RequestOrderDTO orderDTO, String orderId) throws SQLException {
        OrderEntity selectedOrder = orderRepository.findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException("Order Not Found!"));

        selectedOrder.setOrderStatus(orderDTO.getOrderStatus());
        selectedOrder.setOrderDiscount(orderDTO.getOrderDiscount());
        selectedOrder.setPaymentStatus(orderDTO.getPaymentStatus());
        selectedOrder.setPaymentMethod(orderDTO.getPaymentMethod());
        selectedOrder.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(selectedOrder);

        List<OrderItemEntity> savedItems = toOrderItem(orderDTO, selectedOrder);
        calculateAndSetTotals(selectedOrder, orderDTO, savedItems);
    }

    @Override
    public void deleteOrder(String orderId) throws SQLException {
        orderRepository.findById(orderId)
                .orElseThrow(()-> new EntryNotFoundException("Order Not Found!"));
        orderItemRepository.deleteAllByOrderId(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public ResponseOrderDTO findOrderById(String orderId) throws SQLException {
        OrderEntity selectedOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException("Order Not Found!"));
        return toResponseOrderDTO(selectedOrder);
    }

    @Override
    @Transactional
    public OrderPaginateResponseDTO findAllOrders(int page, int size, String searchText) throws SQLException {
        return OrderPaginateResponseDTO.builder()
                .orderDataCount(orderRepository.countAllOrders(searchText))
                .orderDataList(orderRepository.searchAllOrders(searchText, PageRequest.of(page, size))
                        .stream()
                        .map(this::toResponseOrderDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private List<OrderItemEntity> toOrderItem(RequestOrderDTO orderDTO, OrderEntity order){
        List<OrderItemEntity> savedOrderItems = new ArrayList<>();

        if (orderDTO.getOrderItems() != null && !orderDTO.getOrderItems().isEmpty()){
            orderDTO.getOrderItems().forEach(orderItemDTO -> {
                ProductEntity product = productRepository.findById(orderItemDTO.getProductId())
                        .orElseThrow(()-> new EntryNotFoundException("Product Not Found!"));

                PricelistEntity pricelist = pricelistRepository.findById(orderItemDTO.getPricelistId())
                        .orElseThrow(()-> new EntryNotFoundException("Pricelist Not Found!"));

                PricelistItemEntity pricelistItem = pricelistItemRepository
                        .findByProductIdAndPricelistId(orderItemDTO.getProductId(), orderItemDTO.getPricelistId())
                        .orElseThrow(()-> new EntryNotFoundException("Pricelist Item Not Found!"));

                BigDecimal pricePerUnit = pricelistItem.getPrice();
                BigDecimal discountedPrice = pricePerUnit.subtract(BigDecimal.valueOf(orderItemDTO.getItemDiscount()));
                BigDecimal totalPrice = discountedPrice.multiply(new BigDecimal(orderItemDTO.getItemQuantity()));

                OrderItemEntity orderItem = OrderItemEntity.builder()
                        .orderItemId(UUID.randomUUID().toString())
                        .productEntity(product)
                        .orderEntity(order)
                        .pricelistEntity(pricelist)
                        .pricePerUnit(pricePerUnit)
                        .itemDiscount(orderItemDTO.getItemDiscount())
                        .itemQuantity(orderItemDTO.getItemQuantity())
                        .totalPrice(totalPrice)
                        .createdAt(LocalDateTime.now())
                        .build();
                savedOrderItems.add(orderItemRepository.save(orderItem));
            });
        }
        return savedOrderItems;
    }

    private void calculateAndSetTotals(OrderEntity order,
                                       RequestOrderDTO orderDTO,
                                       List<OrderItemEntity> orderItems){
        BigDecimal subTotal = orderItems.stream()
                .map(OrderItemEntity::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grandTotal = subTotal
                .subtract(BigDecimal.valueOf(orderDTO.getOrderDiscount()));

        order.setSubTotal(subTotal);
        order.setGrandTotal(grandTotal);
        orderRepository.save(order);
    }

    private ResponseOrderDTO toResponseOrderDTO(OrderEntity order) {
        return order == null ? null :
                ResponseOrderDTO.builder()
                        .orderId(order.getOrderId())
                        .orderStatus(order.getOrderStatus())
                        .subTotal(order.getSubTotal())
                        .grandTotal(order.getGrandTotal())
                        .orderDiscount(order.getOrderDiscount())
                        .paymentStatus(order.getPaymentStatus())
                        .paymentMethod(order.getPaymentMethod())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .orderItems(order.getOrderItems()
                                .stream()
                                .map(this::toResponseOrderItemDTO)
                                .collect(Collectors.toList()))
                        .build();
    }

    private ResponseOrderItemDTO toResponseOrderItemDTO(OrderItemEntity orderItem) {
        return orderItem == null ? null :
                ResponseOrderItemDTO.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .productId(orderItem.getProductEntity().getProductId())
                        .orderId(orderItem.getOrderEntity().getOrderId())
                        .pricelistId(orderItem.getPricelistEntity().getPricelistId())
                        .pricePerUnit(orderItem.getPricePerUnit())
                        .itemDiscount(orderItem.getItemDiscount())
                        .itemQuantity(orderItem.getItemQuantity())
                        .totalPrice(orderItem.getTotalPrice())
                        .createdAt(orderItem.getCreatedAt())
                        .build();
    }
}
