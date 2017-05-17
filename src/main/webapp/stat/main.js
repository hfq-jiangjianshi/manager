//退出 
function logout() {
	$.ajax({
		url : basePath + "/sysbase/logout.c",
		type : 'post',
		success : function(res) {
			document.location.href = basePath + "/sysbase/login.c";
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

	menuClick();
});
