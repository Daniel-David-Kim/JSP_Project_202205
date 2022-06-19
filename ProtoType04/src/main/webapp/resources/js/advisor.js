var img2 = false; // 게시글 추가에 쓰이는 boolean 항목입니다.

function prevTest() {
	var warnings = document.getElementsByClassName("warning_message");
	var title = document.getElementsByName("title")[0];
	var vid_title = document.getElementsByName("vid_title")[0];
	var vid_url = document.getElementsByName("vid_url")[0];
	var vid_title2 = document.getElementsByName("vid_title2")[0];
	var vid_url2 = document.getElementsByName("vid_url2")[0];
	var isit = false;
	if((title == null || title.value == "") || (vid_title == null || vid_title.value == "") || (vid_url == null || vid_url.value == "")) {
		alert('강의 제목, 동영상 제목, 동영상 URL은 입력 필수사항입니다.');
		if(title == null || title.value == "") {
			warnings[0].innerHTML = "강의 제목을 입력해주세요.";
			title.focus();
			isit = true;
		}
		if(vid_title == null || vid_title.value == "") {
			warnings[1].innerHTML = "동영상 제목을 입력해주세요.";
			if(isit == false) {
				vid_title.focus();
				isit = true;
			}
		}
		if(vid_url == null || vid_url.value == "") {
			warnings[2].innerHTML = "동영상 URL을 등록해주세요.";
			if(isit == false) vid_url.focus();
		}
		return;	
	} else if((vid_title2 == null || vid_title2.value == "") || (vid_url2 == null || vid_url2.value == "")) {
		var code2 = document.getElementsByName("code2")[0];
		var summary2 = document.getElementsByName("summary2")[0];
		if(img2 == true || (code2 != null && code2.value != "") ||(summary2 != null && summary2.value != "")) {
			alert('두번째 동영상 제목과 동영상 URL은 필수입니다.');
			if(vid_title2 == null || vid_title2.value == "") {
				warnings[3].innerHTML = "동영상 제목을 입력해주세요.";
				vid_title2.focus();
				isit = true;
			}
			if(vid_url2 == null || vid_url2.value == "") {
				warnings[4].innerHTML = "동영상 URL을 등록해주세요.";
				if(isit == false) vid_url2.focus();
			}
			return;
		}
	} 
	var form = document.getElementsByClassName("form_update_profile")[0];
	if(form != null) form.submit();
}

function ip2FirstTest(event) {
	var title = document.getElementsByName("title")[0];
	var vid_title = document.getElementsByName("vid_title")[0];
	var vid_url = document.getElementsByName("vid_url")[0];
	var warnings = document.getElementsByClassName("warning_message");
	var isit = false;
	if((title == null || title.value == "") || (vid_title == null || vid_title.value == "") || (vid_url == null || vid_url.value == "")) {
		alert('먼저 상위의 항목들을 입력해주세요.');
		if(title == null || title.value == "") {
			warnings[0].innerHTML = "강의 제목을 입력해주세요.";
			title.focus();
			isit = true;
		}
		if(vid_title == null || vid_title.value == "") {
			warnings[1].innerHTML = "동영상 제목을 입력해주세요.";
			if(isit == false) {
				vid_title.focus();
				isit = true;
			}
		}
		if(vid_url == null || vid_url.value == "") {
			warnings[2].innerHTML = "동영상 URL을 등록해주세요.";
			if(isit == false) vid_url.focus();
		}
		event.preventDefault();		
	}
}

function ip2TitleTest(event) {
	var vid_title2 = document.getElementsByName("vid_title2")[0];
	var warnings = document.getElementsByClassName("warning_message");
	if(vid_title2 == null || vid_title2.value == "") {
		alert('먼저 동영상 제목을 입력해주세요.');
		warnings[3].innerHTML = "동영상 제목을 입력해주세요.";
		vid_title2.focus();
		event.preventDefault();
	}
}

function ip2Test(event) {
	var vid_title2 = document.getElementsByName("vid_title2")[0];
	var vid_url2 = document.getElementsByName("vid_url2")[0];
	var isit = false;
	var warnings = document.getElementsByClassName("warning_message");
	if((vid_title2 == null || vid_title2.value == "") || (vid_url2 == null || vid_url2.value == "")) {
		alert('먼저 동영상 제목과 영상을 올려주세요.');
		
		if(vid_title2 == null || vid_title2.value == "") {
			warnings[3].innerHTML = "동영상 제목을 입력해주세요.";
			vid_title2.focus();
			isit = true;
		}
		if(vid_url2 == null || vid_url2.value == "") {
			warnings[4].innerHTML = "동영상 URL을 등록해주세요.";
			if(isit == false) vid_url2.focus();
		}
		event.preventDefault();		
	}
}

function img2Uploaded() {
	img2 = true;
}