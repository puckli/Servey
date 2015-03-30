<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎参与名为“<%=session.getAttribute("serveyName") %>”<s:property value="request.serveyName"/> 的问卷调查</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<link rel="stylesheet" type="text/css" href="css/replyservey.css">
	<style type="text/css">
	</style>
	
  </head>
  
  <body>
  	<header>
  		<p>Questionnaire </p>
  		<p id="lastc">Creative.value</p>
  		<% String name = ((String)session.getAttribute("username"));
  		if(name == null) {
  		%>
  		<span><a href="login.jsp">登录</a> </span>
  		<%
  		}else{
  		 %>
  		 <span>您好:<%=name %> </span>
  		 <%} %>
  	</header>
  	<div id="cut"> </div>
  	<article>
   	<h3>"当前正在填写的问卷：<%=session.getAttribute("serveyName") %> " 共<%=session.getAttribute("questionCount") %>题.</h3>
   	
   	<p id="questionName1">No.<span id="questionNumber"></span>：<span id="questionName"></span> </p>
   	
   	<!-- 单选择题型 -->	
   	<section class="questionwrap" id="questionwrap1">
   		<%-- <p id="questionName1">No.<%=session.getAttribute("questionId") %>：<span id="qname1"></span> </p> --%>
   		<div id="answerSection1">
   			<label for="answer1" id="label1"></label><input type="radio" name="answer" id="answer1" value="answer1"/><br>
   			<label for="answer2" id="label2"></label><input type="radio" name="answer" id="answer2" value="answer2"/><br>
   			<label for="answer3" id="label3"></label><input type="radio" name="answer" id="answer3" value="answer3"/><br>
   			<label for="answer4" id="label4"></label><input type="radio" name="answer" id="answer4" value="answer4"/><br>
   			<label for="answer5" id="label5"></label><input type="radio" name="answer" id="answer5" value="answer5"/><br>
   			<label for="answer6" id="label6"></label><input type="radio" name="answer" id="answer6" value="answer6"/><br>
   			<label for="answer7" id="label7"></label><input type="radio" name="answer" id="answer7" value="answer7"/><br>
   		</div>
   		<!-- <p id="commit1" class="commit">提交</p> -->
   	</section>
   	
   	<!-- 多选择题型 -->	
   	<section class="questionwrap" id="questionwrap2">
   		<%-- <p id="questionName2">No.<%=session.getAttribute("questionId") %>：<span id="qname2"></span> </p> --%>
   		<div id="answerSection2">
   			<label for="answer1" id="label21"></label><input type="checkbox" name="answer1" id="answer21" value="answer1"/><br>
   			<label for="answer2" id="label22"></label><input type="checkbox" name="answer2" id="answer22" value="answer2"/><br>
   			<label for="answer3" id="label23"></label><input type="checkbox" name="answer3" id="answer23" value="answer3"/><br>
   			<label for="answer4" id="label24"></label><input type="checkbox" name="answer4" id="answer24" value="answer4"/><br>
   			<label for="answer5" id="label25"></label><input type="checkbox" name="answer5" id="answer25" value="answer5"/><br>
   			<label for="answer6" id="label26"></label><input type="checkbox" name="answer6" id="answer26" value="answer6"/><br>
   			<label for="answer7" id="label27"></label><input type="checkbox" name="answer7" id="answer27" value="answer7"/><br>
   		</div>
   		<!-- <p id="commit2" class="commit">提交</p> -->
   	</section>
   	
   	<!-- 问答题型 -->
   	<div class="questionwrap" id="questionwrap3">
   <%-- 		<p id="questionName3">No.<%=session.getAttribute("questionId") %>：<span id="qname3"></span> </p> --%>
   		<p id="answerSection3">
   			<label for="answer31" id="label31"></label><input type="text" name="answer31" id="answer31" value="answer1"/>
   		</p>
   		<p id="commit3" class="commit">提交</p>
   	</div>
   	</article>
   	<a href="#" onclick="commited();return false;" id="btn_page_next"><span>下一题</span></a>
   	<!-- 页脚信息 -->
  <div class="holder_content">
	   <div class="fourcol">
		   <section class="group2">
		   <h1>About me</h1>
		   <p>My name is Marija Zaric. I am a web designer from Belgrade, Serbia. I have finished Faculty of informatics and computing.</p>
		   <p><span style="color:#A93348;font-weight:bold">Biography</span></p>
		   <a href="http://www.marijazaric.com/biography"><img width="105" height="39" title="read more" alt="read more" src="img/read_more.png"></a>    
		   </section>
	   </div>
	   
	   <div class="fourcol">
		   <section class="group2">
		   <h1>Selected works</h1>
		   <p>Here you will see some of my projects from six years of experience in web design: 
		   Dreamweaver, Fireworks, CSS, HTML5, responsive.</p>
		   <p><span style="color:#A93348;font-weight:bold">Web design projects</span></p>
		   <a href="http://www.marijazaric.com/works"><img width="105" height="39" title="read more" alt="read more" src="img/read_more.png"></a>   
		   </section>
	   </div>
	   
	   <div class="fourcol last">
		   <section class="group2">
		   <h1>Web studio</h1>
			<p>Notification: I want to inform all the people who like my work that from the May 2nd you can contact me for the proffesional services
			</p><p><span style="color: #a93348; font-weight: bold;">Creative Web Studio</span></p>
		  	<a href="http://www.marijazaric.com/category/web-design-studio/"><img width="105" height="39" title="read more" alt="read more" src="img/read_more.png"></a>    
		   </section>
	   </div>
  </div>
   	
   	<footer>
   		<audio controls="controls" loop="loop">
 			<source src="music/marija zaric.wav" type="audio/wav"></source>
 		</audio>
   	</footer>
   	<!-- 进入该答题页后获得第一题 -->
   	<script type="text/javascript">
   		var questionId = 1;
   		var answerCount = 0;
   		var qType = 1;
   		var questionIddb = 0;
   		$(function(){
    		$.ajax({
    			type:"post",
    			url:"fillServeyAction",
    			data:{questionId:questionId},
    			success:function(data){
    				qType = data.questionType;
    				var questionName = data.question;
    				answerCount = data.answerCount;
    				questionIddb = data.questionIddb;
    				var answer1 = "";
    				var answer2 = "";
    				var answer3 = "";
    				var answer4 = "";
    				var answer5 = "";
    				var answer6 = "";
    				var answer7 = "";
    				
    				if(qType == 1)
    				{	
    					$("#questionwrap1").addClass("show");
    					$("#questionNumber").empty().append(questionId);
    					$("#questionName").empty().append(questionName);
    					switch(answerCount)
    					{
    						case 2:answer1=data.answer1;answer2=data.answer2;
    							$("#answerSection1 input:lt(2)").addClass("show");
    							/* $("#answer1").addClass("show");$("#answer2").addClass("show"); */
    							$("#label1").html(answer1);$("#label2").html(answer2);
    							break;
    						case 3:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;
    							$("#answerSection1 input:lt(3)").addClass("show");
    							$("#label1").html(answer1);$("#label2").html(answer2);$("#label3").html(answer3);
    							break;
    						case 4:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;
    							$("#answerSection1 input:lt(4)").addClass("show");
    							$("#label1").html(answer1);$("#label2").html(answer2);$("#label3").html(answer3);$("#label4").html(answer4);
    							break;
    						case 5:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;
    							$("#answerSection1 input:lt(5)").addClass("show");
    							$("#label1").html(answer1);$("#label2").html(answer2);$("#label3").html(answer3);$("#label4").html(answer4);$("#label5").html(answer5);
    							break;
    						case 6:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;
    							$("#answerSection1 input:lt(6)").addClass("show");
    							$("#label1").html(answer1);$("#label2").html(answer2);$("#label3").html(answer3);$("#label4").html(answer4);$("#label5").html(answer5);$("#label6").html(answer6);
    							break;
    						case 7:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;answer7=data.answer7;
    							$("#answerSection1 input").addClass("show");
    							$("#label1").html(answer1);$("#label2").html(answer2);$("#label3").html(answer3);$("#label4").html(answer4);$("#label5").html(answer5);$("#label6").html(answer6);$("#label7").html(answer7);
    							break;
    					}
    				}
    				else if(qType == 2)
    				{
    					$("#questionwrap2").addClass("show");
    					$("#questionNumber").empty().append(questionId);
    					$("#questionName").empty().append(questionName);
    					switch(answerCount)
    					{
    						case 2:answer1=data.answer1;answer2=data.answer2;
    							$("#answerSection2 input:lt(2)").addClass("show");
    							/* $("#answer1").addClass("show");$("#answer2").addClass("show"); */
    							$("#label21").html(answer1);$("#label22").html(answer2);
    							break;
    						case 3:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;
    							$("#answerSection2 input:lt(3)").addClass("show");
    							$("#label12").html(answer1);$("#label22").html(answer2);$("#label23").html(answer3);
    							break;
    						case 4:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;
    							$("#answerSection2 input:lt(4)").addClass("show");
    							$("#label21").html(answer1);$("#label22").html(answer2);$("#label23").html(answer3);$("#label24").html(answer4);
    							break;
    						case 5:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;
    							$("#answerSection2 input:lt(5)").addClass("show");
    							$("#label21").html(answer1);$("#label22").html(answer2);$("#label23").html(answer3);$("#label24").html(answer4);$("#label25").html(answer5);
    							break;
    						case 6:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;
    							$("#answerSection2 input:lt(6)").addClass("show");
    							$("#label21").html(answer1);$("#label22").html(answer2);$("#label23").html(answer3);$("#label24").html(answer4);$("#label25").html(answer5);$("#label26").html(answer6);
    							break;
    						case 7:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;answer7=data.answer7;
    							$("#answerSection2 input").addClass("show");
    							$("#label21").html(answer1);$("#label22").html(answer2);$("#label23").html(answer3);$("#label24").html(answer4);$("#label25").html(answer5);$("#label26").html(answer6);$("#label27").html(answer7);
    							break;
    					}
    				}
    				else if(qType == 3)
    				{
    					$("#questionwrap3").addClass("show");
    					$("#answerSection3 input").addClass("show");
    					$("#questionNumber").empty().append(questionId);
    					$("#questionName").empty().append(questionName);
    				}
    				
    			},
    			error:function(xhr, type, exception)
    			{
    				alert("该问卷暂时不可用 ");
    			}
    			});
    		});
   	</script>
  	
  	<!-- 提交答题项 -->
  	<script type="text/javascript">
  		$(function(){
  			$("#commit1").click(function(){
  				commited();
  			});
  			$("#commit2").click(function(){
  				commited();
  			});
  			$("#commit3").click(function(){
  				commited();
  			});
  		});
  		function commited(){
  				/* 后台接收到questionId后需要将其放入session替换原来值，后台需要处理questionIddb */
  				questionId = Number(questionId) + Number(1);
  				var answers = "";
  				/* 收集单选和多项题型答案信息 */
  				if(qType == 1)
  				{
  					if($("#answer1").attr("checked") == "checked"){answers = answers + "answer1";}
  					if($("#answer2").attr("checked") == "checked"){answers = answers + "answer2";}
  					if($("#answer3").attr("checked") == "checked"){answers = answers + "answer3";}
  					if($("#answer4").attr("checked") == "checked"){answers = answers + "answer4";}
  					if($("#answer5").attr("checked") == "checked"){answers = answers + "answer5";}
  					if($("#answer6").attr("checked") == "checked"){answers = answers + "answer6";}
  					if($("#answer7").attr("checked") == "checked"){answers = answers + "answer7";}
  					if(answers == "" || answers == null)
  					{
  						alert("请做出选择再提交！");
  						return;
  					}
  				}
  				else if(qType == 2)
  				{
  					if($("#answer21").attr("checked") == "checked"){answers = answers + "answer1,";}
  					if($("#answer22").attr("checked") == "checked"){answers = answers + "answer2,";}
  					if($("#answer23").attr("checked") == "checked"){answers = answers + "answer3,";}
  					if($("#answer24").attr("checked") == "checked"){answers = answers + "answer4,";}
  					if($("#answer25").attr("checked") == "checked"){answers = answers + "answer5,";}
  					if($("#answer26").attr("checked") == "checked"){answers = answers + "answer6,";}
  					if($("#answer27").attr("checked") == "checked"){answers = answers + "answer7,";}
  					if(answers == "" || answers == null)
  					{
  						alert("请至少选择一项！");
  						return;
  					}
  					answers = answers.substring(0,answers.length - 1);
  				}
  				/* 收集文字作答题型答案信息并判断答案是否合法 */
  				if(qType == 3)
  				{
  					answers = $("#answer31").val();
  					if(answers == "" || answers == null)
  					{
  						alert("答案内容不能为空");
  						return;
  					}
  					if(answers != "" || answers.match(/^[ ]+$/) != null)
					{
						var results = answers.match(/^[\u4e00-\u9fa5a-zA-Z0-9,.;，、~_=。；‘’“”""%￥！!#*@？?]+$/);
						if(results == null)
						{
							alert("答案选项内容不能包含特殊字符！");
							return;
						}
					}
  				}
  				/* 提交答题内容并获得下一题 */
  				$.ajax({
    			type:"post",
    			url:"fillServeyAction2",
    			data:{questionId:questionId,answers:answers,questionIddb:questionIddb,answerCount:answerCount,questionType:qType},
    			success:function(data){
    				var result = data.result;
    				if(result == "success")
    				{
    					$("#questionwrap1").removeClass("show");
    					$("#questionwrap2").removeClass("show");
    					$("#questionwrap3").removeClass("show");
    					$("#questionwrap1").addClass("hide");
    					$("#questionwrap2").addClass("hide");
    					$("#questionwrap3").addClass("hide");
    					qType = data.questionType;
	    				var questionName = data.question;
	    				answerCount = data.answerCount;
	    				questionIddb = data.questionIddb;
	    				var answer1 = "";
	    				var answer2 = "";
	    				var answer3 = "";
	    				var answer4 = "";
	    				var answer5 = "";
	    				var answer6 = "";
	    				var answer7 = "";
	    				if(qType == 1)
	    				{	
	    					$("#questionwrap1").addClass("show");
	    					$("#answerSection1 input").removeClass("show").addClass("hide");
	    					$("#answerSection1 label").empty();
	    					$("#answerSection1 input").attr("checked",null);
	    					$("#questionNumber").empty().append(questionId);
    						$("#questionName").empty().append(questionName);
	    					switch(answerCount)
	    					{
	    						case 2:answer1=data.answer1;answer2=data.answer2;
	    							$("#answerSection1 input:lt(2)").addClass("show");
	    							/* $("#answer1").addClass("show");$("#answer2").addClass("show"); */
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);
	    							break;
	    						case 3:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;
	    							$("#answerSection1 input:lt(3)").addClass("show");
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);$("#label3").empty().html(answer3);
	    							break;
	    						case 4:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;
	    							$("#answerSection1 input:lt(4)").addClass("show");
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);$("#label3").empty().html(answer3);$("#label4").empty().html(answer4);
	    							break;
	    						case 5:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;
	    							$("#answerSection1 input:lt(5)").addClass("show");
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);$("#label3").empty().html(answer3);$("#label4").empty().html(answer4);$("#label5").empty().html(answer5);
	    							break;
	    						case 6:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;
	    							$("#answerSection1 input:lt(6)").addClass("show");
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);$("#label3").empty().html(answer3);$("#label4").empty().html(answer4);$("#label5").empty().html(answer5);$("#label6").empty().html(answer6);
	    							break;
	    						case 7:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;answer7=data.answer7;
	    							$("#answerSection1 input").addClass("show");
	    							$("#label1").empty().html(answer1);$("#label2").empty().html(answer2);$("#label3").empty().html(answer3);$("#label4").empty().html(answer4);$("#label5").empty().html(answer5);$("#label6").empty().html(answer6);$("#label7").empty().html(answer7);
	    							break;
	    					}
	    				}
	    				else if(qType == 2)
	    				{
	    					$("#questionwrap2").addClass("show");
	    					$("#answerSection2 input").removeClass("show").addClass("hide");
	    					$("#answerSection2 label").empty();
	    					$("#answerSection2 input").attr("checked",null);
	    					$("#questionNumber").empty().append(questionId);
    						$("#questionName").empty().append(questionName);
	    					switch(answerCount)
	    					{
	    						case 2:answer1=data.answer1;answer2=data.answer2;
	    							$("#answerSection2 input:lt(2)").addClass("show");
	    							$("#label21").empty().html(answer1);$("#label22").empty().html(answer2);
	    							break;
	    						case 3:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;
	    							$("#answerSection2 input:lt(3)").addClass("show");
	    							$("#label12").empty().html(answer1);$("#label22").empty().html(answer2);$("#label23").empty().html(answer3);
	    							break;
	    						case 4:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;
	    							$("#answerSection2 input:lt(4)").addClass("show");
	    							$("#label21").empty().html(answer1);$("#label22").empty().html(answer2);$("#label23").empty().html(answer3);$("#label24").empty().html(answer4);
	    							break;
	    						case 5:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;
	    							$("#answerSection2 input:lt(5)").addClass("show");
	    							$("#label21").empty().html(answer1);$("#label22").empty().html(answer2);$("#label23").empty().html(answer3);$("#label24").empty().html(answer4);$("#label25").empty().html(answer5);
	    							break;
	    						case 6:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;
	    							$("#answerSection2 input:lt(6)").addClass("show");
	    							$("#label21").empty().html(answer1);$("#label22").empty().html(answer2);$("#label23").empty().html(answer3);$("#label24").empty().html(answer4);$("#label25").empty().html(answer5);$("#label26").empty().html(answer6);
	    							break;
	    						case 7:answer1=data.answer1;answer2=data.answer2;answer3=data.answer3;answer4=data.answer4;answer5=data.answer5;answer6=data.answer6;answer7=data.answer7;
	    							$("#answerSection2 input").addClass("show");
	    							$("#label21").empty().html(answer1);$("#label22").empty().html(answer2);$("#label23").empty().html(answer3);$("#label24").empty().html(answer4);$("#label25").empty().html(answer5);$("#label26").empty().html(answer6);$("#label27").empty().html(answer7);
	    							break;
	    					}
	    				}
	    				else if(qType == 3)
	    				{
	    					$("#questionwrap3").addClass("show");
	    					$("#answerSection3 input").addClass("show");
	    					$("#questionNumber").empty().append(questionId);
    						$("#questionName").empty().append(questionName);
	    				}
	    			}
	    			/* 问卷填写完毕 */
	    			else if(result == "noquestion")
	    			{
	    				 window.location.href="replyover.jsp";
	    			}
	    			/* 错误，答案保存错误等 */
	    			else{
	    				alert("对不起，服务器错误 :(");
	    			}
    			}
    			});
  		}
  		
  
	window.onload=function myfun()
	{
		$.ajax({
    			type:"post",
    			url:"isFilled",
    			data:{},
    			success:function(data){
    				if(data=="filled")
    				{
    					alert("该问卷您已经填写过或者需要登录，谢谢参与！");
    					window.location.href="index.jsp";
    				}
    			},
    			error:function(xhr,type,Exception)
    			{}
    		});
	};
  		
  	</script>
  </body>
</html>
