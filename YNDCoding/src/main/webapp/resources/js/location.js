function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function goToEnroll() {
	var contextPath = getProjectName();
	location.href = contextPath + "/account/insert_member"; 
}

function goToUpdate(m_id) {
	var hdid = document.getElementById("hdid");
	hdid.value = m_id;
	var form = document.getElementsByName("form_forward")[0];
	form.submit();
}

function goToDelete(m_id) {
	var contextPath = getProjectName();
	var yon = confirm("한 번 삭제하면 되돌릴 수 없습니다. 계속 하시겠습니까?");
	if(yon == true) location.href = contextPath + "/account/adminBoard_process?m_id=" + m_id;
}

function goMenu() {
	history.go(-1);
}

function addSubject1001() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	alert(input);
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1001?name=" + input;
	}
}

function addSubject1002() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1002?name=" + input;
	}
}

function addSubject1003() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1003?name=" + input;
	}
}

function addSubject1004() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1004?name=" + input;
	}
}

function addSubject1005() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1005?name=" + input;
	}
}

function addSubject1006() {
	var contextPath = getProjectName();
	var input = prompt("추가할 과목의 이름을 입력하세요.(띄어쓰기 없이 입력)", "");
	if(input != "" && input != null) {
		location.href = contextPath + "/menusControl/addMenu/1006?name=" + input;
	}
}

function delSubject(menu_name) {
	var contextPath = getProjectName();
	var yon = confirm("한 번 삭제하면 되돌릴 수 없습니다. 계속 하시겠습니까?");
	if(yon == true) location.href = contextPath + "/menusControl/delMenu?name=" + menu_name;
}

function enrollComment() {
	var content = document.getElementById("commentEnrollContent");
	if(content.value == "") {
		alert("내용을 입력해주세요.");	
	} else {
		var form = document.getElementsByClassName("form_comment")[0];
		form.submit();
	}
}

function commentDelete(c_num) {
	var contextPath = getProjectName();
	var yon = confirm("한 번 삭제하면 되돌릴 수 없습니다. 계속 하시겠습니까?");
	if(yon == true) location.href = contextPath + "/comment/delete?c_num=" + c_num;
}

function commentDeleteIndex(c_num) {
	var contextPath = getProjectName();
	var yon = confirm("한 번 삭제하면 되돌릴 수 없습니다. 계속 하시겠습니까?");
	if(yon == true) location.href = contextPath + "/centerComment/delete?c_num=" + c_num;
}