<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>


<div id="dialog-form" title="出租房信息维护" class="container container_24">
	<div id="tabs" style="height:400px;">
		<ul>
			<li><a id="tabInfoA" href="#lettingHouse">出租房信息维护</a></li>       									                                                                
			<li id="personInCharge"><a href="${path }/baseinfo/helpPersonnel/lettinghouse/personInChargeList.jsp?keyType=${lettingHouseKeyType}&domainId=${lettingHouse.id}&width=700&height=240">治安负责人信息</a></li>
			<li id="floorpersons"><a href="${path }/baseinfo/helpPersonnel/lettinghouse/floorpersonsList.jsp?keyType=${lettingHouseKeyType}&domainId=${lettingHouse.id}&width=700&height=240">巡场情况信息</a></li>
		</ul>
			<div id="lettingHouse" class="container container_24">
				<s:if test='!"view".equals(mode)'>
				 <form id="maintainForm" method="post"	action="" >
				</s:if>
				<div id="perUuid"></div>
				<input id="lettingHouseKeyType"	type="hidden" name="lettingHouseKeyType" value="${lettingHouseKeyType}" />
				<input id="lettingHouseTypeName" type="hidden" name="lettingHouseTypeName" value="${lettingHouseTypeName}">
				<input id="mode" type="hidden" name="mode" value="${mode}" /> 
				<input id="modeType" type="hidden" name="modeType" value="${modeType}" /> 
				<input id="organizationId"	type="hidden" name="organizationId" value="${lettingHouse.organization.id}" /> 
				<input id="orgId" type="hidden" name="lettingHouse.organization.id" value="${lettingHouse.organization.id}" />
				<input id="lettingHouseId"	type="hidden" name="lettingHouse.id" value="${lettingHouse.id}" /> 
				<input name="isSubmit" id="isSubmit" type="hidden" value="">
				
	            <div  class="grid_3 lable-right">
	                <label class="form-lbl">所属网格：</label>
	            </div>
	            <div class="grid_10">  
		            <input type="text" name="lettingHouse.organization.orgName" id="orgName" class="form-txt" value="" readonly="readonly" 
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>/> 
	            </div>
	            <div class="grid_1">
	                <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	            </div>
	            <div class='clearLine'>&nbsp;</div>
	            <div  class="grid_3 lable-right">
	                <label class="form-lbl">房东姓名：</label>
	            </div>
	            <div class="grid_4">     
	                <input type="text" name="lettingHouse.name" id="name" maxlength="20"
	                    <s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.name}"
	                    class="form-txt" />
	            </div>
	            <div class="grid_1">
	                <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	            </div>
	            <div  class="grid_3 lable-right">
	                <label class="form-lbl">身份证号码：</label>
	            </div>
	            <div class="grid_5">
	                <input type="text" name="lettingHouse.idCardNo" id="idCardNo" maxlength="18"
	                    <s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.idCardNo}"
	                    class="form-txt dialogtip" title="请输入15-18位由数字或字母X组成的身份证号码"/>
	            </div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">登记日期：</label>
		  		</div>
				<div class="grid_4">	 
				    <input type="text" name="lettingHouse.registDate" id="registDate" maxlength="20"
		  				readonly <s:if test='"view".equals(mode)'>disabled='true'</s:if> value='<s:date name="lettingHouse.registDate" format="yyyy-MM-dd" />'
		  				class="form-txt" />
	    		</div>
	    		
	    		<div class='clearLine'>&nbsp;</div>
	    		<div  class="grid_3 lable-right">
	                <label class="form-lbl">编号：</label>
	            </div>
	            <div class="grid_4">     
	                <input type="text" name="lettingHouse.lettingHouseNo" id="lettingHouseNo" maxlength="20"
	                    <s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.lettingHouseNo}"
	                    class="form-txt" />
	            </div>
	    		<div  class="grid_4 lable-right">
		  			<label class="form-lbl">联系电话：</label>
		  		</div>
				<div class="grid_5">
				    <input type="text" name="lettingHouse.telephone" id="telephone" maxlength="20"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.telephone}"
		  				class="form-txt dialogtip" title="请输入由数字和-组成的联系电话（例如：0577-88888888）"/>
	    		</div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">联系手机：</label>
		  		</div>
				<div class="grid_4" >
				    <input type="text" name="lettingHouse.mobileNumber" id="mobileNumber" maxlength="11"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value='${lettingHouse.mobileNumber}' class="form-txt dialogtip" title="请输入11位以1开头的联系手机  例如：13988888888"/>
	    		</div>
	    		<div class='clearLine'>&nbsp;</div>
	    		<div  class="grid_3 lable-right">
	                <label class="form-lbl">房东地址：</label>
	            </div>
	            <div class="grid_20">
	               <input type="text" style="width:99.5%" name="lettingHouse.landlordAddr" id="landlordAddr" maxlength="50"
	                    <s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.landlordAddr}"
	                    class="form-txt" />
	            </div>
	            <div class='clearLine'>&nbsp;</div>
	    		<hr width="93%">
	    		<div class='clearLine'>&nbsp;</div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">出租房地址：</label>
		  		</div>
				<div class="grid_20" >
				    <input type="text" style="width:99.5%" name="lettingHouse.lettingHouseAddr" id="lettingHouseAddr"  maxlength="50"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.lettingHouseAddr}"
		  				class="form-txt" />
	    		</div>
	    		<div class="grid_1">
		  			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		  		</div>
	    		<div class='clearLine'>&nbsp;</div>
	    		<div  class="grid_3 lable-right">
	                <label class="form-lbl">房间数：</label>
	            </div>
	            <div class="grid_4">
	                <input type="text" name="lettingHouse.roomNumbers" id="roomNumbers" maxlength="6"
	                    <s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.roomNumbers}"
	                    class="form-txt"/>
	            </div>
	            <div class="grid_1">
	            </div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">出租房面积：</label>
		  		</div>
				<div class="grid_5 heightAuto">
					<input type="text" name="lettingHouse.lettingHouseAreas" id="lettingHouseAreas" maxlength="8"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.lettingHouseAreas}"
		  				class="form-txt" style="width:85%;" /><font size="1">M</font><font size="1"><sup>2</sup></font> 
	    		</div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">限住人数：</label>
		  		</div>
				<div class="grid_4">
				    <input type="text" name="lettingHouse.limitPersons" id="limitPersons" maxlength="6"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.limitPersons}"
		  				class="form-txt" />
	    		</div>
	            <div class='clearLine'>&nbsp;</div>
	    		<div  class="grid_3 lable-right">
		  			<label class="form-lbl">实住人数：</label>
		  		</div>
				<div class="grid_4">
				    <input type="text" name="lettingHouse.realityPersons" id="realityPersons" maxlength="6"
		  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="${lettingHouse.realityPersons}"
		  				class="form-txt" />
	    		</div>
	            <div class="grid_1">
	            </div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">出租房结构：</label>
		  		</div>
				<div class="grid_5">	 
				    <select name="lettingHouse.lettingHouseStruts.id" id="lettingHouseStruts" class="form-select dialogtip" 
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> title="<b>混合结构：</b>钢筋混凝土和砖木构造（例如：一幢房屋的梁是用钢筋混凝土制成，以砖墙为承重墙，或者梁是用木材建造，柱是用钢筋混凝土建造）。</br>
	<b>钢、钢筋混凝土结构：</b>承重的主要构件是用钢、钢筋混凝土建造。</br><b>钢筋混凝土结构：</b>配有钢筋增强的混凝土制成的结构，承重的主要构件是用钢筋混凝土建造。</br><b>简易棚：</b>简易的棚子或帐篷（例如：临时搭建的工棚）。</br>">
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS"
				   		 defaultValue="${lettingHouse.lettingHouseStruts.id}" />
					</select>
	    		</div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">出租房类别：</label>
		  		</div>
				<div class="grid_4">	 
				    <select name="lettingHouse.type.id" id="type" class="form-select dialogtip"  
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> title="<b>通天房：</b>楼梯在房子内部，容易引起火灾的自建居民房">
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE"
				   		 defaultValue="${lettingHouse.type.id}" />
					</select>
	    		</div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">出租房性质：</label>
		  		</div>
				<div class="grid_4">	 
				    <select name="lettingHouse.lettingHouseProperty.id" id="lettingHouseProperty" class="form-select"  
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_PROPERTY"
				   		 defaultValue="${lettingHouse.lettingHouseProperty.id}" />
					</select>
	    		</div>
	            <div class="grid_1">
	            </div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">出租用途：</label>
		  		</div>
				<div class="grid_5">	 
				    <select name="lettingHouse.usage.id" id="usage" class="form-select" 
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE"
				   		 defaultValue="${lettingHouse.usage.id}" />
					</select>
	    		</div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">隐患程度：</label>
		  		</div>
				<div class="grid_4">
				    <select name="lettingHouse.hiddenTroubleLevel.id" id="hiddenTroubleLevel" class="form-select" 
					<s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL"
				   		 defaultValue="${lettingHouse.hiddenTroubleLevel.id}" />
					</select>
	    		</div>
	    		<div class="grid_1">
		  			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		  		</div>
	    		<div class='clearLine'>&nbsp;</div>
	    		<div class="grid_3 lable-right">
		  			<label class="form-lbl">隐患情况：</label>
		  		</div>
				<div style="display:inline;float:left;height:100px;line-height:100px;width:82%;">
					<textarea style="height:85px;" name="lettingHouse.hiddenRectification" class="form-select" id ="lettingHouse-hiddenRectification"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> >${lettingHouse.hiddenRectification}</textarea>
	    		</div>
	    	
	    	<input type="hidden" name="lettingHouse.orgInternalCode" id="orgInternalCode"
	    		value="${lettingHouse.orgInternalCode}">
	   	<s:if test='!"view".equals(mode)'>
		   </form>
		</s:if>
	</div>
	</div>
