<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="dustbin" class="container container_24">
	<div id="perUuid"></div>
	<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateBuilddatasTempBase.action">
	<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
	<input type="hidden" name="modeType" id="modeType" value="${(modeType)!}" />
	<input type="hidden" name="population.id" id="dustbin-id" value="${(population.id)!}" />
	<input id="organizationId"	type="hidden" name="population.organization.id" value="${(population.organization.id)!}" />
	<input id="keyType" type="hidden" name="" value="${(keyType)!}" />
	<input id="personTypeName" type="hidden" name="personTypeName" value="${(personTypeName)!}">
	<input id="districtOrgId" type="hidden" value="${(population.claimDetail.districtOrgId)! }" />
	<input id="tempClassName" type="hidden" value="${tempClassName}" />
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<input type="text"  name="population.organization.orgName" id="orgName"  readonly class="form-txt" value="${(population.organization.orgName)!}"/>
	</div>
	<div class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">楼宇名称：</label>
	</div>
	<div class="grid_19">
		<input type="text"   name="population.buildingname" id="buildingname" value="${population.buildingname!}"  class="form-txt" maxlength="50" />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">楼宇类型：</label>
	</div>
	<div class="grid_8">
		<select name="population.type.id" id="population-type" class="form-select"  >
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" defaultValue="${population.type.id}" />
		</select>
	</div>
	
	<div class="grid_7 lable-right">
		<input type="checkbox" name="population.isPropertyManagement" value="true" <#if population.isPropertyManagement! >checked</#if>/>
		<label class="form-lb1">是否有物管</label>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">楼宇地址：</label>
	</div>
	<div class="grid_19">
	 	<input type="text" name="population.buildingaddress" id="buildingaddress" value="${population.buildingaddress!}"  class="form-txt" maxlength="50" />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">业主：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="population.owner" id="owner" value="${population.owner!}"  class="form-txt" maxlength="20" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">负责人：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="population.responsibleperson" id="responsibleperson" value="${population.responsibleperson!}" class="form-txt" maxlength="20" />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="population.phone" id="phone" value="${population.phone!}"  class="form-txt" maxlength="15" />
	</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">建筑结构：</label>
	</div>
	<div class="grid_8">
		<select name="population.buildingstructures.id" id="buildingstructures" class="form-select" >
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${population.buildingstructures.id}" />
		</select>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
 </div>	
 </form>
 <script>
	$(document).ready(function(){
		if($("#organizationId").val()==null || $("#organizationId").val()==""){
			$("#organizationId").val($("#currentOrgId").val());
		}

		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								evel: "error"
				 			});
	            			return;
						}
						$.messageBox({message:"楼宇信息修改成功！"});
	    				$("#${tempClassName}List").trigger("reloadGrid");
						$("#${tempClassName}Dialog").dialog("close");
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
				"population.buildingname":{
					required:true,
					exculdeParticalChar:true,
					maxlength:50},
				"population.buildingaddress":{
					required:true,
					exculdeParticalChar:true,
					maxlength:50},
				"population.owner":{
					required:false,
					exculdeParticalChar:true,
					maxlength:20},
				"population.responsibleperson":{
					required:false,
					exculdeParticalChar:true,
					maxlength:20},
				"population.phone":{
					required:false,
					telephone:true,
					minlength:7,
					maxlength:15},
				"population.buildingstructures.id":{
					required:true},
				"population.type.id":{
					required:true}
			},
			messages:{
				"population.buildingname":{
					required:"请输入楼宇名称",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("楼宇名称最多需要输入{0}个字符")},
				"population.buildingaddress":{
					required:"请输入楼宇地址",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("楼宇地址最多需要输入{0}个字符")},
				"population.owner":{
					required:"请输入业主",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("业主最多需要输入{0}个字符")},
				"population.responsibleperson":{
					required:"请输入负责人",
					exculdeParticalChar:"不能输入非法字符",
					maxlength:$.format("负责人最多需要输入{0}个字符")},
				"population.phone":{
					required:"请输入联系电话",
					telephone:$.format("联系电话只能输数字和横杠(-)"),
					minlength:$.format("联系电话最少需要输入{0}个字符"),
					maxlength:$.format("联系电话最多需要输入{0}个字符")},
				"population.buildingstructures.id":{
					required:"请输入建筑结构"},
				"population.type.id":{
					required:"请输入楼宇类型"}		
			}
		});
	});
</script>