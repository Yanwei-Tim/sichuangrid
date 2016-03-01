<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<script type="text/javascript">
	<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	function operatorFormatter(el,options,rowData){
		
		return "<pop:JugePermissionTag ename='updateScenicSpot'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteScenicSpot'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
	
	function nameFont(el,options,rowData){
		if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
			return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicSpot'>  onclick='viewScenicSpot("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.name+"</font></a>";
		}
		return "<a href='javascript:;' <pop:JugePermissionTag ename='viewScenicSpot'> onclick='viewScenicSpot("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
	}
	
	function attentionExtentColor(el,options,rowData){
		var displayName=attentionExtentFormatter(el,options,rowData);
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='中等')
			return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
	}
	
	var gridOption={
			colModel:[
		      {name:"id", index:"id",hidden:true,sortable:false, frozen :true},
		      {name:"encryptId", index:"id",hidden:true,sortable:false, frozen :true},
		  	  {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		  	  //{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
		      {name:"name",index:"name",label:"景点名称",width:100,sortable:true,formatter:nameFont},
		      {name:"address",index:"address",label:"景点地址",width:200,sortable:true/*,formatter:addressColorFormatter*/},
		      {name:"grade",index:"grade",label:"景点等级",width:200,sortable:true},
		      {name:"telephone",index:"telephone",label:"景点电话",width:200,sortable:true},
		      {name:"personLiable",index:"personLiable",label:"景点负责人",sortable:true,width:100,hidden:false},
		      {name:"personLiableTelephone",index:"personLiableTelephone",label:"景点负责人电话",sortable:true,width:100,hidden:false},
			  {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			  {name:'createDate', sortable:true, label:'数据创建时间', width:140},
			  {name:'updateDate', sortable:true, label:'数据更新时间', width:140},
		      {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		      {name:"logOutTime",index:"logOutTime",label:"注销时间",sortable:true,width:100,hidden:true,align:'center'},
		      {name:"logOutReason",index:"logOutReason",label:"注销原因",sortable:true,width:100,hidden:true},
		      {name:"remark",index:"remark",label:"备注",sortable:true,width:200,hidden:true}
			]
		};
	var dialogWidth=850;
	var dialogHeight=600;

	function addressColorFormatter(el,options,rowData){
		if(rowData.address !=null && rowData.address !="null"){
			if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
				return "<font color='#778899'>"+rowData.address+"</font>";
			}
			return "<font color='#000'>"+rowData.address+"</font>";
		}else{
			return "";
		}
	}
	
	function getLocationId(rowData){
		return rowData.id;
	}
	function getLocationName(rowData){
		return $(rowData.name).text();
	}
</script>
<jsp:include page="/baseinfo/scenicManage/common/commonLocationList.jsp">
	<jsp:param value="ScenicSpot" name="moduleName"/>
	<jsp:param value="旅游景点" name="moduleCName"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>