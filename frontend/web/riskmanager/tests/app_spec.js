"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
module.exports = {
    before: function (browser) {
        browser.url(browser.launch_url);
    },
    after: function (browser) {
        browser.end();
    },
    'should check heading contains text Nightwatch.js': function (browser) {
        browser.assert.containsText('#top-section h1', 'Nightwatch.js');
    },
};
