<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" 
	import="java.util.*, java.sql.*, section01.*" %>
<%
	request.setCharacterEncoding("utf-8");
	Object loginedObj = session.getAttribute("logined");
	String logined = null;
	if(loginedObj != null) {
		logined = (String)loginedObj;
	}
	HashMap<String, Object> authorInfo = (HashMap<String, Object>)request.getAttribute("authorInfo");
	application.setAttribute("author_id", (String)authorInfo.get("id"));
	String questionDrawStr = (String)application.getAttribute("questionDraw");
	BoardBean article = null;
%>
<%
	if(questionDrawStr != null && !questionDrawStr.equals("")) {
		boolean questionDraw = Boolean.parseBoolean(questionDrawStr);
		if(questionDraw == true) {
			article = (BoardBean)request.getAttribute("article");
			application.removeAttribute("questionDraw");
			System.out.println("바인딩 해제 : questionDraw");
		}
	} %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Question</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/location.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			
			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<jsp:include page="leftWingLecturer.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="padding-left:40px; padding-right:130px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
					
						<p class="depth_p"></p><!--p.depth_p : 문단 사이의 간격을 정해주기 위한 p클래스입니다.-->
						<h1 class="lecture_title"><%=article.getTitle()%></h1>
						<span class="lecture_date_shower"><%=article.getWriteDate()%></span>
						<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-bottom:10px;">
						<div class="board_question_id">
							<h3><%=article.getM_id()%></h3>
						</div>

						<pre class="dynamic_phrase"><%=article.getContent()%></pre>
						<p class="depth_p"></p>
						<p class="depth_p"></p>

					</article><!--article.article_main-->

					<script>
						function replyQue() {
							location.href = "<%=request.getContextPath()%>/board/lectureBoard/reply?q_num=<%=article.getQ_num()%>";
						}
						function updateQue() {
							location.href = "<%=request.getContextPath()%>/board/lectureBoard/update?q_num=<%=article.getQ_num()%>";
						}
						function deleteQue() {
							var yon = confirm("정말 삭제하시겠습니까? 한 번 삭제하면 다시 되돌릴 수 없습니다.");
							if(yon == true) location.href = "<%=request.getContextPath()%>/board/lectureBoard/deleteQ?q_num=<%=article.getQ_num()%>";
						}
					</script>
					<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-left:40px; margin-right:130px;"><!--hr:밑줄 라인-->
					<!--hr : 인트로 페이지의 그 hr 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->
					<div class="container" style="display:flex; justify-content:flex-end; margin-left:40px; margin-right:130px;">
						<% if((logined != null && logined.equals("true"))&&(article.getM_id() != null && !article.getM_id().equals(""))) { 
								String thisUser = (String)session.getAttribute("uid");
								if(article.getM_id().equals(thisUser)) {%>
									<button class="btn btn_primary" onclick="updateQue()" style="width:80px; margin-left:7px;">Update</button>
									<button class="btn btn_primary" onclick="deleteQue()" style="width:80px; margin-left:7px;">Delete</button>
								<% } %>
								<button class="btn btn_primary" onclick="replyQue()" style="width:80px; margin-left:7px;">Reply</button>
						<% } %>
						<button class="btn btn_secondary" onclick="goMenu()" style="width:80px; margin-left:7px;">Menu</button>
					</div>
					<p class="depth_p boardArt"></p>
					

				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->


			
		</div><!--div.article_container-->
	</div><!--div.whole-->

</body>
</html>