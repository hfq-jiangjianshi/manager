//退出 
function logout() {
	$.ajax({
		url : basePath + "/logout",
		type : 'post',
		success : function(res) {
			if(res.status ==0){
				document.location.href = basePath + "/index";
			}
		},
		error : function() {
			alert("请求失败")
		}
	});
}

function getLoginUser() {
	$.ajax({
		url : basePath + "/getLoginUser",
		type : 'post',
		success : function(res) {
			console.log(res);
			$("#com_label").text(res.data.realName).css("color", "#15428B");
			$("#login_time").text(res.data.lastLoginTime).css("color", "#15428B");
		},
		error : function() {
			alert("请求失败")
		}
	});

}

function menuClick() {
	$('#treeMenu').tree({
		onClick : function(node) {
			if (node.text == "房源管理") {
				addTab(node.text, basePath + "/house/toPage");
			} else if (node.text == "房间管理") {
				addTab(node.text, basePath + "/room/toPage");
			}
		}
	});
}

$(function() {
	getLoginUser();
	menuClick();
});
