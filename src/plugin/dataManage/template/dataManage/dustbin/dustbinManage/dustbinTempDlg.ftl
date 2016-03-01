<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="dustbin" class="container container_24">
	<div id="perUuid"></div>
	<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}">
	<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
	<input type="hidden" name="modeType" id="modeType" value="${(modeType)!}" />
	<input type="hidden" name="population.id" id="dustbin-id" value="${(population.id)!}" />
	<input id="organizationId"	type="hidden" name="population.organization.id" value="${(population.organization.id)!}" />
	<input id="keyType" type="hidden" name="" value="${(keyType)!}" />
	<input id="personTypeName" type="hidden" name="personTypeName" value="${(personTypeName)!}">
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<input type="text"  name="population.organization.orgName" id="orgName"  readonly class="form-txt" value="${(population.organization.orgName)!}"/>
	</div>
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">部件标识码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="dustbinCode" name="population.dustbinCode" class="form-txt" value="${(population.dustbinCode)!}" maxlength="20"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">地址：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="address" name="population.address" class="form-txt" value="${(population.address)!}" maxlength="30"/>
	</div>
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">部件类型：</label>
	</div>
	<div class="grid_7">
	  <select name="population.partType.id" <@s.if test='"edit".equals(mode)'>disabled</@s.if> class="form-txt" id="partType">
	  <@pop.PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" defaultValue="${(population.partType.id)!}" 
	   		reletionId="partNameId" reletionName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" id="partType" />
		</select>
	</div>
	<div class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">部件名称：</label>
	</div>
	<div class="grid_8">
		<select id="partNameId" name="population.partName.id" class="form-txt">
		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME"  defaultValue="${(population.partName.id)! }"  />
		</select>
	</div>
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">主管部门名称：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="deptName" name="population.deptName" class="form-txt" value="${(population.deptName)!}" maxlength="20"/>
	</div>
	<div class="grid_8 lable-right" id="hasVideoShow" style="display:none" >
		<input type="checkbox" id="hasVideo" name="population.hasVideo" value="true"   <@s.if test='true==population.hasVideo'>checked="checked"</@s.if> 
		/>
		<label class="form-check-text">是否有视频流 </label>
	</div>
	<div class='clearLine'></div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">权属单位名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="ownershipUnitName" name="population.ownershipUnitName" class="form-txt" value="${(population.ownershipUnitName)!}" maxlength="20"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">护养单位名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="maintenanceUnitName" name="population.maintenanceUnitName" class="form-txt" value="${(population.maintenanceUnitName)!}" maxlength="20"/>
	</div>
   	<div class='clearLine'></div>
    <div class="grid_5 lable-right">
        <label class="form-lbl">备注：</label>
    </div>
    <div class="grid_19">
        <textarea rows="4" name="population.remark"  cols="" class="form-txt">${(population.remark)!}</textarea>
    </div>
	
	<div class='clearLine'>&nbsp;</div>
	<input name="isSubmit" id="isSubmit" type="hidden" value="">
 </div>	
 
<script type="text/javascript">
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	$(document).ready(function(){
		if($("#organizationId").val()==null || $("#organizationId").val()==""){
			$("#organizationId").val($("#currentOrgId").val());
		}

		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async : false,
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								evel: "error"
				 			});
	            			return;
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});
</script>