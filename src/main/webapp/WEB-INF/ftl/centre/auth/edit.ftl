
<!doctype html>
<html class="no-js">
<#include "../../common/header.ftl">
<script src="${basePath}/bootstrap/js/auth/edit.js?time=201704061507"></script>
<link rel="stylesheet" href="${basePath}/plugins/layui/css/layui.css">  
<script src="${basePath}/plugins/layui/layui.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>编辑认证</title>
<style>
	.text-td{
		width:14%;
		text-align: center;
	}
	.input-td{
		width:36%;
	}
	.tab-div{
		min-height:560px;
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
	.site-upload .site-upbar{
		position: absolute;
    	top: 50%;
    	left: 50%;
    	margin: -18px 0 0 -56px;
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
	#goodsParam span{
		margin:2px 2px;
		padding:1px 2px;
		border: 1px solid #e2e2e2;
		
	}
	.selected{
		background-color: #00fff0;
	}
	.hide{
		display:none;
	}
	.blank-30{
		width:100%;
		height:30px;
		background-color: #e2e2e2;
	}
	.close-modal {
	    position: absolute;
	    //top: -8px;
	    //right: -8px;
	    width: 18px;
	    height: 18px;
	    font-size: 14px;
	    line-height: 16px;
	    border-radius: 9px;
	    z-index: 2;
	    color: #fff;
	    text-align: center;
	    cursor: pointer;
	    background: rgba(153, 153, 153, 0.6);
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
	body .layui-layer-content{overflow: 'visible'}
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
	    			<td class="text-td" style="width:120px;"><lable>真实姓名</lable></td>
	    			<td class="input-td">
	    				<input type="text" name="realName"  placeholder="请输入真实姓名" value="${userAuth.authentication.realName!}" autocomplete="off" class="layui-input"> 
	    			</td>
	    			<td class="text-td" style="width:120px;"><lable>手机号</lable></td>
	    			<td class="input-td">
	    				<input type="text" name="mobilePhone"  placeholder="请输入手机号" value="${userAuth.authentication.mobilePhone!}" autocomplete="off" class="layui-input"> 
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" style="width:120px;"><lable>微信</lable></td>
	    			<td class="input-td">
	    				<input type="text" name="weixin"  placeholder="请输入微信号" value="${userAuth.authentication.weixin!}" autocomplete="off" class="layui-input">
	    			</td>
	    			<td class="text-td" style="width:120px;"><lable>所在地</lable></td>
	    			<td class="input-td">
	    				<input type="text" name="address"  placeholder="请输入所在地" value="${userAuth.authentication.address!}" autocomplete="off" class="layui-input">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" style="width:120px;"><lable>性别</lable></td>
	    			<td class="input-td">
	    			 <div >
				      <input type="radio" name="sex" value="1" title="男" <#if userAuth.authentication.sex?? && userAuth.authentication.sex==1>checked</#if>>
				      <input type="radio" name="sex" value="2" title="女" <#if userAuth.authentication.sex?? && userAuth.authentication.sex==2>checked</#if>>
				      <input type="radio" name="sex" value="3" title="保密" <#if userAuth.authentication.sex?? && userAuth.authentication.sex==3>checked</#if>>
				    </div>
					</td>
	    			<td class="text-td" style="width:120px;"><lable>职业</lable></td>
	    			<td class="input-td" >
	    				<div class="">
					    	<select name="job">
					   			<option value="" >请选择职位</option>
					        <#list param.jobList as job>
					        	<option value="${job.name}" <#if userAuth.authentication.job?? && userAuth.authentication.job==job.name>selected</#if> >${job.name}</option>
					        </#list>
					      </select>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="text-td" style="width:120px;"><lable>身份证证件号</lable></td>
	    			<td class="input-td" colspan="3">
	    				<input type="text" name="idcardNo"  placeholder="请输入身份证证件号" value="${userAuth.authentication.idcardNo!}" autocomplete="off" class="layui-input">
	    			</td>
	    		</tr>
	    		<tr>
	    		<td class="text-td" style="width:120px;"><lable>身份证正反照</lable></td>
	    			<td class="text-td" colspan="3">
	    				<div  class="hide">
	    					<img  src="${basePath}/bootstrap/images/default_goods.jpg" >
						    <div class="site-upbar">
						  		<div class="layui-box layui-upload-button">
						  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
						  				 <input type="file" name="files"  class="layui-upload-file" >
						  			</form>
						  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传证件照 </span>
						    	</div>
						 	</div>
	    				</div>
	    				<#if (userAuth.idCards?size>0)>
	    				<#list userAuth.idCards as idcard>
	    				<div  class="site-upload">
	    					<img data-img="${idcard.image}" class="card-image"  src="${imgUrl+idcard.image}" >
						  	<div class="site-upbar">
						  		<div class="layui-box layui-upload-button">
						  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
						  				 <input type="file" name="files" data-type="card" class="layui-upload-file" >
						  			</form>
						  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传证件照 </span>
						    	</div>
						 	</div>
	    				</div>
	    				</#list>
	    				<#else>
	    				<div  class="site-upload">
	    					<img data-img="" class="card-image"  src="${basePath}/bootstrap/images/default_goods.jpg" >
						  	<div class="site-upbar">
						  		<div class="layui-box layui-upload-button">
						  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
						  				 <input type="file" name="files" data-type="card" class="layui-upload-file" >
						  			</form>
						  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传证件照 </span>
						    	</div>
						 	</div>
	    				</div>
	    				<div  class="site-upload">
	    					<img data-img="" class="card-image" src="${basePath}/bootstrap/images/default_goods.jpg" >
						    <div class="site-upbar">
						  		<div class="layui-box layui-upload-button">
						  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
						  				 <input type="file" name="files" data-type="card" class="layui-upload-file" >
						  			</form>
						  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传证件照 </span>
						    	</div>
						 	</div>
	    				</div>
	    				</#if>
	    			</td>
	    		</tr>
	    		
	    	</table>
    </div>
    <div class="layui-tab-item ">
    	<div class="layui-form-item ">
		    <label class="layui-form-label left">认证类型</label>
		    <#if userAuth.authentication.attestationType?? >
		    <div class="left">
		      <select name="userType" lay-filter="authType">
		      <#if userAuth.authentication.attestationType=="designer">
		      	<option value="designer" selected>设计师</option>
		      <#elseif userAuth.authentication.attestationType=="cloth">
		      <option value="cloth" selected>面料供应商</option>
		      <#elseif userAuth.authentication.attestationType=="accessory">
		      <option value="accessory" selected>辅料供应商</option>
		      <#elseif userAuth.authentication.attestationType=="pattern">
		      <option value="pattern" selected>纸样师</option>
		      <#elseif userAuth.authentication.attestationType=="sewing">
		      <option value="sewing" selected>车板工</option>
		      <#else>
		      <option value="factory" selected>加工厂</option>
		      </#if>
	      	</select>
	    	</div>
	    	<#else>
	    	<div class="left">
		      	<select name="userType" lay-filter="authType">
		        <option value="" >请选择认证类型</option>
		        <option value="designer" >设计师</option>
		        <option value="cloth">面料供应商</option>
		        <option value="accessory" >辅料供应商</option>
		        <option value="pattern" >纸样师</option>
		        <option value="sewing" >车板工</option>
		        <option value="factory" >加工厂</option>
	      		</select>
	    	</div>
	    	</#if>
	    </div>
		<div>
			<#if userAuth.authentication.attestationType?? &&( userAuth.authentication.attestationType=="cloth" || userAuth.authentication.attestationType=="accessory" || userAuth.authentication.attestationType=="factory")>
				<div class="info-person hide" data-type="hide" >
					<table class="layui-table" lay-skin="line">
			    		<tr>
			    			<td class="text-td" ><lable>个人擅长</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="skill"  placeholder="请选择擅长（可多选）" value="" readonly class="layui-input">
			    			</td>
			    			<td  colspan="2">
			    				<div class="layui-input-block hide person-skill" data-type="designer">
							      <#list param.designerSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide person-skill" data-type="pattern">
							      <#list param.patternSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide person-skill" data-type="sewing">
							      <#list param.sewingSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>经验</lable></td>
			    			<td>
			    				<div class="">
							      <select name="experience">
							        <option value="" >请选择经验</option>
							        <option value="1年以下" >1年以下</option>
							        <option value="1至3年" >1至3年</option>
							        <option value="3至5年" >3至5年</option>
							        <option value="5至10年" >5至10年</option>
							        <option value="10年以上">10年以上</option>
							      </select>
							    </div>
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>简介</lable></td>
			    			<td class="input-td" >
			    				<textarea  rows="3" cols="20" name="person-introduction" placeholder="个人简介" class="layui-textarea"></textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>作品（最多上传九张）</lable></td>
			    			<td  colspan="3">
			    				<div  style="width:100%;float:left;">
			    					<ul class="work-image">
			    					</ul>
			    				</div>
							  	<div  style="float:left;">
							  		<div class="layui-box layui-upload-button">
							  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
							  				 <input type="file" name="files" data-type="work" class="layui-upload-file" >
							  			</form>
							  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传作品 </span>
							    	</div>
							 	</div>
			    			</td>
			    		</tr>
					</table>
				</div>
				<div class="info-company" data-type="show" >
					<table class="layui-table" lay-skin="line">
						<tr>
			    			<td class="text-td" style="width:120px;"><lable>公司名称</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="companyName"  placeholder="请输入公司名称" value="${userAuth.authenticationDetail.companyName!}" autocomplete="off" class="layui-input"> 
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>机构代码</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="companyNo"  placeholder="请输入机构代码" value="${userAuth.authenticationDetail.companyNo!}" autocomplete="off" class="layui-input"> 
			    			</td>
			    		</tr>
						<tr>
			    			<td class="text-td" style="width:120px;"><lable>座机</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="telephone"  placeholder="请输入座机号码" value="${userAuth.authenticationDetail.telephone!}" autocomplete="off" class="layui-input"> 
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>公司地址</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="companyAddress"  placeholder="请输入公司地址" value="${userAuth.authenticationDetail.companyAddress!}" autocomplete="off" class="layui-input"> 
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>简介</lable></td>
			    			<td class="input-td" colspan="3">
			    				<textarea  rows="3" cols="40" name="company-introduction" placeholder="公司简介" class="layui-textarea">${userAuth.authenticationDetail.introduction!}</textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>供应/加工类型</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="providerType"  placeholder="请选择供应/加工类型(可多选)" value="${userAuth.authenticationDetail.providerType!}" readonly class="layui-input">
			    			</td>
			    			<td  colspan="2">
			    				<div class="layui-input-block hide provider-type" data-type="cloth">
							      <#list param.clothProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide provider-type" data-type="accessory">
							      <#list param.accessoryProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide provider-type" data-type="factory">
							      <#list param.factoryProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>职位</lable></td>
			    			<td >
			    				<div class="">
							      <select name="delail-job">
							        <option value="" >选择职位</option>
							        <#list param.jobList as job>
							        	<option value="${job.name}" <#if userAuth.authenticationDetail.job?? && userAuth.authenticationDetail.job==job.name> selected</#if>>${job.name}</option>
							        </#list>
							      </select>
							    </div>
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>规模</lable></td>
			    			<td>
			    				<div class="">
							      <select name="scale">
							        <option value="" >选择规模</option>
							        <#list param.publishScale.dictionaries as scale>
							        	<option value="${scale.name}" <#if userAuth.authenticationDetail.scale?? && userAuth.authenticationDetail.scale==scale.name> selected</#if>>${scale.name}</option>
							        </#list>
							      </select>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>营业执照（必须复印件盖章）</lable></td>
			    			<td  colspan="3">
			    				<div  style="width:100%;float:left;">
			    					<ul class="company-image">
			    					<#list userAuth.companys! as company>
			    						<li><img data-img="${company.image}"  src="${imgUrl+company.image}">
			    							<a class="close-modal" onclick="deleteImage(this)">x</a>
			    						</li>
			    					</#list>
			    					</ul>
			    				</div>
							  	<div  style="float:left;">
							  		<div class="layui-box layui-upload-button">
							  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
							  				 <input type="file" name="files" data-type="company" class="layui-upload-file" >
							  			</form>
							  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传营业执照 </span>
							    	</div>
							 	</div>
			    			</td>
			    		</tr>
					</table>
				</div>
			<#else>
				<div class="info-person" data-type="show">
					<table class="layui-table" lay-skin="line" >
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>个人擅长</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="skill"  placeholder="请选择擅长(可多选)" value="${userAuth.authenticationDetail.skill!}" readonly class="layui-input">
			    			</td>
			    			<td class="input-td" colspan="2">
			    				<div class="layui-input-block hide person-skill" data-type="designer">
							      <#list param.designerSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide person-skill" data-type="pattern">
							      <#list param.patternSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide person-skill" data-type="sewing">
							      <#list param.sewingSkill.dictionaries as value>
		    								<span onclick="checkMore(this,'person')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>经验</lable></td>
			    			<td>
			    				<div class="">
							      <select name="experience">
							        <option value="" >请选择经验</option>
							        <option value="1年以下" <#if userAuth.authenticationDetail.experience?? && userAuth.authenticationDetail.experience=="1年以下"> selected</#if>>1年以下</option>
							        <option value="1至3年" <#if userAuth.authenticationDetail.experience?? && userAuth.authenticationDetail.experience=="1至3年"> selected</#if>>1至3年</option>
							        <option value="3至5年" <#if userAuth.authenticationDetail.experience?? && userAuth.authenticationDetail.experience=="3至5年"> selected</#if>>3至5年</option>
							        <option value="5至10年" <#if userAuth.authenticationDetail.experience?? && userAuth.authenticationDetail.experience=="5至10年"> selected</#if>>5至10年</option>
							        <option value="10年以上" <#if userAuth.authenticationDetail.experience?? && userAuth.authenticationDetail.experience=="10年以上"> selected</#if>>10年以上</option>
							      </select>
							    </div>
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>简介</lable></td>
			    			<td class="input-td" >
			    				<textarea  rows="2" cols="20" name="person-introduction" placeholder="个人简介" class="layui-textarea">${userAuth.authenticationDetail.introduction!}</textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>作品（最多上传九张）</lable></td>
			    			<td  colspan="3">
			    				<div  style="width:100%;float:left;">
			    					<ul class="work-image">
			    						<#list userAuth.works! as work>
			    						<li><img data-img="${work.image}"  src="${imgUrl+work.image}">
			    							<a class="close-modal" onclick="deleteImage(this)">x</a>
			    						</li>
			    						</#list>
			    					</ul>
			    				</div>
							  	<div  style="float:left;">
							  		<div class="layui-box layui-upload-button">
							  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
							  				 <input type="file" name="files" data-type="work" class="layui-upload-file" >
							  			</form>
							  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传作品</span>
							    	</div>
							 	</div>
			    			</td>
			    		</tr>
					</table>
				</div>
				<div class="info-company hide" data-type="hide" >
					<table class="layui-table" lay-skin="line">
						<tr>
			    			<td class="text-td" style="width:120px;"><lable>公司名称</lable></td>
			    			<td class="input-td"><input  type="text"  name="companyName" value="" autocomplete="off"  placeholder="请输入公司名称" class="layui-input"></td>
			    			<td class="text-td" style="width:120px;"><lable>机构代码</lable></td>
			    			<td class="input-td"><input  type="text"  name="companyNo" value="" autocomplete="off"  placeholder="请输入机构代码" class="layui-input"></td>
			    		</tr>
						<tr>
			    			<td class="text-td" style="width:120px;"><lable>座机</lable></td>
			    			<td class="input-td"><input  type="text"  name="telephone" value="" autocomplete="off"  placeholder="请输入座机号码" class="layui-input"></td>
			    			<td class="text-td" style="width:120px;"><lable>公司地址</lable></td>
			    			<td class="input-td"><input  type="text"  name="companyAddress" value="" autocomplete="off"  placeholder="请输入公司地址" class="layui-input"></td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" ><lable>简介</lable></td>
			    			<td  colspan="3">
			    				<textarea  rows="2" cols="80" name="company-introduction" placeholder="公司简介" class="layui-textarea"></textarea>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>供应/加工类型</lable></td>
			    			<td class="input-td">
			    				<input type="text" name="providerType"  placeholder="请选择供应/加工类型(可多选)" value="${userAuth.authenticationDetail.providerType!}" readonly class="layui-input">
			    			</td>
			    			<td class="input-td" colspan="2">
			    				<div class="layui-input-block hide provider-type" data-type="cloth">
							      <#list param.clothProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide provider-type" data-type="accessory">
							      <#list param.accessoryProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    				<div class="layui-input-block hide provider-type" data-type="factory">
							      <#list param.factoryProviderType.dictionaries as value>
		    								<span onclick="checkMore(this,'company')">${value.name}</span>&nbsp;&nbsp;
		    					  </#list>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>职位</lable></td>
			    			<td >
			    				<div class="">
							      <select name="delail-job">
							        <option value="" >选择职位</option>
							        <#list param.jobList as job>
							        	<option value="${job.name}" >${job.name}</option>
							        </#list>
							      </select>
							    </div>
			    			</td>
			    			<td class="text-td" style="width:120px;"><lable>规模</lable></td>
			    			<td>
			    				<div class="">
							      <select name="scale">
							        <option value="" >选择规模</option>
							        <#list param.publishScale.dictionaries as scale>
							        	<option value="${scale.name}" >${scale.name}</option>
							        </#list>
							      </select>
							    </div>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="text-td" style="width:120px;"><lable>营业执照（必须复印件盖章）</lable></td>
			    			<td  colspan="3">
			    				<div  style="width:100%;float:left;">
			    					<ul class="company-image">
			    					</ul>
			    				</div>
							  	<div  style="float:left;">
							  		<div class="layui-box layui-upload-button">
							  			<form target="layui-upload-iframe" method="get" key="set-mine" enctype="multipart/form-data" action="#">
							  				 <input type="file" name="files" data-type="company" class="layui-upload-file" >
							  			</form>
							  		 	<span class="layui-upload-icon"><i class="layui-icon">&#xe608;</i>上传营业执照 </span>
							    	</div>
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
<div style="margin-left: 100px;margin-bottom:30px;">
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 100px;" id="save-button"><i class="layui-icon">&#xe605;</i>保存</button>
<button type="button" class="layui-btn layui-btn-normal" style="margin-left: 50px;" id="close-button"><i class="layui-icon">&#x1006;</i>关闭</button>
</div>
<script type="text/javascript">
var basePath="${basePath}";
var imgUrl="${imgUrl}";
var init={
	authType:"${userAuth.authentication.attestationType!}",
	userId:"${userId}",
	authId:"${userAuth.authenticationId!'0'}",
	category:""
}
layui.use(['form','element','upload'],function(){
	var form = layui.form();
	form.on("select(authType)", function(data){
		var type=data.value;
		if(type == "designer" || type=="pattern" || type == "sewing"){
			$(".info-person").removeClass("hide");
			$(".info-company").addClass("hide");
			$(".person-skill").addClass("hide");
			$(".person-skill[data-type='"+type+"']").removeClass("hide");
			init.category="person";
		}else{
			$(".info-company").removeClass("hide");
			$(".info-person").addClass("hide");
			$(".provider-type").addClass("hide");
			$(".provider-type[data-type='"+type+"']").removeClass("hide");
			init.category="company";
		}
		init.authType=type;
	});
 
  element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块

  layui.upload({
    url: basePath+'/restful/file/upload.shtml', //上传接口
    ext: 'jpg|png|bmp|jpeg',
    method: 'post',
    success: function(res,input){ //上传成功后的回调
    	console.log(res);
      if(res.code=="000000"){
	  	var type=$(input).attr("data-type");
	    var id=res.data[0].saveId;
	    var src=imgUrl+id;
	   	if(type=="work"){
	   		var html="<li><img data-img=\""+id+"\"  src=\""+src+"\"><a class=\"close-modal\" onclick=\"deleteImage(this)\">x</a></li>";
	    	$(".work-image").append(html);
	    }else if(type=="company"){
	    	var html="<li><img data-img=\""+id+"\"  src=\""+src+"\"><a class=\"close-modal\" onclick=\"deleteImage(this)\">x</a></li>";
	    	$(".company-image").append(html);
	    }else{
	    	$(input).parent().parent().parent().parent().children("img").attr("src",src);
	    	$(input).parent().parent().parent().parent().children("img").attr("data-img",id);
	    }
      }else{
      	layer.msg("上传失败")
      }
    }
  });
});
</script>
</body>
</html>
