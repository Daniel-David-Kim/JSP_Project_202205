<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lecturer Board Plus</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
					<ul class="lectures_container">
						<li class="lecture_list lecture_label">강의자 게시판</li>		
					</ul>

					<hr width="288" style="height:1px; background-color:#BCBEC0; margin-top:9px;"><!--hr:밑줄 라인-->
					<div class="lecturer"><!-- div.lecturer : 강의자의 프로필을 보여준다. -->
						<img src="<%=request.getContextPath()%>/resources/images/profile1.png" class="lecturer_profile" width=57 height=57 />
						<div class="lecturer_info">
							<div class="lecturer_class">강의자</div>
							<div class="lecturer_name">나도코딩</div>
						</div>
					</div><!-- div.lecturer -->
					<hr width="288" style="height:1px; background-color:#BCBEC0;"><!--hr:밑줄 라인-->
					<!--div id="lecturer_link"--><!-- div#lecturer_link : 강의자로 로그인하면 활성화되는 메뉴 : 현재는 display:none으로 되어있지만, display:flex로 하면 다시 나타난다! -->
						<!--a href="#">강의 수정</a>
						<a href="#">강의 삭제</a>
					</div--><!-- div#lecturer_link -->
					<!--div id="user_link"--><!-- div#user_link : 사용자로 로그인하면 활성화되는 메뉴 : display:none으로 하면 사라지고, display:flex로 하면 다시 나타난다. -->
						<!--a href="#">강의자 Q&A</a>
					</div--><!-- div#lecturer_link -->

				</div>

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="width:810px; padding-left:110px; padding-right:90px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
						
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<table class="table">
								<tr class="tablerow headrow"><th class="numcol">번호</th><th class="titlecol">제목</th><th class="authorcol">작성자</th><th class="datecol">작성일</th></tr>
								<tr class="tablerow rowdip"><td class="numcol">123456789101112123123123</td><td class="titlecol"><a href="#">이 강의에 대해 질문 있습니다! 긴급! 중요함!이 강의에 대해 질문 있습니다! 긴급! 중요함!
								이 강의에 대해 질문 있습니다! 긴급! 중요함!</a></td><td class="authorcol bauthor">administratoradministrator</td><td class="datecol">2022-03-02</td></tr>
								<tr class="tablerow rowwhite"><td class="numcol">123456789101112123123123</td><td class="titlecol"><a href="#">이 강의에 대해 질문 있습니다! 긴급! 중요함!이 강의에 대해 질문 있습니다! 긴급! 중요함!
								이 강의에 대해 질문 있습니다! 긴급! 중요함!</a></td><td class="authorcol bauthor">administratoradministrator</td><td class="datecol">2022-03-02</td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowdip"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
								<tr class="tablerow rowwhite"><td class="numcol"></td><td class="titlecol"></td><td class="authorcol bauthor"></td><td class="datecol"></td></tr>
							</table>
						</div>	
						<div class="container">
							<div class="table_pages">
								<ul class="page_list">
									<li><a href="#" class="page_l">이전</a></li>
									<li><a href="#" class="page_l">1</a></li>
									<li><a href="#" class="page_l">2</a></li>
									<li><a href="#" class="page_l">3</a></li>
									<li><a href="#" class="page_l">4</a></li>
									<li><a href="#" class="page_l">5</a></li>
									<li><a href="#" class="page_l">6</a></li>
									<li><a href="#" class="page_l">7</a></li>
									<li><a href="#" class="page_l">8</a></li>
									<li><a href="#" class="page_l">9</a></li>
									<li><a href="#" class="page_l">10</a></li>
									<li><a href="#">다음</a></li>
								</ul>
							</div>
						</div>
						<p class="depth_p cboard"></p><!--pre.dynamic_phrase-->


					</article><!--article.article_main-->


				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->
			
		</div><!--div.article_container-->
	</div><!--div.whole-->

</body>
</html>