</div>
<script type="text/javascript">
var lettingHouseOrgTree="";
function isGridForTreeSelect(){
	if(lettingHouseOrgTree != ""){
		return $.getSelectedNode(lettingHouseOrgTree).attributes.orgLevelInternalId == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}else{
		return true;
	}
}
jQuery.validator.addMethod("isGridOrganization", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	if(isGridForTreeSelect()){
		return true;
	}else{
		return false;
	}
});

<s:if test='#parameters.dialog != null'>
	lettingHouseOrgTree = $("#orgName").treeSelect({
		inputName:"lettingHouse.organization.id",
		url:"/sysadmin/orgManage/loadTownForExtTree.action",
		onSelect:changeOrgId
	});
	$.setTreeValue($("#orgId").val(),lettingHouseOrgTree);
</s:if>
function changeOrgId(){
$("#organizationId").val($("#orgId").val());
}
$(document).ready(function(){
	$("#tabs").tabFunction({ cache: true });
	if($("#lettingHouseId").val()==null || $("#lettingHouseId").val()==""){
		$("#floorpersons").hide();
	}
	if(!ispersonInCharge()){
		$("#floorpersons").hide();
	}
	$.ajax({
		async: false,   
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#orgId").val()
		},
		success:function(responseData){
			$("#orgName").val(responseData);
		}
	});
	$(".dialogtip").inputtip();
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				"lettingHouse.lettingHouseNo":{
					exculdeParticalChar:true
				},
				"lettingHouse.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
				},
				"lettingHouse.lettingHouseAddr":{
					required:true
				},
				"lettingHouse.lettingHouseAreas":{
					number:true,
					min:0,
					max:999999
				},
				"lettingHouse.telephone":{
					telephone:true
				},
				"lettingHouse.roomNumbers":{
					 digits:true,
					 min:0
				},
				"lettingHouse.limitPersons":{
					 digits:true,
					 min:0
				},
				"lettingHouse.realityPersons":{
					digits:true,
					min:0
				},
				"lettingHouse.idCardNo":{
					idCard:true
				},
				"lettingHouse.mobileNumber":{
					mobile:true
				},
				"lettingHouse.hiddenRectification":{
					maxlength:200
				},
				"lettingHouse.hiddenTroubleLevel.id":{
					required:true
				},
				"lettingHouse.organization.orgName":{
					isGridOrganization:true
				}
			},
			messages:{
				"lettingHouse.lettingHouseNo":{
					exculdeParticalChar:"编号不能有特殊字符"
				},
				"lettingHouse.name":{
					required:"请输入房东姓名",
					exculdeParticalChar:"房东姓名不能有特殊字符",
					minlength:$.format("房东姓名最少需要输入{0}个字符"),
					maxlength:$.format("房东姓名最多只能输入{0}个字符")
				},
				"lettingHouse.lettingHouseAddr":{
					required:"请输入出租房地址"
				},
				"lettingHouse.lettingHouseAreas":{
					number:"出租房面积只能输入数字",
					min:$.format("请输入一个大于 {0} 的值"),
					max:$.format("请输入一个小于 {0} 的值")
				},
				"lettingHouse.telephone":{
					telephone:"电话号码格式不对"
				},
				"lettingHouse.roomNumbers":{
					 digits:"房间数只能输入整数",
					 min:$.format("请输入一个大于 {0} 的值")
				},
				"lettingHouse.limitPersons":{
					 digits:"限住人数只能输入整数",
					 min:$.format("请输入一个大于 {0} 的值")
				},
				"lettingHouse.realityPersons":{
					 digits:"实住人数只能输入整数",
					 min:$.format("请输入一个大于 {0} 的值")
				},
				"lettingHouse.idCardNo":{
					idCard:"请输入正确的身份证号码！"
				},
				"lettingHouse.mobileNumber":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				},
				"lettingHouse.hiddenRectification":{
					maxlength:$.format("隐患情况最多只能输入{0}个字符")
				},
				"lettingHouse.hiddenTroubleLevel.id":{
					required:"隐患程度必须输入 "
				},
				"lettingHouse.organization.orgName":{
					isGridOrganization:"所属网格只能选择片组片格"
                }
			}
			});

		$('#registDate').datePicker({
			yearRange: '1990:2040',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d'
	     });

	</s:if>
		 
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/lettingHouseManage/addLettingHouse.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/baseinfo/lettingHouseManage/updateLettingHouse.action");
	</s:if>
	<s:if test='"add_path".equals(modeType)'>
	$("#orgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	</s:if>
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
                if(!data.id){
               	 $.messageBox({
						message:data,
						level: "error"							
					 });
                	return;
                }
             data["organization.orgName"]=data.organization.orgName;
   	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
				<s:if test='#parameters.dialog != null'>
					//提示
					$("#tagSerch").val($("#name").val());
        			$("#searchPositiveinfo").click();
					$.messageBox({message:"已经成功保存该出租房信息!"});
					$("#<s:property value='#parameters.dialog'/>").dialog("close");
				</s:if>
				<s:else>
					$("#lettingHouseList").addRowData(data.id,data,"first");
			    	$.messageBox({message:"已经成功保存该出租房信息!"});
			    	if($("#isSubmit").val()=="true"){
						$("#lettingHouseMaintanceDialog").dialog("close");
					}else{
<!--					当点击保存并继续是，清空标签页的数据	 			-->
<!--							 		$("#perUuid input").remove();-->
								    emptyObject();		    
									$("#perUuid").text("");
									$("#personInChargeList").jqGrid('clearGridData',false);	
					}	
				     $("#lettingHouseList").setSelection(data.id);
				     checkExport();
				</s:else>
			     </s:if>
			     <s:if test='"edit".equals(mode)'>
			        $("#lettingHouseList").setRowData(data.id,data);
				    $.messageBox({message:"已经成功修改该出租房信息!"});
				     $("#lettingHouseMaintanceDialog").dialog("close");
				     $("#lettingHouseList").setSelection(data.id);
				     checkExport();
			     </s:if>
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	      alert("提交错误");
 	   }
 	  });
}
function doNothing(){}
<s:if test='"add_path".equals(modeType)'>
function setZone(selectOrgId,selectOrgName){
		$("#orgId").val(selectOrgId);
		$("#orgName").val(selectOrgName);
	}
</s:if>

function ispersonInCharge(){
	var flag=false;
	$.ajax({
		async:false,
		url: "${path }/baseinfo/personInCharegeManage/getPersonInCharegeByIdAndPlace.action",
		data:{
			"placeId":$("#lettingHouseId").val(),
			"placeType":$("#lettingHouseKeyType").val()
		},
		success:function(responseData){
			if(responseData.length>0){
				flag=true;
			}
		}
	});
	return flag;
}

function emptyObject(){
	$("#lettingHouseId").val("");
	$("#name").val("");
	$("#idCardNo").val("");
	$("#registDate").val("");
	$("#lettingHouseNo").val("");
	$("#telephone").val("");
	$("#mobileNumber").val("");
	$("#landlordAddr").val("");
	$("#lettingHouseAddr").val("");
	$("#roomNumbers").val("");
	$("#lettingHouseAreas").val("");
	$("#limitPersons").val("");
	$("#realityPersons").val("");
	$("#lettingHouseStruts").val("");
	$("#type").val("");
	$("#lettingHouseProperty").val("");
	$("#usage").val("");
	$("#hiddenTroubleLevel").val("");
	$("#lettingHouse-hiddenRectification").val("");
}

</script>