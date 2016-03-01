<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="editClassified-dialog" title="编辑分类" class="container container_24">
    <form id="editClassified-form" method="post" action="">
    <input type="hidden" name="myDepartmentGropId" id="myDepartmentGropId" value="">
    <input type="hidden" name="id" id="id" value="">
        <div class="groupSelectBox">
            <table id="tablelist"  class="tablelist">
                <tr id="addtr" style="align:center;">
                    <th colspan="2" width="60%"><b>部门规范统称</b></th>
                    <th><b>操作</b></th>
                </tr>
                <s:iterator value="myDepartmentGrops" var="myDepartmentGrop">
                    <tr id="${ myDepartmentGrop.id}tr">
                        <td id="${ myDepartmentGrop.id}text" align="left" colspan="2">
	                        <a href="javascript:void(0)" class="editName" mydepartId='${myDepartmentGrop.id}' mydepartName='${ myDepartmentGrop.name}' >修改名称</a>
	                        <label>${ myDepartmentGrop.name}</label>
                        </td>
                        <td align="center">
                        	<input id="${myDepartmentGrop.id }td" class="defaultBtn groupBtn" type="button" groupId='${myDepartmentGrop.id}' groupMembers='${myDepartmentGrop.groupMembers}' value="管理部门" title="管理部门成员">
                            <input id="${myDepartmentGrop.id }del" type="button" class="defaultBtn deleteBtn" deleteGroupId='${myDepartmentGrop.id}'  value="删除" >
                        </td>
                    </tr>
                </s:iterator>
                <tr >
                    <td colspan="3" align="left"><a id="newOne" href="javascript:;">+新建分类</a>
                </tr>
            </table>
        </div>
        <div id="claasify">
            <div id="choose"  class="grid_11 lable-left heightAuto">
                <label  ><b>可选部门</b></label>
                <div class="waitBox">
                    <select id="selectdepartments" style="width:240px" class="S_object">
                        <option value="" selected="selected">请选择</option>
                        <option value="50,0" >省（直辖市）</option>
                        <option value="40,0" >市（地）</option>
                        <option value="30,0" >县（区）</option>
                        <option value="20,0" >乡镇（街道）</option>
                        <option value="10,0" >村（社区）</option>
                        <option value="0,0" >网格</option>
                        <option value="50,1" >省级职能部门</option>
                        <option value="40,1" >市级职能部门</option>
                        <option value="30,1" >县级职能部门</option>
                        <option value="20,1" >乡镇级职能部门</option>
                    </select>
					<ul id="waitSelectOrgList" class="waitSelectOrgList">
						
					</ul>
                </div>
            </div>
            <div class="grid_2 lable-left" style="text-align:center;"> <img id="appendRightBtn" alt="center" title="右移" src="${resource_path}/resource/system/images/addTo.gif"> </div>
            <div class="grid_11 lable-left heightAuto">
                <label><b>已有部门</b></label>
                <div id="selectOrgList" class="selectOrgList"></div>
            </div>
            <div class="grid_24" style="text-align:center;clear:both;">
                 <input id="saveMembers" type="button" class="defaultBtn"  value="确定"/>
                 <input id="resetMembers" type="button" class="defaultBtn" value="重置"/>
                 <input id="quit" type="button" class="defaultBtn" value="取消"/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
