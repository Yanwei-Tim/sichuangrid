<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization,com.tianque.domain.property.OrganizationLevel"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%
request.setAttribute("viewType",request.getParameter("viewType"));
	Organization userOrg = ThreadVariable.getUser().getOrganization();

	boolean isShowTreeSelect = false;
	Long rootId = userOrg.getId();
	if(userOrg.getParentOrg() != null){
		rootId = userOrg.getParentOrg().getId();
	}
	request.setAttribute("userParentOrgId", rootId);
	if (userOrg.getOrgType().getDisplayName().equals(OrganizationType.getInternalProperties().get(OrganizationType.FUNCTIONAL_ORG).getDisplayName())) {
		request.setAttribute("isFun", true);
		if (userOrg.getDepartmentNo() != null && (userOrg.getDepartmentNo().endsWith("1jg") || userOrg.getDepartmentNo().endsWith("1lx") || userOrg.getDepartmentNo().endsWith("1xw"))) {
			isShowTreeSelect = true;
		}else{
			isShowTreeSelect = false;
		}
	} else {
		request.setAttribute("isFun", false);
		isShowTreeSelect = false;
	}
	request.setAttribute("isShowTreeSelect", isShowTreeSelect);
	
	if (userOrg.getDepartmentNo() != null && userOrg.getDepartmentNo().endsWith("1jg")) {
		request.setAttribute("isShowDeleted", true);
	}
	
	String currentOrgName = "";
	if(userOrg != null && userOrg.getOrgName() !=null){
		currentOrgName = userOrg.getOrgName();
	}
	request.setAttribute("currentOrgName", currentOrgName);
%>

<input type="hidden" name="selectNodeId" id="selectNodeId"/> 
<input type="hidden" name="selectnodeLevel" id="selectnodeLevel"/>
<input type="hidden" name="selectnodeType" id="selectnodeType"/>
<input type="hidden" id="jurisdictionsViewType" value="<s:property value='#parameters.viewType'/>"/>
<input type="hidden" id="jurisdictionsOrgLevel" value="<s:property value='#parameters.orgLevel'/>"/>
<input type="hidden" id="jurisdictionsKeyId" value="<s:property value='#parameters.keyId'/>"/>
<input type="hidden" id="jurisdictionsFunctionalOrgType" value="<s:property value='#parameters.functionalOrgType'/>"/>



<div>
	<c:if test="${!isFun}">
    	<div class="btnbanner btnbannerData" style="float: left;margin-top: 5px">
        	<jsp:include page="/common/orgSelectedComponent.jsp"/>
    	</div>
	</c:if>
	<div class="ui-corner-all" id="nav" style="float: left;">
		 <a id="search" href="javascript:void(0)"><span>查询</span></a> 
		 <a id="view"  href="javascript:void(0)"><span>查看</span></a>
		 <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		 <select id="ledgerType" class="form-txt" style="width: 180px;margin-top: 0px;">
				<option value="" selected="selected">请选择</option>
	         	<option value="11" >水利信息</option>
	         	<option value="12" >交通信息</option>
	         	<option value="16" >能源信息</option>
	         	<option value="13" >教育信息</option>
	         	<option value="110" >科技文体信息</option>
	         	<option value="14" >医疗卫生信息</option>
	         	<option value="17" >劳动和社会保障信息</option>
	         	<option value="18" >环境保护信息</option>
	         	<option value="19" >城乡规划建设管理信息</option>
	         	<option value="15" >农业信息</option>
	         	<option value="111" >其他信息</option>
	         	<option value="2" >困难群众信息</option>
	         	<option value="3" >稳定信息</option>
		</select> 
		
		<label>转换时间：</label>
		<input type="text" id="beginDate" readonly="readonly" class="form-txt" style="width: 120px;margin-top: 0px;" />
		<label>到</label>
		<input type="text" id="endDate" readonly="readonly" class="form-txt" style="width: 120px;margin-top: 0px;" />
	 </div>
	 
	 <c:if test="${isShowTreeSelect}">
		<div id="searchDiv" style="display:inline-block;*display:inline;zoom:1;overflow:hidden;height:30px;"> 
			 <div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="" type="text" value="" id="fastSearchIssueOrg" maxlength="20" class="searchtxt" style="width:200px;height: 25px; margin-top: 5px; background: threedhighlight;" onblur="value=(this.value=='')?'':this.value;" onfocus="value=(this.value=='')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
			</div>
		<a href="javascript:;" id="searchOrgTree">确定</a>
		</div>
	</c:if>
	 
	 
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="ledgerConvertList"></table>
		<div id="ledgerConvertListPager"></div>
	</div>
	<div id="ledgerConvertDialog"></div>
