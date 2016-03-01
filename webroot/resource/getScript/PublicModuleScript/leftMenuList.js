TQ.define('leftMenuFun',[],function(){
	var leftMenuFun ={
	    timer: null
	    ,layoutFun:function(){
	        clearTimeout(leftMenuFun.timer);
	        var documentHeight=document.documentElement.clientHeight-$(".ui-layout-north").outerHeight()-$(".ui-layout-south").outerHeight();
	        leftMenuFun.accordion.height(documentHeight-$("#accordionMenuBDT").outerHeight(true));

	    }
	    ,jsflagRun : function(jsflag){
	    	if(!!jsflag && $("#"+jsflag)[0]){
	    		$('#'+jsflag).click().closest(".uiContBase:hidden").prev().click();
	        }else{
	        	leftMenuFun.allALabel.eq(0).click().closest(".uiContBase:hidden").prev().click();
	        }

	    }
	    ,init:function(dfop){

	        leftMenuFun.accordion = dfop.accordion;
	        leftMenuFun.allALabel = dfop.allALabel;

	        leftMenuFun.layoutFun();
	        $(window).resize(function(){
	        	clearTimeout(leftMenuFun.timer)
	            leftMenuFun.timer=setTimeout(function(){
	                leftMenuFun.layoutFun();
	            },200);
	        });

	        leftMenuFun.accordion.find(".uiMenuBase").unbind("click").on("click",function(){
	            $(this).next(".uiContBase").toggle().end().toggleClass("uiMenuCur");
	        })

	        leftMenuFun.accordion.find(".uiMenuBase").hover(function(){
	            $(this).addClass("uiMenuBaseHover")
	        },function(){
	            $(this).removeClass("uiMenuBaseHover")
	        })

	        leftMenuFun.allALabel.click(function(event){
	            var self = $(this);
	            var baseUrl=self.attr("baseUrl");
	            var leaderUrl=self.attr("leaderUrl");
	            var gridUrl=self.attr("gridUrl");
	            if(self.parent().hasClass("hasChild")){
	                self.closest("li").toggleClass("cur");
	            }
	            if(baseUrl==undefined && leaderUrl==undefined){
	                return false;
	            }

	            leftMenuFun.allALabel.removeClass("cur");
	            self.addClass("cur").closest('.uiContBase').show();

				if(self.attr("id") == "gridIntroductionManagement"){
					if(isCountryDownOrganization()){
						if(isGrid()){
							baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/gridProfileComplete.jsp';
						}else{
							baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/villageProfileComplete.jsp';
						}
					}else{
						baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/introduction.jsp';
					}
				}
	            
	            baseLoad(this,baseUrl,leaderUrl,gridUrl);
	        });

	        leftMenuFun.jsflagRun(dfop.jsflag)
	    }
	};

	return leftMenuFun;
});
/*
TQ.publicModule_leftMenu = function(dfop){
	var leftMenuFun = {
	    timer: null
	    ,layoutFun:function(){
	        clearTimeout(leftMenuFun.timer);
	        var documentHeight=document.documentElement.clientHeight-$(".ui-layout-north").outerHeight()-$(".ui-layout-south").outerHeight();
	        leftMenuFun.accordion.height(documentHeight-$("#accordionMenuBDT").outerHeight(true));

	    }
	    ,jsflagRun : function(jsflag){
	        if(!!jsflag || !$("#"+jsflag)[0]){
	            leftMenuFun.allALabel.eq(0).click().closest(".uiContBase:hidden").prev().click();
	        }else{
	            $('#'+jsflag).click().closest(".uiContBase:hidden").prev().click();
	        }

	    }
	    ,init:function(dfop){

	        leftMenuFun.accordion = dfop.accordion;
	        leftMenuFun.allALabel = dfop.allALabel;

	        leftMenuFun.layoutFun();
	        $(window).resize(function(){
	        	clearTimeout(leftMenuFun.timer)
	            leftMenuFun.timer=setTimeout(function(){
	                leftMenuFun.layoutFun();
	            },200);
	        });

	        
	        leftMenuFun.accordion.find(".uiMenuBase").on("click",function(){
	            $(this).next(".uiContBase").toggle().end().toggleClass("uiMenuCur");
	        })

	        leftMenuFun.accordion.find(".uiMenuBase").hover(function(){
	            $(this).addClass("uiMenuBaseHover")
	        },function(){
	            $(this).removeClass("uiMenuBaseHover")
	        })

	        leftMenuFun.allALabel.click(function(event){
	            var self = $(this);
	            var baseUrl=self.attr("baseUrl");
	            var leaderUrl=self.attr("leaderUrl");
	            var gridUrl=self.attr("gridUrl");
	            if(self.parent().hasClass("hasChild")){
	                self.closest("li").toggleClass("cur");
	            }
	            if(baseUrl==undefined && leaderUrl==undefined){
	                return false;
	            }

	            leftMenuFun.allALabel.removeClass("cur");
	            self.addClass("cur").closest('.uiContBase').show();

				if(self.attr("id") == "gridIntroductionManagement"){
					if(isCountryDownOrganization()){
						if(isGrid()){
							baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/gridProfileComplete.jsp';
						}else{
							baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/villageProfileComplete.jsp';
						}
					}else{
						baseUrl=PATH+'/hotModuel/baseinfo/villageProfile/introduction.jsp';
					}
				}
	            
	            baseLoad(this,baseUrl,leaderUrl,gridUrl);
	        });

	        leftMenuFun.jsflagRun(dfop.jsflag)
	    }
	}
	return leftMenuFun;
	
}
*/