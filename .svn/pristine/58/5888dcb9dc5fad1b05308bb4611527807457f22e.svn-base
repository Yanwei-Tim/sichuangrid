<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
#input_involvedPersonnel{display:none !important;}
#holder_involvedPersonnel{height:22px;}
#jSIPContainer ul{height:150px !important;}
#issueMaintainForm{display:inline;}
#dialog-form{width:100%;}
.issuePeopleList{padding:5px 10px;margin:5px 20px;background:#eee;overflow:hidden;}
.issuePeopleList #addPeopleItem{color:blue;padding:0 0 0 5px;}
.dlg_innerBox label input{margin-right:5px;vertical-align: middle;}
.dlg_innerBox .delItemBox{display: block;}
.dlg_innerBox .delItemBox a{padding:0 10px;color:#f60;}
</style>
<div id="dialog-form" title="" class="container container_24" style="background: #f4f5f5;">
	<form id="issueMaintainForm" method="post" action="">
			<input id="issueId" name="issue.id" type="hidden" value="${issue.id}" />
			<input id="stepId" name="stepId" type="hidden" value="${issue.currentStep.id}" />
			<input id="occurOrgId" name="issue.occurOrg.id" type="hidden" value="${issue.occurOrg.id}" />
			<input id="serialNumber" name="issue.serialNumber" type="hidden" value="${issue.serialNumber}" />
			<input id="ycHours" type="hidden" value="${issue.hours}" />
			<input id="ycMinute" type="hidden" value="${issue.minute}" />
			<input id="centerLon" name="issue.centerLon" type="hidden" value="${issue.centerLon}">
			<input id="centerLat" name="issue.centerLat" type="hidden" value="${issue.centerLat}">
			<input id="zoom" name="issue.zoom" type="hidden" value="${issue.zoom}">
			<input id="gisType" name="issue.gisType" type="hidden" value="${issue.gisType}">
			<input id="isTransformat" name="issue.isTransformat" type="hidden" value="${issue.isTransformat}">
			<s:if test="@com.tianque.core.util.DialogMode@ADD_MODE.equalsIgnoreCase(mode)">
				<pop:token />
			</s:if>
			<div class="grid_3 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件类型：</label>
			</div>
			<div class="grid_10 lable-right">
				<select id="issueDomain" name="issue.issueType.issueTypeDomain.id" class="form-select">
			   		<pop:IssueTypeReleationSelectTag name=
			   		"contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
			   		defaultValue="${issue.issueType.issueTypeDomain.id}" 
			   		reletionId="issueTypeId" isOperateDiv="1" id="issueDomain" defaultTypeValue="${issue.issueType.id }"/>
				</select>
			</div>
			<div class="grid_10 lable-right">
				<select id="issueTypeId" name="issue.issueType.id" class="form-select" disabled></select>
			</div>
			<div class="grid_3 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_10 form-left">
		    	<input type="text" id="issueSubject" name="issue.subject" maxlength="50" value="${issue.subject}" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<em class="form-req">*</em><label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_7 form-left">
				<input type="text" id="issueOccurOrgSelector" name="selectOrgName"  class="form-txt" />
    		</div>
    		<div class='clearLine'></div>
			<div class="dlg_innerBox">
	    		<div class="grid_3 lable-right">
					<em class="form-req">*</em><label class="form-lbl" id="occurDateLabel">发生时间：</label>
				</div>
				<div class="grid_4 form-left">
			    	<input type="text" id="occurDate" name="issue.occurDate" class="form-txt"
					value="<s:date name="issue.occurDate" format="yyyy-MM-dd "/>" readonly />
				</div>
				<div class="grid_1"></div>
	    		<div class="grid_2 form-left">
					<select id="hours" name="hours" class="form-txt">
						<option value="">时</option>
					</select>
	    		</div>
	    		<div class="grid_1"></div>
	    		<div class="grid_2 form-left">
	    			<select id="minute" name="minute" class="form-txt" disabled>
	    				<option value="">分</option>
	    			</select>
	    		</div>
	    		<c:choose>
				    <c:when test="${issue.emergencyLevel!=null}">
				    	<div class="grid_4 lable-right" id="emergencyLevelDiv">
							<em class="form-req"></em><label class="form-lbl">重大紧急等级：</label>	
				  		</div>
				  		<div class="grid_6" id="emergencyLevel">	
							<label><input type="radio" value="<s:property value='issue.emergencyLevel.id'/>" checked="checked" name="issue.emergencyLevel.id"  /><s:property value='issue.emergencyLevel.displayName'/></label>
				  		</div>
				    </c:when>
				    <c:otherwise>
				    	<div class="grid_4 lable-right" id="emergencyLevelDiv"></div>
			  			<div class="grid_6" id="emergencyLevel"></div>
				    </c:otherwise>
				</c:choose>
				<div id="occurLocationDiv">
		    		<div class='clearLine'></div>
					<div class="grid_3 lable-right">
						<div id="lab1"><label class="form-lbl">发生地点：</label></div>
					</div>
					<div class="grid_14 form-left" id="div1">
				    	<input type="text" id="issueLocation" name="issue.occurLocation" maxlength="50" value="${issue.occurLocation}" class="form-txt" />
					</div>
				</div>
				<div id="importantPlaceDiv">
		    		<div class="grid_4 lable-right">
			    		<c:choose>
						    <c:when test="${issue.importantPlace==1}">
						    	<label><input type="checkbox" checked="checked" value="1" name="issue.importantPlace"/>是否重点场所</label>
						    </c:when>
						    <c:otherwise>
						    	<label><input type="checkbox" value="1"  name="issue.importantPlace"/>是否重点场所</label>
						    </c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class='clearLine'></div>	
				<div class="issuePeopleList">	
					<div class="grid_4 heightAuto">
			  			<label class="form-lbl" id="issueRelatedPeoplesLabel"><em class="form-req">*</em>主要当事人：</label>
			  		</div>
			  		<div class="grid_17 lable-right "><a href="javascript:;" id="addPeopleItem">+增加</a></div>
			  		<div class='clearLine'>&nbsp;</div>	
		    		<div class="grid_24 form-left heightAuto">
		    			<ul class="issuePeopleItems" id="issuePeopleItems">
				    			<s:if test="issue.issueRelatedPeoples !=null">
				    				<s:iterator value="issue.issueRelatedPeoples" id="issueRelatedPeople">
					    				<li>
											<div class="grid_3 lable-right">姓名：</div>
					    					<div class="grid_4"><input type="text" name="issueRelatedPeopleNames" value='<s:property value="name"/>' maxlength="20" class='form-txt 
					    							{maxlength:20,minlength:2,exculdeParticalChar:true,messages:{maxlength:$.format("姓名不能多于{0}个字符"),minlength:$.format("姓名不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' /></div>
					    					<div class="grid_3 lable-right">联系手机：</div>
					    					<div class="grid_4"><input type="text" name="issueRelatedPeopleTelephones" value='<s:property value="telephone"/>' maxlength="11" class='form-txt {maxlength:11,mobile:true,messages:{maxlength:$.format("联系手机不能多于{0}个字符"),mobile:"手机号码必须由1开头的11位数字组成"}}' /></div>
					    					<div class="grid_3 lable-right">固话：</div>
					    					<div class="grid_4"><input type="text" name="issueRelatedPeopleFixPhones" value='<s:property value="fixPhone"/>' maxlength="15" class='form-txt {maxlength:15,telephone:true,messages:{maxlength:$.format("固话不能多于{0}个字符"),telephone:"固话不合法，只能输数字和横杠(-)"}}' /></div>
					    					<div class="grid_3 delItemBox" style='display:block;'><a href="javascript:;" class="delPeopleItem">删除</a></div>
				    					</li>
			    					</s:iterator>
								</s:if>
								<s:else>
									<li>
										<div class="grid_3 lable-right">姓名：</div>
					    				<div class="grid_4"><input type="text" name="issueRelatedPeopleNames" value="" maxlength="20" class='form-txt 
					    				{maxlength:20,minlength:2,exculdeParticalChar:true,messages:{maxlength:$.format("姓名不能多于{0}个字符"),minlength:$.format("姓名不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' /></div>
					    				<div class="grid_3 lable-right">联系手机：</div>
					    				<div class="grid_4"><input type="text" name="issueRelatedPeopleTelephones" value="" maxlength="11" class='form-txt {maxlength:11,mobile:true,messages:{maxlength:$.format("联系手机不能多于{0}个字符"),mobile:"手机号码必须由1开头的11位数字组成"}}' /></div>
					    				<div class="grid_3 lable-right">固话：</div>
					    				<div class="grid_4"><input type="text" name="issueRelatedPeopleFixPhones" value="" maxlength="15" class='form-txt {maxlength:15,telephone:true,messages:{maxlength:$.format("固话不能多于{0}个字符"),telephone:"固话不合法，只能输数字和横杠(-)"}}' /></div>
					    				<div class="grid_3 delItemBox" style='display:block;'><a href="javascript:;" class="delPeopleItem">删除</a></div>
									</li>
								</s:else>
			    			
		    			</ul>
		    			
		    		</div>
	    		</div>
	    		<div id="involvedPersonnelDiv">
	    			<div class='clearLine'>&nbsp;</div>	
					<div class="grid_3 lable-right">
		  				<label class="form-lbl">特殊人群：</label>
			  		</div>
		    		<div class="grid_20 form-left heightAuto" style="line-height:20px;">
						<input type="text" id="involvedPersonnel" value="" name="involvedPersonnel" readonly="readonly" class="form-txt" />
		    		</div>
	    		</div>
	    		
				<div id="issueKindAndCount">
					<div class='clearLine'>&nbsp;</div>	
					<div class="grid_3 lable-right">
						<label class="form-lbl"><em class="form-req">*</em>事件规模：</label>
					</div>
					<div class="grid_8 form-left">
					<select id="issueKind" name="issue.issueKind.id" class='form-txt'>
						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}"/>
					</select>
						
					</div>
					<div class="grid_4 lable-right">
						<label class="form-lbl"><em class="form-req">*</em>涉及人数：</label>
					</div>
					<div class="grid_7 form-left">
						<input type="text"  name="issue.relatePeopleCount" maxlength="6" value="${issue.relatePeopleCount}" class="form-txt" style="text-align:right;" />
					</div>
			   		<div class="grid_1">(人)</div>
				</div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_3 lable-right">
					<em class="form-req">*</em><label class="form-lbl">事件简述：</label>
				</div>
				<div class="grid_20 heightAuto">
	    			<textarea id="issueContent" name="issue.issueContent" class="form-txt" style="height:50px;">${issue.issueContent}</textarea>
				</div>
			</div>
			<div id="subMaintanceDialog"></div>
			<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
			<div class='clearLine'>&nbsp;</div>
	</form>
	
	<div class="clearfix">
		<div class="grid_3 lable-right">
			<label class="form-lbl">上传附件：</label>
		</div>
		<div  class="grid_13 heightAuto" >
			<input id="custom_file_upload" name="uploadFile" type="file" />
			<div class='clearLine'>&nbsp;</div>
			<div id="custom-queue" style="width:375px;height:165px;overflow-y: auto;overflow-x: hidden;border:1px solid #ccc;background:#fff;"></div>
		</div>			
		<div class="grid_8 heightAuto" >
			<div>&nbsp;</div>
			<div id="issueViewMap" style="width: 250px; height: 165px;overflow-y: hidden;overflow-x: hidden;border:1px solid #7F9DB9">
			</div>
			<div>
			<c:if test='${mode!="view"}'>
				<input type="button" value="绑定" class="defaultBtn" id="bindMap"/>
				<c:if test='${ issue.centerLon != null && issue.centerLat !=null}'>
					<input type="button" class="defaultBtn" value="清除" id="cancelBind"/>
				</c:if>
			</c:if>
			</div>
		</div>
	</div>
	<div id="appendIssueKindAndCount" style="display: none;">
		<div class='clearLine'>&nbsp;</div>	
		<div class="grid_3 lable-right">
			<label class="form-lbl"><em class="form-req">*</em>事件规模：</label>
		</div>
		<div class="grid_8 form-left">
		<select id="issueKind" name="issue.issueKind.id" class='form-txt'>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}"/>
		</select>
			
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl"><em class="form-req">*</em>涉及人数：</label>
		</div>
		<div class="grid_7 form-left">
			<input type="text"  name="issue.relatePeopleCount" maxlength="6" value="${issue.relatePeopleCount}" class="form-txt" style="text-align:right;" />
		</div>
   		<div class="grid_1">(人)</div>
	</div>
	<!-- 
	<div id="appendEmergencyLevelDiv" style="display: none;">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">重大紧急等级：</label>
  		</div>
  		<div class="grid_6">
  			<s:if test="issue.emergencyLevel!=null">
				<label><input type="radio" value="<s:property value='issue.emergencyLevel.id'/>" checked="checked" name="issue.emergencyLevel.id"  /><s:property value='issue.emergencyLevel.displayName'/></label>
  			</s:if>
			<s:else>
				<s:iterator value="emergencyLevels" id="emergencyLevel">
					<label><input type="radio" value='<s:property value="id"/>'  name="issue.emergencyLevel.id"/><s:property value="displayName"/></label>
				</s:iterator>
			</s:else>
  		</div>
	</div> -->
	<div id="appendImportantPlaceDiv" style="display: none;">
   		<div class="grid_4 lable-right">
   			<c:choose>
			    <c:when test="${issue.importantPlace==1}">
			    	<label><input type="checkbox" checked="checked" value="1" name="issue.importantPlace"/>是否重点场所</label>
			    </c:when>
			    <c:otherwise>
			    	<label><input type="checkbox" value="1"  name="issue.importantPlace"/>是否重点场所</label>
			    </c:otherwise>
			</c:choose>
		</div>
	</div>
	<div id="appendOccurLocationDiv" style="display: none;">
   		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<div id="lab1"><label class="form-lbl" id="occurLocationLabel">发生地点：</label></div>
		</div>
		<div class="grid_14 form-left" id="div1">
	    	<input type="text" id="issueLocation" name="issue.occurLocation" maxlength="50" value="${issue.occurLocation}" class="form-txt" />
		</div>
	</div>
	<div id="appendserviceOccurLocationDiv" style="display: none;">
   		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<div id="lab1"><label class="form-lbl" id="occurLocationLabel">受理地点：</label></div>
		</div>
		<div class="grid_14 form-left" id="div1">
	    	<input type="text" id="serviceIssueLocation" name="issue.occurLocation" maxlength="50" value="${issue.occurLocation}" class="form-txt" />
		</div>
	</div>
	
</div>

<script type="text/javascript" >
var issueTree;
function getDefaultOccurOrg(){
	<c:choose>
	    <c:when test="${issue.occurOrg != null && issue.occurOrg.id!=null}">
	     	return "${issue.occurOrg.id}";
	    </c:when>
	    <c:otherwise>
	    	return -1;
	    </c:otherwise>
	</c:choose>
}
$(document).ready(function(){
	 $("#involvedPersonnel").personnelComplete({
			url: "${path}/issues/searchIssue/searchPersonnelForAutoComplete.action",
			multiple: true,
			postDataFunction: function(){
			    var orgId=getOccurOrgId();
			    return {orgId:orgId};
			}
		});  
	<s:iterator id="person" value="relatePersonMap">
		var key = "<s:property value='#person.key'/>";
	    <s:iterator value="#person.value" status="s">
	   		var value = key+"-<s:property value='worksheetid'/>"
	   		var label = "<s:property value='name'/>";
	    	$("#involvedPersonnel").setPersonnelCompleteVal({value:value,label:label,desc:""});
	    </s:iterator>
	</s:iterator>
	
	 
	 
    <s:if test="@com.tianque.core.util.DialogMode@EDIT_MODE.equalsIgnoreCase(mode)">
		$("#issueDomain").attr("disabled","disabled");
		$("#issueTypeId").attr("disabled","disabled");
		var issueTypeId = $("#issueTypeId").find("option:selected").val();
		var issueTypeDomainId = $("#issueDomain").find("option:selected").val();
		$("#issueMaintainForm").append('<input type="hidden" name="issue.issueType.id" value="'+issueTypeId+'">');	
		$("#issueMaintainForm").append('<input type="hidden" name="issue.issueType.issueTypeDomain.id" value="'+issueTypeDomainId+'">');	
	</s:if>
	
	
	<s:iterator id="place" value="relatePlacesMap">
	if(!$("#isInvolvedPlace").attr("checked")){
		$("#isInvolvedPlace").attr("checked","checked");
		showInvolvedPlaces();
	}
	var key = "<s:property value='#place.key'/>";
    <s:iterator value="#place.value" status="s">
   		var value = key+"-<s:property value='worksheetid'/>"
   		var label = "<s:property value='name'/>";
    	$("#involvedPlace").setPersonnelCompleteVal({value:value,label:label,desc:""});
    </s:iterator>
    </s:iterator>
	
});
    function issueAdding(){
		return <s:property value='"add".equals(mode)'/>;
	}

	function issueEditing(){
		return <s:property value='"edit".equals(mode)'/>;
	}
	function fillIssueAttenchFiles(){
		<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
		    <s:iterator value="issueAttachFiles">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value='id'/>&issueMode=<s:property value='issueMode'/>",   	     	 
			 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
		    </s:iterator>
		</s:if>
	}
	function initIssueTypeSelector(){
		<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
			<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
				$("#issueTypeSelector${st.index}").typeSelect({width:${issueTypeName.width},columns:${issueTypeName.column},close:function(ids,labels){renderSelectedIssueTypes();}});
			</s:if>
		</s:iterator>
	}
	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}
function operateDiv(){
	var issueType = $("#issueDomain").find("option:selected").text();
	setDivShow(issueType);
	//setEmergencyLevelDiv();
}	
function setDivShow(flag){
	switch(flag){
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@PEOPLELIVE_SERVICE" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>主要当事人：');
			$("#issueKindAndCount").empty();
			$("#occurDateLabel").html("受理时间：");
			$("#occurLocationDiv").empty().append($("#appendserviceOccurLocationDiv").html());
			$("#importantPlaceDiv").empty();
			//$("#emergencyLevelDiv").empty();
			$("#involvedPersonnelDiv").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@RESOLVETHECONTRADICTIONS" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>主要当事人：');
			$("#occurDateLabel").html("发生时间：");
			$("#issueKindAndCount").empty().append($("#appendIssueKindAndCount").html());
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());	
			$("#importantPlaceDiv").empty().append($("#appendImportantPlaceDiv").html());
			//$("#emergencyLevelDiv").empty().append($("#appendEmergencyLevelDiv").html());
			$("#involvedPersonnelDiv").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SECURITYPRECAUTIONS" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('联系人：');
			$("#occurDateLabel").html("发生时间：");
			$("#issueKindAndCount").empty();
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());	
			$("#importantPlaceDiv").empty().append($("#appendImportantPlaceDiv").html());
			//$("#emergencyLevelDiv").empty().append($("#appendEmergencyLevelDiv").html());
			$("#involvedPersonnelDiv").hide();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SPECIALPOPULATIONS" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>服务对象：');
			$("#occurDateLabel").html("服务时间：");
			$("#issueKindAndCount").empty();
			$("#involvedPersonnelDiv").hide();
			$("#importantPlaceDiv").empty();
			//$("#emergencyLevelDiv").empty();
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());
			$("#occurLocationLabel").html("服务地点：");
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SOCIALCONDITIONS" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>相关人员：');
			$("#occurDateLabel").html("时间：");
			$("#issueKindAndCount").empty();
			$("#involvedPersonnelDiv").hide();
			$("#importantPlaceDiv").empty();
			//$("#emergencyLevelDiv").empty();
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());
			$("#occurLocationLabel").html("地点：");
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@POLICIESANDLAWS" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>相关人员：');
			$("#occurDateLabel").html("时间：");
			$("#issueKindAndCount").empty();
			$("#involvedPersonnelDiv").hide();
			$("#importantPlaceDiv").empty();
			//$("#emergencyLevelDiv").empty();
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());
			$("#occurLocationLabel").html("地点：");
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@EMERGENCIES" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>主要当事人：');
			$("#occurDateLabel").html("发生时间：");
			$("#issueKindAndCount").empty().append($("#appendIssueKindAndCount").html());
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());	
			$("#importantPlaceDiv").empty().append($("#appendImportantPlaceDiv").html());
			//$("#emergencyLevelDiv").empty().append($("#appendEmergencyLevelDiv").html());
			$("#involvedPersonnelDiv").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@OTHERMANAGE" escape="false"/>':
			$("#issueRelatedPeoplesLabel").html('<em class="form-req">*</em>主要当事人：');
			$("#occurDateLabel").html("时间：");
			$("#issueKindAndCount").empty();
			$("#involvedPersonnelDiv").hide();
			$("#importantPlaceDiv").empty();
			//$("#emergencyLevelDiv").empty().append($("#appendEmergencyLevelDiv").html());
			$("#occurLocationDiv").empty().append($("#appendOccurLocationDiv").html());
			$("#occurLocationLabel").html("地点：");
			break;
	}
}


