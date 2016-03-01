<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false"
	namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById"
	executeResult="false" namespace="/sysadmin/orgManage">
	<s:param name="organization.id"
		value="#loginAction.user.organization.id"></s:param>
</s:action>
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="peopleAspirations.ledgerType" id="ledgerType" value="${peopleAspirations.ledgerType}" />
		<input type="hidden" id="organizationId" name="peopleAspirations.organization.id" value="${peopleAspirations.organization.id}"/>
		<input type="hidden" id="id" name="peopleAspirations.id" value="${peopleAspirations.id}"/>
		<input type="hidden" id="orgInternalCode" name="peopleAspirations.orgInternalCode" value="${peopleAspirations.orgInternalCode}"/>
		<input type="hidden" id="occurOrgId" name="peopleAspirations.occurOrg.id" value="${peopleAspirations.occurOrg.id}" />
		<input type="hidden" id="occurOrgInternalCode" name="peopleAspirations.occurOrgInternalCode" value="${peopleAspirations.occurOrgInternalCode}" />
		<input id="ycHours" type="hidden" value="${peopleAspirations.hours}" />
		<s:if test="#request.type=='convert'">
			<input type="hidden" id="oldIssueId" name="peopleAspirations.oldIssueId" value="${param.oldIssueId}"/>
			<input type="hidden" id="convertId" name="peopleAspirations.convertId" value="${param.keyId}"/>
		</s:if>
		<input id="ycMinute" type="hidden" value="${peopleAspirations.minute}" />
	 	<input type="hidden" id="currentDepartNo" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getDepartmentNo()"/>">
	 	
 		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="peopleAspirations.serialNumber" id="serialNumber"  maxlength="30" value="${peopleAspirations.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">建卡类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.createTableType.id" id="createTableType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE"  defaultValue="${peopleAspirations.createTableType.id}" />
			</select>
		</div>
 		
		
	 	
		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">发生网格：</label>
  		</div>
   		<div class="grid_7 form-left">
			<input type="text"   id="peopleOccurOrgSelector" name="selectOrgName"  value=""  class="form-txt" style="color:red"/>
   		</div>
 		<div class="grid_4 lable-right">
 			<em class="form-req">*</em>
			<label class="form-lbl">登记单位：</label>
	 	</div>
		<div <s:if test='!"view".equals(mode)'>class="grid_7"</s:if><s:else>class="grid_4"</s:else>>
			<input type="text"  id="peopleAspirationsOrgName"  name="peopleAspirations.bookingUnit"  readonly  value="${peopleAspirations.bookingUnit}"
				class='form-txt' />
		</div>
   		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'>
				<em class="form-req">*</em>
			</s:if>
			<label class="form-lbl">登记人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text"  name="peopleAspirations.registrant" id="registrant"  maxlength="20" value="${peopleAspirations.registrant}" 
				class='form-txt' />
		</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		
	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl" id="occurDateLabel">登记时间：</label>
		</div>
		<div class="grid_4 form-left">
	    	<input type="text" id="occurDate" name="peopleAspirations.occurDate" class="form-txt"
			value="<s:date name="peopleAspirations.occurDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class="grid_1"></div>
   		<div class="grid_2 form-left">
			<select id="hours" name="peopleAspirations.hours" class="form-txt">
				<option value="">时</option>
			</select>
   		</div>
   		<div class="grid_1"></div>
   		<div class="grid_2 form-left">
   			<select id="minute" name="peopleAspirations.minute" class="form-txt" disabled>
   				<option value="">分</option>
   			</select>
   		</div>
		
		<div class="grid_4 lable-right " style="display:none">
			<em class="form-req">*</em>
			<label class="form-lbl">登记时间：</label>
		 	</div>
			<div class="grid_7" style="display:none">
			<input type="text"  name="peopleAspirations.registrationTime" readonly id="registrationTime"  maxlength="20"   value='<s:date name="peopleAspirations.registrationTime" format="yyyy-MM-dd HH:mm:ss" />' 
					class='form-txt' />
		</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">反映群体：</label>
	 	</div>
		<div class="grid_7 ">
			<select name="peopleAspirations.appealHumanType.id" id="appealHumanType" onchange="setResponseGroupNo()" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_APPEAL_HUMAN_TYPE" defaultValue="${peopleAspirations.appealHumanType.id}" />
			</select>
		</div>
 			<div class="grid_4 lable-right">
 			<em class="form-req">*</em>
			<label class="form-lbl">反映人数：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.responseGroupNo" id="responseGroupNo"  maxlength="6"  value="${peopleAspirations.responseGroupNo}"
				class='form-txt'/>
		</div>
		
	 	<div id='anonymityDiv'>
	 	<div class="grid_4 lable-right">
  		</div>
	 	<div class="grid_7 lable-left">
				<input id="isAnonymity" name="peopleAspirations.anonymity" type="checkbox" value="${peopleAspirations.anonymity}" 
				<s:if test="peopleAspirations.anonymity">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="isAnonymity">是否匿名</label>
				<input id="_isAnonymity" name="peopleAspirations.anonymity" type="hidden" value="${peopleAspirations.anonymity}" disabled="disabled"/>
		</div>
		<div class="grid_4 lable-right singlePeople">
		 	<em class="form-req">*</em>
			<label class="form-lbl">反映人姓名：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.name" id="name"   maxlength="20"  value="${peopleAspirations.name}"
				class='form-txt'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right ">
	 		<em class="form-req emchar">*</em>
			<label class="form-lbl">身份证：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.idCardNo" id="idCardNo" onblur='removeInputError(this)' maxlength="18"  value="${peopleAspirations.idCardNo}"
					class="form-txt isAnonymity {idCards:true,messages:{required:'请输入身份证',idCards:'请输入一个合法的身份证号码'}}"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
			<label class="form-lbl">出生年月：</label>
	 	</div>
		<div class="grid_7" id="birthdayDiv">
			<input type="text" name="peopleAspirations.birthDay" id="birthDay"  maxlength="32" readonly  value='<s:date name="peopleAspirations.birthDay" format="yyyy-MM-dd" />' class="form-txt isAnonymity" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
			<label class="form-lbl">性别：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.gender.id" id="gender" class='form-txt isAnonymity' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${peopleAspirations.gender.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.mobileNumber" id="mobileNumber"  maxlength="13"  value="${peopleAspirations.mobileNumber}"  
				class='form-txt isAnonymity {phoneAndMobile:true,messages:{phoneAndMobile:$.format("请输入以固定电话：028-87653333或者手机：15102888888为格式的号码")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
			<label class="form-lbl">常住地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="peopleAspirations.permanentAddress" id="permanentAddress"  maxlength="50"  value="${peopleAspirations.permanentAddress}"
				class="form-txt isAnonymity {validatorNativePlaceAddress:true, messages:{validatorNativePlaceAddress:'常住地址不能输入特殊字符'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req emchar">*</em>
			<label class="form-lbl">是否党员：</label>
	 	</div>
		<div class="grid_7">
			<select id="isPartyMember" name="peopleAspirations.isPartyMember" class="form-txt isAnonymity" <s:if test='"view".equals(mode)'>disabled</s:if>>
	 				<option value="true" <s:if test="peopleAspirations.isPartyMember">selected</s:if>>是</option>
	 				<option value="false" <s:if test="!peopleAspirations.isPartyMember">selected</s:if>>否</option>
	 		</select>
		</div>
	 	<div class="grid_4 lable-right">
	 		<em class="form-req emchar">*</em>
			<label class="form-lbl">职业或身份：</label>
	 	</div>
		<div class="grid_7">
			<select name="peopleAspirations.position.id" id="position" class='form-txt isAnonymity' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@POSITION_OR_STATUS" defaultValue="${peopleAspirations.position.id}" />
			</select>
		</div>
		</div>
	
	
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
			<label class="form-lbl">主要愿望或诉求：</label>
	 	</div>
		<div class="grid_18 heightAuto">
			<textarea  id="appealContent" name="peopleAspirations.appealContent"  maxlength="200" class='form-txt' >${peopleAspirations.appealContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">登记单位联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverContractor" id="serverContractor"  maxlength="20" class="form-txt" value="${peopleAspirations.serverContractor}"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverTelephone" id="serverTelephone"  maxlength="13"  value="${peopleAspirations.serverTelephone}"
				class='form-txt {telephone:true,messages:{telephone:$.format("服务联系电话不合法，只能输数字和横杠(-)")}}' title="请输入由数字和-组成的联系电话,例如：0577-88888888" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">单位及职务：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="peopleAspirations.serverJob" id="serverJob"  maxlength="20" class="form-txt" value="${peopleAspirations.serverJob}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	<div id="subMaintanceDialog"></div>	
	<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
		
		
		
		
 	</form>
	<div style="margin-left:125px;">
		<div  id="custom-queue" style="width: 560px; height:70px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>

<script type="text/javascript">

var peopleTree;
$(document).ready(function(){
	
	$("#tabs-option").tabs();
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	
	fillIssueAttenchFiles();
	initOccurOrgSelector();
	
	$("#idCardNo").blur(function(){
    	idCardChanged();
    	
	});

	
	if($("#convertId").val()!=undefined){
		$("#name").val($('#convert_name').text());
		$("#mobileNumber").val($('#convert_mobile').text());
		$("#appealContent").val($('#convert_description').text());					
	}
	

	initOccurDateSelector();
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
	jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value) ; 
	}
	jQuery.validator.addMethod("idCards", function(value, element){
		 //return checkIdcard(value.toUpperCase(),element);
		 if(value==null||value==undefined||value==""){return true;}
		 return checkIdcard(value,element);
	});
	jQuery.validator.addMethod("orgSelected", function(value, element,params){
		if(params[0]==null||params[0]==undefined||params[0]==""||params[0]=="请选 择"){
			return false;
		}
		return true;
	});



	//电话号码
	jQuery.validator.addMethod("phoneAndMobile", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
		var length = value.length;
		if(length == 11 && mobile.test(value)){return true;}
		var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
		if (value.match(phone)==null) {return false;}
		return true
	});
	

	
	function idCardChanged(){
		var text=$('#idCardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
		setGenderTextFromIdCard($('#idCardNo').val());
		text=$('#idCardNo').val();
	}
	resetBirthdayField($("#birthDay").val());
	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
			        	if(data.indexOf('重复提交')>-1){
			        		$('#tabs-option').tabs('select',1); 
			        		$(".ui-dialog-buttonset  button").eq(0).show();
			        	}else{
							$.messageBox({message:data,level: "error"});
			        	}
						return;
					}
		        	if(data.organization){
						data["organization.orgName"]=data.organization.orgName;
					}
					<s:if test='"edit".equals(mode)'>
				    	$.messageBox({message:"台账基本信息修改成功!"});
				    	$("#reload").click();
					</s:if>
					<s:if test='"add".equals(mode)'>
						if($('#id').val()=="")
							$.messageBox({message:"台账基本信息新增成功!"});
						else
							$.messageBox({message:"台账基本信息修改成功!"});
						$("#issueList").trigger("reloadGrid");
						$('#basicId').val(data.id);
						$('#id').val(data.id);
						$('#itemTab').show();
						$("#maintainForm").attr("action","${path}/threeRecords/peopleAspirationManage/updatePeopleAspiration.action");
						
					</s:if>
					$(".ui-dialog-buttonset  button").eq(0).show();
					$(".ui-dialog-buttonset  .ui-button-text").eq(1).text('保存');
					$('#tabs-option').tabs('select',1); 
											
					fillAttenchFiles(data.ledgerAttachFileReturnUtil);
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"peopleAspirations.name":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.createTableType.id":{
				required:true
			},
			"peopleAspirations.responseGroupNo":{
				required:true,
				digits:true,
				range:[1,999999]
			},
			"peopleAspirations.idCardNo":{
				required:true,
				idCards:true
			},
			"peopleAspirations.gender.id":{
				required:true
			},
			"peopleAspirations.isPartyMember":{
				required:true
			},
			"peopleAspirations.appealHumanType.id":{
				required:true
			},
			"peopleAspirations.position.id":{
				required:true
			},
			"peopleAspirations.permanentAddress":{
				required:true
			},
			"peopleAspirations.serverTelephone":{
				required:true,
				phoneAndMobile:true
			},
			"peopleAspirations.mobileNumber":{
				required:true,
				phoneAndMobile:true
			},
			"peopleAspirations.birthDay":{
				required:true
			},
			"peopleAspirations.serverContractor":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.serverJob":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.serverUnit":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"peopleAspirations.appealContent":{
				required:true,
				minlength:2,
				maxlength:200
			},	
			"peopleAspirations.serverJob":{
				required:true
			},
			"selectOrgName":{
				required:true,
				orgSelected:function(){
				   return [!($("#peopleOccurOrgSelector").val().indexOf('请选择')>-1),getOccurOrgId()];
				}
			},
			"peopleAspirations.registrant":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			}
			
		},
		messages:{
			"peopleAspirations.serialNumber":{
				required:"请输入编号",
				exsistedSerialNumber:function(){
					return serialNumberData;
				}
			},
			"peopleAspirations.name":{
				required:"请输入姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("姓名至少需要输入{0}个字符"),
				maxlength:$.format("姓名最多需要输入{0}个字符")
			},
			"peopleAspirations.createTableType.id":{
				required:"请选择建卡类型"
			},
			"peopleAspirations.gender.id":{
				required:"请选择性别"
			},
			"peopleAspirations.position.id":{
				required:"请选择职业或身份"
			},
			"peopleAspirations.birthDay":{
				required:"请选择出生年月"
			},
			"peopleAspirations.responseGroupNo":{
				required:"请填写反映人数"
			},
			"peopleAspirations.serverTelephone":{
				required:"请填写联系电话",
				phoneAndMobile:"请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"	
			},
			"peopleAspirations.mobileNumber":{
				required:"请填写联系电话",
				phoneAndMobile:"请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"	
			},
			"peopleAspirations.permanentAddress":{
				required:"请填写常住地址"
			},
			
			"peopleAspirations.responseGroupNo":{
				digits:"反映人数只能输入1到999999之间的整数",
				range:$.format("反映人数只能输入{0}到{1}之间的整数")
			},
			"peopleAspirations.idCardNo":{
				required:"请填写身份证号码",
				idCards:'请输入一个合法的身份证号码'
			},
			"peopleAspirations.itemCategory.id":{
				required:"请选择项目类别"
			},
			"peopleAspirations.serverContractor":{
				required:"请填写登记单位联系人",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人最多需要输入{0}个字符")
			},
			"peopleAspirations.serverJob":{
				required:"请填写单位及职务",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务职务至少需要输入{0}个字符"),
				maxlength:$.format("服务职务最多需要输入{0}个字符")
			},
			"peopleAspirations.serverUnit":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("服务联系人单位至少需要输入{0}个字符"),
				maxlength:$.format("服务联系人单位最多需要输入{0}个字符")
			},
			"peopleAspirations.appealContent":{
				required:"请输入主要愿望或诉求",
				minlength:$.format("主要愿望或诉求至少需要输入{0}个字符"),
				maxlength:$.format("主要愿望或诉求最多需要输入{0}个字符")
			},
			"selectOrgName":{
				required:"请选择发生网格",
				orgSelected:"请选择发生网格"
			},
			"peopleAspirations.isPartyMember":{
				required:"请选择是否党员",
			},
			"peopleAspirations.appealHumanType.id":{
				required:"请选择反映群体",
			},
			"peopleAspirations.registrant":{
				required:"请输入登记人姓名",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("登记人姓名至少需要输入{0}个字符"),
				maxlength:$.format("登记人姓名最多需要输入{0}个字符")
			}
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm").attr("action","${path}/threeRecords/peopleAspirationManage/addPeopleAspiration.action");
</s:if>
<s:if test='"edit".equals(mode)'>
    $("#maintainForm").attr("action","${path}/threeRecords/peopleAspirationManage/updatePeopleAspiration.action");
