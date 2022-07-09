<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Join us</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/logo2.png" />
				</div>
				<p class="depth_p"></p>
				<p class="phrase">
					생활코딩의 세계에 오신 것을 환영합니다. 생활코딩은 <strong>일반인들에게 프로그래밍을 알려주는 무료 온라인, 오프라인 수업입니다. </strong>&nbsp;
					어떻게 공부할 것인가를 생각해보기 전에 왜&nbsp;프로그래밍을 공부하는 이유에 대한 이유를 함께 생각해보면 좋을 것 같습니다. 아래 영상을 한번 보시죠.
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<iframe width="700" height="455" src="https://www.youtube.com/embed/ldULT0GDqMI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
					</iframe>
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">팀 소개</h2>
				<p class="phrase">
					이 웹 사이트는 한국 폴리텍 인천캠퍼스에서 프로젝트로 진행하여 만들게되었습니다. 팀장 : 김미준, 팀원 : 전사무엘, 김동한
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/4man.png" width="250" height="250" />
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/logo2.png" width=398 height=303 />
				</div>
				<p class="depth_p"></p>


			</article>
			
		</div>
	</div>
	
</body>
</html>