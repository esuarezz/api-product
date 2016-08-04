package com.product.api.service;


import com.product.Product;
import com.product.api.exception.ProductNotFoundException;
import com.product.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Service to isolate the different layers, not really really necesarry because we could call from the api to the repo
 * directly, but I want to keep isolated the implementation from the api, keeping a better clean code
 */


@Service
public class ProductServiceImpl implements ProductService {


    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(long id, Product product) {
        return null;
    }

    @Override
    public Product setPrice(long id, Map<String, BigDecimal> prices) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductBy(long id) {
        Product productFound = productRepository.findOne(id);
        if(productFound==null){
            throw new ProductNotFoundException();
        }

        return productFound;
    }



}
