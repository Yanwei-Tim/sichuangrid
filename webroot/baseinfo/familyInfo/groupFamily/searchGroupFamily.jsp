<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="dialog-form" class="container container_24">
	<div class="grid_5 lable-right"><label class="form-lbl">家庭编号：</label></div>
	<div class="grid_7">
		<input type="text" name="groupFamily.familyAccount" id="groupFamily_familyAccount" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家庭成员个数：</label></div>
	<div class="grid_7">
		<input type="text" name="groupFamily.membersCount" id="groupFamily_membersCount" maxlength="20" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家长姓名：</label>
	</div>
	<div class="grid_7">
	 	<input type="text" name="groupFamily.familyMaster" id="groupFamily_familyMaster" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家长证件号：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="groupFamily.masterCardNo" id="groupFamily_masterCardNo" maxlength="18" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家庭成员姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="groupFamily.memberName" id="groupFamily_memberName" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_5 lable-right"><label class="form-lbl">家庭成员证件号：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="groupFamily.memberCardNo" id="groupFamily_memberCardNo" maxlength="20" class="form-txt" />
	</div>
	<div class="grid_5 lable-right"><label class="form-lbl">家庭住址：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="groupFamily.familyAddress" id="groupFamily_familyAddress" maxlength="20" style="width:99%;" class="form-txt" />
	</div>
</div>