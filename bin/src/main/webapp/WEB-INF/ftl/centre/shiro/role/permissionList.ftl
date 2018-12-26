
<!doctype html>
<html class="no-js">
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${basePath}/bootstrap/css/layoutit.css">
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<script	src="${basePath}/bootstrap/js/shiro/role/permissionList.js?time=201706061122"></script>
<head>
<meta charset="UTF-8">
<title>角色权限信息</title>
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
	.span5{
		display: block;
    	float: left;
	}
	.span2{
		display: block;
    	float: left;
    	margin-left:20px;
    	margin-right:20px;
    	margin-top:10px;
    	
	}
	body{
		padding-left:10%;
		padding-right:10%;
		margin:0px;
		padding-top: 10px;
    	padding-bottom: 20px;
	}
	.title{
		line-height:30px;
		font-size:30px;
		margin-top:20px;
		margin-bottom:20px;
		text-align:center;
	}
	.word{
		line-height:24px;
		font-size:20px;
		text-align:left;
	}
</style>
</head>
<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<h3 class="title">
						${roleName!}
					</h3>
				</div>
			</div>
			<div class="row-fluid" >
				<div class="span5">
					<span class="word">
						未拥有的权限
					</span>
					<table class="table">
						<thead>
							<tr>
								<th></th>
								<th>权限名称</th>
								<th>权限链接</th>
							</tr>
						</thead>
						<tbody>
						<#list permissionList! as permission>
							<tr>
								<td>
									<input type="checkbox" class="data-checkbox"  data-id="${permission.authorId}" data-name="${permission.authorName!}">
								</td>
								<td>
									${permission.authorName}
								</td>
								<td>
									${permission.authorUrl}
								</td>
							</tr>
						</#list>
						</tbody>
					</table>
				</div>
				<div class="span2">
					 <button class="btn btn-primary" type="button" id="save-button">添加</button>
				</div>
				<div class="span5">
					<span class="word">
						已拥有的权限
					</span>
					<table class="table">
						<thead>
							<tr>
								<th>编号</th>
								<th>权限名称</th>
								<th>权限链接</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list rolePermissionList! as rolePermission>
							<tr>
								<td>
									${rolePermission.authorId}
								</td>
								<td>
									${rolePermission.authorName}
								</td>
								<td>
									${rolePermission.authorUrl}
								</td>
								<td>
									<button class="btn btn-primary" type="button" onclick="deleteRolePermission('${rolePermission.recId}')">删除</button>
								</td>
							</tr>
						</#list>
						</tbody>
					</table>
				</div>
			</div>
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
