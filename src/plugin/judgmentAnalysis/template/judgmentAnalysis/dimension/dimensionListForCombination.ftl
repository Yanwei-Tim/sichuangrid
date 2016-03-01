<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
		<div class="content"> 
		<div class="ui-corner-all contentNav" id="nav" >
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入名称" id="name" class="searchtxt" onblur="value=(this.value=='')?'请输入名称':this.value;" onfocus="value=(this.value=='请输入名称')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
				<a id="fastSearchDimension" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="dimensionList"> </table>
			<div id="dimensionListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function(){

	$("#fastSearchDimension").click(function(event){
		var name = $("#name").val();
	 	if(name!=null&&"请输入名称"!=name){
			$("#dimensionList").setGridParam({
				url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
				datatype: "json",
				postData: {
					"dimension.name":$("#name").val()
				},
				page:1
			});
			$("#dimensionList").trigger("reloadGrid");
		}
	});
	
	
	function getSelectedIds(){
		var selectedIds = $("#dimensionList").getGridParam("selrow");
		return selectedIds;
	}

	$("#dimensionList").jqGridFunction({
		url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
		datatype: "json",
		postData: {},
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"name",index:'name',sortable:true,label:'名称',align:'center',width:260},
      		{name:"keyName",index:"keyName",sortable:true,label:'关键词',align:'center',width:165},
	        {name:"type",index:"type",label:'类型',sortable:true,align:'center',width:100},
	        {name:'code',label:'编码',width:120,align:'center',sortable:true},
	        {name:'propertyDomain',label:'字典项名称',width:130,align:'center',sortable:true}
		],
		multiselect:true
	});
	
});

$("#refresh").click(function(){
	$("#name").val('请输入名称');
	refresh();
})
function refresh(){
	var	postData = {
	}
	$("#dimensionList").setGridParam({
		url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
		datatype: "json",
		page:1
	});
	$("#dimensionList").setPostData(postData);
	$("#dimensionList").trigger("reloadGrid");
}
$("#refreshSearchKey").click(function(){
	$("#name").attr("value","请输入名称");
});

</script>
