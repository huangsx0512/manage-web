$(function(){
	$("#login_button").click(function(){
		var accountName=$("#accountName").val();
		var accountPwd=$("#accountPwd").val();
		var rememberMe=$("#rememberMe").val();
		if(accountName.length==0){
			layer.msg("请输入账号");
			return;
		}
		if(accountPwd.length==0){
			layer.msg("请输入密码");
			return;
		}
		accountPwd =md5(accountPwd).toUpperCase();
		console.log(accountPwd);
		account={
				"loginName":accountName,
				"password":accountPwd,
				"rememberMe":rememberMe
		}
		$.ajax({
			url:basePath+"/open/submitLogin.shtml",  
			type:"post",
			data:account,
			dataType:"JSON",
			success:function(data){
				layer.msg(data.msg);
				if(data.code=="000000"){
					setTimeout(function(){
						window.top.location=basePath+"/home/index.shtml";
					}, 1000);
				}else{
					clickState=true;
				}
			},error:function(data){
			}
		});
	})
})