package com.e_comm.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.CartRepository;
import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.CartEntity;
import com.e_comm.entity.UserEntity;
import com.e_comm.service.CartService;
import com.petstore.model.CartItemRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class CartServiceImpl implements CartService{

	private final CartRepository cartRepository;
	
	private final UserRepository userRepository;
	
	private final ModelMapper mapper;
	
	
	
	public CartServiceImpl(CartRepository cartRepository1,UserRepository userRepository1) {
		this.cartRepository =cartRepository1;
		this.userRepository = userRepository1;
		this.mapper = new ModelMapper();
	}
	
	private String getLoggedInUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
		
	}
	@Override
	public CartEntity cartGet() {
		UserEntity userEntity = userRepository.findByUsername(getLoggedInUsername()).orElseThrow();
		CartEntity cartEntity = cartRepository.findbyUserEntity(userEntity);
		
		if (cartEntity == null) {
			cartEntity = new CartEntity();
			cartEntity.setUser(userEntity);
			cartRepository.save(cartEntity);
		}
		return cartEntity;
	}

	@Override
	public CartEntity cartAddPost(@Valid CartItemRequest cartItemRequest) {
		UserEntity userEntity = userRepository.findByUsername(getLoggedInUsername()).orElseThrow();
		CartEntity cartEntity = cartRepository.findbyUserEntity(userEntity);
		return null;
	}

	@Override
	public void cartRemoveProductNameDelete(@NotNull String productName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartClearDelete() {
		// TODO Auto-generated method stub
		
	}
	
}
