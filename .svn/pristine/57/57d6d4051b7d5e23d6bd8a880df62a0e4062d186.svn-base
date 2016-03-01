<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<select id="partyOrgType_seach">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE" />
		</select>	
		<input class="basic-input" type="text" value="请输入上级党组织名称" onblur="value=(this.value=='')?'请输入上级党组织名称':this.value;"  onfocus="value=(this.value=='请输入上级党组织名称')?'':this.value;" name="searchSuperiorText" id="searchSuperiorText" maxlength="20" class="searchtxt" style="width: 180px;" />
		<a id="search_Superior" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>查询</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="superiorOrgansPartyList">
		</table>
		<div id="superiorOrgansPartyListPager"></div>
	</div>
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 360;
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"></pop:formatterProperty>
<pop:formatterProperty name="department" domainName="@com.tianque.domain.property.PropertyTypes@BELONG_ORG"></pop:formatterProperty>

// 改变组织机构树时加载列表
function onOrgChangedSuperior(searchVo){
	var initParam = {
		"searchOrgansPartyVo.orgid":"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getId()'/>"
	}
	var	pageData=$.extend(searchVo,initParam);
	$("#superiorOrgansPartyList").setGridParam({
 		url:'${path}/organsPartyManage/findOrgansPartyPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#superiorOrgansPartyList").setPostData(pageData);
	$("#superiorOrgansPartyList").trigger("reloadGrid");
}

$(function(){
	if($("#organsPartyType option:selected").text()=="党总支"){
		$("#partyOrgType_seach option").each(function(){
			if($(this).text() !="党委"){
				$(this).remove();
			}
		});
	}else if($("#organsPartyType option:selected").text()=="党支部"){
		$("#partyOrgType_seach option").each(function(){
			if($(this).text() !="党委" && $(this).text() !="党总支" ){
				$(this).remove();
			}
		});
	}
	
	// 生成列表
	$("#superiorOrgansPartyList").jqGridFunction({
		mtype:'post',
		datatype:"local",
	   	height:200,
	   	colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:'type', index:'type',label:'党组织类型',formatter:typeFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
	    	{name:'name', index:'name',label:'党组织名称', width:250, sortable:false, align:'center', hidden:false},
	    	{name:'department', index:'department',label:'所属部门',formatter:departmentFormatter, width:100, sortable:false, align:'center', hidden:false}
	   	],
	  	multiselect:false
	  
	});
	jQuery("#superiorOrgansPartyList").jqGrid('setFrozenColumns');
		onOrgChangedSuperior({"searchOrgansPartyVo.type":$('#partyOrgType_seach option:selected').val()});
	
	$("#partyOrgType_seach").change(function(){
		var fastSearchVal = $("#searchSuperiorText").val();
		if(fastSearchVal == "请输入上级党组织名称"){
			fastSearchVal = '';
		} 
		onOrgChangedSuperior({"searchOrgansPartyVo.type":$('#partyOrgType_seach option:selected').val(),"searchOrgansPartyVo.name":fastSearchVal});
	});
	
	$("#search_Superior").click(function(){
		var fastSearchVal = $("#searchSuperiorText").val();
		if(fastSearchVal == '请输入上级党组织名称') return;
		onOrgChangedSuperior({"searchOrgansPartyVo.type":$('#partyOrgType_seach option:selected').val(),"searchOrgansPartyVo.name":$("#searchSuperiorText").val()});
	});
	
	
});

</script>
