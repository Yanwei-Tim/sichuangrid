<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<s:if test="#parameters.fromPopulationType[0]">
	<span style="font-weight: bold; margin-left:20px; ">党员信息</span>
	<div class='clearLine'>&nbsp;</div>
</s:if>

<div class="grid_5 lable-right">
	<em class="form-req">*</em>
	<label class="form-lbl">所属党支部：</label>
</div>
<div class="grid_7">
	<input type="hidden" name="population.partyOrgInfo.id" id="partyOrgId" value="${population.partyOrgInfo.id}" />
	<input type="hidden" id="establishedDate" value="<s:date name='population.partyOrgInfo.establishedDate' format='yyyy-MM-dd'/>"/>
	<input type="text" name="partyBranchName" id="partyBranchName" class="form-txt" value="${population.partyOrgInfo.partyBranchName}" maxlength="50" readonly/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">进入党支部时间：</label>
</div>
<div class="grid_7">
	<input type="text" id="joinPartyBranchDate" name="population.joinPartyBranchDate" readonly="readonly" class="form-txt" value="<s:date name="population.joinPartyBranchDate" format="yyyy-MM-dd"/>" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">党员类型：</label>
</div>
<div class="grid_7">
    <select id="population.partyMemberType.id" name="population.partyMemberType.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYMEMBER_TYPE"  defaultValue="${population.partyMemberType.id }"  />
    </select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">入党时间：</label>
</div>
<div class="grid_7">
	<input type="text" id="joinPartyDate" name="population.joinPartyDate" readonly="readonly" class="form-txt" value="<s:date name="population.joinPartyDate" format="yyyy-MM-dd"/>" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">入党时所在党支部：</label>
</div>
<div class="grid_7">
    <input type="text" name="population.joinPartyBranch" id="joinPartyBranch"  class="form-txt" value="${population.joinPartyBranch}" maxlength="140"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">转正时间：</label>
</div>
<div class="grid_7">
	<input type="text" id="becomesDate" name="population.becomesDate" readonly="readonly" class="form-txt" value="<s:date name="population.becomesDate" format="yyyy-MM-dd"/>"/>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">转正情况：</label>
</div>
<div class="grid_7">
	<select id="population.becomesState.id" name="population.becomesState.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BECOMES_STATE"  defaultValue="${population.becomesState.id }"  />
    </select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">党内主要职务：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.partyPosition" id="partyPosition" class="form-txt" value="${population.partyPosition}" maxlength="48"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">任职日期：</label>
</div>
<div class="grid_7">
	<input type="text" id="officeDate" name="population.officeDate" readonly="readonly" class="form-txt" value="<s:date name="population.officeDate" format="yyyy-MM-dd"/>"/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">培训情况：</label>
</div>
<div class="grid_7">
	<select id="population.trainingState.id" name="population.trainingState.id"  class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TRAINING_STATE"  defaultValue="${population.trainingState.id }"  />
    </select>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">奖惩情况：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.rewardsPunishment" id="rewardsPunishment"  class="form-txt" value="${population.rewardsPunishment}" maxlength="350"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">专长：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.expertise" id="expertise"  class="form-txt" value="${population.expertise}" maxlength="380"
	<s:if test='"view".equals(mode)'>disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
</div>
<div class="grid_19">
	<input type="checkbox" id="isFlowPartyCard" name="population.isFlowPartyCard" value="1" <s:if test='1==population.isFlowPartyCard'>checked="checked"</s:if> />
	<label class="form-check-text">是否持流动党员证 </label>
</div>
<div class='clearLine'>&nbsp;</div>

<div id="flowParty">
	<div class="grid_5 lable-right">
		<label class="form-lbl">流入地党支部：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.flowPartyBranch" id="flowPartyBranch"  class="form-txt" value="${population.flowPartyBranch}" maxlength="140"
		<s:if test='"view".equals(mode)'>disabled</s:if>/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">流入时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="flowPartyBranchDate" name="population.flowPartyBranchDate" readonly="readonly" class="form-txt" value="<s:date name="population.flowPartyBranchDate" format="yyyy-MM-dd"/>"/>
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">党支部联系人：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.partyBranchContacts" id="partyBranchContacts"  class="form-txt" value="${population.partyBranchContacts}" maxlength="8"
		<s:if test='"view".equals(mode)'>disabled</s:if>/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">固定电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.branchTelephone" id="branchTelephone"  class="form-txt" value="${population.branchTelephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"  maxlength="20"
		<s:if test='"view".equals(mode)'>disabled</s:if>/>
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.branchMobileNumber" id="branchMobileNumber"  class="form-txt"  value="${population.branchMobileNumber}" title="请输入11位以1开头的联系手机  例如：13988888888" maxlength="11"
		<s:if test='"view".equals(mode)'>disabled</s:if>/>
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	flowPartyCardData();

	$("#isFlowPartyCard").click(function() {
		flowPartyCardData();
	});

	$('#joinPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#joinPartyBranchDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#joinPartyDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#joinPartyDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#becomesDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#becomesDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#officeDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#officeDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#flowPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#flowPartyBranchDate").datepicker("option", "maxDate",date);
			}
		}
	});

});

function flowPartyCardData() {
	if(($("#isFlowPartyCard").attr("checked")=="checked")){
		$("#flowParty").show();
	} else {
		$("#flowParty").hide();
		$("#flowPartyBranch").val("");
		$("#flowPartyBranchDate").val("");
		$("#partyBranchContacts").val("");
		$("#branchTelephone").val("");
		$("#branchMobileNumber").val("");
	}
}
</script>
