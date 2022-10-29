const { Given, Then, When } = require("@cucumber/cucumber");
const { spec } = require("pactum");

const SERVER_URL = "http://localhost:8080/riskmanager/api";

Given("a related {string},{string}", function (problemId, problemDescription) {
  this.problem = {
    id: problemId,
    description: problemDescription
  };

  spec()
    .post(SERVER_URL + "/problems/")
    .withBody(this.problem)
    .expectStatus(200)
    .toss();
});

Given("an {string},{string} done", function (actionPlanId, actionPlanDescription) {
  this.actionPlan = {
    id: actionPlanId,
    description: actionPlanDescription
  };

  spec()
    .post(SERVER_URL + "/action-plans/")
    .withBody(this.actionPlan)
    .expectStatus(200)
    .toss();
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
    return this.response.expectBodyContains(riskLevel).toss();
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
    .post(SERVER_URL + "/analyzed-result/")
    .withBody(analyzedResult);
});

Then("shows that operation was {string}", function (operationStatus) {
  return this.response.expectStatus(200).expectBodyContains(operationStatus).toss();
});

Given('the <analyzed_results> stored', function (dataTable) {
  let analyzedResultsList = [];

  for (let i=1; i<dataTable.rawTable.length; i++) {
    const problemTemp = {
      id: Number(dataTable.rawTable[i][0].replace(/'/g, "").split(",")[0]),
      description: dataTable.rawTable[i][0].replace(/'/g, "").split(",")[1],
    };
    const actionPlanTemp = {
      id: Number(dataTable.rawTable[i][1].replace(/'/g, "").split(",")[0]),
      description: dataTable.rawTable[i][1].replace(/'/g, "").split(",")[1],
    };
    const analyzedResultTemp = {
      problem: problemTemp,
      actionPlan: actionPlanTemp,
      probabilityLevel: dataTable.rawTable[i][2].replace(/'/g, ""),
      impactLevel: dataTable.rawTable[i][3].replace(/'/g, ""),
      riskLevel: dataTable.rawTable[i][4].replace(/'/g, "")
    };
    analyzedResultsList.push(analyzedResultTemp);
  }

  spec()
    .post(SERVER_URL + "/analyzed-results/")
    .withBody(analyzedResultsList)
    .expectStatus(200)
    .toss();
});

When("the system gets the analyzed results associated", function () {
  this.response = spec()
    .get(SERVER_URL + "/analyzed-results/")
    .withQueryParams("problemId", encodeURIComponent(this.problem.id));
});

Then("shows {string},{string}", function (actionPlanId, actionPlanDescription) {
  this.actionPlanExpected = {
    id: actionPlanId,
    description: actionPlanDescription
  };
});

Then("{string} of each analyzed result", function (riskLevel) {
  return this.response
    .expectStatus(200)
    .expectBodyContains(this.actionPlanExpected.id)
    .expectBodyContains(this.actionPlanExpected.description)
    .expectBodyContains(riskLevel)
    .toss();
});
