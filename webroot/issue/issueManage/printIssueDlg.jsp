<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="issueAccordingContext" >
<div>
<table id="issueAccordingPrint" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top">
    	<table width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" colspan="6" align="left">单号：<s:property value="issue.serialNumber"/></td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          	  <c:if test="${source=='completed'}">
                  	网格化服务管理信息系统任务单
                  </c:if>  
                  <c:if test="${source=='need'}">
                  	网格化服务管理信息系统任务移交单
                  </c:if>  
	          	  <br><br>
	          	</td>
        	</tr>
        	<tr>
        		<td width="100%" colspan="6" ><b>事件名称：</b>${issue.subject }</td>
        	</tr>
        	<tr>
	          	<td width="100%" colspan="6" align="center"><hr/></td>
        	</tr>
        	<c:if test="${source=='need'}">
        	<tr>
	          	<td width="12%" class="tdhr">签收单位：</td>
	          	<td width="18%"></td>
	          	<td width="12%" class="tdhr">签收人：</td>
	          	<td width="20%"></td>
	          	<td width="16%" class="tdhr">联系电话：</td>
	          	<td width="15%"></td>
        	</tr>
        	<tr>
        		<td colspan="4"></td>
        		<td class="tdhr">年&nbsp;&nbsp;月&nbsp;&nbsp;日</td>
        	</tr>
        	<tr>
	          	<td width="100%" colspan="6" align="center"><hr style="border:1px dashed black; height:1px"/></td>
        	</tr>
        	<tr>
	          	<td colspan="4" class="tdhl" >单号：${issue.serialNumber }</td>
	          	<td ></td>
	          	<td ></td>
       		</tr>
       		<tr>
	          	<td colspan="6" style="text-align: center;font-Size:19px; font-family:'黑体';height:20px;width:15%;	font-weight:750;">网格化服务管理信息系统任务移交单</td>
        	</tr>
        	</c:if> 
        	<c:if test="${source=='completed'}">
        		<tr>
	          	<td width="12%" class="tdhr"></td>
	          	<td width="18%"></td>
	          	<td width="12%" class="tdhr"></td>
	          	<td width="20%"></td>
	          	<td width="16%" class="tdhr"></td>
	          	<td width="15%"></td>
        	</tr>
            </c:if>  
       		<tr>
	          	<td width="12%" style="text-align: right;font-Size:16px	font-family:'黑体';height:20px;width:15%;font-weight:750;">派单单位：</td>
	          	<td width="5%" colspan="4">${organization.orgName }</td>
	          	<td width="60%" style="text-align: left;font-Size:16px font-family:'黑体';	height:20px;width:15%;font-weight:750;"  >年&nbsp;&nbsp;月&nbsp;&nbsp;日印</td>
       		</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	<tr class="tr1">
        		<td height="30" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%">主  题</td>
        		<td colspan="5"  style="text-align: left;font-Size:10px; height:20px;width:15%;">${issue.subject }</td>
        	</tr>
        	<tr class="tr1">
        		<td height="30" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%">承办单位</td>
        		<td colspan="5"  style="text-align: left;font-Size:10px; height:20px;width:15%;">${issue.currentStep.target.orgName }</td>
	            <!--<td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">承办单位</td>
	            <td width="58%" colspan="3" style="text-align: left;font-Size:10px; height:20px;width:15%;">${issue.currentStep.target.orgName }</td>
	             <td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">办理时限</td>
	            <td width="18%" style="text-align: left;font-Size:10px; height:20px;width:15%;">&nbsp;</td> -->
        	</tr>
        	<tr class="tr1">
        		<td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;" >信息来源</td>
	            <td width="20%" style="text-align: left;font-Size:10px; height:20px;width:15%;">${user.name }</td>
	            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">联系电话</td>
	            <td width="20%" style="text-align: left;font-Size:10px; height:20px;width:15%;">${user.mobile}</td>
	            <td width="10%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">来源方式</td>
	            <td width="20%" style="text-align: left;font-Size:10px; height:20px;width:15%;">
						<pop:PropertyDictViewTag defaultValue="${issue.sourceKind.id }" name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"></pop:PropertyDictViewTag>
				</td>
        	</tr>
        	<tr class="tr1">
        		<td height="30" align="left" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%">当事人</td>
        		<td colspan="5" style="text-align: left;font-Size:10px; height:20px;width:15%;">
        			<s:property value="issue.mainCharacters"/>
        		</td>
        	</tr>
        	<tr class="tr1">
        		<td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">事件规模</td>
	            <td width="20%" style="text-align: left;font-Size:10px; height:20px;width:15%;"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" />&nbsp;</td>
	            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">涉及人数</td>
	            <td width="20%" style="text-align: center;font-Size:10px; height:20px;width:15%;"><s:property value="issue.relatePeopleCount"/></td>
	            <td width="10%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">发生日期</td>
	            <td width="20%" style="text-align: left;font-Size:10px; height:20px;width:15%;"><s:date name="issue.occurDate" format="yyyy年MM月dd日" /></td>
        	</tr>
        	<tr class="tr1">
        		<td height="30" align="left" width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;">发生网格</td>
        		<td colspan="5" style="text-align: left;font-Size:10px; height:20px;width:15%;"><s:property value="issue.occurOrg.orgName"/></td>
        	</tr>
        	<tr>
        	<td width="12%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:15%;font-weight:750;" >事件类型</td>
	            <%-- <td width="88%" colspan="5" style="text-align:left;font-Size:10px; height:20px;width:15%;">
	            	<s:iterator value="selContradiction" var="type" >
                        <s:property value="issueTypeName"/>,
                    </s:iterator>
                    <s:iterator value="selSpecialisation" var="type" >
                        <s:property value="issueTypeName"/>,
                    </s:iterator>
                    <s:iterator value="selPeopleliveService" var="type" >
                        <s:property value="issueTypeName"/>,
                    </s:iterator>
	            </td> --%>
					<td width="88%" colspan="5" id="issueTypeDescription" style="text-align:left;font-Size:10px; height:20px;width:15%;">${issue.issueType.issueTypeDomain.domainName}:${issue.issueType.issueTypeName}&nbsp;</td>
        	</tr>
        	<tr class="tr1" height="120">
        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%"><p></p><p>内</p><p>容</p><p></p></td>
        		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;width:15%;word-break:break-all;word-wrap:break-word">
	            	<s:property value="issue.issueContent" />
	            </td>
        	</tr>
        	<tr class="tr1" >
        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%"><p>领</p><p>导</p><p>意</p><p>见</p></td>
        		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;width:15%;">
        		<s:iterator status="index" value="issueLogs">
        				<c:if test="${dealType =='51' }">
	        				<div class="grid_24 heightAuto" style="width: 100%">
		                         <c:if test="${dealOrg.orgName =='中国' || dealUserName=='admin' || dealUserName =='超级管理员'  }">
			                        <b>系统消息</b>&nbsp;
		                         </c:if>
		                          <c:if test="${dealOrg.orgName !='中国' && dealUserName!='admin' && dealUserName !='超级管理员'  }">
		                          ${dealOrg.orgName }&nbsp;
			                        <b>${dealUserName }</b>&nbsp;
		                         </c:if>
		                        	联系手机：<s:property value="mobile"/>&nbsp;于&nbsp;
		                        <s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
		                        <s:property value="dealDescription"/><c:if test="${content != null && not empty content}">&nbsp;&nbsp;办理意见:</c:if><br/>
		                       	<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
			                        <div class="grid_24 grid-message heightAuto" align="left">
			                            <span>${content }</span>
			                        </div>
		                    	</c:if>
	        				</div>
        				</c:if>
                    </s:iterator>
        		</td>
        	</tr>
        	<tr class="tr1">
        		<td height="80" style="text-align: center;font-Size:16pxfont-family:'黑体';height:20px;width:15%;font-weight:750;" width="8%"><p>处</p><p>理</p><p>记</p><p>录</p></td>
        		<td colspan="5" valign="top" align="left" style="text-align:left;font-Size:10px; height:20px;width:15%;">
        			<s:iterator status="index" value="issueLogs">
        				<c:if test="${dealType !='51' }">
	        				<div class="grid_24 heightAuto" style="width: 100%">
	        					
		                         <c:if test="${dealOrg.orgName =='中国' || dealUserName=='admin' || dealUserName =='超级管理员'  }">
			                        <b>【系统消息】</b>&nbsp;
		                         </c:if>
		                          <c:if test="${dealOrg.orgName !='中国' && dealUserName!='admin' && dealUserName !='超级管理员'  }">
		                                                                          【${dealOrg.orgName }】&nbsp;
			                        <b>${dealUserName }</b>&nbsp;
		                        	联系手机：<s:property value="mobile"/>&nbsp;于&nbsp;
		                        <s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
		                         </c:if>
		                        <s:property value="dealDescription"/><c:if test="${content != null && not empty content}">&nbsp;&nbsp;办理意见:</c:if><br/>
		                       	<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
			                        <div class="grid_24 grid-message heightAuto" align="left">
			                            <span>${content }</span>
			                        </div>
		                    	</c:if>
	        				</div>
        				</c:if>
                    </s:iterator>
        		</td>
        	</tr>
    </table>
    <p><span style="font-Size:12px">注：承办单位处理完毕后，留复印件，将原件交派单单位</span></p>
</table>
</div>
</div>

<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//renderIssueType();
});
function renderIssueType(){
	var typeDescs=new Array();
	var descIndexs=new Array();
	var typeDesc='';
	var actualIndex;
    <s:iterator value="issue.issueTypes" var="type" >
	    actualIndex=getTypeDescById(descIndexs,<s:property value="#type.issueTypeDomain.id"/>);
    	if (actualIndex==typeDescs.length){
    		descIndexs[actualIndex]=<s:property value="#type.issueTypeDomain.id"/>;
    		typeDescs[actualIndex]='<s:property value="#type.issueTypeDomain.domainName" escape="false"/>'+':';
        }
    	typeDescs[actualIndex]=typeDescs[actualIndex]+"<s:property value="#type.issueTypeName+','" escape="false"/>";
	</s:iterator>

	for (var index=0;index<typeDescs.length;index++){
		if (index>0){
			typeDesc=typeDesc+'<br>';
		}
		typeDesc=typeDesc+typeDescs[index];
	}
	$("#issueTypeDescription").html(typeDesc);
}
</script>
