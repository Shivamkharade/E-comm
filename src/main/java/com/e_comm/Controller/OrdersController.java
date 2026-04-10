package com.e_comm.Controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.e_comm.service.OrderService;
import com.petstore.api.OrdersApi;
import com.petstore.model.CheckoutRequest;
import com.petstore.model.Order;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class OrdersController implements OrdersApi {

	private final OrderService orderService;
	
	private final ModelMapper mapper;
	
	public OrdersController(OrderService orderService1) {
		this.orderService = orderService1;
		this.mapper = new ModelMapper();
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return OrdersApi.super.getRequest();
	}

	@Override
	public ResponseEntity<Order> ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest) {
		Order order = mapper.map(orderService.ordersCheckoutPost(checkoutRequest), Order.class);
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Order>> ordersGet() {
		List<Order> list = orderService.ordersGet().stream()
													.map(entity -> mapper.map(entity, Order.class))
													.toList();
		return ResponseEntity.ok(list);
	}
	
	
}
