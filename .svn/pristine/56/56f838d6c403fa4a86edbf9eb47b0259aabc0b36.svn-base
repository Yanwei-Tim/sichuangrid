<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript">
	<pop:formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" />
	<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" />
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	var isgridBol = false;
	var title=$("#title").html();
	function operatorFormatter(el,options,rowData){
		return "<a href='javascript:viewSchoolInfo("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}
	function nameFormatter(el,options,rowData){
		return "<a href='javascript:viewSchoolInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.chineseName+"</font></U></a>";
	}
	function viewSchoolInfo(selectedId){
		var rowData = $("#schoolList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			return;
		}
	
		$("#schoolDialog").createDialog({
			width: 900,
			height: 580,
			title: '查看'+title+'信息',
			modal: true,
			url:'${path}/baseinfo/schoolManage/dispatchOperateByEncrypt.action?mode=view&locationType='+$("#_locationType_").val()+'&location.id='+ id+'&orgId=' + getOrgIdForStat(),
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	//关注程度显示效果
	function attentionExtentColor(el,options,rowData){
		var displayName=attentionExtentFormatter(el,options,rowData);
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='中等')
			return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
	}
	$(document).ready(function(){
		jQuery("#schoolList").jqGridFunction({
			datatype: "local",
			mtype:'post',
			colModel:[
		    	{name:'id', index:'id', hidden:true },
		    	{name:'encryptId', index:'id',frozen:true, hidden:true},
		    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:50,frozen:true,sortable:false,align:'center' },
		    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	  			{name:'chineseName',index:'chineseName',label:'学校名称',formatter:nameFormatter,width:180,sortable:true},
	  			{name:'address',index:'address',label:'学校地址',width:200,sortable:true},
	  			{name:'president',index:'president',label:'校长',width:100,sortable:true},
	  			{name:'englishName',index:"englishName",label:'英文名称',width:100,hidden:true,sortable:true},
	  			{name:"organization.orgName", index:"orgInternalCode",label:"所属网格",width:200, hidden:true},
	  			{name:'kind',index:'kind',label:'学校性质',sortable:true, formatter:kindFormatter,width:100},
	  			{name:'type',index:'type',label:'学校类型',sortable:true, formatter:typeFormatter,width:100},
	 			{name:'personLiable', index:'contactName',label:'法制副校长',sortable:true,width:100},
	 			
	 			{name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
				{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
				{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	 			{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	  			{name:'personLiableTelephone',index:'telephone', label:'联系电话',hidden:true,sortable:true,width:140},
	  			{name:'personLiableMobileNumber',index:'mobileNumber', label:'联系手机',sortable:true,hidden:true,width:100},
	  			{name:"webSite",index:"webSite",label:"学校网址",hidden:true,width:130,sortable:true},
	  			{name:"fax",index:"fax",label:"传真",hidden:true,width:100,sortable:true},
	  			{name:"email",index:"email",label:"电子邮箱",hidden:true,width:100,sortable:true},
	  			{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
		  	],
		  	width:860,
		  	height:425,
		  	multiselect:true,
		    loadComplete: afterLoad,
			ondblClickRow: viewSchoolInfo
		});
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if('<s:property value="#parameters.isSearch"/>' == 1){
				getServicememberListByOrgId();
			}else if('<s:property value="#parameters.isSearch"/>' == 0){
				fastSearch();
			}else{
				onOrgChangedForStat(getOrgIdForStat(),isGrid());
			}
		}

		function getPlaceName(){
			var placeName = "";
			$("#schoolChineseName").length > 0?placeName = $("#schoolChineseName").val():placeName = $("#searchByPlaceName").val();
			return placeName;
		}

		 function fastSearch(){
			var initParam={
					"orgId":getOrgIdForStat(),
					"school.organization.id":getOrgIdForStat(),
					"school.isLogOut":0
					};
			if("请输入学校名称"!=$("#searchByPlaceName").val()){
				initParam={
						"orgId":getOrgIdForStat(),
						"school.organization.id":getOrgIdForStat(),
						"school.chineseName":$("#searchByPlaceName").val(),
						"school.isLogOut":0
						};
				}
			$("#schoolList").setGridParam({
				url:'${path}/baseinfo/searchSchool/searchSchool.action',
		        datatype: "json"
				});
			$("#schoolList").setPostData(initParam);
			$("#schoolList").trigger("reloadGrid");
		}
		
	 	function getServicememberListByOrgId() {
			var schoolChineseName=getPlaceName();
			var schoolKindId=$('#conditionKindId').val();
			var schoolTypeId=$('#conditionTypeId').val();
			var schoolAddress=$('#schoolAddress').val();
			var schoolEnglishName = $("#schoolEnglishName").val();
			var schoolWebSite = $("#schoolWebSite").val();
			var schoolPresident = $("#schoolPresident").val();
			var schoolFax = $("#schoolFax").val();
			var schoolEmail = $("#schoolEmail").val();
			var schoolPersonLiable = $("#schoolPersonLiable").val();
			var schoolPersonLiableTelephone = $("#schoolPersonLiableTelephone").val();
			var schoolPersonLiableMobileNumber = $("#schoolPersonLiableMobileNumber").val();
			var schoolMinAtSchoolHeadcount = $("#schoolMinAtSchoolHeadcount").val();
			var schoolMaxAtSchoolHeadcount = $("#schoolMaxAtSchoolHeadcount").val();
			var hasServiceTeamMember = $("#searchHasServiceTeamMember").val();
			var hasServiceRecord = $("#searchHasServiceRecord").val();
            var isEmphasis=$("#isLock").val();
			$("#schoolList").setGridParam({
					        url:'${path}/baseinfo/searchSchool/searchSchool.action',
					        datatype: "json"
		 	});
		 	if(isEmphasis==-1){
		 		$("#schoolList").setPostData({
					"orgId":getOrgIdForStat(),
					"school.chineseName" : schoolChineseName,
				   	"school.kind.id" : schoolKindId,
				   	"school.type.id" : schoolTypeId,
					"school.address" : schoolAddress,
					"school.englishName":schoolEnglishName,
					"school.webSite":schoolWebSite,
					"school.president":schoolPresident,
					"school.fax":schoolFax,
					"school.email":schoolEmail,
					"school.personLiable":schoolPersonLiable,
					"school.personLiableTelephone":schoolPersonLiableTelephone,
					"school.personLiableMobileNumber":schoolPersonLiableMobileNumber,
					"school.minAtSchoolHeadcount":schoolMinAtSchoolHeadcount,
					"school.maxAtSchoolHeadcount":schoolMaxAtSchoolHeadcount,
					"school.hasServiceTeamMember":hasServiceTeamMember,
					"school.hasServiceRecord":hasServiceRecord
				});
			}else{
				$("#schoolList").setPostData({
					"orgId":getOrgIdForStat(),
					"school.chineseName" : schoolChineseName,
				   	"school.kind.id" : schoolKindId,
				   	"school.type.id" : schoolTypeId,
					"school.address" : schoolAddress,
					"school.englishName":schoolEnglishName,
					"school.webSite":schoolWebSite,
					"school.president":schoolPresident,
					"school.fax":schoolFax,
					"school.email":schoolEmail,
					"school.personLiable":schoolPersonLiable,
					"school.personLiableTelephone":schoolPersonLiableTelephone,
					"school.personLiableMobileNumber":schoolPersonLiableMobileNumber,
					"school.minAtSchoolHeadcount":schoolMinAtSchoolHeadcount,
					"school.maxAtSchoolHeadcount":schoolMaxAtSchoolHeadcount,
					"school.hasServiceTeamMember":hasServiceTeamMember,
					"school.hasServiceRecord":hasServiceRecord,
					"school.isEmphasis":$("#isLock").val()==1?true:false
				});
			}
			$("#schoolList").trigger("reloadGrid");
			$("#statisticsDialog").dialog("close");
		}

	});


    //当切换责任区的时候根据切换的责任区进行查询
	function onOrgChangedForStat(orgId,isgrid){
		if(isgrid){
			$("#add").buttonEnable();
		}else{
			$("#add").buttonDisable();
		}
		isgridBol = isgrid;
		$("#schoolList").setGridParam({
			 url:'${path}/baseinfo/schoolManage/schoolList.action',
			 datatype: "json",
			 page:1
		});
		var isEmphasis = '';
		if($("#isLock").val() == undefined || $("#isLock").val() == null ){
			isEmphasis = 0;
		}else{
			isEmphasis = $("#isLock").val();
		}
		$("#schoolList").setPostData({
			"orgId":getOrgIdForStat(),
			"school.chineseName" : " ",
		   	"school.kind.id" : " ",
		   	"school.type.id" : " ",
			"school.address" : " ",
			"school.isEmphasis" :0
	   	});
		$("#schoolList").trigger("reloadGrid");
	}

	function afterLoad(){
		isEmphasisFormatter();
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#schoolList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#schoolList").getRowData(idCollection[i]);
				if(ent.isEmphasis=="true"){
				$("#schoolList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}

	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
		}
	}
</script>
<div class="content">
	<div style="width: 100%;">
	    <table id="schoolList" ></table>
	    <div id="schoolListPager"></div>
	</div>
	<div id="schoolDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
</div>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@SCHOOL_KEY"/>'/>

<div style="display: none;">
<pop:JugePermissionTag ename="schoolManageMent">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>