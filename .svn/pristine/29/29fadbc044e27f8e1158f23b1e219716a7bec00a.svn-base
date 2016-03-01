<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchActualCompanyForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="searchActualCompanyVo.companyName" id="conditionCompanyName" maxlength="50"  class="form-txt"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="searchActualCompanyVo.companyAddress" id="conditionCompanyAddress" maxlength="50" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_7">
			<select  id="conditionCompanyType" name="searchActualCompanyVo.companyType"  class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" />
			</select>
		</div>		
		<div class="grid_7 lable-right">
			<input type="checkbox" name="searchActualCompanyVo.isKey" id="conditionIsKey" value="true"/>
			<label class="form-lb1">是否重点单位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		</div>
		<div class="grid_5"></div>	
		<div class="grid_5 lable-right">
			<label class="form-lb1">法人代表：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="searchActualCompanyVo.corporateRepresentative" id="conditionCorporateRepresentative" maxlength="50" class="form-txt"  />
		</div>
		
		<div  class="grid_5 lable-right">
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="searchActualCompanyVo.idCardNo" id="conditionIdCardNo" style="width:92%" maxlength="20"  class="form-txt"/>
		</div>
		
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="治安负责人：" name="memberLabelName"/>
		<jsp:param value="searchActualCompanyVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="巡场记录：" name="recordLabelName"/>
		<jsp:param value="searchActualCompanyVo.hasServiceRecord" name="recordSelectName"/>
	</jsp:include>
	
       	<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false" >现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
        <div class='clearLine'></div>
		<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
			<div id="showMoreCtt" style="display: none">
				<div class="grid_5 lable-right">
					<label class="form-lb1">单位电话：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="searchActualCompanyVo.telephone" id="conditionTelephone" maxlength="20" class="form-txt" />
				</div>	
				<div class="grid_5 lable-right">
					<label class="form-lb1">组织机构代码：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="searchActualCompanyVo.orgCode" id="conditionOrgCode"  maxlength="50"  class="form-txt"/>
				</div>	   
		
				<div class="grid_5 lable-right">
					<label class="form-lb1">营业执照号码：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="searchActualCompanyVo.businessLicenseNo" id="conditionBusinessLicenseNo" maxlength="32" class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">经济性质：</label>
				</div>
				<div class="grid_7">
					<select name="searchActualCompanyVo.economicNature" id="conditionEconomicNature" class="form-txt" >
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE"  />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">主管部门：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="searchActualCompanyVo.competentDepartment" id="conditionCompetentDepartment" maxlength="20"  class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">管理部门：</label>
				</div>
				<div class="grid_7">
					<input type="text" name="searchActualCompanyVo.supervisoryDepartment" id="conditionSupervisoryDepartment" maxlength="20" class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">管理级别：</label>
				</div>
				<div class="grid_7">
					<select name="searchActualCompanyVo.supervisoryLevel" id="conditionSupervisoryLevel" class="form-txt" >
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL"  />
					</select>
				</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lb1">消防等级：</label>
				</div>
				<div class="grid_7">
					<select name="searchActualCompanyVo.fireFightingLevel" id="conditionFireFightingLevel" class="form-txt" >
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL"  />
					</select>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">注册资本：</label>
				</div>
				<div class="grid_7" id="registeredCapitalDiv">
			    	<input type="text" name="searchActualCompanyVo.registeredCapitalStart" id="conditionRegisteredCapitalStart" maxlength="10" class="form-txt "/>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至&nbsp;&nbsp;</label>
				</div>
				<div class="grid_7" id="registeredCapitalDiv">
			    	<input type="text" name="searchActualCompanyVo.registeredCapitalEnd" id="conditionRegisteredCapitalEnd" maxlength="10" class="form-txt "/>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">注册日期：</label>
				</div>
				<div class="grid_7" id="registrationDateDiv">
			    	<input type="text" name="searchActualCompanyVo.registrationDateStart" id="conditionRegistrationDateStart" maxlength="32" readonly class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至&nbsp;&nbsp;</label>
				</div>
				<div class="grid_7 lable-right" id="registrationDateDiv">
			    	<input type="text" name="searchActualCompanyVo.registrationDateEnd" id="conditionRegistrationDateEnd" maxlength="32" readonly class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">有效日期：</label>
				</div>
				<div class="grid_7" id="expiryDateDiv">
					<input type="text" name="searchActualCompanyVo.expiryDateStart" id="conditionExpiryDateStart" maxlength="32" readonly class="form-txt " />		
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至&nbsp;&nbsp;</label>
				</div>
				<div class="grid_7" id="expiryDateDiv">
			    	<input type="text" name="searchActualCompanyVo.expiryDateEnd" id="conditionExpiryDateEnd" maxlength="32" readonly class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">从业人数：</label>
				</div>
				<div class="grid_7" id="employeesNumDiv">
			    	<input type="text" name="searchActualCompanyVo.employeesNumStar" id="conditionEmployeesNumStart" maxlength="11" class="form-txt " />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至&nbsp;&nbsp;</label>
				</div>
				<div class="grid_7" id="employeesNumDiv">
			    	<input type="text" name="searchActualCompanyVo.employeesNumEnd" id="conditionEmployeesNumEnd" maxlength="11" class="form-txt " />
				</div>
				
				<div class="grid_5 lable-right">
					<label class="form-lb1">经营范围：</label>
				</div>
				<div class="grid_19">
					<input type="text" name="searchActualCompanyVo.businessScope" id="conditionBusinessScope" maxlength="18"  class="form-txt "/>
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lb1">注册地址：</label>
				</div>
				<div class="grid_19">
					<input type="text" name="searchActualCompanyVo.registrationAddress" id="conditionRegistrationAddress" maxlength="20" class="form-txt " />
				</div>
		</div>
	</div>
</form>
<script type="text/javascript">

$(function(){
	$('#conditionRegistrationDateStart').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegistrationDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionRegistrationDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegistrationDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#conditionExpiryDateStart').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpiryDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionExpiryDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpiryDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	
	$("#showMoreBtn").toggle(
		function(){
			$("#actualCompanyDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#actualCompanyDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
});

</script>
