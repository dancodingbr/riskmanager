const { Given, Then, When } = require("@cucumber/cucumber");
const { spec } = require("pactum");

const SERVER_URL = "http://localhost:8080/riskmanager/api";

Given("a related {string}", function (problem) {
  this.problem = problem;
});

Given("an {string} done", function (actionPlan) {
  this.actionPlan = actionPlan;
});

Given(
  "a {string} occurrence of this mitigated problem",
  function (probabilityLevel) {
    this.probabilityLevel = probabilityLevel;
  }
);

Given("an {string} of this mitigated problem", function (impactLevel) {
  this.impactLevel = impactLevel;
});

When(
  "the system calculates a risk level using a risk assessment matrix",
  function () {
    this.response = spec()
      .get(SERVER_URL + "/analyzing-results/")
      .withQueryParams("problem", encodeURIComponent(this.problem))
      .withQueryParams("actionPlan", encodeURIComponent(this.actionPlan))
      .withQueryParams(
        "probabilityLevel",
        encodeURIComponent(this.probabilityLevel)
      )
      .withQueryParams("impactLevel", encodeURIComponent(this.impactLevel));
  }
);

Then(
  "this result in the {string} chance to occurs the problem again",
  function (riskLevel) {
    return this.response.expectBodyContains(riskLevel);
  }
);

Given("a {string} risk calculated", function (riskLevel) {
  this.riskLevel = riskLevel;
});

When("the user saves this analyzed result", function () {
  const analyzedResult = {
    problem: this.problem,
    actionPlan: this.actionPlan,
    probabilityLevel: this.probabilityLevel,
    impactLevel: this.impactLevel,
    riskLevel: this.riskLevel,
  };

  this.response = spec()
    .post(SERVER_URL + "/analyzed-results/")
    .withBody(analyzedResult);
});

Then("shows that operation was {string}", function (operationStatus) {
  return this.response.expectStatus(200).expectBodyContains(operationStatus);
});

When("the system gets the analyzed results associated", function () {
  this.response = spec()
    .get(SERVER_URL + "/analyzed-results/")
    .withQueryParams("problem", encodeURIComponent(this.problem));
});

Then("shows {string}", function (actionPlan) {
  //  return this.response.expectStatus(200).expectBodyContains(actionPlan);
  this.actionPlan = actionPlan;
});

Then("{string} of each analyzed result", function (riskLevel) {
  //  return this.response.expectStatus(200).expectBodyContains(riskLevel);
  return this.response
    .expectStatus(200)
    .expectBodyContains(this.actionPlan)
    .expectBodyContains(riskLevel);
});
