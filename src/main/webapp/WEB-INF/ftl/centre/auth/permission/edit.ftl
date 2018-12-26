
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/js/auth/permission/permissionEdit.js"></script>
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
							<input type="hidden" name="mentId" value="${ment.id!}">
							<input type="text"
							name="name" required="" lay-verify="required"
							placeholder="请输入名称"
							value="${ment.name!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">API服务*</label>
					<div class="layui-input-block">
							<input type="text"
							name="apiServer" required="" lay-verify="required"
							placeholder="请输入API服务"
							value="${ment.apiServer!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">URL路径*</label>
					<div class="layui-input-block">
							<input type="text"
							name="url" required="" lay-verify="required"
							placeholder="请输入URL路径"
							value="${ment.url!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">服务动作*</label>
					<div class="layui-input-block">
							<input type="text"
							name="method" required="" lay-verify="required"
							placeholder="请输入服务动作"
							value="${ment.method!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态*</label>
					<div class="layui-input-block">
						<input type="radio" name="status" value="0" title="禁用" <#if !(ment.status??) || ment.status==0>checked</#if>>
						<input type="radio" name="status" value="1" title="启用" <#if ment.status?? && ment.status==1>checked</#if>>
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
	layui.use(['form','layer'], function() {
		var form = layui.form();
		var layer = layui.layer;
	});
</script>
</html>
