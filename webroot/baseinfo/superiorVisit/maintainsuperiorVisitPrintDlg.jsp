<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="superiorVisitPrint" >
<input id="orgId" type="hidden" name="orgId" class="form-txt" value='${orgId}' />
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
			padding:0.3em 0.5em;
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
	
	<div class="mod_print" >
		<h1>${superiorVisit.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5" class="heightAuto"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%" >${superiorVisit.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%" >${superiorVisit.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%" ><input id="superiorVisit.gender.id" type="hidden" value="${superiorVisit.gender.id}"><label id="gender"></label></td>
          </tr>

		 <tr>
            <th class="th">户籍地</th>
            <td colspan="5">${superiorVisit.province}${superiorVisit.city}${superiorVisit.district}</td>
          </tr>	
		  <tr>
            <th class="th">户籍详址</th>
            <td colspan="3" >${superiorVisit.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td><fmt:formatDate value="${superiorVisit.birthday}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
           <tr>
            <th class="th">户籍派出所</th>
            <td colspan="3" >${superiorVisit.nativePoliceStation}</td>
            <td class="th">联系电话</td>
            <td>${superiorVisit.telephone}</td>
          </tr>
          <tr>
          <th class="th">常住地址</th>
            <td colspan="3" >${superiorVisit.currentlyAddress}</td>
            <td class="th">联系手机</td>
            <td>${superiorVisit.mobileNumber}</td>
          </tr>
          <tr>
          <th class="th">工作单位或就读学校</th>
            <td colspan="5" >${superiorVisit.workUnit}</td>
          </tr>
           <tr>
          <th class="th">上访原因</th>
            <td colspan="5" >${superiorVisit.attentionWhys}</td>
          </tr>
          <tr>
            <td class="th">上访状态</td>
            <td  colspan="5">	<input id="superiorVisit.superiorVisitState.id" type="hidden" value="${superiorVisit.superiorVisitState.id}">
					<label id="superiorVisitState"></label>
			</td>
          </tr>
           <tr>
		  <th class="th">备 注</th>
            <td colspan="5" >${superiorVisit.remark}</td>
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
				"organization.id":$("#orgId").val()
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
				"propertyDict.id":$("#superiorVisit\\.gender\\.id").val()
			},
			success:function(responseData){
				$("#gender").html(responseData.displayName);
			}
		});

		$.ajax({
			async: false,
			url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDomain.domainName":"上访状态",
				"propertyDict.id":$("#superiorVisit\\.superiorVisitState\\.id").val()==""?'-1':$("#superiorVisit\\.superiorVisitState\\.id").val()
			},
			success:function(responseData){
				if(responseData!=null){
					$("#superiorVisitState").html(responseData.displayName);
				}
			}
		});
});
</script>


