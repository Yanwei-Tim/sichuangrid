<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>iDVR 版本信息： 3.1 (Build 3.1.60)</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script type="text/javascript"
	src="${path }/videointegration/js/videointegration.js"></script>
<script language="JavaScript">	
	//Monitor.cab 版本比较函数。
	function ComparMonitor()
	{
		monitorDiv.innerHTML = "<object id='monitorObject' style='display:none' classid='clsid:574B47E8-A366-4AB9-B2EA-57F145CA3780' codebase='lib/monitor.cab#version=3,0,0,1' VIEWASTEXT></object>";
		login();
		SetTrustSite()
		GetInput()
		}
	
	function SetTrustSite()
	{
		
		var SiteName=window.location.host;
		try{
			var WshShell=new ActiveXObject("WScript.Shell");
			WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range99\\","");
			WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range99\\http","2","REG_DWORD");
			WshShell.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range99\\:Range",SiteName);
			//TrustedSite_Value=WshShell.RegRead("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\ZoneMap\\Ranges\\Range100\\:Range");
			//alert("你已经将本站点设置为受信任站点.需关闭本页面从新登陆后,设置生效");
			}catch(e){/*
				if(e.number="-2146827859"){
					alert("无法执行此操作,请设置IE安全级:\nIE工具栏中 工具→Internet选项→安全→自定义级别:\n对没有标记为安全的 ActiveX 控件进行初始化和脚本运行项 设置为:提示");	
				}else{alert(e.description);}*/
		}finally{
			delete WshShell;
		}
	}	
</script>



<script language="JavaScript">

	<!--
	for(i=0;i<10;i++)
	{
		for(z=0;z<10;z++)
		{
		if(navigator.appVersion.match("MSIE "+i+"."+z)!=null)
		x=i+"."+z;
		}
	}
		if (x.substr(0, 1)<6)
		{
		alert ("您的Microsoft Internet Explorer版本小于6.0 不能正常使用本系统 请您安装IE6.0 及以上版本" )
		}
	//self.moveTo(0,0)
	//self.resizeTo(screen.availWidth,screen.availHeight)
	-->
	
</script>
<SCRIPT LANGUAGE="Javascript" SRC="http://60.190.81.34/lwga/Cookie.js"></SCRIPT>
<script language="JavaScript">
<!--

 function checklogin(){
	 //login();
     var UserName=window.UserName.value
    var UserPWD=window.UserPWD.value
   
    if(UserName==""){
      window.alert("用户名不能为空!");
      window.UserName.focus();
      return false;
    }
    if(UserPWD==""){
      window.alert("密码不能为空!");
      window.UserPWD.focus();
      return false;
    }
	if (window.IfSave.checked){
		saveCookie("InfoUserName", window.UserName.value, 365);
				  if (PSWD==(window.UserPWD.value)){
		saveCookie("InfoUserPWD", (window.UserPWD.value), 365);
	}
	else{saveCookie("InfoUserPWD", md5((window.UserPWD.value)), 365);}
		if (window.NetType(0).checked){
			saveCookie("NetType", 0, 365);
		}else{
			saveCookie("NetType", 1, 365);
		}
		   saveCookie("InfoIfSave", 1, 365);
	}else {
		deleteCookie("InfoUserName")
		deleteCookie("InfoUserPWD")
		deleteCookie("InfoIfSave")
		deleteCookie("NetType")
	}
     return true;
  }

