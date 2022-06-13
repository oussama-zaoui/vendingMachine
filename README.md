# Vending Machine



## Description:
This is a vending machine api

### Getting started:
``` console
 git clone https://github.com/oussama-zaoui/vendingMachine
 cd vendingMachine
 chmod +x run.tests
 ./run.tests
```



## Tools & languages:
* SpringBoot (Framework)
* Java (logic)
* IntelliJ Idea (Text Editor)
* Docker and docker-compose
* Postman (Api testing tool)
* Postgresql (relational database management system)
* JWT (secure Endpoints)

## Requests
 ```http
POST /user/newUser
POST /login
GET /user/{usename}
PATCH /user/deposit/{amount}
POST /user/buy 
PATCH /user/reset
DELETE /user/delete/{username}
GET /product/{productId}
POST /product/newProduct
PATCH /product/{productId}
DELETE /product/{productId}
```


## Status Codes

vendingMachin returns the following status codes in its API:

| Status Code | Description             |
|:------------|:------------------------|
| 200         | `OK`                    |
| 403         | `FORBIDDEN`             |
| 401         | `UNAUTHORIZED`          |
| 400         | `BAD REQUEST`           |
| 404         | `NOT FOUND`             |
| 500         | `INTERNAL SERVER ERROR` |

