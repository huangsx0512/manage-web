
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/bootstrap/js/shiro/subject/edit.js?time=2017060309015"></script>
<script	src="${basePath}/bootstrap/js/AccountUtil.js?time=201706021728"></script>
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
					<label class="layui-form-label" style="width:112px;">昵称*</label>
					<div class="layui-input-block">
							<input type="text"
							name="nickname" required="" lay-verify="required"
							placeholder="请输入昵称"
							value="${nickname!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">登录账号*</label>
					<div class="layui-input-block">
							<input type="text"
							name="accountName" required="" lay-verify="required"
							placeholder="请输入账号"
							value="${accountName!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">登录密码*</label>
					<div class="layui-input-block">
							<input type="text"
							name="password" required="" lay-verify="required"
							<#if accountId?? && (accountId>0)>placeholder="留空不修改密码"<#else>placeholder="请输入密码"</#if>
							value="" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">状态*</label>
					<div class="layui-input-block">
						<input type="radio" name="status" value="0" title="禁用" <#if !(status??) || status==0>checked</#if> >
						<input type="radio" name="status" value="1" title="启用" <#if status?? && status==1>checked</#if>>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">角色*</label>
					<div class="layui-input-block">
						<#list roleList! as role>
							 <input type="checkbox" name="role" title="${role.roleName}" value="${role.roleId}" <#if role.choose==1>checked</#if>>
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
	var init={
	 	accountId:"${accountId}"
	 	}
	layui.use(['form','layer'], function() {
		var form = layui.form();
		var layer = layui.layer;
	});
</script>
</html>
