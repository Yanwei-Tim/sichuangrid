<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="短信维护" class="container container_24">
	<form id="maintainForm" method="post"
		action="${path}/interaction/sms/outbox/sendSmsProcess.action">
		<input id="orgId" type="hidden" name="orgId" value="${user.organization.id}" />
		<input id="areaorgId" type="hidden" name="areaorgId" value="${user.organization.id}" />
		<div class="grid_24" style="height:420px;">
			<div class="grid_12">
				<div class="grid_4 lable-right" >
					<label>收件人：</label>
				</div>
				<div class="grid_20 heightAuto">
				     <input type="hidden" name="orgDatas" id="orgDatas"/>
					<input type="text" id="smsReceiver" name="smsSendBox.receiver" class="form-txt" style="height:150px;" />
				</div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_4 lable-right" >
					<label>内容：</label>
				</div>
				<div class="grid_20">
					<textarea rows="20" class="form-txt" onkeyup="checkContentKeyUp();"
						id="smsContent" name="smsSendBox.smsContent" style="height:348px;"></textarea>
				</div>
			</div>
			<div class="grid_12">
				<div id="tabs">
					<ul>
	<!-- 				    <li><a href="#areaContacters">联系区域</a></li> -->
						<li><a href="#workContacters">站内联系人</a></li>
						<li><a href="#myContacters">我的联系人</a></li>
					</ul>
					<div id="areaContacters" style="height: 362px; display: none;">
						<div class="grid_24">
							<div class="grid_4">
								<label>部门</label>
							</div>
				  			<div class="grid_20">
								<input type="text" id="areaOrgTree" name="orgName"/>
			    			</div>
				  			
			    		</div>
			    		<div class="grid_24">
			    		      <div class="grid_6">
								<input type="checkbox" id="all" name=""  value="层"/> 全部
							  </div>
							  <div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="省" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/> class="org"/> 省级
								  </div>
							  <div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="市" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/> class="org"/> 市级
								  </div>
							  <div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="区县" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/> class="org"/> 区县级
								</div>
			    		</div>
			    		<div class="grid_24">
			    		<div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="乡镇街道" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>  class="org"/> 乡镇街道级
								</div>
								<div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="村社区" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/> class="org"/> 村社区级
								</div>
								<div class="grid_6">
								<input type="checkbox"  name="" onclick="reloadArea(this)" value="网格" id=<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/> class="org"/> 网格层级
								</div>
			    		</div>
				    	<div class='clearLine'></div>
				    	<hr> 
				    	<div class='clearLine'></div>
						<div id="areaContactersList" style="height:220px;overflow: auto;" >
							<ul id="">
							</ul>
						</div>
						<hr>
						<div>
				    	 不选择的部门:
				    	 <input type="text"/>
				    	 <input type="button"  style="float: right;width: 50px" value="详细" onclick="detail()"/>
				    	</div>
					</div>
					<div id="workContacters" style="height: 362px; overflow: auto;">
						<div class="grid_24">
							<div class="grid_4">
								<label>部门</label>
							</div>
				  			<div class="grid_20">
								<input type="text" id="orgTree" name="orgName"/>
			    			</div>
			    		</div>
				    	<div class='clearLine'></div>
				    	<hr> 
				    	<div class='clearLine'></div>
						<div id="workContacterList">
							<ul id="ul_list">
							</ul>
						</div>
					</div>
					<div id="myContacters" style="height: 362px; overflow: auto;">
						<s:if test="myContacters.size()>0">
							<div>
								<div class="contacterGroup">
									<span class="ui-icon ui-icon-triangle-1-s" style="float: left;"></span>其他联系人
									(<s:property value="myContacters.size()" />)
								</div>
								<ul>
									<s:iterator value="myContacters">
										<li title="${mobileNumber}">
											<input type="checkbox" id="contacter_${id}" value="${id}" class="${name}" 
												onclick="setReceiver('contacter_${id}')"> 
											<label for="contacter_${id}">${name}</label>
										</li>
									</s:iterator>
								</ul>
							</div>
						</s:if>					
						<s:if test="myGroups.size()>0">
							<div>
								<div class="contacterGroup">
									<span class="ui-icon ui-icon-triangle-1-s" style="float: left;"></span>我的群组
									(<s:property value="myGroups.size()" />)
								</div>
								<ul>
									<s:iterator value="myGroups">
										<li title="${mobileNumber}" style="padding-left: 30px;">
											<input type="checkbox" id="contacter_${id}" value="${id}" class="${name}" 
												onclick="setReceiver('contacter_${id}')"> 
											<label for="contacter_${id}">${name}</label>
										</li>
									</s:iterator>
								</ul>
							</div>
						</s:if>
						<s:if test="myGroups.size()<=0 || myGroups.size()<=0">
						<font color="red">暂无联系人</font>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_24" style="text-indent:50px;background-image: "><label id="contentMsg"></label></div>
	</form>
