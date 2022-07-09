<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Enroll Member!</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/location.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ajaxProcess.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<form class="form_template" method="post" action="<%=request.getContextPath()%>/account/enroll_process">
					<p class="depth_p"></p>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Name</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sname" class="form_input" />
					</div>
					<p class="depth_p"></p>
					
					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Id</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sid" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span><!-- 0 : 아이디 -->
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Password</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="password" name="spw1" class="form_input" />
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Password (check)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="password" name="spw2" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span><!-- 1 : 패스워드 -->
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Question (find Password)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="squestion" class="form_input" />
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Answer (find Password)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sanswer" class="form_input" />
					</div>
					<p class="depth_p"></p>
					
					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Class</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sclass" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span><!-- 2 : 클래스 -->
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Contact</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sphone1" class="form_input" placeholder="010 / 011" /><span class="slash">ㅡ</span><input type="text" name="sphone2" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="sphone3" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span><!-- 3 : 휴대폰번호 -->
					</div>
					<p class="depth_p"></p>

					<div class="form_container">
						<input type="button" value="Enroll" class="btn btn_primary" onclick="ajaxPrevCheck()" style="width:100px;" />
					</div>
					<p class="depth_p"></p>
					<p class="depth_p"></p>
				</form>
				
			</article>
		</div>
	</div>
</body>
</html>