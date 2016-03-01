<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
	<div class="container container_24">
	<fieldset class="clearfix" style="border:1px solid #ccc;">
           <legend style="background-color:#EFF5FF;text-align:center;font-family:arial;font-weight:bold" > 房屋信息</legend>
	 	 <@s.include value="${path}/dataManage/location/rentalHouseTempManage/commonActualHouseTempDlg.ftl"></@s.include>
	   
	</fieldset>
	<div class='clearLine'>&nbsp;</div>
	<br>
	<br>
	<fieldset class="clearfix" style="border:1px solid #ccc;">
           <legend style="background-color:#EFF5FF;text-align:center;font-family:arial;font-weight:bold" > 出租房</legend>
	    <@s.include value="${path}/dataManage/location/rentalHouseTempManage/commonRentalHouseTempDlg.ftl"></@s.include>
	</fieldset>
	</div>
	<div id="populationDialogJsp"></div>

<script type="text/javascript">
var orgTree=$("#rentalHouseTempOrgName").treeSelect({
	rootId: <@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>,
	inputName:"organization.id"
});
function isGridForTreeSelect(){
	if(orgTree != ""){
		return $("#organizationId").attr("orgLevelInternalId") == <@s.property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return false;
	}
}
function getSelectOrgId(){
	var selectOrgId = 0;
	if(!isFromResultList){
		selectOrgId = ${(location.organization.id)!};
	}else{
		if(claimOrgIdForGlobal == 0){
			selectOrgId = ${(location.organization.id)!};
		}else{
			selectOrgId = claimOrgIdForGlobal;
		}
	}
	return selectOrgId;
}
jQuery.validator.addMethod("isGridOrganization", function(value, element){
	if(value==null||value==undefined||value==""){return true;}
	if(isGridForTreeSelect()){
		return true;
	}else{
		return false;
	}

});
function submitPlace(form){
		$(form).ajaxSubmit({
	        success: function(data){
	        	if(data.errorType == "1"){
            		 $.confirm({
						title:"确认",
						message:"该出租房的基础信息库中已存在,是否继续操作?",
						okFunc: function() {
						    dispatchOperateOrClaimById($("#houseInfoId").val(),$('#houseCode').val(),$("#organizationId").val());
							
					    },
						close:function(){
							$("#rentalHouseTempDialog").dialog("close");
						}
				    });
            		 return;
            		 
	            }
	        	if(data.errorType=="4"){
       			 	$.messageBox({level: "warn",message:data.errorInfo});
       			 }
	        	 if (isFromResultList) {
	                	$("#rentalHouseTempList").trigger("reloadGrid");
		                $("#successMsg").addRowData(data.id,data,"first");
		                $("#claimErrorMsg").delRowData(data.id);
		              
		            
	                }else{
	                	$("#rentalHouseTempList").trigger("reloadGrid");
	                }
	                if(data.successful){
	                	$.messageBox({message:"修改并认领出租房成功!"});
					    $("#rentalHouseTempDialog").dialog("close");
					    $("#rentalHouseTempList").trigger("reloadGrid");
	                }
				   
	 	   },
	 	   error: function(XMLHttpRequest, textStatus, errorThrown){
	 	      alert("提交数据时发生错误");
	 	   }
		});
	
}
$(document).ready(function(){
	/*
	$("#maintainPlaceForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
         	submitPlace(form);
		},
		ignore:':hidden',
		rules:{
			
		},
		messages:{
			
		}
	});
	*/
	function afterChangNode(node){
		$("#organizationId").attr("orgLevelInternalId",node.attributes.orgLevelInternalId);
	}
	$.addClick(orgTree,afterChangNode);
		threeSelect({
		province:'province',
		city:'city',
		district:'district',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});
});



</script>