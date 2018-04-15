#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Coffee House feature

  @tag1
  Scenario: Add a new customer
    Given The customer is a new customer
    When I want to add a new customer
    Then the customer should be added in our system
    
  @tag2
  Scenario: Add a new variety of Coffee
    Given The coffee is a new variety of coffee
    When I want to add a variety of coffee
    Then the coffee should be added in our inventory
    
  @tag3
  Scenario Outline: A customer places an order
  	Given The variety of coffee exists with <name>
  	When The customer wants to buy <quantity>
  	Then I verify the order <amount> while billing

    Examples: 
      | Arabica   | 100 | 10000 |
      | Arabica   | 200 | 20000 |
      | Kents     | 100 | 50000 |
			| Cauvery   | 100 | 10000 |
			
	@tag4
  Scenario Outline: A customer places an order
  	Given The variety of <name> exists with <price>
  	When The customer wants to buy <quantity> gm
  	Then I verify the order <amount> while billing for each coffee

    Examples: 
      | Arabica   | 100 | 100 | 10000 |
      | Arabica   | 200 | 200 | 20000 |
      | Kents     | 100 | 500 | 50000 |
			| Cauvery   | 100 | 100 | 10000 |
			