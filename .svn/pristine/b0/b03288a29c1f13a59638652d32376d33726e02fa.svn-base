<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- /webroot/includes/orgSelect.jsp -->

<style type="text/css">
    #refreshOrgTreeDiv{padding:0 5px;text-align:right;}
    #refreshOrgTreeDiv .ui-widget{overflow:hidden;}
    #refreshOrgTreeDiv #currentCoordinate{float:left;line-height: 30px;color: #666;}
</style>
<input type="hidden" id="selectType" name="selectType" value="${selectType }"/>
<div id="refreshOrgTreeDiv" style="text-align:right;">
	<div class="ui-widget" >
		<span id="currentCoordinate">所属区域：${organization.orgName }</span>
		<input class="basic-input show_search_btn" id="org-tree-autocomplete" type="text" value="请输入拼音首字母" />
		<input type="hidden" id="orgSelectInput" value="${organization.id }" text="${organization.orgName }" orgLevelInternalId="${organization.orgLevel.internalId}" />
	</div>
</div>

<div id="orgSelect">
	<div class="orgSelectBox" id="orgSelectbox">
		<div class="orgBoxResult">
		 <c:forEach items="${childs }" var="org">
			<%-- <s:iterator var="org" value="childs" status="st"> --%>
				<a href="javascript:;" orgId="${org.id }" orgLevelInternalId="${org.orgLevel.internalId }" leaf='<s:if test="#org.subCount>0">true</s:if><s:else>false</s:else>' <s:if test="#st.index==(childs.size()-1)">class="cur"</s:if>>
					<strong>${org.orgName }</strong>
					<span class="arrows"></span>
				</a>
			<%-- </s:iterator> --%>
			</c:forEach>
		</div>
		<div class="orgBoxSearchBox">
			<div class="organizationBox" >
				<h3><s:property value="organizations.get(0).orgLevel.displayName"/></h3>
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
	var plateName="<s:property value='plateName'/>";
	$.showTxtValue();
	function stringFormatter(str){
		if(str==undefined){
			return "";
		}
		return str;
	}
	$("#orgSelect").orgSelect({
		first:'<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>'
		,filter:function(orgId,orglevelinternalid){
			if(plateName=="party"){
				if(orglevelinternalid<10){
					return false;
				}
			}
		}	
		
	});
	$("#org-tree-autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "/sysadmin/orgManage/ajaxFindOrganizationsByOrgNameAndOrgType.action",
				data:{"organization.orgName":function(){return request.term;}, 'simpleCode': 213},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			$.ajax({
				url:"/sysadmin/orgManage/findChildOrgs.action?id="+ui.item.id,
				success:function(data){
                    $("#orgSelect").find(".orgBoxResult").empty()
					$("#orgSelect").orgSelect("setOrgs",data);
				}
			});
		}
	});
})
</script>
