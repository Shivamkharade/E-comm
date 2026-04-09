package com.e_comm.service;

import com.e_comm.entity.CartEntity;
import com.petstore.model.CartItemRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CartService {

	CartEntity cartGet();

	CartEntity cartAddPost(@Valid CartItemRequest cartItemRequest);

	void cartRemoveProductNameDelete(@NotNull String productName);

	void cartClearDelete();

}
