package com.e_comm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.CartEntity;
import com.e_comm.entity.UserEntity;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

	CartEntity findByUser(UserEntity user);
}
