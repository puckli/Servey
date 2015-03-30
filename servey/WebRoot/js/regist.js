

$(function(){
	$("#sub").click(function(){
		var name = $("#name").val();
		/*if(name=="")
		{
			alert("账号不能为空");
			return;
		}*/
		if(name != "")
		{
			var str = name.substring(0,1);
			if(str.match(/^[0-9]+$/) != null)
			{
				alert("账号不能以数字开头");
				return;
			}
			var result = name.match(/^[a-zA-Z0-9_@]+$/);
			if(result == null)
			{
				alert("账号不能包含特殊字符");
				return;
			}
		}
		
		var pwd = $("#pwd").val();
		/*if(pwd=="")
		{
			alert("密码不能为空");
			return;
		}*/
		if(pwd != "")
		{
			var str = pwd.substring(0,1);
			if(str.match(/^[0-9]+$/) != null)
			{
				alert("密码不能以数字开头");
				return;
			}
			var result = pwd.match(/^[a-zA-Z0-9_@]+$/);
			if(result == null)
			{
				alert("密码不能包含特殊字符");
				return;
			}
		}
		
		var repwd = $("#repwd").val();
		/*if(repwd=="")
		{
			alert("重复密码不能为空");
			return;
		}*/
		if(repwd != "")
		{
			var str = repwd.substring(0,1);
			if(str.match(/^[0-9]+$/) != null)
			{
				alert("确认密码不能以数字开头");
				return;
			}
			var result = repwd.match(/^[a-zA-Z0-9_@]+$/);
			if(result == null)
			{
				alert("确认密码不能包含特殊字符");
				return;
			}
		}
		
		
		var age = $("#age").val();
		/*if(age=="")
		{
			alert("年龄不能为空");
			return;
		}*/
		if(age.match(/^[0-9]+$/) == null || age>100 || age < 12)
		{
				alert("年龄必须是12~100间的数字");
				return;
		}
	});
	
});



