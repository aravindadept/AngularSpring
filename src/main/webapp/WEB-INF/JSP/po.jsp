<div class="container-fluid mt-3">
<div class="card border-success mb-3" >
  <div class="card-header p-0">
  	<div class="row px-2">
  
  		<div class="col-md-6">
  		<h6 class="font-weight-bold mt-3">PO MASTER</h6>
  		</div>
  		<div class="col-md-6 text-right">
  			<button data-ng-click="ctrl.addModal()" type="button" class="btn btn-sm btn-primary" >ADD</button>
			<button type="button" class="btn btn-sm btn-secondary">EXPORT</button>
  		</div>
  		
  	</div>
  </div>
  <div class="card-body p-1" >
  
    <div class="panel panel-default">
        <div class="panel-body">
                <table class="table table-sm"   datatable options="dataTableOptions" tabledata="ctrl.getData('polist')"></table>
        </div>
    </div>
    
    
</div>
</div>
</div>

<form  ng-submit="ctrl.submit()" name="dataForm" id="dataForm" > 
  <modal  name="globData.modalName"  modal-class="globData.modalClass" 
	 	  header-class="globData.modalHeaderClass" header-title="globData.modalTitle" body="globData.modalBody" close-btn="globData.modalClose">
	 	 
	 	  <div  ng-include="globData.modalUrl"></div>
	
  </modal>
 </form> 

<script type="text/ng-template" id="addedit" >

	       <div class="panel-body" >

	       			  <div class="row mb-2">
						 <button ng-hide="globData.modalState==1" ng-click="ctrl.addRow()" type="button" class="btn btn-primary btn-sm">ADD ITEM</button>
 						 <button ng-hide="globData.modalState==1" ng-disabled="dynamicData.length==0" ng-click="ctrl.removeRow()" type="button" class="btn btn-danger btn-sm">REMOVE ITEM</button>
			     	  </div>
	                 <div class="row" data-ng-repeat="x in dynamicData track by $index">
	                    <div class="col-md-3">
        				   <label for="categoryId">Item Name</label>
	                        <select ng-model="x.itemCode" ng-change="ctrl.itemChange(x)" class="browser-default custom-select" 
	                        ng-options="obj.itemCode as obj.itemName for obj in ctrl.getData('itemlist')">
	                        </select> 
	                    </div>
        				<div class="col-md-3">
	                      <label for="itemQty">Item Quantity</label>
							<input type="text"  ng-model="x.itemQty" id="itemQty" class="form-control" valid-number>
	                    </div>
	                    <div class="col-md-3">
	                      <label for="itemPrice">Item Price</label>
							<input type="text" ng-model="x.itemPrice" id="itemPrice" class="form-control"  ng-readonly="true">
	                    </div>
	                    <div class="col-md-3">
	                      <label for="amount">Amount</label>
							<input type="text"  ng-model="x.amount" id="amount" ng-bind="x.amount=x.itemQty*x.itemPrice"   class="form-control" ng-readonly="true" >
	                    </div>
	                 </div>    
	                 <div class="row float-right" >
			      	  <button ng-disabled="dataForm.$invalid || dataForm.$pristine" type="submit" class="btn btn-primary btn-sm" >SUBMIT</button>
			      	  <button ng-show="globData.modalState==0" ng-disabled="dataForm.$pristine" ng-click="ctrl.reset()" type="button" class="btn btn-warning btn-sm">RESET</button>
			       </div>
	           </div>  

  </script>  
 