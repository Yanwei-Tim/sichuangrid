<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
    #blPage{}
    #blPage .left{float:left; width:370px; min-height:450px;  border-right:1px solid #ccc;  margin-right:-1px}

    #blPage .right{float:left; width:305px; min-height:450px;  border-left:1px solid #ccc}

    #blPage #dealCode{width:97%}
    #blPage .left table{width:360px;}
    #blPage .left td{ line-height: 1.6em; padding: 3px;}
    #blPage .dealRecord{padding-top:5px}
    #blPage .innerTxt{position:relative;padding-left:80px; word-break: break-all;}
    #blPage .innerTxt strong{float: left;margin-left: -80px;width:70px;text-align:right; /* position:absolute;top:0; left:0; */}

    #issueTable .record{ padding:5px 10px 5px 10px;  margin-bottom:10px;  line-height:1.6em;  color:#333;  border:1px solid #b2c4d0;  background-color:#ecf5fe;}
     #issueTable .record .recordState{ float:right;}
    #issueTable .dispose{ padding:5px 5px 5px 30px;}

    #dialog-form .grid_4{width:90px}
    #dialog-form .grid_8{width:160px}
    #dialog-form .grid_10{width:190px}
    #dialog-form .grid_5{width:136px}

    #dialog-form .grid_x{ float:left; width:210px}
    #dialog-form .ctt, 
    #dialog-form .tBtn{ float:left; display:inline;  width:132px}
    #dialog-form .tBtn{ margin-left:10px;  width:60px}
    #dialog-form .tBtn input{ width:60px;  height:24px;  line-height:24px}
    #dialog-form .ctt{border:1px solid transparent;padding-right:4px;}
    #dialog-form .errorInput{border: 1px solid #DB0027;}


</style>


