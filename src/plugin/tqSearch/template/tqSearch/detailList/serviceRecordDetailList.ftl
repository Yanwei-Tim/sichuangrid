<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="tqSearchDetailList"> </table>
		<div id="tqSearchDetailListPager"></div>
	</div>
	<div id="detailViewDialog"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#tqSearchDetailList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:350,
		colModel:[
			{name:"id",sortable:false,hidden:true},
			{name:'keyId',sortable:false,hidden:true,frozen:true},
			{name:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:90,frozen :true,align:"center"},
		    {name:'occurPlace',index:"occurPlace",label:'服务地点',sortable:true,width:100,align:"center"},
		    {name:'occurDate',index:"occurDate",label:'服务时间',formatter:occurDateFormatter,sortable:true,width:150},
		    {name:'serviceJoiners',index:'serviceJoiners',label:'服务参与人',sortable:false,width:120},
		    {name:'serviceContent',label:'服务内容',sortable:false,width:120}
		],
		loadComplete:function(){},
		ondblClickRow: viewTqSearchDetail
	});
	$("#tqSearchDetailList").jqGrid('setFrozenColumns');
	findTqSearchDetailList();
	
	function operateFormatter(el,options,rowData){
		return "<a href='javascript:;' onclick='viewTqSearchDetail(\""+rowData.id+"\")'><span>查看</span></a>";
	}
	function occurDateFormatter(el,options,rowData){
		if(rowData.occurDate){
			return new Date(rowData.occurDate).format("yyyy-MM-dd");
		}
		return "";
	}
	function findTqSearchDetailList(){
		$("#tqSearchDetailList").setGridParam({
			url:'${path}/baseinfo/tqSearch/tqSearchDetailList.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#tqSearchDetailList").setPostData({
			"searchs.id":"${(searchs.id)!}",
			"searchs.type":"${(searchs.type)!}",
			"searchs.searchType":"${(searchs.searchType)!}",
			"searchs.searchField":"${(searchs.searchField)!}"
		});
		$("#tqSearchDetailList").trigger("reloadGrid"); 
	}
});
function viewTqSearchDetail(selectedId) {
		var rowData = $("#tqSearchDetailList").getRowData(selectedId);
		$("#detailViewDialog").createDialog({
			width: 800,
			height: 400,
			title: '查看详情',
			url:'${path}${path}/plugin/serviceTeam/serviceRecord/dispatch.action?mode=view&showRecordType=&serviceRecord.id='+rowData.keyId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
</script>