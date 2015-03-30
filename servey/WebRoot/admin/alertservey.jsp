<%@ page language="java" import="java.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改问卷</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<script type="text/javascript" src="../servey/js/jquery-1.8.2.js"></script> 
	<script type="text/javascript" src="../servey/js/dateSelect.js"></script>
	<link rel="stylesheet" href="../servey/css/alterservey.css" type="text/css"/>
	<script type="text/javascript">
		$(function(){
		var myDate = new Date();
		$("#beginDateSelector").DateSelector({
				ctlYearId: 'idYear',
				ctlMonthId: 'idMonth',
				ctlDayId: 'idDay',
				defYear: myDate.getFullYear(),
				defMonth: (myDate.getMonth()+1),
				defDay: myDate.getDate(),
				minYear: 2012,
				maxYear: (myDate.getFullYear()+15)
		});
		$("#endDateSelector").DateSelector({
				ctlYearId: 'eidYear',
				ctlMonthId: 'eidMonth',
				ctlDayId: 'eidDay',
				defYear: myDate.getFullYear(),
				defMonth: (myDate.getMonth()+1),
				defDay: myDate.getDate(),
				minYear: 2012,
				maxYear: (myDate.getFullYear()+15)
		});
			$.ajax({
				type : "post",
				url : "admin/isadmin.action",
				data : "",
				success : function(data) {
				if(data == "noauthority")
				{
				/* 	self.location='login.jsp'; */
				}
				else{$("#showResult").click();}
				},
				error : function(xhr, type, exception) {
				}
			});
			
			
			
			/* 提交修改servey */
			$("#btnServey").click(function(){
				var serveyid = $("#serveyid").val();
				var serveyName = $("#serveyName").val();
				serveyName = $.trim(serveyName);
				var serveyDescription = $("#serveyDescription").val();
				serveyDescription = $.trim(serveyDescription);
				var gender = $(":input:radio:checked[name=gender]").val();
				var beginAge = $("#beginAge").val();
				var endAge = $("#endAge").val();
				var checkLogin = $(":input:radio:checked[name=checkLogin]").val();
				var beginDate = $("#idYear").val() + "-" + $("#idMonth").val() + "-" + $("#idDay").val();
				var endDate = $("#eidYear").val() + "-" + $("#eidMonth").val() + "-" + $("#eidDay").val();
				beginDate = $("#begindate").val();
				endDate = $("#enddate").val();
				if(serveyName == "" || serveyName.match(/^[ ]+$/) != null || serveyName.length < 4)
				{
					alert("问卷名称不能为空或过短！");
					return;
				}
				var result = serveyName.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， ~（）()、_=。；‘’“”""%￥！!#*@？?]+$/);
				if(result == null)
				{
					alert("问卷名称不能包含特殊字符！");
					return;
				}
				if(serveyDescription == "" || serveyDescription.match(/^[ ]+$/) != null || serveyDescription.length < 4)
				{
					alert("问卷描述不能为空或过短");
					return;
				}
				var result2 = serveyName.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，~ ()（）、_=。；‘’“”""%￥！!#*@？?]+$/);
				if(result2 == null)
				{
					alert("问卷描述不能包含特殊字符！");
					return;
				}
				if(beginAge.match(/^[0-9]+$/) == null || beginAge < 6)
				{
					alert("开始年龄必须为数字且大于6");
					return;
				}
				if(endAge.match(/^[0-9]+$/) == null || endAge > 100)
				{
					alert("结束年龄必须为数字且小于100");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"admin/alterServey",
					data:{serveyid:serveyid,serveyName:serveyName,serveyDescription:serveyDescription,gender:gender,beginAge:beginAge,endAge:endAge,checkLogin:checkLogin,beginDate:beginDate,endDate:endDate},
					success:function(data){
						if("error" == data)
						{
							
						}
						else if(data == "success"){
							$("#btnServey").attr("value","已修改").attr("disabled",true);
							alert("修改成功");
							window.close();
						}
					},
					error:function(xhr,type,exception){
						alert("error type:" + type + " exception " + exception);
					}
				});
				
			});
		});
	</script>
  </head>
  
  <body>
    <header>
    	<h1>问卷修改</h1>
    </header>
    
    <article>
    	<section id="serveyInfo">
    		<% out.print("<input type='hidden' value='"+request.getParameter("id")+ "' id='serveyid'>"); %>
   				<label for="serveyName">问卷名称:</label><input type="text" name="name" id="serveyName" maxlength="120" placeholder="不超过120个字符"> <br>
   				<label for="serveyDescription" style="vertical-align:top;">问卷描述:</label><textarea rows="2" placeholder="问卷描述信息，必填项" cols="46" name="serveyDescription" id="serveyDescription"></textarea><br>
   				<label for="">性别限制:</label><input type="radio" name="gender" value="M">男<input type="radio" name="gender" value="F">女<input type="radio" name="gender" value="A" checked="checked">all<br>
   				<label for="beginAge">最小年龄:</label><input type="text" name="beginAge" id="beginAge" min="12" max="100" step="number" placeholder="6~100之间的数字"><br>
   				<label for="beginAge">最大年龄:</label><input type="text" name="endAge" id="endAge" min="12" max="100" step="number" placeholder="6~100之间的数字"><br>
   				<label for="checkLogin">是否要求用户登陆:</label><input type="radio" name="checkLogin" value="yes">yes<input type="radio" name="checkLogin" value="no" checked="checked">no<br>
   				
   				开始日期:<input type="text" id="begindate"><span>格式必须符合xxxx-xx-xx</span>
   				<!-- <div id="beginDateSelector">
					<select id="idYear" name="idYear" data=""></select>年 <select id="idMonth" name="idMonth" data="12"></select>月 <select id="idDay" name="idDay" data="1"></select>日
				</div> -->
				<br>
   				结束日期:<input type="text" id="enddate"><span>格式必须符合xxxx-xx-xx</span>
   				<!-- <div id="endDateSelector">
					<select id="eidYear" name="eidYear" data=""></select>年 <select id="eidMonth" name="eidMonth" data="12"></select>月 <select id="eidDay" name="eidDay" data="1"></select>日
				</div> -->
				<br>
				<input type="button" value="提交修改" id="btnServey">
   				<br>
   			</section>
    </article>
          	<script>
	$(document).ready(function () {
		var serveyid = $("#serveyid").val();
		/* 获取servey信息 */
			$.ajax({
					type:"post",
					url:"admin/getServeyInfo",
					data:{serveyid:serveyid},
					success:function(data){
						$("#serveyName").val(data.name);
						$("#serveyDescription").val(data.description);
						if(data.gender == "F")
						{
							$("input[value='F']").attr("checked","checked");
						}
						else if(data.gender == "M"){
							$("input[value='M']").attr("checked","checked");
						}
						else{
							$("input[value='A']").attr("checked","checked");
						}
						if(data.checklogin == "yes")
						{
							$("input[value='yes']").attr("checked","checked");
						}
						else{
							$("input[value='no']").attr("checked","checked");
						}
						
						$("#beginAge").val(data.beginage);
						$("#endAge").val(data.endage);
						$("#begindate").val(data.begindate);
						$("#enddate").val(data.enddate);
					},
					error:function(xhr,type,exception){
						alert("获取问卷信息错误:" + type + " exception " + exception);
					}
			});
	});
	
	</script> 
  </body>
</html>
