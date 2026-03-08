package com.d13k_mbp.service_api.repository;

import com.d13k_mbp.service_api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
