<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs" style="overflow:auto;">

<div id="gridbox" class="SigmaReport">
</div>
</div>
<div id="pieDialogNew"></div>
<script type="text/javascript">
var grid = null;
function onOrgChanged(){
	if(isTownDownOrganization()) {
	$("#gridbox").html("<h1 align='center'>无统计数据</h1>");
	return ;
	}
		
	columnData();
	
	$(".print").click(function(){
		$("#pieDialogNew").createDialog({
			width:650,
			height:500,
			title:"打印基层组织",
			url:'${path}/statAnalyse/primaryOrganizationStat/statisticSupAreaPrint.jsp?orgId='+getCurrentOrgId(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#pieDialogNew").dialog("close");
			   }
			}
		});
	});
	
	$.ajax({
		url:'${path}/baseInfoStat/areaOrgStatManage/getAreaOrgStatDateList.action?orgId='+getCurrentOrgId(),
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
}

function columnData(){
	var context = {};
	var columns = getColumns();
	grid = new SigmaReport("gridbox",context,columns,null,null,$.getCurrentOrgName()+"组织机构情况统计",$.getCurrentOrgName()+"组织机构情况统计");
}

function getColumns(){
	var columns;
	
	if(isCountry()){
		columns=[
					 {name:"org.orgName",caption:"区域",width:120,mode:"string"}
					,{name:"cityCount",caption:"市（个数）",mode:"number",format:"#"}
					,{name:"districtCount",caption:"县区（个数）",mode:"number",format:"#"}
					,{name:"townCount",caption:"乡镇街道（个数）",mode:"number",format:"#"}
					,{name:"villageCount",caption:"村/社区（个数）",mode:"number",format:"#"}
					,{name:"gridCount",caption:"网格（个数）",mode:"number",format:"#"}
				];
	}
	if(isProvince()){
		columns=[
					 {name:"org.orgName",caption:"区域",width:120,mode:"string"}
					,{name:"districtCount",caption:"县区（个数）",mode:"number",format:"#"}
					,{name:"townCount",caption:"乡镇街道（个数）",mode:"number",format:"#"}
					,{name:"villageCount",caption:"村/社区（个数）",mode:"number",format:"#"}
					,{name:"gridCount",caption:"网格（个数）",mode:"number",format:"#"}
				];
	}
	
	if(isCity()){
		columns=[
					 {name:"org.orgName",caption:"区域",width:120,mode:"string"}
					,{name:"townCount",caption:"乡镇街道（个数）",mode:"number",format:"#"}
					,{name:"villageCount",caption:"村/社区（个数）",mode:"number",format:"#"}
					,{name:"gridCount",caption:"网格（个数）",mode:"number",format:"#"}
				];
	}
	if(isDistrict()){
		columns=[
					 {name:"org.orgName",caption:"区域",width:120,mode:"string"}
					,{name:"villageCount",caption:"村/社区（个数）",mode:"number",format:"#"}
					,{name:"gridCount",caption:"网格（个数）",mode:"number",format:"#"}
				];
	}
	if(isTown()){
		columns=[
					 {name:"org.orgName",caption:"区域",width:120,mode:"string"}
					,{name:"gridCount",caption:"网格（个数）",mode:"number",format:"#"}
				];
	}
	
	
	
	return columns;
}


function print(){
	$("#zongkuangPrint").printArea();
	$("#pieDialogNew").dialog("close");
}
$(document).ready(function(){
	
	onOrgChanged();
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$("#chartsTabs").height(
		$(".ui-layout-center").outerHeight() - $("#thisCrumbs").outerHeight() - 35
	);

})

</script>


