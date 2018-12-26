<!DOCTYPE html>
<html>
<#include "../../common/header.ftl">
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">  
<script src="${basePath}/bootstrap/js/auth/list.js?time=201704061428"></script>
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>认证记录管理</title>
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
<div class="layui-form-item ">
    <label class="layui-form-label left">认证状态</label>
    <div class="layui-input-block left">
      <select name="authStatus" >
        <option value="">所有</option>
        <option value="">所有</option>
        <option value="0" <#if param.authStatus?? && param.authStatus==0> selected</#if>>未认证</option>
        <option value="1" <#if param.authStatus?? && param.authStatus==1> selected</#if>>认证中</option>
        <option value="2" <#if param.authStatus?? && param.authStatus==2> selected</#if>>已认证</option>
        <option value="3" <#if param.authStatus?? && param.authStatus==3> selected</#if>>取消认证</option>
      </select>
    </div>
    <label class="layui-form-label left" >审核状态</label>
    <div class="layui-input-block left">
      <select name="isValid">
        <option value="" >所有</option>
        <option value="" >所有</option>
        <option value="1" <#if param.isValid?? && param.isValid==1> selected</#if>>已失效</option>
        <option value="2" <#if param.isValid?? && param.isValid==2> selected</#if>>待审核</option>
        <option value="3" <#if param.isValid?? && param.isValid==3> selected</#if>>已审核</option>
      </select>
    </div>
    <label class="layui-form-label left" >认证类型</label>
    <div class="layui-input-block left">
      <select name="authType">
        <option value="" >所有</option>
        <option value="" >所有</option>
        <option value="designer" <#if param.authType?? && param.authType=="designer"> selected</#if>>设计师</option>
        <option value="cloth" <#if param.authType?? && param.authType=="cloth"> selected</#if>>面料供应商</option>
        <option value="accessory" <#if param.authType?? && param.authType=="accessory"> selected</#if>>辅料供应商</option>
        <option value="pattern" <#if param.authType?? && param.authType=="pattern"> selected</#if>>纸样师</option>
        <option value="sewing" <#if param.authType?? && param.authType=="sewing"> selected</#if>>车板工</option>
        <option value="factory" <#if param.authType?? && param.authType=="factory"> selected</#if>>加工厂</option>
      </select>
    </div>
</div>
<div class="layui-form-item">
	<label class="layui-form-label left">申请时间</label>
    <div class="layui-input-inline left">
      <input class="layui-input" placeholder="开始" id="createTimeStart"  name="createTimeStart" value=${param.createTimeStart!}>
    </div>
    <div class="layui-input-inline left">
      <input class="layui-input " placeholder="截止" id="createTimeEnd"  name="createTimeEnd" value=${param.createTimeEnd!}>
    </div>
	<label class="layui-form-label left">审核时间</label>
    <div class="layui-input-inline left">
      <input class="layui-input" placeholder="开始" id="checkTimeStart"  name="checkTimeStart" value=${param.checkTimeStart!}>
    </div>
    <div class="layui-input-inline left">
      <input class="layui-input " placeholder="截止" id="checkTimeEnd"  name="checkTimeEnd" value=${param.checkTimeEnd!}>
    </div>
</div>
<div class="layui-form-item">
 <div class="layui-input-block left">
      <input type="text" name="keyword" value="${param.keyword!}"   placeholder="用户ID/手机号/昵称/地区/认证ID/真实姓名" autocomplete="off" 
      class="layui-input" style="width:400px;margin-right:200px;margin-left:30px">
    </div>
     <div class="layui-input-block left">
      <button type="button" class="layui-btn"  id="search-button">搜索</button>
      <button type="reset" class="layui-btn" id="reset-button">重置</button>
    </div>
