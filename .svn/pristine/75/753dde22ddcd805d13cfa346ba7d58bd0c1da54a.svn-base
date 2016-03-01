if(!window.TQSearch){
	window.TQSearch = function(option){if(this.TQSearch) this.TQSearch(option);};
}
$(function(){
	$.extend(window,{
		"isRentalHouseData":{"1":"出租房","0":"非出租房"},
		"isEmphasisData":{"1":"是","0":"否"},
		"evaluateTypeData":{"1":"不满意","2":"基本满意","3":"满意"},
		"orgNameData":{},
		"dataTypeData":{"householdStaff":"户籍人口","floatingPopulation":"流动人口","unsettledPopulation":"未落户人口","overseaStaff":"境外人员"},
	})
   function initTqSearchPanelBtn(){
	   $("#tqSearchText").focus(function(){
	        $(".zjsSeekTextLay").find("span").hide();
	   }).blur(function(){
		   var searchText = $(this).val();
	        if(!searchText){
	        	$(".zjsSeekTextLay").find("span").show();
	        }
	        TQSearch["searchText"] = searchText?searchText:"*";
	   });
	   $(".zjsSeekTextLay .title").click(function(){
	       $("#tqSearchText").focus();
	   })
//	   $("#tqSearchText").unbind("keydown").bind("keydown",function(evt){
//	       if(evt.keyCode==13){//键盘键 Enter 事件
//	       		if($("#tqSearchButton").length>0){
//	       			$("#tqSearchButton").click();
//	       		}else if($("#tqSearchBtn").length>0){
//	       			$("#tqSearchBtn").click();
//	       		}
//	       }
//		});
   }
   $(".searchCont").delegate(".zjsScreenList span","click",function(){
        $(this).parent().find("span").removeClass("on");
        var value = $(this).attr("value");
        var name = $(this).attr("name");
        var $input = $(this).parents(".listLay").nextAll("input");
        if($input==null){
        	$input = $(this).parents(".zjsScreenList").find("input");
        }
        $input.val(value?value:"");
        if(name){
        	$input.attr("name",name);
        }
        $(this).addClass("on");
        if($("span[value='overseaStaff']").hasClass("on")){
        	$(".notOverseaStaffSearch").hide();
        }else if($("#showAll").is(":hidden")){
        	$(".notOverseaStaffSearch").show();
        }
        $("#resultSearchBtn").click();//搜索
   })
   $(".searchCont").delegate(".zjsScreenList input[type='button']","click",function(){
	   var $range = $(this).parent(".range");
	   var min = $range.find("input.min").val();
	   var max = $range.find("input.max").val();
	   var rangeType = $range.attr("rangeType");
	   var $input = $(this).parents(".zjsScreenList").children("input");
	   $range.parent().find("span").removeClass("on");
	   if(!min && !max){
		   $range.parent().find("span:eq(0)").addClass("on");
		   $input.val("");
		   return;
	   }
       min = (min)?(rangeType?rangeType.replace("{}",min):min):"*";
       max = (max)?(rangeType?rangeType.replace("{}",max):max):"*";
       $input.val("["+min+" TO "+max+"]");
       $("#resultSearchBtn").click();//搜索
   })
   $(".searchCont").delegate(".zjsScreenList .more","click",function(){
      $(this).closest(".zjsScreenList").height("auto");
      $(this).hide();
      var dom = "<a href='javascript:;' class='moreBack'>收回</a>"
	  $(this).closest(".zjsScreenList").find('.listLay').append(dom);
   })
   $(".searchCont").delegate(".zjsScreenList .moreBack","click",function(){
      $(this).closest(".zjsScreenList").height(24);
      $(this).hide();
      $('.more').show()
   })
   $(".searchCont").delegate('#showAll','click',function(){
		$(this).hide().next().show();
	   	$('.zjsScreenList').show();
	    if($("span[value='overseaStaff']").hasClass("on")){
	    	$(".notOverseaStaffSearch").hide();
	    }
   })
   $(".searchCont").delegate('#hideAll','click',function(){
   		$(this).hide().prev().show();
   		$('.zjsScreenList').hide().eq(0).show();
   })
  $(".searchCont").delegate(".zjsScreenList .multiSelect","click",function(){
      $(this).closest(".zjsScreenList").height("auto").css({
          "padding-right" : 0
      });
      $(this).closest(".listLay").hide().next(".listLaySelect").show();
   })
   $(".searchCont").delegate(".selectBottom input[type='button']","click",function(){
	   if(!$(this).hasClass("cancel")){
		   var valueSelect = "";
		   var $listLay = $(this).parents(".listLaySelect").prevAll(".listLay");
		   var $checkbox = $(".listLaySelect input[type='checkbox']:checked");
		   $listLay.find("span").removeClass("on");
		   if($checkbox.length==0) $listLay.find("span:eq(0)").addClass("on");
		   $checkbox.each(function(){
			   var value = $(this).val();
			   if(valueSelect){
				   valueSelect += " OR populationTypes:" + $(this).val();
			   }else{
				   valueSelect += $(this).val();
			   }
			   $listLay.find("span[value='"+value+"']").addClass("on");
		   })
		   $(this).parents(".listLaySelect").nextAll("input").val(valueSelect?valueSelect:"");
		   $(".listLaySelect input[type='checkbox']").removeAttr("checked");
		   $("#resultSearchBtn").click();//搜索
	   }
      $(this).closest(".listLaySelect").hide();
      $(this).parents(".zjsScreenList").css({
         "padding-right" : 100,
         "height" : 24
      }).find(".listLay").show();
   })
   $(".searchCont").delegate("#orderFields a","click",function(){
	   var orderType = "desc";
      if($(this).hasClass("desc")){
    	   orderType = "asc";
       }
       $(this).parent().find("a").removeClass("on").removeClass("desc").removeClass("asc");
       var name = $(this).attr("name");
       $("#orderFieldInput").attr({"name":name,"value":orderType});
       $(this).addClass("on").addClass(orderType);
       TQSearch["isOrder"] =  true;
       $("#resultSearchBtn").click();//搜索
   })
   
   $(".searchCont").delegate("#tqSearchType li","click",function(){
		$(this).find("a").addClass("on");
		$(this).siblings().find("a").removeClass("on");
		var key = $(this).find("a").attr("key");
		$("#tqSearchField").load(PATH+"/baseinfo/tqSearch/dispatch.action?mode="+key,function(){
			var info = $("input[name = 'tqSearchVo.searchFields']").attr("title");
			$("#tqSearchTitle").text("请输入"+info);
			if(TQSearch["searchType"]!= key){
				TQSearch["searchType"] = key;
				TQSearch["searchText"] = "*";
			}
			$("#tqSearchText").val(TQSearch["searchText"]);
			$("#tqSearchText").focus();
			$("#tqSearchBtn").click();//搜索
			TQSearch.showTqSearchHot();
		});
	})
	$("#tqSearchButton").click(function(){
		$(".zjsSeekMainLay").load( PATH+"/baseinfo/tqSearch/dispatch.action?mode=listRight&sq="+TQSearch["searchText"]+"&sty="+TQSearch["searchType"])
	})
	$(".searchCont").delegate("#tqSearchBtn","click",function(){
		TQSearch["isResultSearch"] =  false;
		deleteOrderField();
		TQSearch.submitSearchForm();
	});
   $(".searchCont").delegate("#resultSearchBtn","click",function(){
	   TQSearch["isResultSearch"] =  true;
	   TQSearch.submitSearchForm();
	   deleteOrderField();
	});
   $(".searchCont").delegate("#searchOrgContainer","click",function(){
		TQSearch.showOrganizationTree();
	})
	$(".searchCont").delegate("#tqSearchMap","click",function(){
		var $li = $(this).parents("li");
		TQSearch.showMap({
			id:$li.attr("id"),
			type:$li.attr("type"),
			lon:$li.attr("lon"),
			lat:$li.attr("lat"),
			orgId:$("#searchOrgId").val(),
			featureId:$li.attr("featureId"),
			showName:$li.attr("showName")
		});
	})
	$(".searchCont").delegate("#tqSearchRelationship","click",function(){
		var $administra = $(this).parent().next(".administra");
		var divId = $administra.find(".administrativeLevel").attr("id");
		var formId = $(this).parent().find("form").attr("id");
   		if($administra.is(":hidden")){
   			TQSearch.showRelationshipInfo({
				templateId:"relationshipTPL",
				divId:divId,
				formId:formId
			});
   			$administra.parents("ul").find(".administra").removeClass("on").hide();
   			$administra.addClass("on").show();
   		}else{
   			$administra.removeClass("on").hide();
   		}
	})
	$(".searchCont").delegate("#tqSearchLiTitle,.rootNodes","click",function(){
		var $li = $(this).parents("li");
		var urlInfo = TQSearch.getDetailUrl($li.attr("type"));
		TQSearch.showDetailInfo($.extend({
			id:$li.attr("id")
		},urlInfo));
	})
	$(".searchCont").delegate(".subNode a","click",function(){
		var $a = $(this);
		var $subNode =$(this).parents(".subNode");
		var $li = $(this).parents("li");
		var detailType = $subNode.attr("detailType");
		var detailSearchType = $subNode.attr("detailSearchType");
		var detailSearchField = $subNode.attr("detailSearchField");
		var detailRelationId = $subNode.attr("detailRelationId");
		var detailRelationType = $subNode.attr("detailRelationType");
		var id = $li.attr("id");
		var type = $li.attr("type")
		if(detailType=="list"){
			var url = "/baseinfo/tqSearch/detailList.action?mode="+detailSearchType+"&searchs.id="+detailRelationId+"&searchs.type="+detailRelationType+"&searchs.searchField="+detailSearchField+"&searchs.searchType="+detailSearchType;
			TQSearch.showDetailInfo({
				url:url,
				height:550
			});
			return;
		}
		var urlInfo = TQSearch.getDetailUrl($a.attr("nodeType"));
		urlInfo.url = urlInfo.url
				.replace("{{key}}",$a.attr("key"))
					.replace("{{populationType}}",$a.attr("populationType"))
					.replace("{{partyOrgType}}",$a.attr("partyOrgType"))
					.replace("{{organizationId}}",$a.attr("organizationId"));
		TQSearch.showDetailInfo($.extend({
			id:$a.attr("nodeId")
		},urlInfo));
	});
	
	TQSearch.searchType = "populationSearch";
	TQSearch.searchText = "";

	function getSearchTextList(){
		var isResultSearch = TQSearch["isResultSearch"];
		if(!TQSearch.searchTextList || !isResultSearch) TQSearch.searchTextList = [];
		if(!TQSearch.searchTextList.contains(TQSearch["searchText"])){
			if(TQSearch.searchTextList.length==3){//结果中搜索支持三次搜索
				TQSearch.searchTextList.removeAt(0);
			}
			TQSearch.searchTextList.push(TQSearch["searchText"]);
		}
		return TQSearch.searchTextList.toString();
	}
	function deleteOrderField(){
		if(TQSearch["isOrder"] == false){
			$("#orderFields a").removeClass("on");
			$("#orderFields a").removeClass("asc");
			$("#orderFields a").removeClass("desc");
		}
		$("#orderFieldInput").attr({"name":"","value":""});
		TQSearch["isOrder"] = false;
	}
	TQSearch._showFlash=function(option){
		var idfop = $.extend({url:"",divId:""},option);
		if(!idfop.divId || $("#"+idfop.divId).length==0) return;
		$.ajax({
			  url:idfop.url,
			  async:true,
			  success: function(data){
				  $("#"+idfop.divId).empty();
				  var rnumber = Math.floor(Math.random()*9999999);  
				  var cloud = new SWFObject("/resource/tqSearch/img/tagcloud.swf?r="+rnumber, "tagcloudflash", "255px", "220px", "9", "#F3F7FD");
				  cloud.addParam("wmode", "transparent");
				  cloud.addVariable("tspeed", "200"); 
				  cloud.addParam("allowScriptAccess", "always");
				  cloud.addVariable("data", data);
				  cloud.write(idfop.divId);
			  }
		});
	}
	TQSearch.showTqSearchHot = function(){
		TQSearch._showFlash({url:PATH +'/baseinfo/tqSearch/tqSearchHot.action?type='+TQSearch["searchType"],divId:"tqSearchHot"});
	}
	TQSearch.showTqSearchHis = function(hisId){
		TQSearch._showFlash({url:PATH +'/baseinfo/tqSearch/tqSearchHis.action',divId:"tqSearchHis"});
	}

	TQSearch.initTqSearchPanel = function(){
		initTqSearchPanelBtn();
		if(TQSearch["searchText"]){
			$("#tqSearchText").val(TQSearch["searchText"]);
		}
		if(TQSearch["searchType"]){
			$("#tqSearchType li a[key='"+TQSearch["searchType"]+"']").click();
		}
		TQSearch.showTqSearchHis();
	}
	TQSearch.addLoading=function(divId){
		$("#"+divId).html("<div id='loadingValue'><img src='"+PATH+"/resource/tqSearch/img/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div>");
	}
	
	TQSearch.showMap=function(option){
		if(!$("#tqSearchMapDiv")) $("body").append("<div id='tqSearchMapDiv'></div>");
		var idfop = $.extend({showName:""},option)
		$("#tqSearchMapDiv").createDialog({
			width: document.body.clientWidth,
	        height: document.body.clientHeight,
	        title:idfop.showName+"周边搜索",
	        ajaxType:"post",
	        postData:option,
	        url:PATH +"/tqSearch/tqSearchMap.ftl",
	        buttons: {
	            "关闭" : function(){
	            	$(this).dialog("close");
	            }
	        }
		})
	}
	
	TQSearch.showRelationshipInfo=function(option){
		var idfop = $.extend({
			templateId:"",
			divId:"",
			formId:""
		},option)
		TQSearch.addLoading(idfop.divId);
		var postData = $("#"+idfop.formId).serializeObject();
		$.ajax({
			type:"post",
			async:true,
			url:PATH+"/baseinfo/tqSearch/tqSearchRelationship.action",
			data:postData,
			success:function(json){
				if(json && json.children && json.children.length>0){
					var relationHtml = template(idfop.templateId,{'json':[json]});
					relationHtml = formatterProperty(relationHtml);
					$("#"+idfop.divId).empty().html(relationHtml).height(json["children"].length*56);
				}else{
					$("#"+idfop.divId).empty().html("<ul class='nodataOnly'>");
				}
            }
		})
	}
	
	TQSearch.showDetailInfo = function(option){
		var idfop = $.extend({
			id:"",
			url:"",
			width:800,
			height: document.body.clientHeight
		},option)
		if(!$("#tqSearchDetailDlg")) $("body").append("<div id='tqSearchDetailDlg'></div>");
		$("#tqSearchDetailDlg").createDialog({
			url:PATH +idfop.url + idfop.id,
			width:idfop.width,
			 height: idfop.height,
			title:'详情查看',
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	
	TQSearch.getDetailUrl = function(type){
		if(!TQSearch.DetailUrls){
			$.ajax({
				async:false,
				url:PATH+"/baseinfo/tqSearch/findDetailUrls.action",
				success:function(data){
					TQSearch.DetailUrls = data;
				}
			})
		}
		if(TQSearch.DetailUrls) return TQSearch.DetailUrls[type];
	}
	
	TQSearch.showOrganizationTree = function(option){
		if(!$("#globalOrgBox")) $("body").append("<div id='globalOrgBox'></div>");
		$("#globalOrgBox").createDialog({
			url:PATH +'/sysadmin/orgManage/orgSelectComponent.action?id='+$("#searchOrgId").val()+"&plateName=",
			width:550,
			height:'auto',
			title:'辖区选择',
			buttons: {
				"确定" : function(event){
					var selectInput=$("#orgSelectInput");
					$("#searchOrgId").attr({
						text:selectInput.attr("text"),
						value:selectInput.val()
					});
					$("#searchOrgText").text(selectInput.attr("text"));
					$(this).dialog("close");
					$("#resultSearchBtn").click();//搜索
				},
				"取消" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	TQSearch.submitSearchForm =function(option){//houseSearchMapTPL
		$("#searchType").val(TQSearch["searchType"]);
		$("#searchText").val(TQSearch["searchText"]);
		var censusRegister = $("#populationProvince").val()+$("#populationCity").val()+$("#populationDistrict").val()+"";
		if(censusRegister != ""){
			censusRegister = censusRegister + "*";
			$("#censusRegister").val(censusRegister);
		}
		var allSpn = $(".listLay span.on.all").nextAll("span");
		if(allSpn.length>0 && allSpn.length < 4){
			var populationDataType = "";
			allSpn.each(function(){
				if($(this).attr("value")){
					if(populationDataType == ""){
						populationDataType += $(this).attr("value");
					}else{
						populationDataType += " OR dataType:" + $(this).attr("value");
					}
				}
			})
			$("#populationDataType").val(populationDataType);
		}
		var dfop = $.extend({
			formId:"tqSearchForm",
			listId:"tqSearchList",
			templateId:TQSearch["searchType"]+"TPL",
			searchText:getSearchTextList(),
			success:function(data){
				$("#tqSearchSum").html((data)?data.countNum:0);
				var searchAllText = getSearchTextList()+"("+$("#searchOrgText").text()+")";
				$("#andSearchFields span.on").each(function(){
					if($(this).attr("value")){
						searchAllText += "、"+$(this).text();
					}
				})
				$("#tqSearchAllText").html(searchAllText);
				var $ul = $('#tqSearchList').find('ul');
				$('#tqSearchList').find('ul').removeClass('nodata');
				if($ul.children().length==0){
					$('#tqSearchList').find('ul').addClass('nodata');
				}
			}
		},option);
		TQSearch.submitForm(dfop);
	}
	TQSearch.submitMapForm =function(option){//houseSearchMapTPL
		var dfop = $.extend({
			formId:"tqSearchMapForm",
			listId:"tqSearchMapList",
			templateId:TQSearch["searchType"]+"MapTPL",
			success:function(data){
			}
		},option)
		TQSearch.submitForm(dfop);
	}
	
	TQSearch.submitForm =function(option){//houseSearchMapTPL
		var idfop = $.extend({
			formId:"",
			listId:"",
			templateId:"",
			searchText:"",
			page:1,
			rows:10,
			success:function(data){
			}
		},option)
		TQSearch.addLoading(idfop.listId);
		
		idfop.initState = true;
		var postData = $("#"+idfop.formId).serializeObject();
		postData["tqSearchVo.page"] = idfop.page;
		postData["tqSearchVo.rows"] = idfop.rows;
		postData["tqSearchVo.searchText"] = idfop.searchText;
		$.ajax({
			type:"post",
			async:true,
			url:PATH+"/baseinfo/tqSearch/tqSearch.action",
			data:postData,
			success:function(data){
				showRowsInfo({
					templateId:idfop.templateId,
					divId:idfop.listId,
					pageInfo:data
				})
				formatPageInfo(idfop.listId,{
					perpage: (data)?data.pageSize:10,
					page: (data)?data.pageNum:1,
					total:(data)?data.countNum:0,
			        onSelect: function(page) {
			        	if(!idfop.initState){
			        		idfop.page = page;
			        		TQSearch.submitForm(idfop);
			        	}
			        }
				});
				idfop.success(data);
				idfop.initState = false;
            }
		})
	}
	
	function showRowsInfo(option){
		var idfop = $.extend({
			templateId:"TPL",
			divId:"",
			pageInfo:""
		},option);
		var json = idfop.pageInfo;
		if(!json){
			$("#"+idfop.divId).empty();
		}
		for( var i = 0; i< json.length; i++){
			if(json[i].result){
				json[i].result[json[i].result.length-1].lastNode = true;
				json[i].result[0].firstNode = true;
			}
		}
		var times = 0;
		if($("#"+idfop.templateId).length>0){
			var relationHtml = template(idfop.templateId,{'json':json["result"]});
			relationHtml = formatterProperty(relationHtml);
			$("#"+idfop.divId).empty().html(relationHtml);
		}else if(times>=10){
			window.clearInterval(window["timeHandle"]);
			$.messageBox({message:"系统未配置地图列表的"+idfop.templateId+"，请联系管理员！"});
		}
	}
	
	function formatPageInfo(listId,option){
		var dfop = {
	        format: "< ncnn >]",
	        perpage: 10,
			lapping: 0,
			page: 1,
			total:0,
	        onSelect: function(page) {
	        	
	        },
	        onFormat: function(type) {
	            switch (type) {
	                case 'block':
	                        if (!this.active)
	                                return '<span class="disabled">' + this.value + '</span>';
	                        else if (this.value != this.page)
	                                return '<em><a href="#' + this.value + '">' + this.value + '</a></em>';
	                        return '<span class="current" style="font-weight: bolder;font-size: medium;">' + this.value + '</span>';
	                case 'next':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="next">下页</a>';
	                        return '<span class="disabled">Next</span>';
	                case 'prev':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="prev">上页</a>';
	                        return '<span class="disabled">Prev</span>';
	                case 'first':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="first">首页</a>';
	                        return '<span class="disabled">|</span>';
	                case 'last':
//	                        if (this.active)
//	                                return '<div><a href="#' + this.value + '" class="last">尾页</a></div>';
	                        return '<span class="disabled"></span>';
	                        //return '<div class="sumbox"><span class="sum">共'+this.number+'条</span></div>';
	                case "leap":
	                        if (this.active)
	                                return "...";
	                        return "";
	                case 'fill':
	                        if (this.active)
	                                return "...";
	                        return "";
	        	}
	        }
		}
	   $.extend(dfop,option);
	    return $("#"+listId+"Pager").paging(dfop.total,dfop);
	}
	function formatterProperty(relationHtml){
		if(relationHtml){
			for(var start = relationHtml.indexOf("<formatterProperty"); start>=0 ;start = relationHtml.indexOf("<formatterProperty")){
				var end = relationHtml.indexOf("</formatterProperty>") + "</formatterProperty>".length;
				var formatterProperty = relationHtml.substring(start,end);
				var $formatterProperty = $(formatterProperty);
				var property = $formatterProperty.attr("name");
				var value = $formatterProperty.attr("value");
				var dateFormat = $formatterProperty.attr("format");
				var name = value;
				try {
					if(!value){
						relationHtml = relationHtml.replace(formatterProperty,"");
						continue;
					}
					if("imgIcon" ==  property){
						name = getIconUrl("/resource/openLayersMap/images/blueBubble",value);
					}else if("locationHeadImg" ==  property && !name){
						name = PATH + "/resource/tqSearch/img/locationHead.png";
					}else if("populationHeadImg" ==  property && !name){
						name = PATH + "/resource/tqSearch/img/head.png";
					}else if(dateFormat){
						name = new Date(value.replace(/T/g," ").replace(/Z/g,"").replace(/-/g,"/")).format(dateFormat);
					}else{
						var datas = eval(property+"Data");
						name = (datas)?datas[value]:"";
						if("orgName" == property  && !name){
							var org = getSimpleOrgById(value);
							orgNameData[value] = org.orgName;
							name = org.orgName;
						}
					}
					relationHtml = relationHtml.replace(formatterProperty,name?name:"");
				} catch (e) {
					return relationHtml;
				}
				
			}
			var $relationHtmlDiv = $("<div>").append(relationHtml);
			var noImgUrl = $relationHtmlDiv.find("ul").attr("noImgUrl");
			if(!noImgUrl){
				noImgUrl = PATH+"/resource/tqSearch/img/nopic.jpg";
			}
			$relationHtmlDiv.find("li img[src='']").attr("src",noImgUrl);
			return $relationHtmlDiv.html();
		}
	}
	function getSimpleOrgById(orgId){
		var result="";
		$.ajax({
			method:'post',
			async:false,
			url:PATH+'/sysadmin/orgManage/getSimpleOrgById.action',
			data:{ "id":orgId },
			success:function(data){
				result = data;
			}
		});
		return result;
	} 
})
Date.prototype.format = function(format){
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(),    //day
	"h+" : this.getHours(),   //hour
	"H+" : this.getHours(),   //hour
 	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
 	"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
 	"S" : this.getMilliseconds() //millisecond
	}
	if(/(y+)/.test(format)){
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
 	for(var k in o){
		if(new RegExp("("+ k +")").test(format)){
 			format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
}