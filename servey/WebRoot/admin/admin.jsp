<%@ page language="java" import="java.util.*,javax.servlet.*"
	pageEncoding="UTF-8"%>
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

<title>问卷系统管理页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="../servey/css/admins.css">
<link rel="stylesheet" type="text/css"
	href="../servey/css/animated-menu.css">
<script type="text/javascript" src="../servey/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="../servey/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="../servey/js/animated-menu.js"></script>
<script type="text/javascript" src="../servey/tuftegraph/raphael.js"></script>
<script type="text/javascript" src="../servey/tuftegraph/jquery.enumerable.js"></script>
<script type="text/javascript" src="../servey/tuftegraph/jquery.tufte-graph.js"></script>
<link rel="stylesheet" href="../servey/tuftegraph/tufte-graph.css" type="text/css" />
<style type="text/css">
td {
	padding-left: 3px;
}
.graph .label { font-size: 0.8em;clear:both; }
#manageUserdiv,#manageServeysdiv,#manageResultdiv{
	display:none;
}
#btn,#btn2{
	width:93px;
	text-align:center;
}
</style>

<script type="text/javascript">
	function del(value) {
		var id = value;
		if (confirm("确定要删除该用户吗？")) {
			$.post("admin/deleteUser?id=" + id + "", "", function(returnData, status) {
				$("#i" + id + "").remove();
			});
			return true;
		}
		return false;
	}
	function delServey(value) {
		var id = value;
		if (confirm("确定要删除该问卷吗？")) {
			$.post("admin/deleteServey?id=" + id + "", "", function(returnData, status) {
				if (returnData == "success") {
					alert("删除成功!");
					$("#i" + id + "").remove();
				} 
				else {
					alert("对不起，服务器错误，删除失败!");
				}
			});
			return true;
		}
		return false;
	}

	
	$(function() {
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
	
		/* 用户查看，用户管理 */
		$("#manageUser").click(
				function() {
					$("#manageUserdiv").show();
					$("#manageServeysdiv").hide();
					$("#manageResultdiv").hide();
					$("#pre").removeAttr("disabled");
					$("#next").removeAttr("disabled");
					$("#page1").text(1);
					$("#page2").text(1);
					$.ajax({
						type : "post",
						url : "admin/getUser.action",
						data : {page:0},
						success : function(data) {
							var total = data.total;
							var datas = data.data;
							listUser(datas);
							$("#sum").text(total);
							
							$("#pre").attr({"disabled":"disabled"});
							if(total<2){
							$("#next").attr({"disabled":"disabled"});
							}
						},
						error : function(xhr, type, exception) {
						}
					});
				});

		/* 问卷管理-查看问卷 */
		$("#managequestionnaire").click(
				function() {
					$("#manageUserdiv").hide();
					$("#manageServeysdiv").show();
					$("#manageResultdiv").hide();

					$.post("admin/getQuestionnaire", null, function(returnDate, status) {
						var html = "";

						if (returnDate != null) {
							for ( var i = 0; i < returnDate.length; i++) {
								var id = returnDate[i].id;
								var serveyName = returnDate[i].name;
								var description = returnDate[i].description;
								var gender = returnDate[i].gender;
								if(gender == "F")gender="女";
								else if(gender == "M")gender="男";
								else if(gender == "A")gender="无";
								var beginAge = returnDate[i].beginAge;
								var endAge = returnDate[i].endAge;
								var beginDate = returnDate[i].beginDate;
								var endDate = returnDate[i].endDate;
								var checkLogin = returnDate[i].checkLogin;
								if(checkLogin == "yes")checkLogin = "是";
								else if(checkLogin == "no")checkLogin = "否";

								/* html = html + "<tr id=\"i" + id + "\">" + "<td>" + id + "</td>" + "<td>" + serveyName + "</td>" + "<td>"
										+ description + "</td>" + "<td>" + gender + "</td>" + "<td>" + beginAge + "</td>" + "<td>" + endAge
										+ "</td>" + "<td>" + beginDate + "</td>" + "<td>" + endDate + "</td>" + "<td>" + checkLogin
										+ "</td>" + "<td value=\"" + id + "\" onclick=\"return delServey(" + id + ");\">" + "delete"
										+ "</td>" + "</tr>"; */
										
								html += "<div id='i"+id+"'>" + "<p>"+(Number(i) + Number(1))+"." + serveyName +"</p>"
								+"<p>问卷描述：" + description +"</p>"
								+"<ul><li>" + "性别要求："+gender +"</li>"+"<li class='ages'>" +"最低年龄限制：" + beginAge+"</li>"
								+ "<li class='ages'>" + "最大年龄限制：" + endAge +"</li>" + "<li class='dates'>" + "开始日期：" + beginDate + "</li>"
								+"<li class='dates'>" +"结束日期"+ endDate + "</li>" + "<li>" + "是否要求登录：" + checkLogin + "</li>" +"</ul>"
								+"<div>"+ "<button value='修改' onclick='return alterServey(" + id + ");'>修改</button>"
								+"&nbsp;"+ "<button value='删除' onclick='return delServey(" + id + ");'>删除</button>"  +"</div>" + "</div>";
							}
							/* var tablehead = "<tr>" + "<th>" + "id" + "</th>" + "<th>" + "问卷名" + "</th>" + "<th>" + "描述"
									+ "</th>" + "<th>" + "性别限制" + "</th>" + "<th>" + "最小年龄" + "</th>" + "<th>" + "最大年龄" + "</th>"
									+ "<th>" + "开始日期" + "</th>" + "<th>" + "结束日期" + "</th>" + "<th>" + "要求登录" + "</th>"
									+ "<th>" + "删除" + "</th>" + "</tr>"; */
							
							/* $("#serveysTable").empty();
							$("#serveysTable").append(tablehead).append(html); */
							$("#showServeysdiv").empty();
							$("#showServeysdiv").append(html);
						} else {
							var noServeyText = "<p class=\"noServey\">还没有问卷</p>";
							/* $("#serveysTable").empty();
							$("#serveysTable").append(noServeyText); */
							$("#showServeysdiv").empty();
							$("#showServeysdiv").append(noServeyText);
						}

					});

				});
	});
	function alterServey(id)
	{
		/* window.open("testAction?serveyid="+id); */
		window.open('admin/alertservey.jsp?id=' + id);
	}
