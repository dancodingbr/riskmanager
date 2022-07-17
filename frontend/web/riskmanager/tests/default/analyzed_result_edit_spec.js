"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports = {

    before: function (browser) {
        browser.url(browser.launch_url);
    },

    after: function (browser) {
        browser.end();
    },

    'should gets risk level when user selects probability and impact level given a problem and an action plan': function (browser) {
        // arrange
        browser.click('#analyzed-result-edit');
        browser.assert.domPropertyContains('#problem-element-id', 'value', 'BAD GRADES ON MATH');
        browser.assert.domPropertyContains('#action-plan-element-id', 'value', 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');

        // act
        browser
            .click('#probability-level-element-id')
            .click('#probability-level-element-id-panel mat-option[value="RARE"]');
        browser
            .click('#impact-level-element-id')
            .click('#impact-level-element-id-panel mat-option[value="HIGH"]');

        // assert
        browser.assert.domPropertyContains('#risk-level-element-id', 'value', 'LOW');
    },

    'should gets risk level when user selects impact and probability level given a problem and an action plan': function (browser) {
        // arrange
        browser.click('#analyzed-result-edit');
        browser.assert.domPropertyContains('#problem-element-id', 'value', 'BAD GRADES ON MATH');
        browser.assert.domPropertyContains('#action-plan-element-id', 'value', 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');

        // act
        browser
            .click('#impact-level-element-id')
            .click('#impact-level-element-id-panel mat-option[value="HIGH"]');
        browser
            .click('#probability-level-element-id')
            .click('#probability-level-element-id-panel mat-option[value="RARE"]');

        // assert
        browser.assert.domPropertyContains('#risk-level-element-id', 'value', 'LOW');
    },

};
