<%@page import="java.util.Arrays"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.io.File,java.io.File,java.io.FilenameFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logs</title>
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/jarvis.datatable.respon.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/override.style.css">

</head>
<body>
<%
String fileName="",folderName="",fileOption="",user="",folderOpt="";
 if(request.getParameter("filename")!=null){ fileName=request.getParameter("filename");}
 if(request.getParameter("foldername")!=null){ folderName=request.getParameter("foldername");}
 if(request.getParameter("user")!=null){ user=request.getParameter("user");}
 MessageDigest md = MessageDigest.getInstance("MD5");

 byte[] messageDigest = md.digest(user.getBytes()); 

 BigInteger no = new BigInteger(1, messageDigest); 

 String hashtext = no.toString(16); 
 
 if(!folderName.equals("")){
	 
	String path= getServletContext().getRealPath("/")+"PaceLog/"+folderName+"/"; 
	 File[] files = new File(path).listFiles();

	 for (File file : files) {
	    if (file.isFile()) {
	        
	        fileOption+="<option value='"+file.getName()+"' >"+file.getName().replace(".json", "")+"</option>";
	    }
	 } 
	 
	 }
 
String fileName1=fileName.replace(".json","");
%>

<div class="container"  style="background-color: #fff"  >

	<%if(!fileName.equals("")){ // && user.equals("Pace") %>
	     <div class="col-md-12 p-2" align="center">
	     
	    	 <h3>Log  ( <%=fileName1 %> )</h3>
	     
	     </div>
		 <div class="col-md-12">
		 	<table id="example" class="table table-striped table-bordered dt-responsive nowrap" width="100%" cellspacing="0">

		    </table>
		</div>
		
		<%}else if(!folderName.equals("")) { %>
		<form method="POST" action="LogDisplay.jsp" name="logForm">
			<div class="row pt-5">
				<div class="col-md-offset-3 col-md-6">
					<label>Select File Name</label> <select class="form-control" name="filename">
						<%=fileOption%>
					</select>
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-offset-3 col-md-6 mt-5">
<!-- 					<label>User</label> <input type="password" class="form-control" name="user"> -->
					<input type="hidden" class="form-control" name="foldername" value="<%=folderName%>"> 
				</div>
				<div class="col-md-offset-3 col-md-6 mt-5">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</form>
		<%}else {%>
				<form method="POST" action="LogDisplay.jsp" name="logForm">
			<div class="row pt-5">
				<div class="col-md-offset-3 col-md-6">
					<label>Select Folder Name</label> <select class="form-control"
						name="foldername">
						<option value='cron' >cron</option>
						<option value='paytm' >paytm</option>
						<option value='test' >test</option>
					</select>
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-offset-3 col-md-6 mt-5">
<!-- 					<label>User</label> <input type="password" class="form-control"name="user"> -->
				</div>
				<div class="col-md-offset-3 col-md-6 mt-5">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</form>
		<%} %>
</div>

<script type="text/javascript" charset="utf8" src="assets/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" charset="utf8" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="assets/js/jarvis.datatable.responsive.min.js"></script>
<!-- <script type="text/javascript" charset="utf8" src="assets/js/jarvis.buttons.all.js"></script> -->

<script>


$(document).bind("contextmenu",function(e) {  e.preventDefault(); });
	
$(document).keydown(function(e){
	
if (e.ctrlKey && (  e.keyCode === 86 || e.keyCode === 85 ||  e.keyCode === 117) ||  e.keyCode === 123 ||  e.keyCode === 13 ) {
       
        return false;
        
    } else {  return true; }
       
    
});  

$.fn.dataTable.ext.errMode = function ( settings, helpPage, message ) { 
    console.log(message);
};

$(function (){
	
	var cols= [];

	<%if(!fileName.equals("")){ %>	
	
	$.get('<%=folderName+"/"+fileName%>',function(json){
		
    	for (key in json[0]){
			var colObj={};
			colObj.title=key.toUpperCase();colObj.data=key;
			cols.push(colObj);
    	}
        $('#example').dataTable({
      		data:json,
          //  order: [[ 3, "desc" ]],
            columns :cols,
            dom: 'Blfrtip',
            buttons: [ 'excel', 'csv' ],
            lengthMenu: [ [10, 25, 50, -1], [10, 25, 50, "All"] ]
             
        });
		
	});
	
    <% }%>
});
</script>
</body>
</html>