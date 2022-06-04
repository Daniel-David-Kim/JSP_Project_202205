<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lecture</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/template.css" />
	<script type="text/javascript" src="../resources/js/intro.js"></script>
</head>
<body>
	<div class="highest">
		<div class="highest center-content1">
			<div class="logo"><img src="../resources/images/logo1.png" width=100% height=100% class="logolink" onclick="intro()" /></div>
			<div class="item_box">
				<div id="account" style="color:dimgray;">FenixFenixFenix</div>
				<div><a href="#" class="upper_links account_info">INFO</a></div>
				<!--
				로그인이 되면
				.account_info{display: flex;}
				#account{display:flex;}
				해주면 보이게 됨.
				-->
				<div><a href="#" class="upper_links account_control"><pre>SIGN IN</pre></a></div>
				<div><a href="#" class="upper_links"><pre>JOIN US</pre></a></div>
			</div>
		</div>
	</div>
	<div class="pic_container">
		<div class="pic_container-center">
			<img src="../resources/images/code1920.jpg" />
		</div>
	</div>

	<nav class="navbar"><!--nav.navbar-->
		<ul class="nav">
			<li><a href="#" class="navitem">언어 기초</a>
				<ul>
					<li><a href="#">C</a></li>
					<li><a href="#">Java</a></li>
					<li><a href="#">Python</a></li>
				</ul>
			</li>
			<li><a href="#" class="navitem">서버</a>
				<ul>
					<li><a href="#">JSP</a></li>
					<li><a href="#">Spring</a></li>
					<li><a href="#">Node.js</a></li>
				</ul>
			</li>
			<li><a href="#" class="navitem">DB</a>
				<ul>
					<li><a href="#">Oracle</a></li>
					<li><a href="#">MySQL</a></li>
				</ul>
			</li>
			<li><a href="#" class="navitem">개발 도구</a>
				<ul>
					<li><a href="#">VirtualBox</a></li>
				</ul>
			</li>
			<li><a href="#" class="navitem">프로젝트 관리</a>
				<ul>
					<li><a href="#">Github</a></li>
					<li><a href="#">Git</a></li>
				</ul>
			</li>
			<li><a href="#" class="navitem">이론과 팁</a>
				<ul>
					<li><a href="#">알고리즘</a></li>
					<li><a href="#">OAuth</a></li>
				</ul>
			</li>
		</ul>
	</nav><!--nav.navbar-->


	<div class="tree_container"><!--div.tree_container : 네비게이션바 밑의 트리 바입니다.-->
		<div class="tree">
			<span class="nodes">야너두코딩&nbsp;&nbsp;>&nbsp;&nbsp;서버&nbsp;&nbsp;>&nbsp;&nbsp;Node.js</span>
		</div>
	</div><!--div.tree_container-->

	<div class="whole"><!--div.whole : 브라우저 트리바 밑의 본문 부분 가로 부분을 다 포함하는 전체 부분 컨테이너-->
		<div class="article_container"><!--div.article_container : whole 전체 부분 중에서 웹디자인상으로 실제 작업을 하는 가로 사이즈 1280px을 영역으로 묶어 브라우저 중앙으로 정렬시킨 컨테이너.-->

			<div class="page_wrapper"><!--div.page_wrapper : 강의 페이지, 그 외에 왼쪽 날개(left wing)가 필요한 페이지에서 왼쪽 날개를 배치시키기 위한, 감싸주는 컨테이너입니다.-->

				<div class="left_wing"><!--div.left_wing : 좌측에 강의 목록 나오죠? 그거입니다!-->
					<ul class="lectures_container">
						<li class="lecture_list"><a href="#" class="lecture_link">강의 소개</a></li>
						<li class="lecture_list"><a href="#" class="lecture_link">Node.js 설치</a></li>
						<li class="lecture_list"><a href="#" class="lecture_link">Node.js - 웹서버 만들기</a></li>
						<li class="lecture_list"><a href="#" class="lecture_link">Node.js - URL로 입력된 값 사용하기</a></li>
					</ul>
					<hr width="288" style="height:1px; background-color:#BCBEC0; margin-top:9px;"><!--hr:밑줄 라인-->
					<div class="lecturer"><!-- div.lecturer : 강의자의 프로필을 보여준다. -->
						<img src="../resources/images/profile1.png" class="lecturer_profile" width=57 height=57 />
						<div class="lecturer_info">
							<div class="lecturer_class">강의자</div>
							<div class="lecturer_name">나도코딩</div>
						</div>
					</div><!-- div.lecturer -->
					<hr width="288" style="height:1px; background-color:#BCBEC0;"><!--hr:밑줄 라인-->
					<div id="lecturer_link"><!-- div#lecturer_link : 강의자로 로그인하면 활성화되는 메뉴 : 현재는 display:none으로 되어있지만, display:flex로 하면 다시 나타난다! -->
						<a href="#">강의 수정</a>
						<a href="#">강의 삭제</a>
					</div><!-- div#lecturer_link -->
					<div id="user_link"><!-- div#user_link : 사용자로 로그인하면 활성화되는 메뉴 : display:none으로 하면 사라지고, display:flex로 하면 다시 나타난다. -->
						<a href="#">강의자 Q&A</a>
					</div><!-- div#lecturer_link -->
				</div>

				<div class="lecture_container"><!--div.lecture_container : 우측에 강의 내용이 올라오는 컨테이너입니다.-->

					<article class="article_main" style="padding-left:40px; padding-right:130px;"><!--article.article_main : 인트로 페이지의 그 article.article_main 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 padding을 조정했습니다. -->
					
						<p class="depth_p"></p><!--p.depth_p : 문단 사이의 간격을 정해주기 위한 p클래스입니다.-->
						<h1 class="lecture_title">Node.js 설치</h1>
						<span class="lecture_date_shower">2022-05-20</span>
						<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-bottom:56px;">


						<h2 class="titleh2Default">수업소개</h2><!--h2.titleh2Default : 인트로 페이지의 각 굵직한 소제목들, 강의 페이지에서 항목들을 분류하는 소제목으로 쓰입니다.-->
						<p class="small_phrase">
							입문자의 가장 큰 고충은 '무엇을 모르는지 모르는 상태'일 겁니다. 온라인에는 프로그래밍을 익히는 데 필요한 거의 모든 정보가 있지만, 이 지식들은 게시판이나 블로그 또는 커뮤니티에 포스팅 단위로 파편화되어 있습니다. 그래서 최소한 무엇을 검색해야 하는지를 아는 사람들을 위해서는 더 없이 좋은 공간이지만, '무엇을 모르는지 모르는 상태'의 입문자에게는 그림의 떡으로 남아 있습니다. 다시말해서 전문가를 더욱 전문가답게 만드는 혁신에 머물고 있는 것이죠.
						</p>
						<p class="depth_p"></p>
						<p class="depth_p"></p>

						<h2 class="titleh2Default">강의</h2>
						<div class="container"><!--p.container : 부트스트랩의 div.container과 유사하게 만들었습니다. 안에 들어가는 내용물들을 중앙으로 정렬시킵니다.-->
							<!--iframe : 퍼오는 동영상이 올라오는 엘리먼트입니다. 유튜브에서 주어진 형식에 width, height만 바꾸어서 현재 모습입니다. 다른 영상을 올리려면 src 부분만 변경하면 됩니다.-->
							<iframe width="700" height="455" src="https://www.youtube.com/embed/0P8r0XqYfVw" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
							</iframe>
						</div>
						<p class="depth_p"></p>
						<p class="depth_p"></p>

						<h2 class="titleh2Default">참고 이미지</h2>
						<div class="container">
							<img src="../resources/images/door.png" width="250" height="250" />
						</div>
						<p class="depth_p"></p>
						<p class="depth_p"></p>

						<h2 class="titleh2Default">소스코드</h2>
						<pre class="source_code"><!--pre.source_code : 소스 코드를 넣는 부분입니다.-->
