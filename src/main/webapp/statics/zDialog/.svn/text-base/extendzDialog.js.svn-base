function getParentWindow(){
	if(window.ownerDialog){
		return ownerDialog.openerWindow;
	}else{
		return window;
	}
}

function openDialog(width,height,title,url,closeF){
	var diag = new Dialog();
	diag.Width = width;
	diag.Height = height;
	diag.Title = title;
	diag.URL = url;
	diag.CancelEvent=function(){
		closeF && closeF();//回调函数
		diag.close();
	};
	diag.show();
	return diag;
}
