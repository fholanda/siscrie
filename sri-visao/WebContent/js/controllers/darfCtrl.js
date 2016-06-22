'use strict';

app.controller('DARFCtrl', ['$scope','$routeParams', '$rootScope', '$location','DARFSrvc', 'AlertApp', 'TIPO_ALERTAS', 'MENSAGEM', 'RetencaoSrvc', 'UFSrvc', 'MunicipioSrvc','TipoSrvc','RiscoSrvc',
function($scope, $routeParams,  $rootScope, $location, service, alertApp, TIPO_ALERTAS, MENSAGEM, retencaoService, ufService, municipioService, tipoService, riscoService) {
	
	$scope.count = function () {
	        service.count().then(
	            function (data) {
	                $scope.totalServerItems = data;
	            },
	            function (error) {
	                var data = error[0];
	                var status = error[1];

	                if (status === 401) {
	                    alertApp.add(TIPO_ALERTAS.error,'Erro ao calcular total de darfs.');
	                }

	            }
	        );
	};

	var path = $location.$$url;

	if (path === '/darf/list') {
            $scope.count();
        }
    ;


	$scope.new = function () {		
		$location.path('darf/add');		
	};
	
	$scope.carregarTelaNovoDARF = function(){
		$scope.title = "Cadastrar DARF";		
		 if ($routeParams.id != undefined) {
		    //Carregar DARF
		   	$scope.getEdit($routeParams.id);
		 }		  
		 
		carregarRetencaoList();		
		carregarUFList();
		carregarMunicipioList();
		carregarTipoList();
		carregarRiscoList();
		
		$rootScope.closeAlert();
		
	};

	function carregarRetencaoList(){
    	//Carregar retencao
		retencaoService.findAll().then(
			function (data) {			
				console.log(data);
				$scope.retencaoList = data;
			},
			function (data,status) {
				console.log(data);
				console.log(status);
				alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar retenções.');
			}
		);
    }

    function carregarUFList(){
    	//Carregar UFs
		ufService.findAll().then(
			function (data) {			
				console.log(data);
				$scope.ufList = data;
			},
			function (data,status) {
				console.log(data);
				console.log(status);
				alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar UFs.');
			}
		);
    }

    function carregarMunicipioList(){
    	//Carregar municipios
		municipioService.findAll().then(
			function (data) {			
				console.log(data);
				$scope.municipioList = data;
			},
			function (data,status) {
				console.log(data);
				console.log(status);
				alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar municípios.');
			}
		);
    }

    function carregarTipoList(){
    	//Carregar tipos
		tipoService.findAll().then(
			function (data) {			
				console.log(data);
				$scope.tipoList = data;
			},
			function (data,status) {
				console.log(data);
				console.log(status);
				alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar tipos.');
			}
		);
    }

    function carregarRiscoList(){
    	//Carregar riscos
		riscoService.findAll().then(
			function (data) {			
				console.log(data);
				$scope.riscoList = data;
			},
			function (data,status) {
				console.log(data);
				console.log(status);
				alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar riscos.');
			}
		);
    }

    $scope.minDate = new Date();

	// Disable weekend selection
	function disabled(data) {
	    var date = data.date,
	      mode = data.mode;
	    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
	}
	
	$scope.dateOptions = {
			    dateDisabled: disabled,
			    formatYear: 'yy',
			    maxDate: new Date(2020, 5, 22),
			    minDate: $scope.minDate,
			    startingDay: 1
	};

	
	$scope.popup1 = {
		opened: false
	};

	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};

	$scope.popup2 = {
		opened: false
	};

	$scope.open2 = function() {
		$scope.popup2.opened = true;
	};

	

	$scope.carregarTelaListarDarfs = function (){
		$scope.title = "DARFs cadastrados";
		service.findAll().then(
				function (data) {
					$scope.darfs = data;					
				},
				function (data,status) {
					console.log(data);
					console.log(status);
					alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar darfs.');
				}
			);
	};
	
	$scope.cancelar = function () {
		$location.path('/darf/list');
	};
	
	$scope.save = function () {		
		
		var mensagemSucesso = 'DARF número {0} cadastrado com sucesso.';
		var mensagemErro = 'Erro ao cadastrar DARF.';
		
		if ($scope.darf.id != undefined){
			mensagemSucesso = 'DARF atualizado com sucesso.'
			mensagemErro = 'Erro ao atualizar DARF.';
		}
		
		
		if ($scope.form.$valid) {
			service.save($scope.darf).then(
				function (data) {
					// PUT 
					if ($scope.darf.id != undefined){
						alertApp.add(TIPO_ALERTAS.success,mensagemSucesso);
						$location.path("/darf/edit/" + $scope.darf.id);    	
					}
					else{ // POST
						$location.path('/darf/list');
						alertApp.add(TIPO_ALERTAS.success,mensagemSucesso.replace('{0}',data.id));
					}
					
				},
				function (data,status) {
					console.log(data);
					console.log(status);
					alertApp.add(TIPO_ALERTAS.error,mensagemErro);
					alertApp.addList(TIPO_ALERTAS.error, data[0]);
				}
			);
		} else {
			alertApp.add(TIPO_ALERTAS.error, MENSAGEM.formularioInvalido);			
		}
	};
	
	$scope.getEdit = function (id) {	
		service.get(id).then(
		    function (data) {
		    	$scope.darf = data;
		    	$scope.descRetencao = $scope.darf.descRetencao;

		    	console.log("DARF: " + JSON.stringify($scope.darf));
		    },
		    function (data,status) {
		    	console.log(data);
				console.log(status);
		    	alertApp.add(TIPO_ALERTAS.error,'Erro ao obter DARF.');
		    }
		);
	};
			
	
	$scope.edit = function (id) {
            $rootScope.darfCurrentPage = $scope.pagingOptions.currentPage;
            $location.path('/darf/edit/' + id);
        };
    

    $scope.delete = function (id,nrDARF) {

		var mensagemSucesso = 'DARF número {0} excluído com sucesso.';
		var mensagemErro = 'Erro ao excluir DARF.';

    	service.delete(id).then(
    			function (data) {			
    					alertApp.add(TIPO_ALERTAS.success,mensagemSucesso.replace('{0}',nrDARF));
	                    $location.path('/darf/list');
	                    $scope.count();
	                    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
					
				},
				function (data,status) {
					console.log(data);
					console.log(status);
					alertApp.add(TIPO_ALERTAS.error,mensagemErro);
				}
    		);

    }

    $scope.pageChanged = function () {
            $scope.darfs = [];
            var num = (($scope.currentPage - 1) * $scope.itemsPerPage);
            service.list(num, $scope.itemsPerPage).then(
                function (data) {
                    $scope.darfs = data;
                },
                function (error) {
                    if (error.status === 401) {
                    	alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar darfs.');
                    }

                }
            );
    };	

    $scope.setPagingData = function (data, page, pageSize) {
            var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
            $scope.myData = pagedData;
            $scope.totalServerItems = data.length;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        };

    $scope.getPagedDataAsync = function (pageSize, page) {
        var field;
        var order;
        if (typeof ($scope.sortInfo) === "undefined") {
            field = "id";
            order = "asc";
        } else {
            field = $scope.sortInfo.fields[0];
            order = $scope.sortInfo.directions[0];
        }

        setTimeout(function () {
            var init = (page - 1) * pageSize;
            service.list(field, order, init, pageSize).then(
                function (data) {
                    $scope.darfs = data;
                },
                function (error) {
                    var data = error[0];
                    var status = error[1];

                    if (status === 401) {
                    	alertApp.add(TIPO_ALERTAS.error,'Erro ao buscar darfs.');
                    }
                }
            );
        }, 100);
    };



    $scope.pagingOptions = {
            pageSizes: [15],
            pageSize: 15,
            currentPage: 1
        };


    if ($rootScope.darfCurrentPage != undefined) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $rootScope.darfCurrentPage);
            $scope.pagingOptions.currentPage = $rootScope.darfCurrentPage;
        } else {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }


    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }
    }, true);

    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }
    }, true);

    $scope.$watch('sortInfo', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }
    }, true);

    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

    $scope.gridOptions = {
            data: 'darfs',
            columnDefs: [{field: 'nrDARF', displayName: 'Número', width: "14%"},
				{cellTemplate: '<div>{{row.entity.cnpj |cnpj}}</div>', displayName: 'CNPJ', width: "17%"},
                {field: 'nome', displayName: 'Nome', width: "45%"},
                {displayName: 'Ação', cellTemplate: '<a ng-show="!currentUser" ng-click="edit(row.entity.id)" class="btn btn-primary btn-sm">Visualizar</a>\n\
                                                 <a ng-show="currentUser" ng-click="edit(row.entity.id)" class="btn btn-warning btn-sm">Alterar</a>\n\
                                                 <a confirm-button title="Excluir?" confirm-action="delete(row.entity.id,row.entity.nrDARF)" class="btn btn-danger btn-sm">Excluir</a>', width: "22%"}],
            selectedItems: [],
            keepLastSelected: true,
            sortInfo: $scope.sortInfo,
            multiSelect: false,
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            enableSorting: true,
            useExternalSorting: true,
            i18n: "pt"
    };

    $scope.$on('$routeChangeStart', function (event, next) {
            if (next.originalPath.indexOf("darf") === -1) {
                $rootScope.darfCurrentPage = 1;
            }
    });	
    
}]);