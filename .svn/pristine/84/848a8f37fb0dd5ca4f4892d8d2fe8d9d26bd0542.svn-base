;(function ($) {
	$.fn.primaryMemberComplete = function(option){
		var self=$(this);
		var defaultOption={
			postDataFunction	: function(){},
			loadingImg  : RESOURCE_PATH+'/resource/external/selectInPlace/ajax-loader.gif',
			loadingText : "正在加载...",
			multiple	: true,
			url			: "",
			dynamic		: true,
			param 		: "tag",
			height      : "auto",
			width		: "auto",
			htmlMethod	: function (item){
				var detailName=item.detailName;
				if(detailName==null||detailName==undefined){detailName="";}
				return '<li style="width:'+this.width+'px">'+'<div style="padding:0 0 0 0px" class="title">'+item.detailName+'【'+item.displayName+'】'+'</div><div>&nbsp;'+'</div></li>';
			},
			fullMessage : "不能选择更多...",
			valueMethod : function getValue(item){
		    	return item.organizationId;
		    },
			max 		: 20,
			select:function(data){},
			itemClose   : function(data){},
			labelMethod : function(elem){
		    	defaultOption.select($(elem).data('data'));
		    	var itemHtml='<LI class="bit-box">'+elem.children(".title").text()+'<A class="closebutton" href="#"></A>';
		    	return itemHtml;
		    }
		};
		$.extend(defaultOption,option);
		$(this).selectInPlace(defaultOption);
		if(defaultOption.height!="auto"){
			$("#holder_"+self.attr("id")).css({"height":defaultOption.height+"px","overflow":"hidden","overflow-y":"auto"});
		};
	};
	$.fn.setPrimaryMemberCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.detailName+'<a class="closebutton" href="#"></a></li>').data('data',option.organizationId);
		var value=$(this).val();
		if(value!=""){
			$(this).val(option.organizationId+","+value);
		}else{
			$(this).val(option.organizationId);
		}
		$(this).next().prepend(htmlObj);
	};
	$.fn.resetPrimaryMemberCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.detailName+'<a class="closebutton" href="#"></a></li>').data('data',option.organizationId);
		var value=$(this).val();
		$(this).val(option.organizationId);
		$(this).next().children(".bit-box").remove();
		$(this).next().prepend(htmlObj);
	};
	$.fn.clearPrimaryMemberComplete = function(){
		var value=$(this).val();
		$(this).val("");
		$(this).next().children(".bit-box").remove();
	};
	$.fn.removePrimaryMemberCompeleteVal = function(option){
		this.next().children(":contains('"+option.orgName+"')").remove();
		var value = String($(this).val());
		var temp = value.substring(value.indexOf(option.organizationId));
		if(value.indexOf(',')!=-1 && temp.indexOf(',') != -1){
			var last = temp.substring(value.indexOf(',')+1);
			value = value.substring(0, value.indexOf(option.organizationId)).concat(last);
			$(this).val(value);
		}else if(value.indexOf(',')!=-1 && temp.indexOf(',') == -1){
			$(this).val(value.substring(0, value.indexOf(option.organizationId)-1));
		}else{
			$(this).val("");
		}
	};
	$.fn.removePrimaryMemberCompeleteValue = function(val){
		var self=$(this);
		var valueArr=new Array()
		var thisValue=self.val();
		var newValue="";
		var thisId=val;
		var valueArr=thisValue.split(",");
		var thisIndex=-1;
		for(var i=0;i<valueArr.length;i++){
			if(thisId==valueArr[i]){
				thisIndex=i;
				continue;
			};
			newValue=newValue+valueArr[i]+",";
		};
		self.val(newValue.substring(0,newValue.length-1));
		self.next().children(".bit-box:eq("+thisIndex+")").remove();
	};
})(jQuery);
