<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="aidspopulations" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/aidspopulationsManage/addAidspopulations.action"  >
			<@pop.token />
			<div id="perUuid"></div>
			<input type="hidden" name="mode" id="mode" value="<@s.property value="mode"/>" />
			<input type="hidden" name="modeType" id="modeType" value="<@s.property value="modeType"/>" />
			<input type="hidden" name="contextId"  value="<@s.property value="contextId"/>" />
			<input type="hidden" name="cacheId.id"  value="<@s.property value="cacheId.id"/>" />
			<input type="hidden" name="population.id" value="<@s.property value="population.id"/>" />
			<input id="_imgUrl" name="population.imgUrl" type="hidden" value="<@s.property value="population.imgUrl"/>"/>
			<input id="keyType" type="hidden" name="" value="<@s.property value="keyType"/>" />
			<input id="personTypeName" type="hidden" name="personTypeName" value="<@s.property value="personTypeName"/>">
	    	<input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="<@s.property value="population.isEmphasis"/>" />
			<span style="font-weight: bold; margin-left:20px; ">艾滋病人员</span>
				<div class='clearLine'>&nbsp;</div>
			<div class="grid_6 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_6">
				<select name="population.attentionExtent.id" id="aidspopulations-attentionExtent" class="form-txt"
					<@s.if test='"view".equals(mode)'>disabled='true'</@s.if> >
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT"  defaultValue="${(population.attentionExtent.id)!}"></@pop.OptionTag>
				</select>
			</div>
			<div class="grid_6 lable-right">
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">感染途径：</label>
			</div>
			<div class="grid_6">
			    <select id="population.infectway.id" name="population.infectway.id"  class="form-txt" <@s.if test='"view".equals(mode)'>disabled</@s.if>>
			    	<@pop.OptionTag name="@com.tianque.constant.PropertyTypes@INFECT_WAY" defaultValue="${(population.infectway.id)!}" ></@pop.OptionTag>
			    </select>
			</div>
			
			<div class="grid_6 lable-right">
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">违法情况：</label>
			</div>
			<div class="grid_6">
				<select id="population.violationsofthelaw.id" name="population.violationsofthelaw.id"  class="form-txt" <@s.if test='"view".equals(mode)'>disabled</@s.if>>
			    	<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW"  defaultValue="${(population.violationsofthelaw.id)!}"></@pop.OptionTag>
			    </select>
			</div>
			
			<div class="grid_6 lable-right">
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">犯罪类型：</label>
			</div>
			<div class="grid_6">
				<select id="population.crimetype.id" name="population.crimetype.id"  class="form-txt" <@s.if test='"view".equals(mode)'>disabled</@s.if>>
			    	<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@CRIMETYPE"   defaultValue="${(population.crimetype.id)!}" ></@pop.OptionTag>
			    </select>
			</div>
			<input type="hidden" readonly="readonly" name="population.birthday" value="<@s.date name="population.birthday" format="yyyy-MM-dd"/>"  id="aidspopulations-birthday" class="form-txt" />
			<div class="grid_6 lable-right">
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">收治机构：</label>
			</div>
			<div class="grid_6">
				<input type="text" name="population.receivedorganization" id="receivedorganization" value="${(population.receivedorganization)!}"  class="form-txt dialogtip {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" value="<@s.property value="population.receivedorganization"/>" maxlength="30"
				<@s.if test='"view".equals(mode)'>disabled</@s.if>/>
			</div>
			
			<div class="grid_6 lable-right">
				<em class="form-req">*&nbsp;</em>
				<label class="form-lbl">收治机构所属层级： </label>
			</div>
			<div class="grid_6">
				<select name="population.receivedlevel.id" class="form-txt"
			    <@s.if test='"view".equals(mode)'>disabled='true'</@s.if>id="receivedlevel">
			   		<@pop.OptionTag name="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL"  defaultValue="${(population.receivedlevel.id)!}" ></@pop.OptionTag>
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<input name="isSubmit" id="isSubmit" type="hidden" value="">
		</form>
  	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(!data.id){
           	 			$.messageBox({ 
							level: "error",
							message:data
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	 $.messageBox({message:"艾滋病人员新增成功！"});
	                }
	                if("edit"==$("#mode").val()){
	                	 $.messageBox({message:"艾滋病人员修改成功！"});
	                }
	                 $("#aidspopulationsList").trigger("reloadGrid");
	                 doAction("<@s.property value='#parameters.dailogName[0]'/>",data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"population.infectway.id":{
				required: true
			},
			"population.violationsofthelaw.id":{
				required: true
			},
			"population.crimetype.id":{
				required: true
			},
			"population.receivedorganization":{
				required: true,
				minlength:0,
				maxlength: 30,
				exculdeParticalChar :true
			},
			"population.receivedlevel.id":{
				required: true
			}
		},
		messages:{
			"population.infectway.id":{
				required: "请选择感染途径！"
			},
			"population.violationsofthelaw.id":{
				required: "请选择违法情况！"
			},
			"population.crimetype.id":{
				required: "请选择犯罪类型！"
			},
			"population.receivedorganization":{
				required: "请输入收治机构！",
				minlength  : $.format("至少需要{0}个字符"),
	   			maxlength  : $.format("最多只能输入{0}个字符"),
			},
			"population.receivedlevel.id":{
				required: "请选择收治机构所属层级！"
			}
		}
	});
});

</script>