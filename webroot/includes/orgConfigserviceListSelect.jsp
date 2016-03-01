<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${resource_path}/resource/system/js/orgConfigserviceListSelect.js"></script>

<style type="text/css">
    #refreshOrgTreeDiv{padding:0 5px;text-align:right;}
    #refreshOrgTreeDiv .ui-widget{overflow:hidden;}
    #refreshOrgTreeDiv #currentConfigTaskOrg{float:left;line-height: 30px;color: #666;}
</style>
<%-- <input type="hidden" id="selectType" name="selectType" value="${selectType }"/> --%>
<div id="refreshOrgTreeDiv" style="text-align:right;">
	<div class="ui-widget" id='currentConfigTaskOrgDiv'>
		<span id="currentConfigTaskOrg">所属区域：${organization.orgName }</span>
<!-- 		<input class="basic-input show_search_btn" id="org-tree-autocomplete" type="text" value="请输入拼音首字母" /> -->
		<input type="hidden" id="orgConfigTaskSelectInput" value="${organization.id }" text="${organization.orgName }" orgLevelInternalId="${organization.orgLevel.internalId}" />
	</div>
</div>
<div id="orgConfigTaskSelect">
	<div class="orgSelectBox" id="orgConfigTaskSelectbox">
		<div id="childsDiv">
			<div class="orgBoxResult" id="orgBoxResult">
			<a href="javascript:;" orgId="${organization.id }" orgLevelInternalId="${organization.orgLevel.internalId }" leaf='<s:if test="#org.subCount>0">true</s:if><s:else>false</s:else>' <s:if test="#st.index==(childs.size()-1)">class="cur"</s:if>>
				<strong>${organization.orgName }</strong>
				<span class="arrows"></span>
			</a>
			</div>
		</div>
		<div class="orgBoxSearchBox">
			<div class="organizationBox" >
				<h3><s:property value="orgType.displayName"/></h3>
				<div class="organizationlist">
				 <c:forEach items="${organizations }" var="org">
					<%-- <s:iterator var="org" value="organizations"> --%>
						<a href="javascript:;" orgid="${org.id }" orglevelinternalid="${org.orgLevel.internalId }" leaf='<s:if test="#org.subCount>0">true</s:if><s:else>false</s:else>'>
							<strong>${org.orgName }</strong><span class="arrows"></span>
						</a>
					<%-- </s:iterator> --%>
					</c:forEach>
				</div>
			</div>
			<div class="orgLoadBox" style="display: none;"><img src="${resource_path}/resource/images/loading.gif" alt="加载中..."></div>
		</div>
		</div>
</div>
<script>
$(function(){
	if(childOrgsObj!=null){
		$("#childsDiv").empty();
		$("#childsDiv").html(childOrgsObj);
		
	}
	if(currentConfigTaskOrgDiv!=null){
		$("#currentConfigTaskOrgDiv").html(currentConfigTaskOrgDiv);
	}
	$.showTxtValue();
	function stringFormatter(str){
		if(str==undefined){
			return "";
		}
		return str;
	}
	$("#orgConfigTaskSelect").orgConfigTaskSelect({//orgConfigTaskSelect
		first:'<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>'
		,filter:function(orgId,orglevelinternalid){
		}	
		
	});
// 	$("#org-tree-autocomplete").autocomplete({
// 		source: function(request, response) {
// 			$.ajax({
// 				url: "/sysadmin/orgManage/ajaxFindOrganizationsByOrgNameAndOrgType.action",
// 				data:{"organization.orgName":function(){return request.term;}, 'simpleCode': 213},
// 				success: function(data) {
// 					response($.map(data, function(item) {
// 						return {
// 							label: item.orgName+","+stringFormatter(item.contactWay),
// 							value: item.orgName,
// 							id: item.id
// 						}
// 					}))
// 				},
// 				error : function(){
// 					$.messageBox({
// 						message:"搜索失败，请重新登入！",
// 						level:"error"
// 					});
// 				}
// 			})
// 		},
// 		select: function(event, ui) {
// 			$.ajax({
// 				url:"/sysadmin/orgManage/findChildOrgs.action?id="+ui.item.id,
// 				success:function(data){
//                     $("#orgConfigTaskSelect").find(".orgBoxResult").empty()
// 					$("#orgConfigTaskSelect").orgSelect("setOrgs",data);
// 				}
// 			});
// 		}
// 	});
})
</script>
