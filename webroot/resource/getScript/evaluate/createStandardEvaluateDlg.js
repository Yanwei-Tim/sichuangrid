TQ.createStandardEvaluateDlg = function (dfop){

	function judgeGroup(){
		for(var i = 0 ; i < 8 ; i ++){
			for(var j = 0 ; j < i ; j++){
				var jj = j + 1;
				var ii = i + 1;
				var x = parseFloat($("#score" + jj).val())
				var y = parseFloat($("#score" + ii).val())
				if(y > x ){
					arrayMessage2[i] = "此处不能输入比" + x + "大的值！";
					arrayTrueFalse2[i] = false;
					showInputErrorMessage(ii , arrayMessage2[i])
					return false;	
					break;			
				}else{
					arrayMessage2[i] = "";
					arrayTrueFalse2[i] = true;
				}
			}
		}
		return true;
	}
	function showInputErrorMessage(splitId,msg){
		var defaultOption={
				content:"<div class='inputName' id=inputName" + splitId + "><span class='error'></span>" + msg + "</div>"
		}
		defaultOption.alignX="bottom";
		defaultOption.alignY="center";
		if(arrayTrueFalse[splitId - 1]){
			if(arrayTrueFalse2[splitId - 1]){
				return ;
			}
		}
		$("#score" + splitId).dialogtip(defaultOption);
		$("#score" + splitId).poshytip('show');
		$(".inputName").css("cursor","pointer");
		
		$(".inputName").parent().parent().click(function(a){
			$(".inputName").parents(".tip-error").remove();
		})
	}
	function calculateFirstTreeLevelTotalScore(){
		var totalScore=0;
		var checkboxs=$("input[type='checkbox']",$("#itemsList"));
		$.each(checkboxs,function(i,n){
			if($(n).attr("checked")){
				var rowData = $("#itemsList").getRowData($(n).val());
				if(rowData.treelevel==1 || $(rowData.treelevel).text()==1){
					totalScore=totalScore+parseFloat(rowData.standardScore);
				}
			}
		})
		$("#standardEvaluateTotalScore").text(totalScore);
		$("#score1").val(totalScore);
	}
	function alterFirstLevelScore(n){
		//选中的一级节点
		var rowData = $("#itemsList").getRowData($(n).parent().parent().parent().attr("id"));
		if(rowData.treelevel == 1){
			var firstLevelScore = 0;
			var checkboxs=$("input[type='checkbox']",$("#itemsList"));
			$.each(checkboxs , function(i , n){
				var rowDataSecond = $("#itemsList").getRowData($(n).val());
				if(rowDataSecond.parentId == rowData.id && rowDataSecond.treelevel == 2){
					firstLevelScore = Number(firstLevelScore) + Number(rowDataSecond.standardScore);
				}
			})
			if(firstLevelScore != 0){
				$("#itemsList").setRowData(rowData.id,{"standardScore" : firstLevelScore});
			}
			firstLevelScore = 0;
		}
		
	}
	function calculateTotalScore(){
		calculateSecondTreeLevelTotalScore($(this));
		calculateFirstTreeLevelTotalScore();
		alterFirstLevelScore($(this))
		checkChildBox($(this));
		checkParentBox($(this));
	}
	function calculateSecondTreeLevelTotalScore(node){
		var rowData = $("#itemsList").getRowData($(node).val());
		if(rowData.treelevel == 2){
			var checkboxs=$("input[type='checkbox']",$("#itemsList"));
			$.each(checkboxs,function(i,n){
				var thisRowData = $("#itemsList").getRowData($(n).val());
				if(thisRowData.id==rowData.parentId && Boolean($("input[type='checkbox']",$("#" + rowData.parentId)).attr("checked"))){
					if($(node).attr("checked")){//此时进行加运算
						$("#itemsList").setRowData(thisRowData.id,{"standardScore":(parseFloat(thisRowData.standardScore) + parseFloat(rowData.standardScore))});
					}else{//此时进行减运算.
						$("#itemsList").setRowData(thisRowData.id,{"standardScore":(parseFloat(thisRowData.standardScore) - parseFloat(rowData.standardScore))});
					}
				}
			});
		}
	}

	function checkChildBox(node){
		var rowData = $("#itemsList").getRowData($(node).val());
		if(rowData.treelevel==3 || $(rowData.treelevel).text()==3){
			return;
		}
		if(rowData.treelevel==2 || $(rowData.treelevel).text()==2){
			var checkboxs=$("input[type='checkbox']",$("#itemsList"));
			$.each(checkboxs,function(i,n){
				var thisRowData = $("#itemsList").getRowData($(n).val());
				if(thisRowData.parentId==rowData.id){
					if($(node).attr("checked")){
						$("input[type='checkbox'][value="+thisRowData.id+"]",$("#itemsList")).attr("checked","checked");
					}else{
						$("input[type='checkbox'][value="+thisRowData.id+"]",$("#itemsList")).removeAttr("checked");
					}
				}
			});
		}
		if(rowData.treelevel==1 || $(rowData.treelevel).text()==1){
			var childRows = new Array();
			var checkboxs = $("input[type='checkbox']",$("#itemsList"));
			$.each(checkboxs,function(i,n){
				var thisRowData = $("#itemsList").getRowData($(n).val());
				if(thisRowData.parentId==rowData.id){
					if($(node).attr("checked")){
						$("input[type='checkbox'][value="+thisRowData.id+"]",$("#itemsList")).attr("checked","checked");
					}else{
						$("input[type='checkbox'][value="+thisRowData.id+"]",$("#itemsList")).removeAttr("checked");
					}
					childRows[childRows.length]=$(n);
				}
			});
			for(var i = 0;i<childRows.length;i++){
				checkChildBox(childRows[i]);
			}
		}
	}
	function checkParentBox(node){
		var rowData = $("#itemsList").getRowData($(node).val());
		var checkboxs=$("input[type='checkbox']",$("#itemsList"));
		if(rowData.treelevel==2 || $(rowData.treelevel).text()==2 || rowData.treelevel==3 || $(rowData.treelevel).text()==3){
			$.each(checkboxs,function(i,n){
				var thisRowData = $("#itemsList").getRowData($(n).val());
				if(thisRowData.id==rowData.parentId){
					if($(node).attr("checked")){
						$("input[type='checkbox'][value="+thisRowData.id+"]",$("#itemsList")).attr("checked","checked");
					}
					if(rowData.treelevel<3 || $(rowData.treelevel).text()<3){
						checkParentBox($(n));
					}
				}
			});
		}
	}

	function judgePrevNext(splitId){
		var msg="";
		if(splitId % 2 == 0 ){
			var prev = parseFloat($("#score" + splitId).prev().val());
			var own =  parseFloat($("#score" + splitId).val());
			if(own > prev || own == prev){
				msg = "不能输入比" + prev + "大的或相等的值";
				showInputErrorMessage(splitId , msg);
				arrayTrueFalse[splitId - 1] = false;
				arrayMessage[splitId - 1] = " " + msg;
				return false;
			}else{
				arrayTrueFalse[splitId - 1] = true;
				arrayMessage[splitId - 1] = "";
				return true;
			}
		}else{
			var next = parseFloat($("#score" + splitId).next().val());
			var own =  parseFloat($("#score" + splitId).val());
			if(own < next || own == next){
				msg = "不能输入比" + next + "或小的值";
				showInputErrorMessage(splitId , msg);
				arrayTrueFalse[splitId - 1] = false;
				arrayMessage[splitId - 1] = " " + msg;
				return false;
			}else{
				arrayTrueFalse[splitId - 1] = true;
				arrayMessage[splitId - 1] = "";
				return true;
			}
		}
	}
	function judgeScore(id){
		if(id%2 == 0 && id != 0){
			var nextId = parseInt(id) + 1;
			var val = $("#score" + id).val();
			$("#score" + nextId).val(val);
		}
	}
	function isHaveTemplate(){
		if($("#templates").val() == ""){
			$("#score1").val(0);
			$("#score2").val(0);
			$("#score3").val(0);
			$("#score4").val(0);
			$("#score5").val(0);
			$("#score6").val(0);
			$("#score7").val(0);
			$("#score8").val(0);
		}else{
			$("#score2").val("");
			$("#score3").val("");
			$("#score4").val("");
			$("#score5").val("");
			$("#score6").val("");
			$("#score7").val("");
		}
	}
	$(document).ready(function(){
		$("#evaluateyear").val($("#getEvaluateYear").val())
		isHaveTemplate();
		jQuery.validator.addMethod("isPositive", function(value, element){
			if("" != value && null != value){
				if(parseFloat(value) < 0){
					return false
				}
			}
			return true; 
		});
		$("#score1").inputtip();
		$.each($("input[id*=score]"),function(i,n){
			$(n).keyup(function(){
				judgeScore(id);
				id = $(n).attr("id").substring(5);
				var judgeP = judgePrevNext(id);
				var judgeG = judgeGroup(id);
			});
		});
		$.each($("input[id*=score]"),function(i,n){
			$(n).keydown(function(){
				$(".inputName").parents(".tip-error").remove();
			});
		});
		$("#itemsList").jqTreeGrid({
		   	 url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action?evaluate.id="+$("#templates").val(),
			 height : 300,
			 width : 720,
		   	 colModel:[
				{name:"check",label:"选择",sortable:false,width: 10,formatter:chooseRulesModule},
		   		{name:"content",label:"考核评估细则",sortable:false},
		   		{name:"standardScore",label:"标准分",width:30,sortable:false},
		   		{name:"id", width:0	,key:true,sortable:false,hidden:true}
		   	],
		   	treeReader : {
		   	   	level_field: "treelevel",
				parent_id_field: "parentId",
				leaf_field: "leaf",
				expanded_field:"expanded"
		   	},
		   	expandIndex:1,
			loadComplete:function(){
				$("input[type='checkbox']",$("#itemsList")).click(calculateTotalScore);
				calculateFirstTreeLevelTotalScore();
				$("#score1").val($("#standardEvaluateTotalScore").text());
				$("#score8").val(0);
			}
	   	});
	   	$("#templates").change(function(){
	   		$("#itemsList").setPostData({"evaluate.id":$("#templates").val()});
	   		$("#itemsList").setGridParam({url:PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action"});
	   		$("#itemsList").trigger("reloadGrid");
	   		isHaveTemplate();
	   	});
	   	$("#maintainRuleSettingsForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				//if(!$("input[name='detailedRuleIds']:checked").length>0){
				//	$.messageBox({message:"必须选中一条考核细则",level:"error"});
				//	return;
				//}
				judgeGroup();
		   		if($("#score1").val() != 0 && $("#score2").val() == 0){
					$.messageBox({message:"请填写完毕考核打分等级后再进行保存!" , level:"error" });
					return;
				}
				if($("#score1").val() != 0 && (($("#score1").val() == $("#score2").val()) || ($("#score2").val() == $("#score4").val()) 
						|| $("#score4").val() == $("#score6").val() || $("#score6").val() == $("#score8").val())){
					$.messageBox({message:"请不要让两个考核层级的分数一致",level:"error"});
					return;
				}
				for(var i = 0 ; i < 8 ; i ++ ){
					if(arrayTrueFalse[i] == false || arrayTrueFalse2[i] == false){
						var msg = arrayMessage[i];
						var msg2 = arrayMessage2[i];
						var j = i + 1;
						var defaultOption={
								content:"<div class='inputName' id=inputName" + j + "><span class='error'></span>" + msg + " ; " + msg2 + "</div>"
						}
						defaultOption.alignX="bottom";
						defaultOption.alignY="center";
						$("#score" + j).dialogtip(defaultOption);
						$("#score" + j).poshytip('show');
						return;
					}
				}
				if($("#templates").val() == "" || $("#templates").val() == null){
					$.confirm({
				 		level: "info",//TODO 确认 warn 警告,alert,info
					    message: "确定新建无模板的考核标准吗？",
					    title:"考核标准新建确认",
					    okFunc: function(){
							$("#loading_contact").css("display","block");
							$.dialogLoading("open")
					    		$(form).ajaxSubmit({
									success:function(data){
					    				$.dialogLoading("close")
					    				$("#loading_contact").css("display","none");
					    				var yearInSelect = $("#getEvaluateYear").val()+"";
					    				if((null == yearInSelect) || ("" == yearInSelect) || (yearInSelect == data.year)){
						    				$("#evaluateList").addRowData(data.id ,data ,"first");
						    				$("#evaluateList").setSelection(data.id);
						    			}
					    				var isHaveYear = false;
										//判断下拉列表中是否包含指定的年限
					    				$($("#getEvaluateYear").children()).each(function(i){
											if($(this).val() == data.year){
												isHaveYear = true;
											}
								    	});
										//向下拉列表中加入指定的年限
								    	if(!isHaveYear){
											$("#getEvaluateYear").append('<option value="' + data.year + '">' + data.year + '</option>');
								    	}
								    	if($("#score1").val() == "0" || $("#score1").val() == null){
									    	$.messageBox({message:"新增无考核标准的模板成功 !"});
									    }else{
										    $.messageBox({message:"复制考核标准成功!"})
										}
										$("#evaluateDialog").dialog("close")
								    }
								})
						},
					    cancelFunc:false,
					    cancelBtnName:"取消",
					    okbtnName :"确定"
					});
				}else{
					$.confirm({
				 		level: "info",//TODO 确认 warn 警告,alert,info
					    message: "确定复制考核标准吗？",
					    title:"考核标准新建确认",
					    okFunc: function(){
							$("#loading_contact").css("display","block");
							$.dialogLoading("open")
					    		$(form).ajaxSubmit({
									success:function(data){
					    				$.dialogLoading("close")
					    				$("#loading_contact").css("display","none");
					    				var yearInSelect = $("#getEvaluateYear").val()+"";
					    				if((null == yearInSelect) || ("" == yearInSelect) || (yearInSelect == data.year)){
						    				$("#evaluateList").addRowData(data.id ,data ,"first");
						    				$("#evaluateList").setSelection(data.id);
						    			}
					    				var isHaveYear = false;
										//判断下拉列表中是否包含指定的年限
					    				$($("#getEvaluateYear").children()).each(function(i){
											if($(this).val() == data.year){
												isHaveYear = true;
											}
								    	});
										//向下拉列表中加入指定的年限
								    	if(!isHaveYear){
											$("#getEvaluateYear").append('<option value="' + data.year + '">' + data.year + '</option>');
								    	}
										$.messageBox({message:"复制考核标准成功!"})
										$("#evaluateDialog").dialog("close")
								    }
								})
						},
					    cancelFunc:false,
					    cancelBtnName:"取消",
					    okbtnName :"确定"
					});
				}
			},
			rules:{
				"evaluate.title":{
					required:true,
					exculdeParticalChar:true,
					minlength:2
				},
				"great.startScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"great.endScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"good.startScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"good.endScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"qualified.startScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"qualified.endScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"failed.startScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				},
				"failed.endScore":{
					isPositive:true,
					required:true,
					nonNegativeInteger:true,
					exculdeParticalChar:true,
					minlength:1,
					maxlength:10
				}
			},
			messages:{
				"evaluate.title":{
					required:"请输入标题",
					exculdeParticalChar:"标题只能输入字母，数字和中文字符",
					minlength:$.format("标题至少需要输入{0}个字符")
				},
				"great.startScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"great.endScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"good.startScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"good.endScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"qualified.startScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"qualified.endScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"failed.startScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				},
				"failed.endScore":{
					isPositive:"请不要输入负数",
					required:"此处需要填入数据",
					nonNegativeInteger:"此处必须为正整数",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				}
			}
		});
	});
	function chooseRulesModule(el,options,rowData){
		var bchk = " checked='checked' ";
		var submitName = " name=\"detailedRuleIds\" value='"+rowData.id+"'";

	    return "<input type=\"checkbox\" " + submitName + bchk  + "/>";
	}
}