<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
.lable-right .form-lbl,.autoHeight .form-lbl{font:12px/30px "microsoft yahei";color:#333;}
.lable-right .form-lbl{font-weight:700;}
.event-process{height:480px;height:470px\9;overflow:hidden;}
.event-processL,.event-processR{float:left;display:inline;}
.event-processL{height:480px;height:470px\9;overflow:hidden;overflow-y:auto;border-right:2px solid #A6C9E2;}
.dashedLine{margin:0 8px;border-top:1px dashed #999;}
.processContent{margin:0 0 10px 30px;border:1px solid #A6C9E2;background:#EAF4FD;}
.processMC{padding:0 0 0 8px;}
.processMsg .autoHeight .form-lbl{color:#006699;}
.processWay{font-weight:700;color:#FF6600;}
.event-processR{margin:0 0 0 2px;}
.autoHeight{height:auto;overflow:hidden;word-break:break-all;word-wrap:break-word;}
.ui-widget-content a{color:#0C5DAC;}
.ui-dialog .ui-dialog-content{padding:0;}
.ui-dialog .ui-dialog-buttonpane{margin:0;}
.holder {width:163px!important;height:23px!important;line-height:18px!important;}
</style>

<div id="dialog-form" title="办理事件处理" class="container container_24">
	<div class="event-process">
		<div class="event-processL grid_15">
				<div class="clearfix">
					<div class="grid_4 lable-right">
						<label class="form-lbl">事件名称：</label>
					</div>
					<div class="grid_20 autoHeight">
						<label class="form-lbl"><s:property value="issueNew.subject"/></label>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">发生时间：</label>
					</div>
					<div class="grid_20 autoHeight">
						<label class="form-lbl"> <s:date name="issueNew.occurDate" format="yyyy年MM月dd日"/> </label>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">主要人物：</label>
					</div>
					<div class="grid_20 autoHeight">
						<label class="form-lbl"><s:property value="issueNew.mainCharacters"/></label>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">发生地点：</label>
					</div>
					<div class="grid_20 autoHeight">
						<label class="form-lbl"><s:property value="issueNew.occurLocation"/></label>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">事件类型：</label>
					</div>
					<div class="grid_20 autoHeight">
						<label class="form-lbl">
							<s:if test="selContradictionString!=null">${selContradictionString}
					                    <c:forEach items="${selContradiction}" var="type" >
					                        ${type.issueTypeName},
					                    </c:forEach><br>
				                    </s:if>
				                    <s:if test="selSpecialisationString!=null">${selSpecialisationString}
					                    <c:forEach items="${selSpecialisation}" var="type" >
					                        ${type.issueTypeName},
					                    </c:forEach><br>
				                    </s:if>
				                    <s:if test="selPeopleliveServiceString!=null">${selPeopleliveServiceString}
					                    <c:forEach items="${selPeopleliveService}" var="type" >
					                        ${type.issueTypeName},
					                    </c:forEach>
				                    </s:if>
				                    <s:if test="selOtherTypeString!=null">${selOtherTypeString}
					                    <c:forEach items="${selOtherType}" var="type" >
					                        ${type.issueTypeName},
					                    </c:forEach>
				                    </s:if>
						</label>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">事件简述：</label>
					</div>
					<div class="grid_20 autoHeight">
						<div class="grid_24 heightAuto">
							<label class="form-lbl">
								<s:property value="issueNew.issueContent" />
							</label>
						</div>
					</div>
					<div class="grid_4 lable-right">
						<label class="form-lbl">附件：</label>
					</div>
					<div class="grid_20 autoHeight">
						<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
				              <div class="grid_24 filetype heightAuto">
				            	<div class="grid-accessory">
									<span class="filetype-clip"></span>
								</div>
						<c:forEach items="${issueAttachFiles }" var="issueAttachFile">
			                     	<a style="font:12px/30px '\5b8b\4f53';color:#06699B;" href="${path }/issue/issueManage/downLoadActualFile.action?fileId=${issueAttachFile.id }" >${issueAttachFile.fileName }</a>;
			                	</c:forEach>
				              </div>
				            	</s:if>
					</div>
				</div>
				<div class="dashedLine"></div>
				<div class="grid_3 lable-right">
				<label class="form-lbl">处理过程：</label>
			</div>
			<s:subset source="issueLogs" start="0">
                     <s:iterator status="index">
                    	<div class="grid_21 processContent autoHeight">
		                     <div class="grid_24 processMC autoHeight">
			                        	<span><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></span>
				                	<span><s:property value="dealOrg.orgName"/></span>
				                	<span><s:property value="dealUserName"/></span>
			                    		<s:property value="dealDescription" escape="false"/>
		                     </div>
	                    	<s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
	                     	<div class="grid_24 processMsg autoHeight">
				                <div class="grid_5 lable-right">
									<label class="form-lbl">办理意见：</label>
								</div>
								<div class="grid_19 autoHeight">
									<label class="form-lbl">${content }</label>
								</div>
		                	 </div>
		                	 <div class="grid_24 processMsg autoHeight">
		                	 	<div class="grid_5 lable-right">
									<label class="form-lbl">附件：</label>
								</div>
								<s:if test="issueLogAttachFilesMap[id]!=null && issueLogAttachFilesMap[id].size > 0">
		                        <div class="grid_19 filetype heightAuto">
			                    	<div class="grid-accessory">
										<span class="filetype-clip"></span>
		                        <c:forEach items="${issueLogAttachFilesMap[id] }" var="issueLogAttachFile">
		                            <a style="font:12px/30px '\5b8b\4f53';color:#06699B;" href="${path }/issue/issueManage/downLoadActualFile.action?fileId=${issueLogAttachFile.id}" >${issueLogAttachFile.fileName }</a>;
		                        </c:forEach>
		                        	</div>
		                        </div>
	                    			</s:if>
		                	 </div>
	                    		</s:if>
	                    	</div>
	                    </s:iterator>
	                </s:subset>
		</div>
		
	<div id="dialog-form" title="办理事件处理" class="event-processR grid_9">
   <form id="maintainForm" method="post" action="${path }/issue/issueBusinessManage/dealIssue.action">
		<input type="hidden" name="issueLog.dealOrg.id" id="dealOrgId" value="${issueLog.dealOrg.id }" />
		<input type="hidden" name="issueLog.issue.id" id="issueId" value="${issueLog.issue.id }" />
		<input type="hidden" name="stepId" id="stepId" value="${issueStepId}" />
		<input id="DialogName" name="" type="hidden" value="${DialogName}" />
		<div class="grid_7 lable-right">
			<label class="form-lbl">承办人：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="dealUserName" name="issueLog.dealUserName" maxlength="20" value="${issueLog.dealUserName}" class="form-txt" />
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>  
		<div class="grid_7 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_16">
			<input type="text" id="mobile" name="issueLog.mobile" value="${issueLog.mobile}" maxlength="11" class="form-txt" />
		</div>
        <div class="grid_1">
            <em class="form-req">*</em>
        </div>  
		<div class='clearLine'></div>
		
		<div class="grid_7 lable-right">
			<label class="form-lbl">操作类型：</label>
		</div>
		<div class="grid_16">
			<s:select name="dealType" list="canDoList" onchange="changeDealType()" listKey="code" listValue="desc" emptyOption="true"/>
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>
		<div class='clearLine'></div>
		<div id="transferToDiv">
				<div class="grid_7 lable-right">
					<label class="form-lbl">交办给：</label>
				</div>
				<div class="grid_8">
					<input type="radio" name="transferToType" id="transferToAdmin" value="true" checked onclick="transferToTypeChange(this)"/><label for="targeOrgSubmitForwardTypeAdmin"><s:text name="issue.dealIssue.orglable.name"/></label>
				</div>
				<div class="grid_8">
					<input type="radio" name="transferToType" id="transferToFun" value="false" onclick="transferToTypeChange(this)"/><label for="targeOrgSubmitForwardTypeFun">职能部门</label>
				</div>
				<div class='clearLine'></div>
			<div class="grid_7 lable-right">
				<label class="form-lbl">主办单位：</label>
			</div>
			<div style="display:inline;float:left;width:53.00%;">
				<div style="display:inline;float:left;width:85.00%;">
					<input id="transferTo" type="text" name="issueLog.targeOrg.id" class="form-txt" />
				</div>
				<div style="display:inline;float:left;width:10.00%;">
					<input type="button" style="width:40px;height:25px"  value="查询" id="selectTransferTo" />
				</div>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
			<div class="clearLine"></div>
			<div id="tellToSelectorDiv">
				<div class="grid_7 lable-right">
					<label class="form-lbl">抄告：</label>
				</div>
				<div style="display:inline;float:left;width:53.00%;"  class='grid_17'>
					<div style="display:inline;float:left;width:85.00%;">
						<input id="tellToSelector" name="tellOrgIds" class="form-txt"/>
					</div>
					<div style="display:inline;float:left;width:10.00%;">
						<input type="button" style="width:40px;height:25px"  value="查询" id="selecteTellTo" />
					</div>
					
				</div>
			</div>
			<div class='clearLine'></div>
		</div>
		<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">办理意见：</label>
		</div>
		<div class="grid_16 heightAuto">
			<textarea name="issueLog.content" rows="5" cols="90" class="form-txt" id="content" ></textarea>
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div> 
		<div class='clearLine'></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
</form>
	<div style="position: relative;left: 28.583%;top:0;">
		<div id="custom-queue" style="width: 205px; height:150px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
	<div id="attachFilesComponent"></div>
	<div id="targeOrgSelectDialog"></div>
</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	$("#attachFileNames").empty();

	$("#selectTransferTo").hide();
	$("#selecteTellTo").hide();
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.issueLogId){
	           	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
	           	 	if($("#DialogName").val()=='maxDeal'){
						 $("#maxIssueForBenchDialog").dialog("close");
					}else{
						 $("#issueForBenchDialog").dialog("close");
					}
	            		return;
	            	}
					var dealType = $("#dealType").val();
					if(dealType != <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>){
		     			$.messageBox({message:"已经成功办理该事件处理!"});
		     			if($("#DialogName").val()=='maxDeal'){
							 $("#maxIssueForBenchDialog").dialog("close");
							 $("#maxIssueListForNeed").deleteSubGridByRowId($("#stepId").val());
							 $("#maxIssueListForNeed").delRowData($("#stepId").val());
							 $("#maxIssueListForNeed").trigger("reloadGrid");
						}else{
							 $("#issueForBenchDialog").dialog("close");
							 $("#issueListForNeed").deleteSubGridByRowId($("#stepId").val());
							 $("#issueListForNeed").delRowData($("#stepId").val());
							 $("#issueListForNeed").trigger("reloadGrid");
						}
						disableButtons();
					}else{
						
						 if($("#DialogName").val()=='maxDeal'){
								$("#maxIssueForBenchDialog").dialog("close");
							    $("#maxIssueListForNeed").toggleSubGridRow($("#stepId").val());
								$("#maxIssueListForNeed").trigger("reloadGrid");
							}else{
								$("#issueForBenchDialog").dialog("close");
								$("#issueListForNeed").toggleSubGridRow($("#stepId").val());
								$("#issueListForNeed").trigger("reloadGrid");
							}
							 disableButtons();
							$.messageBox({message:"已经成功办理该事件处理!"});
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
			  	}
			});
		},
		rules:{
			"issueLog.dealUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"issueLog.mobile":{
				required:true,
				mobile:true
			},
			"dealType":{
				required:true
			},
			"targeOrg":{
				required:function(){
					return $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
						&& $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>;
				}
			},
			"issueLog.content":{
				required:true
			}
		},
		messages:{
			"issueLog.dealUserName":{
				required:"请输入承办人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("至少需要输入{0}个字符")
			},
			"issueLog.mobile":{
				required:"请输入联系人手机",
				mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"dealType":{
				required:"请选择操作类型"
			},
			"targeOrg":{
				required:"请输入目标人"
			},
			"issueLog.content":{
				required:"请输入办理意见"
			}
		}
	});

	
	$("#transferTo").personnelComplete({
		url:"${path}/issue/issueBusinessManage/searchTransferTarget.action?stepId="+$("#stepId").val(),
//		url: "${path}/issue/issueManage/searchTargeOrg/searchTargeOrgForAutoComplete.action?issueDealType=1001",
		multiple: false,
		postDataFunction: function(){
		    return {searchAdmin:function(){
			    				if ($("#transferToAdmin").attr("checked")){
			    					return true;
			    				}else{
				    				return false;
				    			}},
				    dealType :$("#dealType").val(),
				    exceptOrgIds:$("#tellToSelector").val()};
		}
	});
	
	$("#tellToSelector").personnelComplete({
		url:"${path}/issue/issueBusinessManage/searchTellTarget.action?stepId="+$("#stepId").val(),
		postDataFunction: function(){
		    return {
			    dealType :$("#dealType").val(),
			    exceptOrgIds:$("#transferTo").val()};
		}
	});

	transferToDivInit();

});
var bol = true;


