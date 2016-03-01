<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div id="dialog-form" title="户籍人口" class="container container_24">
	<div class="grid_5 lable-right"><label class="form-lbl">户口号：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.familyHouseAccountNumber" id="searchFamilyHouseVo.familyHouseAccountNumber" maxlength="20"
		class="form-txt" /></div>
	<div class="grid_5 lable-right"><label class="form-lbl">家庭住址：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.familyHouseAddress"
		id="searchFamilyHouseVo.familyHouseAddress" maxlength="20" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">户主姓名：</label>
	</div>
	<div class="grid_7"><input type="text" name="searchFamilyHouseVo.headOfFamilyHouseName" id="searchFamilyHouseVo.headOfFamilyHouseName" maxlength="20" class="form-txt" />
	</div>
	<div class="grid_5 lable-right"><label class="form-lbl">户主身份证号码：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.familyHouseIdCardNo" id="searchFamilyHouseVo.familyHouseIdCardNo"
		maxlength="18" class="form-txt" /></div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家庭成员姓名：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.familyHouseMemberName"
		id="searchFamilyHouseVo.familyHouseMemberName" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家庭成员身份证号码：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.familyHouseMemberIdCardNo"
		id="searchFamilyHouseVo.familyHouseMemberIdCardNo" maxlength="20" class="form-txt" />
	</div>
	<!-- 
	<div class="grid_5 lable-right"><label class="form-lbl">成员个数：</label>
	</div>
	<div class="grid_7"><input type="text"
		name="searchFamilyHouseVo.memberNum" id="searchFamilyHouseVo.memberNum" maxlength="20"
		class="form-txt" /></div>
	 -->
</div>