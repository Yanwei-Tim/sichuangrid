<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %> 
    <jsp:include page="/includes/baseInclude.jsp" />
    <style>
    	#videotapeList{line-height:24px;height:440px;overflow:hidden;overflow-y:auto;}
    	#videotapeList li{padding:5px 0;border-bottom:1px solid #ccc;overflow:hidden;}
    	#videotapeList li .filename,#videotapeList li .starttime,#videotapeList li .endtime,#videotapeList li .size{}
    	#videotapeList li span{float:left;padding-right:5px;font-weight:bold;}
    </style>
    <script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/json2.js"></script>
	<div id="dialog-form" title="视频录像播放" class="container container_24" style="width:auto;height:auto;">
		<div class="grid_16">
			<OBJECT id="HardplayVideo" classid="clsid:5466B456-2BF0-42B4-8471-A68FFBE51610" width="570" height="440" hspace=0 vspace=0></OBJECT>
		</div>
		<div class="grid_8 heightAuto">
			<!--  <div class="grid_16 lable-right">
				<label>选择录像：</label>
				<input type="text" id="selectRecordNum" value="1">(<font color="red">*请输入录像序号</font>)<br>
			</div>
			<div class="grid_8 lable-left">
				<button onclick="startPlayback();">回放录像</button>
				<button onclick="stopPlayback();">停止回放</button>
				<button onclick="videotapeCapture();">抓拍</button><br>
				
			</div>-->
			
			<div id="videotapeDiv">
				<ul id="videotapeList"></ul>
			</div>
			<div style="clear:both"></div>
		</div>
		<input type="hidden" value='<s:property value="content"/>' id="content">
		
   	</div>
	<script>	
		var content=$("#content").val();
		var data = eval('(' + content + ')').content;  
		var resArray = new Array();
		$(function(){
			initActiveX();
		})
		
		function videotapeCapture(){
			var path="D:\\RecordFile\\通道1\\";
			var aaa=$("#HardplayVideo")[0].SetPicturePath(path);
			var bbb=$("#HardplayVideo")[0].Captrue(path);
		}
	
		//视频初始化
		function initActiveX(){
			var ret = $("#HardplayVideo")[0].init("");
			//$("#HardplayVideo")[0].SetScreenStyle(1,1);
			if(ret != 0){
				alert("HardplayVideo视频插件初始化失败！");
			} else {
				$("#HardplayVideo")[0].SetActive(0);
			}
			onClick_btnQuery();
		}

		//视频录像播放
		function startPlayback(i){
			var no = i ;
			//var no = $("#selectRecordNum").val() - 1;
			if (no < 0) {
				alert("请输入大于0的编号");
				return;
			}
			if (no + 1 > resArray.length) {
				alert("请输入不大于查询结果条数的数字");
				return;
			}
			if (resArray[no] === undefined || resArray[no] == null) {
				alert("失败！-1");
				return;
			}
			var info = {
				"dev_id": resArray[no].dev_id,
				"username":resArray[no].username,
				"password":resArray[no].password,
				"client_sup_id": resArray[no].client_sup_id,
				"dev_sup_id": resArray[no].dev_sup_id,
				"client_sup_ip":resArray[no].client_sup_ip,
				"client_sup_port":resArray[no].client_sup_port,
				"play_type":resArray[no].play_type,//　0 播放本地文件　1 播放NVR上文件
				"file_info":
				{
					"ch":resArray[no].ch,
					"filename":resArray[no].filename,
					"size":1*1,
					"starttime":resArray[no].starttime,
					"endtime":resArray[no].endtime,
					"driveno":0,
					"startcluster": 0,
					"filetype": 0,
					"pbtData":"",
					"devid": resArray[no].devid
				}
			}
			var infoObejct = eval(info);
			var infoText = JSON.stringify(infoObejct);
			var ret = $("#HardplayVideo")[0].StartPlayback(infoText);
			if (ret != 0) {
				alert("ret != 0,回放失败！");
			}
		}
		//停止播放
		function stopPlayback(){
			var ret = $("#HardplayVideo")[0].StopPlayback();
			if (ret != 0) {
				alert("停止回放失败！", ret);
			}
		}

		function startDownload(ob){
			var downloadInfo={
				"dev_id":data.dev_id,
				"username":data.username,
				"password":data.password,
				"caption":data.caption,
				"client_sup_id":data.client_sup_id,
				"dev_sup_id":data.dev_sup_id,
				"client_sup_ip":data.client_sup_ip,
				"client_sup_port":data.client_sup_port,
				"hostcaption":data.hostcaption,
				"play_type":"1",
				"cam_id":"",
				"file_info":{
					"ch":ob.ch,
					"filename":ob.filename,
					"size":ob.size,
					"starttime":ob.starttime,
					"endtime":ob.endtime,
					"driveno":ob.driveno,
					"startcluster": ob.startcluster,
					"filetype": ob.filetype,
					"pbtData":ob.pbtData,
					"devid": ob.devid
				}
			}

			var infoObejct = eval(downloadInfo);
			var infoText = JSON.stringify(infoObejct);
			var path="D:\\RecordFile\\通道1\\";
			var bbb=$("#VideoOcx")[0].SetDownloadPath(path);
			var aaa=$("#VideoOcx")[0].StartDownload(infoText);
		}

		function downLoad(){
			var no = $("#selectRecordNum").val() - 1;
			if (no < 0) {
				alert("请输入大于0的编号");
				return;
			}
			if (no + 1 > resArray.length) {
				alert("请输入不大于查询结果条数的数字");
				return;
			}
			if (resArray[no] === undefined || resArray[no] == null) {
				alert("失败！-2");
				return;
			}
			var info = {
				"dev_id": resArray[no].dev_id,
				"username":resArray[no].username,
				"password":resArray[no].password,
				"client_sup_id": resArray[no].client_sup_id,
				"dev_sup_id": resArray[no].dev_sup_id,
				"client_sup_ip":resArray[no].client_sup_ip,
				"client_sup_port":resArray[no].client_sup_port,
				"play_type":resArray[no].play_type,//　0 播放本地文件　1 播放NVR上文件
				"hostcaption":"ccc",
				"caption":"bbb",
				"cam_id":resArray[no].cam_id,
				"file_info":
				{
					"ch":resArray[no].ch,
					"filename":resArray[no].filename,
					"size":resArray[no].size,
					"starttime":resArray[no].starttime,
					"endtime":resArray[no].endtime,
					"driveno":0,
					"startcluster": 0,
					"filetype": resArray[no].filetype,
					"pbtData":"",
					"devid": resArray[no].devid
				}
			}
			
			var infoObejct = eval(info);
			var infoText = JSON.stringify(infoObejct);
			var path="D:\\RecordFile\\通道1\\";
			$("#VideoOcx")[0].SetDownloadPath(path);
			var ret = $("#VideoOcx")[0].StartDownload(infoText);
			if (ret != 0) {
				alert("ret != 0,下载失败！");
			}				
		}
		
		
		function onClick_btnQuery() {
			var d = new Date();
			var startTime = d.getFullYear() + "-" +(d.getMonth()+1) + "-" + d.getDate() + " 00:00:00";
			//var startTime = "2013-09-16 00:00:00";
			var endTime = d.getFullYear() + "-" +(d.getMonth()+1) + "-" + d.getDate() + " 23:59:59";
			if (startTime >= endTime) {
				alert("开始时间不能大于结束时间");
				return;
			}
			
			
			var storeType = 1 ;
			var host_id = "6401000010000010";
			var info = {
				"type":1,
				"dev_id":data.client_sup_id,
				"username":data.username,
				"password":data.password,
				"ch":data.ch,
				"client_sup_id":data.client_sup_id,
				"client_sup_ip":data.client_sup_ip,	
				"client_sup_port":data.client_sup_port,
				"filetype": "0",
				"start_time": startTime,
				"stop_time": endTime,
				"hostcaption":data.hostcaption,
				"host_id":data.dev_id,
				"dev_sup_id":data.dev_sup_id
			};

			info.canPlayback = true;
			info.canDownLoad = true;
			var count = 0;	//added by zhengqiang
			var infoObejct = eval(info);
			var infoText = JSON.stringify(infoObejct);
			var afrmHardplayOcx = $("#HardplayVideo")[0];
			var ret = afrmHardplayOcx.FindFile(infoText);
			if (ret == -1) {
				alert("失败！-3");
				return;
			}
			var result;
			try {
				do {
					try {
						var file = afrmHardplayOcx.FindNext(ret);
						result = JSON.parse(file);
					} catch (e) {
						alert("失败！-4");
						return;
					}

					if (result == null) {
						alert("失败！-5");
						return;
					}

					if (result.ret === undefined) {
						alert("失败！-6");
						return;
					}

					if (result.ret == 0) {
						if (result.filelist !== undefined && result.filelist != null) {
							var filelist = result.filelist;
							//aaa=filelist;
							for (var i = 0; i < filelist.length; ++i) {
								//var filename=filelist[i].filename;
								var json2str=json2strConvert();
								var ob=json2str._(filelist[i]);
								var size=filelist[i].size/1048576;
								size=Math.round(size*100)/100+"m";
								$("#videotapeList").append("<li><div class='filename'><span>文件名称:</span>"+filelist[i].filename+"</div><div class='starttime'><span>起始日期:</span>"+filelist[i].starttime+"</div><div class='endtime'><span>结束日期:</span>"+filelist[i].endtime+'</div><div class="size"><span>文件大小:</span>'+size+"</div><div class='btnlist'><button onclick='startPlayback("+i+");'>播放</button><button onclick='stopPlayback();'>停止</button><button onclick='startDownload("+ob+");'>下载</button><button onclick='videotapeCapture();'>抓拍</button></div></li>")
								filelist[i].caption = info.caption;
								filelist[i].dev_id = info.dev_id;
								filelist[i].username = info.username;
								filelist[i].password = info.password;
								filelist[i].client_sup_id = info.client_sup_id;
								filelist[i].dev_sup_id = info.dev_sup_id;
								filelist[i].client_sup_ip = info.client_sup_ip;
								filelist[i].client_sup_port = info.client_sup_port;
								filelist[i].play_type = info.type;
								filelist[i].ch = info.ch;
								filelist[i].hostcaption = info.hostcaption;
								filelist[i].canPlayback = info.canPlayback;
								filelist[i].canDownLoad = info.canDownLoad;
								//filelist[i].authority = info.authority; 
								//filelist[i].cam_id = info.cam_id;
								//this.appandFileInfo(grid, filelist[i]);
								resArray[count] = filelist[i];
								count++;
							}
						}
						break;
					} else if (result.ret == 1) {
						if (result.filelist === undefined) {
							alert("失败！-7");
							return;
						}
						if (result.filelist.length <= 0) {
							alert("失败！-8");
							return;
						}
						var filelist = result.filelist;
						for (var i = 0; i < filelist.length; ++i) {
							filelist[i].caption = info.caption;
							filelist[i].dev_id = info.dev_id;
							filelist[i].username = info.username;
							filelist[i].password = info.password;
							filelist[i].client_sup_id = info.client_sup_id;
							filelist[i].dev_sup_id = info.dev_sup_id;
							filelist[i].client_sup_ip = info.client_sup_ip;
							filelist[i].client_sup_port = info.client_sup_port;
							filelist[i].play_type = info.type;
							filelist[i].ch = info.ch;
							filelist[i].hostcaption = info.hostcaption;
							filelist[i].canPlayback = info.canPlayback;
							filelist[i].canDownLoad = info.canDownLoad;                     
							//filelist[i].authority = info.authority;
							//filelist[i].cam_id = info.cam_id;
							//this.appandFileInfo(grid, filelist[i]);
							resArray[count] = filelist[i];
							count++;
						}
						continue;
					}
					else if (result.ret == 2) {
						continue;
					} else {
						alert("失败！-9");
						return;
					}
				} while (true);
			} finally {
				afrmHardplayOcx.FindClose(ret);
			}
			
			//var tip = "查询结果共"+count+"条";alert(tip);
			//document.getElementById("tipLabel").innerHTML = tip;
		}


		function json2strConvert(){
			var json2str={
				isObject:function(b){return "object" == typeof b},
				isElement:function(b){return b && 1 == b.nodeType},
				isUndefined:function(b){return "undefined" == typeof b},
				isFunction:function(b){return "Function" == this.getType(b)},
				isNumber:function(b){return "Number" == this.getType(b)},
				isString:function(b){return "String" == this.getType(b)},
				isArray:function(b){return "Array" == this.getType(b)},
				getType:function(b){return Object.prototype.toString.call(b).slice(8, -1)},
				_:function(b){
					var z=json2str;
					var c=[],
						f =z.isArray(b);
					if (z.isObject(b)){
						if (null === b) return "null";
						if (window.JSON && window.JSON.stringify) return JSON.stringify(b);
						for (var h in b) c.push((f ? "" :'"' + h + '":') + this.json2str(b[h]));
						c=c.join();
						return f ? "[" + c + "]" :"{" + c + "}"
					}
					return z.isNumber(b) ||z.isFunction(b) ? b.toString() :
						z.isUndefined(b) ? "undefined" :!b ? '""' :'"' + b + '"'
				}
			}
			return json2str;
		}

		//PTZ控制
		function ptzControl(type,flag){
			var right ="0";//设置权限
			//拼成串作为参数调用接口---cmd:控制命令，param:速度，start:1启动，0：停止
			var info = "{\"cmd\" : \""+type+"\", \"param\" : \""+param+"\", \"start\" : \""+flag+"\", \"right\" : \""+right+"\"}";
			var ret = $("#HardplayVideo")[0].PtzControl(info);
			if (ret != 0) {
				alert("PTZ控制失败！", ret);
			}
		}

		//视频预览
		function startVideo(){
			//获取接口需设置参数
			var info = getGisVedioParam();
			var ret = $("#HardplayVideo")[0].StartVideo(info);
            if (ret != 0) {
                alert("ret != 0,视频连接失败！");
            }
		}

		//停止预览
		function stopVideo(){
			var ret = $("#HardplayVideo")[0].StopVideo();
	        if (ret != 0) {
	            alert("停止预览失败！", ret);
	        }
		}

		var param="0";//设置速度
		function setParam(paramValue){
			param =	$("#setParam").value;
		}

	 	//视屏预览校验及视频初始化
	 	function getGisVedioParam(){
	 		
			var dev_id=data.dev_id;
			var username=data.username;
			var password=data.password;
			var client_sup_id=data.client_sup_id;
			var dev_sup_id=data.dev_sup_id;
			var client_sup_ip=data.client_sup_ip;
			var client_sup_port=data.client_sup_port;
			var ch=data.ch;
			var caption=data.caption;
			var data_type=data.data_type;

			if(dev_id==null||dev_id==""){
				alert("设备ID不能为空！");
				return;
			}

			if(client_sup_id==null||client_sup_id==""){
				alert("客户端接入服务器ID不能为空！");
				return;
			}
			if(dev_sup_id==null||dev_sup_id==""){
				alert("设备端接入服务器ID不能为空！");
				return;
			}
			if(client_sup_ip==null||client_sup_ip==""){
				alert("客户端接入服务器IP不能为空！");
				return;
			}
			if(client_sup_port==null||client_sup_port==""){
				alert("客户端接入服务器端口不能为空！");
				return;
			}
			var GisVedioInfo = {
				"caption":caption,
				"dev_id":dev_id,
				"username":username,
				"password":password,
				"client_sup_id":client_sup_id,
				"dev_sup_id":dev_sup_id,
				"client_sup_ip":client_sup_ip,
				"client_sup_port":client_sup_port,
				"ch":ch,
				"data_type":data_type
			};
			var GisVedioInfoObject = eval(GisVedioInfo);
			var Jsontext = JSON.stringify(GisVedioInfoObject); 
			return Jsontext;
		}
	</script>
