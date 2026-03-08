package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.PricelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricelistRepository extends JpaRepository<PricelistEntity, String> {
}
