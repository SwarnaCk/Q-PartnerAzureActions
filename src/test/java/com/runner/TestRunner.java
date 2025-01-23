package com.runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources/features",
                 glue = {"com.stepdefinitions","com.hooks"},
                 plugin = {
                        "pretty", 
                        "html:target/cucumber-reports/cucumber.html",
                        "json:target/cucumber-reports/cucumber.json"
                })
public class TestRunner extends AbstractTestNGCucumberTests {

}
