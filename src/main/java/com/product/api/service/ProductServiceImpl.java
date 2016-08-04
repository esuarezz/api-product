package com.product.api.service;


import com.product.Product;
import com.product.api.exception.ProductExistException;
import com.product.api.exception.ProductNotFoundException;
import com.product.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service to isolate the different layers, not really really necessary because we could call from the api to the repo
 * directly, but I want to keep isolated the implementation from the api, keeping a better clean code
 * In this domain if we save and its created its ok, and just replace it, simple implementation
 */


@Service
public class ProductServiceImpl implements ProductService {


    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Adds the product if it is not in the database, we would check by name (with constraints)
     * In this domain is done in this way, but for example in online shops you keep all of them
     * because they are from different users. If we have one with name created we will thrown a custom exception
     * @param product
     * @return
     */
    @Override
    public Product add(Product product) {
        Product productAdded;
        try {
            productAdded = productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new ProductExistException();

        }
        return productAdded;

    }

    /**
     * In this domain , it the product exist , it will be updated, if not we will throw exception.
     * Another option it would be create it, but then perhaps it would be better use the put(SaveOrUpdate)
     * and remove add(post operation).
     *
     * @param id
     * @param product
     * @return
     */
    @Override
    public Product update(long id, Product product) {
        //check if id could be null
        this.getProductBy(id);

        return productRepository.save(product);
    }

    /**
     * Updates the price in the product , controlling the product exist and then is saved
     * @param id
     * @param prices
     * @return
     */
    @Override
    public Product setPrice(long id, Map<String, BigDecimal> prices) {
        Product productWithPriceUpdated = getProductPriceMerged(this.getProductBy(id), prices);
        return productRepository.save(productWithPriceUpdated);
    }

    /**
     * Gets the completed list of products
     * @return
     */
    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    /**
     * Gets the product identified by ID, if this product does not exist, it throws a custom exception
     * @param id
     * @return
     */
    @Override
    public Product getProductBy(long id) {

        Product productFound = productRepository.findOne(id);

        if(productFound==null){
            throw new ProductNotFoundException();
        }

        return productFound;
    }

    /**
     * Gets a new Map merged with both Maps. Gets the new map updated and just add the old values that should not
     * be updated
     * @param productToUpdate
     * @param pricesToUpdate
     * @return
     */
    private Product getProductPriceMerged(Product productToUpdate,Map pricesToUpdate){

        Map<String, BigDecimal> oldPrices = productToUpdate.getPrices();

        Map<String, BigDecimal> mapToMerge = new HashMap(pricesToUpdate);
        oldPrices.forEach(
                (k, v) -> mapToMerge.putIfAbsent(k, v)
        );
        productToUpdate.setPrices(mapToMerge);
        return productToUpdate;
    }



}
