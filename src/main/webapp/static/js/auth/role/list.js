var layerId;
var initKeyword="";
/**
 * 页面初始化绑定事件
 */
$(function(){
	$("#search-button").click(function(){
		var keyword=$("input[name='keyword']").val();
		var url=basePath+"/role/roleList.shtml?pageNo=1&pageSize=20&keyword="+encodeURI(encodeURI(keyword));
		window.location.href=url;
	});
	$("#add-button").click(function(){
		openEdit(0);
	});
	$("#reset-button").click(function(){
		$("input[name='keyword']").val("");
	});
})
/**
 * 修改角色信息
 */
function openEdit(roleId){
	layerId=layer.open({
		title: "信息编辑",
		area: ['1200px','700px'],
		fix:true,
		type: 2,
		content: basePath+'/role/initEditInfo.shtml?roleId='+roleId
	});
}
function closeLayer(flag){
	layer.close(layerId);
	if(flag>0){
		self.location.reload(true); 
	}
}
/**
 * 变更角色的状态
 */
function updateStatus(roleId,status){
	 $.ajax({
   		 url:basePath+"/role/updateStatus.shtml",  
   		 type:"post",
   		 data:{"roleId":roleId,"status":status},
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
	 //刷新查询页面
	 alert("角色状态修改成功！");
	 location.href=basePath+"/role/roleList.shtml?pageNo=1&pageSize=20&keyword="+"";
}
/**
 * 删除角色
 */
function deleteRole(roleId){
	$.ajax({
		url:basePath+"/role/deleteRole.shtml",  
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
	//刷新查询页面
	alert("删除成功！");
	location.href=basePath+"/role/roleList.shtml?pageNo=1&pageSize=20&keyword="+"";
}
/**
 * 翻页查询
 */
function fanye(pageNo){
	var url=basePath+"/role/roleList.shtml?pageNo="+pageNo+"&pageSize=20&keyword="+initKeyword;
	window.location.href=url;
}