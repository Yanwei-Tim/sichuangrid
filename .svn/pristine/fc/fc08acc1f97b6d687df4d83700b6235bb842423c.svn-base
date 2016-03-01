<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<%request.setAttribute("detailedType",request.getParameter("detailedType"));%>
<%request.setAttribute("mapCurrentOrgId",request.getParameter("orgIdForStat")); %>
<script type="text/javascript">
	var dialogWidth = 800;
	var dialogHeight = 600;

	//字典的自定义标签
	<pop:formatterProperty name="houseStructure" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
	<pop:formatterProperty name="houseUses" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
	
	function viewHouseDialog(rowid){
		if(rowid==null){
	 		return;
		}
		var houseInfo =  $("#actualHouseList").getRowData(rowid);
		$("#actualHouseMaintanceDialog").createDialog({
			width: 800,
			height: 650,
			title:'查看房屋信息',
			modal : true,
			url:'${path}/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseId='+houseInfo.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	function operateFormatter(el,options,rowData){
		return "<a href='javascript:viewHouseDialog("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}
	function isRentalhouseFormatter(el,options,rowData){
		if(true==rowData.isRentalHouse){
			return '是';
		}else{
			return '否';
		}
	}

	function houseCodeFont(el,options,rowData){
		return "<a href='javascript:viewHouseDialog("+rowData.id+")'>"+rowData.houseCode+"</a>";
	}
	function addressCodeFont(el,options,rowData){
		var s = rowData.stateAddress;
		if(rowData.stateAddress==null){
			s = "";
			return "<a title='"+s+"'href='javascript:viewHouseDialog("+rowData.id+")'>"+rowData.address+"</a>";
		}
		return "<a style='color:red;' title='"+s+"'href='javascript:viewHouseDialog("+rowData.id+")'><image x='96' y='37' width='12' height='12' src='${resource_path}/resource/system/images/issue/icon_Emerignce.png' '></image>"+rowData.address+"</a>";
	}
	
	var gridOption = {
			colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"organization.id",index:"organization.id",hidden:true,frozen:true},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"operator", index:'id', label:'操作',formatter:operateFormatter,width:65,frozen:true,sortable:false,align:'center' },
	        {name:'address',index:'address',label:'房屋地址',title:false,formatter:addressCodeFont,sortable:false,width:150},
	        {name:'houseCode-Font',index:'houseCode',label:'房屋编号',formatter:houseCodeFont,width:100,frozen:true,hidden:true},
	        {name:'houseCode',index:'houseCode',label:'房屋编号',width:100,hidden:true,frozen:true},
	        {name:'houseOwner',index:'houseOwner',label:'代表人/业主',width:120,frozen:true},
	        {name:'name',index:'name',label:'产权人姓名',width:80,frozen:true},
	        {name:'certificateNumbe',index:'certificateNumbe',label:'产权人证件号码',width:100},
	        {name:"houseUses",index:'houseUses',label:'房屋用途', width:80,formatter:houseUsesFormatter},
	        {name:'isRentalHouse',index:'isRentalHouse',label:'是否出租房',width:60,align:"center",formatter:isRentalhouseFormatter},
	        {name:'updateDate', sortable:false, label:'数据更新时间', width:150,align:"center"},
	        {name:'houseArea',index:'houseArea',label:'住房面积(㎡)',hidden:true,width:100},
	        {name:'houseStructure',index:'houseStructure',label:'住房结构',hidden:true,sortable:false,formatter:houseStructureFormatter,width:110},
            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',hidden:true,sortable:false,width:230}
		]
	}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp">
	<jsp:param value="ActualHouse" name="moduleName"/>
	<jsp:param value="房屋信息" name="moduleCName"/>
	<jsp:param value="${searchType}" name="searchType"/>
	<jsp:param value="${detailedType}" name="detailedType"/>
	<jsp:param value="${mapCurrentOrgId }" name="mapCurrentOrgId"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@HANDICAPPED_KEY"/>'/>
		<input type="hidden" id="_supervisorName_" 									  
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@HANDICAPPED_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" 
	value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@HANDICAPPED_KEY)" escape="false"/>'/>
</div>