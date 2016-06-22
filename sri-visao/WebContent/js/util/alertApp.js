'use strict';

app.constant('TIPO_ALERTAS', {
    success: 'success',
    info: 'info',
    warning: 'warning',
    error: 'danger'
});

app.factory('AlertApp', ['$rootScope', '$timeout', function($rootScope, $timeout) {
	var alertApp = {};

	// create an array of alerts available globally
	$rootScope.alerts = [];

	alertApp.addWithTimeout = function(type, msg, timeout) {
  	    var alert = alertApp.add(type, msg);
		$timeout(function() {
			alertApp.clear();
		}, timeout ? timeout: 1000);
	};
			

	alertApp.clear = function(){
		$rootScope.alerts = [];
	};
	
	alertApp.add = function(type, msg, timeout) {
		alertApp.clear();
		
		if(type && msg){
			$rootScope.alerts.push({
				'type' : type,
				'msg' : msg
			});
		}
	};

	alertApp.addList = function(type, list, timeout) {
		alertApp.clear();
		
		if(type && list){

			for(var x=0; x < list.length; x++){
				$rootScope.alerts.push({
					'type' : type,
					'msg' : list[x].property + " " + list[x].message
				});	
			}
		}
	};

	alertApp.showMessageForbiden = function(){
		this.addWithTimeout('danger', 'Você não tem permissão para executar essa operação');
	};

		
	$rootScope.closeAlert = function(alert) {
				
		for(var x=0; x < $rootScope.alerts.length; x++){
			if ($rootScope.alerts[x].$$hashKey == alert.alert.$$hashKey){
				$rootScope.alerts.pop(x);
			}
		}		
	};


	
	return alertApp;
}]);