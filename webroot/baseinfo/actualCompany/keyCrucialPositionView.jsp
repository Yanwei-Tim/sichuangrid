<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
	<tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${location.organization.orgName}</td>
  </tr>
	<tr>
		<td class="title"><label>名称</label>
		</td>
		<td class="content" colspan="3"><span>${location.keyName }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>武警人数</label>
		</td>
		<td class="content"><span>${location.policeNumber}</span>
		</td>
		<td class="title"><label>方位</label>
		</td>
		<td class="content"><span>${location.position}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>重要设施</label>
		</td>
		<td class="content"><span>${location.importantFacilities }</span>
		</td>
		<td class="title"><label>护卫队人数</label>
		</td>
		<td class="content"><span>${location.convoyNumber }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>经济价值 &nbsp;(万元)</label>
		</td>
		<td class="content"><span>${location.economicValue}</span>
		</td>
		<td class="title"><label>保安人数</label>
		</td>
		<td class="content"><span>${location.securityNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>工作人员数</label>
		</td>
		<td class="content"><span>${location.staffNumber}</span>
		</td>
		<td class="title"><label>经警人数</label>
		</td>
		<td class="content"><span>${location.classicsAlarmNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>电话</label>
		</td>
		<td class="content"><span>${location.keyPhone}</span>
		</td>

		<td class="title"><label>具体位置</label>
		</td>
		<td class="content"><span>${location.location}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>负责人</label>
		</td>
		<td class="content"><span>${location.chief }</span>
		</td>
		<td class="title"><label>负责人身份证号码</label>
		</td>
		<td class="content" colspan="2"><span>${location.chiefIdNumber }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>有关安全防范标准</label>
		</td>
		<td class="content" colspan="5"><span>${location.relevantSafety}</span></td>	 
	</tr>
	<tr>
		<td class="title"><label>技防措施</label>
		</td>
		<td class="content"><span>${location.safetyMeasures}</span>
		</td>
		<td class="title"><label>人防措施</label>
		</td>
		<td class="content" colspan="2"><span>${location.civilMeasures}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>物防措施</label>
		</td>
		<td class="content"><span>${location.thingMeasures }</span>
		</td>
		<td class="title"><label>犬防措施</label>
		</td>
		<td class="content" colspan="2"><span>${location.dogMeasures}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>判定依据</label>
		</td>
		<td class="content"><span>${location.judgementBasis }</span>
		</td>

		<td class="title"><label>技防设施投入资金 &nbsp;(万元)</label>
		</td>
		<td class="content" colspan="2"><span>${location.safetyMeasuresFunds }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>保卫制度建立情况</label>
		</td>
		<td colspan="5" class="content"><span id="">${location.defendEstablishment}
		</span>
		</td>
	</tr>
</table>