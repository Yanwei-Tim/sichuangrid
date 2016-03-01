<%@page session="false"%>
<%@page import="java.net.*,java.io.*, org.dom4j.*, org.dom4j.io.*" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%
/**
*为实现客户端跨域请求而采用jsp制作的代理转发器
*使用方式：
*将该文件放在iserver webapps下面你的工程目录下，请求格式：http://localhost:8090/yourRoot/Proxy.jsp?url=yoururl,注意url参数中特殊字符需要转码。
*支持post，get请求
*/
try {
	String reqUrl = request.getParameter("url");
	try{
		// 解析客户端发送的请求
		SAXReader builder = new SAXReader();
		Document indoc = builder.read(new InputStreamReader(request.getInputStream(), "UTF-8"));
		Element element = indoc.getRootElement();
		String service = element.attribute("service").getValue();
		String version = element.attribute("version").getValue();
		String typeName = element.element("Query").attribute("typeName").getValue();
		String bbox = element.element("Query").element("Filter").element("BBOX").element("Box").element("coordinates").getText().replace(" ",",");
		reqUrl += "?SERVICE="+service+"&REQUEST=getFeature&VERSION="+version+"&typeName="+typeName+"&BBOX="+bbox;
	}catch(Exception e){}
	System.out.println(reqUrl);
	URL url = new URL(reqUrl);
	HttpURLConnection con = (HttpURLConnection)url.openConnection();
	con.setRequestProperty("content-type", "application/xml; charset=UTF-8");
	con.setDoOutput(true);
	con.setRequestMethod(request.getMethod());
	//con.setCharacterEncoding("UTF-8");  
	int clength = request.getContentLength();
	if(clength > 0) {
		con.setDoInput(true);
		byte[] idata = new byte[clength];
		
		//String s = String.ValueOf(clength);
		//System.out.println(clength);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));  
		String line1 = null;  
		StringBuilder sb = new StringBuilder();  
		while((line1 = br.readLine())!=null){  
		   sb.append(line1);  
		}
		//request.getInputStream().read(idata, 0, clength);
		//String test=String.valueOf(idata);
		//System.out.println(sb);
		
		//OutputStreamWriter out=new OutputStreamWriter(con.getOutputStream()); 
		//out.write(new String(sb.toString().getBytes("ISO-8859-1")));

		byte[] paradata = sb.toString().getBytes();
		//con.getOutputStream().write(paradata);
		
		
		/* OutputStream os = con.getOutputStream();  
	    String param = new String();  
	    param = "SERVICE=WFS&REQUEST=getFeature&VERSION=1.0.0&typeName=mapdata:wusheng&maxFeatures=100&BBOX=-472.15384615384,-180,472.15384615384,180";  
	    os.write(param.getBytes()); */
	}
	response.setContentType(con.getContentType());

	BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
	String line;
	StringBuilder sb1 = new StringBuilder();  
	while ((line = rd.readLine()) != null) {
		out.println(line); 	
		sb1.append(line);  	
	}
	rd.close();

} catch(Exception e) {
System.out.println(0);
System.out.println(e);

	response.setStatus(500);
}
%>