package sec02.ex01;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/first/test")
public class TestServlet1 extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init method activated.");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet method activated.");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
									
		ServletContext context = getServletContext();
		out.print("<!DOCTYPE html><html><head><meta charset='utf-8'><title></title></head><body>");
		out.print("TestServlet1입니다.<br>");
		out.print("컨텍스트명 : " + context.getContextPath());
									
		out.print("</body></html>");
	}
	@Override
	public void destroy() {
		System.out.println("destroy method activated.");
	}
}
						</pre><!--pre.source_code-->
						<p class="depth_p"></p>
						<p class="depth_p"></p>

						<h2 class="titleh2Default">요약</h2>
						<pre class="dynamic_phrase"><!--pre.dynamic_phrase : 본문의 영역 안에서 입력된 본문 그대로 출력해주는 단락 엘리먼트입니다. 내용이 범위를 넘을 경우 자동으로 줄바꿈 해줍니다.-->
1. 생활코딩 주력 수업인 WEBn은 프로그래밍이 처음인 분들을 위해서 고안된 수업입니다. WEBn을 통해서 교양으로 코딩을 공부하려는 분들에게는 출구를, 직업으로 코딩을 공부하려는 분들에게는 입구를 제공해드리려고 노력하고 있습니다. 웹이라는 구체적인 사례를 통해서 코딩이 무엇인가 파악해보세요. 또 코딩을 통해서 웹을 만드는 방법을 공부해보세요. <br><br>
2. 아래 수업은 생활코딩 수업들 간의 의존관계를 나타내고 있는 지도입니다. 이 지도를 통해서 심화과정을 스스로 탐구 할 수 있습니다. 각각의 수업에 방문해서 소개 영상부터 구경해보세요. 
						</pre>
						<p class="depth_p"></p><!--pre.dynamic_phrase-->

					</article><!--article.article_main-->

					<hr width="840" style="border:1px solid #f1f1f2; background-color:#f1f1f2; margin-left:40px; margin-right:130px;"><!--hr:밑줄 라인-->
					<!--hr : 인트로 페이지의 그 hr 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->

					<div class="comments" style="margin-left:40px; margin-right:130px;"><!--div.comments : 댓글 작성 폼과 댓글 목록들을 포함하는 컨테이너입니다.-->
						<!--div.comments : 인트로 페이지의 그 div.comments 맞습니다. 다만, 왼쪽 날개에 맞게 style에서 margin을 조정했습니다. -->
						<form class="form_comment">
							<img src="../resources/images/profile1.png" width="50" height="50" />
							<textarea cols=102 rows=7></textarea>
						</form>
						<div class="comment_list"><!--div.comment_list :댓글들이 여기 부분으로 쭉 나열됩니다.-->
							
						</div><!--div.comment_list-->
					</div><!--div.comments-->

				</div><!--div.lecture_container-->
			</div><!--div.page_wrapper-->


			
		</div><!--div.article_container-->
	</div><!--div.whole-->

</body>
</html>