function GetInput(){
	if (checklogin()){
		
		  var username=window.UserName.value;
		  var userPwd=window.UserPWD.value;
		  var keystr=window.keyxStr.value;
		  var keyuname=window.keyUName.value;
		  var nettype
		   if(window.NetType(0).checked){nettype="0";}
		   if(window.NetType(1).checked){nettype="1";}
		  xmlText = "<Message><username>"+(username)+"</username>";
		  if (PSWD==(userPwd)){
		  	xmlText = xmlText + "<userpasswd>"+(userPwd)+"</userpasswd>";
		  	}
		  else{
		xmlText = xmlText + "<userpasswd>"+md5((userPwd)) +"</userpasswd>";
		}
		  
		  xmlText = xmlText + "<keyusername>"+(keyuname) +"</keyusername>";
		  xmlText = xmlText + "<keystr>"+(keystr) +"</keystr>";	
		  xmlText = xmlText + "<nettype>"+(nettype) +"</nettype>";
		  xmlText = xmlText + "</Message>";
		  var oSend=new ActiveXObject("msxml2.XMLHTTP");
		  oSend.open("POST","http://60.190.81.34/lwga/loginXML.asp",false);
		  oSend.setRequestHeader("CONTENT-TYPE", "text/xml")
		  oSend.send(xmlText);
		  var xmlResult=new ActiveXObject("Msxml2.DOMDocument");
		  xmlResult.async=false;
		  xmlResult.resolveExternals=false;
		  xmlResult.loadXML(oSend.responsexml.xml);
//alert(oSend.responsexml.xml);
		  bResult=xmlResult.selectSingleNode("LoginResult/r").text ;
		  overdue_time=xmlResult.selectSingleNode("LoginResult/overdueTime").text ;
		if(bResult!=1 ){
			 switch(bResult){
				case "0":
			      	alert(" 用户名或密码错误！");
			      	break;
			     case "2":
			      	alert(" 无此用户");
			      	break;
			    case "3":
			      	var isOnLine = window.confirm("该用户已经在线: 单击“确定”继续(强行中断在线的用户)。单击“取消”停止。");
					if (isOnLine) {
						oSend.open("POST","http://60.190.81.34/lwga/unloginXML.asp",false);
						oSend.setRequestHeader("CONTENT-TYPE", "text/xml")
						//alert(xmlText);
						oSend.send(xmlText);

						oSend.open("POST","http://60.190.81.34/lwga/loginXML.asp",false);
						oSend.setRequestHeader("CONTENT-TYPE", "text/xml")
						oSend.send(xmlText);
						//var xmlResult=new ActiveXObject("Msxml2.DOMDocument");
						xmlResult.async=false;
						xmlResult.resolveExternals=false;
						xmlResult.loadXML(oSend.responsexml.xml);
			//alert(oSend.responsexml.xml);
						bResult=xmlResult.selectSingleNode("LoginResult/r").text ;
						
		  				userp=xmlResult.selectSingleNode("LoginResult/userp").text ;
		  				if(bResult==1 ){
								if(userp>8){ 
								window.open('http://60.190.81.34/lwga/monitor.asp','fullscreen','resizeable=1,scrollbars=1,fullscreen=1');
								window.opener=null;
								window.close()
								}else{
								window.location.href='http://60.190.81.34/lwga/devicelist.asp';
								}
							}else{
								alert("密码错误");
								}
					}  else  return false;

			      	break;
			    case "4":
					overdue_time=0-overdue_time;
			      	alert(" 该用户已经失效，请与管理员联系");
			      	break;
				case "5":
			      	alert(" 您的登陆限次已到！无法继续使用本系统");
			      	break;
			     case "6":
			      	alert(" SUB key 验证失败!请正确插入USB key 或需要正确安装KeyClient客户端控件 否则无法继续使用本系统");
			      	break;
			     case "7":
			      	alert(" SUB key 的用户和所输入的用户不同,请更换 key 或更换用户名！否则无法继续使用本系统");
			      	break;  
				default:
			    		alert(" 未知错误："+bResult);
			      	break;
      			}
      			//''ErrorID=3 此用户在线。 1:有此用户 并验证成功。2：无此用户，0：密码错误。4:用户过期 5:次数不足     9： 未知错误
				 return false;
		}else{
			pr=xmlResult.selectSingleNode("LoginResult/userp").text ;
			//var userguid="'"+xmlResult.selectSingleNode("LoginResult/sessionid").text+"'" ;
			//userguid=userguid.replace(/[-]/g,'0');   //去掉-
			//var xxxxx = prompt('变量: ',userguid);

			if(pr>8){  //left=150  screen.availWidth,screen.availHeight
				//window.open("monitor.asp","","Height="+screen.availWidth+"px, Width="+screen.availHeight+"px, top=0; resizable=yes, status=No");
				window.open('http://60.190.81.34/lwga/monitor.asp','fullscreen','resizeable=1,scrollbars=1,fullscreen=1');
				//w=window.open("about:blank","w","width="+(screen.availWidth+8)+",height="+(screen.availHeight+8)+",toolbar=No,location=No,directories=No,status=No,menubar=No,scrollbars=No,resizable=yes,copyhistory=No");
				//w.moveTo(-4,-4);
				//w.location.href="monitor.asp"
				window.opener=null;
				window.close()
				}
			else{
				window.location.href='http://60.190.81.34/lwga/devicelist.asp';
				}
			}
		}
		return true;
}

