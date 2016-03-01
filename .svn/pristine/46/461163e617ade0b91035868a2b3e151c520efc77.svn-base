<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.issueCttLeft{width:50%;float:left;}
.issueCttRight{width:49%;float:right;background:none;border:0;}
.processRecord{margin-top:5px;font:bold 12px/20px "";}
h3.processRecord{color:#000;font-weight:bold!important;}
.issueContentInfo{padding:0;border:0;}
.recordContent,.tableBox,.issuePjCtt{background:#fff;border:1px solid #ccc;border-top:0;}
.recordContent{position:relative;}
.issuePjCtt{padding:10px;}
.tableBox{padding:10px;}
.star{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;height:23px;background:url(/resource/system/images/incident/star.png) no-repeat;}
.star.star1{width:20px;}
.star.star2{width:40px;}
.star.star3{width:60px;}
.star.star4{width:80px;}
.star.star5{width:100px;}
.issueC{font:12px/20px "";}
.issueRight .smallText{padding-left:10px;}
.issueRight .recordList .recordData{height:auto;overflow:hidden;}
.issueRight .recordData .place{width: 280px;padding: 0;}
.issueRight .recordData .handle{position:static;float:right;width: 92px;line-height: 27px;padding-right: 0;}
.issueTitle .pmState, .state, .issueRight .recordData .time{background:none;padding-left:0;}
.viewImage {
background: url(/resource/system/images/issue/images_dw_search.gif) no-repeat;
display: inline-block;
width: 72px;
height: 20px;
vertical-align: middle;
}
.ui-state-disabled, .ui-widget-content .ui-state-disabled, .ui-widget-header .ui-state-disabled{
	opacity: 1;
	filter: Alpha(Opacity=100);
	background-image: none;
}
</style>
<div class="issueCttLeft">
	<div class="issueContentInfo">
		<div class="tableBox">
			<table class="newTableList">
				<tbody>
					<tr>
						<td class="title">服务单号</td><td class="contable">${issueJoint.serialNumber}</td>
					</tr>
					<tr>
						<td class="title">事件类型</td><td class="contable">
							<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE" 
								defaultValue="${issueJoint.issueJointType.id}" /></td>
						</td>
					</tr>
					<tr>
						<td class="title">事件子类</td><td class="contable">
							<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_JOINT_TYPE_SUB" 
								defaultValue="${issueJoint.issueJointTypeSub.id}" /></td>
						</td>
					</tr>
					<tr>
						<td class="title">事件名称</td><td class="contable">${issueJoint.subject}</td>
					</tr>
					<tr>
						<td class="title">发生网格</td><td class="contable">${issueJoint.occurOrg.orgName}</td>
					</tr>
			        <tr class="resolveTheContradictions securityPrecautions emergencies">
						<td class="title">发生时间</td><td class="contable"><s:date name="issueJoint.occurDate" format="yyyy-MM-dd "/>
						<c:if test="${not empty issueJoint.hours &&issueJoint.hours!=null&&not empty  issueJoint.minute &&issueJoint.minute!=null}">${issueJoint.hours }:${issueJoint.minute }</c:if>
						</td>
					</tr>
					<tr class="resolveTheContradictions securityPrecautions emergencies otherManage">
			       		<td class="title">发生地点</td><td class="contable">${issueJoint.occurLocation }</td>
			       	</tr>
			       	<tr class="resolveTheContradictions emergencies">
						<td class="title">事件规模</td><td class="contable">
			       	 	<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issueJoint.issueKind.id}" /></td>
			       	</tr>
					<tr class="resolveTheContradictions emergencies">
			        	<td class="title">涉及人数</td><td class="contable">${issueJoint.relatePeopleCount}</td>
			       	</tr>
					<tr class="contradiction resolveTheContradictions emergencies otherManage">
			        	<td class="title">主要当事人</td><td class="contable">${issueJoint.maincharacters}</td>
			       	</tr>
			       	<tr>
			       		<c:if test="${issueJointAttachfiles!=null && fn:length(issueJointAttachfiles) > 0}">
						<p>
							<td class="title"><div class="grid-accessory"><span class="filetype-clip"></span>附件：</div></td>
							<td class="contable">	
							<div id="gallery">
								<c:forEach items="${issueJointAttachfiles}" var="issueJointAttachfile">
									<c:choose>
									    <c:when test="${fn:endsWith(fn:toLowerCase(issueJointAttachfile.physicsFullFileName),'.jpg')||fn:endsWith(fn:toLowerCase(issueJointAttachfile.physicsFullFileName),'.jpge')||fn:endsWith(fn:toLowerCase(issueJointAttachfile.physicsFullFileName),'.png')||fn:endsWith(fn:toLowerCase(issueJointAttachfile.physicsFullFileName),'.gif')}">
									     	<a data-gallery="gallery" style="color: red" class="view" href="${issueJointAttachfile.physicsFullFileName }" title="${issueJointAttachfile.fileName}">${issueJointAttachfile.fileName}</a>;
			                        		<div class="clear"></div>
									    </c:when>
									    <c:otherwise>
									    	<a href="${path }/issueJointManage/downLoadAttachFile.action?issueJointAttachfileId=${issueJointAttachfile.id}" style="color: red">${issueJointAttachfile.fileName}</a>;
			                        		<div class="clear"></div>
									    </c:otherwise>
									</c:choose>
		                        </c:forEach>
		                        <a href="javascript:;" class="viewImage" id="viewImages"></a>
							</div>
							</td>
						</p>
						</c:if>
			       	</tr>
			       	<tr class="contradiction resolveTheContradictions securityPrecautions emergencies">
			       		<td class="title">事件简述</td><td class="contable"><div class="issueC"><p>${issueJoint.issueContent }</p></div></td>
			       	</tr>
				</tbody>
			</table>
	</div>
	</div>
</div>


<div class="issueRight issueCttRight">
	<div class="dealRecord recordList clearfix">
		<h3 class="processRecord">处理记录
			<div class="processBtnList">
				<a href="javascript:;" class="cur" id="textView"><span class="text"></span>文字视图</a>
			</div>
		</h3>
			<div class="recordContent clearfix">
				<ul id="issueTable">
					<s:subset source="issueJointLogs" start="0">
						<s:iterator status="index">
							<li id="2012-12-11">
								<dl class="recordData clearfix">
									<dd class="time"><s:date name="dealTime" format="yy年MM月dd" /></dd>
									<dd class="place"><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;
									<c:choose>
									    <c:when test="${dealUserName == 'admin' || dealUserName =='超级管理员'}">
									     	【系统消息】
									    </c:when>
									    <c:otherwise>
									    	【<s:property value="dealOrg.orgName"/>】
									    </c:otherwise>
									</c:choose>
									<s:property value="dealDescription"/></dd>
								</dl>
								<c:if test="${(content != null && not empty content)}">
			                        <div class="smallText">
											<span>办理意见:</span><span>${content }</span>
									</div>
									<c:if test="${issueJointLogAttachfiles!=null && fn:length(issueJointLogAttachfiles) > 0}">
				                    	<div class="smallText" id="gallery<s:property value="id"/>">
											<span class="filetype-clip"></span>附件：
											<c:forEach items="${issueJointLogAttachfiles}" var="issueJointLogAttachfile">
											<c:choose>
											    <c:when test="${fn:endsWith(fn:toLowerCase(issueJointLogAttachfile.physicsFullFileName),'.jpg')||fn:endsWith(fn:toLowerCase(issueJointLogAttachfile.physicsFullFileName),'.jpge')||fn:endsWith(fn:toLowerCase(issueJointLogAttachfile.physicsFullFileName),'.png')||fn:endsWith(fn:toLowerCase(issueJointLogAttachfile.physicsFullFileName),'.bmp') ||fn:endsWith(fn:toLowerCase(issueJointLogAttachfile.physicsFullFileName),'.gif')}">
											     	<a data-gallery="gallery"  class="view" href="${issueJointLogAttachfile.physicsFullFileName }" title="${issueJointLogAttachfile.fileName}">${issueJointLogAttachfile.fileName}</a>;
					                        		<div class="clear"></div>
											    </c:when>
											    <c:otherwise>
											    	<a href="${path }/issueJointManage/downLoadAttachFile.action?issueJointAttachfileId=${issueJointLogAttachfile.id}" >${issueJointLogAttachfile.fileName}</a>;
											    </c:otherwise>
											</c:choose>
					                        </c:forEach>
					                        <a href="javascript:;" class="viewImage" id="viewImages<s:property value="id"/>"></a>
					                        <script type="text/javascript">
					                        $('#gallery<s:property value="id"/>').imagegallery({
					                	        show: "random",
					                	        zIndex:99999<s:property value="id"/>,
					                	        close:function(){
					                				$('#gallery<s:property value="id"/>').imagegallery('disable');
					                	        }
					                	    });
					                		$('#gallery<s:property value="id"/>').imagegallery('disable');
					                	    $("#viewImages<s:property value="id"/>").click(function(){
					                	    	$('#gallery<s:property value="id"/>').imagegallery('enable');
					                	    	if($('#gallery<s:property value="id"/>').find(".view").eq(0).html() == null) {
					                	    		$('#gallery<s:property value="id"/>').find("a:first")[0].click();
					                	    	} else {
					                	    		$('#gallery<s:property value="id"/>').find(".view").eq(0).click();
					                	    	}
					                	    });
					                        </script>
			                        	</div>
		                   			</c:if>
	                    		</c:if>
							</li>
						</s:iterator>
					</s:subset>
				</ul>
			</div>
	
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$(".processBtnList a").click(function(){
			$(this).addClass("cur").siblings().removeClass("cur");
		});
		$("#chartView").click(function(){
			$("#issueTable").hide();
		});
		$("#textView").click(function(){
			$("#issueTable").show();
		});
		$('div .show').each(function(){
			if($(this).attr("id")){
			$(this).raty({
				readOnly:	true,
				start:		$(this).attr("id")
		   });
			}
		});
		
		var issueType = '${issue.issueTypeDomainName}';
		
		$('#gallery').imagegallery({
	        show: "random",
	        zIndex:99999,
	        close:function(){
				$('#gallery').imagegallery('disable');
	        }
	    });
		$('#gallery').imagegallery('disable');
	    $("#viewImages").click(function(){
	    	$('#gallery').imagegallery('enable');
	    	if($('#gallery').find(".view").eq(0).html() == null) {
	    		$('#gallery').find("a:first")[0].click();
	    	} else {
	    		$('#gallery').find(".view").eq(0).click();
	    	}
	    });
	    
	});

</script>