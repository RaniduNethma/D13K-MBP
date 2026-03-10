package com.d13k_mbp.service_api.controller;

import com.d13k_mbp.service_api.dto.request.RequestProductDTO;
import com.d13k_mbp.service_api.service.ProductService;
import com.d13k_mbp.service_api.service.util.StandardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-management/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("admin/create")
    public ResponseEntity<StandardResponseDTO> create(
            @RequestBody RequestProductDTO dto) throws SQLException{
        productService.createProduct(dto);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Product Saved Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("admin/update/{productId}")
    public ResponseEntity<StandardResponseDTO> update(
            @PathVariable() String productId,
            @RequestBody RequestProductDTO dto) throws SQLException{
        productService.updateProduct(dto, productId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Product Updated Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("host/delete/{productId}")
    public ResponseEntity<StandardResponseDTO> delete(
            @PathVariable() String productId) throws SQLException{
        productService.deleteProduct(productId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        204,
                        "Product Deleted Successfully",
                        null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("user/find-by-id/{productId}")
    public ResponseEntity<StandardResponseDTO> findById(
            @PathVariable() String productId) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Product Found!",
                        productService.findProductById(productId)
                ), HttpStatus.OK
        );
    }

    @GetMapping("user/find-all")
    public ResponseEntity<StandardResponseDTO> findAll(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                    200,
                        "Product List",
                        productService.findAllProducts(page, size, searchText)
                ), HttpStatus.OK
        );
    }

    @GetMapping("/user/find-all-by-category-id/{categoryId}")
    public ResponseEntity<StandardResponseDTO> findAllByCategoryId(
            @PathVariable() String categoryId,
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Product List",
                        productService.findAllProductsByCategoryId(page, size, categoryId, searchText)
                ), HttpStatus.OK
        );
    }
}
