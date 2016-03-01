<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="企业维护" class="container container_24">
	<div id="tabs">
		<ul>
		<li><a id="tabInfoA" href="#enterpriseInfo">规上企业信息</a></li>
		<li id="personInCharge"><a href="${path }/baseinfo/siteinfo/enterprise/personInCharge/personInChargeList.jsp?keyType=${keyType}&domainId=${enterprise.id}&width=880&height=300">治安负责人信息</a></li>
		<li id="floorpersons"><a href="${path }/baseinfo/siteinfo/enterprise/floorpersons/floorpersonsList.jsp?keyType=${keyType }&domainId=${enterprise.id}&width=880&height=300">巡场情况信息</a></li>
		</ul>
			<div id="enterpriseInfo" class="container container_24">
			<form name="maintainForm" id="maintainForm" method="post" action="">
			<div id="perUuid"></div>
			<input id="modeType" type="hidden"	 name="modeType" value="${modeType}" />
			<input id="mode" type="hidden"	 name="mode" value="${mode}" />
			<input id="organizationId"	type="hidden" name="ownerOrg.id" value="${ownerOrg.id}" />
			<input id="enterpriseId"	type="hidden" name="enterprise.id" value="${enterprise.id}" />
			<input id="keyType" type="hidden" name="enterprise.keyType" value="${keyType }" />
			<input id="placeTypeName" type="hidden" name="placeTypeName" value="${placeTypeName}">
				<div class="grid_3 lable-right">
					<label class="form-lbl">所属网格：</label>
				</div>
				<div class="grid_20">
			    	<input type="text" name="orgName" id="orgName" value="" readonly
			    	<s:if test='"view".equals(mode)'>disabled='true'</s:if> class="form-txt" style="width: 99%" />
				</div>
				<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
				<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">场所名称：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="enterprise.name" id="name"
					value="${enterprise.name}" maxlength="50"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt" style="width: 99%" />
			</div>
			<div class="grid_1">
				<s:if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</s:if>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">场所类型： </label>
			</div>
			<div class="grid_4">
				<select name="enterprise.type.id" class="form-txt dialogtip"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
					 <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIALTRADE_TYPE"
                     defaultValue="${enterprise.type.id}" />
				</select>
			</div>
			<div class="grid_1">
				<s:if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</s:if>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">负责人：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.legalPerson" class="form-txt"
					value="${enterprise.legalPerson }" maxlength="20"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
			</div>
			<div class="grid_1">
				<s:if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</s:if>
			</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.telephone" maxlength="15"
					value="${enterprise.telephone }" class="form-txt  dialogtip"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">手机号码：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.mobileNumber"
					value="${enterprise.mobileNumber }" maxlength="11"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如:13988888888" />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<!--
			<div class="grid_3 lable-right">
				<label class="form-lbl">注册资金：</label>
			</div>
			<div class="grid_3">
				<input type="text" name="enterprise.registeredCapital"
					maxlength="11" class="form-txt" style="text-align:right;"
					value="${enterprise.registeredCapital }"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
			</div>
			<div class="grid_2" >
				<label class="form-lbl">(万元)</label>
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">所属行业：</label>
			</div>
			<div class="grid_4">
				<select name="enterprise.industry.id" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@OCCUPATION"
						defaultValue="${enterprise.industry.id}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">面积：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.area" maxlength="11"
					class="form-txt" style="text-align: right; width: 85%;"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					value="${enterprise.areaString}" />M<sup>2</sup>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">员工数：</label>
			</div>
			<div class="grid_5">
				<input type="text" name="enterprise.employeeAmount" id="employeeAmount"
					value="${enterprise.employeeAmount }" maxlength="9"
					style="text-align: right;" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
			</div>
			<div class="grid_3 lable-right">
				<label class="form-lbl">党员数：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.partyMemberAmount" id="partyMemberAmount"
					value="${enterprise.partyMemberAmount }" maxlength="9"
					style="text-align: right;" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>
			 <div class="grid_3 lable-right">
				<label class="form-lbl">安全责任人：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.businessLicense" maxlength="20"
					class="form-txt dialogtip" value="${enterprise.businessLicense }"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">联系手机：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.enterpriseTelephone"
					value="${enterprise.enterpriseTelephone }" maxlength="15"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt  dialogtip"
					title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="enterprise.fax" value="${enterprise.fax }"
					maxlength="15" class="form-txt dialogtip"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					title="请输入由数字和-组成的传真号码!例如：0577-88888888" />
			</div>
			 -->
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">场所地址： </label>
			</div>
			<div class="grid_20">
				<input type="text" name="enterprise.address"
					value="${enterprise.address }" maxlength="50"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt" style="width: 99%" />
			</div>
			<div class="grid_1">
				<s:if test='!"view".equals(mode)'>
					<em class="form-req">*</em>
				</s:if>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<!--
			<div class="grid_3"></div>
			<div class="grid_21">
				<input id="isRiskEnterprise" type="checkbox"
					name="enterprise.riskEnterprise" value="true" class="dialogtip"
					<s:if test="enterprise.riskEnterprise" >checked="checked"</s:if>
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					title="指依法设立且取得企业法人营业执照的，从事危险化学品生产的企业，包括最终产品或中间产品列入《危险化学品名录》的危险化学品的生产企业。" />
				<label class="form-check-text" for="isRiskEnterprise">是否为危化企业</label>
			</div>
			<div class='clearLine'>&nbsp;</div>
			 -->
			<div class="grid_3 lable-right">
				<label class="form-lbl">隐患情况：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="enterprise.hiddenCases"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt" value="${enterprise.hiddenCases }"
					style="width: 99%" maxlength="100" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">隐患整改情况：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="enterprise.hiddenRectification"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt" value="${enterprise.hiddenRectification }"
					style="width: 99%" maxlength="100" />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_3 lable-right">
				<label class="form-lbl">备注：</label>
			</div>
			<div class="grid_20">
				<textarea name="enterprise.remark"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>
					class="form-txt" style="height: 3em; width: 99%">${enterprise.remark }</textarea>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<br />
