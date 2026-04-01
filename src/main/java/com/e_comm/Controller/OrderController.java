package com.e_comm.Controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.e_comm.service.OrderService;
import com.petstore.api.OrdersApi;
import com.petstore.model.CheckoutRequest;
import com.petstore.model.Order;

import jakarta.validation.Valid;

@RestController
public class OrderController implements OrdersApi {

	private final OrderService orderService; 
	
	private final ModelMapper mapper;
	
	public OrderController(OrderService orderService1) {
		this.orderService = orderService1;
		this.mapper = new ModelMapper();
	}

	@Override
	public ResponseEntity<Order> ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest) {
		// TODO Auto-generated method stub
		return OrdersApi.super.ordersCheckoutPost(checkoutRequest);
	}

	@Override
	public ResponseEntity<List<Order>> ordersGet() {
		// TODO Auto-generated method stub
		return OrdersApi.super.ordersGet();
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return OrdersApi.super.getRequest();
	}
}
