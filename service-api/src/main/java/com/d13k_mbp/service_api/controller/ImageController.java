package com.d13k_mbp.service_api.controller;

import com.d13k_mbp.service_api.dto.request.RequestImageDTO;
import com.d13k_mbp.service_api.service.ImageService;
import com.d13k_mbp.service_api.service.util.StandardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-management/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/admin/create")
    public ResponseEntity<StandardResponseDTO> create(
            @RequestBody RequestImageDTO imageDTO) throws SQLException{
        imageService.createImage(imageDTO);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Image Saved Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @PutMapping("/admin/update/{imageId}")
    public ResponseEntity<StandardResponseDTO> update(
            @PathVariable String imageId,
            @RequestBody RequestImageDTO imageDTO) throws SQLException{
        imageService.updateImage(imageDTO, imageId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        201,
                        "Image Updated Successfully",
                        null
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/admin/delete/{imageId}")
    public ResponseEntity<StandardResponseDTO> delete(
            @PathVariable String imageId) throws SQLException{
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        204,
                        "Image Deleted Successfully",
                        null
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("user/find-by-id/{imageId}")
    public ResponseEntity<StandardResponseDTO> findById(
            @PathVariable String imageId) throws SQLException{
        return new ResponseEntity<>(
                new StandardResponseDTO(
                        200,
                        "Image Found!",
                        imageService.findImageById(imageId)
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
                        "Images Found!",
                        imageService.findAllImages(page, size, searchText)
                ), HttpStatus.OK
        );
    }
}
