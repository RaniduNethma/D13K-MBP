package com.d13k_mbp.service_api.controller;

import com.d13k_mbp.service_api.dto.request.RequestOrderDTO;
import com.d13k_mbp.service_api.service.OrderService;
import com.d13k_mbp.service_api.service.util.StandardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-management/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/user/create")
    public ResponseEntity<StandardResponseDTO> create(
            @RequestBody RequestOrderDTO orderDTO) throws SQLException{
        orderService.createOrder(orderDTO);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Order Saved Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("/user/update/{orderId}")
    public ResponseEntity<StandardResponseDTO> update(
            @PathVariable String orderId,
            @RequestBody RequestOrderDTO orderDTO) throws SQLException{
        orderService.updateOrder(orderDTO, orderId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Order Updated Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/admin/delete/{orderId}")
    public ResponseEntity<StandardResponseDTO> delete(
            @PathVariable String orderId) throws SQLException{
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        204,
                        "Order Deleted Successfully",
                        null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/user/find-by-id/{orderId}")
    public ResponseEntity<StandardResponseDTO> findById(
            @PathVariable String orderId) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Order Found!",
                        orderService.findOrderById(orderId)
                ), HttpStatus.OK
        );
    }

    @GetMapping("/user/find-all")
    public ResponseEntity<StandardResponseDTO> findAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Orders Found!",
                        orderService.findAllOrders(page, size, searchText)
                ), HttpStatus.OK
        );
    }
}
