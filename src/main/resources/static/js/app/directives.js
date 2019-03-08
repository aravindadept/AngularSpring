
app.directive('compile',function($compile, $timeout){
    return{
        restrict:'A',
        link: function(scope,elem,attrs){
            $timeout(function(){                
                $compile(elem.contents())(scope);    
            });
        }        
    };
});

app.directive('modal', function () {
    return {
        restrict: 'EA',
        scope: {
           
        	name: '=name',
            modalClass: '=modalClass',
            headerClass: '=headerClass',
            headerTitle:'=headerTitle',
            //body:'=body',
            closeBtn:'=closeBtn'
           
        },
        template: '<div class="modal fade" id="{{name}}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
		   '<div class="modal-dialog {{modalClass}}" role="document">'+
		          '<div class="modal-content">'+
			          '<div class="modal-header" ng-class="headerClass" >'+
			          	'<h5 class="modal-title w-100 font-weight-bold" id="myModalLabel">{{headerTitle}}</h5>'+
			          	//'<p class="heading w-100 font-weight-bold">{{headerTitle}}</p>'+
			          	'<button ng-if="closeBtn" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
			          '</div>'+
			          '<div  class="modal-body"  ng-transclude ></div>'+
		          '</div>'+
	      '</div>'+
	      '</div>',
	    transclude: true,
        controller: function ($scope) {
        	$scope.name='modal';
        }
    };
});

app.directive('datatable', function () {
    return {
        restrict: 'E, A, C',
        link: function (scope, element, attrs, controller) {

           var dataTable = element.dataTable(scope.options); //init plugin
           var mapToDatatableFormat = function (data) {
            	return data.map(scope.options.columnMap)
            }
            scope.$watch('tabledata', function (newData) {
                if (newData) {
                    dataTable.fnClearTable();
                    dataTable.fnAddData( mapToDatatableFormat(newData) )
                }
            }, true);
            
        },
        scope: { options: "=", tabledata: "=" }
        
    };
});

app.directive('formInvalidFocus', function () {
    return {
        restrict: 'A',
        link: function (scope, elem) {
            elem.on('submit', function () {
                var firstInvalid = elem[0].querySelector('.ng-invalid');
                if (firstInvalid) {  firstInvalid.focus();}
            });
        }
    };
});

app.directive('validNumber', function() {
    return {
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
        if(!ngModelCtrl) { return; }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {var val = '';}

          var clean = val.replace(/[^-0-9]/g, '');clean=clean.replace(/-/g,'');
          
          if (val !== clean) {ngModelCtrl.$setViewValue(clean); ngModelCtrl.$render();}
          return clean;
          
        });

        element.bind('keypress', function(event) {
          if(event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    };
  });

app.directive('validDecimal', function() {
    return {
      require: '?ngModel',
      scope:{ limit: '@?validDecimal', },
      controller: function($scope){
    	  $scope.afterDecimal=2;
      },
      link: function(scope, element, attrs, ngModelCtrl) {
    	  
    	  var lenArr=scope.limit.split(',');var beforeDecimal=lenArr[0];  var afterDecimal=lenArr[1];

    	  if(!ngModelCtrl) { return; }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {var val = '';}

          var clean = val.replace(/[^-0-9.]/g, '');clean=clean.replace(/-/g,'');
          if(clean.split('.').length>2)  { clean =clean.replace(/\.+$/,""); }
          var arr=clean.split('.');
          if(arr.length==2){  
        	  if(arr[0].length > beforeDecimal){ arr[0]=arr[0].slice(0,beforeDecimal); clean=arr[0]+'.'+arr[1] }
        	  if(arr[1].length > afterDecimal){ arr[1]=arr[1].slice(0,afterDecimal); clean=arr[0]+'.'+arr[1] }
          }else { clean=clean.slice(0,beforeDecimal);   }

          if (val !== clean) {ngModelCtrl.$setViewValue(clean); ngModelCtrl.$render();}
          
          return clean;
          
        });

		element.bind('keypress', function(event) { var kv = event.which || event.keyCode; if (kv === 32) { event.preventDefault();} });
		
      }
    };
  });

app.directive('limitLength', function() {
    'use strict';
    return {
        restrict: 'A',
        scope: {
            limit: '@limitLength',
            ngModel: '=ngModel'
        },
        link: function(scope) {
            scope.$watch('ngModel', function(newValue, oldValue) {
                if (newValue) {
                    var length = newValue.toString().length;
                    if (length > scope.limit) { scope.ngModel = oldValue;}
                }
            });
        }
    };
});

app.directive('noSpace', function() {
    return {
        restrict: 'A',
        link: function($scope, $element) {
        	$element.bind('keypress', function(event) { var kv = event.which || event.keyCode; if (kv === 32) { event.preventDefault();} });
        }
    }
});

app.directive('validLetter', function() {
	return {
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) { return;}

			ngModelCtrl.$parsers.push(function(text) {
				if (angular.isUndefined(text)) { var text = '';}
				
			   var transformedInput = text.replace(/[^a-zA-Z ]/g, '');
			   
			            if (transformedInput !== text) {
			              ngModelCtrl.$setViewValue(transformedInput);
			              ngModelCtrl.$render();
			            }
			            return transformedInput;
			});
		}
	};
});


