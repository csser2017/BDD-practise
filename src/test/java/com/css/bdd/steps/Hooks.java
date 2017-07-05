package com.css.bdd.steps;

import java.sql.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.css.bdd.framework.ExecutionException;
import com.css.bdd.framework.drivers.BrowserDriver;
import com.css.bdd.framework.drivers.BrowserTypes;
import com.css.bdd.framework.drivers.IDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

//Only browser, ios, android are available 
@ActiveProfiles(profiles = "browser")
public class Hooks {

	BrowserDriver driver = new BrowserDriver();

	@Before
	public void setUp() {
		System.out.println("I am set up!!");
		try {
			this.driver.createNewDriverInstance();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown(final Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = this.driver.getScreenShot();
			scenario.embed(screenshot, "image/png");
		}
		try {
			this.driver.destoryDriver();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
