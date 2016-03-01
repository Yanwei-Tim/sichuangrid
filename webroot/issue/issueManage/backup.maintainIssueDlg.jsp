<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
<form id="issueMaintainForm" method="post" action="">
		<input id="issueMode" name="issueMode" type="hidden" value="${issueMode}" />
		<input id="issueNew.id" name="issueNew.id" type="hidden" value="${issueNew.id}" />
		<input id="issueLogId" name="issueLogId" type="hidden" value="${issueLogId}" />
		<input id="occurOrgId" name="issue.occurOrg.id" type="hidden" value="${issue.occurOrg.id}" />
		<input id="issueOrganizationId" name="organization.id" type="hidden" value="${organization.id}" />
		<input id="serialNumber" name="issueNew.serialNumber" type="hidden" value="${issueNew.serialNumber}" />
		
		<div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="issueNew.subject" name="issueNew.subject" maxlength="50" value="${issueNew.subject}" class="form-txt" />
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
			
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_4 form-left">
				<input type="text" id="issueNewOrgName" name="issueNewOrgName" value="${issueNew.occurOrg.orgName}" class="form-txt" />
    		</div>
    		<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
    		<div class='clearLine'></div>
    		
			<div class="grid_3 lable-right">
				<label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="issueNew.subject" name="issueNew.subject" maxlength="50" value="${issueNew.subject}" class="form-txt" />
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
    		<div class="grid_3 lable-right">
				<label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="occurrence-time" name="issueNew.occurDate" class="form-txt"
				value="<s:date name="issueNew.occurDate" format="yyyy-MM-dd"/>" readonly />
			</div>
            <div class="grid_1">
                <em class="form-req">*</em>
            </div>
			<div class='clearLine'></div>		
			
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">主要当事人：</label>
	  		</div>
    		<div class="grid_12 form-left">
				<input type="text" id="issueNew.mainCharacters" name="issueNew.mainCharacters" maxlength="30" value="${issueNew.mainCharacters}" class="form-txt" />
    		</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>
    		<div class="grid_3"></div>
			<div class="grid_5">
				<input id="issueNewImportant" name="issueNew.important" type="checkbox" value="true"
				<s:if test="issueNew.important">checked="checked"</s:if> />
				<label class="form-check-text" for="issueNewImportant">是否重大事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>	
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_4 form-left">
				<pop:PropertyDictSelectTag id="issueKind" name="issueNew.issueKind.id" defaultValue="${issueNew.issueKind.id}"
				domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="issueNewRelatePeopleCount" name="issueNew.relatePeopleCount" maxlength="6" value="${issueNew.relatePeopleCount}"
					class="form-txt" style="text-align:right;" />
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
    		<div class="grid_3"></div>
			<div class="grid_5">
				<input id="issueNewIsEmergency" name="issueNew.isEmergency" type="checkbox" value="true"
				<s:if test="issueNew.isEmergency">checked="checked"</s:if> />
				<label class="form-check-text" for="issueNewIsEmergency">是否紧急事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			
			<div class="grid_3 lable-right">
  				<label class="form-lbl">涉及单位：</label>
	  		</div>
    		<div class="grid_18 form-left" style="line-height:20px;">
    			<input type="text" id="involvedPlace" name="involvedPlace" maxlength="50" class="form-txt"/>
    		</div>
    		<div class="grid_3 form-left">
   			    <input type='button' class="defaultButton" value='查询' id='searchPlace'/>
				<input type='button' class="defaultButton" value='新增' id='addPlace' />
    		</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">涉及人员：</label>
	  		</div>
    		<div class="grid_18 form-left heightAuto" style="line-height:20px;">
				<input type="text" id="involvedPersonnel" name="involvedPersonnel" class="form-txt" />
    		</div>
										
			<div class="grid_3">
				<input type='button' class="defaultButton" value='查询' id='searchPersonnel'/>
				<input type='button' class="defaultButton" value='新增' id='addPersonnel' />
				<div class='clearLine'>&nbsp;</div>
				<ul class="heightAuto" id="personnelId">
					<li id='druggyId'>吸毒人员</li>
					<li id='superiorVisitId'>重点上访人员</li>
					<li id='mentalPatientId' class="line">严重精神障碍患者</li>
					<li id='positiveInfosId'>刑释人员</li>
					<li id='rectificativePerson'>社区矫正人员</li>
					<li id='idleYouthsId'>重点青少年</li>
					<li id='dangerousGoodsPractitioner'>危险品从业人员</li>
					<li id='attentionObjectId'>其他人员</li>
					<li id='poorpeopleId'>需救助人员</li>
				<!-- 	<li id='dangerTrampResident'>高危流动人口</li>  -->
				</ul>
			</div>
			<div class='clearLine'>&nbsp;</div>	
			
			
			
			
										
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件类型：</label>
			</div>
			
			<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
				<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
					<div class="grid_${issueTypeName.titleWidth} multipeSelect">
						<input id="issueTypeSelector${st.index}" name="" type="checkbox" class="category" <s:if test="selContradiction.size()>0">checked</s:if> />
						<label class="form-check-text" for="dissension${st.index}">${issueTypeName.typeName}</label>
						<ul  class="zc-sf" >
							<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
							<s:iterator value="issueTypes[#issueTypeName.typeName]" var="type" >
								<li><input name="selContradictionId" type="checkbox" value='<s:property value="id"/>' <s:if test="selContradiction.contains(#type)">checked</s:if> />
						 		<label><s:property value="issueTypeName"/></label></li>
							</s:iterator>
							<div class="clear"></div>
						</ul>
					</div>
				</s:if>
			</s:iterator>
		
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right"></div>
				<div style="overflow-y:auto;width:54%;height:65px;border:1px solid #7F9DB9;background:#ffffff;padding:6px;overflow:auto" 
					id="issueTypeDescription">
				</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件简述：</label>
			</div>
			<div class="heightAuto" style="display:inline;float:left;width:57%;">
    			<textarea rows="3" cols="" id="issueContent" name="issueNew.issueContent"
    				class="form-txt" style="height:86px;">${issueNew.issueContent}</textarea>
			</div>
			<div class="grid_1">
				<em class="form-req">*</em>
			</div>  
			<div class='clearLine'>&nbsp;</div>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
