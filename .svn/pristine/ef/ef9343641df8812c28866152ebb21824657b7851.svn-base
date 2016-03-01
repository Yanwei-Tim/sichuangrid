<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网视星平台IE</title>
<style type="text/css">
<!--
body {
	background-image: url();
	background-repeat: repeat;
	font-size:12px;
}
.input_style{
	width:160px;
	height:16px;
}
.select_style{
	width:80px;
	height:16px;
}
.STYLE9 {font-size: 12px}
.STYLE10 {font-size: 14px}
.front-frame {
    font-size:12px;
    font-family:"宋体";
}
-->

.myTd{
	display: none;
}
</style>
<SCRIPT language="javascript" type="text/javascript">
		if(document.all.IP_CAM.object == null) { 
			$("#videoSystemDialog").dialog("close");
			$("#vedioShowDialog").dialog("close");
			console.log(PATH)
			$("#videoDown").createDialog({
				width: 400,
				height: 270,
				title:'插件下载',
				url:PATH+'/digitalCity/publicSecurity/videoSystemManagement/videoDownload.jsp',
				postData:{
					path:PATH
				},
				buttons: {
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				}
			});
		}



		var i = 0;
		//var iViewMode = -1; //0实时视频，1录像回放
		var isLogin = 0;//是否登录，0为否
		var isRec = 0;//是否打开了录像回放
		var isVideo = 0;//是否打开了视频回放
		var iSpeed = 5; //云台控球速度
		//登陆
		function Login()
		{
			var strTemp,digits;
			digits="0123456789"; 
			
			strTemp = document.getElementById("UserName").value;
			if(strTemp == ""){
				alert("请输入正确的用户名！");
				return;
			}
		
			strTemp = document.getElementById("LoginPort").value;
			if(strTemp == ""){
				alert("请输入正确的端口号码！");
				return;
			}
		
			if(strTemp.length > 5 || strTemp.length == 0){	
				alert("请输入正确的端口号码！");
				return; 
			}
				
			for (i=0;i<strTemp.length;i++){  //not number
				j=digits.indexOf(strTemp.charAt(i)); 
				if (j==-1) { 
					alert("请输入正确的端口号码");
					return; 
				} 
			}
			
			var ServerIP = document.getElementById("IP").value;
			var UserName = document.getElementById("UserName").value;
			var PassWord = document.getElementById("PassWord").value;
			var ServerPort = document.getElementById("LoginPort").value;
			IP_CAM.ResetTreePos(0,0);//树大小
			var iret=IP_CAM.FastLogin(ServerIP,ServerPort,UserName,PassWord,1);	
		}
		
		//注销
		function Logout()
		{
			if(isLogin == 0)
			{
				return;
			}
			
			var iret = IP_CAM.Logout();
			if(iret == -1)
			{
				return;
			}
			
			isLogin = 0;
			isRec = 0;
			isVideo = 0;
			VideoAreaView();
		
			document.getElementById("PassWord").value = "";
		}
		
		//实时视频
		function startPlayVideo()//调用控件登录
		{
			if(isLogin == 1)
			{
				var GUID =  document.getElementById("GUIDName").value;
				VideoAreaView();//切换实时视频画面
				IP_CAM.PlayRealVideo(GUID);//播放GUID
				return;
			}
		}
		
		//关闭视频
		function stopPlayVideo()
		{
			if(isLogin == 1)
			{
				IP_CAM.StopRealVideo(); //关闭实时视频
				return;
			}
		}
		
		function DoMenuRight()
		{
			if(document.getElementById("MenuRight").checked==true)
			{
				IP_CAM.SetPlayWndPopMenuEnable(1);
			}
			else
			{
				IP_CAM.SetPlayWndPopMenuEnable(0);
			}
		}
		
		function ShowVideoTitle()
		{
			var TitleSel =  document.getElementById("ShowTitle");
			if(TitleSel.checked==true)
			{
				IP_CAM.ShowVideoTitle(1);
			}
			else
			{
				IP_CAM.ShowVideoTitle(0);
			}
		}
		
		function onChangePosSelect()
		{
			var iPosSelectValue =  document.getElementById("PosSelect").value;
			if (iPosSelectValue == 2)//网络
			{
				document.getElementById("VodSelect").style.display = "";
				var GUID =  document.getElementById("GUIDName").value;
				var iRet = IP_CAM.GetVODServerInfo(GUID);
				if (iRet == -1)
				{
					alert("查询VOD服务器失败！");	
				}
			}
			else 
			{
				document.getElementById("VodSelect").style.display = "none";
			}
		
		}
		
		function QueryRecord()
		{
			var GUID =  document.getElementById("GUIDName").value;
			
			var recordType =  document.getElementById("PosSelect").value;
			var formatType =  document.getElementById("FormatSelect").value;
			var TimeSel =  document.getElementById("TimeSel");
			var HandSel =  document.getElementById("HandSel");
			var AlarmSel =  document.getElementById("AlarmSel");
			
			var recordDate = document.getElementById("TextQueryDate").value;
			var stratTime = document.getElementById("TextTimeStart").value;
			var endTime = document.getElementById("TextTimeEnd").value;
			
			var iVodPos = 0;
			if (recordType == 2) //网络需要知道VOD服务器号
			{
				iVodPos = document.all.VodSelect.value;
				if (iVodPos == "")
				{
					alert("没有任何VOD点播服务器！");
					return;
				}
			}
		
			var timeRecordFlag = 1;
			var handRecordFlag = 1;
			var AlarmRecordFlag = 1;	
			if(TimeSel.checked==true)
			{
				timeRecordFlag = 1;
			}
			else
			{
				timeRecordFlag = 0;
			}
			
			if(HandSel.checked==true)
			{
				handRecordFlag = 1;
			}
			else
			{
				handRecordFlag = 0;
			}
			
			if(AlarmSel.checked==true)
			{
				AlarmRecordFlag = 1;
			}
			else
			{
				AlarmRecordFlag = 0;
			}
				
			//	alert("GUID="+GUID+" formatType="+formatType+" recordType="+recordType+" timeRecordFlag="+timeRecordFlag+" handRecordFlag="+handRecordFlag+" AlarmRecordFlag="+AlarmRecordFlag+" recordDate="+recordDate+" stratTime="+stratTime+" endTime="+endTime+" VodPos="+iVodPos)
			var iRet = IP_CAM.QueryPuRecord(GUID, formatType, recordType, timeRecordFlag, handRecordFlag, AlarmRecordFlag, recordDate, stratTime, endTime, iVodPos); 
			if (iRet == -1)
			{
				alert("查询录像失败！");
			}
			else if (iRet == -2)
			{
				alert("输入不合法！");
			}
			else if (iRet == -3)
			{
				alert("不支持录像回放查询！");
			}
			else
			{
				alert("总共查询到文件有："+iRet+"条记录！");
			}
		
		}
		
		//下载录像回放
		function DownloadRecord()
		{
			var RecordNo =  document.getElementById("ResultSelect").value;
			var iRet = IP_CAM.DownloadRecFile(RecordNo);
			if (iRet == -1)
			{
				alert("下载录像失败！");
			}
		}
		
		//开始录像回放
		function startPlayRec()
		{
			//已经登陆
			if(isLogin == 1)
			{
				var iRecordNo = document.all.ResultSelect.value;
				if (iRecordNo == "")
				{
					alert("没有录像文件可回放！");
					return;
				}
				
				var iRet = IP_CAM.PlayRecordFilePos(iRecordNo);
				if (iRet < 0)
				{
					alert("录像回放失败！");
					return;
				} 
			}
		}
		
		//下载录像回放
		function downloadPlayRec()
		{
			if(isLogin == 1)
			{
				//暂未实现
				return;
			}
		}
		
		
		//录像区域
		function RecordAreaView()
		{
			document.getElementById("PTZTable").style.display = "none";
			document.getElementById("RecordTable").style.display = "";
			IP_CAM.style.height = 400;
			IP_CAM.style.width  = 600;
			IP_CAM.ResetWindowsPos(600,400);
			IP_CAM.RecordAreaView();
		}
		
		//视频区域
		function VideoAreaView()
		{
			document.getElementById("PTZTable").style.display = "";
			document.getElementById("RecordTable").style.display = "none";
			IP_CAM.style.height = 465;
			IP_CAM.style.width  = 680;
			IP_CAM.ResetWindowsPos(680,465);
			IP_CAM.VideoAreaView();
			IP_CAM.VideoOne();//单画面
			IP_CAM.ShowVideoTitle(0);//隐藏标题
		
		}
		
		function onChangeSpeed()
		{
			var iCurSpeed = document.getElementById("ptzSpeed").value;
			iSpeed = parseInt(iCurSpeed) + 1;
			if (iSpeed == 10)
			{
				iSpeed = 1;
			}
			
			document.getElementById("ptzSpeed").value = iSpeed;
		}
		
		function PtzControlFunc(strCmd, bFlag)
		{
			var GUID, bStart, cmd;
			GUID = document.getElementById('GUIDName').value;
			if(GUID == "")
			{
				return;
			}
		
			var varRet = IP_CAM.PtzControl(GUID, bFlag, strCmd, iSpeed*11);
		}
		
		function PTZScanFunc(strType)
		{
			var GUID, iType;
			GUID = document.getElementById('GUIDName').value;
			if(GUID == "")
			{
				return;
			}
			
			var varRet = IP_CAM.PTZScan(GUID, strType, iSpeed*11);
		}
		
		function GetGUList()
		{
			document.getElementById("TextAllGU").value = "";
			IP_CAM.GetAllVideoInfo();
		}
	/*	
		function document.onkeydown()  //屏蔽 F5 刷新键
		{ 
		  if (event.keyCode==116 )
			{
				 event.keyCode=0; 
				 event.returnValue=false; 
			} 
		}*/
		
		function fullscreen()
		{
			document.all("VIEW_TABLE").style.display = "none";//
			window.moveTo(0,0);
			window.resizeTo(screen.width,screen.height-30);
			VideoAreaView();
			IP_CAM.ResetTreePos(0,0);//树大小
		}
		
		function VodServerInfoFunc(VodPos, VodServerName)
		{
		//查询得到相关的VOD服务器事件
			if (VodPos == 1)
			{
				document.all.VodSelect.length = 0;
			}
			var varItem = new Option(VodServerName, VodPos);
			document.all.VodSelect.options.add(varItem);
		}
		
		function PuRecordInfoFunc(fileNo, fileType, filePos, filePath, fileSize, fileName)
		{
		//查询得到相关的前端或网络事件
			if (fileNo == 1)
			{
				document.all.ResultSelect.length = 0;
			}
			
			var RecordName = "No:"+fileNo + " " + fileName + " " + fileSize;
			var varItem = new Option(RecordName, fileNo);
			document.all.ResultSelect.options.add(varItem);
		}
		Login();
		
		setTimeout(function(){
			startPlayVideo();
		},200);
		</SCRIPT>
		
		<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" event="GetLoginInfo(flag)">
		//登陆事件
		if(flag == 1)
		{
			isLogin = 1;
			document.all("LOGIN_TABLE").style.display="none"
			document.all("VIEW_TABLE").style.display = "";//
			//alert("登陆成功");
		}
		else
		{
			isLogin = 0;
			//alert("登陆失败");
		}
