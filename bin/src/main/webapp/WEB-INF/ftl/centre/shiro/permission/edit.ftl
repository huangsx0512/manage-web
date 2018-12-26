
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/bootstrap/js/shiro/permission/edit.js?time=201706051007"></script>
<head>
<meta charset="UTF-8">
<title>信息编辑</title>
<style>
	.foot{
		position: absolute;
		left: 10%;
		top: 90%;
	}
	.lable_text{
		padding:0px 8px;
	}
	.hide{
		display:none;
	}
	.readonly-input{
		background-color: #e2e2e2;
	}
	.left{
		float:left;
	    width: 45%;
	}
</style>
</head>
<body>
<div style="min-height: 80%; min-width: 90%; margin: 20px 20px;">
		<div style="width:80%;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">权限名称*</label>
					<div class="layui-input-block">
							<input type="text"
							name="authorName" required="" lay-verify="required"
							placeholder="请输入名称"
							value="${authorName!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">链接*</label>
					<div class="layui-input-block">
							<input type="text"
							name="authorUrl" required="" lay-verify="required"
							placeholder="请输入链接"
							value="${authorUrl!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</form>
			</div>
	<div class="foot">
		<button type="button" class="layui-btn layui-btn-normal"
			id="save-button">
			<i class="layui-icon">&#xe605;</i>保存
		</button>
		<button type="button" class="layui-btn layui-btn-normal"
			id="close-button">
			<i class="layui-icon">&#x1006;</i>取消
		</button>
	</div>
</body>
<script type="text/javascript">
	var basePath = "${basePath}";
	var init={
	 	authorId:"${authorId}"
	 	}
	layui.use(['form','layer'], function() {
		var form = layui.form();
		var layer = layui.layer;
	});
</script>
</html>
