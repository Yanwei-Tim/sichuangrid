<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
	
<div id="dialog-form" title="我的群组联系人维护" class="container container_24">
	<c:if test='${mode!="view"}'>
	    <form id="myGroupHasContacter-Form" method="post" action="" >
	    	<pop:token />
	</c:if>
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input type="hidden" name="myGroup.id" value="${myGroup.id}" />

	<div class="grid_11">
		<div class="container container_24">
			<div style="clear:both"></div>
			<div class="grid_10 lable-right" id="belongOrgLabel" >
				<label >所属部门：</label>
			</div>
			<div class="grid_14"  id="belongOrgContext"><input type="text" id="orgName" width="200"/>
				<input type="hidden" name="orgId" id="orgId"/></div>
			<div style="clear:both"></div>
			
			<div class="grid_24 ui-widget-border" style="height:290px;overflow:auto;" id="contacterListContext">
				<div id="workContacterContent" class="propertyDomainList">
					<div class="contacterGroup">
						<span class="ui-icon ui-icon-triangle-1-e" style="float:left;margin-top:5px;"></span><span>平台内联系人()
						</span>
					</div>
					<ul>
						<li  title="" style="padding-left: 45px;"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="grid_3">
		<div class="container container_24" style="height:320px;">
			<div style="height:100px;"></div>
			<div class="grid_24" style="height:100px;">
				<input type="button" value=" 添加 > " id="addButton" class="defaultButton" disabled>
			</div>
			<div style="clear:both"></div>
			<div class="grid_24" style="height:100px;">
				<input type="button" value=" < 删除 " id="delButton" class="defaultButton" disabled>
			</div>
		</div>
	</div>
	
	<div class="grid_10">
		<div class="container container_24">
			<div class="grid_24">
				<label >已选联系人：</label>
			</div>
			<div style="clear:both"></div>
			<div class="grid_24 ui-widget-border" style="height:320px;overflow:auto;" >
				<ul id="hasSelectedContacter">
					
				</ul>
			</div>
		</div>
	</div>
	
<c:if test='${mode!="view"}'>
	</form>
</c:if>
</div>

<script type="text/javascript">
var provienceId;
$(document).ready(function(){
	provienceId = getprovienceOrgId();
	<c:if test='${mode!="view"}'>
	var tree=$("#orgName").treeSelect({
		inputName:"orgId",
		rootId:provienceId,
		allOrg:false,
		isFuncDep:true,
		onSelect:loadContacterList
	});
	$.addClick(tree , function(){
		loadContacterList();
	});
	$(".contacterGroup").click(function(event){
		var uiSpan=$(this).find("span:first");
		if(uiSpan.hasClass("ui-icon-triangle-1-s")){
			uiSpan.removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
			$(this).parent().find("ul").hide();
		}else{
			uiSpan.removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
			$(this).parent().find("ul").show();
		}
	}).css('cursor','pointer').click();

	$("#myGroupHasContacter-Form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			var idValues = $("input[name='contacterIds']").val();
			if(idValues == undefined || idValues == 'undefined'){
				$.messageBox({message:"请选择成员进行新增!",level:"warn"});
				return false ;
			}
			doAjaxSubmit();
		}
	});
	
	</c:if>
	<c:if test='${mode=="add"}'>
		$("#myGroupHasContacter-Form").attr("action","${path}/contact/myGroupManage/addMyGroupHasContacter.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
		$("#myGroupHasContacter-Form").attr("action","${path}/contact/myGroupManage/updateMyGroupHasContacter.action");
		<c:forEach items="${contacterVOs}" var="contacterVO">
			addSelectedContacter("${contacterVO.id}","${contacterVO.name}","${contacterVO.mobileNumber}");
		</c:forEach>
	</c:if>

	$("#addButton").click(function(){
		if($("#contacterListContext .select").length>0){
			var target=$("#contacterListContext .select:first");
			addSelectedContacter(target.attr("name"),target.text(),target.attr("title"));
			moveButtonOperator();
		}
	});	

	$("#delButton").click(function(){
		if($("#hasSelectedContacter .select").length>0){
			var target=$("#hasSelectedContacter .select:first");
			delSelectedContacter(target.attr("id"));
			moveButtonOperator();
		}
	});	

});
/**
 * 通过当前用户的ID去查询该用户省级网格ID
 */
