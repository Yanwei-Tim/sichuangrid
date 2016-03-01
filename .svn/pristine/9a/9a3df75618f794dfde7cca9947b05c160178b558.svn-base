<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style>
.highcharts-container{
	height:auto !important;
}
</style>
<div id="taskListinfoTrend" class="SigmaReport"></div>
<script>
		var situationType = 0 ;
		var selectTypes='';
		if(basesicTypes=='' || basesicTypes==undefined){
			basesicTypes="pandect";
			selectTypes = basesicTypes;
		}else if(basesicTypes!='' && detailTypes=='' && subTypes==''){
			selectTypes = basesicTypes;
		}else if(detailTypes!='' && subTypes=='' ){
			selectTypes = detailTypes;
		}else if(subTypes!=''){
			selectTypes = subTypes;
		}
		
		if((basesicTypes=='a_floating' && detailTypes=='' && subTypes=='') || (basesicTypes=='a_floating' && detailTypes=='a_propagandaAndVerification')){
			situationType=1;
		}else if(basesicTypes=='special' || basesicTypes=='e_positiveInfoRecord' || basesicTypes=='d_termerRecord' || basesicTypes=='c_mentalPatientTask'||basesicTypes=='b_druggyTask'){
			situationType=3;
		}else if(basesicTypes=='pandect'){
			situationType=0;
		}else{
			situationType=2;
		}
		$("#taskListinfoTrend").lineChart({
		url: "${(path)!}/plugin/statistics/generalSituationManage/getTaskListOfMapTrend.action?taskListStatisticsVo.situationType="+situationType+"&taskListStatisticsVo.selectTypes="+selectTypes+"&taskListStatisticsVo.searchDateType=0&taskListStatisticsVo.isSegin="+signType+"&taskListStatisticsVo.year="+year+"&taskListStatisticsVo.month="+month+"&taskListStatisticsVo.orgName="+organizationName,
		moduleName:""
		});

</script>
