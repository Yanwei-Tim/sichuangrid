$.fn.issueFlow = function(option){
	var self=$(this);
	var selfId=self.attr("id");
	var dfop={
		data:[],
		stepHeight:200,
		stepWidth:200
    }
    $.extend(dfop,option);
    var curColourIndex = 1, maxColourIndex = 24, nextColour = function() {
		var R,G,B;
		R = parseInt(128+Math.sin((curColourIndex*3+0)*1.3)*128);
		G = parseInt(128+Math.sin((curColourIndex*3+1)*1.3)*128);
		B = parseInt(128+Math.sin((curColourIndex*3+2)*1.3)*128);
		curColourIndex = curColourIndex + 1;
		if (curColourIndex > maxColourIndex) curColourIndex = 1;
		return "rgb(" + R + "," + G + "," + B + ")";
	};	
    this.PopData=[];
	this.popup=function(options){
		var that=this;
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
				top:$(dfop.thatDom[0]).offset().top-dfop.thatDom.height()-110,
				left:$(dfop.thatDom[0]).offset().left+dfop.thatDom.width()/2-130
			},50);
			$("#holderBoxPop").click(function(event){
				event.stopPropagation();
			})
			for(var i=0;i<that.PopData.length;i++){
				if(that.PopData[i].id==dfop.thatDom.data("id")){
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
						thisData={id:dfop.thatDom.data("id"),data:''}
						thisData.data=data;
						that.PopData.push(thisData);
					}
				})
			}else{
				$popContent.html(thisData.data);
			}
			$(document).one("click",function(){
				$("#holderBoxPop").hide();
			});
			$(".issueRight").scroll(function(){
				$("#holderBoxPop").hide();
			})
		}()
	};
	this.build=function(data){
    	var stepHeight=dfop.stepHeight+10;
    	var stepWidth=dfop.stepWidth;
    	var $stepBox='';
    	var domData=new Array();
    	var maxOrgLevel=0;
    	for(var i=0;i<data.length;i++){
	    	var bool=true;
	    	var indexBool=true;
	    	if(maxOrgLevel<data[i].orgLevelInternalId){
	    		maxOrgLevel=data[i].orgLevelInternalId
	    	}
			for(var j=0;j<domData.length;j++){
				if(data[i].orgId==domData[j].orgId){bool=false;}
				
			}
			if(bool==true){
				var index=0;
				for(var j=0;j<domData.length;j++){
					if(data[i].orgLevelInternalId==domData[j].orgLevelInternalId){
						index++;
					}
				}
				data[i].index=index;
				domData.push(data[i]);
			}
	    }
		for(var i=0;i<domData.length;i++){
			var top=(maxOrgLevel/10-(domData[i].orgLevelInternalId/10))*stepHeight;
			var left=(stepWidth+250)*domData[i].index;
			var thisStep='<div class="step" style="top:'+top+'px;left:'+left+'px;" id="'+selfId+'step'+domData[i].orgId+'" data-id="'+domData[i].id+'" data-orgid='+domData[i].orgId+'>'+'<span class="name">'+domData[i].name+'</span>'+'</div>';
			$stepBox=$stepBox+thisStep;
		}
		self.append($stepBox);
    };
    this.connect = function(data){
    	this.connects=[];
	    for(var i=0;i<data.length;i++){
	    	var toStep=data[i].to;
	    	$(data).each(function(){
	    		if(this.id==toStep){
	    			toStep=this.orgId;
	    		}
	    	})
    	    if(toStep){
    	    	var connectOption={
    	    		source:selfId+"step"+data[i].orgId, 
					target:selfId+"step"+toStep, 
					ConnectionsDetachable :false,
					joinstyle:"round",
					connector:[ "StateMachine", { curviness:1 } ],
					connectorStyle:{
						lineWidth:4,
						strokeStyle:"#deea18",
						joinstyle:"round",
						outlineColor:"#EAEDEF",
						outlineWidth:2
					},
					paintStyle:{ 
						lineWidth:3,
						strokeStyle:"#c6591e",
						dashstyle:"4 2",
						joinstyle:"miter"
					},
					detachable:false,
					anchor:"", 
					endpointsOnTop:true,
					overlays:[
						["Label", {
							label :'<div class="relation" id="label_'+data[i].id+'" data-id="'+data[i].id+'" data-orgid="'+data[i].orgId+'"><div class="number">'+(i+1)+'</div>'+'<div class="text">'+data[i].relation+'</div></div>'
						}],
						["Arrow",{
							location:1,
							id:"arrow",
		                    length:14,
		                    foldback:0.8
						}]
					]
    	    	}
		    	/*if(data[i].relation=="回退"){
		    		$.extend(connectOption,{
		    			anchors:[[0.75,0,0,0],[0.75,0,0,0]]
		    		})
		    	}*/
    			var thisConnect=jsPlumb.connect(connectOption); 
    			$(thisConnect.canvas).attr("data-id",data[i].id).attr("data-orgid",data[i].orgId);
    			this.connects.push(thisConnect);
    	    }
	    }
	    /*function initStates(thisData){
    		var state='';
			for(var i=0;i<thisData.states.length;i++){//构建状态
				switch(thisData.states[i]){
					case '加急':
						state=state+'<div class="states"><img src="/resource/system/images/issue/icon_Emerignce.png" /></div>';
						break;
					case '普通督办':
						state=state+'<div class="states"><img src="/resource/system/images/issue/icon_Emerignce.png" /></div>'
						break;
					case '黄牌督办':
						state=state+'<div class="states"><img src="/resource/system/images/issue/icon_yHandle.png" /></div>'
						break;
					case '红牌督办':
						state=state+'<div class="states"><img src="/resource/system/images/issue/icon_rHandle.png" /></div>'
						break;
					case '蓝牌督办':
						state=state+'<div class="states"><img src="/resource/system/images/issue/icon_rHandle.png" /></div>'
						break;
				}
			} 
			return state;
    	}
	    for(var i=0;i<data.length;i++){
		    var state=initStates(data[i]);
		    if(state!=''){
		    	$("#issueFlowstep"+data[i].orgId).append(state);
		    }
	    }*/
	};
	this.init = function(data) {
		var that=this;
		that.build(data);
		jsPlumb.reset();
		//初始化
		jsPlumb.importDefaults({
			Endpoint : ["Dot", {radius:0.1}],
			Connector:"StateMachine",
			HoverPaintStyle : {strokeStyle:"#42a62c", lineWidth:2 },
			ConnectionOverlays : [
				["Arrow",{ 
					location:1,
					id:"arrow",
                    length:14,
                    foldback:0.8
				}]
			]
		});
        //拖动
		jsPlumb.draggable($("#"+selfId+" .step"),{
			create:function(){
				$(this).data("defaultLeft",$(this).css("left")).data("defaultTop",$(this).css("top"));
			},
			stop:function(){
				jsPlumb.animate($(this).attr("id"), {left:$(this).data("defaultLeft"), top:$(this).data("defaultTop")}, { duration:1400, easing:'easeOutBack' });
			}
		});
		
        //label点击
		$("#"+selfId).delegate(".relation","click", function(event) {
			var thatDom=$(this);
			var thisId=thatDom.data("id");
			var thisOrgId=thatDom.data("orgid");
			that.popup({thatDom:thatDom,url:"/issues/issueManage/viewIssueDealLog.action?issueMap.id="+thisId+"&issueMap.orgId="+thisOrgId});
			event.stopPropagation();
		})

		//jsPlumb.bind("jsPlumbConnection", function(connInfo, originalEvent) { 
			//that.initRelation(connInfo);
		//});
		
        jsPlumb.makeTarget($("#"+selfId+" .step"), {
			dropOptions:{ hoverClass:"dragHover" },
			anchor:"Continuous"				
		});
		$("#"+selfId+" .step").each(function(i,e) {			
			jsPlumb.makeSource($(e), {
				anchor:"Continuous",
				connectorStyle:{ strokeStyle:nextColour(), lineWidth:2 }
			});
		});
        that.connect(data);
        //jsPlumb.getAllConnections();获取所有连线
	}
	this.init(dfop.data);
	return this;
}
    