<div id="blPage" class="cf">
		
		<div class="left">
			<table >
			<tbody>
		        <tr>
					<td ><div class='innerTxt cf'><strong>事件名称:</strong>${issue.subject}</div></td>
				</tr>
				 <tr>
					<td ><div class='innerTxt cf'><strong>发生时间:</strong><s:date format="yyyy-MM-dd" name="issue.occurDate" /></div></td>
				</tr>
				 <tr>
					<td ><div class='innerTxt cf'><strong>主要当事人:</strong>${issue.mainCharacters}</div></td>
				</tr>
				<tr>
					<td ><div class='innerTxt cf'><strong>发生地点:</strong>${issue.occurLocation}</div></td>
				</tr>
				<tr>
					<td ><div class='innerTxt cf'><strong>事件类型:</strong>
                        <s:if test="issue.issueType==true">
                            <lable>服务审批&nbsp;</lable>
                        </s:if><s:else>
                            <div id="issueTypeDescription"></div>
                        </s:else>
                    </div></td>
			    </tr>
				<tr>
					<td ><div class='innerTxt cf'><strong>事件描述:</strong>${issue.issueContent}</div></td>
				</tr>
				<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
                <tr>
                    <td ><div class='innerTxt'><strong>附件:</strong>
                            <s:iterator value="issueAttachFiles">
                                <a href="/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>">
                                <img  src="${resource_path}/resource/images/fujian.jpg"/><s:property value="fileName"/>
                                </a>
                                <div class='clearLine'></div>
                            </s:iterator>&nbsp;
                    
                        </div>
                    </td>
                </tr>
				</s:if>
				
				
				<tr>
					<td style="border-bottom:dashed 1px #555;width: 300px"></td>
				</tr>
				<tr>
					<td ><strong>处理记录：</strong>
						<div class="dealRecord recordList clearfix">
						<div class="recordContent clearfix">
							<ul style="display:block;" id="issueTable">
								<s:subset source="issueDealLogs" start="0">
									<s:iterator status="index">
									<li class="record" id="2012-12-11">
										<label class="recordState"><s:property value="dealDescription"/></label>
                                        <label ><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></label>
                                        <label ><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="dealUserName"/></label>

                                        <s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
                                        <div class="dispose">
                                            <div class="smallText">
                                                    <strong>办理意见：</strong><span >${content }</span>
                                            </div>
                                            <s:if test="issueLogAttachFiles[id]!=null && issueLogAttachFiles[id].size > 0">
                                                <div class="smallText">
                                                    <span class="filetype-clip"></span>附件
                                                    <s:iterator value="issueLogAttachFiles[id]">
                                                    <a href="${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>"><s:property value="fileName"/></a>
                                                    </s:iterator>
                                                </div>
                                            </s:if>
                                        </div>
                                        </s:if>
									</li>
									</s:iterator>
								</s:subset>
							</ul>
							<!-- issueTable End -->
						</div>
						</div>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		
		
		<div class="right">
			<div id="dialog-form" title="办理事件处理">
				<form id="issueDealForm" method="post" action="${path }/fourTeamsIssueManage/dealIssue.action">
					<pop:token />
					<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
					<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
					<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
					<div class="grid_4 lable-right">
						&nbsp;&nbsp;&nbsp;&nbsp;<em class="form-req">*</em><label class="form-lbl">承办人：</label>
					</div>
					<div class="grid_8">
						<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" />
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<em class="form-req">*</em><label class="form-lbl">联系手机：</label>
					</div>
					<div class="grid_8">
						<input type="text" id="mobile" name="operation.mobile" value="${operation.mobile}" maxlength="11" class="form-txt" />
					</div>
					<div class='clearLine'></div>
					
					<div class="grid_4 lable-right">
						<em class="form-req">*</em><label class="form-lbl">操作类型：</label>
					</div>
					<div class="grid_8">
						<s:select name="dealCode" list="canDoList" onchange="dealTypeChanged()" listKey="code" listValue="desc" emptyOption="true" id="dealCode" />
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right" id="overTime" style="display: none;">
							<label>结案时间：</label>
					</div>
					<div class="grid_4 form-left"  id="endTime"  style="display: none;">
			    			<input type="text" id="getTime" name="issue.occurDate" class="form-txt" value="" readonly />
					</div>
					<div class='clearLine'></div>
					<div id="transferToDiv">
						<s:if test='issueSkiprule==null||issueSkiprule.id==null'>
							<div class="grid_4 lable-right">
								<label class="form-lbl">给：</label>
							</div>
							<div class="grid_8">
								<label for="transferToAdmin">
	                                <input type="radio" name="transferToType" id="transferToAdmin" value="true" checked onclick="transferToTypeChange(this)"/><s:text name="issue.dealIssue.orglable.name"/>
	                            </label>
	                            <label for="transferToFun">
	                                <input type="radio" name="transferToType" id="transferToFun" value="false" onclick="transferToTypeChange(this)"/>职能部门
								</label>
							</div>
						</s:if>
					</div>
					<div class='clearLine'></div>
					<div id="transferTargetDiv">
					<s:if test='issueSkiprule==null||issueSkiprule.id==null'>
						<div class="grid_4 lable-right">
							<em class="form-req">*</em><label class="form-lbl">主办单位：</label>
						</div>
						<div class="grid_x" >
							<div class="ctt">
								<input id="transferTo" type="text" name="operation.targeOrg.id" class="form-txt" />
							</div>
							<div class="tBtn" >
								<input type="button" class="defaultBtn" value="选择" id="selectTransferTo" />
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="grid_4 lable-right">
							<em class="form-req">*</em><label class="form-lbl">主办单位：</label>
						</div>
						<div class="grid_5" >
							<input type="hidden" id="selectSingleOrgIdHid"/>
					 		<input type="hidden" id="selectSingleOrgNameHid"/>
					 		<input type="hidden" id="selectSingleOrgTypeHid"/>
							<input type="hidden" name="operation.targeOrg.id" id="issueSkipruleSubmitOrgId"   class="form-txt" value=""/>
							<input type="text" name="targeOrgName" id=issueSkipruleSubmitOrgName readOnly  class="form-txt" value=""/>
						</div>
						<script type="text/javascript">
						if($("#dealCode").val() == "<s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>"){
							$("#issueSkipruleSubmitOrgId").val("${issueSkiprule.submitOrgId}");
							$("#issueSkipruleSubmitOrgName").val("${issueSkiprule.submitOrgName}");
						}
						$("#issueSkipruleSubmitOrgName").click(function(event){
							var selectSingleOrgUrl="";
							if($("#dealCode").val() != "<s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>"){
								selectSingleOrgUrl = '${path}/issue/issueSkipruleManage/selectSingleOrgDlg.jsp';
							}else{
								if("${issueSkiprule.submitOrgLevel}"=="${issueSkiprule.submitLevel.id}"){
									selectSingleOrgUrl = '${path}/issue/issueSkipruleManage/selectSingleFunOrgDlg.jsp?rootOrgId=${issueSkiprule.submitOrgId}';
								}
							}
							if(selectSingleOrgUrl!=""){
								$("#issueSkipruleMaintanceDialog").createDialog({
									width:300,
									height:400,
									title:'抄送单位选择',
							 		url:selectSingleOrgUrl,
									buttons: {
								   		"确定" : function(event){
								   			$("#issueSkipruleSubmitOrgName").val($("#selectSingleOrgNameHid").val());
											$("#issueSkipruleSubmitOrgId").val($("#selectSingleOrgIdHid").val());
											if($("#selectSingleOrgTypeHid").val()=='1') {
												$("#itemDiv").show();
												fillItemForSkip($("#selectSingleOrgIdHid").val());
											} else {
												$("#itemDiv").hide();
												$("#itemTypeId option").remove();
											}
								        	$(this).dialog("close");
							   			},
								   		"关闭" : function(){
								        	$(this).dialog("close");
								   		}
									}
								});
							}
						});
						</script>
					</s:else>
						<div class="clearLine"></div>
						<div id="itemDiv">
							<div class="grid_4 lable-right">
								<label class="form-lbl">项目类型：</label>
							</div>
							<div class="grid_5 form-left">
							    <select name="operation.issueStep.itemTypeId" class='form-txt' id="itemTypeId">
								</select>
							</div>
						</div>
						<div class="clearLine"></div>
						<div id="dealDeadlineDiv">
							<div class="grid_4 lable-right">
								<label class="form-lbl">办理截止时间：</label>
							</div>
							<div class="grid_5 form-left">
							    <input type="text" id="dealDeadline" name="operation.dealDeadline" readonly="readonly" class="form-txt" />
							</div>
						</div>
						<div class="clearLine"></div>
						<div id="tellToSelectorDiv" style="margin-top:10px;">
						<s:if test='issueSkiprule==null||issueSkiprule.id==null'>
							<div class="grid_4 lable-right">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="form-lbl">抄告：</label>
							</div>
							<div class="grid_x">
								<div class="ctt">
									<input id="tellToSelector" name="tellOrgIds" class="form-txt"/>
								</div>
								<div class="tBtn">
									<input type="button" class="defaultBtn" value="选择" id="selecteTellTo" />
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="grid_4 lable-right">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="form-lbl">抄告：</label>
							</div>
							<div class="grid_5">
								<input type="hidden" id="selectOrgIdHid"/>
						 		<input type="hidden" id="selectOrgNameHid"/>
								<input type="hidden" name="tellOrgIds" id="issueSkipruleCcOrgIds"   class="form-txt" value=""/>
								<input type="text" name="tellOrgNames" id="issueSkipruleCcOrgNames" readOnly  class="form-txt" value=""/>
							
							</div>
						<script type="text/javascript">
						if($("#dealCode").val() == "<s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>"){
							$("#issueSkipruleCcOrgIds").val("${issueSkiprule.ccOrgIds}");
							$("#issueSkipruleCcOrgNames").val("${issueSkiprule.ccOrgNames}");
						}
						$("#issueSkipruleCcOrgNames").click(function(event){
							var rootOrgId = "";
							if($("#dealCode").val() == "<s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>"){
								rootOrgId = "${issueSkiprule.submitOrgId}";
							}
							$("#issueSkipruleMaintanceDialog").createDialog({
								width:300,
								height:400,
								title:'抄送单位选择',
						 		url:'${path}/issue/issueSkipruleManage/selectOrgDlg.jsp?rootOrgId='+rootOrgId,
								buttons: {
							   		"确定" : function(event){
							   			$("#issueSkipruleCcOrgNames").val($("#selectOrgNameHid").val());
										$("#issueSkipruleCcOrgIds").val($("#selectOrgIdHid").val());
							        	$(this).dialog("close");
						   			},
							   		"关闭" : function(){
							        	$(this).dialog("close");
							   		}
								}
							});
						});
						</script>
						</s:else>
						
						</div>
						<div class='clearLine'></div>
					</div>
					<div class='clearLine'></div>
					<div class="grid_4 lable-right">
						<em class="form-req">*</em><label class="form-lbl">办理意见：</label>
					</div>
					<div class="grid_10 heightAuto">
						<textarea name="operation.content" style="width:180px;" rows="8" cols="26"  id="content" ></textarea>
					</div>
					<div class='clearLine'></div>
					<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
				</form>
				
				<div >
					<div class="grid_4 lable-right">
						<label class="form-lbl">&nbsp;&nbsp;&nbsp;&nbsp;附件上传：</label>
					</div>
					<div class="grid_13 heightAuto">
						<div id="custom-queue" style="width: 180px; height: 150px;overflow-y: auto;overflow-x: hidden;" class="ui-widget-border"></div>
						<input id="custom_file_upload" name="uploadFile" type="file" />
					</div>
				</div>
				<div id="orgSelectDialog"></div>
				<div id="issueSkipruleMaintanceDialog"></div>
			</div>
		
		</div>
