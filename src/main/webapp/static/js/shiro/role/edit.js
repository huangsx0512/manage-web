var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var roleId=init.roleId;
		var roleName=$("input[name='roleName']").val();
		var roleType=$("input[name='roleType']:checked").val();
		var childMenuArray=$(".childMenu:checked");
		var menuIdArray=new Array();
		for(i=0;i<childMenuArray.length;i++){
			menuId=$(childMenuArray[i]).val();
			menuIdArray.push(menuId);
		}
		if(roleName.length==0){
			layer.msg("名称不能为空");
			return false;
		}
		var role={
				"roleId":roleId,
				"roleName":roleName,
				"roleType":roleType,
				"menuIds":JSON.stringify(menuIdArray)
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/admin/role/saveInfo.shtml",  
				type:"post",
				data:role,
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