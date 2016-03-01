TQ.issueForViewList = function (dfop){
	function setTopButton(){
		var selectedIssue = getSelectedData();
		if(selectedIssue.topState==dfop.top){
			$("#topIssue").html("<span>取消置顶</span>");
		}else{
			$("#topIssue").html("<span>置顶</span>");
		}
	}

	function setUrgentButton(){
		var selectedIssue = getSelectedData();
		if(selectedIssue.urgent=='1'){
			$("#urgent").html('<span>取消加急</span>');
			
		}else{
			$("#urgent").html('<span>加急</span>');
		}
	}


	

	function hasRowSelected(){
		if(null != $("#issueList").getGridParam("selrow")){
			return true;
		}
		return false;
	}
	function getSelectedId(){
		return $("#issueList").getGridParam("selrow");
	}
	function getSelectedData(){
		var selectedId = $("#issueList").getGridParam("selrow"); 
		return $("#issueList").getRowData(selectedId);
	}
	function getTypeDescById(indexs,id){
		for (var index=0;index<indexs.length;index++){
			if (indexs[index]==id) return index;
		}
		return indexs.length;
	}
}