function getprovienceOrgId(){
		$.ajax({
			url:"${path}/sysadmin/orgManage/getOrgProvinceByOrgId.action",
			async:false,
			success:function(data){
				rootId = eval(data);
			}
		});
	return rootId;
}

function loadContacterList(){
	var belongClass="workContact";
	var urlLink='${path}/contact/myGroupManage/loadContacterList.action?belongClass='+belongClass;
	if(belongClass!=null&& belongClass=='workContact'){
		urlLink = urlLink + "&orgId="+$("#orgId").val();
	}
	$.ajax({
		url:urlLink,
		success:function(data){
			loadWorkContactersList(data);
			return ;
        }
	});
}

function addSelectedContacter(id,name,mobileNumber){
	if($("#hasSelectedContacter li[id="+id+"]").length<1){
		var liTag=$("<li>").attr("id",id).text(name).attr("title",mobileNumber)
			.click(function(){
					$("#hasSelectedContacter li").removeClass("select").css("background","");
					$(this).addClass("select").css("background","#D4E4F6");
					moveButtonOperator();
				}).css("padding-left","35px").appendTo($("#hasSelectedContacter"));
		var inputTab=$("<input>").attr("type","checkbox").hide()
					.val(id).attr("name","contacterIds").attr("checked",true).appendTo(liTag);
		inputTab.attr("checked",true);
	}
}

function delSelectedContacter(id){
	$("#hasSelectedContacter li[id="+id+"]").remove();
}


function loadWorkContactersList(data){
	if(data.workContacters==null||data.workContacters==''){
		$("#workContacterContent").css("display","none");
		return ;
	}
	
	if(data.workContacters.length>0){
		$("#workContacterContent").css("display","");
		$("#workContacterContent").find("div span:eq(1)").text("平台内联系人("+data.workContacters.length+")");
		var ulTag=$("#workContacterContent").find("ul").empty();
		for(var i=0;i<data.workContacters.length;i++){
			$("<li>").css("padding-left","35px").attr("title","")
						.attr("name",data.workContacters[i].id)
						.text(data.workContacters[i].name).click(function(event){addClickEvent(this);})
						.dblclick(function(event){onDblclick(event);})
						.appendTo(ulTag);
		}
	}else{
		$("#workContacterContent").css("display","none");
	}
}

function onDblclick(event){
	var target=$(event.target);
	addSelectedContacter(target.attr("name"),target.text(),target.attr("title"));
	 moveButtonOperator();
}

function addClickEvent(that){
	$("#contacterListContext div ul li").removeClass("select").css("background","");
	$(that).addClass("select").css("background","#ccc");
	moveButtonOperator();
}

function moveButtonOperator(){
	if($("#contacterListContext .select").length>0
			&&!contacterIsAdded()){
		$("#addButton").attr("disabled",false);
	}else{
		$("#addButton").attr("disabled",true);
	}
	if($("#hasSelectedContacter .select").length>0){
		$("#delButton").attr("disabled",false);
	}else{
		$("#delButton").attr("disabled",true);
	}
}

function contacterIsAdded(){
	var sourceContacterId=$("#contacterListContext .select:first").attr("name");
	if(sourceContacterId!=null){
		if($("#hasSelectedContacter li[id="+sourceContacterId+"]").length>0){
			return true;
		}
	}
	return false;
}

function doAjaxSubmit(){
	$("#myGroupHasContacter-Form").ajaxSubmit({
        success: function(data){
        		if(!data.id){
        			 $.messageBox({
							message:data,
							level: "error"
						 });
	                 	return;
        		}
   	   		 	<c:if test='${mode=="add"}'>
			    	$.messageBox({message:"成功保存新群组联系人信息!"});
			     </c:if>
			     <c:if test='${mode=="edit"}'>
				    $.messageBox({message:"成功添加群组联系人!"});
			     </c:if>
			     
			     $("#mygroupMemberList").trigger("reloadGrid");
			     $("#myGroupList").trigger("reloadGrid");
			     $("#addMemberDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>