</script>

</head>

<body>

	<div id="wrap">
		<header class="menu">
			<ul>
				<li class="green">
					<p>
						<a href="javascript:void(0);">用户管理</a>
					</p>
					<p class="subtext" id="manageUser">查看用户</p></li>
				<li class="yellow">
					<p>
						<a href="javascript:void(0);">问卷管理</a>
					</p>
					<p class="subtext" id="managequestionnaire">查看问卷</p>
					<p class="subtext" id="createquestionnaire"><a target="_blank" href="admin/createServey.jsp">创建问卷</a></p>
					</li>
				<li class="red">
					<p>
						<a href="javascript:void(0);">结果统计</a>
					</p>
					<p class="subtext" id="showResult">结果统计</p></li>
				<li class="blue">
					<p>
						<a href="javascript:void(0);">about me</a>
					</p>
					<p class="subtext">about me</p></li>
			</ul>
		</header>

		<article id="manageUserdiv">
			<h1>
				<font color="red">用户列表管理</font>
			</h1>

			<table  border="1" width="900px" align="center" id="userTable">
				<tr>
					<td>id</td>
					<td>username</td>
					<td>password</td>
					<td>age</td>
					<td>gender</td>
					<td>registerDate</td>
					<td>delete</td>
				</tr>

			</table>
			<div>
				<span>第<span id="page1">1</span>页，共<span id="sum">1</span>页 </span>
				&nbsp;&nbsp;<button id="pre">上一页</button><span id="page2">1</span> <button id="next">下一页</button>
			</div>
		</article>

		<article id="manageServeysdiv">
			<h1>
				<font color="red">问卷列表管理</font>
			</h1>
			<div id="showServeysdiv">
				<table id="serveysTable" border="1" width="900px" align="center">
				</table>
				<br> 
			</div>
		</article>

		<article id="manageResultdiv">
			<section id="countinfoleft">
				<span>当前共有问卷 <span id="serveyNumber"></span> 份</span> <br>
				<span>请选择通过何种方式查看问卷结果</span><br>
				文字<input type="radio" name="radio" checked="checked" value="character"> 图表<input type="radio" name="radio" value="graphics"> 
				<select id="serveylist" style="width:140px;"></select> 
				<select id="questionlist" style="width:140px;">
					<option id="allquestionresult" value="allquestionresult">--查看所有问题结果--</option>
				</select> 
				<input type="button" id="btn" value="查看统计结果"><br>
				<input type="button" id="btn2" value="导出到Excel">
			</section>
			<section id="countinforight">
				<div id="serveyInfo"></div>
				<div id="countbycharacter">
				</div>
				<div id="countbygraphics">
				</div>
			</section>
		</article>
	
	</div>
	<footer>Info Create by linsser 2013-05-27</footer>
