package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.PricelistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricelistItemRepository extends JpaRepository<PricelistItemEntity, String> {
}
