<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container_24">
	<div class="grid_3 lable-right"><label class="form-lbl">家庭户口号：</label>
	</div>
	<div class="grid_5"><input type="text" id="houseFamilyAccountNumber" value="${houseFamily.censusRegisterFamily.accountNumber}" maxlength="20" class="form-txt" disabled="disabled"/>
	</div>
	<div class="grid_3 lable-right"><label class="form-lbl">户主姓名：</label>
	</div>
	<div class="grid_5"><input type="text" id="houseFamilyHouseHoldName" value="${houseFamily.censusRegisterFamily.householdName}" maxlength="20" class="form-txt" disabled="disabled"/>
	</div>
	<div class="grid_4 lable-right"><label class="form-lbl">户主身份证号码：</label>
	</div>
	<div class="grid_4"><input type="text" id="houseFamilyIdCardNo" value="${houseFamily.censusRegisterFamily.idCardNo}" maxlength="20" class="form-txt" disabled="disabled"/>
	</div>
	
	<input type="hidden" id="houseFamilyId" value="${houseFamily.censusRegisterFamily.id}" maxlength="20" class="form-txt" disabled="disabled"/>
	<input type="hidden" id="orgId" value="${orgId}" maxlength="20" class="form-txt" disabled="disabled"/>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">与户主关系：</label>
	</div>
	<div class="grid_4">
		<select name="newHouseHold.relationShipWithHead.id"
			id="newRelationShipWithHeadId" class="form-select 
			{required:true,messages:{required:'请选择与户主关系'}}" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@HEADER" defaultValue="${population.relationShipWithHead.id}" />
		</select>
	</div>
	<div class="grid_3 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_5">
		<input type="text" id="newIdCardNo" maxlength="18" class="form-txt {required:true,exculdeParticalChar:true,exsistedCompanyName:true,messages:{required:'请输入场所名称',exculdeParticalChar:'不能输入非法字符',exsistedCompanyName:'场所名称在该网格下已经存在，请重新输入'}}"/>
	</div>
	<div class="btnbanner btnbannerData">
		<a id="addMem" href="javascript:;"><span>添加</span></a>
	</div>
	<div class='grid_24'>
		<b>现有成员</b>
	</div>
	<div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="houseFamilyMemberList"></table>
			<div id="houseFamilyMemberListPager"></div>
		</div>
		<div id="houseFamilyMemberDialog"></div>
	</div>
	<div class='clearLine'></div>
	
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
$(document).ready(function(){
	loadList();
	$("#addMem").click(function(){
		var newIdCardNo = $("#newIdCardNo").val();
		var newRelationShipWithHeadId = $("#newRelationShipWithHeadId").val();
		var accountNumber = $("#houseFamilyAccountNumber").val();
		if(newRelationShipWithHeadId == '' || newRelationShipWithHeadId == null){
			$.messageBox({ message:"与户主关系字段为空!",level:'warn'});
			return;
		}
		if(newIdCardNo == '' || newIdCardNo == null){
			$.messageBox({ message:"身份证号码字段为空!",level:'warn'});
			return;
		}else{
			$.ajax({
				type:'post',
				url:'${path}/baseinfo/houseFamily/haveRepatCardOrNo.action',
				data:{'card':newIdCardNo},
				success:function(data){
					if(data){
						addFun();
					}else{
						$.messageBox({ message:"此身份证号码不存在!",level:'warn'});
						return;
					}
				}
			});
		}
	});
	function loadList(){
		$("#houseFamilyMemberList").jqGridFunction({
			width:600,
			height:300,
			datatype: "local",
			colModel:[
				{name:"id",label:"id",hidden:false},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"name",index:"name",label:"姓名",formatter:nameFont,hidden:false},
				{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
				{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
				{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
				{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false},
				{name:'operation',label:"操作",width:80,formatter:operateFormatter,frozen:true,sortable:false,align:"center"}
			],
			multiselect:false
		});
		$("#houseFamilyMemberList").jqGrid('setFrozenColumns');
		var initParam = {
			"orgId": $("#orgId").val(),
			"houseFamily.id": $("#houseFamilyId").val()
		}
		$("#houseFamilyMemberList").setGridParam({
			url:'${path}/baseinfo/householdStaff/findHouseholdStaffByOrgIdAndId.action?householdStaffVo.logout=0&exceptHead=true',
			datatype: "json",
			page:1
		});
		$("#houseFamilyMemberList").setPostData(initParam);
		$("#houseFamilyMemberList").trigger("reloadGrid");
	}
	function addFun(){
		var newIdCardNo = $("#newIdCardNo").val();
		var newRelationShipWithHeadId = $("#newRelationShipWithHeadId").val();
		var accountNumber = $("#houseFamilyAccountNumber").val();
		$.confirm({
			title:"确认添加",
			message:'添加后将脱离原有家庭,确认添加吗?',
			width: 400,
			okFunc: function(){
				$.ajax({
					url:'${path}/baseinfo/houseFamily/addFamilyMemberByIdCardNo.action?orgId='+$("#orgId").val()+'&houseFamily.id='+$("#houseFamilyId").val()+'&newFamilyMember.idCardNo='+newIdCardNo+'&newFamilyMember.relationShipWithHead.id='+newRelationShipWithHeadId+'&newFamilyMember.accountNumber='+accountNumber,
					success:function(data){
						if(data == true || data=="true"){
							/**
							var oldNum = $("#houseFamilyMemberList").getGridParam("records");
							$("#houseFamilyMemberList").trigger("reloadGrid");
							var newNum = $("#houseFamilyMemberList").getGridParam("records");
							if(oldNum == newNum){
								$.messageBox({ level:"warn",message:"此人在该网格下不存在或在该户籍下已存在"});
							}else{
								*/
							$.messageBox({ message:"成功新增家庭成员!"});
							$("#houseFamilyMemberList").trigger("reloadGrid");
							$("#houseFamilyList").trigger("reloadGrid");
							return;
						}else{
							$.messageBox({ message:data,level: "error"});
						}
			        }
				});
			}
		});
		
	}
	function operateFormatter(el, options, rowData){
		return "<a href='javascript:removeFun("+rowData.id+")'><U><font color='#6633FF'>移除</font></U></a>";
	}
});
function removeFun(rowId){
	if(rowId == null || rowId == undefined){
		return;
	}
	var newIdCardNo = $("#houseFamilyMemberList").getRowData(rowId).idCardNo;
	$("#houseFamilyMemberDialog").createDialog({
		width:400,
		height:300,
		title:'移除成员',
		url:'${path}/baseinfo/houseFamily/dispatchOperate.action?mode=move&orgId='+$("#orgId").val()+'&houseFamily.id='+$("#houseFamilyId").val()+'&newFamilyMember.idCardNo='+newIdCardNo,
		buttons : {
			"保存" : function(){
				$("#moveHouseMemberForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function viewDialog(event,rowid){
	if(null == rowid){
 		return;
	}
	var rowData = $("#houseFamilyMemberList").getRowData(rowid);
	var hpid = rowData.encryptId;
	$("#houseFamilyMemberDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看户籍人口详细信息',
		modal : true,
		url:PATH+'/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+hpid,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
</script>