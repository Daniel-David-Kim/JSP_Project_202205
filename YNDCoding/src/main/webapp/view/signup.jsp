<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/ajaxProcess3.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<form class="form_template" method="post" action="<%=request.getContextPath()%>/account/signup_process">
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
						<span class="warning_message"></span><!-- 0 -->
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
						<span class="warning_message"></span><!-- 1 -->
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
						<h2 class="form_label">Contact</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="sphone1" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="sphone2" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="sphone3" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span><!-- 2 -->
					</div>
					<p class="depth_p"></p>

					<div class="form_container">
						<input type="button" value="Sign up" class="btn btn_primary" onclick="ajaxPrevCheck3()" style="width:100px;" />
					</div>
					<p class="depth_p"></p>
					<p class="depth_p"></p>

					<hr width="692" style="border:1.5px solid #F8E494; background-color:#F8E494;">

					<p class="depth_p"></p>
					<div class="form_container arrange">
						<a href="<%=request.getContextPath()%>/account/findid" class="form_link">Forgot Id/Password</a>
						<a href="<%=request.getContextPath()%>/account/login" class="form_link">Sign in</a>
					</div>
					<p class="depth_p"></p>
				</form>
				
			</article>
		</div>
	</div>
</body>
</html>