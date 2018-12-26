var layerId;
$(function(){
	$("#search-button").click(function(){
		var keyword=$("input[name='keyword']").val();
		var url=basePath+"/admin/subject/list.shtml?pageNo=1&keyword="+keyword;
		window.location.href=url;
	});
	$("#add-button").click(function(){
		openEdit(0);
	});
	$("#reset-button").click(function(){
		init.keyword="";
		$("input[name='keyword']").val("");
	});
})
function openEdit(accountId){
	layerId=layer.open({
		title: "信息编辑",
		area: ['1200px','700px'],
		fix:true,
		type: 2,
		content: basePath+'/admin/subject/edit.shtml?accountId='+accountId
	});
}
function resetPassword(accountId){
	$.ajax({
  		 url:basePath+"/admin/subject/resetPassword.shtml?accountId="+accountId,  
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
function updateStatus(accountId,status){
	 $.ajax({
   		 url:basePath+"/admin/subject/updateStatus.shtml",  
   		 type:"post",
   		 data:{"accountId":accountId,"status":status},
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
	var url=basePath+"/admin/subject/list.shtml?pageNo="+pageNo+"&keyword="+init.keyword;
	window.location.href=url;
}



