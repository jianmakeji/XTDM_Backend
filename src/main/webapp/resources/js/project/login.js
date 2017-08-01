$(document).ready(function() {

	

	$("#usernameLabel").addClass('active');
	$("#passwordLabel").addClass('active');

	$("#submitBtn").click(function() {

		let
		username = $("#username").val();
		let
		password = $("#password").val();

		let
		requestData = {
			"email" : username,
			"password" : password
		};

		$.ajax({

			type : "POST",
			url : "../user/ajaxLogin",
			contentType : 'application/json',
			dataType : "json",
			data : JSON.stringify(requestData),
			beforeSend : function() {
				$("#circleProgress").show();
			},
			success : function(data) {
				if (data.resultCode == 200) {
					location.href = "index.html";
				} else {
					$("#loginMsg").html(data.message);
					$("#circleProgress").hide();
				}
			},
			statusCode : {
				404 : function() {
					$("#circleProgress").hide();
					Materialize.toast('没有加载到相应页面!', 4000);
				},
				500 : function() {
					$("#circleProgress").hide();
					Materialize.toast('服务器内部错误!', 4000);
				}
			}
		});
	});
});
