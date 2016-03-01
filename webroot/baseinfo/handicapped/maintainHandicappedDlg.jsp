<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form" title="残疾人信息维护" class="container container_24">
<div id="tabs" style="height:380px;">
			<ul>
			<li><a id="tabInfoA" href="#handicapped">残疾人员信息</a></li>

			<li id="helpPersonnel"><a href="${path }/baseinfo/helpPersonnel/handicapped/guardianList.jsp?keyType=${keyType}&domainId=${handicapped.id}&width=710&height=200">监护人员信息 </a></li>
			<li id="helpPrecord"><a href="${path }/baseinfo/helpPersonnel/handicapped/managementGuardianList.jsp?keyType=${keyType}&domainId=${handicapped.id}&width=730&height=200">监护情况信息</a></li>
			</ul>
		
			<div id="handicapped"" class="container container_24">
			<div class="container container_24" style="height:10px;">
			</div>
			<c:if test='${mode!="view"}'>
			 <form id="maintainForm" method="post"	action="" >
			 <pop:token />
			</c:if>
			<div id="perUuid"></div>
			<input id="mode" type="hidden" name="mode" value="${mode}" />
			<input id="modeType" type="hidden" name="modeType" value="${modeType}" />
			<input id="organizationId"	type="hidden" name="organizationId" value="${organizationId}" />
			<input id="orgId" type="hidden" name="handicapped.organization.id" value="${organizationId}" />
			<input id="handicappedId"	type="hidden" name="handicapped.id" value="${handicapped.id}" />
			<input id="keyType"	type="hidden" name="" value="${keyType}" />
			<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
			<div class="grid_3 lable-right">
    			<label class="form-lb1">所属网格：</label>
    		</div>
    		<div class="grid_13">
    			<input type="text"  id="handicappedOrgName"  name="handicapped.organization.orgName"  readonly
    			<c:if test='${mode=="view"}'>disabled='true'</c:if> value="" style="width: 99%"	class="form-txt" />
    		</div>
    		<div  class="grid_1"> <c:if test='${mode!="view"}'><em class="form-req">*</em></c:if></div>
    		<div class='clearLine'>&nbsp;</div>

			<div  class="grid_3 lable-right">
	  			<label class="form-lbl">姓名： </label>
	  		</div>
			<div class="grid_4">
			    <input type="text" name="handicapped.name" id="name" maxlength="20"
	  				<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${handicapped.name}"
	  				class="form-txt" />
    		</div>
    		<div  class="grid_1"> <c:if test='${mode!="view"}'><em class="form-req">*</em></c:if></div>
    		<div  class="grid_3 lable-right">
	  			<label class="form-lbl">身份证号码：</label>
	  		</div>
			<div class="grid_5">
			    <input type="text" name="handicapped.idCardNo" id="idCardNo" maxlength="18"
	  				<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${handicapped.idCardNo}" title="请输入15-18位由数字或字母X组成的身份证号码"
	  				class="dialogtip form-txt" />
    		</div>
    		<div  class="grid_1"> <c:if test='${mode!="view"}'><em class="form-req">*</em></c:if></div>

    		<div  class="grid_2 lable-right">
	  			<label class="form-lbl">性别 ：</label>
	  		</div>
			<div class="grid_4">
			    <select name="handicapped.gender.id" id="gender" class="form-txt"
			    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${handicapped.gender.id}" />
				</select>
    		</div>
    		<div  class="grid_1"> <c:if test='${mode!="view"}'><em class="form-req">*</em></c:if></div>
    		<div class='clearLine'>&nbsp;</div>
    		<hr width="90%" />
    			<div class="clearLine">&nbsp;</div>
    		
    		<div class="grid_3 lable-right">
    			<label class="form-lb1">出生日期：</label>
    		</div>
    		<div class="grid_4" id="birthdayDiv">
    			<input type="text" name="handicapped.birthday" id="birthday" maxlength="32"
	  				readonly <c:if test='${mode=="view"}'>disabled='true'</c:if> value='<fmt:formatDate value="${handicapped.birthday}" type="date" pattern="yyyy-MM-dd" />'
	  				class="form-txt" />
    		</div>
    		<div class="grid_1"></div>
    		<div class="grid_3  lable-right">
    			<label class="form-lb1">联系手机：</label>
    		</div>
    		<div class="grid_4">
    			<input type="text" name="handicapped.mobileNumber" id="mobileNumber" maxlength="11"
	  				<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${handicapped.mobileNumber }" title="请输入11位以1开头的联系手机  例如：13988888888"
	  				class="dialogtip form-txt" />
    		</div>
    		<div class="grid_4 lable-right">
    			<label class="form-lb1">联系电话：</label>
    		</div>
    		<div class="grid_4">
    			<input type="text" name="handicapped.telephone" id="telephone" maxlength="15"
	  				<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${handicapped.telephone }" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
	  				class="dialogtip form-txt" />
    		</div>
  
    		<div class="clearLine">&nbsp;</div>
    		
    		<div class="grid_3 lable-right">
    			<label class="form-lb1">残疾证号：</label>
    		</div>
    		<div class="grid_4" >
    			<input type="text" name="handicapped.disabilityCardNo" id="disabilityCardNo" maxlength="26"
	  				<c:if test='${mode=="view"}'>disabled='true'</c:if> value='${handicapped.disabilityCardNo }'
	  				class="form-txt" />
    		</div>
    		<div  class="grid_1"> <c:if test='${mode!="view"}'><em class="form-req">*</em></c:if></div>
    		<div class="grid_3  lable-right">
    			<label class="form-lb1">残疾状况：</label>
    		</div>
    		<div class="grid_4">
    			<select name="handicapped.disability.id" id="disability" class="form-txt"
			    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" defaultValue="${handicapped.disability.id}" />
				</select>
    		</div>
    		<div  class="grid_1"></div>
    		<div class="grid_3 lable-right">
    			<label class="form-lb1">劳动能力：</label>
    		</div>
    		<div class="grid_4">
    			<select name="handicapped.workProfile.id" id="workProfile" class="form-txt"
			    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" defaultValue="${handicapped.workProfile.id}" />
				</select>
    		</div>
    		<div class="clearLine">&nbsp;</div>
    		
    		<div class="grid_3 lable-right">
    			<label class="form-lb1">技能特长 ：</label>
    		</div>
    		<div class="grid_4">
    			<select name="handicapped.skillProfile.id" id="skillProfile" class="form-txt"
			    	<c:if test='${mode=="view"}'>disabled='true'</c:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" defaultValue="${handicapped.skillProfile.id}" />
				</select>
    		</div>
    		
    		<div class="clearLine">&nbsp;</div>
    		
    		<div class="grid_3 lable-right">
    			<label class="form-lb1">常住地址 ：</label>
    		</div>
    		<div class="grid_13">
    			<input type="text" name="handicapped.presentAddress" id="presentAddress" maxlength="60"
    				<c:if test='${mode=="view"}'>disabled='true'</c:if> value="${handicapped.presentAddress }"
    			 class="form-txt" style="width: 100%" />
    		</div>
    		
    		<div class="clearLine">&nbsp;</div>

    		<div class="grid_3 lable-right">
    			<label class="form-lb1">备注：</label>
    		</div>
    		<div class="grid_20">
    			<textarea rows="5" name="handicapped.remark" id="remark"
    				<c:if test='${mode=="view"}'>disabled='true'</c:if>
    				class="form-txt" style="width: 100%">${handicapped.remark }</textarea>
    				<input name="isSubmit" id="isSubmit" type="hidden" value="">
    		</div>
    		
    		
    		<c:if test='${mode!="view"}'>
			   </form>
			</c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
