var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var roleId=init.roleId;
		var permissionArray=$(".data-checkbox:checked");
		var rolePermissionArray= new Array();
		var length=permissionArray.length;
		if(length==0){
			layer.msg("请选择所需权限！");
			return false;
		}
		for(i=0;i<length;i++){
			authorId=$(permissionArray[i]).attr("data-id");
			rolePermission={
					"roleId":roleId,
					"authorId":authorId
			}
			rolePermissionArray.push(rolePermission);
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/admin/role/permissionAdd.shtml",  
				type:"post",
				data:{"rolePermissionList":JSON.stringify(rolePermissionArray)},
				dataType:"JSON",
				success:function(data){
					layer.msg(data.msg);
					if(data.code=="000000"){
						setTimeout(function(){
							self.location.reload(true);
						}, 1000);
					}else{
						clickState=true;
					}
				},error:function(data){
				}
			});
		}
	});
})

function deleteRolePermission(recId){
	if(confirm("确认删除?")){
		$.ajax({
			url:basePath+"/admin/role/permissionDelete.shtml",  
			type:"post",
			data:{"recId":recId},
			dataType:"JSON",
			success:function(data){
				layer.msg(data.msg);
				if(data.code=="000000"){
					setTimeout(function(){
						self.location.reload(true);
					}, 1000);
				}
			},error:function(data){
			}
		});
	}
}
