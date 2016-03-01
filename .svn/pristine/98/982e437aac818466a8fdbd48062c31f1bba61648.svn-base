<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div style="width: 100%;">
	<table id="peripheryDetailList"></table>
	
</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

$(document).ready(function(){
	
	jQuery("#peripheryDetailList").jqGridFunction({
	   datatype: "local",	
	   colModel: [
	         {name:"id_key", index:"id",hidden:true  },
	         {name:"type", index:"type",hidden:true  },
	         {name:'sid',index:"sid",hidden:true },
	  		<%
	  			String mode = request.getParameter("mode");
	         	if("place".equals(mode)){
	  		%>
	         	{name:'name',index:"name",label:"姓名",width:120,align:'center'},
	  			{name:"gender",index:"gender",label:"性别",formatter:genderFormatter,width:55,align:'center'},
		     	{name:"idCardNo", index:"idCardNo", width:180,label:"身份证号码",align:'center'},
		     <%
	         	}else if("person".equals(mode)){
		     %>
		     	{name:'name',index:"name",label:"名称",width:120,align:'center'},
		     	{name:"address", index:"address", width:240,label:"地址",align:'center'},
		    <%}%>
		     {name:"typeName", index:"typeName", width:100,label:"类型",align:'center'},
	  		 {name:"distance",index:"distance",label:"距离范围（米）",width:105,align:'center'},
		     {name:'operate',index:'id',label:"操作",width:130,align:"center",sortable:false},
		     {name:"pointX", index:"pointX",hidden:true},
		     {name:"pointY", index:"pointY",hidden:true}
	  			
	    ],
	   width:720,
	   height:425,
	   ondblClickRow: doubleClickTable,
	   gridComplete:function(){ 
			var ids=jQuery("#peripheryDetailList").jqGrid('getDataIDs'); 
	   		
			for(var i=0; i<ids.length; i++){ 
				var id=ids[i]; 
				
				var pointX = $("#peripheryDetailList").getCell(id,"pointX");
				var pointY = $("#peripheryDetailList").getCell(id,"pointY");
				var sid = $("#peripheryDetailList").getCell(id,"sid");
				var type = $("#peripheryDetailList").getCell(id,"type");
				
				var operate ="<a href='#' style='color: blue;' onclick='window.parent.peripheryMoveTo("+pointX+","+pointY+")'>定位</a>"; 
				operate +="&nbsp;&nbsp;";
				operate +="<a href='#' style='color: blue;' onclick='viewDetail("+sid+",\""+type+"\")'>查看</a>";
				jQuery("#peripheryDetailList").jqGrid('setRowData', ids[i], { operate: operate}); 
			} 
		} 
		
		
	});
	
	
	$("#peripheryDetailList").setGridParam({
		url:'${path}/gis/commonGisManage/getPageInfoForSearchPeriphery.action',
		datatype: "json"
	});
	
	$("#peripheryDetailList").setPostData({
 		"peripheryVo.elements":"<s:property value='#parameters.elements'/>",
		"peripheryVo.range":<s:property value='#parameters.range'/>,
		"peripheryVo.centerX":<s:property value='#parameters.centerX'/>,
		"peripheryVo.centerY":<s:property value='#parameters.centerY'/>,
		"mode":"<s:property value='#parameters.mode'/>",
		//"peripheryVo.num":<s:property value='#st.index'/>,
 		//"page":1,
 		//"sidx":"",
		//"sord":"",
		"rows":<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@SEARCH_PERIPHERY_NUM'/>,
		"type":"<s:property value='#parameters.type'/>"
		
	});
	
	$("#peripheryDetailList").trigger("reloadGrid");
	
	
	


});


function doubleClickTable(cellvalue, options, rowObject){
	
	var rowdata=jQuery("#peripheryDetailList").jqGrid('getRowData',cellvalue);  
	
	var type = rowdata["type"];
	var id = rowdata["sid"];
	viewDetail(id,type);
	
}

function viewDetail(id,type){
	var model = $("#mode").val();
	
	if(model == "person"){
		parent.getKeyPlace(id,type);
	}else if(model == "place"){
		parent.getPermanentResident(id);
	}
}
	
</script>



