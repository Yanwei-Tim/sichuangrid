<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

		<div class="container container_24">
			<s:if test='"add".equals(mode)'>
				<form action="${path}/incident/systemManage/addIncidentType.action" method="post" id="maintainForm">
			</s:if>
			<s:elseif test='"edit".equals(mode)'>
				<form action="${path}/incident/systemManage/updateIncidentType.action" method="post" id="maintainForm">
			</s:elseif>
				<input type="hidden" name="mode" id="mode" value="${mode}" />
				<input type="hidden" name="incidentType.subjection.id" id="incidentTypeSubjectionId" value="${incidentType.subjection.id}" />
				<input type="hidden" name="incidentType.id" id="incidentTypeId" value="${incidentType.id}" />
				
		<div class="system_management_add">
			<div class="system_event">
				<strong class="eventName">事件名称:</strong>
				<input type="text"  name="incidentType.name"   id="incidentTypeName"   value="${incidentType.name}" class="eventDefinetTitle"//>
	 		</div>
		
			<div class="system_event">
				<strong class="eventName">事件定义:</strong> 
				<textarea name="incidentType.description" id="incidentTypeDescription" class="eventDefinetTitle eventDefine">${incidentType.description}</textarea>
			</div>
			<div class="system_grade">
				<label> 
					<input type="checkbox" name="incidentType.degreed" id="degreed" value="true" <s:if test='true==incidentType.degreed'>checked="checked"</s:if> /> 是否分级
				</label>
			</div>
			
			<div class="system_baseGrade system_baseGrade_add">
				<div class="baseGradeMain_add"  id="detailcontainer">
				<h3 class="baseGradeTitle">分级依据:</h3>
					<div class="gradeS">
						<h3 class="title title_I">I级(特别重大)<span class="Gradestar Gradestar_I"></span></h3>
						<div class="content">
							<textarea class="inputTextarea"  name="incidentType.degreeRule.rule" id="incidentTypeDegreeRule" >${incidentType.degreeRule[0].rule}</textarea>
						</div>
					</div>
					<div class="gradeS">
						<h3 class="title title_II">II级(重大)<span class="Gradestar Gradestar_II"></span></h3>
						<div class="content">
							<textarea class="inputTextarea" name="incidentType.degreeRule.rule" id="incidentTypeDegreeRule">${incidentType.degreeRule[1].rule}</textarea>
						</div>
					</div>
					<div class="gradeS">
						<h3 class="title title_III">III级(较大)<span class="Gradestar Gradestar_III"></span></h3>
						<div class="content">
							<textarea class="inputTextarea" name="incidentType.degreeRule.rule" id="incidentTypeDegreeRule" >${incidentType.degreeRule[2].rule}</textarea>
						</div>
					</div>
					<div class="gradeS">
						<h3 class="title title_V">IV级(一般)<span class="Gradestar Gradestar_V"></span></h3>
						<div class="content">
							<textarea class="inputTextarea" name="incidentType.degreeRule.rule" id="incidentTypeDegreeRule" >${incidentType.degreeRule[3].rule}</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
   	</form>
</div>
<script type="text/javascript">
	$(function(){
		ifshowDegreeRule();
	});

	if('add'=='${mode}'){
		$("#incidentTypeSubjectionId").val(getPropertyDictId());
	}

	function ifshowDegreeRule(){
		if('${mode}'=='add'){
			$("#degreed").attr("checked",true);
			$("#detailcontainer").show();
		}else if('${mode}'=='edit'){
			if('${incidentType.degreed}'=='true'){
				$("#detailcontainer").show();
				$("#degreed").attr("checked",true);
			}else{
				$("#incidentTypeDialog").dialog({ position: 'center' });
				$("#incidentTypeDialog").dialog( "option" , {height:300} );
				$("#degreed").attr("checked", false);
			 	$("#detailcontainer").hide();
			}
		}
	}
	
	$("#degreed").click(function(){
		if($("#degreed").attr("checked")){
			$("#incidentTypeDialog").dialog( "option" , {height:600});
			$("#detailcontainer").show();
		}else{
			$("#incidentTypeDialog").dialog( "option" , {height:300} );
			$("#detailcontainer").hide();
			}	
	});
	
	
	jQuery.validator.addMethod("Degreed", function(value, element){
		var boo = true;
		var outGone = document.getElementsByName("incidentType.degreed");
		for(var i = 0; i < outGone.length; i++){
			if(outGone[i].checked){
				boo=true;
			}
		}
		if(boo){
			if($("#degreeRule").val() == null || $("#degreeRule").val() == ""){
				return false;
			}
		}
		return true;
	});

	jQuery.validator.addMethod("exsitedIncidentType",function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:'/incident/systemManage/hasDuplicateIncidentType.action',
		   	data:{
		   		"incidentType.subjection.id":$('#incidentTypeSubjectionId').val(),
				"incidentType.name":$('#incidentTypeName').val(),
				"incidentType.id":$('#incidentTypeId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag
	});
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			formSubmit(form);
		},
		rules:{
			"incidentType.name":{
				required:true,
				exculdeParticalChar:true,
				exsitedIncidentType:true,
				maxlength:20
			},
			"incidentType.description":{
				required:true,
				maxlength:500
			}
		},
		messages:{
			"incidentType.name":{
				required:"请输入事件名称",
				exculdeParticalChar:"事件名称只能输入字母，数字和中文字符",
				exsitedIncidentType:"该分类下已存在相同的名称",
				maxlength:$.format("事件名称最多只能输入{0}个字符")
			},
			"incidentType.description":{
				required:"请输入事件描述",
				malength:$.format("事件描述最多只能输入{0}个字符")
			}
		}
	});

	function refershViewIncidentType(){
		cleanViewIncidentTypeDates();
 		 viewIncidentType();
		}

	
	function formSubmit(form){
	    $(form).ajaxSubmit({
	        success: function(data){
	    	if(!data.id){
		    	  $.messageBox({message : data,level : "error"});
				  return;
		     }
          	if("add"==$("#mode").val()){
				if(data.degreed){
					newNode = new Ext.tree.TreeNode({id:data.id, text:data.name, degreed:data.degreed, level:2, enableAddIncident:false, leaf:false, propertyDictId:data.subjection.id, cls:"file", icon:"${resource_path}/resource/external/ext/treeImages/funLeaf.gif"});
				}else{
					newNode = new Ext.tree.TreeNode({id:data.id, text:data.name, degreed:data.degreed, level:2, enableAddIncident:false, leaf:true, propertyDictId:data.subjection.id, cls:"file", icon:"${resource_path}/resource/external/ext/treeImages/funLeaf.gif"});
				}
				$.addNode(newNode, currentSelectNode());
          		$.messageBox({message:"新增预警类型成功!"});
	    		$("#incidentTypeDialog").dialog("close");
          	}else if("edit"==$("#mode").val()){
          		$.rename(tree,data.id,data.name);
          		refershViewIncidentType();
          		 $.messageBox({message:"修改预警类型成功!"});
                $("#incidentTypeDialog").dialog("close");
               
          	}
	  },
	 	   error: function(XMLHttpRequest, textStatus, errorThrown){
	 	      alert("提交数据时发生错误");
	 	   }
		});
	}
</script>