TQ.youthsList = function(){
	function search(orgId){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入姓名或身份证号码') return;
		var initParam = {
			"organizationId": orgId,
			"searchYouthsVo.keyType": $("#keyTypes").val(),
			"searchYouthsVo.logOut":0,
			"searchYouthsVo.isDeath":false,
			"searchYouthsVo.fastSearchKeyWords":condition
		}
		$("#youthsList").setGridParam({
			url:PATH+'/baseinfo/youthsManage/findYouthsList.action',
			datatype: "json",
			page:1
		});
		$("#youthsList").setPostData(initParam);
		$("#youthsList").trigger("reloadGrid");
	}
	function removeLoad(){
		$(".click_load .click_btn").hide();
	}
	
	$(document).ready(function(){

		function clickDisabled(name){
			var id="#"+name;
			var isDisabled=$(id).attr("disabled");
			if(isDisabled=="disabled"){
				return true;
			}
		}
		$("#contentDiv").click( function () { 
			removeLoad();
		}); 
		$("#searchByConditionButton").click(function(){
			search(getCurrentOrgId());

		});

		$("#refreshSearchKey").click(function(event){
			$("#searchByCondition").attr("value","请输入姓名或身份证号码");
		});

		$("#reload").click(function(event){
			$("#searchByCondition").attr("value","请输入姓名或身份证号码");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		
		$(".click_load .click_btn").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
			$(this).hide(100);
		});
		
		$("#youthsList").jqGridFunction({
			datatype: "local",
		   	colModel:[
	   	        {name:"rowNum", index:"rowNum",sortable:false, hidden:true,frozen:true,key:true},//这里的rowNum表示行号没实际意义,注意解决id相同选择不到数据的问题
		        {name:"id", index:"id",sortable:false, hidden:true,frozen:true},
		        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:"actualPopulationType", index:"actualPopulationType",hidedlg:true,hidden:true},
		        {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
		        {name:"operator", index:'id', label:'操作',hidedlg:true,hidden:true,formatter:operateFormatter,width:90,frozen:true,sortable:false,align:'center' },
		        {name:"name", index:'name', label:'姓名',formatter:youthsNameFont,width:80,sortable:false,frozen:true },
		        {name:'gender',formatter:genderFormatter, label:'性别', align:'center',sortable:false, width:50},
		        {name:'idCardNo',index:'idCardNo', label:'身份证号码', align:'center', width:160,sortable:false,frozen:true},
		        {name:'currentAddress', sortable:false, label:'常住地址', width:200 },      
		        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:200},
		        
		        {name:"usedName", index:'usedName', label:'曾用名/别名', width:95,sortable:false,hidden:true},
		        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:200,hidden:true},
		        {name:'mobileNumber',sortable:false, label:'联系手机', width:100,hidden:true},
		        {name:'telephone', sortable:false, label:'固定电话', width:120,hidden:true},
		        {name:"birthday", index:'birthday', sortable:false,label:'出生日期',align:'center', width:80 ,hidden:true},
		        
		        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80, hidden:true },
		        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:150},
		        {name:"schooling",sortable:false,label:"文化程度",formatter:schoolingFormatter,width:80, hidden:true },
		        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80,hidden:true},
		        {name:"maritalState",sortable:false,label:"婚姻状况",formatter:maritalStateFormatter,align:'center',width:80, hidden:true },
		        
		        {name:"stature",sortable:false,label:"身高(cm)",width:80,align:'center',hidden:true },
		        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80, hidden:true },
		        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80, hidden:true },
		        {name:"email",sortable:false,label:"电子邮箱",width:120, hidden:true },
		        {name:'province',index:'province',sortable:false,label:'户籍地',formatter:householdRegisterFormatter,width:150,hidden:true},
		        
		        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150},
		        {name:'otherAddress', sortable:false, label:'临时居所', width:150,hidden:true},
		        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		        {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80},
		        {name:'logoutDetail.logoutReason',sortable:false,hidden:true,hidedlg:true,width:80}
			],
			multiselect:true,
		  	onSelectAll:function(aRowids,status){},
		  	ondblClickRow:viewDialogForDbClik,
			loadComplete: afterLoad,
			onSelectRow: selectRow
		});
		jQuery("#youthsList").jqGrid('setFrozenColumns');
		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			//onOrgChanged(getCurrentOrgId(),isGrid());
		}

		$("#search").click(function(event){
			$("#youthsDialog").createDialog({
				width: 650,
				height: 300,
				title: title+'查询-请输入查询条件',
				url:PATH+'/baseinfo/youthsManage/dispatchOperate.action?mode=search&keyType='+$("#keyTypes").val(),
				buttons: {
					"查询" : function(event){
						if($("#searchYouthsForm").valid()){
							searchYouths();
							$(this).dialog("close");
						}
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		});

		$("#export").click(function(event){
			if ($("#youthsList").getGridParam("records")>0){
				var postData = $("#youthsList").getPostData();
				$("#youthsList").setPostData($.extend(postData,{"searchYouthsVo.logOut":0,"searchYouthsVo.isDeath":false
					,"keyType":$('#keyTypes').val()}));
				$("#youthsDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'youthsList',
						downloadUrl:PATH+'/baseinfo/youthsManage/downloadYouths.action'
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
				$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			}
		});
	    
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
	});

	function afterLoad(){
		logOutFormatter();
		disableButtons();
		checkExport();
		changeColor();
	}

	function changeColor(){
		if(notExecute==null||notExecute.length==0){
			return;
		}
		for(var i=0;i<notExecute.length;i++){
			var rowData=$("#youthsList").getRowData(notExecute[i]);
			$("#"+notExecute[i]).css('background','red');
			$("#youthsList").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}

	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#youthsList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#youthsList").getRowData(idCollection[i]);
				if(ent.logOut==1||ent.logOut=='1'){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}

	function checkExport(){
	}
	function disableButtons(){
	}
	function selectRow(){
		var count = $("#youthsList").jqGrid("getGridParam","records");
		var selectedCounts = getActualjqGridMultiSelectCount("youthsList");
		if(selectedCounts == count){
			jqGridMultiSelectState("youthsList", true);
		}else{
			jqGridMultiSelectState("youthsList", false);
		}
	}

	function searchYouths(){
		$("#youthsList").setGridParam({
			url:PATH+"/baseinfo/youthsManage/searchYouthsList.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchYouthsForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
	           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
	            dataJson[data[i].name] = data[i].value;
			}
		}
		$("#youthsList").setPostData($.extend({"organizationId":getCurrentOrgId()},dataJson));
	    $("#youthsList").trigger("reloadGrid");
	}

	function youthsNameFont(el,options,rowData){
		if(null == rowData.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
			return '<a href="javascript:;"  onclick="viewDialog(event,'+rowData.rowNum+')"><font color="red">'+rowData.name+'</font></a>';
		}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
			return '<a href="javascript:;"  onclick="viewDialog(event,'+rowData.rowNum+' )"><font color="#778899">'+rowData.name+'</font></a>';
		}
		return '<a href="javascript:;" onclick="viewDialog(event,'+rowData.rowNum+')"   ><font color="#6633FF">'+rowData.name+'</font></a>';
	}


	function viewDialogForDbClik(rowNum){
		viewDialog(event,rowNum);
	}
};