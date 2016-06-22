'use strict';

app.controller('ApplicationCtrl', ['$rootScope', '$notification','GuiaSrvc',
    function ($rootScope, $notification, GuiaSrvc) {


        $rootScope.$on("websocket", function (emit, args) {
            $notification(args.emit.event, {
                body: args.emit.data
            });
        });        

        
        //obtém dados do usuário autenticado no Autentikus        
        $rootScope.getUsuarioLogado = function(){        	

          GuiaSrvc.getUsuarioLogado().then(
       	         function (data) {
       	    	     console.log(data);
       	    	     $rootScope.currentUser = data;       	    	     
       	    	 },
       	    	 function (error) {
      	        	 console.log("ERROR ApplicationCtrl"); 
       	    		   var data = error[0];
       	    	     var status = error[1];
       	    	     console.log("Data " + data);
       	    	     console.log("Status " + status);                          	    	  
       	    	 });
        };



        
}]);