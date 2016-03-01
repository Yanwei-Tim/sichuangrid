<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div style="width: 100%">
	<table id="builddatasList">
	</table>
	<div id="builddatasListPager"></div>
</div>

<script type="text/javascript">
<@pop.formatterProperty name="buildingstructures" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" />

$(function(){
	$("#builddatasList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	   		{name:"id",index:"id",hidden:true},
	   		{name:"operator", index:'id', label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
			{name:'buildingname',index:'BUILDINGNAME',label:'楼宇名称',width:100},
			{name:'buildingaddress',index:'BUILDINGADDRESS',label:'楼宇地址',width:140},
			{name:'owner',index:'OWNER',label:'业主',width:120},
			{name:'phone',index:'PHONE',label:'联系电话',width:120,align:"center"},
			{name:'buildingstructures',index:'buildingstructures',label:'建筑结构',formatter:buildingstructuresFormatter,width:120},
			{name:'type',index:'type',label:'楼宇类型',width:120,formatter:typeFormatter},
			{name:'isBind',index:'isBind',label:'绑定地图状态',width:120,align:"center"}
		],
		gridComplete:function(){
	    	 var ids=jQuery("#builddatasList").jqGrid('getDataIDs');
	    	 for(var i=0; i<ids.length; i++){
	    		 var id=ids[i];
	    		 var isBind = $("#builddatasList").getCell(id,"isBind");
	    		 if(isBind == 1){
	    			 type= $("#type").val();
	    			modify="绑定";
	    		}else{
	    			modify="未绑定";
	    		}

	    		 jQuery("#builddatasList").jqGrid('setRowData', ids[i], { isBind: modify});

	    	 }

	     },
		multiselect:true,
		height:330
	});
	
});

function operatorFormatter(el,options,rowData){
	return "<@pop.JugePermissionTag ename='viewBuilddatas'><a href='javascript:;' onclick='viewBuilddatas("+rowData.id+")'><span>查看</span></a> </@pop.JugePermissionTag>";
}

function viewBuilddatas(rowId) {
	if (rowId == null) {
		return;
	}
	$("#builddatasMaintanceDialog").createDialog({
		width : 600,
		height : 320,
		title : '查看' + title ,
		url : '${path}/builddatasManage/dispatch.action?mode=view&builddatas.id='+rowId,
		buttons : {
			"关闭" : function() {
				$(this).dialog("close");
			}
		}
	});
}


</script>