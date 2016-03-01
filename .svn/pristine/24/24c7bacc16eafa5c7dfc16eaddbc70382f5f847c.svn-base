<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>    
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:iterator  value="partyworkMembersList" var="partyworkMembers" status="s" >
	<li data-id="<s:property value="#partyworkMembers.id" />" data-index="<s:property value="#s.index+1" />" id="leaderInfo<s:property value="#partyworkMembers.id" />">
			<div class="imgBox">
				<s:if test="#partyworkMembers.imageUrl==null && #partyworkMembers.imageUrl!=''">
				<img src="${resource_path}/resource/system/images/defaultAvatar.jpg"></img>
			   </s:if>
			   <s:else>
	   			<img  src="<s:property value="#partyworkMembers.imageUrl" />"></img>
	   		   </s:else>
			</div>
			<div class="smallText">
				<span class="name"><s:property value="#partyworkMembers.name" /></span>
				<c:if test="${partyworkMembers.gender !=null } ">
					(<s:property value="#partyworkMembers.genderName" />)
			    </c:if>
				<strong class="post"><s:property value="#partyworkMembers.duty" /></strong>
				<p class="info">
					 <s:property value="#partyworkMembers.introduction" />
				</p>
			</div>
			<div class="leaderGroup-buttons" name="leaderOperate" lid="<s:property value="#partyworkMembers.id" />">
			<pop:JugePermissionTag ename="updatePartyWorkMembers">
				<a href="javascript:;" class="base-button" name="update"><span>编辑</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deletePartyWorkMembers">
				<a href="javascript:;" class="base-button" name="delete"><span>删除</span></a>
			</pop:JugePermissionTag>	
			</div>
	</li>
</s:iterator>
<script type="text/javascript">
   function callback(){
	    var dfop = {
	    		baseInfoType:'<s:property value="#partyworkMembers.baseInfoType" />',
	    		path:'${path}'
	    }
	    TQ.partyworkMembersList(dfop);
	    
	}

	$.cacheScript({
	    url:'/resource/getScript/partyBuilding/baseInfos/partyworkMembersList.js',
	    callback: callback
	})
   
</script>
