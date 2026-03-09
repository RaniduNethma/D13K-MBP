package com.d13k_mbp.service_api.service;

import com.d13k_mbp.service_api.dto.request.RequestImageDTO;
import com.d13k_mbp.service_api.dto.response.ResponseImageDTO;
import com.d13k_mbp.service_api.dto.response.paginate.ImagePaginateResponseDTO;
import java.sql.SQLException;

public interface ImageService {
    public void createImage(RequestImageDTO dto) throws SQLException;
    public void updateImage(RequestImageDTO dto, String imageId) throws SQLException;
    public void deleteImage(String imageId) throws SQLException;
    public ResponseImageDTO findImageById(String imageId) throws SQLException;
    public ImagePaginateResponseDTO findAllImages(int page,
                                                  int size,
                                                  String searchText) throws SQLException;
    public ImagePaginateResponseDTO findImagesByProductId(int page,
                                                          int size,
                                                          String productId,
                                                          String searchText) throws SQLException;
}
