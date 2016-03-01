<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input id="_imgUrl" type="hidden" name="population.imgUrl"
			value="${population.imgUrl}" />
<table width="200" class="tablelist">
	<tr>
		<td class="title"><label>所属网格</label>
		</td>
		<td colspan="3" class="content" id="optimalPopulationOrgName">${population.organization.orgName}</td>
		<td rowspan="7"><c:choose>
				<c:when
					test='${null!=population.imgUrl && not empty population.imgUrl}'>
					<img id="optimalImg" name="population.imgUrl"
						src="" width="148px"/>
				</c:when>
				<c:otherwise>
					<img id="img" name="population.imgUrl"
						src="${resource_path}/resource/images/head.png" width="148px"/>
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td class="title"><label>姓 名</label>
		</td>
		<td class="content"><span>${population.name}</span>
		</td>
		<td class="title"><label>身份证号码</label>
		</td>
		<td class="content"><span>${population.idCardNo}</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>曾用名/别名</label>
		</td>
		<td class="content"><span>${population.usedName}</span>
		</td>
		<td class="title"><label>性别</label>
		</td>
		<td class="content"><span><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@GENDER"
					defaultValue="${population.gender.id}" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>民族</label>
		</td>
		<td class="content"><span><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@NATION"
					defaultValue="${population.nation.id}" />
		</span>
		</td>
		<td class="title"><label>出生日期</label>
		</td>
		<td class="content"><span><fmt:formatDate
					value="${population.birthday}" type="date" pattern="yyyy-MM-dd" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>联系手机</label>
		</td>
		<td class="content"><span>${population.mobileNumber }</span>
		</td>
		<td class="title"><label>固定电话</label>
		</td>
		<td class="content"><span>${population.telephone }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>关注程度 </label>
		</td>
		<td class="content" colspan="3"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT"
					defaultValue="${population.attentionExtent.id}" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>优待证号 </label>
		</td>
		<td class="content"><span>${population.optimalCardNo }</span>
		</td>
		<td class="title"><label>优抚类型 </label>
		</td>
		<td class="content"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE"
					defaultValue="${population.optimalCardType.id}" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>劳动能力</label>
		</td>
		<td class="content"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY"
					defaultValue="${population.laborAbility.id}" />
		</span>
		</td>
		<td class="title"><label>生活能力</label>
		</td>
		<td colspan="2" class="content"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@VIABILITY"
					defaultValue="${population.abilityLiving.id}" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>就业情况</label>
		</td>
		<td class="content"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS"
					defaultValue="${population.employmentSituation.id}" />
		</span>
		</td>
		<td class="title"><label>供养情况</label>
		</td>
		<td colspan="2" class="content"><span id=""><pop:PropertyDictViewTag
					name="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS"
					defaultValue="${population.supportSituation.id}" />
		</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>月生活费 </label>
		</td>
		<td colspan="4" class="content"><span id="">${population.monthLivingExpenses}</span>(元)</td>
	</tr>
</table>
<script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#optimalImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>