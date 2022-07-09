<%@ page language="java" pageEncoding="utf-8" import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	Object lectures = request.getAttribute("lectureResult");
	HashMap<String, Object> lectureResult = null;
	if(lectures != null) lectureResult = (HashMap<String, Object>)request.getAttribute("lectureResult");
	String add = (String)request.getAttribute("add");
	HashMap<String, Object> authorInfo = (HashMap<String, Object>)request.getAttribute("authorInfo");
	
	int lectureNum = -1;
	if(request.getAttribute("lectureNum") != null) lectureNum = (int)request.getAttribute("lectureNum");
	
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
%>
<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
	<ul class="lectures_container">
		<%
			if(lectures != null) {
				for(LectureBean lecture:(Vector<LectureBean>)lectureResult.get("all")) {
		%>
				<li class="lecture_list"><a href="<%=request.getContextPath()%>/lecture/<%=(int)request.getAttribute("categoryCode")%>/<%=lecture.getScat_num()%>" class="lecture_link"><%=lecture.getTitle()%></a></li>
		<% 
				}
			}
		%>
		
		<!-- 강의자 본인이거나 관리자만 보이게 -->
		<% if(logined != null && logined.equals("true")) { 
				if(((String)authorInfo.get("id")).equals(uid)||uclass == 0) {%>
				
		<li class="lecture_list"><a href="<%=request.getContextPath()%>/lecture/<%=(int)request.getAttribute("categoryCode")%>/0/addLecture" class="lecture_link">강의 추가</a></li>
		
		<% 		}
			} %>
		<!-- 강의자 본인이거나 관리자만 보이게 -->
			
	</ul>
	
	<hr width="288" style="height:1px; background-color:#BCBEC0; margin-top:9px;"><!--hr:밑줄 라인-->
	
	<div class="lecturer"><!-- div.lecturer : 강의자의 프로필을 보여준다. -->
		<% if(authorInfo.get("profile") == null) { %>
			<img src="<%=request.getContextPath()%>/resources/images/profile1.png" class="lecturer_profile" width=57 height=57 />
		<% } else { 
			Blob profile = (Blob)authorInfo.get("profile");
			application.setAttribute("profileAuthor", profile);
		%>
			<img src="<%=request.getContextPath()%>/getRes/lecture/profileAuthor" class="lecturer_profile" width=57 height=57 />
		<% } %>
		<div class="lecturer_info">
			<div class="lecturer_class">강의자</div>
			<div class="lecturer_name"><%=(String)authorInfo.get("name")%></div>
		</div>
	</div><!-- div.lecturer -->
	
	<hr width="288" style="height:1px; background-color:#BCBEC0;"><!--hr:밑줄 라인-->
	<% 
		if(add == null) { 
	%>
	<div id="lecturer_link"><!-- div#lecturer_link : 강의자로 로그인하면 활성화되는 메뉴 : 현재는 display:none으로 되어있지만, display:flex로 하면 다시 나타난다! -->
		
		<!-- 강의자 본인이거나 관리자만 보이게 -->
		<% if(logined != null && logined.equals("true")) { 
				if(((String)authorInfo.get("id")).equals(uid)||uclass == 0) {
					if(lectureNum != -1) { %>
				
		<a href="<%=request.getContextPath()%>/lecture/<%=(int)request.getAttribute("categoryCode")%>/0/reviseLecture">강의 수정</a>
		
		<a href="#" onclick="youSure()">강의 삭제</a>
		<script>
			function youSure() {
				var yesOrno = confirm("한 번 삭제하면 되돌릴 수 없습니다. 계속하시겠습니까?");
				if(yesOrno == true) {
					var form = document.getElementById("hidden");
					form.action ="<%=request.getContextPath()%>/lecture/<%=(int)request.getAttribute("categoryCode")%>/0/deleteLecture";
					form.submit();
				}
			}
		</script>
		
		<% 		   }
				}
			} %>
		<!-- 강의자 본인이거나 관리자만 보이게 -->
		
		
	</div><!-- div#lecturer_link -->
	<% } %>
	<div id="user_link"><!-- div#user_link : 사용자로 로그인하면 활성화되는 메뉴 : display:none으로 하면 사라지고, display:flex로 하면 다시 나타난다. -->
		<% if((int)authorInfo.get("mclass") < 2) { %>
		<a href="<%=request.getContextPath()%>/board/lectureBoard?author_id=<%=(String)authorInfo.get("id")%>">강의자 Q&A</a>
		<% } %>
	</div><!-- div#user_link -->
</div><!--div.left_wing -->