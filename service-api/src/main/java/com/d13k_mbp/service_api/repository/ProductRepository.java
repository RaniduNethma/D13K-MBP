package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "SELECT * FROM product WHERE product_name LIKE %?1% AND is_active=true", nativeQuery=true)
    public Page<ProductEntity> searchAllProducts(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM product WHERE product_name LIKE %?1% AND is_active=true", nativeQuery = true)
    public long countAllProducts(String searchText);

    @Query(value = "SELECT p FROM product p WHERE p.category_id = :category_id AND p.product_name LIKE %:searchText% OR :searchText IS NULL OR :searchText = ''", nativeQuery = true)
    public Page<ProductEntity> searchAllProductsByCategoryId(@Param("category_id") String categoryId,
                                                             @Param("searchText") String searchText,
                                                             Pageable pageable);

    @Query(value = "SELECT COUNT(p) FROM product p WHERE p.category_id = :category_id AND p.product_name LIKE %:searchText% OR :searchText IS NULL OR :searchText = ''", nativeQuery = true)
    public long countAllProductsByCategoryId(@Param("category_id") String categoryId,
                                             @Param("searchText") String searchText);
}
