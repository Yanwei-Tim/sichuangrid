TQ.floatingPopulationDlg = function (dfop){
	$('#inflowingDate').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+0y'
	});
	$('#registerDate').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+0y'
	});
	$('#expectedDatedue').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd'
	});
	
	$(document).ready(function(){
	
		threeSelect({province:'inflowingProvince',
			city:'inflowingCity',
			district:'inflowingDistrict',
			provinceValue:$('#provinceValue').val(),
			cityValue:$('#cityValue').val(),
			districtValue:$('#districtValue').val()
		});
	
		jQuery.validator.addMethod("registerDate", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var inflowingDate = $("#inflowingDate").val();
			if(value<inflowingDate){
				return false;
			}else{
				return true;
			}
		});
		
		jQuery.validator.addMethod("expectedDatedue", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var registerDate = $("#registerDate").val();
			if(registerDate==null||registerDate==undefined||registerDate==""){return true}
			if(value<registerDate){
				return false;
			}else{
				return true;
			}
		});
	
		jQuery.validator.addMethod("expectedDatedue1", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var inflowingDate = $("#inflowingDate").val();
			if(inflowingDate==null||inflowingDate==undefined||inflowingDate==""){return true}
			if(value<inflowingDate){
				return false;
			}else{
				return true;
			}
		});
	
		if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
			$("#img").attr("src",PATH +$("#_imgUrl").val());
		}
	
		if(dfop.isFloatingPopulationDialog == 'true'){
			$("#maintainForm").attr("action", PATH + "/baseinfo/floatingPopulationManage/saveFloatingPopulationInfo.action");
		}
	});
	$("#maintainForm").formValidate({
		submitHandler: function(form){
		$("#_imgUrl").val($("#imgUrl").val());
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (!data.id) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
				if("add"==$("#mode").val()){
					 if(data.name) {
						 if(($("#isLock").val()=='1'&&$("#logOut").val()!="1")||($("#isLock").val()=='2'&&$("#isDeath").val()!="true")){
							 $("#floatingPopulationList").addRowData(data.id,data,"first");
							 $("#floatingPopulationList").setSelection(data.id);
						 }
						 $("#floatingPopulationList").trigger("reloadGrid");
					 	 $.messageBox({message:"流动人口新增成功！"});
					 }
				 }
				 else if("edit"==$("#mode").val()){
					 if(data.name) {
						 if(data.death == true || data.death == "true"){
							 if(($("#isLock").val()=='1'&&$("#logOut").val()=="0")||($("#isLock").val()=='2'&&$("#isDeath").val()=="false")){
								 $("#floatingPopulationList").delRowData(data.id);
							 }else{
								 $("#floatingPopulationList").setRowData(data.id,data);
								 $("#"+data.id+"").css('color','#778899');
								 $("#floatingPopulationList").setSelection(data.id);
							 }
						 }else{
							 if($("#isLock").val()=='2'&&$("#isDeath").val()=="true"){
								 $("#floatingPopulationList").delRowData(data.id);
							 }else{
								 $("#floatingPopulationList").setRowData(data.id,data);
								 $("#floatingPopulationList").setSelection(data.id);
							 }
						 }
						 $("#floatingPopulationList").trigger("reloadGrid");
						 //	disableButtons();
						 //checkExport();
					 	 $.messageBox({message:"流动人口修改成功！"});
					 }
				 }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"population.certificateNumber":{
				minlength:2,
				maxlength:50
			}
		},
		messages:{	
			"population.certificateNumber":{
				minlength:$.format("登记证编号至少需要输入{0}个字符"),
				maxlength:$.format("登记证编号最多需要输入{0}个字符")
			}
		}
	});
}