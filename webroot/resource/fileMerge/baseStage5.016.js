/**
 * 根据户口号或身份证号异步查询常住人员信息
 */
;(function($) {
	$.fn.extend( {
		inhabitantAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchInhabitantAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		//孕妇
		pregnantPersonAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10,
				gender:0
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchPregnantPersonAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		pregnantPersonManAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10,
				gender:1
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=1;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchPregnantPersonAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									idCardNo : rowData[1],
									headName : rowData[2],
									houseAddress : rowData[3],
									name : rowData[6],
									gender : rowData[7],
									label : rowData[0] + " ," + rowData[1]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ,"+rowData[6]+" ,"+rowData[8],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		
		//李锋添加关于填写义工编号，自动补充义工姓名、性别、编号。
		volunteerAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"volunteerNumber" : value},defaultPostData);
			};
			
			var fillIndex=0;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/issue/volunteerService/searchVolunteerAutocomplete.action?ownerOrg.id="+getCurrentOrgId(),
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									volunteerNumber : rowData[0],
									id : rowData[1],
									vname : rowData[2],
									vsex : rowData[3],
									label : rowData[0] + " ," 
											+ " ," + rowData[1]+ " ," + rowData[2]+ " ," + rowData[4],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
	
	familyAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"yhh" : value},defaultPostData);
			};
			
			var fillIndex=0;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 1,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchFamilyAutocomplete.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									yhh : rowData[0],
									headIdCardNo : rowData[5],
									headName : rowData[2],
									houseAddress : rowData[3],
									telephone : rowData[4],
									label : rowData[0] + " ," + rowData[5]
											+ " ," + rowData[2]+ " ," + rowData[3]+" ," + rowData[4],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		},
		
		searchInhabitantAutocomplete : function(option) {
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend({"idCardNo" : value},defaultPostData);
			};
			
			var fillIndex=2;
			
			if(option&&option.fillIndex)
				fillIndex=option.fillIndex;
			
			var defaultOption={
				delay : 500,
				minLength : 15,
				source : function(request, response) {
					$.ajax({
						url : PATH
								+ "/baseinfo/searchInhabitantAutocomplete/searchInhabitantByIdCardNo.action",
						data : getPostData(request.term.toLowerCase()) ,
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									id : rowData[0],
									name : rowData[1],
									idCardNo : rowData[2],
									label : rowData[1] + " ," + rowData[2],
									value : rowData[fillIndex]
								};
							}));
						},
						error : function() {
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)
;(function($) {
	$.fn.extend( {
		actualPopulationAutocomplete : function(option) {
			var self=$(this);
			var defaultPostData = {
				rows : 10
			}; 
			$.extend(defaultPostData,option.postData);
			
			function getPostData(value){
				return $.extend(defaultPostData,{"idCardNo":value});
			};
			
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					if(request.term==''){
						request.term=null;
					}
					$.ajax({
						url : PATH
								+ "/commonPopulation/commonPopulationManage/findActualPopulationsByOrgIdAndIdCardNo.action",
						data : getPostData(request.term) ,
						success : function(_data) {
							self.data("size",_data.length);
							response($.map(_data, function(rowData) {
								return {
									id : rowData["id"],
									orgId : rowData.organization.id,
									name : rowData["name"],
									idCardNo : rowData["idCardNo"],
									label : rowData["idCardNo"] + " ," + rowData["name"],
									value : rowData["idCardNo"]
								};
							}));
						},
						error : function() {
							$.messageBox( {
								message : "搜索失败，请重新登入！",
								level : "error"
							});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)
;(function($) {
	$.fn.extend( {

		houseAutoComplete : function(option) {
			var self = $(this);
			var defaultPostData = {
//				address:'',
//				community:'',
//				block:'',
//				unit:'',
//				searchType : function(){return defaultOption.searchType},
//				orgId :function(){return getCurrentOrgId();},
				rows : 10
			};
			$.extend(defaultPostData,option.postData);

			function getPostData(value){
				return $.extend({"searchCondition" : value,
								 "searchByAddress":defaultOption.searchByAddress,
								 "searchType":defaultOption.searchLevel,
								 "orgId":getCurrentOrgId()},defaultPostData);
			};

			function getDisplayText(value){
				if (!value || value==""){
					return "...";
				}else{
					return value;
				}
			};

			function getDisplayLabel(rowData){
				if (defaultOption.searchByAddress){
					return rowData.address;
				} else if("houseCode"==defaultOption.searchLevel) {
					if(null == rowData.comnunity || rowData.comnunity == "") {
						return "编号："+getDisplayText(rowData.houseCode)+"，"+rowData.address;
					} else {
						return "编号："+getDisplayText(rowData.houseCode)+"，"+getDisplayText(rowData.comnunity)+"小区"+getDisplayText(rowData.block)+"幢"+getDisplayText(rowData.unit)+"单元"+getDisplayText(rowData.room);
					}
				} else{
					return getDisplayText(rowData.houseCode)+"，"+getDisplayText(rowData.comnunity)+"小区"+getDisplayText(rowData.block)+"幢"+getDisplayText(rowData.unit)+"单元"+getDisplayText(rowData.room);
				}
			};

			function getDisplayValue(rowData){
				if (defaultOption.searchByAddress){
					return rowData.address;
				}else if ("community"==defaultOption.searchLevel){
					return rowData.comnunity;
				}else if ("block"==defaultOption.searchLevel){
					return rowData.block;
				}else if ("unit"==defaultOption.searchLevel){
					return rowData.unit;
				}else if ("room"==defaultOption.searchLevel){
					return rowData.room;
				}else if ("houseCode"==defaultOption.searchLevel){
					return rowData.houseCode;
				}else{
					return rowData.houseId;
				}
			};

			var defaultOption={
				delay : 500,
				minLength : 0,
				searchByAddress:false,
				searchLevel:'',
				close:function(){
					//self.removeClass("errorInput").poshytip('disable');
				},
				source : function(request, response) {
					if(request.term==''){
						request.term=null;
//						response();
					}
					$.ajax({
						url : PATH + "/baseinfo/houseAutoComplete/findHousesByAddressLib.action",
						data : getPostData(request.term) ,
						success : function(_data) {
							self.data("data",_data);
							response($.map(_data, function(rowData) {
								return {
									houseId   : rowData.houseId,
									community : rowData.comnunity,
									block     : rowData.block,
									unit      : rowData.unit,
									room      : rowData.room,
									address   : rowData.address,
									houseCode : rowData.houseCode,
									addressType : rowData.addressType,
									label     : function(){return getDisplayLabel(rowData);},
									value     : function(){return getDisplayValue(rowData);}
								};
							}));
						},
						error : function() {
							$.messageBox( {
								message : "搜索失败，请重新登入！",
								level : "error"
							});
						}
					});
				}
			};
			$.extend(defaultOption,option);
			defaultOption.select=function(event,ui){
				if (option.select!=null){
					option.select(event,ui);
				}
			};
			$(this).autocomplete(defaultOption);
		}
	});

})(jQuery)

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


/*!
Math.uuid.js (v1.4)
http://www.broofa.com
mailto:robert@broofa.com
 
Copyright (c) 2010 Robert Kieffer
Dual licensed under the MIT and GPL licenses.
*/
 
/*
 * Generate a random uuid.
 *
 * USAGE: Math.uuid(length, radix)
 *   length - the desired number of characters
 *   radix  - the number of allowable values for each character.
 *
 * EXAMPLES:
 *   // No arguments  - returns RFC4122, version 4 ID
 *   >>> Math.uuid()
 *   "92329D39-6F5C-4520-ABFC-AAB64544E172"
 *
 *   // One argument - returns ID of the specified length
 *   >>> Math.uuid(15)     // 15 character ID (default base=62)
 *   "VcydxgltxrVZSTV"
 *
 *   // Two arguments - returns ID of the specified length, and radix. (Radix must be <= 62)
 *   >>> Math.uuid(8, 2)  // 8 character ID (base=2)
 *   "01001010"
 *   >>> Math.uuid(8, 10) // 8 character ID (base=10)
 *   "47473046"
 *   >>> Math.uuid(8, 16) // 8 character ID (base=16)
 *   "098F4D35"
 */
;(function() {
  // Private array of chars to use
  var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
 
  Math.uuid = function (len, radix) {
    var chars = CHARS, uuid = [], i;
    radix = radix || chars.length;
 
    if (len) {
      // Compact form
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      // rfc4122, version 4 form
      var r;
 
      // rfc4122 requires these characters
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
 
      // Fill in random data.  At i==19 set the high bits of clock sequence as
      // per rfc4122, sec. 4.1.5
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
 
    return uuid.join('');
  };
 
  // A more performant, but slightly bulkier, RFC4122v4 solution.  We boost performance
  // by minimizing calls to random()
  Math.uuidFast = function() {
    var chars = CHARS, uuid = new Array(36), rnd=0, r;
    for (var i = 0; i < 36; i++) {
      if (i==8 || i==13 ||  i==18 || i==23) {
        uuid[i] = '-';
      } else if (i==14) {
        uuid[i] = '4';
      } else {
        if (rnd <= 0x02) rnd = 0x2000000 + (Math.random()*0x1000000)|0;
        r = rnd & 0xf;
        rnd = rnd >> 4;
        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
      }
    }
    return uuid.join('');
  };
 
  // A more compact, but less performant, RFC4122v4 solution:
  Math.uuidCompact = function() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
      return v.toString(16);
    });
  };
})();
$.fn.tabPaging=function(o){
	var self = $(this);
	var dfop = {
        format: "< ncnn >]",
        perpage: 10,
		lapping: 0,
		page: 1,
        onSelect: function(page) {
        	
        },
        onFormat: function(type) {
            switch (type) {
                case 'block':
                        if (!this.active)
                                return '<span class="disabled">' + this.value + '</span>';
                        else if (this.value != this.page)
                                return '<em><a href="#' + this.value + '">' + this.value + '</a></em>';
                        return '<span class="current">' + this.value + '</span>';
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
                        return '<span class="disabled">|<</span>';
                case 'last':
                        if (this.active)
                                return '<div><span class="sum">共'+this.number+'条</span><a href="#' + this.value + '" class="last">尾页</a></div>';
                        return '<div><span class="sum">共'+this.number+'条</span></div>';
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
    $.extend(dfop,o);
    return self.paging(dfop.total,dfop);
}
$.fn.orgSelect=function(options,orgIds){
	var self=$(this);
	var selfId=self.attr("id");
	var dfop={
		url:'/sysadmin/orgManage/ajaxOrgsForExtTree.action?orgType=0&selectType='+$("#selectType").val()+'&simpleCode=213',
		level:[
			{
				org:'COUNTRY',
				text:'全国',
				orgLevelInternalId:60
			},
			{
				org:'PROVINCE',
				text:'省',
				orgLevelInternalId:50
			},
			{
				org:'CITY',
				text:'市',
				orgLevelInternalId:40
			},
			{
				org:'DISTRICT',
				text:'区',
				orgLevelInternalId:30
			},
			{
				org:'TOWN',
				text:'乡镇（街道）',
				orgLevelInternalId:20
			},
			{
				org:'VILLAGE',
				text:'村（社区）',
				orgLevelInternalId:10
			},
			{
				org:'GRID',
				text:'片组片格',
				orgLevelInternalId:0
			}
		],
		loadComplete:function(){
		},
		onSelect:function(orgId){
			
		},
		filter:function(orgId,orglevelinternalid){
			
		}
	}
	if(typeof options=='object'){
		$.extend(dfop,options);
		self.data("dfop",dfop);
	}
	var $orgBox,$orgBoxResult,$orgBoxSearchBox;
	$orgBox=$("#"+selfId+"box");
	$orgBoxResult=$orgBox.find(".orgBoxResult");
	$orgBoxSearchBox=$orgBox.find(".orgBoxSearchBox");
	function todoAjax(url,callback){//Ajax
		var jsonData;
		$(".orgLoadBox").show();
		$.ajax({
			url:url,
			async:false,
			success:function(jsonData){
				callback(jsonData);
				$(".orgLoadBox").hide();
			}
		})
	}
	function buildDom(jsonData){
		var $thisBox;
		var buildData=jsonData;
		if(buildData=='' && self.data("setOrg")!=true){
			$orgBox.closest(".ui-dialog").find(".ui-dialog-buttonpane").find("button:contains('确定')").click();
			return false;
		}
		for(var index=0;index<dfop.level.length;index++){//构建组织机构窗口
			var that=dfop.level[index];
			if(buildData==''){return false;}
			if(that.orgLevelInternalId==buildData[0].orgLevelInternalId){
				$(".organizationBox").hide();
				$thisBox=$orgBox.find(".organizationBox").show();
				$thisBox.show().find("h3").html(that.text);
				$thisBox.find(".organizationlist").empty();
			}
		}
		for(var i=0;i<buildData.length;i++){//build 组织机构列表
			var $item=$('<a href="javascript:;"></a>').attr({
				orgId:buildData[i].id,
				orgLevelInternalId:buildData[i].orgLevelInternalId,
				leaf:buildData[i].leaf
			}).append("<strong>"+buildData[i].text+"</strong>").append("<span class='arrows'></span>");
			if(buildData[i].leaf==true){
				$item.addClass("leaf");
			}
			if($thisBox){
				$thisBox.find(".organizationlist").append($item).show();
			}
		}
		bindEvent(buildData);
	}
	function bindEvent(jsonData){//绑定事件
		$orgBox.find(".organizationlist").unbind().delegate("a","click",function(){
			var that=$(this);
			var thisId=$(that).attr("orgId");
			var thisOrglevelinternalid=that.attr("orgLevelInternalId");
			var loadUrl=dfop.url;
			var thisNextDom=$(that).closest(".organizationBox").nextAll();
			if(dfop.filter(thisId,thisOrglevelinternalid)==false){//过滤
				return false;
			}
			if(loadUrl.indexOf("?")==-1){
				loadUrl=dfop.url+'?parentId='+thisId;
			}else{
				loadUrl=dfop.url+'&parentId='+thisId;
			}
			thisNextDom.each(function(index,thisDom){
				var listDom=$(thisDom).find(".organizationlist");
				if(listDom.find("a")[0]){
					$(thisDom).hide();
					listDom.empty();
				}
			})
			selectOrg(that);
			todoAjax(loadUrl,buildDom);
			$orgBox.find(".orgBoxResult").find("a").removeClass("cur").end().append($(that));
		});
		$orgBox.find(".orgBoxResult").unbind().delegate("a","click",function(event){
			var that=$(this);
			var thisId=that.attr("orgId");
			var thisOrglevelinternalid=that.attr("orgLevelInternalId");
			var loadUrl=dfop.url;
			if(dfop.filter(thisId,thisOrglevelinternalid)==false){//过滤
				return false;
			}
			if(loadUrl.indexOf("?")==-1){
				loadUrl=dfop.url+'?parentId='+thisId;
			}else{
				loadUrl=dfop.url+'&parentId='+thisId;
			}
			$(this).addClass("cur").nextAll("a").remove();
			todoAjax(loadUrl,buildDom);
			selectOrg($(this));
		});
	}
	function setOrgs(data){//遍历org
		self.data("setOrg",true);
		var dfop=self.data("dfop");
		for(var i=0;i<data.length;i++){
			var leaf='false';
			if(data[i].subCount>0){
				leaf='true';
			}
			var thisDom=$('<a href="javascript:;" orgid="'+data[i].id+'" orglevelinternalid="'+data[i].orgLevel.internalId+'" leaf="'+leaf+'"><strong>'+data[i].orgName+'</strong><span class="arrows"></span></a>');
			$orgBox.find(".orgBoxResult").append(thisDom);
		}
		$orgBox.find(".orgBoxResult a:last").addClass("cur").click();
		var loadUrl=dfop.url+'&parentId='+data[data.length-1].id;
		if(data[data.length-1].subCount>0){
			todoAjax(loadUrl,buildDom);
		}else{
			$orgBox.find(".organizationBox").hide();
		}
		self.data("setOrg",false);
	}
	function selectOrg(that){//组织机构选中事件
		var orgLevelInternalId=that.attr("orgLevelInternalId");
		$("#orgSelectInput").attr({
			"orgLevelInternalId":orgLevelInternalId,
			text:$.trim(that.text()),
			value:$.trim(that.attr("orgId"))
		})
		$("#currentCoordinate").html("所选区域："+that.text());
	}
	switch(options){
		case 'setOrgs':
			setOrgs(orgIds);
			break;
		default:
			bindEvent();
	}
}
;(function ($){
	/*$.fn.dynamicTagElement = function(options){
		var defaultOptions = {
				height      : "12px",
				width		: "25px"
		}
		var self=$(this);
		$.extend(defaultOptions,options);
		self.css("width",defaultOptions.width).css("height",defaultOptions.height).css("background-color","white");
	}*/
	$.fn.addDynamicTag = function(options){
		var defaultOptions = {
				id      :null,
				name		:null,
				outerDivId	:null,
				onCallBack	:null,
				isRemoved	:null
		}
		var self=$(this);
		$.extend(defaultOptions,options);
		var createConstruct="<div id='"+defaultOptions.outerDivId+"-"+defaultOptions.id+"' class='sendObjTextArea'>"
				+defaultOptions.name
				+"<a class='delete' id='"+defaultOptions.outerDivId+"closebtn-"+defaultOptions.id+"' href='javascript:void(0)'></a></div>";
		self.append(createConstruct);
		if(defaultOptions.onCallBack){
			defaultOptions.onCallBack(defaultOptions);
		}
		
		var aTagId = defaultOptions.outerDivId+"closebtn-"+defaultOptions.id;//要删除的a标签id
		$("#"+aTagId).click(function(){
			var self = $(this);
			var $removedElement = self.parent().remove();
			if($removedElement){
				var deletedId = self.attr("id");
				var index = deletedId.indexOf('-');
				var turnBackId = deletedId.substring(index+1,deletedId.length);
				//alert("turnBackId:"+turnBackId);
				defaultOptions.isRemoved(turnBackId);
			}
			return false;
		})
	}
	
})(jQuery);




















$.raphael=function(options){
	var self=this;
	var dfop={
		id:'',
		width:'100%',
		height:400,
		baseHeight:100,
		baseWidth:100,
		url:'',
		/*data:[
			{id:1,name:'网格办公室',orgLevelInternalId:0,relation:'上报',to:2,states:['加急','红牌督办','黄牌督办','普通督办','蓝牌督办']},
			{id:2,name:'村办公室',orgLevelInternalId:10,relation:'上报',to:3,states:['加急','红牌督办']},
			{id:3,name:'镇、街道办公室1',orgLevelInternalId:20,relation:'上报',to:4,states:['加急','红牌督办']},
			{id:4,name:'区办公室1',orgLevelInternalId:30,relation:'上报',to:5,states:['加急','红牌督办']},
			{id:5,name:'市委办公室1',orgLevelInternalId:40,relation:'交办',to:7,states:['加急','红牌督办']},
			{id:7,name:'市委办公室2',orgLevelInternalId:40,relation:'交办',to:8,states:['加急','红牌督办']},
			{id:8,name:'市委办公室3',orgLevelInternalId:40,relation:'交办',to:9,states:['加急','红牌督办']},
			{id:9,name:'区办公室2',orgLevelInternalId:30,relation:'回退',to:10,states:['加急','红牌督办']},
			{id:10,name:'市委办公室3',orgLevelInternalId:40,relation:'回退',to:11,states:['加急','红牌督办']},
			{id:11,name:'区办公室2',orgLevelInternalId:30,relation:'办结',states:['加急','红牌督办']}
		],*/
		boxHeight:35,//节点高
		boxWidth:100,//节点宽
		boxIntervalHeight:100,//节点上下边距
		boxIntervalWidth:150,//节点左右边距
		nodeClick:function(data){
			//alert("node");
		},
		relationClick:function(data){
			//alert("relation");
		},
		nodeMouseover:function(data){
			
		}
	}
	$.extend(dfop,options);
	var that=new Raphael(dfop.id, dfop.width,dfop.height);
	var nodeSet=that.set();
	var imageSet=that.set();
	var relationSet=that.set();
	var textSet=that.set();
	var color = Raphael.getColor();
	that.connections=[];
	that.popups=[];
	that.dragger = function () {
		this.ox = this.type == "rect" ? this.attr("x") : this.attr("cx");
		this.oy = this.type == "rect" ? this.attr("y") : this.attr("cy");
		this.animate({"fill-opacity": .2}, 500);
		
	};
	that.move = function (dx, dy) {
		var att = this.type == "rect" ? {x: this.ox + dx, y: this.oy + dy} : {cx: this.ox + dx, cy: this.oy + dy};
		var thatAttrs=this.attrs;
		var linkText=that.getById("text"+this.id);
		var motionText=that.getById("motion"+this.id);
		this.attr(att);
		if(linkText){
			var thisAtt={
				x:att.x+thatAttrs.width/2,
				y:att.y+thatAttrs.height/2
			};
			$.extend(true,att,thisAtt);
			linkText.attr(att);
		}
		for (var i = that.connections.length; i--;) {
			that.connection(that.connections[i]);
		}
		that.safari();
	};
	that.up = function () {
		this.animate({"fill-opacity": 0}, 500);
	};
	that.PopData=[];
	that.popup=function(options){
		var dfop={
			top:0,
			left:0,
			url:''
		};
		$.extend(dfop,options);
		var build=function(){
			var $dom;
			var buildFlag=true;
			if($("#holderBoxPop")[0]){
				$dom=$("#holderBoxPop");
			}else{
				$dom=$('<div class="raphaelTips" id="holderBoxPop"><a href="javascript:;" class="tip-close">关闭</a><div class="raphaelTips-text"></div><div class="raphaelTips-angle diamond"></div></div>');
				$dom.find(".tip-close").click(function(){
					$("#holderBoxPop").fadeOut(300);
				})
				$dom.appendTo("body");
			}
			var $popContent=$("#holderBoxPop").find(".raphaelTips-text").empty();;
			var thisData;
			var opts = {
			  lines: 10,
			  length: 10,
			  width: 5,
			  radius: 5,
			  corners: 1, 
			  rotate: 0, 
			  direction: 1,
			  color: '#999',
			  speed: 1,
			  trail: 100,
			  shadow: false,
			  hwaccel: false,
			  className: 'spinner',
			  zIndex: 2e9,
			  top: 'auto',
			  left: 'auto' 
			};
			$("#holderBoxPop").show().animate({
				top:$(dfop.thatDom[0]).offset().top-dfop.thatDom.attrs.height-160,
				left:$(dfop.thatDom[0]).offset().left+dfop.thatDom.attrs.width/2-132
			},50);
			for(var i=0;i<that.PopData.length;i++){
				if(that.PopData[i].id==dfop.thatDom.id || that.PopData[i].id=="text"+dfop.thatDom.id){
					thisData=that.PopData[i];
					buildFlag=false;
				}
			}
			if(dfop.url!='' && buildFlag==true){
				var spinner = new Spinner(opts).spin($popContent[0]);
				$.ajax({
					url:dfop.url,
					success:function(data){
						$popContent.html(data);
						thisData={id:dfop.thatDom.id,data:''}
						thisData.data=data;
						that.PopData.push(thisData);
					}
				})
			}else{
				$popContent.html(thisData.data);
			}
			$(".issueRight").scroll(function(){
				$("#holderBoxPop").hide();
			})
		}()
	}
	that.connection=function (obj1, obj2, line, bg,thisRelation,triangle) {
		if (obj1.line && obj1.from && obj1.to) {
			line = obj1;
			obj1 = line.from;
			obj2 = line.to;
		}
		var bb1 = obj1.getBBox(),
			bb2 = obj2.getBBox(),
			p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
			{x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
			{x: bb1.x - 1, y: bb1.y + bb1.height / 2},
			{x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
			{x: bb2.x + bb2.width / 2, y: bb2.y - 1},
			{x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
			{x: bb2.x - 1, y: bb2.y + bb2.height / 2},
			{x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
			d = {}, dis = [];
		for (var i = 0; i < 4; i++) {
			for (var j = 4; j < 8; j++) {
				var dx = Math.abs(p[i].x - p[j].x),
					dy = Math.abs(p[i].y - p[j].y);
				if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
					dis.push(dx + dy);
					d[dis[dis.length - 1]] = [i, j];
				}
			}
		}
		if (dis.length == 0) {
			var res = [0, 4];
		} else {
			res = d[Math.min.apply(Math, dis)];
		}
		var x1 = p[res[0]].x,
			y1 = p[res[0]].y,
			x4 = p[res[1]].x,
			y4 = p[res[1]].y;
		dx = Math.max(Math.abs(x1 - x4) / 2, 10);
		dy = Math.max(Math.abs(y1 - y4) / 2, 10);
		var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
			y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
			x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
			y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
		var arrow=function (l, k, d) {
            var g = Math.atan2(l.y - k.y, k.x - l.x) * (180 / Math.PI);
            var h = k.x - d * Math.cos(g * (Math.PI / 180));
            var f = k.y + d * Math.sin(g * (Math.PI / 180));
            var e = h + d * Math.cos((g + 120) * (Math.PI / 180));
            var j = f - d * Math.sin((g + 120) * (Math.PI / 180));
            var c = h + d * Math.cos((g + 240) * (Math.PI / 180));
            var i = f - d * Math.sin((g + 240) * (Math.PI / 180));
            return "M" + k.x + " " + k.y + "L" + e + " " + j + "L" + c + " " + i + "z";
        }
        var path = ["M", x1.toFixed(3), y1.toFixed(3), "L", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
		if (line && line.line) {
			line.bg && line.bg.attr({path: path});
			line.line.attr({path: path});
			if(line.relation){//add motion
				line.relation.attr({
					x:(x1+x4)/2,
					y:(y1+y4)/2
				})
			}
		} else {
			var arrayPath=arrow({x:x3,y:y3},{x:x4,y:y4},5);
			var connection={
				line:this.path(path).attr({'stroke-width':1,fill:'none',stroke: line}),
				from: obj1,
				to: obj2,
				array:this.path(arrayPath).attr({stroke:line,fill: line})
			}
			if(thisRelation){//init motion
				connection.relation=thisRelation.attr({
					x:(x1+x4)/2,
					y:(y1+y4)/2
				})
			}
			if(that.connections != undefined){
				that.connections[that.connections.length]=connection;
			}else{
				that.connections[0]=connection;
			}
			return connection;
		}
	};//连线
	var createNode=function(id,x, y, width, height,r){
		var thatRect=that.rect(x, y, width, height,r).attr({fill: '90-#FFE084:10-#FFF4D2', "stroke-width":1,stroke:'#FFD65F', cursor: "hand"}).mouseover(function(){//绑定指向事件
			if(this.glows!=undefined){
				this.glows.show();
			}else{
				this.glows=this.glow({color:'#FFD65F',width:7});
			}
		}).mouseout(function(){//绑定鼠标移出事件
			this.glows.hide();
		});
		thatRect.id=id;
		nodeSet.push(thatRect);
		return thatRect;
	};
	var createText=function(id,x,y,name){
		var thatText=that.text(x, y, name).attr({font: 'bold 14px "microsoft yahei", Arial',fill: '#B22D00',cursor:'hand'}).mouseover(function(){//绑定指向事件
			var thisDate={
				id:this.id,
				type:this.nodeType
			}
			if(that.getById(this.nodeId).type=='rect'){
				if(this.glows!=undefined){
					this.glows.show();
				}else{
					this.glows=that.getById(this.nodeId).glow({color:'#FFD65F',width:7});
				}
			}
			clearTimeout(window._holderPopTimerOut);
		}).mouseout(function(){//绑定鼠标移出事件
			var thisDate={
				id:this.id,
				type:this.nodeType
			}
			if(that.getById(this.nodeId).type=='rect'){
				this.glows.hide();
			}
		});
		thatText.id="text"+id;
		thatText.nodeId=id;
		textSet.push(thatText);
		return thatText;
	};
	var createRelation=function(id,x,y,name){
		var thatRelation=that.text(x, y, name).attr({font:'normal 14px "microsoft yahei"', fill: "#333","stroke-linejoin": "round",cursor:'hand'})
		//thatMotion.id="motion"+id;
		relationSet.push(thatRelation);
		return thatRelation;
	};
	var createImage=function(src,x,y,w,h){
		var thatImage=that.image(src,x, y,w,h);
		
		//thatMotion.id="motion"+id;
		imageSet.push(thatImage);
		return thatImage;
	};
	var calculateIndex=function(data){
		var index=1;
		var maxIndex=1;
		function conculationJiaoBan(data,i){
			if(data[i-1].orgLevelInternalId==data[i].orgLevelInternalId){
				return data[i-1].index+1;
			}
			if(data[i-1].orgLevelInternalId>data[i].orgLevelInternalId){
				if(!data[i-2]||data[i-2].orgLevelInternalId>=data[i-1].orgLevelInternalId){
					return data[i-1].index;
				}else {
					return data[i-1].index+1;
				}
			}
		}
		function conculationHuitui(data,i){
			return data[i-1].index+1;	
		}
		function conculationJieAn(data,i){
			return data[i-1].index+1;	
		}
		function conculationShangbao(data,i){
			if(data[i-2]&&data[i-2].orgLevelInternalId>data[i-1].orgLevelInternalId){
				return data[i-1].index+1;
			}else if(data[i-1].orgLevelInternalId==data[i].orgLevelInternalId){
				return data[i-1].index+1;
			}else{
				return data[i-1].index;
			}
		}
		for(var i=0;i<data.length;i++){
			if(i==0){
				data[i].index=index;
			}
			if(i!=0){
				if(data[i-1].relation=='上报'){
					index=conculationShangbao(data,i)
					data[i].index=index;
				}
				if(data[i-1].relation=='交办'){
					index=conculationJiaoBan(data,i);
					data[i].index=index;
				}
				if(data[i-1].relation=='回退'){
					index=conculationHuitui(data,i);
					data[i].index=index;
				}
				if(data[i-1].relation=='结案'){
					index=conculationJieAn(data,i);
					data[i].index=index;
				}
			}
		}
		return data;
	}
	var createNodes=function(holder,thisData,maxOrgLevel){
		var x=(thisData.index-1)*dfop.boxIntervalWidth+10;
		var y=(maxOrgLevel/10-(thisData.orgLevelInternalId/10))*dfop.boxIntervalHeight+10;
		var box=createNode(thisData.id,x, y, dfop.boxWidth, dfop.boxHeight, 3).click(function(){
			dfop.nodeClick(thisData);
		}).mouseover(function(){
			clearTimeout(window._holderPopTimerOut);
			var that=this;
			holder.popup({thatDom:that,url:"/issues/issueManage/viewIssueDealLog.action?issueMap.id="+thisData.id+"&issueMap.orgId="+thisData.orgId+"&statusType="+thisData.statusType});
			$("#holderBoxPop").unbind("mouseover").mouseover(function(){
				clearTimeout(window._holderPopTimerOut);
			}).unbind("mouseout").mouseout(function(){
				window._holderPopTimerOut=setTimeout(function(){
					$("#holderBoxPop").hide();
				},500);
			})
		}).mouseout(function(){
			clearTimeout(window._holderPopTimerOut);
			window._holderPopTimerOut=setTimeout(function(){
				$("#holderBoxPop").hide();
			},300);
		})
		var thisName=createText(thisData.id,x+dfop.boxWidth/2,y+dfop.boxHeight/2,thisData.name).click(function(){
			dfop.nodeClick(thisData);
		})
		$.extend(true,box,{//遍历box对象
			text:thisData.text,
			orgLevelInternalId:thisData.orgLevelInternalId,
			index:thisData.index
		})
		return box;
	}
	var createState=function(holder,thisData){
		var thisBox=holder.getById(thisData.id);
		var toBox=holder.getById(thisData.to);
		var x=thisBox.attr("x");
		var y=thisBox.attr("y");
		
		var boxWidth=thisBox.attrs.width;
		var boxHeight=thisBox.attrs.height;
		var boxInterval=thisBox.attrs.interval;
		var buildState=function(id,x,y,name){
			var iconLength=0;
			for(var i=0;i<thisData.states.length;i++){//构建状态
				var iconW=14;
				var iconH=16;
				var thisX=x+boxWidth/2-iconW*(iconLength+1)-5*iconLength;
				var thisY=y+boxHeight/2-iconH/2;
				switch(thisData.states[i]){
					case '加急':
						createImage('/resource/system/images/issue/icon_Emerignce.png',thisX,thisY,12,12);
						iconLength++;
						break;
					case '普通督办':
						createImage('/resource/system/images/issue/icon_bHandle.png',thisX,thisY,iconW,iconH);
						iconLength++;
						break;
					case '黄牌督办':
						createImage('/resource/system/images/issue/icon_yHandle.png',thisX,thisY,iconW,iconH);
						iconLength++;
						break;
					case '红牌督办':
						createImage('/resource/system/images/issue/icon_rHandle.png',thisX,thisY,iconW,iconH);
						iconLength++;
						break;
					case '蓝牌督办':
						createImage('/resource/system/images/issue/icon_rHandle.png',thisX,thisY,iconW,iconH);
						iconLength++;
						break;
				}
			}
		}
		var buildMotion=function(id,x,y,motion){
			var thisRelation=createRelation(id,x,y,motion);
			return thisRelation;
		}
		
		buildState(thisData.id,x+boxWidth/2,y+boxHeight/2,thisData.name)
		if(thisData.id && thisData.to){//连线
			var toBox=holder.getById(thisData.to);
			var toX=toBox.attr("x");
			var toY=toBox.attr("y");
			var centerX=(x+toX)/2+boxWidth/2;
			var centerY=(y+toY)/2+boxHeight/2;
			var thisRelation=buildMotion(thisData.id,centerX,centerY,thisData.relation).click(function(){
				dfop.relationClick(thisData);
			});
			holder.connection(thisBox,toBox,"#888",'',thisRelation,true);
		}
	}
	function buildNodes(data){
		var maxOrgLevel=0;
		var minOrgLevel=60;
		var maxIndex=1;
		for(var i=0;i<data.length;i++){
			if(maxOrgLevel<data[i].orgLevelInternalId){
				maxOrgLevel=data[i].orgLevelInternalId;
			}
			if(minOrgLevel>data[i].orgLevelInternalId){
				minOrgLevel=data[i].orgLevelInternalId;
			}
		}
		for(var i=0;i<data.length;i++){
			createNodes(that,data[i],maxOrgLevel);
			if(maxIndex<data[i].index){
				maxIndex=data[i].index;
			}
		}
		$(that.canvas).width(maxIndex*(dfop.boxWidth+dfop.boxIntervalWidth/2)).height((maxOrgLevel-minOrgLevel)/10*dfop.boxIntervalHeight+dfop.boxHeight*2);//设定画布宽高
		for(var i=0;i<data.length;i++){
			createState(that,data[i]);
			
		}
	}
	if(dfop.url!=''){
		$.ajax({
			type:'POST',
			url:dfop.url,
			async:false,
			success:function(data){
				data=calculateIndex(data);
				buildNodes(data);
			}
		})
	}else{
		data=calculateIndex(dfop.data);
		buildNodes(data);
	}
	return that;
}
