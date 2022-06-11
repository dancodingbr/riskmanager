package com.dancodingbr.riskmanager.bdd.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HelloStepDefinitions {

	HttpUriRequest request;	
	HttpResponse response;
	@Value("${server.port}")
	private int port;

	@Given("a {string}")
	public void a(String request) {
		this.request = new HttpGet("http://localhost:" + this.port + request);
	}
	@When("client calls it")
	public void client_calls_it() throws ClientProtocolException, IOException {
		this.response = HttpClientBuilder.create().build().execute(this.request);
	}
	@Then("it receives a {string}")
	public void it_receives_a(String message) throws ParseException, IOException {
		String atualMessage = EntityUtils.toString(this.response.getEntity());
		assertEquals(message, atualMessage);
	}
	
}
