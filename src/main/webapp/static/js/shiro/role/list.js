var layerId;
$(function(){
	$("#add-button").click(function(){
		openEdit(0);
	});
})
function openEdit(roleId){
	layerId=layer.open({
		title: "信息编辑",
		area: ['90%','80%'],
		fix:true,
		type: 2,
		content: basePath+'/admin/role/edit.shtml?roleId='+roleId
	});
}
function rolePermissionEdit(roleId){
	layerId=layer.open({
		title: "角色权限编辑",
		area: ['90%','80%'],
		fix:true,
		maxmin:true,
		type: 2,
		content: basePath+'/admin/role/permissionList.shtml?roleId='+roleId
	});
}
function closeLayer(flag){
	layer.close(layerId);
	if(flag>0){
		self.location.reload(true); 
	}
}
function deleteRole(roleId){
	 $.ajax({
   		 url:basePath+"/admin/role/delete.shtml",  
   		 type:"post",
   		 data:{"roleId":roleId},
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