$("#tablelist").delegate('.deleteBtn',"click",function(){
	var id=$(this).attr("deleteGroupId");
	$.ajax({
		url:'${path}/documents/dispatchDocumentsManage/findDocumentIdsByGroupId.action?id='+id,
		type:'GET',
		dataType:'json',
		success : function(data){
			if(data.length>0){
				$.confirm({
					title:"确认删除",
					message:"该部门群已存在于未发文的文件中，删除后会对该文件产生影响，是否确定删除?",
					width: 400,
					okFunc: function(){deleteDepartmentGroup(id,true);}
				});
			}
			if(data.length==0){
				$.confirm({
					title:"确认删除",
					message:"是否确定删除?",
					width: 400,
					okFunc: function(){deleteDepartmentGroup(id,false);}
				});
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
});
function deleteDepartmentGroup(id,exist){
	$.ajax({
		url:'${path}/documents/dispatchDocumentsManage/deleteMyDepartmentGropById.action?exist='+exist+'&id='+id,
		type:'GET',
		dataType:'json',
		success : function(data){
			if(data){
				$.messageBox({
					message : "部门群删除成功！"
				});
				$("#"+id+"tr").remove();
				$("#claasify").hide();
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}
$("#resetMembers").click(function(){
	$("#selectOrgList").empty();
	$("#waitSelectOrgList dd.disable").removeClass("disable");
});
$("#saveMembers").click(function(){
	$.ajax({
		url:'${path}/documents/dispatchDocumentsManage/updateMyDepartmentGropMembers.action?organizationIds='+getOrgList()+'&id='+$("#myDepartmentGropId").val(),
		type:'GET',
		dataType:'json',
		success : function(_data){
			if(_data!=null){
				$.messageBox({
					message : "部门群修改成功！"
				});
				$("#claasify").hide();
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
});
function updatename(id){
	if($("#updateName").val()==""){
		$("#updateName").focus();
		return;
	}
	$("#editClassified-form").attr("action","${path}/documents/dispatchDocumentsManage/updateMyDepartmentGropName.action");
	$("#id").val(id);
	
	 $("#editClassified-form").ajaxSubmit({
			success:function(data){
				if(!data.id){
					$.messageBox({
						message:data,
						level: "error"
					});
					return;
				}
				if(data!=null){
					$.messageBox({
						message : "修改部门群成功！"
					});
					$("#"+data.id+"text label").html(data.name);
					$("#"+data.id+"text a").attr("mydepartName",data.name);
				}

			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
		    }
		}); 
	 getRightOrgGroup();
}
$("#tablelist").delegate('.editName',"click",function(){
	var mydepartId = $(this).attr("mydepartId");
	var mydepartName = $(this).attr("mydepartName");
	$("#"+mydepartId+"text label").html('<input id="updateName" type="text" name="name" class="defaultText" value="'+mydepartName+'" onBlur="updatename(\''+mydepartId+'\')"></input>');
	$("#updateName").focus();
});

$("#tablelist").delegate('.groupBtn',"click",function(){
	$("#myDepartmentGropId").val($(this).attr("groupId"));
	var groupMembers=$(this).attr("groupMembers");
	$("#claasify").show();
	$("#selectOrgList").empty();
	$("#waitSelectOrgList").empty();
	$("#selectdepartments option[value='']").attr("selected", true);
	$.ajax({
		url:'${path}/documents/dispatchDocumentsManage/findOrganizationByIds.action?id='+$(this).attr("groupId"),
		type:'POST',
		dataType:'json',
		success : function(data){
			if(data!=null){
				$($.parseHTML(".childrenOrgListBox dd")).removeClass("disable");
				for(var i=0;i<data.length;i++){
// 					if($($.parseHTML("#selectOrgList dd:[orgId="+data[i].id+"]"))[0]){return false;}
					var $item=$('<dd orgId="'+data[i].id+'"><a href="javascript:;">'+data[i].orgName+'</a><a href="javascript:;" class="remove"  title="删除">【删除】</a></dd>');
					$("#selectOrgList").append($item);
				}
				$("#editClassifiedDialog,#editClassifiedContent").scrollTop($("#claasify").offset().top);
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
});
$("#tablelist").delegate('#cancelAdd',"click",function(){
	$("#addNametr").remove();
});
$("#tablelist").delegate('#successAdd',"click",function(){
	if($("#addName").val()==""){
		$("#addName").focus();
		return;
	}
	$("#newOne").removeAttr("disabled"); 
	$("#editClassified-form").attr("action","${path}/documents/dispatchDocumentsManage/addMyDepartmentGrop.action");
	$("#editClassified-form").ajaxSubmit({
		success:function(data){
			if(!data.id){
				$.messageBox({message : data, level : "error"});
				return;
			}
			if(data.id){
				$.messageBox({message : "添加部门群成功！"});
				$("#addNametr").replaceWith("<tr id='"+data.id+"tr'>"+
                        "<td id='"+data.id+"text' align='left' colspan='2'>"+
                        "<a href='javascript:void(0)' mydepartId='"+data.id+"' mydepartName='"+data.name+"' class='editName'>修改名称</a>"+
                        "<label>"+data.name+"</label>"+
                        "</td><td align='center'><input id='"+data.name+"td' class='defaultBtn groupBtn' type='button' groupId='"+data.id+"' groupMembers='"+data.groupMembers+"' value='管理部门' >"+
                       "<input id='"+data.id+"del' type='button' class='defaultBtn deleteBtn' deleteGroupId='"+data.id+"'  value='删除' title='删除'></td> </tr>");
			}

		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
});
$("#newOne").click(function(){
	if($("#newOne").attr("disabled")){
		return false
	}
    var addtrHtml='<tr id="addNametr"><td colspan="3"><input id="addName" name="name" class="defaultText" type="text"   value="" ><input type="button" id="successAdd" class="defaultBtn" value="添加"/><input type="button" id="cancelAdd" class="defaultBtn" value="取消添加"/></td></tr>';
    $("#tablelist tbody tr:last").before(addtrHtml);
    $("#addName").focus();
});
function getOrgList(){
	var orgList='';
	$("#selectOrgList dd").each(function(){
		orgList=orgList+$(this).attr("orgId")+',';
	})
	return orgList;
}
$(document).ready(function(){
	$("#claasify").hide();
	$("#editClassified-dialog").keydown(function(evt){
	    var evt = window.event?window.event:evt;
	    if(evt.keyCode==13){
	    	return false;
	    }
	});

	$('#quit').click(function(){$("#claasify").hide();});
	
	$("#selectdepartments").change(function(){//选择事件
		var selectdepartments = $("#selectdepartments").val();
		var selectLevel = selectdepartments.split(",")[0];
		var selectType = selectdepartments.split(",")[1];
		var selectdepartmentsTwo = "";
		if(selectType == 1){
			selectdepartmentsTwo =selectLevel+",0";
		}else{
			selectdepartmentsTwo=selectdepartments;
		}
		$.ajax({
			url:'${path}/documents/dispatchDocumentsManage/getOrgList.action?internalId='+selectdepartmentsTwo,
			type:'GET',
			dataType:'json',
			success : function(data){
				if(data!=null){
					var $box=$("#waitSelectOrgList").empty();
					for(var i=0;i<data.length;i++){
						var $item=$('<li orgId='+data[i].id+'><a href="javascript:;">'+data[i].orgName+'</a></li>');
						$box.append($item);
					}
					$box.find("li").eq(0).click();
				}
			},
			error : function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		});
	})
	$("#waitSelectOrgList").delegate("li","click",function(){//左侧列表父级点击事件
		var $that=$(this);
		var thisOrgId=$that.attr("orgId");
		if(!$(this).hasClass("cur")){
			$that.addClass("cur").find("dl").show();
		}else{
			$that.removeClass("cur").find("dl").hide();
		}
		if($("#childrenOrgList"+thisOrgId)[0]){return false;}
		var selectdepartments = $("#selectdepartments").val();
		var selectType = selectdepartments.split(",")[1];
		var onlyOrg = "";
		if(selectType == 1){
			onlyOrg ="only";
		}else{
			onlyOrg="";
		}
		$.ajax({
			url:'${path}/documents/dispatchDocumentsManage/getOrgsForParentIdAndOrgType.action?parentId='+thisOrgId+'&internalId='+$("#selectdepartments").val()+'&onlyOrg='+onlyOrg,
			success:function(data){
				if(data!=null){
					var $box=$('<dl class="childrenOrgListBox" id="childrenOrgList'+thisOrgId+'"></dl>');
					for(var i=0;i<data.length;i++){
						var $item=$('<dd orgId="'+data[i].id+'"><a href="javascript:;">'+data[i].orgName+'</a></dd>');
						if($($.parseHTML("#selectOrgList dd:[orgId="+data[i].id+"]"))[0]){
							$item.addClass("disable");
						}
						$box.append($item);
					}
					$that.append($box);
				}
			},
			error:function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		})
	});
	$("#waitSelectOrgList").delegate("dd","click",function(){//左侧列表点击事件
		var thisOrgId=$(this).attr("orgId");
		$(this).toggleClass("cur");
		return false;
	});
	$("#selectOrgList").delegate(".remove","click",function(){//右侧移除事件
		var thisOrgId=$(this).attr("orgId");
		var $that=$(this).closest("dd");
		$("#waitSelectOrgList dd").each(function(){
			if($that.attr("orgId")==$(this).attr("orgId")){
				$(this).removeClass("disable");
			}
		});
		$that.remove();
	});
	$("#appendRightBtn").click(function(){//向右移动按钮事件
		$("#waitSelectOrgList").find("dd.cur").each(function(){
			var $that=$(this);
			var bool=true;
			$("#selectOrgList dd").each(function(){
				if($that.attr("orgId")==$(this).attr("orgId")){
					bool=false;
					return false;
				}
			})
			if(bool==true){
				$that.removeClass("cur").clone().append('<a href="javascript:;" class="remove" title="删除">【删除】</a>').appendTo("#selectOrgList");
				$that.addClass("disable");
			}
		})
	});
	$("#waitSelectOrgList").delegate("dd","dblclick",function(){//左侧列表双击事件
		var thisOrgId=$(this).attr("orgId");
		var $that=$(this);
		var bool=true;
		$("#selectOrgList dd").each(function(){
			if($that.attr("orgId")==$(this).attr("orgId")){
				bool=false;
				return false;
			}
		})
		if(bool==true){
			$that.removeClass("cur").clone().append('<a href="javascript:;" class="remove" title="删除">【删除】</a>').appendTo("#selectOrgList");
			$that.addClass("disable");
		}
	});
});
</script>