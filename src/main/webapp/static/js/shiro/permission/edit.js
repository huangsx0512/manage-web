var clickState=true;
$(function(){
	$("#save-button").click(function(){
		var authorId=init.authorId;
		var authorName=$("input[name='authorName']").val();
		var authorUrl=$("input[name='authorUrl']").val();
		if(authorName.length==0){
			layer.msg("名称不能为空");
			return false;
		}
		if(authorUrl.length==0){
			layer.msg("链接不能为空");
			return false;
		}
		var permission={
				"authorId":authorId,
				"authorName":authorName,
				"authorUrl":authorUrl
		}
		if(clickState){
			clickState=false;
			$.ajax({
				url:basePath+"/admin/permission/saveInfo.shtml",  
				type:"post",
				data:permission,
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