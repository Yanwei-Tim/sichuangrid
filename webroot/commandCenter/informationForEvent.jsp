<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	<div class="clearfix" style="border:1px solid #A6C9E2;background:#EAF4FD;">
		<div class="grid_4 lable-right">
			<label class="form-lb1">来源人：</label>
		</div>
		<div class="grid_7">
			<label class="form-lb1">${eventSource.sourcePeople }</label>
		</div>	
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">发生时间：</label>
		</div>
		<div class="grid_7">
			<label class="form-lb1"><s:date	name="eventSource.sourceDate" format="yyyy-MM-dd" /></label>
		</div>
			
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系号码：</label>
		</div>
		<div class="grid_7">
			<label class="form-lb1">${eventSource.telephone }</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">来源方式：</label>
		</div>
		<div class="grid_7">
			<label class="form-lb1"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"
			 defaultValue="${eventSource.sourceType.id}" /></label>
		</div>	 
		<div class="grid_4 lable-right">	
			<label class="form-lb1">所属区域：</label>
		</div>
		<div class="grid_20">	
			<label class="form-lb1">${eventSource.orgName}</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">主题：</label>
		</div>
		<div class="grid_20">
			<label class="form-lb1">${eventSource.title}</label>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">内 容：</label>
		</div>
		<div class="grid_20">
			<label class="form-lb1">${eventSource.content}</label>
		</div>
		
  </div>  
  <div class="" style="margin:10px 0 0 0;">
		<form id="eventSourceAddIssueForm" method="post" action="${path}/issues/issueManage/addIssue.action">	
		        <input type="hidden" id="eventSourceId" name="eventSource.id" value="${eventSource.id}">
		        <input id="occurOrgId" name="issue.occurOrg.id" type="hidden" value="${issue.occurOrg.id}" />
		        <input id="sourceKind" name="issue.sourceKind.id" type="hidden" value="${eventSource.sourceType.id}" />
		        <input id="dealOrgId" name="dealOrgId" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>"> 
		        <input id="internalId" name="internalId" type="hidden" value="2"> 
		        <pop:token />
		    	<div class="grid_4 lable-right">
		    		 <em class="form-req">*</em><label class="form-lb1">事件名称：</label>
				</div>
		    	<div class="grid_20">
		    		<input type="text" id="issueSubject" name="issue.subject" maxlength="50" value="${eventSource.title}" class="form-txt" />
		   	 	</div>
		   	 	<div class="grid_4 lable-right">
		   	 	    <em class="form-req">*</em><label class="form-lbl">发生网格：</label>
			  	</div>
		    	<div class="grid_7 form-left">
					<input type="text" id="issueOccurOrgSelector" name="selectOrgName" value="${issue.occurOrg.orgName}" class="form-txt" />
		    	</div>
		    	<div class="grid_4 lable-right">
					<label class="form-lbl">发生时间：</label>
				</div>
				<div class="grid_7 form-left">
						<input type="text" id="formEventOccurDate" name="issue.occurDate" class="form-txt{required:true,messages:{required:'必须输入'}}"value="<s:date	name="eventSource.sourceDate" format="yyyy-MM-dd" />"  /><em class="form-req">*</em>
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lb1">发生地点：</label>
				</div>
		    	<div class="grid_7">
		   	 	    <input type="text" id="issueLocation" name="issue.occurLocation" maxlength="50" value="${eventSource.orgName}" class="form-txt{required:true,messages:{required:'必须输入'}}" />
		   	 	    <em class="form-req">*</em>
		   	 	</div>
		   	 	<div class="grid_4 lable-right">
		   	 	    <em class="form-req">*</em>
					<label class="form-lbl">事件规模：</label>
				</div>
				<div class="grid_7 form-left">
					<pop:PropertyDictSelectTag id="issueKind" name="issue.issueKind.id" defaultValue="${issue.issueKind.id}"
						domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
					</div>
				<div class="grid_4 lable-right">
						<label class="form-lbl">事件类型：</label>
				</div>
					<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
						<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
							<div class="grid_${issueTypeName.titleWidth} multipeSelect">
								<input id="issueTypeSelector${st.index}" name="" type="checkbox" class="category" <s:if test="selContradiction.size()>0">checked</s:if> />
								<label class="form-check-text" for="issueTypeSelector${st.index}">${issueTypeName.typeName}</label>
								<ul  class="zc-sf" >
									<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
									<s:iterator value="issueTypes[#issueTypeName.typeName]" var="type" >
										<li><input name="selectedTypes" type="checkbox" value='<s:property value="id"/>' <s:if test="selContradiction.contains(#type)">checked</s:if> />
								 		<label><s:property value="issueTypeName"/></label></li>
									</s:iterator>
									
								</ul>
							</div>
						</s:if>
					</s:iterator>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_4 lable-right"></div>
						<div style="width:78%;overflow-y:auto;height:68px;border:1px solid #7F9DB9;background:#ffffff;padding:6px;overflow:auto" 
							id="issueTypeDescription" >
					</div>
					<div class='clearLine'>&nbsp;</div>
		      <div class="grid_4 lable-right">
				<label class="form-lb1">事件简述：</label>
			 </div>
			 <div class="heightAuto" style="display:inline;float:left;width:81%;">
		    	 <textarea rows="3" cols="" id="issueContent" name="issue.issueContent"
		    	  class="form-txt" style="height:86px;">${eventSource.content}</textarea>
			</div>
		</form>
