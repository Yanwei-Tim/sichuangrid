<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
.del {
	display: none;
}
</style>
<div id="dialog-form" class="container container_24">
	<form id="maintainFormOfBasicYearInfo" method="post"
		action="${path }/baseinfo/basicYearInfoManage/maintainBasicYearInfo.action">
		<pop:token />
		<input type="hidden" name="basicYearInfo.organization.id"
			value="${organization.id }" /> <input type="hidden" name="dataType"
			value="${dataType }" /> <input type="hidden"
			name="basicYearInfo.newVillageBasic.id" id="newVillageBasicId"
			value="${newVillageBasic.id }" /> <input type="hidden"
			name="basicYearInfo.id" id="basicYearInfoId"
			value="${basicYearInfo.id }" />

		<div id="basicYearInfoDialog" style="height: 350px;">
			<span style="font-weight: bold;">土地信息</span>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">幅员面积(平方公里)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.totalArea" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.totalArea }' pattern='#0.00'/>"
					<s:if test="'view'.equals(mode)">disabled</s:if> maxlength="12" />
			</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">&emsp;&emsp;耕地面积(亩)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.cultivatedLandArea" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.cultivatedLandArea}' pattern='#0.00'/>" maxlength="12"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">林地面积(亩)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.woodlandArea" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.woodlandArea}' pattern='#0.00'/>" maxlength="12"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">荒地面积(亩)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.wastelandArea" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.wastelandArea}' pattern='#0.00'/>" maxlength="12"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">流转土地面积(亩)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.landTransfer" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.landTransfer}' pattern='#0.00'/>" maxlength="12"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<hr />
			<div class='clearLine'>&nbsp;</div>
			<span style="font-weight: bold;">人口信息</span>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">总户数(户)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.totalHouseholdsNum" id="" class="form-txt"
					value="${basicYearInfo.totalHouseholdsNum}" maxlength="8"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">总人数(口)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.totalPeopleNum" id="" class="form-txt"
					value="${basicYearInfo.totalPeopleNum}" maxlength="8"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">外出务工人员(口)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.outWorkNum" id="" class="form-txt"
					value="${basicYearInfo.outWorkNum}" maxlength="8"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>

			<hr />
			<div class='clearLine'>&nbsp;</div>
			<span style="font-weight: bold;">村集体资产信息</span>
			<div class='clearLine'>&nbsp;</div>

			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">集体资金(万元)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.collectiveFunds" id="" class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.collectiveFunds}' pattern='#0.00'/>" maxlength="12"
					<s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class="grid_4 lable-right" style="margin-left: 50px;">
				<label class="form-lbl">集体资产折资(万元)：</label>
			</div>
			<div class="grid_6">
				<input type="text" <s:if test="flag">disabled</s:if>
					name="basicYearInfo.collectiveAssets" id=""
					class="form-txt"
					value="<fmt:formatNumber value='${basicYearInfo.collectiveAssets}' pattern='#0.00'/>"
					maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if> />
			</div>
			<div class='clearLine'>&nbsp;</div>
		  		<div class="grid_4 lable-right" style="margin-left: 50px;">
					<label class="form-lbl">村集体资源： </label>
				</div>
				<div class="grid_17">
		   			<textarea rows="3" cols="77"  maxlength="300" name="basicYearInfo.assetsDescribe" class="form-txt">${basicYearInfo.assetsDescribe }</textarea>
		   		</div>
			
		</div>
	<s:if test="!'view'.equals(mode) && flag==false">
			<input type="submit" value="保存"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
				style="margin-left: 900px;" />
	</form>
	</s:if>
</div>
<script>
	function mySelected(values) {
		if ($("#" + values).val() == 1) {
			$("#" + values + "S").removeClass("span_none");
		} else {
			$("#" + values + "I").val("");
			$("#" + values + "S").addClass("span_none");
		}
	}

	$(document).ready(function() {
		$("#maintainFormOfBasicYearInfo").formValidate({
			promptPosition : "bottomLeft",
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					async : false,
					success : function(data) {
						if (data.id) {
							$("#basicYearInfoId").val(data.id);
							$.messageBox({
								message : "基本信息保存成功!"
							});
						} else {
							$.messageBox({
								message : data,
								level : 'error'
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交数据时发生错误");
					}
				});
			},
			rules : {
				"basicYearInfo.totalArea" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.cultivatedLandArea" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.woodlandArea" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.wastelandArea" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.landTransfer" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.totalHouseholdsNum" : {
					digits : true,
					min : 0,
					max : 999999999
				},
				"basicYearInfo.totalPeopleNum" : {
					digits : true,
					min : 0,
					max : 999999999
				},
				"basicYearInfo.outWorkNum" : {
					digits : true,
					min : 0,
					max : 999999999
				},
				"basicYearInfo.collectiveFunds" : {
					posNumWiPot : true,
					max : 9999999999.99
				},
				"basicYearInfo.collectiveAssets" : {
					posNumWiPot : true,
					max : 9999999999.99
				}
			},
			messages : {
				"basicYearInfo.totalArea" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "幅员面积最大输入9999999999.99"
				},
				"basicYearInfo.cultivatedLandArea" : {
					posNumWiPot : "请请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "耕地面积最大输入9999999999.99"
				},
				"basicYearInfo.woodlandArea" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "林地面积最大输入9999999999.99"
				},
				"basicYearInfo.wastelandArea" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "荒地面积最大输入9999999999.99"
				},
				"basicYearInfo.landTransfer" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "土地流转面积最大输入9999999999.99"
				},
				"basicYearInfo.totalHouseholdsNum" : {
					digits : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					min : "总户数请输入正数",
					max : "总户数最大输入9999999999"
				},
				"basicYearInfo.totalPeopleNum" : {
					digits : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					min : "总人数请输入正数",
					max : "总人数最大输入9999999999"
				},
				"basicYearInfo.outWorkNum" : {
					digits : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					min : "外出务工人数请输入正数",
					max : "外出务工人数最大输入9999999999"
				},
				"basicYearInfo.collectiveFunds" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "集体资金最大输入9999999999.99"
				},
				"basicYearInfo.collectiveAssets" : {
					posNumWiPot : "请输入0-9999999999.99范围数字,最大保留2位小数点",
					max : "集体资产折资最大输入9999999999.99"
				}
			}
		});
	});

	
</script>