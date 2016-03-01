<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript">
<pop:formatterProperty name="level" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITAL_LEVEL" />
<pop:formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND" />
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE" />
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />	
	var isgridBol = false;
	var title=$("#title").html();
	function operatorFormatter(el,options,rowData){
		return "<a href='javascript:viewHospital("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
	}
	function nameFormatter(el,options,rowData){
		aaaa=rowData;
		return "<a href='javascript:viewHospital("+rowData.id+")'><U><font color='#6633FF'>"+rowData.hospitalName+"</font></U></a>";
	}
	function viewHospital(selectedId){
		var rowData = $("#hospitalList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			return;
		}
		$("#hospitalDialog").createDialog({
			width: 900,
			height: 580,
			title: '查看'+title+'信息',
			modal: true,
			url:'${path}/baseinfo/hospitalManage/dispatchOperateByEncrypt.action?mode=view&locationType='+$("#_locationType_").val()+'&location.id='+ id+'&orgId=' + getOrgIdForStat(),
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
		jQuery("#hospitalList").jqGridFunction({
			datatype: "local",
			mtype:'post',
			colModel:[
		    	{name:"id",index:"id",hidden:true},
		    	{name:"encryptId",index:"id",hidden:true,frozen:true},
   	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
    		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	        {name:"hospitalName",index:'hospitalName',label:'医院名称',sortable:true,formatter:nameFormatter},
	        {name:'director',index:'director',sortable:true,label:'医院院长'},
	        {name:'address',index:'address',sortable:true,label:'医院地址'},
	        {name:'affiliatedUnit',index:'affiliatedUnit',sortable:true,label:'所属单位'},
	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格', hidden:true},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true},
	        {name:'mobileNumber',index:'mobileNumber',label:'联系手机',sortable:true},
	        {name:'email',index:'email',label:'电子邮箱',sortable:true,hidden:true},
	        {name:'fax',index:'fax',label:'传真',sortable:true,hidden:true},
	        {name:'kind',index:'kind',label:'医院性质',formatter:kindFormatter,hidden:true},
	        {name:'type',index:'type',label:'医院类型',formatter:typeFormatter,hidden:true}
		  	],
		  	width:860,
		  	height:425,
		  	multiselect:true,
		    loadComplete: afterLoad,
			ondblClickRow: viewHospital
		});
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if('<s:property value="#parameters.isSearch"/>' == 1){
				getServicememberListByOrgId();
			}else{
				fastSearch();
			}
		}

		function getPlaceName(){
			var placeName = "";
			$("#hospitalName").length > 0?placeName = $("#hospitalName").val():placeName = $("#searchByPlaceName").val();
			return placeName;
		}
		
		 function fastSearch(){
			var initParam={
					"orgId":getOrgIdForStat(),
					"searchHospitalVo.organization.id":getOrgIdForStat(),
					"searchHospitalVo.isLogOut":0
					};
			if("请输入医院名称"!=$("#searchByPlaceName").val()){
				initParam={
						"orgId":getOrgIdForStat(),
						"organizationId":getOrgIdForStat(),
						"searchHospitalVo.hospitalName":$("#searchByPlaceName").val(),
						"searchHospitalVo.isLogOut":0
						};
				}
			$("#hospitalList").setGridParam({
				url:'${path}/baseinfo/searchHospital/fastSearch.action',
		        datatype: "json"
				});
			$("#hospitalList").setPostData(initParam);
			$("#hospitalList").trigger("reloadGrid");
		}
		
	 	function getServicememberListByOrgId() {
	 		$("#hospitalList").setGridParam({
				url:"${path}/baseinfo/searchHospital/findHospitalsByQueryCondition.action",
				datatype: "json",
				page:1,
				mtype:"post"
			});
			var postData=$.extend({"organizationId":getCurrentOrgId()},$("#searchHospitalForm").serializeObject());
			if($("#isLock").val()!=""){
				postData = $.extend(postData,{"searchHospitalVo.isEmphasis":$("#isLock").val()});
			}
			$("#hospitalList").setPostData(postData);
		    $("#hospitalList").trigger("reloadGrid");
		    $("#statisticsDialog").dialog('close');
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
		$("#hospitalList").setGridParam({
			 url:'${path}/baseinfo/hospitalManage/hospitalList.action',
			 datatype: "json",
			 page:1
		});
		var isEmphasis = '';
		if($("#isLock").val() == undefined || $("#isLock").val() == null ){
			isEmphasis = 0;
		}else{
			isEmphasis = $("#isLock").val();
		}
		$("#hospitalList").setPostData({
			"orgId":getOrgIdForStat(),
			"organizationId":getOrgIdForStat(),
			"searchHospitalVo.hospitalName":$("#searchByPlaceName").val(),
			"searchHospitalVo.isLogOut":0
	   	});
		$("#hospitalList").trigger("reloadGrid");
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
	    <table id="hospitalList" ></table>
	    <div id="hospitalListPager"></div>
	</div>
	<div id="hospitalDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
</div>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@HOSPITAL_KEY"/>'/>

<div style="display: none;">
<pop:JugePermissionTag ename="hospitalManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>