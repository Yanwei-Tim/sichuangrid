<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div title="打分详情" class="content" >
    <input type="hidden" id="orgId" value="<s:property value='#parameters.orgId'/>"/>
    <input type="hidden" id="reoprtDateTypeId" value="<s:property value='#parameters.reoprtDateTypeId'/>"/>
    <input type="hidden" id="year" value="<s:property value='#parameters.year'/>"/>
    <input type="hidden" id="month" value="<s:property value='#parameters.month'/>"/>
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
		       {name:"regradedOrg.orgName",index:'regradedOrg',label:"操作单位",formatter:orgNameFormatter,width:80},
		       {name:'regradedUserRealName',label:"操作用户",formatter:realNameFormatter,sortable:false,width:80},
		       //{name:'targeOrg.orgName',label:"应用单位",sortable:false,width:80},
		       {name:'pointType',label:"类型",index:'pointType',formatter:paresePointType,width:50},
		       {name:'points',label:"分值",index:'points',width:50,align:"right"},
		       //{name:'applicationDate',index:'applicationDate',label:'应用时间',width:80},
	           {name:'regradedDate',label:"打分时间",index:'regradedDate'},
	           {name:'content',label:"打分原因",sortable:false,width:390}
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
            "reoprtDateType.id":$("#reoprtDateTypeId").val(),
            "year":$("#year").val(),
            "month":$("#month").val()
        });
        $("#regradedPointsList").trigger("reloadGrid");
    }
});
function paresePointType(el,options,rowData){
    if(rowData.pointType == 1){
        return "<span style='color:green'>加分</span>";
    }else{
        return "<span style='color:red'>扣分</span>";
    }
}
function orgNameFormatter(el,options,rowData){
    if(rowData.regradedOrg.orgName == '中国'){
        return "系统";
    }else{
        return rowData.regradedOrg.orgName+"";
    }
}
function realNameFormatter(el,options,rowData){
    if(rowData.regradedUserRealName == '超级管理员' || rowData.regradedOrg.orgName == 'admin'  ){
        return "系统";
    }else{
        return rowData.regradedUserRealName+"";
    }
}
</script>

