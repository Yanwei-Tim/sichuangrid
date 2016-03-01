<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<script type="text/javascript" >
$(function(){
	$("#peaceVillageTitle").html("${searchPeaceVillageVo.year }年${searchPeaceVillageVo.month }月平安和谐网格考核情况");
	$(".countlist tr:odd").addClass("even");
	$(".countlist tr .evaluate").click(function(){
		if($(".countlist .edit").hasClass("on")){//如果是编辑则加上红旗
			if($(this).attr("class").indexOf("select")!=-1){
				$(this).children("input").val("1");
			}else{
				$(this).children("input").val("0");
			}
			$(this).toggleClass("select");
		}
	})
	if("${searchPeaceVillageVo.organization.id }"==USER_ORG_ID){
		$(".countlist .edit").click(function(){
			$(this).toggleClass("on");//开/关
			if($(this).hasClass("on")){//开关替换
				$(this).text("保存");	
			}else{
				$("#maintainForm").submit();
				$(this).text("编辑");	
			}
		});
	}else{
		$(".countlist .edit").hide();
	}
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					if(data!="success"){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
		        	$.messageBox({message:"保存成功!"});
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
})
</script>

<form id="maintainForm" method="post" action="${path}/peaceVillageManage/savePeaceVillage.action">
	<table width="100%" cellspacing="0" class="countlist">
	  <tr>
		  <th colspan="11" class="title">
		  	<a href="javascript:;" class="edit">编辑</a>
			<span id ="peaceVillageTitle"></span>
			  
		  </th>
	  </tr>
	  <tr>
	    <th>地区</th>
	    <th>无刑事案件</th>
	    <th>无毒品</th>
	    <th>无安全事故</th>
	    <th>无群体性事件</th>
	    <th>无脏乱差</th>
	    <th>有自治组织</th>
	    <th>有三防措施</th>
	    <th>有法律制度宣传</th>
	    <th>有社会力量参与</th>
	    <th>有群众文化活动</th>
	    
	  </tr>
	  <c:forEach var="peaceVillage" items="${peaceVillageList}" varStatus="pvList">
		  <tr>
		    <td><c:out value='${peaceVillage.organization.orgName}'/>
		    	<input type="hidden" id="orgId${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].organization.id" 
	    				value="<c:out value='${peaceVillage.organization.id}'/>"  />
	    		<input type="hidden" id="orgInternalCode${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].organization.orgInternalCode" 
	    				value="<c:out value='${peaceVillage.organization.orgInternalCode}'/>"  />
	    		<input type="hidden" id="peaceVillageYear${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].year" 
	    				value="<c:out value='${searchPeaceVillageVo.year}'/>"  />
	    		<input type="hidden" id="peaceVillageMonth${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].month" 
	    				value="<c:out value='${searchPeaceVillageVo.month}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.noCriminal eq 0}'>select</c:if>">
		    	<input type="hidden" id="noCriminal${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].noCriminal" 
	    				value="<c:out value='${peaceVillage.noCriminal}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.noDrugs==0}'>select</c:if>">
		    	<input type="hidden" id="noDrugs${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].noDrugs" 
	    				value="<c:out value='${peaceVillage.noDrugs}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.noAccident==0}'>select</c:if>">
		    	<input type="hidden" id="noAccident${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].noAccident" 
	    				value="<c:out value='${peaceVillage.noAccident}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.noGroupEvents==0}'>select</c:if>">
		    	<input type="hidden" id="noGroupEvents${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].noGroupEvents" 
	    				value="<c:out value='${peaceVillage.noGroupEvents}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.noDirty==0}'>select</c:if>">
		    	<input type="hidden" id="noDirty${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].noDirty" 
	    				value="<c:out value='${peaceVillage.noDirty}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.hasOrganization==0}'>select</c:if>">
		    	<input type="hidden" id="hasOrganization${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].hasOrganization" 
	    				value="<c:out value='${peaceVillage.hasOrganization}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.hasMeasures==0}'>select</c:if>">
		    	<input type="hidden" id="hasMeasures${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].hasMeasures" 
	    				value="<c:out value='${peaceVillage.hasMeasures}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.hasLegalAdvocacy==0}'>select</c:if>">
		    	<input type="hidden" id="hasLegalAdvocacy${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].hasLegalAdvocacy" 
	    				value="<c:out value='${peaceVillage.hasLegalAdvocacy}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.hasSocialForces==0}'>select</c:if>">
		    	<input type="hidden" id="hasSocialForces${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].hasSocialForces" 
	    				value="<c:out value='${peaceVillage.hasSocialForces}'/>"  />
		    </td>
		    <td class="evaluate <c:if test='${peaceVillage.hasCulturalActivities==0}'>select</c:if>">
		    	<input type="hidden" id="hasCulturalActivities${pvList.index}" 
	    				name="peaceVillageList[${pvList.index}].hasCulturalActivities" 
	    				value="<c:out value='${peaceVillage.hasCulturalActivities}'/>"  />
		    </td>
		  </tr>
	  </c:forEach>
	</table>
</form>

