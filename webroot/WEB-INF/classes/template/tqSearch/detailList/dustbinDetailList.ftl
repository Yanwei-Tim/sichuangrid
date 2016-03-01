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
	<@pop.formatterProperty name="partType" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" />
	<@pop.formatterProperty name="partName" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" />
	$("#tqSearchDetailList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:350,
		colModel:[
			{name:"id",index:"keyId",sortable:false,hidden:true},
			{name:'keyId',index:"id",sortable:false,hidden:true,frozen:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:90,frozen :true,align:"center"},
		    {name:'dustbinCode',index:"dustbinCode",label:'部件标识码',sortable:true,width:100,align:"center"},
		    {name:'address',index:"address",label:'地址',sortable:true,width:150},
		    {name:'partType',index:'partType',label:'部件类型',formatter:partTypeFormatter,sortable:false,width:120},
		    {name:'partName',label:'部件名称',formatter:partNameFormatter,sortable:false,width:120}
		],
		loadComplete:function(){},
		ondblClickRow: viewTqSearchDetail
	});
	$("#tqSearchDetailList").jqGrid('setFrozenColumns');
	findTqSearchDetailList();
	
	function operateFormatter(el,options,rowData){
		return "<a href='javascript:;' onclick='viewTqSearchDetail(\""+rowData.id+"\")'><span>查看</span></a>";
	}
	
	function partTypeFormatter(el,options,rowData){
		if(rowData.partType){
			return partTypeData[rowData.partType];
		}
		return "";
	}
	function partNameFormatter(el,options,rowData){
		if(rowData.partName){
			return partNameData[rowData.partName];
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
		url:'${path}/baseinfo/dustbinManage/dispatchOperateByEncrypt.action?mode=view&location.id='+rowData.keyId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
</script>