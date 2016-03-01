<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
<!--
#tb {
	border: 1px;
	border-color: black;
	border-style: solid;
}

#tb td {
	border: 1px;
	border-color: black;
	border-style: solid;
}
-->
</style>
<center>
<div id="throughoutHidden">
<form id="dailyLog-form" method="post" action="">
	<input id="mode" type="hidden" name="mode" value="${mode}" />
	<input id="dailyDailyDirectoryId" type="hidden" name="emphasisSafetyDetail.dailyDirectory.id" value="${emphasisSafetyDetail.dailyDirectory.id}" />
</form>
</div>

<form id="societySafeCheck-form" method="post" action="">
<div>
	<table id="tb" border="1"
		style="table-layout: fixed; border-collapse: collapse;">
		<tr>
	  		<td colspan=4 height=44 class="toDataJosn" id="reportName" name="name" style='text-align:center;border:0px;height:33.0pt;font-size:16px; font-weight:bold;'>${emphasisSafetyDetail.name}</td>
	 	</tr>
	 	<tr>
		  	<td colspan=2 style='height:32.25pt;border:0px;border-bottom:1px solid gray;' id="areacode">
		  		填报单位（盖章）：${organization.orgName}
		  	</td>
		  	<td colspan=2 style="border:0px; border-bottom:1px solid gray; text-align:right;margin-right:20px;">填报时间：
		  	<select id="selectMonth">
			<s:iterator value="monthmap.keySet()" id="key">
				<option value="<s:property value="key"/>"><s:property value="monthmap.get(#key)"/></option>
			</s:iterator>
			</select></td>
	 	</tr>
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
			<td class=label style="width: 130px; height: 24px;">县市区数</td>
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
			<td class=label>邪教组织违法犯罪地区数</td>
			<td name="findAreaHeresy" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaHeresy}</td>
		</tr>
		<tr>
			<td class=label>其他</td>
			<td name="findAreaOtherType" class="toDataJosn inputClick" style="height: 24px;">${emphasisSafetyDetail.findAreaOtherType}</td>
		</tr>
	
		<tr>
			<td rowspan="5" align="center" style="width: 130px;">已整治重点地区数</td>
			<td colspan="2" align="center" style="width: 260px;">总数</td>
			<td name="alreadyRenovateSum" class="toDataJosn" style="width: 100px; height: 24px;">${emphasisSafetyDetail.alreadyRenovateSum}</td>
		</tr>
		<tr>
			<td rowspan="4" align="center" style="width: 130px;">其中</td>
			<td style="width: 130px; height: 24px;">县市区数</td>
			<td name="alreadyRenovateCounty" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateCounty}</td>
		</tr>
		<tr>
			<td>乡镇(街道)数</td>
			<td name="alreadyRenovateStreet" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateStreet}</td>
		</tr>
	
		<tr>
			<td>村(社区)数</td>
			<td name="alreadyRenovateVillage" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateVillage}</td>
		</tr>
	
		<tr>
			<td>其他</td>
			<td name="alreadyRenovateOther" style="height: 24px;">${emphasisSafetyDetail.alreadyRenovateOther}</td>
		</tr>
		<tr>
			<td rowspan="5" align="center" style="width: 130px;">正在整治重点地区数</td>
			<td colspan="2" align="center" style="width: 260px;">总数</td>
			<td name="nowRenovateSum" class="toDataJosn" style="width: 100px; height: 24px;">${emphasisSafetyDetail.nowRenovateSum}</td>
		</tr>
		<tr>
			<td rowspan="4" align="center" style="width: 130px;">其中</td>
			<td style="width: 130px; height: 24px;">县市区数</td>
			<td name="nowRenovateCounty" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.nowRenovateCounty}</td>
		</tr>
		<tr>
			<td>乡镇(街道)数</td>
			<td name="nowRenovateStreet" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.nowRenovateStreet}</td>
		</tr>
	
		<tr>
			<td>村(社区)数</td>
			<td name="nowRenovateVillage" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.nowRenovateVillage}</td>
		</tr>
	
		<tr>
			<td>其他</td>
			<td name="nowRenovateOther" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.nowRenovateOther}</td>
		</tr>
	
		<tr>
			<td rowspan="13" align="center" style="width: 130px; height: 120px;">打击整治数</td>
			<td rowspan="4" align="center" style="width: 130px; height: 98px;">破获刑事案件数</td>
			<td>总数</td>
			<td name="strikeCrackedSum" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeCrackedSum}</td>
		</tr>
		<tr>
			<td>杀人爆炸等严重暴力案件数</td>
			<td name="strikeViolence" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeViolence}</td>
		</tr>
	
		<tr>
			<td>两抢一盗案件数</td>
			<td name="strikeRob" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeRob}</td>
		</tr>
	
		<tr>
			<td>黄赌毒案件数</td>
			<td name="strikePoison" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikePoison}</td>
		</tr>
		<tr>
			<td rowspan="6" align="center" style="width: 130px; height: 98px;">抓获犯罪嫌疑人数</td>
			<td>总数</td>
			<td name="strikeArrestSum" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeArrestSum}</td>
		</tr>
		<tr>
			<td>流串犯罪嫌疑人数</td>
			<td name="strikeFlow" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeFlow}</td>
		</tr>
	
		<tr>
			<td>外来人员</td>
			<td name="strikeOutside" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeOutside}</td>
		</tr>
	
		<tr>
			<td>无业人员</td>
			<td name="strikeUnemployed" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeUnemployed}</td>
		</tr>
		<tr>
			<td>刑释人员</td>
			<td name="strikePenal" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikePenal}</td>
		</tr>
	
		<tr>
			<td>14-25周岁青少年</td>
			<td name="strikeJuvenile" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeJuvenile}</td>
		</tr>
		<tr>
			<td rowspan="3" align="center" style="width: 130px; height: 72px;">打掉黑恶势力数</td>
			<td>总数</td>
			<td name="strikeAttackSum" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeAttackSum}</td>
		</tr>
		<tr>
			<td>黑社会性质组织数</td>
			<td name="strikeGangdom" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeGangdom}</td>
		</tr>
	
		<tr>
			<td>恶势力</td>
			<td name="strikeDadness" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.strikeDadness}</td>
		</tr>
	
		<tr>
			<td rowspan="4" align="center" style="width: 130px; height: 96px;">警示数</td>
			<td colspan="2" align="center" style="width: 260px;">总数</td>
			<td name="cautionSum" style="width: 100px; height: 24px;">${emphasisSafetyDetail.cautionSum}</td>
		</tr>
		<tr>
			<td rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
			<td style="width: 130px; height: 24px;">省级警示数</td>
			<td name="cautionCity" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.cautionCity}</td>
		</tr>
		<tr>
			<td>地市警示数</td>
			<td name="cautionLand" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.cautionLand}</td>
		</tr>
	
		<tr>
			<td>县市区警示数</td>
			<td name="cautionCounty" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.cautionCounty}</td>
		</tr>
	
		<tr>
			<td rowspan="4" align="center" style="width: 130px; height: 96px;">挂牌整治数</td>
			<td colspan="2" align="center" style="width: 260px;">总数</td>
			<td name="brandSum" class="toDataJosn" style="width: 100px; height: 24px;">${emphasisSafetyDetail.brandSum}</td>
		</tr>
		<tr>
			<td rowspan="3" align="center" style="width: 130px; height: 72px;">其中</td>
			<td style="width: 130px; height: 24px;">省级挂牌数</td>
			<td name="brandCity" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.brandCity}</td>
		</tr>
		<tr>
			<td>地市挂牌数</td>
			<td name="brandLand" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.brandLand}</td>
		</tr>
		<tr>
			<td>县市区挂牌数</td>
			<td name="brandCounty" class="toDataJosn" style="height: 24px;">${emphasisSafetyDetail.brandCounty}</td>
		</tr>
	</table>

	<br />
	<div>
		<div style="float: left;margin-left: 80px">建表人：<input
			name="" id="createTabUser"
			value="" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" type="text" />
		</div>
		<div style="float: left;margin-left: 80px">制表时间：<input
			name="" id="dealDate"
			value="" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" type="text" />
		</div>
		<div style="float: left;margin-left: 20px" id="checkUserContainer">签发人：
			<input name="" id="checkUser"  
				maxlength="10" type="text" style="border-width: 0;BORDER-BOTTOM:black 1px solid;" 
				value="" /> (注意:该项上报时填写)
		</div>
	</div>
