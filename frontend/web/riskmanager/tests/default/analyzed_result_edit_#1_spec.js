"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports = {
    '@disabled': false,

    before: function (browser) {
        browser.url(browser.launch_url);
    },

    after: function (browser) {
        browser.end();
    },

    'should gets risk level when user selects probability and impact level given a problem and an action plan': function (browser) {
        // arrange
        browser.navigateTo(browser.launch_url + '/analyzed-results/1');

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
        browser.navigateTo(browser.launch_url + '/analyzed-results/1');

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
