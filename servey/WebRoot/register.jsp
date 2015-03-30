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
    
    <title>用户注册|问卷调查|regist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="问卷调查,注册">
	<meta http-equiv="description" content="问卷调查注册页">
 
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/regist.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/register.css">
	

  </head>
  
  <body>
   <header>
    	<h1>问卷调查网注册</h1>
    </header>
    <section>
    	<article>
    		<div id="forms">
    	<%-- 	<s:actionerror/>  <s:fielderror></s:fielderror>
	    	<s:form action="registerAction" id="form1" method="post">
	    	
	    	<s:textfield name="username" label="账号" id="name" required="required" min="3" max="15"></s:textfield> 
	    	<s:password name="password" label="密码" id="pwd" required="required" min="3" max="15"></s:password> 
	    	<s:password name="repassword" label="重复密码" id="repwd" required="required" min="3" max="15"></s:password> 
	    	<s:textfield name="age" label="年龄" id="age" step="number" required="required"></s:textfield> 
	    	<s:radio label="性别" list="#{'M':'男', 'F': '女'}" name="gender"></s:radio>
	    	<s:submit value="submit" id="sub"></s:submit>
	    	
	    	</s:form> --%>
    		
     
  		<s:actionerror/>  <s:fielderror></s:fielderror>
	    <form action="registerAction" method="post">
	    	<label for="name">账号：</label><input type="text" name="username" id="name" required="required" maxlength="15" pattern="[a-zA-Z0-9_]{3,15}" placeholder="3~15位的字母或数字或‘_’组成"><br>
	    	<label for="pwd">密码：</label><input type="password" name="password" id="pwd" required="required" maxlength="15" pattern="[a-zA-Z0-9_]{3,15}" placeholder="3~15位的字母或数字或‘_’组成"><br>
	    	<label for="repwd">确认密码：</label><input type="password" name="repassword" id="repwd" required="required" maxlength="15" pattern="[a-zA-Z0-9_]{3,15}" placeholder="3~15位的字母或数字或‘_’组成"><br>
	    	<label for="age">年龄：</label><input type="text" name="age" id="age" step="number" required="required" min="12" max="100"><br>
	    	<label for="">性别：</label><input type="radio" name="gender" checked="checked" value="F">男 <input type="radio" name="gender" value="M">女<br>
	    	<input type="submit" value="提交" id="sub">
	    </form>
    	<a href="login.jsp">已有账号?前去登录</a>
    		</div>
    	  
    	  <footer>
    		immortal company ©2013-05
    	  </footer>
    	</article>
    </section>
    
  </body>
</html>
