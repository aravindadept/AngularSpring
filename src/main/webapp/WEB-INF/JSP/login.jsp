<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" >
<head>
        <title>${title}</title>
  		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  		<link href="css/bootstrap.min.css" rel="stylesheet">
  		<link href="css/mdb.min.css" rel="stylesheet">
 		<link href="css/style.css" rel="stylesheet">
 		
</head>
<body  >
  

<div class="container">

<div class="row">

<div class="col-md-6 offset-md-3 mt-5">

<!-- Default form login -->
<form class="text-center border border-light p-5 white" action="logincheck" method="post">

    <p class="h4 mb-4">Sign in</p>

    <!-- Email -->
    <input type="text" id="username"  name="username" class="form-control mb-4" placeholder="Username">

    <!-- Password -->
    <input type="password" id="password" name="password"  class="form-control mb-4" placeholder="Password">

    <div class="d-flex justify-content-around">
        <div>
            <!-- Forgot password -->
            <a href="">Forgot password?</a>
        </div>
    </div>

    <!-- Sign in button -->
    <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

    <!-- Register -->
    <p>Not a member?
        <a href="">Register</a>
    </p>


</form>
<!-- Default form login -->

</div>

</div>

</div>


        <script type="text/javascript" src="js/lib/jquery-3.3.1.min.js"></script>
 		<script type="text/javascript" src="js/lib/popper.min.js"></script>
 	 	<script type="text/javascript" src="js/lib/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/lib/mdb.min.js"></script>
    </body>
</html>