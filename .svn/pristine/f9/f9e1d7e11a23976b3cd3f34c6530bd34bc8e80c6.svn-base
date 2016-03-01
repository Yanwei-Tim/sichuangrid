<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%request.setAttribute("moduleName",request.getParameter("moduleName"));
request.setAttribute("moduleNameTemp",request.getParameter("moduleNameTemp"));
request.setAttribute("mode",request.getParameter("mode"));
%>

<script type="text/javascript">
function hasServiceTeamMemberFormatter(el,options,rowData){
	if(rowData.hasServiceTeamMember!=undefined){
		if(rowData.hasServiceTeamMember==0){
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
		}else{
			return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
		}
	}else{
		return "<s:property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
	}
	
}
function nameFont(el,options,rowData){
    var moduleName = "${moduleName}";
	if(null == rowData.name) {
		return "&nbsp;&nbsp;"
	}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
		return '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="red">'+rowData.name+'</font></a>';
	}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
		return '<a href="javascript:;"  <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="#778899">'+rowData.name+'</font></a>';
	}else if(moduleName!=undefined && moduleName!=null && moduleName !="" && moduleName=="FloatingPopulation"){
		return  floatingPopulationFontmat(el,options,rowData,moduleName);
	}
	return '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font></a>';
}

function floatingPopulationFontmat(el,options,rowData,moduleName){
	var province = rowData.province;
	if(province!=undefined && province!=null && province!='' && province=='新疆维吾尔自治区'){
		return  '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font>'+
		'<pop:JugePermissionTag ename="tibetSinkiangIdentification"><img src="${resource_path}/resource/images/InvolveSinkiang.png"/></pop:JugePermissionTag></a>';
// 	}else if(province!=undefined && province!=null && province!='' && province=='西藏'){
// 		return '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font>'+
// 		'<pop:JugePermissionTag ename="tibetSinkiangIdentification"><img src="${resource_path}/resource/images/InvolveTibet.png"/></pop:JugePermissionTag></a>';
	}else{
		return '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font></a>';
	}
}

function idCardNoFont(el,options,rowData){
	if(null == rowData.idCardNo) {
		return "&nbsp;&nbsp;"
	}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
		return '<a href="javascript:;"  <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> ><font color="#778899">'+rowData.idCardNo+'</font></a>';
	}else{
		return '<a href="javascript:;" <pop:JugePermissionTag ename="view${moduleName}"> onclick="viewDialog(event,'+rowData.id+')" </pop:JugePermissionTag> >'+rowData.idCardNo+'</a>';
	}
}

