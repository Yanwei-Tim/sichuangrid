$.extend({
	addGisTabs:function(url,tabTitle){
		var tabs=$('#tabmenulist li a').filter(function(index) {
			return $(this).text() == tabTitle.replace('<em>','').replace('</em>','');
		});
		if(tabs[0]){
			var thisIndex=tabs.attr("index");
			$("#gisTabs").tabs("remove",thisIndex);
		}		
		$("#gisInfoBox").hide();
		$("#gisSearchBox").show();
		$("#gisTabs").tabs("add", url, tabTitle);
		$("#gisTabs").tabs("select",$("#gisTabs").tabs("length")-1);
	}
})
$.fn.extend({
	tabPaging:function(o){
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
	                        return '<div class="sumbox"><span class="sum">共'+this.number+'条</span></div>';
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
	},
	gisTabs:function(o){
		var that=this;
		var self=$(this);
		var selectTabsFun=function(index){
			$("#moreTabs a").eq(index).addClass("cur").siblings().removeClass("cur");
			var thisTabs=$("#tabmenulist li");
			var showIndex=$("#tabmenulist li.ui-tabs-selected:first").index();
			var hideTabsBol=true;
			thisTabs.filter(".showItems").each(function(){
				if($(this).index()==index){
					hideTabsBol=false;
				}
			})
			if(hideTabsBol==true){
				thisTabs.hide().removeClass("showItems");
				for(var i=index;i>index-3;i--){
					thisTabs.eq(i).show().addClass("showItems");
				}
			}
		}
		var dfop={
			tabTemplate: "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>删除</span></li>",
			create:function(){
				
			},
			cache:true,
			add: function(event,ui) {
				$("#gisSearchBox").show();
				var tab_content ="检索中,请稍候...";
				$(ui.panel).append( "<div>" + tab_content + "</div>" );
				$(ui.panel).height($("#gisContainer").height()-35);
				$(window).resize(function(){
					$(ui.panel).height($("#gisContainer").height()-35);
				})
				$(ui.tab).attr("index",ui.index).clone().prepend(">>").appendTo("#moreTabs");
			},
			select:function(event,ui){
				$("#moreTabs a").eq(ui.index).addClass("cur").siblings().removeClass("cur");
				selectTabsFun(ui.index);
			},
			remove:function(event,ui){
				var index=$(ui.tab).attr("index");
				var sum=$("#tabmenulist li").size();
				if(sum<=0){
					$("#gisSearchBox").hide();
					$("#gisInfoBox").show();
				}
				$("#moreTabs a").eq(index).remove();
			}
		}
		$.extend(dfop,o);
		var $tabs = self.tabs(dfop);
		$("#tabmenulist").delegate("span.ui-icon-close","click", function() {//移除事件
			var index = $("li",$("#tabmenulist")).index( $( this ).parent());
			$tabs.tabs("remove", index);
		});
		
		$(".gis_tools_sys .more").hover(function(){
			clearTimeout(that.timer);
			that.timer=setTimeout(function(){
				$("#moreTabs").show();
			},100)
		},function(){
			clearTimeout(that.timer);
			that.timer=setTimeout(function(){
				$("#moreTabs").hide();
			},300)
		})
		
		$("#moreTabs").delegate("a","click",function(event){
			event.preventDefault();
			var index=$(this).index();
			self.tabs("select",index);
			$("#moreTabs").hide();
		})
		$(".move_left").on("click",function(){
			var thisIndex=$("#tabmenulist li.ui-tabs-selected").index();
			var sum=$("#tabmenulist li").size()-1;
			if(thisIndex>0){
				$tabs.tabs( "select",thisIndex-1);
			}
		})
		$(".move_right").on("click",function(){
			var thisIndex=$("#tabmenulist li.ui-tabs-selected").index();
			var sum=$("#tabmenulist li").size()-1;
			if(thisIndex<sum){
				$tabs.tabs( "select",thisIndex+1);
			}
		})
		window.tabIndex=0;
		return $tabs;
	},
	scrollBar:function(o){
		var self=$(this);
		var dfop={
			W:"10px",									//设置滚动条宽度
			BgUrl:"",									//设置滚动条背景图片地址
			Bg:"scrollbar_bgy.png",						//设置滚动条背景图片position,颜色等
			Bar:{
				Pos:"",							//设置滚动条初始化位置在底部
				Bd:{Out:"",Hover:""},				//设置滚动滚轴边框颜色：鼠标离开(默认)，经过
				Bg:{Out:"#ccc",Hover:"",Focus:"#000"}		//设置滚动条滚轴背景：鼠标离开(默认)，经过，点击
			},
			Btn:{  btn:false},							//设置下按钮背景：鼠标离开(默认)，经过，点击
			Fn:function(){}								//滚动时候触发的方法
		}
		$.extend(dfop,o);
		self.droppable(dfop);
		self.jscroll(dfop);
	},
	clearField:function(s){//<input type="text" class="clearField" value="请输入用户名" />   调用方法：$('.clearField').clearField();
		s=jQuery.extend({blurClass:'clearFieldBlurred',activeClass:'clearFieldActive',attribute:'rel',value:''},s);
		return $(this).each(function(){
			var el=$(this);
			s.value=el.val();
			if(el.attr(s.attribute)==undefined){
				el.attr(s.attribute,el.val()).addClass(s.blurClass)
			}else{
				s.value=el.attr(s.attribute)
			}
			el.focus(function(){
				if(el.val()==el.attr(s.attribute)){
					el.val('').removeClass(s.blurClass).addClass(s.activeClass)
				}
			});
			el.blur(function(){
				if(el.val()==''){
					el.val(el.attr(s.attribute)).removeClass(s.activeClass).addClass(s.blurClass)
				}
			})
		})
	},
	ajaxResult:function(option){
		var self=jQuery(this);
		var selfId=self.attr("id");
		var timestamp = Date.parse(new Date());
		var selfContent=self.find(".resultCon");
		var timer = null;
		var dfop={
			rowNum:5,
			total:2,
			page:1,
			searchTxt:'',
			type : "get",
	    	paramData : {},
			url:'',
			keyId:'id',
			dataModel:[
				{name:'id',hidden:true},
				{name:'title'},//,hidden:true,formatter:aaa
				{name:'address'}	
   			],
			data:{"page":"1","total":10,"records":"13",
				rows:[
					{id:'111',title:'记录1',address:'地址1'},
					{id:'112',title:'记录2',address:'地址2'},
					{id:'113',title:'记录3',address:'地址3'},
					{id:'114',title:'记录4',address:'地址4'},
					{id:'115',title:'记录5',address:'地址5'},
					{id:'116',title:'记录6',address:'地址6'},
					{id:'117',title:'记录7',address:'地址7'},
					{id:'118',title:'记录8',address:'地址8'},
					{id:'119',title:'记录9',address:'地址9'},
					{id:'110',title:'记录10',address:'地址10'}]},//数据格式
			height: 'auto',
			width:'auto',
		   	sortname: 'id',
		    sortorder: "desc",
			onSelectRow:function(rowId,rowData){},
			pageShowNum:5,
			ondblClickRow:function(rowId,rowData){
				
			},
			loadComplete:function(){}
		}
		jQuery.extend(dfop,option);
		var ajaxFun=function(){
			jQuery.extend(dfop.paramData,{"rows":dfop.rowNum,"page":dfop.page});
			if(dfop.url){
				jQuery.ajax({
					url:dfop.url,
					type:dfop.type,
					data:dfop.paramData,
					success:function(data){
						dfop.total=data.total;
						dfop.data=data;
						dfop.page=data.page;
						init();
						dfop.loadComplete();
					}
				});
			}else{
				init();
				dfop.loadComplete();
			}
		}
		var pageInit=function(){
			var showNum=dfop.pageShowNum;
			var i=1;
			var total;
			if(dfop.data.records%dfop.rowNum==0){
				total=parseInt(dfop.data.records/dfop.rowNum);
			}else{
				total=parseInt(dfop.data.records/dfop.rowNum)+1;
			}
			
			var nowPageLength=(showNum>=total)? total:showNum;
			var resultPager=jQuery('<div />').attr("id","Page"+timestamp).addClass("number");
			if(parseInt(dfop.page/showNum)>=1){//是否生成上一页
				i=parseInt(dfop.page/showNum)*showNum;
				nowPageLength=(i+showNum>=total)? total:i+showNum;
				var thisPage=jQuery('<a href="javascript:;"></a>').text(1).attr("value",1);
				var morePage=jQuery('<a href="javascript:;"></a>').text("…").attr("title","上一组").attr("value",i-1);
				resultPager.append(thisPage).append(morePage);
			}
			
			for(;i<=nowPageLength;i++){//生成页码
				var thisPage;
				if(dfop.page==i || dfop.total<=1){
					thisPage=i;
				}else{
					thisPage=jQuery('<a href="javascript:;"></a>').text(i).attr("value",i);
				}
				resultPager.append(thisPage);
			}
			if(total>nowPageLength){//判断是否显示下一组
				if(nowPageLength>=showNum){
					var morePage=jQuery('<a href="javascript:;"></a>').text("…").attr("title","下一组").attr("value",nowPageLength+1);
					resultPager.append(morePage);
				}
			}
			
			if(dfop.total>=1){
				var nextPage=jQuery('<a href="javascript:;" class="next">下页></a>').attr("value",parseInt(dfop.page)+1);
				var prevPage=jQuery('<a href="javascript:;" class="prev">上页<</a>').attr("value",parseInt(dfop.page)-1);
								
				if(dfop.page>1){
					resultPager.prepend(prevPage);
				}
				
				if(parseInt(dfop.page)<dfop.total){
					resultPager.append(nextPage);
				}
			}
			resultPager.append('<a href="javascript:;">共'+total+'页</a>');
			
			resultPager.delegate("a", "click", function(){
				var thisValue=jQuery(this).attr("value");
				if(thisValue==undefined){return;}
				jQuery(this).after('<span>'+jQuery(this).text()+'</span>').remove();
				dfop.page=thisValue;
				selfContent.empty();
				ajaxFun();
			});
			self.append(resultPager);
		}
		
		//取得数组的值
		var getRowData=function(rowId){
			var thisRowData;
			jQuery.each(dfop.data.rows,function(i, n){
				if(n[dfop.keyId]==rowId){
					thisRowData=n;
				};
			});
			return thisRowData;
		}
		
		var init=function(){
			var showRowNumber=(dfop.data.rows.length>=dfop.rowNum)?dfop.rowNum:dfop.data.rows.length;
			var resultCon=selfContent;
			var resultNum='<div class="resultNum"><span class="plaint"></span>共有<span class="red">'+dfop.data.records+'</span>项符合<span class="red">'+dfop.searchTxt+'</span>的搜索条件</div>';
			var resultList=jQuery("<ul />").addClass("resultlist");//结果列表
			for(var i=0;i<showRowNumber;i++){
				var rowNum=i+1;
				var thisRowData=new Array();
				var rowId;
				var thisRow=jQuery('<li />').data("data",dfop.data.rows[i]);
				for(var j=0;j<dfop.dataModel.length;j++){//根据Model中设置的字段，循环取得结果集的各个字段
					var rowValue = "";
					if(dfop.dataModel[j].name.split(".").length > 1){
						var obj
						for(var index = 0; index < dfop.dataModel[j].name.split(".").length ; index++){
							if(index == (dfop.dataModel[j].name.split(".").length-1)){
								rowValue = obj[dfop.dataModel[j].name.split(".")[index]];
							}else{
								obj = dfop.data.rows[i][dfop.dataModel[j].name.split(".")[index]];
							}
						}
					}else{
						rowValue =dfop.data.rows[i][dfop.dataModel[j].name];
					}
					if(rowValue == "null"){
						rowValue = "";
					}
					var thisAttr=jQuery('<div />').attr("aria-describedby",dfop.dataModel[j].name);
					if(dfop.dataModel[j].key==true){
						thisRow.attr("rowId",rowValue);
					}
					if(dfop.dataModel[j].hidden==true){
						thisAttr.hide();
					}
					if(dfop.dataModel[j].formatter){
						thisAttr.append(dfop.dataModel[j].formatter(thisRow,dfop.dataModel,dfop.data.rows[i]));
					}else{
						thisAttr.append(rowValue);//根据model中的字段取得并添加当前记录
					}
					thisRow.append(thisAttr);
				}
				
				thisRow.bind("click",function(){
					var thisId=jQuery(this).attr("rowId");
					var row=jQuery(this);
					clearTimeout(timer);
					var rowData=getRowData(thisId);
					timer = setTimeout(function() {
						$(".resultBar").data("selectrow", getRowData(thisId));
						dfop.onSelectRow(thisId,rowData);
					}, 250);
				})
				thisRow.bind("dblclick",function(){
					var thisId=jQuery(this).attr("rowId");
					clearTimeout(timer);
					var rowData=getRowData(thisId);
					$(".resultBar").data("selectrow", getRowData(thisId));
					dfop.ondblClickRow(thisId,rowData);
				})
				resultList.append(thisRow);
			}
			//清空
			self.empty();
			resultCon.empty();
			//插入
			selfContent.append(resultList)
			self.append(resultNum).append(resultCon).append();
			//生成页码
			pageInit();
			//高度问题
			jQuery(".resultlist").delegate("li", "click", function(){
				jQuery(".resultlist li").removeClass("cur");
				jQuery(this).addClass("cur");
			})
		}
		
		ajaxFun();
		return this;
		
	}
})

