<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
    #issueTable .dispose{ padding:5px 5px 5px 3px;}

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
	.small_Text {display:block;color:#777;word-break: break-all;word-wrap: break-word;padding-right:0px;!important}
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
                        <c:choose>
						    <c:when test="${issue.issueType==true}">
						     <lable>服务审批&nbsp;</lable>
						    </c:when>
						    <c:otherwise>
						    <div id="issueTypeDescription"></div>
						    </c:otherwise>
						</c:choose>
                    </div></td>
			    </tr>
				<tr>
					<td ><div class='innerTxt cf'><strong>事件描述:</strong>${issue.issueContent}</div></td>
				</tr>
				<c:if test="${issueAttachFiles != null && fn:length(issueAttachFiles)>0}">
                <tr>
                    <td ><div class='innerTxt'><strong>附件:</strong>
                    		<c:forEach items="${issueAttachFiles}" var="issueAttachFile">
                                <a href="/issues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}">
                                <img  src="${resource_path}/resource/images/fujian.jpg"/>${issueAttachFile.fileName}
                                </a>
                                <div class='clearLine'></div>
                            </c:forEach>&nbsp;
                    
                        </div>
                    </td>
                </tr>
				</c:if>
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
                                        <label ><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></label>
                                        <label >
                                        <c:if test="${dealOrg.orgName !='中国' }">
                                           <s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
	                                        <c:choose>
											    <c:when test="${dealUserName == 'admin' }">
											     	系统消息
											    </c:when>
											    <c:otherwise>
											    	<s:property value="dealUserName"/>
											    </c:otherwise>
											</c:choose>
                                        </label>
                                        <label class="">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="dealDescription"/></label>
                                        
										<c:if test="${(content != null && not empty  content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0) }">
                                       	<hr size=1 style="border-style:dashed ;width:100%">
                                        <div class="dispose">
                                            <div class="small_Text">
                                            <c:if test="${not empty fourTeamsName}">
												 <strong>办理队伍：</strong>${fourTeamsName }<br/>
											</c:if>
                                              <strong>办理意见：</strong>${content }
                                            </div>
                                            <c:if test="${issueLogAttachFiles[id] != null && fn:length(issueLogAttachFiles[id])>0}">
                                                <div class="small_Text">
                                                <strong>附件：</strong>
                                                    <c:forEach items="${issueLogAttachFiles[id]}" var="issueLogAttachFile">
                                                    <span>
                                                    	<a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=${issueLogAttachFile.id}" title="${issueLogAttachFile.fileName }">
                                                    		<c:choose>
                                                    			<c:when test="${fn:length(issueLogAttachFile.fileName) > 18 }">
                                                    				${fn:substring(issueLogAttachFile.fileName, 0, 8)}...${fn:substring(issueLogAttachFile.fileName,fn:length(issueLogAttachFile.fileName)-12 , fn:length(issueLogAttachFile.fileName))}
                                                    			</c:when>
                                                    			<c:when test="${fn:length(issueLogAttachFile.fileName) <= 18 }">
                                                    				${issueLogAttachFile.fileName }
                                                    			</c:when>
                                                    		</c:choose>
                                                    	</a>
                                                    </span><br>
                                                    </c:forEach>
                                                </div>
                                            </c:if>
                                        </div>
                                        </c:if>
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
				<form id="issueDealForm" method="post" action="${path }/issues/issueManage/dealIssue.action">
					<pop:token />
					<input type="hidden" name="issueStep.id" value="${issueStep.id }">
					<input type="hidden" name="issueStep.emergencyLevel.id" value="${issueStep.emergencyLevel.id }">
					<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
					<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
					<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
					<input type="hidden"  id="hasTeamOrgId_temp" value="<s:property value='operation.issueStep.target.id'/>"/>
					<input type="hidden"  id="fourTeamType_temp" value="<s:property value='operation.issueStep.fourTeamsTypeID'/>" />
					<input type="hidden"  id="fourTeamName_temp" value="<s:property value='operation.issueStep.fourTeams.names'/>"/>
					<input type="hidden"  id="fourTeam_temp" value="<s:property value='operation.issueStep.fourTeams.id'/>" />
					<div class="grid_4 lable-right">
						&nbsp;&nbsp;&nbsp;&nbsp;<em class="form-req">*</em><label class="form-lbl">承办人：</label>
					</div>
					<div class="grid_8">
						<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" readonly="readonly" />
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
					<div class="grid_4 lable-right" id="verificationTime" style="display: none;">
							<label>验证时间：</label>
					</div>
					<div class="grid_4 form-left"  id="endTime"  style="display: none;">
			    			<input type="text" id="getTime" name="issue.occurDate" class="form-txt" value="" readonly />
					</div>
					<div class='clearLine'></div>
					<div id="transferToDiv" style="display: none;">
					
						<c:if test="${issueSkiprule==null||issueSkiprule.id==null}">
							<div class="grid_4 lable-right">
								<label class="form-lbl">给：</label>
							</div>
							<div class="grid_8">
								<label for="transferToAdmin">
	                                <input type="radio" name="transferToType" id="transferToAdmin" value="true" checked onclick="transferToTypeChange(this)"/>
	                                <!--<s:text name="issue.dealIssue.orglable.name"/>-->行政部门
	                            </label>
	                            
	                            <label for="transferToFun">
	                                <input type="radio" name="transferToType" id="transferToFun" value="false" onclick="transferToTypeChange(this)"/>职能部门
								</label>
								
							</div>
						</c:if>
					
					</div>
					<div class='clearLine'></div>
					<div id="transferTargetDiv">
					<c:choose>
					    <c:when test="${issueSkiprule==null||issueSkiprule.id==null}">
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
					    </c:when>
					    <c:otherwise>
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
										title:'主办单位选择',
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
						</c:otherwise>
					</c:choose>
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
								<label class="form-lbl">截止时间：</label>
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
							</div>
							<div class='clearLine'></div>
					</div>
					<div id="selectTeams" style="display: none;">
						<div class="grid_4 lable-right">
							<input type="checkbox" value="0" id="selectTeamsId" onclick="selectTeams(this.value);"/>&nbsp;&nbsp;
						</div>
						<div class="grid_5 form-left">
							   <label class="form-lbl">&nbsp;&nbsp;是否由四支队伍办理</label>
						</div>
					</div>
					<div class='clearLine'></div>
					<div id="serviceTeams" style="display: none;">
						<div class="grid_4 lable-right">
							<em class="form-req">*</em><label class="form-lbl">办理队伍：</label>
						</div>
						<div class="grid_x" >
							<div class="ctt">
								<input id="issueStepFourTeamName" type="text" name="operation.fourTeamsName" readonly="readonly" unselectable="on" class="form-txt {required:true,messages:{required:'请选择办理队伍！'}}" value="" />
								<input id="issueStepFourTeamId" type="hidden" name="operation.issueStep.fourTeamsTypeID" class="form-txt" value="" />
								<input id="issueStepFourTeams" type="hidden" name="operation.issueStep.fourTeams.id" class="form-txt" value="" />
							</div>
							<div class="tBtn" id="selectFourTeamsDiv">
								<input type="button" class="form-txt" value="选择" id="selectFourTeams" />
								<script type="text/javascript">
								$("#selectFourTeams").click(function(event){
									$("#selectFourTeamsDialog").createDialog({
										width:600,
										height:400,
										title:'',
								 		url:'${path}/issue/issueManage/search/searchAndSelectFourTeam.jsp?orgId='+USER_ORG_ID,
										buttons: {
									   		"确定" : function(event){
								 				fillFourTeamInputer("issueStepFourTeamName","issueStepFourTeamId","issueStepFourTeams");
									        	$(this).dialog("close");
								   			},
									   		"关闭" : function(){
									        	$(this).dialog("close");
									   		}
										}
									});
								});
								</script>
							</div>
						</div>
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
				<div id="selectFourTeamsDialog"></div>
			</div>
		
		</div>
</div>



<script type="text/javascript">
$(function(){
	OperationOfTellFun();
 	renderIssueType();
	initAttachFileUploader();
	initTargetAutocomplete();
  	initTellsAutocomplete();
    initTargetSelectorAction();
    initTellSelectorAction();
    dealTypeChanged();
    initSkipSubmit();
        
    function initSkipSubmit(){//如果越级上报的目标层级比当前登录用户低，则不能上报
    	<c:if test='${issueSkiprule!=null&&issueSkiprule.id!=null}'>
	    	if("<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()'/>"
	    			>"<s:property value='issueSkiprule.submitLevel.internalId'/>"){
	    		$("#dealCode option[value='41']").remove(); 
	    	}
	    </c:if>
    }
    
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
			&& $("#dealCode").val() != "<s:property value='@com.tianque.issue.state.IssueOperate@VERIFICATION.code'/>"
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
					$("#issueForBenchDialog").dialog("close");
					findIssueNeedDo();
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
					return $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
						&& $("#dealType").val() != <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>;
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
			createOrgSearchDialog("${path}/issues/issueManage/dispatch.action?mode=searchTarget&keyId="+
										$("#keyId").val(),"transferTo",false);
		});
	}

	function initTellSelectorAction(){
		$("#selecteTellTo").click(function(event){
			createOrgSearchDialog("${path}/issues/issueManage/dispatch.action?mode=searchTells&keyId="+
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
			url:"${path}/issues/issueManage/searchTransferTarget.action?mode=auto&keyId="+$("#keyId").val(),
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
			url:"${path}/issues/issueManage/searchTellTarget.action?mode=auto&keyId="+$("#keyId").val(),
			postDataFunction: function(){
			    return {adminTarget:targetIsAdmin(),
    					dealCode :$("#dealCode").val(),
				    	exceptOrgIds:getSelectedOrgIds()
				    	};
			}
		});
	}
});

