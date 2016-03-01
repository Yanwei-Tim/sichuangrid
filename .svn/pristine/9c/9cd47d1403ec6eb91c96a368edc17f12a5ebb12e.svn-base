$.extend({
    typeSelect:function(){
        function showOrHide(){
        	var item = 0;
        	
        	$('#ITD_dlg .multipeSelect').each(function(){
        		if ( $(this).find("ul input[type=checkbox]:checked")[0] ){
        			item = item+1
        		}
        	})
            if ( item ){
                $("#issueTypeDescription").animate({'height':item*22+16+'px'})
                $("#ITD_rightBar").animate({'height':item*22+28+'px'})
                $.renderSelectedIssueTypes();
                
            }else{
            	$("#itemNameIds").val("");
                $("#ITD_dlg .category").attr('checked',false)
                $("#issueTypeDescription").html('').animate({'height':'16px'})
                $("#ITD_rightBar").animate({'height':'28px'})
            }
        }
    
        // 显示 && 隐藏弹框
        $('#ITD_rightBar,#issueTypeDescription').bind('click',function(event){
            $('#ITD_dlg').show()
            $(document).one("click",function(){
                showOrHide()
                $('#ITD_dlg').hide();
            });
            $("#objectSelectBoxClose").click(function(){
            	showOrHide()
                $('#ITD_dlg').hide();
            	
            })
            event.stopPropagation();
        })
       
        $('#issueTypeDescription').on('click','.subBox',function(event){
        	event.stopPropagation();
        })
        // 弹框绑定事件监听
        $('#ITD_dlg').click(function(event){
            event.stopPropagation();
        })
        
        //var selected;
        // 删除已选中的标签
        $("#issueTypeDescription").on('click','.iLabel',function(){
        	
            var index = $(this).attr('index');
            
            $('#ITD_dlg').find('input').each(function(){
                if( index == $(this).attr('value') ){
                    $(this).attr('checked',false)
                    return false;
                }
            });
            showOrHide()
            $(this).remove();
        })
    },
    renderSelectedIssueTypes:function(){
    	var sortArray=new Array();	
		 $('input[name="selectedTypes"]:checked').each(function(){
			sortArray.push($(this).val());
		 });	
		 $("#itemNameIds").val(sortArray);
		var typeDesc="";
		$("#ITD_dlg .category").each(function(index,value){
			if ( $(value).getTypeSelectLabels() != '' ){
				typeDesc=typeDesc+'<div class="subBox" ><strong>['+$(this).next().text()+']：</strong>'+$(value).getTypeSelectLabels()+"</div>";
			}
		});
		$("#issueTypeDescription").html(typeDesc);
	}
   
})


$.fn.extend({
    moreAccurateTime:function(){
        var that = $(this);
        var getDom = function(time){
            var time = time || "",
                p = '<div class="editable-select-options"><ul>';

            var e = [];
            for (var m = 0; m < 24; m++) {
                e.push(m < 10 ? "0" + m + ":00" : m + ":00");
            }

            for (var hhmm=0; hhmm<e.length; hhmm++) {
                if (e[hhmm] == time) {
                    p += '<li class="selected">' + e[hhmm] + "</li>"
                } else {
                    p += '<li>' + e[hhmm] + "</li>"
                }
            }
            p += "</div>";
            return p
        };

        var init = function(){
            var hours = that.find('#hours');
            var minute = that.find('#minute');
            var txt_hours = that.find('.eat_hours');
            var txt_minute = that.find('.eat_minute');
            var dom = getDom(hours.text()+':'+minute.text());
            var timeCtt = null;

            that.click(function(event){
                if( !$('.editable-select-options')[0] ){
                    $('#exactTime').append( dom )
                }
                
                timeCtt = $('.editable-select-options');
                timeCtt.show().find('li').click(function(){
                    var val = $(this).text().split(":");

                    hours.val(val[0])
                    minute.val(val[1])
                    txt_hours.val(val[0])
                    txt_minute.val(val[1])

                    timeCtt.hide()
                })
                event.stopPropagation();
                $(document).one("click", function(){
                    timeCtt.hide();
                })
            })
            
            txt_hours.focusout(function(){
                var val = $(this).val();
                if( val >= 24 ){
                    $(this).val(23)
                }
                hours.val( val )
                
                if( ! /^\d*$/.test( val ) ){
                    $(this).val(00)
                    hours.val( 00 )
                }
                
            })
            txt_minute.focusout(function(){
                var val = $(this).val();
                if( val >= 59 ){
                    $(this).val(59)
                }
                minute.val( val )
                
                if( !/^\d*$/.test( val ) ){
                    $(this).val(00)
                    minute.val( 00 )
                }
            })

        };
        
        init()
    },
	getTypeSelectValues:function(){
		var itemId=$(this).attr("id");
		var showItem=$(this).nextAll("ul:first");
		$(".close").parent().parent().hide();
		var _check=showItem.find(":checkbox");
		var selectValue="";
		var selectText="";
		$(_check).each(function(){
			if($(this).attr("checked")){
				selectValue=selectValue+$(this).attr("value")+',';
				selectText=selectText+$(this).parent().text()+',';
			}
		});
		if(showItem.find("input:checked").size()>0){
			$("#"+itemId).attr("checked","checked");
		}
		return selectValue;
	},
	setTypeSelectValues:function(ids){
		//设置选中的值
		var selectValue=ids.split(",");
		for(i=0;i<selectValue.length;i++){
			$(this).nextAll("ul:first").find("input[value='"+selectValue[i]+"']").attr("checked","checked");
		}
		$(this).attr("checked","checked");
	},
	
	getTypeSelectLabels:function(){
		var itemId=$(this).attr("id");
		var showItem=$(this).nextAll("ul:first");
		var _check=showItem.find($("input:checked"));
		var selectValue="";
		var selectText="";
        var selectDom="";
		$(_check).each(function(){
			if($(this).attr("checked")){
				selectValue = $(this).attr("value");
				selectText  = $.trim( $(this).parent().text() );
                selectDom   = selectDom +'<span class="iLabel" index="'+selectValue+'">'+selectText+'</span>';
			}
		});
		if(showItem.find("input:checked").size()>0){
			$("#"+itemId).attr("checked","checked");
		}else{
			$("#"+itemId).attr("checked",false);
        }
		return selectDom;
	},
	resetTypeSelectLabels:function(){
		//$(defaultOption.addTo).text(self.getTypeSelectLabels());
	}
});