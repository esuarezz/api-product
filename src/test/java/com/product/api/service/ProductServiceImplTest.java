package com.product.api.service;

import com.product.Product;
import com.product.ProductApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ProductApplication.class)
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

        Product productAdded = productService.add(new Product("name", "description", tags, prices));
        Assert.assertNotNull(productAdded);
        Assert.assertEquals(productAdded.getName(),"name");
        Assert.assertEquals(productAdded.getDescription(),"description");
        Assert.assertEquals(productAdded.getTags().size(),2);
        System.out.println(productAdded);

    }

    @Test
    public void getProducts() throws Exception {
        List<Product> products = productService.getProducts();

        Assert.assertEquals(products.size(),2);

    }


    @Test
    public void update() throws Exception {

    }

    @Test
    public void setPrice() throws Exception {

    }

    @Test
    public void getProductBy() throws Exception {

    }


}