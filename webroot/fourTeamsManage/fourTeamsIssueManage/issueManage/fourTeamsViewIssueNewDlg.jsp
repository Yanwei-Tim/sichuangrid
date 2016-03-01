<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
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
.issueRight .recordData .place{width: 90px;padding: 0;}
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
						<td class="title">服务单号</td><td class="contable">${issue.serialNumber}</td>
					</tr>
					<tr>
						<td class="title">事件类型</td><td class="contable">${issue.issueTypeDomainName}</td>
					</tr>
					<tr>
						<td class="title">事件子类</td><td class="contable">${issue.issueTypeName}</td>
					</tr>
					<tr>
						<td class="title">事件名称</td><td class="contable">${issue.subject}</td>
					</tr>
					<tr>
						<td class="title">发生网格</td><td class="contable">${issue.occurOrg.orgName}</td>
					</tr>
			        <tr class="resolveTheContradictions securityPrecautions emergencies">
						<td class="title">发生时间</td><td class="contable"><s:date name="issue.occurDate" format="yyyy-MM-dd "/><s:if test="issue.hours!=''&&issue.hours!=null&&issue.minute!=''&&issue.minute!=null">${issue.hours }:${issue.minute }</s:if></td>
					</tr>
					<tr class="contradiction">
						<td class="title">受理时间</td><td class="contable"><s:date name="issue.occurDate" format="yyyy-MM-dd "/><s:if test="issue.hours!=''&&issue.hours!=null&&issue.minute!=''&&issue.minute!=null">${issue.hours }:${issue.minute }</s:if></td>
					</tr>
					<tr class="specialPopulations">
						<td class="title">服务时间</td><td class="contable"><s:date name="issue.occurDate" format="yyyy-MM-dd "/><s:if test="issue.hours!=''&&issue.hours!=null&&issue.minute!=''&&issue.minute!=null">${issue.hours }:${issue.minute }</s:if></td>
					</tr>
					<tr class="socialConditions policiesAndLaws otherManage">
						<td class="title">时间</td><td class="contable"><s:date name="issue.occurDate" format="yyyy-MM-dd "/><s:if test="issue.hours!=''&&issue.hours!=null&&issue.minute!=''&&issue.minute!=null">${issue.hours }:${issue.minute }</s:if></td>
					</tr>
					<tr class="specialPopulations otherManage">
						<td class="title">服务地点</td><td class="contable">${issue.occurLocation}</td>
					</tr>
					<tr class="resolveTheContradictions securityPrecautions emergencies otherManage">
			       		<td class="title">发生地点</td><td class="contable">${issue.occurLocation }</td>
			       	</tr>
					<tr class="socialConditions policiesAndLaws otherManage">
						<td class="title">地点</td><td class="contable">${issue.occurLocation}</td>
					</tr>
			       	<tr class="resolveTheContradictions emergencies">
						<td class="title">事件规模</td><td class="contable">
			       	 	<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" /></td>
			       	</tr>
					<tr class="resolveTheContradictions emergencies">
			        	<td class="title">涉及人数</td><td class="contable">${issue.relatePeopleCount}</td>
			       	</tr>
					<tr class="contradiction resolveTheContradictions emergencies otherManage">
			        	<td class="title">主要当事人</td><td class="contable">${issue.mainCharacters}</td>
			       	</tr>
			       	<tr class="socialConditions policiesAndLaws ">
			        	<td class="title">相关人员</td><td class="contable">${issue.mainCharacters}</td>
			       	</tr>
			       	<tr class="specialPopulations">
			        	<td class="title">服务对象</td><td class="contable">${issue.mainCharacters}</td>
			       	</tr>
			       	<tr class="securityPrecautions">
			        	<td class="title">联系人</td><td class="contable">${issue.mainCharacters}</td>
			       	</tr>
			       	<tr class="contradiction resolveTheContradictions">
			        	<td class="title">特殊人群</td><td class="contable">${involvedPersonnel}</td>
			       	</tr>
			       	<s:if test="issue.emergencyLevel.id != null">
			       	<tr class="resolveTheContradictions securityPrecautions emergencies otherManage">
			        	<td class="title">重大紧急等级</td><td class="contable">
			        	<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@URGENT_LEVEL" defaultValue="${issue.emergencyLevel.id}" /></td>
			       	</tr>
			       	</s:if>
			       	<tr>
			       		<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
						<p>
							<td class="title"><div class="grid-accessory"><span class="filetype-clip"></span>附件：</div></td>
							<td class="contable">	
							<div id="gallery">
								<s:iterator value="issueAttachFiles">
		                        <s:if test="fileActualUrl.toLowerCase().endsWith('.jpg')||fileActualUrl.toLowerCase().endsWith('.jpge')||fileActualUrl.toLowerCase().endsWith('.png')||fileActualUrl.toLowerCase().endsWith('.bmp')||fileActualUrl.toLowerCase().endsWith('.gif')">
		                        	<a data-gallery="gallery" style="color: red" class="view" href="<s:property value="fileActualUrl"/>" title="<s:property value="fileName"/>"><s:property value="fileName"/></a>;
		                        	<div class="clear"></div>
		                        </s:if>
	        					<s:else>
		                            <a href="${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>" style="color: red"><s:property value="fileName"/></a>;
		                        	<div class="clear"></div>
		                        </s:else>
		                        </s:iterator>
		                        <a href="javascript:;" class="viewImage" id="viewImages"></a>
							</div>
							</td>
						</p>
						</s:if>
			       	</tr>
			       	<tr class="contradiction resolveTheContradictions securityPrecautions emergencies">
			       		<td class="title">事件简述</td><td class="contable"><div class="issueC"><p>${issue.issueContent }</p></div></td>
			       	</tr>
			       	<tr class="specialPopulations">
			       		<td class="title">服务简述</td><td class="contable"><div class="issueC"><p>${issue.issueContent }</p></div></td>
			       	</tr>
			       	<tr class="socialConditions policiesAndLaws otherManage">
			       		<td class="title">简述</td><td class="contable"><div class="issueC"><p>${issue.issueContent }</p></div></td>
			       	</tr>
				</tbody>
			</table>
	</div>
	</div>
	<!-- <span class="star star1"></span> -->
	<s:if test="issueEvaluate.id!=null">
	<div class="issuePj">
		<h3 class="processRecord">事件验证</h3>
		<div class="issuePjCtt">
			<div><label>验证时间：</label><s:date name="#request.issueEvaluate.evaluateTime" format="yyyy-MM-dd" /></div>
			<div id="${issueEvaluate.evaluateType}" class="show"><label>验证等级：</label></div>
			<div><label>验证内容：</label>${issueEvaluate.evaluateContent }</div>
			 <s:if test="issueEvaluateAttachFiles !=null && issueEvaluateAttachFiles.size > 0">
	          	<div class="smallText" id="galleryEvaluate">
					<span class="filetype-clip"></span>附件：
		               <s:iterator value="issueEvaluateAttachFiles">
		               <s:if test="fileActualUrl.toLowerCase().endsWith('.jpg')||fileActualUrl.toLowerCase().endsWith('.jpge')||fileActualUrl.toLowerCase().endsWith('.png')||fileActualUrl.toLowerCase().endsWith('.bmp')||fileActualUrl.toLowerCase().endsWith('.gif')">
		               	<a data-gallery="gallery" class="view" href="<s:property value="fileActualUrl"/>" title="<s:property value="fileName"/>"><s:property value="fileName"/></a>;
		               </s:if>
					   <s:else>
		                   <a href="${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>"><s:property value="fileName"/></a>;
		               </s:else>
		               </s:iterator>
		               <a href="javascript:;" class="viewImage" id="viewImagesEvaluate"></a>
		               <script type="text/javascript">
		               $('#galleryEvaluate').imagegallery({
			       	        show: "random",
			       	        zIndex:999999,
			       	        close:function(){
			       				$('#galleryEvaluate').imagegallery('disable');
			       	        }
		       	    	});
		               $('#galleryEvaluate').imagegallery('disable');
	               	    $("#viewImagesEvaluate").click(function(){
	               	    	$('#galleryEvaluate').imagegallery('enable');
	               	    	if($('#galleryEvaluate').find(".view").eq(0).html() == null) {
	               	    		$('#galleryEvaluate').find("a:first")[0].click();
	               	    	} else {
	               	    		$('#galleryEvaluate').find(".view").eq(0).click();
	               	    	}
	               	    });
	               </script>
	           	</div>
 			</s:if>     
		</div>
	</div>
	</s:if>