function AutoLogin(){
	// login();
var username=window.AUTOUser.value;
var userPwd=window.AUTOPWD.value;
var nettype=window.AUTONetType.value;

if (username!=""){
		  xmlText = "<Message><username>"+(username)+"</username>";
		  xmlText = xmlText + "<userpasswd>"+(userPwd) +"</userpasswd>";
		  xmlText = xmlText + "<nettype>"+(nettype) +"</nettype>";
		  xmlText = xmlText + "</Message>";
		  var oSend=new ActiveXObject("msxml2.XMLHTTP");
		  oSend.open("POST","http://60.190.81.34/lwga/loginXML.asp",false);
		  oSend.setRequestHeader("CONTENT-TYPE", "text/xml")
		  oSend.send(xmlText);
		  var xmlResult=new ActiveXObject("Msxml2.DOMDocument");
		  xmlResult.async=false;
		  xmlResult.resolveExternals=false;
		  xmlResult.loadXML(oSend.responsexml.xml);
		  bResult=xmlResult.selectSingleNode("LoginResult/r").text ;
		if(bResult!=1 ){
				alert(" 用户名或密码错误！");
				 return false;
			}
		else{
			pr=xmlResult.selectSingleNode("LoginResult/userp").text ;
			if(pr>8){  //left=150  screen.availWidth,screen.availHeight
				//window.open("monitor.asp","","Height="+screen.availWidth+"px, Width="+screen.availHeight+"px, top=0; resizable=yes, status=No");
				window.open('http://60.190.81.34/lwga/monitor.asp','fullscreen','resizeable=1,scrollbars=1,fullscreen=1');
				//w=window.open("about:blank","fullscreen","width="+(screen.availWidth+8)+",height="+(screen.availHeight+8)+",toolbar=No,location=No,directories=No,status=No,menubar=No,scrollbars=No,resizable=yes,copyhistory=No");
				//w.moveTo(-4,-4);
				//w.location.href="monitor.asp"
				window.opener=null;
				window.close()
				}
			else{
				window.location.href='http://60.190.81.34/lwga/devicelist.asp';
				}
			}
	}

}
-->
</script>
<script LANGUAGE="VBSCRIPT"
	SRC="http://60.190.81.34/lwga/include/md5code.vbs"></script>
<SCRIPT LANGUAGE="VBSCRIPT">
function VBAdvKey_Cer(keyxFun)
	Dim AdvKey_Cer, d, s
    Set AdvKey_Cer = CreateObject("AdvKeyCer.CCer")
	VBAdvKey_Cer =AdvKey_Cer.open(keyxFun,KeyUserName,Keystrstr)
	VBAdvKey_Cer=VBAdvKey_Cer & "|||" & KeyUserName & "|||" & Keystrstr
	Set AdvKey_Cer =nothing
end function
</SCRIPT>
<style type="text/css">
<!--
body {
	font-family: "Verdana";
	font-size: 9pt;
	font-style: normal;
	background-color: #ffffff;
	/*
	background-image: url(http://60.190.81.34/lwga/photo/login.gif);
	*/
}

TD {
	font-size: 12px;
	font-style: Verdana;
}

.input {
	border: 1 solid #738AE7;
	font-size: 9pt;
	background-color: #f9fcff
}

.style1 {
	color: #000000
}
div{display: none}
-->
</style>

