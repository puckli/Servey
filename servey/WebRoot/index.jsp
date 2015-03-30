<%@ page language="java" import="java.util.*,org.json.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>��ӭ���뱾�ʾ����</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<link rel="stylesheet" type="text/css" href="css/index.css"> 
	<style type="text/css">
		body{
			width:800px;
			margin:0px auto 10px;
			position:relative;
  			background: url("img/bg.gif") repeat-x scroll center top #F9F7E8;
		}
		#nonserveydiv #p1{
			background-color:red;
			height:50px;
			width:600px;
			cursor:pointer;
		}
		.hide{
			display:none;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$("article").addClass("hide");
		});
	</script>
  </head>
  
  <body>
    <header>
    	<h1>��ӭ���������ʾ��������</h1>
    </header>
	<nav>
		<ul class="sf-menu sf-js-enabled sf-shadow" id="nav">
			<li class="custom"><a href="contact.html">CONTACT</a> </li>
			<li class="custom"><a href="contact.html">BLOG</a> </li>
			<li class="custom"><a href="contact.html">GALLERY</a></li>
			<li class="custom hoverli"><a href="about.html">ABOUT</a> 
				<ul style="display: none; visibility: hidden;">
					<li><a href="#">sub page1</a></li>
					<li><a href="#">sub page2</a></li>
					<li><a href="#">sub page3</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	
	<% String name = ((String)session.getAttribute("username"));
  		if(name == null) {
  		%>
  		<p id="loginc"><a href="login.jsp">��¼</a>|<a href="register.jsp">ע��</a> </p>
  		<%
  		}else{
  		 %>
  		 <p id="hello">����:<%=name %>| <a href="quitAction">�ǳ�</a></p>
  		 <%} %>
	
    <article id="haveservey">
    	<h3>��������������أ�</h3>
    	<div id="p1">
    		
    		<% if(session.getAttribute("username") == null){ %>
    		<p id="needlogin">
    			ĳЩ�ʾ���Ҫ��¼������ʾ��ת��<a href="login.jsp">��¼</a>
    		</p>
    	<% } %>
    	</div>
    </article>
    
    <article id="nonservey">
    	<h3>�Բ��𣬵�ǰû���ʺ�����д���ʾ�:-(</h3>
    	<div id="nonserveydiv">
    	 ԭ������ǣ�
    	
    	<% if(session.getAttribute("username") != null){ %>
    		<section>
    			�ʾ�δ������
    		</section>
    	<% }else{ %>
    		<section>
    			����û�е�¼��ת�� <a href="login.jsp">��¼</a>
    		</section>
    	<% } %>
    	</div>
    </article>
    
    <!-- ajax��ȡ�ʾ����� /��¼��-->
    <% if(session.getAttribute("username") != null){ %>
    <script type="text/javascript">
    	$(function(){
    		$.ajax({
    			type:"post",
    			url:"indexLoginAction",
    			data:"",
    			success:function(data){
    				if(data == null || data == "")
    				{
    					$("#nonservey").removeClass("hide");
    				}
    				else
    				{
    					$("#haveservey").removeClass("hide");
    					if(data.length == undefined)
    					{
	    					var id = data.id;
							var serveyName= data.name;
							var html = serveyName + "<a href=\"startFillServeyAction?serveyId=" + serveyId + "\"" + ">������ʾ�</a>";
							$("#p1").prepend(html);
						}
						else
						{
							for(var i=0;i < data.length;i++){
								var id = data[i].id;
								var serveyName= data[i].name;
								var serveyId = data[i].id;
								var html ="<p>" + serveyName + "<a href=\"startFillServeyAction?serveyId=" + serveyId + "\"" + ">������ʾ�</a></p>";
								$("#p1").prepend(html);
							}
						}
    				}
    				
    			},
    			error:function(xhr, type, Exception){
    				alert("error: " + Exception);
    			}
    		});
    		});
    </script>
    <% }
    else{ %>
     <script type="text/javascript">
    	$(function(){
    		$.ajax({
    			type:"post",
    			url:"indexUnloginAction",
    			data:"",
    			success:function(data){
    				if(data == null || data == "")
    				{
    					$("#nonservey").removeClass("hide");
    				}
    				else
    				{
    					$("#haveservey").removeClass("hide");
    					if(data.length == undefined)
    					{
	    					var id = data.id;
							var serveyName= data.name;
							var serveyId = data.id;
							var htmls ="<p onclick=\"go(this);\"><a href=''>" + serveyName + "</a></p>";
							var html = serveyName + "<a href=\"startFillServeyAction?serveyId=" + serveyId + "\"" + ">������ʾ�</a>";
							$("#p1").prepend(html);
						}
						else
						{
							for(var i=0;i < data.length;i++){
								var id = data[i].id;
								var serveyName= data[i].name;
								var serveyId = data[i].id;
								var html ="<p>" + serveyName + "<a href=\"startFillServeyAction?serveyId=" + serveyId + "\"" + ">������ʾ�</a></p>";
								$("#p1").prepend(html);
							}
						}
    				} 
    			},
    			error:function(xhr, type, Exception)
    			{
    				alert("error in unlogin: " + Exception);
    			}
    		});
    	});
    
    </script>
    <% } %>

  </body>
</html>
