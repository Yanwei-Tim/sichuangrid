<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<table width="200" class="tablelist">
	<tr>
		<td class="title"><label>所属网格</label>
		</td>
		<td colspan="3" class="content" id="orgName">${location.organization.orgName}</td>
		<td class="imagesTX" rowspan="7">
		<input id="_imgUrl" type="hidden" name="location.imgUrl" value="${location.imgUrl}"/>
		<s:if
				test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
				<img id="locationIMg" name="location.imgUrl"
					src="${path }${location.imgUrl}" width="148px" height="174px" />
			</s:if> <s:else>
				<img id="img" name="location.imgUrl"
					src="${path }/resource/images/head.png" width="148px"
					height="174px" />
			</s:else>
		</td>
	</tr>
	<tr>
		<td class="title"><label>名称</label>
		</td>
		<td colspan="3" class="content"><span>${location.companyName }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>从业人数</label>
		</td>
		<td class="content"><span>${location.employeesNum}</span>
		</td>
		<td class="title"><label>女职工数</label>
		</td>
		<td class="content"><span>${location.femaleWorkerNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>本市城镇职工数</label>
		</td>
		<td class="content"><span>${location.localTownNumber }</span>
		</td>
		<td class="title"><label>本市农村职工数</label>
		</td>
		<td class="content"><span>${location.localCountryNumber }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>外省市职工数</label>
		</td>
		<td class="content"><span>${location.otherTownNumber}</span>
		</td>
		<td class="title"><label>其它职工数</label>
		</td>
		<td class="content"><span>${location.otherNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>保证上岗人数</label>
		</td>
		<td class="content"><span>${location.workNumber}</span>
		</td>
		<td class="title"><label>标准工作时间人数</label>
		</td>
		<td class="content"><span>${location.standardWorkNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>不定时工作人数</label>
		</td>
		<td class="content"><span>${location.unLimitWorkNumber}</span>
		</td>

		<td class="title"><label>综合工作时间人数</label>
		</td>
		<td class="content"><span>${location.compositeWorkNumber}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>加班工资支付情况</label>
		</td>
		<td class="content"><span>${location.overtimePayCondition }</span>
		</td>
		<td class="title"><label>工资支付日期</label>
		</td>
		<td class="content"><span><s:date name="location.payMoneyDate" format="yyyy-MM-dd" /></span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>实际发放工资总额</label>
		</td>
		<td class="content"><span>${location.payTotalMoney}</span></td>	 
		<td class="title"><label>领取生活费人数</label>
		</td>
		<td class="content"><span>${location.getLivingMoneyNum}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>应签劳动合同人数</label>
		</td>
		<td class="content"><span>${location.shouldEmployeeNum}</span>
		</td>
		<td class="title"><label>未签劳动合同人数</label>
		</td>
		<td class="content"><span>${location.unEmployeeNum }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>未签劳动合同原因</label>
		</td>
		<td colspan="3" class="content"><span>${location.unEmployeeReason}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>是否有接受奖励情况</label></td>
	    <td class="content"><span><s:if test="location.isAcceptRewardCondition">是</s:if><s:else>否</s:else></span></td>
	    <td class="title"><label>是否有违法情况</label></td>
	    <td class="content"><span><s:if test="location.isBreakLaw">是</s:if><s:else>否</s:else></span></td>
    </tr>
    <tr>
		<td class="title"><label>是否有接受处罚情况</label></td>
	    <td class="content"><span><s:if test="location.isAcceptPunish">是</s:if><s:else>否</s:else></span></td>
	    <td class="title"><label>是否使用童工</label></td>
	    <td class="content"><span><s:if test="location.isUserdChild">是</s:if><s:else>否</s:else></span></td>
    </tr>
    <tr>
	    <td class="title"><label>是否遵守女职工未成年保护规定</label></td>
	    <td class="content"><span><s:if test="location.isObservedOrder">是</s:if><s:else>否</s:else></span></td>
    </tr>
</table>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locationIMg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>