<!--
			<div id="comprehensiveManagementPosts" class="container container_24">
			<s:iterator value="comprehensiveManagementPosts" id="comprehensiveManagementPost"  status="statu" >
				<s:iterator value="enterprise.comprehensiveManageMembers" id="comprehensiveManageMember">
					<s:if test="#comprehensiveManagementPost.id == #comprehensiveManageMember.type.id">
						<s:set name="comprehensiveManageMemberValid" value="#comprehensiveManageMember" />
					</s:if>
				</s:iterator>

				<input type="hidden" name="enterprise.comprehensiveManageMembers[${statu.index }].type.id"
					value="<s:property value="id"/>" />
				<input type="hidden" name="enterprise.comprehensiveManageMembers[${statu.index }].id"
					value="<s:property value="#comprehensiveManageMemberValid.id"/>" />
				<div class="grid_4 lable-right">
					<label class="form-lbl"> <s:property value="displayName" />：</label>
				</div>
				<div class="grid_4">
					<input type="text" id="name${statu.index}"
						name="enterprise.comprehensiveManageMembers[${statu.index}].name"
						value="<s:property value="#comprehensiveManageMemberValid.name"/>"
						class="form-txt" maxlength="20"
						<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
				</div>
				<div class="grid_1">
					<s:if test='displayName == "综治专干" or displayName == "综治负责人"'>
						<s:if test='!"view".equals(mode)'>
							<em class="form-req">*</em>
						</s:if>
					</s:if>
				</div>
				<div class="grid_3 lable-right">
					<label class="form-lbl">联系电话：</label>
				</div>
				<div class="grid_4">
					<input type="text" id="telephone${statu.index}"
						name="enterprise.comprehensiveManageMembers[${statu.index}].telephone"
						value="<s:property value="#comprehensiveManageMemberValid.telephone"/>"
						class="form-txt" maxlength="15"
						<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">联系手机：</label>
				</div>
				<div class="grid_4">
					<input type="text" id="mobileNumber${statu.index}"
						name="enterprise.comprehensiveManageMembers[${statu.index}].mobileNumber"
						value="<s:property value="#comprehensiveManageMemberValid.mobileNumber"/>"
						class="form-txt" maxlength="11"
						<s:if test='"view".equals(mode)'>disabled='true'</s:if> />
				</div>
				<div class='clearLine'>&nbsp;</div>
				<s:set name="comprehensiveManageMemberValid" value="" />
			</s:iterator>
			</div>
 -->
 </form>
		</div>
	</div>