function getCommonPopulation(idCardNo){
	if( idCardNo.length != null && idCardNo.length == 18){
		ajaxForCommonPopulation(idCardNo);
	}else if( idCardNo.length != null && idCardNo.length == 15){
		ajaxForCommonPopulation(idCardNo);
	}
}
function ajaxForCommonPopulation(idCardNo){
	$.ajax({
		async:false,
		url: "${path}/baseinfo/commonPopulationManage/getCommonPopulationByIdCardNo.action",
		data:{
			"commonPopulation.idCardNo":idCardNo
		},
		success:function(responseData){
			manageCommonPopulation(responseData);
		}
	});
}
function manageCommonPopulation(responseData){

	//性别的选中
	if(null != responseData.gender)
		$("#gender").val(responseData.gender);
	//姓名
	if(null != responseData.name)
	    $("#name").val(responseData.name)
	if(null != responseData.telephone)
	    $("#telephone").val(responseData.telephone)
	if(null != responseData.mobileNumber)
	    $("#mobileNumber").val(responseData.mobileNumber)
	if(null != responseData.nativePlaceAddress)
	    $("#presentAddress").val(responseData.nativePlaceAddress)
}
function changeOrgId(){
	$("#organizationId").val($("#orgId").val());
}

$(document).ready(function(){
	$("#tabs").tabFunction({ cache: true });
	if($("#handicappedId").val()==null || $("#handicappedId").val()==""){
		$("#helpPrecord").hide();
	}
	if(!isHelpPersonnel()){
		$("#helpPrecord").hide();
	}
	threeSelect({province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});
	$(".dialogtip").inputtip();

	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/sysadmin/orgManage/isGridOrganization.action",
			data:{
				"organization.id":$("#orgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		return bol;
	});

	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/baseinfo/handicappedManage/hasDuplicateHandicapped.action",
		   	data:{
			"handicapped.idCardNo":$('#idCardNo').val(),
			"organizationId":$('#organizationId').val(),
			"mode":$('#mode').val(),
			"handicapped.id":$("#mode").val() == "add"?"-1":$('#handicappedId').val()
	        },
			success:function(responseData){
	        		if(!responseData){
	        			$.confirm({
							title:"确认",
							message:"该身份证号码已存在,是否继续操作?",
							width: 300,
							okFunc:getHandicapped
						});
		        }
			}
		});
		return true;
	});

	<c:if test='${mode!="view"}'>
	resetBirthdayField($("#birthday").val());
	$('#idCardNo').blur(idCardChanged);
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(!data.id){
	                    	 $.messageBox({
								message:data,
								level: "error"
							 });
	                     	return;
	                     }
	                     data["organization.orgName"]=data.organization.orgName;
	                     if("add"==$("#mode").val()){
	                    	 <c:choose>
		 	 				<c:when test='${dialog != null}'>
								//提示
								$("#involvedPersonnel").setPersonnelCompleteVal({value:'IDLE_YOUTH-'+data.id,label:data.name,desc:""});
								$.messageBox({message:"成功保存残疾人信息!"});
								$("#<s:property value='#parameters.dialog'/>").dialog("close");
	  						</c:when>
			       	   		<c:otherwise>
			   		 			$("#handicappedList").addRowData(data.id,data,"first");
			   		 			$.messageBox({message:"成功保存残疾人信息!"});
		        				 if($("#isSubmit").val()=="true"){
									$("#handicappedDialog").dialog("close");
								 }else{
							 		emptyObject();
								 }
							     $("#handicappedList").setSelection(data.id);
							     checkExport();
		        			</c:otherwise>
		 	 				</c:choose>
	                     }
	                     if("edit"==$("#mode").val()){
	                    	 <c:choose>
	                    	 <c:when test='${dialog != null}'>
	        					//提示
		                    	 var ids = $("#involvedPersonnel").val().split(",");
		 							for(var i = 0 ; i<ids.length;i++){
		 							if('IDLE_YOUTH-'+data.id ==ids[i]){
		 								$("#<s:property value='#parameters.dialog'/>").dialog("close");
		 								return;
		 								}
		 							}
		        					$("#involvedPersonnel").setPersonnelCompleteVal({value:'IDLE_YOUTH-'+data.id,label:data.name,desc:""});
		        					   $.messageBox({message:"残疾人修改成功!"});
		        					$("#<s:property value='#parameters.dialog'/>").dialog("close");
			      			</c:when>
			      			<c:otherwise>
					        $("#handicappedList").setRowData(data.id,data);
					        $.messageBox({message:"成功保存残疾人修改信息!"});
							if(data.isEmphasis==1){
								 $("#"+data.id+"").css('color','#778899');
								}
						     $("#handicappedDialog").dialog("close");
						     $("#handicappedList").setSelection(data.id);
						     checkExport();
						     </c:otherwise>
							</c:choose>
	                     }
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
			rules:{
				"handicapped.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
					},
				"handicapped.gender.id":{
					required:true
				},
				"handicapped.idCardNo":{
					required:true,
					idCard:true,
					exsistedIdCard:true
				},
				"handicapped.telephone":{
					telephone:true
				},
				"handicapped.mobileNumber":{
					mobile:true
				},
				"handicapped.remark":{
					maxlength:300
				},
				"handicapped.organization.orgName":{
					isGridOrganization:true
				},
				"handicapped.disabilityCardNo":{
					required:true,
					minlength:2,
					maxlength:30
				}
			},
			messages:{
				"handicapped.name":{
					required:"请输入姓名",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("姓名至少需要输入{0}个字符"),
					maxlength:$.format("姓名最多需要输入{0}个字符")
					},
					"handicapped.gender.id":{
						required:"请选择性别"
					},
					"handicapped.idCardNo":{
						required:"请输入身份证号码",
						idCard:$.format("请输入一个合法的身份证号码"),
						exsistedIdCard:"该身份证号码已存在"
					},
					"handicapped.telephone":{
						telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
					},
					"handicapped.mobileNumber":{
						mobile:"手机号码输入只能是以1开头的11位数字"
					},
					"handicapped.remark":{
						maxlength:"备注最多需要输入300个字符"
					},
					"handicapped.organization.orgName":{
						isGridOrganization:"网格只能属于片组片格"
					},
					"handicapped.disabilityCardNo":{
						required:"请输入残疾证号",
						minlength:$.format("残疾证号至少需要输入{0}个字符"),
						maxlength:$.format("残疾证号最多需要输入{0}个字符")
					}
			}
		});

	</c:if>

	<c:if test='${mode!="add"}'>
		$("#maintainForm").attr("action","${path}/baseinfo/handicappedManage/addHandicapped.action");
	</c:if>
	<c:if test='${mode!="edit"}'>
		$("#maintainForm").attr("action","${path}/baseinfo/handicappedManage/editHandicapped.action");
	</c:if>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":${organizationId}
		},
		success:function(responseData){
			$("#handicappedOrgName").val(responseData);
		}
	});

	<c:if test='${modeType=="add_path"}'>
	$("#handicappedOrgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	</c:if>


	<c:if test='${dialog != null}'>
		var tree = $("#handicappedOrgName").treeSelect({
			inputName:"handicapped.organization.id",
			url:"/sysadmin/orgManage/loadTownForExtTree.action",
			onSelect:changeOrgId
		});
		//$.setTreeValue($("#orgId").val(),tree);
		var bol = false;
		$.ajax({
			async:false,
			url:"${path}/issue/issueManage/checkOccurOrgId.action",
			data:{
				"issueNew.occurOrg.id":${USER_ORG_ID}
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		var url = "";
		if(bol){
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#orgId").val()
		}else{
			url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#orgId").val()
		}
		$.ajax({
			url:url,
			success:function(data){
				$.searchChild(tree,data);
			}
		});
	</c:if>

});

<c:if test='${modeType=="add_path"}'>
function setZone(selectOrgId,selectOrgName){
		$("#orgId").val(selectOrgId);
		$("#organizationId").val(selectOrgId);
		$("#handicappedOrgName").val(selectOrgName);
	}
</c:if>

function idCardChanged(){
	var text=$('#idCardNo').val();
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
	text=$('#idCardNo').val();
	getCommonPopulation(text);
}


function resetBirthdayField(text){
	if (text!="" && $('#idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='handicapped.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
	}
}

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

function isHelpPersonnel(){
	var flag=false;
	$.ajax({
		async:false,
		url: "${path }/baseinfo/helpPersonnel/getHelpPersonnelByIdAndPlace.action",
		data:{
			"personnelId":$("#handicappedId").val(),
			"personnelType":$("#keyType").val()
		},
		success:function(responseData){
			if(responseData.length>0){
				flag=true;
			}
		}
	});
	return flag;
}

function getHandicapped(){
	$.ajax({
		async: false,
		url: "${path }/baseinfo/handicappedManage/getHandicappedByIdCardNo.action",
		data:{
			"handicapped.organization.id":$("#organizationId").val(),
			"handicapped.idCardNo":$("#idCardNo").val()
		},
		success:function(responseData){
			if(responseData.id){
				$("#mode").val("edit");
				$("#maintainForm").attr("action","${path}/baseinfo/handicappedManage/editHandicapped.action");
				$("#handicappedId").val(responseData.id);
				$("#name").val(responseData.name);
				$("#idCardNo").val(responseData.idCardNo);
				$("#gender").val(responseData.gender.id);
				$("#presentAddress").val(responseData.presentAddress!=null?responseData.presentAddress:"");
				$("#birthday").val(responseData.birthday!=null?responseData.birthday:"");
				$("#skillProfile").val(responseData.skillProfile!=null?responseData.skillProfile.id:"");
				$("#telephone").val(responseData.telephone!=null?responseData.telephone:"");
				$("#disabilityCardNo").val(responseData.disabilityCardNo!=null?responseData.disabilityCardNo:"");
				$("#mobileNumber").val(responseData.mobileNumber!=null?responseData.mobileNumber:"");
				$("#disability").val(responseData.disability!=null?responseData.disability.id:"");
				$("#workProfile").val(responseData.workProfile!=null?responseData.workProfile.id:"");
				$("#remark").val(responseData.remark!=null?responseData.mobileNumber:"");
			}
		}
	});
}

function emptyObject(){
				$("#handicappedId").val("");
				$("#name").val("");
				$("#idCardNo").val("");
				$("#gender").val("");
				$("#birthday").val("");
				$("#mobileNumber").val("");
				$("#telephone").val("");
				$("#disabilityCardNo").val("");
				$("#disability").val("");
				$("#workProfile").val("");
				$("#skillProfile").val("");
				$("#presentAddress").val("");
				$("#remark").val("");
				threeSelect({province:'province',
					city:'city',
					district:'district',
					provinceValue:$('#provinceValue').val(),
					cityValue:$('#cityValue').val(),
					districtValue:$('#districtValue').val()
				});
}
</script>