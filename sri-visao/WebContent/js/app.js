'use strict';

var app = angular.module('sri', [
     'ngSanitize',
     'brasil.filters',
     'ngResource',
     'ngRoute',
     'ui.bootstrap',
     'ngGrid',
     'notification',
     'ngWebsocket',
     'ui.grid', 
     'ui.grid.selection',
     'ui.select',
     'angular-toArrayFilter',
     'ui.mask',
     'ui.utils.masks'
]).config(['$httpProvider', 'uiSelectConfig', function ($httpProvider, uiSelectConfig) {
	
	    uiSelectConfig.theme = 'bootstrap';

        var numLoadings = 0;
    
        $httpProvider.interceptors.push(['$q', '$rootScope',function ($q, $rootScope) {
                return {
                    //Interceptador de requests
                    
                    'request': function (config) {
                        
                        numLoadings++;

                        // Show loader
                        $rootScope.$broadcast("loader_show");
                        
                        $rootScope.$broadcast('loading-started');

                        config.headers['Accept'] = '*/*';
                         
                        return config || $q.when(config);
                    },
                    'response': function (response) {
                        $rootScope.$broadcast('loading-complete');
                        
                        if ((--numLoadings) === 0) {
                            // Hide loader
                            $rootScope.$broadcast("loader_hide");
                        }
                        
                        
                        return response || $q.when(response);
                    },
                    'responseError': function (rejection) {
                        $rootScope.$broadcast('loading-complete');
                        
                        if (!(--numLoadings)) {
                            // Hide loader
                            $rootScope.$broadcast("loader_hide");
                        }                        
                        return $q.reject(rejection);
                    },
                    'requestError': function (rejection) {
                        $rootScope.$broadcast('loading-complete');
                        return $q.reject(rejection);
                    }
                };
            }]);

    }]);



app.run(['$rootScope', '$location', '$window', 'TIPO_ALERTAS', 'AlertApp',
    function ($rootScope, $location, $window, TIPO_ALERTAS, AlertApp) {
        
		 $rootScope.$on('$routeChangeStart', function (event, next) {
	
			 AlertApp.clear();

	     });
    }]);
app.directive("loader", function ($rootScope) {
    return function ($scope, element, attrs) {
        $scope.$on("loader_show", function () {
            return element.show();
        });
        return $scope.$on("loader_hide", function () {
            return element.hide();
        });
    };
}
)