</s:if>


$("#isAnonymity").change(function(event){
	if($("#isAnonymity").attr("checked")=="checked"){
		$("#isAnonymity").val(true);
		$("#name").val('匿名');
		$(".isAnonymity").each(function(){
			$(this).rules('remove');  
			$(this).removeClass("errorInput");
		})
		$(".emchar").each(function(){
			$(this).hide();  
		})
		

	}else{
		$("#isAnonymity").val(false);
		$('#anonymityDiv').show();
		$(".isAnonymity").each(function(){
			$(this).rules('add',{required: true,  messages:{  required: '内容不能为空！', } }); 
		}) 
		$(".emchar").each(function(){
			$(this).show();  
		})
	}
	
});
if($("#isAnonymity").attr("checked")=="checked"){
	$('#anonymityDiv').show();
	$(".isAnonymity").each(function(){
		$(this).rules('remove');  
	})
	$(".emchar").each(function(){
		$(this).hide();  
	})
}




<s:if test='"add".equals(mode)'>
$("#registrant").val('${NAME}');
$("#createTableType option").eq(1).attr("selected", true);
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#peopleAspirationsOrgName").val(responseData);
			
		}
	});
</s:if>

setUnEdit();

});

function fillIssueAttenchFiles(){
    <s:iterator value="peopleAspirations.issueAttachFiles">
	    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}",   	     	 
			 onRemove:function(){removeAttach("<s:property value='id' />")}
	     });
	    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
	    attachFileUploadComplete = true ;
    </s:iterator>
}

