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

    'should save analyzed result when user clicks on save button given a analyzed result edited': async function (browser) {
        // arrange
        browser.navigateTo(browser.launch_url + '/analyzed-results');
        browser
            .click('#problems-element-id')
            .useXpath()
            .click('//span[contains(text(), "BAD GRADES ON MATH")]');
        browser
            .useXpath()
            .click('//table//tbody//tr//td//a[contains(@href, "analyzed-results/1")]');

        browser
            .useXpath()
            .click('//mat-select[@id="probability-level-element-id"]')
            .click('//mat-option[@value="OCCASIONAL"]');
        browser
            .useXpath()
            .click('//mat-select[@id="impact-level-element-id"]')
            .click('//mat-option[@value="LOW"]');

        // act
        browser
            .useXpath()
            .click('//button[@id="save-button-id"]');

        // assert
        browser
            .useXpath()
            .assert
            .domPropertyContains('//snack-bar-container//span', 'textContent', 'SUCCESS');
    },

};
