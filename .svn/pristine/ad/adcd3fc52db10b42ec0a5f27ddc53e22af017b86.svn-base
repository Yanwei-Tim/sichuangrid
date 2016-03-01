<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style type="text/css">
	.propertyMulSelect { 
		height: 100px;
		overflow-y: auto !important;
		overflow-x: hidden;
        padding:3px 0 10px 12px;
	}
	.propertyMulSelect label{
        display:inline-block;
        *zoom:1;
        *display:inline;
        
		min-width:130px;
        height:28px;
        line-height:28px;
	}
	.propertyMulSelect label input {
		vertical-align: middle;
		margin-right: 3px;
	}
    #finishTypeDiv11{
        display:none;
    }
    #maintainAccountLogsForm .heightAuto{}
    #maintainAccountLogsForm .multipeSelectCtt{
        margin: -115px 0 0 126px;
        float: left;
        width: 300px;
        display: none;
        border: 1px solid #B4B4B4;
        background: #E1E4E1;
    }
  #maintainAccountLogsForm .hover{background-color:#E1E4E1;}
    #dialog-maintainAccountLogsForm .grid_x{ float:left; width:210px}
    #maintainAccountLogsForm .ctt, 
    #maintainAccountLogsForm .tBtn{ float:left; display:inline;  width:132px}
    #maintainAccountLogsForm .tBtn{ margin-left:10px;  width:60px}
  /*   #maintainAccountLogsForm .tBtn input{ width:60px;  height:24px; line-height:24px} */
    #maintainAccountLogsForm .ctt{border:1px solid transparent;padding-right:4px;}
	#maintainAccountLogsForm .ctt .holder{width: 140px !important;} 
