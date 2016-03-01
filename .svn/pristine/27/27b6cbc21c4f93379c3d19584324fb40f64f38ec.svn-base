<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="" class="container container_24">
<form id="issueMaintainForm" method="post" action="">
        <input id="esInternalId" name="eventSourceVo.sourceType.internalId" type="hidden" value="${eventSourceVo.sourceType.internalId }">
        <input id="internalId" name="internalId" type="hidden" value="${internalId}" />
        <input id="eventSourceId" name="eventSource.id" type="hidden" value="${eventSource.id}" />
		<input id="issueId" name="issue.id" type="hidden" value="${issue.id}" />
		<input id="ycHours" type="hidden" value="${issue.hours}" />
		<input id="ycMinute" type="hidden" value="${issue.minute}" />
		<input id="stepId" name="stepId" type="hidden" value="${issue.currentStep.id}" />
		<input id="occurOrgId" name="issue.occurOrg.id" type="hidden" value="${issue.occurOrg.id}" />
		<input id="serialNumber" name="issue.serialNumber" type="hidden" value="${issue.serialNumber}" />
		<input id="sourceKind" name="issue.sourceKind.id" type="hidden" value="${issue.sourceKind.id}" />
		<input id="sourcePerson" name="issue.sourcePerson" type="hidden" value="${issue.sourcePerson}" />
		<input id="sourceMobile" name="issue.sourceMobile" type="hidden" value="${issue.sourceMobile}" />
		<div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_13 form-left">
		    	<input type="text" id="issueSubject" name="issue.subject" maxlength="50" value="${issue.subject}" class="form-txt" />
			</div>
			<div class="grid_3 lable-right">
				<em class="form-req">*</em><label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_4 form-left">
				<input type="text" id="issueOccurOrgSelector" name="selectOrgName" value="${issue.occurOrg.orgName}" class="form-txt" />
    		</div>
    		<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">发生地点：</label>
			</div>
			<div class="grid_13 form-left">
		    	<input type="text" id="issueLocation" name="issue.occurLocation" maxlength="50" value="${issue.occurLocation}" class="form-txt" />
			</div>
    		<div class="grid_3 lable-right">
                <em class="form-req">*</em><label class="form-lbl">发生时间：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="occurDate" name="issue.occurDate" class="form-txt"
				value="<s:date name="issue.occurDate" format="yyyy-MM-dd"/>" readonly />
			</div>
			<div class='clearLine'></div>		
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">主要当事人：</label>
	  		</div>
    		<div class="grid_13 form-left">
				<input type="text" id="issueMainCharacters" name="issue.mainCharacters" maxlength="30" value="${issue.mainCharacters}" class="form-txt" />
    		</div>
    		<div class="grid_2 lable-right">
		  			<label class="form-lbl">时：</label>
		  		</div>
	    		<div class="grid_2 form-left">
					<select id="hours" name="hours" class="form-txt">
						<option value="">请选择</option>
					</select>
	    		</div>
	    		<div class="grid_1 lable-right">
		  			<label class="form-lbl">分：</label>
		  		</div>
	    		<div class="grid_2 form-left">
	    			<select id="minute" name="minute" class="form-txt" disabled>
	    				<option value="">请选择</option>
	    			</select>
	    		</div>
			<div class='clearLine'>&nbsp;</div>	
			<div class="grid_4 lable-right">
				<label class="form-lbl">事件规模：</label>
			</div>
			<div class="grid_4 form-left">
				<pop:PropertyDictSelectTag id="issueKind" name="issue.issueKind.id" defaultValue="${issue.issueKind.id}" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">涉及人数：</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="issueRelatePeopleCount" name="issue.relatePeopleCount" maxlength="6" value="${issue.relatePeopleCount}" class="form-txt" style="text-align:right;" />
			</div>
    		<div class="grid_1">&nbsp;(人)</div>
    		<div class="grid_3"></div>
			<div class="grid_4">
				<input id="issueImportant" name="issue.important" type="checkbox" value="true"
				<s:if test="issue.important">checked="checked"</s:if> />
				<label class="form-check-text" for="issueImportant">是否重大事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件类型：</label>
			</div>
			<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
				<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
					<div class="grid_${issueTypeName.titleWidth} multipeSelect">
					   <s:if test="!#issueTypeName.typeName.equals('一站审批')">
							<input id="issueTypeSelector${st.index}" name="" type="checkbox" class="category" <s:if test="issueHasTypeDomainName.contains(#issueTypeName.typeName)">checked</s:if> />
							<label class="form-check-text" for="issueTypeSelector${st.index}">${issueTypeName.typeName}</label>
						</s:if>
						<ul  class="zc-sf" >
							<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
							<s:iterator value="issueTypes[#issueTypeName.typeName]" var="type" >
								<li>
									<input name="selectedTypes" type="checkbox" value='<s:property value="id"/>' <s:if test="issue.issueTypes.contains(#type)">checked</s:if> />
						 			<label><s:property value="issueTypeName"/></label>
						 		</li>
							</s:iterator>
							<div class="clear"></div>
						</ul>
					</div>
				</s:if>
			</s:iterator>
			<div class="grid_4">
				<input id="issueIsEmergency" name="issue.isEmergency" type="checkbox" value="true"
				<s:if test="issue.isEmergency">checked="checked"</s:if> />
				<label class="form-check-text" for="issueIsEmergency">是否紧急事件 </label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right"></div>
			<div style="overflow-y:auto;width:50.5%;height:65px;border:1px solid #7F9DB9;background:#ffffff;padding:6px;" id="issueTypeDescription" name="typeNames"></div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">事件简述：</label>
			</div>
			<div class="grid_13 heightAuto" >
    			<textarea rows="3"  id="issueContent" name="issue.issueContent" class="form-txt">${issue.issueContent}</textarea>
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		<div id="subMaintanceDialog"></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
</form>
	<div style="position:absolute;top:160px;left:560px;_top:175px;">
		<div id="custom-queue" style="width: 180px; height: 150px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