</body>
</html>
<script type="text/javascript">

$(function(){
	$("#btn2").click(function(){
		var serveyId = $("#serveylist option:selected").val();
		$.ajax({
			type : "post",
			url : "admin/toExcel.action",
			data : {serveyId:serveyId},
			success : function(data) {
				if(data == true || data =="true")
				{alert("导出成功");}
			},
			error : function(xhr, type, exception) {
			}
		});
	});
	$("#pre").click(function(){
		var page = $("#page1").text();
		page = Number(page)-Number(2);
		
		$.ajax({
			type : "post",
			url : "admin/getUser.action",
			data : {page:page},
			success : function(data) {
				var total = data.total;
				var datas = data.data;
				listUser(datas);
				$("#sum").text(total);
				
				var page1 = $("#page1").text();
				var page2 = $("#page2").text();
				$("#page1").text(Number(page1) - Number(1));
				$("#page2").text(Number(page1) - Number(1));
				
				page1 = $("#page1").text();
				if(page1 == 1){
				$("#pre").attr({"disabled":"disabled"});
				}
				$("#next").removeAttr("disabled");
			},
			error : function(xhr, type, exception) {
			}
		});
	});
	$("#next").click(function(){
		var page = $("#page1").text();
		page = Number(page);
		$.ajax({
			type : "post",
			url : "admin/getUser.action",
			data : {page:page},
			success : function(data) {
				var total = data.total;
				var datas = data.data;
				listUser(datas);
				$("#sum").text(total);
				
				var page1 = $("#page1").text();
				$("#page1").text(Number(page1) + Number(1));
				$("#page2").text(Number(page1) + Number(1));
				
				page1 = $("#page1").text();
				if(page1 == total){
				$("#next").attr({"disabled":"disabled"});
				}
				$("#pre").removeAttr("disabled");
			},
			error : function(xhr, type, exception) {
			}
		});
	});
});

function listUser(data)
{
	var json = data;
	var html = "";
	for ( var i = 0; i < json.length; i++) {
		var id = json[i].id;
		var username = json[i].username;
		var password = json[i].password;
		var age = json[i].age;
		var gender = json[i].gender;
		var registerDate = json[i].registerDate;
		html = html + "<tr id=\"i" + id + "\">" + "<td>" + id + "</td>" + "<td>" + username + "</td>" + "<td>"
				+ password + "</td>" + "<td>" + age + "</td>" + "<td>" + gender + "</td>" + "<td>" + registerDate
				+ "</td>" + "<td value=\"" + id + "\" onclick=\"return del(" + id + ");\">" + "删除" + "</td>"
				+ "</tr>";
	}
	$("#userTable").empty();
	var tablehead = "<tr>" + "<th>" + "id" + "</th>" + "<th>" + "用户名" + "</th>" + "<th>" + "密码" + "</th>"
			+ "<th>" + "年龄" + "</th>" + "<th>" + "性别" + "</th>" + "<th>" + "注册日期" + "</th>" + "<th>"
			+ "删除" + "</th>" + "</tr>";
	$("#userTable").append(tablehead).append(html);

}

function creategraph(arg,data,args2)
{
	 $("#" + arg + "").tufteBar({
          data: data ,
          barWidth:  function(i) { return 0.9; },
          barLabel:  function(i) { return this[0]; },
          axisLabel: function(i) { return this[1].label; }, 
          color:     function(i) { 
            return this[1].color || ['#E57536', '#82293B', '#8EEA3B', '#22293B', '#32293B', '#42293B', '#52293B'][i % 6];
          },
          legend: {
		      data: args2,
		      color: function(index) { 
		        return ['#E57536', '#82293B', '#8EEA3B', '#22293B', '#32293B', '#42293B', '#52293B'][index % 6]; 
		      },
		      label: function(index) { return this; }
		    }
          
        });
}

