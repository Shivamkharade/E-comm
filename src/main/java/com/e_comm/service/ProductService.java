package com.e_comm.service;

import java.util.List;

import com.e_comm.entity.ProductEntity;
import com.petstore.model.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ProductService {

	List<ProductEntity> productsGet();

	ProductEntity productsIdGet(@NotNull String id);

	void productsIdDelete(@NotNull String id);

	ProductEntity productsPost(@Valid Product product);

}
