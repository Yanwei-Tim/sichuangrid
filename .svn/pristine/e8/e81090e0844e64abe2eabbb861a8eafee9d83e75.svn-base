<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
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
		}
		table.tb td.head{
			text-align:center;
			font-weight:bold;
			font-size:15px;
		}
		table.tb td.bottom{
			border:0px;
		}
		table.tb td.order{
			text-align:center;
			height:26px;
		}
		table.tb td.label{
			text-align:left;
			font-size:15px;
		}
		.tableCtt{
			height:380px;
			overflow-y:auto;
		}
		#reportName , #selectedMonth{
			text-align:center;border:0px;border-style:none;height:33.0pt;font-size:25px; font-weight:bold;
		}
	</style>
	<center>
		<table class="tb" cellpadding=0 cellspacing=0 
			style="table-layout: fixed; border-collapse: collapse;">
		 	<s:if test='"add".equals(mode)'>
			<tr>
				<td colspan=4 height=44 id="reportName" >${dailyYear.yearDate}年<span id="selectedMonth" ></span>${organization.orgName}治安重点整治工作表</td>
			</tr>
			</s:if>
			<s:else>
			<tr>
				<td colspan=4 height=44 id="reportName"  >${emphasisSafetyDetail.name}</td>
			</tr>
			</s:else>
		 	<tr>
		 		<td colspan=2 style='height:20.25pt;border:0px;width:280px;' id="areacode">
			  		填报单位（盖章）：<s:if test='"add".equals(mode)'>${organization.orgName}</s:if><s:else>${emphasisSafetyDetail.organization.orgName}</s:else>
			  	</td>
			  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@MONTH_REPORT">
					<td colspan=2 class=head style="height:20.25pt;border:0px; text-align:right;margin-right:20px;width:220px;">填报月份：
						<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
                            <s:iterator value="dateMap" id="date" >
							    <s:property value="#date"/>
								<option value='<s:property value="key"/>'><s:property value="value"/></option>
							</s:iterator>
			   			</select>
					</td>
			  	</s:if>
			  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@QUARTER_REPORT">
				  	<td class=head colspan=2 style="border:0px; text-align:right;margin-right:20px;width:220px;">填报季度：
					  	<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
							<s:iterator value="dateMap" id="date" >
							    <s:property value="#date"/>
								<option value='<s:property value="key"/>'><s:property value="value"/></option>
							</s:iterator>
						</select>
					</td>
			  	</s:if>
			  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@SEMIYEARLY_REPORT">
				  	<td class=head colspan=2 style="border:0px; text-align:right;margin-right:20px;width:220px;">填报半年度：
					  	<select id="reportTime" <s:if test='!"add".equals(mode)'>disabled="disabled"</s:if>>
							<option value="1">上半年</option>
							<option value="2">下半年</option>
						</select>
					</td>
			  	</s:if>
			  	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
				  	<td colspan=2 class=head style="border:0px;  text-align:right;margin-right:20px;width:220px;">填报年度：
					  	<select id="reportTime" disabled="disabled">
						</select>
					</td>
			  	</s:if>
		 	</tr>
		 </table>
		 <div class="tableCtt" style="margin-bottom:15px">
			<table id='tb' class="tb" cellpadding=0 cellspacing=0 
			style="table-layout: fixed; border-collapse: collapse;height:100px;overflow-y:auto;">
			<tr>
				<td rowspan="3" align="center" class=label style="width: 130px; height: 60px;">排查次数</td>
				<td colspan="2" align="center" class=label style="width: 260px;">总次数</td>
				<td name="inspectSum" class="toDataJosn inputClick" style="width: 100px; height: 24px;">${emphasisSafetyDetail.inspectSum }</td>
			</tr>
			<tr>
				<td rowspan="2" class=label align="center" style="width: 130px; height: 40px;">其中</td>
				<td class=label>组织工作组数</td>
				<td name="inspectworkingTeam" countName="inspectSum-data" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.inspectworkingTeam }</td>
			</tr>
			<tr>
				<td class=label>其他</td>
				<td name="inspectOther" countName="inspectSum-data" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.inspectOther }</td>
			</tr>
		
			<tr>
				<td rowspan="8" class=label align="center" style="width: 130px; height: 120px;">宣传发动数</td>
				<td colspan="2" class=label align="center" style="width: 260px;">总人数</td>
				<td name="publicitySum" countName="publicitySum" class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.publicitySum }</td>
			</tr>
			<tr>
				<td rowspan="7" class=label align="center" style="width: 130px; height: 98px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">发动干部数</td>
				<td name="publicityCadre" countName="publicitySum-data" countTarget="publicitySum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityCadre }</td>
			</tr>
			<tr>
				<td class=label>发动群众数</td>
				<td name="publicityPeople" countName="publicitySum-data" countTarget="publicitySum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityPeople }</td>
			</tr>
			<tr>
				<td class=label>发布通告数</td>
				<td name="publicityPublicNotice" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityPublicNotice }</td>
			</tr>
			<tr>
				<td class=label>召开座谈会数</td>
				<td name="publicitySymposium" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicitySymposium}</td>
			</tr>
		
			<tr>
				<td class=label>群众举报数</td>
				<td name="publicityPeopleInform" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityPeopleInform}</td>
			</tr>
		
			<tr>
				<td class=label>从中破获刑事案件数</td>
				<td name="publicityCrackedCase" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityCrackedCase}</td>
			</tr>
			<tr>
				<td class=label>从中抓获犯罪嫌疑人数</td>
				<td name="publicityCatchingCriminal" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.publicityCatchingCriminal}</td>
			</tr>
		
		
			<tr>
				<td rowspan="11" class=label align="center" style="width: 140px;">排查发现治安重点地区数</td>
				<td colspan="2" class=label align="center" style="width: 260px;">总数</td>
				<td name="findAreaSum" countName="findAreaSum" class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.findAreaSum}</td>
			</tr>
			<tr>
				<td rowspan="4" align="center" class=label style="width: 130px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">县（市、区）数</td>
				<td name="findAreaCounty" countName="findAreaSum-data" countTarget="findAreaSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaCounty}</td>
			</tr>
			<tr>
				<td class=label>乡镇(街道)数</td>
				<td name="findAreaStreet" countName="findAreaSum-data" countTarget="findAreaSum"  class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaStreet}</td>
			</tr>
		
			<tr>
				<td class=label>村(社区)数</td>
				<td name="findAreaVillage" countName="findAreaSum-data" countTarget="findAreaSum"  class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaVillage}</td>
			</tr>
		
			<tr>
				<td class=label>其他</td>
				<td name="findAreaOther" countName="findAreaSum-data" countTarget="findAreaSum"  class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaOther}</td>
			</tr>
		
			<tr>
				<td class=label rowspan="6" align="center" style="width: 130px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">黑恶势力地区数</td>
				<td name="findAreaVicious" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaVicious}</td>
			</tr>
			<tr>
				<td class=label>杀人爆炸等严重暴力犯罪地区数</td>
				<td name="findAreaViolence" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaViolence}</td>
			</tr>
		
			<tr>
				<td class=label>两抢一盗犯罪地区数</td>
				<td name="findAreaRob" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaRob}</td>
			</tr>
		
			<tr>
				<td class=label>黄赌毒违法犯罪地区数</td>
				<td name="findAreaPoison" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaPoison}</td>
			</tr>
			<tr>
				<td class=label>邪教违法犯罪地区数</td>
				<td name="findAreaHeresy" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaHeresy}</td>
			</tr>
			<tr>
				<td class=label>其他</td>
				<td name="findAreaOtherType" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaOtherType}</td>
			</tr>
		
			<tr>
				<td class=label rowspan="5" align="center" style="width: 130px;">整治结束重点地区数</td>
				<td class=label colspan="2" align="center" style="width: 260px;">总数</td>
				<td name="alreadyRenovateSum" countName="alreadyRenovateSum" class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.alreadyRenovateSum}</td>
			</tr>
			<tr>
				<td class=label rowspan="4" align="center" style="width: 130px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">县（市、区）数</td>
				<td name="alreadyRenovateCounty" countName="alreadyRenovateSum-data" countTarget="alreadyRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateCounty}</td>
			</tr>
			<tr>
				<td class=label>乡镇(街道)数</td>
				<td name="alreadyRenovateStreet" countName="alreadyRenovateSum-data" countTarget="alreadyRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateStreet}</td>
			</tr>
		
			<tr>
				<td class=label>村(社区)数</td>
				<td name="alreadyRenovateVillage" countName="alreadyRenovateSum-data" countTarget="alreadyRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateVillage}</td>
			</tr>
		
			<tr>
				<td class=label>其他</td>
				<td name="alreadyRenovateOther" countName="alreadyRenovateSum-data" countTarget="alreadyRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateOther}</td>
			</tr>
			<tr>
				<td class=label rowspan="5" align="center" style="width: 130px;">正在整治重点地区数</td>
				<td class=label colspan="2" align="center" style="width: 260px;">总数</td>
				<td name="nowRenovateSum" countName="nowRenovateSum" class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.nowRenovateSum}</td>
			</tr>
			<tr>
				<td class=label rowspan="4" align="center" style="width: 130px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">县（市、区）数</td>
				<td name="nowRenovateCounty" countName="nowRenovateSum-data" countTarget="nowRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.nowRenovateCounty}</td>
			</tr>
			<tr>
				<td class=label>乡镇(街道)数</td>
				<td name="nowRenovateStreet" countName="nowRenovateSum-data" countTarget="nowRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.nowRenovateStreet}</td>
			</tr>
		
			<tr>
				<td class=label>村(社区)数</td>
				<td name="nowRenovateVillage" countName="nowRenovateSum-data" countTarget="nowRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.nowRenovateVillage}</td>
			</tr>
		
			<tr>
				<td class=label>其他</td>
				<td name="nowRenovateOther" countName="nowRenovateSum-data" countTarget="nowRenovateSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.nowRenovateOther}</td>
			</tr>
		
			<tr>
				<td class=label rowspan="13" align="center" style="width: 130px; height: 120px;">打击整治数（以重点地区所在乡镇、街道为单位统计）</td>
				<td class=label rowspan="4" align="center" style="width: 130px; height: 98px;">破获刑事案件数</td>
				<td class=label>总数</td>
				<td name="strikeCrackedSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeCrackedSum}</td>
			</tr>
			<tr>
				<td class=label>杀人爆炸等严重暴力犯罪案件数</td>
				<td name="strikeViolence" countName="strikeCrackedSum-data" countTarget="strikeCrackedSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeViolence}</td>
			</tr>
		
			<tr>
				<td class=label>两抢一盗案件数</td>
				<td name="strikeRob" countName="strikeCrackedSum-data" countTarget="strikeCrackedSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeRob}</td>
			</tr>
		
			<tr>
				<td class=label>黄赌毒案件数</td>
				<td name="strikePoison" countName="strikeCrackedSum-data" countTarget="strikeCrackedSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikePoison}</td>
			</tr>
			<tr>
				<td class=label rowspan="6" align="center" style="width: 130px; height: 98px;">抓获犯罪嫌疑人数</td>
				<td class=label>总数</td>
				<td name="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeArrestSum}</td>
			</tr>
			<tr>
				<td class=label>流串犯罪嫌疑人数</td>
				<td name="strikeFlow" countName="strikeArrestSum-data" countTarget="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeFlow}</td>
			</tr>
		
			<tr>
				<td class=label>外来人员犯罪嫌疑人</td>
				<td name="strikeOutside" countName="strikeArrestSum-data" countTarget="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeOutside}</td>
			</tr>
		
			<tr>
				<td class=label>无业人员犯罪嫌疑人</td>
				<td name="strikeUnemployed" countName="strikeArrestSum-data" countTarget="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeUnemployed}</td>
			</tr>
			<tr>
				<td class=label>刑释人员犯罪嫌疑人</td>
				<td name="strikePenal" countName="strikeArrestSum-data" countTarget="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikePenal}</td>
			</tr>
		
			<tr>
				<td class=label>14-25周岁青少年犯罪嫌疑人</td>
				<td name="strikeJuvenile" countName="strikeArrestSum-data" countTarget="strikeArrestSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeJuvenile}</td>
			</tr>
			<tr>
				<td  class=label rowspan="3" align="center" style="width: 130px; height: 72px;">打掉黑恶势力数</td>
				<td class=label>总数</td>
				<td name="strikeAttackSum" countName="strikeAttackSum" class="toDataJosn" style="height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.strikeAttackSum}</td>
			</tr>
			<tr>
				<td class=label>黑社会性质组织数</td>
				<td name="strikeGangdom" countName="strikeAttackSum-data" countTarget="strikeAttackSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeGangdom}</td>
			</tr>
		
			<tr>
				<td class=label>恶势力</td>
				<td name="strikeDadness" countName="strikeAttackSum-data" countTarget="strikeAttackSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.strikeDadness}</td>
			</tr>
		
			<tr>
				<td class=label rowspan="4" align="center" style="width: 130px; height: 96px;">警示数</td>
				<td class=label colspan="2" align="center" style="width: 260px;">总数</td>
				<td name="cautionSum" countName="cautionSum"  class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.cautionSum}</td>
			</tr>
			<tr>
				<td class=label rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">省级警示数</td>
				<td name="cautionCity" countName="cautionSum-data" countTarget="cautionSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.cautionCity}</td>
			</tr>
			<tr>
				<td class=label>市级警示数</td>
				<td name="cautionLand" countName="cautionSum-data" countTarget="cautionSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.cautionLand}</td>
			</tr>
		
			<tr>
				<td class=label>县(市、区)警示数</td>
				<td name="cautionCounty" countName="cautionSum-data" countTarget="cautionSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.cautionCounty}</td>
			</tr>
		
			<tr>
				<td class=label rowspan="4" align="center" style="width: 130px; height: 96px;">挂牌整治数</td>
				<td class=label colspan="2" align="center" style="width: 260px;">总数</td>
				<td name="brandSum" countName="brandSum" class="toDataJosn" style="width: 100px; height: 24px; background-color:#D5D5D5;">${emphasisSafetyDetail.brandSum}</td>
			</tr>
			<tr>
				<td class=label rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
				<td class=label style="width: 130px; height: 24px;">省级挂牌数</td>
				<td name="brandCity" countName="brandSum-data" countTarget="brandSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.brandCity}</td>
			</tr>
			<tr>
				<td class=label>市级挂牌数</td>
				<td name="brandLand" countName="brandSum-data" countTarget="brandSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.brandLand}</td>
			</tr>
			<tr>
				<td class=label>县(市、区)挂牌数</td>
				<td name="brandCounty" countName="brandSum-data" countTarget="brandSum" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.brandCounty}</td>
			</tr>
		</table>
		</div>
		<div>
			<div  class=bottom style="float: left;" id="checkUserContainer">签发人：
				<input class=bottom name="" id="checkUser" <s:if test='"view".equals(mode)'>readonly</s:if>
					maxlength="10" type="text" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" 
					value="${emphasisSafetyDetail.dealPerson}" /> (注意:该项上报时填写)
			</div>
			<div class=bottom style="float: right;">制表人：<input
				name="" id="createTabUser" <s:if test='"view".equals(mode)'>readonly</s:if>
				value="${emphasisSafetyDetail.lister}" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" type="text" />
			</div>
		</div>
	<input id="emphasisSafetyDetailid" value="${emphasisSafetyDetail.id}" type="hidden"/>
	</center>