$(function() {
	$("#btn").click(function(){
		var serveyId = $("#serveylist option:selected").val();
		var questionId = $("#questionlist option:selected").val();
		var radio = $("input[name='radio']:checked").val();
		$.ajax({
			type : "post",
			url : "admin/getAnswerResult.action",
			data : {serveyId:serveyId,questionId:questionId},
			success : function(data) {
				if(radio == "character"){
					//文字方式
					if(data.length == undefined){
						$("#countbycharacter").empty();
						$("#countbygraphics").empty();
						createans1(data,0);
					}
					else{
						$("#countbycharacter").empty();
						$("#countbygraphics").empty();
						for(var i = 0; i<data.length; i++)
						{
							createans1(data[i],i);
						}
					}
				}
				//图表方式
				else
				{
					
					if(data.length == undefined){
						$("#countbygraphics").empty();
						$("#countbycharacter").empty();
						var div = $("<div id='default' class='graph' style='width:300px; height: 220px;backgroun-color:red;'></div>");
						var h = $("<h3>1." + data.questionname + "</h3>");
						$("#countbygraphics").append(h);
						$("#countbygraphics").append(div);
						var args = datatransform(data);
						var args2 = questionnameargs(data);
						creategraph("default",args,args2);
					}
					else{
						$("#countbygraphics").empty();
						$("#countbycharacter").empty();
						for(var i = 0; i<data.length; i++)
						{
							var div = $("<div id=" + "ans" + i +" class='graph' style='width:300px; height: 220px'></div>");
							var h = $("<h3>"+ (Number(i) + Number(1)) + "." + data[i].questionname + "</h3>");
							$("#countbygraphics").append(h);
							$("#countbygraphics").append(div);
							var args = datatransform(data[i]);
							var args2 = questionnameargs(data[i]);
							creategraph("ans" + i,args,args2);
						}
					}
				}
				
			},
			error : function(xhr, type, exception) {
				alert("获取统计结果错误：" + exception);
			}
		});
		
	});
	

	//点击查看结果操作
	$("#showResult").click(function() {
		$("#manageUserdiv").hide();
		$("#manageServeysdiv").hide();
		$("#manageResultdiv").show();
		$.ajax({
			type : "post",
			url : "admin/getAllservey.action",
			data : "",
			success : function(data) {
				$("#serveylist").empty();
				var json = null;
				var serveyNumber = data.length;
				$("#serveyNumber").html(serveyNumber);
				for(var i = 0; i < data.length; i++){
					var $option = $("<option></option>");
					json = data[i];
					name = (json.name).substring(0,9);
					if((json.name).length > 9)name += "...";
					$option.html(name);
					$option.val(json.id);
					$("#serveylist").append($option);
				}
				var ids = data[0].id;
				loadQuestion(ids);
			},
			error : function(xhr, type, exception) {
			}
		});

	});
	
	$("#serveylist").change(function(){
		var id = $("#serveylist option:selected").val();
		$("#questionlist option:gt(0)").remove();
		loadQuestion(id);
	});
	$("#createquestionnaire").click(function(){
		
	});
});

function datatransform(data)
{
	var acount = data.answercount;
	var args = null;
	switch(7)
	{
		case 2:args = [
      		[data.acount[0], {label: '答案1'}],
       	[data.acount[1], {label: '答案2'}]];break;
         	case 3:args = [
         [data.acount[0], {label: '答案1'}],
         [data.acount[1], {label: '答案2'}],
         [data.acount[2], {label: '答案3'}]];break;
         	case 4:args = [
         [data.acount[0], {label: '答案1'}],
         [data.acount[1], {label: '答案2'}],
         [data.acount[2], {label: '答案3'}],
         [data.acount[3], {label: '答案4'}]];break;
         	case 5:args = [
         [data.acount[0], {label: '答案1'}],
         [data.acount[1], {label: '答案2'}],
         [data.acount[2], {label: '答案3'}],
         [data.acount[3], {label: '答案4'}],
         [data.acount[4], {label: '答案5'}]];break;
         	case 6:args = [
         [data.acount[0], {label: '答案1'}],
         [data.acount[1], {label: '答案2'}],
         [data.acount[2], {label: '答案3'}],
         [data.acount[3], {label: '答案4'}],
         [data.acount[4], {label: '答案5'}],
         [data.acount[5], {label: '答案6'}]];break;
         	case 7:args = [
         [data.acount[0], {label: '答案1'}],
         [data.acount[1], {label: '答案2'}],
         [data.acount[2], {label: '答案3'}],
         [data.acount[3], {label: '答案4'}],
         [data.acount[4], {label: '答案5'}],
         [data.acount[5], {label: '答案6'}],
         [data.acount[6], {label: '答案7'}]];
	}
	return args;
}

