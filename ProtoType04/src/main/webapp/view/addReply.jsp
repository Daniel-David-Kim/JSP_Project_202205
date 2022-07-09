<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	HashMap<String, Object> authorInfo = (HashMap<String, Object>)request.getAttribute("authorInfo");
	application.setAttribute("author_id", (String)authorInfo.get("id"));
	String p_num = (String)request.getAttribute("p_num");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Write Question</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/location.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/confirm.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<jsp:include page="leftWingLecturer.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="width:710px; padding-left:160px; padding-right:120px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
					

						<div class="container">
							<form class="form_update_profile" action="<%=request.getContextPath()%>/board/lectureBoard/reply_process" method="post" encType="utf-8">
								<div class="form_update_box">
									<input type="hidden" name="p_num" value="<%=p_num%>" />
									
									<div class="form_update_row">
										<div>
											<h3>답글 제목</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<input type="text" name="title" class="form_input" />	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>답글 내용</h3>
											<p class="deep_depth_textarea"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_textfield_wrap">
											<textarea name="content" style="height:300px;"></textarea>	
										</div>
									</div>
									<p class="depth_p"></p>

									<div class="container" style="justify-content:flex-end;">
										<input type="button" value="Submit" onclick="beforeSubmit()" class="btn btn_primary" style="width:100px; margin-right:5px;" />
										<input type="button" value="Cancel"  onclick="goMenu()" class="btn btn_secondary" style="width:100px;" />
									</div>

								</div>
							</form>
						</div>


					</article><!--article.article_main-->


				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->


			
		</div><!--div.article_container-->
	</div><!--div.whole-->

</body>
</html>