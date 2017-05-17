function login() {
	if ($('#loginForm').form('validate')) {
//		var url = basePath + "/sysbase/checkLogin.c";
//		$('#loginForm').form('submit', {
//			url : url,
//			success : function(result) {
//				var result = eval('(' + result + ')');
//				if (result.isSuccess) {
//					$('#msg').html(result.msg);
//					window.location.href = basePath + "/sysbase/index.c";
//				} else {
//					$('#msg').html(result.msg);
//				}
//			}
//		});
		$('#loginForm').submit();
	}
}
$(document).ready(function(){
	if(window.top!=window){
		window.top.location.href=window.location.href;
	}
});

