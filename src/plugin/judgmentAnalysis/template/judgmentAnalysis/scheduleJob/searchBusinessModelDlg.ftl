<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content" >
	<input type="hidden" id="modelIdStr" value="${(modelIdStr)!}"/>
	<div id="search-condition-form" title="查询" class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">业务模块名称：</label>
		</div>
		<div class="grid_7">
			<input name="name" type="text" id="businessModelName" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="查询" id="fastSearchBusinessModel" />
		</div>
		<div class='clearLine' style="margin-top:10px;height:10px">&nbsp;</div>
	</div>
	<div style="width: 100%;">
	<table id="businessModelList"></table>
	<div id="businessModelListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	
	$("#businessModelList").jqGridFunction({
		url:'${path}/judgmentAnalysis/businessModelManage/findBusinessModelForPage.action?businessModel.modelIds=<@s.property value="#parameters.modelIds[0]"/>',
		datatype: "json",
		postData: {
		},
		colModel:[
		    {name:"id",index:"id",sortable:false,hidden:true},		         		      
	        {name:'name',index:"name",label:'业务模块名称',sortable:true,width:200},
	        {name:'keyName',index:'keyName',label:'关键字',sortable:true,width:120},    
	        {name:'type',index:'type',label:'类型',sortable:true,width:100,align:"center"} 		
		],
		multiselect:false,
		showColModelButton:false
	});
	
	$("#fastSearchBusinessModel").click(function(event){
		var businessModelName = $("#businessModelName").val();
		$("#businessModelList").setGridParam({
			url:'${path}/judgmentAnalysis/businessModelManage/findBusinessModelForPage.action',
			datatype: "json",
			page:1
		});
		var searchCondition={
			"businessModel.name":businessModelName,
			"businessModel.modelIds" : '<@s.property value="#parameters.modelIds[0]"/>'
		};
		$("#businessModelList").setPostData(searchCondition);
		$("#businessModelList").trigger("reloadGrid");
	});
});

function fillSearchBusinessModelInputer(elId){
	var selectedId = $("#businessModelList").getGridParam("selrow");
	if(selectedId.length>0){
		var rowData = $("#businessModelList").jqGrid("getRowData",selectedId); 
		$("#tellToSelector").clearPersonnelComplete();
		$("#"+elId).setPersonnelCompleteVal({value:rowData.id,label:rowData.name,desc:""});
	}
}	

</script>