$.fn.extend({
    tablist:function(options){

        var $this=$(this);
        var $gisList=$this.children("ul");
        var $gislistLi=$gisList.children("li");
        var $gisCon=$this.children("div");

        var defultOptions={
                div:'.searchContain',
                updownlist:'.updownList',
                current:'current'
        };

        $.extend(defultOptions,options);

        var searchTab=function(){
            $gislistLi.each(function(index){
                $(this).click(function(){
                    $(this).addClass(defultOptions.current).siblings().removeClass(defultOptions.current);
                    $gisCon.children(defultOptions.div).hide().eq(index).show();

                    if(index==0){
                        $(defultOptions.updownlist).show();
                    }else{
                        $(defultOptions.updownlist).hide();
                    };
                });
            });
        }();
    },
    gisSearchSelect:function(options){

        var $self=$(this);
        var $selfId=$self.attr("id");
        var $options=$self.children("option");
        var $downlist=$("<div class='updownList'></div>");
        var $sarrow=$("<div class='sarrow'></div>");
        var $createDl=$("<dl/>");
        var $createDt=$("<dt/>");
        var $createDd=$("<dd class='updwonlist-Content'></dd>").attr("id",$selfId+"Content");
        var $createUl=$("<ul/>");
        var $c=$options.first().text();

        $self.after($downlist);
        $downlist.append($sarrow);
        $downlist.append($createDl);
        $createDl.append($createDt);
        $createDl.append($createDd);
        $createDd.append($createUl);
        $createDt.text($c);

        var defaultOptions={
                updownlist:'.updownList',
                updownlistC:'.updwonlist-Content',
                current:'cur',
                inputText:'#queryString',
                change:function(){

                },
                linkageFunc:function(){

                }
        };

        $.extend(defaultOptions,options);

        var previewDefault="";

        var init=function(){

            $options.each(function(index){

                var self=$(this);
                var firstOption=$options.eq(0);
                var $createLl=$("<li/>");
                var thisValue=self.attr("value");
                var thisName=self.attr("name");
                var thisTitle=self.attr("title");
                var thisAlt=self.attr("alt");
                var thisDir=self.attr("dir");
                var thisModeType=self.attr("modeType");
                var thisSearchfieldname=self.attr("searchfieldname");
                var thisSearchfield=self.attr("searchfield");

                var $firstTitle=$.trim(firstOption.text());
                var $con=$.trim(self.text());

                var searchfieldaname=$.trim(firstOption.attr("name"));
                var searchfieldbname=$.trim(firstOption.attr("title"));
                var searchfielda=$.trim(firstOption.attr("alt"));
                var searchfieldb=$.trim(firstOption.attr("dir"));
                var modeType=$.trim(firstOption.attr("modeType"));
                var modeValue=$.trim(firstOption.attr("value"));
                var searchfieldname=$.trim(firstOption.attr("searchfieldname"));
                var searchfield=$.trim(firstOption.attr("searchfield"));

                $createUl.append($createLl);

                $createDt.attr({
                    "name":searchfieldaname,
                    "title":$firstTitle,
                    "alt":searchfielda,
                    "dir":searchfieldb,
                    "value":modeValue,
                    "modeType":modeType,
                    "searchfieldname":searchfieldname,
                    "searchfield":searchfield
                });

                $createLl.attr({
                    "title":$con,
                    "value":thisValue,
                    "name":thisName,
                    "alt":thisAlt,
                    "dir":thisDir,
                    "modeType":thisModeType,
                    "searchfieldname":thisSearchfieldname,
                    "searchfield":thisSearchfield
                }).text($con);

                var str="请输入("+$createDt.eq(0).attr("name");
                if(searchfieldbname!="" && thisSearchfieldname==""){
                    str+="/"+searchfieldbname;
                }
                if(thisSearchfieldname!="" && searchfieldbname==""){
                    str+="/"+thisSearchfieldname;
                }
                if(searchfieldbname!="" && thisSearchfieldname!=""){
                    str+="/"+searchfieldbname+"/"+thisSearchfieldname;
                }
                str+=")";
                if($createDt.eq(0).attr("name")==''){
                    str="请输入搜索条件";
                    previewDefault=str;
                    excutefocusFunc(str);
                }

                $(defaultOptions.inputText).attr("value",str);
                //$(defaultOptions.inputText).attr("value","请输入("+$createLl.eq(0).attr("name")+"/"+searchfieldbname+"/"+thisSearchfieldname+")"); //default

                var $defaultValueC=$(defaultOptions.inputText).attr("value");

                excutefocusFunc($defaultValueC);

                previewDefault=$defaultValueC;

                $createLl.bind("click",function(){

                    var thisText=$(this).text();
                    var currentOption=$options.eq(index);

                    var thisFieldaname=$.trim(currentOption.attr("name"));
                    var thisFieldbname=$.trim(currentOption.attr("title"));
                    var thisFielda=$.trim(currentOption.attr("alt"));
                    var thisFieldb=$.trim(currentOption.attr("dir"));
                    var thisModeType=$.trim(currentOption.attr("modeType"));
                    var thisSearchfieldname=$.trim(currentOption.attr("searchfieldname"));
                    var thisSearchfield=$.trim(currentOption.attr("searchfield"));
                    var $defaultValue=$(defaultOptions.inputText).attr("value");

                    var str="请输入("+thisFieldaname;
                    if(thisFieldbname!="" && thisSearchfieldname==""){
                        str+="/"+thisFieldbname;
                    }
                    if(thisSearchfieldname!="" && thisFieldbname=="" ){
                        str+="/"+thisSearchfieldname;
                    }
                    if(thisFieldbname!="" && thisSearchfieldname!=""){
                        str+="/"+thisFieldbname+"/"+thisSearchfieldname;
                    }
                    str+=")";

                    if(thisFieldaname==''){
                        str="请输入搜索条件";
                        excutefocusFunc(str);
                    }
                    if(thisFieldaname==''&&($(defaultOptions.inputText).attr("value")==previewDefault||$(defaultOptions.inputText).attr("value")=='')){
                        $(defaultOptions.inputText).attr("value",str);
                        previewDefault=str;
                    }

                    if($(defaultOptions.inputText).attr("value")==''||$(defaultOptions.inputText).attr("value")==previewDefault){
                        previewDefault=str;
                        $(defaultOptions.inputText).attr("value",str);
                    }
                    excutefocusFunc(str);
                    if($(defaultOptions.inputText).attr("value")!=''&&$(defaultOptions.inputText).attr("value")!=previewDefault){
                        previewDefault=str;
                    }

                    //$(defaultOptions.inputText).attr("value","请输入("+thisFieldaname+"/"+thisFieldbname+"/"+thisSearchfieldname+")");   //click

                    $(this).addClass(defaultOptions.current).siblings().removeClass(defaultOptions.current);
                    $(this).text($(this).text());
                    $createDt.text($(this).text()).attr("title",$con);
                    $self.attr("value",thisValue);
                    $createDt.attr({
                        "name":thisFieldaname,
                        "title":thisFieldbname,
                        "alt":thisFielda,
                        "dir":thisFieldb,
                        "value":thisValue,
                        "modeType":thisModeType,
                        "searchfieldname":thisSearchfieldname,
                        "searchfield":thisSearchfield
                    });

                    defaultOptions.change(thisText,thisValue);
                    defaultOptions.linkageFunc(thisValue);
                    //excutefocusFunc($defaultValue);
                    $createDd.hide();

                });
             });
        }();

        var clickUnit=function(){
            $createDt.bind("click",function(e){
                triggleContent();
            });

            $sarrow.bind("click",function(e){
                triggleContent();
            });

            $(document).on("click",function(e){
                var $target=$(e.target);
                if(!($target.is(defaultOptions.updownlistC) || $target.closest(defaultOptions.updownlistC)[0] || $target.closest(defaultOptions.updownlist)[0])){
                    $createDd.hide();
                }
            });

            function triggleContent(){
                if($createDd.is(":visible")){
                     $createDd.hide();
                }else{
                     $createDd.show();
                }
            }
        }();

        function excutefocusFunc($defaultValue){
            $(defaultOptions.inputText).unbind("focusin").bind("focusin",function(){
                if($(defaultOptions.inputText).attr("value")==$defaultValue){
                    $(defaultOptions.inputText).attr("value","");
                }
            }).unbind("focusout").bind("focusout",function(){
                if($(defaultOptions.inputText).val()!=""){
                    //$(defaultOptions.inputText).attr("value");
                }else{
                    $(defaultOptions.inputText).attr("value",$defaultValue);
                }
            });
        }
    },
    updownlist:function(options){

        var $self=$(this);
        var $options=$self.children("option");
        var $downlist=$("<div class='updownList'></div>");
        var $createDl=$("<dl/>");
        var $createDt=$("<dt/>");
        var $createDd=$("<dd class='updwonlist-Content'></dd>");
        var $createUl=$("<ul/>");
        var $c=$options.first().text();

        $self.after($downlist);
        $downlist.append($createDl);
        $createDl.append($createDt);
        $createDl.append($createDd);
        $createDd.append($createUl);
        $createDt.text($c);

        var init=function(){
            $options.each(function(index){
                var $createLl=$("<li/>");
                var thisValue=$(this).attr("value");
                var $con=$(this).text();

                $createUl.append($createLl);
                $createLl.text($con).attr("value",thisValue);
                    $createLl.click(function(){
                        var thisText=$(this).text();
                        var thisValue=$(this).attr("value");

                        $(this).text($(this).text());
                        $createDt.text($(this).text());
                        $self.attr("value",thisValue);
                        $createDd.hide();
                    });
             })	;
        }();

        $createDt.click(function(){
            if($createDd.is(":visible")){
                 $createDd.hide();
            }else{
                 $createDd.show();
                 $downlist.prevAll().click(function(){
                     $createDd.hide();
                 });
            }
        });

    },
    upDownList:function(options){

        var $this=$(this);
        var $exeContent=$this.children("div");
        var $allSpan=$exeContent.children("h1").children("span");
        var $exeClist=$exeContent.children("div");

        var defaultOptions={
            current:'downCur'
        }

        $.extend(defaultOptions,options);

        $exeContent.each(function(index){

            var $exeCT=$(this).children("h1");
            var $exeCTC=$(this).children("div");
            var $exeCtitleCur=$($exeCT,this);

            var $exeClistCur=$($exeCTC,this);
            var $span=$exeCtitleCur.find("span");

            $exeCtitleCur.find("span").click(function(){
                if($exeClistCur.is(":visible")){
                    $exeClistCur.hide();
                    $span.addClass(defaultOptions.current).removeClass(defaultOptions.current);

                }else{
                    $exeClistCur.show();
                    $span.removeClass(defaultOptions.current).addClass(defaultOptions.current);
                    if($exeClistCur.is(":visible")){
                        showLayer();
                    };
                }
            });

            function showLayer(){
                $exeClistCur.show();
                $allSpan.removeClass(defaultOptions.current);
                $span.addClass(defaultOptions.current);
            }
        });
    },
    MultiLevelMenu:function(options){
        var self=$(this);
        var timer;
        var defaultOption={
                childObj:'showPersonLayer',
                currentHover:'currentPosHover',
                currentClick:'currentPosClick',
                checkedFun:function(){},
                uncheckedFun:function(){},
                hoverFun:function(){},
                removeFunc:function(){}
            };
        $.extend(defaultOption,options);
        function displayMultiLevelMenu(){
            $("#"+defaultOption.childObj+">ul>li").hover(function(){

                if($(this).children("ul")[0]){
                    $(this).removeClass("currentPointer").addClass("currentDefault");
                    $(this).children("ul").children("li").addClass("currentPointer");
                    $(this).children("ul").children("li").find("label").css({
                                                                            cursor:"pointer"
                                                                        });
                }else{
                    $(this).addClass("currentPointer").removeClass("currentDefault");
                    $(this).find("label").css({
                                                cursor:"pointer"
                                            });
                }

                defaultOption.hoverFun(this);
                $("#"+defaultOption.childObj+">ul>li").find("ul").hide();
                $(this).find("ul").show();
                $("#"+defaultOption.childObj+">ul>li").removeClass(defaultOption.currentHover);
                $(this).addClass(defaultOption.currentHover);
                $(this).find("ul").children("li").hover(function(){
                    $(this).addClass(defaultOption.currentHover);
                },function(){
                    $(this).removeClass(defaultOption.currentHover);
                })
                var ul=$(this).find("ul");
                var sublen=ul.find("li").size();
                if(sublen>3&&$(this).index()>5){
                    ul.css({top:-28*(sublen-1)});
                }
            },function(){
                timer=setTimeout(function(){
                    $(this).find("ul").hide();
                },1000)
                $(this).removeClass(defaultOption.currentHover);
            })
        }

        displayMultiLevelMenu();

        function displayEffect(){
            self.find("ul>li").delegate("label","click",function(){

                if($(this).parent().children("ul")[0]){
                    $(this).parent().find("li").removeClass(defaultOption.currentClick);
                    $(this).parent().removeClass(defaultOption.currentClick);
                }else{
                    if($(this).parent("li").hasClass(defaultOption.currentClick)){

                        $(this).parent("li").removeClass(defaultOption.currentClick);
                    }else{
                        $(this).parent("li").addClass(defaultOption.currentClick).siblings().removeClass(defaultOption.currentClick);
                        $(this).parents("li").siblings().find("ul li").removeClass(defaultOption.currentClick);
                    }
                }
            });

        }
        displayEffect();
    },
    scrollWay:function(options){

        var $this=$(this);
        var maxLength= 0,
            upCount  = 0;

        dfop={
            floor:'.displayFloor',
            up:'',
            down:'',
            left:'',
            right:'',
            upLine:'1',
            leftLine:'11',
            speed:'3000',
            num:'1',
            pObject:'table',
            cObject:'tr',
            crObject:'td'
        };
        $.extend(dfop,options);

        /*button*/
        var _btnUp = $("#"+dfop.up),_btnDown = $("#"+dfop.down), _btnLeft = $("#"+dfop.left), _btnRight = $("#"+dfop.right);

        /*elements*/
        var scrollObj =$this.find(dfop.pObject);
        var floorCon=$(dfop.floor);

        var displayFloor=$("<ul/>").addClass("floorList").appendTo(floorCon);

        var marginUpSpace = parseInt(scrollObj
                .find(dfop.cObject+":first")
                .css("marginTop"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first")
                        .css("marginBottom"),10);

        var marginLeftSpace	= parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("marginLeft"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("marginRight"),10);

        var paddingLeftSpace = parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("paddingLeft"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("paddingRight"),10);

        var paddingUpSpace	= parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("paddingTop"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("paddingBottom"),10);

        var borderTopSpace = parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("border-bottom-width"),10);


        var trHeight = parseInt(scrollObj
                .find(dfop.cObject+":first")
                .height(),10);

        // 合并前默认宽度
        var tdWidth	= parseInt(scrollObj
                .find(dfop.cObject+":first")
                .find(dfop.crObject)
                .outerWidth(),10);

        var allTdColspan = scrollObj.find(dfop.cObject+":first").find(dfop.crObject).attr("colspan");

        //合并后获取最小宽度
        if(allTdColspan > 1){
            //判断全部被合并的情况下
            tdWidth	=parseInt(scrollObj.find(dfop.cObject+":first").find(dfop.crObject).outerWidth(),10)/allTdColspan;

        }else{
            //判断非全部合并获取最小宽度
//            scrollObj.find(dfop.cObject+":first").find(dfop.crObject).each(function(index){
//                var _allTd = scrollObj.find(dfop.cObject+":first").find(dfop.crObject);
//
//                if(_allTd.eq(index).outerWidth() > _allTd.eq(index-1).outerWidth()){
//                    tdWidth = _allTd.eq(index-1).outerWidth();
//                }
//            })
        }

        var lineTrH=marginUpSpace+trHeight+borderTopSpace;
        var lineTdW=tdWidth;
        var m=trSingleHeightLine = dfop.upLine ? parseInt(dfop.upLine, 10):parseInt($this.height()/lineTrH,10);
        var n=tdSingleWidthLine = dfop.leftLine ? parseInt(dfop.leftLine, 10):parseInt($this.width()/lineTdW,10);

        var upHeight = trSingleHeightLine * lineTrH;
        var leftWidth = tdSingleWidthLine * lineTdW;
        var spd = dfop.speed ? parseInt(dfop.speed,10):600;

        $this.data("len",scrollObj.find(dfop.cObject).length/parseInt(dfop.num,10));

        function getLeftCount(){
        	 /*max length*/
            scrollObj.find(dfop.cObject).each(function(index){
                var thisLength=$(this).children(dfop.crObject).size();
                if(maxLength<thisLength){
                    maxLength=thisLength;
                }
            });
            return maxLength/dfop.num;
        }
        /*create Floor*/
        function screateFloor(){
            var length=scrollObj.find(dfop.cObject).length;
            scrollObj.find(dfop.cObject).each(function(index){
                var index=length-index;
                var createLi=$("<li/>")
                        .html("<div>第</div><div>"+index+"</div><div>层</div>")
                        .appendTo(displayFloor)
                        .height(lineTrH-1);

                createLi.children("div").css("lineHeight",lineTrH/3+'px');

                $(this).find(dfop.crObject).each(function(Tindex){
                    var Tindex=Tindex+1;
                    if(Tindex<10){
                        if($(this).parent().find("td.split")[0]){

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",(index)+'0'+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }else{

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",index+'0'+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }

                    }else{
                        if($(this).parent().find("td.split")[0]){

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",(index+1)+''+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }else{

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",index+''+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }
                    }
                })
            })
        }
        screateFloor();

        /*scroll Floor*/
        function scrollUp() {

            if (!scrollObj.is(":animated")) {
                upCount = $this.data("len");
                if (m < upCount) {

                    m += trSingleHeightLine;
                    scrollObj.animate({ marginTop: "-=" + upHeight + "px" }, spd);
                    displayFloor.animate({
                        marginTop: "-=" + upHeight + "px"
                        }, spd);

                    if(m==upCount){
                        _btnUp.removeClass("upEnable")
                            .addClass("upDisable upHover");
                    }else{
                        _btnUp.addClass("upEnable")
                            .removeClass("upDisable");

                        _btnDown.addClass("downEnable")
                            .removeClass("downDisable");
                    }
                }
            }
        };
        function scrollDown() {
            if (!scrollObj.is(":animated")) {
                if (m > trSingleHeightLine) {
                    m -= trSingleHeightLine;
                    scrollObj.animate({
                            marginTop: "+=" + upHeight + "px"
                        }, spd);
                    displayFloor.animate({
                            marginTop: "+=" + upHeight + "px"
                        }, spd);

                    if(m==trSingleHeightLine){
                        _btnDown.removeClass("downEnable")
                        .addClass("downDisable downHover");

                    }else{
                        _btnDown.addClass("downEnable")
                        .removeClass("downDisable");


                        _btnUp.addClass("upEnable")
                        .removeClass("upDisable");

                    }

                }
            }
        };
        function scrollLeft() {
        	var leftCount=getLeftCount();
            if (!scrollObj.is(":animated")) {
                if (n < leftCount) {
                    n += tdSingleWidthLine;
                    scrollObj.animate({
                            marginLeft:"-="+leftWidth+"px"
                        }, spd);

                    if(n==leftCount){
                        _btnLeft.removeClass("leftEnable")
                            .addClass("leftDisable leftHover");
                    }else{
                        _btnLeft.addClass("leftDisable")
                            .removeClass("leftEnable");
                        _btnRight.addClass("rightEnable")
                            .removeClass("rightDisable");
                    }

                }
            }
        };
        function scrollRight() {
            if (!scrollObj.is(":animated")) {
                if (n > tdSingleWidthLine) {
                    n -= tdSingleWidthLine;
                    scrollObj.animate({
                            marginLeft: "+=" + leftWidth + "px"
                        }, spd);
                    if(n==tdSingleWidthLine){
                        _btnRight.removeClass("rightEnable")
                            .addClass("rightDisable rightHover");
                    }else{
                        _btnRight.addClass("rightEnable")
                            .removeClass("rightDisable");
                        _btnLeft.addClass("leftEnable")
                            .removeClass("leftDisable");
                    }
                }
            }
        };
        function scorllLeftFunc(){
            if(n<getLeftCount()){
                scrollLeft();
                scrollRight();
            }else{
                return false;
            }
        }

        function scorllRightFunc(){
            if(n>dfop.leftLine){
                scrollRight();
            }else{
                return false;
            }
        }

        /*triggle event*/
        function triggleEvent(){
            _btnUp.unbind("click").bind("click", scrollUp);
            _btnDown.unbind("click").bind("click", scrollDown);
            _btnLeft.unbind("click").bind("click", scorllLeftFunc);
            _btnRight.unbind("click").bind("click", scorllRightFunc);
        };
        triggleEvent();
    }
});