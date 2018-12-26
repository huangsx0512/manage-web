var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var accountId=init.accountId;
		var nickname=$("input[name='nickname']").val();
		var accountName=$("input[name='accountName']").val();
		var roles=$("input[name='role']:checked");
		var password = $("input[name='password']").val();
		var status=$("input[name='status']:checked").val();
		console.log(password);
		if(!isNickName(nickname)){
			layer.msg("昵称规则：2-16位的字母，数字，汉字");
			return false;
		}
		if(!isRegisterUserName(accountName)){
			layer.msg("登录账号规则：4-20位以字母开头、可带数字、“_”、“@”");
			return false;
		}
		if(roles==null){
			layer.msg("角色不能为空");
			return false;
		}
		if(accountId==0 && !isPassword(password)){
			layer.msg("请核对密码（由6-20个字母、数字组成）");
			return false;
		}
		if(accountId>0 && password.length>0 && !isPassword(password)){
			layer.msg("请校对密码（由6-20个字母、数字组成）");
			return false;
		}
		var roleIds="";
		for(i=0;i<roles.length;i++){
			roleIds+=$(roles[i]).val()+",";
		}
		roleIds=roleIds.substring(0, roleIds.length-1);
		var account={
				"accountId":accountId,
				"nickname":nickname,
				"accountName":accountName,
				"password":password,
				"roleIds":roleIds,
				"status":status
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/admin/subject/saveInfo.shtml?",  
				type:"post",
				data:account,
				dataType:"JSON",
				success:function(data){
					layer.msg(data.msg);
					if(data.code=="000000"){
						setTimeout(function(){
							parent.closeLayer(1);
						}, 1000);
					}else{
						clickState=true;
					}
				},error:function(data){
				}
			});
		}
	})
	$("#close-button").click(function(){
		parent.closeLayer(0);
	})
})