<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
    import="java.util.*, section01.*" %>
<%
	request.setCharacterEncoding("utf-8");
	HashMap<String, Object> lectureResult = (HashMap<String, Object>)request.getAttribute("lectureResult");
	String lectureAddStr = (String)application.getAttribute("lectureAdd");
	String lectureReviseStr = (String)application.getAttribute("lectureRevise");
%>
<% 
	if(lectureAddStr != null && !lectureAddStr.equals("")) {
		boolean lectureAdd = Boolean.parseBoolean(lectureAddStr);
		if(lectureAdd == true) {
%> 			<script>alert("강의 추가에 성공했습니다.");</script>
	 <% } else { %> <script>alert("강의 추가에 실패했습니다.");</script>
<%  
	    }
	} 
    application.removeAttribute("lectureAdd");
%>
<% 
	if(lectureReviseStr != null && !lectureReviseStr.equals("")) {
		boolean lectureRevise = Boolean.parseBoolean(lectureReviseStr);
		if(lectureRevise == true) {
%> 			<script>alert("강의 수정에 성공했습니다.");</script>
	 <% } else { %> <script>alert("강의 수정에 실패했습니다.");</script>
<%  
	    }
	} 
    application.removeAttribute("lectureRevise");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lecture</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="navtree.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->
				<jsp:include page="leftWing.jsp" />
				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="padding-left:40px; padding-right:130px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
					
						<p class="depth_p"></p><!--p.depth_p : 문단 사이의 간격을 정해주기 위한 p클래스입니다.-->
						<% 
							LectureBean lecture = (LectureBean)lectureResult.get("one");
							application.setAttribute("lectureNum", lecture.getScat_num());
						%>
									
						<form id="hidden">
							<input type="hidden" name="lecture_num" value="<%=lecture.getScat_num()%>" />
						</form>
						
						<h1 class="lecture_title"><%=lecture.getTitle()%></h1>
						<span class="lecture_date_shower"><%=lecture.getWriteDate()%></span>
						<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-bottom:56px;">

						<% if(lecture.getContent() != null && !lecture.getContent().equals("")) { %>
						<h2 class="titleh2Default">수업소개</h2><!--h2.titleh2Default : 인트로 페이지의 각 굵직한 소제목들, 강의 페이지에서 항목들을 분류하는 소제목으로 쓰입니다.-->
						<pre class="dynamic_phrase">
							<%=lecture.getContent().trim()%>
						</pre>
						<p class="depth_p"></p>
						<p class="depth_p"></p>
						<% } %>

						<h2 class="titleh2Default"><%=lecture.getVid_title()%></h2>
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<!--iframe : 퍼오는 동영상이 올라오는 엘리먼트입니다. 유튜브에서 주어진 형식에 width, height만 바꾸어서 현재 모습입니다. 다른 영상을 올리려면 src 부분만 변경하면 됩니다.-->
							<iframe width="700" height="455" src="<%=lecture.getVid_url()%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
							</iframe>
						</div>
						<p class="depth_p"></p>
						<p class="depth_p"></p>
						
						<% if(lecture.getImg() != null) { 
								application.setAttribute("lectureImg", lecture.getImg());
						%>
							<h2 class="titleh2Default">참고 이미지</h2>
							<div class="container">
								<img src="<%=request.getContextPath()%>/getRes/lecture/image" width="590" height="350" />
							</div>
							<p class="depth_p"></p>
							<p class="depth_p"></p>
						<% } %>
						

						<% if(lecture.getCode() != null && !lecture.getCode().trim().equals("")) { %>
							<h2 class="titleh2Default">소스코드</h2>
							<pre class="source_code"><!--pre.source_code : 소스 코드를 넣는 부분입니다.-->
								<%=lecture.getCode()%>
							</pre><!--pre.source_code-->
							<p class="depth_p"></p>
							<p class="depth_p"></p>
						<% } %>
						
						
						<% if(lecture.getSummary() != null && !lecture.getSummary().trim().equals("")) { %>
							<h2 class="titleh2Default">요약</h2>
							<pre class="dynamic_phrase"><!--pre.dynamic_phrase : 본문의 영역 안에서 입력된 본문 그대로 출력해주는 단락 엘리먼트입니다. 내용이 범위를 넘을 경우 자동으로 줄바꿈 해줍니다.-->
								<%=lecture.getSummary()%>
							</pre>
							<p class="depth_p"></p><!--pre.dynamic_phrase-->
							<p class="depth_p"></p>
						<% } %>
						
						<% if((lecture.getVid_title2() != null && !lecture.getVid_title2().trim().equals("")) && (lecture.getVid_url2() != null && !lecture.getVid_url2().trim().equals(""))) { %>
						<h2 class="titleh2Default"><%=lecture.getVid_title2()%></h2>
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<!--iframe : 퍼오는 동영상이 올라오는 엘리먼트입니다. 유튜브에서 주어진 형식에 width, height만 바꾸어서 현재 모습입니다. 다른 영상을 올리려면 src 부분만 변경하면 됩니다.-->
							<iframe width="700" height="455" src="<%=lecture.getVid_url2()%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
							</iframe>
						</div>
						<p class="depth_p"></p>
						<p class="depth_p"></p>
						<% } %>
						
						<% if(lecture.getImg2() != null) { 
								application.setAttribute("lectureImg2", lecture.getImg2());
						%>
							<h2 class="titleh2Default">참고 이미지</h2>
							<div class="container">
								<img src="<%=request.getContextPath()%>/getRes/lecture/image2" width="590" height="350" />
							</div>
							<p class="depth_p"></p>
							<p class="depth_p"></p>
						<% } %>
						

						<% if(lecture.getCode2() != null && !lecture.getCode2().trim().equals("")) { %>
							<h2 class="titleh2Default">소스코드</h2>
							<pre class="source_code"><!--pre.source_code : 소스 코드를 넣는 부분입니다.-->
								<%=lecture.getCode2()%>
							</pre><!--pre.source_code-->
							<p class="depth_p"></p>
							<p class="depth_p"></p>
						<% } %>
						
						
						<% if(lecture.getSummary2() != null && !lecture.getSummary2().trim().equals("")) { %>
							<h2 class="titleh2Default">요약</h2>
							<pre class="dynamic_phrase"><!--pre.dynamic_phrase : 본문의 영역 안에서 입력된 본문 그대로 출력해주는 단락 엘리먼트입니다. 내용이 범위를 넘을 경우 자동으로 줄바꿈 해줍니다.-->
								<%=lecture.getSummary2()%>
							</pre>
							<p class="depth_p"></p><!--pre.dynamic_phrase-->
						<% } %>
														

					</article><!--article.article_main-->

					<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-left:40px; margin-right:130px;"><!--hr:밑줄 라인-->
					<!--hr : 인트로 페이지의 그 hr 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->

					<div class="comments" style="margin-left:40px; margin-right:130px;"><!--div.comments : 댓글 작성 폼과 댓글 목록들을 포함하는 컨테이너입니다.-->
						<!--div.comments : 인트로 페이지의 그 div.comments 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->
						<form class="form_comment">
							<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width="50" height="50" />
							<textarea cols=102 rows=7></textarea>
						</form>
						<div class="comment_list"><!--div.comment_list :댓글들이 여기 부분으로 쭉 나열됩니다.-->
							
						</div><!--div.comment_list-->
					</div><!--div.comments-->

				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->
	
		</div><!--div.article_container-->
	</div><!--div.whole-->
</body>
</html>