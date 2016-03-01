<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>基础数据统计分析</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="<%=basePath %>extend/amchart/swfobject.js"></script>
  </head>
  
  <body>
    <div id="baseInfoStanalFlah">
		<strong>You need to upgrade your Flash Player</strong>
	</div>

	<script type="text/javascript">
	var data_key1 =('${data_key1}');
	var title_key1 =('${title_key1}');
		// <![CDATA[		
		var so = new SWFObject("../extend/amchart/pie/ampie.swf", "ampie", "770", "260", "4", "#FFFFFF");
		so.addVariable("path", "../extend/amchart/pie/");
		so.addVariable("settings_file", encodeURIComponent("../extend/amchart/pie/day_settings.jsp?showTitle="+title_key1+"&chartDataList="+data_key1));
		so.addVariable("data_file", encodeURIComponent("../extend/amchart/pie/day_data.jsp?showTitle="+title_key1+"&chartDataList="+data_key1));
		so.write("baseInfoStanalFlah");
		// ]]>
	</script>
  </body>
</html>
