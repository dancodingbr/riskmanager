package com.dancodingbr.riskmanager.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = "pretty", 
	features = "src/test/resources/features")
public class RunCucumberTest {

}
