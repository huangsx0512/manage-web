var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var roleId=$("input[name='roleId']").val();
		var name=$("input[name='name']").val();
		var status=$("input[name='status']:checked").val();
		var ments=$("input[name='ment']:checked");
		if(name==null){
			layer.msg("角色名称不能为空");
			return false;
		}
		if(ments==null||ments==""||ments.length==0){
			layer.msg("权限不能为空,至少选择一种权限");
			return false;
		}
		var mentIds="";
		for(i=0;i<ments.length;i++){
			mentIds+=$(ments[i]).val()+",";
		}
		mentIds = mentIds.substring(0, mentIds.length-1);
		var role={
				"roleId":roleId,
				"name":name,
				"mentIds":mentIds,
				"isEnabled":status
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/role/saveRoleInfo.shtml?",  
				type:"POST",
				data:role,
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