</div>
<script type="text/javascript">
var enterpriseOrgTree="";
function isGridForTreeSelect(){
	if(enterpriseOrgTree != ""){
		return $.getSelectedNode(enterpriseOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
<s:if test='#parameters.dialog != null'>
	enterpriseOrgTree = $("#orgName").treeSelect({
		url:"/sysadmin/orgManage/loadTownForExtTree.action",
		inputName:"ownerOrg.id"
	});
	$.setTreeValue($("#organizationId").val(),enterpriseOrgTree);
</s:if>

var keyType='';
var keyTypeName='';

	function keyTypeValue(){
		if($("#keyType") != null && $("#keyType")[0] != null && $("#keyType")[0].value != ''){
			keyType = $("#keyType")[0].value;
		}
	}

	function getKeyTypeName(){
		keyTypeValue();
		if(keyType == 'enterpriseKey'){
			keyTypeName ='规上企业';
		}else if(keyType == 'protectionKey'){
			keyTypeName ='重点保障';
		}else if(keyType == 'safetyProductionKey'){
			keyTypeName ='安全生产重点';
		}else if(keyType == 'fireSafetyKey'){
			keyTypeName ='消防安全重点';
		}else if(keyType == 'securityKey'){
			keyTypeName ='治安重点';
		}
	}

	function initTabText(){
		getKeyTypeName();
		$("#tabInfoA").text(keyTypeName+"信息");
	}
	$(document).ready(function(){
		$.ajax({
			async: false,
			url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#orgName").val(responseData);
			}
		})
		$("#tabs").tabFunction({ cache: true });
		$(".dialogtip").inputtip();
		if($("#enterpriseId").val()==""){
			$("#floorpersons").hide();
		}
		if(!ispersonInCharge()){
			$("#floorpersons").hide();
		}
		initTabText();
		<s:if test='"add".equals(mode)'>
			$("#maintainForm").attr("action", "${path}/baseinfo/enterpriseManage/addEnterprise.action");
		</s:if>
		<s:if test='"edit".equals(mode)'>
			$("#maintainForm").attr("action", "${path}/baseinfo/enterpriseManage/updateEnterprise.action");
		</s:if>

		jQuery.validator.addMethod("partyMemberAmountLessThanEmployeeAmount", function(value, element){
			if($("#partyMemberAmount").val() != null
					&& $("#employeeAmount").val() != null
					&& $("#partyMemberAmount").val() != ""
					&& $("#employeeAmount").val() != ""
					&& parseInt($("#partyMemberAmount").val()) > parseInt($("#employeeAmount").val())){
				return false;
			}
			return true;
		});

		jQuery.validator.addMethod("isGridOrganization", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			if(isGridForTreeSelect()){
				return true;
			}else{
				return false;
			}
		});

		<s:iterator value="comprehensiveManagementPosts" status="statu" >
		<s:if test='!"view".equals(mode)'>
			setWhetherReadonly(${statu.index});
		</s:if>
		$("#name${statu.index}").blur(function(){
			setWhetherReadonly(${statu.index});
			});
		</s:iterator>


		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
							$.messageBox({
								message: data,
								level: "error"
							});
							return;
						}
						data["organization.orgName"]=data.organization.orgName;
						getKeyTypeName();
						<s:if test='"add".equals(mode) || "copy".equals(mode)' >
			 				<s:if test='#parameters.dialog != null'>
								//提示
								$("#tagSerch").val($("#name").val());
        						$("#searchPositiveinfo").click();
								$.messageBox({message:"已成功保存"+keyTypeName+"新信息"});
								$("#<s:property value='#parameters.dialog'/>").dialog("close");
							</s:if>
	   						<s:else>
								$("#enterpriseList").addRowData(data.id, data, "first");
								$.messageBox({message:"已成功保存"+keyTypeName+"新信息"});
								 $("#enterpriseDialog").dialog("close");
							     $("#enterpriseList").setSelection(data.id);
							     checkExport();
							</s:else>
						</s:if>
						<s:if test='"edit".equals(mode)'>
						 	$("#enterpriseList").setRowData(data.id,data);
							$.messageBox({message:"已成功保存"+keyTypeName+"修改信息"});
							 $("#enterpriseDialog").dialog("close");
							 if(data.isEmphasis==1){
								 $("#"+data.id+"").css('color','#778899');
								}
						     $("#enterpriseList").setSelection(data.id);
						     checkExport();
						</s:if>
					}
				})
			},
			rules:{
				"orgName":{
					isGridOrganization:true
				},
				"enterprise.name":{
					required:true,
					exculdeParticalChar:true,
					remote:{
					data: {
						"enterprise.name": function () { return $("#name").val(); },
						"enterprise.organization.id" : function () { return $("#organizationId").val(); }  ,
						"mode":function(){return $("#mode").val();},
						"enterprise.id":function(){if($("#mode").val() == "add" ){return -1;}else{ return $("#enterpriseId").val();}},
						"enterprise.keyType":$("#keyType").val()
						} ,
						url: "${path}/baseinfo/enterpriseManage/hasDuplicateEnterpriseName.action",
						type:"get"
					},
					maxlength:50
				},
				"enterprise.legalPerson":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
				},
				"enterprise.businessLicense":{
					minlength:6,
					maxlength:20
				},
				"enterprise.registeredCapital":{
					number:true,
					min:0,
					max:999999999
				},
				"enterprise.area":{
					number:true,
					min:0,
					max:999999999
				},
				"enterprise.employeeAmount":{
					digits:true,
					min:0,
					max:999999999
				},
				"enterprise.partyMemberAmount":{
					digits:true,
					min:0,
					max:999999999,
					partyMemberAmountLessThanEmployeeAmount:true
				},
				"enterprise.enterpriseTelephone":{
					telephone:true
				},
				"enterprise.fax":{
					telephone:true
				},
				"enterprise.telephone":{
					telephone:true
				},
				"enterprise.mobileNumber":{
					mobile:true
				},
				"enterprise.address":{
					required:true,
					minlength:2,
					maxlength:50
				},
				"enterprise.hiddenCases":{
					maxlength:200
				},
				"enterprise.hiddenRectification":{
					maxlength:200
				},
				"enterprise.remark":{
					maxlength:200
				}
				<s:iterator value="comprehensiveManagementPosts" status="statu" >
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].name":{
					<s:if test='displayName == "综治专干" or displayName == "综治负责人"'>
						required:true,
					</s:if>
					minlength:2,
					maxlength:20
				}
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].telephone":{
					telephone:true
				}
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].mobileNumber":{
					mobile:true
				}
				</s:iterator>
			},
			messages:{
				"orgName":{
					isGridOrganization:"网格只能属于片组片格"
				},
				"enterprise.name":{
					required:"请输入企业名称",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("企业名称至少需要输入{0}个字符"),
					remote:"已存在相同的企业名称!",
					maxlength:$.format("企业名称最多需要输入{0}个字符")

				},
				"enterprise.legalPerson":{
					required:"请输入法人代表",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("法人代表至少需要输入{0}个字符"),
					maxlength:$.format("法人代表最多需要输入{0}个字符")
				},
				"enterprise.businessLicense":{
					minlength:$.format("工商执照至少需要输入{0}个字符"),
					maxlength:$.format("工商执照最多需要输入{0}个字符")
				},
				"enterprise.registeredCapital":{
					number: "注册资金需要输入正数",
					min: "注册资金需要输入正数",
					max: "注册资金最大输入999999999"
				},
				"enterprise.area":{
					number: "面积需要输入正数",
					min: "面积需要输入正数",
					max: "面积最大输入999999999"
				},
				"enterprise.employeeAmount":{
					digits: "员工数量需要输入正整数",
					min: "员工数量需要输入正整数",
					max: "员工数量最大输入999999999"
				},
				"enterprise.partyMemberAmount":{
					digits: "党员数量需要输入正整数",
					min: "党员数量需要输入正整数",
					max: "员工数量最大输入999999999",
					partyMemberAmountLessThanEmployeeAmount: "党员数不能大于员工数"
				},
				"enterprise.enterpriseTelephone":{
					telephone:$.format("企业联系电话不合法，只能输数字和横杠(-)")
				},
				"enterprise.fax":{
					telephone:$.format("企业传真不合法，只能输数字和横杠(-)")
				},
				"enterprise.telephone":{
					telephone:$.format("法人联系电话不合法，只能输数字和横杠(-)")
				},
				"enterprise.mobileNumber":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				},
				"enterprise.address":{
					required:"请输入企业地址",
					minlength:$.format("企业地址至少需要输入{0}个字符"),
					maxlength:$.format("企业地址最多需要输入{0}个字符")
				},
				"enterprise.hiddenCases":{
					maxlength: "隐患情况最多输入200个字符"
				},
				"enterprise.hiddenRectification":{
					maxlength: "隐患整改情况最多输入200个字符"
				},
				"enterprise.remark":{
					maxlength: "备注最多输入200个字符"
				}
				<s:iterator value="comprehensiveManagementPosts" status="statu" >
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].name":{
					<s:if test='displayName == "综治专干" or displayName == "综治负责人"'>
						required: "<s:property value='displayName'/>不能为空",
					</s:if>
					minlength:$.format("至少需要输入{0}个字符"),
					maxlength: "最多输入20个字符"
				}
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].telephone":{
					telephone:$.format("联系电话不合法，只能输数字和横杠(-)")
				}
				,
				"enterprise.comprehensiveManageMembers[${statu.index}].mobileNumber":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				}
				</s:iterator>
			}
		});

		<s:if test='"add_path".equals(modeType)'>
		$("#orgName").click(function(event){
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
	</s:if>

	});

	<s:if test='"add_path".equals(modeType)'>
	function setZone(selectOrgId,selectOrgName){
			$("#organizationId").val(selectOrgId);
			$("#orgName").val(selectOrgName);
		}
	</s:if>

	function ispersonInCharge(){
		var flag=false;
		$.ajax({
			async:false,
			url: "${path }/baseinfo/personInCharegeManage/getPersonInCharegeByIdAndPlace.action",
			data:{
				"placeId":$("#enterpriseId").val(),
				"placeType":$("#keyType").val()
			},
			success:function(responseData){
				if(responseData.length>0){
					flag=true;
				}
			}
		});
		return flag;
	}

	function setWhetherReadonly(index){
		if($("#name"+index).val() == null || $("#name"+index).val() == ""){
			setIsReadonly(index);
		}else{
			setUnReadonly(index);
		}
	}

	function setIsReadonly(index){
		$("#telephone"+index).attr("readonly", "readonly");
		$("#mobileNumber"+index).attr("readonly", "readonly");
	}

	function setUnReadonly(index){
		$("#telephone"+index).removeAttr("readonly");
		$("#mobileNumber"+index).removeAttr("readonly");
	}
</script>