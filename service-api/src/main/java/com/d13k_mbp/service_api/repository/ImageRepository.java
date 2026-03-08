package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
