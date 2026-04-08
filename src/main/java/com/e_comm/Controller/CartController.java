package com.e_comm.Controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.e_comm.service.CartService;
import com.petstore.api.CartApi;
import com.petstore.model.Cart;
import com.petstore.model.CartItemRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class CartController implements CartApi {

	private final CartService cartService;
	
	private final ModelMapper mapper;
	
	public CartController(CartService cartService1) {
		this.cartService = cartService1;
		this.mapper = new ModelMapper();
	}

	@Override
	public ResponseEntity<Cart> cartAddPost(@Valid CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		return CartApi.super.cartAddPost(cartItemRequest);
	}

	@Override
	public ResponseEntity<Void> cartClearDelete() {
		// TODO Auto-generated method stub
		return CartApi.super.cartClearDelete();
	}

	@Override
	public ResponseEntity<Cart> cartGet() {
		// TODO Auto-generated method stub
		return CartApi.super.cartGet();
	}

	@Override
	public ResponseEntity<Void> cartRemoveProductNameDelete(@NotNull String productName) {
		// TODO Auto-generated method stub
		return CartApi.super.cartRemoveProductNameDelete(productName);
	}
	
	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return CartApi.super.getRequest();
	}

}
