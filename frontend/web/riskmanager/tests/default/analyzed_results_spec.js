"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports = {

    before: function (browser) {
        browser.url(browser.launch_url);
    },

    after: function (browser) {
        browser.end();
    },

    'should gets analyzed results when user selects a given problem': function (browser) {
        // arrange
        browser.click('#analyzed-results');

        // act
        browser
            .click('#problems-element-id')
            .useXpath()
            .click('//span[contains(text(), "BAD GRADES ON MATH")]');

        // arrange
        browser.expect.element('table tbody').text.to.contain('STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
        browser.expect.element('table tbody').text.to.contain('LOW');
    },

};
