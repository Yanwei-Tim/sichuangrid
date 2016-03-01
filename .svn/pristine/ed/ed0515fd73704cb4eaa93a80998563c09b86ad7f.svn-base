<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<input type="hidden" id="idCardNo" name="idCardNo" value="<s:property value="#parameters.idCardNo"/>"/>

<div class="content">
	<div style="width: 100%;">
		<table id="pList"></table>
		<div id="pListPager"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	    $("#pList").jqGridFunction({
		url:"${path}/baseinfo/houseTracksManage/findHouseTracksByHouseId.action?houseId=<s:property value='#parameters.houseId'/>",
		datatype: "json",
		colModel:[
	    	{name:"id", index:"id", hidden:true},
  			{name:"operationDate", index:"operationDate",label:"时间",width:120},
  			{name:"houseOrganization.orgName", index:"houseOrganization.orgName", width:200, label:"所属网格"},
  			{name:"oldHouseOrganization.orgName", index:"oldHouseOrganization.orgName", width:160, label:"曾经所属网格",hidden:true},
  			{name:"operationOrganization.orgName", index:"operationOrganization.orgName", width:160, label:"操作用户所属网格",hidden:true},
  			{name:"houseAddress", index:"houseAddress", width:160, label:"房屋地址",hidden:true},
  			{name:"houseType", index:"houseType", width:60, label:"房屋类型",hidden:true,formatter:houseTypeFormatter},
  			{name:"operationType", index:"operationType", width:80, label:"操作类型",hidden:true,formatter:operationTypeFormatter},
  			{name:"houseinitType", index:"houseinitType", width:100, label:"类型",formatter:residentTypeFormatter},
  			{name:"operationContent", index:"operationContent", width:250, label:"操作",formatter:operationContentFormatter}

  		],
  		page:1,
  		width: <s:property value="#parameters.width"/>,
		height: <s:property value="#parameters.height"/>
	});
});
function operationTypeFormatter(el,options,rowData){
	var datavalue = rowData.operationType;
	if(datavalue=='1'){
		return "录入";
	}
	if(datavalue=='2'){
		return "转移";
	}
	if(datavalue=='3'){
		return "迁入";
	}
	if(datavalue=='4'){
		return "迁出";
	}
	if(datavalue=='5'){
		return "注销";
	}
	if(datavalue=='6'){
		return "取消注销";
	}
	if(datavalue=='7'){
		return "删除";
	}
	return "";
}
function qq(datavalue){
	if(datavalue=='1'){
		return "录入";
	}
	if(datavalue=='2'){
		return "转移";
	}
	if(datavalue=='3'){
		return "迁入";
	}
	if(datavalue=='4'){
		return "迁出";
	}
	if(datavalue=='5'){
		return "注销";
	}
	if(datavalue=='6'){
		return "取消注销";
	}
	if(datavalue=='7'){
		return "删除";
	}
	return "";
}
function houseTypeFormatter(el,options,rowData){
	var datavalue = rowData.houseType;
    if(datavalue.toUpperCase()=='ACTUALHOUSE'){
        return "房屋信息";
    }
    if(datavalue.toUpperCase()=='RENTALHOUSE'){
         return "出租房";
    }
    if(datavalue.toUpperCase()=='builddatasData'){
        return "楼宇";
    }
    return "";
}
function aa(datavalue){
	if(datavalue.toUpperCase()=='ACTUALHOUSE'){
        return "房屋信息";
    }
    if(datavalue.toUpperCase()=='RENTALHOUSE'){
         return "出租房";
    }
    if(datavalue.toUpperCase()=='builddatasData'){
        return "楼宇";
    }
    return "";
}
function residentTypeFormatter(el,options,rowData){
	var datavalue = rowData.houseinitType;
	if(datavalue=='1'){
		return "房屋信息类";
	}
	if(datavalue=='2'){
		return "转移流动信息类";
	}
	if(datavalue=='3'){
		return "迁户注销信息类";
	}
	return "";

}

function operationContentFormatter(el,options,rowData){
    var datavalue=qq(rowData.operationType);
    if(datavalue=='录入'){
    	return rowData.houseAddress+"在"+rowData.houseOrganization.orgName+"的"+aa(rowData.houseType)+"中被"+qq(rowData.operationType);
    }
	if(datavalue=='关注'||datavalue=='重新关注'||datavalue=='取消关注'){
		return rowData.houseAddress+"在"+aa(rowData.houseType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=='转移'){
		return rowData.houseAddress+"从"+rowData.oldHouseOrganization.orgName+"的"+aa(rowData.houseType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=='迁出'){
		return rowData.houseAddress+"从"+rowData.oldHouseOrganization.orgName+"中"+qq(rowData.operationType);
	}
	if(datavalue=='迁入到'){
		return rowData.houseAddress+""+qq(rowData.operationType)+""+rowData.oldHouseOrganization.orgName;
	}
	if(datavalue=="注销"){
		return rowData.houseAddress+"在"+aa(rowData.houseType)+"中"+qq(rowData.operationType);
	}
	if(datavalue=='删除'){
		return rowData.houseAddress+"在"+aa(rowData.houseType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=="取消注销"){
		return rowData.houseAddress+"在"+aa(rowData.houseType)+"中"+qq(rowData.operationType);
	}

}
</script>