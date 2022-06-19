function appearForm() {
	var button = document.getElementsByClassName("popup_button");
	var hidden = document.getElementsByClassName("popup_form");
	if(hidden != null && button != null) {
		button = button[0];
		button.style.display = 'none';
		hidden = hidden[0];
		hidden.style.display = 'block';
	}
}