<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registersuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{
			background-color:#D9D1CE;
			width:600px;
			margin:200px auto 50px;
		}
		p{
			background-color:#F47D31;
			width:600px;
			height:80px;
			font-size:50px;
			line-height:80px;
			text-align:center;
			font-weight:bold;
			box-shadow:6px 3px 4px 3px,1px 6px 5px 4px #ff0000;
			border-radius:5px;
		}
		#tran{
			background-color:#F47D31;
			width:600px;
			height:80px;
			font-size:50px;
			line-height:80px;
			text-align:center;
			font-weight:bold;
			box-shadow:6px 3px 4px 3px,1px 6px 5px 4px #ff0000;
			border-radius:5px;
			transform: rotate(7.5deg)  translate(0px,-40px);
			-webkit-transform: rotate(7.5deg) translate(0px,-40px);
		}
		#tran2{
			background-color:#F47D31;
			width:600px;
			height:80px;
			font-size:50px;
			line-height:80px;
			text-align:center;
			font-weight:bold;
			box-shadow:6px 3px 4px 3px,1px 6px 5px 4px #ff0000;
			border-radius:5px;
			/* transform: rotate(-7.5deg);
			-webkit-transform: rotate(-7.5deg); */
		}
		#tran3{
			transform: skew(-7.5deg,0);
			-webkit-transform: skew(-30.5deg,0);
		}
	</style>
  </head>
  
  <body>
    <p id="tran">hello: <%=session.getAttribute("username") %>  </p>
     <p id="tran2">恭喜你，注册成功！</p>
     
     <p id="tran3"><a href="index.jsp"> >>跳转到问卷主页 </a></p>
  </body>
</html>
