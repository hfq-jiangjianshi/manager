
/**
 * 更改easyui加载panel时的提示文字
 * 
 * @author syw
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.panel.defaults, {
	loadingMessage : '加载中....'
});

$.extend($.fn.validatebox.methods, {
		remove : function(jq,newposition) {
		return jq.each(function(){
			$(this).removeClass('validatebox-text validatebox-invalid').unbind('focus.validatebox').unbind('blur.validatebox');		
		});	
		},
		reduce : function(jq,newposition) {
			return jq.each(function(){
				var opt=$(this).data().validatebox.options;
				$(this).addClass('validatebox-text').validatebox(opt);
			});	
			}
});

/**
 * 输入非法特殊字符时的提示文字
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	forbidSymbol : {
		validator : function(evt) {
			if (evt) {
				return /^[^`,，“”‘’·~;:!￥！。…….&#$%\s\^*\'\"\+\(\)\_\=\-\\]+$/i.test(evt);
			}

		},
		message : "请不要输入特殊字符"
	}
});

/**
 * 输入非法特殊字符时的提示文字
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	forbidSymbolAnd : {
		validator : function(evt) {
			if (evt) {
				return /^[^`￥&#$\^*\+\_\=\-\\]+$/i.test(evt);
			}

		},
		message : "请不要输入特殊字符"
	}
});

/**
 * 检查密码是否一致
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: "密码不一致"   
    }    
});  



/**
 * 输入全角字符时的提示文字
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	forbidQuanjiao : {
		validator : function(evt) {
			if (evt) {
				return /^[x00-xff\u4E00-\u9FA5`,，、“”‘’·~;:!￥！。…….&#$%\s\^*\'\"\+\(\)\_\=\-\\]+$/i.test(evt);
			}

		},
		message : "请不要输入全角字符"
	}
});

/**
 * 输入空格时的提示文字
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	spaceBar : {
		validator : function(evt) {
			if (evt) {
				return /^[^\s]+$/i.test(evt);
			}

		},
		message : "请不要输入空格"
	}
});

/**
 * 限制输入框固定长度
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {    
    fixLength: {    
        validator: function(value, param){    
            return value.length == param[0];    
        },    
        message: '请输入{0}位'   
    }    
}); 

/**
 * 限制输入框固定长度
 * 
 * @author 张治洋
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {    
    fixLengthzzy: {    
        validator: function(value, param){    
            return value.length == param[0];    
        },    
        message: '请输入不超过{0}位的数值'   
    }    
}); 


/**
 * 输入数字验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkNumber : {
		validator : function(evt) {
			return /^[0-9]+$/i.test(evt);
		},
		message : "只能输入数字"
	}
});

/**
 * 输入数字验证（允许浮点逗号）
 * <pre>
 * 	参数说明,若无参数，则均为默认值（-1）
 * <code>
 * 	checkNumeric[{精度：默认为不限制精度}, {数值长度：默认不限制长度}, {数值最小位数：默认不限制位数长度}]
 * </code>
 * </pre>
 * 
 * @author Cyrus
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkNumeric: {
		validator : function(evt, param) {
			if(!param) param = [-1, -1, -1];
			var precision = param[0]||-1;
			var length = param[1]||-1;
			var minlen = param[2]||-1;
			var evtlen = replaceAll(evt, ',', '').length;
			var pointIndex = replaceAll(evt, ',', '').lastIndexOf('.');
			if(!/^-?[0-9]+(,\d{3})*(\.\d+)?$/i.test(evt)){
				$.fn.validatebox.defaults.rules.checkNumeric.message = "该值必须是数值类型";
				return false;
			}
			if(precision != -1 && (pointIndex > -1 && evtlen-pointIndex-1 > precision)){
				$.fn.validatebox.defaults.rules.checkNumeric.message = "精度需小于或等于"+precision+"位小数";
				return false;
			}
			if(length != -1 && ((pointIndex > -1 && pointIndex > length) || (pointIndex == -1 && evtlen > length))){
				$.fn.validatebox.defaults.rules.checkNumeric.message = "数值长度需小于或等于"+length+"位整数";
				return false;
			}
			if(minlen != -1 && ((pointIndex > -1 && pointIndex < minlen) || (pointIndex == -1 && evtlen < minlen))){
				$.fn.validatebox.defaults.rules.checkNumeric.message = "数值长度需大于或等于"+minlen+"位整数";
				return false;
			}
			return true;
		},
		message : "该值必须是数值类型"
	}
});

function replaceAll(src, exp, str){
	var _replaced = src.replace(exp, str);
	if (_replaced == src)
		return src;
	return replaceAll(_replaced);
}

/**
 * 小数点输入框输入数字验证
 * 
 * @author 张治洋
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkNumberzzy : {
		validator : function(evt) {
		//	return /^[0-9]+$/i.test(evt);;
        return /^\d+(\d.\d{2})?$/i.test(evt);
		},
		message : "只能输入数字,      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 小数后留2位"
	}
});



/**
 * 输入电话号码，传真验证（数字及-）
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkTelephone : {
		validator : function(evt) {
//			return /^[0-9\-]+$/i.test(evt);
//			return /^(\d{3,4}-)?\d{7,8}$/i.test(evt);
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(evt);
		},
		message : "请输入正确格式的电话号码，如：010-85235320 或 85235320"
	}
});

/**
 * 客户服务或投诉电话（数字或-都可以）
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkTelephone_keFuOrTouSu : {
		validator : function(evt) {
			return /^[0-9\-]+$/i.test(evt);
		},
		message : "请输入正确格式的电话号码"
	}
});

/**
 * 输入电话号码/手机号码（数字及-）
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkTelOrMobil : {
		validator : function(evt) {
//			return /^[0-9\-]+$/i.test(evt);

			if(/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(evt)){
				return true;
			}else{
				if(/^\d{11}$/i.test(evt)){
					return true;
				}else{
					$.fn.validatebox.defaults.rules.checkTelOrMobil.message='请输入正确格式的电话号码/手机号码';
					return false;
				}
			}
		},
		message : ""
	}
});

/**
 * 输入数字和字母（身份证）
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	forbidIdCard: {
		validator : function(evt) {
//			return /^[A-Za-z0-9]+$/i.test(evt);;
			return /^[0-9]{17}[0-9xX]+$/i.test(evt);;
		},
		message : "请输入正确格式的身份证号码"
	}
});





/**
 * 输入英文验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkEnglish: {
		validator : function(evt) {
			return /^[A-Za-z]+$/i.test(evt);;
		},
		message : "只能输入英文字母"
	}
});

/**
 * 邮编验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	postalCode: {
		validator : function(evt) {
			return /^[0-9][0-9]{5}$/i.test(evt);
		},
		message : "请输入正确格式的邮编"
	}
});
/**
 * 年份验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkYear: {
		validator : function(evt) {
			return /^[0-9]{4}$/i.test(evt);
		},
		message : "请输入正确格式的年份，如：2014"
	}
});
/**
 * 手机号码验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	mobilePhone: {
		validator : function(evt) {
			//return /^1[3|4|5|8][0-9]\d{4,8}$/i.test(evt);
			return /^\d{11}$/i.test(evt);
		},
		message : "请输入正确格式的手机号码"
	}
});
/**
 * 传真号码验证
 * 
 * @author 王雨欣
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	fax: {
		validator : function(evt) {
			return /^(\d{3,4}-)?\d{7,8}$/i.test(evt);
		},
		message : "请输入正确格式的传真号码，如：010-85235320"
	}
});


/**
 * 更改easyui加载grid时的提示文字
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.datagrid.defaults, {
	loadMsg : '数据加载中....'
});

/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for ( var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, onMove);
$.extend($.fn.window.defaults, onMove);
$.extend($.fn.panel.defauzwxs, onMove);

/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, onLoadError);
$.extend($.fn.treegrid.defaults, onLoadError);
$.extend($.fn.tree.defaults, onLoadError);
$.extend($.fn.combogrid.defaults, onLoadError);
$.extend($.fn.combobox.defaults, onLoadError);
$.extend($.fn.form.defaults, onLoadError);

/**
 * 扩展combobox在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combobox.defaults, {
	onShowPanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combobox('textbox').val();
			var _combobox = $(this);
			if (_value.length > 0) {
				$.post(_options.url, {
					q : _value
				}, function(result) {
					if (result && result.length > 0) {
						_combobox.combobox('loadData', result);
					}
				}, 'json');
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combobox('getData');/* 下拉框所有选项 */
			var _value = $(this).combobox('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for ( var i = 0; i < _data.length; i++) {
				if (_data[i][_options.valueField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combobox('setValue', '');
			}
		}
	}
});

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
	onShowPanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combogrid('textbox').val();
			if (_value.length > 0) {
				$(this).combogrid('grid').datagrid("load", {
					q : _value
				});
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
			var _value = $(this).combogrid('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for ( var i = 0; i < _data.length; i++) {
				if (_data[i][_options.idField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combogrid('setValue', '');
			}
		}
	}
});

