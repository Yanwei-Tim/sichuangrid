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
	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	$("#tqSearchDetailList").jqGridFunction({
		datatype: "local",
		multiselect:true,
		height:350,
		colModel:[
			{name:"id",sortable:false,hidden:true},
			{name:'keyId',sortable:false,hidden:true,frozen:true},
			{name:'dataType',sortable:false,hidden:true,frozen:true},
			{name:'internalId',sortable:false,hidden:true,frozen:true},
			{name:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:80,frozen :true,align:"center"},
			{name:'name',index:"name",label:'名称',sortable:true,width:100,align:"center"},
		    {name:'idCardNo',index:"idCardNo",label:'身份证',sortable:true,width:100,align:"center"},
		    {name:'gender',index:"gender",label:'性别',formatter:genderFormatter,sortable:true,width:80,align:"center"},
		    {name:'birthday',index:"birthday",label:'出生日期',formatter:birthdayFormatter,sortable:true,width:100,align:"center"},
		    {name:'censusRegister',index:'censusRegister',label:'户籍地',sortable:false,width:120},
		    {name:'address',label:'地址',sortable:false,width:120}
		],
		loadComplete:function(){},
		ondblClickRow: viewTqSearchDetail
	});
	$("#tqSearchDetailList").jqGrid('setFrozenColumns');
	findTqSearchDetailList();
	
	function operateFormatter(el,options,rowData){
		return "<a href='javascript:;' onclick='viewTqSearchDetail(\""+rowData.id+"\")'><span>查看</span></a>";
	}
	
	function genderFormatter(el,options,rowData){
		if(rowData.gender){
			return genderData[rowData.gender];
		}
		return "";
	}
	function birthdayFormatter(el,options,rowData){
		if(rowData.birthday){
			return new Date(rowData.birthday).format("yyyy-MM-dd");
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
	var urlInfo = TQSearch.getDetailUrl(rowData.dataType);
	$("#detailViewDialog").createDialog({
		width: 800,
		height: 600,
		title: '查看详情',
		url:'${path}'+urlInfo.url+rowData.keyId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
</script>