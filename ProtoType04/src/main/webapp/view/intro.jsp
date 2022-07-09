<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" 
	import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String resultCode = (String)application.getAttribute("resultCode");
	String loginResult = (String)session.getAttribute("loginResult");
	String menuResult = null;
	if(application.getAttribute("menuResult") != null) menuResult = (String)application.getAttribute("menuResult");
	
	HashMap<String, Object> comments = (HashMap<String, Object>)request.getAttribute("comments");
	String logined = null;
	String uid = null;
	int uclass = 2;
	if(session.getAttribute("logined") != null) {
		logined = (String)session.getAttribute("logined");
		if(logined.equals("true")) {
			uid = (String)session.getAttribute("uid");
			uclass = (int)session.getAttribute("uclass");
		}
	}
	
	String signupResult = null;
	if(application.getAttribute("signupResult") != null) signupResult = (String)application.getAttribute("signupResult");
	
	String commentResult = null;
	if(application.getAttribute("commentResult") != null) commentResult = (String)application.getAttribute("commentResult");
	
	String commentInsertResult = null;
	if(application.getAttribute("commentInsertResult") != null) commentInsertResult = (String)application.getAttribute("commentInsertResult");
	
%>
<% if(loginResult != null && !loginResult.equals("")) { 
		boolean loginResultBool = Boolean.parseBoolean(loginResult);
		if(loginResultBool == true) {
			String uname = (String)session.getAttribute("uname");
%>
		<script>alert("<%=uname%>님! 환영합니다!");</script>	
	 <% } else { 
	 	String errorMsg = (String)session.getAttribute("errorLogin");
	 %>
	 	<script>alert('<%=errorMsg%>');</script>
<%      }
		session.removeAttribute("loginResult");
   } 
%>
<% if(menuResult != null && !menuResult.equals("")) {
		if(menuResult.equals("success")) {%>
			<script>alert("과목 변경에 성공했습니다!");</script>
		<%} else {%>
			<script>alert("과목 변경에 실패했습니다.....");</script>
		<% }
		application.removeAttribute("menuResult");
	}%>
<% if(commentResult != null && !commentResult.equals("")) {
		if(commentResult.equals("success")) {%>
			<script>alert("댓글 변경에 성공했습니다!");</script>
		<%} else {%>
			<script>alert("댓글 변경에 실패했습니다.....");</script>
		<% }
		application.removeAttribute("commentResult");
	}%>
<% if(commentInsertResult != null && !commentInsertResult.equals("")) {
		if(commentInsertResult.equals("success")) {%>
			<script>alert("댓글 삽입에 성공했습니다!");</script>
		<%} else {%>
			<script>alert("댓글 삽입에 실패했습니다.....");</script>
		<% }
		application.removeAttribute("commentInsertResult");
	}%>
