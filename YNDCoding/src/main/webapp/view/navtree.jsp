<%@ page language="java" pageEncoding="utf-8" import="java.util.*, section01.*"  %>
<%
	request.setCharacterEncoding("utf-8");
	HashMap<String, Vector<MenusBean>> menus = (HashMap<String, Vector<MenusBean>>)request.getAttribute("menus");
	String author = (String)request.getAttribute("author");
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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/location.js"></script>
<nav class="navbar"><!--nav.navbar-->
	<ul class="nav">
			<li><a href="#" class="navitem">언어 기초</a>
				<ul class="nav ul first">
				<% for(MenusBean bean : menus.get("cat1001")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1001()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
				</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">서버</a>
				<ul class="nav ul second">
				<% for(MenusBean bean : menus.get("cat1002")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1002()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
				</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">DB</a>
				<ul class="nav ul third">
				<% for(MenusBean bean : menus.get("cat1003")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1003()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
				</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">개발 도구</a>
				<ul class="nav ul fourth">
				<% for(MenusBean bean : menus.get("cat1004")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1004()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
				</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">프로젝트 관리</a>
				<ul class="nav ul fifth">
				<% for(MenusBean bean : menus.get("cat1005")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1005()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
				</li>
				<% } %>
				</ul>
			</li>
			<li><a href="#" class="navitem">이론과 팁</a>
				<ul class="nav ul last">
				<% for(MenusBean bean : menus.get("cat1006")) { %>
					<li><a href="<%=request.getContextPath()%>/lecture/<%=bean.getMenu_snum()%>"><%=bean.getMenu_name()%></a>
						<div class="lecture btns">
							<% if(logined != null && logined.equals("true")) { 
									if(bean.getM_id().equals(uid)||uclass == 0) {%>
							<div class="btn-menus" onclick="delSubject('<%=bean.getMenu_name()%>')"><img src="<%=request.getContextPath()%>/resources/images/minus.png" width=15 height=15 /></div>
							<% 		}
							   } %>
						</div>
					</li>
				<% } %>
				<% if(logined != null && uclass < 2) { %>
				<li style="display:flex; justify-content:center;">
					<div class="btn-menus" onclick="addSubject1006()"><img src="<%=request.getContextPath()%>/resources/images/plust.png" width=15 height=15 /></div>
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