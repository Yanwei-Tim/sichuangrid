<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addLettingHouse">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateLettingHouse">
			<a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewLettingHouse">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteLettingHouse">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchLettingHouse">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="importLettingHouse">
			<a id="import" href="javascript:void(0)"><span>导入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downLettingHouse">
            <a id="export" href="javascript:void(0)"><span>导出</span></a>
        </pop:JugePermissionTag>
        
       <pop:JugePermissionTag ename="shiftLettingHouse"> 
			<a id="shift" href="javascript:void(0)"><span>转移</span></a>
		</pop:JugePermissionTag> 
		
		<pop:JugePermissionTag ename="abolishLettingHouse">
			<a id="isEmphasis" href="javascript:void(0)"><span>取消关注</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="anewLettingHouse">
			<a id="emphasis" href="javascript:void(0)"><span>重新关注</span></a>
		</pop:JugePermissionTag>
		<div style="float: right;white-space:nowrap;">
				<select id="isLock" name="">
						<option value="-1">全部</option>
	 					<option value="0" selected="selected">现在关注</option>
	 					<option value="1">曾经关注</option>
				</select>
			</div>
	</div>
	<div style="width:100%;" >
		<table id="lettingHouseList"></table>
		<div id="lettingHouseListPager"></div>
	</div>
	<div id="lettingHouseMaintanceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="searchDialog"></div>
	<div id="personInChargeDialog"></div>
	<div id="floorpersonDialog"></div>
</div>
<div style="display: none;"><pop:JugePermissionTag
	ename="lettingHouseManagement">
	<span id="title"><s:property value="#request.name"/></span>
</pop:JugePermissionTag></div>
<script type="text/javascript">
<pop:formatterProperty name="lettingHouseStruts"
 domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<pop:formatterProperty name="type"
domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE" />
<pop:formatterProperty name="lettingHouseProperty"
domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY" />
<pop:formatterProperty name="usage"
domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
<pop:formatterProperty name="hiddenTroubleLevel"
domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
var dialogWidth=760;
var dialogHeight=510;
var isgridBol = false;
var title=$("#title").html();
var currentOrgId=getCurrentOrgId();
var hiddenTroubleLevelColor = function(el,options,rowData){
	var displayName=hiddenTroubleLevelFormatter(el,options,rowData);
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
		$("#add").buttonEnable();
    else
    	$("#add").buttonDisable();
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#lettingHouseList").setGridParam({
		url:'${path}/baseinfo/lettingHouseManage/lettingHouseList.action',
		datatype: "json",
		page:1
	});

	$("#lettingHouseList").setPostData({
			"organizationId":function(){
			return currentOrgId;
		},
	"lettingHouse.isEmphasis":$("#isLock").val()
	});
	$("#lettingHouseList").trigger("reloadGrid");
}

