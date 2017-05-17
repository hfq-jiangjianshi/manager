/**
 * 屏蔽右键
 * @author syw
 * @requires jQuery
 */
$(document).bind('contextmenu', function() {
	//return false;
});

/**
 * 禁止复制
 * @author syw
 * @requires jQuery
 */
$(document).bind('selectstart', function() {
	// return false;
});



/**
 * 将form表单元素的值序列化成对象
 * @example serializeObject($('#formId'))
 * @author syw
 * @returns object
 */
serializeObject = function(form,extParams) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	if(extParams){
		o = $.extend(extParams,o);
	}
	return o;
};

/**
 * 增加formatString功能
 * @author syw
 * @example formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * @returns 格式化后的字符串
 */
formatString = function(str) {
	for (var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

createButtonStr = function(icon,action,text){
	return "<a style='margin-left:6px;' onclick=\""+action+"\" href=\"#\">"+text+"</a>";
};

/**
 * 改变jQuery的AJAX默认属性和方法
 * @author syw
 * @requires jQuery
 */
$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		try {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} catch (e) {
			alert(XMLHttpRequest.responseText);
		}
	}
});

//扩展jQuery，增加toolTip工具提示框
/*
 * 用法如下：
 *	HTML部分: <xxx class="quicktip" title="要提示的内容"></xxx> （可用于任何类型的Html标签）
 *	JS  部分: 	$('.quicktip').quberTip({
					  speed:200
				});
 */
jQuery.fn.quberTip = function (options) {
    var defaults = {
        speed: 500,
        xOffset: 10,
        yOffset: 10
    };
    var options = $.extend(defaults, options);
    return this.each(function () {
        var $this = jQuery(this);
        if ($this.attr('title') != undefined) {
            //Pass the title to a variable and then remove it from DOM
            if ($this.attr('title') != '') {
                var tipTitle = ($this.attr('title'));
            } else {
                var tipTitle = 'QuberTip';
            }
            //Remove title attribute
            $this.removeAttr('title');
            $(this).hover(function (e) {
                $("body").append("<div id='tooltip'>" + tipTitle + "</div>");
                $("#tooltip").css({ "position": "absolute",
                    "z-index": "9999",
                    "background": "#D3DDF5",
                    //"background-image": "url(../../Quber_Image/Quber_Common/Quber_TB_TitltBG.png)",
                    "padding": "5px",
                    "opacity": "0.9",
                    "border": "1px solid #A3C0E8",
                    "-moz-border-radius": "3px",
                    "border-radius": "3px",
                    "-webkit-border-radius": "3px",
                    "font-weight": "normal",
                    "font-size": "12px",
                    "display": "none"
                });
                $("#tooltip").css("top", (e.pageY + defaults.xOffset) + "px")
                			 .css("left", (e.pageX + defaults.yOffset) + "px")
                			 .fadeIn(options.speed);
            }, function () {
                $("#tooltip").remove();
            });
            
            $(this).mousemove(function (e) {
                $("#tooltip") .css("top", (e.pageY + defaults.xOffset) + "px")
                				.css("left", (e.pageX + defaults.yOffset) + "px");
            });
        }
    });
};
$(document).ready(function(){
	$('.quicktip').quberTip({
	  speed:200
	});
});
//下拉组件 封装
var comboboxEx = function(options){
	var url = basePath + "/commonCtrl/loadData.c?t=code-sql&s=comboboxData&tc="+options.ctype_code;
	options = options || {};
	var opts = $.extend({
		url:url,
		valueField : 'code_value',
		textField : 'code_name'
	},options);
	return  $(opts.inputId).combobox(opts);  
}
//级联下拉组件【父级】 封装
var comboboxCascade = function(options){
	var url = basePath + "/commonCtrl/loadData.c?t=sys_treecode-sql&s=comboboxCascade_type&ctype_code="+options.ctype_code;
	options = options || {};
	var opts = $.extend({
		url:url,
		valueField : 'code_value',
		textField : 'code_name'
	},options);
	this.getValue=function(){
		return this.comboboxObj.combobox("getValue");
	};
	this.comboboxObj = $(opts.inputId).combobox(opts); 
	return this; 
}
//级联下拉组件【子级】 封装
var comboboxCascadeSub = function(options){
	var url = basePath + "/commonCtrl/loadData.c?t=sys_treecode-sql&s=comboboxCascade_code&pId=null";
	options = options || {};
	var opts = $.extend({
		url:url,
		valueField : 'code_value',
		textField : 'code_name'
	},options);
	this.comboboxObj = $(opts.inputId).combobox(opts); 
	this.show = function(nextF){
		var pId = $(opts.parentComboId).combobox("getValue");
		var url = basePath + "/commonCtrl/loadData.c?t=sys_treecode-sql&s=comboboxCascade_code&pId="+pId;
		this.comboboxObj.combobox('reload',url);
		nextF && nextF();//回调函数
	};	
	this.getValue=function(){
		return comboboxObj.combobox("getValue");
	};
	return this;
}
function getPersonInfo( next ){
    var myInfo = PersonInfo;
    next && next( myInfo );
}

