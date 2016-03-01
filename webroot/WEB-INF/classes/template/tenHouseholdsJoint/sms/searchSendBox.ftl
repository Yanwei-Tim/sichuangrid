<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 
<div class="container container_24">
	<form action="" method="post" id="searchMaintainForm">
		<div class="grid_5 lable-right">
			<label>用户姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="messageInfoVo.userName" id="name" class="form-txt"/>
		</div>
		<div class="grid_5 lable-right">
			<label>联户长：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="messageInfoVo.houseMaster" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label>接收手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="messageInfoVo.telephone" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label>发送时间从：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="startSendDate" name="messageInfoVo.startSendDate" class="form-txt"/>
		</div>
		<div class="grid_5 lable-right">
			<label>到：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="endSendDate" name="messageInfoVo.endSendDate" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label>短信内容：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="messageInfoVo.text" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label>所属分组：</label>
		</div>
		<div class="grid_4">
			<select name="messageInfoVo.teamId" id="" class="form-txt" style="height: 22px;width:137px;">
				<option value="">请选择</option>
				<@s.iterator value="familyTeams" id="team">
					<option value="${(team.id)!}">${(team.teamName)!}</option>
				</@s.iterator>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
	$('#startSendDate').dateTimePicker({
			yearRange:'1930:2060',
			timeFormat: 'HH:mm:ss',
			maxDate:'+0y'
		});
	$('#endSendDate').dateTimePicker({
			yearRange:'1930:2060',
			timeFormat: 'HH:mm:ss',
			maxDate:'+0y'
		});
</script>