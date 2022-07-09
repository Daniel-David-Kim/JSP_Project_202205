<%@ page language="java" pageEncoding="utf-8" import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	HashMap<String, Object> authorInfo = (HashMap<String, Object>)request.getAttribute("authorInfo"); 
%>
<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
					<ul class="lectures_container">
						<li class="lecture_list lecture_label">강의자 게시판</li>		
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
					<hr width="288" style="height:1px; background-color:#BCBEC0;">
				</div>