</div>



<script type="text/javascript">
$(function(){
 	renderIssueType();
	initAttachFileUploader();
	initTargetAutocomplete();
  	initTellsAutocomplete();
    initTargetSelectorAction();
    initTellSelectorAction();
    dealTypeChanged();

	$("#dealDeadline").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		minDate:"+0d"
		});
	$("#issueDealForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			if($("#dealCode").val() != "<s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>"
			&& $("#dealCode").val() != "<s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>"
		    && $("#dealCode").val() != "<s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>"
			&& $("input[name='operation.targeOrg.id']").val()==""){
				$.messageBox({message:"主办单位必须输入！",level:"error"});
				return;
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null||!data.issueStepId){
	           	 		$.messageBox({message:data,level:"error"});
	            		return;
	            	}
					$.messageBox({message:"已经成功办理该事件!"});
					
	     			reloadIssue();
	     			
                    if(typeof(issueForBenchDialog) != 'undefined' ){
                    	$("#issueForBenchDialog").dialog("close");
                    	$("#maxIssueListForNeed").trigger("reloadGrid");
    				}else{
    					try {
		                	$("div[id='issueDialog']:eq(1)").dialog("close");		                	
		                	$("div[id='issueDialog']:eq(0)").dialog("close");
		                } catch (e) {}
        			}
    				
                    getMessageByUser();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交数据时发生错误");
			  	}
			});
		},
		rules:{
			"operation.dealUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"operation.mobile":{
				required:true,
				mobile:true
			},
			"dealCode":{
				required:true
			},
			"targeOrg":{
				required:function(){
					return $("#dealType").val() != <s:property 

value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
						&& $("#dealType").val() != <s:property 

value='@com.tianque.issue.state.IssueOperate@BACK.code'/>;
				}
			},
			"operation.content":{
				required:true,
				maxlength:1000
			}
		},
		messages:{
			"operation.dealUserName":{
				required:"请输入承办人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("至少需要输入{0}个字符")
			},
			"operation.mobile":{
				required:"请输入联系人手机",
				mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"dealCode":{
				required:"请选择操作类型"
			},
			"targeOrg":{
				required:"请输入目标人"
			},
			"operation.content":{
				required:"请输入办理意见",
				maxlength:"办理意见不能超过1000字"
			}
		}
	});

	function getSelectedOrgIds(){
		return $("#tellToSelector").val()+","+$("input[name='operation.targeOrg.id']").val();
	}

	
	function createOrgSearchDialog(searchUrl,inputId,isMultiselect){
		$("#orgSelectDialog").createDialog({
			width:550,
			height:430,
			title:'选择部门',
			url: searchUrl,
			postData:{	adminTarget:function(){return targetIsAdmin();},
						dealCode :$("#dealCode").val(),
						exceptIds:function(){return getSelectedOrgIds();}
					},
			buttons: {
				"确定" : function(event){
						fillOrgInputer(inputId,isMultiselect);
						fillItemName();
						$(this).dialog("close");
				},
				"关闭" : function(){
						$(this).dialog("close");
				}
			}
		});
		
	}
	function initTargetSelectorAction(){
		$("#selectTransferTo").click(function(event){
			createOrgSearchDialog("${path}/fourTeamsIssueManage/dispatch.action?mode=searchTarget&keyId="+
										$("#keyId").val(),"transferTo",false);
		});
	}

	function initTellSelectorAction(){
		$("#selecteTellTo").click(function(event){
			createOrgSearchDialog("${path}/fourTeamsIssueManage/dispatch.action?mode=searchTells&keyId="+
									$("#keyId").val(),"tellToSelector",true);
		});
	}

	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames"});
		$("#attachFileNames").empty();
	}
	
	function initTargetAutocomplete(){
		$("#transferTo").personnelComplete({
			url:"${path}/fourTeamsIssueManage/searchTransferTarget.action?mode=auto&keyId="+$("#keyId").val(),
			multiple: false,
			postDataFunction: function(){
			    return {adminTarget:function(){return targetIsAdmin();},
					    dealCode :$("#dealCode").val(),
					    exceptIds:function(){return getSelectedOrgIds();}
			    		};
			}
		});
	};
	
	function initTellsAutocomplete(){
		$("#tellToSelector").personnelComplete({
			url:"${path}/fourTeamsIssueManage/searchTellTarget.action?mode=auto&keyId="+$("#keyId").val(),
			postDataFunction: function(){
			    return {adminTarget:targetIsAdmin(),
    					dealCode :$("#dealCode").val(),
				    	exceptOrgIds:getSelectedOrgIds()
				    	};
			}
		});
	}
});

