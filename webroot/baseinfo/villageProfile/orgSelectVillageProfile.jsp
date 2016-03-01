<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().internalId!=@com.tianque.domain.property.OrganizationLevel@GRID || @com.tianque.core.util.ThreadVariable@getUser().admin">
	<a href="javascript:;" class="globalOrgBtnMod" id="globalOrgBtnMod"><span id="globalOrgBtn"></span></a>
	<script>
		$(function(){
			$("#globalOrgBtnMod").click(function(){
				$("#globalOrgBox").createDialog({
					url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id='+$.getCurrentOrgId(),
					width:550,
					height:'auto',
					draggable:false,
					title:'辖区选择',
					buttons: {
						"确定" : function(event){
							var selectInput=$("#orgSelectInput");
							var orgLevelInternalId=selectInput.attr("orgLevelInternalId");
							var text=selectInput.attr("text");
							$("#currentOrgId").attr({
								"orgLevelInternalId":orgLevelInternalId,
								text:text,
								value:selectInput.val()
							});
							$("#globalOrgBtn").html(text);
							var curMenu=$("#accordion a.cur");
							var baseUrl="";
							var leaderUrl="";
							if(isCountryDownOrganization()){
								if(isGrid()){
									baseUrl='${path}/hotModuel/baseinfo/villageProfile/gridProfileComplete.jsp';
								}else{
									baseUrl='${path}/hotModuel/baseinfo/villageProfile/villageProfileComplete.jsp';
								}
							}else{
								baseUrl='${path}/hotModuel/baseinfo/villageProfile/introduction.jsp';
							}


							
							baseLoad(curMenu,baseUrl,leaderUrl);
						},
						"取消" : function(){
							$(this).dialog("close");
						}
					}
				});
			})
		})
	</script>
</s:if>