function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function innerPrevCheck3() {
	var name = document.getElementsByName("sname")[0];
	var id = document.getElementsByName("sid")[0];
	var que = document.getElementsByName("squestion")[0];
	var answer = document.getElementsByName("sanswer")[0];
	var sphone1 = document.getElementsByName("sphone1")[0];
	var sphone2 = document.getElementsByName("sphone2")[0];
	var sphone3 = document.getElementsByName("sphone3")[0];
	var sclass = document.getElementsByName("sclass")[0];
	// 				0    1   2      3     4       5        6             	
	var fields = [name, id, que, answer, sphone1, sphone2, sphone3, sclass];
	for(var i = 0; i < fields.length; i++) {
		if((fields[i].value == null) || (fields[i].value == "")) {
			return [-1, []];
		}
	}
	return [1, fields];
}

function ajaxPrevCheck3(idChange, pwChange, contactChange, classChange) {
	var prev = innerPrevCheck3();
	var contextPath = getProjectName();
	if(idChange == false && pwChange == false && contactChange == false && classChange == false) return 1;
	if(prev[0] == -1) {
		alert("모든 입력사항은 필수입니다.");
	} else {
		var warnings = document.getElementsByClassName("warning_message");
		var fields = prev[1];
		var sphone = fields[4].value + "-" + fields[5].value + "-" + fields[6].value;
		
		var id = "";
		if(idChange == true) id = document.getElementsByName("sid")[0].value;
		var pw = ""; 
		if(pwChange == true) pw = document.getElementsByName("spw1")[0].value;
		var uclass = "";
		if(classChange == true) uclass = document.getElementsByName("sclass")[0].value;
		
		// ajax 처리 : 수정할 때는 아디, 비번, 휴대폰번호, 클래스번호 넘겨서 검사
		var xmlReq = new XMLHttpRequest();
		
		
		xmlReq.onreadystatechange = () => {
			if(xmlReq.readyState == XMLHttpRequest.DONE) {
				var error = false;
				
				// 여기까지 클리어!
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
								case "id": warningIdx=0; break;
								case "pw": warningIdx=2; break;
								case "phone": warningIdx=3; break;
								case "uclass": warningIdx=1; break;
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
		xmlReq.open("POST", contextPath + "/ajax/update_validate", true);
		xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xmlReq.responseType = "text";
		var reqStr = "";
		
		var bools = [idChange, pwChange, contactChange, classChange];
		var labels = ["id", "pw", "phone", "uclass"];
		var values = [id, pw, sphone, uclass];
		var first = true;
		for(var i = 0; i < bools.length; i++) {
			if(bools[i] == true) {
				if(first == true) {
					reqStr += labels[i] + "=" + values[i];
					first = false;	
				} else reqStr += "&" + labels[i] + "=" + values[i];
			}
		}
		//alert("ajax confirm : " + reqStr);
		xmlReq.send(reqStr);
	}
}

function phone_validate() {
	var tel1 = document.getElementsByName("tel1")[0].value;
	var tel2 = document.getElementsByName("tel2")[0].value;
	var tel3 = document.getElementsByName("tel3")[0].value;
	var tel = tel1 + "-" + tel2 + "-" + tel3;
	var regex = /(010)-[1-9][0-9]{3}-[0-9]{4}/;
	var regex2 = /(011)-[1-9][0-9]{2}-[0-9]{4}/;
	if(regex.test(tel) == true || regex.test(tel) == true) return true;
	else return false;
}

function findId() {
	var contextPath = getProjectName();
	var name = document.getElementsByName("name")[0].value;
	var tel1 = document.getElementsByName("tel1")[0].value;
	var tel2 = document.getElementsByName("tel2")[0].value;
	var tel3 = document.getElementsByName("tel3")[0].value;
	if((name == null||name == "")&&(tel1 == null||tel1 == "")&&(tel2 == null||tel2 == "")&&(tel3 == null||tel3 == "")) {
		alert("정보를 입력해주세요.");
	} else {
		var isValid = phone_validate();
		if(isValid == false) {
			var warning = document.getElementsByClassName("warning_message")[0];
			warning.innerHTML = "휴대폰 번호 형식에 맞지 않습니다.\n010-X000-0000 혹은 011-X00-0000\n(X은 1-9 사이의 정수, 0은 0-9 사이의 정수)";
		} else {
			var tel = tel1 + "-" + tel2 + "-" + tel3;
			var xmlReq = new XMLHttpRequest();
			xmlReq.onreadystatechange = () => {
				if(xmlReq.readyState == XMLHttpRequest.DONE) {
					var result = xmlReq.responseText;
					alert(result);
				}
			}
			xmlReq.open("POST", contextPath + "/ajax/findid", true);
			xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			xmlReq.responseType = "text";
			xmlReq.send("name=" + name + "&tel=" + tel);
		}
	}
	
}

function findpw() {
	var contextPath = getProjectName();
	var id = document.getElementsByName("id")[0].value;
	var warning = document.getElementsByClassName("warning_message")[0];
	if(id == null || id == "") {
		warning.innerHTML = "아이디를 입력해주세요.";
	} else {
		var xmlReq = new XMLHttpRequest();
		xmlReq.onreadystatechange = () => {
			if(xmlReq.readyState == XMLHttpRequest.DONE) {
				var result = xmlReq.responseText;
				if(result == "회원 정보를 찾을 수 없습니다.") alert(result);
				else {
					var ip = prompt(result, "");
					if(ip == null || ip == "") {
						alert("답이 틀렸습니다. 회원 정보를 찾을 수 없습니다.");
					} else {
						findpw2(ip);
						return;
					}
				}
			}
		}
		xmlReq.open("POST", contextPath + "/ajax/findpw", true);
		xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xmlReq.responseType = "text";
		xmlReq.send("id=" + id);
	}
}

function findpw2(ip) {
	var contextPath = getProjectName();
	var xmlReq = new XMLHttpRequest();
	xmlReq.onreadystatechange = () => {
		if(xmlReq.readyState == XMLHttpRequest.DONE) {
			var result = xmlReq.responseText;
			alert(result);
		}
	}
	xmlReq.open("POST", contextPath + "/ajax/findpw2", true);
	xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	xmlReq.responseType = "text";
	xmlReq.send("answer=" + ip);
}