function changeDealType(){
	var dealType = $("#dealType").val();
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
	if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| dealType == <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>
		|| dealType == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
		$("#transferToDiv").hide();
		$("#transferToSelectorDiv").hide();
		$("#tellToSelectorDiv").hide();
	}else{
		$("#transferToDiv").show();
		$("#transferToSelectorDiv").show();
		$("#tellToSelectorDiv").show();
		transferToTypeChange($("#transferToAdmin").val());
	}
}

function transferToDivInit(){
	$("#transferToDiv").hide();
	$("#transferToSelectorDiv").hide();
	$("#tellToSelectorDiv").hide();
}

function transferToTypeChange(radio){
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
	if ($("#transferToAdmin").attr("checked")=="checked"){
		if ($("#dealType").val()!=<s:property value='@com.tianque.issue.state.IssueOperate@GIVETO.code'/>){
			$.ajax({
				url:"${path}/issue/issueBusinessManage/getUniqueTrgetAdminOrg.action?stepId="+$("#stepId").val()+"&dealType="+$("#dealType").val(),
				async:false,
				type:'post',
				success:function(data){
					if(!data.value){
					}else{
						$("#transferTo").setPersonnelCompleteVal({
	   						value:data.value,label:data.label,desc:data.desc
	   					});
						$("#transferTo").attr("readonly",true);
					}
				}
			});
		}else{
			$("#transferTo").removeAttr("readonly");
		}
	}else{
		$("#transferTo").removeAttr("readonly");
	}
}

function attachUploaded(id, fileName, responseJSON){
	$("<option>").attr("id",id).val(fileName).attr("selected",true).appendTo($("#attachFileNames"));
}

function removeAttach(fileName){
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}
</script>	
		
	