$(document).ready(function(){

	$("#lettingHouseList").jqGridFunction({
		datatype: "local",
		colNames:['id','隐患程度','房东姓名','身份证号码','联系电话','所属网格','出租房地址','房间数','限住人数','实住人数','出租房面积（平方米）','出租房性质','出租房类别','出租用途','出租房结构','编号','登记日期','房东地址','联系手机','隐患情况','是否关注'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'hiddenTroubleLevel',index:"hiddenTroubleLevel",width:100,formatter :hiddenTroubleLevelColor},
      		{name:"name",index:"name",width:100},
            {name:'idCardNo',hidden:true,sortable:false},
	        {name:'telephone',sortable:false,width:100},
	        {name:'organization.orgName',sortable:false,width:250, hidden:true},
	        {name:'lettingHouseAddr',sortable:false,width:250},
	        {name:'roomNumbers',width:60,sortable:false,align:"right"},
	        {name:'limitPersons',hidden:true,sortable:false,width:60,align:"right"},
            {name:'realityPersons',hidden:true,sortable:false,width:60,align:"right"},
            {name:'lettingHouseAreas',hidden:true,sortable:false,width:120,align:"right"},
	        {name:'lettingHouseProperty',width:90,sortable:false,formatter : lettingHousePropertyFormatter},
	        {name:'type',width:90,sortable:false,formatter : typeFormatter},
	        {name:'usage',width:90,sortable:false,formatter : usageFormatter},
	        {name:'lettingHouseStruts',width:120,sortable:false,formatter : lettingHouseStrutsFormatter},
            {name:'lettingHouseNo',hidden:true,sortable:false,width:90},
            {name:'registDate',hidden:true,sortable:false,width:90},
            {name:'landlordAddr',hidden:true,sortable:false,width:250},
            {name:'mobileNumber',hidden:true,sortable:false,width:90},
            {name:'hiddenRectification',hidden:true,sortable:false,width:300},
            {name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100}
		],
		<pop:JugePermissionTag ename="viewLettingHouse">
		ondblClickRow: viewLettingHouseInfo,
		</pop:JugePermissionTag>
		loadComplete: afterLoad,
		onSelectAll:function(aRowids,status){ if(status) {
	   		var selectedIds = $("#lettingHouseList").jqGrid("getGridParam", "selarrrow");
	   		for(var i = 0;i<=selectedIds.length;i++){
	   			var row=$("#lettingHouseList").getRowData(selectedIds[i]);
	   			if(row.isEmphasis==1){
	   				$("#emphasis").buttonEnable();
	   			}
	   			if(row.isEmphasis==0){
	   				$("#isEmphasis").buttonEnable();

	   			}
	   			if(selectedIds.length ==2){
			   		$("#view").buttonEnable();
					$("#update").buttonEnable();
				}
	   		if(selectedIds.length != 0){
			   	$("#delete").buttonEnable();
			   	if(isGrid()){
					$("#shift").buttonEnable();
			   	}
		   	}
	   		if(selectedIds.length >2){
   				$("#view").buttonDisable();
				$("#update").buttonDisable();
				$("#emphasis").buttonDisable();
				$("#isEmphasis").buttonDisable();
   			}
	    	}
	   	}else{$("#delete").buttonDisable();
	   	$("#shift").buttonDisable();
	   	$("#view").buttonDisable();
		$("#update").buttonDisable();
		$("#emphasis").buttonDisable();
		$("#isEmphasis").buttonDisable();
	   	}},
		multiselect:true,
		onSelectRow:function(){setLettingHouseOperateButton();}
	});
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#isLock").change(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#add").click(function(event){
		if (!isgridBol){
			return;
		}
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			$("#lettingHouseMaintanceDialog").createDialog({
				width: 800,
				height: 550,
				title:'新增'+title+'信息',
				url:'${path}/baseinfo/lettingHouseManage/dispatch.action?mode=add&organizationId='+currentOrgId,
				buttons: {
						"保存并关闭" : function(event){
				   			$("#maintainForm").submit();
				   			$("#isSubmit").val("true");
			   				},
			   			"保存并继续" : function(event){
				   			$("#maintainForm").submit();
				   			$("#isSubmit").val("false");
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
				}
			});
		}
	});

	$("#shift").click(function(event){
		if (!isgridBol){
			return;
		}
		$("#lettingHouseMaintanceDialog").createDialog({
			width: 300,
			height: 300,
			title:"转移"+title+"信息",
			url:'${path}/baseinfo/lettingHouseManage/shiftDispatch.action?orgId='+getCurrentOrgId()+'&lettingHouseIds='+$("#lettingHouseList").jqGrid("getGridParam", "selarrrow"),
			buttons: {
				"保存" : function(event){
			      $("#maintainShiftForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	$("#update").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var lettingHouse =  $("#lettingHouseList").getRowData(selectedId);
		$("#lettingHouseMaintanceDialog").createDialog({
			width: 800,
			height: 550,
			title:'修改'+title+'信息',
			url:'${path}/baseinfo/lettingHouseManage/dispatch.action?mode=edit&lettingHouse.id='+lettingHouse.id+"&organizationId="+currentOrgId,
			buttons: {
				"保存" : function(event){
	   			$("#maintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:'${path}/common/import.jsp?dataType=lettingHouseData&dialog=noticeDialog&startRow=5&templates=LETTINGHOUSE',
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

	$("#search").click(function(event){

		$("#searchDialog").createDialog({
			width: 760,
			height: 400,
			datatype: "json",
			title:'查询'+title+'信息',
			url:'${path}/baseinfo/lettingHouseManage/prepareSearchLettingHouses.action?organizationId='+currentOrgId,
			buttons: {
				"查询" : function(event){
	   				refreshLettingHouseGrid();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#isEmphasis").click(function(){
		var selectedId =  getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/lettingHouseManage/updateLettinghouseById.action",
			data:{
				"lettingHouse.id":selectedId,
				"lettingHouse.isEmphasis":1
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#lettingHouseList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','#778899');
				}else{
					$("#lettingHouseList").delRowData(responseData.id);
				}
				$.messageBox({message:"取消关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	$("#emphasis").click(function(){
		var selectedId =  getSelectedIdLast();
		if(selectedId == null){
			return;
		}
		$.ajax({
			url:"${path}/baseinfo/lettingHouseManage/updateLettinghouseById.action",
			data:{
				"lettingHouse.id":selectedId,
				"lettingHouse.isEmphasis":0
			},
			success:function(responseData){
				if($("#isLock").val()=="-1"){
					$("#lettingHouseList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','black');
				}else{
					$("#lettingHouseList").delRowData(responseData.id);
				}
				$.messageBox({message:"重新关注成功！"});
				disableButtons();
				checkExport();
			}
		});
	});

	$("#delete").click(function(){
		var allValue = setCompVal();
		if(allValue.length ==0){
			 return;
		}
		var str = relatePlace(allValue);
		$.confirm({
			title:"确认删除",
			message:str,
			width: 400,
			okFunc: deleteLettingHouse
		});
	});

	$("#view").click(function(event){
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewLettingHouseInfo(selectedId);
	});

	$("#reload").click(function(event){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#export").click(function(event){
        if ($("#lettingHouseList").getGridParam("records")>0){
            $("#lettingHouseMaintanceDialog").createDialog({
                width: 250,
                height: 200,
                title:'导出'+title+'信息',
                url:'${path}/common/exportExcel.jsp',
                postData:{
                    gridName:'lettingHouseList',
                    downloadUrl:'${path}/baseinfo/lettingHouseManage/downloadLettingHouse.action'
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
	setLettingHouseOperateButton();
	checkExport();
	isEmphasisFormatter();
}

function disableButtons(){
	$("#update").buttonDisable();
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
	$("#isEmphasis").buttonDisable();
	$("#emphasis").buttonDisable();
	$("#shift").buttonDisable();
}
function checkExport(){
	if($("#lettingHouseList").getGridParam("records")>0
			|| $("#lettingHouseList").getGridParam("selrow")!=null){
		$("#export").buttonEnable();
	}else{
		$("#export").buttonDisable();

	}
}
function setLettingHouseOperateButton(){
	var selectedCounts = getActualjqGridMultiSelectCount("lettingHouseList");
	var count = $("#lettingHouseList").jqGrid("getGridParam","records");
	if(selectedCounts == count && selectedCounts!=0){
		jqGridMultiSelectState("lettingHouseList", true);
	}else{
		jqGridMultiSelectState("lettingHouseList", false);
	}
	var selectedId = $("#lettingHouseList").getGridParam("selrow");
	if(selectedId==null){
	  return;
	}
	var lettingHouse =  $("#lettingHouseList").getRowData(selectedId);
		$("#search").buttonEnable();
		if(isGrid()){
			$("#shift").buttonEnable();
		}else{
			$("#shift").buttonDisable();
		}
		$("#delete").buttonEnable();
		if(selectedCounts==1){
			var id = getSelectedIdLast();
			var lettingHouseNew = $("#lettingHouseList").getRowData(id);
			$("#update").buttonEnable();
			$("#view").buttonEnable();
			if(lettingHouseNew.isEmphasis!=1){
				$("#isEmphasis").buttonEnable();
				$("#emphasis").buttonDisable();
			}else{
				$("#emphasis").buttonEnable();
				$("#isEmphasis").buttonDisable();
			}
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#emphasis").buttonDisable();
			$("#isEmphasis").buttonDisable();
		}
		if(selectedCounts==0){
			$("#delete").buttonDisable();
			$("#shift").buttonDisable();
		}
}
function viewLettingHouseInfo(rowid){
	if(rowid==null){
 		return;
	}
	var lettingHouse =  $("#lettingHouseList").getRowData(rowid);
	$("#lettingHouseMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		modal : true,
		url:'${path}/baseinfo/lettingHouseManage/dispatch.action?mode=view&lettingHouse.id='+lettingHouse.id,
		buttons: {
		   "打印" : function(event){
    		    $(this).dialog("close");
    		    printLettingHouse(rowid);
		   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function printLettingHouse(rowid){
	$("#lettingHouseMaintanceDialog").createDialog({
		width: 790,
		height: dialogHeight,
		title: '打印'+title+'信息',
		modal: true,
		url: '${path}/baseinfo/lettingHouseManage/dispatch.action?mode=print&lettingHouse.id='+rowid,
		buttons: {
			"确定" : function(event){
				$("#lettingHousePrint").printArea();
				$(this).dialog("close");
			 },
	  		 "关闭" : function(){
	       		 $(this).dialog("close");
	  		 }
		}
	});
}

function refreshLettingHouseGrid() {
	var query_orgInternalCode=$('#query_orgInternalCode').val();
	var query_lettingHouseNo = $("#query_lettingHouseNo").val();
	var query_telephone = $("#query_telephone").val();
	var query_name=$('#query_name').val();
  	var query_idCardNo=$('#query_idCardNo').val();
  	var query_mobileNumber = $("#query_mobileNumber").val();
  	var query_registDateFrom = $("#query_registDateFrom").val();
  	var query_registDateEnd = $("#query_registDateEnd").val();
  	var query_landlordAddr = $("#query_landlordAddr").val();
  	var query_lettingHouseAddr = $("#query_lettingHouseAddr").val();
  	var query_roomNumbersFrom = $("#query_roomNumbersFrom").val();
  	var query_roomNumbersEnd = $("#query_roomNumbersEnd").val();
    var query_lettingHouseAreasFrom=$('#query_lettingHouseAreasFrom').val();
    var query_lettingHouseAreasEnd=$('#query_lettingHouseAreasEnd').val();
    var query_limitPersonsFrom = $("#query_limitPersonsFrom").val();
    var query_limitPersonsEnd = $("#query_limitPersonsEnd").val();
    var query_realityPersonsFrom = $("#query_realityPersonsFrom").val();
    var query_realityPersonsEnd = $("#query_realityPersonsEnd").val();
    var query_lettingHouseStruts = $("#query_lettingHouseStruts").val();
    var query_type = $("#query_type").val();
  	var query_lettingHouseProperty=$('#query_lettingHouseProperty').val();
	var query_usage=$('#query_usage').val();
	var query_hiddenTroubleLevel=$('#query_hiddenTroubleLevel').val();
	$("#lettingHouseList").setGridParam({
		url:'${path}/baseinfo/lettingHouseManage/searchLettingHouses.action',
		postData: {
			"organizationId":getCurrentOrgId(),
			"searchLettingHouseVo.orgInternalCode":query_orgInternalCode,
			"searchLettingHouseVo.lettingHouseNo":query_lettingHouseNo,
			"searchLettingHouseVo.telephone":query_telephone,
			"searchLettingHouseVo.name":query_name,
			"searchLettingHouseVo.idCardNo":query_idCardNo,
			"searchLettingHouseVo.mobileNumber":query_mobileNumber,
			"searchLettingHouseVo.registDateFrom":query_registDateFrom,
			"searchLettingHouseVo.registDateEnd":query_registDateEnd,
			"searchLettingHouseVo.landlordAddr":query_landlordAddr,
			"searchLettingHouseVo.lettingHouseAddr":query_lettingHouseAddr,
			"searchLettingHouseVo.roomNumbersFrom":query_roomNumbersFrom,
			"searchLettingHouseVo.roomNumbersEnd":query_roomNumbersEnd,
            "searchLettingHouseVo.lettingHouseAreasFrom":query_lettingHouseAreasFrom,
            "searchLettingHouseVo.lettingHouseAreasEnd":query_lettingHouseAreasEnd,
            "searchLettingHouseVo.limitPersonsFrom":query_limitPersonsFrom,
            "searchLettingHouseVo.limitPersonsEnd":query_limitPersonsEnd,
            "searchLettingHouseVo.realityPersonsFrom":query_realityPersonsFrom,
            "searchLettingHouseVo.realityPersonsEnd":query_realityPersonsEnd,
            "searchLettingHouseVo.lettingHouseStruts.id":query_lettingHouseStruts,
            "searchLettingHouseVo.type.id":query_type,
			"searchLettingHouseVo.lettingHouseProperty.id":query_lettingHouseProperty,
			"searchLettingHouseVo.usage.id":query_usage,
			"searchLettingHouseVo.hiddenTroubleLevel.id":query_hiddenTroubleLevel,
			"searchLettingHouseVo.isEmphasis":$("#isLock").val()
		}
	});
	$("#lettingHouseList").trigger("reloadGrid");
}


function deleteLettingHouse(){
	var allValue = setCompVal();
	if(allValue.length ==0){
		 return;
	}
	$.ajax({
		url:'${path}/baseinfo/lettingHouseManage/deleteLettingHouse.action?lettingHouseIds='+ allValue,
		success:function(data){
		$("#lettingHouseList").trigger("reloadGrid");
	    $.messageBox({message:"已经成功删除该"+title+"信息!"});
	    $("input[role='checkbox']").attr("checked",false);
	    disableButtons();
	    checkExport();
        }
	});
}

function setCompVal(){
	var selectedIds = $("#lettingHouseList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#lettingHouseList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#lettingHouseList").getRowData(idCollection[i]);
			if(ent.isEmphasis==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}

function relatePlace(placeIds){
	var str="";
	$.ajax({
		async:false,
		url: "${path}/baseinfo/lettingHouseManage/hasRelatePlace.action?lettingHouseIds="+placeIds,
		success:function(responseData){
			if(responseData){
				str="有"+title+"被引用,要删除未被引用的"+title+"吗?一经删除将无法恢复!";
			}else{
				str=title+"信息删除后，将无法恢复,您确认删除该"+title+"信息吗?";
			}
		}
	});
	return str;
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#lettingHouseList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
</script>
