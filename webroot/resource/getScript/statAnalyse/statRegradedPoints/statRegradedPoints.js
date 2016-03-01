TQ.statRegradedPoints = function (dfop){
	function referenceValue(){
	    return 100;
	}

	function defaultValue(el,options,rowData){
	    if(rowData.amoutPoint == null || rowData.amoutPoint < 0)
	        return 0;
	    else 
	        return rowData.amoutPoint;
	}
	$(document).ready(function(){
    	//onYearChange();
        $("#statRegradedPointsList").jqGridFunction({
            datatype: "local",
            sortname:'vOrg.id',
            sortorder:"asc",
            colModel:[
                {name:"org.id",key:true,hidden:true},
                {name:"org",hidden:true},
                {name:"encryptId",index:'id',frozen:true,hidden:true},
                {name:"orgName",label:"单位",index:"vOrg.id",width:120},
                {name:"yellowAmout",label:"黄牌扣分",align:"right",sortable:false,width:90},
                {name:"redAmout",label:"红牌扣分",align:"right",sortable:false,width:90},
                {name:"deductPointByPerson",label:"人工扣分",align:"right",sortable:false,width:90},
                {name:"addPointByPerson",label:"人工加分",align:"right",sortable:false,width:90},
                {name:"assessmentUser",label:"其他打分",align:"right",sortable:false,width:90},
                {name:"integratedIndicator",label:"综合指标",align:"right",sortable:false,width:90},
                {name:"",label:"基准分",align:"right",sortable:false,width:90,formatter:referenceValue},
                {name:"amoutPoint",label:"总得分",align:"right",sortable:false,formatter:defaultValue,width:90}
//                 ,{name:"audited",label:"是否已审核",sortable:false,formatter:auditedFormater}
            ],
            pager:false,
            rowNum:Math.pow(2,10),
            ondblClickRow:viewRegradedDetail,
            loadComplete:loadAddButton,
            onSelectRow:showButtons
        });
        initMonthVal();
        if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
            onOrgChanged(getCurrentOrgId(),isGrid());
        }
        $("#search").click(function(){
            onOrgChanged(getCurrentOrgId(),isGrid());
        });
        $("#reload").click(function(){
            $("#statRegradedPointsList").trigger("reloadGrid");
        });

        $("#add").click(function(event){
            var selectId = $("#statRegradedPointsList").getGridParam("selrow");
            if(selectId == null){
            	$.messageBox({level : 'warn',message:"请先选择一条数据，再进行打分！"});
            	return;
            }
            var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
            if(statRegradedPoint.audited && statRegradedPoint.audited == "已审核"){return;}
            $("#statRegradedPointsDialog").createDialog({
                width:450,
                height:240,
                title:"给部门打分",
                url:PATH+"/regradedPoints/regradedPointsManage/dispatch.action",
                postData:{
                    orgId:statRegradedPoint.encryptId
                },
                buttons:{
                    "保存" : function(){
                    	if($("#maintainForm").valid()){
                    		if($("input:radio:checked").val()=="-1" && parseInt($("#points").val())>statRegradedPoint.amoutPoint){
                    			$.messageBox({level : 'warn',message:"扣分的打分分值不能大于总得分！"});
	                    		return;
	                    	}
                    	}
                    	$("#maintainForm").submit();
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                }
            });  
        });
        $("#view").click(function(event){
        	viewRegradedDetail();
        });

        $("#audit").click(function(event){
            var selectId = $("#statRegradedPointsList").getGridParam("selrow");
            if(selectId == null){return;}
            var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
            if(statRegradedPoint.audited && statRegradedPoint.audited == "已审核" || ($("#statYear").val() >= dfop.nowYearValue && $("#statMonth").val() >= dfop.nowMonthValue)){return;}
            $.confirm({
                title:"确认审核部门：" + statRegradedPoint.orgName + "的得分",
                message:"该部门得分被审核后，将不再允许对该部门进行打分!",
                width:400,
                okFunc:auditStatRegradedPoint
            });
        });
        
        $("#print").click(function(){
        	var reoprtDateTypeId = $("#reoprtDateType").val();
        	var year = $("#statYear").val();
    		$("#statRegradedPointsDialog").createDialog({
    			width:700,
    			height:550,
    			title:"打印部门绩效考核",
    			url:PATH+"/statAnalyse/statRegradedPoints/statRegradedPointsPrint.jsp?reoprtDateTypeId=" + reoprtDateTypeId + "&year=" + year + "&month=" + getMonthValue() + "&internalId=" + dfop.internalIdValue,
    			buttons: {
    			   "打印" : function(){
    					$("#statRegradedPointsPrint").printArea();
    		        	$(this).dialog("close");
    		   		},
    			   "关闭" : function(){
    			        $("#statRegradedPointsDialog").dialog("close");
    			   }
    			}
    		});
    	});
        
        $("#export").click(function(event){
    		if($("#statRegradedPointsList").getGridParam("records")>0){
    			var postData = $("#statRegradedPointsList").getPostData();
    			$("#statRegradedPointsDialog").createDialog({
    				width: 250,
    				height: 200,
    				title:"导出部门考核信息",
    				url:PATH+'/common/exportExcel.jsp',
    				postData:{
    					gridName:'statRegradedPointsList',
    					downloadUrl:PATH+'/statAnalyse/statRegradedPointManage/downloadRegradedPoints.action'
    				},
    				buttons: {
    		   			"导出" : function(event){
    						exceldownloadSubmitForm();
    		        		$(this).dialog("close");
    	   				},
    		   			"关闭" : function(){
    		        		$(this).dialog("close");
    		   			}
    				},
    				shouldEmptyHtml:false
    			});
    		}else{
    			$.messageBox({level : 'warn',message:"列表中没有数据，不能导出！"});
    			return;
    		}
    	});
        
        $("#createTable").click(function(event){
        	$.ajax({
        		async : true,
        		url:PATH+"/statAnalyse/statRegradedPointManage/createRegradedPointStatTable.action?year="+$("#statYear").val()+"&month="+getMonthValue(),
        		success : function(data) {
        			if(data==true||"true"==data){
        				$.messageBox({message:"创建统计表"});
        			}else{
        				$.messageBox({message:data,level:"error"});
        			}
        		}
        	 });
    	});
        
        $("#reoprtDateType").change(function(){
    		$("#statMonth").empty();
    		initMonthVal();
    	});
        
        $("#statYear").change(function(){
    		$("#statMonth").empty();
    		initMonthVal();
    	});
        
    });

    function initMonthVal() {
    	$.ajax({
    		async: false,
    		url: PATH+"/statAnalyse/statRegradedPointManage/getLastMonthTypeList.action?reoprtDateType.id="+$("#reoprtDateType").val()+"&year="+$("#statYear").val(),
    		success:function(responseData){
    			if(!responseData.length) {
    				$("#statMonth").append("<option value='1'>1</option>");
    				$("#statMonth").hide();
    			} else {
    				$("#statMonth").show();
    			}
    			for(var i = 0;i<responseData.length;i++){
    				$("#statMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
    			}
    		}
    	});
    	//$("#statYear").val(${nowYear});
    	//$("#statMonth").val(${nowMonth});
    }
    
    function getMonthValue() {
    	if ($("#statMonth").val() == "第1季度") {
    		return 1;
    	} else if ($("#statMonth").val() == "第2季度") {
    		return 4;
    	} else if ($("#statMonth").val() == "第3季度") {
    		return 7;
    	} else if ($("#statMonth").val() == "第4季度") {
    		return 10;
    	} else return $("#statMonth").val();
    }
    
    function viewRegradedDetail(){
    	var reoprtDateTypeId = $("#reoprtDateType").val();
    	var year = $("#statYear").val();
    	var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){
        	$.messageBox({level : 'warn',message:"请选择需要查看详情的部门！"});
        	return;
        }
        var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
        $("#statRegradedPointsDialog").createDialog({
            width:900,
            height:500,
            title:"查看打分详情",
            url:PATH+"/statAnalyse/statRegradedPoints/viewSubDetailList.jsp?reoprtDateTypeId=" + reoprtDateTypeId + "&year=" + year + "&month=" + getMonthValue() + "&orgId=" + statRegradedPoint.encryptId,
            buttons:{
                "关闭" : function(){
                    $(this).dialog("close"); 
               }
            },
            shouldEmptyHtml:false
        });
    }

    function onOrgChanged(orgId,isgrid){
        $("#statRegradedPointsList").setGridParam({
            url:PATH+"/statAnalyse/statRegradedPointManage/statRegradedPoints.action",
            datatype:"json"
        });
        $("#statRegradedPointsList").setPostData({
            "year":function(){return $("#statYear").val();},
            "month":getMonthValue(),
            "reoprtDateType.id":function(){return $("#reoprtDateType").val();},
            "internalId":dfop.internalIdValue,
            "targeOrgId":function(){return orgId;}
        });
        $("#statRegradedPointsList").trigger("reloadGrid");
    }



    function getStatDate(){
    	var statMonth;
    	if($("#statMonth").val()<10){
        	statMonth="0"+$("#statMonth").val();
        }else{
            statMonth=$("#statMonth").val();
        }
        return $("#statYear").val() + "" + statMonth;
    }

    function loadAddButton(){ 
        //$("#view").buttonDisable();
        //$("#add").buttonDisable();
        //$("#audit").buttonDisable();
    }

    function showButtons(){
        $("#view").buttonEnable();
        var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){return;}
        var statRegradedPoints = $("#statRegradedPointsList").getRowData(selectId);
        
        if(statRegradedPoints.audited && statRegradedPoints.audited == "已审核"){
        	$("#add").buttonDisable();
            $("#audit").buttonDisable();
        }else{
        	$("#add").buttonEnable();
        	if($("#statYear").val() >= dfop.nowYearValue && $("#statMonth").val() >= dfop.nowMonthValue)
        		$("#audit").buttonDisable();
        	else
        	    $("#audit").buttonEnable();
        }
    }

    function auditedFormater(el,options,rowData){
        if(rowData.audited)
            return "已审核";
        else
            return "未审核";
    }

    function auditStatRegradedPoint(){
    	var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){return;}
        var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
        $.ajax({
            url:PATH+"/statAnalyse/statRegradedPointManage/auditStatRegradedPoints.action",
            data:{
            	    "statRegradedPoint.parentOrg.id":getCurrentOrgId(),
            	    "statRegradedPoint.org.id":selectId,
            	    "statRegradedPoint.orgName":statRegradedPoint.orgName,
            	    "statRegradedPoint.yellowAmout":statRegradedPoint.yellowAmout,
            	    "statRegradedPoint.redAmout":statRegradedPoint.redAmout,
            	    "statRegradedPoint.deductPointByPerson":statRegradedPoint.deductPointByPerson,
            	    "statRegradedPoint.addPointByPerson":statRegradedPoint.addPointByPerson,
            	    "statRegradedPoint.assessmentUser":statRegradedPoint.assessmentUser,
            	    "statRegradedPoint.amoutPoint":statRegradedPoint.amoutPoint,
            	    "statRegradedPoint.audited":true,
            	    "statRegradedPoint.year":function(){return $("#statYear").val();},
            	    "statRegradedPoint.month":function(){return $("#statMonth").val();}
                },
            success:function(data){
                	$("#statRegradedPointsList").setRowData(selectId,data);
                    showButtons();
                }
        });
    }

}
