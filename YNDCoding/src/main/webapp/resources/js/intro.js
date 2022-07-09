function getProjectName() {
	var pathname = window.location.pathname;
	var parse = pathname.split("/");
	return "/" + parse[1];
}

function intro() {
	contextPath = getProjectName();
	location.href= contextPath + "/center/intro";
}