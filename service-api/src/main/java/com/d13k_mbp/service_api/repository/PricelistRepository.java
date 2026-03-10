package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.PricelistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PricelistRepository extends JpaRepository<PricelistEntity, String> {

    @Query(value = "SELECT * FROM pricelist WHERE pricelist_name LIKE %?1% AND is_active=true", nativeQuery=true)
    public Page<PricelistEntity> searchAllPricelists(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM pricelist WHERE pricelist_name LIKE %?1% AND is_active=true", nativeQuery = true)
    public long countAllPricelists(String searchText);
}
