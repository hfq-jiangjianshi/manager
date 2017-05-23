var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 20, 30, 50, 100 ],
	columns : [ [ {
		field : 'id',
		title : 'id',
		hidden : true
	}, {
		field : 'houseSellId',
		title : '房源ID',
		align : 'left',
		sortable : true,
		width : 140
	}, {
		field : 'province',
		title : '省份',
		sortable : true,
		width : 100
	}, {
		field : 'city',
		title : '城市',
		sortable : true,
		width : 100
	}, {
		field : 'district',
		title : '行政区',
		sortable : true,
		width : 100
	}, {
		field : 'bizname',
		title : '商圈',
		sortable : true,
		width : 150
	}, {
		field : 'communityName',
		title : '小区',
		sortable : true,
		width : 150
	}, {
		field : 'houseNo',
		title : '门牌号',
		sortable : true,
		width : 80
	}, {
		field : 'livingroomNums',
		title : '厅室',
		hidden : true,// 不显示
		width : 150
	}, {
		field : 'bedroomNums',
		title : '厅室',
		width : 80,
		formatter : function(value, row) {
			return value + "室" + row.livingroomNums + "厅";
		}
	}, {
		field : 'status',
		title : '房源状态',
		sortable : true,
		width : 80,
		formatter : function(value, row) {
			return get_js_codeText('house_statuss_js', value);
		}
	}, {
		field : 'rentPriceMonth',
		title : '月租金',
		sortable : true,
		width : 80,
		formatter : function(value) {
			if (value != '') {
				return value / 100;
			}
			return '';
		}
	}, {
		field : 'entireRent',
		title : '出租类型',
		sortable : true,
		width : 80,
		formatter : function(value, row) {
			return get_js_codeText('rent_types_js', value);
		}
	}, {
		field : 'periodMonth',
		title : '每次付几个月的租金',
		hidden : true,// 不显示
		width : 60
	}, {
		field : 'depositMonth',// 押金押几个月
		title : '押几付几',
		sortable : true,
		width : 80,
		formatter : function(value, row) {
			if (value != 0 && row.periodMonth != 0)
				return "押" + value + "付" + row.periodMonth;
			else
				return "";
		}
	}, {
		field : 'source',
		title : '所属公司',
		sortable : true,
		width : 200
	}, {
		field : 'imgCount',
		title : '图片数',
		sortable : true,
		width : 80,
		formatter : function(value, row) {
			if (value != "") {
				return value + "张";
			}
			return '';
		}
	}, {
		field : 'creationDate',
		title : '创建时间',
		sortable : true,
		width : 150
	}, {
		field : 'lastChangeDate',
		title : '更新时间',
		sortable : true,
		width : 150
	}, {
		field : 'isSale',
		title : '是否上架',
		sortable : true,
		width : 80,
		formatter : function(value, row) {
			return get_js_codeText('is_sales_js', value);
		}
	} ] ]
};

$(document).ready(function() {
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/house/queryHouseDetailList',
		// queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		fit : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onDblClickRow : function(rowIndex, rowData) {
			opt.edit(rowIndex, rowData);
		}
	});

	loadData();
}); // $(document).ready--end

opt = {
	// 信息查询
	search : function() {
		grid.datagrid('reload', serializeObject($('#searchForm')));
	},
	// 重置查询条件
	resetSearch : function() {
		$('#searchForm input').val('');
		grid.datagrid('reload', dataGridParams.queryParams);
	},
	edit : function(rowIndex, rowData) {
		var url = basePath + "/house/toEdit?houseSellId=" + rowData.houseSellId;
		openDialog(1000, 1000, "编辑房源", url, function() {
			// opt.search();
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

	// $("#companyName").click(function() {
	// WdatePicker();
	// })

}