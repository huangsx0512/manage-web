<!DOCTYPE html>
<html  lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<title>登录页面</title>
		<#include "../common/header.ftl">
		<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${basePath}/css/login.css" />
		<script type="text/javascript" src="${basePath}/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${basePath}/js/login.js?time=201709141739"></script>
		<script type="text/javascript" src="${basePath}/js/md5.js"></script>
	</head>

	<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
				<h1>后台登录</h1>
			</header>
			<div class="beg-login-main" >
				<form action="${basePath}/open/submitLogin.shtml" class="layui-form" method="post">
					<div class="layui-form-item" style="color:#000">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe612;</i>
                    	</label>
						<input type="text" id="accountName" name="accountName" lay-verify="userName" autocomplete="off" placeholder="账号" class="layui-input">
					</div>
					<div class="layui-form-item" style="color:#000">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe642;</i>
                    </label>
						<input type="password" id="accountPwd" name="accountPwd" lay-verify="password" autocomplete="off" placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="beg-pull-left beg-login-remember">
							<label>记住帐号？</label>
							<input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
						</div>
						<div class="beg-pull-right">
                        <input type="button"  class="layui-btn layui-btn-primary" id="login_button" lay-submit lay-filter="login" value="登录"/>  
						</div>
						<div class="beg-clear"  ></div>
					</div>
				</form>
			</div>
			<footer>
				<p>深圳市子牧服饰有限公司 </p>
			</footer>
		</div>
		<script type="text/javascript" src="${basePath}/plugins/layui/layui.js"></script>
		<script>
			var basePath='${basePath}';
			$(function(){
				layui.use(['form', 'layer'], function(){
				  var layer = layui.layer;
				  var form = layui.form(); 
				});
			});
		</script>
	</body>

</html>


