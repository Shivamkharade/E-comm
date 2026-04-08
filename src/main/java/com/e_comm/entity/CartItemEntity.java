package com.e_comm.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(
	    name = "cart_item",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"})
	)
public class CartItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id",
				nullable = false)
	private CartEntity cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",
				nullable = false)
	private ProductEntity product;
	
	private int quantity;
	
	private BigDecimal price; // price at time of adding 
	
	private BigDecimal subtotal;
	
	@PrePersist
	@PreUpdate
	public void updateSubtotal() {
	    if (price != null) {
	        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
	    }
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public CartItemEntity(Long id, CartEntity cart, ProductEntity product, int quantity, BigDecimal price,
			BigDecimal subtotal) {
		super();
		this.id = id;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}
	
	public CartItemEntity() {
		
	}
}
