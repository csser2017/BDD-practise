package com.css.bdd.steps;

import java.util.List;
import java.util.Map;

import com.css.bdd.flow.LogonFlow_163;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LogonStep {
	
	LogonFlow_163 logonFlow = new LogonFlow_163();
	
	@Given("^I have (\\d+) mailbox account$")
	public void i_have_mailbox_account(int arg1, DataTable arg2) throws Throwable {
	}

	@When("^I logon successfully with the account$")
	public void i_logon_successfully_with_the_account(List<Map<String,String>> userinfo) throws Throwable {
        logonFlow.OpenMailBox();
		logonFlow.LogonMailBox(userinfo.get(0).get("username"), userinfo.get(0).get("password"));
	}

	@Then("^I should see below welcome message on the mailbox$")
	public void i_should_see_below_welcome_message_on_the_mailbox(List<Map<String,String>> userinfo) throws Throwable {
         logonFlow.verifyLogonInfo(userinfo.get(0).get("msg"));
	}


}
