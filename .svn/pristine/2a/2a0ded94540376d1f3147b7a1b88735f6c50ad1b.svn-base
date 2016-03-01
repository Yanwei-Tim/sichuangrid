<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
<pop:formatterProperty name="equipType" domainName="@com.tianque.domain.property.PropertyTypes@SCENICEQUIP_TYPES" />
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateScenicEquipment'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteScenicEquipment'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicEquipment'>  onclick='viewScenicEquipment("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.equipName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicEquipment'> onclick='viewScenicEquipment("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.equipName+"</a>";
}

	var gridOption={
			colModel:[
	          {name:"id", index:"id",hidden:true,sortable:false, frozen :true},
	          {name:"encryptId", index:"id",hidden:true,sortable:false, frozen :true},
	      	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,sortable:false,align:'center' },
	          {name:"equipName",index:"equipName",label:"名称",width:200,sortable:true,formatter:nameFont},
	          {name:"equipAddress",index:"equipAddress",label:"地址",width:200,sortable:true},
	          {name:"equipType",index:"equipType",label:"类型",sortable:true,width:100,hidden:false,formatter:equipTypeFormatter},
	          {name:"mobile",index:"mobile",label:"联系电话",sortable:true,width:200},
	          {name:"manager",index:"manager",label:"负责人",sortable:true,width:80},	          
	          {name:"managerMobile",index:"managerMobile", sortable:true, label:'负责人联系电话', width:140,align:'center'},
			  {name:"aroundScenic",index:"aroundScenic", sortable:true, label:'周边景点',width:300},
			  {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
		      {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
			  {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
	          {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true},
	          {name:"isEmphasis",label:'是否关注',sortable:false,hidden:true,hidedlg:true,width:100,align:'center'}
				]
			};
	var dialogWidth=850;
	var dialogHeight=400;

	function placeAddressColorFormatter(el,options,rowData){
	if(rowData.placeAddress !=null && rowData.placeAddress !="null"){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<font color='#778899'>"+rowData.placeAddress+"</font>";
		}
		return "<font color='#000'>"+rowData.placeAddress+"</font>";
	}else{
		return "";
	}
	}
	function getLocationName(rowData){
		return $(rowData.equipName).text();
	}
</script>
<jsp:include page="/baseinfo/scenicManage/common/commonLocationList.jsp">
	<jsp:param value="ScenicEquipment" name="moduleName"/>
	<jsp:param value="配套设施" name="moduleCName"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>