function targetIsAdmin(){
	return $("#transferToAdmin").attr("checked")=="checked";
}


function dealTypeChanged(){
	var operateCode = $("#dealCode").val();
	if(    operateCode == ""
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>){
		clearTargetOrg();
		$("#overTime").hide();
		$("#endTime").hide();
		setTransferToVisiabled(false);
		setTellsVisiabled(false);
	}else{
		setTransferToVisiabled(true);
		transferToTypeChange();
		setTellsVisiabled(true);
		if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@ASSIGN.code'/> ){
			<s:if test='issueSkiprule!=null&&issueSkiprule.id!=null'>
				$("#issueSkipruleCcOrgIds").val("");
				$("#issueSkipruleCcOrgNames").val("");
				$("#issueSkipruleSubmitOrgId").val("");
				$("#issueSkipruleSubmitOrgName").val("");
			</s:if>
			$("#overTime").hide();
			$("#endTime").hide();
			$("#dealDeadlineDiv").show();
		}else{
			<s:if test='issueSkiprule!=null&&issueSkiprule.id!=null'>
				if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>){
					$("#issueSkipruleCcOrgIds").val("");
					$("#issueSkipruleCcOrgNames").val("");
					$("#issueSkipruleSubmitOrgId").val("${issueSkiprule.submitOrgId}");
					$("#issueSkipruleSubmitOrgName").val("${issueSkiprule.submitOrgName}");
				}
			</s:if>
			$("#overTime").hide();
			$("#endTime").hide();
			$("#dealDeadlineDiv").hide();
		}
	}
	if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
		 //设置结案时间
		var thisTime=new Date();
		var addMonth=thisTime.getMonth()+1;
		if(addMonth<10){
			addMonth="0"+addMonth;
		}
		var formatdate= thisTime.getFullYear()+"-"+addMonth+"-"+thisTime.getDate();
		$("#getTime").attr("value",formatdate);
		$("#overTime").show();
		$("#endTime").show();
	}
	$("#itemDiv").hide();
}

