
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/bootstrap/js/shiro/role/edit.js?time=201706071442"></script>
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
	.parent_span{
		font-size: 18px;
	    font-weight: 500;
	    line-height: 22px;
	}
	.child_span{
		padding-left:5px;
		font-size: 14px;
	    line-height: 18px;
	}
</style>
</head>
<body>
<div style="min-height: 80%; min-width: 90%; margin: 20px 20px;">
	<div>
		<div style="width:45%;float:left;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:112px;">角色名称*</label>
					<div class="layui-input-block">
							<input type="text"
							name="roleName" required="" lay-verify="required"
							placeholder="请输入名称"
							value="${roleName!}" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">类型*</label>
					<div class="layui-input-block">
						<input type="radio" name="roleType" value="admin" title="管理员" <#if roleType?? && roleType=="admin">checked</#if> >
						<input type="radio" name="roleType" value="common" title="普通" <#if !(roleType??) || roleType=="common">checked</#if>>
					</div>
				</div>
			</form>
		</div>
		<div style="width:45%;float:right;">
			<ul class="am-list admin-sidebar-list">
			<#list menuList! as menu>
				<li class="parent" >
					<span class="parent_span">${menu.menuName!}</span>
					<ul class="am-list admin-sidebar-sub ">
					<#list menu.childMenuList! as childMenu>
						<li class="child">
							<input type="checkbox" value="${childMenu.menuId}" class="childMenu"  <#if (childMenu.choose==1)>checked="checked"</#if>/>
								<span class="child_span">${childMenu.menuName }</span>
						</li>
					</#list>
					</ul>
				</li>
			
			</#list>
    	</ul>
		</div>
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
	 	roleId:"${roleId}"
	 	}
	layui.use(['form','layer'], function() {
		var form = layui.form();
		var layer = layui.layer;
	});
</script>
</html>
