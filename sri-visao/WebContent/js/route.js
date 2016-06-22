'use strict';

app.config(['$routeProvider',
         function ($routeProvider) {

             $routeProvider                
                 
				.when('/darf/add', {
					templateUrl: 'views/darf/darfAdd.html',
					controller: 'DARFCtrl'
				})

				.when('/darf/add2', {
					templateUrl: 'views/darf/darfAdd.html',
					controller: 'DARFCtrl2'
				})
				
				.when('/darf/list', {
					templateUrl: 'views/darf/darfList.html',
					controller: 'DARFCtrl'
				})

				.when('/darf/list2', {
					templateUrl: 'views/darf/darfList2.html',
					controller: 'DARFCtrl2'
				})
				
				.when('/darf/edit/:id', {
					templateUrl: 'views/darf/darfAdd.html',
					controller: 'DARFCtrl'
				})

				.when('/darf/edit2/:id', {
					templateUrl: 'views/darf/darfAdd.html',
					controller: 'DARFCtrl2'
				})
	
				.otherwise({
                     redirectTo: '/darf/list'
                 });
         }]);