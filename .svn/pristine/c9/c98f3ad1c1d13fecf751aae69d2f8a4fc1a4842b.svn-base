<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav" >
		<div class="btnbanner btnbannerData">
			<select id="createYear" style="height: 23px">
					<option value="">全部</option>
			</select>
		<input type="text" value="请输入文件标题" name="searchByCondition" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入文件标题':this.value;" onfocus="value=(this.value=='请输入文件标题')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 270px; cursor: pointer; outline: none;"></button>
		<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
		<a id="docsSearch" href="javascript:void(0)" ><span>高级搜索</span></a>
		<span class="lineBetween"></span>
			
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	</div> 
		
<div class='clearLine'></div>
<div style="width: 100%;margin-top: 6px">
		<table id="searchAllDocumentsList"> </table>
		<div id="searchAllDocumentsListPager"></div>
</div>
<div id="allDocumentsMaintanceDialog"></div>

<script>
function viewDialog(id){
	var rowData=  $("#searchAllDocumentsList").getRowData(id);
	$("#allDocumentsMaintanceDialog").createDialog({
		
		width: 600,
		height: 260,
		title:'查看公文信息',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=view&document.id='+rowData.encryptId,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$(document).ready(function(){
 setQueryCondition();
 function setQueryCondition(){
	$.ajax({
		url:'${path}/documents/dispatchDocumentsManage/searchEarliestDocument.action',
		success:function(data){
			if (data != null && data.createDate !=""){
				var count=0;
				var year=new Date().getFullYear();
				var earliestYear=data.createDate.substr(0,4);
				count=year-earliestYear;
				for(var i=0;i<=count;i++){
					$("#createYear").append("<option value="+(year-i)+">"+(year-i)+"</option>");
				}
			}
			//else{
			 	//$.messageBox({message:data});
			//}
		}
	});
}
		
	$("#createYear").change(function(){
		loadAllDocsList();
	});
	function loadAllDocsList(){
		$("#searchAllDocumentsList").setGridParam({
			url:'${path}/documents/dispatchDocumentsManage/searchAllDocuments.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#searchAllDocumentsList").setPostData({
	    	"searchDocumentVo.createYear":$("#createYear").val()
	   	});
	    $("#searchAllDocumentsList").trigger("reloadGrid");
		}   
	$("#docsSearch").click(function(event){
		$("#allDocumentsMaintanceDialog").createDialog({
			width: 640,
			height: 400,
			title:'公文查询-请输入查询条件',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=allSearch&searchDocumentVo.createYear='+$("#createYear").val(),
			buttons: {
		   		"查询" : function(event){
		   			searchAllDocuments();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	
	});
	
	$("#refresh").click(function(){
		$("#searchByCondition").attr("value","请输入文件标题");
		loadAllDocsList();
	});

	function searchAllDocuments(){
		setSpecialElement();
		var data=$("#searchAllDocsForm").serializeArray();
		$("#searchAllDocumentsList").setGridParam({
				url:'${path}/documents/dispatchDocumentsManage/searchAllDocuments.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#searchAllDocumentsList").setPostData(data);
		    $("#searchAllDocumentsList").trigger("reloadGrid");
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

	$("#fastSearch").click(function(){
		fastSearchfun();
	});

	function fastSearchfun(){
		var condition = $("#searchByCondition").val();
		if('请输入文件标题'== condition) return;
		var	initParam = {
				"searchDocumentVo.createYear":$("#createYear").val(),
				"searchDocumentVo.title":condition
				
				}	
		$("#searchAllDocumentsList").setGridParam({
			url:'${path}/documents/dispatchDocumentsManage/searchAllDocuments.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#searchAllDocumentsList").setPostData(initParam);
		$("#searchAllDocumentsList").trigger("reloadGrid");
	}
	
	
	function titleFont(el,options,rowData){
		return "<a href='javascript:viewDialog("+rowData.id+")'><U><font color='#6633FF'>"+rowData.title+"</font></U></a>";
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
		if("transmit"==rowData.status)
			{return "转发";}
		
	}
	
	$("#searchAllDocumentsList").jqGridFunction({
	datatype: "local",
   	colModel:[
        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	    //{name:"操作",index:'id',width:50,formatter:operateFormatter,sortable:false,frozen:true},
        {name:"title",index:'title',label:'文件标题',sortable:true,formatter:titleFont,width:120},
        {name:'documentNo',label:'文件号',sortable:true,width:120,frozen:true},
        {name:"status",index:'status',label:'文件类型',sortable:true,formatter:statusFormatter,width:75,align:'center'},
        {name:"synchroDocs",index:'title',label:'是否同步资料库',sortable:true,formatter:synchroDocsFormatter,width:110,align:'center'},
        {name:'dispatchUnit',label:'发文单位',sortable:true,width:180},
        {name:"dispatchDate",label:'发文日期',sortable:true,width:145,align:'center'},
        {name:"createDate",label:'创建日期',sortable:true,width:145,align:'center'}
	],
    multiselect:false,
    ondblClickRow:viewDialog
});
	loadAllDocsList();
});
$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","");});
</script>