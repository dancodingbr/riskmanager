"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports = {
    before: function (browser) {
        browser.url(browser.launch_url);
    },
    after: function (browser) {
        browser.end();
    },
    'should shows hello world message at home page when user clicks on hello link': function (browser) {
        browser.click('#hello-message');
        browser.assert.containsText('body', 'hello world');
    },
};
