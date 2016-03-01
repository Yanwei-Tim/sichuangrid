<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="peopleDuplicateRemoval">
	<div class="grid_4 lable-right">
   		<label class="form-lb1">类别：</label>
  	</div>
  	<div class="grid_8">
		   <select name="peopleDuplicateRemovalTable" id="peopleDuplicateRemovalTable">
		   		<option value="all"  selected="selected">全部</option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@POSITIVEINFO"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@RECTIFICATIVEPERSON"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@RECTIFICATIVEPERSON_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@MENTALPATIENT"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@MENTALPATIENT_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@DRUGGY"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@DRUGGY_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@AIDSPOPULATIONS"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@IDLEYOUTH"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@IDLEYOUTH_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@SUPERIORVISIT"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@SUPERIORVISIT_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@DANGEROUSGOODSPRACTITIONER"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@DANGEROUSGOODSPRACTITIONER_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@OTHERATTENTIONPERSONNEL"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@OTHERATTENTIONPERSONNEL_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@ELDERLYPEOPLE"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@ELDERLYPEOPLE_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@HANDICAPPED"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@HANDICAPPED_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@OPTIMALOBJECT"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@CIVIL_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@AIDNEEDPOPULATION"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@POORPEOPLE_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@UNEMPLOYEDPEOPLE"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@UNEMPLOYED_DISPLAYNAME"/></option>
				<option value="<s:property value="@com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType@NURTURESWOMEN"/>"><s:property value="@com.tianque.core.util.BaseInfoTables@BIRTH_DISPLAYNAME"/></option>
			</select>
   	</div>
   	<div class="grid_6">
	   	<div class="btnbanner btnbannerData">
	   		<a id="DuplicateRemoval" href="javascript:void(0)"><span>去重</span></a>
	   	</div>
	</div>
	</form>
</div>

<script type="text/javascript">
	$("#DuplicateRemoval").click(function(){
		$.dialogLoading("open");
		$.ajax({
			url:PATH+'/baseinfo/householdStaff/peopleDuplicateRemoval.action',
			type:'post',
			data:{
				'peopleDuplicateRemovalTable':$("#peopleDuplicateRemovalTable").val()
			},
			success:function(data){
				 $.dialogLoading("close");
					if(data==true || data=='true'){
						$.messageBox({ message:"去重成功!"});
						$("#householdStaffList").trigger("reloadGrid");
						return;
					}else{
						$.messageBox({ message:data});
					}
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		    	$.dialogLoading("close");
		 	    alert("提交数据时发生错误");
	 	   	}
		});
	});
</script>