</div>
<script type="text/javascript">
var tree=$("#orgTree").treeSelect({
	allOrg:true,
	inputName:"orgId",
	onSelect:reloadWorkContacters
});
var areaTree=$("#areaOrgTree").treeSelect({
	allOrg:true,
	inputName:"areaorgId",
	onSelect:checkBoxState
});
function reloadArea(object){
	    isCheckedAll($(object).attr("checked"));
		if($(object).attr("checked")){
		   $(object).data($("#areaorgId").val(),true);
		   $("#areaContactersList>ul").append(
				'<li style="padding-left: 10px;">' +
				'<div id="'+$(object).attr("id")+'-'+$("#areaorgId").val()+'"> <label>'+$("#areaOrgTree").val()+' 下的全部 '+$(object).val()+'级的用户</label><a class="delbutton" href="javascript:void(0)" onclick="del(\''+$(object).attr('id')+'\',\''+$("#areaorgId").val()+'\')"/></div></li>')
		}else{
			del($(object).attr("id"),$("#areaorgId").val());
		}
}
function del(id,orgId){
	$("#"+id).data(orgId,false);
	$("#"+id+"-"+orgId).parent().remove();
	if($("#areaorgId").val()==orgId){
	 $("#"+id).attr("checked",false);
	 if(id!="all"){
		 $("#all").attr("checked",false);
	 }
	}
	 $("#all").data(orgId,false);
}
function isCheckedAll(checked){
	if(checked){
		var level=$.getSelectedNode(areaTree).attributes.orgLevelInternalId;
		var flag=true;
		$(".org").each(function(){
			 if($(this).attr("id")<level){
			 	if($(this).attr("checked")!="checked"){
				 	flag=false;
				 	return;
			  	}
			 }
		})
		if(flag){
			$("#all").attr("checked",true);
			$("#all").data($("#areaorgId").val(),true);
	     }
	}else{
		$("#all").attr("checked",false);
		$("#all").data($("#areaorgId").val(),false);
	}
}
function checkBoxState(){
	var level=$.getSelectedNode(areaTree).attributes.orgLevelInternalId;
    $(".org").each(function(){
	   if($(this).attr("id")>=level){
		   $(this).attr("disabled",true);
		}else{
			$(this).attr("disabled",false);
			}
	   $(this).attr("checked",$(this).data($("#areaorgId").val())?true:false);
    })
    if(level==<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>){
    	 $("#all").attr("disabled",true);
      }else{
    	   $("#all").attr("disabled",false);
           $("#all").attr("checked",$("#all").data($("#areaorgId").val())?true:false);
     }
}
//$.setTreeValue(${orgId},tree); 
function reloadWorkContacters(){
	$.ajax({
		async:false,
		url:"${path}/interaction/sms/outbox/findWorkContacters.action",
		data:{
			"orgId":$("#orgId").val()
		},
		success:function(data){
			loadWorkContacterList(data);
			return ;
		}
	});
}
function loadWorkContacterList(data){
	$("#workContacterList>ul").empty();
	if(data==null||data==''){
		return ;
	}
	if(data.length>0){
		$("#workContacterList>ul").append('<li >' +
				'<input type="checkbox" onclick="selectAll(this)" id="'+data[0].id+'all"> <label>全选</label></li>'
				)
		$("#"+data[0].id+"all").attr("checked",$("#workContacterList").data(data[0].id+"all"));
		for(var i=0;i<data.length;i++){
			var exist=$.inArray(data[i].name+'-'+data[i].id,contacters());
			$("#workContacterList>ul").append(
				'<li style="padding-left: 10px;">' +
				'<input type="checkbox" onclick="setReceiver(\'contacter_'+data[i].id
				+'\')" class="'+data[i].name+'" value="'+data[i].id+'" id="contacter_'
				+data[i].id+'"> <label>'+data[i].name+'</label></li>'
			)	
			if(exist>=0){ 
				$('#contacter_'+data[i].id+'').attr("checked",true);
			}
		}
		checkedSelectAll();
	}else{
		return ;
	}
}
function selectAll(object){
	var checked=$(object).attr("checked");
	$("#workContacterList").data($(object).attr("id"),checked?true:false);
	$("#ul_list li input[id!='"+$(object).attr("id")+"']").each(function(){
		if(checked){
			if($(this).attr("checked")==undefined){
				 $(this).attr("checked",true);
				  setReceiver($(this).attr("id"));
				}
		}else{
				if($(this).attr("checked")){
					 $(this).attr("checked",false);
					  setReceiver($(this).attr("id"));
				}
			}
	});
}
//每回点击判断全选按钮是否应该被选中
function checkedSelectAll(){
	var checked=true;
	$("#ul_list li:gt(0) input").each(function(){
		if($(this).attr("checked")==undefined){
			checked=false;
				  return;
		}
	});
	if(checked){
	$("#ul_list li:first input").attr("checked",true);
	$("#workContacterList").data($("#ul_list li:first input").attr("id"),true);
	}else{
		$("#ul_list li:first input").attr("checked",false);
		$("#workContacterList").data($("#ul_list li:first input").attr("id"),false);
		}
}

