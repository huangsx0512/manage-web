<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>ZIMMUR平台管理后台</title>
		<#include "../common/header.ftl">
		<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${basePath}/css/global.css" media="all">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/daohang.css">

	 <script type="text/javascript" src="${basePath}/js/menuload.js"></script>
	 <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
	 <script type="text/javascript" src="${basePath}/js/auth/app/list.js"></script>
	 <script type="text/javascript" src="${basePath}/js/auth/role/list.js"></script>
	 <script type="text/javascript" src="${basePath}/js/auth/permission/list.js"></script>
	 <script type="text/javascript" src="${basePath}/js/jquery.easyui.min.1.2.2.js"></script>
	 
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;width:300px;" href="#">
							<span style="font-size: 22px;">ZIMMUR平台管理后台</span>
						</a>
					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="../bootstrap/images/0.jpg" />
								<span>${token.nickname!}</span>
							</a>
							<dl class="layui-nav-child">
								<!--<dd>
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i>个人信息</a>
								</dd>
								<dd>
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd> -->
								<dd>
									<a href="${basePath}/open/logout.shtml"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				
				  
		<!-- 左导航 -->		  
<div class="container">
	<div class="leftsidebar_box">
		<div class="line"></div>
		
		
		<dl class="system_log">
			<dt>系统管理</dt>
			<dd class="first_dd"><a href="javascript:void(0)" onclick="addTab('账号管理','${basePath}/admin/subject/list.shtml','')">账号管理</a></dd>
			<dd><a href="javascript:void(0)" onclick="addTab('角色管理','${basePath}/admin/role/list.shtml','')">角色管理</a></dd>
			<dd><a href="javascript:void(0)" onclick="addTab('权限管理','${basePath}/admin/permission/list.shtml','')">权限管理</a></dd>
		</dl>
	
		<dl class="custom">
			<dt >应用权限管理</dt>
			<dd class="first_dd"><a href="javascript:void(0)" onclick="addTab('应用管理','${basePath}/app/initApp.shtml','')">应用管理</a></dd>
			<dd><a href="javascript:void(0)" onclick="addTab('应用角色管理','${basePath}/role/initRole.shtml','')">应用角色管理</a></dd>
			<dd><a href="javascript:void(0)" onclick="addTab('应用权限管理','${basePath}/ment/initMent.shtml','')">应用权限管理</a></dd>
		</dl>
		<!--
			<#list menuList! as menu>
			<dl>
				<dt >${menu.menuName}</dt>
				<#list menu.childMenuList! as childMenu>
				<dd><a href="javascript:void(0)" onclick="addTab('${childMenu.menuName!}','${basePath}${childMenu.menuUrl!}','')">${childMenu.menuName!}</a></dd>
				</#list>
			</dl>
			</#list>
		<dl class="service">
			<dt>服务管理</dt>
			<dd class="first_dd"><a href="#" onclick="addTab('服务管理','${basePath}/service/searchService.shtml','')"  >服务信息</a></dd>
		</dl>
		<dl class="channel">
			<dt>供应商管理</dt>
			<dd class="first_dd"><a href="#" onclick="addTab('供应商信息','${basePath}/provider/queryProvider.shtml','')"  >供应商信息</a></dd>
			<dd><a href="#" onclick="addTab('供应商商品','${basePath}/goods/searchGoods.shtml','')"  >供应商商品</a></dd>
			<dd class=""><a href="#"  onclick="addTab('商品类型管理','${basePath}/goodsType/queryType.shtml','')"  >商品类型管理</a></dd>
		</dl>
	
		<dl class="app">
			<dt >运营管理</dt>
			<dd class="first_dd"><a href="#"  onclick="addTab('找面料页管理','${basePath}/page/clothModel.shtml','')" >找面料页管理</a></dd>
			<dd ><a href="#"  onclick="addTab('找服务页管理','${basePath}/page/serviceModel.shtml','')" >找服务页管理</a></dd>
			<dd ><a href="#"  onclick="addTab('订阅页管理','${basePath}/page/subscriptionModel.shtml','')" >订阅页管理</a></dd>
			<dd><a href="#" onclick="addTab('推送消息','${basePath}/userManager/queryUserMessage.shtml','')" >推送消息</a></dd>
			<dd><a href="#" onclick="addTab('评价管理','${basePath}/userManager/estimate/list.shtml','')"  >评价管理</a></dd>
			<dd><a href="#"  onclick="addTab('字典管理','${basePath}/dictionary/searchDictionaryType.shtml','')">字典管理</a></dd>
			<dd ><a href="#"  onclick="openTab('在线客服','${basePath}/page/huanxinLogin.shtml','')" >在线客服</a></dd>
			<dd ><a href="#"  onclick="addTab('问题类型','${basePath}/question/queryQuestionType.shtml','')" >问题类型</a></dd>
			<dd ><a href="#"  onclick="addTab('常见问题','${basePath}/question/queryQuestion.shtml','')" >常见问题</a></dd>
			<dd ><a href="#"  onclick="addTab('问题反馈','${basePath}/userManager/queryUserAdvice.shtml','')" >问题反馈</a></dd>
		</dl>
	
		<dl class="source">
			<dt>订单管理</dt>
			<dd class="first_dd"><a href="#"  onclick="addTab('订单管理','${basePath}/orderManager/queryOrder.shtml','')" >订单管理</a></dd>
			 <dd><a href="#" onclick="addTab('微信支付记录','${basePath}/orderManager/queryWxpayLog.shtml','')" >微信支付记录</a></dd>
			<dd><a href="#" onclick="addTab('支付宝支付记录','${basePath}/orderManager/queryAlipayLog.shtml','')"  >支付宝支付记录</a></dd>
		</dl>
		-->
	</div>
</div>

  <#-- 引入导航栏需要的js文件 -->
<script type="text/javascript" src="${basePath}/js/daohan.js"></script>
</div>
			
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;min-height:91%; padding: 5px 0 0 0;"  >
				<div class="ment-tab"  style="height:4%"></div>
				<div class="iframe-content" style="height:95%">
					<iframe scrolling="auto" frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
				</div>
			</div>
			
			<div class="layui-footer footer footer-demo" id="admin-footer" style="z-index:9999;">
				<div class="layui-main">
					<p>深圳市子牧有限公司 </p>
				</div>
			</div>
		</div>
	</body>

<script type="text/javascript">
$(function(){
	$(".admin-header-item").mouseover(function(){
		$(".layui-nav-child").show();
	})
	$(".admin-header-item").mouseout(function(){
		$(".layui-nav-child").hide();
	})
})

</script>
</html>