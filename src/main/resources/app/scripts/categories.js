'use strict';

(function() {
    var app = angular.module('categories', []);

    app.controller('CategoriesCtrl', function($scope) {
        $('#accordian').accordion({
            active: false,
            collapsible: true
        });
    });
})();