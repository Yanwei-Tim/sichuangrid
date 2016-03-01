<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" id="viewManageTeamMember">
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="manageTeamMemberList"> </table>
		<div id="manageTeamMemberListPager"></div>
	</div>
</div>

<div id="manageTeamMemberDialog"></div>
<script type="text/javascript">
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}
$(function(){
	
	$("#manageTeamMemberList").jqGridFunction({
		datatype: "local",
		height: 390,
		colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operatorFormatter,width:120,align:"center"},
			{name:'business',index:"business",label:'职务',sortable:true,width:80,align:"center"},
			{name:'name',index:"name",label:'姓名',sortable:true,width:80,align:"center"},
	        {name:'gender',index:'gender',label:'性别',sortable:true,width:40,align:"center"},
	        {name:'age',index:'age',label:'年龄',sortable:true,width:40,align:"center"},
	        {name:'status',index:'status',label:'政治面貌',sortable:true,width:80,align:"center"},
	        {name:'merit',index:'merit',label:'特长',sortable:true,width:80,align:"center"},
	        {name:'mobile',index:'mobile',label:'联系手机',sortable:true,width:90,align:"center"},
	        {name:'year',index:'year',label:'出生年份',sortable:true,width:60,align:"center"},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true,width:100,align:"center"},
	        {name:'serviceGrid',index:'serviceGrid',label:'服务网格',sortable:true,width:100,align:"center"},
	        {name:'serviceName',index:'serviceName',label:'网格管理员名称',sortable:true,width:100,align:"center"}
	        
	    	//{name:'seq', index:'seq',label:'排序', width:100, sortable:true, align:'center', hidden:false}, 	
// 	    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true}, 
// 	    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true}
		],
		multiselect:false
	});
	
	jQuery("#manageTeamMemberList").jqGrid('setFrozenColumns');
	var dataId =$("#dataId").val();
	var managedata = [];
	if(dataId==81){
		managedata = [
       		{id:"1",business:"队  长",name:"王建强",gender:"男",age:"49",status:"党员",merit:"",mobile:"13890360389"},
       		{id:"2",business:"联络员",name:"任忠",gender:"男",age:"45",status:"党员",merit:"",mobile:"13990305528"},
       		{id:"3",business:"队员1",name:"刘柯",gender:"男",age:"35",status:"党员",merit:"",mobile:"15883330222"},
       		{id:"4",business:"队员2",name:"刘文忠",gender:"男",age:"47",status:"党员",merit:"",mobile:"13568312839"},
       		{id:"5",business:"队员3",name:"罗平",gender:"男",age:"49",status:"党员",merit:"",mobile:"13778857899"},
       		{id:"6",business:"队员4",name:"马正林",gender:"男",age:"43",status:"党员",merit:"",mobile:"13990309060"},
       		{id:"7",business:"队员5",name:"罗军",gender:"男",age:"49",status:"党员",merit:"",mobile:"13890368999"},
       		{id:"8",business:"队员6",name:"郭蜀梅",gender:"女",age:"44",status:"党员",merit:"",mobile:"13708160621"}
       	];
	}else if(dataId==82){
		managedata = [
       		{id:"1",business:"队  长",name:"王平",gender:"男",age:"41",status:"党员",merit:"管理",mobile:"13708166338"},
       		{id:"2",business:"联络员",name:"王继东",gender:"男",age:"39",status:"",merit:"办公室",mobile:"13708166031"},
       		{id:"3",business:"队员1",name:"许兴盛",gender:"男",age:"42",status:"党员",merit:"管理",mobile:"13909035022"},
       		{id:"4",business:"队员2",name:"杨加兵",gender:"男",age:"40",status:"党员",merit:"安装、维修",mobile:"13778866120"},
       		{id:"5",business:"队员3",name:"毛伟",gender:"男",age:"31",status:"党员",merit:"水质化验",mobile:"13795524259"},
       		{id:"6",business:"队员4",name:"李正芳",gender:"男",age:"25",status:"",merit:"维修、焊接",mobile:"13778836675"},
       		{id:"7",business:"队员5",name:"张海霞",gender:"女",age:"35",status:"",merit:"抄表、收费",mobile:"13990325879"},
       		{id:"8",business:"队员6",name:"金晓娇",gender:"女",age:"26",status:"",merit:"抄表、收费",mobile:"15082326915"},
       		{id:"9",business:"队员7",name:"易晓娟",gender:"女",age:"36",status:"",merit:"抄表、收费",mobile:"13890333893"}
       	];
	}
	for(var i=0;i<=managedata.length;i++){
  		jQuery("#manageTeamMemberList").jqGrid('addRowData',i+1,managedata[i]);
  	}
  	
});

function updateOperator(selectedId){
	
}

function deleteOperator(selectedId){
	
}
</script>