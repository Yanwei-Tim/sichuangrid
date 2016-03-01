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
		url:"${path}/baseinfo/orgLocationTracksManage/findOrgLocationTracksByOrgIdAndName.action?orgLocationOrgId=<s:property value='#parameters.orgLocationOrgId'/>&orgLocationName=<s:property value='#parameters.orgLocationName'/>",
		datatype: "json",
		colModel:[
	    	{name:"id", index:"id", hidden:true},
  			{name:"operationDate", index:"operationDate",label:"时间",width:120},
  			{name:"orgLocationOrganization.orgName", index:"orgLocationOrganization.orgName", width:200, label:"所属网格"},
  			{name:"oldOrgLocationOrganization.orgName", index:"oldOrgLocationOrganization.orgName", width:160, label:"曾经所属网格",hidden:true},
  			{name:"operationOrganization.orgName", index:"operationOrganization.orgName", width:160, label:"操作用户所属网格",hidden:true},
  			{name:"orgLocationName", index:"orgLocationName", width:120, label:"场所名称",hidden:true},
  			{name:"orgLocationType", index:"orgLocationType", width:60, label:"场所类型",hidden:true,formatter:orgLocationTypeFormatter},
  			{name:"operationType", index:"operationType", width:80, label:"操作类型",hidden:true,formatter:operationTypeFormatter},
  			{name:"orgLocationinitType", index:"orgLocationinitType", width:100, label:"类型",formatter:residentTypeFormatter},
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
function orgLocationTypeFormatter(el,options,rowData){
	var datavalue = rowData.orgLocationType;
    if(datavalue.toUpperCase()=='SAFETYPRODUCTIONKEY'){
        return "安全生产重点";
    }
    if(datavalue.toUpperCase()=='FIRESAFETYKEY'){
         return "消防安全重点";
    }
    if(datavalue.toUpperCase()=='SECURITYKEY'){
        return "治安重点";
    }
    if(datavalue.toUpperCase()=='SCHOOL'){
       return "学校";
    }
    if(datavalue.toUpperCase()=='OTHERLOCALE'){
       return "其他场所";
    }
    if(datavalue.toUpperCase()=='DANGEROUSCHEMICALSUNIT'){
       return "危险化学品单位";
    }
    if(datavalue.toUpperCase()=='INTERNETBAR'){
       return "网吧";
    }
    if(datavalue.toUpperCase()=='PUBLICPLACE'){
        return "公共场所";
     }
    if(datavalue.toUpperCase()=='PUBLICCOMPLEXPLACES'){
        return "公共复杂场所";
     }
    if(datavalue.toUpperCase()=='actualCompany'){
        return "实有单位";
     }
    if(datavalue.toUpperCase()=='NEWSOCIETYORGANIZATIONS'){
        return "新社会组织";
     }
    if(datavalue.toUpperCase()=='NEWECONOMICORGANIZATIONS'){
    	return "新经济组织";
    }
    if(datavalue.toUpperCase()=='ENTERPRISEKEY'){
        return "规上企业";
     }
    if(datavalue.toUpperCase()=='ENTERPRISEDOWNKEY'){
    	return "规下企业";
    }
    return "";
}
function aa(datavalue){
	if(datavalue.toUpperCase()=='SAFETYPRODUCTIONKEY'){
        return "安全生产重点";
    }
    if(datavalue.toUpperCase()=='FIRESAFETYKEY'){
         return "消防安全重点";
    }
    if(datavalue.toUpperCase()=='SECURITYKEY'){
        return "治安重点";
    }
    if(datavalue.toUpperCase()=='SCHOOL'){
       return "学校";
    }
    if(datavalue.toUpperCase()=='OTHERLOCALE'){
       return "其他场所";
    }
    if(datavalue.toUpperCase()=='DANGEROUSCHEMICALSUNIT'){
       return "危险化学品单位";
    }
    if(datavalue.toUpperCase()=='INTERNETBAR'){
       return "网吧";
    }
    if(datavalue.toUpperCase()=='PUBLICPLACE'){
        return "公共场所";
     }
    if(datavalue.toUpperCase()=='actualCompany'){
        return "实有单位";
     }
    if(datavalue.toUpperCase()=='NEWSOCIETYORGANIZATIONS'){
        return "新社会组织";
     }
    if(datavalue.toUpperCase()=='NEWECONOMICORGANIZATIONS'){
    	return "新经济组织";
    }
    if(datavalue.toUpperCase()=='ENTERPRISEKEY'){
        return "规上企业";
     }
    if(datavalue.toUpperCase()=='ENTERPRISEDOWNKEY'){
    	return "规下企业";
    }
    return "";
}
function residentTypeFormatter(el,options,rowData){
	var datavalue = rowData.orgLocationinitType;
	if(datavalue=='1'){
		return "组织场所信息类";
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
    	return rowData.orgLocationName+"在"+rowData.orgLocationOrganization.orgName+"的"+aa(rowData.orgLocationType)+"中被"+qq(rowData.operationType);
    }
	if(datavalue=='关注'||datavalue=='重新关注'||datavalue=='取消关注'){
		return rowData.orgLocationName+"在"+aa(rowData.orgLocationType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=='转移'){
		return rowData.orgLocationName+"从"+rowData.oldOrgLocationOrganization.orgName+"的"+aa(rowData.orgLocationType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=='迁出'){
		return rowData.orgLocationName+"从"+rowData.oldOrgLocationOrganization.orgName+"中"+qq(rowData.operationType);
	}
	if(datavalue=='迁入到'){
		return rowData.orgLocationName+""+qq(rowData.operationType)+""+rowData.oldOrgLocationOrganization.orgName;
	}
	if(datavalue=="注销"){
		return rowData.orgLocationName+"在"+aa(rowData.orgLocationType)+"中"+qq(rowData.operationType);
	}
	if(datavalue=='删除'){
		return rowData.orgLocationName+"在"+aa(rowData.orgLocationType)+"中被"+qq(rowData.operationType);
	}
	if(datavalue=="取消注销"){
		return rowData.orgLocationName+"在"+aa(rowData.orgLocationType)+"中"+qq(rowData.operationType);
	}

}
</script>