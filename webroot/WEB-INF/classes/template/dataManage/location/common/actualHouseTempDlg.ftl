<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="dialog-form" title="房屋信息维护" class="container container_24">
	<div id="houseInfo" class="container container_24">
		<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateTempBase.action" >
			<@pop.token />
			<input type="hidden" name="dailogName" value="${(dailogName)!}" />
			<input id="_imgUrl" name="houseInfo.imgUrl" type="hidden" value="${(houseInfo.imgUrl)!}"/>
			<input type="hidden" id="organizationId" name="houseInfo.organization.id" value="${(houseInfo.organization.id)!}"/>
			<#include "commonActualHouseDlg.ftl"/>
		</form>
	</div>
</div>

<script type="text/javascript">
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

$(document).ready(function(){
	
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};
	
	$("#maintainForm").formValidate({
		
		submitHandler: function(form) {
		$("#_imgUrl").val($("#imgUrl").val());
		businessHouseToCurrentAddress();
		var data={};
		if(!$("#isRentalHouse").attr("checked")){
			data={'houseInfo.isRentalHouse':false};
		}
		$("#currentAddressType option:eq(1)").attr('selected', true);
		$("#addressTypeId").val($("#currentAddressType").find("option:selected").attr("id"));
        $(form).ajaxSubmit({
        	async : false,
            data:data,
			success: function(data){
	        	if(!data.id){
					$.messageBox({
						message:data,
						level: "error"
					});
					return;
				}
	        	 $("#<@s.property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
	        	 $("#<@s.property value='#parameters.dailogName[0]'/>").proccessTypeSuccess(data.houseType,data);
      	   },
      	   error: function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交错误");
      	   }
      	});
	},
	rules:{
	},
	messages:{
	},
	ignore:':hidden'
});

});

</script>