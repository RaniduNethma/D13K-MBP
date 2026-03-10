package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ProductCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, String> {

    @Query(value = "SELECT * FROM product_category WHERE category_name LIKE %?1% AND is_active=true", nativeQuery=true)
    public Page<ProductCategoryEntity> searchAllProductCategories(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM product_category WHERE category_name LIKE %?1% AND is_active=true", nativeQuery = true)
    public long countAllProductCategories(String searchText);
}
