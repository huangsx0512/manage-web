
<!doctype html>
<html class="no-js">
<#include "../../common/header.ftl">
<script src="${basePath}/bootstrap/js/auth/check.js?time=201703301143"></script>
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">  
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>认证信息</title>
<style>
	.text-td{
		width:10%;
		text-align: center;
	}
	.input-td{
		width:15%;
	}
	.tab-div{
		min-height:400px;
	}
	.site-upload{
		position: relative;
    	background: #e2e2e2;
    	float:left;
    	margin-right:20px;
	}
	.site-upload, .site-upload img{
		width: 200px;
    	height: 200px;
   		//border-radius: 100%;
	}
	.left{
		float:left;
		min-width:100px;
		margin:2px 5px;
	}
	#content-ul li{
		 float:left;
		 margin:2px 5px;
		 list-style:none;
		 display:block; 
		 white-space: nowrap;
		 width:48%;
	}
	#content-ul img{
		width:150px;
		height:120px;
	}
	.company-image{
		width:100%;
		min-height:120px;
		padding:5px 10px;
	}
	.company-image li{
		float: left;
	    margin: 2px 5px;
	    list-style: none;
	    display: block;
	    white-space: nowrap;
	    width: 120px;
	}
	.company-image img{
		width:100px;
		height:100px;
	}
	.work-image{
		width:100%;
		min-height:120px;
		padding:5px 10px;
	}
	.work-image li{
		float: left;
	    margin: 2px 5px;
	    list-style: none;
	    display: block;
	    white-space: nowrap;
	    width: 120px;
	}
	.work-image img{
		width:100px;
		height:100px;
	}
