<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	Object loginedObj = session.getAttribute("logined");
	String logined = null;
	if(loginedObj != null) {
		logined = (String)loginedObj;
	}
	HashMap<String, Object> authorInfo = (HashMap<String, Object>)request.getAttribute("authorInfo");
	application.setAttribute("author_id", (String)authorInfo.get("id"));
	HashMap<String, Object> articles = (HashMap<String, Object>)request.getAttribute("articles");
	int allArticles = (int)articles.get("AllArticles");
	int currentPage = (int)articles.get("currentPage");
	int startNo = ((currentPage-1)*10) + 1; // 이 페이지를 시작할 게시글 번호
	
	int startPage = (int)((currentPage-1)/ 10.0) * 10 + 1;
	int currentResultRows = (int)articles.get("resultRows");
	int totalPages = (int)(allArticles/10.0) + 1;
	Vector<BoardBean> articleList = (Vector<BoardBean>)articles.get("articles");
	
	String boardResult = "";
	if(application.getAttribute("boardResult") != null) {
		boardResult = (String)application.getAttribute("boardResult");
		if(boardResult.equals("success")) {%>
			<script>alert("변경에 성공했습니다!");</script>	
		<%} else { %>
			<script>alert("변경에 실패했습니다.....");</script>
		<%}
		application.removeAttribute("boardResult");
	}
	
%>
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

				<jsp:include page="leftWingLecturer.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="width:810px; padding-left:110px; padding-right:90px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
						
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<table class="table">
								<tr class="tablerow headrow"><th class="numcol">번호</th><th class="titlecol">제목</th><th class="authorcol">작성자</th><th class="datecol">작성일</th></tr>
								
								<!-- 게시글이 하나도 없을 때 -->
								<% if(articleList.size() == 0) { %>
									<tr class="tablerow rowdip"><td class="numcol" colspan="4">게시글이 없습니다.</td></tr>
								<% } %>
								
								<% for(int i = 0; i < articleList.size(); i++) { 
										BoardBean article = articleList.get(i);
										if(i % 2 == 0) {
								%>
									<tr class="tablerow rowdip"><td class="numcol"><%=startNo + i%></td><td class="titlecol titlecolrows"><a href="<%=request.getContextPath()%>/board/lectureBoard/question?q_id=<%=article.getQ_num()%>"
									 <% if(article.getM_id() == null || article.getM_id().equals("")) { %> style="color:gray;"<% } %>>
										<% for(int j = 0; j < article.getDepth()-1; j++) { %><span style="padding-right:20px;"></span><% } %>
										<% if(article.getDepth() > 1&&(article.getM_id() != null && !article.getM_id().equals(""))) { %><span class="reply_board">[답글]</span><% } %>
										<%=article.getTitle()%></a></td><td class="authorcol bauthor"><%=article.getM_id()%></td><td class="datecol"><%=article.getWriteDate()%></td></tr>
								<% 		} else { %>
									<tr class="tablerow rowwhite"><td class="numcol"><%=startNo + i%></td><td class="titlecol titlecolrows"><a href="<%=request.getContextPath()%>/board/lectureBoard/question?q_id=<%=article.getQ_num()%>"
									 <% if(article.getM_id() == null || article.getM_id().equals("")) { %> style="color:gray;"<% } %>>
										<% for(int j = 0; j < article.getDepth()-1; j++) { %><span style="padding-right:20px;"></span><% } %>
										<% if(article.getDepth() > 1&&(article.getM_id() != null && !article.getM_id().equals(""))) { %><span class="reply_board">[답글]</span><% } %>
										<%=article.getTitle()%></a></td><td class="authorcol bauthor"><%=article.getM_id()%></td><td class="datecol"><%=article.getWriteDate()%></td></tr>
								<% 		}
								   }
								%>
							</table>
						</div>	
						<div class="container">
							<div class="board_add_btn_container">
								<% if(logined != null && logined.equals("true")) { %>
								<button class="btn btn_primary" style="width:80px;" onclick="addQuestion()">Write</button>
								<% } %>
								<script>
									function addQuestion() {
										location.href = "<%=request.getContextPath()%>/board/lectureBoard/newQuestion";
									}
								</script>
							</div>
						</div>
						<div class="container">
							<div class="table_pages">
								<ul class="page_list">
									<% if(currentPage - 10 > 0) { %>
										<li><a href="<%=request.getContextPath()%>/board/lectureBoard/page/<%=startPage%>/<%=((startPage-1)*10) + 1%>/prev" class="page_l">이전</a></li>
									<% }
									   for(int i = 0; i < 10; i++) { 
									   		if((startPage + i) <= (totalPages)) {
									   			if((startPage + i) == currentPage) {
									   %>
											<li><a href="<%=request.getContextPath()%>/board/lectureBoard/page/<%=startPage + i%>/<%=((startPage-1 + i)*10) + 1%>" class="page_l curPage"><%=startPage + i%></a></li>
											<% } else { %>
											<li><a href="<%=request.getContextPath()%>/board/lectureBoard/page/<%=startPage + i%>/<%=((startPage-1 + i)*10) + 1%>" class="page_l"><%=startPage + i%></a></li>
									<%			 
											   }
									   		}
									   } %>
									<% if(totalPages > 10 && (currentPage < totalPages)) { %>
										<li><a href="<%=request.getContextPath()%>/board/lectureBoard/page/<%=startPage + 9%>/<%=((startPage-1 + 9)*10) + 1%>/next">다음</a></li>
									<% } %>
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