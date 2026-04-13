package com.e_comm.serviceimpl;

import java.math.BigDecimal;
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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	
	private final CartRepository cartRepository;
	
	private final UserRepository userRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository1,CartRepository cartRepository1,UserRepository userRepository1) {
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

	@Transactional
	@Override
	public OrderEntity ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest) {

	    UserEntity userEntity = userRepository.findByUsername(getLogedInUser()).orElseThrow();

	    CartEntity cartEntity = getCartLoggedinUser(userEntity);

	    OrderEntity orderEntity = new OrderEntity();

	    orderEntity.setUser(userEntity);

	    orderEntity.setPaymentMethod(
	        PaymentMethod.valueOf(checkoutRequest.getPaymentMethod().name()));

	    // Proper mapping
	    for (CartItemEntity cartItem : cartEntity.getItems()) {
	        OrderItemEntity item = new OrderItemEntity();
	        item.setProduct(cartItem.getProduct());
	        item.setPrice(cartItem.getPrice());
	        item.setQuantity(cartItem.getQuantity());

	        orderEntity.addItem(item); 
	    }

	    // total price auto via @PrePersist
	    orderRepository.save(orderEntity);

	    // clear cart
	    cartEntity.getItems().clear();
	    orderEntity.calculateTotalPrice();
	    cartEntity.setTotalPrice(BigDecimal.ZERO);
	    cartRepository.save(cartEntity);

	    return orderEntity;
	}
	
}








