<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<%-- <div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	    <select id="conditionMark">
	    	<option value="fast_hosueCode" selected="selected">房屋编号</option>
	       	<option value="fast_address">房屋地址</option>
	       	<!-- <option value="fast_houseOwner">房主</option>
	       	<option value="fast_houseOwnerIdCard">房主身份证号码</option> -->
	    </select>
		<input type="text" value="请输入检索条件" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入检索条件':this.value;" onfocus="value=(this.value=='请输入检索条件')?'':this.value;"/>
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a href="javascript:;" id="searchHouseByConditionButton"><span>检索</span></a>
</div> --%>
<div class="content" id="actualHousesListDiv">
<%-- 	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addRentalHouse">
			<a id="addRentalHouse" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateRentalHouse">
			<a id="updateRentalHouse"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewRentalHouse">
			<a id="viewRentalHouse" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteRentalHouse">
			<a id="deleteRentalHouse" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchRentalHouse">
			<a id="searchRentalHouse" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importRentalHouse">
			<a id="importRentalHouse" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downloadRentalHouse">
            <a id="exportRentalHouse" href="javascript:void(0)"><span>导出</span></a>
        </pop:JugePermissionTag>
 		<pop:JugePermissionTag ename="isEmphasisRentalHouse">
			<a id="isEmphasisRentalHouse" href="javascript:void(0)"><span>注销</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="cancelIsEmphasis">
			<a id="cancelIsEmphasis" href="javascript:void(0)"><span>取消注销</span></a>
		</pop:JugePermissionTag>
           <a id="mantanceRentalHousePopulation" href="javascript:void(0)"><span>住户管理</span></a>
		<div style="float: right;white-space:nowrap;">
			<select id="isEmphas" name="" class="S_object">
					<option value="null">全部</option>
 					<option value="false" selected="selected">未注销</option>
 					<option value="true">已注销</option>
			</select>
		</div>
		<div style="float: right;white-space:nowrap;">
			<select id="usageId" class="form-txt" >
   				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
			</select>
		</div>
	</div> --%>
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="actualHouseList"></table>
		<div id="actualHouseListPager"></div>
	</div>
	<div id="rentalHouseMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="searchDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
	<div id="actualAppendPopulationDialog"></div>
</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="rentalHouseManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag>
</div>
<script type="text/javascript">
<pop:formatterProperty name="usage" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
<pop:formatterProperty name="hiddenDangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
function isSignGuarantee(el, options, rowData){
	if(1==rowData.isSignGuarantee){
		return "是";
	}else{
		return "否";
	}
}
function memberNumFormatter(el,options,rowData){
	return "<a href='javascript:viewActualHousePopulation("+rowData.id+")'>"+rowData.memberNum+"</a>";
}
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewRentalHouse'><a href='javascript:;' onclick='viewHouseInfo("+rowData.id+")'><span>查看</span></a>  </pop:JugePermissionTag>";
}
function addressFormatter(el,options,rowData){
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewActualHouse'> onclick='viewHouseInfo("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.address+"</a>";
}

function viewHouseInfo(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo = $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 650,
		title:'查看出租房信息',
		modal : true,
		url:'${path}/baseinfo/houseInfo/rentalhouse/viewRentalHouseDlg.jsp?mode=view&houseId='+houseInfo.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function viewActualHousePopulation(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 500,
		title:'查看居住人员信息',
		modal : true,
		//url:'${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseCode.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.houseCode='+houseInfo.houseCode,
		url:'${path}/baseinfo/actualHouseManage/dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt.action?houseInfo.organization.id='+houseInfo['organization.id']+'&houseInfo.id='+houseInfo.houseInfoEncryptId,		
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
var dialogWidth=760;
var dialogHeight=510;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();
var hiddenDangerLevelColor = function(el,options,rowData){
	var displayName=hiddenDangerLevelFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='严重')
		return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='一般')
		return '<span>一般：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='安全')
		return '<span>安全：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}

function onOrgChanged(orgId,isgrid){
	if(isgrid)
		$("#addRentalHouse").buttonEnable();
    else
    	$("#addRentalHouse").buttonDisable();
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#actualHouseList").setGridParam({
		url:'${path}/baseinfo/rentalHouseManage/searchHouseInfo.action',
		datatype: "json",
		page:1
	});
	$("#actualHouseList").setPostData({
		"orgId":function(){return currentOrgId;},
		"searchHouseInfoVo.isEmphasis" : $("#isEmphas").val()
	});
	$("#actualHouseList").trigger("reloadGrid");
}

