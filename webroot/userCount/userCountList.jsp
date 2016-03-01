<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style>
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }  
.ui-timepicker-div dl { text-align: left; }  
.ui-timepicker-div dl dt { height: 20px; margin-bottom: -25px; }  
.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }  
.ui-timepicker-div td { font-size: 90%; }  
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; } 
</style>

<script type="text/javascript">
var thisTime=new Date();
var addMonth=thisTime.getMonth()+1;
var formatdate= thisTime.getFullYear()+"-"+addMonth+"-"+thisTime.getDate();

$(function(){
	$("#beginLoginTime").attr("value",'2008-01-01');
	$("#endLoginTime").attr("value",formatdate);
	$("#beginCreateDate").attr("value",'2008-01-01');
	$("#endCreateDate").attr("value",formatdate);
	$("#beginActivationTime").attr("value",'2008-01-01');
	$("#endActivationTime").attr("value",formatdate);
});


$('#beginLoginTime').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$('#endLoginTime').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$('#beginCreateDate').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$('#endCreateDate').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$('#beginActivationTime').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$('#endActivationTime').datePicker({
	yearRange:'2008:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'%y-%M-#{%d}'
});

$(document).ready(function(){
	
	function change(){
		$("#userCountList").setGridParam({
			url:"${path}/sysadmin/userCountManage/userCountList.action",
			datatype: "json",
			page:1
		});
		$("#userCountList").setPostData({
			"organizationId":getCurrentOrgId(),
			"pcOrMobile":function(){return $("#pcOrMobile").val();},
			"beginLoginTime":function(){return $("#beginLoginTime").val();},
			"endLoginTime":function(){return $("#endLoginTime").val();},
			"beginCreateDate":function(){return $("#beginCreateDate").val();},
			"endCreateDate":function(){return $("#endCreateDate").val();},
			"isSelectLoginTime":function(){return $("#isSelectLoginTime").val();},
			"beginActivationTime":function(){return $("#beginActivationTime").val();},
			"endActivationTime":function(){return $("#endActivationTime").val();},
			"isSelectActivationTime":function(){return $("#isSelectActivationTime").val();}
	   	});
		$("#userCountList").trigger("reloadGrid");
	}
	
	
	$("#pcOrMobile").change(function(){
		change();
	});
	$("#beginLoginTime").change(function(){
		change();
	});
	$("#endLoginTime").change(function(){
		change();
	});
	$("#beginCreateDate").change(function(){
		change();
	});
	$("#endCreateDate").change(function(){
		change();
	});
	$("#beginActivationTime").change(function(){
		change();
	});
	$("#endActivationTime").change(function(){
		change();
	});
	
	function getRoleNames(el,options,rowData){
        var j=0;
        var roleNames="";
        if(rowData.roles!=null && rowData.roles.length >0){
        	var n = rowData.roles.length;
            for(var m=0;m<n;m++){
            	j++;
                if(j!=n){
                    roleNames+=rowData.roles[m].roleName+",";
                }else{
                	roleNames+=rowData.roles[m].roleName;
                }
            }
        }else{
        	roleNames="系统管理员"
        }
        return roleNames;
    }
	
	function getPcOrMobile(el,options,rowData){
		var pcOrMobile = "未知";
		if(rowData.pcusable == 1 && rowData.mobileusable == 1){
			pcOrMobile = "PC、手机";	
		}else if(rowData.pcusable == 1 && rowData.mobileusable == 0){
			pcOrMobile = "PC";	
		}else if(rowData.pcusable == 0 && rowData.mobileusable == 1){
			pcOrMobile = "手机";	
		}
		return pcOrMobile;
	}
	
	function formatLastLoginDate (el,options,rowData) {
	    return formatDate(rowData.lastLoginTime);
	}
	
	function formatCreateDate (el,options,rowData) {
	    return formatDate(rowData.createDate);
	}
	
	function formatDate(time){
		var date = new Date(time);
	    return date.getFullYear()+"/"+date.getMonth()+"/"+date.getDate() + " " + (date.getHours()-8)+":"+date.getMinutes()+":"+date.getSeconds();
	}
	
	jQuery("#userCountList").jqGridFunction({
	   	url:'${path}/sysadmin/userCountManage/userCountList.action',
	   	postData: {
	   		"organizationId":getCurrentOrgId(),
			"pcOrMobile":function(){return $("#pcOrMobile").val();},
			"beginLoginTime":function(){return $("#beginLoginTime").val();},
			"endLoginTime":function(){return $("#endLoginTime").val();},
			"beginCreateDate":function(){return $("#beginCreateDate").val();},
			"endCreateDate":function(){return $("#endCreateDate").val();},
			"isSelectLoginTime":function(){return $("#isSelectLoginTime").val();},
			"beginActivationTime":function(){return $("#beginActivationTime").val();},
			"endActivationTime":function(){return $("#endActivationTime").val();},
			"isSelectActivationTime":function(){return $("#isSelectActivationTime").val();}
        },
		datatype: "json",
	   	colModel:[
	   	    {name:'organization.id',index:'organization.id',hidden:true, sortable:true},
	   	 	{name:'userId',index:'userId',hidden:true, sortable:true},
	   		{name:'organization.orgName',label:'所属机构',width:310,index:'organization.orgName', sortable:false},
	   		{name:'userName',label:'用户名',sortable:true,align:'center',width:120},
	   		{name:'name',label:'姓名',sortable:true,align:'center',width:100},
	   		{name:'lastLoginTime',label:'最后登录时间',sortable:true,align:'center',width:150},
	   		{name:'createDate',label:'新增时间',sortable:true,align:'center',width:150},
	   		{name:'activationTime',label:'激活时间',sortable:true,align:'center',width:150},
	   		{name:"roles",index:"name",label:'所在岗位',sortable:false,formatter:getRoleNames,width:70,hidden:false},
	   		{name:'pcusable',label:'账号类型',sortable:true,align:'center',formatter:getPcOrMobile,width:60}
	   	],
	   	showColModelButton:false
	});

	$("#reload").click(function(){
		$("#userCountList").trigger("reloadGrid");
	});
	
	$("#export").click(function(event){
		if ($("#userCountList").getGridParam("records")>0){
			$("#userCountDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出账号统计信息',
				url:PATH+'/common/exportExcel.jsp',
				postData:{
					gridName:'userCountList',
					downloadUrl:PATH+'/sysadmin/userCountManage/downloadUserCountAbout.action'
					},
				buttons: {
		   			"导出" : function(event){
		   				exceldownloadSubmitForm();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
		}
	});
	
	$("#isSelectLoginTime").change(function(event){
		if($("#isSelectLoginTime").attr("checked")=="checked"){
			$("#isSelectLoginTime").val(1);
			$("#beginLoginTime").show();
			$("#endLoginTime").show();
		}else{
			$("#isSelectLoginTime").val(0);
			$("#beginLoginTime").hide();
			$("#endLoginTime").hide();
		}
		change();
	});
	
	$("#isSelectActivationTime").change(function(event){
		if($("#isSelectActivationTime").attr("checked")=="checked"){
			$("#isSelectActivationTime").val(1);
			$("#beginActivationTime").show();
			$("#endActivationTime").show();
		}else{
			$("#isSelectActivationTime").val(0);
			$("#beginActivationTime").hide();
			$("#endActivationTime").hide();
		}
		change();
	});

});
</script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<select id="pcOrMobile" name="pcOrMobile">
			<option value="0" selected="selected">全部</option>
			<option value="1">PC</option>
			<option value="2">手机</option>
		</select>
		
		<input type="checkbox" id="isSelectLoginTime" checked="checked" value="1" />
		
		<label id="lastLabel">最后登录时间：</label>
		<input type="text" id="beginLoginTime" class="form-txt" style="width: 70px;"  readonly />→
		<input type="text" id="endLoginTime"  class="form-txt" style="width: 70px;"  readonly />
					
		<label>新增时间：</label>
		<input type="text" id="beginCreateDate" class="form-txt" style="width: 70px;"  readonly />→
		<input type="text" id="endCreateDate" class="form-txt" style="width: 70px;"  readonly />
		
		
		<input type="checkbox" id="isSelectActivationTime" checked="checked" value="1" />
		<label>激活时间：</label>
		<input type="text" id="beginActivationTime" class="form-txt" style="width: 70px;"  readonly />→
		<input type="text" id="endActivationTime" class="form-txt" style="width: 70px;"  readonly />
		
		<pop:JugePermissionTag ename="downUserCount">
				<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="userCountList"></table>
		<div id="userCountListPager"></div>
	</div>
	<div id="userCountDialog"></div>
</div>