function questionnameargs(data)
{
	var acount = data.answercount;
	var args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3, "4."+data.answer4, "5."+data.answer5, "6."+data.answer6, "7."+data.answer7 ];
	switch(acount)
	{
			case 2:args = ["1."+data.answer1,"2."+data.answer2 ];break;
         	case 3:args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3 ];break;
         	case 4:args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3, "4."+data.answer4 ];break;
         	case 5:args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3, "4."+data.answer4, "5."+data.answer5 ];break;
         	case 6:args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3, "4."+data.answer4, "5."+data.answer5, "6."+data.answer6 ];break;
         	case 7:args = ["1."+data.answer1,"2."+data.answer2, "3."+data.answer3, "4."+data.answer4, "5."+data.answer5, "6."+data.answer6, "7."+data.answer7 ];
	}
	return args;
}

//文字方式显示统计结果
function createans1(data,i){
	var div1 = $("<div></div>");
	var p1 = $("<p></p>");
	var span1 = $("<span></span>");
	var span11 = $("<span></span>");
	var span12 = $("<span></span>");
	var span21 = $("<span></span>");
	var span22 = $("<span></span>");
	var span31 = $("<span></span>");
	var span32 = $("<span></span>");
	var span41 = $("<span></span>");
	var span42 = $("<span></span>");
	var span51 = $("<span></span>");
	var span52 = $("<span></span>");
	var span61 = $("<span></span>");
	var span62 = $("<span></span>");
	var span71 = $("<span></span>");
	var span72 = $("<span></span>");
	
	p1.html("题目" + (Number(i) + Number(1)) +"：" + data.questionname);
	span1.html("题目描述：" + ((data.questiondescription=="")?"无":data.questiondescription) + "<br>");
	div1.append(p1).append(span1);
	span11.html("答案1："+data.answer1 + "<br>");
	span12.html("投票总数：" + data.acount[0] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer0b + "<br>");
	div1.append(span11).append(span12);
	span21.html("答案2："+data.answer2 + "<br>");
	span22.html("投票总数：" + data.acount[1] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer1b + "<br>");
	div1.append(span21).append(span22);
	
	if(data.answercount > 2){
	span31.html("答案3："+data.answer3 + "<br>");
	span32.html("投票总数：" + data.acount[2] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer2b + "<br>");
	div1.append(span31).append(span32);
	}
	if(data.answercount > 3){
	span41.html("答案4："+data.answer4 + "<br>");
	span42.html("投票总数：" + data.acount[3] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer3b + "<br>");
	div1.append(span41).append(span42);
	}
	if(data.answercount > 4){
	span51.html("答案5："+data.answer5 + "<br>");
	span52.html("投票总数：" + data.acount[4] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer4b + "<br>");
	div1.append(span51).append(span52);
	}
	if(data.answercount > 5){
	span61.html("答案6："+data.answer6 + "<br>");
	span62.html("投票总数：" + data.acount[5] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer5b + "<br>");
	div1.append(span61).append(span62);
	}
	if(data.answercount > 6){
	span71.html("答案7："+data.answer7 + "<br>");
	span72.html("投票总数：" + data.acount[6] + "  "  + "&nbsp;&nbsp;&nbsp;投票比率：" + data.answer6b + "<br>");
	div1.append(span71).append(span72);
	}
	$("#countbycharacter").append(div1);
}


/* 通过问卷id获得问题 */
function loadQuestion(id){
	$.ajax({
			type : "post",
			url : "admin/getAllQuestionByServeyId.action",
			data : {serveyId:id},
			success : function(data) {
				var json = null;
				for(var i = 0; i < data.length; i++){
					var $option = $("<option></option>");
					json = data[i];
					question = (json.question).substring(0,12);
					if((json.question).length > 12)question += "...";
					$option.html(question);
					$option.val(json.id);
					$("#questionlist").append($option);
				}
				
			},
			error : function(xhr, type, exception) {
				alert("error");
			}
		});
}
</script>
