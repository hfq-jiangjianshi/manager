opt = {

	save : function() {
		var data = serializeObject($('#editForm'));
		$.ajax({
			url : '/house/updateHouseInfo',
			type : 'POST',
			data : data,
			dataType : 'json',
			success : function(data) {
				alert(data.msg)
			},
			error : function() {
				alert("操作失败")
			}
		})
	},
	bindClickOnCheckBox : function() {
		$("input[name=houseSetting]").click(function() {

			// if ($(this).attr("checked") == "checked") {
			// }
			var inputDom = $(this);
			var settingId = $(this).val();
			var houseSellId = $("#houseSellId").val();
			var settingDesc = $(this).next("span").text();
			var isDelete = 1; // 默认为删除，只能settingId ！= 0 时才会用到此值
			$.ajax({
				url : '/house/updateOrAddSetting',
				type : 'POST',
				async : true,
				data : {
					'settingId' : settingId,
					'houseSellId' : houseSellId,
					'roomId' : 0,
					'settingDesc' : settingDesc,
					'isDelete' : isDelete
				},
				dataType : 'json',
				success : function(result) {
					if (settingId == 0) {
						$(inputDom).val(result.data)
					} else {
						$(inputDom).val('0');
					}
				},
				error : function() {
					alert("操作失败")
				}
			})

		});
	},

	selectFirstImg : function() {
		$("input[name=firstImg]").click(function() {
			var isDefault = 1; // 1 为首图
			var imgId = $(this).val();
			var houseSellId = $("#houseSellId").val();
			$.ajax({
				url : '/house/setImgDefaultOrDelete',
				type : 'POST',
				data : {
					'houseSellId' : houseSellId,
					'imgId' : imgId,
					'isDefault' : isDefault
				},
				dataType : 'json',
				success : function(result) {
				},
				error : function() {
					alert("操作失败")
				}
			})
		});
	},

	deleteImg : function() {
		$("input[name=validImg]").click(function() {
			var isDelete = 1; // 1 为删除状态
			if ($(this).attr("checked") == "checked") {
				isDelete = 0;
			}
			var imgId = $(this).val();
			var houseSellId = $("#houseSellId").val();
			$.ajax({
				url : '/house/setImgDefaultOrDelete',
				type : 'POST',
				data : {
					'houseSellId' : houseSellId,
					'imgId' : imgId,
					'isDelete' : isDelete
				},
				dataType : 'json',
				success : function(result) {
				},
				error : function() {
					alert("操作失败")
				}
			})
		});
	}

}

function loadData() {
	$('#isSale').combobox({
		data : auto_js_codes_imp['is_sales_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight : 'auto'
	});

	$('#status').combobox({
		data : auto_js_codes_imp['house_statuss_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight : 'auto'
	});

	$('#entireRent').combobox({
		data : auto_js_codes_imp['rent_types_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight : 'auto'
	});

	$('#orientations').combobox({
		data : auto_js_codes_imp['orientations_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight : 'auto'
	});

	$('#decoration').combobox({
		data : auto_js_codes_imp['decorations_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight : 'auto'
	});

	$("#canCheckinDate").click(function() {
		WdatePicker();
	});

	// 转换页面上的值
	var $span = $("#sourceFlag");
	$span.text(get_js_codeText('data_sources_js', $span.text()))

}

$(function() {
	opt.bindClickOnCheckBox();
	opt.selectFirstImg();
	opt.deleteImg();
	loadData();
})