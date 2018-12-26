<!DOCTYPE html>
<html>
<#include "../../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css?time=201703311121">  
<script src="${basePath}/bootstrap/js/shiro/subject/list.js?time=201706021723"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>后台账号管理</title>
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
		      <input type="text" name="keyword" value="${keyword!}"   placeholder="昵称" autocomplete="off" 
		      class="layui-input" style="width:400px;margin-right:200px;margin-left:30px">
		 </div>
	     <div class="layui-input-block left">
	      <button type="button" class="layui-btn"  id="search-button">搜索</button>
	      <button type="button" class="layui-btn" id="reset-button">重置</button>
	      <button type="button" class="layui-btn" id="add-button">添加账号</button>
	    </div>
	</div>
</form>
</div>

    <table class="table table-margin">
  <thead>
      <tr>
         <th>用户ID</th>
         <th>登录账号</th>
         <th>昵称</th>
         <th>创建时间</th>
         <th>最后登录时间</th>
         <th>账号状态</th>
         <th>角色</th>
         <th>操作</th>
      </tr>
   </thead>
	<tbody>
	<#list page.itemList! as account>
			<tr>
				<td>${account.accountId!}</td>
				<td>${account.accountName!}</td>
				<td>${account.nickname!}</td>
				<td>${account.createTime!}</td>
				<td>${account.lastLoginTime!}</td>
				<td><#if account.status?? && account.status==1>正常<#else>禁用</#if></td>
				<td>${account.roleName!}</td>
				<td>
				<div class="btn-group ">
				  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    	操作<span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
				    <li><a href="#" onclick="openEdit('${account.accountId!}')">修改</a></li>
				    <#if account.status == 1>
				    <li><a href="#" onclick="updateStatus('${account.accountId!}','0')")">禁用</a></li>
				    <#else>
				    <li><a href="#" onclick="updateStatus('${account.accountId!}','1')")">启用</a></li>
				    </#if>
				    <li><a href="#" onclick="resetPassword('${account.accountId!}')">重置密码</a></li>
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
	var init={
		keyword:"${keyword!}"
	}
	$(function(){
		layui.use(['form', 'layer'], function(){
		  var layer = layui.layer;
		  var form = layui.form(); 
		});
	});
</script>
</html>