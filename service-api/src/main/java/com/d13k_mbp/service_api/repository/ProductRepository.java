package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
