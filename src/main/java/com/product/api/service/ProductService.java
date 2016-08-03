package com.product.api.service;

import com.product.Product;


import java.math.BigDecimal;
import java.util.Map;


public interface ProductService {

    Product add(Product product);
    Product update(long id, Product product);
    Iterable<Product> getProducts();
    Product getProductBy(long id);
    Product setPrice(long id, Map<String, BigDecimal> prices);

}
