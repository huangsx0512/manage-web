


$(".leftsidebar_box dt").css({"background-color":"#1AA094"});
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#1AA094"})
		$(this).css({"background-color": "#696969"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","../images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","../images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
})



