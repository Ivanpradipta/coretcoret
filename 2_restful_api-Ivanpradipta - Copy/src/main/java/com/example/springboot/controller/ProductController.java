package com.example.springboot.controller;

import com.example.springboot.entity.Product;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    CollectionModel<Product> getAll() {
        List<Product> allProducts = repository.findAll();
        for (Product product : allProducts) {
            long productId = product.getId();
            product.removeLinks();
            Link selfLink = linkTo(ProductController.class).slash("products").slash(productId).withSelfRel();
            product.add(selfLink);
        }

        Link link = linkTo(ProductController.class).slash("products").withSelfRel();
        return CollectionModel.of(allProducts, link);
    }

    

    /*
    API untuk menampilkan semua product
    request body None


    
    contoh response body :
    {
    "_embedded": {
        "productList": [
            {
                "id": 0,
                "name": "Jam tangan",
                "stock": 15,
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/products/0"
                    }
                }
            }
        ]
    },
    "_links": {
        "all": {
            "href": "http://localhost:8080/products"
        }
    }
}
    * */
//    @GetMapping("/products")




    /*
    API untuk menambahkan produk
    request dalam bentuk json dan dikirmkan lewat request body
    contoh request :
    {
        "id":0,
        "name":"jam",
        "stock":10
    }

    Contoh reponse:
    {
        "id": 0,
        "name": "jam",
        "stock": 10,
        "_links": {
            "self": {
                "href": "http://localhost:8080/products/0"
            }
        }
    }
    * */
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
    Product product = repository.save(newProduct);
    product.removeLinks();
    Link selfLink = linkTo(ProductController.class).slash("products").slash(product.getId()).withSelfRel();
    product.add(selfLink);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
    }






//    @PostMapping("/products")

    /*
     API untuk mendapatkan detail produk
    detail id diberikan lewat path variable
    contoh request :
    GET http://localhost:8080/products/0

    contoh response :
    {
        "id": 0,
        "name": "Jam tangan",
        "stock": 10,
        "_links": {
            "self": {
                "href": "http://localhost:8080/products/0"
            }
        }
    }
     * */
//    @GetMapping("/products/{id}")

@GetMapping("/products/{id}")
    EntityModel<Product> getOne(@PathVariable Long id) {
        final Product product = repository.findById(id).get();
        return EntityModel.of(product, //
                linkTo(ProductController.class).slash("products").slash(id).withSelfRel(),
                linkTo(ProductController.class).slash("products").withRel("all"));
    }


/*
    API untuk mengupdate produk
    id produk diberikan lewat path variable dan detail produk dikirimkan lewat request body
    contoh request :
    PUT http://localhost:8080/products/0
    request body :
    {
        "id":0,
        "name":"Jam tangan",
        "stock":10
    }

    response body :
    {
        "id": 0,
        "name": "Jam tangan",
        "stock": 10,
        "_links": {
            "self": {
                "href": "http://localhost:8080/products/0"
            }
        }
    }

    
* */
@PutMapping("/products/{id}")
Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
    return repository.findById(id)
            .map(product -> {
                product.setName(updatedProduct.getName());
                product.setStock(updatedProduct.getStock());
                return repository.save(product);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
}

//    @PutMapping("/products/{id}")


   /* API untuk menambah atau mengurangi stok produk
    operasi yang dapat diberikan adalah "add" untuk menambah dan "deduct" untuk mengurangi stok
    id produk diberikan lewat path variable dan detail stock/operasi dikirimkan lewat request body
    contoh request :
    PATCH http://localhost:8080/products/0
    request body :
    {
        "amount":5,
        "operation":"add"
    }

    response body :
    {
        "id": 0,
        "name": "Jam tangan",
        "stock": 15,
        "_links": {
            "self": {
                "href": "http://localhost:8080/products/0"
            }
        }
    }*/
//    @PatchMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)

@PatchMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
    try {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        int amount = (int) payload.get("amount");
        String operation = (String) payload.get("operation");

        if (operation.equalsIgnoreCase("add")) {
            product.setStock(product.getStock() + amount);
        } else if (operation.equalsIgnoreCase("deduct")) {
            product.setStock(product.getStock() - amount);
        } else {
            return ResponseEntity.badRequest().build();
        }

        Product updatedProduct = repository.save(product);
        updatedProduct.removeLinks();
        Link selfLink = linkTo(ProductController.class).slash("products").slash(updatedProduct.getId()).withSelfRel();
        updatedProduct.add(selfLink);

        return ResponseEntity.ok(updatedProduct);
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


/*
    API untuk menghapus Produk
    id produk yang akan dihapus diberikan lewat path variable
    contoh request :
    DELETE http://localhost:8080/products/0

    response :
    empty
* */
//    @DeleteMapping("/products/{id}")
@DeleteMapping("/products/{id}")
public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
    repository.deleteById(id);
    return ResponseEntity.ok().build();
}



}