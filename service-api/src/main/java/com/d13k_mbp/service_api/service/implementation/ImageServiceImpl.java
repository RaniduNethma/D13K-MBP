package com.d13k_mbp.service_api.service.implementation;

import com.d13k_mbp.service_api.dto.request.RequestImageDTO;
import com.d13k_mbp.service_api.dto.response.ResponseImageDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ImagePaginateResponseDTO;
import com.d13k_mbp.service_api.entity.FileFormatter;
import com.d13k_mbp.service_api.entity.ImageEntity;
import com.d13k_mbp.service_api.entity.ProductEntity;
import com.d13k_mbp.service_api.exception.EntryNotFoundException;
import com.d13k_mbp.service_api.repository.ImageRepository;
import com.d13k_mbp.service_api.repository.ProductRepository;
import com.d13k_mbp.service_api.service.ImageService;
import com.d13k_mbp.service_api.service.util.ByteCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ByteCodeHandler byteCodeHandler;

    @Override
    public void createImage(RequestImageDTO dto) throws SQLException {
        imageRepository.save(toImage(dto));
    }

    @Override
    public void updateImage(RequestImageDTO dto, String imageId) throws SQLException {
        ImageEntity selectedImage = imageRepository.findById(Long.parseLong(imageId))
                .orElseThrow(() -> new EntryNotFoundException("Image Not Found!"));

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntryNotFoundException("Product Not Found!"));

        String fileName = dto.getFile().getOriginalFilename();
        String hash = generateHash(dto);
        String directory = generateDirectory(dto.getProductId());
        String resourceUrl = generateResourceUrl(selectedImage.getImageId(), fileName);

        selectedImage.setProductEntity(product);
        assert fileName != null;
        selectedImage.setFileFormatter(FileFormatter.builder()
                .fileName(fileName.getBytes())
                .hash(hash.getBytes())
                .directory(directory.getBytes())
                .resourceUrl(resourceUrl.getBytes())
                .build());
        imageRepository.save(selectedImage);
    }

    @Override
    public void deleteImage(String imageId) throws SQLException {
        imageRepository.findById(Long.parseLong(imageId))
                .orElseThrow(() -> new EntryNotFoundException("Image Not Found!"));

        imageRepository.deleteById(Long.parseLong(imageId));
    }

    @Override
    @Transactional
    public ResponseImageDTO findImageById(String imageId) throws SQLException {
        ImageEntity selectedImage = imageRepository.findById(Long.parseLong(imageId))
                .orElseThrow(() -> new EntryNotFoundException("Image Not Found!"));
        return toResponseImageDTO(selectedImage);
    }

    @Override
    @Transactional
    public ImagePaginateResponseDTO findAllImages(int page, int size, String searchText) throws SQLException {
        return ImagePaginateResponseDTO.builder()
                .imageDataCount(imageRepository.countAllImages(searchText))
                .imageDataList(imageRepository.searchAllImages(searchText, PageRequest.of(page, size))
                        .stream()
                        .map(this::toResponseImageDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional
    public ImagePaginateResponseDTO findImagesByProductId(int page, int size, String productId, String searchText) throws SQLException {
        return ImagePaginateResponseDTO.builder()
                .imageDataCount(imageRepository.countAllImagesByProductId(productId, searchText))
                .imageDataList(imageRepository.searchAllImagesByProductId(productId, searchText, PageRequest.of(page, size))
                        .stream()
                        .map(this::toResponseImageDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private ImageEntity toImage(RequestImageDTO dto) throws SQLException{
        if (dto == null) return null;

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new EntryNotFoundException("Product Not Found!"));

        String fileName = dto.getFile().getOriginalFilename();
        String hash = generateHash(dto);

        assert fileName != null;
        ImageEntity image = ImageEntity.builder()
                .productEntity(product)
                .fileFormatter(FileFormatter.builder()
                        .fileName(fileName.getBytes())
                        .hash(hash.getBytes())
                        .directory("".getBytes())
                        .resourceUrl("".getBytes())
                        .build())
                .build();
        ImageEntity savedImage = imageRepository.save(image);

        String directory = generateDirectory(dto.getProductId());
        String resourceUrl = generateResourceUrl(savedImage.getImageId(), fileName);

        savedImage.getFileFormatter().setDirectory(directory.getBytes());
        savedImage.getFileFormatter().setResourceUrl(resourceUrl.getBytes());
        return imageRepository.save(savedImage);
    }

    private String generateHash(RequestImageDTO dto) throws SQLException{
        try {
            byte[] fileBytes = dto.getFile().getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(fileBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashBytes){
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException("Failed to generate hash", e);
        }
    }

    private String generateDirectory(String productId) {
        return "/images/products/" + productId + "/";
    }

    private String generateResourceUrl(Long imageId, String fileName) {
        return "/images/" + imageId + "/" + fileName;
    }

    private ResponseImageDTO toResponseImageDTO(ImageEntity selectedImage) {
        return selectedImage == null ? null :
                ResponseImageDTO.builder()
                        .imageId(selectedImage.getImageId())
                        .productId(selectedImage.getProductEntity().getProductId())
                        .fileName(new String(selectedImage.getFileFormatter().getFileName()))
                        .hash(new String(selectedImage.getFileFormatter().getHash()))
                        .directory(new String(selectedImage.getFileFormatter().getDirectory()))
                        .resourceURL(new String(selectedImage.getFileFormatter().getResourceUrl()))
                        .build();
    }
}