</style>
</head>
<body>
<form class="layui-form " action="#" >
<div class="layui-tab tab-div">
  <ul class="layui-tab-title">
    <li class="layui-this">基础信息</li>
    <li>认证信息</li>
  </ul>
  <div class="layui-tab-content " >
    <div class="layui-tab-item layui-show">
	    	<table class="layui-table" lay-skin="line">
	    		<tr>
	    			<td class="text-td" ><lable>真实姓名</lable></td>
	    			<td class="input-td"><lable>${userAuth.authentication.realName!}</lable></td>
	    			<td class="text-td" ><lable>手机号</lable></td>
	    			<td class="input-td"><lable>${userAuth.authentication.mobilePhone!}</lable></td>
	    			<td class="text-td" ><lable>微信</lable></td>
	    			<td class="input-td"><lable>${userAuth.authentication.weixin!}</lable></td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" style="width:120px;"><lable>所在地</lable></td>
	    			<td class="input-td"><lable>${userAuth.authentication.address!}</lable></td>
	    			<td class="text-td" style="width:120px;"><lable>性别</lable></td>
	    			 <td class="input-td"><lable>
	    			 	<#if userAuth.authentication.sex?? && userAuth.authentication.sex==1> 男
	    			 	<#elseif userAuth.authentication.sex?? && userAuth.authentication.sex==2>女
	    			 	<#else >保密
	    			 	</#if></lable>
	    			 </td>
	    			<td class="text-td" style="width:120px;"><lable>职业</lable></td>
	    			<td class="input-td"><lable>${userAuth.authentication.job!}</lable></td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" colspan="1"><lable>身份证证件号</lable></td>
	    			<td class="input-td" colspan="5"><lable>${userAuth.authentication.idcardNo!}</lable></td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" ><lable>身份证正反照</lable><br/>(点击图片放大)</td>
	    			<td  colspan="5">
	    				<#if (userAuth.idCards?size>0)>
	    				<#list userAuth.idCards as idcard>
	    				<div  class="site-upload">
	    					<img data-img="${idcard.image}" class="card-image" onclick="showImg(this)"  src="${imgUrl+idcard.image}" >
	    				</div>
	    				</#list>
	    				<#else>
	    					没上传证件照
	    				</#if>
	    			</td>
	    		</tr>
	    		
	    	</table>
    </div>
    <div class="layui-tab-item ">
    	<div class="layui-form-item ">
		    <label class="left">认证类型:<#if userAuth.authentication.attestationType?? && userAuth.authentication.attestationType=="designer">设计师
		    	<#elseif userAuth.authentication.attestationType?? && userAuth.authentication.attestationType=="cloth">面料供应商
		    	<#elseif userAuth.authentication.attestationType?? && userAuth.authentication.attestationType=="accessory">辅料供应商
		    	<#elseif userAuth.authentication.attestationType?? && userAuth.authentication.attestationType=="pattern">纸样师
		    	<#elseif userAuth.authentication.attestationType?? && userAuth.authentication.attestationType=="sewing">车板工
		    	<#else>加工厂
		    	</#if></label>
	    </div>
		<div>
			<#if userAuth.authentication.attestationType?? &&( userAuth.authentication.attestationType=="cloth" || userAuth.authentication.attestationType=="accessory" || userAuth.authentication.attestationType=="factory")>
				<div class="info-company" data-type="show" >
					<table class="layui-table" lay-skin="line">
						<tr>
			    			<td class="text-td"><lable>公司名称</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.companyName!}</lable></td>
			    			<td class="text-td" ><lable>机构代码</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.companyNo!}</lable></td>
			    			<td class="text-td" ><lable>座机</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.telephone!}</lable></td>
			    		</tr>
						<tr>
			    			<td class="text-td" ><lable>公司地址</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.companyAddress!}</lable></td>
			    			<td class="text-td" ><lable>供应/加工类型</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.providerType!}</lable></td>
			    			<td class="text-td" ><lable>职位</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.job!}</lable></td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" ><lable>规模</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.scale!}</lable></td>
			    			<td class="text-td" ><lable>公司简介</lable></td>
			    			<td class="input-td" colspan="3">
			    				<textarea  rows="1" cols="40" readonly name="company-introduction" placeholder="公司简介" class="layui-textarea">${userAuth.authenticationDetail.introduction!}</textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>营业执照</lable><br/>(点击图片放大)</td>
			    			<td class="input-td" colspan="5">
			    				<div  style="width:100%;float:left;">
			    					<ul class="company-image">
			    					<#list userAuth.companys! as company>
			    						<li><img data-img="${company.image}" onclick="showImg(this)"  src="${imgUrl+company.image}">
			    						</li>
			    					</#list>
			    					</ul>
			    				</div>
			    			</td>
			    		</tr>
					</table>
				</div>
			<#else>
				<div class="info-person" data-type="show">
					<table class="layui-table" lay-skin="line" >
			    		<tr>
			    			<td class="text-td"  ><lable>个人擅长</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.skill!}</lable></td>
			    			<td class="text-td" ><lable>经验</lable></td>
			    			<td class="input-td" ><lable>${userAuth.authenticationDetail.experience!}</lable></td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" ><lable>简介</lable></td>
			    			<td class="input-td" colspan="3" >
			    				<textarea  rows="2" cols="20" name="person-introduction" readonly placeholder="个人简介" class="layui-textarea">${userAuth.authenticationDetail.introduction!}</textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>作品</lable><br/>(点击图片放大)</td>
			    			<td class="input-td"  colspan="3">
			    				<div  style="width:100%;float:left;">
			    					<ul class="work-image">
			    						<#list userAuth.works! as work>
			    						<li><img data-img="${work.image}" onclick="showImg(this)"  src="${imgUrl+work.image}">
			    						</li>
			    						</#list>
			    					</ul>
			    				</div>
			    			</td>
			    		</tr>
					</table>
				</div>
			</#if>
		</div>
	</div>
  </div>
</div>
</form>
<#if isCheck==1>
<div class="remark-div" style="margin: 20px 10px" >
<form class="layui-form " action="#">
	<textarea  rows="3" cols="20" name="remark" placeholder="审核不通过备注"  class="layui-textarea" ></textarea>
</form>
</div>
<div style="margin-left: 100px;margin-bottom:30px;">
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 100px;" id="success-button"><i class="layui-icon">&#xe605;</i>审核通过</button>
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 100px;" id="fail-button"><i class="layui-icon">&#xe605;</i>审核不通过</button>
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 50px;" id="cloth-button"><i class="layui-icon">&#x1006;</i>关闭</button>
</div>
<#else>
<div style="margin-left: 100px;margin-bottom:30px;">
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 50px;" id="cloth-button"><i class="layui-icon">&#x1006;</i>关闭</button>
</div>
</#if>
<script type="text/javascript">
var basePath="${basePath}";
var init={
	userId:"${userId}",
	authId:"${userAuth.authenticationId!}"
}
layui.use(['form','element'],function(){
	var form = layui.form();
 	element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
});
</script>
</body>
</html>