function clearTargetOrg(){
	$("#transferTo").clearPersonnelComplete();
	$("#tellToSelector").clearPersonnelComplete();
}

function setTransferToVisiabled(visiabled){
	if (visiabled){
		$("#selectTransferTo").show();
		$("#transferToDiv").show();
		$("#transferTargetDiv").show();
	}else{
		$("#selectTransferTo").hide();
		$("#transferToDiv").hide();
		$("#transferTargetDiv").hide();
	}
}

function setTellsVisiabled(visiabled){
	if (visiabled){
		$("#selecteTellTo").show();
		$("#tellToSelectorDiv").show();
	}else{
		$("#selecteTellTo").hide();
		$("#tellToSelectorDiv").hide();
	}
}

function canTells(){
	$.ajax({
		url:"${path}/fourTeamsIssueManage/hasTellTarget.action?keyId="+$("#keyId").val(),
		data:{dealCode    :$("#dealCode").val(),
			  exceptOrgIds:$("#transferTo").val(),
			  adminTarget :function(){return targetIsAdmin();}
			} ,
		async:false,
		type:'post',
		success:function(data){return data && data=="true";}
	});
}

function autoFillUniqueTrget(){
	var funOrgId;
	$.ajax({
		url:"${path}/fourTeamsIssueManage/getUniqueTrgetOrg.action?keyId="+$("#keyId").val(),
		data:{dealCode :$("#dealCode").val(),
			  adminTarget :function(){return targetIsAdmin();}
			} ,
		async:false,
		type:'post',
		success:function(data){
			funOrgId=data.value;
			if(data !=null && data.value){
				$("#transferTo").setPersonnelCompleteVal({
					value:data.value,label:data.label,desc:data.desc
				});
			}
		}
	});
	
	fillItem(funOrgId);
}