</SCRIPT>


<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" event = "VodServerInfo(VodPos, VodServerName)">
    VodServerInfoFunc(VodPos, VodServerName);
</SCRIPT>

<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" 
event = "PuRecordInfo(fileNo, fileType, filePos, filePath, fileSize, fileName)">
	PuRecordInfoFunc(fileNo, fileType, filePos, filePath, fileSize, fileName);
</SCRIPT>

<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" event = "VideoGuInfo(GUName, GUID, bState)">
	var szOldVaule = document.getElementById("TextAllGU").value;
	var szState;
	if (bState == 0)
	{
		szState = "离线";
	}
	else
	{
		szState = "在线";
	}
	document.getElementById("TextAllGU").value = szOldVaule + " GU 名称:" + GUName + "  GUID:" +  GUID + "   当前状态:"+ szState + "\n";
</SCRIPT>
</head>


<META content="MSHTML 6.00.2900.3562" name=GENERATOR></HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" 
ondragstart="return false" onload=fullscreen()>
<DIV id=login_W align="center"></DIV>
<table width=324 height=204 border=0 align="center" cellpadding=0 cellspacing=0 
style="BORDER-RIGHT: #ffffff 3px solid; BORDER-TOP: #ffffff 3px solid; BORDER-LEFT: #ffffff 3px solid; BORDER-BOTTOM: #ffffff 3px solid;display: none;" id="LOGIN_TABLE">
  <tbody>
    <tr>
      <td 
    style="PADDING-RIGHT: 5px; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; PADDING-TOP: 5px;" 
    bgcolor=#819fc5><table 
      style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; BORDER-BOTTOM: #90b9d7 1px solid" 
      height=182 cellspacing=0 cellpadding=0 width="100%" border=0>
        <tbody>
          <tr>
            <td height="180" align=middle><table width="306" border=0 
            cellpadding=0 cellspacing=0 style="FONT-SIZE: 14px; COLOR: #000000">
              <tbody>
                <tr>
                  <td width="295" height=30 align="center">平台地址:                    
                    <input name="IP" id="IP" 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 120px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px"
                  value="<s:property value='#parameters.ip'/>" /></td>
                </tr>
                <tr>
                  <td height=30 align="center">用户名:
                    <input name=UserName id=UserName 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 120px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px"
                  value=<s:property value='#parameters.account'/> /></td>
                </tr>
                <tr>
                  <td height=30 align="center">密 码 :
                    <input name=PassWord 
                  type=password id=PassWord 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 120px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" value="<s:property value='#parameters.password'/>" maxlength="64" 
                  onpaste="return false" /></td>
                </tr>
                <tr>
                  <td height=30 align="center">端口号:
                    <input id=LoginPort 
                  onkeyup="value=value.replace(/[^\d]/g,'') " 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 120px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" 
                  value=<s:property value='#parameters.portNum'/> 
      name=LoginPort /></td>
                </tr>
                <tr>
                  <td> 　　 </td>
                </tr>
                <tr align="center">
                  <td><input name="loginBtn" type="button" id="loginBtn" onclick="Login();"  value="登 陆" 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 80px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" />
                    　
                    
                    <input name="logoutBtn" type="button" id="logoutBtn" onclick="Logout();"  value="注 销" 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 80px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" /></td>
                </tr>
                <tr>
                  <td height="14">&nbsp;</td>
                </tr>
              </tbody>
            </table></td>
          </tr>
        </tbody>
      </table></td>
    </tr>
    <tr>
      <td  height=12><table width="100%" border=0 cellpadding=0 cellspacing=0>
        <tbody>
          <tr>
            <td align=middle width="39%"></td>
            <td width="36%"></td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>
