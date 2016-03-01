<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="grid_4 lable-right">
	<em class="form-req">*</em>
	<label class="form-lb1">所属网格：</label>
</div>
<div class="grid_18">
	<input type="text" id="houseInfoOrgName"  name="houseInfo.organization.orgName"  readonly  value="${houseInfo.organization.orgName}" style="width: 99%"	class="form-txt" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
<em class="form-req">*</em>
	<label class="form-lb1">房屋编号：</label>
</div>
<div class="grid_18">
	<input type="text" id="houseCode"  name="houseInfo.houseCode" value="${houseInfo.houseCode}"  maxlength="50" style="width: 99%"
		class="form-txt {required:true,messages:{required:'请输入房屋编号'}}" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<em class="form-req">*</em>
	<label class="form-lb1">房屋地址：</label>
</div>
<div class="grid_3">
	<input id="addressTypeId" type="hidden" name="houseInfo.addressType.id" value="${houseInfo.addressType.id}" />

	<select name="currentAddressType" id="currentAddressType" class="form-txt" disabled>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" showInternalId="true" defaultValue="${houseInfo.addressType.id}" notNull="true" />
	</select>
</div>
<div id="building" >
	<div class="grid_3">
	 	<input type="text"  id="community"  name="houseInfo.community"  maxlength="20"  value="${houseInfo.community}" style="width: 93%"
	 		class="form-txt {community:true,messages:{community:'该房屋地址不存在，请重新输入房屋编号'}}" readonly/>
	</div>
	<div class="grid_2">
  		 	<label class="form-lbl">小区</label>
	</div>
	<div class="grid_2">
<input type="text"  id="block"  name="houseInfo.block"  maxlength="8"  value="${houseInfo.block}" style="width: 93%"	class="form-txt" readonly />
	</div>
	<div class="grid_1">
     	<label class="form-lbl">幢</label>
	</div>
	<div class="grid_2">
	 	<input type="text"  id="unit"  name="houseInfo.unit"  maxlength="6"  value="${houseInfo.unit}" style="width: 93%"	class="form-txt" readonly />
	</div>
	<div class="grid_2">
 	 	<label class="form-lbl">单元</label>
	</div>
	<div class="grid_2">
	 	<input type="text"  id="room"  name="houseInfo.room"  maxlength="10"  value="${houseInfo.room}" style="width: 93%"	class="form-txt" readonly />
	</div>
	<div class="grid_1">
 		<label class="form-lbl">室</label>
	</div>
</div>
<div id="otherAddress" class="grid_15" style="display:none">
	<input type="text"  id="address"  name="houseInfo.address"  maxlength="50"  value="${houseInfo.address }" style="width: 99%"
		class="form-txt {address:true,messages:{address:'请输入房屋地址'}}" readonly />
</div>
<div class='clearLine'>&nbsp;</div>