</form>
	<div style="position:absolute;top:242px;left:560px;_top:260px;">
		<div id="custom-queue" style="width: 180px; height: 150px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	if (issueAdding() || issueEditing()){
		var tree=$("#issueNewOrgName").treeSelect({
			inputName:"issue.occurOrg.id",
			onSelect:divNone
		});
		$.setTreeValue(getDefaultOccurOrg(),tree); 
	}else{
//		disableRelatePlaceInputer();
//		disableRelatePersonInputer();
	}

	$("#searchPlace").click(function(event){
		var bol = false;
		alert($("#occurOrgId").val());
		$.ajax({
			async:false,
			url:"${path}/tools/org/levelCompare.action",
			data:{
				"orgId":$("#occurOrgId").val(),
				"orgLevel":<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>
			},
			success:function(responseData){
				bol = responseData<=0;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			return ;
		}
		if(isNull()){
			$("#searchandaddDialog").createDialog({
				width:620,
				height:450,
				title:'涉及重点场所查询',
				url:"${path}/issue/issueManage/search/searchPlaceDlg.jsp?orgId="+getOccurOrgId(),
				buttons: {
					"确定" : function(event){
					   if(setCompVal()){
					   	$(this).dialog("close");
					   }
				   },
				   "取消" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	});

	$("#addPersonnel").toggle(function(){
		var bol = false;
		$("#personnelId li").hover(
			function(){
				$(this).addClass("hover");
			},
			function(){
				$(this).removeClass("hover");
			}
		)
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			$("#addPersonnel").click();
			$("#addPersonnel").click();
			return ;
		}
		$("#personnelId").css("display" ,"block");
		$("#addPersonnel").attr("showMenu","true");
	},
		function(){
			var bol = false;
			$.ajax({
				async:false,
				url:"${path}/issue/issueManage/checkOccurOrgId.action",
				data:{
					"issueNew.occurOrg.id":$("#occurOrgId").val()
				},
				success:function(responseData){
					bol = responseData;
				}
			});
			$("#personnelId").css("display" ,"none");
			$("#addPersonnel").attr("showMenu","false");
		}
	);
	$("#issueMaintainForm").click(function(){
		if($("#addPersonnel").attr("showMenu")=="true"){
			$("#addPersonnel").click();
		}
	});
	
	$("#druggyId").click(function(){
		createAddDialog(800,490,"新增吸毒人员信息","${path}/baseinfo/druggyManage/dispatchOperate.action?mode=add&ownerOrg.id="+ getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#superiorVisitId").click(function(){
		createAddDialog(800,485,'新增重点上访人员信息','${path}/baseinfo/superiorVisit/dispatch.action?orgId='+getOccurOrgId()+'&mode=add&dialog=addPlaceAndPersonnelDialog');
	});

	$("#mentalPatientId").click(function(){
		createAddDialog(800,500,'新增严重精神障碍患者信息',"${path}/baseinfo/mentalPatient/dispatchOperate.action?mode=add&orgId="+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#positiveInfosId").click(function(){
		createAddDialog(800,580,'新增刑释人员信息','${path}/baseinfo/positiveInfo/getPositiveInfoById.action?orgId='+getOccurOrgId()+'&mode=add&dialog=addPlaceAndPersonnelDialog');
	});

	$("#rectificativePerson").click(function(){
		createAddDialog(800,550,'新增社区矫正人员信息','${path}/baseinfo/rectificativePersonManage/dispatchOperate.action?mode=add&orgId='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#idleYouthsId").click(function(){
		createAddDialog(800,550,'新增重点青少年信息','${path}/baseinfo/idleYouthManage/dispatch.action?mode=add&organizationId='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#dangerousGoodsPractitioner").click(function(){
		createAddDialog(780,480,'新增危险品从业人员信息','${path}/baseinfo/dangerousGoodsPractitionerManage/dispatch.action?mode=add&organization.Id='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#attentionObjectId").click(function(){
		createAddDialog(810,450,'新增其他关注人员信息','${path}/baseinfo/attentionPersonnelManage/dispatchOperate.action?mode=add&orgId='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#poorpeopleId").click(function(){
		createAddDialog(600,300,'新增需救助人员信息','${path}/baseinfo/poorPeopleManage/dispatchOperate.action?mode=add&ownerOrg.id='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});

	$("#dangerTrampResident").click(function(){
		createAddDialog(900,600,'新增高危流动人口信息','${path}/baseinfo/dangerTrampResidentManage/dispatch.action?mode=add&organizationId='+getOccurOrgId()+"&dialog=addPlaceAndPersonnelDialog");
	});
	
	
	$("#involvedPersonnel").personnelComplete({
		url: "${path}/issues/searchIssue/searchPersonnelForAutoComplete.action",
		multiple: true,
		postDataFunction: function(){
		    var orgId=getOccurOrgId();
		    return {orgId:orgId};
		},
		height:50
	});
	$("#involvedPlace").personnelComplete({
		url: "${path}/issues/searchIssue/searchPlaceForAutoComplete.action",
		multiple: false,
		postDataFunction: function(){
		    var orgId=getOccurOrgId();
		    return {orgId:orgId};
		}
	});

	$("#searchPersonnel").click(function(event){
		var bol = false;
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			return ;
		}
		if(isNull()){
			$("#searchandaddDialog").createDialog({
				width:610,
				height:430,
				title:'涉及特殊人群查询',
				url:"${path}/issue/issueManage/search/searchPersonnelDlg.jsp?orgId="+getOccurOrgId(),
				buttons: {
					"确定" : function(event){
					   setCompVal();
					   $(this).dialog("close");
				   },
				   "取消" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	});

	jQuery.validator.addMethod("checkOccurOrgId", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		return bol;
	});
	
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	$("#attachFileNames").empty();
	fillFile();
	<s:if test='"add".equals(issueMode)'>
		$("#issueMaintainForm").attr("action","${path}/issue/issueManage/addIssue.action");
	</s:if>
	<s:if test='"edit".equals(issueMode)'>
		$("#issueMaintainForm").attr("action","${path}/issue/issueManage/updateIssue.action");
	</s:if>
	renderIssueTypes();
	<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
		<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
			$("#issueTypeSelector${st.index}").typeSelect({width:${issueTypeName.width},columns:${issueTypeName.column},close:function(ids,labels){renderIssueTypes();}});
		</s:if>
	</s:iterator>
	$("#penId").typeSelect({close:function(values){showValue(values);},width:300,position:"bottom-left"});
	$("#siteId").typeSelect({close:function(values){showPenValue(values);},width:300,position:"bottom-left"});
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})

	$("#issueMaintainForm").formValidate({
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
						data["occurOrg.orgName"] = data.occurOrg.orgName;
						<s:if test='"add".equals(issueMode)'>
							/*$("#promptDialog").createDialog({
								width: 500,
								height:	150,
								title: "提示",
								url: "${path}/issue/issueManage/acceptPromptDlg.jsp",
								close: function(event, ui){
									if(data.occurOrg.id == $("#organizationId").val()){
										$("#issueList").addRowData(data.id,data,"first");
			    						$.messageBox({message:"已经成功保存该事件处理对象信息!"});
			    						$("#issueList").setSelection(data.issueId);		
									}else{
										$.messageBox({message:"已经成功保存到" + data.occurOrg.orgName});
									}
		    						$("#issueDialog").dialog("close");
								},
								buttons: {
							   		"受理" : function(){$(this).dialog("close");},
							   		"取消" : function(){$(this).dialog("close");}
								}
							});
							*/
							
							$.messageBox({message:"已经成功保存该事件处理信息!"});
    						$("#issueList").trigger("reloadGrid");
    						$("#issueList").setSelection(data.issueLogId);	
		                    refreshMyIssueCount();
		                    $(".message").click();
							$("#issueDialog").dialog("close");
		     			</s:if>
		     			<s:if test='"edit".equals(issueMode)'>
		     				if(data.occurOrg.id == $("#issueOrganizationId").val()){
		     					$.messageBox({message:"已经成功修改该事件处理信息!"});
		     					$("#issueList").trigger("reloadGrid");
		     					$("#issueList").setSelection(data.issueLogId);
			     			}else{
			     				$.messageBox({message:"已经成功修改下辖事件处理信息!"});
				     		}
							$("#issueDialog").dialog("close");
				    	</s:if>
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"issueNew.subject":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"issueNew.relatePeopleCount":{
				required:true,
				digits:true,
				min:0,
				max:999999
			},
			"issueNew.mainCharacters":{
				exculdeParticalChar:true
			},
			"issueNew.occurDate":{
				required:true
			},
			"issueNewOrgName":{
				checkOccurOrgId:true
			},
			"issueNew.issueKind.id":{
				required:true
			},
			"issueNew.issueContent":{
				required:true
			},
			"issueNew.mainCharacters":{
				required:true
			}
		},
		messages:{
			"issueNew.subject":{
				required:"请输入事件名称",
				exculdeParticalChar:"事件名称只能输入字母，数字和中文字符",
				minlength:$.format("事件名称至少需要输入{0}个字符")
			},
			"issueNew.relatePeopleCount":{
				required:"请输入涉及人数",
				digits:"涉及人数只能输入正整数",
				min:"涉及人数最小输入1",
				max:"涉及人数最大输入999999"
			},
			"issueNew.mainCharacters":{
				exculdeParticalChar:"主要当事人只能输入字母，数字和中文字符"
			},
			"issueNew.occurDate":{
				required:"请选择发生时间"
			},
			"issueNewOrgName":{
				checkOccurOrgId:"发生网格无法选择乡镇(街道)以上级别！",
				position:"right"
			},
			"issueNew.issueKind.id":{
				required:"请选择事件规模"
			},
			"issueNew.issueContent":{
				required:"请输入事件简述"
			},
            "issueNew.mainCharacters":{
                required:"请输入主要当事人"
            }
		}
	});

	$("#issueNewRelatePeopleCount").change(function(){
		if($("#issueNewRelatePeopleCount").val() >49){
			$("#issueNewImportant").attr("checked",true);
		}
	});

	$("#issueNewImportant").click(function(){
		if($("#issueNewRelatePeopleCount").val() >49) $("#issueNewImportant").attr("checked",true);
	});

	$("#lab2").hide();
	$("#div2").hide();

	<s:iterator id="person" value="relatePersonMap">
		var key = "<s:property value='#person.key'/>"; 
	    <s:iterator value="#person.value" status="s">  
	   		var value = key+"-<s:property value='worksheetid'/>"
	   		var label = "<s:property value='name'/>";
	    	$("#involvedPersonnel").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
	    </s:iterator> 
	</s:iterator>
	
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

	checkOccurOrgIsTown();
});

$('#occurrence-time').datePicker({
	yearRange:'1930:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});

var status = 1;
function showInvolvedPlaces(){
	if($("#isInvolvedPlace").attr("checked")){
		$("#lab1").hide();
		$("#div1").hide();

		$("#lab2").show();
		$("#div2").show();
		<s:if test='"add".equals(issueMode)'>
			if(isNull()){
				status++;
			}
		</s:if>
	}else{
		$("#lab2").hide();
		$("#div2").hide();

		$("#lab1").show();
		$("#div1").show();
		<s:if test='"add".equals(issueMode)'>
			if(isNull()){
				status++;
			}
		</s:if>
	}
	if(status%2 == 0){
		<s:if test='"add".equals(issueMode)'>
			$("#searchPlace").click();
		</s:if>
		//$("#isInvolvedPlace").attr("checked",true);
	}else{
		//$("#isInvolvedPlace").attr("checked",false);
	}
}

function fillFile(){
	<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
	    <s:iterator value="issueAttachFiles">
	    
	    $("#custom-queue").addUploadFileValue({
	     id:"<s:property value='id'/>",
	     filename:"<s:property value='fileName'/>",
	     link:${path}"/issue/issueManage/downLoadActualFile.action?fileId=<s:property value='id'/>&issueMode=<s:property value='issueMode'/>",   	     	 
	     onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
	     });
	    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
	    </s:iterator>
	</s:if>
}


function removeAttach(fileName){
	$("input[name='file']").removeAttr("disabled");
	$("#attachFileNames").find("option[value="+fileName+"]").remove();
}


function isNull(){
	if($("#issueNewOrgName").val()==null||$("#issueNewOrgName").val()==""){
		$.notice({
			title:"提示",
			message:"请先选择所属网格！",
			width: 300
		});
		return false;
		}
	return true;
}

function  getOccurOrgId(){
	return $("#occurOrgId").val();
	} 


function isExist(penid,pen){
	for(var i=0;i<pen.length-1;i++){
		if(pen[i]==penid){
			return false;
		}
	}
	return true;
}

function renderIssueTypes(){
	var issueSelectors=$("[id|=issueTypeSelector]");
	for (var index=0;index<issueSelectors.length;index++){
		alert(index);
	}
//	var text="矛盾纠纷："+$.trim($("#dissension").getTypeSelectLabels())+"<br>治安,安全隐患："+$.trim($("#employment").getTypeSelectLabels())+"<br>民生服务："+$.trim($("#peopleLive").getTypeSelectLabels());
//	<s:if test="otherType.size()>0">
//		text = text+"<br>其他："+$.trim($("#others").getTypeSelectLabels());
//	</s:if>
//	$("#issueTypeDescription").html(text);
}

function createAddDialog(width,height,title,url){
	if($("#personnelId").css("display")=="block"){
		$("#addPersonnel").click();
	}
	$("#addPlaceAndPersonnelDialog").createDialog({
		width:width,
		height:height,
		title:title,
		url:url,
		buttons: {
	   		"保存" : function(event){
	   			$("#maintainForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function divNone(){
	if($("#personnelId").css("display")=="block"){
		$("#addPersonnel").click();
	}
	checkOccurOrgIsTown();
}

function checkOccurOrgIsTown(){
	var bol = false;
	$.ajax({
		async:false,
		url:"${path}/issue/issueManage/checkOccurOrgId.action",
		data:{
			"issueNew.occurOrg.id":$("#occurOrgId").val()
		},
		success:function(responseData){
			bol = responseData;
		}
	});
	if(bol){
		$("#input_involvedPlace").removeAttr("disabled");
		$("#input_involvedPersonnel").removeAttr("disabled");
		$("#addPersonnel").removeAttr("disabled");
		
	}else{
		$("#input_involvedPlace").attr("disabled","disabled");
		$("#input_involvedPersonnel").attr("disabled","disabled");
		$("#addPersonnel").attr("disabled","disabled");
		
	}
}
</script>