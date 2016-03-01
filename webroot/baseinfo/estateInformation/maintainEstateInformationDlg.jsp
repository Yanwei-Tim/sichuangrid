<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<style>
.defaultButton{background:url(${resource_path}/resource/images/button.jpg) no-repeat;border:0;width:54px;height:24px;line-height:24px;text-align:center;color:#2C6EA2;}
</style>

<div class="container container_24">
	<s:if test='!"view".equals(mode)'>
		<form id="maintainForm" method="post"	action="" >
		<pop:token />
	</s:if>
	
	<input type="hidden" name="mode" id="mode" value="${mode}" /> 
	<input type="hidden" name="estateInformation.id" id="estateInformation-id" value="${estateInformation.id}" />
	<input type="hidden" name="ownerOrg.id" id="organizationId" value="${ownerOrg.id}" />
	<input type="hidden" name="deleteIds" id="deleteIds" value="" />
	
   <fieldset>
		<legend style="background-color:#EFF5FF;text-align:center;font-family:arial;font-weight:bold" >房产信息</legend>
		    <div  class="grid_4 lable-right" >
	  			<label class="form-lbl">房产证号：</label>
	  		</div>
			<div class="grid_4">	
			    <input type="text" name="estateInformation.proprietaryNo" value="${estateInformation.proprietaryNo}" id="estateInformation-proprietaryNo" maxlength="20"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
	   		<div class="grid_1">
	   		</div>
	        <div  class="grid_3 lable-right" >
	  			<label class="form-lbl">土地证号：</label>
	  		</div>
			<div class="grid_4">	
			    <input type="text" name="estateInformation.landPermit" value="${estateInformation.landPermit}" id="estateInformation-landPermit" maxlength="20"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
	   		<div  class="grid_4 lable-right" >
	  			<label class="form-lbl">住宅产权：</label>
	  		</div>
			<div class="grid_4">
				<select name="estateInformation.houseEquity.id" class="form-select"
			    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HOUSE_EQUITY" defaultValue="${estateInformation.houseEquity.id}" />
				</select>
			</div>
			<div class="clearLine">&nbsp;</div>
		
		    <div  class="grid_4 lable-right" >
	  			<label class="form-lbl">建筑结构：</label>
	  		</div>
			<div class="grid_4">
				<select name="estateInformation.buildingStructure.id" class="form-select"
			    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BUILDING_STRUCTURE" defaultValue="${estateInformation.buildingStructure.id}" />
				</select>
			</div>
		    <div  class="grid_4 lable-right" >
	  			<label class="form-lbl">建筑面积：</label>
	  		</div>
			<div class="grid_3">	
			    <input type="text" name="estateInformation.coveredArea" value="${estateInformation.coveredArea}" id="estateInformation-coveredArea" maxlength="10" style="text-align:right;"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
	   		<div  class="grid_1" >
	  			<label class="form-lbl">(㎡)</label>
	  		</div>
	        <div  class="grid_4 lable-right" >
	  			<label class="form-lbl">占地面积：</label>
	  		</div>
			<div class="grid_3">	
			    <input type="text" name="estateInformation.floorArea" value="${estateInformation.floorArea}" id="estateInformation-floorArea" maxlength="10" style="text-align:right;"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
	   		<div  class="grid_1" >
	  			<label class="form-lbl">(㎡)</label>
	  		</div>
			<div class="clearLine">&nbsp;</div>
			
			<div  class="grid_4 lable-right" >
	  			<label class="form-lbl">建构年代：</label>
	  		</div>
			<div class="grid_4">
				<select name="estateInformation.structureYear.id" class="form-select"
			    <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STRUCTURE_YEAR" defaultValue="${estateInformation.structureYear.id}" />
				</select>
			</div>
	        <div  class="grid_4 lable-right" >
	  			<label class="form-lbl">房产地址：</label>
	  		</div>
			<div class="grid_12">	
			    <input type="text" name="estateInformation.housePropertyPlace" value="${estateInformation.housePropertyPlace}" id="estateInformation-housePropertyPlace" maxlength="50"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
			<div class="clearLine">&nbsp;</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">所属网格：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="" id="orgName" value="" readonly class="form-txt" />
			</div>
	   		<div class="grid_1">
				<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	   		</div>
			<div  class="grid_3 lable-right" ></div>
			<div class="grid_4">
				<input id="estateInformation-isHire" name="estateInformation.Hire" type="checkbox" value="true" 
				<s:if test="estateInformation.Hire">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="Hire">有出租房</label>
			</div>
			<div  class="grid_4 lable-right" ></div>
			<div class="grid_4">
				<input id="estateInformation-isDangerousBuilding" name="estateInformation.DangerousBuilding" type="checkbox" value="true" 
				<s:if test="estateInformation.DangerousBuilding">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="DangerousBuilding">危房</label>
			</div>
			<div class="clearLine">&nbsp;</div>
			
			<div  class="grid_4 lable-right" >
	  		</div>
			<div class="grid_4">
				<input id="estateInformation-isHasTapWater" name="estateInformation.HasTapWater" type="checkbox" value="true" 
				<s:if test="estateInformation.HasTapWater">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="HasTapWater">自来水已入户</label>
			</div>
	        <div  class="grid_4 lable-right" >
	  		</div>
			<div class="grid_4">
				<input id="estateInformation-isHasNet" name="estateInformation.HasNet" type="checkbox" value="true" 
				<s:if test="estateInformation.HasNet">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="HasNet">互联网已入户</label>
			</div>
	   		<div  class="grid_4">
	  		</div>
			<div class="grid_4">
				<input id="estateInformation-isHasToilet" name="estateInformation.HasToilet" type="checkbox" value="true" 
				<s:if test="estateInformation.HasToilet">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="HasToilet">有卫生间</label>
			</div>
			<div class="clearLine">&nbsp;</div>
			
			<div  class="grid_4 lable-right" >
	  			<label class="form-lbl">出租房地址：</label>
	  		</div>
			<div class="grid_11">	
			    <input type="text" name="estateInformation.occupiedAddress" value="${estateInformation.occupiedAddress}" id="estateInformation-occupiedAddress" maxlength="50"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
	        <div  class="grid_5 lable-right" >
	  			<label class="form-lbl">出租房房产证号：</label>
	  		</div>
			<div class="grid_4">	
			    <input type="text" name="estateInformation.occupiedNo" value="${estateInformation.occupiedNo}" id="estateInformation-occupiedNo" maxlength="20"
			    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
	   		</div>
			<div class="clearLine">&nbsp;</div>
	  </fieldset>
	  <fieldset>
		<legend style="background-color:#EFF5FF;text-align:center;font-family:arial;font-weight:bold" >房主信息</legend>
			<s:if test='!"view".equals(mode)'>
			    <div class="grid_4 lable-right" >
		  			<label class="form-lbl">身份证号码：</label>
		  		</div>
				<div class="grid_14">	
				    <input type="text" id="search_idCardNo" maxlength="18" name="search_idCardNo" 
				    <s:if test='"view".equals(mode)'>readonly</s:if> class="form-txt" />
		   		</div>
				<div class="clearLine">&nbsp;</div>
			</s:if>
			
			<input type="text" name="atLeastOneData" style="border:0;width:0px;height:0px;overflow:hidden:clear:both;margin-left:250px;margin-top:-20px;" /> 
			<div id="inhabitants">
			    <s:iterator value="inhabitants" var="inhabitantConnect">
			       <div id="div-${inhabitant.id}">
					  <input type="hidden" id="inhabitants-${inhabitantConnect.id}" value="${inhabitantConnect.id}"  class="inhabitant-notnull"/>
					  <div  class="grid_4 lable-right" >
			  			  <label class="form-lbl">姓名：</label>
			  		  </div>
			  		  <div class="grid_4">
			  		      <input type="text" value="${inhabitantConnect.name}" id="inhabitants-${inhabitantConnect.name}" maxlength="20"
					      readonly="readonly" class="form-txt" name="inhabitant_name" />
			   		  </div>
			          <div  class="grid_4 lable-right" >
			  			  <label class="form-lbl">身份证号码：</label>
			  		  </div>
					  <div class="grid_6">
					      <input type="text" value="${inhabitantConnect.idCardNo}" id="inhabitants-${inhabitantConnect.idCardNo}" maxlength="18"
					      readonly="readonly" class="form-txt" name="inhabitant_idCardNo"/>
			   		  </div>
			   		  <div  class="grid_4 lable-right" >
			  			  <input type="button" value="删除" id="deleteInhabitant-${inhabitantConnect.id}" class="defaultButton"  
			  			  <s:if test='"view".equals(mode)'>disabled</s:if>  />
			  		  </div>
			  		  <script type="text/javascript">
			  		      $("#deleteInhabitant-${inhabitantConnect.id}").click(function(){
			  		    	  if($('.inhabitant-notnull').length==1){
				  		    	 $("#deleteInhabitant-${inhabitantConnect.id}").dialogtip({
									content:"至少输入一条房主信息，不能删除",
									alignX:"center"
								 });
				  		    	 $("#deleteInhabitant-${inhabitantConnect.id}").poshytip('show');
				  		    	 $(".tip-error").bgiframe();
								 $(".tip-error").css("cursor","pointer");
								 $(".tip-error").click(function(){
									 var inputObj = $(document.getElementsByName($(this).find(".inputName").attr("inputName"))[0]);
									 if(inputObj.css("display")=="none"){
										 if(inputObj.next().css("display")!="none"){
											 inputObj = inputObj.next();
										 }else{
											 inputObj = inputObj.parent();
										 }
									 }
									 inputObj.attr("createMsg","false");
									 $(this).remove();
								 });
				  		      }else{
					  		      if($("#deleteIds").val()=="" || $("#deleteIds").val()==null){
					  		    	$("#deleteIds").val($("#deleteIds").val()+$("#inhabitants-${inhabitantConnect.id}").val());
					  		      }else{
					  		    	$("#deleteIds").val($("#deleteIds").val()+","+$("#inhabitants-${inhabitantConnect.id}").val());
					  		      }
							      $(this).parent().parent().remove();
				  		      }
					      });
			  		  </script>
					  <div class="clearLine">&nbsp;</div>
				   </div>
			    </s:iterator>
			</div>
	  </fieldset>	
	  <s:if test='!"view".equals(mode)'>
		</form>
	 </s:if>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		jQuery.validator.addMethod("isProprietaryNoRepeat", function(value, element){
			var proprietaryNo=$('#estateInformation-proprietaryNo').val();
			var count;
			if (!(proprietaryNo==null||proprietaryNo=="")){
				$.ajax({
					async: false,
					url: "${path }/baseinfo/estateInformationManage/getExistedProprietaryNoCount.action",
					data:{
						"ownerOrg.id":$("#organizationId").val(),
						"estateInformation.id":$("#estateInformation-id").val(),
						"mode":$("#mode").val(),
						"estateInformation.proprietaryNo":proprietaryNo
					},
					success:function(responseData){
						count= responseData;
					}
				});
				if(count!=0){
					return false;
				}
			}
			return true;
		});

		jQuery.validator.addMethod("isLandPermitRepeat", function(value, element){
			var landPermit=$('#estateInformation-landPermit').val();
			var count;
			if (!(landPermit==null||landPermit=="")){
				$.ajax({
					async: false,
					url: "${path }/baseinfo/estateInformationManage/getExistedLandPermitCount.action",
					data:{
						"ownerOrg.id":$("#organizationId").val(),
						"estateInformation.id":$("#estateInformation-id").val(),
						"mode":$("#mode").val(),
						"estateInformation.landPermit":landPermit
					},
					success:function(responseData){
						count= responseData;
					}
				});
				if(count!=0){
					return false;
				}
			}
			return true;
		});

		jQuery.validator.addMethod("isHasData", function(value, element){
			if($('.inhabitant-notnull').length==0)
				return false;                
			return true;
		});
		
		
		$("#search_idCardNo").searchInhabitantAutocomplete({
			select : function(event, ui){
			    var flag=true;
			    $(".inhabitant-notnull").each(function(index) { 
				    if($(this).val()==ui.item.id){
				    	 $("#search_idCardNo").dialogtip({
							content:"不能添加重复的房主人员",
							alignX:"right"
						 });
				    	 $("#search_idCardNo").poshytip('show');
		  		    	 $(".tip-error").bgiframe();
						 $(".tip-error").css("cursor","pointer");
						 $(".tip-error").click(function(){
							 var inputObj = $(document.getElementsByName($(this).find(".inputName").attr("inputName"))[0]);
							 if(inputObj.css("display")=="none"){
								 if(inputObj.next().css("display")!="none"){
									 inputObj = inputObj.next();
								 }else{
									 inputObj = inputObj.parent();
								 }
							 }
							 inputObj.attr("createMsg","false");
							 $(this).remove();
						 });
						 flag=false;
						 return false;
				    }
				});
				if(flag){
					var id=ui.item.id;
					var name=ui.item.name;
					var idCardNo=ui.item.idCardNo;
					var sHtml='<div>'
						+'<input type="hidden" name="ids" id="inhabitants-'+id+'" value="'+id+'" class="inhabitant-notnull"/>' 
						+'<div  class="grid_4 lable-right" >'
				  			+'<label class="form-lbl">姓名：</label>'
				  		+'</div>'
				  		+'<div class="grid_4">'
				  		    +'<input type="text" value="'+name+'" id="inhabitants-'+name+'" maxlength="20"'
						    +'readonly="readonly" class="form-txt" name="inhabitant_name" />'
				   		+'</div>'
				        +'<div  class="grid_4 lable-right" >'
				  			+'<label class="form-lbl">身份证号码：</label>'
				  		+'</div>'
						+'<div class="grid_6">'	
						    +'<input type="text" value="'+idCardNo+'" id="inhabitants-'+idCardNo+'" maxlength="18"'
						    +'readonly="readonly" class="form-txt" name="inhabitant_idCardNo" />'
				   		+'</div>'
				   		+'<div  class="grid_4 lable-right" >'
				  			+'<input type="button" value="删除" id="deleteInhabitant-'+id+'" class="defaultButton" />'
				  		+'</div>'
				  		+'<script type="text/javascript">'
				  		    +'$("#deleteInhabitant-'+id+'").click(function(){'
				  		        +'if($(".inhabitant-notnull").length==1 && $("#mode").val()=="edit"){'
				  		            +'$("#deleteInhabitant-'+id+'").dialogtip({'
					  		            +'content:"至少输入一条房主信息，不能删除",'
					  		            +'alignX:"center"'
				  		        	+'});'
					  		        +'$("#deleteInhabitant-'+id+'").poshytip("show");'
					  		        +'$(".tip-error").bgiframe();'
					  		        +'$(".tip-error").css("cursor","pointer");'
					  		        +'$(".tip-error").click(function(){'
					  		             +'var inputObj = $(document.getElementsByName($(this).find(".inputName").attr("inputName"))[0]);'
					  		             +'if(inputObj.css("display")=="none"){'
					  		                 +'if(inputObj.next().css("display")!="none"){'
					  		                     +'inputObj = inputObj.next();'
					  		                 +'}else{'
					  		                     +'inputObj = inputObj.parent();'
					  		                 +'}'
					  		             +'}'
					  		             +'inputObj.attr("createMsg","false");'
					  		             +'$(this).remove();'
					  		         +'});'
				  		       	+'}else{'
				  		       		+'$(this).parent().parent().remove();'
				  		      	+'}'
				  		    +'});'
		  		    	+'<'+'/script>'
						+'<div class="clearLine">&nbsp;</div>'
					+'</div>';
					$("#inhabitants").append(sHtml);
				}
			}
		});
		
		<s:if test='"view".equals(mode)'>
			$("input[type='checkbox']").click(function(){return false});
		</s:if>
		<s:if test='!"view".equals(mode)'>
	        
			$("#maintainForm").formValidate({
				promptPosition: "bottomLeft",
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						success:function(data){
							if(!data.id){
			               	 	$.messageBox({
									message:data,
									level: "error"							
							 	});
			                	return;
			                }
			                data["organization.orgName"]=data.organization.orgName;
			   	   		 	<s:if test='"add".equals(mode) || "copy".equals(mode) '>
						    	$("#estateInformationList").addRowData(data.id,data,"first");
						    	$.messageBox({message:"已经成功保存该房产信息!"});
						     </s:if>
						     <s:if test='"edit".equals(mode)'>
						        $("#estateInformationList").setRowData(data.id,data);
							    $.messageBox({message:"已经成功修改该房产信息!"});
						     </s:if>
						    $("#estateInformationDialog").dialog("close");
						    $("#estateInformationList").setSelection(data.id);
						} 
					});
					return false;
				},
				rules:{
					"estateInformation.proprietaryNo":{
						exculdeParticalChar:true,
						isProprietaryNoRepeat:true
					},
					"estateInformation.landPermit":{
						exculdeParticalChar:true,
						isLandPermitRepeat:true
					},
					"atLeastOneData":{
						isHasData:true
					},
					"estateInformation.coveredArea":{
						number:true,
						min:0,
						max:9999999.99
					},
					"estateInformation.floorArea":{
						number:true,
						min:0,
						max:9999999.99
					},
					"estateInformation.occupiedNo":{
						exculdeParticalChar:true
					}
				},
				messages:{
					"estateInformation.proprietaryNo":{
					    exculdeParticalChar:"房产证号只能输入字母，数字和中文字符",
					    isProprietaryNoRepeat:"房产证号重复请重新输入"
				    },
				    "estateInformation.landPermit":{
				    	exculdeParticalChar:"土地证号只能输入字母，数字和中文字符",
						isLandPermitRepeat:"土地证号重复请重新输入"
					},
					"atLeastOneData":{
						isHasData:"请添加一条房主信息"
					},
					"estateInformation.coveredArea":{
						number:"请输入正确的数字",
						min: "建筑面积需要输入正数",
						max:"请输入一个小于：9999999.99的值"
					},
					"estateInformation.floorArea":{
						number:"请输入正确的数字",
						min: "占地面积需要输入正数",
						max:"请输入一个小于：9999999.99的值"
					},
					"estateInformation.occupiedNo":{
						exculdeParticalChar:"出租房房产证号只能输入字母，数字和中文字符"
					}
				}
			});
			<s:if test='"add".equals(mode)'>
			$("#maintainForm").attr("action","${path}/baseinfo/estateInformationManage/addEstateInformation.action");
			</s:if>
			<s:else>
			$("#maintainForm").attr("action","${path}/baseinfo/estateInformationManage/updateEstateInformation.action");
			</s:else>
			</s:if>
			$.ajax({
				async: false,
				url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id":$("#organizationId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
	})
</script>