<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	int uclass = (int)session.getAttribute("uclass");
	String classStr = "";
	switch(uclass) {
		case 0 : classStr="관리자"; break;
		case 1 : classStr="강의자"; break;
		case 2 : classStr="일반회원"; break;
	}
	String memberRevise = (String)application.getAttribute("memberRevise");
	String loginResult = (String)session.getAttribute("loginResult");
	if(loginResult != null && !loginResult.equals("")) session.removeAttribute("loginResult");
%>
<% if(memberRevise != null && !memberRevise.equals("")) {
		boolean memberRevised = Boolean.parseBoolean(memberRevise);
		if(memberRevised == true) {%>
			<script>alert('멤버 정보 수정에 성공했습니다!');</script>
		<% } else { %>
			<script>alert('멤버 정보 수정에 실패했습니다.....');</script>
		<% }
		application.removeAttribute("memberRevise");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Account Main Plus</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<jsp:include page="leftWingAccount.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="padding-left:40px; padding-right:130px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->


						<p class="depth_p"></p><!--p.depth_p : 문단 사이의 간격을 정해주기 위한 p클래스입니다.-->
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<div class="logined_profile_container">
								<div class="logined_profile_container_pad_fr">
									<% Blob profile = (Blob)session.getAttribute("uprofile"); %>
									<% if(profile == null) { %>
										<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width=233 height=233 />
									<% } else { 
											application.setAttribute("profileAuthor", profile);
									%>
										<img src="<%=request.getContextPath()%>/getRes/lecture/profileAuthor" width=233 height=233 />
									<% } %>
									<div class="logined_info">
										<h1><%=(String)session.getAttribute("uname")%></h1>
										<h3><%=classStr%></h3>
										<h3><%=(String)session.getAttribute("utel")%></h3>
										<div><button class="btn btn_primary" onclick="editProfile()">Edit Profile</button></div>
										<script>
											function editProfile() {
												location.href="<%=request.getContextPath()%>/account/editProfile";
											}
										</script>
									</div>
								</div>
							</div>
						</div>	
						<p class="depth_p logined"></p><!--pre.dynamic_phrase-->



					</article><!--article.article_main-->

				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->
	
		</div><!--div.article_container-->
	</div><!--div.whole-->

</body>
</html>