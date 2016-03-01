<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#holder_userReceivers { height: 22px;}
#holder_userReceiversCopy { height: 22px;}
</style>
<div id="wrap_add" class="cf">

	<div class="left">
    
            <div id="dialog-form" title="" class="container container_24">
                <form id="dispatchDocumentsForm" method="post" action="">
                 <pop:token/>
                    <input type="hidden" id="mode" name="mode" value="${mode}" />
                    <input type="hidden"  value="${document.dispatchState}" name="document.dispatchState"/>
                    <input type="hidden"  value="${document.id}" name="document.id"/>
                    <div>
                        <div class="grid_4 lable-right">  
                            <em class="form-req">*</em>主送对象：
                        </div>
                        <div class="grid_16 form-left heightAuto">
                        	<input type="hidden" name="userReceivers"	id="userReceivers" class="form-txt" />
                        
	                        <input type="hidden" name="orgReceivers" id="orgReceivers"/>
	                         <input type="hidden" name="receiversNames" id="receiversNames"/>
                        </div>
                        
                        <div class="grid_4">
							<input type="button" class="defaultBtn" id="selectPersonBtn" value="添加" >
							<input type="button" class="defaultBtn" id="clearPersonBtn" value="清空" >
						</div>
                        
                        <div class="grid_4 lable-right">抄送对象：
                        </div>
                        
						 <div class="grid_16 form-left heightAuto">
                        	<input type="hidden" name="userReceiversCopy"	id="userReceiversCopy" class="form-txt" />
                        	
	                        <input type="hidden" name="orgReceiversCopy" id="orgReceiversCopy"/>
	                        <input type="hidden" name="receiversNamesCopy" id="receiversNamesCopy"/>
                        </div>
                        
                        <div class="grid_4">
							<input type="button" class="defaultBtn" id="selectPersonBtnCopy" value="添加" >
							<input type="button" class="defaultBtn" id="clearPersonBtnCopy" value="清空" >
						</div>

                        <div class="grid_4 lable-right">
                            <em class="form-req">*</em>
                            <label class="form-lbl">文件标题：</label>
                        </div>
                        <div class="grid_20 form-left">
                            <input type="text" id="title" name="document.title" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if> maxlength="150" value="${document.title}" class="form-txt"/>
                        </div>
                        <div class='clearLine'></div>
                        <div class="grid_4 lable-right">
                            <em class="form-req">*</em>
                            <label class="form-lbl">发文单位：</label>
                        </div>
                        <div class="grid_20 form-left">
                            <input type="text" id="dispatchUnit" name="document.dispatchUnit" maxlength="50" <s:if test="'transmit'.equals(mode) or 'add'.equals(mode) or 'edit'.equals(mode)">readonly="true" </s:if>  <s:if test="'add'.equals(mode)">value="${userOrgName}" </s:if><s:else>value="${document.dispatchUnit}" </s:else> class="form-txt" />
                        </div>
                        <div class='clearLine'></div>
                        
                        <div class="grid_4 lable-right">
                            <label class="form-lbl">文件号：</label>
                        </div>
                        <div class="grid_8 form-left">
                            <input type="text" id="documentNo" name="document.documentNo" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if>  maxlength="50" value="${document.documentNo}" class="form-txt" />
                        </div>
                        <div class="grid_3 lable-right">
                            <label class="form-lbl">机密程度：</label>
                        </div>
                        <div class="grid_8">
                            <select name="document.secretDegree.id" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if>  id="secretDegree" class="form-select">
                                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SECRETDEGREE" defaultValue="${document.secretDegree.id}"/>
                            </select>
                        </div>
                        <div class='clearLine'></div>		
                        
                        <div class="grid_4 lable-right">
                            <label class="form-lbl">主题词：</label>
                        </div>
                        <div class="grid_8 form-left">
                            <input type="text" id="theme" name="document.theme" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if>  maxlength="30" value="${document.theme}" class="form-txt" />
                        </div>
                        
                        <div class="grid_3 lable-right">
                            <label class="form-lbl">紧急程度：</label>
                        </div>
                        <div class="grid_8">
                            <select name="document.urgentDegree.id" id="urgentDegree" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if>  class="form-select">
                                    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@URGENTDEGREE" defaultValue="${document.urgentDegree.id}"/>
                            </select>
                        </div>
                        <div class='clearLine'>&nbsp;</div>	
                        <div class="grid_4 lable-right">
                            <label class="form-lbl">内容：</label>
                        </div>
                        <div class="grid_20 heightAuto">
                            <textarea rows="3" cols="" id="contents" name="document.contents" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if> 
                                class="form-txt" style="height:90px;">${document.contents}</textarea>
                        </div>
                        <div class='clearLine'>&nbsp;</div>
	                        <s:if test="'transmit'.equals(mode)">
		                        <div class="grid_4 lable-right">
		                            <label class="form-lbl">审批意见：</label>
		                        </div>
		                        <div class="grid_20 heightAuto">
			                        <textarea rows="3" cols="" id="approvalOpinionHistory" name="approvalOpinion" class="form-txt" style="height:90px;" readonly >${document.approvalOpinion}</textarea>
			                        <textarea rows="2" cols="" id="approvalOpinion" name="document.approvalOpinion" class="form-txt" style="height:60px;margin-top:0px;"></textarea>
		                        </div>
		                        <div class='clearLine'>&nbsp;</div>
	                        </s:if>
                    </div>
          			 <select id="attachFileNames" <s:if test="'transmit'.equals(mode)">readonly="true" </s:if>  name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
                </form>
                <div class="grid_4 lable-right">
                </div>
                <div class="grid_20 heightAuto">
                   <s:if test="!'transmit'.equals(mode)"> <input id="custom_file_upload" name="uploadFile" type="file" /></s:if>
                    <div id="custom-queue" style="clear:both;overflow:auto;"></div>
                </div>
            </div>
    
    </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	approvalOpinionHistoryDeal();
	
	//主送对象收件人选择
	$("#selectPersonBtn").click(function(){
		$("#selectPersonDialog").createDialog({
			width: 650,
			height: 340,
			title:'选择收件人',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=documentSelect',
			buttons: {
				"确定" : function(event){
					  $(this).dialog("close");
			    },
			    "关闭" : function(){
			    	$(this).dialog("close");
			    }
			}
		});
	});
	$("#clearPersonBtn").click(function(){
		var selectId = $("#userReceivers").val().split(",");
		var selectOrgId = $("#orgReceivers").val().split(",");
		if(selectId!=null && selectId.length!=0&& selectId!=undefined){
			for(var i=0;i<selectId.length;i++){
				$("#userReceivers").removePersonnelCompeleteValue(selectId[i]);
			}
		}
		if(selectOrgId!=null && selectOrgId.length!=0&& selectOrgId!=undefined){
			for(var i=0;i<selectOrgId.length;i++){
				$("#orgReceivers").removePersonnelCompeleteValue(selectOrgId[i]);
			}
		}
		
		$("#holder_userReceivers .bit-box").each(function(){
				$(this).remove();
		});
	});
	$("#userReceivers").personnelComplete({
		
	});
	$("#holder_userReceivers").css("width","500");
	$("#userReceiversCopy").personnelComplete({
		
	});
	$("#holder_userReceiversCopy").css("width","500");
	//抄送对象收件人选择
	$("#selectPersonBtnCopy").click(function(){
		$("#selectPersonDialog").createDialog({
			width: 650,
			height: 340,
			title:'选择收件人',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=documentSelectCopy',
			buttons: {
				"确定" : function(event){
					  $(this).dialog("close");
			    },
			    "关闭" : function(){
			    	$(this).dialog("close");
			    }
			}
		});
	});
	
	$("#clearPersonBtnCopy").click(function(){
		var selectId = $("#userReceiversCopy").val().split(",");
		var selectOrgId = $("#orgReceiversCopy").val().split(",");
		if(selectId!=null && selectId.length!=0&& selectId!=undefined){
			for(var i=0;i<selectId.length;i++){
				$("#userReceiversCopy").removePersonnelCompeleteValue(selectId[i]);
			}
		}
		if(selectOrgId!=null && selectOrgId.length!=0&& selectOrgId!=undefined){
			for(var i=0;i<selectOrgId.length;i++){
				$("#orgReceiversCopy").removePersonnelCompeleteValue(selectOrgId[i]);
			}
		}
		
		$("#holder_userReceiversCopy .bit-box").each(function(){
				$(this).remove();
		});
	});
	
	if("edit"== $("#mode").val()){
		setDefaultData();
	}
	
	
	initAttachFileUploader(); // 初始化附件上传工具
	fillDocAttenchFiles(); 			// 填充附件
	bindFormAction();					//表单提交的action
	$("#dispatchDocumentsForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var messageStr="";
			if($("#userReceivers").val()=="" && $("#orgReceivers").val()=="" ){
				$.messageBox({message:"主送对象不能为空！",level:"warn"});
				return;
			}
			var receiversNames="";
			$("#holder_userReceivers .bit-box").each(function(){
				receiversNames+=$(this).data("data")+"="+$(this).text()+",";
			});
			if(receiversNames!='' && receiversNames!=0){
				$("#receiversNames").attr("value",receiversNames);
			}
			
			var receiversNamesCopy="";
			$("#holder_userReceiversCopy .bit-box").each(function(){
				receiversNamesCopy+=$(this).data("data")+"="+$(this).text()+",";
			});
			if(receiversNamesCopy!='' && receiversNamesCopy!=0){
				$("#receiversNamesCopy").attr("value",receiversNamesCopy);
			}
			
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({message:data,level:"error"});
					}else{
						if($("#dispatchState").val()=="unSend"){
							if("edit"==$("#mode").val()){
								$("#dispatchDocumentsList").setRowData(data.id,data);
							}
							else{
			 					$("#dispatchDocumentsList").addRowData(data.id,data,"first");
			 					$("#dispatchDocumentsList").setSelection(data.id);
							}
						}else{
							$("#dispatchDocumentsList").trigger("reloadGrid");
						}
						if("edit"==$("#mode").val()){
							messageStr="修改发文成功";
							$("#dispatchDocumentsList").trigger("reloadGrid");
						}else if("transmit"==$("#mode").val()){
							messageStr="公文转发成功";
							$("#receiveDocumentsList").trigger("reloadGrid");
							$("#receiveDocumentsMaintanceDialog").dialog("close");
						}
						else{
							messageStr = "已经成功将该发文信息保存到系统中!";
						}
						$("#dispatchDocumentsList").trigger("reloadGrid");
						$.messageBox({message:messageStr});
						$("#dispatchDocumentsMaintanceDialog").dialog("close");
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"document.title":{
				required:true,
				titleStr:true,
				maxlength:150
			},
			"document.dispatchUnit":{
				required:true,
				maxlength:50
			},
			"document.documentNo":{
				maxlength:50
			},
			"document.theme":{
				maxlength:50,
				multiNames:true
			},
			"document.contents":{
				maxlength:4000
			}
		},
		messages:{
			"document.title":{
				required:"请填写一个不大于150个字的文件标题",
				titleStr:"文件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符",
				maxlength:$.format("文件主题不能大于{0}个字符")
			},
			"document.dispatchUnit":{
				required:"请填写一个不大于50个字的发文单位",
				maxlength:$.format("发文单位不能大于{0}个字符")
			},
			"document.documentNo":{
				required:"请填写一个不大于50个字的文件号",
				maxlength:$.format("发文单位不能大于{0}个字符")
			},
			"document.theme":{
				maxlength:$.format("主题词不能大于50个字符"),
				multiNames:"主题词中只能输入中英文、数字、逗号、顿号等字符"
			},
			"document.contents":{
				maxlength:$.format("主题词不能大于{0}个字符")
			}
		}
	});
	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
		    queueID : 'custom-queue',
			
			selectInputId : "attachFileNames" 
			});
		$("#attachFileNames").empty();
	}
	function fillDocAttenchFiles(){
		<s:if test="docfiles!=null && docfiles.size > 0">
		   <s:iterator value="docfiles" var="file">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='fileId'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/documents/dispatchDocumentsManage/downLoadDocfiles.action?fileId=<s:property value='fileId'/>",   	     	 
		     <s:if test='"view".equals(mode)'>
	          	showCloseButton:false,
	          </s:if>
		     onRemove:function(id){deleteMyDocumentsAttachFile(id)}
		     });
		    $("<option>").attr("id","<s:property value='fileId'/>").val("<s:property value='fileId'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
		    </s:iterator>
		</s:if>
	}
	function removeAttach(id){
		$("#attachFileNames").find("option[id="+id+"]").remove();
		$("#fileIds").find("option[id="+id+"]").remove();
	}
	
	function deleteMyDocumentsAttachFile(id){
		$.ajax({
			url:"${path}/documents/dispatchDocumentsManage/deleteMyDocumentsAttachFile.action?myDocumentsAttachFile.id="+id,
			type:'GET',
			dataType:'json',
			success : function(_data){
				if(_data==true){
					removeAttach(id);
				}
			},
			error : function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		});
	}
	
	function bindFormAction(){
		var mode=$("#mode").val();
		if ("add"== mode){
			$("#dispatchDocumentsForm").attr("action","${path}/documents/dispatchDocumentsManage/addDispatchDocuments.action");
		}else if ("edit"== mode){
			$("#dispatchDocumentsForm").attr("action","${path}/documents/dispatchDocumentsManage/updateDispatchDocuments.action");
		}else if ("transmit"== mode){
			$("#dispatchDocumentsForm").attr("action","${path}/documents/dispatchDocumentsManage/transmitDocuments.action");
		}
	}
});

