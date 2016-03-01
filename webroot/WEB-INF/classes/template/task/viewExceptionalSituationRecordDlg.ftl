<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<#assign c=JspTaglibs["/WEB-INF/taglib/c.tld"] />
<@s.include value="/includes/baseInclude.jsp"/>
<div id="tabList">
	<ul>
		<li><a  href="javascript:;" id="infoTab">信息</a> </li>
		<li><a  href="javascript:;" id="taskListReplyTab">回复列表</a> </li>
	</ul>
</div>
<table class="tablelist" id="infoList">
	<tr>
		<td class="title"><label>时间</label></td>
		 <td class="content" colspan="3"><span><@s.date name="exceptionalSituationRecord.recordDate" format="yyyy-MM-dd HH:mm:ss" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>地点</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.address)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>异常类型</label></td>
		<td class="content" colspan="3"><span><@pop.PropertyDictViewTag  name="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SITUATION_TYPE" defaultValue="${(exceptionalSituationRecord.exceptionSituation.id)!}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>所属网格</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.organization.fullOrgName)!}</span></td>
	</tr>
	
	<tr>
		<td class="title"><label>网格员联系电话</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.gridMemberPhone)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>签收人</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.signMemberName)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>签收时间</label></td>
		<td class="content" colspan="3"><span><@s.if test='exceptionalSituationRecord.status == 1'><@s.date name="exceptionalSituationRecord.signDate" format="yyyy-MM-dd HH:mm:ss" /></@s.if></span></td>
	</tr>
	<tr>
		<td class="title"><label>签收意见</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.attitude)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>备注</label></td>
		<td class="content" colspan="3"><span>${(exceptionalSituationRecord.mark)!}</span></td>
	</tr>
	<tr>
		<#if (exceptionalSituationRecord.exceptionalSituationAnnexFiles??)&&(exceptionalSituationRecord.exceptionalSituationAnnexFiles?size>0)>	
		<p>
							<td class="title"><div class="grid-accessory"><span class="filetype-clip"></span>附件：</div></td>
							<td class="contable" colspan="3">	
							<div id="gallery">
							<#list exceptionalSituationRecord.exceptionalSituationAnnexFiles as exceptionalSituationAnnexFile>
							<a data-gallery="gallery" style="color: red" class="view" target="_Blank" href="${exceptionalSituationAnnexFile.physicsFullFileName }" title="${exceptionalSituationAnnexFile.fileName}">${exceptionalSituationAnnexFile.fileName}</a>;
			                        		<div class="clear"></div>
							</#list>
								
								
		                        
		                   <a href="javascript:;" class="viewImage" id="viewImages"></a>
					</div>
					</td>
				</p>		
		</#if>
	</tr>
	<tr>
</table>
<div id="showTaskListReply"></div>
<script type="text/javascript">
$(function(){
	$( "#tabList" ).tabs({ selected: 0});
	$("#infoTab").click(function(){
		$("#infoList").show();
		$("#showTaskListReply").hide();
	});
	$("#taskListReplyTab").click(function(){
		$("#infoList").hide();
		$("#showTaskListReply").show();
		if($("#showTaskListReply").html()==""){
			$.get(PATH+"/plugin/taskListManage/common/taskListReplyListDlg.action",{'taskListReply.moduleKey':'reply_exceptionalSituationRecord','taskListReply.taskId':'${id}'},function(data){
				$("#showTaskListReply").html(data);
			});
		}
	});
})
</script>


