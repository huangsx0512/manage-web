
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/js/auth/app/appEdit.js"></script>
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
					<label class="layui-form-label" style="width:112px;">应用名称*</label>
					<div class="layui-input-block">
							<input type="hidden" name="appId" value="${app.id!}">
							<input type="text"
							name="name" required="" lay-verify="required"
							placeholder="请输入名称"
							value="${app.name!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">应用码*</label>
					<div class="layui-input-block">
							<input type="text"
							name="code" required="" lay-verify="required"
							placeholder="请输入应用码"
							value="${app.code!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">应用关键字*</label>
					<div class="layui-input-block">
							<input type="text"
							name="appKey" required="" lay-verify="required"
							placeholder="应用关键字"
							value="${app.appKey!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">应用的IP地址*</label>
					<div class="layui-input-block">
							<input type="text"
							name="ips" required="" lay-verify="required"
							placeholder="请输应用的IP地址"
							value="${app.ips!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态*</label>
					<div class="layui-input-block">
						<input type="radio" name="status" value="0" title="禁用" <#if !(app.isEnabled??) || app.isEnabled==0>checked</#if> >
						<input type="radio" name="status" value="1" title="启用" <#if app.isEnabled?? && app.isEnabled==1>checked</#if>>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">角色*</label>
					<div class="layui-input-block">
						<#list roleList! as roleVo>
							 <input type="checkbox" name="role" title="${roleVo.name!}" value="${roleVo.id!}" <#if roleVo.choose==1>checked</#if>>
						</#list>
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
