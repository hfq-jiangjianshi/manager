function gotoPage(curPage){
	var curUrl = window.location.href;
	$("#page").val(curPage);
	$("#pageForm").attr("action",curUrl);
	$("#pageForm").submit();
}
function jumpToPage(){
	var curUrl = window.location.href;
	var curPage = $("#jumpToPageInput").val();
	if(curPage>totalPages){
		curPage = totalPages;
	}else if(curPage<1){
		curPage = 1;
	}
	$("#page").val(curPage);
	$("#pageForm").attr("action",curUrl);
	$("#pageForm").submit();
}

