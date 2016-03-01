<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="hiddenDanger" class="container container_24">
		<form action="${path}/baseInfo/hiddenDangerManage/updateHiddenDangerSignDetail.action" method="post" id="maintainFormForHiddenDanger">
		<input type="hidden" name="hiddenDanger.id" id="hiddenDanger-id" value="${hiddenDanger.id}" />
		
		
		<input type="hidden" name="hiddenDanger.organization.id" id="orgId" value="${(hiddenDanger.organization.id)}" />
		
			
		<div class='clearLine'>&nbsp;</div>
			
		<div class="grid_7 lable-right">
			<label class="form-lbl">发现时间：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="hiddenDanger.discoverDate" id="discoverDate" class="form-txt"
		value="<s:date name="hiddenDanger.discoverDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">发现地点：</label>
		</div>
		<div class="grid_14">
			<input type="text" id="address" name="hiddenDanger.address" class="form-txt" value="${hiddenDanger.address}" maxlength="30" readonly/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_7 lable-right">
			<label class="form-lbl">异常情况：</label>
		</div>
		<div class="grid_14">
		  <select name="hiddenDanger.exceptionType.id" class="form-txt" id="exceptionType" disabled >
		  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" defaultValue="${hiddenDanger.exceptionType.id}" />
			</select>
		</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">异常情况:</label>
		</div>
		<div class="grid_14 heightAuto">
		 <textarea rows="4" name="hiddenDanger.exceptionSituation"   class="form-txt" readonly style="background-color: #EBEBE4">${hiddenDanger.exceptionSituation}</textarea>
			
		</div>
			<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_14 heightAuto">
            <textarea rows="4" name="hiddenDanger.remark"   class="form-txt" readonly style="background-color: #EBEBE4">${hiddenDanger.remark}</textarea>
        </div>
        
        
        
		<div class='clearLine'></div>
		<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收人:</label>
		</div>
		<div class="grid_14">
			<input type="text" id="signUserName" name="hiddenDanger.signUserName" class="form-txt" value="${hiddenDanger.signUserName}" maxlength="20" readonly/>
		</div>
			<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="hiddenDanger.signDate" id="signDate" class="form-txt"
		value="<s:date name="hiddenDanger.signDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		</div>
			<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		 <div class="grid_14">
            <input  name="hiddenDanger.advice"   class="form-txt" value="${hiddenDanger.advice}">
        </div>
        <div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
				<label class="form-lbl">附件：</label>
		</div>

		<div class='grid_17 lable-left heightAuto'>
		<c:if test="${hiddenDanger.hiddenDangerAnnexFiles!=null && fn:length(hiddenDanger.hiddenDangerAnnexFiles) > 0}">
					<tr >	
					<c:forEach items="${hiddenDanger.hiddenDangerAnnexFiles}" var="hiddenDangerAttachFile">
							<td>
							  <c:choose>
									    <c:when test="${fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.jpg')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.jpge')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.png')||fn:endsWith(fn:toLowerCase(hiddenDangerAttachFile.physicsFullFileName),'.gif')}">
									    <a  data-gallery="gallery" style="color: red" class="view " target="_Blank" href="${hiddenDangerAttachFile.physicsFullFileName }" title="${hiddenDangerAttachFile.fileName}">${hiddenDangerAttachFile.fileName}</a>;<br>
									    </c:when>
									    <c:otherwise>
									    <a href="${path }/baseInfo/hiddenDangerManage/downLoadAttachFile.action?keyId=${hiddenDangerAttachFile.id}" style="color: red" >${hiddenDangerAttachFile.fileName}</a>;<br>
			                        		
									    </c:otherwise>
									</c:choose>
							</td>
		              </c:forEach> 
			</tr>
			
			</c:if>
	
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		</form>
	 </div>	
<script type="text/javascript">



$(document).ready(function(){

	
	$("#maintainFormForHiddenDanger").formValidate({
		submitHandler: function(form){
		
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (!data) {
					$.messageBox({
						message : "签收失败!",
						level : "error"
					});
					return;
				}
	
				$.messageBox({message:"签收成功!"}); 
		
				$("#hiddenDangerList").trigger("reloadGrid");
				$("#hiddenDangerDialog").dialog("close");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"hiddenDanger.advice":{
				maxlength:200
			}
		},
		messages:{	
			"hiddenDanger.advice":{
			    maxlength:$.format("签收意见最多输入{0}个字")
					}
		}
	});
	
});

</script>