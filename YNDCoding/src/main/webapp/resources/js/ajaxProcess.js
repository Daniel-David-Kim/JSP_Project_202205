function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function ajaxPrevCheck() {
	var prev = innerPrevCheck();
	var contextPath = getProjectName();
	if(prev[0] == -1) {
		alert("모든 입력사항은 필수입니다.");
	} else {
		var warnings = document.getElementsByClassName("warning_message");
		var fields = prev[1];
		var sphone = fields[7].value + "-" + fields[8].value + "-" + fields[9].value;
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
					var classDiv = divs[2];
					if(classDiv.split("=")[1] != "ok") {
						warnings[2].innerHTML = classDiv.split("=")[1];
						error = true;
					} else warnings[2].innerHTML = "";
					var phoneDiv = divs[3];
					if(phoneDiv.split("=")[1] != "ok") {
						warnings[3].innerHTML = phoneDiv.split("=")[1];
						error = true;
					} else warnings[3].innerHTML = "";
				} else {
					alert("failed.....");
				}
				if(error == true) return;
				else {
					var form = document.getElementsByClassName("form_template")[0];
					form.submit();
				}
			}
		}
		xmlReq.open("POST", contextPath + "/ajax/enroll_validate", true);
		xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xmlReq.responseType = "text";
		xmlReq.send("id=" + fields[1].value + "&pw=" + fields[2].value + "&uclass=" + fields[6].value + "&phone=" + sphone);
	}
}

function innerPrevCheck() {
	var name = document.getElementsByName("sname")[0];
	var id = document.getElementsByName("sid")[0];
	var pw1 = document.getElementsByName("spw1")[0];
	var pw2 = document.getElementsByName("spw2")[0];
	var que = document.getElementsByName("squestion")[0];
	var answer = document.getElementsByName("sanswer")[0];
	var sclass = document.getElementsByName("sclass")[0];
	var sphone1 = document.getElementsByName("sphone1")[0];
	var sphone2 = document.getElementsByName("sphone2")[0];
	var sphone3 = document.getElementsByName("sphone3")[0];
	// 				0    1   2   3     4     5        6       7       8         9	
	var fields = [name, id, pw1, pw2, que, answer, sclass, sphone1, sphone2, sphone3];
	for(var i = 0; i < fields.length; i++) {
		if((fields[i].value == null) || (fields[i].value == "")) {
			return [-1, []];
		}
	}
	return [1, fields];
}

function innerPrevCheck2() {
	var name = document.getElementsByName("sname")[0];
	var id = document.getElementsByName("sid")[0];
	var que = document.getElementsByName("squestion")[0];
	var answer = document.getElementsByName("sanswer")[0];
	var sphone1 = document.getElementsByName("sphone1")[0];
	var sphone2 = document.getElementsByName("sphone2")[0];
	var sphone3 = document.getElementsByName("sphone3")[0];
	// 				0    1   2      3     4       5        6             	
	var fields = [name, id, que, answer, sphone1, sphone2, sphone3];
	for(var i = 0; i < fields.length; i++) {
		if((fields[i].value == null) || (fields[i].value == "")) {
			return [-1, []];
		}
	}
	return [1, fields];
}

function ajaxPrevCheck2(pwChange, contactChange) {
	var prev = innerPrevCheck2();
	var contextPath = getProjectName();
	if(pwChange == false && contactChange == false) return 1;
	if(prev[0] == -1) {
		alert("모든 입력사항은 필수입니다.");
	} else {
		var warnings = document.getElementsByClassName("warning_message");
		var fields = prev[1];
		var sphone = fields[4].value + "-" + fields[5].value + "-" + fields[6].value;
		var pw = ""; 
		if(pwChange == true) pw = document.getElementsByName("spw1")[0].value;
		
		// ajax 처리 : 수정할 때는 비번, 휴대폰번호 넘겨서 검사
		var xmlReq = new XMLHttpRequest();
		xmlReq.onreadystatechange = () => {
			if(xmlReq.readyState == XMLHttpRequest.DONE) {
				var error = false;
				if(xmlReq.status == 200) {
					var result = xmlReq.responseText;
					alert("result : " + result);
					var divs = result.split("&");
					
					
					for(var i = 0; i < divs.length; i++) {
						var temp = divs[i];
						var warningIdx = -1;
						if(temp != null && temp != "") {
							var duo = temp.split("=");
							switch(duo[0]) {
								case "pw": warningIdx=0; break;
								case "phone": warningIdx=1; break;
							}
							if(duo[1] != "ok") {
								warnings[warningIdx].innerHTML = duo[1];
								error = true;
							} else warnings[warningIdx].innerHTML = "";	
						}
					}
					
				} else {
					alert("failed.....");
				}
				if(error == true) {
					alert("에러! 변경되지 않습니다!");
					return;
				} else {
					//alert("변경!");								
					var form = document.getElementsByClassName("form_update_profile")[0];
					if(form != null) form.submit();
					return;
				}	
			}
		}
		xmlReq.open("POST", contextPath + "/ajax/edit_validate", true);
		xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xmlReq.responseType = "text";
		var reqStr = "";
		if(pwChange == true && contactChange == true) reqStr = "pw=" + pw + "&phone=" + sphone;
		else if(pwChange == true && contactChange == false) reqStr = "pw=" + pw;
		else if(pwChange == false && contactChange == true) reqStr = "phone=" + sphone;
		//alert("ajax confirm : " + reqStr);
		xmlReq.send(reqStr);
	}
}