</div>


<div class="issueRight issueCttRight">
	<div class="dealRecord recordList clearfix">
		<h3 class="processRecord">处理记录
			<div class="processBtnList">
				<a href="javascript:;" class="cur" id="textView"><span class="text"></span>文字视图</a>
				<a href="javascript:;" id="chartView"><span class="chart"></span>图表视图</a>
			</div>
		</h3>
			<div class="recordContent clearfix">
				<ul style="display:none;" id="issueTable">
					<s:subset source="issueDealLogs" start="0">
						<s:iterator status="index">
							<li id="2012-12-11">
								<dl class="recordData clearfix">
									<dd class="time"><s:date name="dealTime" format="yy年MM月dd HH:mm" /></dd>
									<dd class="place"><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="dealUserName"/></dd>
									<dd class="handle"><s:property value="dealDescription"/></dd>
								</dl>
								<s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
			                        <div class="smallText">
											<span>办理意见:</span><span>${content }</span>
									</div>
			                        <s:if test="issueLogAttachFiles[id]!=null && issueLogAttachFiles[id].size > 0">
				                    	<div class="smallText" id="gallery<s:property value="id"/>">
											<span class="filetype-clip"></span>附件：
					                        <s:iterator value="issueLogAttachFiles[id]">
					                        <s:if test="fileActualUrl.toLowerCase().endsWith('.jpg')||fileActualUrl.toLowerCase().endsWith('.jpge')||fileActualUrl.toLowerCase().endsWith('.png')||fileActualUrl.toLowerCase().endsWith('.bmp')||fileActualUrl.toLowerCase().endsWith('.gif')">
					                        	<a data-gallery="gallery" class="view" href="<s:property value="fileActualUrl"/>" title="<s:property value="fileName"/>"><s:property value="fileName"/></a>;
					                        </s:if>
	           								<s:else>
					                            <a href="${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>"><s:property value="fileName"/></a>;
					                        </s:else>
					                        </s:iterator>
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
		                   			</s:if>
	                    		</s:if>
							</li>
						</s:iterator>
					</s:subset>
				</ul>
				<div id="issueGraph" style="padding-top:45px;overflow: auto;width: 405px;height: 320px;">
				    <s:include value="/fourTeamsManage/fourTeamsIssueManage/IssueManage/fourTeamsRaphael.jsp" ></s:include>
			    </div>
			</div>
	
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("#issueTable").show();
		$("#issueGraph").hide();
		$(".processBtnList a").click(function(){
			$(this).addClass("cur").siblings().removeClass("cur");
		});
		$("#chartView").click(function(){
			$("#issueGraph").show();
			$("#issueTable").hide();
		});
		$("#textView").click(function(){
			$("#issueTable").show();
			$("#issueGraph").hide();
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
		setDivShow(issueType);
		
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
function setDivShow(flag){
	$(".contradiction,.resolveTheContradictions,.securityPrecautions,.specialPopulations,.socialConditions,.policiesAndLaws,.emergencies,.otherManage").hide();
	switch(flag){
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@PEOPLELIVE_SERVICE" escape="false"/>':
			$(".contradiction").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@RESOLVETHECONTRADICTIONS" escape="false"/>':
			$(".resolveTheContradictions").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SECURITYPRECAUTIONS" escape="false"/>':
			$(".securityPrecautions").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SPECIALPOPULATIONS" escape="false"/>':
			$(".specialPopulations").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@SOCIALCONDITIONS" escape="false"/>':
			$(".socialConditions").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@POLICIESANDLAWS" escape="false"/>':
			$(".policiesAndLaws").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@EMERGENCIES" escape="false"/>':
			$(".emergencies").show();
			break;
		case '<s:property value="@com.tianque.issue.constants.IssueTypes@OTHERMANAGE" escape="false"/>':
			$(".otherManage").show();
			break;
	}
}
</script>