function selectTeams(value){
	$("#selectFourTeamsDiv").show();
	if(value == '' || value == 0){
	 	 var temOrgid = $("#hasTeamOrgId_temp").val();
		 var teamTypeId = $("#fourTeamType_temp").val();
		 var teamName = $("#fourTeamName_temp").val();
		 var team = $("#fourTeam_temp").val();
		 if(teamName != '' && teamName !='' && teamTypeId !='' && teamTypeId !=""){
			 if(temOrgid == USER_ORG_ID){
					$("#issueStepFourTeamName").val(teamName);
					$("#issueStepFourTeamId").val(teamTypeId);
					$("#issueStepFourTeams").val(team);
					$("#selectFourTeamsDiv").hide();
				 }
			}
		 $("#selectTeamsId").val(1);
		 $("#selectTeamsId").attr("checked",true);
		 $("#serviceTeams").show();
		 $("#issueStepFourTeamName").removeAttr("disabled");
	}else{
		 $("#selectTeamsId").val(0);
		 $("#selectTeamsId").attr("checked",false);
		 $("#serviceTeams").hide();
		 $("#issueStepFourTeamName").val("");
		 $("#issueStepFourTeamId").val("");
		 $("#issueStepFourTeams").val("");
		 $("#issueStepFourTeamName").attr("disabled","disabled");
	}
}

