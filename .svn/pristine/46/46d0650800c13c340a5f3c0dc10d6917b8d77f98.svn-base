<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchLowerPartyOrgInfoForm" method="post">
<div class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">党支部名称：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="searchPartyOrgInfoVo.partyBranchName" id="partyBranchName" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">党支部书记：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="partyBranchSecretary" name="searchPartyOrgInfoVo.partyBranchSecretary" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
            <label class="form-lbl">党支部成立时间从：</label>
        </div>
        <div class="grid_8">
            <input type="text" id="establishedDate" name="searchPartyOrgInfoVo.establishedDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_4" align="center">
            <label class="form-lbl">至&nbsp;&nbsp;</label>
        </div>
        <div class="grid_8">
            <input type="text" id="endEstablishedDate" name="searchPartyOrgInfoVo.endEstablishedDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_4 lable-right">
            <label class="form-lbl">党员人数从：</label>
        </div>
        <div class="grid_8">
            <input type="text" id="partyNumbers" name="searchPartyOrgInfoVo.partyNumbers" class="form-txt" />
        </div>
        <div class="grid_4" align="center">
            <label class="form-lbl">至&nbsp;&nbsp;</label>
        </div>
        <div class="grid_8">
            <input type="text" id="endPartyNumbers" name="searchPartyOrgInfoVo.endPartyNumbers" class="form-txt" />
        </div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">党组织地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" id="partyBranchAddr" name="searchPartyOrgInfoVo.partyBranchAddr" class="form-txt" />
		</div>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$('#establishedDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#establishedDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endEstablishedDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endEstablishedDate").datepicker("option", "maxDate",date);
			}
		}
	});

});
</script>
