package com.product.api;

import com.product.Product;
import com.product.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping(
		value = "/api/v1/products",
		consumes = {"application/json"},
		produces = {"application/json"})
public class ProductApi {

	ProductService productService;

	@Autowired
	public ProductApi(ProductService productService) {
		this.productService = productService;
	}


	@RequestMapping(method= RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Product add(@Valid @RequestBody Product product) {
		return productService.add(product);
	}


	//If is not defined the product, return CREATED
	@RequestMapping(method= RequestMethod.PUT,value = "/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Product update(@Valid @PathVariable("id") Long id, @PathVariable Product product) {

		return productService.update(id,product);
	}


	@RequestMapping(method= RequestMethod.PUT,value = "/{id}/price")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Product price(@Valid @PathVariable long id,@Valid Map price) {

		return productService.setPrice(id,price);
	}


	@RequestMapping(method= RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Product> getProducts() {

		return productService.getProducts();
	}


	@RequestMapping(method= RequestMethod.GET,value = "/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Product getProductBy(@Valid @PathVariable long id) {

		return productService.getProductBy(id);
	}



}
