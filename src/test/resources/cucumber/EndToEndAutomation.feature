	Feature: Purchase an order from the ECommerce website
	
	Background: Launch the application
	Given The user landed on the ECommerce website login page
	
	
	Scenario Outline: Verify the product added in the cart
	Given the user logged in using the "<userName>" as Email and "<password>" as password
	When the user added a product "<itemsToBeAdded>" in the cart
	And the user verifies the order "<itemsToBeAdded>" in My Cart section
	Then the user checks out and selects the country
	And the user clicks the Place Order button
	And The user verifies the order ID and order confirmation message "THANK YOU FOR THE ORDER."
	Examples:
	userName				|		password		|		itemsToBeAdded		|
sourabhdeshpande@gmail.com	|S@uRabh@1990			|ZARA COAT 3				|


	
	
	