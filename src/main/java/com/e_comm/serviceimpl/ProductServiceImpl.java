package com.e_comm.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.ProductRepository;
import com.e_comm.entity.ProductEntity;
import com.e_comm.service.ProductService;
import com.petstore.model.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	private final ModelMapper mapper;
	
	public ProductServiceImpl(ProductRepository productRepository1) {
		this.productRepository = productRepository1;
		this.mapper = new ModelMapper();
	}

	@Override
	public List<ProductEntity> productsGet() {
		return productRepository.findAll();
	}

	@Override
	public ProductEntity productsIdGet(@NotNull String id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id : " + id));
	}

	@Override
	public void productsIdDelete(@NotNull String id) {
		if (!productRepository.existsById(id)) {
			throw new RuntimeException("Product not found with id : " + id);
		}
		productRepository.deleteById(id);
	}

	@Override
	public ProductEntity productsPost(@Valid Product product) {
		ProductEntity entity = mapper.map(product, ProductEntity.class);
		return productRepository.save(entity);
	}
	
}
