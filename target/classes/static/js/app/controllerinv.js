'use strict';

app.controller('poController',[ 'Service','GlobData', '$scope','$compile', function(Service,GlobData,scope,$compile) {
	
	
	var self = this;
	var ctrlName="po";
	var ctrlName1="poItem";
	scope.globData=GlobData;
	scope.ctrlData={};
	scope.dynamicData =[];
	
	
	self.data =[];
	self.log=log;
	self.submit = submit;
	self.getData = getData;
	self.create = create;
	self.update = update;
	self.remove = remove;
	self.reset = reset;
	self.addModal=addModal;
	self.editModal=editModal;
	self.closeModal=closeModal;
	self.confirmModal=confirmModal;
	self.confirmYes=confirmYes;
	self.addRow=addRow;
	self.removeRow=removeRow;
	self.itemChange=itemChange;
	
	var tableVH="30vh";
	var viWidth=$(window).width();
	
	if(viWidth<600){  tableVH="20vh";  }else{  tableVH="40vh"; }
	
    scope.dataTableOptions = {
        columns: [
            { title: "S NO" },
            { title: "PO ID" },
            { title: "TRAN DATE" },
            { title: "AMOUNT"},
            { title: "EDIT" },
            { title: "DELETE" }
        ],
        columnMap: function (p,i) { 
            return [  i+1,p.id, p.tranDate, p.amount,
            	'<button data-ng-click="ctrl.editModal('+i+')" type="button" class="btn btn-sm btn-warning p-1 m-0"><i class="fas fa-pen"></i></button>',
            	'<button data-ng-click="ctrl.confirmModal('+p.id+')" type="button" class="btn btn-sm btn-danger p-1 m-0"><i class="fas fa-trash-alt"></i></button>' ]
        },
        order: [[ 0, "asc" ]],
        columnDefs: [
        	  { targets: 4 , orderable: false },
        	  { targets: 5 , orderable: false }
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
    
    scope.$watch('dynamicData',function(data){ self.data=data;  }, true);
    	
	function log(type,data,data1){
		Service.logService(type,data,data1);
	}
	function getData(key){
		log(0,key,Service.getData(key));
        return Service.getData(key);
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
				self.getData(ctrlName1+"list");						
				scope.dynamicData=[];
				scope.dataForm.$setPristine();
				self.closeModal();
			},
			function (errResponse){
				log(1,'Error while creating '+ctrlName);
			}
		)		
	}
	function update(data){		
		Service.putService('update'+ctrlName+'/',data,ctrlName+'list').then(
			function(response){
				log(0,ctrlName+' Updated successfully');
				self.getData(ctrlName1+'list');						
				scope.dynamicData=[];
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
				self.getData(ctrlName1+'list');						
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
		
		scope.dynamicData=[];
		var title=ctrlName.toUpperCase()+' ADD';
    	Service.modalService('addedit','modal',0,'modal-fluid','green lighten-1 align-middle p-2',title,true);
	}
	function editModal(index){
		
	    	self.data=self.getData('polist')[index].poItem;
	    	log(0,'editdata',self.data)
	    	scope.dynamicData=self.data;
			var title=ctrlName.toUpperCase()+' EDIT';
	    	Service.modalService('addedit','modal',1,'modal-fluid','green lighten-1 align-middle p-2',title,true);
	}
	
	function closeModal(){  $('#'+scope.globData.modalName).modal('hide'); }
	
	function confirmModal(id){  Service.confirmService(id);}
	
	function confirmYes(data){  self.remove(data);}
	
	function addRow(){
		var obj={itemQty:"1"};
		var itemArr=self.getData('itemlist');
		
		//obj.itemList=itemArr;
		obj.itemCode=itemArr[0].itemCode;
		obj.itemPrice=itemArr[0].itemPrice;
		scope.dynamicData.push(obj);
	}
	
	function removeRow(){
		
		scope.dynamicData.pop();
		
	}
	
	function itemChange(obj){
		
		obj.itemList.forEach(function(item){
			if(item.itemCode==obj.itemCode){ obj.itemPrice=item.itemPrice;}
		})
		
	}
   
}]);