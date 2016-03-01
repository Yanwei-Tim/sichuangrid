<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body,html {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.tableSHOW {
	margin: 25px 0 0 0;
}

.tableSHOW  .table-hd {
	width: 100%;
	border-top: 1px solid #CCDFEE;
	border-left: 1px solid #ccc;
	border-collapse: collapse;
	position: relative;
	z-index: 1;
	table-layout: fixed;
}

.tableSHOW  .table-hd th {
	height: 28px;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	border-collapse: collapse;
	font: 12px/28px "";
	color: #333;
	background: #D0DDE6 url(/resource/images/icon_tbbg.jpg) repeat-x;
}

.tableSHOW  .table-bd {
	width: 100%;
	border-top: 1px solid #CCDFEE;
	border-left: 1px solid #ccc;
	border-collapse: collapse;
	position: relative;
	table-layout: fixed;
}

.tableSHOW  .table-bd td {
	height: 25px;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	border-collapse: collapse;
	font: 12px/25px "";
	color: #333;
	text-align: center;
}

.tableSHOW  .table-bd tr.even {
	background: #fff;
}

.tableSHOW  .table-bd tr.odd {
	background: #F5F5F5;
}

.tableSHOW  .table-bd td.status .state {
	width: 85px;
}

.tableSHOW  .pulicClass td div {
	word-wrap: break-all;
	word-break: break-all;
}

/* 
.tableSHOW  .table-bd .todo{width:20px;height:22px;display:block;margin:0 auto;vertical-align:top;background:url(/resource/images/icon_tbmk.png) no-repeat;}
.tableSHOW  .table-bd .todo.yellow-todo{background-position:0 -27px;}
.tableSHOW  .table-bd .todo.red-todo{background-position:0 -54px;}
.tableSHOW  .table-bd .todo.blue-todo{background-position:0 0px;}

.tableSHOW  .table-bd .event-expedited .expedited{width:14px;height:22px;display:block;margin:0 auto;background:url(/resource/images/icon_tbmk.png) no-repeat -2px -90px;}
 */
.tableSHOW  .pulicClass th.eventArea {
	width: 10%;
}

.tableSHOW  .pulicClass td.eventArea-e {
	width: 3%;
}
.tableSHOW  .dealState.publicltycass{background:url(/resource/system/images/issue/icon_publicltycassForList.png) no-repeat;}
.tableSHOW  .pulicClass td.eventArea-r {
	width: 7%;
}

.tableSHOW  .pulicClass td .event-toDo {
	width: 5%;
	align: center;
}

.tableSHOW  .todo {
	width: 22px;
	height: 22px;
	float: left;
	display: inline-block;
	*display: inline;
	margin: 0 auto;
}

.tableSHOW  .todo.yellow-todo {
	background: url(/resource/system/images/issue/icon_yHandleForList.png)
		no-repeat;
}

.tableSHOW  .todo.red-todo {
	background: url(/resource/system/images/issue/icon_rHandleForList.png)
		no-repeat;
}

.tableSHOW  .todo.blue-todo {
	background: url(/resource/system/images/issue/icon_bHandleForList.png)
		no-repeat;
}

.tableSHOW  .event-expedited.expedited {
	float: left;
	width: 17px;
	height: 17px;
	display: inline-block;
	*display: inline;
	*zoom: 1;
	margin: 0 0 0 10px;
	background: url(/resource/system/images/issue/icon_EmerignceForList.png)
		no-repeat;
}

.tableSHOW  .dealState {
	float: left;
	width: 17px;
	height: 17px;
	display: inline-block;
	*display: inline;
	*zoom: 1;
	margin: 0 0 0 10px;
}

.tableSHOW  .dealState.unconcepted-state {
	background:
		url(/resource/system/images/issue/icon_unconceptedForList.png)
		no-repeat;
}

.tableSHOW  .dealState.unread-state {
	background: url(/resource/system/images/issue/icon_unreadForList.png)
		no-repeat;
}

.tableSHOW  .dealState.complete-state {
	background: url(/resource/system/images/issue/icon_bLampForList.png)
		no-repeat;
}

.tableSHOW  .dealState.dealing-state {
	background: url(/resource/system/images/issue/icon_gLampForList.png)
		no-repeat;
}

.tableSHOW  .dealState.earingWarn {
	background: url(/resource/system/images/issue/icon_yLampForList.png)
		no-repeat;
}

.tableSHOW  .scrollDomoC {
	overflow: hidden;
	position: relative;
}

.tableSHOW  .scrollDomoF,.tableSHOW  .scrollDomoS {
	position: relative;
}

.tableSHOW  .scrollDomoS {
	border-top: none;
}

.dlgclose {
	display: block;
	position: absolute;
	right: 8px;
	top: 5px;
	cursor: pointer;
	background: url(/resource/system/images/dlgclose.png) no-repeat;
	text-indent: 18px;
	height: 15px;
	font: bold 15px/15px 'microsoft yahei' !important;
}

.ui-dialog .ui-dialog-content {
	padding: 0;
	width: 100% !important;
}
.tableSHOW th,.tableSHOW td{font-family:'microsoft yahei'}
</style>
<a href="javascript:;" class="dlgclose" id="dlgClose" title="返回">返回事项列表</a>
<div id='issueDialog'></div>
<div class="tableSHOW">
	<div id="eventProcess" class="">
		<table class="pulicClass table-hd">
			<tr>
				<th>
					<div class="event-toDo" align="center">督办</div>
				</th>
				<th>
					<div class="eventArea-r status" align="center">预警</div>
				</th>
				<!-- <th width="5%"><div class="event-expedited">加急</div></th>
                <th><div class="sevice-num" align="center">服务单号</div></th> -->
				<th><div class="event-name" align="center">事件</div>
				</th>
				<th><div class="event-name" align="center">事件类型</div>
				</th>
				<s:if test="#request.processType=='done'">
					<th><div class="event-name" align="center">评分</div>
					</th>
				</s:if>
				<th><div class="event-time" align="center">反馈时间</div>
				</th>
				<!--   <th><div class="event-lastTime" align="center">最后处理时间</div></th>-->
				<th><div class="event-department" align="center">承办部门</div>
				</th>
				<!--<th><div class="event-department" align="center">反馈部门</div></th>-->
				<th><div class="event-department" align="center">反馈人</div>
				</th>
				<th><div class="event-grid" align="center">反馈部门</div>
				</th>
				<th><div class="event-static" align="center">承办情况</div>
				</th>
			</tr>
		</table>
		<div class="scrollDomoC" id="scrollDomoC">
			<table id="scrollDomoF" class="pulicClass table-bd scrollDomoF">
				<s:iterator value="#request.gridPage.rows" id="issue">
					<tr>
						<td class="event-name"><s:if
								test="#issue.supervisionState==1">
								<div class="event-toDo">
									<strong class="todo blue-todo"></strong>
								</div>
							</s:if> <s:elseif test="#issue.supervisionState==100">
								<div class="event-toDo">
									<strong class="todo yellow-todo"></strong>
								</div>
							</s:elseif> <s:elseif test="#issue.supervisionState==200">
								<div class="event-toDo">
									<strong class="todo red-todo"></strong>
								</div>
							</s:elseif> <s:else>
								<div class="event-toDo">
									<strong></strong>
								</div>
							</s:else> <s:if test="#issue.earingWarn > 0">
								<div>
									<span style='color: red;'>剩<s:property
											value="#issue.earingWarn" />天时限</span>
								</div>
							</s:if></td>
						<td class="event-name">
							<div class="state">
								<s:if test="#issue.supervisionState==1||#issue.supervisionState==100||#issue.supervisionState==200">
								</s:if>
								<s:else>
									<s:if test="#issue.dealState==110">
										<span class="dealState unconcepted-state" title='待处理'
											style="height: 22px; width: 18px;"></span>
									</s:if>
									<s:elseif test="#issue.dealState==140">
										<span class="dealState unread-state" title='待阅读'
											style="height: 22px; width: 18px;"></span>
									</s:elseif>
									<s:elseif test="#issue.dealState==500">
										<span class="dealState complete-state" title='已处理'
											style="height: 22px; width: 18px;"></span>
									</s:elseif>
									<s:elseif test="#issue.dealState==800">
										<span class="dealState complete-state" title='待验证'
											style="height: 22px; width: 18px;"></span>
									</s:elseif>
									<s:elseif test="#issue.dealState==1000">
										<s:if test="#issue.status==301">
											<span class="dealState complete-state" title='已评分'
												style="height: 22px; width: 18px;"></span>
										</s:if>
										<s:else>
											<span class="dealState complete-state" title='已验证'
												style="height: 22px; width: 18px;"></span>
										</s:else>
									</s:elseif>
									<s:else>
										<span class="dealState dealing-state" title='处理中'
											style="height: 22px; width: 18px;"></span>
									</s:else>
									<s:if test="#issue.earingWarn > 0">
										<span class="dealState earingWarn"
											title='剩<s:property value="#issue.earingWarn"/>天时限'
											style="height: 22px; width: 18px;"></span>
									</s:if>
									<s:if test="#issue.urgent==1">
										<span class="event-expedited expedited" title="加急"
											style="height: 22px; width: 18px;"></span>
									</s:if>
								</s:else>
							</div></td>
						<!--  
	                    <td><div class="sevice-num"><a href="javascript:;" style="text-decoration: underline;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" 
	                    	issueId='<s:property value="#issue.issueId"/>' title='<s:property value="#issue.serialNumber"/>'>
	                    	<s:property value="#issue.serialNumber"/></a></div></td>
	                    -->
						<td><div class="event-name"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;width:145px;margin:0 auto;"
								title="<s:property value='#issue.subject'/>">
								<s:if
									test="#issue.topState==@com.tianque.issue.domain.TopIssue@TOP">
									<strong class='handleTop' title='置顶'></strong>
								</s:if>
								<s:property value="#issue.subject" />
								<s:if test="#issue.publicltycass==1">
									 <span class="dealState publicltycass" title="宣传事例" style="height: 22px; width: 18px;"></span>
								</s:if>
							</div>
						</td>
						<td>
							<!-- 事件类型（当是已办结完成的，显示办结满意度） -->
							<div class="event-name"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;width:145px;margin:0 auto;"
								title="<s:property value='#issue.issueTypes'/>">
								<s:property value="#issue.issueTypes" />
							</div>
						</td>
						<s:if test="#request.processType=='done'">
							<td><div class="event-static">
									<s:if test="#issue.evaluateType == 1">不满意</s:if>
									<s:elseif test="#issue.evaluateType == 2">基本满意</s:elseif>
									<s:elseif test="#issue.evaluateType == 3">满意</s:elseif>
								</div>
							</td>
						</s:if>
						<!--  
	                    <td><div class="event-time" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" title='<s:property value="#issue.occurDate"/>'>
	                    	<s:property value="#issue.occurDate"/></div></td>-->
						<!--  <td><div class="event-lastTime" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" title='<s:property value="#issue.dealTime"/>'>
	                    	<s:property value="#issue.dealTime"/></div></td>-->
						<td>
							<!-- 反馈时间 -->
							<div class="event-department"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
								title='<s:property value="#issue.dealTime"/>'>
								<s:property value="#issue.dealTime" />
							</div>
						</td>
						<td>
							<!-- 承办部门 --> <!-- <div class="event-grid" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" title='<s:property value="#issue.lastOrg.orgName"/>'>
	                    	<s:property value="#issue.lastOrg.orgName"/></div> -->
							<div class="event-grid"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
								title='<s:property value="#issue.targeOrg.orgName"/>'>
								<s:property value="#issue.targeOrg.orgName" />
							</div></td>
						<td>
							<!-- 督办人 -->
							<div class="event-grid"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
								title='<s:property value="#issue.createName"/>'>
								<s:property value="#issue.createName" />
							</div>
						</td>
						<td>
							<!--  反馈部门 --> <!-- <div class="event-grid" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" title='<s:property value="#issue.occurOrg.orgName"/>'>
	                    	<s:property value="#issue.occurOrg.orgName"/></div> -->
							<div class="event-grid"
								style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
								title='<s:property value="#issue.createOrg.orgName"/>'>
								<s:property value="#issue.createOrg.orgName" />
							</div>
						</td>
						<s:if test="#issue.dealState==110">
							<td><div class="event-static">待受理</div>
							</td>
						</s:if>
						<s:elseif test="#issue.dealState==140">
							<td><div class="event-static">待阅读</div>
							</td>
						</s:elseif>
						<s:elseif test="#issue.dealState==500">
							<td><div class="event-static">已办</div>
							</td>
						</s:elseif>
						<s:elseif test="#issue.dealState==800">
							<td><div class="event-static">待验证</div>
							</td>
						</s:elseif>
						<s:elseif test="#issue.dealState==1000">
							<s:if test="#issue.status==301">
								<td><div class="event-static">已评分</div>
								</td>
							</s:if>
							<s:else>
								<td><div class="event-static">已验证</div>
								</td>
							</s:else>

						</s:elseif>
						<s:else>
							<td><div class="event-static">办理中</div>
							</td>
						</s:else>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
</div>
<script>
	$.fn
			.extend({
				scrollFunc : function(option) {
					var _ = $(this);

					var defaultOption = {
						_scrollDomoC : 'scrollDomoC',
						_scrollDomoF : 'scrollDomoF',
						_timer : '',
						_obj : 'table-bd',
						_timerSpeed : 150
					};
					$.extend(defaultOption, option || {});
					var scrollDomoC = _.find("." + defaultOption._scrollDomoC);
					var scrollDomoF = _.find("." + defaultOption._scrollDomoF);
					var scrollDomoS = $("<table/>").attr("class",
							"pulicClass table-bd scrollDomoS").appendTo(
							$("." + defaultOption._scrollDomoC));

					function controlContainer() {
						function setHeight() {
							_.width(document.documentElement.clientWidth)
							scrollDomoC.height(scrollDomoF.height());
						}
						setHeight();

						$(window).resize(function() {

							clearTimeout(defaultOption._timer);
							defaultOption._timer = setTimeout(function() {
								setHeight();
							}, 10);
						})

					}
					controlContainer();

					$(".scrollDomoS:eq(0)").html(scrollDomoF[0].innerHTML);

					_.find("." + defaultOption._obj + " tr").hover(function() {
						clearTimeout(defaultOption.timer);
					}, function() {

						scrollShow();

					})
					function scrollShow() {
						defaultOption.timer = setInterval(
								function() {
									if (scrollDomoF[0].offsetHeight
											- scrollDomoC[0].scrollTop <= 0) {
										scrollDomoC[0].scrollTop -= scrollDomoF[0].offsetHeight;
									} else {
										scrollDomoC[0].scrollTop++;
									}
								}, defaultOption._timerSpeed)
					}
					scrollShow();
				}
			});
	$(function() {
		var tableControl = function() {
			$(".table-bd tr:even").addClass("even");
			$(".table-bd tr:odd").addClass("odd");
		}()
		$("#eventProcess").scrollFunc();
		$("#eventProcess")
				.delegate(
						"a",
						"click",
						function() {
							if ($(this).attr("issueId") != null) {
								$("#issueDialog")
										.createDialog(
												{
													width : 700,
													height : 450,
													title : "查看事件详情",
													url : "${path}/issues/issueManage/dispatch.action?mode=view&keyId="
															+ $(this).attr(
																	"issueId"),
													buttons : {
														"关闭" : function() {
															$(this).dialog(
																	"close");
														}
													}
												});
							}
						});
		$("#dlgClose").click(function() {
			$("#viewProcessDialog").dialog("close");
		})
	});

	
</script>