function householdRegisterFormatter(el, options, rowData){
	var str = "";
	if(rowData.province != null)
		str += rowData.province;
	if(rowData.city != null)
		str += rowData.city
	if(rowData.district != null)
		str += rowData.district;
	return str;
}
function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='update${moduleName}'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+")'><span>修改</span></a> </pop:JugePermissionTag><pop:JugePermissionTag ename='delete${moduleName}'>| <a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+")'><span>删除</span></a> </pop:JugePermissionTag>";

}
//服务团队操作
function operateFormatter_ServiceTeam(el,options,rowData){
	return "<pop:JugePermissionTag ename='update${moduleName}'><a href='javascript:' onclick='updateOperator("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='delete${moduleName}'> | <a href='javascript:;' onclick='deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}
//服务成员操作
function operateFormatter_ServiceTeam_Member(el,options,rowData){
	return "<pop:JugePermissionTag ename='update${moduleName}'><a href='javascript:' onclick='updateOperator("+rowData.baseId+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='delete${moduleName}'> | <a href='javascript:;' onclick='deleteOperator("+rowData.baseId+")'><span>删除</span></a></pop:JugePermissionTag>";
}
//服务记录操作
function operateFormatter_ServiceRecord(el,options,rowData){
	return "<pop:JugePermissionTag ename='update${moduleName}'><a href='javascript:' onclick='updateRecordOperator("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='delete${moduleName}'> | <a href='javascript:;' onclick='deleteRecordOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}
//服务对象（删除）操作
function operateFormatter_ServiceObject(el,options,rowData){
	return "<a href='javascript:' onclick='deleteObjectOperator("+rowData.objectId+")'><span>删除</span></a>";
}
//服务成员查看
function nameFont_ServiceTeamMember(el,options,rowData){
	return "<pop:JugePermissionTag ename='view${moduleName}'><a href='javascript:viewServiceMember("+rowData.baseId+")'></pop:JugePermissionTag>"+rowData.name+"<pop:JugePermissionTag ename='view${moduleName}'></a></pop:JugePermissionTag>";
}
//服务成员移除待合并的重复数据
function remove_ServiceTeamMember(el,options,rowData){
	return "<a href='javascript:' onclick='removeServiceMember("+rowData.baseId+")'><span>移除</span></a>";
}
//服务团队查看
function nameFont_ServiceTeam(el,options,rowData){
	return "<pop:JugePermissionTag ename='view${moduleName}'><a href='javascript:viewDialog("+rowData.id+")'></pop:JugePermissionTag>"+rowData.teamName+"<pop:JugePermissionTag ename='view${moduleName}'></a></pop:JugePermissionTag>";
}
function nativePlaceFormatter(){
	var str = "";
	if($("#provinceValue").val() != null &&$("#provinceValue").val()!="")
		str += $("#provinceValue").val();
	if($("#cityValue").val() != null &&$("#cityValue").val()!="")
		str += $("#cityValue").val();
	if($("#districtValue").val() != null &&$("#districtValue").val()!="")
		str += $("#districtValue").val();
	$("#nativePlace").html(str);
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

function attentionextentColor(el,options,rowData){
	var displayName=attentionextentFormatter(el,options,rowData);
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
//数据来源
function sourceStateFormatter(el,options,rowData){
	if(0==rowData.sourcesState){
		return '新增';
	}else if(1==rowData.sourcesState){
		return '导入';
	}else if(2==rowData.sourcesState){
		return '同步';
	}else if(3==rowData.sourcesState){
		return '认领';
	}else if(4==rowData.sourcesState){
		return '已核实';
	}else if(5==rowData.sourcesState){
		return '落户';
	}else if(6==rowData.sourcesState){
		return 'JOB录入';
	}else if(7==rowData.sourcesState){
		return '转移';
	}else{
		return "";
	}
}
function operateFormatterClaim(el,options,rowData){
	
	if('${mode}'=="claimList"){
		if(10<=rowData.claimDetail.claimState){
			return "<pop:JugePermissionTag ename='claimView${moduleName}'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><span>查看</span></a></pop:JugePermissionTag>"
		}
		return "<pop:JugePermissionTag ename='claimUpdate${moduleName}'><a href='javascript:;' onclick='updateTempById(event,"+rowData.id+",\"FROM_LIST\");'><span>修改</span></a></pop:JugePermissionTag>"

	}else{
		if(10<=rowData.claimDetail.claimState){
			return "<pop:JugePermissionTag ename='importView${moduleName}'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><span>查看</span></a></pop:JugePermissionTag>"
		}
		if(0==rowData.claimDetail.dispose || (1==rowData.claimDetail.dispose && rowData.claimDetail.claimAvailable == 0)){
			return "<pop:JugePermissionTag ename='importDelete${moduleName}'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
		}else{
			return "<pop:JugePermissionTag ename='importView${moduleName}'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><span>查看</span></a></pop:JugePermissionTag>";
		}
	}
	
}
//数据管理认领的查看
function nameFontClaim(el,options,rowData){
	return "<pop:JugePermissionTag ename='importView${moduleName},claimView${moduleName}'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+")'></pop:JugePermissionTag>"+rowData.name+"<pop:JugePermissionTag ename='importByName${moduleName},claimByName${moduleName}'></a></pop:JugePermissionTag>";
}
//数据管理批量认领错误类型
function errorTypeFormatter(el, options, rowData){
	isFromResultList = true;
	if(rowData.claimState.errorType == 0){
		return "<a onClick='updateTempById(event,"+rowData.temp.id+",\"FROM_CLAIM\")' href='javascript:void(0)' style='text-decoration: underline;'>完善数据</a>";
	}else if(rowData.claimState.errorType == 1){
		return "<a onClick='compareRepeatClaimData(event,"+rowData.temp.id+")' href='javascript:void(0)' style='text-decoration: underline;'>修改重复数据</a>";
	}else if(rowData.claimState.errorType == 2){
		return "请先在实口中添加";
	}else if(rowData.claimState.errorType == 3){
		return "<a onClick='updateTempById(event,"+rowData.temp.id+")' href='javascript:void(0)' style='text-decoration: underline;'>修改网格</a>";
	}else if(rowData.claimState.errorType == 20){
		return rowData.claimState.errorInfo;
	}else{
		return "";
	}
}
//数据管理
function claimStateFormatter(el, options, rowData){
	if(10<=rowData.claimDetail.claimState){
		return "已认领";
	}else{
		return "未认领";
	}
}
//原始数据的状态
function dataStateFormatter(el, options, rowData){
	if(rowData.DATASTATE==1){
		return "格式错误";
	}else if(rowData.DATASTATE==2){
		return "通过校验";
	}else{
		return "未校验";
	}
}

function doubleClickClaim(selectedId){
	if(selectedId==null){
		 return;
	}
	viewDialog(selectedId);
}
function typeFormatter_ServiceRecord(el, options, rowData){
	if(0==rowData.recordType){
		return "排查类";
	}else{
		return "整改类";
	}
}
</script>