function callback(){
    var dfop = {
       	peoplelive:'<s:property value="@com.tianque.issue.constants.IssueTypes@PEOPLELIVE_SERVICE" escape="false"/>',
       	resolvetheCon:'<s:property value="@com.tianque.issue.constants.IssueTypes@RESOLVETHECONTRADICTIONS" escape="false"/>',
       	security:'<s:property value="@com.tianque.issue.constants.IssueTypes@SECURITYPRECAUTIONS" escape="false"/>',
       	specialPo:'<s:property value="@com.tianque.issue.constants.IssueTypes@SPECIALPOPULATIONS" escape="false"/>',
       	social:'<s:property value="@com.tianque.issue.constants.IssueTypes@SOCIALCONDITIONS" escape="false"/>',
       	policiesandLaws:'<s:property value="@com.tianque.issue.constants.IssueTypes@POLICIESANDLAWS" escape="false"/>',
       	emergences:'<s:property value="@com.tianque.issue.constants.IssueTypes@EMERGENCIES" escape="false"/>',
       	othermanage:'<s:property value="@com.tianque.issue.constants.IssueTypes@OTHERMANAGE" escape="false"/>',
       	edit:'<s:property value="@com.tianque.core.util.DialogMode@EDIT_MODE"/>',
       	town:'<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>',
       	mode:'${mode}'
    }
    TQ.maintainIssueDlg(dfop);
}

$.cacheScript({
    url:'/resource/getScript/issue/issueManage/maintainIssueDlg.js',
    callback: callback
})
</script>