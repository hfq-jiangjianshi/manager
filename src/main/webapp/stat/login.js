var opt = {
	login : function() {
		var account = $("#account").val();
		var password = $("#password").val();
		if (account.length == 0) {
			$("#msg_top").text("用户名不能为空").css("color", "red");
			return;
		}
		if (password.length == 0) {
			$("#msg_top").text("密码不能为空").css("color", "red");
			return;
		}

		$.ajax({
			url : '/login',
			type : 'POST',
			data : {
				'account' : account,
				'password' : password
			},
			dataType : 'json',
			success : function(result) {

				if (result.status == 1) {
					$("#msg_top").text(result.msg).css("color", "red");
				} else {
					window.top.location.href = "/main";
				}
			},
			error : function() {
				alert("系统异常，请联系管理员")
			}
		})
	}
}
