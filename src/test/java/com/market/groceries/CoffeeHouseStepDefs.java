package com.market.groceries;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CoffeeHouseStepDefs extends CustomerServiceTest {
	
	@Given("^The customer is a new customer$")
	public void the_customer_is_a_new_customer() throws Throwable {
	    throw new PendingException();
	}

	@When("^I want to add a new customer$")
	public void i_want_to_add_a_new_customer() throws Throwable {
	    throw new PendingException();
	}

	@Then("^the customer should be added in our system$")
	public void the_customer_should_be_added_in_our_system() throws Throwable {
	    throw new PendingException();
	}

	@Given("^The coffee is a new variety of coffee$")
	public void the_coffee_is_a_new_variety_of_coffee() throws Throwable {
	    throw new PendingException();
	}

	@When("^I want to add a variety of coffee$")
	public void i_want_to_add_a_variety_of_coffee() throws Throwable {
	    throw new PendingException();
	}

	@Then("^the coffee should be added in our inventory$")
	public void the_coffee_should_be_added_in_our_inventory() throws Throwable {
	    throw new PendingException();
	}

	@Given("^The variety of coffee exists with <name>$")
	public void the_variety_of_coffee_exists_with_name() throws Throwable {
	    throw new PendingException();
	}

	@When("^The customer wants to buy <quantity>$")
	public void the_customer_wants_to_buy_quantity() throws Throwable {
	    throw new PendingException();
	}

	@Then("^I verify the order <amount> while billing$")
	public void i_verify_the_order_amount_while_billing() throws Throwable {
	    throw new PendingException();
	}

	@Given("^The variety of <name> exists with <price>$")
	public void the_variety_of_name_exists_with_price() throws Throwable {
	    throw new PendingException();
	}

	@When("^The customer wants to buy <quantity> gm$")
	public void the_customer_wants_to_buy_quantity_gm() throws Throwable {
	    throw new PendingException();
	}

	@Then("^I verify the order <amount> while billing for each coffee$")
	public void i_verify_the_order_amount_while_billing_for_each_coffee() throws Throwable {
	    throw new PendingException();
	}
}
