<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
	 function operateFormatter(el, options, rowData){
			return "<pop:JugePermissionTag ename='updateWorkingRecord'><a href='javascript:updateFun("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteWorkingRecord'><a href='javascript:deleteFun("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
	}
	 function nameFont(el,options,rowData){
			return "<pop:JugePermissionTag ename='viewWorkingRecord,viewAreaWorkingRecord'><a href='javascript:viewFun("+rowData.id+")'>"+rowData.name+"</a></pop:JugePermissionTag>";
	}
	 function formatterAttachFile(el,options,rowData){
			if(rowData.dailyLogAttachFile.length>0){
				return "<div style='clear:both' align='center'><a href='javascript:void(0);' id='workingRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' workingRecordId='"+rowData.id+"' >附件>></a></div>";
			}
			return "";
	}
	
var gridOption = {
		colModel:[
		   {name:"id",index:"id",hidden:true},
		   {name:"operate", index:"operate",label:'操作',align:'center',width:65,formatter:operateFormatter,frozen : true},
		   {name:'name',sortable:true,label:'名称',width:200,formatter:nameFont},
		   {name:'dailyLogType',index:'dailyLogType',label:'类型',formatter:dailyLogTypeFormatter,sortable:true,width:200},
		   {name:"dailyLogAttachFile",label:'附件',sortable:false,width:80,formatter:formatterAttachFile},
		   {name:'subject',hidden:true,label:'主题',sortable:false,width:350},
		   {name:"dealDate",hidden:true,label:'时间',align:'center',sortable:false,width:100},
		   {name:'participant',hidden:true,label:'参加人员',sortable:false,width:100},
		   {name:'proceedSite',hidden:true,label:'地点',sortable:false,width:150},
		   {name:'createDate',hidden:false,label:'创建时间',align:'center',sortable:false,width:150},
		   {name:'content',hidden:true,label:'内容',sortable:false,width:150}
		]
	};
</script>
<jsp:include page="/daily/workingRecord/workingRecordList.jsp"></jsp:include>



