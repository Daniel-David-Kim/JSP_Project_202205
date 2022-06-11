<%@ page language="java" pageEncoding="utf-8" import="java.util.*, section01.*"  %>
<%
	request.setCharacterEncoding("utf-8");
	HashMap<String, Vector<MenusBean>> menus = (HashMap<String, Vector<MenusBean>>)request.getAttribute("menus");
%>
<nav class="navbar"><!--nav.navbar-->
	<ul class="nav">
			<li><a href="#" class="navitem">언어 기초</a>
				<ul class="nav ul first">
				<% for(MenusBean bean : menus.get("cat1001")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">서버</a>
				<ul class="nav ul second">
				<% for(MenusBean bean : menus.get("cat1002")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">DB</a>
				<ul class="nav ul third">
				<% for(MenusBean bean : menus.get("cat1003")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">개발 도구</a>
				<ul class="nav ul fourth">
				<% for(MenusBean bean : menus.get("cat1004")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">프로젝트 관리</a>
				<ul class="nav ul fifth">
				<% for(MenusBean bean : menus.get("cat1005")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">이론과 팁</a>
				<ul class="nav ul last">
				<% for(MenusBean bean : menus.get("cat1006")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></a>&nbsp;
							<a href="#"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></a>
						</div>
					</li>
				<% } %>
				</ul>
			</li>
	</ul>
</nav><!--nav.navbar-->
	
<div class="tree_container">
	<div class="tree">
		<span class="nodes">야너두코딩</span>
	</div>
</div>