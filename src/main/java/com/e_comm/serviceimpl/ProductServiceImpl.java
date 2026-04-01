package com.e_comm.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.e_comm.Repository.ProductRepository;
import com.e_comm.service.ProductService;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	
}
