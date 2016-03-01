<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().internalId!=@com.tianque.domain.property.OrganizationLevel@GRID || @com.tianque.core.util.ThreadVariable@getUser().admin">
	<a href="javascript:;" class="globalOrgBtnMod" id="globalOrgBtnMod"><span id="globalOrgBtn"></span></a>
	<script>
		$(function(){
			var plateName='<s:property value="#parameters.plateName"/>';
			if(plateName=='party'){
				var party_selectInput=$("#orgSelectInput");
				var party_orgLevelInternalId=party_selectInput.attr("orgLevelInternalId");
				var party_orgId=$.getCurrentOrgId();
				if(party_orgLevelInternalId<10){
					isGridChangeOrg(party_orgLevelInternalId,party_orgId);
				}
			}
			$("#globalOrgBtnMod").click(function(){
				$("#globalOrgBox").createDialog({
					url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id='+$.getCurrentOrgId()+"&plateName="+plateName,
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
							if(isDistrictDownOrganization()){
								baseUrl='${path}/partyBuilding/baseInfos/basicCaseList.jsp';
							}else{
								baseUrl='${path}/partyBuilding/baseInfos/districtBasiccaseList.jsp';
							}
							baseLoad(curMenu,baseUrl,leaderUrl);
						},
						"取消" : function(){
							$(this).dialog("close");
						}
					}
				});
			})
		});

		function isGridChangeOrg(party_orgLevelInternalId,party_orgId){
			if(party_orgLevelInternalId>0 || party_orgId==null || party_orgId==''){
				return;
			}
			$.ajax({
				 async:false,
				url:'${path}/sysadmin/orgManage/isGridChangeOrg.action?id='+party_orgId+'&orgLevelInternalId='+party_orgLevelInternalId,
				success:function(data){
					$("#currentOrgId").attr({
						"orgLevelInternalId":data.orgLevelInternalId,
						text:data.orgName,
						value:data.id
					});
					$("#globalOrgBtnMod.globalOrgBtnMod").html(data.orgName);
				}
			});
		}
	</script>
</s:if>