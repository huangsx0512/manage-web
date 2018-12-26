var layerId;
var initKeyword="";
/**
 * 页面初始化绑定事件
 */
$(function(){
	$("#search-button").click(function(){
		var keyword=$("input[name='keyword']").val();
		var url=basePath+"/app/appList.shtml?pageNo=1&pageSize=20&keyword="+encodeURI(encodeURI(keyword));
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
 * 修改应用信息
 */
function openEdit(appId){
	layerId=layer.open({
		title: "信息编辑",
		area: ['1200px','700px'],
		fix:true,
		type: 2,
		content: basePath+'/app/initEditInfo.shtml?appId='+appId
	});
}
function resetPassword(appId){
	$.ajax({
  		 url:basePath+"/admin/subject/resetPassword.shtml?appId="+appId,  
  		 type:"get",
  		 dataType:"JSON",
  		 success:function(data){
  			 layer.msg(data.msg);
  		 },error:function(data){
  		 }
  	 });
}
function closeLayer(flag){
	layer.close(layerId);
	if(flag>0){
		self.location.reload(true); 
	}
}
/**
 * 变更应用的状态
 */
function updateStatus(appId,status){
	 $.ajax({
   		 url:basePath+"/app/updateStatus.shtml",  
   		 type:"post",
   		 data:{"appId":appId,"status":status},
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
	 alert("状态修改成功！");
	 location.href=basePath+"/app/appList.shtml?pageNo=1&pageSize=20&keyword="+"";
}
/**
 * 删除应用
 */
function deleteApp(appId){
	$.ajax({
		url:basePath+"/app/deleteApp.shtml",  
		type:"post",
		data:{"appId":appId},
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
	location.href=basePath+"/app/appList.shtml?pageNo=1&pageSize=20&keyword="+"";
}
/**
 * 翻页查询
 */
function fanye(pageNo){
	var url=basePath+"/app/appList.shtml?pageNo="+pageNo+"&pageSize=20&keyword="+initKeyword;
	window.location.href=url;
}