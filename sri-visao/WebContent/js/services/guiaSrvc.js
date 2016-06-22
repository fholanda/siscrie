'use strict';

app.service('GuiaSrvc', [ '$http', '$q', 
    function($http, $q) {

		var services = {};	
	
		services.getUsuarioLogado = function() {
			var deferred = $q.defer();
			$http({
				url: propertiesGuia.user_data_url,
				method : "GET"
			}).success(function(data) {
				deferred.resolve(data);
			}).error(function(data, status) {
				deferred.reject([ data, status ]);
			});
	
			return deferred.promise;
		};

	return services;

} ]);