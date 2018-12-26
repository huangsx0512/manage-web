

//打开标签窗口
var opentabwindow = function(tabTitle,url){
	addTab(tabTitle,url,'icon icon-null');
};
//菜单单击事件
/*var menuclick = function(){
		//var tabTitle = $(this).children('.nav').text();
	    //获取二级菜单的title,数据从后台的menu.json中获取
		var tabTitle = $(this).attr("title");
		//获取二级菜单的url,rel是获取json数据时拼接成的html的一部分
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = 'icon '+$(this).attr("icon");
		addTab(tabTitle,url,icon);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
}; **/


/**
 * 
 * @param subtitle 标题
 * @param url 访问地址
 * @param icon 图标
 */
function openTab(subtitle,url,icon){
	window.open(url);
}
function addTab(subtitle,url,icon){
	$(".ment-tab").html(subtitle);
	$(".iframe-content iframe").attr("src",url);
	//如果指定标题的tab不存则添加一个新的
//	if(!$('#tabs').tabs('exists',subtitle)){

		/*$('#tabs').each(function(index){
			  	var tab = $(".tabs-closable", this).text();
				if(tab!="Home") $("#tabs").tabs('close', tab);//除了标签为首页的其他全部关闭
					});

		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});*/
	//}else{
		
					/** //重新加载 tab页数据
					//根据标题获取tab对象
					var currTab = $('#tabs').tabs('getTab', subtitle);
					var iframe = $(currTab.panel('options').content);//获取标签的内容

					var src = iframe.attr('src');//获取iframe的src
					//当重新选中tab时将ifram的内容重新加载一遍，目的是获取当前页面的最新内容
					$('#tabs').tabs('update', {
						tab : currTab,
						options : {
						content : createFrame(src)
						//ifram内容
						}
					});*/
	//}
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

/*//窗体加载时
window.onload=function(){
	addTab('认证管理','../userManager/userAuthenticationIndex.shtml?pageIndex=1','');
}*/