</div>
</form>
</center>
<script type="text/javascript">
	var allJson="";
	$(document).ready(function(){
		$("#selectMonth").change(function(){
			//TODO
			
		});
		$('#dealDate').datePicker({
			yearRange: '1930:2060',
			dateFormat:'yy-mm-dd',
			maxDate:'+0y'
		});
		$("#throughoutHidden").hide();
	});

	function getParticularData(){
		var json;
		var alltd=$(".toDataJosn");
		var name;
		var value;
		$.each(alltd,function(i){
			name=$(alltd[i]).attr("name");
			value=$(alltd[i]).text();
			allJson=allJson+"emphasisSafetyDetail."+name+"="+value+"&";
		});
	}
	function getOtherData(){
		allJson=allJson+"emphasisSafetyDetail.name="+$("#reportName").text()+"&";
		allJson=allJson+"emphasisSafetyDetail.lister="+$("#createTabUser").val()+"&";
		allJson=allJson+"emphasisSafetyDetail.dealPerson="+$("#checkUser").val()+"&";
		allJson=allJson+"emphasisSafetyDetail.dealDate="+$('#dealDate').val()+"&";
	}
	function submitData(){
		if($("#createTabUser").val()==""){
			$.messageBox({message:"请填写制表人",level:"error"});
			return ;
		}
		if($("#dealDate").val()==""){
			$.messageBox({message:"请填写制表时间",level:"error"});
			return ;
		}
		getOtherData();
		getParticularData();
		var submitUrl;
		<s:if test='"add".equals(mode)'>
		submitUrl="${path}/workingRecord/publicSecurityRenovateManage/addEmphasisSafetyDetail.action?dailyDirectory.id="+dailyDirectoryId+"&dailyYear.id="+$("#dailyYear").val()+"&"+allJson;
		</s:if>
		<s:if test='"edit".equals(mode)'>
		submitUrl="${path}/workingRecord/publicSecurityRenovateManage/updateEmphasisSafetyDetail.action?"+allJson;
		</s:if>
		$.ajax({
			url: submitUrl,
			success:function(responseData){
				if(responseData==null){
					$.messageBox({message:"保存报表信息失败!"});
				}else{
					$.messageBox({message:"保存报表信息成功!"});
					$("#dailyLogMaintanceDialog").dialog("close");
					$("#dailyLogList").addRowData(responseData.id,responseData,"first");
					$("#dailyLogList").setSelection(responseData.id);
				}
			}
		});
	}
</script>