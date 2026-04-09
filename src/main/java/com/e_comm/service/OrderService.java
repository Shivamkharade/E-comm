package com.e_comm.service;

import java.util.List;

import com.e_comm.entity.OrderEntity;
import com.petstore.model.CheckoutRequest;

import jakarta.validation.Valid;

public interface OrderService {

	List<OrderEntity> ordersGet();

	OrderEntity ordersCheckoutPost(@Valid CheckoutRequest checkoutRequest);

}
