
var app = angular.module('adminApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8081/',
    BASE_API : 'http://localhost:8081/api/'
});

app.config(['$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
			
	var category= {				
		name: 'category',
		url: '/',
		templateUrl: 'category',
		controller:'categoryController',
		controllerAs:'ctrl',
		resolve: {
			users: function ($q, Service) {
				
				localStorage.clear();
				Service.logService(0,'Load all category');
		        var deferred = $q.defer();
		        Service.loadSerData('categorylist').then(deferred.resolve, deferred.resolve);
		        return deferred.promise;
			}
	    }
	        
	};
	
	var item= {				
		name: 'item',
		url: '/item',
		templateUrl: 'item',
		controller:'itemController',
		controllerAs:'ctrl',
		resolve: {
			users: function ($q, Service) {
				localStorage.clear();
				Service.logService(0,'Load all Items');
		        var deferred = $q.defer();
		        Service.loadSerData('categorylist').then(deferred.resolve, deferred.resolve);
		        Service.loadSerData('itemlist').then(deferred.resolve, deferred.resolve);
		        
		        return deferred.promise;
			}
	    }
	        
	};	
			
	$stateProvider.state(category);	
	$stateProvider.state(item);
		
	$urlRouterProvider.otherwise('/');
                
}]);

