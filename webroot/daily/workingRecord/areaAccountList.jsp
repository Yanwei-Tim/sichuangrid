<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	var gridOption = {
		colModel:[
		   {name:"id",index:"id",hidden:true},
		   {name:'name',sortable:true,label:'名称',width:200,formatter:nameFont},
		   {name:'dailyLogType',index:'dailyLogType',label:'类型',formatter:dailyLogTypeFormatter,sortable:true,width:200},
		   {name:"dailyLogAttachFile",label:'附件',sortable:false,width:80,formatter:formatterAttachFile},
		   {name:'subject',hidden:true,label:'主题',sortable:false,width:350},
		   {name:"dealDate",hidden:true,label:'时间',align:'center',sortable:false,width:100},
		   {name:'participant',hidden:true,label:'参加人员',sortable:false,width:100},
		   {name:'proceedSite',hidden:true,label:'地点',sortable:false,width:150},
		   {name:'createDate',hidden:false,align:'center',label:'创建时间',sortable:false,width:150},
		   {name:'content',hidden:true,label:'内容',sortable:false,width:150}
		]
	};
});
</script>
<jsp:include page="/daily/workingRecord/workingRecordList.jsp"></jsp:include>