function targetIsAdmin(){
	return $("#transferToAdmin").attr("checked")=="checked";
}


function dealTypeChanged(){
	OperationOfTellFun();
	var operateCode = $("#dealCode").val();
	$("#serviceTeams").hide();
	$("#selectTeams").hide();
	$("#issueStepFourTeamName").attr("disabled","disabled");
	selectTeams(1);
	$("#issueStepFourTeamName").attr("disabled","disabled");
	if(    operateCode == ""
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@VERIFICATION.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@BACK.code'/>){
		clearTargetOrg();
		$("#overTime").hide();
		$("#verificationTime").hide();
		$("#endTime").hide();
		if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMMENT.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@VERIFICATION.code'/>
		|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
			$("#selectTeams").show();
			 var temOrgid = $("#hasTeamOrgId_temp").val();
			 var teamTypeId = $("#fourTeamType_temp").val();
			 var teamName = $("#fourTeamName_temp").val();
			 if(teamName != '' && teamName !='' && teamTypeId !='' && teamTypeId !=""){
				 selectTeams(0);
				 $("#selectTeamsId").attr("disabled","disabled");
			 }
			//$("#issueStepFourTeamName").removeAttr("disabled");
		}else{
			$("#serviceTeams").hide();
			$("#selectTeams").hide();
			$("#issueStepFourTeamName").attr("disabled","disabled");
		}
		setTransferToVisiabled(false);
		setTellsVisiabled(false);
	}else{
		$("#serviceTeams").hide();
		$("#selectTeams").hide();
		$("#issueStepFourTeamName").attr("disabled","disabled");
		setTransferToVisiabled(true);
		transferToTypeChange();
		setTellsVisiabled(true);
		if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@ASSIGN.code'/> ){
			<c:if test='${issueSkiprule!=null&&issueSkiprule.id!=null}'>
				$("#issueSkipruleCcOrgIds").val("");
				$("#issueSkipruleCcOrgNames").val("");
				$("#issueSkipruleSubmitOrgId").val("");
				$("#issueSkipruleSubmitOrgName").val("");
			</c:if>
			$("#overTime").hide();
			$("#verificationTime").hide();
			$("#endTime").hide();
			$("#dealDeadlineDiv").show();
			$("#transferToDiv").show();
			OperationOfTellFun();
			var orgLevelAssign = '${orgLevel}';
			if(orgLevelAssign == 20){
				$("#tellToSelectorDiv").hide();
			}
		}else{
			<c:if test='${issueSkiprule!=null&&issueSkiprule.id!=null}'>
				if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@SUBMIT.code'/>){
					$("#issueSkipruleCcOrgIds").val("");
					$("#issueSkipruleCcOrgNames").val("");
					$("#issueSkipruleSubmitOrgId").val("${issueSkiprule.submitOrgId}");
					$("#issueSkipruleSubmitOrgName").val("${issueSkiprule.submitOrgName}");
				}
			</c:if>
			$("#overTime").hide();
			$("#verificationTime").hide();
			$("#endTime").hide();
			$("#dealDeadlineDiv").hide();
			$("#transferToDiv").hide();
		}
	}
	if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>	
	|| operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@VERIFICATION.code'/>){
		 //设置结案时间
		var thisTime=new Date();
		var addMonth=thisTime.getMonth()+1;
		if(addMonth<10){
			addMonth="0"+addMonth;
		}
		var formatdate= thisTime.getFullYear()+"-"+addMonth+"-"+thisTime.getDate();
		$("#getTime").attr("value",formatdate);
		if(operateCode == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
			$("#verificationTime").hide();
			$("#overTime").show();
		}else{
			$("#verificationTime").show();
			$("#overTime").hide();
			$("#selectTeams").hide();
		}
		$("#endTime").show();
		$("#itemDiv").hide();
	}
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
	OperationOfTellFun();
}

