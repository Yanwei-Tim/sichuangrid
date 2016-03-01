$.fn.extend({
	VideoList:function(option,updateOption){
		var self=$(this);
		
		if(typeof(option)=="string"){
			switch(option){
				case 'option':
					if(updateOption){
						$.extend(self.data("defaultOption"),updateOption);
					}else{
						return self.data("defaultOption");
					}
					break;
				case 'reload':
					ajaxData(self.data("defaultOption"));
					break;
			}
			return ;
		}
	
		var defaultOption = {
				url:'',data:{},
				dataType:"json",
				rowFormatter:false,
				afterInit:false,
				ajaxLoad:function(data){
					
				}
		};
		$.extend(defaultOption,option);
		self.data("defaultOption",defaultOption);
		function changeRecords(data){
			$("#"+self.attr("id")+"Total").html(data.length);
		}
	
		function formatterRowList(data,page){
			self.nextAll().remove();
			var pageNum = self.data("defaultOption").data.rows
			if(data.length>0){
				var listHtml = $("<ul />").addClass("resultlist");
				for(var i =(page-1)*pageNum ;i<page*pageNum && i<data.length;i++){
					var rowObj=defaultOption.rowFormatter(data[i],i);
					listHtml.append(rowObj);
				}
				self.parent().append(listHtml);
			}
		}
		
		function formatterPager(data){
			$("#"+self.attr("id")+"Pager").tabPaging({
				total:data.length,
				perpage: self.data("defaultOption").data.rows,
				page: 1,
		        onSelect: function(page) {
					formatterRowList(self.data("features"),page);
					/*
					if(!self.data("initState")){
						$.extend(self.data("defaultOption").data,{page:page});
						ajaxData(self.data("defaultOption"));
					}
					*/
		        }
			});
		}
		
		function ajaxData(defaultOption){
			$.ajax({
				url:defaultOption.url,
				async:true,
				dataType:defaultOption.dataType,
				data:defaultOption.data,
				success:function(data){
					if(data!=null){
						data = data.features;
					}
					self.data("features",data);
					changeRecords(data);
					formatterRowList(data,1);
					if(self.data("initState")){
						formatterPager(data);
					}
					self.data("initState",false);
					if(defaultOption.afterInit){
						defaultOption.afterInit(data);
					}
					defaultOption.ajaxLoad(data);
				}
			});
		}
		self.data("initState",true);
		ajaxData(self.data("defaultOption"));
	}
});