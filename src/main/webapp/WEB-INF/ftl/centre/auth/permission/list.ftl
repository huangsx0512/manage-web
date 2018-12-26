<!DOCTYPE html>
<html>   
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css?time=201703311121">  
<script src="${basePath}/js/auth/permission/list.js"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		      <input type="text" name="keyword" value="${keyword!}"   placeholder="权限名称或服务名称" autocomplete="off" 
		      class="layui-input" style="width:400px;margin-right:200px;margin-left:30px">
		 </div>
	     <div class="layui-input-block left">
	      <button type="button" class="layui-btn" id="search-button">搜索</button>
	      <button type="button" class="layui-btn" id="reset-button">重置</button>
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
         <th>API服务</th>
         <th>URL路径</th>
         <th>服务动作</th>
         <th>状态</th>
         <th>权限创建日期</th>
      </tr>
   </thead>
	<tbody>
	<#if page??>
		<#list page.itemList as ment>
				<tr>
					<td>${ment.id!}</td>
					<td>${ment.name!}</td>
					<td>${ment.apiServer!}</td>
					<td>${ment.url!}</td>
					<td>${ment.method!}</td>
					<td><#if ment.status?? &&  ment.status==0>禁用<#else>已启用</#if></td>
					<td>${ment.createTime?string("yyyy-MM-dd HH:mm:ss zzzz")!}</td>
					<td>
					<div class="btn-group ">
					  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					    	操作<span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu">
					    <li><a href="#" onclick="openEdit('${ment.id!}')">修改</a></li>
					    <#if ment.status == 1>
					    <li id="forbid"><a href="#" onclick="updateStatus('${ment.id!}','0')")">禁用</a></li>
					    <#else>
					    <li id="enable"><a href="#" onclick="updateStatus('${ment.id!}','1')")">启用</a></li>
					    </#if>
					    <li id="delete"><a href="#" onclick="deleteRole('${ment.id!}')">删除</a></li>
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