</style>
<div class="container container_24" >
	<form id="maintainAccountLogsForm" method="post" action="" >
		<input id="accountId" type="hidden" name="accountLogs.accountId" value="${param.accountId}" />
		<input id="id" type="hidden" name="accountLogs.id" value="${accountLogs.id}" />
		<input id="accountType" type="hidden" name="accountLogs.accountType" value="${param.accountType}" />
		<!-- <input id="targetOrgId" type="hidden" name="accountLogs.targetOrg.id"> 
		<input id="targetOrgInternalCode" type="hidden" name="accountLogs.targetOrg.orgInternalCode">  -->
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
   			<label class="form-lb1">处理时间：</label>
   		</div>
   		<div class="grid_7" id="dealDateDiv">
   			<input type="text" name="accountLogs.dealDate" id="dealDate" maxlength="32" readonly  value='<s:date name="accountLogs.dealDate" format="yyyy-MM-dd" />' class='form-txt {required:true,messages:{required:"处理时间必须选择"}}' />
   		</div>
   		<div class="grid_4 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">承办人：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="accountLogs.dealUser" id="dealUser" maxlength="20" value="${accountLogs.dealUser}" 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"承办人必须输入",exculdeParticalChar:"不能输入非法字符",minlength:$.format("承办人至少需要输入{0}个字符"),maxlength:$.format("承办人最多需要输入{0}个字符")}}' 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">地点：</label>
   		</div>
   		<div class="grid_7">
   			<input type="text" name="accountLogs.site" id="site" maxlength="20" value="${accountLogs.site}" 
				class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("承办人至少需要输入{0}个字符"),maxlength:$.format("承办人最多需要输入{0}个字符")}}' 
   			/>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_4 lable-right" >
   			<em class="form-req">*</em>
   			<label class="form-lb1">工作措施及内容：</label>
   		</div>
   		<div class="grid_18" style="height: 120px">
   			<textarea rows="4" name="accountLogs.content" id="content" style="width: 99%" class='form-txt {required:true,maxlength:300,messages:{required:"工作措施及内容",maxlength:"工作措施及内容最多需要输入300个字符"}}' 
   			>${accountLogs.content }</textarea>
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right" >
   			<em class="form-req">*</em>
   			<label class="form-lb1">当事人或当事人<br/>代表意见：</label>
   		</div>
   		<div class="grid_18" style="height: 120px">
   			<textarea rows="4" name="accountLogs.opinion" id="opinion" style="width: 99%" class='form-txt {required:true,maxlength:300,messages:{required:"代表意见必须输入",maxlength:"代表意见最多需要输入300个字符"}}' 
   			>${accountLogs.opinion }</textarea>
   		</div>
   		<input type="hidden"" id="comparedFinishType" value="<s:property value='accountLogs.finishType'/>">
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否办结</label>
			<input type="checkbox" id="isEnd" name="accountLogs.isFinish" value="true" <s:if test="true==accountLogs.isFinish">checked="checked"</s:if>/>
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div id="finishTypeDiv11">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">办结时间：</label>
			</div>
			<div class="grid_7 lable-right">
				<input type="text" id="finishDate" name="accountLogs.finishDate" maxlength="20" readonly value='<s:date name="accountLogs.finishDate" format="yyyy-MM-dd" />' class="form-txt">
			</div>
		    <div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">办结方式：</label>
			</div>
			<div class="grid_4 heightAuto" id="zcfPart">
				<label class="form-check-text"><input id="zcf" name="finishType" type="radio" class="category" value="1">实质性办结方式</label>
			</div>

			<div class="grid_4 heightAuto" id="fcfPart">
				<label class="form-check-text"><input id="fcf" name="finishType" type="radio" class="category" value="0">程序性办结方式</label>
			</div>


		    <div class="grid_10 heightAuto">
		        <input id="supval" type="text" name="supval_just_marked" class="form-txt dialogtip" value='' maxlength="50" readonly="readonly" <s:if test='"view".equals(mode)'>disabled='true'</s:if> title="..."/>
		    </div>
            <div class='clearLine'>&nbsp;</div>
		    <div id="orgDiv" style="display: none;">
				<div class="grid_4 lable-right" >
		   			<em class="form-req">*</em>
		   			<label class="form-lb1">选择组织机构：</label>
		   		</div>
		   		<div class="grid_x" >
					<div class="ctt">
						<input id="transferTo" type="text" name="accountLogs.targetOrg.id" class="form-txt" />
					</div>
					<div class="tBtn" >
						<input type="button" class="defaultBtn" value="选择" id="selectTransferTo" />
					</div>
				</div>
			</div>
			
		    <div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right" >
	   			<em class="form-req">*</em>
	   			<label class="form-lb1">办结结果：</label>
	   		</div>
	   		<div class="grid_18" style="height: 120px">
	   			<textarea rows="4" name="accountLogs.finishContent" id="finishContent" style="width: 99%"
	   				class='form-txt {maxlength:300,messages:{maxlength:"办结结果最多需要输入300个字符"}}' 
	   			>${accountLogs.finishContent }</textarea>
	   		</div>
            <div class='clearLine'>&nbsp;</div>
            
            <div class="multipeSelectCtt" id="zcfUl" dataId='zcf'>
                <div class="propertyMulSelect">
                <label><input id="zcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REAL_SOLVE'/>">实质化解</label>
				<label><input id="zcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REAL_EVENTUALLY'/>">实质性终结</label>
				<label><input id="zcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@PROGRAM_EVENTUALLY'/>">程序性终结</label>
				</div>
            </div>

            <div class="multipeSelectCtt" id="fcfUl"  dataId='fcf'>
                <div class="propertyMulSelect">
                <label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@STEADY_CONTROL'/>">稳控中</label>
				<label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_TOWN'/>" disabled>流转乡镇、街道</label>
				<label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_KEEP'/>" disabled>呈报市维稳工作台账办公室</label>
				<label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_DISTRICT'/>">呈报市委市政府</label>
				<label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@OTHER'/>">其他方式</label>
				<label><input id="fcfRadio" name="accountLogs.finishType" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_FUNCTION'/>" disabled>流转市职能部门</label>
				</div>
            </div>

		</div>
	</form>
</div>

<script type="text/javascript">
var REPORT_TO_TOWN= "<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_TOWN'/>";
var REPORT_TO_KEEP="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_KEEP'/>";
var REPORT_TO_FUNCTION="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@REPORT_TO_FUNCTION'/>";
function initTargetAutocomplete(){
	$("#transferTo").personnelComplete({
		url:"${path}/account/accountLogsManage/searchTransferTarget.action?mode=auto&targetOrgId="+USER_ORG_ID,
		multiple: false,
		postDataFunction: function(){
		    return {
		    		adminTarget:function(){return targetIsAdmin();}
		    		};
		}
	});
};
function targetIsAdmin(){
		if($("#fcfUl input:radio:checked").val()==REPORT_TO_TOWN)
			return true;
		else//$("#fcfUl input:radio:checked").val()==REPORT_TO_FUNCTION
			return false;
	
}
function initTargetSelectorAction(){
	$("#selectTransferTo").click(function(event){
		createOrgSearchDialog("${path}/account/accountLogsManage/dispatchOperate.action?mode=searchTarget&targetOrgId="+USER_ORG_ID
									,"transferTo");
	});
}


function createOrgSearchDialog(searchUrl,inputId){
	$("#orgSelectDialog").createDialog({
		width:550,
		height:430,
		title:'选择部门',
		url: searchUrl,
		postData:{	
					adminTarget:function(){return targetIsAdmin();}
				},
		buttons: {
			"确定" : function(event){
					fillOrgInputer(inputId);
					fillItemName();
					$(this).dialog("close");
			},
			"关闭" : function(){
					$(this).dialog("close");
			}
		}
	});
	
}

