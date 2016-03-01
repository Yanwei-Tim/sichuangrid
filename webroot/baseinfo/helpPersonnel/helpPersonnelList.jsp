<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>

	<input type="hidden" id="domainId" name="" value="<s:property value="#parameters.domainId"/>"/>
	<input type="hidden" id="keyType" name="" value="<s:property value="#parameters.keyType"/>"/>

<div class="content">
	<div class="ui-cornRr-all" id="nav">
			<a id="addHelp" href="javascript:void(0)" >新增</span></a>
			<a id="updateHelp" href="javascript:void(0)"><span>修改</span></a>
			<a id="viewHelp" href="javascript:void(0)"><span>查看</span></a>
			<a id="logoutHelp" href="javascript:void(0)"><span>注销</span></a>
	</div>
</div>

<div style="width: 100%;">
	<table id="helpPersonnelList"></table>
	<div id="helpPersonnelListPager"></div>
</div>


<script type="text/javascript">
	var keyType=$("#keyType").val();
	var domainId=$("#domainId").val();
	if($("#mode").val() == "add"){
		$("#helpPersonnelListPager").hide();
	}else{
		$("#helpPersonnelListPager").show();
		}
	function loadList(){
		$("#helpPersonnelList").setGridParam({
			url:'${path}/baseinfo/helpPersonnel/findHelpPersonnelForListPage.action',
			datatype: "json",
			page:1
		});
		$("#helpPersonnelList").setPostData({
			"personnelType":keyType,
			"personnelId":domainId
		});
		$("#helpPersonnelList").trigger("reloadGrid");
	}

	$(document).ready(function(){
		if("view" == $("#mode").val()){
			$("#addHelp").buttonDisable();
			$("#updateHelp").buttonDisable();
			$("#logoutHelp").buttonDisable();
		}
		if(isHelpPersonnel()){
			$("#helpPrecord").show();
		}else{
			$("#helpPrecord").hide();
		}
		$("#helpPersonnelList").jqGridFunction({
			datatype: "local",
			colModel:[
				{name:"trId", index:"trId", hidden:true,hidedlg:true},
				{name:"id", index:"id", hidden:true },
				{name:"name", sortable:false,index:"name", sortable:false,label: '姓名'},
				{name:"identity", sortable:false, label: '帮教人员身份'},
				{name:"mobile", sortable:false, label:'联系手机'},
				{name:"telephone", sortable:false, label: '联系电话'},
				{name:"status", sortable:false, label:'',hidedlg:true,hidden:true }
			],
			loadComplete: afterHelpLoad,
			width: <s:property value="#parameters.width"/>,
			height: <s:property value="#parameters.height"/>,
			onSelectRow:selectHelpRow,
			ondblClickRow: viewObject,
			showColModelButton:false
		});
		loadList();
		$("#addHelp").click(function(){
			var domainId = $("#domainId").val();
			if(domainId==null || domainId==""){
				domainId=0;
			}
			$("#helpPersonnelDialog").createDialog({
				width: 300,
				height: 250,
				title:'新增帮教人员信息',
				url:'${path}/baseinfo/helpPersonnel/dispatch.action?personnelId='+domainId+'&personnelType=<s:property value="#parameters.keyType"/>&mode=add&personType=help',
				buttons: {
					"保存" : function(event){
				      $("#helpPersonnelForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#updateHelp").click(function(){
			var selectedId = $("#helpPersonnelList").getGridParam("selrow");
			if(selectedId==null){
			  return;
			}
			var id = selectedId.split("_")
			$("#helpPersonnelDialog").createDialog({
				width: 300,
				height: 250,
				title:'修改帮教人员信息',
				url:'${path}/baseinfo/helpPersonnel/dispatch.action?helpPersonnel.id='+id[0]+'&mode=edit&personType=help',
				buttons: {
					"保存" : function(event){
				      $("#helpPersonnelForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#viewHelp").click(function(){
			viewObject();
		});
		

		$("#logoutHelp").click(function(){
			var selectedId = $("#helpPersonnelList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			var id = selectedId.split("_")
			$.confirm({
				title:"确认注销",
				message:"该帮教人信息注销后，将无法恢复,您确认注销该人员信息吗?",
				width: 300,
				okFunc: function(){
				$.ajax({
					url:"${path}/baseinfo/helpPersonnel/updateHelpPersonnel.action",
					data:{
						"helpPersonnel.id":id[0],
						"helpPersonnel.status":1
					},
					success:function(responseData){
						$("#helpPersonnelList").setRowData(responseData.trId,responseData);
						$("#"+responseData.trId+"").css('color','#778899');
						$("#logoutHelp").buttonDisable();
						$.messageBox({message:"注销人员成功！"});
					}
				});
			}
			});
		});

	});

	function viewObject(){
		var selectedId = $("#helpPersonnelList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		var id = selectedId.split("_")
		$("#helpPersonnelDialog").createDialog({
			width: 300,
			height: 250,
			title:'查看帮教人员信息',
			url:'${path}/baseinfo/helpPersonnel/dispatch.action?helpPersonnel.id='+id[0]+'&mode=view&personType=help',
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function statusFormatter(){
		var idCollection = new Array();
		idCollection=$("#helpPersonnelList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#helpPersonnelList").getRowData(idCollection[i]);
				if(ent.status==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}

	function afterHelpLoad(){
		disableHelpButton();
		statusFormatter();
		selectHelpRow();

	}

	function selectHelpRow(){
		var selectedId = $("#helpPersonnelList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		var helpPersonnel =  $("#helpPersonnelList").getRowData(selectedId);
		if("view" == $("#mode").val()){
			$("#addHelp").buttonDisable();
			$("#updateHelp").buttonDisable();
			$("#logoutHelp").buttonDisable();
			$("#viewHelp").buttonEnable();
		}else{
			if(helpPersonnel.id!=null && helpPersonnel.id!=""){
				if(helpPersonnel.status==1){
					$("#logoutHelp").buttonDisable();
				}else{
					$("#logoutHelp").buttonEnable();
				}
				$("#updateHelp").buttonEnable();
				$("#viewHelp").buttonEnable();
			}
		}
	}

	function disableHelpButton(){
		$("#updateHelp").buttonDisable();
		$("#viewHelp").buttonDisable();
		$("#logoutHelp").buttonDisable();
	}

	function isHelpPersonnel(){
		var flag=false;
		$.ajax({
			async:false,
			url: "${path }/baseinfo/helpPersonnel/getHelpPersonnelByIdAndPlace.action",
			data:{
				"personnelId":$("#domainId").val(),
				"personnelType":$("#keyType").val()
			},
			success:function(responseData){
				if(responseData.length>0){
					flag=true;
				}
			}
		});
		return flag;
	}
</script>