</div>
</div>

<script type="text/javascript">
$('#formEventOccurDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	maxDate : '+0d'
});
$(document).ready(function(){

	function getDefaultOccurOrg(){
		<s:if test="null!=issue.occurOrg && null!=issue.occurOrg.id">
			return "${issue.occurOrg.id}";
		</s:if>
		<s:else>
			return -1;
		</s:else>
	}

		initOccurOrgSelector();
		initIssueTypeSelector();
		bindFormValidation();
		
		renderSelectedIssueTypes();


	function renderSelectedIssueTypes(){
			var typeDesc="";
			$(":input[id^=issueTypeSelector]").each(function(index,value){
				typeDesc=typeDesc+$("[for="+value.id+"]").first().html()+":"+$.trim($(value).getTypeSelectLabels())+"<br>";
			});
			$("#issueTypeDescription").html(typeDesc);
	}


	function  getOccurOrgId(){
			return $("#occurOrgId").val();
	} 


	function initOccurOrgSelector(){
			var tree=$("#issueOccurOrgSelector").treeSelect({
				inputName:"issue.occurOrg.id"
			});
			$.setTreeValue(getDefaultOccurOrg(),tree); 
	}
		
	function initIssueTypeSelector(){
			<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
				<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
					$("#issueTypeSelector${st.index}").typeSelect({width:${issueTypeName.width},columns:${issueTypeName.column},close:function(ids,labels){renderSelectedIssueTypes();}});
				</s:if>
			</s:iterator>
	}

		
		
		
	function bindFormValidation(){
			$("#eventSourceAddIssueForm").formValidate({
				promptPosition: "bottomLeft",
				submitHandler: function(form){
					$(form).ajaxSubmit({
						success:function(data){
							if(!data.issueId){
		               	 		$.messageBox({
									message:data,
									level:"error"							
						 		});
						 		return;
							}
							$.messageBox({message:"已经成功将该事件信息保存到系统中!"});
							$("#informationDialog").dialog("close");
							if($("#isFlow").val()=="true"){
								$.createDialog({
									id:'issueDialog',
									width:700,
									height:480,
									title:'分流',
									url:'${path}/issues/issueManage/dispatchDeal.action?mode=deal&keyId='+data.issueStepId,
									buttons: {
										"确定" : function(event){
											$("#issueDealForm").submit();
										},
										"关闭" : function(){
									        $(this).dialog("close");
									   }
									},
									close:function(){
										var dealType = $("#dealCode").val();
										if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@COMPLETE.code'/>){
											$.ajax({
												url:"${path}/commandCenterManage/updateInformation.action?eventSource.id=${eventSource.id}&internalId=1&serialNumber="+data.serialNumber,
												success:function(data){
											       $("#refresh").click();}
												});
										  }else{
											  $.ajax({
													url:"${path}/commandCenterManage/updateInformation.action?eventSource.id=${eventSource.id}&internalId=2&serialNumber="+data.serialNumber,
													success:function(data){
												       $("#refresh").click();}
													});
												}

											  }
										}
								});
							}else{
								$.ajax({
									url:"${path}/commandCenterManage/updateInformation.action?eventSource.id=${eventSource.id}&internalId=2&serialNumber="+data.serialNumber,
									success:function(data){
								       $("#refresh").click();}
									});
								}
							
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
		  	      			alert("提交数据时发生错误");
			   		  	}
					});
				},
				rules:{
					"issue.subject":{
						required:true,
						titleStr:true,
						rangelength:[2,50]
					},
					"issue.occurLocation":{
						required:true,
						addressStr:true,
						maxlength:50
					},
					"issue.occurDate":{
						required:true
					},
					"issue.issueKind.id":{
						required:true
					},
					"issue.issueContent":{
						required:true
					},
					"selectOrgName":{
						required:true,
						orgLevelLessEqual:function(){
								return [getOccurOrgId(),<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>];
							}
					}
				},
				messages:{
					"issue.subject":{
						required:"请为该事件填写一个不小于2-50个字的主题",
						titleStr:"事件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符",
						rangelength:$.format("事件主题不能小于{0}个或大于{1}个字符")
					},
					"issue.occurLocation":{
						required:"请为该事件填写一个不超过50个字符的事发地点",
						maxlength:$.format("事发地点不能大于{0}个字符"),
						addressStr:"事发地点只能输入中英文、数字、括号、空格、减号、#号等字符"
					},
					"issue.occurDate":{
						required:"请输入该事件的发生日期"
					},
					"issue.issueKind.id":{
						required:"请选择事件规模"
					},
					"issue.issueContent":{
						required:"请输入事件简述"
					},
					"selectOrgName":{
						required:"请选择发生网格",
						orgLevelLessEqual:"发生网格不能选择乡镇（街道）以上级别"
					}
				}
			});
		}
		
});



</script>