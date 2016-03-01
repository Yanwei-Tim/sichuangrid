<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
	</div>
</div>
<div id="" class="container container_24">
	<div id="tabs">
		<ul style="display: none;">
			<li><a id="fourteamsLeader_char" href="${path}/fourTeamsManage/fourTeamsOrgCharForLeader.jsp" style="display: none;">组织机构架构图</a></li>
			<li><a id="fourteamsdistrict_char" href="" style="display: none;">组织机构架构图</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	$(function(){
	
		var leaderView = '<%=com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl.FOURTEAM_LEADER_LEVLE%>';
		var districtView = '<%=com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl.FOURTEAM_DISTRICT_LEVLE%>';
		var basicView ='<%=com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl.FOURTEAM_BASIC_LEVLE%>';
		
		$("#fourTeamsChar").height($(".ui-layout-center").height()-100);
		onOrgChanged(getCurrentOrgId(),isGrid());
		function onOrgChanged(orgId,isgrid){
			$.ajax({
				url:"${path}/fourTeamsOrgManage/getOrgLevelType.action",
				data:{
					"orgId": orgId
				},
				success:function(responseData){
					if(responseData == false || responseData == 'false'){
						$.messageBox({level:'error',message:"查询出错请尝试刷新"});
					}else{
						if(responseData == leaderView){
							$( "#tabs" ).tabs({selected:0});
						}else if (responseData == districtView){
							$("#fourteamsdistrict_char").attr("href","${path}/fourTeamsOrgManage/queryFourTeamsOrgForList.action?fourTeamsOrg.organization.id="+orgId+"&orgLevel="+districtView+"");
							$( "#tabs" ).tabs({selected:1});
						}else if (responseData == basicView){
							$("#fourteamsdistrict_char").attr("href","${path}/fourTeamsOrgManage/queryFourTeamsOrgForList.action?fourTeamsOrg.organization.id="+orgId+"&orgLevel="+basicView+"");
							$( "#tabs" ).tabs({selected:1});
						}
					}
				}
			});
		}
	});
</script>
