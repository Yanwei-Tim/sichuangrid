<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content" >
	<input type="hidden" id="combinationIdStr" value="${(combinationIdStr)!}"/>
	<div id="search-condition-form" title="查询" class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">维度组合名称：</label>
		</div>
		<div class="grid_7">
			<input name="name" type="text" id="combinationName" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="查询" id="fastSearchcombination" />
		</div>
		<div class='clearLine' style="margin-top:10px;height:10px">&nbsp;</div>
	</div>
	<div style="width: 100%;">
	<table id="combinationList"></table>
	<div id="combinationListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	
	$("#combinationList").jqGridFunction({
		url:'${(path)!}/judgmentAnalysis/dimensionCombinationManage/findDimensionCombinationForPage.action?dimensionCombination.combinationIds=<@s.property value="#parameters.combinationIds[0]"/>',
		datatype: "json",
		postData: {
		},
		colModel:[
		    {name:"id",index:"id",sortable:false,hidden:true},		         		      
	        {name:'name',index:'name',sortable:true,label:'名称',align:'center',width:160},
	        {name:'keyName',index:'keyName',label:'关键字',sortable:true,width:120},    
	        {name:'tableName',index:'tableName',label:'业务模型',sortable:true,width:100,align:"center"},
	        {name:'combination',index:'combination',label:'组合名称',sortable:true,width:100,align:"center"}
		],
		multiselect:false,
		showColModelButton:false
	});
	
	$("#fastSearchcombination").click(function(event){
		var combinationName = $("#combinationName").val();
		$("#combinationList").setGridParam({
			url:'${path}/judgmentAnalysis/dimensionCombinationManage/findDimensionCombinationForPage.action',
			datatype: "json",
			page:1
		});
		var searchCondition={
			"dimensionCombination.name":combinationName,
			"dimensionCombination.combinationIds" : '<@s.property value="#parameters.combinationIds[0]"/>'
		};
		$("#combinationList").setPostData(searchCondition);
		$("#combinationList").trigger("reloadGrid");
	});
});

function fillSearchCombinationInputer(elId){
	var selectedId = $("#combinationList").getGridParam("selrow");
	if(selectedId.length>0){
		var rowData = $("#combinationList").jqGrid("getRowData",selectedId); 
		$("#tellToSelectorDimension").clearPersonnelComplete();
		$("#"+elId).setPersonnelCompleteVal({value:rowData.id,label:rowData.name,desc:""});
	}
}	

</script>

