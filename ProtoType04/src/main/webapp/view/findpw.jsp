<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Find Password</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/template.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/intro.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<form class="form_template">
					<p class="depth_p"></p>
					<p class="depth_p"></p>
					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Please enter your Id</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" />
					</div>

					<div class="form_container">
						<span class="warning_message"></span>
					</div>

					<p class="depth_p"></p>
					<div class="form_container">
						<input type="submit" value="find" class="btn btn_primary" style="width:100px;" />
					</div>
					<p class="depth_p"></p>
					<p class="depth_p"></p>
					<hr width="692" style="border:1.5px solid #F8E494; background-color:#F8E494;">
					<p class="depth_p"></p>
					<div class="form_container arrange">
						<a href="<%=request.getContextPath()%>/account/findid" class="form_link">Find Id</a>
						<a href="<%=request.getContextPath()%>/account/signup" class="form_link">Create a new account</a>
					</div>
					<p class="depth_p"></p>
				</form>
				
			</article>
		</div>
	</div>
</body>
</html>