</div>

<script type="text/javascript">
var thisTime=new Date();
var addMonth=thisTime.getMonth()+1;
var formatdate= thisTime.getFullYear()+"-"+addMonth+"-"+thisTime.getDate();

var firstDate=new Date();
firstDate.setDate(1);
var currentMonthFirstDay = firstDate.getFullYear()+"-"+(firstDate.getMonth()+1)+"-"+firstDate.getDate();
$("#beginDate").attr("value",currentMonthFirstDay);
$("#endDate").attr("value",formatdate);
$('#beginDate').datePicker({
	yearRange:'2013:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});
$('#endDate').datePicker({
	yearRange:'2013:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});


var dialogWidth=580;
var dialogHeight=540;
//var currentOrgId="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>";
$(document).ready(function(){
	findLedgerConvert();

	$("#search").click(function(event){
		var orgId = $("#currentOrgId").val();
		var status = '';
		$("#ledgerConvertDialog").createDialog({
			width: 500,
			height: 300,
			title: "事件转台账查询-请输入查询条件",
			url: "${path}/account/ledgerConvert/searchLedgerConvert.jsp",
			buttons: {
		   		"查询" : function(event){
		   			if($("#searchForm").valid()){
		   				searchLedgerConvert(orgId);
			        	$(this).dialog("close");
		   			}
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#view").click(function(){
		viewLedgerConvert(); 
	});
	
	
	
	var orgTree;
	function initOccurOrgSelector() {
        var orgLevelId = $("#jurisdictionsOrgLevel").val();
        var orgFuntionalTypeId = $("#jurisdictionsFunctionalOrgType").val();
        orgTree = $("#fastSearchIssueOrg").treeSelect({
            nodeUrl: '/sysadmin/orgManage/ajaxOrgsForExtTreeByLevel.action?orgFuntionalTypeId=' + orgFuntionalTypeId + '&orgLevelInternalId=' + orgLevelId,
            allOrg: false,
            isRootSelected: false,
            emptyText: '${currentOrgName}',
            isFuncDep:true,
            rootId: ${userParentOrgId}
        });
        orgTree.on("click", function (node, e) {
            if (node != null) {
                var nodeId = node.attributes.id;
                var nodeName = node.attributes.name;
                var nodeLevel = node.attributes.orgLevelInternalId;
                var nodeType = node.attributes.orgTypeInternalId;
                $("#selectNodeId").val(nodeId);
                $("#selectnodeLevel").val(nodeLevel);
                $("#selectnodeType").val(nodeType);
                $("#fastSearchIssueOrg").val(nodeName);
                $("#jurisdictionsKeyId").val(node.attributes.id);
            }
        });
    }
    
    $("#searchOrgTree").click(function () {
        var orgLevelId = $("#jurisdictionsOrgLevel").val();
        var orgFuntionalTypeId = $("#jurisdictionsFunctionalOrgType").val();
        var nodeLevel = $("#selectnodeLevel").val();
        var nodeType = $("#selectnodeType").val();
        var selectNodeId = $("#selectNodeId").val();
        if (orgFuntionalTypeId == "" || orgFuntionalTypeId == null) {
            if (selectNodeId == "") {
                $.messageBox({level: 'warn', message: "请选择组织机构！"});
                return;
            }
            searchLedgerConvert(selectNodeId);
        } else {
            var orgTypeInternal = '<s:property value='@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG'/>';
            if (selectNodeId == "") {
                $.messageBox({level: 'warn', message: "请选择组织机构！"});
                return;
            }
            searchLedgerConvert(selectNodeId);
        }
    });
	
	<c:if test="${isShowTreeSelect}">
		initOccurOrgSelector();
	</c:if>
});


function findLedgerConvert(){
	var currentOrgId = $("#currentOrgId").val();
	var convertBegin = $("#beginDate").val();
	var convertEnd = $("#endDate").val();
	var ledgerType = $("#ledgerType").val();
	jQuery("#ledgerConvertList").jqGridFunction({
		url:"${path}/threeRecords/ledgerConvert/findLedgerConverts.action?searchVo.createOrg.id="+currentOrgId+"&searchVo.status=1&searchVo.convertBegin="+convertBegin+"&searchVo.convertEnd="+convertEnd+"&searchVo.ledgerType="+ledgerType,
		datatype: "json",
	   	colModel:[
			{name:'id',frozen:true,hidden:true, index:'id',sortable:false},
			{name:'issueId',frozen:true,hidden:true, index:'issueId',sortable:false},
	   		{name:'serialNumber', label:'事件编码', sortable:false,width:190},
	   		{name:'ledgerSerialNumber',hidden:true, label:'新台账编码', sortable:false,width:190},
	   		{name:'issueTypeName', label:'事件类型', sortable:false,width:200},
	   		{name:'issueName', label:'事件名称', sortable:false}, 
	   		{name:'name', label:'当事人', sortable:false},
	   		{name:'mobile', label:'当事人电话', sortable:false},
	   		{name:'occurDate', label:'事件发生时间', sortable:false},
	   		{name:'convertDate', label:'转换时间', sortable:false},
	   		{name:'ledgerType',hidden:true, label:'台账类型', sortable:false,width:190,
	   			formatter:function(el,options,rowData){
	   			if(rowData.ledgerType==11)return"水利信息"; 
	   			else if(rowData.ledgerType==15)return"农业信息";
	   			else if(rowData.ledgerType==12)return"交通信息";
	   			else if(rowData.ledgerType==16)return"能源信息";
	   			else if(rowData.ledgerType==13)return"教育信息";
	   			else if(rowData.ledgerType==110)return"科技文体信息";
	   			else if(rowData.ledgerType==14)return"医疗卫生信息";
	   			else if(rowData.ledgerType==17)return"劳动和社会保障信息";
	   			else if(rowData.ledgerType==18)return"环境保护信息";
	   			else if(rowData.ledgerType==19)return"城乡规划建设管理信息";
	   			else if(rowData.ledgerType==111)return"其他信息";
	   			else if(rowData.ledgerType==2)return"困难群众信息";
	   			else if(rowData.ledgerType==3)return"稳定信息";
	   			else return "";
	   		}},
	   		{name:'occurOrg.orgName',label:'发生网格',index:'occurOrg',sortable:true},
	   		{name:'description', label:'描述',sortable:true,width:400}
	   	],
	   	loadComplete: function(){
			var count=$("#ledgerConvertList").getGridParam("records");
			if(!isNaN(count)){
					if(count>99) $("#historyCount").html("99+");
					else $("#historyCount").html(count);
			}
		},
	   	shrinkToFit:true,
	   	showColModelButton:true,
	   	onSelectRow:function(id,option){},
	   	ondblClickRow:viewLedgerConvert,
	   	multiselect:false
	});
}

function viewLedgerConvert(){
	var selectedId = $("#ledgerConvertList").jqGrid("getGridParam", "selrow");
	if(selectedId==null){
		$.messageBox({level:'warn',message:"没有可查看的数据！"});
		return ;
	}
	$("#ledgerConvertDialog").createDialog({
		width: 880,
		height: 350,
		title: "查看事件转入信息",
		url: "${path}/threeRecords/ledgerConvert/dispatch.action?mode=view&id="+selectedId,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function searchLedgerConvert(orgId){
	var data=$("#searchForm").serializeArray();
	$("#ledgerConvertList").setGridParam({
		url:"${path}/threeRecords/ledgerConvert/findLedgerConverts.action?searchVo.createOrg.id="+orgId+"&searchVo.status=1",
		datatype: "json",
		page : 1
	});
	$("#ledgerConvertList").setPostData(data);
	$("#ledgerConvertList").trigger("reloadGrid");
}
$("#refresh").click(function(){
	reloadLedger();
});

$('#ledgerType,#beginDate,#endDate').change(function(event){
	reloadLedger();
});

function reloadLedger(){
	$("#ledgerConvertList").setGridParam({
		url:"${path}/threeRecords/ledgerConvert/findLedgerConverts.action",
		datatype: "json"
	});
	$("#ledgerConvertList").setPostData({
		"searchVo.createOrg.id":$("#currentOrgId").val(),
		"searchVo.status":1,
		"searchVo.convertBegin":$("#beginDate").val(),
		"searchVo.convertEnd":$("#endDate").val(),
		"searchVo.ledgerType":$("#ledgerType").val()
	});
	
	$("#ledgerConvertList").trigger("reloadGrid");
}
</script>