function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function ajaxPrevCheck3() {
	var prev = innerPrevCheck3();
	var contextPath = getProjectName();
	if(prev[0] == -1) {
		alert("모든 입력사항은 필수입니다.");
	} else {
		var warnings = document.getElementsByClassName("warning_message");
		var fields = prev[1];
		var sphone = fields[6].value + "-" + fields[7].value + "-" + fields[8].value;
		if(fields[2].value != fields[3].value) {
			alert("비밀번호가 일치하지 않습니다.");
			warnings[1].innerHTML = "비밀번호가 일치하지 않습니다.";
			return;
		}
		
		// ajax 처리 : 아이디, 비번, 클래스, 휴대폰번호 넘겨서 검사
		var xmlReq = new XMLHttpRequest();
		xmlReq.onreadystatechange = () => {
			if(xmlReq.readyState == XMLHttpRequest.DONE) {
				var error = false;
				if(xmlReq.status == 200) {
					var result = xmlReq.responseText;
					var divs = result.split("&");
					
					var idDiv = divs[0];
					if(idDiv.split("=")[1] != "ok") {
						warnings[0].innerHTML = idDiv.split("=")[1];
						error = true;
					} else warnings[0].innerHTML = "";
					
					var pwDiv = divs[1];
					if(pwDiv.split("=")[1] != "ok") {
						warnings[1].innerHTML = pwDiv.split("=")[1];
						error = true;
					} else warnings[1].innerHTML = "";
					
					var phoneDiv = divs[2];
					if(phoneDiv.split("=")[1] != "ok") {
						warnings[2].innerHTML = phoneDiv.split("=")[1];
						error = true;
					} else warnings[2].innerHTML = "";
				} else {
					alert("failed.....");
				}
				if(error == true) return;
				else {
					var form = document.getElementsByClassName("form_template")[0];
					//alert("성공!");
					form.submit();
				}
			}
		}
		xmlReq.open("POST", contextPath + "/ajax/signup_validate", true);
		xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xmlReq.responseType = "text";
		xmlReq.send("id=" + fields[1].value + "&pw=" + fields[2].value + "&phone=" + sphone);
	}
}

function innerPrevCheck3() {
	var name = document.getElementsByName("sname")[0];
	var id = document.getElementsByName("sid")[0];
	var pw1 = document.getElementsByName("spw1")[0];
	var pw2 = document.getElementsByName("spw2")[0];
	var que = document.getElementsByName("squestion")[0];
	var answer = document.getElementsByName("sanswer")[0];
	//var sclass = document.getElementsByName("sclass")[0];
	var sphone1 = document.getElementsByName("sphone1")[0];
	var sphone2 = document.getElementsByName("sphone2")[0];
	var sphone3 = document.getElementsByName("sphone3")[0];
	// 				0    1   2   3     4     5   /    6       7        8         9	
	var fields = [name, id, pw1, pw2, que, answer, sphone1, sphone2, sphone3];
	for(var i = 0; i < fields.length; i++) {
		if((fields[i].value == null) || (fields[i].value == "")) {
			return [-1, []];
		}
	}
	return [1, fields];
}