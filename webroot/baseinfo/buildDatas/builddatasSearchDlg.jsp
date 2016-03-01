<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="楼宇信息表"
	class="container container_24">
	<form id="maintainForm" method="post" action="">
	<pop:token />
		<input type="hidden" id="organization" name="builddatasSearchVo.organization.id" value="${param.orgId}"/>
		<input type="hidden" id="organization.id" name="organization.id" value="${param.orgId}"/>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">楼宇名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatasSearchVo.buildingname" id="buildingname" value=""   class="form-txt" maxlength="50" />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">楼宇地址：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatasSearchVo.buildingaddress" id="buildingaddress" value=""   class="form-txt" maxlength="50" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">业主：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatasSearchVo.owner" id="owner" value=""   class="form-txt" maxlength="20" />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="builddatasSearchVo.responsibleperson" id="responsibleperson" value=""   class="form-txt" maxlength="20" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		
		<div class="grid_8">
			<input type="text" name="builddatasSearchVo.phone" id="phone" value=""   class="form-txt" maxlength="15" />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">建筑结构：</label>
		</div>
		<div class="grid_8">
			<select name="builddatasSearchVo.buildingstructuresId" id="buildingstructures" class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
			</select>
		</div>
		<div class='clearLine'></div>
	</form>
</div>
<script>
$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
				"builddatasSearchVo.buildingname":{
					maxlength:50
					},
				"builddatasSearchVo.buildingaddress":{
					maxlength:50
					},
				"builddatasSearchVo.owner":{
					maxlength:20
					},
				"builddatasSearchVo.responsibleperson":{
					maxlength:20
					},
				"builddatasSearchVo.phone":{
					maxlength:15
					}
				
		},
		messages:{

				"builddatasSearchVo.buildingnameMax":{
						maxlength:$.format("楼宇名称最多需要输入{0}个字符")
					},
				"builddatasSearchVo.buildingaddressMax":{
						maxlength:$.format("楼宇地址最多需要输入{0}个字符")
					},
				"builddatasSearchVo.ownerMax":{
						maxlength:$.format("业主最多需要输入{0}个字符")
					},
				"builddatasSearchVo.responsiblepersonMax":{
						maxlength:$.format("负责人最多需要输入{0}个字符")
					},
				"builddatasSearchVo.phone":{
					minlength:$.format("联系电话最少需要输入{0}个字符"),
					maxlength:$.format("联系电话最多需要输入{0}个字符"),
					telephone:$.format("联系电话只能输数字和横杠(-)")

					}
		}
	});
</script>