package com.e_comm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.OrderEntity;
import com.e_comm.entity.UserEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>{

	List<OrderEntity> findByUser(UserEntity userEntity);

}
