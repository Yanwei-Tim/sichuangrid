;(function(jQuery){
	function orgLevelLessEqual(orgId,level){
		var bol = false;
		$.ajax({
			async:false,
			url:PATH+"/tools/org/levelCompare.action",
			data:{
				"orgId":orgId,
				"orgLevel":level
			},
			success:function(responseData){
				bol = responseData<=0;
			}
		});
		return bol;
	}
	function objectEquals(level1,level2){
		var bol = false;
		if(level1==level2){
			bol=true;
		}
		return bol;
	}
	jQuery.validator.addMethod("orgLevelLessEqual", function(value, element,params){
		if(params[0]==null||params[0]==undefined||params[0]==""){return false;}
		return orgLevelLessEqual(params[0],params[1])==true || orgLevelLessEqual(params[0],params[1])=="true";
	});
	jQuery.validator.addMethod("objectEquals", function(value, element, params){
		if(params[0]==undefined||params[1]==undefined||params[0]==null||params[1]==null){return false;}
		return objectEquals(params[0],params[1])==true || objectEquals(params[0],params[1])=="true";
	});
	
})(jQuery)
