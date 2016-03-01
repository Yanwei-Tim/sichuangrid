<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
<input type="hidden" id="column" value="${(column)?if_exists}"/>
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入搜索关键字" id="searchName" maxlength="50" class="searchtxt"
					onblur="value=(this.value=='')?'请输入搜索关键字':this.value;"
					onfocus="value=(this.value=='请输入搜索关键字')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearch"><span>搜索</span> </a>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span> </a>
	</div>
	<div style="width: 100%;">
		<table id="columnInfoList"></table>
		<div id="columnInfoListPager"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#columnInfoList").jqGridFunction({
		datatype: "local",
		sortname:"column_Name",
		colModel:[
			{name:'columnName',index:'columnName',label:'字段名称',sortable:true},
			{name:'tableName',index:'tableName',label:'业务表名称',sortable:false},
			{name:'comments',index:'comments',label:'表名注解',sortable:false}
		],
		multiselect:false,
		shrinkToFit:true,
		ondblClickRow:function(rowId){
			var columnInfo =  $('#columnInfoList').getRowData(rowId);
			var column = $("#column").val();
			$('#'+column).val(columnInfo.columnName);
			$('#nameSelectDialog').dialog('close');
		}
	});
	getColumnInfoList({"columnInfo.tableName":$("#tableName").val()});
	$("#fastSearch").click(function(){
		if($("#searchName").val()=='请输入搜索关键字'){
			getColumnInfoList({"columnInfo.tableName":$("#tableName").val()});
		}else{
			getColumnInfoList({
				"columnInfo.columnName":$("#searchName").val().toUpperCase(),
				"columnInfo.tableName":$("#tableName").val()
			});
		}
	});
	$("#reload").click(function(){
		getColumnInfoList({"columnInfo.tableName":$("#tableName").val()});
	});
	$("#refreshSearchKey").click(function(){
		$("#searchName").val("请输入搜索关键字");
	});
});

//列表显示
function getColumnInfoList(obj){
	$("#columnInfoList").setGridParam({
		url:'${path}/orgchange/moduleTableManage/findColumnInfos.action',
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#columnInfoList").setPostData(obj);
	$("#columnInfoList").trigger("reloadGrid"); 
}
</script>