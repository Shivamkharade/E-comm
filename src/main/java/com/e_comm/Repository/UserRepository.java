package com.e_comm.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity>  findByUsername(String username);
}
