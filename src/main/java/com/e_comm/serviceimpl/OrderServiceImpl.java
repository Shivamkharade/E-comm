package com.e_comm.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.OrderRepository;
import com.e_comm.service.OrderService;

@Service
@Validated
public class OrderServiceImpl implements OrderService {

	private final ModelMapper mapper;
	
	private final OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository1) {
		this.mapper = new ModelMapper();
		this.orderRepository = orderRepository1;
	}
	
	
	
}