function fillItem(selectedId) {
	if($("#transferToFun").attr("checked")=='checked' && typeof(selectedId)!='undefined') {
		if(selectedId == null) {
			return;
		} else {
			$.ajax({
				url:"${path}/fourTeamsIssueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
				async:false,
				type:'post',
				success:function(data){
					$("#itemTypeId option").remove();
					$.each(data,function(name,value) {
						$("<option>").attr("value",name).html(value).appendTo($("#itemTypeId"));
					});
					$("<option>").attr("value","").attr("selected",true).html("").prependTo($("#itemTypeId"));
				}
			});
		}
	}
}
function fillItemForSkip(selectedId) {
	if(typeof(selectedId)!='undefined') {
		if(selectedId == null) {
			return;
		} else {
			$.ajax({
				url:"${path}/fourTeamsIssueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
				async:false,
				type:'post',
				success:function(data){
					$("#itemTypeId option").remove();
					$.each(data,function(name,value) {
						$("<option>").attr("value",name).html(value).appendTo($("#itemTypeId"));
					});
					$("<option>").attr("value","").attr("selected",true).html("").prependTo($("#itemTypeId"));
				}
			});
		}
	}
}


function transferToTypeChange(radio){
	clearTargetOrg();
	autoFillUniqueTrget();
	autoShowOrHideItemDiv();
}
$('#getTime').datePicker({
	yearRange:'1930:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
	
});

function autoShowOrHideItemDiv() {
	if($("#transferToFun").attr("checked")=='checked') {
		$("#itemDiv").show();
	} else {
		$("#itemDiv").hide();
		$("#itemTypeId option").remove();
	}
}

function renderIssueType(){
	var typeDescs=new Array();
	var descIndexs=new Array();
	var typeDesc;
	var actualIndex;
    <s:iterator value="issue.issueTypes" var="type" >
	    actualIndex=getTypeDescById(descIndexs,<s:property value="#type.issueTypeDomain.id"/>);
    	if (actualIndex==typeDescs.length){
    		descIndexs[actualIndex]=<s:property value="#type.issueTypeDomain.id"/>;
    		typeDescs[actualIndex]='<s:property value="#type.issueTypeDomain.domainName" escape="false"/>'+':';
        }
    	typeDescs[actualIndex]=typeDescs[actualIndex]+"<s:property value="#type.issueTypeName+','" escape="false"/>";
	</s:iterator>

	
	for (var index=0;index<typeDescs.length;index++){
		if (index>0){
			typeDesc=typeDesc+'<br>';
		}else{
			typeDesc="";
		}
		typeDesc=typeDesc+typeDescs[index];
	
	}
 	$("#issueTypeDescription").html(typeDesc);
}
</script>

