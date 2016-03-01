<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" id="securityOfImportPrint">
<style>
	table.tb {
		font-size:14px;
	}
	table.tb td {
		border:1px solid gray;
		padding-left:3px;
		padding-right:3px;
		background-color:#D5D5D5;
	}
	table.tb td.head{
		text-align:center;
		font-weight:bold;
		background-color:#ffffff;
		font-size:15px;
	}
	table.tb td.bottom{
		border:0px;
		background-color:#ffffff;
	}
	table.tb td.order{
		text-align:center;
		height:26px;
		background-color:#ffffff;
	}
	table.tb td.label{
		text-align:left;
		background-color:#ffffff;
		font-size:15px;
	}
	table.tb td.inputClick{
	background-color:#ffffff;
	}
</style>
<table id='tb' class="tb" border=0 cellpadding=0 cellspacing=0 style='margin:10px 10px;border-collapse:collapse;table-layout:fixed;width:690pt'>
	<s:if test='"add".equals(mode)'>
	<tr>
		<td colspan=9 height=44 id="reportName" class=head style='text-align:center;border:0px;height:33.0pt;font-size:25px; font-weight:bold;'>${dailyYear.name}年<span id="selectedMonthForDis"></span>${organization.orgName}矛盾纠纷排查情况表</td>
	</tr>
	</s:if>
	<s:else>
		<tr>
			<td colspan=9 height=44 id="reportName" class=head style='text-align:center;border:0px;height:33.0pt;font-size:25px; font-weight:bold;'>${disputEsexamine.name}</td>
		</tr>
		</s:else>
	<tr>
		<td colspan=5 class=head style='height:32.25pt;border:0px;border-bottom:1px solid gray;' id="areacode">
		填报单位（盖章）：${organization.orgName}
		</td>
		<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@MONTH_REPORT">
			<td colspan=4 class=head style="border:0px; border-bottom:1px solid gray; text-align:right;margin-right:20px;">填报月份：
				<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
					<s:iterator value="dateMap" id="date" >
					    <s:property value="#date"/>
						<option value='<s:property value="key"/>'><s:property value="value"/></option>
					</s:iterator>
				</select>
			</td>
	  	</s:if>
	  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@QUARTER_REPORT">
		  	<td colspan=4 class=head style="border:0px; border-bottom:1px solid gray; text-align:right;margin-right:20px;">填报季度：
			  	<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
					<s:iterator value="dateMap" id="date" >
					    <s:property value="#date"/>
						<option value='<s:property value="key"/>'><s:property value="value"/></option>
					</s:iterator>
				</select>
			</td>
	  	</s:if>
	  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@SEMIYEARLY_REPORT">
		  	<td colspan=4 class=head style="border:0px; border-bottom:1px solid gray; text-align:right;margin-right:20px;">填报半年度：
			  	<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
					<option value="1">上半年</option>
					<option value="2">下半年</option>
				</select>
			</td>
	  	</s:if>
	  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
		  	<td colspan=4 class=head style="border:0px; border-bottom:1px solid gray; text-align:right;margin-right:20px;">填报年度：
			  	<select id="reportTime" disabled="disabled">
				</select>
			</td>
	  	</s:if>
	</tr>
	<tr  height=43 style='height:32.25pt'>
		<td colspan=4 rowspan=2 height=80 class=head >内容</td>
		<td colspan=5 class=head >层<span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>级</td>
	</tr>
 
	<tr>
		<td class=head >县市区</td>
		<td width="339" class=head >乡镇街道</td>
		<td width="339" class=head  >村居</td>
		<td width="339" class=head  >小计</td>
		<td width="347" class=head  >年度累计</td>
	</tr>
	
	
	<tr id='c_0_' >
		<td width="32" height=26 class=order >1</td>
		<td colspan=2 rowspan=5 class=label style='width:213pt'>总<span
		style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>况</td>
		<td class=label >排查数</td>
		<td name="totalCountyIssuecount" finishRateDenominator="totalCountyFinishrate-denominator" dealRateDenominator="totalCountyDealrate-denominator" dealRateDenominator="totalCountyDealrate-denominator" class="toDataJosn" countName="totalUnIssuecount-data" countTarget="totalUnIssuecount">${disputEsexamine.totalCountyIssuecount}</td>
		<td name="totalTwonIssuecount" finishRateDenominator="totalTwonFinishrate-denominator" dealRateDenominator="totalTwonDealrate-denominator" class="toDataJosn" countName="totalUnIssuecount-data" countTarget="totalUnIssuecount">${disputEsexamine.totalTwonIssuecount}</td>
		<td name="totalVilIssuecount" finishRateDenominator="totalVilFinishrate-denominator" dealRateDenominator="totalVilDealrate-denominator" class="toDataJosn" countName="totalUnIssuecount-data" countTarget="totalUnIssuecount">${disputEsexamine.totalVilIssuecount}</td>
		<td name="totalUnIssuecount" class="toDataJosn" countName="totalUnIssuecount">${disputEsexamine.totalUnIssuecount}</td>
		<td name="totalYearIssuecount" class="toDataJosn">${disputEsexamine.totalYearIssuecount}</td>
	</tr>
	<tr id='c_0_' >
		<td height=26 class=order >2</td>
		<td class=label >调处数</td>
		<td name="totalCountyDealcount" dealRateMolecule="totalCountyDealrate-molecule" class="toDataJosn" countName="totalUnDealcount-data" countTarget="totalUnDealcount">${disputEsexamine.totalCountyDealcount}</td>
		<td name="totalTwonDealcount" dealRateMolecule="totalTwonDealrate-molecule" class="toDataJosn" countName="totalUnDealcount-data" countTarget="totalUnDealcount">${disputEsexamine.totalTwonDealcount}</td>
		<td name="totalVilDealcount" dealRateMolecule="totalVilDealrate-molecule" class="toDataJosn" countName="totalUnDealcount-data" countTarget="totalUnDealcount">${disputEsexamine.totalVilDealcount}</td>
		<td name="totalUnDealcount" class="toDataJosn" countName="totalUnDealcount">${disputEsexamine.totalUnDealcount}</td>
		<td name="totalYearDealcount" class="toDataJosn">${disputEsexamine.totalYearDealcount}</td>
	</tr>
	<tr id='c_0_' >
		<td height=26 class=order >3</td>
		<td class=label >调处率</td>
		<td name="totalCountyDealrate" countName="totalUnDealrate-data" class="toDataJosn" countTarget="totalUnDealrate">${disputEsexamine.totalCountyDealrate}</td>
		<td name="totalTwonDealrate" countName="totalUnDealrate-data" class="toDataJosn" countTarget="totalUnDealrate">${disputEsexamine.totalTwonDealrate}</td>
		<td name="totalVilDealrate" countName="totalUnDealrate-data" class="toDataJosn" countTarget="totalUnDealrate">${disputEsexamine.totalVilDealrate}</td>
		<td name="totalUnDealrate" countName="totalUnDealrate" class="toDataJosn">${disputEsexamine.totalUnDealrate}</td>
		<td name="totalYearDealrate" class="toDataJosn">${disputEsexamine.totalYearDealrate}</td>
	</tr>
	<tr id='c_0_' >
		<td height=26 class=order >4</td>
		<td class=label >成功数</td>
		<td name="totalCountyFinishcount" yearCountTarget="totalYearFinishcount" finishRateMolecule="totalCountyFinishrate-molecule" class="toDataJosn"  countName="totalUnFinishcount-data" countTarget="totalUnFinishcount">${disputEsexamine.totalCountyFinishcount}</td>
		<td name="totalTwonFinishcount" yearCountTarget="totalYearFinishcount" finishRateMolecule="totalTwonFinishrate-molecule" class="toDataJosn"  countName="totalUnFinishcount-data" countTarget="totalUnFinishcount">${disputEsexamine.totalTwonFinishcount}</td>
		<td name="totalVilFinishcount" yearCountTarget="totalYearFinishcount" finishRateMolecule="totalVilFinishrate-molecule" class="toDataJosn"  countName="totalUnFinishcount-data" countTarget="totalUnFinishcount">${disputEsexamine.totalVilFinishcount}</td>
		<td name="totalUnFinishcount" class="toDataJosn" countName="totalUnFinishcount">${disputEsexamine.totalUnFinishcount}</td>
		<td name="totalYearFinishcount" class="toDataJosn">${disputEsexamine.totalYearFinishcount}</td>
	</tr>
	<tr id='c_0_' >
		<td height=26 class=order >5</td>
		<td class=label >成功率</td>
		<td name="totalCountyFinishrate" countName="totalUnFinishrate-data" class="toDataJosn" countTarget="totalUnFinishrate">${disputEsexamine.totalCountyFinishrate}</td>
		<td name="totalTwonFinishrate" countName="totalUnFinishrate-data" class="toDataJosn" countTarget="totalUnFinishrate">${disputEsexamine.totalTwonFinishrate}</td>
		<td name="totalVilFinishrate" countName="totalUnFinishrate-data" class="toDataJosn" countTarget="totalUnFinishrate">${disputEsexamine.totalVilFinishrate}</td>
		<td name="totalUnFinishrate" countName="totalUnFinishrate" class="toDataJosn">${disputEsexamine.totalUnFinishrate}</td>
		<td name="totalYearFinishrate" class="toDataJosn">${disputEsexamine.totalYearFinishrate}</td>
	</tr>
	<tr id='c_406_' >
		<td height=26 class=order >6</td>
		<td colspan=2 rowspan=2 class=label>民族宗教矛盾纠纷</td>
		<td class=label  >排查数</td>
		<td name="religionCountyIssuecount" yearCountTarget="religionYearIssuecount" class="toDataJosn" countName="religionUnIssuecount-data" countTarget="religionUnIssuecount">${disputEsexamine.religionCountyIssuecount}</td>
		<td name="religionTwonIssuecount" yearCountTarget="religionYearIssuecount" class="toDataJosn" countName="religionUnIssuecount-data" countTarget="religionUnIssuecount">${disputEsexamine.religionTwonIssuecount}</td>
		<td name="religionVilIssuecount" yearCountTarget="religionYearIssuecount" class="toDataJosn" countName="religionUnIssuecount-data" countTarget="religionUnIssuecount">${disputEsexamine.religionVilIssuecount}</td>
		<td name="religionUnIssuecount" countName="religionUnIssuecount" class="toDataJosn">${disputEsexamine.religionUnIssuecount}</td>
		<td name="religionYearIssuecount" class="toDataJosn">${disputEsexamine.religionYearIssuecount}</td>
	</tr>
	<tr id='c_406_' >
		<td height=26 class=order >7</td>
		<td class=label >调处数</td>
		<td name="religionCountyDealcount" yearCountTarget="religionYearDealcount" class="toDataJosn" countName="religionUnDealcount-data" countTarget="religionUnDealcount">${disputEsexamine.religionCountyDealcount}</td>
		<td name="religionTwonDealcount" yearCountTarget="religionYearDealcount" class="toDataJosn" countName="religionUnDealcount-data" countTarget="religionUnDealcount">${disputEsexamine.religionTwonDealcount}</td>
		<td name="religionVilDealcount" yearCountTarget="religionYearDealcount" class="toDataJosn" countName="religionUnDealcount-data" countTarget="religionUnDealcount">${disputEsexamine.religionVilDealcount}</td>
		<td name="religionUnDealcount" class="toDataJosn" countName="religionUnDealcount">${disputEsexamine.religionUnDealcount}</td>
		<td name="religionYearDealcount" class="toDataJosn">${disputEsexamine.religionYearDealcount}</td>
	</tr>
	<tr id='c_407_' >
		<td height=26 class=order >8</td>
		<td colspan=2 rowspan=2 class=label>军转干部、复原退伍军人安置引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="soldierCountyIssuecount" yearCountTarget="soldierYearIssuecount" class="toDataJosn" countName="soldierUnIssuecount-data" countTarget="soldierUnIssuecount">${disputEsexamine.soldierCountyIssuecount}</td>
		<td name="soldierTwonIssuecount" yearCountTarget="soldierYearIssuecount" class="toDataJosn" countName="soldierUnIssuecount-data" countTarget="soldierUnIssuecount">${disputEsexamine.soldierTwonIssuecount}</td>
		<td name="soldierVilIssuecount" yearCountTarget="soldierYearIssuecount" class="toDataJosn" countName="soldierUnIssuecount-data" countTarget="soldierUnIssuecount">${disputEsexamine.soldierVilIssuecount}</td>
		<td name="soldierUnIssuecount" class="toDataJosn" countName="soldierUnIssuecount">${disputEsexamine.soldierUnIssuecount}</td>
		<td name="soldierYearIssuecount" class="toDataJosn">${disputEsexamine.soldierYearIssuecount}</td>
	</tr>
	<tr id='c_407_' >
		<td height=26 class=order >9</td>
		<td class=label >调处数</td>
		<td name="soldierCountyDealcount" yearCountTarget="soldierYearDealcount" class="toDataJosn"  countName="soldierUnDealcount-data" countTarget="soldierUnDealcount">${disputEsexamine.soldierCountyDealcount}</td>
		<td name="soldierTwonDealcount" yearCountTarget="soldierYearDealcount" class="toDataJosn"  countName="soldierUnDealcount-data" countTarget="soldierUnDealcount">${disputEsexamine.soldierTwonDealcount}</td>
		<td name="soldierVilDealcount" yearCountTarget="soldierYearDealcount" class="toDataJosn"  countName="soldierUnDealcount-data" countTarget="soldierUnDealcount">${disputEsexamine.soldierVilDealcount}</td>
		<td name="soldierUnDealcount" class="toDataJosn" countName="soldierUnDealcount">${disputEsexamine.soldierUnDealcount}</td>
		<td name="soldierYearDealcount" class="toDataJosn">${disputEsexamine.soldierYearDealcount}</td>
	</tr>
	<tr id='c_408_' >
		<td height=26 class=order >10</td>
		<td colspan=2 rowspan=2 class=label>征地拆迁安置引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="removeCountyIssuecount" yearCountTarget="removeYearIssuecount" class="toDataJosn"   countName="removeUnIssuecount-data" countTarget="removeUnIssuecount">${disputEsexamine.removeCountyIssuecount}</td>
		<td name="removeTwonIssuecount" yearCountTarget="removeYearIssuecount" class="toDataJosn"  countName="removeUnIssuecount-data" countTarget="removeUnIssuecount">${disputEsexamine.removeTwonIssuecount}</td>
		<td name="removeVilIssuecount" yearCountTarget="removeYearIssuecount" class="toDataJosn"   countName="removeUnIssuecount-data" countTarget="removeUnIssuecount">${disputEsexamine.removeVilIssuecount}</td>
		<td name="removeUnIssuecount" class="toDataJosn" countName="removeUnIssuecount">${disputEsexamine.removeUnIssuecount}</td>
		<td name="removeYearIssuecount" class="toDataJosn">${disputEsexamine.removeYearIssuecount}</td>
	</tr>


	
	<tr id='c_408_' >
		<td height=26 class=order >11</td>
		<td class=label >调处数</td>
		<td name="removeCountyDealcount" yearCountTarget="removeYearDealcount" countName="removeUnDealcount-data" countTarget="removeUnDealcount" class="toDataJosn">${disputEsexamine.removeCountyDealcount}</td>
		<td name="removeTwonDealcount" yearCountTarget="removeYearDealcount" countName="removeUnDealcount-data" countTarget="removeUnDealcount" class="toDataJosn">${disputEsexamine.removeTwonDealcount}</td>
		<td name="removeVilDealcount" yearCountTarget="removeYearDealcount" countName="removeUnDealcount-data" countTarget="removeUnDealcount" class="toDataJosn">${disputEsexamine.removeVilDealcount}</td>
		<td name="removeUnDealcount" class="toDataJosn" countName="removeUnDealcount">${disputEsexamine.removeUnDealcount}</td>
		<td name="removeYearDealcount" class="toDataJosn">${disputEsexamine.removeYearDealcount}</td>
	</tr>
	<tr id='c_409_' >
		<td height=26 class=order >12</td>
		<td colspan=2 rowspan=2 class=label>建筑工程质量、物业管理等问题引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="assetsCountyIssuecount" yearCountTarget="assetsYearIssuecount" countName="assetsUnIssuecount-data" countTarget="assetsUnIssuecount" class="toDataJosn">${disputEsexamine.assetsCountyIssuecount}</td>
		<td name="assetsTwonIssuecount" yearCountTarget="assetsYearIssuecount" countName="assetsUnIssuecount-data" countTarget="assetsUnIssuecount" class="toDataJosn">${disputEsexamine.assetsTwonIssuecount}</td>
		<td name="assetsVilIssuecount" yearCountTarget="assetsYearIssuecount" countName="assetsUnIssuecount-data" countTarget="assetsUnIssuecount" class="toDataJosn">${disputEsexamine.assetsVilIssuecount}</td>
		<td name="assetsUnIssuecount" class="toDataJosn" countName="assetsUnIssuecount" >${disputEsexamine.assetsUnIssuecount}</td>
		<td name="assetsYearIssuecount" class="toDataJosn">${disputEsexamine.assetsYearIssuecount}</td>
	</tr>
	<tr id='c_409_' >
		<td height=26 class=order >13</td>
		<td class=label >调处数</td>
		<td name="assetsCountyDealcount" yearCountTarget="assetsYearDealcount" countName="assetsUnDealcount-data" countTarget="assetsUnDealcount" class="toDataJosn">${disputEsexamine.assetsCountyDealcount}</td>
		<td name="assetsTwonDealcount" yearCountTarget="assetsYearDealcount" countName="assetsUnDealcount-data" countTarget="assetsUnDealcount" class="toDataJosn">${disputEsexamine.assetsTwonDealcount}</td>
		<td name="assetsVilDealcount" yearCountTarget="assetsYearDealcount" countName="assetsUnDealcount-data" countTarget="assetsUnDealcount" class="toDataJosn">${disputEsexamine.assetsVilDealcount}</td>
		<td name="assetsUnDealcount" class="toDataJosn" countName="assetsUnDealcount" >${disputEsexamine.assetsUnDealcount}</td>
		<td name="assetsYearDealcount" class="toDataJosn">${disputEsexamine.assetsYearDealcount}</td>
	</tr>
	<tr id='c_410_' >
		<td height=26 class=order >14</td>
		<td colspan=2 rowspan=2 class=label>土地矿产山林水利界限权属纠纷</td>
		<td class=label >排查数</td>
		<td name="landBoundariesCountyIssuecount" yearCountTarget="landBoundariesYearIssuecount" countName="landBoundariesUnIssuecount-data" countTarget="landBoundariesUnIssuecount" class="toDataJosn">${disputEsexamine.landBoundariesCountyIssuecount}</td>
		<td name="landBoundariesTwonIssuecount" yearCountTarget="landBoundariesYearIssuecount" countName="landBoundariesUnIssuecount-data" countTarget="landBoundariesUnIssuecount" class="toDataJosn">${disputEsexamine.landBoundariesTwonIssuecount}</td>
		<td name="landBoundariesVilIssuecount" yearCountTarget="landBoundariesYearIssuecount" countName="landBoundariesUnIssuecount-data" countTarget="landBoundariesUnIssuecount" class="toDataJosn">${disputEsexamine.landBoundariesVilIssuecount}</td>
		<td name="landBoundariesUnIssuecount" class="toDataJosn" countName="landBoundariesUnIssuecount" >${disputEsexamine.landBoundariesUnIssuecount}</td>
		<td name="landBoundariesYearIssuecount" class="toDataJosn">${disputEsexamine.landBoundariesYearIssuecount}</td>
	</tr>
	<tr id='c_410_' >
		<td height=26 class=order >15</td>
		<td class=label >调处数</td>
		<td name="landBoundariesCountyDealcount" yearCountTarget="landBoundariesYearDealcount" countName="landBoundariesUnDealcount-data" countTarget="landBoundariesUnDealcount" class="toDataJosn" >${disputEsexamine.landBoundariesCountyDealcount}</td>
		<td name="landBoundariesTwonDealcount" yearCountTarget="landBoundariesYearDealcount" countName="landBoundariesUnDealcount-data" countTarget="landBoundariesUnDealcount" class="toDataJosn">${disputEsexamine.landBoundariesTwonDealcount}</td>
		<td name="landBoundariesVilDealcount" yearCountTarget="landBoundariesYearDealcount" countName="landBoundariesUnDealcount-data" countTarget="landBoundariesUnDealcount" class="toDataJosn">${disputEsexamine.landBoundariesVilDealcount}</td>
		<td name="landBoundariesUnDealcount" class="toDataJosn" countName="landBoundariesUnDealcount" >${disputEsexamine.landBoundariesUnDealcount}</td>
		<td name="landBoundariesYearDealcount" class="toDataJosn" >${disputEsexamine.landBoundariesYearDealcount}</td>
	</tr>
	<tr id='c_411_' >
		<td height=26 class=order >16</td>
		<td colspan=2 rowspan=2 class=label>经济活动引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="economyCountyIssuecount" yearCountTarget="economyYearIssuecount" countName="economyUnIssuecount-data" countTarget="economyUnIssuecount" class="toDataJosn">${disputEsexamine.economyCountyIssuecount}</td>
		<td name="economyTwonIssuecount" yearCountTarget="economyYearIssuecount" countName="economyUnIssuecount-data" countTarget="economyUnIssuecount" class="toDataJosn">${disputEsexamine.economyTwonIssuecount}</td>
		<td name="economyVilIssuecount" yearCountTarget="economyYearIssuecount" countName="economyUnIssuecount-data" countTarget="economyUnIssuecount" class="toDataJosn">${disputEsexamine.economyVilIssuecount}</td>
		<td name="economyUnIssuecount" class="toDataJosn" countName="economyUnIssuecount" >${disputEsexamine.economyUnIssuecount}</td>
		<td name="economyYearIssuecount" class="toDataJosn">${disputEsexamine.economyYearIssuecount}</td>
	</tr>
	<tr id='c_411_' >
		<td height=26 class=order >17</td>
		<td class=label >调处数</td>
		<td name="economyCountyDealcount" yearCountTarget="economyYearDealcount" countName="economyUnDealcount-data" countTarget="economyUnDealcount" class="toDataJosn" >${disputEsexamine.economyCountyDealcount}</td>
		<td name="economyTwonDealcount" yearCountTarget="economyYearDealcount" countName="economyUnDealcount-data" countTarget="economyUnDealcount" class="toDataJosn" >${disputEsexamine.economyTwonDealcount}</td>
		<td name="economyVilDealcount" yearCountTarget="economyYearDealcount" countName="economyUnDealcount-data" countTarget="economyUnDealcount" class="toDataJosn" >${disputEsexamine.economyVilDealcount}</td>
		<td name="economyUnDealcount" class="toDataJosn" countName="economyUnDealcount">${disputEsexamine.economyUnDealcount}</td>
		<td name="economyYearDealcount" class="toDataJosn">${disputEsexamine.economyYearDealcount}</td>
	</tr>
	<tr id='c_412_' >
		<td height=26 class=order >18</td>
		<td colspan=2 rowspan=2 class=label>劳资纠纷</td>
		<td class=label >排查数</td>
		<td name="labourCountyIssuecount" yearCountTarget="labourYearIssuecount" countName="labourUnIssuecount-data" countTarget="labourUnIssuecount" class="toDataJosn">${disputEsexamine.labourCountyIssuecount}</td>
		<td name="labourTwonIssuecount" yearCountTarget="labourYearIssuecount" countName="labourUnIssuecount-data" countTarget="labourUnIssuecount" class="toDataJosn">${disputEsexamine.labourTwonIssuecount}</td>
		<td name="labourVilIssuecount" yearCountTarget="labourYearIssuecount" countName="labourUnIssuecount-data" countTarget="labourUnIssuecount" class="toDataJosn">${disputEsexamine.labourVilIssuecount}</td>
		<td name="labourUnIssuecount" class="toDataJosn" countName="labourUnIssuecount">${disputEsexamine.labourUnIssuecount}</td>
		<td name="labourYearIssuecount" class="toDataJosn">${disputEsexamine.labourYearIssuecount}</td>
	</tr>
	<tr id='c_412_' >
		<td height=26 class=order >19</td>
		<td class=label >调处数</td>
		<td name="labourCountyDealcount" yearCountTarget="labourYearDealcount" countName="labourUnDealcount-data" countTarget="labourUnDealcount" class="toDataJosn">${disputEsexamine.labourCountyDealcount}</td>
		<td name="labourTwonDealcount" yearCountTarget="labourYearDealcount" countName="labourUnDealcount-data" countTarget="labourUnDealcount" class="toDataJosn">${disputEsexamine.labourTwonDealcount}</td>
		<td name="labourVilDealcount" yearCountTarget="labourYearDealcount" countName="labourUnDealcount-data" countTarget="labourUnDealcount" class="toDataJosn">${disputEsexamine.labourVilDealcount}</td>
		<td name="labourUnDealcount" class="toDataJosn" countName="labourUnDealcount" >${disputEsexamine.labourUnDealcount}</td>
		<td name="labourYearDealcount" class="toDataJosn">${disputEsexamine.labourYearDealcount}</td>
	</tr>
	<tr id='c_413_' >
		<td height=26 class=order >20</td>
		<td colspan=2 rowspan=2 class=label>企业改制引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="ltdCountyIssuecount" yearCountTarget="ltdYearIssuecount" countName="ltdUnIssuecount-data" countTarget="ltdUnIssuecount" class="toDataJosn">${disputEsexamine.ltdCountyIssuecount}</td>
		<td name="ltdTwonIssuecount" yearCountTarget="ltdYearIssuecount" countName="ltdUnIssuecount-data" countTarget="ltdUnIssuecount" class="toDataJosn">${disputEsexamine.ltdTwonIssuecount}</td>
		<td name="ltdVilIssuecount" yearCountTarget="ltdYearIssuecount" countName="ltdUnIssuecount-data" countTarget="ltdUnIssuecount" class="toDataJosn">${disputEsexamine.ltdVilIssuecount}</td>
		<td name="ltdUnIssuecount" class="toDataJosn" countName="ltdUnIssuecount" >${disputEsexamine.ltdUnIssuecount}</td>
		<td name="ltdYearIssuecount" class="toDataJosn">${disputEsexamine.ltdYearIssuecount}</td>
	</tr>
	
	
	<tr id='c_413_' >
		<td height=26 class=order >21</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="ltdYearDealcount" name="ltdCountyDealcount" countName="ltdUnDealcount-data" countTarget="ltdUnDealcount">${disputEsexamine.ltdCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="ltdYearDealcount" name="ltdTwonDealcount" countName="ltdUnDealcount-data" countTarget="ltdUnDealcount">${disputEsexamine.ltdTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="ltdYearDealcount" name="ltdVilDealcount" countName="ltdUnDealcount-data" countTarget="ltdUnDealcount">${disputEsexamine.ltdVilDealcount}</td>
		<td class="toDataJosn" name="ltdUnDealcount" countName="ltdUnDealcount">${disputEsexamine.ltdUnDealcount}</td>
		<td class="toDataJosn" name="ltdYearDealcount">${disputEsexamine.ltdYearDealcount}</td>
	</tr>
	<tr id='c_414_' >
		<td height=26 class=order >22</td>
		<td colspan=2 rowspan=2 class=label>环境污染、生态破坏引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="environYearIssuecount" name="environCountyIssuecount" countName="environUnIssuecount-data" countTarget="environUnIssuecount">${disputEsexamine.environCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="environYearIssuecount" name="environTwonIssuecount" countName="environUnIssuecount-data" countTarget="environUnIssuecount">${disputEsexamine.environTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="environYearIssuecount" name="environVilIssuecount" countName="environUnIssuecount-data" countTarget="environUnIssuecount">${disputEsexamine.environVilIssuecount}</td>
		<td class="toDataJosn" name="environUnIssuecount" countName="environUnIssuecount">${disputEsexamine.environUnIssuecount}</td>
		<td class="toDataJosn" name="environYearIssuecount">${disputEsexamine.environYearIssuecount}</td>
	</tr>
	<tr id='c_414_' >
		<td height=26 class=order >23</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="environYearDealcount" name="environCountyDealcount" countName="environUnDealcount-data" countTarget="environUnDealcount">${disputEsexamine.environCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="environYearDealcount" name="environTwonDealcount" countName="environUnDealcount-data" countTarget="environUnDealcount">${disputEsexamine.environTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="environYearDealcount" name="environVilDealcount" countName="environUnDealcount-data" countTarget="environUnDealcount">${disputEsexamine.environVilDealcount}</td>
		<td id='c_414_99_dealcount' class="toDataJosn" name="environUnDealcount" countName="environUnDealcount">${disputEsexamine.environUnDealcount}</td>
		<td id='c_414_100_dealcount' class="toDataJosn" name="environYearDealcount">${disputEsexamine.environYearDealcount}</td>
	</tr>
	<tr id='c_415_' >
		<td height=26 class=order >24</td>
		<td colspan=2 rowspan=2 class=label>司法活动引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="judicialYearIssuecount" name="judicialCountyIssuecount" countName="judicialUnIssuecount-data" countTarget="judicialUnIssuecount">${disputEsexamine.judicialCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="judicialYearIssuecount" name="judicialTwonIssuecount" countName="judicialUnIssuecount-data" countTarget="judicialUnIssuecount">${disputEsexamine.judicialTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="judicialYearIssuecount" name="judicialVilIssuecount" countName="judicialUnIssuecount-data" countTarget="judicialUnIssuecount">${disputEsexamine.judicialVilIssuecount}</td>
		<td id='c_415_99_issuecount' class="toDataJosn" name="judicialUnIssuecount" countName="judicialUnIssuecount">${disputEsexamine.judicialUnIssuecount}</td>
		<td id='c_415_100_issuecount' class="toDataJosn" name="judicialYearIssuecount">${disputEsexamine.judicialYearIssuecount}</td>
	</tr>
	<tr id='c_415_' >
		<td height=26 class=order >25</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="judicialYearDealcount" name="judicialCountyDealcount" countName="judicialUnDealcount-data" countTarget="judicialUnDealcount">${disputEsexamine.judicialCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="judicialYearDealcount" name="judicialTwonDealcount" countName="judicialUnDealcount-data" countTarget="judicialUnDealcount">${disputEsexamine.judicialTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="judicialYearDealcount" name="judicialVilDealcount" countName="judicialUnDealcount-data" countTarget="judicialUnDealcount">${disputEsexamine.judicialVilDealcount}</td>
		<td id='c_415_99_dealcount' class="toDataJosn" name="judicialUnDealcount" countName="judicialUnDealcount">${disputEsexamine.judicialUnDealcount}</td>
		<td id='c_415_100_dealcount' class="toDataJosn" name="judicialYearDealcount">${disputEsexamine.judicialYearDealcount}</td>
	</tr>
	<tr id='c_416_' >
		<td height=26 class=order >26</td>
		<td colspan=2 rowspan=2 class=label>行政执法活动引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="adminYearIssuecount" name="adminCountyIssuecount" countName="adminUnIssuecount-data" countTarget="adminUnIssuecount">${disputEsexamine.adminCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="adminYearIssuecount" name="adminTwonIssuecount" countName="adminUnIssuecount-data" countTarget="adminUnIssuecount">${disputEsexamine.adminTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="adminYearIssuecount" name="adminVilIssuecount" countName="adminUnIssuecount-data" countTarget="adminUnIssuecount">${disputEsexamine.adminVilIssuecount}</td>
		<td id='c_416_99_issuecount' class="toDataJosn" name="adminUnIssuecount" countName="adminUnIssuecount">${disputEsexamine.adminUnIssuecount}</td>
		<td id='c_416_100_issuecount' class="toDataJosn" name="adminYearIssuecount">${disputEsexamine.adminYearIssuecount}</td>
	</tr>
	<tr id='c_416_' >
		<td height=26 class=order >27</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="adminYearDealcount" name="adminCountyDealcount" countName="adminUnDealcount-data" countTarget="adminUnDealcount">${disputEsexamine.adminCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="adminYearDealcount" name="adminTwonDealcount" countName="adminUnDealcount-data" countTarget="adminUnDealcount">${disputEsexamine.adminTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="adminYearDealcount" name="adminVilDealcount" countName="adminUnDealcount-data" countTarget="adminUnDealcount">${disputEsexamine.adminVilDealcount}</td>
		<td id='c_416_99_dealcount' class="toDataJosn" name="adminUnDealcount" countName="adminUnDealcount">${disputEsexamine.adminUnDealcount}</td>
		<td id='c_416_100_dealcount' class="toDataJosn" name="adminYearDealcount">${disputEsexamine.adminYearDealcount}</td>
	</tr>
	<tr id='c_417_' >
		<td height=26 class=order >28</td>
		<td colspan=2 rowspan=2 class=label>大中专院校、中小学校引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="schoolYearIssuecount" name="schoolCountyIssuecount" countName="schoolUnIssuecount-data" countTarget="schoolUnIssuecount">${disputEsexamine.schoolCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="schoolYearIssuecount" name="schoolTwonIssuecount" countName="schoolUnIssuecount-data" countTarget="schoolUnIssuecount">${disputEsexamine.schoolTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="schoolYearIssuecount" name="schoolVilIssuecount" countName="schoolUnIssuecount-data" countTarget="schoolUnIssuecount">${disputEsexamine.schoolVilIssuecount}</td>
		<td class="toDataJosn" name="schoolUnIssuecount" countName="schoolUnIssuecount">${disputEsexamine.schoolUnIssuecount}</td>
		<td class="toDataJosn" name="schoolYearIssuecount">${disputEsexamine.schoolYearIssuecount}</td>
	</tr>
	<tr id='c_417_' >
		<td height=26 class=order >29</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="schoolYearDealcount" name="schoolCountyDealcount" countName="schoolUnDealcount-data" countTarget="schoolUnDealcount">${disputEsexamine.schoolCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="schoolYearDealcount" name="schoolTwonDealcount" countName="schoolUnDealcount-data" countTarget="schoolUnDealcount">${disputEsexamine.schoolTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="schoolYearDealcount" name="schoolVilDealcount" countName="schoolUnDealcount-data" countTarget="schoolUnDealcount">${disputEsexamine.schoolVilDealcount}</td>
		<td id='c_417_99_dealcount' class="toDataJosn" name="schoolUnDealcount" countName="schoolUnDealcount">${disputEsexamine.schoolUnDealcount}</td>
		<td id='c_417_100_dealcount' class="toDataJosn" name="schoolYearDealcount">${disputEsexamine.schoolYearDealcount}</td>
	</tr>
	<tr id='c_418_' >
		<td height=26 class=order >30</td>
		<td colspan=2 rowspan=2 class=label>海事渔事纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="seaYearIssuecount" name="seaCountyIssuecount" countName="seaUnIssuecount-data" countTarget="seaUnIssuecount">${disputEsexamine.seaCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="seaYearIssuecount" name="seaTwonIssuecount" countName="seaUnIssuecount-data" countTarget="seaUnIssuecount">${disputEsexamine.seaTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="seaYearIssuecount" name="seaVilIssuecount" countName="seaUnIssuecount-data" countTarget="seaUnIssuecount">${disputEsexamine.seaVilIssuecount}</td>
		<td name="seaUnIssuecount" class="toDataJosn" countName="seaUnIssuecount">${disputEsexamine.seaUnIssuecount}</td>
		<td name="seaYearIssuecount" class="toDataJosn">${disputEsexamine.seaYearIssuecount}</td>
	</tr>
	
	
	
	<tr id='c_418_' >
		<td height=26 class=order >31</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="seaYearDealcount" name="seaCountyDealcount" countName="seaUnDealcount-data" countTarget="seaUnDealcount">${disputEsexamine.seaCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="seaYearDealcount" name="seaTwonDealcount" countName="seaUnDealcount-data" countTarget="seaUnDealcount">${disputEsexamine.seaTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="seaYearDealcount" name="seaVilDealcount" countName="seaUnDealcount-data" countTarget="seaUnDealcount">${disputEsexamine.seaVilDealcount}</td>
		<td name="seaUnDealcount" class="toDataJosn" countName="seaUnDealcount">${disputEsexamine.seaUnDealcount}</td>
		<td name="seaYearDealcount" class="toDataJosn">${disputEsexamine.seaYearDealcount}</td>
	</tr>
	<tr id='c_419_' >
		<td height=26 class=order >32</td>
		<td colspan=2 rowspan=2 class=label>干部作风等问题引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="cadreYearIssuecount" name="cadreCountyIssuecount" countName="cadreUnIssuecount-data" countTarget="cadreUnIssuecount">${disputEsexamine.cadreCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="cadreYearIssuecount" name="cadreTwonIssuecount" countName="cadreUnIssuecount-data" countTarget="cadreUnIssuecount">${disputEsexamine.cadreTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="cadreYearIssuecount" name="cadreVilIssuecount" countName="cadreUnIssuecount-data" countTarget="cadreUnIssuecount">${disputEsexamine.cadreVilIssuecount}</td>
		<td name="cadreUnIssuecount" class="toDataJosn" countName="cadreUnIssuecount">${disputEsexamine.cadreUnIssuecount}</td>
		<td name="cadreYearIssuecount" class="toDataJosn">${disputEsexamine.cadreYearIssuecount}</td>
	</tr>
	<tr id='c_419_' >
		<td height=26 class=order >33</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="cadreYearDealcount" name="cadreCountyDealcount" countName="cadreUnDealcount-data" countTarget="cadreUnDealcount">${disputEsexamine.cadreCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="cadreYearDealcount" name="cadreTwonDealcount" countName="cadreUnDealcount-data" countTarget="cadreUnDealcount">${disputEsexamine.cadreTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="cadreYearDealcount" name="cadreVilDealcount" countName="cadreUnDealcount-data" countTarget="cadreUnDealcount">${disputEsexamine.cadreVilDealcount}</td>
		<td name="cadreUnDealcount" class="toDataJosn" countName="cadreUnDealcount">${disputEsexamine.cadreUnDealcount}</td>
		<td name="cadreYearDealcount" class="toDataJosn">${disputEsexamine.cadreYearDealcount}</td>
	</tr>
	<tr id='c_420_' >
		<td height=26 class=order >34</td>
		<td colspan=2 rowspan=2 class=label>村务管理引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="vilthYearIssuecount" name="vilthCountyIssuecount" countName="vilthUnIssuecount-data" countTarget="vilthUnIssuecount">${disputEsexamine.vilthCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="vilthYearIssuecount" name="vilthTwonIssuecount" countName="vilthUnIssuecount-data" countTarget="vilthUnIssuecount">${disputEsexamine.vilthTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="vilthYearIssuecount" name="vilthVilIssuecount" countName="vilthUnIssuecount-data" countTarget="vilthUnIssuecount">${disputEsexamine.vilthVilIssuecount}</td>
		<td name="vilthUnIssuecount" class="toDataJosn" countName="vilthUnIssuecount">${disputEsexamine.vilthUnIssuecount}</td>
		<td name="vilthYearIssuecount" class="toDataJosn">${disputEsexamine.vilthYearIssuecount}</td>
	</tr>
	<tr id='c_420_' >
		<td height=26 class=order >35</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="vilthYearDealcount" name="vilthCountyDealcount" countName="vilthUnDealcount-data" countTarget="vilthUnDealcount">${disputEsexamine.vilthCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="vilthYearDealcount" name="vilthTwonDealcount" countName="vilthUnDealcount-data" countTarget="vilthUnDealcount">${disputEsexamine.vilthTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="vilthYearDealcount" name="vilthVilDealcount" countName="vilthUnDealcount-data" countTarget="vilthUnDealcount">${disputEsexamine.vilthVilDealcount}</td>
		<td name="vilthUnDealcount" class="toDataJosn" countName="vilthUnDealcount">${disputEsexamine.vilthUnDealcount}</td>
		<td name="vilthYearDealcount" class="toDataJosn">${disputEsexamine.vilthYearDealcount}</td>
	</tr>
	<tr id='c_421_' >
		<td height=26 class=order >36</td>
		<td colspan=2 rowspan=2 class=label>农村土地承包、农村集体“三资”及农民负担等问题引发的矛盾纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="jobYearIssuecount" name="jobCountyIssuecount" countName="jobUnIssuecount-data" countTarget="jobUnIssuecount">${disputEsexamine.jobCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="jobYearIssuecount" name="jobTwonIssuecount" countName="jobUnIssuecount-data" countTarget="jobUnIssuecount">${disputEsexamine.jobTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="jobYearIssuecount" name="jobVilIssuecount" countName="jobUnIssuecount-data" countTarget="jobUnIssuecount">${disputEsexamine.jobVilIssuecount}</td>
		<td name="jobUnIssuecount" class="toDataJosn" countName="jobUnIssuecount">${disputEsexamine.jobUnIssuecount}</td>
		<td name="jobYearIssuecount" class="toDataJosn">${disputEsexamine.jobYearIssuecount}</td>
	</tr>
	<tr id='c_421_' >
		<td height=26 class=order >37</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="jobYearDealcount" name="jobCountyDealcount" countName="jobUnDealcount-data" countTarget="jobUnDealcount">${disputEsexamine.jobCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="jobYearDealcount" name="jobTwonDealcount" countName="jobUnDealcount-data" countTarget="jobUnDealcount">${disputEsexamine.jobTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="jobYearDealcount" name="jobVilDealcount" countName="jobUnDealcount-data" countTarget="jobUnDealcount">${disputEsexamine.jobVilDealcount}</td>
		<td name="jobUnDealcount" class="toDataJosn" countName="jobUnDealcount">${disputEsexamine.jobUnDealcount}</td>
		<td name="jobYearDealcount" class="toDataJosn">${disputEsexamine.jobYearDealcount}</td>
	</tr>
	<tr id='c_422_' >
		<td height=26 class=order >38</td>
		<td colspan=2 rowspan=2 class=label>婚姻家庭邻里等各类民间纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="familyYearIssuecount" name="familyCountyIssuecount" countName="familyUnIssuecount-data" countTarget="familyUnIssuecount">${disputEsexamine.familyCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="familyYearIssuecount" name="familyTwonIssuecount" countName="familyUnIssuecount-data" countTarget="familyUnIssuecount">${disputEsexamine.familyTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="familyYearIssuecount" name="familyVilIssuecount" countName="familyUnIssuecount-data" countTarget="familyUnIssuecount">${disputEsexamine.familyVilIssuecount}</td>
		<td name="familyUnIssuecount" class="toDataJosn" countName="familyUnIssuecount">${disputEsexamine.familyUnIssuecount}</td>
		<td name="familyYearIssuecount" class="toDataJosn">${disputEsexamine.familyYearIssuecount}</td>
	</tr>
	<tr id='c_422_' >
		<td height=26 class=order >39</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="familyYearDealcount" name="familyCountyDealcount" countName="familyUnDealcount-data" countTarget="familyUnDealcount">${disputEsexamine.familyCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="familyYearDealcount" name="familyTwonDealcount" countName="familyUnDealcount-data" countTarget="familyUnDealcount">${disputEsexamine.familyTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="familyYearDealcount" name="familyVilDealcount" countName="familyUnDealcount-data" countTarget="familyUnDealcount">${disputEsexamine.familyVilDealcount}</td>
		<td name="familyUnDealcount" class="toDataJosn" countName="familyUnDealcount">${disputEsexamine.familyUnDealcount}</td>
		<td name="familyYearDealcount" class="toDataJosn">${disputEsexamine.familyYearDealcount}</td>
	</tr>
	<tr id='c_423_' >
		<td height=26 class=order >40</td>
		<td colspan=2 rowspan=2 class=label>医患纠纷</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="patientYearIssuecount" name="patientCountyIssuecount" countName="patientUnIssuecount-data" countTarget="patientUnIssuecount">${disputEsexamine.patientCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="patientYearIssuecount" name="patientTwonIssuecount" countName="patientUnIssuecount-data" countTarget="patientUnIssuecount">${disputEsexamine.patientTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="patientYearIssuecount" name="patientVilIssuecount" countName="patientUnIssuecount-data" countTarget="patientUnIssuecount">${disputEsexamine.patientVilIssuecount}</td>
		<td name="patientUnIssuecount" class="toDataJosn" countName="patientUnIssuecount">${disputEsexamine.patientUnIssuecount}</td>
		<td name="patientYearIssuecount" class="toDataJosn">${disputEsexamine.patientYearIssuecount}</td>
	</tr>
	
	
	<tr id='c_423_' >
		<td height=26 class=order >41</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="patientYearDealcount" name="patientCountyDealcount" countName="patientUnDealcount-data" countTarget="patientUnDealcount">${disputEsexamine.patientCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="patientYearDealcount" name="patientTwonDealcount" countName="patientUnDealcount-data" countTarget="patientUnDealcount">${disputEsexamine.patientTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="patientYearDealcount" name="patientVilDealcount" countName="patientUnDealcount-data" countTarget="patientUnDealcount">${disputEsexamine.patientVilDealcount}</td>
		<td name="patientUnDealcount" class="toDataJosn" countName="patientUnDealcount">${disputEsexamine.patientUnDealcount}</td>
		<td name="patientYearDealcount" class="toDataJosn">${disputEsexamine.patientYearDealcount}</td>
	</tr>
	<tr id='c_424_' >
		<td height=26 class=order >42</td>
		<td colspan=2 rowspan=2 class=label>其他</td>
		<td class=label >排查数</td>
		<td class="toDataJosn" yearCountTarget="otherYearIssuecount" name="otherCountyIssuecount" countName="otherUnIssuecount-data" countTarget="otherUnIssuecount">${disputEsexamine.otherCountyIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="otherYearIssuecount" name="otherTwonIssuecount" countName="otherUnIssuecount-data" countTarget="otherUnIssuecount">${disputEsexamine.otherTwonIssuecount}</td>
		<td class="toDataJosn" yearCountTarget="otherYearIssuecount" name="otherVilIssuecount" countName="otherUnIssuecount-data" countTarget="otherUnIssuecount">${disputEsexamine.otherVilIssuecount}</td>
		<td name="otherUnIssuecount" class="toDataJosn" countName="otherUnIssuecount">${disputEsexamine.otherUnIssuecount}</td>
		<td name="otherYearIssuecount" class="toDataJosn">${disputEsexamine.otherYearIssuecount}</td>
	</tr>
	<tr id='c_424_' >
		<td height=26 class=order >43</td>
		<td class=label >调处数</td>
		<td class="toDataJosn" yearCountTarget="otherYearDealcount" name="otherCountyDealcount" countName="otherUnDealcount-data" countTarget="otherUnDealcount">${disputEsexamine.otherCountyDealcount}</td>
		<td class="toDataJosn" yearCountTarget="otherYearDealcount" name="otherTwonDealcount" countName="otherUnDealcount-data" countTarget="otherUnDealcount">${disputEsexamine.otherTwonDealcount}</td>
		<td class="toDataJosn" yearCountTarget="otherYearDealcount" name="otherVilDealcount" countName="otherUnDealcount-data" countTarget="otherUnDealcount">${disputEsexamine.otherVilDealcount}</td>
		<td name="otherUnDealcount" class="toDataJosn" countName="otherUnDealcount">${disputEsexamine.otherUnDealcount}</td>
		<td name="otherYearDealcount" class="toDataJosn">${disputEsexamine.otherYearDealcount}</td>
	</tr>
	<tr id='c_425_' >
		<td height=26 class=order >44</td>
		<td rowspan=4 class=label>涉及矛盾纠纷人数</td>
		<td class=label>50人以下</td>
		<td class=label >起数</td>
		<td class="toDataJosn" yearCountTarget="issuelowYearStagescount" name="issuelowCountyStagescount" countName="issuelowUnStagescount-data" countTarget="issuelowUnStagescount">${disputEsexamine.issuelowCountyStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuelowYearStagescount" name="issuelowTwonStagescount" countName="issuelowUnStagescount-data" countTarget="issuelowUnStagescount">${disputEsexamine.issuelowTwonStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuelowYearStagescount" name="issuelowVilStagescount" countName="issuelowUnStagescount-data" countTarget="issuelowUnStagescount">${disputEsexamine.issuelowVilStagescount}</td>
		<td name="issuelowUnStagescount" class="toDataJosn" countName="issuelowUnStagescount">${disputEsexamine.issuelowUnStagescount}</td>
		<td name="issuelowYearStagescount" class="toDataJosn">${disputEsexamine.issuelowYearStagescount}</td>
	</tr>
	<tr id='c_426_' >
		<td height=26 class=order >45</td>
		<td class=label>50至100人</td>
		<td class=label >起数</td>
		<td class="toDataJosn" yearCountTarget="issuemidYearStagescount" name="issuemidCountyStagescount" countName="issuemidUnStagescount-data" countTarget="issuemidUnStagescount">${disputEsexamine.issuemidCountyStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuemidYearStagescount" name="issuemidTwonStagescount" countName="issuemidUnStagescount-data" countTarget="issuemidUnStagescount">${disputEsexamine.issuemidTwonStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuemidYearStagescount" name="issuemidVilStagescount" countName="issuemidUnStagescount-data" countTarget="issuemidUnStagescount">${disputEsexamine.issuemidVilStagescount}</td>
		<td name="issuemidUnStagescount" class="toDataJosn" countName="issuemidUnStagescount">${disputEsexamine.issuemidUnStagescount}</td>
		<td name="issuemidYearStagescount" class="toDataJosn">${disputEsexamine.issuemidYearStagescount}</td>
	</tr>
	<tr id='c_427_' >
		<td height=26 class=order >46</td>
		<td class=label>100人至500人</td>
		<td class=label >起数</td>
		<td class="toDataJosn" yearCountTarget="issuehighYearStagescount" name="issuehighCountyStagescount" countName="issuehighUnStagescount-data" countTarget="issuehighUnStagescount">${disputEsexamine.issuehighCountyStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuehighYearStagescount" name="issuehighTwonStagescount" countName="issuehighUnStagescount-data" countTarget="issuehighUnStagescount">${disputEsexamine.issuehighTwonStagescount}</td>
		<td class="toDataJosn" yearCountTarget="issuehighYearStagescount" name="issuehighVilStagescount" countName="issuehighUnStagescount-data" countTarget="issuehighUnStagescount">${disputEsexamine.issuehighVilStagescount}</td>
		<td name="issuehighUnStagescount" class="toDataJosn" countName="issuehighUnStagescount">${disputEsexamine.issuehighUnStagescount}</td>
		<td name="issuehighYearStagescount" class="toDataJosn">${disputEsexamine.issuehighYearStagescount}</td>
	</tr>
	<tr id='c_428_' >
		<td height=26 class=order >47</td>
		<td class=label>500人以上</td>
		<td class=label >起数</td>
		<td name="issuehigherCountyStagescount" yearCountTarget="issuehigherYearStagescount" class="toDataJosn" countName="issuehigherUnStagescount-data" countTarget="issuehigherUnStagescount">${disputEsexamine.issuehigherCountyStagescount}</td>
		<td name="issuehigherTwonStagescount" yearCountTarget="issuehigherYearStagescount" class="toDataJosn" countName="issuehigherUnStagescount-data" countTarget="issuehigherUnStagescount">${disputEsexamine.issuehigherTwonStagescount}</td>
		<td name="issuehigherVilStagescount" yearCountTarget="issuehigherYearStagescount" class="toDataJosn" countName="issuehigherUnStagescount-data" countTarget="issuehigherUnStagescount">${disputEsexamine.issuehigherVilStagescount}</td>
		<td name="issuehigherUnStagescount" class="toDataJosn" countName="issuehigherUnStagescount" >${disputEsexamine.issuehigherUnStagescount}</td>
		<td name="issuehigherYearStagescount" class="toDataJosn">${disputEsexamine.issuehigherYearStagescount}</td>
	</tr>
	<tr id='c_429_' >
		<td height=26 class=order >48</td>
		<td colspan=2 rowspan=4 class=label style='width:213pt'>重大矛盾纠纷</td>
		<td class=label >排查数</td>
		<td name="imptCountyImptIssuecount" yearCountTarget="imptYearImptIssuecount" class="toDataJosn" countName="imptUnImptIssuecount-data" countTarget="imptUnImptIssuecount">${disputEsexamine.imptCountyImptIssuecount}</td>
		<td name="imptTwonImptIssuecount" yearCountTarget="imptYearImptIssuecount" class="toDataJosn" countName="imptUnImptIssuecount-data" countTarget="imptUnImptIssuecount">${disputEsexamine.imptTwonImptIssuecount}</td>
		<td name="imptVilImptIssuecount" yearCountTarget="imptYearImptIssuecount" class="toDataJosn" countName="imptUnImptIssuecount-data" countTarget="imptUnImptIssuecount">${disputEsexamine.imptVilImptIssuecount}</td>
		<td name="imptUnImptIssuecount" class="toDataJosn" countName="imptUnImptIssuecount">${disputEsexamine.imptUnImptIssuecount}</td>
		<td name="imptYearImptIssuecount" class="toDataJosn" >${disputEsexamine.imptYearImptIssuecount}</td>
	</tr>
	<tr id='c_429_' >
		<td height=26 class=order >49</td>
		<td class=label >调处数</td>
		<td name="imptCountyImptDealcount" class="toDataJosn" yearCountTarget="imptYearImptDealcount" countName="imptUnImptDealcount-data" countTarget="imptUnImptDealcount">${disputEsexamine.imptCountyImptDealcount}</td>
		<td name="imptTwonImptDealcount" class="toDataJosn" yearCountTarget="imptYearImptDealcount" countName="imptUnImptDealcount-data" countTarget="imptUnImptDealcount">${disputEsexamine.imptTwonImptDealcount}</td>
		<td name="imptVilImptDealcount" class="toDataJosn" yearCountTarget="imptYearImptDealcount" countName="imptUnImptDealcount-data" countTarget="imptUnImptDealcount">${disputEsexamine.imptVilImptDealcount}</td>
		<td name="imptUnImptDealcount" class="toDataJosn" countName="imptUnImptDealcount">${disputEsexamine.imptUnImptDealcount}</td>
		<td name="imptYearImptDealcount" class="toDataJosn" >${disputEsexamine.imptYearImptDealcount}</td>
	</tr>
	<tr id='c_429_' >
		<td height=26 class=order >50</td>
		<td class=label >成功数</td>
		<td name="imptCountyImptFinishcount" class="toDataJosn" yearCountTarget="imptYearImptFinishcount" countName="imptUnImptFinishcount-data" countTarget="imptUnImptFinishcount">${disputEsexamine.imptCountyImptFinishcount}</td>
		<td name="imptTwonImptFinishcount" class="toDataJosn" yearCountTarget="imptYearImptFinishcount" countName="imptUnImptFinishcount-data" countTarget="imptUnImptFinishcount">${disputEsexamine.imptTwonImptFinishcount}</td>
		<td name="imptVilImptFinishcount" class="toDataJosn" yearCountTarget="imptYearImptFinishcount" countName="imptUnImptFinishcount-data" countTarget="imptUnImptFinishcount">${disputEsexamine.imptVilImptFinishcount}</td>
		<td name="imptUnImptFinishcount" class="toDataJosn" countName="imptUnImptFinishcount">${disputEsexamine.imptUnImptFinishcount}</td>
		<td name="imptYearImptFinishcount" class="toDataJosn" >${disputEsexamine.imptYearImptFinishcount}</td>
	</tr>
	<tr id='c_429_' >
		<td height=26 class=order >51</td>
		<td class=label >成功率</td>
		<td name="imptCountyImptFinishrate" class="toDataJosn" countName="imptUnImptFinishrate-data" countTarget="imptUnImptFinishrate">${disputEsexamine.imptCountyImptFinishrate}</td>
		<td name="imptTwonImptFinishrate" class="toDataJosn" countName="imptUnImptFinishrate-data" countTarget="imptUnImptFinishrate">${disputEsexamine.imptTwonImptFinishrate}</td>
		<td name="imptVilImptFinishrate" class="toDataJosn" countName="imptUnImptFinishrate-data" countTarget="imptUnImptFinishrate">${disputEsexamine.imptVilImptFinishrate}</td>
		<td name="imptUnImptFinishrate" class="toDataJosn" countName="imptUnImptFinishrate">${disputEsexamine.imptUnImptFinishrate}</td>
		<td name="imptYearImptFinishrate" class="toDataJosn" >${disputEsexamine.imptYearImptFinishrate}</td>
	</tr>
	<tr id='c_430_'>
		<td height=26 class=order >52</td>
		<td colspan=2 class=label >开展集中排查</td>
		<td class=label >次数</td>
		<td name="concentrateCountyFinishcount" yearCountTarget="concentrateYearFinishcount" class="toDataJosn" countName="concentrateUnFinishcount-data" countTarget="concentrateUnFinishcount">${disputEsexamine.concentrateCountyFinishcount}</td>
		<td name="concentrateTwonFinishcount" yearCountTarget="concentrateYearFinishcount" class="toDataJosn" countName="concentrateUnFinishcount-data" countTarget="concentrateUnFinishcount">${disputEsexamine.concentrateTwonFinishcount}</td>
		<td name="concentrateVilFinishcount" yearCountTarget="concentrateYearFinishcount" class="toDataJosn" countName="concentrateUnFinishcount-data" countTarget="concentrateUnFinishcount">${disputEsexamine.concentrateVilFinishcount}</td>
		<td name="concentrateUnFinishcount" class="toDataJosn" countName="concentrateUnFinishcount">${disputEsexamine.concentrateUnFinishcount}</td>
		<td name="concentrateYearFinishcount" class="toDataJosn">${disputEsexamine.concentrateYearFinishcount}</td>
	</tr>
	
	
	<tr height=42 >
		<td height=42 class=order>备注</td>
		<td colspan=8 class=label>各市于每季度第一个月的10日前将此报表上报省综治办；县（市、区）于次月5日前上报市综治办。</td>
	</tr>
	<tr height=37 >
		<td colspan=3 height=37 class=bottom>签发人：<input  id="checkUser" type= "text"   maxLength= "12 " value="${disputEsexamine.dealPerson}"   name="disputEsexamine.dealPerson"  size= "12 "   style= "BORDER-BOTTOM:   #000000   1px solid; BORDER-LEFT:   #ffffff   0px   solid;   BORDER-RIGHT:   #ffffff   0px   solid;   BORDER-TOP:   #ffffff   0px   solid "/>  </td>
		<td colspan=3 class=bottom>制表人：<input id="createTabUser" type= "text"   maxLength= "12 " value="${disputEsexamine.lister}"   name="disputEsexamine.lister" size= "12 "   style= "BORDER-BOTTOM:   #000000   1px solid; BORDER-LEFT:   #ffffff   0px   solid;   BORDER-RIGHT:   #ffffff   0px   solid;   BORDER-TOP:   #ffffff   0px   solid "/> </td>
		<td colspan=3 class=bottom>四川省社会治安综合治理委员会办公室制</td>
	</tr>
	<tr >
		<td height=26 colspan=2 class=bottom></td>
		<td class=bottom></td>
		<td class=bottom><input type='button' id="detailsub"  onclick="saveIssueDetails('已上报')" style="display:none;" value='上报'/>&nbsp;&nbsp;&nbsp; <input type='button' id="detailsave" onclick="saveIssueDetails('已编制')"style="display:none;"  value='保存'/>&nbsp;&nbsp;&nbsp;</td>
		<td colspan=5 class=bottom></td>
	</tr>
</table>
<input id="disputEsexamineId" value="${disputEsexamine.id}" type="hidden"/>
<input value="" id="" type="hidden"/>
</div>
<script>
var reportJudge=0;
var reportJudgeBoolean;
var keyDownArr = new Array();
var keyUpValueArr = new Array();
var keyUpNum = 0;
var selectLength = $("#reportTime").find("option").length;
/* for(var i = ${reportTime}+1 ; i<=selectLength ; i++){
$("#reportTime option[value = '"+i+"']").remove();
	} */
	function inputBlur(selfDoc){
		var selfDocValue=selfDoc.val();
		var tdDoc = selfDoc.parent();
		tdDoc.html(selfDocValue!=''?selfDocValue:0);
		tdDoc.removeClass("inputOut");
		keyDownArr = [];
		keyUpValueArr = [];
		keyUpNum = 0;
	}
	function countXiaoji(selfDoc){
		var value=selfDoc.val();
		if(value==""||isNaN(value)){
			value=0;
		}
		var tdDoc = selfDoc.parent();
		var countName=tdDoc.attr("countName");
		var countTarget=tdDoc.attr("countTarget");
		var tds=$("td[countName='"+countName+"']");
		var totalCount=0;
		$.each(tds,function(i){
			if(!$(tds[i]).hasClass("inputOut") && $(tds[i]).text()!="" && !isNaN($(tds[i]).text())){
				totalCount=totalCount+parseFloat($(tds[i]).text());
			}
		});
		totalCount=parseFloat(value)+totalCount;
		$("td[countName='"+countTarget+"']").text(totalCount);
	}
	var inputKeyDownValue=0;
	function countNianDuLeiJi(selfDoc,inputKeyDownValue){
		var rateCount = getRateValue(selfDoc,inputKeyDownValue);
		var tdDoc = selfDoc.parent();
		var nianduLeijji = $("td[name='"+tdDoc.attr("yearCountTarget")+"']");
		if(nianduLeijji.text()!=""&&!isNaN(nianduLeijji.text())){
			nianduLeijji.text(parseFloat(nianduLeijji.text())+rateCount);
		}else{
			nianduLeijji.text(rateCount);
		}
	}
	function countDiaochulv(selfDoc,denominator,molecule){
		var tdDoc = selfDoc.parent();
		var value = selfDoc.val();
		if(tdDoc.attr(denominator)!="" && tdDoc.attr(denominator)!=undefined){
			var denominator=tdDoc.attr(denominator);
			var name=denominator.split("-")[0];
			var moleculeValue = $("td["+molecule+"='"+name+"-molecule']").text();
			if(value!="" && !isNaN(value) && moleculeValue!="" && !isNaN(moleculeValue)){
				$("td[name='"+name+"']").text(moleculeValue/value);
			}
		}
		if(tdDoc.attr(molecule)!="" && tdDoc.attr(molecule)!=undefined){
			var molecule=tdDoc.attr(molecule);
			var name=molecule.split("-")[0];
			var denominatorValue = $("td["+denominator+"='"+name+"-denominator']").text();
			if(value!="" && !isNaN(value) && denominatorValue!="" && !isNaN(denominatorValue)){
				$("td[name='"+name+"']").text(value/denominatorValue);
			}
		}
	}
	function inputKeyDown(selfDoc){
		inputKeyDownValue=$(selfDoc).val()==''?0:$(selfDoc).val();
		keyDownArr.push(inputKeyDownValue);
	}
	function inputKeyOut(selfDoc){
		var value = $(selfDoc).val().replace(/[^0-9]/g,'');
		keyUpValueArr.push(new Array(inputKeyDownValue,value));
		$(selfDoc).val(value);

		if(keyUpValueArr[parseInt(keyUpNum)][0]==keyDownArr[parseInt(keyUpNum)]){
		countXiaoji(selfDoc);
		countNianDuLeiJi(selfDoc,keyDownArr[0]);
		countZongShu(selfDoc,"CountyIssuecount",["totalCountyIssuecount","totalUnIssuecount","totalYearIssuecount"],keyDownArr[0]);
		countZongShu(selfDoc,"CountyDealcount",["totalCountyDealcount","totalUnDealcount","totalYearDealcount"],keyDownArr[0]);
		countZongShu(selfDoc,"TwonIssuecount",["totalTwonIssuecount","totalUnIssuecount","totalYearIssuecount"],keyDownArr[0]);
		countZongShu(selfDoc,"TwonDealcount",["totalTwonDealcount","totalUnDealcount","totalYearDealcount"],keyDownArr[0]);
		countZongShu(selfDoc,"VilIssuecount",["totalVilIssuecount","totalUnIssuecount","totalYearIssuecount"],keyDownArr[0]);
		countZongShu(selfDoc,"VilDealcount",["totalVilDealcount","totalUnDealcount","totalYearDealcount"],keyDownArr[0]);

		
		var issueCounts=["totalCountyIssuecount","totalTwonIssuecount","totalVilIssuecount","totalUnIssuecount","totalYearIssuecount"];
		var dealCounts=["totalCountyDealcount","totalTwonDealcount","totalVilDealcount","totalUnDealcount","totalYearDealcount"];
		var dealRates=["totalCountyDealrate","totalTwonDealrate","totalVilDealrate","totalUnDealrate","totalYearDealrate"];
		var finishCounts=["totalCountyFinishcount","totalTwonFinishcount","totalVilFinishcount","totalUnFinishcount","totalYearFinishcount"];
		var finishRates=["totalCountyFinishrate","totalTwonFinishrate","totalVilFinishrate","totalUnFinishrate","totalYearFinishrate"];
		
		var imptIssueCounts=["imptCountyImptIssuecount","imptTwonImptIssuecount","imptVilImptIssuecount","imptUnImptIssuecount","imptYearImptIssuecount"];
		var imptFinishCounts=["imptCountyImptFinishcount","imptTwonImptFinishcount","imptVilImptFinishcount","imptUnImptFinishcount","imptYearImptFinishcount"];
		var imptFinishRates=["imptCountyImptFinishrate","imptTwonImptFinishrate","imptVilImptFinishrate","imptUnImptFinishrate","imptYearImptFinishrate"];
		var imptDealcount =["imptCountyImptDealcount","imptTwonImptDealcount","imptVilImptDealcount","imptUnImptDealcount","imptYearImptDealcount"];
		countBiLv(issueCounts,dealCounts,dealRates);
		countChenggongLv(selfDoc, dealCounts, finishCounts, finishRates);
		countChenggongLv(selfDoc, imptDealcount, imptFinishCounts, imptFinishRates);
		keyDownArr = [];
		keyUpValueArr = [];
		keyUpNum = 0;
		}else{
			keyUpNum++;
			}
		}
	var allJson="";
	function getParticularData(){
		var json;
		var alltd=$(".toDataJosn");
		var name;
		var value;
		$.each(alltd,function(i){
			name=$(alltd[i]).attr("name");
			value=$(alltd[i]).text();
			value = value.replace('%', '%25');
			allJson=allJson+"disputEsexamine."+name+"="+value+"&";
		});
	}
	function getOtherData(){
		<s:if test='"edit".equals(mode)'>
			allJson=allJson+"disputEsexamine.id="+$("#disputEsexamineId").val()+"&";
		</s:if>
		allJson=allJson+"disputEsexamine.name="+encodeURIComponent(encodeURIComponent($("#reportName").text()))+"&";
		allJson=allJson+"disputEsexamine.lister="+encodeURIComponent(encodeURIComponent($("#createTabUser").val()))+"&";
		allJson=allJson+"disputEsexamine.dealPerson="+encodeURIComponent(encodeURIComponent($("#checkUser").val()))+"&";
		allJson=allJson+"disputEsexamine.reportTime="+$('#reportTime').val()+"&";
	}
	
	function submitData(){
		if($("#createTabUser").val()==""){
			$.messageBox({message:"请填写制表人",level:"error"});
			$("#createTabUser").focus();
			return ;
		}
		if(reportJudge==1){
			$.messageBox({message:$("#reportTime").find("option:selected").text()+"信息已添加!",level:"error"});
			return ;
		}
		if(reportJudge==2){
			$.messageBox({message:$("#reportTime").find("option:selected").text()+"子辖区没有全部上报!",level:"error"});
			return;
		}
		if((parseInt($("td[name='issuelowYearStagescount']").text())+
				parseInt($("td[name='issuemidYearStagescount']").text())+
				parseInt($("td[name='issuehighYearStagescount']").text())+
				parseInt($("td[name='issuehigherYearStagescount']").text()))!=
					parseInt($("td[name='totalYearIssuecount']").text())){
				$.messageBox({message:"起数之和不等于总排查数!",level:"error"});
				return;
				}
		
		//sunReportJudge();
		if(reportJudgeBoolean){
			return ;
		}
		getOtherData();
		getParticularData();

		var submitUrl;
		<s:if test='"add".equals(mode)'>
		submitUrl="${path}/workingRecord/socialConflictReordManage/addSocialConflictReord.action";
		allJson = "dailyDirectory.id="+${dailyDirectory.id}+"&dailyYear.id="+$("#dailyYear").val()+"&"+allJson;
		</s:if>
		<s:if test='"edit".equals(mode)'>
		submitUrl="${path}/workingRecord/socialConflictReordManage/updateSocialConflictReord.action";
		</s:if>
		$.ajax({
			url: submitUrl,
			type:'post',
			data:allJson,
			success:function(responseData){
				if(responseData==null){
					<s:if test='"add".equals(mode)'>
						$.messageBox({message:"保存报表信息失败!",level:"error"});
					</s:if>
					<s:if test='"edit".equals(mode)'>
						$.messageBox({message:"修改报表信息失败!",level:"error"});
					</s:if>
				}else{
					<s:if test='"add".equals(mode)'>
						$.messageBox({message:"保存报表信息成功!"});
						$("#dailyLogList").addRowData(responseData.id,responseData,"first");
					</s:if>
					<s:if test='"edit".equals(mode)'>
						$.messageBox({message:"修改报表信息成功!"});
						$("#dailyLogList").setRowData(responseData.id,responseData);
					</s:if>
					$("#dailyLogMaintanceDialog").dialog("close");
					$("#dailyLogList").setSelection(responseData.id);
				}
			}
		});
	}
	function paddingTdData(data){
		var toDataJosnTds=$(".toDataJosn");
		$.each(toDataJosnTds,function(i){
			if(data==null){
				$(toDataJosnTds[i]).text(0);
			}else{
				$(toDataJosnTds[i]).text(data[$(toDataJosnTds[i]).attr("name")]);
			}
		});
	}
	$(document).ready(function(){
			<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
			$("#reportTime").append("<option value='"+$('select[id ="dailyYear"] option:selected').text()
			+"'>"+$('select[id ="dailyYear"] option:selected').text()+"</option>");
			</s:if>
		<s:if test='"add".equals(mode)'>
			$("#reportTime").val(${reportTime});
		onSelectChanged();
		</s:if>
		<s:else>
			$("#reportTime").val(${disputEsexamine.reportTime});
			$("#reportTime > option[value = "+${disputEsexamine.reportTime}+"]").attr("selected", "selected");
		</s:else>

		<s:if test='!"view".equals(mode)'>
		$("#reportTime").change(onSelectChanged);
		$(".inputClick").click(function(){
			var self = $(this);
			var all= $(".inputClick");
			if(!self.hasClass("inputOut")){
				self.addClass("inputOut");
				var value=self.text();
				self.html("");
				var inputDataReport=$("<input onblur='inputBlur($(this))' onkeydown='inputKeyDown($(this))' onkeyup='inputKeyOut($(this))' style='width:80px' name='' class='reportDataInput' />");
				self.append(inputDataReport);
				inputDataReport.focus().val(value!=0?value:'');
			}
		});
		</s:if>
		
		
	});
<s:if test='!"view".equals(mode)'>
function onSelectChanged(){
	<s:if test="reportTypeInternalId!=@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
	$("#selectedMonthForDis").text($("#reportTime").find("option:selected").text());
	</s:if>
	$.ajax({
		url:"${path}/workingRecord/socialConflictReordManage/getDisputEsexamineByReportTime.action?dailyDirectory.id="+${dailyDirectory.id}+"&reportTime="+$("#reportTime").val(),
		success:function(data){
			paddingTdData(data);
			reportJudge=0;
			<s:if test='"add".equals(mode)'>
				if(data!=null && data.id!=null){
					reportJudge=1;
					$.messageBox({message:$("#reportTime").find("option:selected").text()+"信息已添加!",level:"error"});
				}else if(data==null){
					reportJudge=2;
					$.messageBox({message:$("#reportTime").find("option:selected").text()+"子辖区没有全部上报!",level:"error"});
				}
				$("#checkUser").val("");
				$("#createTabUser").val("");
				
			</s:if>
		}
	});
}
function sunReportJudge(){
	$.ajax({
		async : false,
		url:"${path}/workingRecord/publicSecurityRenovateManage/sunReportJudge.action?dailyDirectory.id="+${dailyDirectory.id}+"&reportTime="+$("#reportTime").val(),
		success:function(data){
			if(data){
				$.messageBox({message:"本月子辖区没有全部上报!",level:"error"});	
				reportJudgeBoolean=data;
			}else{
				reportJudgeBoolean=data;
			}
		}
	});
}
function countZongShu(selfDoc,suffix,totalArray,inputKeyDownValue){
	var value=selfDoc.val();
	if(value==""||isNaN(value)){
		value=0;
	}
	var tdDoc = selfDoc.parent();
	var tdName = $(tdDoc).attr("name");
	if(tdName.indexOf(suffix)!=-1){
		var rateCount = getRateValue(selfDoc,inputKeyDownValue);
		for(var i=0; i<totalArray.length; i++){
			var totalVilIssuecount = $("td[name='"+totalArray[i]+"']");
			totalVilIssuecount.text(parseFloat(totalVilIssuecount.text())+rateCount);
		}
	}
}
function getRateValue(selfDoc,inputKeyDownValue){
	var value=selfDoc.val();
	if(value==""||isNaN(value)){
		value=0;
	}
	if(inputKeyDownValue==""||isNaN(inputKeyDownValue)){
		inputKeyDownValue=0;
	}
	return parseFloat(value)-parseFloat(inputKeyDownValue);
}
function countBiLv(issueCounts,dealCounts,dealRates){
	for(var i=0;i<issueCounts.length;i++){
		if($("td[name='"+issueCounts[i]+"']").text()==""||parseFloat($("td[name='"+issueCounts[i]+"']").text())==0||isNaN($("td[name='"+issueCounts[i]+"']").text())||parseFloat($("td[name='"+dealCounts[i]+"']").text())==0){
			$("td[name='"+dealRates[i]+"']").text(0);
		}else{
			$("td[name='"+dealRates[i]+"']").text((Math.round(parseFloat($("td[name='"+dealCounts[i]+"']").text())/parseFloat($("td[name='"+issueCounts[i]+"']").text())* 10000) / 100.00)+"%");
		}
	}
}
function countChenggongLv(selfDoc,issueCounts,dealCounts,dealRates){
	var tdDoc = selfDoc.parent();
	var value = selfDoc.val();
	if(isNaN(value)){
		value=0;
	}
	for(var i=0;i<issueCounts.length;i++){
		if(tdDoc.attr("name")==issueCounts[i]){
			if(value==""||isNaN(value)||value==0||$("td[name='"+dealCounts[i]+"']").text()==""){
				$("td[name='"+dealRates[i]+"']").text(0);
			}else{
				$("td[name='"+dealRates[i]+"']").text((Math.round(parseFloat($("td[name='"+dealCounts[i]+"']").text())/parseFloat(value)* 10000) / 100.00)+"%");
			}
		}else if(tdDoc.attr("name")==dealCounts[i]){
			if(value==""||isNaN($("td[name='"+issueCounts[i]+"']").text())||parseFloat($("td[name='"+issueCounts[i]+"']").text())==0){
				$("td[name='"+dealRates[i]+"']").text(0);
			}else{
				$("td[name='"+dealRates[i]+"']").text((Math.round(parseFloat(value)/parseFloat($("td[name='"+issueCounts[i]+"']").text())* 10000) / 100.00)+"%");
			}
		}else{
			if(value==""||isNaN($("td[name='"+issueCounts[i]+"']").text())||parseFloat($("td[name='"+issueCounts[i]+"']").text())==0||$("td[name='"+dealCounts[i]+"']").text()==""){
				$("td[name='"+dealRates[i]+"']").text(0);
			}else{
				$("td[name='"+dealRates[i]+"']").text((Math.round(parseFloat($("td[name='"+dealCounts[i]+"']").text())/parseFloat($("td[name='"+issueCounts[i]+"']").text())* 10000) / 100.00)+"%");
			}
		}
	}
}
</s:if>
</script>