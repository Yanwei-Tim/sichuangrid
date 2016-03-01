<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>

	<input type="hidden" id="domainId" name="personInCharges.placeId" value="<s:property value="#parameters.domainId"/>"/>
	<input type="hidden" id="keyType" name="personInCharges.placeType" value="<s:property value="#parameters.keyType"/>"/>

<div class="content">
	<div class="ui-cornRr-all" id="nav">
			<a id="addPerson" href="javascript:void(0)" >新增</span></a>
			<a id="updatePerson" href="javascript:void(0)"><span>修改</span></a>
			<a id="viewPerson" href="javascript:void(0)"><span>查看</span></a>
			<a id="logoutPerson" href="javascript:void(0)"><span>注销</span></a>
	</div>
</div>

<div style="width: 100%;">
	<table id="personInChargeList"></table>
	<div id="personInChargeListPager"></div>
</div>


<script type="text/javascript">
	var keyType=$("#keyType").val();
	var domainId=$("#domainId").val();
	if($("#mode").val() == "add"){
		$("#personInChargeListPager").hide();
	}else{
		$("#personInChargeListPager").show();
		}

	function loadList(){
		$("#personInChargeList").setGridParam({
			url:'${path}/baseinfo/personInCharegeManage/personInCharegeList.action',
			datatype: "json",
			page:1
		});
		$("#personInChargeList").setPostData({
			"placeType":keyType,
			"placeId":domainId
		});
		$("#personInChargeList").trigger("reloadGrid");
	}

	$(document).ready(function(){
		if("view" == $("#mode").val()){
			$("#addPerson").buttonDisable();
		}
		if(ispersonInCharge()){
			$("#floorpersons").show();
		}else{
			$("#floorpersons").hide();
		}
		$("#personInChargeList").jqGridFunction({
			datatype: "local",
			colModel:[
				{name:"trId", index:"trId", hidden:true,hidedlg:true},
				{name:"id", index:"id", hidden:true },
				{name:"name", sortable:false,index:"name", label: '姓名'},
				{name:"moblie", sortable:false, label:'手机'},
				{name:"telephone", sortable:false, label: '联系电话'},
				{name:"status", sortable:false, label:'',hidedlg:true,hidden:true }
			],
			loadComplete: afterPersonInChargeLoad,
			width: <s:property value="#parameters.width"/>,
			height: <s:property value="#parameters.height"/>,
			onSelectRow:selectPersonInChargeRow,
			ondblClickRow: viewFloor,
			showColModelButton:false
		});
		loadList();
		$("#addPerson").click(function(){
			var domainId = $("#domainId").val();
			if(domainId==null || domainId==""){
				domainId=0;
			}
			$("#personInChargeDialog").createDialog({
				width: 400,
				height: 250,
				title:'新增消防安全负责人信息',
				url:'${path}/baseinfo/personInCharegeManage/dispatch.action?placeId='+domainId+'&placeType=<s:property value="#parameters.keyType"/>&mode=add',
				buttons: {
					"保存" : function(event){
				      $("#personInChargeForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#updatePerson").click(function(){
			var selectedId = $("#personInChargeList").getGridParam("selrow");
			if(selectedId==null){
			  return;
			}
			var id = selectedId.split("_")
			$("#personInChargeDialog").createDialog({
				width: 400,
				height: 250,
				title:'修改消防安全负责人信息',
				url:'${path}/baseinfo/personInCharegeManage/dispatch.action?personInCharges.id='+id[0]+'&mode=edit',
				buttons: {
					"保存" : function(event){
				      $("#personInChargeForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#viewPerson").click(function(){
			viewFloor();
		});

		$("#logoutPerson").click(function(){
			var selectedId = $("#personInChargeList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			var id = selectedId.split("_")
			$.confirm({
				title:"确认注销",
				message:"该消防安全负责人信息注销后，将无法恢复,您确认注销该人员信息吗?",
				width: 400,
				okFunc: function(){
				$.ajax({
					url:"${path}/baseinfo/personInCharegeManage/updatePersonInCharege.action",
					data:{
						"personInCharges.id":id[0],
						"personInCharges.status":1
					},
					success:function(responseData){
						$("#personInChargeList").setRowData(responseData.id,responseData);
						$("#"+responseData.trId+"").css('color','#778899');
						$("#logoutPerson").buttonDisable();
						$("#personInChargeList").trigger("reloadGrid");
						$.messageBox({message:"注销人员成功！"});
					}
				});}
			});
		});

	});

	function viewFloor(){
		var selectedId = $("#personInChargeList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		var id = selectedId.split("_")
		$("#personInChargeDialog").createDialog({
			width: 400,
			height: 250,
			title:'查看消防安全负责人信息',
			url:'${path}/baseinfo/personInCharegeManage/dispatch.action?personInCharges.id='+id[0]+'&mode=view',
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function statusFormatter(){
		var idCollection = new Array();
		idCollection=$("#personInChargeList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#personInChargeList").getRowData(idCollection[i]);
				if(ent.status==1){
					$("#logoutPerson").buttonDisable();
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function afterPersonInChargeLoad(){
		disablePersonInCharegeButton();
		statusFormatter();
	}

	function ispersonInCharge(){
		var flag=false;
		$.ajax({
			async:false,
			url: "${path }/baseinfo/personInCharegeManage/getPersonInCharegeByIdAndPlace.action",
			data:{
				"placeId":$("#domainId").val(),
				"placeType":$("#keyType").val()
			},
			success:function(responseData){
				if(responseData.length>0){
					flag=true;
				}
			}
		});
		return flag;
	}

	function selectPersonInChargeRow(){
		var selectedId = $("#personInChargeList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		var personInCharge =  $("#personInChargeList").getRowData(selectedId);
		if("view" == $("#mode").val()){
			$("#addPerson").buttonDisable();
			$("#updatePerson").buttonDisable();
			$("#logoutPerson").buttonDisable();
			$("#viewPerson").buttonEnable();
		}else{
			if(personInCharge.id!=null && personInCharge.id!=""){
				if(personInCharge.status==1){
					$("#logoutPerson").buttonDisable();
				}else{
					$("#logoutPerson").buttonEnable();
				}
				$("#updatePerson").buttonEnable();
				$("#viewPerson").buttonEnable();
			}
		}
	}

	function disablePersonInCharegeButton(){
		$("#updatePerson").buttonDisable();
		$("#viewPerson").buttonDisable();
		$("#logoutPerson").buttonDisable();

	}
</script>