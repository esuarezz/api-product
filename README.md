Some products are added by default.
Basic Entity Product representation cause we are using embedded database
 
Example Json schema used:

`{"name": "name",
    "description": "desc",
     "tags": [
         "tag1",
         "tag12"
     ],
     "prices": {
         "USD": 2,
         "Euro": 2.40
     }
 }`
         
          
API:


Add a new product:            
   **POST [/api/v1/products/]()**    
   Response HTTP -> Created if it was successful,             
   Response HTTP -> Conflict if a element was in the database with same name 
 
 
Update a product:            
   **PUT [/api/v1/products/{id}]()**    
   Response HTTP -> OK, it was updated successfully             
   Response HTTP -> Not found, it was not in the database the product 
            
               
Set a price for product:       
   **PUT [/api/v1/products/{id}/price]()**
   Response HTTP -> OK, it was updated successfully             
   Response HTTP -> Not found, it was not in the database the product 

Get all products             
   **GET [/api/v1/products]()**
   Response HTTP -> OK, List of products will be returned,
    it could be empty

Get a specific product by id             
   **GET [/api/v1/products/{id}]()**
   Response HTTP -> OK, product will be returned
   Response HTTP -> Not found, it was not in the database the product
 
   