function getPostDataForList(){
	var condition = null;
	if("请输入检索条件" != $("#searchByCondition").val()) {
		condition = $("#searchByCondition").val();
	}
	var initParam = {
		"orgId": getCurrentOrgId(),
		"searchHouseInfoVo.organization.id":getCurrentOrgId(),
		"searchHouseInfoVo.usage.id" : $("#usageId").val()
	}
	if(null != $("#isEmphas").val() && "null" != $("#isEmphas").val()) {
		initParam = $.extend(initParam,{"searchHouseInfoVo.isEmphasis":$("#isEmphas").val()});
	}
	if("fast_hosueCode"==$("#conditionMark").val()){
		initParam = $.extend(initParam,{"searchHouseInfoVo.houseCode":condition});
	}else if("fast_address"==$("#conditionMark").val()){
		initParam = $.extend(initParam,{"searchHouseInfoVo.address":condition});
	}
	return initParam;
}

$(document).ready(function(){
	$("#usageId").change(function(){
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/rentalHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData(getPostDataForList());
		$("#actualHouseList").trigger("reloadGrid");
	});
	$("#isEmphas").change(function(){
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/rentalHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData(getPostDataForList());
		$("#actualHouseList").trigger("reloadGrid");
	});
	$("#actualHouseList").jqGridFunction({
		datatype: "local",
		height:$("#actualHousesListDiv").parent().height()-70,
		colModel:[
			        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			        {name:"houseInfoEncryptId",index:"houseInfoEncryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			        {name:"organization.id", index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
			        {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
			        {name:'hiddenDangerLevel',index:"hiddenDangerLevel",label:'隐患等级',width:80,sortable:true,formatter :hiddenDangerLevelColor},
			        //{name:'houseCode-fromatter',index:'houseCode',label:'房屋编号',sortable:true,formatter:houseCodeFont,width:120,frozen:true},
			        //{name:'houseCode',index:'houseCode',label:'房屋编号',width:50,hidden:true,sortable:true,frozen:true},
			        {name:'houseId',index:'houseId',label:'houseId',width:50,hidden:true,sortable:false,frozen:true},
			        {name:'address',index:'address',label:'房屋地址',formatter:addressFormatter,sortable:true,width:200},
			        {name:'rentalPerson',index:'name',label:'出租人（单位）',width:80,sortable:true,frozen:true},
			        {name:'houseFileNum',index:'houseFileNum',label:'租赁备案证号',width:100,frozen:true,sortable:true,hidden:true},
			        {name:'memberNum',index:'memberNum',label:'居住人数',sortable:false,hidden:false,formatter:memberNumFormatter,width:80,align:'center'},
			        {name:'usage', index:'usage', label:'出租用途',width:100, sortable:true,formatter:usageFormatter},
			        
			        {name:'hasServiceTeamMember',label:'有无治安负责人',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
					{name:'lastRecordTime',label:'最新巡场时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
					{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			        {name:'updateDate', label:'数据更新时间',sortable:true,width:200,align:'center'},
			        {name:'isSignGuarantee',index:'isSignGuarantee', label:'是否签治安责任保证书',formatter:isSignGuarantee,hidden:true,sortable:true,width:130,align:"center"},
		            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',sortable:false,width:200,hidden:true},
		            {name:"isEmphasis",index:"isEmphasis",label:'是否注销',hidden:true,frozen:true}
				],
		<pop:JugePermissionTag ename="viewRentalHouse">
		ondblClickRow: viewHouseInfo,
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectAll:function(aRowids,status){ if(status) {
			var reEmphasis=false;
			var cancelEmphasis=false;
	   		var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	   		for(var i = 0;i<=selectedIds.length;i++){
	   			var row=$("#actualHouseList").getRowData(selectedIds[i]);
	   			if(selectedIds.length ==1){
			   		$("#viewRentalHouse").buttonEnable();
			   		$("#mantanceRentalHousePopulation").buttonEnable();
					$("#updateRentalHouse").buttonEnable();
				}
		   		if(selectedIds.length != 0){
				   	$("#deleteRentalHouse").buttonEnable();
			   	}
		   		if(selectedIds.length >2){
	   				$("#viewRentalHouse").buttonDisable();
					$("#updateRentalHouse").buttonDisable();
			   		$("#mantanceRentalHousePopulation").buttonDisable();
	   			}
		   		if(row.isEmphasis=="true"){
					reEmphasis=true;
				}
				if(row.isEmphasis!="true"){
					cancelEmphasis=true;
				}
	    	}
	   		if(cancelEmphasis && !reEmphasis) {
	   			$("#isEmphasisRentalHouse").buttonEnable();
	   			$("#cancelIsEmphasis").buttonDisable();
	   		} else if(!cancelEmphasis && reEmphasis) {
	   			$("#isEmphasisRentalHouse").buttonDisable();
	   			$("#cancelIsEmphasis").buttonEnable();
	   		} else {
	   			$("#isEmphasisRentalHouse").buttonDisable();
	   			$("#cancelIsEmphasis").buttonDisable();
	   		}
	   	}else{
		   	$("#deleteRentalHouse").buttonDisable();
	   		$("#viewRentalHouse").buttonDisable();
			$("#updateRentalHouse").buttonDisable();
	   		$("#mantanceRentalHousePopulation").buttonDisable();
	   		$("#isEmphasisRentalHouse").buttonDisable();
	   		$("#cancelIsEmphasis").buttonDisable();
	   	}},
		multiselect:true,
		onSelectRow:function(){setHouseInfoOperateButton();}
	});
	jQuery("#actualHouseList").jqGrid('setFrozenColumns');
	
	if("${searchType}"=='fast'){
		search();
		
	}else if("${searchType}"=='advanced'){
		searchRentalHouseInfo();
	}
		$("#RentalHousestatisticsDialog").dialog("close");
	
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	/*function houseCodeFont(el,options,rowData){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<a href='javascript:viewHouseInfo("+rowData.id+")'><font color='#778899'>"+rowData.houseCode+"</font></a>";
		}
		return "<a href='javascript:viewHouseInfo("+rowData.id+")'>"+rowData.houseCode+"</a>";
	}
	*/
	$("#mantanceRentalHousePopulation").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var houseInfo =  $("#actualHouseList").getRowData(selectedId);
		$("#rentalHouseMaintanceDialog").createDialog({
			width: 840,
			height: 600,
			title:'维护使用人信息',
			url:'${path}/baseinfo/actualHousePopulation/prepairMantanceLivingPopulation.action?houseInfo.houseCode='+houseInfo.houseCode+"&orgId="+houseInfo["organization.id"],
			buttons: {
				"保存" : function(event){
					populationBindHouse($(this));

	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#addRentalHouse").click(function(event){
		if (!isgridBol){
			return;
		}
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			$("#rentalHouseMaintanceDialog").createFrameDialog(
				{
					model :"add",
					title:"新增出租房",
					width: 800,
					height: 640,
					data:[
						{title:'房屋信息',url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?dailogName=rentalHouseMaintanceDialog&mode=add&orgId='+currentOrgId,buttons:{next:true}},
						{title:'出租房',url:'${path}/baseinfo/rentalHouseManage/dispatchOperate.action?dailogName=rentalHouseMaintanceDialog&mode=add&orgId='+currentOrgId,buttons:{prev:true,save:true,saveContinue:true}}
					]
				}
			);
		}
	});
	$("#updateRentalHouse").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var houseInfo =  $("#actualHouseList").getRowData(selectedId);
		$("#rentalHouseMaintanceDialog").createFrameDialog({
			model :"edit",
			title:'修改房屋信息',
			width: 800,
			height: 650,
			data:[
				{title:'房屋信息',url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?dailogName=rentalHouseMaintanceDialog&mode=edit&houseInfo.houseCode='+houseInfo.houseCode+"&houseInfo.organization.id="+houseInfo["organization.id"],buttons:{next:true}},
				{title:'出租房',url:'${path}/baseinfo/rentalHouseManage/dispatchOperate.action?dailogName=rentalHouseMaintanceDialog&mode=edit&houseInfo.houseCode='+houseInfo.houseCode+"&houseInfo.organization.id="+houseInfo["organization.id"],buttons:{prev:true,save:true,saveContinue:true}}
			]
		});
	});

	$("#importRentalHouse").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=rentalHouseData&dialog=noticeDialog&startRow=6&templates=RENTALHOUSE',
			buttons:{
					"导入" : function(event){
				      $("#mForm").submit();
				   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			},
			shouldEmptyHtml:false
		});
	});

	$("#searchRentalHouse").click(function(event){
		$("#searchDialog").createDialog({
			width: 760,
			height: 400,
			datatype: "json",
			title:'查询出租房信息',
			url:'${path}/baseinfo/rentalHouseManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
			buttons: {
				"查询" : function(event){
				searchRentalHouseInfo();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	function searchRentalHouseInfo() {
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/rentalHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData($.extend({"searchHouseInfoVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchRentalHouseForm").serializeObject()));
		$("#actualHouseList").trigger("reloadGrid");
	}

	$("#deleteRentalHouse").click(function(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.ajax({
			url:"${path}/baseinfo/rentalHouseManage/houseHasActualPopulation.action?houseIds="+allValue,
			success:function(data){
				if(data){
					 $.messageBox({message:"出租房信息不能被删除，请先移除房屋中的人口信息!",level:'error'});
				}else{
					$.confirm({
						title:"确认删除",
						message:"确定要删除吗?一经删除将无法恢复",
						okFunc: function(){
							$.ajax({
								url:"${path}/baseinfo/rentalHouseManage/deleteHouseInfo.action?houseIds="+allValue,
								success:function(data){
									$("#actualHouseList").trigger("reloadGrid");
								    $.messageBox({message:"已经成功删除出租房信息!"});
								    disableButtons();
									checkExport();
							    }
						    });
					   	}
				 	});
				}
			}
		});
	});

	$("#viewRentalHouse").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewHouseInfo(selectedId);
	});
	

	$("#isEmphasisRentalHouse").click(function(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.confirm({
			title:"确认注销",
			message:"确定要注销吗?",
			okFunc: function(){
				$.ajax({
					url:"${path}/baseinfo/rentalHouseManage/updateEmphasiseById.action?houseInfo.isEmphasis=true&houseIds="+allValue,
					success:function(datas){
						for(var i = 0;i<datas.length;i++){
							var responseData = datas[i];
							if(($("#isEmphas").val()=='false')){
				 				$("#actualHouseList").delRowData(responseData.id);
					 		}else{
				 				$("#actualHouseList").setRowData(responseData.id,responseData);
				 				$("#"+responseData.id+"").css('color','#778899');
				 				$("#actualHouseList").setSelection(responseData.id);
			 				}
						}
						$("#actualHouseList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功注销该出租房信息!"});
					    disableButtons();
						checkExport();
				    }
			    });
		   }
	 });
	});

	$("#cancelIsEmphasis").click(function(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		$.confirm({
			title:"确认取消注销",
			message:"确定要取消注销吗?",
			okFunc: function(){
				$.ajax({
					url:"${path}/baseinfo/rentalHouseManage/updateEmphasiseById.action?houseInfo.isEmphasis=false&houseIds="+allValue,
					success:function(datas){
						for(var i = 0;i<datas.length;i++){
							var responseData = datas[i];
							if(($("#isEmphas").val()=='false')){
				 				$("#actualHouseList").delRowData(responseData.id);
					 		}else{
				 				$("#actualHouseList").setRowData(responseData.id,responseData);
				 				$("#"+responseData.id+"").css('color','#778899');
				 				$("#actualHouseList").setSelection(responseData.id);
			 				}
						}
						$("#actualHouseList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功取消注销该出租房信息!"});
					    disableButtons();
						checkExport();
				    }
			    });
		   }
	 });
	});

	$("#reload").click(function(event){
		$("#conditionMark").val("fast_hosueCode");
		$("#searchByCondition").attr("value","");
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#searchHouseByConditionButton").click(search);
	function search(){
		$("#actualHouseList").setGridParam({
			url:'${path}/baseinfo/rentalHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#actualHouseList").setPostData(getPostDataForList());
		$("#actualHouseList").trigger("reloadGrid");
	}

	$("#exportRentalHouse").click(function(event){
        if ($("#actualHouseList").getGridParam("records")>0){
            $("#rentalHouseMaintanceDialog").createDialog({
                width: 250,
                height: 200,
                title:'导出出租房信息',
                url:'${path}/common/exportExcel.jsp',
                postData:{
                    gridName:'actualHouseList',
                    orgId:getCurrentOrgId(),
                    downloadUrl:'${path}/baseinfo/rentalHouseManage/downloadRentalHouse.action'
                },
                buttons: {
                    "导出" : function(event){
                        $("#exceldownload").submit();
                        $(this).dialog("close");
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                },
                shouldEmptyHtml:false
            });
        }else{
        }
    });

});
function afterLoad(){
	disableButtons();
	setHouseInfoOperateButton();
	checkExport();
	isEmphasisFormatter();
}

function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#actualHouseList").getDataIDs();
	for(var i=0;i<idCollection.length;i++){
		var ent =  $("#actualHouseList").getRowData(idCollection[i]);
		if(ent.isEmphasis=="1" || ent.isEmphasis==1){
			$("#actualHouseList tr[id="+idCollection[i]+"]").css('color','#778899');
		}
	}
}

function disableButtons(){
	$("#updateRentalHouse").buttonDisable();
	$("#viewRentalHouse").buttonDisable();
	$("#deleteRentalHouse").buttonDisable();
	$("#mantanceRentalHousePopulation").buttonDisable();
	$("#isEmphasisRentalHouse").buttonDisable();
		$("#cancelIsEmphasis").buttonDisable();
}
function checkExport(){
	if($("#actualHouseList").getGridParam("records")>0
			|| $("#actualHouseList").getGridParam("selrow")!=null){
		$("#exportRentalHouse").buttonEnable();
	}else{
		$("#exportRentalHouse").buttonDisable();

	}
}
function setHouseInfoOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("actualHouseList");
	var count = $("#actualHouseList").jqGrid("getGridParam","records");
	if(selectedCounts == count && count > 0){
		jqGridMultiSelectState("actualHouseList", true);
	}else{
		jqGridMultiSelectState("actualHouseList", false);
	}
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var houseInfoNew = $("#actualHouseList").getRowData(id);
			$("#updateRentalHouse").buttonEnable();
			$("#viewRentalHouse").buttonEnable();
			$("#deleteRentalHouse").buttonEnable()
	   		$("#mantanceRentalHousePopulation").buttonEnable();
			if(houseInfoNew.isEmphasis == "true") {
				$("#isEmphasisRentalHouse").buttonDisable();
			} else {
				$("#isEmphasisRentalHouse").buttonEnable();
			}
			if(houseInfoNew.isEmphasis == "true") {
				$("#cancelIsEmphasis").buttonEnable();
			} else {
				$("#cancelIsEmphasis").buttonDisable();
			}
		}else{
			$("#updateRentalHouse").buttonDisable();
			$("#viewRentalHouse").buttonDisable();
	   		$("#mantanceRentalHousePopulation").buttonDisable();
		}
		if(selectedCounts==0){
			$("#deleteRentalHouse").buttonDisable();
			$("#isEmphasisRentalHouse").buttonDisable();
			$("#cancelIsEmphasis").buttonDisable();
		}
}

function printRentalHouse(rowid){
	$("#rentalHouseMaintanceDialog").createDialog({
		width: 790,
		height: dialogHeight,
		title: '打印出租房信息',
		modal: true,
		url: '${path}/baseinfo/rentalHouseManage/dispatch.action?mode=print&houseInfo.id='+rowid,
		buttons: {
			"确定" : function(event){
				$("#houseInfoPrint").printArea();
				$(this).dialog("close");
			 },
	  		 "关闭" : function(){
	       		 $(this).dialog("close");
	  		 }
		}
	});
}

function setCompVal(){
	var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}

$("#refreshOrgTree1").click(function(){
	$("#searchByCondition").attr("value","");
});

</script>