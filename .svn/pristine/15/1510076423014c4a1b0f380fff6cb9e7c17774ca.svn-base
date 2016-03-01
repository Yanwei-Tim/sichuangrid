<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div title="打分详情" class="content">
    <input type="hidden" id="orgId" value="<@s.property value='#parameters.orgId'/>"/>
    <input type="hidden" id="statDate" value="<@s.property value='#parameters.statDate'/>"/>
    <div style="width:100%">
        <table id="regradedPointsList"></table>
        <div id="regradedPointsListPager"></div>
    </div>
</div>
<script>
$(document).ready(function(){
    $("#regradedPointsList").jqGridFunction({
		datatype: "local",
		colModel:[
	   	       {name:"id",index:"id",hidden:true},
		       {name:"regradedOrg.orgName",index:'regradedOrg',label:"打分单位",width:80},
		       {name:'regradedUserRealName',label:"打分用户",sortable:false,width:80},
		       {name:'targeOrg.orgName',label:"应用单位",sortable:false,width:80},
		       {name:'pointType',label:"类型",index:'pointType',formatter:paresePointType,width:50},
		       {name:'points',label:"分值",index:'points',width:50,align:"right"},
		       {name:'applicationDate',index:'applicationDate',label:'应用时间',width:80},
	           {name:'regradedDate',label:"打分时间",index:'regradedDate'},
	           {name:'content',label:"打分原因",sortable:false,width:240}
	   	],
	 	showColModelButton :false,
	 	rowNum:10,
	    width:860,
	    height:320
	});
    if($("#orgId").val() != null && $("#orgId").val() != ""){
        $("#regradedPointsList").setGridParam({
            url:"${path}/regradedPoints/regradedPointsManage/viewSubDetail.action",
            datatype:"json",
            page:1
        });
        $("#regradedPointsList").setPostData({
            "orgId":$("#orgId").val(),
            "statDate":$("#statDate").val()
        });
        $("#regradedPointsList").trigger("reloadGrid");
    }
});
function paresePointType(el,options,rowData){
    if(rowData.pointType == 1){
        return "<span style='color:red'>加分</span>";
    }else{
        return "<span style='color:green'>扣分</span>";
    }
}
</script>

