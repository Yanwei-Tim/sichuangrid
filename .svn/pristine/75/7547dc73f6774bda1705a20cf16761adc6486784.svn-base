TQ.maintainIssueDlg = function (dfop){

	var issueTree;
	
	//用来判断附件是否全部上传完成
	var attachFileUploadComplete = false ;
	
	
	$(document).ready(function(){
		
	//标题（中英文、数字、引号、括号、空格、书名号、减号、下划线）
	jQuery.validator.addMethod("titleStrForIssue", function(value, element){
		if(value==null||value==undefined||value=="" ){return true;};
		var patrn=/^(?:[\u4e00-\u9fa5]*\w*-*_*\s*）*\“*\”*\‘*\’*\"*\'*<*>*《*》*（*\(*\)*)+$/;  
		if (!patrn.exec(value.replace(/[ ]/g,""))) return false  ;
		return true  ;
	});
	operateDiv();
	
	
	
	$("#addPeopleItem").click(function(){
		var sum=$("#issuePeopleItems li").size()-1;
		var temp='<li style="clear:both">\
			<div class="grid_3 lable-right">姓名：</div>\
			<div class="grid_4"><input type="text" name="issueRelatedPeopleNames" maxlength="20" value="" class="form-txt {maxlength:20,minlength:2,exculdeParticalChar:true,messages:{maxlength:$.format(\'姓名不能多于{0}个字符\'),minlength:$.format(\'姓名不能少于{0}个字符\'),exculdeParticalChar:\'不能输入非法字符\'}}" /></div>\
			<div class="grid_3 lable-right">联系手机：</div>\
			<div class="grid_4"><input type="text" name="issueRelatedPeopleTelephones" value="" maxlength="11" class="form-txt {maxlength:11,mobile:true,messages:{maxlength:$.format(\'联系手机不能多于{0}个字符\'),mobile:\'手机号码必须由1开头的11位数字组成\'}}" /></div>\
			<div class="grid_3 lable-right">固话：</div>\
			<div class="grid_4"><input type="text" name="issueRelatedPeopleFixPhones" value="" maxlength="15" class="form-txt {maxlength:15,telephone:true,messages:{maxlength:$.format(\"固话不能多于{0}个字符\"),telephone:\"固话不合法，只能输数字和横杠(-)\"}}" /></div>\
			<div class="grid_3 delItemBox"><a href="javascript:;" class="delPeopleItem">删除</a></div>\
			</li>';
		$("#issuePeopleItems li").find(".delItemBox").show().end().eq(sum).after(temp);
		
	})
	$("#issuePeopleItems").delegate(".delPeopleItem","click",function(){
		if($("#issuePeopleItems li").size()==1){
			$.messageBox({level:'warn',message:"请保留至少一组姓名和联系手机!"});
			return;
		}
		$(this).closest("li").remove();
	})
	
	 
	$("#holder_involvedPersonnel").click(function(){
		var bol = false;
		$.ajax({
			async:false,
			url:PATH+"/issues/issueManage/checkOccurOrgId.action",
			data:{
				"issue.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			return ;
		}
		$(this).peopleSelect(getOccurOrgId());
	});
	
	$("#involvedPlace").personnelComplete({
		url: PATH+"/issues/searchIssue/searchPlaceForAutoComplete.action",
		multiple: false,
		postDataFunction: function(){
		    var orgId=getOccurOrgId();
		    return {orgId:orgId};
		}
	});
	
	$("#lab2").hide();
	$("#div2").hide();
	
	$("#searchPersonnel").click(function(event){
		var bol = false;
		$.ajax({
			async:false,
			url:PATH+"/issues/issueManage/checkOccurOrgId.action",
			data:{
				"issue.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			return ;
		}
		if(isNull()){
			$("#searchandaddDialog").createDialog({
				width:610,
				height:430,
				title:'涉及特殊人群查询',
				url:PATH+"/issue/issueManage/search/searchPersonnelDlg.jsp?orgId="+getOccurOrgId(),
				buttons: {
					"确定" : function(event){
					   setCompVal();
					   $(this).dialog("close");
				   },
				   "取消" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	});
	
	$("#bindMap").click(function(){
		if(isTownDownOrganization()){
			$("#issueOpenLayersMapDialog").createDialog({
				width:800,
				height:600,
				title:"地图绑定",
				url:PATH+"/openLayersMap/product_3.0/gisBindIssue.jsp?flag=1&currentOrgId="+$("#occurOrgId").val(),
				buttons:{
					"关闭" : function(){
			        	$("#issueOpenLayersMapDialog").dialog("close");
			   		}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({message:"发生网格不能选择乡镇（街道）以上级别",level:"error"});
		}
	});
	
	$("#cancelBind").click(function(){
		$("#centerLon").val("");
		$("#centerLat").val("");
		$("#zoom").val("")
		$("#map").TqMap("clearMarkers");
	});

	$("#searchPlace").click(function(event){
		var bol = false;
		$.ajax({
			async:false,
			url:PATH+"/issues/issueManage/checkOccurOrgId.action",
			data:{
				"issue.occurOrg.id":$("#occurOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
	    	$.notice({
				level:'warn',
				message:'请先选择发生网格为乡镇(街道)或乡镇(街道)以下!'
			});
			return ;
		}
		if(isNull()){
			$("#searchandaddDialog").createDialog({
				width:620,
				height:450,
				title:'涉及重点场所查询',
				url:PATH+"/issue/issueManage/search/searchPlaceDlg.jsp?orgId="+getOccurOrgId(),
				buttons: {
					"确定" : function(event){
					   if(setCompVal()){
					   	$(this).dialog("close");
					   }
				   },
				   "取消" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	});
	 // 初始化日期
	initOccurDateSelector(); 
	// 初始化附件上传工具
	initAttachFileUploader(); 
	// 初始发生化网格 
	initOccurOrgSelector();
	// 初始化事件种类 
	initIssueTypeSelector(); 
	//修改的时候填充附件
	if(issueEditing()){
		fillIssueAttenchFiles(); 			
	}
	//表单提交的action
	bindFormAction();	
	//  表单校验				
	bindFormValidation();				
	//事件种类填充
	renderSelectedIssueTypes();	
			
	for(i=0;i<24;i++){
		var time = i<10?"0"+i:i; 
		$('#hours').append($("<option value='"+time+"'>"+time+"</option>"));
	};
	
	for(i=0;i<60;i++){ 
		var time = i<10?"0"+i:i; 
		$('#minute').append($("<option value='"+time+"'>"+time+"</option>"));
	};
	
	if(dfop.mode=="edit"){
		$('#minute').attr("disabled",false);
		$('#hours').val($('#ycHours').val());
		$('#minute').val($('#ycMinute').val());
	}
	
	$('#hours').change(function(){
		if($('#hours').val()!=""){
			$('#minute').val("00");
			$('#minute').attr("disabled",false);
		}else{
			$('#minute').val("");
			$('#minute').attr("disabled",true);
		}
	});

})	

function initOccurOrgSelector(){
		//<s:if test='"add".equals(mode)||"edit".equals(mode)'>
			var tree=$("#issueOccurOrgSelector").treeSelect({
				inputName:"issue.occurOrg.id",
				changeFun:function(node,e){
					//setEmergencyLevelDiv();
					loadOpenLayersMap();
				},
				loadCom:function(){
					if(issueEditing()){
						$.setTreeValue(getDefaultOccurOrg(),tree); 
					}
				}
			});
			issueTree=tree;
		//</s:if>
		//<s:else>
		   // $("#isInvolvedPlace").attr("disabled","disabled");
	   // </s:else>
	}
	
	
	
	function renderSelectedIssueTypes(){
		var typeDesc="";
		$(":input[id^=issueTypeSelector]").each(function(index,value){
			typeDesc=typeDesc+$("[for="+value.id+"]").first().html()+":"+$.trim($(value).getTypeSelectLabels())+"<br>";
		});
		$("#issueTypeDescription").html(typeDesc);
	}


	function  getOccurOrgId(){
		return $("#occurOrgId").val();
	} 

	
	function initOccurDateSelector(){
		if($("#ui-datepicker-div")[0]){
			$("body").append("<div id='ui-datepicker-div' />")
		}
		$('#occurDate').datePicker({
			yearRange:'1930:2060',
			dateFormat:'yy-mm-dd',
			maxDate:'%y-%M-#{%d}'
			
		});
	}

	
	function initAttachFileUploader(){
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames",
			onAllComplete:function(){attachFileUploadComplete = true ;}
		});
		$("#attachFileNames").empty();
	}
	function bindFormAction(){
		if (issueAdding()){
			$("#issueMaintainForm").attr("action",PATH+"/issues/issueManage/addIssue.action");
		}else if (issueEditing()){
			$("#issueMaintainForm").attr("action",PATH+"/issues/issueManage/updateIssue.action");
		}
	}
	function bindFormValidation(){
		$("#issueMaintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
			/*
			if(!attachFileUploadComplete && $("#attachFileNames").val()!=null && $("#attachFileNames").val()!=''){
				$.messageBox({level:'warn',message:"附件还未上传完成，请稍候!"});
				return  ;
			}*/
			$(form).ajaxSubmit({
				success:function(data){
					if(data==null || !data.issueId){
						$.messageBox({message:data,level:"error"});
						return  ;
                	}else{
						$.messageBox({message:"已经成功将该事件信息保存到系统中!"});
						getMessageByUser();
						setTimeout(reloadIssue,1000);
		                try {
		                	$("div[id='issueDialog']:eq(1)").dialog("close");		                	
		                	$("div[id='issueDialog']:eq(0)").dialog("close");
		                } catch (e) {}
	                }
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
			rules:{
				"issue.subject":{
					required:true,
					isLawful:true,
					titleStrForIssue:true,
					rangelength:[2,50]
				},
				"issue.occurLocation":{
					//required:true,
					addressStr:true,
					maxlength:50
				},
				"issue.occurDate":{
					required:true
				},
				"issue.mainCharacters":{
					required:true,
					maxlength:30,
					multiNames:true
				},
				"issue.relatePeopleCount":{
					required:true,
					digits:true,
					range:[1,999999]
				},
				"issue.issueContent":{
					required:true
				},
				"selectOrgName":{
					required:true,
					orgLevelLessEqual:function(){
							return [getOccurOrgId(),dfop.town];
						}
				},
				"issue.issueKind.id":{
					required:true
				},
				"issue.issueType.issueTypeDomain.id":{
					required:true
				},
				"issue.issueType.id":{
					required:true
				},
				"issue.emergencyLevel.id":{
					//required:true
				}
			},
			messages:{
				"issue.subject":{
					required:"请为该事件填写一个不小于2-50个字的主题",
					titleStrForIssue:"事件主题只能输入中英文、数字、引号、括号、空格、书名号、减号等字符",
					isLawful:"事件主题中不能包含非法脚本",
					rangelength:$.format("事件主题不能小于{0}个或大于{1}个字符")
				},
				"issue.occurLocation":{
					//required:"请为该事件填写一个不超过50个字符的事发地点",
					maxlength:$.format("事发地点不能大于{0}个字符"),
					addressStr:"事发地点只能输入中英文、数字、括号、空格、减号、#号等字符"
				},
				"issue.occurDate":{
					required:"请输入该事件的发生日期"
				},
	            "issue.mainCharacters":{
					 required:"请输入主要当事人",
					maxlength:$.format("主要当事人不能大于{0}个字符"),
					multiNames:"主要当事人中只能输入中英文、数字、逗号、顿号等字符"
	            },
	            "issue.relatePeopleCount":{
	            	 required:"请输入涉及人数",
	            	digits:"涉及人数只能输入1到999999之间的整数",
					range:$.format("涉及人数只能输入{0}到{1}之间的整数")
	            },
				"issue.issueContent":{
					required:"请输入事件简述"
				},
				"selectOrgName":{
					required:"请选择发生网格",
					orgLevelLessEqual:"发生网格不能选择乡镇（街道）以上级别"
				},
				"issue.issueKind.id":{
					required:"请选择事件规模"
				},
				"issue.issueType.issueTypeDomain.id":{
					required:"请选择事件大类"
				},
				"issue.issueType.id":{
					required:"请选择事件小类"
				},
				"issue.emergencyLevel.id":{
					required:"请选择重大紧急等级"
				}
			}
		});
	}
	
	
	var status = 1;
	function showInvolvedPlaces(){
		if($("#isInvolvedPlace").attr("checked")){
			$("#lab1").hide();
			$("#div1").hide();

			$("#lab2").show();
			$("#div2").show();
			if("add".equals(dfop.mode)){
				if(isNull()){
					status++;
				}
			}
		}else{
			$("#lab2").hide();
			$("#div2").hide();

			$("#lab1").show();
			$("#div1").show();
			if("add".equals(dfop.mode)){
				if(isNull()){
					status++;
				}
			}
		}
		if(status%2 == 0){
			if("add".equals(dfop.mode)){
				$("#searchPlace").click();
			}
		}
	}

	function isNull(){
		if($("#issueOccurOrgSelector").val()==null||$("#issueOccurOrgSelector").val()==""){
			$.notice({
				title:"提示",
				message:"请先选择所属网格！",
				width: 300
			});
			return false;
			}
		return true;
	}
	//加载地图
	function loadOpenLayersMap(){
		$("#issueViewMap").load(PATH+"/openLayersMap/product_3.0/gisIssue.jsp?currentOrgId="+$("#occurOrgId").val()+"&lon="+$("#centerLon").val()+"&lat="+$("#centerLat").val()+"&zoom="+$("#zoom").val());
	}
	
	function isTownDownOrganization(){
		return $.getSelectedNode(issueTree).attributes.orgLevelInternalId <= dfop.town;
	}

}