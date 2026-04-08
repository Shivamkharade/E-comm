package com.e_comm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.CartItemEntity;
@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

}
