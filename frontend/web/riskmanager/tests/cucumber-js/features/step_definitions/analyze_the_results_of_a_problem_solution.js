const { Given, Then, When } = require("@cucumber/cucumber");
const { spec } = require("pactum");

const SERVER_URL = 'http://localhost:8080/riskmanager/api';

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

Given("an {string} impact of this mitigated problem", function (impactLevel) {
  this.impactLevel = impactLevel;
});

When(
  "the system calculates a risk level using a risk assessment matrix",
  function () {
    this.response = spec()
      .get(SERVER_URL + "/analyzing-results/")
      .withQueryParams("problem", encodeURIComponent(this.problem))
      .withQueryParams("actionPlan", encodeURIComponent(this.actionPlan))
      .withQueryParams("probabilityLevel", encodeURIComponent(this.probabilityLevel))
      .withQueryParams("impactLevel", encodeURIComponent(this.impactLevel));
  }
);

Then(
  "this result in the {string} chance to occurs the problem again",
  function (riskLevel) {
    return this.response.expectBodyContains(riskLevel);
  }
);
