const {Given, Then, When} = require('@cucumber/cucumber');

const CLIENT_URL = 'http://localhost:4200/';
const SERVER_URL = 'http://localhost:8080/riskmanager/api';

Given("a home page", function () {
  // Write code here that turns the phrase above into concrete actions
  return browser.navigateTo(CLIENT_URL);
});

When("client clicks on {string}", function (link) {
  // Write code here that turns the phrase above into concrete actions
  browser.assert.attributeContains('#hello-message', 'href', SERVER_URL + link);
  return browser.click('#hello-message');
});

Then("shows a {string}", function (message) {
  // Write code here that turns the phrase above into concrete actions
  return browser.assert.containsText('body', message);
});
