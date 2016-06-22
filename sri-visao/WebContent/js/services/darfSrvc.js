'use strict';

app.service('DARFSrvc', [ '$http', '$q', function($http, $q) {

	var services = {};

	//var urlbase = "https://dessolucoes.corporativo.serpro/apim/sri/1.0.0/";
	var urlbase = "http://localhost:8080/sri-servicos/api/";

	services.findAll = function() {
		var deferred = $q.defer();
		$http({
			url : urlbase+'darfs',
			method : "GET",
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject([ data, status ]);
		});
		return deferred.promise;
	};

	services.save = function(darf) {
		console.log(darf);		
		var deferred = $q.defer();
		var id = darf.id;
		
		var url =  id ? (urlbase+'darfs/') + id : (urlbase+'darfs');
		console.log("url: " + url);
		
		$http({
			url: id ? (urlbase+'darfs/') + id : (urlbase+'darfs/'),
			method : id ? "PUT" : "POST",
			data : darf,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject([ data, status ]);
		});
		return deferred.promise;
	};

	services.get = function(id) {
		var deferred = $q.defer();
		$http({
			url : urlbase+'darfs/' + id,
			method : "GET",
		}).success(function(data) {
			deferred.resolve(data);
		}).error(function(data, status) {
			deferred.reject([ data, status ]);
		});
		return deferred.promise;
	};
	
	
	services.delete = function(id) {
        var deferred = $q.defer();
        $http({
            url: urlbase+'darfs/' + id,
            method: "DELETE",
        }).success(function(data) {
            deferred.resolve(data);
        }).error(function(data, status) {
            deferred.reject([data, status]);
        });
        return deferred.promise;
    };

    /*services.list = function (field, order, init, qtde) {

  		return $http.get(urlbase+'darfs/' + field + '/' + order + '/' + init + '/' + qtde).then(
                function (res) {
                    return res.data;
                }
            );                

    };  */ 

    services.list = function (field, order, init, qtde) {

    	return $http.get(urlbase+'darfs?sort=' + ((order.substr(0,3)==="des") ? ("-"+field) : field ) + '&offset=' + init + '&limit=' + qtde).then(
                function (res) {
                    return res.data;
                }
            );                

    };  

    services.count = function () {
            return $http.get(urlbase+'darfs/count').then(function (res) {
                return res.data;
            });
        };		
    

	return services;

} ]);