'use strict';

app.factory('GlobData', function() {  
	return {username:"root",password:"root"};
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


app.factory('Service',['$localStorage', '$http', '$q', 'urls', 'GlobData',
        function ($localStorage, $http, $q, urls, GlobData) {

            var factory = {
                loadSerData: loadSerData,
                setLocData : setLocData,
                getLocData : getLocData,
                postService: postService,
                putService : putService,
                removeService : removeService,
                logService : logService,
                modalService : modalService,
                confirmService:confirmService
            };
         
            return factory;
            
            function modalService(murl,name,mstate,mclass,mhclass,mtitle,mclose){
            	
               	GlobData.modalUrl= murl;
            	GlobData.modalName=name;
            	GlobData.modalState=mstate;
            	GlobData.modalClass=mclass;
            	GlobData.modalHeaderClass=mhclass;
            	GlobData.modalTitle=mtitle;
            	GlobData.modalClose=mclose;
            	$('#'+GlobData.modalName).modal({ show: true, backdrop: 'static', keyboard: false}); 
            	
            }
            function confirmService(confirmdata){
            	
               	GlobData.modalUrl= 'confirm';
            	GlobData.modalName='modal';
            	GlobData.modalState='2';
            	GlobData.modalClass='modal-sm modal-notify modal-danger';
            	GlobData.modalHeaderClass='d-flex justify-content-center text-center text-white';
            	GlobData.modalTitle='Are you sure?';
            	GlobData.modalClose=false;
            	GlobData.confirmData=confirmdata;
            	
            	$('#'+GlobData.modalName).modal({ show: true, backdrop: 'static', keyboard: false}); 
            	
            }
            function logService(type,data,data1){
         	   if(type==0){type="info: ";}
         	   if(type==1){type="err: ";}
         	   if(typeof(data1)=='undefined'){data1="";}
         	  // console.log(type,data,data1);
            } 
         
            function loadSerData(url) {
            	
            	let apiUrl=urls.BASE_API+url;
            	logService(0,apiUrl);
                logService(0,'Fetching all '+url);
                var deferred = $q.defer();
                $http.get(apiUrl)
                    .then(
                        function (response) {
                        	logService(0,'Fetched successfully all '+url);
                            $localStorage[url] = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                        	
                            console.error('Error while loading '+url );
                            $localStorage[url] = {};
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function setLocData(locvar,locdata){
            	
            	 logService(0,'Setting local data');
            	 logService(0,locdata);
                 $localStorage[locvar]=locdata;
            }

            function getLocData(locvar){
            	
                return $localStorage[locvar];
            }
            
            function postService(url,data,rurl){
            	
            	let apiUrl=urls.BASE_API+url;
            	logService(0,apiUrl);
            	logService(0,'Post data',data);
            	
            	var deferred=$q.defer();
            	$http.post(apiUrl,data)
            		.then(function (response){
            					loadSerData(rurl);
            					deferred.resolve(response.data);
            				},
            				function(errResponse){
            					deferred.reject(errResponse);
            				}	
            		);
            	return deferred.promise;
            }  
            
            function putService(url,data,rurl){
            	
            	let apiUrl=urls.BASE_API+url;
            	logService(0,apiUrl);
            	logService(0,'Put data',data);
            	
            	var deferred=$q.defer();
            	$http.put(apiUrl,data)
            		.then(function (response){
            					loadSerData(rurl);
            					deferred.resolve(response.data);
            				},
            				function(errResponse){
            					deferred.reject(errResponse);
            				}	
            		);
            	return deferred.promise;
            }   
            
            function removeService(url,rurl){
            	
            	let apiUrl=urls.BASE_API+url;
            	logService(0,'delete data',apiUrl);
            	
            	var deferred=$q.defer();
            	$http.delete(apiUrl)
            		.then(function (response){
            					loadSerData(rurl);
            					deferred.resolve(response.data);
            				},
            				function(errResponse){
            					deferred.reject(errResponse);
            				}	
            		);
            	return deferred.promise;
            }  
            
              
}]);

