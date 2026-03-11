package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.PricelistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PricelistItemRepository extends JpaRepository<PricelistItemEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pricelist_item WHERE pricelist_id = :pricelistId", nativeQuery = true)
    void deleteAllByPricelistId(@Param("pricelistId") String pricelistId);

    @Modifying
    @Transactional
    @Query(value = "SELECT p FROM pricelist_item p WHERE p.product.product_id = :productId AND p.pricelist.pricelist_id = :pricelistId", nativeQuery = true)
    Optional<PricelistItemEntity> findByProductIdAndPricelistId(
            @Param("productId") String productId,
            @Param("pricelistId") String pricelistId);
}
