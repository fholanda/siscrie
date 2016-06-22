'use strict';

app.service('DARFSrvc2', [ '$http', '$q', function($http, $q) {

	// serviços para tetes de acesso ao WSO2 com cabeçalho Authorization contendo o Acess Token

	var services = {};

	//var urlbase = "https://dessolucoes.corporativo.serpro/apim/sri2/1.0.0/";
	var urlbase = "http://localhost:8080/sri-servicos/api/";

	services.findAll = function() {
		var deferred = $q.defer();
		$http({
			url : urlbase+'darfs',
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

	services.save = function(darf) {
		console.log(darf);		
		var deferred = $q.defer();
		var id = darf.id;
		//demanda.id = null;
		var url =  id ? (urlbase+'darfs/') + id : (urlbase+'darfs');
		console.log("url: " + url);
		
		$http({
			url: id ? (urlbase+'darfs/') + id : (urlbase+'darfs/'),
			method : id ? "PUT" : "POST",
			data : darf,
			headers : {
				'Content-Type' : 'application/json',
				'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'
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
	
	
	services.delete = function(id) {
        var deferred = $q.defer();
        $http({
            url: urlbase+'darfs/' + id,
            method: "DELETE",
            headers : {
            	'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'
            }
        }).success(function(data) {
            deferred.resolve(data);
        }).error(function(data, status) {
            deferred.reject([data, status]);
        });
        return deferred.promise;
    };

    /*services.list = function (field, order, init, qtde) {

  		return $http.get(urlbase+'darfs/' + field + '/' + order + '/' + init + '/' + qtde,
  							{
							    headers: {'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'}
							}
  				).then(
                function (res) {
                    return res.data;
                }
            );                

    };*/

    services.list = function (field, order, init, qtde) {

    	return $http.get(urlbase+'darfs?sort=' + ((order.substr(0,3)==="des") ? ("-"+field) : field ) + '&offset=' + init + '&limit=' + qtde,
  							{
							    headers: {'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'}
							}
						).then(
                function (res) {
                    return res.data;
                }
            );                

    };

    services.count = function () {

		var deferred = $q.defer();
        $http({
            url: urlbase+'darfs/count',
            method: "GET",
            headers : {
            	'Authorization': 'Bearer 4ff5e276f030e3345bbcd2bb195cfcfe'
            }
        }).success(function(data) {
            deferred.resolve(data);
        }).error(function(data, status) {
            deferred.reject([data, status]);
        });
        return deferred.promise;     
           
    };		
    

	return services;

} ]);