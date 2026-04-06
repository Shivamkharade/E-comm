package com.e_comm.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.CartRepository;
import com.e_comm.entity.CartEntity;
import com.e_comm.service.CartService;

@Service
@Validated
public class CartServiceImpl implements CartService{

	private final CartRepository cartRepository;
	
	private final ModelMapper mapper;
	
	public CartServiceImpl(CartRepository cartRepository1) {
		this.cartRepository =cartRepository1;
		this.mapper = new ModelMapper();
	}

	@Override
	public CartEntity cartGet() {
		return null;
	}
	
	
	
	
}
