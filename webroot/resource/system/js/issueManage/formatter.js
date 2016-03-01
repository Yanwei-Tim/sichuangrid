function rendSupervise(el, options, rowData){
	if(1==rowData.supervisionState || 1==el){
		return "<img src='/resource/system/images/normalcard.gif'>";
	}else if(100==rowData.supervisionState){
		return "<img src='/resource/system/images/yellowcard.gif'>";
	}else if(200==rowData.supervisionState){
		return "<img src='/resource/system/images/redcard.gif'>";
	}else{
		return "";
	}
	
}
function rendUrgent(el, options, rowData){
	if(1==rowData.urgent || el==true){
		return "<img src='/resource/system/images/immediate.gif'>";
	}else{
		return "";
	}
}