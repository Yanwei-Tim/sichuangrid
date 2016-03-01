<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="orSearchFields">
		<input type="hidden" name="tqSearchVo.searchFields" value="subject,serialNumber" title="事件名称,服务单号">
	</div>
	<div id="andSearchFields" class="ScreenListLay">
		<div class="zjsScreenList">
			<div class="listName"><strong>事件类型：</strong></div>
			<div class="listLay" id="issueDomainDiv"><span class="on">全部</span></div>
			<div class="listLayhover listLaySelect cf">
				<div class="listLay"  id="issueTypeDiv" style="padding-left: 0px;"><span class="on">全部</span></div>
				<input type="hidden" name="tqSearchVo.searchs.issueTypeId">
			</div>
			<input type="hidden" name="tqSearchVo.searchs.issueTypeDomainId">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>督办情况：</strong></div>
			<div class="listLay"><span class="on">全部</span>
				<span value="<@s.property value='@com.tianque.issue.state.IssueState@NORMAL_SUPERVISE'/>"><@s.property value='@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE'/></span>
				<span value="<@s.property value='@com.tianque.issue.state.IssueState@YELLOW_CARD_SUPERVISE'/>"><@s.property value='@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE'/></span>
				<span value="<@s.property value='@com.tianque.issue.state.IssueState@RED_CARD_SUPERVISE'/>"><@s.property value='@com.tianque.issue.state.IssueOperate@RED_SUPERVISE'/></span>
				<span value="[<@s.property value='@com.tianque.issue.state.IssueState@NO_SUPERVISE'/> TO 0]">未督办</span>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.superviseLevel">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>事件状态：</strong></div>
			<div class="listLay">
				<span class="on">全部</span><span name="tqSearchVo.searchs.stateCode" value="110">待处理</span><span name="tqSearchVo.searchs.stateCode" value="120">办理中</span><span name="tqSearchVo.searchs.stateCode" value="500">待验证</span><span name="tqSearchVo.searchs.stateCode" value="1000">已验证</span><span name="tqSearchVo.searchs.issueevaluateNum" value="0">待评分</span>
				<span name="tqSearchVo.searchs.issueevaluateNum" value="[1 TO *]">已评分</span>
			</div>
			<input type="hidden">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>涉及人数：</strong></div>
			<div class="listLay">
				<div style="float:left">
					<span  class="on">全部</span><span value="[* TO 10]">10人以下</span><span value="[10 TO 100]">10-100人</span><span value="[100 TO *]">100人以上</span>
				</div>
				<div class="range" style="float:left">
					<input type="text" class="age min"> - <input type="text" class="age max"><input type="button" class="agesubmit" value="确定">
				</div>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.relatePeopleCount">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>事件规模：</strong></div>
			<div class="listLay"><span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND"/></div>
			<input type="hidden" name="tqSearchVo.searchs.issueKind">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>是否越级办理：</strong></div>
			<div class="listLay"><span class="on">全部</span><span  value="1">是</span><span  value="0">否</span></div>
			<input type="hidden" name="tqSearchVo.searchs.emergencyLevel">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>是否宣传案例：</strong></div>
			<div class="listLay"><span class="on">全部</span><span  value="1">是</span><span  value="0">否</span></div>
			<input type="hidden" name="tqSearchVo.searchs.publicltycassId">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>发生时间：</strong></div>
			<div class="listLay">
				<div style="float:left">
					<span  class="on">全部</span><span value="[NOW/DAY-7DAY TO NOW/DAY]">近一周</span><span value="[NOW/DAY-30DAY TO NOW/DAY]">近一月</span>
				</div>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.occurDate">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>录入时间：</strong></div>
			<div class="listLay">
				<div style="float:left">
					<span  class="on">全部</span><span value="[NOW/DAY-7DAY TO NOW/DAY]">近一周</span><span value="[NOW/DAY-30DAY TO NOW/DAY]">近一月</span>
				</div>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.createDate">
		</div>
	</div>
	<div id="orderFields" class="zjsLeftTop">排序：
	  <a href="javascript:;" name="tqSearchVo.sortFields.createDate">录入时间</a>
	  <a href="javascript:;" name="tqSearchVo.sortFields.occurDate">发生时间</a>
	  <a href="javascript:;" name="tqSearchVo.sortFields.stateCode">事件状态</a>
	</div>
</div>
<select id="issueDomain" class="hidden">
	<@pop.IssueTypeReleationSelectTag name="contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
	reletionId="issueTypeId" isOperateDiv="1" id="issueDomain"/>
</select>
<select id="issueTypeId" class="hidden"></select>
<script language="javascript">
var issueTypeDomainData=[];
var issueTypeData=[];
$(document).ready(function(){
	$("#issueDomain option").each(function(){
		$this = $(this);
		var typeId = $this.val();
		var typeName = $this.text();
		if(typeId){
			issueTypeDomainData[typeId] = typeName;
			$("#issueDomainDiv").append("<span value='"+typeId+"'>"+typeName+"</span>");
		}
	})
	if(typeof issueTypeArr != "undefined"){
		for(var i=0;i<issueTypeArr.length;i++){
			var id = issueTypeArr[i].id;
			var name = issueTypeArr[i].issueTypeName;
			issueTypeData[id] = name;
		}
	}
	$("#issueDomainDiv ").delegate("span","click",function(){
		var value = $(this).attr("value");
		$("#issueTypeDiv").empty();
		if(!value){
			$("#issueDomainDiv").next().hide();
			$("input[name='tqSearchVo.searchs.issueTypeId']").val("");
			return;
		}
		$(this).closest(".zjsScreenList").height("auto").css({"padding-right" : 0 });
        $('#issueDomainDiv').next().show();
		$("select#issueDomain").val(value).change();
		$("#issueTypeDiv").append('<span class="on">全部</span>');
		$("select#issueTypeId option").each(function(){
			$this = $(this);
			var typeId = $this.val();
			var typeName = $this.text();
			if(typeId){
				$("#issueTypeDiv").append("<span value='"+typeId+"'>"+typeName+"</span>");
			}
		})
	})
	
	$("#issueDomainDiv").append('<a href="javascript:;" id="showAll">展开</a><a href="javascript:;" id="hideAll" style="display:none;">收缩</a>');
	$('.zjsScreenList').eq(0).show().siblings().hide();
	$('.zjsLeftTop').show();
});
</script>	