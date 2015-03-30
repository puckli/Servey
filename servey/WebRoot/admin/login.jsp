<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login to servey</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		section{
	margin:50px auto 0;
	-webkit-background-color:white;
	background-image:linear-gradient(to top,#999,#eee);
	width:220px;
	height:400px;
	box-shadow:1px 1px 6px 2px;
	border-radius:15px;
	padding:30px 60px 20px;
	line-height:34px;
	font-size:20px;
	font-family:Tahoma;
		}
		body{
	padding:0;
	background-color:#ddd;
	width:800px;
	height:500px;
	margin:20px auto 10px;
	font-size:16px;
}
#sub{
	height:24px;
	width:60px;
	font-size:16px;
	line-height:18px;
	display:block;
	margin:10px 0px 0 153px;
	background-image:-webkit-linear-gradient(top,#ccc,#777);
	background-image:-moz-linear-gradient(top,#ccc,#777);
}
input[type=text]:focus,input[type=password]:focus{
	background-color:pink;
}
section ul li{
	margin:0;
	padding:0;
	font-size:14px;
	line-height:16px;
	color:red;
}
h3{
	margin:0 auto;
	padding:0;
	text-align:center;
	margin-bottom:20px;
	color:#51433A;
}
	</style>

  </head>
  
  <body>
  	<section>
  		<h3>问卷系统登录</h3>
	    <s:actionerror/>
	    <form action="loginAction" method="post">
	   		账号：<input type="text" name="username" required="required"><br>
	    	密码：<input type="password" name="password" required="required"><br>
	    	<input type="submit" id="sub" value="登录">
	    </form>
	    <br>
		<a href="register.jsp">还没账号，前去注册！</a><br>
		<a href="index.jsp">直接参与调查</a>
	</section>
  </body>
</html>
