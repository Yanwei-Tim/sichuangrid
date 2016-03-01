// 绑定到JQ上面
TQ.commonStatistics = function(dfop){
		var populationType = dfop.populationType;
    	// 一些公用的变量和方法
    	var title = document.title;
    	var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
    	var tableHeight=wrapHeight-50;
    	$(".leaderTit").each(function(){
    		$(this).text(document.title+$(this).text());
    	});

    	function onOrgChanged(){
    		$("#areaDatatable").setGridParam({
    			url:PATH+"/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action",
    			datatype:"json",
    			page:1,
    			loadComplete:function(){
    				var areaContainerChart = $("#areaContainer").columnChart({
    					ajax:false,
    					table:document.getElementById('gbox_areaDatatable')
    				});
    				areaContainerChart.series[0].hide();
    				areaContainerChart.series[1].hide();
    				//areaContainerChart.series[2].hide();
    				$("#areaDatatableSun").empty();
    				$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
    				$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
    				$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
    			}
    		});
    		$("#areaDatatable").setPostData({
    	    	"orgId":getCurrentOrgId(),
    	    	"tableName":populationType
    	   	});

    		$("#areaDatatable").trigger("reloadGrid");
    		getBaseInfoSummary();
    	}
    	
    	function getBaseInfoSummary(){
    		$("#dataMonthTable").setGridParam({
    			url:PATH+"/baseinfo/leaderViewManage/statisticsPopulationSummary.action",
    			datatype:"json",
    			page:1,
    			loadComplete:function(){
    				var personGeneralConditionLine = $("#containerSummary").lineChart({
    					ajax:false,
    					table:document.getElementById('gbox_dataMonthTable'),
    					isChangeColor:true
    				});
    				personGeneralConditionLine.series[1].hide();
    				//personGeneralConditionLine.series[2].hide();
    			}
    		});
    		$("#dataMonthTable").setPostData({
    	    	"orgId":getCurrentOrgId(),
    	    	"tableName":populationType
    	   	});
    		$("#dataMonthTable").trigger("reloadGrid");
    	}

        function getDialogParam(isSearch){
    		return {
    			width: 900,
    			height: 600,
    			title: title+'信息',
    			modal:true,
    			url: PATH+'/baseinfo/householdPopulation/householdStaffStatistics.jsp?isSearch='+isSearch,
    			buttons: {
    		   		"关闭" : function(){
    		        	$(this).dialog("close");
    		   		}
    			}
    		};
    	}
    	
        
		$("#areaDatatable").jqGridFunction({
			rowNum:100,
			datatype:"local",	
			width:375,
			height:'auto',
			colModel:[
		  		{name:'id',index:'id',hidden:true},
		  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false},
		 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false},
		 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
		 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false}
				//{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
			],
			autowidth:false,
			shrinkToFit:true,
			gridComplete:function(){
				$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
			}
		});
		$("#dataMonthTable").jqGridFunction({
			datatype:"local",	
			width:375,
			height:tableHeight,
			colModel:[
		  		{name:'id',index:'id',hidden:true},
		  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false},
		 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
		 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false}
				//{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
			],
			autowidth:false,
			shrinkToFit:true
		});	
	
		if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
			onOrgChanged();
		}
		
		if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
			getBaseInfoSummary();
		}
		var layout=function(){
			var wrapWidth=$(".ui-layout-center").width()-400;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth);
		}
		layout();
		$(window).resize(function(){
			layout();
		})
		$("#refreshOrgTree1").click(function(){
			var searchConditionStr="请输入姓名或身份证号码";
			if(populationType=="overseaStaff"){
				searchConditionStr="请输入英文名或证件号码";
			}
			$("#searchByCondition").attr("value",searchConditionStr);
			$("#searchText").attr("value",searchConditionStr);
		});
		
		$("#searchHighA").click(function(){
			$("#householdStaffPopulationDialog").createDialog({
				width: 650,
				height: 330,
				title: title+'查询-请输入查询条件',
				modal:true,
				url: PATH+'/baseinfo/householdPopulation/searchHouseholdStaffDlg.jsp',
				buttons: {
					"查询" : function(event){
						$("#statisticsListDialog").createDialog(getDialogParam(1));
				   },
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#searchByIdcardA").click(function(){
			if($("#searchByIdC").val()=="请输入身份证号码"){
				$.notice({
					level: 'alert',
				    okbtnName: "确定",
				    title:'提示',
				    message: "请输入身份证号码"
				});
				return;
			};
			$("#statisticsListDialog").createDialog(getDialogParam(0));
		});

};