'use strict';

app.factory('GlobData', function() {  
	return {username:"root",password:"root"};
});


app.factory('Service',['$localStorage', '$http', '$q', 'urls', 'GlobData',
        function ($localStorage, $http, $q, urls, GlobData) {

            var factory = {
            	loadData: loadData,
                setData : setData,
                getData : getData,
                postService: postService,
                putService : putService,
                removeService : removeService,
                logService : logService,
                modalService : modalService,
                confirmService:confirmService
            };
         
            return factory;
            
            function logService(type,data,data1){
          	   if(type==0){type="info: ";}
          	   if(type==1){type="err: ";}
          	   if(typeof(data1)=='undefined'){data1="";}
          	   console.log(type,data,data1);
             } 
            
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
            
            function setData(key,data){
            	
           	 logService(0,'Setting localStorage data');
           	 logService(0,data);
                $localStorage[key]=data;
           }

           function getData(key){
           	
               return $localStorage[key];
           }
         
            function loadData(url) {
            	
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
            
            function postService(url,data,rurl){
            	
            	let apiUrl=urls.BASE_API+url;
            	logService(0,apiUrl);
            	logService(0,'Post data',data);
            	
            	var deferred=$q.defer();
            	$http.post(apiUrl,data)
            		.then(function (response){
            					loadData(rurl);
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
            					loadData(rurl);
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
            					loadData(rurl);
            					deferred.resolve(response.data);
            				},
            				function(errResponse){
            					deferred.reject(errResponse);
            				}	
            		);
            	return deferred.promise;
            }  
            
              
}]);

