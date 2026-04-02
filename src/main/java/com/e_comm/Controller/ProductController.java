package com.e_comm.Controller;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.e_comm.service.ProductService;
import com.petstore.api.ProductsApi;
import com.petstore.model.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class ProductController implements ProductsApi{

	
	private final ProductService productService;
	
	private final ModelMapper mapper;
	
	public ProductController(ProductService productService1) {
		this.productService = productService1;
		this.mapper = new ModelMapper();
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return ProductsApi.super.getRequest();
	}

	@Override
	public ResponseEntity<List<Product>> productsGet() {
		List<Product> list = productService.productsGet().stream()
														  .map(entity -> mapper.map(entity, Product.class))
														  .toList();
		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<Product> productsIdGet(@NotNull String id) {
		Product product = mapper.map(productService.productsIdGet(id), Product.class);
		return ResponseEntity.ok(product);
	}
	
	@Override
	public ResponseEntity<Void> productsIdDelete(@NotNull String id) {
		productService.productsIdDelete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Product> productsPost(@Valid Product product) {
		Product product2 = mapper.map(productService.productsPost(product), Product.class);
		return new ResponseEntity<>(product2,HttpStatus.CREATED);
	}	
	
	
	
}
