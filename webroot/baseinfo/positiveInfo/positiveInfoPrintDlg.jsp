<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div id="positiveInfoPrint" >
<input id="orgId" type="hidden" name="orgId" class="form-txt" value='${positiveInfos.organization.id}' />
<style type="text/css">
	.mod_print{
			margin:0 auto;
		}
		.mod_print h1{
			font-size:34px;
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
		<h1>${positiveInfos.name}信息</h1>
    	<div class="b">
    	<table>
          <tr>
            <th width="17%" class="th">所属网格</th>
            <td colspan="5"><label id="orgName"></label></td>
          </tr>
          <tr>
            <th class="th">姓名</th>
            <td width="19%">${positiveInfos.name}</td>
            <td width="12%" class="th">身份证号码</td>
            <td width="23%">${positiveInfos.idCardNo}</td>
            <td width="13%" class="th">性 别</td>
            <td width="16%"><label id="gender"></label><input id="positiveInfos.gender.id" type="hidden" value="${positiveInfos.gender.id}"></input></td>
          </tr>
          <tr>
            <th class="th">户籍地</th>
            <td colspan="5">${positiveInfos.province}${positiveInfos.city}${positiveInfos.district} </td>
          </tr>
          <tr>
            <th class="th">户籍详址</th>
            <td colspan="3">${positiveInfos.nativePlaceAddress}</td>
            <td class="th">出生日期</td>
            <td><fmt:formatDate value="${positiveInfos.birthday}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
          <tr>
            <th class="th">户籍派出所</th>
            <td colspan="3">${positiveInfos.nativePoliceStation}</td>
            <td class="th">联系电话</td>
            <td>${positiveInfos.telephone}</td>
          </tr>
          <tr>
            <th class="th">常住地址</th>
            <td colspan="3">${positiveInfos.currentlyAddress}</td>
            <td class="th">联系手机</td>
            <td>${positiveInfos.mobileNumber}</td>
          </tr>
          <tr>
            <th class="th">原职业</th>
            <td colspan="3"> <input id="positiveInfos.agoProfession.id" type="hidden" value="${positiveInfos.agoProfession.id}"> <label id="agoProfession"></label> </td>
            <td class="th">文化程度</td>
            <td><input id="positiveInfos.schooling.id" type="hidden" value="${positiveInfos.schooling.id}"> <label id="schooling"></label></td>
          </tr>
          <tr>
            <th class="th">是否累犯</th>
            <td colspan="3">${positiveInfos.isRepeat==1?'是':'否'}</td>
            <td class="th">人员类型</td>
            <td> <input id="positiveInfos.positiveInfosType.id" type="hidden" value="${positiveInfos.positiveInfosType.id}"> <label id="positiveInfosType"></label>  </td>
          </tr>
          <tr>
            <th class="th">劳教(服刑)场所</th>
            <td colspan="5">${positiveInfos.laborEduAddress}</td>
          </tr>
          <tr>
            <th class="th">原罪名</th>
            <td colspan="5">${positiveInfos.caseReason}</td>
          </tr>
          <tr>
            <th class="th">原判刑期</th>
            <td colspan="3">${positiveInfos.imprisonmentDate}</td>
            <td class="th">刑释日期</td>
            <td><fmt:formatDate value="${positiveInfos.releaseDate}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
          <tr>
            <th class="th">解教日期</th>
            <td colspan="3"><fmt:formatDate value="${positiveInfos.backDate}" type="date" pattern="yyyy-MM-dd" /></td>
            <td class="th">安置日期</td>
            <td><fmt:formatDate value="${positiveInfos.resettlementDate}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
          <tr>
            <th class="th">本年度是否重新犯罪</th>
            <td colspan="3">${positiveInfos.isCrime==true?'是':'否'}</td>
            <td class="th">重犯日期</td>
            <td><fmt:formatDate value="${positiveInfos.crimeDate}" type="date" pattern="yyyy-MM-dd" /></td>
          </tr>
          
          <tr>
            <th class="th">未安置原因</th>
            <td colspan="5">${positiveInfos.noResettlementReason}</td>
          </tr>
          <tr>
            <th class="th">备 注</th>
            <td colspan="5"><p>${positiveInfos.remark}</p>
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
			"propertyDict.id":$("#positiveInfos\\.gender\\.id").val()
		},
		success:function(responseData){
			$("#gender").html(responseData.displayName);
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"职业",
			"propertyDict.id":$("#positiveInfos\\.agoProfession\\.id").val()==""?'-1':$("#positiveInfos\\.agoProfession\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
			$("#agoProfession").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"文化程度",
			"propertyDict.id":$("#positiveInfos\\.schooling\\.id").val()==""?'-1':$("#positiveInfos\\.schooling\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
			$("#schooling").html(responseData.displayName);
			}
		}
	});

	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"刑释解教类型",
			"propertyDict.id":$("#positiveInfos\\.positiveInfosType\\.id").val()==""?'-1':$("#positiveInfos\\.positiveInfosType\\.id").val()
		},
		success:function(responseData){
			if(responseData!=null){
				$("#positiveInfosType").html(responseData.displayName);
			}
		}
	});

});
</script>


