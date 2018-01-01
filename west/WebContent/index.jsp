<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>聊城智慧城市认证页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/flexslider.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jquery.fancybox.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/responsive.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/font-icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/1.css">
</head>

<body>
	<!-- header section -->
	<section class="banner" role="banner"> 
	<header id="header">
		<div class="header-content clearfix" >
			<img alt="" src="${pageContext.request.contextPath }/resources/images/success.jpg">
			<!-- <a class="logo" href="#">认证成功</a> -->
	
	
		</div>
	</header> <!-- banner text -->
	<div class="banner" id="banner" >
		<div class="slider-banner">
			<div data-lazy-background="${pageContext.request.contextPath }/resources/images/tianqibj.jpg" >
				<span id="contentspan" ></span>
			</div>
		</div>
		<!-- banner text -->
	</div>
	<!-- header section -->

	<!-- services section -->
	<section id="services" class="services service-section">
	<div class="container">
		<div class="section-header">
			<h4 class="wow fadeInDown animated">江北水城，智慧聊城</h4>
			<!-- <p class="wow fadeInDown animated">
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent
				eget risus vitae massa <br> semper aliquam quis mattis quam.
			</p> -->
		</div>
		<div class="row">
			<div id="newslist" class="col-md-4 col-sm-6 services text-center">
				<span class=""></span>
				
			</div>
		</div>
	</div>
	</section>
	<!-- services section -->


	<!-- contact section -->
	<section id="contact" class="section">
	<div class="container">
		<div class="section-header">
			<p class="wow fadeInDown animated">
				聊城市人民政府  &nbsp;&nbsp;&nbsp;智慧聊城建设办公室
			</p>
		</div>

	</div>
	</section>
	<!-- contact section -->

	<!-- JS FILES -->
	<script src="${pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jquery.flexslider-min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jquery.fancybox.pack.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/modernizr.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.contact.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/js/jquery.devrama.slider.min-0.9.4.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.slider-banner').DrSlider({
				'transition' : 'fade',
				showNavigation : false,
				progressColor : "#03A9F4"
			});
			
			$.post("${pageContext.request.contextPath }/homeController/getWeather",function(result){
			   var json = JSON.parse(result); 
			   json.cond_url="${pageContext.request.contextPath }/resources/images/100.png";
			   if(json.cond_txt==="晴"){
				   json.cond_url="${pageContext.request.contextPath }/resources/images/100.png";
			   }
			   if(json.cond_txt==="多云"){
				   json.cond_url="${pageContext.request.contextPath }/resources/images/101.png";
			   }
			   
			   if(json.cond_txt==="阴"){
				   json.cond_url="${pageContext.request.contextPath }/resources/images/104.png";
			   }
			   
			   	$("#contentspan").html("&nbsp;&nbsp;&nbsp;<font size='4' color='#000000' face='微软雅黑'>温度:"+json.fl+"&#8451;&nbsp; 风向："+json.wind_dir +"  天气："+json.cond_txt +"</font><img height='50px' src='"+json.cond_url+"'/>");
			});
			
			$.post("${pageContext.request.contextPath }/homeController/getLiaoChengNews",function(result){
				var text="";
				for (var i = 0; i < 5; i++) {
					var title= result[i].title;
					var href= result[i].src;
					var img="<img src='${pageContext.request.contextPath }/resources/images/"+i+".jpg' width='70px' height='70px'/>";
					var time=result[i].time;
					text+="<div class='services-content'><div style='float:left'> "+img+"</div>&nbsp;<div style='margin-left:80px; margin-top:-20px;'><p><a href='"+href+"'>"+title.substring(0,17)+" </p><p>抓取时间："+time+"</P></div>	</div>";
				}
				$("#newslist").html(text);
			});
			
			$.post("${pageContext.request.contextPath }/homeController/getTouTiaoNews",function(result){
				var data=result.real_time_news;
				var text="";
				for (var i = 0; i < 5; i++) {
					var title= decodeUnicode(data[i].title);
					var href= "http://www.toutiao.com/group/"+data[i].group_id;
					var url=""+(data[i].image_url);
					var time=data[i].time;
					var img="<img src="+url+" width='70px' height='70px'/>"
					text+="<div class='services-content'> <div style='float:left'>"+img+"</div>&nbsp;<div style='margin-left:80px; margin-top:-20px;'><p><a href='"+href+"' >"+title.substring(0,30)+"</a>	</p><p>抓取时间："+time+"</P></div></div>";
				}
				$("#newslist").append(text);
			});
		});
		
	    // 转为unicode 编码  
	    function encodeUnicode(str) {  
	        var res = [];  
	        for ( var i=0; i<str.length; i++ ) {  
	        res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);  
	        }  
	        return "\\u" + res.join("\\u");  
	    }  
	      
	    // 解码  
	    function decodeUnicode(str) {  
	        str = str.replace(/\\/g, "%");  
	        return unescape(str);  
	    }  
	</script>
</body>
</html>