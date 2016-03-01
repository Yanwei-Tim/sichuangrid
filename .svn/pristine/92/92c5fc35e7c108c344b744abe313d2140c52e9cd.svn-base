TQ.updateStandardEvaluateDlg = function (dfop){
	function judgeGroup(){
		for(var i = 0 ; i < 8 ; i ++){
			for(var j = 0 ; j < i ; j++){
				var jj = j + 1;
				var ii = i + 1;
				var x = parseFloat($("#score" + jj).val())
				var y = parseFloat($("#score" + ii).val())
				if(y > x){
					arrayMessage2[i] = "此处不能输入比" + x + "大的值！";
					arrayTrueFalse2[i] = false;
					showInputErrorMessage(ii , arrayMessage2[i])
					break;
				}else{
					arrayMessage2[i] = "";
					arrayTrueFalse2[i] = true;
				}
			}
		}
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

	function showInputErrorMessage(splitId,msg){
		var defaultOption={
				content:"<div class='inputName' id=inputName" + splitId + "><span class='error'></span>" + msg + "</div>"
		}
		defaultOption.alignX="bottom";
		defaultOption.alignY="center";
		$("#score" + splitId).dialogtip(defaultOption);
		$("#score" + splitId).poshytip('show');
		$(".inputName").css("cursor","pointer");
		$(".inputName").parent().parent().click(function(a){
			$(".inputName").parents(".tip-error").remove();
		})
	}
	function calculateTotalScore(){
		var totalScore=0;
		var checkboxs=$("tr",$("#itemsList"));
		$.each(checkboxs,function(i,n){
			if($(n).attr("id")){
				var rowData = $("#itemsList").getRowData($(n).attr("id"));
				if(rowData.treelevel==1 || $(rowData.treelevel).text()==1)
					if("" != rowData.standardScore && null != rowData.standardScore)
					totalScore=totalScore+parseFloat(rowData.standardScore);
			}
		})
		$("#standardEvaluateTotalScore").text(totalScore);
	}
	function judgePrevNext(splitId){
		var msg="";
		if(splitId % 2 == 0 ){
			var prev = parseFloat($("#score" + splitId).prev().val());
			var own =  parseFloat($("#score" + splitId).val());
			if(own > prev){
				msg = "不能输入比" + prev + "大的值";
				showInputErrorMessage(splitId , msg);
				arrayTrueFalse[splitId - 1] = false;
				arrayMessage[splitId - 1] = " " + msg;
			}else{
				arrayTrueFalse[splitId - 1] = true;
				arrayMessage[splitId - 1] = "";
			}
		}else{
			var next = parseFloat($("#score" + splitId).next().val());
			var own =  parseFloat($("#score" + splitId).val());
			if(own < next){
				msg = "不能输入比" + next + "或小的值";
				showInputErrorMessage(splitId , msg);
				arrayTrueFalse[splitId - 1] = false;
				arrayMessage[splitId - 1] = " " + msg;
			}else{
				arrayTrueFalse[splitId - 1] = true;
				arrayMessage[splitId - 1] = "";
			}
		}
	}
	function ruleTypeDataFormatter(el,options,rowData){
		return ruleTypeData[rowData.ruleType];
	}
	function getTreeGrid()
	{
		$("#itemsList").jqTreeGrid({
		   	 url: PATH+"/evaluate/evaluateManage/getDetailedRulesByEvaluateId.action?evaluate.id=" +dfop.evaluateId,
			 height : 260,
			 width : 860,
		   	 colModel:[
		   		{name:"content",label:"考核评估细则",sortable:false},
		   		{name:"standardScore",label:"标准分",width:30,sortable:false},
		   		{name:"ruleType",label:"类型",width:20,sortable:false,formatter: ruleTypeDataFormatter},
		   		{name:"id", width:0	,key:true,sortable:false,hidden:true}
		   	 ],
		   	 
		   	 treeReader : {
		   	   	level_field: "treelevel",
				parent_id_field: "parentId",
				leaf_field: "leaf",
				expanded_field:"expanded"
		   	 },
			loadComplete:function(){
				$("input[type='checkbox']",$("#itemsList")).click(calculateTotalScore);
				calculateTotalScore();
				$("#score1").val($("#standardEvaluateTotalScore").text());
				afterCalculateTotalScore();
				$("#score8").val(0);
			}
		   });
		}
	function afterCalculateTotalScore(){
		if(Number($("#score1").val()) < Number($("#score2").val())){
			$("#score2").val(0);
			$("#score3").val(0);
			$("#score4").val(0);
			$("#score5").val(0);
			$("#score6").val(0);
			$("#score7").val(0);
		}

		if(!isFirst){
			showValidateMessage = false;
			//$("#standForm").submit();
		}
	}
	function deleteRule(){
		var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
		if(rowId == null){
			$.messageBox({message:"请先选择一条记录" ,level:"warn"});
			return;
		}
		$.ajax({
			url:PATH+"/evaluate/evaluateManage/deleteDetailedRuleById.action?detailedRule.id="+rowId,
			success:function(data){
				if(data == true){
					reload();
					$.messageBox({ message:"成功删除细则!"});
					return;
				}
	            $.messageBox({
					message:data,
					level: "error"
				});
	        }
		});
	}
	
	function judgeScore(id){
		if(id%2 == 0 && id != 0){
			var nextId = parseInt(id) + 1;
			var val = $("#score" + id).val();
			$("#score" + nextId).val(val);
		}
	}
	function validateIsSelfEvaluate(){
		var idCollection = $("#itemsList").getDataIDs();
		if(idCollection.length<1){
			return false;
		}
		for(var i=0;i<idCollection.length;i++){
	        var ent =  $("#itemsList").getRowData(idCollection[i]);
	        if(ent.leaf == 'true' || ent.leaf==true){
	            if(ent.standardScore != "" ){
	            	$("#"+idCollection[i]).css('background','red');
					return true;
	            }
			}
		}
		return false;
	}
	$(document).ready(function(){
		$("#evaluateyear").val(dfop.evaluateYear);
		jQuery.validator.addMethod("isPositive", function(value, element){
			if("" != value && null != value){
				if(parseFloat(value) < 0){
					return false
				}
			}
			return true;
		});
		$("#score2").inputtip();
		$.each($("input[id*=score]"),function(i,n){
			$(n).focusout(function(){
				id = $(n).attr("id").substring(5);
				judgeScore(id);
				judgePrevNext(id)
				judgeGroup(id)

			});
		});
		$.each($("input[id*=score]"),function(i,n){
			$(n).keyup(function(){
				id = $(n).attr("id").substring(5);
				if(id%2 == 0 && id != 0){
					var nextId = parseInt(id) + 1;
					var val = $("#score" + id).val();
					$("#score" + nextId).val(val);
				}
			});
		});
		isFirst = true;
		getTreeGrid();
		$("#add-firstDetailedRule").click(function(){
			if(clickDisabled("add-firstDetailedRule")){
				return;
			}
			$("#rulesDialog").createDialog({
				width:480,
				height:300,
				title:'新增考核细则',
				modal : true,
				url:PATH+'/evaluate/evaluateManage/dispatchDetailedRule.action?mode=add&detailedRule.evaluate.id=' + dfop.evaluateId+ '&level=4&typeOfDetailedRule=0',
				buttons :{
					"保存" : function(){
						$("#routes-form").submit();
					},
					"取消" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
		$("#add-detailedRule").click(function(event){
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({message:"请先选择一条记录" ,level:"warn"});
				return;
			}
			var itemsList =  $("#itemsList").getRowData(rowId);
			var level = $("td[aria-describedby='itemsList_treelevel']" , $("#"+ itemsList.id)).text();
			if(!level){
				level = 0;
			}
			if(null == itemsList.standardScore || "" == itemsList.standardScore){
				$.messageBox({message:"无法再增加子细则，已到最后一层细则" ,level:"warn"});
				return;
			}
			$.ajax({
				url:PATH+"/evaluate/evaluateManage/validateTypeOfChildDetailedRule.action?detailedRule.id="+rowId,
				success:function(data){
					$("#rulesDialog").createDialog({
						width:480,
						height:300,
						title:'新增考核细则',
						modal : true,
						url:PATH+"/evaluate/evaluateManage/dispatchDetailedRule.action?mode=add&detailedRule.evaluate.id="+dfop.evaluateId+"&level="+level+'&typeOfDetailedRule='+data.type,
						buttons :{
							"保存" : function(){
								$("#routes-form").submit();
							},
							"取消" : function(){
								$(this).dialog("close");
							}
						}
					});
		        }
			});
			return;
		});
		$("#update-detailedRule").click(function(event){
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({message:"请先选择一条记录" ,level:"warn"});
				return;
			}
			var itemsList =  $("#itemsList").getRowData(rowId);
			var level = $("td[aria-describedby='itemsList_treelevel']" , $("#"+ itemsList.id)).text();
			if(!level){
				level = 0;
			}
			$.ajax({
				url:PATH+"/evaluate/evaluateManage/validateTypeOfChildDetailedRule.action?detailedRule.id="+itemsList.parentId,
				success:function(data){
					var urlDispatch = PATH+"/evaluate/evaluateManage/dispatchDetailedRule.action?detailedRule.id="+rowId+"&mode=edit&level="+level+"&standardScore="+itemsList.standardScore;
					if(null != data){
						urlDispatch = PATH+"/evaluate/evaluateManage/dispatchDetailedRule.action?detailedRule.id="+rowId+"&mode=edit&level="+level+"&typeOfDetailedRule=" + data.type +"&standardScore="+itemsList.standardScore;
					}
					$("#rulesDialog").createDialog({
						width:480,
						height:300,
						title:'修改考核细则',
						modal : true,
						url:urlDispatch,
						buttons :{
							"保存" : function(){
								$("#routes-form").submit();
							},
							"取消" : function(){
								$(this).dialog("close");
							}
						}
					});
		        }
			});
			return;

		});
		$("#delete-detailedRule").click(function(event){
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({message:"请先选择一条记录" ,level:"warn"});
				return;
			}
			$.confirm({
				title:"确认删除",
				message:"细则删除后，将无法恢复,您确认删除该细则吗?",
				width: 400,
				okFunc: deleteRule
			});

		});
		$("#view-detailedRule").click(function(event){
			var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
			if(rowId == null){
				$.messageBox({message:"请先选择一条记录" ,level:"warn"});
				return;
			}
			var itemsList =  $("#itemsList").getRowData(rowId);
			var level = $("td[aria-describedby='itemsList_treelevel']" , $("#"+ itemsList.id)).text();
			if(!level){
				level = 0;
			}
			$("#rulesDialog").createDialog({
				width:480,
				height:300,
				title:'查看考核细则',
				modal : true,
				url:PATH+"/evaluate/evaluateManage/dispatchDetailedRule.action?detailedRule.id="+rowId+"&mode=view&level="+level,
				buttons :{
					"取消" : function(){
						$(this).dialog("close");
					}
				}
			});
			return;


		});
	});

	function clickRecord(doom){
		var rowid = ($(doom).parents("tr:first").attr("id"));
	}

	function afterLoad(){
		disableButtons();
	}

	function disableButtons(){
		$("#update-detailedRule").buttonDisable();
		$("#view-detailedRule").buttonDisable();
		$("#delete-detailedRule").buttonDisable();
	}
	function clickDisabled(name){
		var id="#"+name;
		var isDisabled=$(id).attr("disabled");
		if(isDisabled=="disabled"){
			return true;
		}
	}
	function selectRow(){
		var rowId = $("#itemsList").jqGrid('getGridParam','selrow');
		var itemsList =  $("#itemsList").getRowData(rowId);
		if(itemsList.treelevel==3 || $(itemsList.treelevel).text()==3){
			$("#add-detailedRule").buttonDisable();
		}else{
			$("#add-detailedRule").buttonEnable();
		}
		$("#view-detailedRule").buttonEnable();
		$("#update-detailedRule").buttonEnable();
		$("#delete-detailedRule").buttonEnable();
	}

	function resetEvaluateListRowData(){
		$("#evaluateList").setRowData(dfop.evaluateId,{title:$("#evaluate-title").val(),year:$("#evaluateyear").val(),templateTotalScore:$("#standardEvaluateTotalScore").text()});
	}

	$(document).ready(function(){
		$("#standForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(){
				if(validateIsSelfEvaluate()){
					$.messageBox({message:"请设置考核细则！",level:"warn"});
					return;
				}
				if(parseFloat($("#score1").val()) < parseFloat($("#score2").val())){
					$.messageBox({message:"您给出细则总分太小,分数层级会有问题!",level:"warn"});
					return;
				}
				if($("#score2").val() == 0 && showValidateMessage){
					$.messageBox({message:"请填写完毕考核打分等级后再进行保存!" , level:"warn" });
					return;
				}
				if((($("#score1").val() == $("#score2").val()) || ($("#score2").val() == $("#score4").val())
						|| $("#score4").val() == $("#score6").val() || $("#score6").val() == $("#score8").val())
						&& showValidateMessage){
					$.messageBox({message:"请不要让两个考核层级的分数一致",level:"warn"});
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
				$("#scoreSum").val($("#standardEvaluateTotalScore").text());
				$("#standForm").ajaxSubmit({
					success:function(data){
						resetEvaluateListRowData();
						if(showValidateMessage){
							$.messageBox({message:"已经成功保存!"});
							$("#evaluateDialog").dialog("close");
						}
						showValidateMessage = true;
					}
				});

			},
			rules:{
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
					nonNegativeInteger:"此处必须为数字",
					exculdeParticalChar:"不能包含特殊字符以及数字",
					minlength:"最小长度为{0}",
					maxlength:"最大长度为{0}"
				}
			}
		});
	})
}