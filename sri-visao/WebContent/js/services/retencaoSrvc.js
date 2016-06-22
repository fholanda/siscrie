'use strict';

app.service('RetencaoSrvc', [ '$http', '$q', function($http, $q) {

	var services = {};

	var urlbase = "http://localhost:8080/sri-servicos/api/";
	//var urlbase = "https://dessolucoes.corporativo.serpro/apim/sri/1.0.0/";
	var urlbaseAccessToken = "https://dessolucoes.corporativo.serpro/apim/sri2/1.0.0/";

	services.findAll = function() {
		var deferred = $q.defer();
		$http({
			url : urlbase+'retencao',
			method : "GET",
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject([ data, status ]);
		});
		return deferred.promise;
	};

	services.findAllAccessToken = function() {
		var deferred = $q.defer();
		$http({
			url : urlbaseAccessToken+'retencao',
			method : "GET",
			headers : {
            	'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'
            }
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject([ data, status ]);
		});
		return deferred.promise;
	};

			

	return services;

} ]);