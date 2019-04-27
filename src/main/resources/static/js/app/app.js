
var app = angular.module('adminApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8081/',
    BASE_API : 'http://localhost:8081/api/'
});


app.factory('AuthInterceptor', ['GlobData',function(data) {  
    return {
    // Send the Authorization header with each request
        'request': function(config) {
            config.headers = config.headers || {};
            var encodedString = btoa(data.username+':'+data.password);
            config.headers.Authorization = 'Basic '+encodedString;
            //console.log(config);
           return config;
        }
    };
}]);

app.config(['$httpProvider', function($httpProvider) {
	  $httpProvider.interceptors.push('AuthInterceptor');
	}]);


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
		        Service.loadData('categorylist').then(deferred.resolve, deferred.resolve);
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
		        Service.loadData('categorylist').then(deferred.resolve, deferred.resolve);
		        Service.loadData('itemlist').then(deferred.resolve, deferred.resolve);
		        
		        return deferred.promise;
			}
	    }
	        
	};	
		
	var po= {				
			name: 'po',
			url: '/po',
			templateUrl: 'po',
			controller:'poController',
			controllerAs:'ctrl',
			resolve: {
				users: function ($q, Service) {
					localStorage.clear();
					Service.logService(0,'Load all po');
			        var deferred = $q.defer();
			        Service.loadData('polist').then(deferred.resolve, deferred.resolve);
			        Service.loadData('itemlist').then(deferred.resolve, deferred.resolve);
			        return deferred.promise;
				}
		    }
		        
		};	
	
	$stateProvider.state(category);	
	$stateProvider.state(item);
	$stateProvider.state(po);
		
	$urlRouterProvider.otherwise('/');
                
}]);

