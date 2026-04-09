package com.e_comm.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.OrderRepository;
import com.e_comm.entity.OrderEntity;
import com.e_comm.service.OrderService;
import com.petstore.model.CheckoutRequest;

import jakarta.validation.Valid;

@Service
@Validated
public class OrderServiceImpl implements OrderService {

	private final ModelMapper mapper;
	
	private final OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository1) {
		this.mapper = new ModelMapper();
		this.orderRepository = orderRepository1;
	}

	@Override
	public List<OrderEntity> ordersGet() {
		return orderRepository.findAll();
	}

	@Override
	public OrderEntity ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
