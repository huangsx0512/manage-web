var layerId;
$(function(){
	$("#add-button").click(function(){
		openEdit(0);
	});
})
function openEdit(authorId){
	layerId=layer.open({
		title: "信息编辑",
		area: ['600px','400px'],
		fix:true,
		type: 2,
		content: basePath+'/admin/permission/edit.shtml?authorId='+authorId
	});
}
function closeLayer(flag){
	layer.close(layerId);
	if(flag>0){
		self.location.reload(true); 
	}
}
function deletePermission(authorId){
	 $.ajax({
   		 url:basePath+"/admin/permission/delete.shtml",  
   		 type:"post",
   		 data:{"authorId":authorId},
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
function fanye(pageNo){
	var url=basePath+"/admin/permission/list.shtml?pageNo="+pageNo;
	window.location.href=url;
}



