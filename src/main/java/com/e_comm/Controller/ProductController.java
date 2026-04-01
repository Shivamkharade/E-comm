package com.e_comm.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ProductService productService;

	@Override
	public Optional<NativeWebRequest> getRequest() {
		// TODO Auto-generated method stub
		return ProductsApi.super.getRequest();
	}

	@Override
	public ResponseEntity<List<Product>> productsGet() {
		// TODO Auto-generated method stub
		return ProductsApi.super.productsGet();
	}

	@Override
	public ResponseEntity<Void> productsIdDelete(@NotNull String id) {
		// TODO Auto-generated method stub
		return ProductsApi.super.productsIdDelete(id);
	}

	@Override
	public ResponseEntity<Product> productsIdGet(@NotNull String id) {
		// TODO Auto-generated method stub
		return ProductsApi.super.productsIdGet(id);
	}

	@Override
	public ResponseEntity<Void> productsPost(@Valid Product product) {
		// TODO Auto-generated method stub
		return ProductsApi.super.productsPost(product);
	}

	
	
	
}