function fillItem(selectedId) {
	if($("#transferToFun").attr("checked")=='checked' && typeof(selectedId)!='undefined') {
		if(selectedId == null) {
			return;
		} else {
			$.ajax({
				url:"${path}/issues/issueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
				async:false,
				type:'post',
				success:function(data){
					$("#itemTypeId option").remove();
					$.each(data,function(name,value) {
						$("<option>").attr("value",name).html(value).appendTo($("#itemTypeId"));
					});
					$("<option>").attr("value","").attr("selected",true).html("").prependTo($("#itemTypeId"));
				}
			});
		}
	}
}
$(function(){
	initTargetAutocomplete();
	initTargetSelectorAction();
	if(USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>"){
		 $("#fcfUl input:radio").each(function(index,domEle){
		    	if(REPORT_TO_TOWN==$(this).val()){
					$(this).attr("disabled",true);
				}
		    	//县市级可以流转职能部门(其他的不可以)
		    	if(REPORT_TO_FUNCTION==$(this).val()){
					$(this).attr("disabled",true);
				}
		    	if(REPORT_TO_KEEP==$(this).val()){
		    		$(this).attr("disabled",false);
		    	}
		  });
	}else if(USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"
		&&"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()'/>"=="0"){
		 $("#fcfUl input:radio").each(function(index,domEle){
		    	if(REPORT_TO_TOWN==$(this).val()){
					$(this).attr("disabled",false);
				}
		    	//县市级可以流转职能部门
		    	if(REPORT_TO_FUNCTION==$(this).val()){
					$(this).attr("disabled",false);
				}
		    	if(REPORT_TO_KEEP==$(this).val()){
		    		$(this).attr("disabled",true);
		    	}
		  });
	}else if(USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"
		&&"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()'/>"=="1"){
		 $("#fcfUl input:radio").each(function(index,domEle){
			 if(REPORT_TO_TOWN==$(this).val()){
					$(this).attr("disabled",false);
				}
		    	//县市级可以流转职能部门(其他的不可以)
		    	if(REPORT_TO_FUNCTION==$(this).val()){
					$(this).attr("disabled",true);
				}
		    	if(REPORT_TO_KEEP==$(this).val()){
		    		$(this).attr("disabled",false);
		    	}
		  });
	}else if(USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@VILLAGE'/>"){
		 $("#fcfUl input:radio").each(function(index,domEle){
		    	if(REPORT_TO_TOWN==$(this).val()){
					$(this).attr("disabled",false);
				}
		    	//县市级可以流转职能部门(其他的不可以)
		    	if(REPORT_TO_FUNCTION==$(this).val()){
					$(this).attr("disabled",true);
				}
		    	if(REPORT_TO_KEEP==$(this).val()){
		    		$(this).attr("disabled",true);
		    	}
		  });
	}
	
	function displayAndHideSelectOrg(){
		//县市级可以流转职能部门显示组织机构(其他的不可以)
		if(USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
			if($("#fcfUl input:radio:checked").val()==REPORT_TO_TOWN || $("#fcfUl input:radio:checked").val()==REPORT_TO_FUNCTION ){
				$("#orgDiv").show();
			}
			
			else
				$("#orgDiv").hide();
		}else{
			$("#orgDiv").hide();
		}
	}
	if($("#comparedFinishType").val()==REPORT_TO_TOWN && USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
		$("#orgDiv").show();
	}
	//县市级可以流转职能部门显示组织机构(其他的不可以)
	if($("#comparedFinishType").val()==REPORT_TO_FUNCTION && USER_ORG_LEVEL=="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
		$("#orgDiv").show();
	}
	/**
	var orgTree=$("#orgSelector").treeSelect({
		nodeUrl:'/sysadmin/orgManage/ajaxOrgsForExtTreeByLevel.action?orgLevelInternalId='+"<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>",
		allOrg:false,
		isRootSelected:false,
		emptyText:'',
		rootId:USER_ORG_ID
	});
	
	orgTree.on("click",function(node,e) {
		if(node != null){
			$("#targetOrgId").val(node.attributes.id);
			$("#targetOrgInternalCode").val(node.attributes.orgInternalCode);
		}
	});
	*/
	function pulldownFun(option){
    	var dfop={
    	}
        var op=this.op=$.extend(dfop,option||{});
        var on  = $(op.on),
            dlg = $(op.dialog),
            mouseover_tid = [],
            i   = 0;
        
        on.hover(
            function(){
                if(op.offset){
                    var offset=on.position();
                    dlg.css({'top':offset.top+(+op.offset),'left':offset.left})
                }
                setTimeout(function(){
                    dlg.show()
                },200)
                clearTimeout(mouseover_tid[i]);
            }, function(){
                mouseover_tid[i] = setTimeout(function(){
                    dlg.hide()
                },200)
            }
        )
        dlg.hover(
            function(){
                clearTimeout(mouseover_tid[i]);
            }, function(){
                mouseover_tid[i] = setTimeout(function(){
                    dlg.hide()
                },200)
            }
        )
    }

    new pulldownFun({
        offset:'30',
        on:"#zcfPart",
        dialog:"#zcfUl"
    });
    new pulldownFun({
        offset:'30',
        on:"#fcfPart",
        dialog:"#fcfUl"
    });


    var finishTypeDiv = $('#finishTypeDiv11');
	/* multipeSelect.hover(function(){
		multipeSelect.find('.multipeSelectCtt').hide()
		$(this).find('.multipeSelectCtt').show()
	},function(){
		$(this).find('.multipeSelectCtt').hide()
		
	}) */
    $('#zcfUl input').click(function(){
        $('#zcf').trigger('click');
        showVisit();
    });
    $('#fcfUl input').click(function(){
        $('#fcf').trigger('click');
        showVisit();
    });
	
	if( $('#isEnd').attr('checked') == 'checked' ){
    	finishTypeDiv.show()
    }else{
    	finishTypeDiv.hide()
    }

    $('#isEnd').change(function(){
    	if( $('#isEnd').attr('checked') == 'checked' ){
    		finishTypeDiv.show()
        }else{
        	finishTypeDiv.hide()
        	$("#finishDate").val("");
        	$("#comparedFinishType").val("");
        	$("#finishContent").val("");
        	$("#isEnd").val(null);
        	$("#zcf").attr("checked",false);
        	$("#fcf").attr("checked",false);
        	$("#zcfRadio").attr("checked",false);
        	$("#fcfRadio").attr("checked",false);
        	$("#supval").val("");
        	$("#orgSelector").val("");
        	$("#orgDiv").hide();
        }
    	//finishTypeDiv.toggle();
    });
    
    $("#zcfUl input:radio").each(function(index,domEle){
		if($("#comparedFinishType").val()==$(this).val()){
			$(this).attr("checked",true);
			$("#zcf").attr("checked",true);
			var superValue="实质性办结方式：";
			superValue=superValue+$(this).parent().text();
			$("#supval").val(superValue);
		}
  	}); 
    
    $("#fcfUl input:radio").each(function(index,domEle){
    	if($("#comparedFinishType").val()==$(this).val()){
			$(this).attr("checked",true);
			$("#fcf").attr("checked",true);
			var superValue="程序性办结方式：";
			superValue=superValue+$(this).parent().text();
			$("#supval").val(superValue);
		}
  	});


	function showVisit(){
		var VisitValue="";
		var superValue="";
		$("#transferTo").clearPersonnelComplete();
		var val = $("input[name='finishType']:checked").val();
        
		if( val=="1"){
	    	$("#zcfUl input:checked").each(function(index,domEle){
	    		superValue="实质性办结方式：";
	    		VisitValue+=","+$(domEle).parent().text();
	      	});
		}else if( val == '0'){
	   		$("#fcfUl input:checked").each(function(index,domEle){
	   			superValue="程序性办结方式：";
	   			VisitValue+=","+$(domEle).parent().text();
	      	});
		}
		if(VisitValue==""){
			$("#fcf").removeAttr("checked");
			$("#zcf").removeAttr("checked");
			superValue="";
		}
		$("#supval").val(superValue+VisitValue.substr(1));
		displayAndHideSelectOrg();
	}
	
	$('#dealDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	
	$('#finishDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	
	$("#maintainAccountLogsForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					if('add'=='<s:property value="#parameters.mode[0]"/>'){
						if(data!=null&&data!='true'&&data!=true){
							$.messageBox({
								message:data,
								level: "error"
							});
							return;
						}
						$.messageBox({message:"办理完成"});
						$("#steadyWorkDialog").dialog("close");
						$("#steadyWorkList").trigger("reloadGrid");
					} else {
						if(!data.id){
							$.messageBox({
								message:data,
								level: "error"
							});
							return;
						}
						$.messageBox({message:"修改成功!"});
						//$("#steadyWorkDialog").dialog("close");
						$("#accountLogsDialog").dialog("close");
						$("#accountLogsList").trigger("reloadGrid");
						$("#steadyWorkList").trigger("reloadGrid");
					}
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{			
		},
		messages:{
		},
		ignore:':hidden'
	});
	var mode='${param.mode}';
	if('add'==mode){
		$("#maintainAccountLogsForm").attr("action","${path}/account/accountLogsManage/addAccountLogs.action");
	} else {
	    $("#maintainAccountLogsForm").attr("action","${path}/account/accountLogsManage/updateAccountLogs.action");
	}
});
</script>