</div>
</form>
</div>

    <table class="table table-margin">
  <thead>
      <tr>
         <th>认证ID</th>
         <th>认证名称</th>
         <th>认证状态</th>
         <th>认证类型</th>
         <th>所在地</th>
         <th>关联用户ID</th>
         <th>关联手机号</th>
         <th>关联用户昵称</th>
         <th>真实姓名</th>
         <th>申请时间</th>
         <th>申请人</th>
         <th>审核时间</th>
         <th>审核状态</th>
         <th>审核人</th>
         <th>审核备注</th>
         <th>操作</th>
      </tr>
   </thead>
	<tbody>
	<#list page.itemList! as auth>
			<tr>
				<td>${auth.authenticationId!}</td>
				<td>
					<#if auth.attestationType=="designer" || auth.attestationType=="pattern"|| auth.attestationType=="sewing">${auth.realName!}
					<#else>${auth.companyName!}
					</#if>
				</td>
				<td>
					<#if auth.authenticationStatus?? && auth.authenticationStatus==0>未认证
					<#elseif auth.authenticationStatus?? && auth.authenticationStatus==1>认证中
					<#elseif auth.authenticationStatus?? && auth.authenticationStatus==2>已认证
					<#elseif auth.authenticationStatus?? && auth.authenticationStatus==3>取消认证
					</#if>
				</td>
				<td>
					<#if auth.attestationType?? && auth.attestationType="designer">设计师
					<#elseif auth.attestationType?? && auth.attestationType=="cloth">面料供应商
					<#elseif auth.attestationType?? && auth.attestationType=="accessory">辅料供应商
					<#elseif auth.attestationType?? && auth.attestationType=="pattern">纸样师
					<#elseif auth.attestationType?? && auth.attestationType=="sewing">车板工
					<#else>加工厂
					</#if>
				</td>
				<td>${auth.address!}</td>
				<td>${auth.userId!}</td>
				<td>${auth.mobileNo!}</td>
				<td>${auth.nickname!}</td>
				<td>${auth.realName!}</td>
				<td>${auth.createTime!}</td>
				<td>${auth.createMan!}</td>
				<td>${auth.checkTime!}</td>
				<td>
					<#if auth.isValid==1>待审核
					<#elseif  auth.isValid==2 || auth.isValid==3>已审核
					<#else >已失效
					</#if>
				</td>
				<td>${auth.checkMan!}</td>
				<td>${auth.remark!}</td>
				<td>
				<div class="btn-group ">
				  <button type="button" class="btn btn-primary dropdown-toggle " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    	操作<span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu">
				   <li><a href="#" onclick="openCheck('${auth.userId}','${auth.authenticationId}','0')">查看详细信息</a></li>
				  	<#if auth.isValid==1>
				    <li><a href="#" onclick="openCheck('${auth.userId}','${auth.authenticationId}','1')">认证审核</a></li>
				    </#if>
				  	<#if auth.authenticationStatus ==2 && auth.isValid!=-1>
				    <li><a href="#" onclick="openAuth('${auth.userId}','${auth.authenticationId}')">更新认证信息</a></li>
				    <li><a href="#" onclick="cancelAuth('${auth.userId}')">取消认证</a></li>
				    </#if>
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
		authType:"${param.authType!}",
		authStatus:"${param.authStatus!}",
		checkTimeStart:"${param.checkTimeStart!}",
		checkTimeEnd:"${param.checkTimeEnd!}",
		createTimeStart:"${param.createTimeStart!}",
		createTimeEnd:"${param.createTimeEnd!}",
		keyword:"${param.keyword!}",
		isValid:"${param.isValid!}"
	}
	$(function(){
		layui.use(['form', 'layer','laydate'], function(){
		  var layer = layui.layer;
		  var form = layui.form(); 
		
		  var laydate = layui.laydate;
		  var create_start = {
		    min: '2017-01-01' 
		    ,max: '2099-12-29'
		    ,istoday: false
		    ,choose: function(datas){
		      create_end.min = datas; //开始日选好后，重置结束日的最小日期
		      create_end.start = datas //将结束日的初始值设定为开始日
		    }
		  };
		  var create_end = {
		    min: laydate.now()
		    ,max: '2099-12-29'
		    ,istoday: false
		    ,choose: function(datas){
		      create_start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		  };
		  var check_start = {
		    min: '2017-01-01' 
		    ,max: '2099-12-29'
		    ,istoday: false
		    ,choose: function(datas){
		      check_end.min = datas; //开始日选好后，重置结束日的最小日期
		      check_end.start = datas //将结束日的初始值设定为开始日
		    }
		  };
		  var check_end = {
		    min: laydate.now()
		    ,max: '2099-12-29'
		    ,istoday: false
		    ,choose: function(datas){
		      check_start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		  };
		   document.getElementById('createTimeStart').onclick = function(){
		    create_start.elem = this;
		    laydate(create_start);
		  }
		  document.getElementById('createTimeEnd').onclick = function(){
		    create_end.elem = this
		    laydate(create_end);
		  }
		  document.getElementById('checkTimeStart').onclick = function(){
		    check_start.elem = this;
		    laydate(check_start);
		  }
		  document.getElementById('checkTimeEnd').onclick = function(){
		    check_end.elem = this
		    laydate(check_end);
		  }
		 
	});
  
});
</script>
</html>