</head>
<input type="hidden" name="AUTOUser" value="">
<input type="hidden" name="AUTOPWD" value="">
<input type="hidden" name="AUTONetType" value="">
<input type="hidden" name="keyxFun" value="">
<input type="hidden" name="keyUName" value="">
<input type="hidden" name="keyxStr" value="">
<script language="JavaScript">
  	 AutoLogin();
  	 //ComparMonitor();//验证 Monitor.cab 版本
</script>
<body bgcolor="#0869E7" text="#000000"  onLoad="ComparMonitor() ">
<br />
<br />
<center >
<h2>注意：如果您首次打开本页，请先<a
	href="http://60.190.81.34/lwga/%5Clib%5Cmonitor.cab" class="style1">下载客户端</a></h2>
<br /><h3>1.视频功能仅支持IE浏览器 <br/>2.请将本站点设为安全站点</h3>
</center>

<div id="Layer1" 
	style="position: absolute; width: 200px; height: 105px; z-index: 1; left: 351px; top: 224px"
	class="TD">

<table width="200" align="center" cellspacing="2" cellpadding="2"
	id="login">
	<tr>
		<td width="37%">用 户 名：</td>
		<td colspan="3" width="63%"><input type="text" name="UserName"
			id="UserName" class=".input" style="ime-mode: disabled"
			onMouseOver="this.focus()" onFocus="this.select()" size="13"
			value="用户名"></td>
	</tr>
	<tr>
		<td width="37%">密 码：</td>
		<td colspan="3" width="63%"><input type="password" name="UserPWD"
			id="UserPWD" size="13" style="ime-mode: disabled"
			onMouseOver="this.focus()" onFocus="this.select()"><br>
		<input type="checkbox" name="IfSave" value="0" title="记住用户名和密码"><font
			color="#666666">记住用户名密码</font></td>
	</tr>
	<tr>
		<td width="37%">网络类型：</td>
		<td colspan="3" width="63%"><input type="radio" name="NetType"
			value="0">局域 <input type="radio" name="NetType" value="1"
			checked>广域</td>
	</tr>
</table>
<br>
<table width="200" cellspacing="0" cellpadding="0" id="loginbutton">
	<tr>
		<td width="100" style="cursor: hand">
		<div align="center"><input type="image" border="0"
			name="imageField" src="http://60.190.81.34/lwga/photo/btnlogin.gif"
			width="71" height="18" onClick="return GetInput()"></div>
		</td>
		<td width="100" style="cursor: hand">
		<div align="center"><input type="image" border="0"
			name="imageField" src="http://60.190.81.34/lwga/photo/btnexit.gif"
			width="71" height="18" onClick="window.opener=null;window.close();"></div>
		</td>
	</tr>
</table>
</div>
<!-- 
<div id="Layer2"
	style="position: absolute; width: 70px; height: 21px; z-index: 1; left: 470px; top: 385px; color: #FFFFFF;"
	class="TD"><a href="http://60.190.81.34/lwga/%5Clib%5Cmonitor.cab" class="style1">下载客户端</a>
</div>

<div id="Layer3"
	style="position: absolute; width: 200px; height: 21px; z-index: 1; left: 429px; top: 409px; color: #FFFFFF;"
	class="TD"></div>
<div id="Layer4"
	style="position: absolute; width: 90px; height: 21px; z-index: 1; left: 365px; top: 385px; color: #FFFFFF;"
	class="TD"><a href="http://60.190.81.34/lwga/link/reg.js"
	class="style1">设为安全站点</a></div>
 -->
<script language="javascript">
	var PSWD;
	if (getCookie("InfoUserName")!=null){
		PSWD=(getCookie("InfoUserPWD"));
		window.UserName.value=getCookie("InfoUserName");
		window.UserPWD.value=getCookie("InfoUserPWD");
		if (getCookie("InfoIfSave")==1){
			window.IfSave.checked=true;
		}
		if (getCookie("NetType")==1){
			window.NetType(1).checked=true;
		}
	}
</script>

<div id="monitorDiv" style="display: none" /></div>


</body>
</html>

