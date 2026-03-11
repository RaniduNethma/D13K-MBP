package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ImageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM image WHERE file_name LIKE %?1%", nativeQuery = true)
    long countAllImages(String searchText);

    @Query(value = "SELECT * FROM image WHERE file_name LIKE %?1%", nativeQuery = true)
    List<ImageEntity> searchAllImages(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM image WHERE product_id = ?1 AND file_name LIKE %?2%", nativeQuery = true)
    long countAllImagesByProductId(String productId, String searchText);

    @Query(value = "SELECT * FROM image WHERE product_id = ?1 AND file_name LIKE %?2%", nativeQuery = true)
    List<ImageEntity> searchAllImagesByProductId(String productId, String searchText, Pageable pageable);
}