<table width="690" height="361" border="1" align="center" id="VIEW_TABLE">
  <tr class="myTd">
    <td width="400">GUID  <span class="STYLE10">
      <input name="GUIDName" type="text" id="GUIDName"  style="width:230px" height="22px" onkeyup="value=value.replace(/[^\d\.]/g,'')" value="<s:property value='#parameters.guid'/>"/>
    菜单
    <input type="checkbox" name="MenuRight" checked="checked" value="checkbox" id="MenuRight" onclick="DoMenuRight()"/>
     标签
     <input type="checkbox" name="ShowTitle" value="checkbox" id="ShowTitle" onclick="ShowVideoTitle()"/>
     <input type="submit" style="height:25px; width:120px" name="Submit722323" value="获取所有视频点信息" onclick="GetGUList();"/>
    </span></td>
    <td width="131" height="27" align="center"><input type="submit" style="height:25px; width:100px" name="Submit10" value="视频浏览界面" onclick="VideoAreaView();"/></td>
    <td width="137" align="center"><input type="submit" style="height:25px; width:100px" name="Submit102" value="录像回放界面" onclick="RecordAreaView();"/></td>
  </tr>
  <tr>
    <td width="400" rowspan="4"><p>
      <textarea name="TextAllGU" id="TextAllGU" style=" height:100px; width:600px;display: none" ></textarea>
    </p>
      <p>
        <object classid="CLSID:A3151DD9-AE91-4D4E-B872-2212A43F6AA4" width="680" height="465" id="IP_CAM">
          <param name="wmode" value="transparent" />
              </object>
        </p>
      <p>&nbsp;</p></td>
    <td colspan="2" align="right">&nbsp;</td>
  </tr>
  <tr class="myTd">
    <td height="101" colspan="2" align="center" id="PTZTable"><table width="270" height="450" border="1">
      <tr>
        <td height="23" colspan="5" align="center"><input name="PlayVideo" type="button" id="PlayVideoBtn" onclick="startPlayVideo();"  value="打开视频" 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 60px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" />
          　　
            <input name="PlayVideo2" type="button" id="StopVideoBtn" onclick="stopPlayVideo();"  value="关闭视频" 
                  style="BORDER-RIGHT: #90b9d7 1px solid; BORDER-TOP: #90b9d7 1px solid; BORDER-LEFT: #90b9d7 1px solid; WIDTH: 60px; BORDER-BOTTOM: #90b9d7 1px solid; HEIGHT: 19px" /></td>
      </tr>
      <tr class="myTd">
        <td height="23" colspan="5" align="center">云台控球</td>
        </tr>
      <tr class="myTd">
        <td width="67" height="30"><label></label></td>
        <td width="35"><input type="submit" style="height:35px; width:35px" name="Submit" value="左上" onmousedown="PtzControlFunc('TUPL',1);" onmouseup="PtzControlFunc('TUPL', 0);"/></td>
        <td width="35" align=""><input type="submit" style="height:35px; width:35px" name="Submit2" value="向上" onMouseDown="PtzControlFunc('TU',1);" onMouseUp="PtzControlFunc('TU', 0);"/></td>
        <td width="35" align=""><input type="submit" style="height:35px; width:35px" name="Submit3" value="右上" onmousedown="PtzControlFunc('TUPR',1);" onmouseup="PtzControlFunc('TUPR', 0);"/></td>
        <td width="64">&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="37">&nbsp;</td>
        <td height="37"><input type="submit" style="height:35px; width:35px" name="Submit4" value="向左" onmousedown="PtzControlFunc('PL',1);" onmouseup="PtzControlFunc('PL', 0);"/></td>
        <td><input type="submit" style="height:35px; width:35px" name="ptzSpeed" id="ptzSpeed" value="5" onclick="onChangeSpeed();"/></td>
        <td><input type="submit" style="height:35px; width:35px" name="Submit6" value="向右" onmousedown="PtzControlFunc('PR',1);" onmouseup="PtzControlFunc('PR', 0);"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7" value="左下" onmousedown="PtzControlFunc('TDPL',1);" onmouseup="PtzControlFunc('TDPL', 0);"/></td>
        <td><input type="submit" style="height:35px; width:35px" name="Submit8" value="向下" onmousedown="PtzControlFunc('TD',1);" onmouseup="PtzControlFunc('TD', 0);"/></td>
        <td><input type="submit" style="height:35px; width:35px" name="Submit9" value="右下" onmousedown="PtzControlFunc('TDPR',1);" onmouseup="PtzControlFunc('TDPR', 0);"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="16" colspan="5">&nbsp;</td>
        </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7223" value="－" onmousedown="PtzControlFunc('IO',1);" onmouseup="PtzControlFunc('IO', 0);"/></td>
        <td align="center">光圈</td>
        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit724" value="＋"  onmousedown="PtzControlFunc('IC',1);" onmouseup="PtzControlFunc('IC', 0);"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7222" value="－"  onmousedown="PtzControlFunc('ZIN',1);" onmouseup="PtzControlFunc('ZIN', 0);"/></td>
        <td align="center">缩放</td>
        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit723" value="＋"  onmousedown="PtzControlFunc('ZOUT',1);" onmouseup="PtzControlFunc('ZOUT', 0);"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit722" value="－"  onmousedown="PtzControlFunc('FR',1);" onmouseup="PtzControlFunc('FR', 0);"/></td>
        <td align="center">聚集</td>
        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit72" value="＋"  onmousedown="PtzControlFunc('FN',1);" onmouseup="PtzControlFunc('FN', 0);"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="16" colspan="5">&nbsp;</td>
        </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit72232" value="开始" onclick="PTZScanFunc(1);"/></td>
        <td align="center"><p>自动线扫</p>          </td>
        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit72233" value="停止" onclick="PTZScanFunc(-1)"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr class="myTd">
        <td height="39">&nbsp;</td>
        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit722322" value="开始" onclick="PTZScanFunc(2);"/></td>
        <td align="center">随机线扫</td>
        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit722332" value="停止" onclick="PTZScanFunc(-2)"/></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  <tr style="display: none">
    <td colspan="2" id="RecordTable"><table width="270" height="450" border="1">
      <tr>
        <td width="71">位置：</td>
        <td colspan="3"><select name="PosSelect" class="select_style" id="PosSelect" onchange="onChangePosSelect();">
          <option value="1">前端</option>
          <option value="2">网络</option>
        </select>　
           <select name="VodSelect" class="select_style" id="VodSelect" style="display:none">
          </select></td>
        </tr>
      <tr>
        <td>文件：</td>
        <td colspan="3"><select name="FormatSelect" class="select_style" id="FormatSelect">
          <option value="0">录像</option>
          <option value="1">抓拍</option>
        </select></td>
        </tr>
      <tr>
        <td><span class="STYLE9">类型：</span></td>
        <td width="52"><span class="STYLE9">
          <input type="checkbox" name="TimeSel" value="checkbox" id="TimeSel" checked="checked"/>
          定时</span></td>
        <td width="56"><span class="STYLE9">
          <input type="checkbox" name="HandSel" value="checkbox" id="HandSel" checked="checked"/>
          手动</span></td>
        <td width="63"><span class="STYLE9">
          <input type="checkbox" name="AlarmSel" value="checkbox" id="AlarmSel" checked="checked"/>
