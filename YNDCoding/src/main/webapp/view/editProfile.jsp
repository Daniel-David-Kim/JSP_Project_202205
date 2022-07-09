<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, section01.*, java.sql.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String memberDraw = (String)session.getAttribute("memberDraw");
	MemberBean memberInfo = null;
	String[] phones = null;
%>
<% if(memberDraw.equals("success")) {
	memberInfo = (MemberBean)session.getAttribute("memberInfo");
   } else {
%>
	<script>alert("멤버 정보 불러오기에 실패했습니다......");</script>
<% } %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Edit Profile</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/advisor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ajaxProcess.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<jsp:include page="leftWingAccount.jsp" />

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="width:710px; padding-left:160px; padding-right:120px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->



						<div class="container">
							<form class="form_update_profile" action="<%=request.getContextPath()%>/account/editProfile_process" method="post" encType="multipart/form-data">
								<div class="form_update_box">

									<input type="hidden" name="changedFields" value="" /> 
									<div class="form_update_row">
										<div>
											<h3>아이디</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(memberInfo != null) { %>
												<input type="text" name="sid" class="form_input" value="<%=memberInfo.getM_id()%>" readonly />
											<% } else { %>
												<input type="text" name="sid" class="form_input" readonly />
											<% } %>	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>이름</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(memberInfo != null) { %>
												<input type="text" name="sname" class="form_input" value="<%=memberInfo.getM_name()%>" />
											<% } else { %>
												<input type="text" name="sname" class="form_input" />
											<% } %>	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>현재 비밀번호</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<input type="password" name="sprevpw" class="form_input" />
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>새 비밀번호</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<input type="password" name="spw1" class="form_input" />	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>새 비밀번호 확인</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<input type="password" name="spw2" class="form_input" />	
										</div>	
									</div>
									<div class="form_container">
										<span class="warning_message"></span><!-- 0 : 패스워드 -->
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>비밀번호 찾기 질문</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(memberInfo != null) { %>
												<input type="text" name="squestion" class="form_input" value="<%=memberInfo.getM_findq()%>" />	
											<% } else { %>
												<input type="text" name="squestion" class="form_input" />	
											<% } %>	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>비밀번호 찾기 답</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<% if(memberInfo != null) { %>
												<input type="text" name="sanswer" class="form_input" value="<%=memberInfo.getM_finda()%>" />	
											<% } else { %>
												<input type="text" name="sanswer" class="form_input" />	
											<% } %>	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>연락처</h3>
											<p class="depth_update"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap update_cont">
											<% if(memberInfo != null) { 
												String phone = memberInfo.getM_contact();
												phones = phone.split("-");
												System.out.println("[" + phones[0] + "]");
												System.out.println("[" + phones[1] + "]");
												System.out.println("[" + phones[2] + "]");
											%>
												<input type="text" name="sphone1" class="form_input" value="<%=phones[0]%>" /><span class="slash">ㅡ</span><input type="text" name="sphone2" class="form_input" value="<%=phones[1]%>" /><span class="slash">ㅡ</span><input type="text" name="sphone3" class="form_input" value="<%=phones[2]%>" />
											<% } else { %>
												<input type="text" name="sphone1" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="sphone2" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="sphone3" class="form_input" />
											<% } %>	
											
										</div>
										
									</div>
									<div class="form_container">
										<span class="warning_message"></span><!-- 1 : 연락처 -->
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="form_update_row">
										<div>
											<h3>프로필 이미지</h3>
											<p class="depth_update_deep"></p>
											<hr style="width:200px; border:1px solid #F8E494;">
										</div>
										<div class="form_input_wrap">
											<div class="update_img_file">
												
												<% if(memberInfo != null) {
														Blob profile = memberInfo.getM_profile();
														if(profile == null) {
												%>
												<input type="hidden" name="profileUploaded" value="false" />
												<img src="<%=request.getContextPath()%>/resources/images/Profile2.png" name="profileShow" onclick="profileDelete()" style="width:110px; height:110px;" />
													 <% }else { // 프로필 사진이 있을 때
														 application.setAttribute("profile", profile);
														 %>
														 <input type="hidden" name="profileUploaded" value="true" />
														<img src="<%=request.getContextPath()%>/getRes/account/profile" name="profileShow" onclick="profileDelete()" style="width:110px; height:110px;" />
												<% 
														}
												   } else { %>
												<input type="hidden" name="profileUploaded" value="false" />
												<img src="<%=request.getContextPath()%>/resources/images/Profile2.png" name="profileShow" onclick="profileDelete()" style="width:110px; height:110px;" />
												<% } %>	
												<input type="hidden" name="profileChanged" value="false" />
												<input type="file" name="sprofile" onchange="profileUpload(this)" />
											</div>	
										</div>
									</div>
									<p class="depth_p"></p>
									<p class="depth_p"></p>

									<div class="container">
										<input type="button" value="Save" onclick="revisePrevCheck()" class="btn btn_primary" style="width:100px;" />
									</div>
									<script>
										function revisePrevCheck() {
											var pw_changed = false; // 패스워드가 바뀐 거면 true
											var prev = innerPrevCheck2();
											var contextPath = getProjectName();
											var sprevpw = document.getElementsByName("sprevpw")[0];
											<% String pw = "\'" + memberInfo.getM_pw() + "\'"; %>
											if(sprevpw.value != <%=pw%>) {
												alert("회원 변경을 위한 비밀번호가 틀렸습니다. 다시 입력해주세요.");
												return;
											}
											if(prev[0] == -1) {
												alert("모든 입력사항은 필수입니다.");
											} else {
												<%
													String id = "\'" + memberInfo.getM_id() + "\'";
													String name = "\'" + memberInfo.getM_name() + "\'";
													String findq = "\'" + memberInfo.getM_findq() + "\'";
													String finda = "\'" + memberInfo.getM_finda() + "\'";
													String contact = "\'" + memberInfo.getM_contact() + "\'";
												%>
												var profileChBool = document.getElementsByName("profileChanged")[0].value;
												var pw1 = document.getElementsByName("spw1")[0];
												var pw2 = document.getElementsByName("spw2")[0];
												if((prev[1][1].value==<%=id%>)&&(prev[1][0].value==<%=name%>)&&(((pw1.value == null)||(pw1.value == ""))&&((pw2.value == null)||(pw2.value == "")))&&(prev[1][2].value==<%=findq%>)&&(prev[1][3].value==<%=finda%>)&&(prev[1][4].value==<%="\'" + phones[0] + "\'"%>)&&(prev[1][5].value==<%="\'" + phones[1] + "\'"%>)&&(prev[1][6].value==<%="\'" + phones[2] + "\'"%>)&&(profileChBool=='false')) {
													alert("변경사항을 입력해주세요.");
													return;
												}
												if((pw1.value != null) && (pw1.value != "")||(pw2.value != null) && (pw2.value != "")) {
													if(pw1.value != pw2.value) {
														alert("새 비밀번호가 일치하지 않습니다.");
														return;
													} else pw_changed = true;
												}
												var pwChange = false; var contactChange = false;
												var fields = pw_changed == true? [sprevpw.value, prev[1][0].value, prev[1][2].value, prev[1][3].value, prev[1][4].value+"-"+prev[1][5].value+"-"+prev[1][6].value] : [prev[1][0].value, prev[1][2].value, prev[1][3].value, prev[1][4].value+"-"+prev[1][5].value+"-"+prev[1][6].value];
												var values = pw_changed == true? [pw1.value, <%=name%>, <%=findq%>, <%=finda%>, <%=contact%>] : [<%=name%>, <%=findq%>, <%=finda%>, <%=contact%>];
												var labels = pw_changed == true? ["m_pw", "m_name", "m_findq", "m_finda", "m_contact"] : ["m_name", "m_findq", "m_finda", "m_contact"];
												var collection = "";
												
												
												for(var i = 0; i < fields.length; i++) {
													//alert("field : " + fields[i] + " / value : " + values[i]);
													if(fields[i] != values[i]) {
														collection += labels[i] + ",";
														if(labels[i] == "m_pw") pwChange = true;
														if(labels[i] == "m_contact") contactChange = true;
													}	
												}
												var changedFields = document.getElementsByName("changedFields")[0];
												changedFields.value = collection;
												//alert("changed vlaues : " + changedFields.value);
													
												var res = ajaxPrevCheck2(pwChange, contactChange);
												if(res == 1) {
													var form = document.getElementsByClassName("form_update_profile")[0];
													if(form != null) form.submit();
												}
											}
										}
									</script>
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