/**
 * 扩展validatebox，添加新的验证功能
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {/* 验证两次密码是否一致功能 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 * 
 */
loadFilter = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField,stateField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			stateField=opt.stateField|| 'state';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]]
						&& data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					data[i]['id'] = data[i][idField];
					
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					data[i]['id'] = data[i][idField];	
					data[i]['state'] = data[i][stateField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, loadFilter);
$.extend($.fn.tree.defaults, loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]]
						&& data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

/**
 * 创建一个模式化的dialog
 * 
 * @author 张万喜
 * 
 * @requires jQuery,EasyUI
 * 
 */
modalDialog = function(options) {
	if(window.parent != window)
		return window.parent.modalDialog(options);
	options = options || {};
	options.width = options.width || 640;
	options.height = options.height || 480;
	//自动调整窗口大小
	if(document.body.offsetHeight - options.height < 50)
		options.height = document.body.offsetHeight - 50;
	var opts = $.extend({
		title : '&nbsp;',
		width : options.width,
		height : options.height,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
			if(window.__currentDialog)
				window.__currentDialog.pop();
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="'
				+ options.url
				+ '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	if(!window.__currentDialog)
		window.__currentDialog = new Array();
	window.__currentDialog.push($('<div/>').dialog(opts));
	return window.__currentDialog[window.__currentDialog.length-1];
};

currentDialog = function(){
	if(window.parent != window)
		return window.parent.currentDialog();
	if(window.__currentDialog)
		return window.__currentDialog[window.__currentDialog.length-1];
	return null;
};

/**
 * 等同于原form的load方法，但是这个方法支持{data:{name:''}}形式的对象赋值
 */
$.extend($.fn.form.methods, {
	loadData : function(jq, data) {
		return jq.each(function() {
			load(this, data);
		});

		function load(target, data) {
			if (!$.data(target, 'form')) {
				$.data(target, 'form', {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var opts = $.data(target, 'form').options;

			if (typeof data == 'string') {
				var param = {};
				if (opts.onBeforeLoad.call(target, param) == false)
					return;

				$.ajax({
					url : data,
					data : param,
					dataType : 'json',
					success : function(data) {
						_load(data);
					},
					error : function() {
						opts.onLoadError.apply(target, arguments);
					}
				});
			} else {
				_load(data);
			}
			function _load(data) {
				var form = $(target);
				var formFields = form
						.find("input[name],select[name],textarea[name]");
				formFields
						.each(function() {
							var name = this.name;
							var value = jQuery.proxy(function() {
								try {
									return eval('this.' + name);
								} catch (e) {
									return "";
								}
							}, data)();
							var rr = _checkField(name, value);
							if (!rr.length) {
								var f = form.find("input[numberboxName=\""
										+ name + "\"]");
								if (f.length) {
									f.numberbox("setValue", value);
								} else {
									$("input[name=\"" + name + "\"]", form)
											.val(value);
									$("textarea[name=\"" + name + "\"]", form)
											.val(value);
									$("select[name=\"" + name + "\"]", form)
											.val(value);
								}
							}
							_loadCombo(name, value);
						});
				opts.onLoadSuccess.call(target, data);
				$(target).form("validate");
			}

			function _checkField(name, val) {
				var rr = $(target).find(
						'input[name="' + name + '"][type=radio], input[name="'
								+ name + '"][type=checkbox]');
				rr._propAttr('checked', false);
				rr
						.each(function() {
							var f = $(this);
							if (f.val() == String(val)
									|| $.inArray(f.val(), val) >= 0) {
								f._propAttr('checked', true);
							}
						});
				return rr;
			}

			function _loadCombo(name, val) {
				var form = $(target);
				var cc = [ 'combobox', 'combotree', 'combogrid', 'datetimebox',
						'datebox', 'combo' ];
				var c = form.find('[comboName="' + name + '"]');
				if (c.length) {
					for ( var i = 0; i < cc.length; i++) {
						var type = cc[i];
						if (c.hasClass(type + '-f')) {
							if (c[type]('options').multiple) {
								c[type]('setValues', val);
							} else {
								c[type]('setValue', val);
							}
							return;
						}
					}
				}
			}
		}
	}
});

/**
 * 更换主题
 * 
 * @author 张万喜
 * @requires jQuery,EasyUI
 * @param themeName
 */
changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			try {
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			} catch (e) {
				try {
					ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
				} catch (e) {
				}
			}
		}
	}

	cookie('easyuiTheme', themeName, {
		expires : 7
	});
};

/**
 * 滚动条
 * 
 * @author 张万喜
 * @requires jQuery,EasyUI
 */
progressBar = function(options) {
	if (typeof options == 'string') {
		if (options == 'close') {
			$('#progressBarDiv').dialog('destroy');
		}
	} else {
		if ($('#progressBarDiv').length < 1) {
			var opts = $
					.extend(
							{
								title : '&nbsp;',
								closable : false,
								width : 300,
								height : 60,
								modal : true,
								content : '<div id="progressBar" class="easyui-progressbar" data-options="value:0"></div>'
							}, options);
			$('<div id="progressBarDiv"/>').dialog(opts);
			$.parser.parse('#progressBarDiv');
		} else {
			$('#progressBarDiv').dialog('open');
		}
		if (options.value) {
			$('#progressBar').progressbar('setValue', options.value);
		}
	}
};

/**
 * 获取浏览器根window对象
 * @return window对象
 */
getRootWindow = function(){
	if(window.parent != window)
		return window.parent.getRootWindow();
	return window;
};

/**
 * @author syw
 * 	数据字典 下拉列表控件
 * 控件参数说明：
 * (必选参数)inputId：要初始化的input id。如：<input id="type" name="type"/>  			
 * (必选参数)dictCode：字典表code。如：数据库中X27_DICT表中，[用户类型]Code码为：5050 			
 * (可选参数)selectedEnumCode：默认选中的选项,如：数据库表X27_ENUM中，[券商]Code码为：5050-1010 
 */
var dict_select = function(options){
	var url;
	if(options.selectedEnumCode){
		url = webPath+'/dict_select_datasource.dict?dictCode='+options.dictCode+'&selectedEnumCode='+options.selectedEnumCode;
	}else{
		url = webPath+'/dict_select_datasource.dict?dictCode='+options.dictCode;
	}
	options = options || {};
	var opts = $.extend({
		url:url,    
	    valueField:'id', 
	    textField:'text',
	    panelHeight:'auto',
	    editable:false
	}, options);
	return $('#'+options.inputId).combobox(opts);
}

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

/**
 * 输入数字验证（允许浮点逗号）
 * <pre>
 * 	参数说明,若无参数，则均为默认值（-1）
 * <code>
 * 	checkNumeric[{精度：默认为不限制精度}, {数值长度：默认不限制长度}, {数值最小位数：默认不限制位数长度}]
 * </code>
 * </pre>
 * 
 * @author Cyrus
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	checkNumericm: {
		validator : function(evt, param) {
			if(!param) param = [-1, -1, -1];
			var precision = param[0]||-1;
			var length = param[1]||-1;
			var minlen = param[2]||-1;
			if("-"==(evt.substring(0,1))){
				evt = evt.substring(1,evt.length+1);
			}
			var evtlen = replaceAll(evt, ',', '').length;
			var pointIndex = replaceAll(evt, ',', '').lastIndexOf('.');
			if(!/^[0-9]+(,\d{3})*(\.\d+)?$/i.test(evt)){
				$.fn.validatebox.defaults.rules.checkNumericm.message = "该值必须是数值类型";
				return false;
			}
			if(precision != -1 && (pointIndex > -1 && evtlen-pointIndex-1 > precision)){
				$.fn.validatebox.defaults.rules.checkNumericm.message = "精度需小于或等于"+precision+"位小数";
				return false;
			}
			if(length != -1 && ((pointIndex > -1 && pointIndex > length) || (pointIndex == -1 && evtlen > length))){
				$.fn.validatebox.defaults.rules.checkNumericm.message = "数值长度需小于或等于"+length+"位整数";
				return false;
			}
			if(minlen != -1 && ((pointIndex > -1 && pointIndex < minlen) || (pointIndex == -1 && evtlen < minlen))){
				$.fn.validatebox.defaults.rules.checkNumericm.message = "数值长度需大于或等于"+minlen+"位整数";
				return false;
			}
			return true;
		},
		message : "该值必须是数值类型"
	}
});


