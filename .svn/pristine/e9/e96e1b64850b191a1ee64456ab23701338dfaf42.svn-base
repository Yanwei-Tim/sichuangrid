TQ.maintainUnsettledPopulationConditionDlg = function (dfop){
	$(document).ready(function(){
		$("#showMoreBtn").toggle(
			function(){
				$("#unsettledPopulationMaintanceDialog").dialog( "option" , {height:450});
				$("#showMoreCtt").show();
				$(this).addClass("cur").text("隐藏更多条件");
			},
			function(){
				$("#unsettledPopulationMaintanceDialog").dialog( "option" , {height:330} );
				$("#showMoreCtt").hide();
				$(this).removeClass("cur").text("显示更多条件");
			}
		);
		
		var idCard = $("#searchByIdC").val();
		if(idCard=="请输入姓名"){
			$("#conditionName").val("");
		}else{
			$("#conditionName").val(idCard);
		}
		threeSelect({
			province:'conditionProvince',
			city:'conditionCity',
			district:'conditionDistrict'
		});
	
	
	
		$('#conditionBirthdayStart').datePicker({
			yearRange: '1900:2030',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d',
	        onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionBirthdayEnd").datepicker("option", "minDate",date);
				}
			}
		});
	
		$('#conditionBirthdayEnd').datePicker({
			yearRange: '1900:2030',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d',
	        onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionBirthdayStart").datepicker("option", "maxDate",date);
				}
			}
		});
	
		$('#conditionUnsettedTimeStart').datePicker({
			yearRange: '1900:2030',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d',
	        onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionUnsettedTimeEnd").datepicker("option", "minDate",date);
				}
			}
		});
	
		$('#conditionUnsettedTimeEnd').datePicker({
	        onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionUnsettedTimeStart").datepicker("option", "maxDate",date);
				}
			}
		});
	});
}