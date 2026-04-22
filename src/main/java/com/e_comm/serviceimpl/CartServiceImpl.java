package com.e_comm.serviceimpl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.CartRepository;
import com.e_comm.Repository.ProductRepository;
import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.CartEntity;
import com.e_comm.entity.CartItemEntity;
import com.e_comm.entity.ProductEntity;
import com.e_comm.entity.UserEntity;
import com.e_comm.service.CartService;
import com.petstore.model.CartItemRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // Centralized cart creation
    private CartEntity getOrCreateCart(UserEntity user) {
        CartEntity cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        return cart;
    }

    // GET CART
    @Override
    public CartEntity cartGet() {
        UserEntity user = userRepository
        							.findByUsername(getLoggedInUsername())
        							.orElseThrow();

        return getOrCreateCart(user);
    }

    // ADD TO CART
    @Override
    public CartEntity cartAddPost(@Valid CartItemRequest request) {

        UserEntity user = userRepository
                .findByUsername(getLoggedInUsername())
                .orElseThrow();

        CartEntity cart = getOrCreateCart(user);

        ProductEntity product = productRepository
                .findByName(request.getProductName())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int quantity = request.getQuantity() != null ? request.getQuantity() : 1;

        // Validation
        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        Optional<CartItemEntity> existingItem = cart.getItems()
        														.stream()
        														.filter(item -> item.getProduct().getId().equals(product.getId()))
        														.findFirst();

        if (existingItem.isPresent()) {
            CartItemEntity item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(product.getPrice());
        } else {
            CartItemEntity newItem = new CartItemEntity();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice(product.getPrice());

            cart.getItems().add(newItem);
        }
        
        for (CartItemEntity item : cart.getItems()) {
            item.updateSubtotal();
        }

        cart.calculateTotalPrice();

        return cartRepository.save(cart);
    }

    // REMOVE PRODUCT FROM CART
    @Override
    public void cartRemoveProductNameDelete(@NotNull String productName) {

        UserEntity user = userRepository
                .findByUsername(getLoggedInUsername())
                .orElseThrow();

        CartEntity cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        ProductEntity product = productRepository
        										.findByName(productName)
        										.orElseThrow(() -> new RuntimeException("Product not found"));

        CartItemEntity itemToRemove = cart.getItems()
                									.stream()
                									.filter(item -> item.getProduct().getId().equals(product.getId()))
                									.findFirst()
                									.orElseThrow(() -> new RuntimeException("Product not in cart"));

        cart.getItems().remove(itemToRemove);

        cart.calculateTotalPrice();

        cartRepository.save(cart);
    }

    // CLEAR CART
    @Override
    public void cartClearDelete() {

        UserEntity user = userRepository
                .findByUsername(getLoggedInUsername())
                .orElseThrow();

        CartEntity cart = cartRepository.findByUser(user);
        if (cart == null) {
            return;
        }

        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);

        cartRepository.save(cart);
    }
}