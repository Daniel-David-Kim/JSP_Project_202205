<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*" %>
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

				<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
					<ul class="lectures_container">
						<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/accountInfo" class="lecture_link">회원 정보</a></li>
						<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/editProfile" class="lecture_link">회원 정보 수정</a></li>
						<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/lectureBoard" class="lecture_link">강의자 게시판</a></li>
						<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/adminBoard" class="lecture_link">회원 관리</a></li>	
					</ul>

					<!--hr width="288" style="height:1px; background-color:#BCBEC0; margin-top:9px;"--><!--hr:밑줄 라인-->
					<!--div class="lecturer"--><!-- div.lecturer : 강의자의 프로필을 보여준다. -->
						<!--img src="./resources/images/profile1.png" class="lecturer_profile" width=57 height=57 />
						<div class="lecturer_info">
							<div class="lecturer_class">강의자</div>
							<div class="lecturer_name">나도코딩</div>
						</div>
					</div--><!-- div.lecturer -->
					<!--hr width="288" style="height:1px; background-color:#BCBEC0;"--><!--hr:밑줄 라인-->
					<!--div id="lecturer_link"--><!-- div#lecturer_link : 강의자로 로그인하면 활성화되는 메뉴 : 현재는 display:none으로 되어있지만, display:flex로 하면 다시 나타난다! -->
						<!--a href="#">강의 수정</a>
						<a href="#">강의 삭제</a>
					</div--><!-- div#lecturer_link -->
					<!--div id="user_link"--><!-- div#user_link : 사용자로 로그인하면 활성화되는 메뉴 : display:none으로 하면 사라지고, display:flex로 하면 다시 나타난다. -->
						<!--a href="#">강의자 Q&A</a>
					</div--><!-- div#lecturer_link -->

				</div>

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="padding-left:40px; padding-right:130px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->


						<p class="depth_p"></p><!--p.depth_p : 문단 사이의 간격을 정해주기 위한 p클래스입니다.-->
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<div class="logined_profile_container">
								<div class="logined_profile_container_pad_fr">
									<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width=233 height=233 />
									<div class="logined_info">
										<h1>AdminiStractor</h1>
										<h3>관리자</h3>
										<h3>010-1111-2222</h3>
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