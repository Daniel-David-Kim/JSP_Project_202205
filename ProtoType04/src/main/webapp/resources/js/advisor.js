var img1 = false; // 이미지가 업로드 되었으면 true로 바뀝니다.
var img2 = false; // 게시글 추가에 쓰이는 boolean 항목입니다.
var profile = false;

function prevTest() {
	var resCode = prevValidate();
	if(resCode != -1) {
		var form = document.getElementsByClassName("form_update_profile")[0];
		if(form != null) form.submit();
	}
}

function prevValidate() {
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
		return -1;	
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
			return -1;
		}
	} 
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

function img1Uploaded() {
	var img1Chbool = document.getElementsByName("img1Changedbool")[0];
	img1Chbool.value = "true";
	img1 = true;
}

function img2Uploaded() {
	var img2Chbool = document.getElementsByName("img2Changedbool")[0];
	img2Chbool.value = "true";
	img2 = true;
}

function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function img1Delete() {
	var img1show = document.getElementsByName("img1show")[0];
	var img1bool = document.getElementsByName("img1Uploadedbool")[0];
	var img1Chbool = document.getElementsByName("img1Changedbool")[0];
	var contextPath = getProjectName();
	if(img1bool.value == "true") {
		var b = confirm("이 이미지를 삭제하시겠습니까?");
		if(b == true) {
			img1show.src = contextPath + "/resources/images/gray_50.jpg";
			img1bool.value = "false";
			img1Chbool.value="true";
			alert("변경이 완료되었습니다.");
		}
	}	
}

function img2Delete() {
	var img2show = document.getElementsByName("img2show")[0];
	var img2bool = document.getElementsByName("img2Uploadedbool")[0];
	var img2Chbool = document.getElementsByName("img2Changedbool")[0];
	var contextPath = getProjectName();
	if(img2bool.value == "true") {
		var b = confirm("이 이미지를 삭제하시겠습니까?");
		if(b == true) {
			img2show.src = contextPath + "/resources/images/gray_50.jpg";
			img2bool.value = "false";
			img2Chbool.value="true";
			alert("변경이 완료되었습니다.");
		}
	}	
}

function profileDelete() {
	var profileShow = document.getElementsByName("profileShow")[0];
	var profileBool = document.getElementsByName("profileUploaded")[0];
	var profileChBool = document.getElementsByName("profileChanged")[0];
	var contextPath = getProjectName();
	if(profileBool.value == "true") {
		var b = confirm("이 이미지를 삭제하시겠습니까?");
		if(b == true) {
			profileShow.src = contextPath + "/resources/images/gray_50.jpg";
			profileBool.value = "false";
			profileChBool.value="true";
			alert("변경이 완료되었습니다.");
		}
	}	
}

function profileUpload(input) {
	var imgShow = document.getElementsByName("profileShow")[0];
	
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			imgShow.src = e.target.result;
		}
		reader.readAsDataURL(input.files[0]);
		alert("profile change activated.");
		var profileChBool = document.getElementsByName("profileChanged")[0];
		profileChBool.value = "true";
		profile = true;
	}
}