package com.e_comm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.CartEntity;
import com.e_comm.entity.UserEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

	CartEntity findbyUserEntity(UserEntity userEntity);
}
