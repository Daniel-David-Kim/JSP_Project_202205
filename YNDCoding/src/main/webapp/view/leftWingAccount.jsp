<%@ page language="java" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uid = (String)session.getAttribute("uid");
	int uclass = (int)session.getAttribute("uclass"); 
%>
<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
	<ul class="lectures_container">
		<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/accountInfo" class="lecture_link">회원 정보</a></li>
		<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/editProfile" class="lecture_link">회원 정보 수정</a></li>
		<% if(uclass < 2) { %>
		<li class="lecture_list"><a href="<%=request.getContextPath()%>/board/lectureBoard?author_id=<%=uid%>" class="lecture_link">강의자 게시판</a></li>
		<% } 
		   if(uclass < 1) { %>
		<li class="lecture_list"><a href="<%=request.getContextPath()%>/account/adminBoard" class="lecture_link">회원 관리</a></li>
		<% } %>	
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