var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var appId=$("input[name='appId']").val();
		var name=$("input[name='name']").val();
		var code=$("input[name='code']").val();
		var roles=$("input[name='role']:checked");
		var status = $("input[name='status']").val();
		var appKey = $("input[name='appKey']").val();
		var ips=$("input[name='ips']").val();
		if(name==null){
			layer.msg("应用名称不能为空");
			return false;
		}
		if(code==null){
			layer.msg("应用码不能为空");
			return false;
		}
		if(roles==null||roles==""||roles.length==0){
			layer.msg("角色不能为空,至少选一种角色");
			return false;
		}
		var roleIds="";
		for(i=0;i<roles.length;i++){
			roleIds+=$(roles[i]).val()+",";
		}
		roleIds = roleIds.substring(0, roleIds.length-1);
		var app={
				"appId":appId,
				"name":name,
				"code":code,
				"appKey":appKey,
				"roleIds":roleIds,
				"isEnabled":status,
				"ips":ips
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/app/saveAppInfo.shtml?",  
				type:"POST",
				data:app,
				dataType:"json",
				success:function(result){
					layer.msg(result.msg);
					if(result.code=="000000"){
						setTimeout(function(){
							parent.closeLayer(1);
						}, 1000);
					}else{
						clickState=true;
					}
				},error:function(result){
				}
			});
		}
	})
	$("#close-button").click(function(){
		parent.closeLayer(0);
	})
})