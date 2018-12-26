var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var mentId=$("input[name='mentId']").val();
		var name=$("input[name='name']").val();
		var apiServer=$("input[name='apiServer']").val();
		var url=$("input[name='url']").val();
		var method=$("input[name='method']").val();
		var status=$("input[name='status']:checked").val();
		if(name==null){
			layer.msg("角色名称不能为空");
			return false;
		}
		if(apiServer==null){
			layer.msg("API服务不能为空");
			return false;
		}
		if(url==null){
			layer.msg("url不能为空");
			return false;
		}
		if(method==null){
			layer.msg("method不能为空");
			return false;
		}
		var ment={
				"mentId":mentId,
				"apiServer":apiServer,
				"url":url,
				"method":method,
				"name":name,
				"status":status
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/ment/saveMentInfo.shtml?",  
				type:"POST",
				data:ment,
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