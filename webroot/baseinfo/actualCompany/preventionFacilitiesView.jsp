<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
	<tr>
		<td class="title"><label>所属网格</label>
		</td>
		<td colspan="5" class="content" id="orgName">${location.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>设施名称</label>
		</td>
		<td class="content" colspan="5"><span>${location.facilitiesName }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>设施安装时间</label>
		</td>
		<td class="content"><span><s:date
					name="location.installationTime" format="yyyy-MM-dd" /> </span>
		</td>
		<td class="title"><label>设施安装位置</label>
		</td>
		<td class="content" colspan="3"><span>${location.installationPosition}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>其中含入侵探测器数量</label>
		</td>
		<td class="content" ><span>${location.intrusionDetectorNumber
				}</span>
		</td>
	    <td class="title"><label>能否输至公安图像中心</label>
		</td>
		<td class="content" colspan="3"><span><s:if test="location.canLose">是</s:if><s:else>否</s:else></span>
		</td>
	</tr>

	<tr>
		<td class="title"><label>报警设施连接部门</label>
		</td>
		<td class="content"><span>${location.connectedDepartment}</span>
		</td>
		<td class="title"><label>覆盖区域</label>
		</td>
		<td class="content" colspan="3"><span>${location.coverageArea}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>设施保存天数</label>
		</td>
		<td class="content"><span>${location.keepDays}</span>
		</td>

		<td class="title"><label>存储方式</label>
		</td>
		<td class="content" colspan="3"><span>${location.storageWays}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>其中含摄像机数量</label>
		</td>
		<td class="content"><span>${location.camerasNumber }</span>
		</td>
		<td class="title"><label>运行投入金额(万元)</label>
		</td>
		<td class="content" colspan="3"><span>${location.investmentAmount }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>配置描述</label>
		</td>
		<td class="content"  colspan="5"><span>${location.configurationDescribe}</span>
		</td>
	</tr>
</table>