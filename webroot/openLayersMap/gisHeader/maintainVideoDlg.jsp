<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %> 
    <jsp:include page="/includes/baseInclude.jsp" />
    <script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/json2.js"></script>
	<link  rel="stylesheet"  href="${resource_path}/resource/openLayersMap/css/video.css"></link>	
		<div id="videoPlay">
			<div class="videoPlayLeft">
				<OBJECT id="VideoOcx" classid="clsid:02EFF2E9-3D57-461F-BDAC-7A598239E53C" style="z-index:9" width=470 height=430 hspace=0 vspace=0></OBJECT>
			</div>
		    <div class="videoPlayRight">
		    	<div class="videoInfo">
		        	<h2>视频信息<a href="javascript:;" id="videotapeSearch">视频查询</a></h2>
		            <ul>
		            	<s:if test="objectList[0].detailFieldNameA!=null && objectList[0].detailFieldNameA!='' ">
							<li>${objectList[0].detailFieldNameA}：${objectList[0].detailFieldA}</li>
						</s:if>
						<s:if test="objectList[0].detailFieldNameB!=null && objectList[0].detailFieldNameB!='' ">
							<li>${objectList[0].detailFieldNameB}：${objectList[0].detailFieldB}</li>
						</s:if>
						<s:if test="objectList[0].detailFieldNameC!=null && objectList[0].detailFieldNameC!='' ">
							<li>${objectList[0].detailFieldNameC}：${objectList[0].detailFieldC}</li>
						</s:if>
						<s:if test="objectList[0].detailFieldNameD!=null && objectList[0].detailFieldNameD!='' ">
							<li>${objectList[0].detailFieldNameD}：${objectList[0].detailFieldD}</li>
						</s:if>
						<s:if test="objectList[0].detailFieldNameE!=null && objectList[0].detailFieldNameE!='' ">
							<li>${objectList[0].detailFieldNameE}：${objectList[0].detailFieldE}</li>
						</s:if>
		            </ul>
		      </div>
		        <div class="videoInfo mt10">
		        	<h2>操作盘
		            	<label for="select">速率调节：
		                    <select id="setParam" onchange="setParam(this.value)">
								<option value="0" selected>0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
							</select>
		             	</label>
		            </h2>
		          <div class="operation">
		            	<div class="multiple big_small">
		                	<span class="title">变倍</span>
		                	<a href="javascript:;" class="putbig" onclick="ptzControl(11,1);" title="放大"></a>
		                  	<a href="javascript:;" class="putsmall" onclick="ptzControl(12,1);" title="缩小"></a>
		                  	<img name="vertical" class="jian" src="${resource_path}/resource/openLayersMap/images/video/j1.png" />
		                </div>
		                <div class="direction">
		                	<span class="title">方向</span>
		                    <a href="javascript:;" name="stop" title="自动" class="centerQuan"></a>
		                    <img class="up der" id="s1" src="${resource_path}/resource/openLayersMap/images/video/up.png" />
		                    <img class="rightUp der" id="s2" src="${resource_path}/resource/openLayersMap/images/video/rightUp.png" />
		                    <img class="right der" id="s3" src="${resource_path}/resource/openLayersMap/images/video/right.png" />
		                    <img class="downright der" id="s4" src="${resource_path}/resource/openLayersMap/images/video/downright.png" />
		                    <img class="down der" id="s5" src="${resource_path}/resource/openLayersMap/images/video/down.png" />
		                    <img class="leftDown der" id="s6" src="${resource_path}/resource/openLayersMap/images/video/leftdown.png" />
		                    <img class="left der" id="s7" src="${resource_path}/resource/openLayersMap/images/video/left.png" />
		                    <img class="leftUp der" id="s8" src="${resource_path}/resource/openLayersMap/images/video/leftUp.png" />
		                    <img src="${resource_path}/resource/openLayersMap/images/video/blankpng.png" alt="" width="154" height="154" usemap="#Map" class="blank" border="0" />
		                    <map name="Map" id="Map">
		                    <area onclick="ptzControl(21,1);" shape="poly" class="map" id="m1" coords="53,17,68,50,74,49,81,50,86,51,89,47,102,15,95,12,85,10,74,10,60,11,53,16" href="#" />
		                    <area onclick="ptzControl(26,1);" shape="poly" class="map" id="m2" coords="100,64,137,50,130,38,120,26,108,18,103,15,88,51" href="#" />
		                    <area onclick="ptzControl(24,1);" shape="poly" class="map" id="m3" coords="102,84,102,74,100,65,137,51,140,59,142,73,142,84,138,95,136,100,104,86" href="#" />
		                    <area onclick="ptzControl(28,1);" shape="poly" class="map" id="m4" coords="102,133,87,98,91,95,96,92,100,86,135,101,132,109,127,116,122,121,113,128,104,133" href="#" />
		                    <area onclick="ptzControl(22,1);" shape="poly" class="map" id="m5" coords="68,99,53,135,60,138,69,140,76,140,85,139,95,138,101,135,86,99,77,100" href="#" />
		                    <area onclick="ptzControl(27,1);" shape="poly" class="map" id="m6" coords="52,135,44,131,35,125,26,116,20,107,17,100,52,85,60,93,65,98,68,99" href="#" />
		                    <area onclick="ptzControl(23,1);" shape="poly" class="map" id="m7" coords="17,99,53,84,52,79,52,72,52,66,16,51,14,60,12,73,13,87" href="#" />
		                    <area onclick="ptzControl(25,1);" shape="poly" class="map" id="m8" coords="52,64,17,50,21,41,29,30,35,24,46,18,52,16,67,51" href="#" />
		                    </map>
		            </div>
		            <div class="multiple r big_small">
	                	<span class="title">焦距</span>
	                	<a href="javascript:;" class="putbig" onclick="ptzControl(13,1);" title="前调"></a>
	                    <a href="javascript:;" class="putsmall" onclick="ptzControl(14,1);" title="后调"></a>
	                    <img name="vertical" class="jian" src="${resource_path}/resource/openLayersMap/images/video/j.png" />
	                </div>
		            </div>
		            <div class="clear"></div>
		            <div class="gq big_small">
		            	<span class="title">光圈</span>
		            	<a href="javascript:;" class="putbig" onclick="ptzControl(15,1);" title="扩大"></a>
		                <a href="javascript:;" class="putsmall" onclick="ptzControl(16,1);" title="缩小"></a>
		                <img name="horizontal" src="${resource_path}/resource/openLayersMap/images/video/j2.png" class="jian" />
		            </div>
		        </div>
		    </div>
		</div>
		<input type="hidden" value='<s:property value="content"/>' id="content">
		
	<script>	
	var content=$("#content").val();
	var data = eval('(' + content + ')').content;  
	//var content='<s:property value="content"/>';
	//alert(content);
	//alert(dustbinCode);
	//var data=$("#VideoOcx")[0].GetLinkVideoParam(dustbinCode);
	//alert(data);
	//$("#VideoOcx").attr("classid","clsid:"+data.objId);
		$(function(){
			if(checkVideoOcxActiveIsExists()){
				initVideoOcx();//初始化视频
			}else{
				alert("请先安装视频插件!");
			}
			
			
			var i;
			$(".map").hover(function(){
				i= $(this).index() + 1;	
				$("#s" + i).css({"display":"block"});
			},function(){
				$("#s" + i).css({"display":"none"});
			});
			$(".centerQuan").click(function(){
				var name = $(this).attr("name");
				if(name=="play"){
					$(this).attr("title","停止");
					$(this).attr("name","stop");
					ptzControl(28,0);
				}else if(name=="stop"){
					$(this).attr("title","自动");
					$(this).attr("name","play");
					ptzControl(29,1);
				}
			})
			$(".big_small>a").each(function(){
				$(this).click(function(){
					var bigger = $(this).hasClass("putbig");
					var smaller = $(this).hasClass("putsmall");
					var $img = $(this).nextAll("img");
					var direction = $img.attr("name");
					if(bigger){
						if(direction=="horizontal") {
							$img[0].style.left = "220px";
						}else{
							$img[0].style.top = "65px";
						}
					}else if(smaller){
						if(direction=="horizontal") {
							$img[0].style.left = "66px";
						}else{
							$img[0].style.top = "150px";
						}
					}
				})
			})
			
			$("#videotapeSearch").click(function(){
				$("#videotapeDialog").createDialog({
					width: 950,
					height: 550,
					open:function(){
						$("#VideoOcx").hide();
					},
					//modal:false,
					title:"视频录像播放",
					url:"${path}/baseinfo/dustbinManage/dispatchOperate.action?mode=video&id=${param.id}",
					buttons: {
				   		"关闭" : function(){
							$("#VideoOcx").show();
				        	$(this).dialog("close");
				   		}
					}
				});
			})
		})
		
		
		function capture1(){
			var info={
				"diskplaninfo":{
					"diskpolicytype":"2",
					"disklist":["d"],
					"diskdeltype":"-1"
				},
				"otherinfo":{
					"packtime":"5",
					"capturepath":"D:\\RecordFile\\通道1\\",　
					"downloadpath":"D:\\RecordFile\\通道1\\"
				}
			}
			var infoObejct = eval(info);
			var infoText = JSON.stringify(infoObejct);
			var aaa=$("#VideoOcx")[0].SetRecordPlan(infoText);
			var bbb=$("#VideoOcx")[0].StartRecord();
		}
		
		function capture(){
			var path="D:\\RecordFile\\通道1\\";
			var aaa=$("#VideoOcx")[0].SetCapturePath(path);
			var bbb=$("#VideoOcx")[0].Capture(path);
		}
		
		function initVideoOcx(){
			var x=1;
			var y=1;
			var videoNumber=x*y;

// 			var dev_id="6401000010000005";
// 			var username='Admin';
// 			var password='1111';
// 			var client_sup_id='6401000010000002';
// 			var dev_sup_id='6401000010000002';
// 			var client_sup_ip='10.0.66.166';
// 			var client_sup_port='8000';
// 			var ch='0';
// 			var caption='4G2';
// 			var data_type='0';

			var info = {
				"client_sup_id":data.client_sup_ip,
				"dev_sup_id":data.dev_sup_id,
				"client_sup_ip":data.client_sup_ip,
				"client_sup_port":data.client_sup_port,
				"switchNum":16,
				"OcxType":0,
				"iUserLevel":0,
				"screen":{"x":x,"y":y,"z":5},
				"buttons":[ 1, 2, 3, 4],
				"menus":[4],
				"diskplaninfo":{'diskpolicytype':3,'disklist':'de'}, 
				"diskplaninfo":{'diskpolicytype':3,'disklist':'de','diskdeltype':-1},
				"otherinfo":{'packtime':30,'capturepath':'G:/capture'}
			};
			var infoObejct = eval(info);
			var infoText = JSON.stringify(infoObejct);
			var ret = $("#VideoOcx")[0].init(infoText);//视频插件初始化
			if(ret != 0){
				alert("GIS视频插件初始化失败！");
			} else {
				videoPreview(videoNumber);
			}
		}

		function videoPreview(videoNumber){

// 			var dev_id="演示球机";
// 			var username='admin';

// 			var dev_id="6401000010000005";
// 			var username='Admin';

// 			var password='1111';
// 			var client_sup_id='6401000010000002';
// 			var dev_sup_id='6401000010000002';
// 			var client_sup_ip='10.0.66.166';
// 			var client_sup_port='8000';
// 			var ch='0';
// 			var caption='4G2';
// 			var data_type='0';

			
			for (var i=0;i<videoNumber;i++ ){
				$("#VideoOcx")[0].SetActive(i);
				if(data.dev_id==null||data.dev_id==""){
					alert("设备ID不能为空！");
					return;
				}

				if(data.client_sup_id==null||data.client_sup_id==""){
					alert("客户端接入服务器ID不能为空！");
					return;
				}
				if(data.dev_sup_id==null||data.dev_sup_id==""){
					alert("设备端接入服务器ID不能为空！");
					return;
				}
				if(data.client_sup_ip==null||data.client_sup_ip==""){
					alert("客户端接入服务器IP不能为空！");
					return;
				}
				if(data.client_sup_port==null||data.client_sup_port==""){
					alert("客户端接入服务器端口不能为空！");
					return;
				}
// 				if(data.ch==null||data.ch==""){
// 					alert("通道号不能为空！");
// 					return;
// 				}
				var GisVedioInfo = {
					"caption":data.caption,
					"dev_id":data.dev_id,
					"username":data.username,
					"password":data.password,
					"client_sup_id":data.client_sup_id,
					"dev_sup_id":data.dev_sup_id,
					"client_sup_ip":data.client_sup_ip,
					"client_sup_port":data.client_sup_port,
					"ch":data.ch,
					"data_type":data.data_type//,
					//"screen":{"x":x,"y":y,"z":5}
				};

				var GisVedioInfoObject = eval(GisVedioInfo);
				var Jsontext = JSON.stringify(GisVedioInfoObject); 
				var ret = $("#VideoOcx")[0].StartVideo(Jsontext);
				if (ret != 0) {
					alert("ret != 0,视频连接失败！");
				}
			}
		}
		
		//PTZ控制
		function ptzControl(type,flag){
			var right ="0";//设置权限
			//拼成串作为参数调用接口---cmd:控制命令，param:速度，start:1启动，0：停止
			var info = "{\"cmd\" : \""+type+"\", \"param\" : \""+param+"\", \"start\" : \""+flag+"\", \"right\" : \""+right+"\"}";
			var ret = $("#VideoOcx")[0].PtzControl(info);
			if (ret != 0) {
				alert("PTZ控制失败！", ret);
			}
		}

		var param="0";//设置速度
		function setParam(paramValue){
			param =	$("#setParam").value;
		}

		//检测视频插件是否安装（判断安装的文件路径是否存在）
		function checkVideoOcxActiveIsExists(){
			var isExists=false;
			var fso=new ActiveXObject("Scripting.FileSystemObject");   
			if(fso.FileExists("C:\\VideoLib\\AnalyseConfig.exe"))   {     
		  		isExists=true;  
			}else{   
		  		isExists=false;
			} 
			return isExists;
		}　

		
	</script>