</div>
<script type="text/javascript">
var reportJudge=0;
var reportJudgeBoolean;
var selectLength = $("#reportTime").find("option").length;

/* if(${sysTime} == ${dailyYear.yearDate}){
	for(var i = ${reportTime}+1 ; i<=selectLength ; i++){
		alert(i);
		$("#reportTime option[value = '"+i+"']").remove();
	}
} */

function inputOnFocus(selfDoc){
	$(document).unbind("keypress");
	$(document).keypress(function(e) {
		if (e.which =='13'){
			selfDoc.closest("tr").nextAll("tr").each(function(i){
				if($(this).find(".inputClick")[0]){
					$(this).find(".inputClick").click();
					return false;
				}
			})
		}
	})
}

function inputBlur(selfDoc){
	selfDoc.keyup();
	var selfDocValue=selfDoc.val();
	var tdDoc = selfDoc.parent();
	tdDoc.html(selfDocValue!=''?selfDocValue:0);
	tdDoc.removeClass("inputOut");
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


function inputKeyDown(selfDoc){

}

function inputKeyOut(selfDoc){
	$(selfDoc).val($(selfDoc).val().replace(/[^0-9]/g,''));
	countXiaoji(selfDoc);
}

var allJson="";
function getParticularData(){
	var json="";
	var alltd=$(".toDataJosn");
	var name;
	var value;
	$.each(alltd,function(i){
		name=$(alltd[i]).attr("name");
		value=$(alltd[i]).text();
		value = value.replace('---', '0');
		allJson=allJson+"emphasisSafetyDetail."+name+"="+value+"&";
		
	});
}
function getOtherData(){
	<s:if test='"edit".equals(mode)'>
		allJson=allJson+"emphasisSafetyDetail.id="+$("#emphasisSafetyDetailid").val()+"&";
	</s:if>
	allJson=allJson+"emphasisSafetyDetail.name="+encodeURIComponent(encodeURIComponent($("#reportName").text()))+"&";
	allJson=allJson+"emphasisSafetyDetail.lister="+encodeURIComponent(encodeURIComponent($("#createTabUser").val()))+"&";
	allJson=allJson+"emphasisSafetyDetail.dealPerson="+encodeURIComponent(encodeURIComponent($("#checkUser").val()))+"&";
	allJson=allJson+"emphasisSafetyDetail.reportTime="+$('#reportTime').val()+"&";
}

function submitData(){
	allJson="";
	if($("#createTabUser").val()==""){
		$.messageBox({message:"请填写制表人!",level:"warn"});
		$("#createTabUser").focus();
		return ;
	}
	if(reportJudge==1){
		$.messageBox({message:$("#reportTime").find("option:selected").text()+"报表已添加!",level:"error"});
		return ;
	}
	if(reportJudge==2){
		$.messageBox({message:$("#reportTime").find("option:selected").text()+"子辖区没有全部上报!",level:"error"});
		return;
	}
	//sunReportJudge();
	//if(reportJudgeBoolean){
	//	return ;
	//}
	getOtherData();
	getParticularData();
	
	if(isFindAreaSumTrueOne()){
    	$.messageBox({message:'"整治结束重点地区数"的"总数 "和 "正在整治重点地区数"的"总数"相加与"排查发现治安重点地区数"的"总数"不一致!',level:"error"});
		return;
    }
    if(isFindAreaSumTrueTwo()){
    	$.messageBox({message:'"黑恶势力地区数"、"杀人爆炸等严重暴力犯罪地区数"、"两抢一盗犯罪地区数"、"黄赌毒违法犯罪地区数"、"邪教组织违法犯罪地区数"、"其他"项相加与"排查发现治安重点地区数"的"总数"不一致!',level:"error"});
		return;
    }

	var submitUrl;
	<s:if test='"add".equals(mode)'>
	submitUrl="${path}/daily/publicSecurityRenovateManage/addEmphasisSafetyDetail.action";
	allJson = "dailyDirectory.id="+${dailyDirectory.id}+"&dailyYear.id="+$("#dailyYear").val()+"&"+allJson;
	</s:if>
	<s:if test='"edit".equals(mode)'>
	submitUrl="${path}/daily/publicSecurityRenovateManage/updateEmphasisSafetyDetail.action";
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
			}else if(!responseData.id){
				$.messageBox({message:responseData,level:"error"});
			}else{
				<s:if test='"add".equals(mode)'>
					$.messageBox({message:"保存报表信息成功!"});
					$("#publicSecurityList").addRowData(responseData.id,responseData,"first");
				</s:if>
				<s:if test='"edit".equals(mode)'>
					$.messageBox({message:"修改报表信息成功!"});
					$("#publicSecurityList").setRowData(responseData.id,responseData);
				</s:if>
				$("#dailyLogMaintanceDialog").dialog("close");
				$("#publicSecurityList").setSelection(responseData.id);
				$("#publicSecurityList").trigger("reloadGrid");
			}
		}
	});
}

