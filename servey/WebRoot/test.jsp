<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<!-- <link rel="stylesheet" type="text/css" href="css/animated-menu.css">
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/animated-menu.js"></script>
	<script type="text/javascript" src="js/test.js"></script> -->
	
	<style type="text/css">
	
	</style>
</head>
<body>
	<div id="wrap">
		'${request.name }'.<br>
		<%=request.getAttribute("name") %>
	</div>
<script type="text/javascript">
	
</script>



</body>
</html>


