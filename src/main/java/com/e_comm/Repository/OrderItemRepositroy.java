package com.e_comm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.OrderItemEntity;
@Repository
public interface OrderItemRepositroy extends JpaRepository<OrderItemEntity, Long> {

}
