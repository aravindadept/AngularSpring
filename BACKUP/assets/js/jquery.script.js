$(document).ready(function() {

	
	
	var outnam=$('#out_nam').val();
	
	$('#example').DataTable( {	
		
		
//		"responsive": true,
//      "serverSide": true,
    //    "searching": false,
        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "All"] ],
       
        "ajax":{
        	  
        	    "url" : "JsonTableData.jsp", // json datasource
        
        
               },
         dom: 'Blfrtip',
         buttons: [
        	 'pdf', 'excel', 'csv'
                  ]
          
});

	$( "#out_nam" ).keyup(function() {	
	
		
	    $('#example').DataTable().ajax.reload(); 
	} );
    
	$('#outlet_frm').submit(function(event) 
     {
	   event.preventDefault();
     //	alert($(this).serialize());
    	
    	$.ajax({
    		type:"POST",
    		url:"outlet_insert.jsp",
    		data:$(this).serialize(),
    		datatype:'json',
    		success: function(result){
    			if(result==1){
    			alert("inserted");
    			}else{
    				alert("error")
    			}
    			$('#example').DataTable().ajax.reload();
    			
    		},async:false
    		
    	});
    	
   	
    });
	
    
	} );