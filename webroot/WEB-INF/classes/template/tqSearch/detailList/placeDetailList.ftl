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
	<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" />
	<@pop.formatterProperty name="classification" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" />
	<@pop.formatterProperty name="category" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" />
	
	$("#tqSearchDetailList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:350,
		colModel:[
			{name:"id",sortable:false,hidden:true},
			{name:'keyId',sortable:false,hidden:true,frozen:true},
			{name:'classificationen',sortable:false,hidden:true,frozen:true},
			{name:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:90,frozen :true,align:"center"},
		    {name:'name',index:"name",label:'名称',sortable:true,width:100},
		    {name:'type',index:'type',label:'类型',formatter:typeFormatter,sortable:false,width:100,align:"center"},
		    {name:'classification',index:'classification',label:'分类',formatter:classificationFormatter,sortable:false,width:120,align:"center"},
		    {name:'category',index:'category',label:'类别',formatter:categoryFormatter,sortable:false,width:120,align:"center"},
		    {name:'address',label:'地址',sortable:false,width:170}
		],
		loadComplete:function(){},
		ondblClickRow: viewTqSearchDetail
	});
	$("#tqSearchDetailList").jqGrid('setFrozenColumns');
	findTqSearchDetailList();
	
	function operateFormatter(el,options,rowData){
		return "<a href='javascript:;' onclick='viewTqSearchDetail(\""+rowData.id+"\")'><span>查看</span></a>";
	}
	
	function typeFormatter(el,options,rowData){
		if(rowData.type){
			return typeData[rowData.type];
		}
		return "";
	}
	
	function classificationFormatter(el,options,rowData){
		if(rowData.classification){
			return classificationData[rowData.classification];
		}
		return "";
	}
	
	function categoryFormatter(el,options,rowData){
		if(rowData.category){
			return categoryData[rowData.category];
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
		height: 600,
		title: '查看详情',
		url:'${path}/baseinfo/companyPlace/companyPlaceViewDlg.jsp?key='+rowData.classificationen+'&cid='+rowData.keyId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
</script>