$.fn.extend({
	GisList:function(option,updateOption){
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
				rowFormatter:false,
				afterInit:false,
				ajaxLoad:function(data){
					
				}
		};
		$.extend(defaultOption,option);
		self.data("defaultOption",defaultOption);
		function changeRecords(data){
			$("#"+self.attr("id")+"Total").html(data.records);
		}
	
		function formatterRowList(data){
			self.nextAll().remove();
			if(data.records>0){
				var rows=data.rows;
				var listHtml = $("<ul />").addClass("resultlist");
				for(var i =0 ;i<rows.length;i++){
					var rowObj=defaultOption.rowFormatter(rows[i],i);
					listHtml.append(rowObj);
				}
				self.parent().append(listHtml);
			}
		}
		
		function formatterPager(data){
			$("#"+self.attr("id")+"Pager").tabPaging({
				total:data.records,
				perpage: self.data("defaultOption").data.rows,
				page: data.page,
		        onSelect: function(page) {
					if(!self.data("initState")){
						$.extend(self.data("defaultOption").data,{page:page});
						ajaxData(self.data("defaultOption"));
					}
		        }
			});
		}
		
		function ajaxData(defaultOption){
			$.ajax({
				url:defaultOption.url,
				async:true,
				data:defaultOption.data,
				success:function(data){
					changeRecords(data);
					formatterRowList(data);
					if(self.data("initState")){
						formatterPager(data);
					}
					self.data("initState",false);
					if(defaultOption.afterInit){
						self.data("defaultOption").afterInit();
					}
					defaultOption.ajaxLoad(data);
				}
			});
		}
		self.data("initState",true);
		ajaxData(self.data("defaultOption"));
	}
});