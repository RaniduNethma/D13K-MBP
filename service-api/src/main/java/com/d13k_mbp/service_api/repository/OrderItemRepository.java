package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_item WHERE order_id = :orderId", nativeQuery = true)
    void deleteAllByOrderId(@Param("orderId") String orderId);
}
