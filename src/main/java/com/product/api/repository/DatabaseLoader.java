package com.product.api.repository;

import com.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DatabaseLoader(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.productRepository.save(new Product("name","description",new ArrayList(),new HashMap()));
        this.productRepository.save(new Product("name2","description2",new ArrayList(),new HashMap()));

    }
}
