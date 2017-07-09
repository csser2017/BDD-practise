package com.css.bdd.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/resources/features",tags={"@163logon"},glue={"com.css.bdd.steps"})
public class logonRunner {

}
