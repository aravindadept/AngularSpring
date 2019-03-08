<div class="container-fluid mt-3">
<div class="card border-success mb-3" >
  <div class="card-header p-0">
  	<div class="row px-2">
  
  		<div class="col-md-6">
  		<h6 class="font-weight-bold mt-3">CATEGORY MASTER</h6>
  		</div>
  		<div class="col-md-6 text-right">
  			<button data-ng-click="ctrl.addModal()" type="button" class="btn btn-sm btn-primary" >ADD</button>
			<button type="button" class="btn btn-sm btn-secondary">EXPORT</button>
  		</div>
  		
  	</div>
  </div>
  <div class="card-body p-1">
  
    <div class="panel panel-default">
        <div class="panel-body">
                <table class="table table-sm"   datatable options="dataTableOptions" tabledata="ctrl.getLocData('categorylist')"></table>
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
	                 	<div class="col-md-3"></div>
	                    <div class="col-md-6">                       
	                      <label for="categoryname">Category Name</label>
							<input valid-letter no-space data-ng-model="ctrl.data.categoryName" type="text" id="categoryName" class="form-control">
	                    </div> 
	                    <div class="col-md-3"></div>                   
	                 </div>  
	                 <div class="row" style="float:right" >
			      	  <button ng-disabled="dataForm.$invalid || dataForm.$pristine" type="submit" class="btn btn-primary btn-sm" >SUBMIT</button>
			      	  <button ng-show="globData.modalState==0" ng-disabled="dataForm.$pristine" ng-click="ctrl.reset()" type="button" class="btn btn-warning btn-sm">RESET</button>
			       </div>
	           </div>  

</script>
