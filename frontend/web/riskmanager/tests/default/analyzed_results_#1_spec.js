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

    'should gets analyzed results when user selects a given problem': function (browser) {
        // arrange
        browser.navigateTo(browser.launch_url + '/analyzed-results');

        // act
        browser
            .click('#problems-element-id')
            .useXpath()
            .click('//span[contains(text(), "BAD GRADES ON MATH")]');

        // assert
        browser.useXpath().expect.element("//table[@id='analyzed-results-table']").text.to.contain('STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
        browser.useXpath().expect.element("//table[@id='analyzed-results-table']").text.to.contain('LOW');
    },

};
