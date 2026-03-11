package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

//    @Query(value = "SELECT * FROM orders WHERE product_name LIKE %?1% AND is_active=true", nativeQuery=true)
//    public Page<OrderEntity> searchAllOrders(String searchText, Pageable pageable);
//
//    @Query(value = "SELECT COUNT(*) FROM orders WHERE order_name LIKE %?1% AND is_active=true", nativeQuery = true)
//    public long countAllOrders(String searchText);

    @Query(value = "SELECT * FROM orders WHERE CAST(order_status AS VARCHAR) LIKE %?1%", nativeQuery = true)
    public Page<OrderEntity> searchAllOrders(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM orders WHERE CAST(order_status AS VARCHAR) LIKE %?1%", nativeQuery = true)
    public long countAllOrders(String searchText);
}
