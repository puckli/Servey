<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'replyover.jsp' starting page</title>
    
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
		.qv{
			background-color:#ED813B;
			width:700px;
			height:80px;
			font-size:40px;
			line-height:80px;
			text-align:center;
			font-weight:bold;
			box-shadow:6px 3px 4px 3px,1px 6px 5px 4px #ff0000;
			border-radius:5px;
			transform: rotate(7.5deg);
			-webkit-transform: rotate(7.5deg);
		}
	</style>
  </head>
  
  <body>
   	<p class="qv">问卷填写完毕，非常感谢您的参与 :)</p>
   	
   	<p>by 李文忠  软件1班 0908054129 Thanks!</p>
   	
  </body>
</html>
