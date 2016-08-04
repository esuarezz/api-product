package com.product.api.repository;

import com.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private Map prices;
    private Set tags,tags2;

    @Autowired
    public DatabaseLoader(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.init();

        this.productRepository.save(new Product("name","description",tags,prices));
        this.productRepository.save(new Product("name2","description2",tags,prices));

    }

    private void init(){
        prices = new HashMap();
        prices.put("USD",new BigDecimal(1.23));
        prices.put("Pounds",new BigDecimal(0.89));

        tags = new HashSet();
        tags.add("waterpoorf");
        tags.add("huge");

        tags2 = new HashSet();
        tags2.add("light");
        tags2.add("small");
    }
}
