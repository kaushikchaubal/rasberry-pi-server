'use strict';

(function() {
    var app = angular.module('main', []);

    app.controller('MainCtrl', function($scope) {
        $('#accordian').accordion({
            active: false,
            collapsible: true
        });

        $('#firstChart').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: 'Why Invest?'
            },
            subtitle: {
                text: 'Magic of Compounding - investing £100'
            },
            xAxis: {
                categories: ['10', '20', '30', '40', '50'],
                title: {
                    text: 'Number of years investment'
                }
            },
            yAxis: {
                title: {
                    text: 'Size of fund (£)'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: true
                }
            },
            series: [{
                name: 'Simple Interest',
                data: [200, 300, 400, 500, 600]
            }, {
                name: 'Compound Interest',
                data: [200, 673, 1745, 4525, 11739]
            }]
        });
    });
})();