function setTellsVisiabled(visiabled){
	if (visiabled){
		$("#selecteTellTo").show();
		//$("#tellToSelectorDiv").show();
	}else{
		$("#selecteTellTo").hide();
		//$("#tellToSelectorDiv").hide();
	}
}

function canTells(){
	$.ajax({
		url:"${path}/issues/issueManage/hasTellTarget.action?keyId="+$("#keyId").val(),
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
		url:"${path}/issues/issueManage/getUniqueTrgetOrg.action?keyId="+$("#keyId").val(),
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
				url:"${path}/issues/issueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
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
				url:"${path}/issues/issueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
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

function getTypeDescById(indexs,id){
	for (var index=0;index<indexs.length;index++){
		if (indexs[index]==id) return index;
	}
	return indexs.length;
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

function OperationOfTellFun(){
	//职能部门不显示交办和抄告
	var functional = '${OperationOfTell}';
	var orgLevel = '${orgLevel}';
	if(functional == true || functional == 'true'){
		$("#dealCode option[value='21']").remove();
		$("#transferToDiv").hide();
		$("#tellToSelectorDiv").hide();
	}else{
		if(orgLevel <=20){
			$("#transferToDiv").hide();
			$("#tellToSelectorDiv").hide();
			if(orgLevel == 20){
				$("#tellToSelectorDiv").show();
			}
		}
	}
	
}
</script>

