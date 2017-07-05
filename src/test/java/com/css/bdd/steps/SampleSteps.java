package com.css.bdd.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleSteps {
	@Given("^I have received an email from \"([^\"]*)\"$")
	public void i_have_received_an_email_from(String arg1) throws Throwable {
		System.out.println("I am Given method!!");
	}

	@When("^I Sign in$")
	public void i_Sign_in() throws Throwable {
		System.out.println("I am When method!!");
	}

	@Then("^I should see one email from \"([^\"]*)\" in my inbox$")
	public void i_should_see_one_email_from_in_my_inbox(String arg1)
			throws Throwable {
		System.out.println("I am Then method!!");
	}
}