function approvalOpinionHistoryDeal(){
	var approvalOpinionHistory=$("#approvalOpinionHistory").val();
	if(approvalOpinionHistory==null||approvalOpinionHistory==''){
		$("#approvalOpinionHistory").hide();
	}
	
}

function setDefaultData(){
	var receiversNames = "${document.receiversNames}";
	var receiversNamesCopy=	"${document.receiversNamesCopy}";
	var userRecevis;
	var orgRecevis;
	var mainInfo;
	if(receiversNames!=null && receiversNames!=''){
		userRecevis = receiversNames.split("|")[0].split("&")[0];
		orgRecevis = receiversNames.split("|")[0].split("&")[1];
		mainInfo= receiversNames.split("|")[1].split(",");
	}
	var userRecevisCopy;
	var orgRecevisCopy
	var secondaryInfo
	if(receiversNamesCopy!=null && receiversNamesCopy!=''){
		userRecevisCopy = receiversNamesCopy.split("|")[0].split("&")[0];
		orgRecevisCopy = receiversNamesCopy.split("|")[0].split("&")[1];
		secondaryInfo = receiversNamesCopy.split("|")[1].split(",");
	}
	
	/* if(userRecevis!=null){
		$("#userReceivers").attr("value",userRecevis);
	} */
	if(orgRecevis!=null){
		$("#orgReceivers").attr("value",orgRecevis);
	}
	
	/* if(userRecevisCopy!=null){
		$("#userReceiversCopy").attr("value",userRecevisCopy);
	} */
	if(orgRecevisCopy!=null){
		$("#orgReceiversCopy").attr("value",orgRecevisCopy);
	}
	if(mainInfo!=null && mainInfo.length!=0){
		for(var i=0;i<mainInfo.length;i++){
			var info = mainInfo[i].split("=");
			if(mainInfo[i]==null || mainInfo[i]==''){
				continue;
			}
			$("#userReceivers").setPersonnelCompleteVal({
				value : info[0],
				label : info[1],
				desc : ""
			});
		}
	}
	if(secondaryInfo!=null && secondaryInfo.length!=0){
		for(var i=0;i<secondaryInfo.length;i++){
			var info = secondaryInfo[i].split("=");
			if(secondaryInfo[i]==null || secondaryInfo[i]==''){
				continue;
			}
			$("#userReceiversCopy").setPersonnelCompleteVal({
				value : info[0],
				label : info[1],
				desc : ""
			});
		}
	}
}




</script>
