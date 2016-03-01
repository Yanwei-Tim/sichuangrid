/**
 * 学校名称自动填充
 */
;(function($) {
	$.fn.extend( {
		schoolAutoComplete:function(option){
			var defaultPostData ={
				rows : 10
			};
			
			$.extend(defaultPostData,option.postData);
			
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					$.ajax({
						url : PATH + "/baseinfo/idleYouthManage/autoCompleteSchoolName.action",
						data : defaultPostData, 
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									label : rowData,
									value : rowData
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				},
				open:function(){
					$(".ui-autocomplete").css("z-index","100000");
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)