</div>

<script type="text/javascript">

function getDefaultOccurOrg(){
	<s:if test="null!=issue.occurOrg && null!=issue.occurOrg.id">
		return "${issue.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}


//用来判断附件是否全部上传完成
var attachFileUploadComplete = false ;

$(document).ready(function(){
	// 初始化日期
	initOccurDateSelector();  
	// 初始化附件上传工具
	initAttachFileUploader();
	// 初始发生化网格 
	initOccurOrgSelector(); 
	// 初始化事件种类
	initIssueTypeSelector(); 
	// 修改的时候填充附件
	if(issueEditing()){
		fillIssueAttenchFiles(); 			
	}
	//表单提交的action  
	bindFormAction();
	//  表单校验					
	bindFormValidation();				
	//事件种类填充
	renderSelectedIssueTypes();

	for(i=0;i<24;i++){
		var time = i<10?"0"+i:i; 
		$('#hours').append($("<option value='"+time+"'>"+time+"</option>"));
	};
	
	for(i=0;i<60;i++){ 
		var time = i<10?"0"+i:i; 
		$('#minute').append($("<option value='"+time+"'>"+time+"</option>"));
	};
	
	if("${mode}"=="edit"){
		$('#minute').attr("disabled",false);
		$('#hours').val($('#ycHours').val());
		$('#minute').val($('#ycMinute').val());
	}
	
	$('#hours').change(function(){
		if($('#hours').val()!=""){
			$('#minute').val("00");
			$('#minute').attr("disabled",false);
		}else{
			$('#minute').val("");
			$('#minute').attr("disabled",true);
		}
	});

	function issueAdding(){
		return <s:property value='"event".equals(mode)'/>;
	}

	function issueEditing(){
		return <s:property value='"edit".equals(mode)'/>;
	}


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

	function initOccurDateSelector(){
		if($("#ui-datepicker-div")[0]){
			$("body").append("<div id='ui-datepicker-div' />")
		}
		$('#occurDate').datePicker({
			yearRange:'1930:2060',
			timeFormat: 'HH:mm:ss',
			maxDate:'+0y'
		})
	}

	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames",
			onAllComplete:function(){attachFileUploadComplete = true ;}
		});
		$("#attachFileNames").empty();
	}

	function initOccurOrgSelector(){
		var tree=$("#issueOccurOrgSelector").treeSelect({
			inputName:"issue.occurOrg.id",
			loadCom:function(){
			if(issueEditing()){
				$.setTreeValue(getDefaultOccurOrg(),tree); 
			}
		}
		});
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

	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}
		
	function initIssueTypeSelector(){
		<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
			<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
				$("#issueTypeSelector${st.index}").typeSelect({width:${issueTypeName.width},columns:${issueTypeName.column},close:function(ids,labels){renderSelectedIssueTypes();}});
			</s:if>
		</s:iterator>
	}

	function bindFormAction(){
		if (issueAdding()){
			$("#issueMaintainForm").attr("action","${path}/commandCenterManage/addIssue.action");
		}else if (issueEditing()){
			$("#issueMaintainForm").attr("action","${path}/commandCenterManage/updateIssue.action");
		}
	}
	function bindFormValidation(){
		
		$("#issueMaintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
			var issueType = $(".category[checked='checked']");
			if(issueType.length < 1){
				$.messageBox({level:'warn',message:"请至少为该事件选择一种事件类型!"});
				return  ;
			}
			if(!attachFileUploadComplete && $("#attachFileNames").val()!=null && $("#attachFileNames").val()!=''){
				$.messageBox({level:'warn',message:"附件还未上传完成，请稍候!"});
				return  ;
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(data!=null){
						if( data[0] == "tranevent" ){
							$.messageBox({message:"转入事件成功!"});
							$("#informationDialog").dialog("close");
							getMessageByUser();
	                	} else {
	                		$("#informationDialog").dialog("close");
	                		$("#informationDialog").createDialog({
	                			width:680,
	                			height:460,
	                			title:'分流事件',
	                			url:'${path}/commandCenterManage/dispatchDeal.action?mode=deal&keyId='+data[1],
	                			buttons: {
	                				"确定" : function(event){
	                					$("#issueDealForm").submit();
	                				},
	                				"关闭" : function(){
	                					$(this).dialog("close");
	                				}
	                			}
	                		});
		                }
					}
					
					onOrgChanged();
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
				"issue.mainCharacters":{
					maxlength:30,
					multiNames:true
				},
				"issue.relatePeopleCount":{
					digits:true,
					range:[0,999999]
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
	            "issue.mainCharacters":{
					maxlength:$.format("主要当事人不能大于{0}个字符"),
					multiNames:"主要当事人中只能输入中英文、数字、逗号、顿号等字符"
	            },
				"issue.relatePeopleCount":{
					digits:"涉及人数只能输入1到999999之间的整数",
					range:$.format("涉及人数只能输入{0}到{1}之间的整数")
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