function contacters(){
	var contacter = $("#smsReceiver").val();
	var contacterList = contacter.split(",");
//	if(contacterList.length==1){
//		return contacter[0]=contacter;
//	} else {
		return contacterList;
//	}
}
function setReceiver(id) {
	if ($("#" + id).attr("checked")) {
		checkedSelectAll();
		$("#smsReceiver").setPersonnelCompleteVal({
			value : $("#" + id).attr("class")+"-"+$("#" + id).val(),
			label : $("#" + id).attr("class"),
			desc : ""
		});
	}else{
		$("#ul_list li:first input").attr("checked",false);
		$("#workContacterList").data($("#ul_list li:first input").attr("id"),false);
		$("#smsReceiver").removePersonnelCompeleteValue($("#" + id).attr("class")+"-"+$("#" + id).val());
	}
}

function contacter(data){
	if(data==null||data==''){
		return ;
	}else{
		var index = data.indexOf('-');
		var id = data.substring(index+1);
		var contacter = "contacter_"+data.substring(index+1);
		return contacter;
	}
}
function checkContentKeyUp(){  
	var content=document.getElementById('smsContent');  
	var length=content.value.length;  
	var smsItem=0;  
	if(length%70==0){
		smsItem=length/70;
	}else{
		smsItem=(parseInt(length/70))+1;
	}
	var contentMsg = document.getElementById('contentMsg');  
	contentMsg.innerHTML = "一条短信最大长度为70个字,您已输入<font color='red'>"  
		                + length  
		                + "</font>字，<font color='red'>将以"
		                + smsItem
		                + "条短信的形式发送</font>";  
}
$(document).ready(function(){
	var i = 0 ;
	tree.getLoader().on("load" , function(){
		if(i == 0){
			$.ajax({
				async:false,
				url:"${path}/interaction/sms/outbox/findWorkContacters.action",
				data:{
					"orgId":tree.root.firstChild.id
				},
				success:function(data){
					loadWorkContacterList(data);
					return ;
				}
			});
			i ++;
		}
	});

	
		$( "#tabs" ).tabs();
	$(".contacterGroup").click(function(event){
		var uiSpan=$(this).find("span:first");
		if(uiSpan.hasClass("ui-icon-triangle-1-s")){
			//open ->close;
			uiSpan.removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
			$(this).parent().find("ul").hide();
		}else{
			//close ->open;
			uiSpan.removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
			$(this).parent().find("ul").show();
		}
	}).css('cursor','pointer').click();
	$("#smsReceiver").personnelComplete({
		url: "${path}/interaction/sms/outbox/searchContacterForAutoComplete.action",
		multiple: true,
		postDataFunction: function(){
			return {orgId:''};
		},
		select: function(data){
			$("#" + contacter(data)).attr("checked",true);
			checkedSelectAll();
		},
		itemClose: function(data){
			$("#" + contacter(data)).attr("checked",false);
			checkedSelectAll();
			
		}
	});
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft", 
		submitHandler: function(form){
		$("#orgDatas").val(constructsDatas());
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							message:data,
							evel: "error"							
			 			});
            			return;
					}
					 $("#smsSendList").addRowData(data.id,data,"first");
					 $.messageBox({message:"成功发送短信!"});
				     $("#smsSendDialog").dialog("close");
				     $("#smsSendList").setSelection(data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"smsSendBox.receiver":{
				required:true
			},
			"smsSendBox.smsContent":{
				required:true,
				minlength:2,
				maxlength:210
			}
		},
		messages:{
			"smsSendBox.receiver":{
				required:"请输入收件人"
			},
			"smsSendBox.smsContent":{
				required:"请输入短信内容",
				minlength:$.format("短信内容至少需要输入{0}个字符"),
				maxlength:$.format("短信内容最多只能输入{0}个字符")
			}
		}
	});

	$("#all").click(function(){
		var level=$.getSelectedNode(areaTree).attributes.orgLevelInternalId;
		var checked=$(this).attr("checked");
		$(this).data($("#areaorgId").val(),checked?true:false);
		 $(".org").each(function(){
			   if($(this).attr("id")<level){
				   if(checked){
				     if($(this).attr("checked")!="checked"){
				     	 $(this).attr("checked",true);
				     	 reloadArea(this);
				     }
				   }else{
					   if($(this).attr("checked")){
					     	 $(this).attr("checked",false);
					     	 reloadArea(this);
					     }
					   }
				}
		    })
		})
});

function detail(){
	$.createDialog({
		id:'detailDialog',
		width:700,
		height:480,
		title:'详细',
		url:'${path}/interaction/sms/outbox/findAreaContacters.action?orgDatas='+constructsDatas(),
		buttons: {
			"关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function constructsDatas(){
	var orgDatas="";
	$("#areaContactersList>ul div").each(function(){
		orgDatas=orgDatas+","+$(this).attr("id");
		})
	return orgDatas.substr(1);
	}
</script>