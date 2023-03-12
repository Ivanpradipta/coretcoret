## @GetMapping("/products")
API untuk menampilkan semua product

    contoh request 
    GET localhost:8080/products
    
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


##   @PostMapping("/products")
API untuk menambahkan produk
request dalam bentuk json dan dikirmkan lewat request body
contoh request :

    POST localhost:8080/products
    
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
 

  ## @GetMapping("/products/{id}")
  API untuk mendapatkan detail produk detail id diberikan lewat path variable contoh request :
  
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
   
## @PutMapping("/products/{id}")
API untuk mengupdate produk. id produk diberikan lewat path variable dan detail produk dikirimkan lewat request body
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

## @PatchMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
API untuk menambah atau mengurangi stok produk. operasi yang dapat diberikan adalah "add" untuk menambah dan "deduct" untuk mengurangi stok
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

## @DeleteMapping("/products/{id}")
API untuk menghapus Produk
id produk yang akan dihapus diberikan lewat path variable
contoh request :

    DELETE http://localhost:8080/products/0

    response :
        empty
