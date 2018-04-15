# CoffeeHouse

All though I have given the name of the Project as Coffee House but it can be used given any shoping mart.
E-Software to manage different activities happening inside Coffee house.

Below are the various APIs built in which are available to carry out below mentioned activities.

1. Add a new customer
2. Build an order
3. Check the inventory
4. Generate a report of all the sales for a given day.

All the APIs are RESTfull APIs.

Customer - 
	JSON - 
		{
    		"firstName": "Varun",
    		"lastName": "Dhawan",
    		"phoneNumber": "919999911111",
    		"emailId": "varun.dhawan@abc.com"
		}
	URLs - 
		1. POST - http://localhost:8080/coffee-house/customer/
		2. GET - http://localhost:8080/coffee-house/customer/{fisrtName}/{lastName}/{phoneNumber}

Product - 
	JSON - 
		{
    		"name": "Kents",
    		"variety": "Indian",
    		"availableQuantity": 10000.00,
    		"pricePerUnit": 100.00,
    		"unit": "GRAM"
		}
	URLs - 
		1. POST - http://localhost:8080/coffee-house/product/
		2. GET - http://localhost:8080/coffee-house/product/{name}/{variety}
		3. PUT - http://localhost:8080/coffee-house/product/
		4. GET - http://localhost:8080/coffee-house/product/allAvailable