function fillAttenchFiles(obj){
	if(obj==undefined){
		return ;
	}
	$("#attachFileNames").empty();
    var id = obj.attachFileId;
    var name =obj.attachFileName;
    $(".uploadifyQueueItem").each(function(){
		$(this).remove();
    })
    for(var i =0;i<id.length;i++){
    	 $("#custom-queue").addUploadFileValue({
		     id:id[i],
		     filename:name[i],
		     link:"${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId="+id[i],   	     	 
			 onRemove:function(filename){
				 removeAttach(filename)}
	     });
	    $("<option>").attr("id",id[i]).val(id[i]+","+name[i]).html(name[i]).attr("selected",true).appendTo($("#attachFileNames"));
	    attachFileUploadComplete = true ;
    } 
}

//根据身份证得到出生日期
function getBirthDayTextFromIdCard(idCard){
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(6,14);
		if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
			return "";
		}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
			return "";
		}else{
			return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
		}
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(6,12);
		if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
			return "";
		}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
			return "";
		}else{
			return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
		}
	}
	return "";
}

//根据身份证得到出生日期
function setGenderTextFromIdCard(idCard){
	$("#gender option").each(function(){
		if($(this).text().indexOf("不明")>-1){
			$(this).attr("selected","selected");
			return;
		}
	})
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(16,17);
		
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(14,15);
	}else{
		return;
	}
	if(parseInt(idCard)%2==0){
		$("#gender option").each(function(){
			if($(this).text().indexOf("女")>-1){
				$(this).attr("selected","selected");
				return;
			}
		})
		
	}else{
		$("#gender option").each(function(){
			if($(this).text().indexOf("男")>-1){
				$(this).attr("selected","selected");
				return;
			}
		})
	}
}

