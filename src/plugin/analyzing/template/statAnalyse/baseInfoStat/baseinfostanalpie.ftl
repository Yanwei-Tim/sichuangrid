<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

	<script type="text/javascript" src="<%=basePath %>extend/amchart/swfobject.js"></script>
 
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

