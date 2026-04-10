package com.e_comm.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Enum.PaymentMethod;
import com.e_comm.Repository.CartRepository;
import com.e_comm.Repository.OrderRepository;
import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.CartEntity;
import com.e_comm.entity.CartItemEntity;
import com.e_comm.entity.OrderEntity;
import com.e_comm.entity.OrderItemEntity;
import com.e_comm.entity.UserEntity;
import com.e_comm.service.OrderService;
import com.petstore.model.CheckoutRequest;
import com.petstore.model.Order;

import jakarta.validation.Valid;

@Service
@Validated
public class OrderServiceImpl implements OrderService {

	private final ModelMapper mapper;
	
	private final OrderRepository orderRepository;
	
	private final CartRepository cartRepository;
	
	private final UserRepository userRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository1,CartRepository cartRepository1,UserRepository userRepository1) {
		this.mapper = new ModelMapper();
		this.orderRepository = orderRepository1;
		this.cartRepository = cartRepository1;
		this.userRepository = userRepository1;
	}
	
	private String getLogedInUser() {
		return SecurityContextHolder.getContext()
									.getAuthentication()
									.getName();
	}
	
	private CartEntity getCartLoggedinUser(UserEntity user) {
		CartEntity cartEntity = cartRepository.findByUser(user);
		return cartEntity;
	}

	@Override
	public List<OrderEntity> ordersGet() {
		UserEntity userEntity = userRepository.findByUsername(getLogedInUser()).orElseThrow();
		return orderRepository.findByUser(userEntity);
	}

	@Override
	public OrderEntity ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest) {
		
		//logged in user
		UserEntity userEntity = userRepository.findByUsername(getLogedInUser()).orElseThrow();
		
		//logged in user cart
		CartEntity cartEntity = getCartLoggedinUser(userEntity);
		
		//creating new order for the logged in user
		OrderEntity orderEntity = new OrderEntity();
		
		//setting the user
		orderEntity.setUser(userEntity);
		
		//payment method selecting by user
		orderEntity.setPaymentMethod(PaymentMethod.valueOf(checkoutRequest.getPaymentMethod().name()));
		
		//getting items from the cart to the order list 
		List<OrderItemEntity> itemList = cartEntity.getItems().stream()
															  .map(cartItem -> {
																  OrderItemEntity item = mapper.map(cartItem, OrderItemEntity.class);
																  return item;
															  })
															  .toList();
		//saving the item list
		orderEntity.setItems(itemList);
		
		//calculating total price 
		orderEntity.calculateTotalPrice();
		
		//saving the order
		orderRepository.save(orderEntity);
		
		//after save clearing cart
		cartEntity.getItems().clear();
		
		cartRepository.save(cartEntity);
		
		//return order 
		return orderEntity;
	}
	
}








