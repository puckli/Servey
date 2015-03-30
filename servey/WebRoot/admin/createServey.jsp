<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'createServey.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta charset=UTF-8>
	<script type="text/javascript" src="../servey/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="../servey/js/dateSelect.js"></script>
	<link rel="stylesheet" href="../servey/css/createservey.css" type="text/css"/>
	<script type="text/javascript">
		/* 创建问题 */
		$(function(){
			/* 验证是否管理员身份 */
			$.ajax({
			type : "post",
			url : "admin/isadmin.action",
			data : "",
			success : function(data) {
			if(data == "noauthority")
			{
				self.location='login.jsp';
			}
			else{$("#showResult").click();}
			},
			error : function(xhr, type, exception) {
			}
			});
		
			$("#answerCount").blur(function(){
				var answerCount = $("#answerCount").val();
				if(answerCount.match(/[0-9]+$/) == null || answerCount>7 || answerCount<2)
					{
							alert("答案个数必须是2~7间的数字");
							$("#answerCount").focus();
					}
					else if(answerCount == null || answerCount == "")
					{
						alert("答案数量不能为空,请填写2~7间的数字");
						$("#answerCount").focus();
					}
					else{
					answerCount = Number(answerCount);
					$("#answerSection input:gt(" + answerCount + ")").attr({"disabled":"disabled"});
				}
				
			});
			$("#answerCount").focus(function(){
				$("#answerSection input:gt(0)").removeAttr("disabled");
			});
		
			$("#test").click(function(){
				var serveyName = $("#serveyName").val();
				var beginAge = $("#beginAge").val();
				var result = serveyName.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，、_=。；‘’“”""%￥！!#*@]+$/);
				var re = serveyName.match(/^[ ]+$/);
				var str = encodeURIComponent(serveyName);
				/* alert(result); 
				if(serveyName.match(/^[ ]+$/) != null || serveyName == "")
				{
					alert("不能为空");
				}  */
				var serveyNames = $.trim(serveyName);
				alert(serveyNames);
				/* alert(str.length);  */
			});
			/* 创建servey */
			$("#btnServey").click(function(){
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
			/* 	serveyName = encodeURIComponent(serveyName);
				serveyDescription = encodeURIComponent(serveyDescription); */
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
					url:"admin/createServeyAction",
					data:{serveyName:serveyName,serveyDescription:serveyDescription,gender:gender,beginAge:beginAge,endAge:endAge,checkLogin:checkLogin,beginDate:beginDate,endDate:endDate},
					success:function(data){
						if(data == "success")
						{
							$("#btnServey").attr("value","已提交").attr("disabled",true);
							$("#questionInfo").css("display","inline");
						}
					},
					error:function(xhr,type,exception){
						alert("error type:" + type + " exception " + exception);
					}
				});
				
			/* 	var html="题</strong><br>" + "问题：<input type=\"text\" name=\"question\"><br>问题类型：<select name=\"type\"><option value=\"1\">
				单选题类型</option><option value=\"2\">多选题类型</option><option value=\"3\">文字作答类型</option></select> <br>问题描述（可不填）：
				<input type=\"text\" name=\"questionDescription\"><br>答案1：<input type=\"text\" name=\"answer1\"><br>答案2：<input type=\"text\"
				 name=\"answer2\"><br>答案3：<input type=\"text\" name=\"answer3\"><br>答案4：<input type=\"text\" name=\"answer4\"><br>答案5：<input
				  type=\"text\" name=\"answer5\"><br>答案6：<input type=\"text\" name=\"answer6\"><br>答案7：<input type=\"text\" name=\"answer7\">
				  <br></p>"; */
			
			});
			/* 提交并添加一道题 */
			$("#btnadd").click(function(){
			/* 	var serveyId = $("#serveyId").val(); */
				var question = $("#question").val();
				question = $.trim(question);
				var questiontype = $("#questiontype").val();
				var questionDescription = $("#questionDescription").val();
				questionDescription = $.trim(questionDescription);
				var answer1 = $("#answer1").val();
				answer1 = $.trim(answer1);
				var answer2 = $("#answer2").val();
				answer2 = $.trim(answer2);
				var answer3 = $("#answer3").val();
				answer3 = $.trim(answer3);
				var answer4 = $("#answer4").val();
				answer4 = $.trim(answer4);
				var answer5 = $("#answer5").val();
				answer5 = $.trim(answer5);
				var answer6 = $("#answer6").val();
				answer6 = $.trim(answer6);
				var answer7 = $("#answer7").val();
				answer7 = $.trim(answer7);
				var answerCount = $("#answerCount").val();
				answerCount = $.trim(answerCount);
				if(questiontype == 3)
				{
					answerCount = 0;
				}
				
				if(question == "" || question.match(/^[ ]+$/) != null || question.length < 4)
				{
					alert("问题不能为空或过短");
					return;
				}
				var result3 = question.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， (（）)~、 _=。；‘’“”""%￥！!#*@？?]+$/);
				if(result3 == null)
				{
					alert("问题内容不能包含特殊字符！");
					return;
				}
				/* question = encodeURIComponent(question); */
				if(questionDescription != ""){
					/* questionDescription = encodeURIComponent(questionDescription); */
					var result4 = questionDescription.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
					if(result4 == null)
					{
						alert("答案描述不能包含特殊字符！");
						return;
					}
				}
				if(questiontype == 3){}
				else
				{
					if(answer1 != "")
					{
						var result7 = answer1.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer2 != "" || answer2.match(/^[ ]+$/) != null)
					{
						var result7 = answer2.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer3 != "" || answer3.match(/^[ ]+$/) != null)
					{
						var result7 = answer3.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer4 != "" || answer4.match(/^[ ]+$/) != null)
					{
						var result7 = answer4.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， 、 (（）)~_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer5 != "" || answer5.match(/^[ ]+$/) != null)
					{
						var result7 = answer5.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer6 != "" || answer6.match(/^[ ]+$/) != null)
					{
						var result7 = answer6.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer7 != "" || answer7.match(/^[ ]+$/) != null)
					{
						var result7 = answer7.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answerCount.match(/[0-9]+$/) == null || answerCount>7)
					{
							alert("答案个数必须是1~7间的数字");
							return;
					}
					else if(answerCount == null || answerCount == "")
					{
						alert("答案数量不能为空,请填写1~7间的数字");
						return;
					}
				}
				
				$.ajax({
					type:"post",
					url:"admin/createQuestionAction",
					data:{questionName:question,questiontype:questiontype,questionDescription:questionDescription,answer1:answer1,answer2:answer2,answer3:answer3,answer4:answer4,answer5:answer5,answer6:answer6,answer7:answer7,answerCount:answerCount},
					success:function(data){
						if(data == "success")
						{
							$("#reset").click();
							var i = $("#questionId").text();
							$("#questionId").text(Number(i) + Number(1));
						}
						else if(data == "noServey")
						{
							alert("您还没有创建问卷，请在上面问卷区创建问卷");
						}
					},
					error:function(xhr,type,exception){
						alert("error type:" + type + " exception " + exception);
					}
				});
			});
			/* 完成问题创建 */
			$("#btnover").click(function(){
				var serveyId = $("#serveyId").val();
				var question = $("#question").val();
				question = $.trim(question);
				var questiontype = $("#questiontype").val();
				var questionDescription = $("#questionDescription").val();
				questionDescription = $.trim(questionDescription);
				var answer1 = $("#answer1").val();
				answer1 = $.trim(answer1);
				var answer2 = $("#answer2").val();
				answer2 = $.trim(answer2);
				var answer3 = $("#answer3").val();
				answer3 = $.trim(answer3);
				var answer4 = $("#answer4").val();
				answer4 = $.trim(answer4);
				var answer5 = $("#answer5").val();
				answer5 = $.trim(answer5);
				var answer6 = $("#answer6").val();
				answer6 = $.trim(answer6);
				var answer7 = $("#answer7").val();
				answer7 = $.trim(answer7);
				var answerCount = $("#answerCount").val();
				answerCount = $.trim(answerCount);
				if(questiontype == 3)
				{
					answerCount = 0;
				}
				
				if(question == "" || question.match(/^[ ]+$/) != null || question.length < 4)
				{
					alert("问题不能为空或过短");
					return;
				}
				var result5 = question.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
				if(result5 == null)
				{
					alert("问题内容不能包含特殊字符！");
					return;
				}
				/* question = encodeURIComponent(question); */
				if(questionDescription != "")
				{
					/* questionDescription = encodeURIComponent(questionDescription); */
					var result6 = questionDescription.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
					if(result6 == null)
					{
						alert("答案描述不能包含特殊字符！");
						return;
					}
				}
				if(questiontype == 3){}
				else
				{
					if(answer1 != "")
					{
						var result7 = answer1.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer2 != "" || answer2.match(/^[ ]+$/) != null)
					{
						var result7 = answer2.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， (（）) ~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer3 != "" || answer3.match(/^[ ]+$/) != null)
					{
						var result7 = answer3.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer4 != "" || answer4.match(/^[ ]+$/) != null)
					{
						var result7 = answer4.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，  (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer5 != "" || answer5.match(/^[ ]+$/) != null)
					{
						var result7 = answer5.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， (（）) ~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer6 != "" || answer6.match(/^[ ]+$/) != null)
					{
						var result7 = answer6.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， (（）) ~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answer7 != "" || answer7.match(/^[ ]+$/) != null)
					{
						var result7 = answer7.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;， (（）)~、_=。；‘’“”""%￥！!#*@？?]+$/);
						if(result7 == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
					if(answerCount.match(/^[0-9]+$/) == null || answerCount>7)
					{
							alert("答案数量必须是1~7间的数字");
							return;
					}
					else if(answerCount == null || answerCount == "")
					{
						alert("答案数量不能为空,请填写1~7间的数字");
						return;
					}
				}
				
				$.ajax({
					type:"post",
					url:"admin/createQuestionAction",
					data:{questionName:question,questiontype:questiontype,questionDescription:questionDescription,answer1:answer1,answer2:answer2,answer3:answer3,answer4:answer4,answer5:answer5,answer6:answer6,answer7:answer7,answerCount:answerCount},
					success:function(data){
						if(data == "success")
						{
							$("#reset").click();
							$("#btnadd").attr("disabled","disabled");
							$("#btnover").attr("disabled","disabled");
							$("#reset").attr("disabled","disabled");
							alert("创建成功，您可以关闭此页了！");
						}
						else if(data == "noServey")
						{
							alert("您还没有创建问卷，请在上面问卷区域创建问卷");
						}
					},
					error:function(xhr,type,exception){
						alert("error type:" + type + " exception " + exception);
					}
				});
			});
		});
		
		function disableAns()
		{
			$("#answerSection input").attr("disabled","disabled");
		}
		function enableAns()
		{
			$("#answerSection input").removeAttr("disabled","disabled");
		}
		
		
	</script>
	<style type="text/css">
		input[type=text]{
			width:400px;
		}
		#questionInfo{
			/* display:none; */
		}
		.read{
			disabled:"disabled";
		}
	</style>
	
  </head>
  <body>
  		<header>
  			<h1>问卷创建</h1>
  		</header>
    
      	<article id="createServeydiv">
   			<section id="serveyInfo">
   				<!-- <input type="button" value="test" id="test"> -->
   				<label for="serveyName">问卷名称:</label><input type="text" name="name" id="serveyName" maxlength="120" placeholder="不超过120个字符"> <br>
   				<label for="serveyDescription" style="vertical-align:top;">问卷描述:</label><textarea rows="2" placeholder="问卷描述信息，必填项" cols="46" name="serveyDescription" id="serveyDescription"></textarea><br>
   				<label for="">性别限制:</label><input type="radio" name="gender" value="M">男<input type="radio" name="gender" value="F">女<input type="radio" name="gender" value="A" checked="checked">all<br>
   				<label for="beginAge">最小年龄:</label><input type="text" name="beginAge" id="beginAge" min="12" max="100" step="number" placeholder="6~100之间的数字"><br>
   				<label for="beginAge">最大年龄:</label><input type="text" name="endAge" id="endAge" min="12" max="100" step="number" placeholder="6~100之间的数字"><br>
   				<label for="checkLogin">是否要求用户登陆:</label><input type="radio" name="checkLogin" value="yes">yes<input type="radio" name="checkLogin" value="no" checked="checked">no<br>
   				
   				开始日期:
   				<div id="beginDateSelector">
					<select id="idYear" name="idYear" data=""></select>年 <select id="idMonth" name="idMonth" data="12"></select>月 <select id="idDay" name="idDay" data="1"></select>日
				</div>
   				结束日期：
   				<div id="endDateSelector">
					<select id="eidYear" name="eidYear" data=""></select>年 <select id="eidMonth" name="eidMonth" data="12"></select>月 <select id="eidDay" name="eidDay" data="1"></select>日
				</div>
				<!-- 问题数量：<input type="text" id="questionNum" name="questionNum"> -->
				<input type="button" value="提交并创建问题" id="btnServey">
   				<br>
   			</section>
   			<section id="questionInfo">
   				<p>注意：问题信息一旦创建将不能更改</p>
   				<form>
   				<%-- 	<input name="serveyId" value=<%=session.getAttribute("serveyId")%> id="serveyId">  --%> <%-- value=<%=session.getAttribute("serveyId")%> --%>
   					<strong> 第<span id="questionId">1</span> 题</strong><br>
   					问题：<input type="text" name="question" id="question" placeholder="问题内容不超过120个字符"><br>
   					问题类型：<select name="type" id="questiontype"><option value="1" onclick="enableAns();">单选题类型</option><option value="2" onclick="enableAns();">多选题类型</option> </select> <br>
   					问题描述：<input type="text" name="questionDescription" id="questionDescription" placeholder="非必填项"><br>
   					<div id="answerSection">
   					答案个数：<input type="text" name="answerCount" id="answerCount" min="2" max="7" step="number" placeholder="2~7之间的数字"><br>
   					答案1：<input type="text" name="answer1" id="answer1"><br>
   					答案2：<input type="text" name="answer2" id="answer2"><br>
   					答案3：<input type="text" name="answer3" id="answer3"><br>
   					答案4：<input type="text" name="answer4" id="answer4"><br>
   					答案5：<input type="text" name="answer5" id="answer5"><br>
   					答案6：<input type="text" name="answer6" id="answer6"><br>
   					答案7：<input type="text" name="answer7" id="answer7"><br>
   					</div>
   					<input type="button" value="提交" id="btnadd">
   					<input type="button" value="提交并完成创建" id="btnover">
   					<input type="reset" value="清空重填" id="reset">
   				</form>
   			</section>
    	</article>
    
       	<script>
	$(document).ready(function () {
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
	});
	
	</script> 
    
  </body>
</html>