报警</span></td>
      </tr>
      <tr>
        <td>日期：</td>
        <td colspan="3"><input name="TextQueryDate" type="text" id="TextQueryDate" style="width:80px;height:21px" value="2012-07-04" />
        格式:2011-03-11</td>
        </tr>
      <tr>
        <td>开始时间：</td>
        <td colspan="3"><input name="TextTimeStart" type="text" id="TextTimeStart" style="width:80px;height:21px" value="00:00:00" />
格式:00:00:00</td>
        </tr>
      <tr>
        <td>结束时间：</td>
        <td colspan="3"><input  name="TextTimeEnd" type="text" id="TextTimeEnd" style="width:80px;height:21px" value="23:59:59" />
        格式:23:59:59</td>
        </tr>
      <tr>
        <td colspan="4">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="4" align="center"><input type="submit" style="height:25px; width:100px" name="Submit103" value="查询录像/抓拍" onclick="QueryRecord();"/>
          <input type="submit" style="height:25px; width:100px" name="Submit1033" value="下载录像/抓拍" onclick="DownloadRecord();"/></td>
        </tr>
      <tr>
        <td colspan="4">查询录像结果</td>
        </tr>
      <tr>
        <td colspan="4"><select name="ResultSelect" class="select_style" id="ResultSelect" style="width:250px">
        </select></td>
        </tr>
      <tr>
        <td colspan="4">&nbsp;</td>
        </tr>
      <tr align="center">
        <td colspan="4"><input type="submit" style="height:25px; width:80px" name="Submit1032" value="录像回放" onclick="startPlayRec();"/>　
          (抓拍回放暂未实现)
          　          </td>
        </tr>
    </table></td>
  </tr>
</table>
<div id="videoDown"></div>

</body>
</html>

