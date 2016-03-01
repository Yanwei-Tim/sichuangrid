<%@ page language="java" import="java.util.*,com.tianque.core.util.GridProperties" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String msg = request.getAttribute("msg").toString();
%>
<html>

    <head>
        <title>消息上报</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <style>
    body {
    background: #f8f8f8;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    text-align: center;
    color: #fff;
    }
    .page-container {margin: 60px auto 0 auto;}
    </style>   
    </head>

    <body>
        <div class="page-container">
            <h1 style="color:black"><%=msg%></h1>   
        </div>
    </body>
    <script type="text/javascript">
    </script>
</html>