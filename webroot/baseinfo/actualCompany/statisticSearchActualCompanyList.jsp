<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<%request.setAttribute("supervisorPerson",request.getParameter("supervisorPerson"));%>

<div style="width: 100%;">
<table id="actualCompanyList">
</table>
<div id="actualCompanyListPager"></div>
</div>
<div id="viewActualCompanyDialog"></div>

<script type="text/javascript">
<pop:formatterProperty name="companyType" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" />
<pop:formatterProperty name="supervisoryLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" />
<pop:formatterProperty name="economicNature" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" />
<pop:formatterProperty name="facilitiesCategory" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FACILITIESCATEGORY" />
<pop:formatterProperty name="securityClassification" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SECURITYCLASSIFICATION" />
<pop:formatterProperty name="fireFightingLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" />
var dialogWidth=800;
var dialogHeight=600;	
$(function(){
	var gridOption={
		colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"encryptId",index:"id",hidden:true,frozen:true},
	        {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
		    {name:"companyName",id:"companyName",label:'单位名称',formatter:nameFormatter,width:150,frozen:true},
		    {name:"companyType",label:'单位类别',align:'center',width:80,formatter:companyTypeFormatter},
		    {name:"corporateRepresentative",label:'法人代表',align:'center',width:150},
		    {name:"companyAddress",label:'单位地址',align:'center',width:150},
		    {name:"supervisoryLevel",label:'管理级别',align:'center',width:80,formatter:supervisoryLevelFormatter},
		    {name:"supervisoryDepartment",label:'管理部门',align:'center',width:150},
		    {name:"securityChief",label:'治安负责人',align:'center',width:100},
		    {name:"isKey",label:'是否重点单位',formatter:isKeyFormatter,align:'center',width:100},
		    {name:"fireFightingLevel",label:'消防等级',align:'center',width:100,formatter:fireFightingLevelFormatter},
		    {name:"organization.orgName",label:'所属网格',align:'center',width:100,hidden:true},
		    {name:"idCardNo",label:'身份证号码',align:'center',width:100,hidden:true},
		    {name:"telephone",label:'单位电话',align:'center',width:100,hidden:true},
		    {name:"facsimile",label:'单位传真',align:'center',width:100,hidden:true},
		    {name:"businessLicenseNo",label:'营业执照号码',align:'center',width:100,hidden:true},
		    {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
			{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
			{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		    {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		    {name:"orgCode",label:'组织机构代码',align:'center',width:100,hidden:true},
		    {name:"registeredCapital",label:'注册资本(元)',align:'center',width:100,hidden:true},
		    {name:"economicNature",label:'经济性质',align:'center',width:100,hidden:true,formatter:economicNatureFormatter},
		    {name:"expiryDate",label:'有效期至',align:'center',width:100,hidden:true},
		    {name:"registrationDate",label:'注册日期',align:'center',width:100,hidden:true},
		    {name:"businessScope",label:'经营范围',align:'center',width:100,hidden:true},
		    {name:"registrationAddress",label:'注册地址',align:'center',width:100,hidden:true},
		    {name:"employeesNum",label:'从业人数(人)',align:'center',width:100,hidden:true},
		    {name:"competentDepartment",label:'主管部门',align:'center',width:100,hidden:true},
		    {name:"remark",sortable:false,label:"备注",hidden:true,width:100},
		    {name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
		    {name:"logOutTime",sortable:false,label:"注销时间",align:'center',hidden:true,width:100},
		    {name:"logOutReason",sortable:false,label:"注销原因",align:'center',hidden:true,width:100}
		]
	};

	$("#actualCompanyList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
	  	multiselect:true,
		loadComplete: isEmphasisFormatter
		<pop:JugePermissionTag ename="viewActualCompany">
	    	,ondblClickRow: doubleClickTable
		</pop:JugePermissionTag>
	});
	
	function isKeyFormatter(el, options, rowData){
		var str ="否";
		if(el){
			str="是"
		}
		return str;
	}

	function operatorFormatter(el, options, rowData){
		return "<a href='javascript:doubleClickTable("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}

	function nameFormatter(el, options, rowData){
		return "<a href='javascript:doubleClickTable("+rowData.id+")'><U><font color='#6633FF'>"+rowData.companyName+"</font></U></a>";
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#actualCompanyList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#actualCompanyList").getRowData(idCollection[i]);
				if(ent.isEmphasis==true || ent.isEmphasis=="true"){
					$("#actualCompanyList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}

/*	function companyNameFormatter(el,options,rowData){
		if(rowData.isEmphasis==false||rowData.isEmphasis=='false'){
			return "<font color='#778899'>"+rowData.companyName+"</font>";
		}
		return "<font color='#000'>"+rowData.companyName+"</font>";
	}*/

	if('<s:property value="#parameters.searchType"/>'=='fast'){
		search(getCurrentOrgId());
	}else if('<s:property value="#parameters.searchType"/>'=='advanced'){
		searchActualCompany();
		$("#actualCompanyDialog").dialog("close");
		}
	
	function search(orgId){
		var conditions ="";
		if('请输入单位名称' != $("#searchText").val()){
			conditions=$("#searchText").val();
			}
		var initParam = {
				"organizationId":orgId,
				"searchActualCompanyVo.isEmphasis": 0,
				"searchActualCompanyVo.companyName":conditions
			}
		$("#actualCompanyList").setGridParam({
			url:"${path}/baseinfo/searchActualCompany/fastSearch.action",
			datatype: "json",
			page:1
		});
		$("#actualCompanyList").setPostData(initParam);
		$("#actualCompanyList").trigger("reloadGrid");
	}

	function searchActualCompany(){
		$("#actualCompanyList").setGridParam({
			url:"${path}/baseinfo/searchActualCompany/findActualCompanysByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchActualCompanyForm").serializeObject());
		if($("#isLock").val()!=""){
			postData = $.extend(postData,{"searchActualCompanyVo.isEmphasis":$("#isLock").val()});
		}
		$("#actualCompanyList").setPostData(postData);
	    $("#actualCompanyList").trigger("reloadGrid");
	    $("#actualCompanystatisticsDialog").dialog("close");
	}
});	
	
	function doubleClickTable(selectedId){
		
		var rowData = $("#actualCompanyList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#viewActualCompanyDialog").createDialog({
			width:dialogWidth,
			height:600,
			title:"查看实有单位信息",
			url:'${path}/baseinfo/actualCompanyManage/dispatchOperateByEncrypt.action?mode=view&location.id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	 }
	

</script>