function isFindAreaSumTrueOne(){  
	var areaSum=parseInt($("td[name='alreadyRenovateSum']").text())+parseInt($("td[name='nowRenovateSum']").text());
    if($("td[name='findAreaSum']").text()!=areaSum){
    	return true;
    }
    return false;
}

function isFindAreaSumTrueTwo(){ 
	var areaSum=parseInt($("td[name='findAreaVicious']").text())+parseInt($("td[name='findAreaViolence']").text())+parseInt($("td[name='findAreaRob']").text())+parseInt($("td[name='findAreaPoison']").text())+parseInt($("td[name='findAreaHeresy']").text())+parseInt($("td[name='findAreaOtherType']").text());
    if($("td[name='findAreaSum']").text()!=areaSum){
    	return true;
    }
    return false;
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
<s:if test='!"view".equals(mode)'>
function onSelectChanged(){
	<s:if test="reportTypeInternalId!=@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
	$("#selectedMonth").text($("#reportTime").find("option:selected").text());
	</s:if>
	$.ajax({
		url:"${path}/daily/publicSecurityRenovateManage/getEmphasisSafetyDetailByReportTime.action?dailyDirectory.id="+${dailyDirectory.id}+"&reportTime="+$("#reportTime").val(),
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
		url:"${path}/daily/publicSecurityRenovateManage/sunReportJudge.action?dailyDirectory.id="+${dailyDirectory.id}+"&reportTime="+$("#reportTime").val(),
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
</s:if>
$(document).ready(function(){
	var orgLevelForDailyYearTree = $("#orgLevelForDailyYearTree").val();
	<s:if test="reportTypeInternalId==@com.tianque.domain.property.DirectoryReportType@YEAR_REPORT">
		$("#reportTime").append("<option value='1'>"+$('select[id ="dailyYear"] option:selected').text()+"</option>");
	</s:if>
	<s:if test='"add".equals(mode)'>
		$("#reportTime").val(${reportTime});
		onSelectChanged();
	</s:if>
	<s:else>
		$("#reportTime").val(${emphasisSafetyDetail.reportTime});
	</s:else>
	
	<s:if test='!"view".equals(mode)'>
	$("#reportTime").change(onSelectChanged);
	$(".inputClick").click(function(){
		var self = $(this);
		var allInputClick = $(".inputClick");
		if(!self.hasClass("inputOut")){
			self.addClass("inputOut");
			var value=self.text();
			self.html("");
			var inputDataReport=$("<input onblur='inputBlur($(this))' onkeydown='inputKeyDown($(this))' onkeyup='inputKeyOut($(this))' onfocus='inputOnFocus($(this))' title='只能输入非负整数!' style='width:80px' name='' class='reportDataInput' maxLength='12' />");
			self.append(inputDataReport);
			inputDataReport.focus().val(value!=0?value:'');
		}
	});
	</s:if>
	$(".inputClick").focusin(function(){
		$(this).addClass("selected");
	}).focusout(function(){
		$(this).removeClass("selected");
	})
	switch(orgLevelForDailyYearTree){
		case '10':
			$("[name='findAreaCounty'],[name='findAreaStreet'],[name='alreadyRenovateCounty'],[name='alreadyRenovateStreet'],[name='nowRenovateCounty'],[name='nowRenovateStreet']").removeClass("toDataJosn inputClick").unbind("click").text('---');
			break;
		case '20':
			$("[name='findAreaCounty'],[name='alreadyRenovateCounty'],[name='nowRenovateCounty']").removeClass("toDataJosn inputClick").unbind("click").text('---');
			break;
	}
	
});
</script>