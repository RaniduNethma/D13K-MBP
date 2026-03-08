package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, String> {
}