function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='peopleAspirations.birthDay' id='birthDay' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthDay').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

function isNull(){
	if($("#peopleOccurOrgSelector").val()==null||$("#peopleOccurOrgSelector").val()==""){
		$.notice({
			title:"提示",
			message:"请先选择所属网格！",
			width: 300
		});
		return false;
		}
	return true;
}

function initOccurOrgSelector(){
		var tree=$("#peopleOccurOrgSelector").treeSelect({
			inputName:"peopleAspirations.occurOrg.id",
			loadCom:function(){
				if(peopleEditing()){
					$.setTreeValue(getDefaultOccurOrg(),tree); 
				}
				if($('#id').val()=="")
					$("#peopleOccurOrgSelector").val("请选择");
			}
		});
		peopleTree=tree;
}

function peopleEditing(){
	return <s:property value='"edit".equals(mode)'/>;
}

function getDefaultOccurOrg(){
	<s:if test="null!=peopleAspirations.occurOrg && null!=peopleAspirations.occurOrg.id">
		return "${peopleAspirations.occurOrg.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}

function  getOccurOrgId(){
	return $("#occurOrgId").val();
} 
function changeForm(form){
	$('#_id').val(form);
}
function submit(){
	$("#"+$('#_id').val()).submit();
}
function setResponseGroupNo(){
	if($("#appealHumanType").find("option:selected").text()=="反映人"){
		$("#responseGroupNo").val(1);
	}
}
function initOccurDateSelector(){
	if($("#ui-datepicker-div")[0]){
		$("body").append("<div id='ui-datepicker-div' />")
	}
	$('#occurDate').datePicker({
		yearRange:'1930:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'%y-%M-#{%d}',
		minDate:'-'+(new Date().getDate()-1)+'d'
	});
}

function removeAttach(id){
	$("input[name='file']").removeAttr("disabled");
	$("#"+id).remove();
}
function removeInputError(obj){
	if(($("#isAnonymity").attr("checked")=="checked")&&($(obj).val()=="")){
		$(obj).removeClass("errorInput");
	}
}
$("#createTableType option").each(function(){
	if($(this).text().indexOf("上年接转")>-1){
		$(this).hide();
	}
	<s:if test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@DISTRICT">
		if($(this).text().indexOf("县委县政府")>-1){
			$(this).hide();
		}
	</s:if>
	<s:if test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@TOWN">
		if($(this).text().indexOf("上级主管部门")>-1){
			$(this).hide();
		}
	</s:if>
	<s:if test="#getFullOrgById.organization.orgLevel.internalId < @com.tianque.domain.property.OrganizationLevel@TOWN">
		if($(this).text().indexOf("上级主管部门")>-1){
			$(this).hide();
		}
		if($(this).text().indexOf("县")>-1){
			$(this).hide();
		}
	</s:if>
}) 
function setUnEdit(){
	if($("#id").val()!=''&& !new RegExp(/(1jg)$/).exec($("#currentDepartNo").val())){
		$("#name").attr("readOnly","readOnly");
		$("#isAnonymity").attr("disabled","disabled");
		$("#_isAnonymity").removeAttr("disabled");
		$("#appealContent").attr("readOnly","readOnly");
	}
}
/*
$("#idCardNo").blur(function(){
	var idCard = $("#idCardNo").val();
	if(null != idCard && "" != idCard){
		$.ajax({
			async: false ,
			url:"${path}/threeRecordsIssue/ledgerPoorPeopleManage/getCountrymenByIdCardNoAndOrgInternalCode.action",
		   	data:{
				"householdStaffVo.idCardNo": idCard,
	        },
			success:function(responseData){
				if(null == responseData ||　"" == responseData || responseData.idCardNo == null){
					var text = getBirthDayTextFromIdCard(idCard);
					resetBirthdayField(text, $("#birthDayTime"));
					return;
				}else{
					$("#name").val(responseData.name);
					$("#gender").val(responseData.gender.id);
					$("#birthDayTime").val(responseData.birthDay);
					$("#position").val(responseData.career);
					$("#permanentAddress").val(responseData.currentAddress);
					$("#conditionNation").val(responseData.nation.id);
					$("#mobileNumber").val(responseData.mobileNumber);
				}
			}
		});
	  }
  });
  */
</script>


