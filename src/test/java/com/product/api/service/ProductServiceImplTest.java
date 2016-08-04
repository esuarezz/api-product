package com.product.api.service;

import com.product.Product;
import com.product.api.exception.ProductExistException;
import com.product.api.exception.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceImplTest {
    private Map prices;
    private Set tags;

    @Autowired
    private ProductService productService;

    @Before
    public void init(){


        prices = new HashMap();
        prices.put("USD",new BigDecimal(1.23));
        prices.put("Pounds",new BigDecimal(0.89));

        tags = new HashSet();
        tags.add("waterpoorf");
        tags.add("huge");

    }

    @Test
    public void add() throws Exception {

        Product productAdded = productService.add(new Product("name5", "description", tags, prices));
        Assert.assertNotNull(productAdded);
        Assert.assertEquals(productAdded.getName(),"name5");
        Assert.assertEquals(productAdded.getDescription(),"description");
        Assert.assertEquals(productAdded.getTags().size(),2);
        System.out.println(productAdded);

    }
    @Test(expected = ProductExistException.class)
    public void addException() throws Exception {

         productService.add(new Product("name5", "description", tags, prices));
    }

    @Test
    public void getProducts() throws Exception {

        List<Product> products = productService.getProducts();
        Assert.assertEquals(products.size(),3);

    }

    @Test
    public void getProductByOk() throws Exception {

        Assert.assertNotNull(productService.getProductBy(1));
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductByNotFoundException() throws Exception {

        Assert.assertNotNull(productService.getProductBy(12l));
    }

    @Test
    public void update() throws Exception {

        productService.update(1, new Product("nameToUpdate", "descriptionToUpdate", tags, prices));
    }

    @Test
    public void setPrice() throws Exception {
        HashMap<String,BigDecimal> newPrices = new HashMap();
        newPrices.put("USD",new BigDecimal(500));
        newPrices.put("Bitcoins",new BigDecimal(700));

        productService.setPrice(productService.getProductBy(1).getId(),newPrices);
        Map<String, BigDecimal> pricesUpdated = productService.getProductBy(1).getPrices();
        Assert.assertEquals(productService.getProductBy(1).getPrices().size(),3);

        Assert.assertEquals(pricesUpdated.get("USD"),new BigDecimal("500.00"));
    }



}