'use strict';


app.directive('alerts', function () {
    return {
        restrict: 'E',
        templateUrl: 'views/util/alerts.html'
    };
});

app.directive("autofill", function () {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ngModel) {
            scope.$on("autofill:update", function () {
                ngModel.$setViewValue(element.val());
            });
        }
    };
});

app.directive("appVersion", ["version", function (version) {
        return function (scope, elm, attrs) {
            elm.text(version);
        };
    }]);


app.directive("confirmButton", function ($timeout) {
    return {
        restrict: 'A',
        scope: {
            actionOK: '&confirmAction',
            actionCancel: '&cancelAction'
        },
        link: function (scope, element, attrs) {
            var buttonId, html, message, nope, title, yep;
            buttonId = Math.floor(Math.random() * 10000000000);
            attrs.buttonId = buttonId;
            message = attrs.message || "Tem certeza?";
            yep = attrs.yes || "Sim";
            nope = attrs.no || "Não";
            title = attrs.title || "Confirmação";

            element.bind('click', function (e) {

                var box = bootbox.dialog({
                    message: message,
                    title: title,
                    buttons: {
                        success: {
                            label: yep,
                            className: "btn-success",
                            callback: function () {
                                $timeout(function () {
                                    scope.$apply(scope.actionOK);
                                });
                            }
                        },
                        danger: {
                            label: nope,
                            className: "btn-danger",
                            callback: function () {
                                scope.$apply(scope.actionCancel);
                            }
                        }
                    }
                });

            });
        }
    };
});

app.directive('validationMsg', ['ValidationApp', function (ValidationApp) {
        return {
            restrict: 'E',
            scope: {
                propriedade: '@'
            },
            template: "<div class='error text-danger' ng-show='msg'><small class='error' >{{msg}}</small></div>",
            controller: function ($scope) {
                $scope.$watch(function () {
                    return ValidationApp.validation[$scope.propriedade];
                },
                        function (msg) {
                            $scope.msg = msg;
                        }
                );
            }
        };
    }]);

app.directive("maxLength", ['$compile', 'AlertApp', function ($compile, AlertApp) {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, elem, attrs, ctrl) {
                attrs.$set("ngTrim", "false");
                var maxlength = parseInt(attrs.maxLength, 10);
                ctrl.$parsers.push(function (value) {
                    if (value !== undefined && value.length !== undefined) {
                        if (value.length > maxlength) {
                            AlertApp.addWithTimeout('warning', 'O valor máximo de caracteres (' + maxlength + ') para esse campo já foi alcançado', 2000);
                            value = value.substr(0, maxlength);
                            ctrl.$setViewValue(value);
                            ctrl.$render();
                        }
                    }
                    return value;
                });
            }
        };
    }]);


app.directive('multiSelect',['$q', function($q) {
	  return {
	    restrict: 'E',
	    require: 'ngModel',
	    scope: {
	      selectedLabel: "@",
	      availableLabel: "@",
	      displayAttr: "@",
	      available: "=",
	      model: "=ngModel"
	    },
	    template: '<div class="multiSelect">' + 
	                '<div class="select">' +
	                  '<label class="control-label" for="multiSelectAvailable">{{ availableLabel }} ' +
	                      '({{ available.length }})</label>' +
	                  '<select id="multiSelectAvailable" class="form-control" ng-model="selected.available" multiple ' +
	                      'ng-options="e as e[displayAttr] for e in available | toArray:false | orderBy:\'{{displayAttr}}\' "></select>' +
	                '</div>' +
	                '<div class="select buttons">' + 
	                  '<button class="btn mover left" ng-click="add()" title="Adicionar" ' + 
	                      'ng-disabled="selected.available.length == 0">' + 
	                    '<i class="glyphicon glyphicon-arrow-right"></i>' + 
	                  '</button>' + 
	                  '<button class="btn mover right" ng-click="remove()" title="Remover" ' + 
	                      'ng-disabled="selected.current.length == 0">' + 
	                    '<i class="glyphicon glyphicon-arrow-left"></i>' + 
	                  '</button>' +
	                '</div>' + 
	                '<div class="select">' + 
	                  '<label class="control-label" for="multiSelectSelected">{{ selectedLabel }} ' +
	                      '({{ model.length }})</label>' +
	                  '<select id="currentRoles" ng-model="selected.current" multiple ' + 
	                      'class="form-control" ng-options="e as e[displayAttr] for e in model | toArray:false | orderBy:\'{{displayAttr}}\' ">' + 
	                      '</select>' + 
	                '</div>' + 
	              '</div>',
	    link: function(scope, elm, attrs) {
	      scope.selected = {
	        available: [],
	        current: []
	      };

	      /* Handles cases where scope data hasn't been initialized yet */
	      var dataLoading = function(scopeAttr) {
	        var loading = $q.defer();
	        if(scope[scopeAttr]) {
	          loading.resolve(scope[scopeAttr]);
	        } else {
	          scope.$watch(scopeAttr, function(newValue, oldValue) {
	            if(newValue !== undefined)
	              loading.resolve(newValue);
	          });  
	        }
	        return loading.promise;
	      };

	      /* Filters out items in original that are also in toFilter. Compares by reference. */
	      var filterOut = function(original, toFilter) {
	        var filtered = [];
	        angular.forEach(original, function(entity) {
	          var match = false;
	          for(var i = 0; i < toFilter.length; i++) {
	            if(toFilter[i][attrs.displayAttr] == entity[attrs.displayAttr]) {
	              match = true;
	              break;
	            }
	          }
	          if(!match) {
	            filtered.push(entity);
	          }
	        });
	        return filtered;
	      };
	      scope.getdisplayAttr = function(item) {
	          return item[scope.displayAttr];
	        }
	      scope.refreshAvailable = function() {
	        scope.available = filterOut(scope.available, scope.model);
	        scope.selected.available = [];
	        scope.selected.current = [];
	      }; 

	      scope.add = function() {
	        scope.model = scope.model.concat(scope.selected.available);
	        scope.refreshAvailable();
	      };
	      scope.remove = function() {
	        scope.available = scope.available.concat(scope.selected.current);
	        scope.model = filterOut(scope.model, scope.selected.current);
	        scope.refreshAvailable();
	      };

	      $q.all([dataLoading("model"), dataLoading("available")]).then(function(results) {
	        scope.refreshAvailable();
	      });
	    }
	  };
}]);
