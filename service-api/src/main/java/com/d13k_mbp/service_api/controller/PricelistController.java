package com.d13k_mbp.service_api.controller;

import com.d13k_mbp.service_api.dto.request.RequestPricelistDTO;
import com.d13k_mbp.service_api.service.PricelistService;
import com.d13k_mbp.service_api.service.util.StandardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-management/api/v1/pricelist")
public class PricelistController {
    private final PricelistService pricelistService;

    @PostMapping("/admin/create")
    public ResponseEntity<StandardResponseDTO> create(
            @RequestBody RequestPricelistDTO pricelistDTO) throws SQLException{
        pricelistService.createPricelist(pricelistDTO);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Pricelist Saved Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("/admin/update/{pricelistId}")
    public ResponseEntity<StandardResponseDTO> update(
            @PathVariable String pricelistId,
            @RequestBody RequestPricelistDTO pricelistDTO) throws SQLException{
        pricelistService.updatePricelist(pricelistDTO, pricelistId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Pricelist Updated Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/host/delete/{pricelistId}")
    public ResponseEntity<StandardResponseDTO> delete(
            @PathVariable String pricelistId) throws SQLException{
        pricelistService.deletePricelist(pricelistId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        204,
                        "Pricelist Deleted Successfully",
                        null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/user/find-by-id/{pricelistId}")
    public ResponseEntity<StandardResponseDTO> findById(
            @PathVariable String pricelistId) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Pricelist Found!",
                        pricelistService.findPricelistById(pricelistId)
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
                        "Pricelists Found!",
                        pricelistService.findAllPricelists(page, size, searchText)
                ), HttpStatus.OK
        );
    }
}
