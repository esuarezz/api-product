package com.product.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO: internationalization in properties the message on reason
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="The product exists in the database")
public class ProductExistException extends RuntimeException {


}
