<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String add = (String)request.getAttribute("add");
	boolean isAdd = Boolean.parseBoolean(add); 
	int categoryCode = (int)request.getAttribute("categoryCode");
	HashMap<String, Object> lectureResult = null;
	LectureBean lecture = null;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<% if(isAdd == true) { %>
		<title>Write Lecture Plus</title>
	<% } else { 
		lectureResult = (HashMap<String, Object>)request.getAttribute("lectureResult");
		lecture = (LectureBean)lectureResult.get("one");
	%>
		<title>Revise Lecture Plus</title>
	<% } %>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/effect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ajaxProcess.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/advisor.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->
				<jsp:include page="leftWing.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="width:710px; padding-left:160px; padding-right:120px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->

						<div class="container">
							<% if(isAdd == true) { %>
							<form class="form_update_profile" action="<%=request.getContextPath()%>/lecture/<%=categoryCode%>/0/addLecture_process" method="post" encType="multipart/form-data">
							<% } else { %>
							<form class="form_update_profile" action="<%=request.getContextPath()%>/lecture/<%=categoryCode%>/0/reviseLecture_process" method="post" encType="multipart/form-data">
							<% } %>
								<div class="form_update_box">

									
									<div class="form_update_row">
										<div>
											<h3>제목</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(isAdd == true) { %>
											<input type="text" name="title" class="form_input" />	<!-- 00 -->
											<% } else { %>
											<input type="text" name="title" class="form_input" value="<%=lecture.getTitle()%>" />	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<div class="form_container">  <!-- 0 -->
										<span class="warning_message"></span>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>


									<div class="form_update_row">
										<div>
											<h3>수업 소개</h3>
											<p class="depth_textarea"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_textfield_wrap">
											<% if(isAdd == true) { %>
											<textarea name="content"></textarea>	<!-- 00 -->
											<% } else { %>
											<textarea name="content"><%=lecture.getContent()%></textarea>	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>
									
									
									<div class="form_update_row">
										<div>
											<h3>강의 제목</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(isAdd == true) { %>
											<input type="text" name="vid_title" class="form_input" />	<!-- 00 -->
											<% } else { %>
											<input type="text" name="vid_title" class="form_input" value="<%=lecture.getVid_title()%>" />	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<div class="form_container">  <!-- 1 -->
										<span class="warning_message"></span>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>


									<div class="form_update_row">
										<div>
											<h3>강의 URL</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(isAdd == true) { %>
											<input type="text" name="vid_url" class="form_input" />	<!-- 00 -->
											<% } else { %>
											<input type="text" name="vid_url" class="form_input" value="<%=lecture.getVid_url()%>" />	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<div class="form_container">  <!-- 2 -->
										<span class="warning_message"></span>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>소스 코드</h3>
											<p class="depth_textarea"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_textfield_wrap">
											<% if(isAdd == true) { %>
											<textarea name="code"></textarea>	<!-- 00 -->
											<% } else { %>
											<textarea name="code"><%=lecture.getCode()%></textarea>	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>요약</h3>
											<p class="depth_textarea"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_textfield_wrap">
											<% if(isAdd == true) { %>
											<textarea name="summary"></textarea>	<!-- 00 -->
											<% } else { %>
											<textarea name="summary"><%=lecture.getSummary()%></textarea>	<!-- 00 -->
											<% } %>
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<% if(isAdd == true) { %>
									<div class="form_update_row">
										<div>
											<h3>참고 이미지</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<input type="file" name="img" />	<!-- 00 -->
										</div>
									</div>
									<p class="depth_p"></p>
									<% } else { %>
									<div class="form_update_row">
										<div>
											<h3>참고 이미지</h3>
											<p class="depth_update_deep"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<div class="update_img_file">
												<% if(lecture.getImg() == null) { %>
												<img src="<%=request.getContextPath()%>/resources/images/gray_50.jpg" style="width:110px; height:110px;" />
												<% } else { 
													application.setAttribute("lectureImg", lecture.getImg());
												%>
												<img src="<%=request.getContextPath()%>/getRes/lecture/image" style="width:110px; height:110px;" />
												<% } %>
												<input type="file" name="" />
											</div>	
										</div>
									</div>
									<p class="depth_p"></p>
									<% } %>
									
									<% if(isAdd == true || lecture.getVid_title2() == null || lecture.getVid_title2().equals("")) { %>
										<div class="container">
											<div class="popup_button" onclick="appearForm()">
												<img src="<%=request.getContextPath()%>/resources/images/plust.png" width=25 height=25 />
												&nbsp;<span class="popup_brand">강의 추가</span>
											</div>
										</div>
									<% } %>
									
									<% if(isAdd == true || lecture.getVid_title2() == null || lecture.getVid_title2().equals("")) { %>
									<div class="popup_form">
									<% } else { %>
									<div>
									<% } %>
										<p class="depth_p"></p>
										<div class="form_update_row">
											<div>
												<h3>강의 제목2</h3>
												<p class="depth_update"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_input_wrap">
												<% if(isAdd == true) { %>
												<input type="text" name="vid_title2" class="form_input" onfocus="ip2FirstTest(event)" />	<!-- 00 -->
												<% } else { %>
												<input type="text" name="vid_title2" class="form_input" onfocus="ip2FirstTest(event)" value="<%=lecture.getVid_title2()%>" />	<!-- 00 -->
												<% } %>
											</div>
										</div>
										<div class="form_container">  <!-- 3 -->
											<span class="warning_message"></span>
										</div>
										<p class="depth_p"></p>
										<p class="depth_p"></p>
	
										<div class="form_update_row">
											<div>
												<h3>강의 URL2</h3>
												<p class="depth_update"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_input_wrap">
												<% if(isAdd == true) { %>
												<input type="text" name="vid_url2" class="form_input" onfocus="ip2TitleTest(event)" />	<!-- 00 -->
												<% } else { %>
												<input type="text" name="vid_url2" class="form_input" onfocus="ip2TitleTest(event)" value="<%=lecture.getVid_url2()%>" />	<!-- 00 -->
												<% } %>
											</div>
										</div>
										<div class="form_container"> <!-- 4 -->
											<span class="warning_message"></span>
										</div>
										<p class="depth_p"></p>
										<p class="depth_p"></p>
									
										<div class="form_update_row">
											<div>
												<h3>소스 코드2</h3>
												<p class="depth_textarea"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_textfield_wrap">
												<% if(isAdd == true) { %>
												<textarea name="code2" onfocus="ip2Test(event)"></textarea>	<!-- 00 -->
												<% } else { %>
												<textarea name="code2" onfocus="ip2Test(event)"><%=lecture.getCode2()%></textarea>	<!-- 00 -->
												<% } %>
											</div>
										</div>
										<p class="depth_p"></p>
										<p class="depth_p"></p>
	
										<div class="form_update_row">
											<div>
												<h3>요약2</h3>
												<p class="depth_textarea"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_textfield_wrap">
												<% if(isAdd == true) { %>
												<textarea name="summary2" onfocus="ip2Test(event)"></textarea>	<!-- 00 -->
												<% } else { %>
												<textarea name="summary2" onfocus="ip2Test(event)"><%=lecture.getSummary2()%></textarea>	<!-- 00 -->
												<% } %>
											</div>
										</div>
										<p class="depth_p"></p>
										<p class="depth_p"></p>
	
										<% if(isAdd == true) { %>
										<div class="form_update_row">
											<div>
												<h3>참고 이미지2</h3>
												<p class="depth_update"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_input_wrap">
												<input type="file" name="img2" onClick="ip2Test(event)" onchange="img2Uploaded()" />	<!-- 00 -->
											</div>
										</div>
										<p class="depth_p"></p>
										<% } else { %>
										<div class="form_update_row">
											<div>
												<h3>참고 이미지2</h3>
												<p class="depth_update_deep"></p>
												<hr style="width:200px; border:1px solid #F8E494;">
											</div>
											<div class="form_input_wrap">
												<div class="update_img_file">
													<% if(lecture.getImg2() == null) { %>
													<img src="<%=request.getContextPath()%>/resources/images/gray_50.jpg" style="width:110px; height:110px;" />
													<% } else { 
														application.setAttribute("lectureImg2", lecture.getImg2());
													%>
													<img src="<%=request.getContextPath()%>/getRes/lecture/image2" style="width:110px; height:110px;" />
													<% } %>
													<input type="file" name="" />
												</div>	
											</div>
										</div>
										<p class="depth_p"></p>
										<% } %>
										
									</div>
									

									<div class="container" style="justify-content:flex-end;">
										<input type="button" value="Submit" class="btn btn_primary" style="width:100px; margin-right:5px;" onclick="prevTest()" />
										<input type="submit" value="Cancel" class="btn btn_secondary" style="width:100px;" />
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