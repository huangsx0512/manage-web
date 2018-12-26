<!DOCTYPE html>
<html>   
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css?time=201703311121">  
<script src="${basePath}/js/auth/role/list.js"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		      <input type="text" name="keyword" value="${keyword!}"   placeholder="角色名称" autocomplete="off" 
		      class="layui-input" style="width:400px;margin-right:200px;margin-left:30px">
		 </div>
	     <div class="layui-input-block left">
	      <button type="button" class="layui-btn"  id="search-button">搜索</button>
	      <button type="button" class="layui-btn" id="reset-button">重置</button>
	      <button type="button" class="layui-btn" id="add-button">添加角色</button>
	    </div>
	</div>
</form>
</div>

    <table class="table table-margin">
  <thead>
      <tr>
         <th>角色ID</th>
         <th>角色名称</th>
         <th>状态</th>
         <th>角色权限</th>
      </tr>
   </thead>
	<tbody>
	<#if page??>
		<#list page.itemList as role>
				<tr>
					<td>${role.id!}</td>
					<td>${role.name!}</td>
					<td><#if role.isEnabled?? && role.isEnabled==1>正常<#else>禁用</#if></td>
					<td>${role.ment!}</td>
					<td>
					<div class="btn-group ">
					  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    	操作<span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu">
					    <li><a href="#" onclick="openEdit('${role.id!}')">修改</a></li>
					    <#if role.isEnabled == 1>
					    <li id="forbid"><a href="#" onclick="updateStatus('${role.id!}','0')")">禁用</a></li>
					    <#else>
					    <li id="enable"><a href="#" onclick="updateStatus('${role.id!}','1')")">启用</a></li>
					    </#if>
					    <li id="delete"><a href="#" onclick="deleteRole('${role.id!}')">删除</a></li>
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
	</#if>
  
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