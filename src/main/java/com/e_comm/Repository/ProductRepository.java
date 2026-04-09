package com.e_comm.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_comm.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String>{

	Optional<ProductEntity> findByName(String name);
	
	void deleteByName(String name);
	
	boolean existsByName(String name);
}
