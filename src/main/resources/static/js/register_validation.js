$("#register input").on("blur", function() {

	var current = $(this).val();
	var name = $(this).attr("name");

	if (isBlank(current) || !validateCurrentInput(name, current)) {
		addInvalidClass($(this), name)

	} else {
		addValidClass($(this), name);
	}

})

function validateFirstOrLastName(current) {
	var pattern = "^[a-zA-Z]{2,20}$";

	return checkPattern(pattern, current);
}

function validatePassword(current) {
	var pattern = "^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z]).{8,20}$";

	if (!checkPattern(pattern, current)) {
		return false;

	} else if (!checkCredentials(current, "Password")) {
		registrationMessage("Izabrana lozinka vec postoji");
		return false;
	} else {
		return true;
	}
}

function validateUsername(current) {
	var pattern = "^[a-zA-Z0-9]{5,20}$";

	if (!checkPattern(pattern, current)) {
		return false;
	} else if (!checkCredentials(current, "Username")) {
		console.log("f")
		return false;
	} else {
		return true;
	}

}

function validateEmail(current) {
	var pattern = "^\\S+@\\S+\\.*\\S+$";

	if (!checkPattern(pattern, current)) {
		return false;
	} else if (!checkCredentials(current, "Email")) {
		registrationMessage("Ne mogu postojati dva racuna sa istim email-om");
		return false;
	} else {
		return true;
	}
}

function checkPattern(pattern, value) {

	var regex = new RegExp(pattern, "g");

	return regex.test(value);

}

function isBlank(current) {

	if (current == null || current == "") {
		return true;
	} else {
		return false;
	}
}

function addValidClass(inputField, glyphicon) {

	$("." + glyphicon).removeClass("glyphicon glyphicon-remove");
	$("." + glyphicon).addClass("glyphicon glyphicon-ok");
	$("." + glyphicon).css("color", "green");
	$(inputField).removeClass("wrong");
	$(inputField).addClass("correct");
}

function addInvalidClass(inputField, glyphicon) {
	$("." + glyphicon).removeClass("glyphicon glyphicon-ok");
	$("." + glyphicon).addClass("glyphicon glyphicon-remove");
	$("." + glyphicon).css("color", "red");
	$(inputField).removeClass("correct");
	$(inputField).addClass("wrong");

}

function validateCurrentInput(name, current) {

	var boo = false;

	switch (name) {

	case "firstName":
		boo = validateFirstOrLastName(current);
		break;

	case "lastName":
		boo = validateFirstOrLastName(current);
		break;

	case "username":
		boo = validateUsername(current);
		break;

	case "password":
		boo = validatePassword(current);
		break;

	case "email":
		boo = validateEmail(current);
		break;
	}

	return boo;
}

$("#register_button").on("click", function(event) {
	event.preventDefault();
	$("#register input").each(function() {
		if ($(this).hasClass("wrong")) {
			return false;
		}
	})
	ajaxCall();
})

function ajaxCall() {

	var userdata = {}

	$("#register input").each(function() {
		userdata[$(this).attr("name")] = $(this).val();
	})
	var token = $("meta[name='_csrf.parameterName']").attr("content");

	$.ajax({

		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-Token', token);
		},
		url : "register_new_user_ajax",
		data : JSON.stringify(userdata),
		type : 'POST',
		contentType : 'application/json',
		dataType : 'text',

		success : function(data) {

			$("#registration_message").text(data);
			$("#register").trigger("reset");
			$("#register input").removeClass("correct");
			$("#register label").removeClass("glyphicon glyphicon-ok");
		},
		error : function(e) {
			console.log(e);
		}
	})
}

function checkCredentials(current, requiredUrl) {

	var boo = false;

	$.ajax({

		url : "check" + requiredUrl,
		data : "credentials=" + current,
		type : "GET",
		async : false,
		success : function(response) {
			boo = response;
		},
		error : function(e) {
			alert("error " + e)
		}

	})

	return boo;
}

function registrationMessage(message) {
	$("#registration_message").text(message).delay(2500).fadeOut(1500);

}
