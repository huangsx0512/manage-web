<!DOCTYPE html>
<html>
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css?time=201703311121">  
<script src="${basePath}/bootstrap/js/shiro/role/list.js?time=201706071442"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>角色管理</title>
<style>
	.left{
		float:left;
		min-width:100px;
		margin-left: 10px;
	}
	.red_text{
		color:red;
	}
	.blue_text{
		color:blue;
	}
	.table-margin{
		margin:5px 3%;
		width:92%;
	}
</style>

</head>
<body>
   
<div class="container" style="margin-left:5%;width:92%;">
<form class="layui-form left" action="#" >
	<div class="layui-form-item">
	     <div class="layui-input-block left">
	      <button type="button" class="layui-btn" id="add-button">添加角色</button>
	    </div>
	</div>
</form>
</div>

    <table class="table table-margin">
  <thead>
      <tr>
         <th>角色ID</th>
         <th>名称</th>
         <th>类型</th>
         <th>操作</th>
      </tr>
   </thead>
	<tbody>
	<#list roleList! as role>
			<tr>
				<td>${role.roleId!}</td>
				<td>${role.roleName!}</td>
				<td><#if role.roleType?? && role.roleType=='admin'>管理员<#else>普通</#if></td>
				<td>
				<div class="btn-group ">
				  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    	操作<span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
				    <li><a href="#" onclick="openEdit('${role.roleId!}')">修改</a></li>
				    <li><a href="#" onclick="rolePermissionEdit('${role.roleId!}')">管理权限</a></li>
				    <li><a href="#" onclick="deleteRole('${role.roleId!}')">删除</a></li>
				  </ul>
				</div>
				</td>
			</tr>
	</#list>
	</tbody>
	</table>
</body>
<script type="text/javascript">
	var basePath="${basePath}";
	$(function(){
		layui.use(['form', 'layer'], function(){
		  var layer = layui.layer;
		  var form = layui.form(); 
		});
	});
</script>
</html>