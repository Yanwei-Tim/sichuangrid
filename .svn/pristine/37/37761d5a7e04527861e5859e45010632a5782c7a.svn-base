<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div style="width: 100%;margin-top: 6px">
		<table id="readerList"> </table>
		<div id="readerListPager"></div>
</div>
<input type="hidden" id=organizationId value="${documentsHasUser.organizationId}" name="documentsHasUser.organizationId" />
<input type="hidden" id=documentId value="${documentsHasUser.documentId}" name="documentsHasUser.documentId" />
<script>
function findReaderList(){
	$("#readerList").setGridParam({
		url:'${path}/documents/dispatchDocumentsManage/findReaderList.action',
		datatype: "json",
		page:1
	});

	$("#readerList").setPostData({
		"documentsHasUser.organizationId":$("#organizationId").val(),
		"documentsHasUser.documentId":$("#documentId").val()
   	});
	$("#readerList").trigger("reloadGrid");
}

function readDateFormatter(el, options, rowData){  
	if(""==rowData.readDate || null==rowData.readDate)
		return "未阅读";
	else 
		return rowData.readDate;
}
$(document).ready(function(){
$("#readerList").jqGridFunction({
	datatype: "local",
	sortname:'readDate',
   	colModel:[
      {name:"id",index:"id",hidden:true,hidedlg:true},  
      {name:"name",label:'姓名',width:70},
      {name:'readDate',label:'阅读时间',width:78,formatter:readDateFormatter,align:'center',},
      {name:'mobile',label:'手机',align:'center', width:110},
 	  {name:'workPhone',label:'工作电话',index:"mobileNumber",align:'center',sortable:false,width:100},
      {name:"orgName",label:'所属部门',width:80,}
	],
	height:150,
    rowNum:5,
    showColModelButton:false,
    rowList:[5,10,15,20,25,30,50,80,100]
	});

findReaderList();
});

</script>