<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/template.css" />
	<script type="text/javascript" src="../resources/js/intro.js"></script>
</head>
<body>
	<div class="highest">
		<div class="highest center-content1">
			<div class="logo"><img src="../resources/images/logo1.png" width=100% height=100% class="logolink" onclick="intro()" /></div>
			<div class="item_box">
				<div id="account" style="font-family:'함초롬돋움';">FenixFenixFenix</div>
				<div><a href="#" class="upper_links account_info">INFO</a></div>
				<!--
				로그인이 되면
				.account_info{display: flex;}
				#account{display:flex;}
				해주면 보이게 됨.
				-->
				<div><a href="./login.jsp" class="upper_links account_control"><pre>SIGN IN</pre></a></div>
				<div><a href="#" class="upper_links"><pre>JOIN US</pre></a></div>
			</div>
		</div>
	</div>
	<div class="pic_container">
		<div class="pic_container-center">
			<img src="../resources/images/code1920.jpg" />
		</div>
	</div>

	<div class="whole">
		<div class="article_container">
			<article class="article_main">
				
				<form class="form_template">
					<p class="depth_p"></p>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Name</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" />
					</div>
					<p class="depth_p"></p>
					
					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Id</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span>
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Password</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="password" name="" class="form_input" />
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Password (check)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="password" name="" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span>
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Question (find Password)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" />
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Answer (find Password)</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" />
					</div>
					<p class="depth_p"></p>

					<div class="form_container" style="justify-content:left;">
						<h2 class="form_label">Contact</h2>
					</div>
					<p class="depth_p slim"></p>
					<div class="form_container">
						<input type="text" name="" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="" class="form_input" /><span class="slash">ㅡ</span><input type="text" name="" class="form_input" />
					</div>
					<div class="form_container">
						<span class="warning_message"></span>
					</div>
					<p class="depth_p"></p>

					<div class="form_container">
						<input type="submit" value="Sign up" class="btn btn_primary" style="width:100px;" />
					</div>
					<p class="depth_p"></p>
					<p class="depth_p"></p>

					<hr width="692" style="border:1.5px solid #F8E494; background-color:#F8E494;">

					<p class="depth_p"></p>
					<div class="form_container arrange">
						<a href="./findid.jsp" class="form_link">Forgot Id/Password</a>
						<a href="./login.jsp" class="form_link">Sign in</a>
					</div>
					<p class="depth_p"></p>
				</form>
				
			</article>
		</div>
	</div>
</body>
</html>