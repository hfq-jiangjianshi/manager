
//添加选项卡
function addTab(subtitle,url){
	if(subtitle==""){
		$.messager.alert('错误信息','无法创建空标签页');
		return;
	}
	if(!$('#tabs').tabs('exists',subtitle)){
		var tabs=$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			cache:false,
			closable:true,
			width:$('#center').width()-10,
			height:$('#center').height()-26
		});
		dblclickReflashTab();
	}else{
		$('#tabs').tabs('select',subtitle);
		//updateTAB(subtitle,url);
	}
}
//关闭选项卡
function tabCloseAll(){
	$('.tabs-inner span').each(function(i,n){
		var t = $(n).text();
		if(t!='首页'){
			$('#tabs').tabs('close',t);
		}
	});	
}
//添加首页选项卡
function addSysTab(canUseNav){
	var tabs=$('#tabs').tabs('add',{
		title:'首页',
		content:createFrame('sysTab/sys_Msg.html?param='+canUseNav),
		cache:false,
		closable:true,
		width:$('#center').width()-10,
		height:$('#center').height()-26
	});
}

//更新 TAB 内容
function updateTAB(title,url){
	var currTab=$('#tabs').tabs('getTab',title);
	$('#tabs').tabs('update',{
		tab:currTab,
		options: {
			content:createFrame(url)
	    }
	});
}


//function tabClose() {}
function tabCloseEven(){}
function dblclickReflashTab()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		//$('#tabs').tabs('close',subtitle);
		var tab=$('#tabs').tabs('getTab',subtitle);
		var frame=$('iframe', tab);
		frame[0].contentWindow.location.reload()
	});
}
//
//
////绑定右键菜单事件
//function tabCloseEven()
//{
//	//关闭当前
//	$('#mm-tabclose').click(function(){
//		var currtab_title = $('#mm').data("currtab");
//		$('#tabs').tabs('close',currtab_title);
//	});
//	//全部关闭
//	$('#mm-tabcloseall').click(function(){
//		$('.tabs-inner span').each(function(i,n){
//			var t = $(n).text();
//			if(t!='首页'){
//				$('#tabs').tabs('close',t);
//			}
//			
//		});	
//	});
//	//关闭除当前之外的TAB
//	$('#mm-tabcloseother').click(function(){
//		var currtab_title = $('#mm').data("currtab");
//		$('.tabs-inner span').each(function(i,n){
//			var t = $(n).text();
//			if(t!=currtab_title&&t!='首页')
//				$('#tabs').tabs('close',t);
//		});	
//	});
//	//关闭当前右侧的TAB
//	$('#mm-tabcloseright').click(function(){
//		var nextall = $('.tabs-selected').nextAll();
//		if(nextall.length==0){
//			$.messager.alert('错误信息','右侧无标签页');
//			return false;
//		}
//		nextall.each(function(i,n){
//			var t=$('a:eq(0) span',$(n)).text();
//			if(t!='首页'){
//				$('#tabs').tabs('close',t);
//			}
//		});
//		return false;
//	});
//	//关闭当前左侧的TAB
//	$('#mm-tabcloseleft').click(function(){
//		var prevall = $('.tabs-selected').prevAll();
//		if(prevall.length==0){
//			$.messager.alert('错误信息','左侧无标签页');
//			return false;
//		}
//		prevall.each(function(i,n){
//			var t=$('a:eq(0) span',$(n)).text();
//			if(t!='首页'){
//				$('#tabs').tabs('close',t);
//			}
//		});
//		return false;
//	});
//
//	//退出
//	$("#mm-exit").click(function(){
//		$('#mm').menu('hide');
//	});
//}

function createFrame(url)
{
	var s = '<iframe id="myframe" name="myframe" onload="window.status=\'成功加载完毕\'" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:98%;"></iframe>';
	
	return s;
}


function purge(d) {
	
	$( "*", d).add([d]).each(function(i,v){
		$.event.remove(v);
		$.removeData(v);
	});
}

$(function(){
	$('#tabs').tabs({
		onBeforeClose:function(title){
			if(title=='首页'){
				return false;
			}
			var tab=$('#tabs').tabs('getTab',title);
			var frame=$('iframe', tab);
			if(frame.length>0){
				for(var i=0;i<frame.length;i++){
					frame[i].src="about:blank";
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
					frame[i]=null;
				}
				if($.browser.msie){setTimeout(CollectGarbage, 1);}
			}
			frame.remove();
			frame=null;
		},
		onSelect:function(title){
			var tab=$('#tabs').tabs('getTab',title);
			var frame=$('iframe', tab);
			frame.focus();
		}
	});
});



