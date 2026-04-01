package com.e_comm.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.petstore.api.CartApi;
import com.petstore.model.Cart;
import com.petstore.model.CartItemRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class CartController implements CartApi {

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return CartApi.super.getRequest();
	}

	@Override
	public ResponseEntity<Void> cartAddPost(@Valid CartItemRequest cartItemRequest) {
		// TODO Auto-generated method stub
		return CartApi.super.cartAddPost(cartItemRequest);
	}

	@Override
	public ResponseEntity<Cart> cartGet() {
		// TODO Auto-generated method stub
		return CartApi.super.cartGet();
	}

	@Override
	public ResponseEntity<Void> cartRemoveProductIdDelete(@NotNull String productId) {
		// TODO Auto-generated method stub
		return CartApi.super.cartRemoveProductIdDelete(productId);
	}

	
}
