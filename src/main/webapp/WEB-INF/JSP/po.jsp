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
	 	 
	 	  <div ng-include="globData.modalUrl"></div>
	
  </modal>
 </form> 

<script type="text/ng-template" id="addedit">

	       <div class="panel-body">
	                 <div class="row">
	                    <div class="col-md-6">
	                      <label for="itemCode">Item Code</label>
							<input type="text" ng-model="ctrl.data.itemCode" id="itemCode" class="form-control">
	                    </div>
	                    <div class="col-md-6">
	                      <label for="itemPrice">Item Price</label>
							<input type="text" ng-model="ctrl.data.itemPrice" id="itemPrice" class="form-control">
	                    </div>
	                    <div class="col-md-6">
	                      <label for="itemPrice">Amount</label>
							<input type="text" ng-model="ctrl.data.amount" id="amount" class="form-control">
	                    </div>
	                 </div>    
	                 <div class="row" style="float:right" >
			      	  <button ng-disabled="dataForm.$invalid || dataForm.$pristine" type="submit" class="btn btn-primary btn-sm" >SUBMIT</button>
			      	  <button ng-show="globData.modalState==0" ng-disabled="dataForm.$pristine" ng-click="ctrl.reset()" type="button" class="btn btn-warning btn-sm">RESET</button>
			       </div>
	           </div>  

</script>
 