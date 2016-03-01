<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
<form id="searchAllDocsForm" method="post" action="">
		<div><br/>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件标题：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="title" name="searchDocumentVo.title" maxlength="200" value="${searchDocumentVo.title}" class="form-txt" style="width:447px"/>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件号：</label>
			</div>
			<div class="grid_4 form-left">
		    	<input type="text" id="documentNo" name="searchDocumentVo.documentNo" maxlength="50" value="${searchDocumentVo.documentNo}" class="form-txt" />
			</div>
    		<div class="grid_2 lable-right">
				<label class="form-lbl">机密程度：</label>
			</div>
			<div class="grid_4">
				<select class="form-txt" name="searchDocumentVo.secretDegree.id" id="secretDegree" style="width: 175px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SECRETDEGREE" defaultValue="${searchDocumentVo.secretDegree.id}"/>
 				</select>
 			</div>
			<div class='clearLine'></div>		
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">主题词：</label>
	  		</div>
    		<div class="grid_4 form-left">
				<input type="text" id="theme" name="searchDocumentVo.theme" maxlength="30" value="${searchDocumentVo.theme}" class="form-txt" />
    		</div>
    		
    		<div class="grid_2 lable-right">
				<label class="form-lbl">紧急程度：</label>
			</div>
			<div class="grid_8">
				<select class="form-txt" name="searchDocumentVo.urgentDegree.id" id="urgentDegree" style="width: 175px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@URGENTDEGREE" defaultValue="${searchDocumentVo.urgentDegree.id}"/>
 				</select>
 			</div>
 			<div class='clearLine'>&nbsp;</div>	
 			<div class="grid_4 lable-right">
				<label class="form-lbl">是否同步资料库：</label>
			</div>
			<div class="grid_4">
			<select class="form-txt" style="width: 187px;height: 25px" id="synchroDocs" name="searchDocumentVo.synchroDocs">
				<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
 			</div>
 			<div class="grid_2 lable-right">
				<label class="form-lbl">是否有附件：</label>
			</div>
			<div class="grid_4">
			  <select class="form-txt" style="width: 175px;height: 25px" id="attachFiles" name="searchDocumentVo.attachFiles">
			 	<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			  </select>
 			</div>
			
			<div class='clearLine'>&nbsp;</div>		
			<div class="grid_4 lable-right">
				<label class="form-lbl">内容：</label>
			</div>
			<div class="heightAuto" style="display:inline;float:left;width:460px;">
    			<textarea rows="3" cols="" id="contents" name="searchDocumentVo.contents"
    				class="form-txt" style="height:90px;">${searchDocumentVo.contents}</textarea>
			</div>
			<div class='clearLine'></div>	
			<div style="margin-left: 425px"><input type="button"  id='docsSearch'  value='查询' style="width: 50px;height: 28px;"/>
				<input type="button"  id='docsReload'  value='刷新' style="width: 50px;height: 28px;margin-left: 8px;"/>
			</div>
		</div>
</form>

<div class='clearLine'></div>
<div style="width: 100%;margin-top: 6px">
		<table id="searchAllDocumentsList"> </table>
		<div id="searchAllDocumentsListPager"></div>
</div>
<div id="allDocumentsMaintanceDialog"></div>
</div>

<script>
function viewDialog(id){
	$("#allDocumentsMaintanceDialog").createDialog({
		width: 600,
		height: 260,
		title:'查看公文信息',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=view&document.id='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function setSpecialElement(){
	//是否同步资料库	
	if($("#synchroDocs").val()==""){
		$("#synchroDocs").attr("disabled","disabled");
	}
	//是否有附件 
	if($("#attachFiles").val()==""){
		$("#attachFiles").attr("disabled","disabled");
	}
}
$(document).ready(function(){
	$("#docsSearch").click(function(){
		setSpecialElement();
	   	var data=$("#searchAllDocsForm").serializeArray();
		$("#searchAllDocumentsList").setGridParam({
		    url:"${path}/documents/dispatchDocumentsManage/searchAllDocuments.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#searchAllDocumentsList").setPostData(data);
		$("#synchroDocs").removeAttr('disabled');
		$("#attachFiles").removeAttr('disabled');
	    $("#searchAllDocumentsList").trigger("reloadGrid");
	});
	$("#docsReload").click(function(){
		 $("#title").val("");
		 $("#documentNo").val("");
		 $("#secretDegree").val("");
		 $("#theme").val("");
		 $("#urgentDegree").val("");
		 $("#synchroDocs").val("");
		 $("#attachFiles").val("");
		 $("#contents").val("");
		 $("#searchAllDocumentsList").clearGridData();
	});
	
	function operateFormatter(el, options, rowData){
		return "<a href='javascript:viewDialog("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}
	
	function synchroDocsFormatter(el, options, rowData){
		if(true==rowData.synchroDocs || "true"==rowData.synchroDocs)
			return "是";
		else 
			return "否";
	}
	function statusFormatter(el, options, rowData){  
		if("unSend"==rowData.status || "sended"==rowData.status)
			{return "发文";}
		if("sign"==rowData.status || "notSign"==rowData.status)
			{return "收文";}
	}
	
	$("#searchAllDocumentsList").jqGridFunction({
	datatype: "local",
   	colModel:[
        {name:"id",index:"id",hidden:true,frozen:true},
	    {name:"操作",index:'id',align:'center',width:50,formatter:operateFormatter,sortable:false,frozen:true},
        {name:'documentNo',label:'文件号',width:120,frozen:true},
        {name:"title",index:'title',label:'文件标题',width:120},
        {name:"status",index:'title',label:'文件类型',formatter:statusFormatter,width:60},
        {name:"synchroDocs",index:'title',label:'是否同步资料库',formatter:synchroDocsFormatter,width:100,align:'center'},
        {name:'dispatchUnit',label:'发文单位',width:120},
        {name:"dispatchDate",label:'发文日期',width:145,align:'center'}
	],
	height:$(".ui-layout-west").outerHeight()-$("#thisCrumbs").outerHeight()-$("#searchAllDocsForm").height()-70,
    multiselect:true,
    rowNum:7,
    rowList:[7,14,21,28,35,42,49,56,63]
});
});
</script>