<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<form id="searchServiceTeamForm">
<@pop.token />
	<input id="mode" type="hidden" name="mode" value="${mode!}"/> 
	<input id="teamOrgId" type="hidden"	name="serviceTeamVo.org.id" value="<@s.property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
	
	<div id="search-condition-form" title="服务团队查询" class="container container_24">
	
		<div class="grid_4 lable-right">
	   		<label class="form-lb1">团队状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="conditionLogOutState" class="form-select" >
				<option value="-1">全部</option>
				<option value="0" selected="selected">未解散</option>
				<option value="1">已解散</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">团队名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionName" name="serviceTeamVo.teamName" class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">团队类别：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="serviceTeamVo.teamType.id" class="form-select" >
				<@pop.OptionTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
			</select>
	  	</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成立时间：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="buildDateStart" name="serviceTeamVo.buildDateStart" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="buildDateEnd" name="serviceTeamVo.buildDateEnd" class="form-txt" readonly="readonly" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成员人数：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMin" name="serviceTeamVo.teamCountMin" class="form-txt" />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMax" name="serviceTeamVo.teamCountMax" class="form-txt" />
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	
	//成立日期开始
	$('#buildDateStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#buildDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	//成立日期结束
	$('#buildDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#buildDateStart").datepicker("option", "maxDate",date);
			}
		}
	});

	$("#searchServiceTeamForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"serviceTeamVo.teamCountMin":{
				number:true,
				min:0,
				max:999999
			},
			"serviceTeamVo.teamCountMax":{
				number:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"serviceTeamVo.teamCountMin":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			},
			"serviceTeamVo.teamCountMax":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			}
		}
	});
	
});
</script>
