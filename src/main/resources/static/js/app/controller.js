'use strict';

app.controller('homeController',[ 'Service','GlobData','$scope', function(Service,GlobData,scope) {
	
	var self = this;
	scope.globData=GlobData;
	scope.ctrlData={};
	
	var menuData=[ 
					{
						name:"Masters",
						menuList:[ 
							{ name:"Category" , state:"category" ,  subMenu :[] },
							{ name:"Item" , state:"item" , subMenu :[] } 
						  ] 
					}, 
					{ 
						name:"Inventry" , 
						menuList:[ 
 							{ name:"Po" , state:"po" , subMenu :[] },
 							{ name:"Grn" , state:"grn" , subMenu :[] } 
 						  ]
					} 
				  ];
	
	
	scope.globData.menuData=menuData;
	
	
}]);

app.controller('categoryController',[ 'Service','GlobData','$scope','$compile', function( Service, GlobData, scope ,$compile ) {
	
	var self = this;
	var ctrlName="category";
	scope.globData=GlobData;
	scope.ctrlData={};

	var tableVH="30vh";
	var viWidth=$(window).width();
	
	if(viWidth<600){  tableVH="20vh";  }else{  tableVH="40vh"; }
	
    scope.dataTableOptions = {
        columns: [
            { title: "S NO" },
            { title: "CATEGORY NAME" },
            { title: "EDIT" },
            { title: "DELETE" }
        ],
        columnMap: function (p,i) { 
            return [  i+1, p.categoryName, 
            	'<button data-ng-click="ctrl.editModal('+p.categoryId+')" type="button" class="btn btn-sm btn-warning p-1 m-0"><i class="fas fa-pen"></i></button>',
            	'<button data-ng-click="ctrl.confirmModal('+p.categoryId+')" type="button" class="btn btn-sm btn-danger p-1 m-0"><i class="fas fa-trash-alt"></i></button>' ]
        },
        order: [[ 0, "asc" ]],
        columnDefs: [
        	  { targets: 2 , orderable: false },
        	  { targets: 3 , orderable: false }
        	],
        scrollY:tableVH,
        scrollCollapse: true,
        sScrollX: "100%", 
        sScrollXInner: "100%", 
        bScrollCollapse: true,
       	rowCallback: function(row) {  
		    if (!row.compiled) {
		      $compile(angular.element(row))(scope);
		      row.compiled = true;  
		    }  
		  }
    };

	self.data = {};
	self.submit = submit;
	self.getLocData = getLocData;
	self.create = create;
	self.update = update;
	self.remove = remove;
	self.reset = reset;
	self.addModal=addModal;
	self.editModal=editModal;
	self.closeModal=closeModal;
	self.confirmModal=confirmModal;
	
	function log(type,data,data1){
		Service.logService(type,data,data1);
	}
	function getLocData(locvar){
		log(0,locvar,Service.getLocData(locvar));
        return Service.getLocData(locvar);
	}	
	function submit(){	
		if(scope.globData.modalState==0){
			log(0,'Submitting Saving New '+ctrlName, self.data);
			create(self.data);	
		}else if(scope.globData.modalState==1){
			log(0,'Submitting Update '+ctrlName, self.data);
			update(self.data);	
		}		
	}
	function create(data){		
		Service.postService('create'+ctrlName,data,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' created successfully');
				getLocData(ctrlName+"list");						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while creating '+ctrlName);
			}
		)		
	}
	function update(data){		
		
		scope.dataTableOptions.scrollY="10vh";
		Service.putService('update'+ctrlName+'/'+data.categoryId,data,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' Updated successfully');
				getLocData(ctrlName+'list');						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while updating '+ctrlName);
			}
		)		
	}
	function remove(id){		
		Service.removeService('remove'+ctrlName+'/'+id,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' Removed successfully');
				getLocData(ctrlName+'list');						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while Removing '+ctrlName);
			}
		)		
	}
	function reset() {
		self.data = {};
		scope.dataForm.$setPristine();		
	}	
	function addModal(){
		
		self.data={};
		var title=ctrlName.toUpperCase()+' ADD';
    	Service.modalService('addedit','modal',0,'modal-lg','green lighten-1 align-middle p-2',title,true);
	}
	function editModal(id){
		
		let temp='single'+ctrlName+'/'+id;
		
	    Service.loadSerData(temp).then(function(){
	    	
	    	self.data=getLocData(temp);
			var title=ctrlName.toUpperCase()+' EDIT';
			
	    	Service.modalService('addedit','modal',1,'modal-lg','green lighten-1 align-middle p-2',title,true);
	    }
	    )
	}
	
	function closeModal(){  $('#'+scope.globData.modalName).modal('hide'); }
	
	function confirmModal(id){  Service.confirmService(id);}
	
}]);

app.controller('itemController',[ 'Service','GlobData', '$scope', function(Service,GlobData,scope) {
	var self = this;
	var ctrlName="item";
	scope.globData=GlobData;
	scope.ctrlData={};
	
	   $(document).ready(function () {
		   
	       	$('#datatable').DataTable({
	       	  scrollY:"50vh",
	          scrollCollapse: true,
	          sScrollX: "100%", 
	          sScrollXInner: "100%", 
	          bScrollCollapse: true
	       	});
	       	
	     });
		
	
	self.data = {};
	self.submit = submit;
	self.getLocData = getLocData;
	self.create = create;
	self.update = update;
	self.remove = remove;
	self.reset = reset;
	self.addModal=addModal;
	self.editModal=editModal;
	self.closeModal=closeModal;
	self.confirmModal=confirmModal;

	function log(type,data,data1){
		Service.logService(type,data,data1);
	}
	function getLocData(locvar){
		log(0,locvar,Service.getLocData(locvar));
        return Service.getLocData(locvar);
	}		
	function submit(){	
		if(scope.globData.modalState==0){
			log(0,'Submitting Saving New '+ctrlName, self.data);
			create(self.data);	
		}else if(scope.globData.modalState==1){
			log(0,'Submitting Update '+ctrlName, self.data);
			update(self.data);	
		}		
	}
	function create(data){		
		Service.postService('create'+ctrlName,data,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' created successfully');
				getLocData(ctrlName+"list");						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while creating '+ctrlName);
			}
		)		
	}
	function update(data){		
		Service.putService('update'+ctrlName+'/'+data.categoryId,data,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' Updated successfully');
				getLocData(ctrlName+'list');						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while updating '+ctrlName);
			}
		)		
	}
	function remove(id){		
		Service.removeService('remove'+ctrlName+'/'+id,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' Removed successfully');
				getLocData(ctrlName+'list');						
				self.data={};
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while Removing '+ctrlName);
			}
		)		
	}
	function reset() {
		self.data = {};
		scope.dataForm.$setPristine();
	}
	
	function addModal(){
		
		self.data={};
		var title=ctrlName.toUpperCase()+' ADD';
		self.data.categoryId=getLocData('categorylist')[0].categoryId;
    	Service.modalService('addedit','modal',0,'modal-lg','green lighten-1 align-middle p-2',title,true);
	}
	function editModal(id){
		
		let temp='single'+ctrlName+'/'+id;
		
	    Service.loadSerData(temp).then(function(){
	    	
	    	self.data=getLocData(temp);
			var title=ctrlName.toUpperCase()+' EDIT';
			
	    	Service.modalService('addedit','modal',1,'modal-lg','green lighten-1 align-middle p-2',title,true);
	    }
	    )
	}
	
	function closeModal(){  $('#'+scope.globData.modalName).modal('hide'); }
	
	function confirmModal(id){  Service.confirmService(id);}
   
}]);