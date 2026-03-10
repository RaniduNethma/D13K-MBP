package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.PricelistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PricelistItemRepository extends JpaRepository<PricelistItemEntity, String> {

    @Modifying
    @Query(value = "DELETE FROM pricelist_item WHERE pricelist_id = :pricelistId", nativeQuery = true)
    void deleteAllByPricelistId(@Param("pricelistId") String pricelistId);
}