<% if(signupResult != null && !signupResult.equals("")) {
		if(signupResult.equals("success")) {%>
			<script>alert("회원 가입에 성공했습니다!");</script>
		<%} else {%>
			<script>alert("회원 가입에 실패했습니다.....");</script>
		<% }
		application.removeAttribute("signupResult");
	}%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Intro</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="navtree.jsp" />
	
	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/team1.png" />
				</div>
				<p class="depth_p"></p>
				<p class="phrase">
					야!너두 코딩에 오신 것을 환영합니다. 저희 야!너두 코딩은 <strong>일반인들에게 프로그래밍을 알려주는 무료 온라인 수업입니다. </strong>&nbsp;
					어떻게 공부할 것인가를 생각해보기 전에 왜&nbsp;프로그래밍을 공부하는 이유에 대한 이유를 함께 생각해보면 좋을 것 같습니다. 아래 영상을 한번 보시죠.
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<iframe width="700" height="455" src="https://www.youtube.com/embed/ldULT0GDqMI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
					</iframe>
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">온라인 강의 소개</h2>
				<p class="phrase">
					입문자의 가장 큰 고충은 '무엇을 모르는지 모르는 상태'일 겁니다. 온라인에는 프로그래밍을 익히는 데 필요한 거의 모든 정보가 있지만, 이 지식들은 게시판이나 블로그 또는 커뮤니티에 포스팅 단위로 파편화되어 있습니다. 그래서 최소한 무엇을 검색해야 하는지를 아는 사람들을 위해서는 더 없이 좋은 공간이지만, '무엇을 모르는지 모르는 상태'의 입문자에게는 그림의 떡으로 남아 있습니다. 저희 야!너두 코딩은 초심자 입문자 여러분들에게 시작할 때 배우기 쉽도록 단계별 커리큘럼과 간단한 언어부터 시작하여 가르쳐드립니다.
				</p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/call1.png" />
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">수업소개</h2>
				<p class="phrase">
					야!너두 코딩의 수업은 기초언어인 C, JAVA, Python 을 시작으로 서버언어인 JSP, Spring, Node.js가 있으며 데이터베이스로 오라클과 MYSQL을 가르치고 있습니다. 프로그래밍이 처음인 분들을 위해서 가장 기초적인 언어를 가르쳐드리기 위해 고안되었습니다.
				</p>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">야나두코딩 Youtube</h2>
				<p class="phrase">
					야!너두 코딩의 모든 동영상 강의는 Youtube를 통해서 서비스 되고 있습니다. Youtube 채널을 구독하시면 야!너두 코딩의 새로운 동영상을 받아보실 수 있습니다.<br><br>
					<a href="http://www.youtube.com/user/egoing2">http://www.youtube.com/user/egoing2</a>
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/youtube.png" width="250" height="250" />
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">질문</h2>
				<p class="phrase">
					각각의 수업의 하단에는 댓글이 있습니다. 이 댓글을 통해서 질문을 받습니다. 댓글을 달면 운영자에게 이메일이 발송되기 때문에 질문은 모두 운영자에게 열람이 됩니다. 하지만 많은 양의 질문을 받기 때문에 운영자 입장에서는 큰 부담이 되는 것도 사실입니다. 운영자가 답장을 하지 않는 것은 운영자도 잘 모르는 문제이거나 지금은 답변하기 어려운 것일 수 있습니다. 꼭 운영자를 통해서 문제를 해결해야 하는 것은 아니기 때문에 우선은 검색이나 커뮤니티에 질문하는 것을 통해서 문제를 해결하셨으면 좋겠습니다. 물론 운영자에게 질문하시는 것을 주저하실 필요는 없습니다. 답변할 수 있는 것은 최대한 신속하게 도움을 드립니다. 그리고 질문은 최대한 상세하게 해주세요.
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/que.png" width="250" height="250" />
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<h2 class="titleh2Default">라이선스</h2>
				<p class="phrase">
					야!너두 코딩은 오픈소스를 지지합니다. 그 연장 선상에서 야!너두 코딩의 모든 컨텐츠는 오픈된 컨텐츠 라이선스인 CCL를 따릅니다. 이 말은 야!너두 코딩의 컨텐츠를 이용해서 영리활동을 하셔도 되고, 블로그나 홈페이지에 담아가셔도 됩니다. 또한 야!너두 코딩을 사용하는 컨텐츠가 CCL 라이선스를 따라야 하는 것도 아닙니다. 다만, 영리를 목적으로 하는 경우에는 영리 활동이 야!너두 코딩과의 제휴관계가 아니라 CCL 라이선스에 따른 사용관계라는 것을 사용자가 충분히 인지 가능한 형태로 명시해주셔야 합니다. 야!너두 코딩의 라이선스 규정은 Creative Commons 저작자 표시 2.0 문서를 참고해주세요.
				</p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/door.png" width="250" height="250" />
				</div>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<p class="depth_p"></p>
				<div class="container">
					<img src="<%=request.getContextPath()%>/resources/images/logo2.png" width=398 height=303 />
				</div>
				<p class="depth_p"></p>


			</article>
			<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-left:220px; margin-right:220px;">
			
			
			
			<!-- 댓글 영역 -->
					<%
						int numOfRows = (int)comments.get("numOfRows");
						Vector<CommentBean> cs = (Vector<CommentBean>)comments.get("comments");
					%>
					<div class="comments"><!--div.comments : 댓글 작성 폼과 댓글 목록들을 포함하는 컨테이너입니다.-->
						<!--div.comments : 인트로 페이지의 그 div.comments 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->
						<form class="form_comment" action="<%=request.getContextPath()%>/centerComment/enroll" method="post" encType="utf-8">
							<input type="hidden" name="revise" id="hdip" />
							<% if(logined != null && logined.equals("true")) { 
									if(session.getAttribute("uprofile") != null) {
										application.setAttribute("profileAuthor", (Blob)session.getAttribute("uprofile"));%>
										<img src="<%=request.getContextPath()%>/getRes/lecture/profileAuthor" width="50" height="50" />
									<%} else {%>
										<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width="50" height="50" />
								   <% } %>
							<% } else { %>
								<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width="50" height="50" />
							<% } %>
							<% if(logined != null && logined.equals("true")) { %>
								<textarea name="content" id="commentEnrollContent" cols=102 rows=7></textarea>
							<% } else { %>
								<textarea name="content" id="commentEnrollContent" cols=102 rows=7 disabled></textarea>
							<% } %>
						</form>
						<div style="display:flex; justify-content:flex-end; margin-bottom:30px;">
							<% if(logined != null && logined.equals("true")) { %>
							<div class="btn-menus" onclick="enrollComment()"><h5>등록</h5></div>
							<% } %>
						</div>
						<hr style="border:1px solid #f1f1f2; width: 95%; background-color:#f1f1f2; margin-left:40px; margin-right:90px;"><!--hr:밑줄 라인-->
						
						<div class="comment_list"><!--div.comment_list :댓글들이 여기 부분으로 쭉 나열됩니다.-->
							<% if(numOfRows > 0) {
									for(int i =0; i < cs.size(); i++) { 
										CommentBean comment = cs.get(i);%>
										<!-- 이렇게 하나의 댓글 -->
										<div class="container">
											<div style="width:100%; display:flex; align-items:flex-start;">
												<% if(comment.getM_profile() == null) { %>
													<img src="<%=request.getContextPath()%>/resources/images/profile1.png" width="50" height="50" /><!-- 프로필 사진 -->
												<% } else {
													application.setAttribute("profile" + i, comment.getM_profile());
													%>
													<img src="<%=request.getContextPath()%>/getRes/comment/profile?num=<%=i%>" width="50" height="50" /><!-- 프로필 사진 -->
												<% } %>
												<div style="margin-left:30px;">
													<div style="display:flex; justify-content:space-between;">
														<div style="display:flex;">
															<h5><%=comment.getM_name()%></h5><!-- 닉넴 -->
															<span style="padding-left:20px;"></span>
															<h5><%=comment.getWriteDate()%></h5><br><!-- 날짜 -->
														</div>
														<div style="display:flex;">
															<div class="btn-menus" onclick="commentRevise<%=i%>()"><h5>수정</h5></div>
															<span style="padding-left:20px;"></span>
															<div class="btn-menus" onclick="commentDeleteIndex(<%=comment.getC_num()%>)"><h5>삭제</h5></div>
														</div>
													</div>
													<pre class="dynamic_phrase" style="width:750px; margin-top:0px;"><%=comment.getContent()%></pre><!-- 본문 -->
												</div>
											</div>
											<script>
												function commentRevise<%=i%>() {
													var ip = prompt("변경할 내용을 입력해주세요.", "<%=comment.getContent()%>");
													if(ip == "<%=comment.getContent()%>") {
														alert("변경사항을 입력해주세요.");
													} else if(ip != "" && ip != null) {
														var form = document.getElementsByClassName("form_comment")[0];
														var hidden = document.getElementById("hdip");
														hidden.value = ip;
														form.action = "<%=request.getContextPath()%>" + "/centerComment/revise?c_num=<%=comment.getC_num()%>";
														form.submit();
													}
												}
											</script>
										</div>
										<!-- 이렇게 하나의 댓글 -->
							<% 		
									}
								} %>	
						</div><!--div.comment_list-->
					</div><!--div.comments-->
					<!-- 댓글 영역 -->
			
			
			
		</div>
	</div>
	<%
		if(resultCode != null) {
			if(resultCode.equals("error")) {%>
				<script>
					alert('<%=application.getAttribute("error_msg")%>');
				</script>	
	<% 			application.removeAttribute("error_msg");
				application.removeAttribute("resultCode");
			}
		}
	%>
</body>
</html>