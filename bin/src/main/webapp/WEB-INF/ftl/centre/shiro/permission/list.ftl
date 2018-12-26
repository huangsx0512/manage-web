<!DOCTYPE html>
<html>
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css?time=201703311121">  
<script src="${basePath}/bootstrap/js/shiro/permission/list.js?time=201706051009"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>权限管理</title>
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
	      <button type="button" class="layui-btn" id="add-button">添加权限</button>
	    </div>
	</div>
</form>
</div>

    <table class="table table-margin">
  <thead>
      <tr>
         <th>权限ID</th>
         <th>权限名称</th>
         <th>链接</th>
         <th>操作</th>
      </tr>
   </thead>
	<tbody>
	<#list page.itemList! as permission>
			<tr>
				<td>${permission.authorId!}</td>
				<td>${permission.authorName!}</td>
				<td>${permission.authorUrl!}</td>
				<td>
				<div class="btn-group ">
				  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    	操作<span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
				    <li><a href="#" onclick="openEdit('${permission.authorId!}')">修改</a></li>
				    <li><a href="#" onclick="deletePermission('${permission.authorId!}')">删除</a></li>
				  </ul>
				</div>
				</td>
			</tr>
	</#list>
	</tbody>
	</table>
 
	<#-- 翻页模块代码 -->
	<ul class="pager">
		<li><a href="#" onclick="fanye('1')">首页</a></li>
		<#if page.pageNo!=1 > 
		<li><a	href="#" onclick="fanye('${page.prevPage}')">上一页</a></li>
		<#else>
		<li><a>上一页</a></li> 
		</#if>
		<li><a href="#">第${page.pageNo}页/共${page.totalPage}页</a></li>
		<#if (page.nextPage>page.pageNo) >
		<li><a href="#" onclick="fanye('${page.nextPage}')">下一页</a></li>
		<#else>
		<li><a>下一页</a></li>
		</#if>
		<li><a href="#" onclick="fanye('${page.totalPage}')">尾页</a></li>
	</ul>
  
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