function beforeSubmit() {
	var title = document.getElementsByName("title")[0];
	var content = document.getElementsByName("content")[0];
	if((title == null||title == "")||(content == null||content == "")) {
		alert("내용을 입력해주세요.");
	} else {
		var form = document.getElementsByClassName("form_update_profile")[0];
		form.submit();
	}
}