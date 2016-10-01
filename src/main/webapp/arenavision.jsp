<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>arena</title>

	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<!--
		<div class="row">
			<% out.print(request.getContextPath()); %>
		</div>
		-->
		<div class="row">
			<table class="table table-hover table-bordered">
				${html}
			</table>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="<% out.print(request.getContextPath()); %>/resources/arena.js"></script>
	
	<script type="text/javascript"> 

	</script>

</body>

</html>
