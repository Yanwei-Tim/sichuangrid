<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
    <div id="dangerousGoodsPractitionerPrint">
    <input id="organizationId"	type="hidden" name="organization.id" value="${organization.id}" />
    <style type="text/css">
	.mod_print{
			margin:0 auto;
		}
		.mod_print h1{
			font-size:30px;
			text-align:center;
			line-height:2em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.2em 0.3em;
			border:1px solid #333;
		}
		.mod_print .b table th{
			text-align:right;
		}
		.mod_print .b table .th{
			text-align:center;
			font-weight:bold;
		}
	</style>
	<div class="mod_print">
		<h1>${dangerousGoodsPractitioner.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5" class="heightAuto"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%" >${dangerousGoodsPractitioner.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${dangerousGoodsPractitioner.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%" ><input id="dangerousGoodsPractitioner.gender.id" type="hidden" value="${dangerousGoodsPractitioner.gender.id}"><label id="gender"></label></td>
          </tr>
          <tr>
            <th class="th">户籍地</th>
            <td colspan="5">${dangerousGoodsPractitioner.province}${dangerousGoodsPractitioner.city}${dangerousGoodsPractitioner.district} </td>
          </tr>
          <tr>
            <th class="th">户籍详址</th>
            <td colspan="3" >${dangerousGoodsPractitioner.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td><s:date name="dangerousGoodsPractitioner.birthday" format="yyyy-MM-dd"/></td>
          </tr>
          <tr>
            <th class="th">户籍派出所</th>
            <td colspan="3" >${dangerousGoodsPractitioner.nativePoliceStation}</td>
            <td class="th">联系电话</td>
            <td>${dangerousGoodsPractitioner.telephone}</td>
          </tr>
          <tr>
            <th class="th">常住地址</th>
            <td colspan="3" >${dangerousGoodsPractitioner.currentlyAddress}</td>
            <td class="th">联系手机</td>
            <td>${dangerousGoodsPractitioner.mobileNumber}</td>
          </tr>
          
          <tr>
            <th class="th">从业资格证书</th>
            <td width="19%" >${dangerousGoodsPractitioner.workingCertificate}</td>
            <td width="12%" class="th">从业资格证书号</td>
            <td width="23%" >${dangerousGoodsPractitioner.workingCertificateNo}</td>
            <td width="13%" class="th">有效日期</td>
            <td width="16%" ><s:date name="dangerousGoodsPractitioner.availableDate" format="yyyy-MM-dd"/></td>
          </tr>
           <tr>
            <th class="th">工作单位或就读学校</th>
            <td colspan="3" >${dangerousGoodsPractitioner.workUnit}</td>
            <td class="th">危险从业类别</td>
            <td ><input id="dangerousGoodsPractitioner.dangerousWorkingType.id" type="hidden" value="${dangerousGoodsPractitioner.dangerousWorkingType.id}"><label id="dangerousWorkingType"></label></td>
          </tr>
          
             <tr>
            <th class="th">法人代表</th>
            <td width="19%" >${dangerousGoodsPractitioner.legalPerson}</td>
            <td width="12%" class="th">法人联系手机</td>
            <td width="23%" >${dangerousGoodsPractitioner.legalPersonMobileNumber}</td>
            <td width="13%" class="th">法人联系电话</td>
            <td width="16%" >${dangerousGoodsPractitioner.legalPersonTelephone}</td>
          </tr>
          
          <tr>
            <th class="th">备 注</th>
            <td colspan="5" >${dangerousGoodsPractitioner.remark}<p></p>
            <p></p></td>
          </tr>
        </table>
		</div>
		</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#orgName").html(responseData);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":$("#dangerousGoodsPractitioner\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"危险从业类别",
			"propertyDict.id":$("#dangerousGoodsPractitioner\\.dangerousWorkingType\\.id").val()==""?'-1':$("#dangerousGoodsPractitioner\\.dangerousWorkingType\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#dangerousWorkingType").html(responseData.displayName);
			}
		}
	});
	
});
</script>

