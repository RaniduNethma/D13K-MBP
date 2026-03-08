package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, String> {
}
