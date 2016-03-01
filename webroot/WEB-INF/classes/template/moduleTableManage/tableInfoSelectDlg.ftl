<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
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
		<table id="tableInfoList"></table>
		<div id="tableInfoListPager"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#tableInfoList").jqGridFunction({
		datatype: "local",
		sortname:"b.table_name",
		sortorder:"ASC",
		colModel:[
			{name:'tableName',index:'tableName',label:'业务表名称',sortable:false},
			{name:'comments',index:'comments',label:'表名注解',sortable:false}
		],
		multiselect:false,
		shrinkToFit:true,
		ondblClickRow:function(rowId){
			var tableInfo =  $('#tableInfoList').getRowData(rowId);
			$('#tableName').val(tableInfo.tableName);
			$('#attributeName').val("");
			$('#nameSelectDialog').dialog('close');
		}
	});
	getTableInfoList({});
	$("#fastSearch").click(function(){
		if($("#searchName").val()=='请输入搜索关键字'){
			getTableInfoList({});
		}else{
			getTableInfoList({"tableInfo.tableName":$("#searchName").val().toUpperCase()});
		}
	});
	$("#reload").click(function(){
		getTableInfoList({});
	});
	$("#refreshSearchKey").click(function(){
		$("#searchName").val("请输入搜索关键字");
	});
});

//列表显示
function getTableInfoList(obj){
	$("#tableInfoList").setGridParam({
		url:'${path}/orgchange/moduleTableManage/findTableInfos.action',
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#tableInfoList").setPostData(obj);
	$("#tableInfoList").trigger("reloadGrid"); 
}
</script>