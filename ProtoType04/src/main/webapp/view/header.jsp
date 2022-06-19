<%@ page language="java" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)session.getAttribute("id");
%>
<div class="highest">
	<div class="highest center-content1">
		<div class="logo"><img src="<%=request.getContextPath()%>/resources/images/logo1.png" width=100% height=100% class="logolink" onclick="intro()" /></div>
		<div class="item_box">
			<% if(id != null && !id.equals("")) { %>
			<div id="account" style="color:dimgray;"><%=id%></div>
			<div><a href="<%=request.getContextPath()%>/account/accountInfo" class="upper_links account_info">INFO</a></div>
			<!--
			로그인이 되면
			.account_info{display: flex;}
			#account{display:flex;}
			해주면 보이게 됨.
			-->
			<div><a href="<%=request.getContextPath()%>/account/login" class="upper_links account_control"><pre>SIGN OUT</pre></a></div>
			<% } else { %>
			<div><a href="<%=request.getContextPath()%>/account/login" class="upper_links account_control"><pre>SIGN IN</pre></a></div>
			<% } %>
			<div><a href="#" class="upper_links"><pre>JOIN US</pre></a></div>
		</div>
	</div>
</div>
<div class="pic_container">
	<div class="pic_container-center">
		<img src="<%=request.getContextPath()%>/resources/images/code1920.jpg" />
	</div>
</div>