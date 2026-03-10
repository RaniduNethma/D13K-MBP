package com.d13k_mbp.service_api.controller;

import com.d13k_mbp.service_api.dto.request.RequestProductCategoryDTO;
import com.d13k_mbp.service_api.service.ProductCategoryService;
import com.d13k_mbp.service_api.service.util.StandardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-management/api/v1/product-category")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping("admin/create")
    public ResponseEntity<StandardResponseDTO> create(
            @RequestBody RequestProductCategoryDTO dto) throws SQLException{
        productCategoryService.createProductCategory(dto);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Product Category Saved Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("admin/update/{categoryId}")
    public ResponseEntity<StandardResponseDTO> update(
            @PathVariable String categoryId,
            @RequestBody RequestProductCategoryDTO dto) throws SQLException{
        productCategoryService.updateProductCategory(dto, categoryId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Product Category Updated Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("host/delete/{categoryId}")
    public ResponseEntity<StandardResponseDTO> delete(
            @PathVariable String categoryId) throws SQLException{
        productCategoryService.deleteProductCategory(categoryId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        204,
                        "Product Category Deleted Successfully",
                        null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("user/find-by-id/{categoryId}")
    public ResponseEntity<StandardResponseDTO> findById(
            @PathVariable() String categoryId) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Product Category Found!",
                        productCategoryService.findProductCategoryById(categoryId)
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
                        "Product Category List",
                        productCategoryService.findAllProductCategories(page, size, searchText)
                ), HttpStatus.OK
        );
    }
}
