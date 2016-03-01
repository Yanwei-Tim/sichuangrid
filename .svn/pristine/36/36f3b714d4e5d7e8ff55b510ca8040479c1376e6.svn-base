<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.tianque.core.util.GridProperties" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path2 = request.getContextPath();
String proxyIp = GridProperties.PROXY_SERVER_DOMAIN_NAME;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
String orgId = (String)request.getAttribute("orgId");
String weChatUserId = (String)request.getAttribute("weChatUserId");
String openId = (String)request.getAttribute("openId"); 
String fanName = (String)request.getAttribute("fanName"); 
%>
<html>

    <head>
        <title>事件跟踪</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=proxyIp%>/resource/weChat/weui.min.css" media="all" rel="stylesheet"/>
		<script type="text/javascript" src="<%=proxyIp%>/resource/desktop/js/jquery.js"></script>
   <style>
	td{line-height:2em;}
    .page_title {
			text-align: center;
			font-size: 34px;
			color: #225FBA;
			font-weight: 400;
			margin: 0 15%;
		}
    </style>   
       
    </head>

    <body>
        <div class="page cell">
            <h1 class="page_title" style="color:#225FBA">事件跟踪</h1>
            <input id="proxyIp"  type="hidden" value="<%=proxyIp%>" />
            <input id="openId"  type="hidden" value="<%=openId%>" />
            <input id="orgId" name="preciseInbox.org.id"  type="hidden" value="<%=orgId%>" />
            <input id="toUserName" name="preciseInbox.toUserName"  type="hidden" value="<%=weChatUserId%>" />
            <form id = "findIssueLoger" action="" method="post">
              <div class="weui_cells weui_cells_form" >
                <div class="weui_cell">
                   <div class="weui_cell_hd"><label style="color:red;">*</label><span style="color:black;">服务单号:</span></div>
                   <div class="weui_cell_bd weui_cell_primary">
                      <input type="text" class="weui_input" id="serviceId" name="preciseInbox.serviceId" placeholder="请输入服务单号" checked="checked" oninvalid="这是必填选项" >
                   </div>
                   <div id="myButton" class="weui_cell_bd weui_cell_primary">
                      <button id="getIssueLoger" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >查询</button>
                   </div>
                 </div>
               </div>
            </form>
            
            <div id="issueProcess" class="weui_cell_hd" style="display:none"><span style="color:#333399;font-size:20px">事件处理流程列表:</span></div>
            <div id="issue">
            
                
                <table id="issueTable" class='weui_input'>
                </table>
                
                <!-- 评价框 -->
                <div id="evaluation" style="display:none">
            
                  <div class="weui_cell_hd"><span style="color:#333399;font-size:20px">事件处理流程评价:</span></div>
            
                  <div class="weui_cell">
                     <div class="weui_cell_hd"><span style="color:black;">昵称:</span></div>
                     <div class="weui_cell_bd weui_cell_primary">
                      <input type="text" class="weui_input" id="scorePerson" name="evaluationIssueHandle.scorePerson" checked="checked" value="<%=fanName%>" readonly="readonly" oninvalid="这是必填选项" >
                    </div>
                  </div>
                  <div class="weui_cells_title"><label style="color:red;">*</label><span style="color:black;">满意度</span></div>
                  <div class="weui_cells weui_cells_split">
                  <div class="weui_cell weui_cell_select">
                   <div class="weui_cell_bd weui_cell_primary">
                       <select class="weui_select" id="scoreStarNumber" name="evaluationIssueHandle.scoreStarNumber" oninvalid="这是必选选项" >
                           <option value=''>请选择</option>
		  		           <option value='5'>非常满意(5分)</option>
		  			       <option value='4'>比较满意(4分)</option>
		  			       <option value='3'>满意(3分)</option>
		  			       <option value='2'> 一般(2分)</option>
		  			       <option value='1'> 不满意(1分)</option>
                       </select>
                    </div>                    
                  </div>
                  </div>
                  <div class="weui_cell">
                   <div class="weui_cell_hd"><span style="color:black;">内容:</span></div>
                   <div class="weui_cell_bd weui_cell_primary">
                       <textarea class="weui_textarea"  id="content" name="evaluationIssueHandle.content" placeholder=""></textarea>
                   </div>
                  </div>
               </div>
                
                
                
                <button id="addEvaluation" type="button" class="weui_btn weui_btn_primary" style="display:none">确定</button>
                <button id="evaluationButton" type="button" class="weui_btn weui_btn_primary" style="display:none">评价</button>
                
                <button id="backButton" type="button" class="weui_btn weui_btn_primary" style="display:none">返回列表</button>
            </div>
            

            
        </div> 
     <script type="text/javascript">
        //防止多次点击
        //var multipleClicks=true;
        var orgId=$('#orgId').val();
        var toUserName=$('#toUserName').val();
        var totalnumber;
        var pageSize=12;//每页显示条数
        var totalPage=0;//总页数
        var currentPage=1;//当前页
        var openId = $('#openId').val();//用户openId     
		var isCloseCase=false;//是否结案
		var serviceId;//事件的服务单号
		var closeCaseDate;//结案时间
		var issueName;//单个事件名称
        
        $(function() {
        	findTotalNumber();
        	
        	//评价处理
    		$("#evaluationButton").click(function(){
    			evaluation();
    		});
    		//返回处理
    		$("#backButton").click(function(){
    			removeButtonAndBack();
    		});
    		//查询单个事件
    		$("#getIssueLoger").click(function(){
    			serviceId=$("#serviceId").val()
    			getIssueLoger(serviceId);
    		});
    		//添加评价
    		$("#addEvaluation").click(function(){
    			evaluationAjax();
    		});
    		
        });
        
        /**
         * 根据不同的表求出，表的总记录数
         */
        function  findTotalNumber(){
        	var url=$("#proxyIp").val()+"/weChat/preciseInbox/findIssueTotal.action";
        	if(openId.length==0){
        	 alert("获取openId失败,请重新进入该界面!");
           	 return;
        	}
        	
        	var dataList = "openId="+openId;
        	$.ajax({         		  
        		  type:'post',
        		  url:url,
        		  async:false,
        		  dataType:'json',
        		  data:dataList,
        		   success:function(json){    		
        			   if(json.id==null){
                   		alert(json);
                   		return;
        			   }else{
        				   totalnumber=json.id;   			
            			   pagesfun(totalnumber);
            			   getIssueList(currentPage,pageSize);
        			   }   			   
        		  }
        	  });
        }
        
        //事件历史列表
        function getIssueList() {
        	 var buttonList="";
        	 var url=$("#proxyIp").val()+"/weChat/preciseInbox/findHistoryIssue.action";
            	if(openId.length==0){
               	 alert("获取openId失败,请重新进入该界面!");
                  	 return;
               	}
 	
        		var dataList = "openId="+openId+"&page"+currentPage+"&rows"+pageSize;
        		
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: url,
                    data:dataList,
                    success: function(json) {                 
                    	if(json.rows==null){
                    		alert(json);
                    		return;
                    	}else{                  		
                    		var th="<tr style='font-weight:bold;'><td>服务单号</td><td >事件名称</td><td >时期</td></tr>";
                        	$("#issueTable").append(th);
                            var typeData = json.rows; 
                            $.each(typeData, function(i, n) {
                                var tbBody = "";
                                var trColor;
                               
                                if (i % 2 == 0) {
                                    trColor = "even";
                                }
                                else {
                                    trColor = "odd";
                                }
                                var date = n.createDate;             
                                date = date.substring(0,10);
                                tbBody += "<tr style='color:#0066FF' onclick='getIssueLoger("+'"'+n.serviceId+'","'+n.issueName+'"'+");'><td>" + n.serviceId + "</td>" + "<td>" + n.issueName + "</td>" + "<td>" + date + "</td></tr>";                             
                                //<a href='#' onclick='favoriten("+obj.teachlibrary.id+",'"+obj.teachlibrary.name+"',"+obj.teachlibrary.uploaduserid+");'>点击</a>
                                $("#issueTable").append(tbBody);
                            });
                            
                            buttonList="<div id='clearButtonList'><button type='button' class='weui_btn weui_btn_mini weui_btn_primary' onclick='upPage();'>上一页</button>"+
                            "<button type='button' class='weui_btn weui_btn_mini weui_btn_primary' onclick='downPage();' >下一页</button>"+
                            "<button type='button' class='weui_btn weui_btn_mini weui_btn_primary'>当前"+currentPage+"/"+totalPage+"</button>"+                       
                            "<input type='number' class='weui_input weui_btn_mini' id='jumpPageNumber' placeholder='请输入页数' style='color:red'>"+
                            "<button type='button' class='weui_btn weui_btn_mini weui_btn_primary 'onclick='jumpPage();' >跳转</button>"
                            +" </div>";
                            $("#issue").append(buttonList);
                    	}	
                    }
                });
        	
        }
        
        //获取单个事件详细信息	
        function getIssueLoger(id,name) {

        	 var url=$("#proxyIp").val()+"/weChat/preciseInbox/findIssueLoger.action";
        	//serviceId=$("#serviceId").val(); 
        	
        	serviceId=id;
        	issueName=name;
        	if(serviceId.length==0){
        		alert("服务号为空!");
        		return;
        	}
        	//alert(serviceId);
        	//if(multipleClicks){
        		//multipleClicks=false;
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: url,
                    data:{'preciseInbox.serviceId':serviceId},
                    success: function(json) {
                    	var evaluationIssueHandle = json.evaluationIssueHandle;
                    	
                    //alert(evaluationIssueHandle);
                    	 if (json.issueLogs==null){
                    		 //multipleClicks=true;
                    		alert(json);
                    		return;
                    	}else{
                    		var typeData = json.issueLogs;
                    		
                    		$("#issueProcess").show();
                    		$("#findIssueLoger").hide();
                            $("#issueTable").html("");
                            $("#clearButtonList").remove();
                            var th="<tr style='font-weight:bold;'><td>处理描述</td><td >处理人电话</td><td >处理时间</td></tr>";
                        	$("#issueTable").append(th);
                            $.each(typeData, function(i, n){
                                var tbBody = ""
                                var trColor;
                                if (i % 2 == 0) {
                                    trColor = "even";
                                }
                                else {
                                    trColor = "odd";
                                }                 
                               
                                var dealDescription = n.dealDescription;             
                                dealDescription = dealDescription.substring(0,2);
                                if(dealDescription=="结案"){
                                	isCloseCase = true;
                                	closeCaseDate = n.createDate;                            	
                                }
                                tbBody += "<tr><td>" + n.dealDescription + "</td>" + "<td>" + n.mobile + "</td>" + "<td>" + n.createDate + "</td></tr>";
                                $("#issueTable").append(tbBody);
                            });
                            
                            $("#backButton").show();
                            
                            //是否结案判断
                            if(isCloseCase){                       	
                            	//findEvaluationIssueHandle();
                            		var scoreStarNumber;
                            	   if(evaluationIssueHandle!=null){                            			
                            		   scoreStarNumber= evaluationIssueHandle.scoreStarNumber;
                            	   }
                            		//用户满意度为空说明没有评价(是否评价)
                     			   if(scoreStarNumber==null){
                     				$("#evaluationButton").show();
                                		return;
                     			   }else{   
                     				   isCloseCase=false;
                     				   $("#evaluation").show();
                     				   $("#scoreStarNumber").val(evaluationIssueHandle.scoreStarNumber);
                     				   $("#content").val(evaluationIssueHandle.content);
                           			   $("#scoreStarNumber").attr("disabled",true);
                            		   $("#content").attr("readonly",true);                     				   
                     			   }   		                       	
                            }
                                               
                    	}
                    	
                    }
                });
        	 
 
        }

        //查询单个事件评价
        function findEvaluationIssueHandle(){
        	
        	var url=$("#proxyIp").val()+"/evaluationIssueHandleManage/findEvaluationIssueHandle.action";
        	if(serviceId.length==0){
        	 alert("服务单号为空!");
           	 return;
        	}
        	
        	var dataList = "evaluationIssueHandle.serialNumber="+serviceId;
        	$.ajax({         		  
        		  type:'post',
        		  url:url,
        		  async:false,
        		  dataType:'json',
        		  data:dataList,
        		   success:function(json){
        			   var scoreStarNumber= json.scoreStarNumber;
        			   if(scoreStarNumber==null){
        				$("#evaluationButton").show();
                   		return;
        			   }else{   
        				   isCloseCase=false;
        				   $("#evaluation").show();
        				   $("#scoreStarNumber").val(json.scoreStarNumber);
        				   $("#content").val(json.content);
              				$("#scoreStarNumber").attr("disabled",true);
               				$("#content").attr("readonly",true);
        				   
        			   }   			   
        		  }
        	  });
        }
        /**
         * 评价处理 
         */
        function evaluation(serviceId){
          //隐藏评价按钮
          $("#evaluationButton").hide();
          //显示确定按钮
          $("#addEvaluation").show();
          //显示评价框
       	  $("#evaluation").show();
    
       //	 multipleClicks=true;
       	 
       }
        
        function evaluationAjax(){

            var url=$("#proxyIp").val()+"/evaluationIssueHandleManage/addEvaluationIssueHandle.action";
            var scorePerson=$("#scorePerson").val();
            var scoreStarNumber=$("#scoreStarNumber").val();
            content=$("#content").val();

  			if(openId.length==0){
            	alert("获取openId失败!");
           	    //multipleClicks=true;
           	 return;
  			}else if(serviceId.length==0){
            	alert("获取事件的服务单号失败!");
            	 //multipleClicks=true;
            	 return;
            }else if(scorePerson.length==0){
            	alert("评价人不能为空!");
            	 //multipleClicks=true;
            	 return;
            }else if(scoreStarNumber.length==0){
            	alert("满意度不能为空!");
            	 //multipleClicks=true;
            	 return;
            }else if(orgId.length==0){
        		 alert("获取组织ID失败,请重新进入该界面!");      		   
      		   return;
        	}else if(toUserName.length==0){
        		alert("获取微信公众号失败,请重新进入该界面!");
        	}else if(closeCaseDate.length==0){
       		     alert("获取结案时间失败,请重新进入该界面!");    		   
    		   return;
      	    }else if(issueName.length==0){
          		 alert("获取到事件名称失败,请重新进入该界面!");      		   
      		   return;
        	}
  			
        	 var dataList = "evaluationIssueHandle.openId="+openId+
        	 "&evaluationIssueHandle.org.id="+orgId+
        	 "&evaluationIssueHandle.toUserName="+toUserName+
        	 "&evaluationIssueHandle.serialNumber="+serviceId+
        	 "&evaluationIssueHandle.scorePerson="+scorePerson+
        	 "&evaluationIssueHandle.scoreStarNumber="+scoreStarNumber+
        	 "&evaluationIssueHandle.closeCaseDate="+closeCaseDate+
        	 "&evaluationIssueHandle.issueName="+issueName;

        	 if(content.length!=0){
        		 dataList += "&evaluationIssueHandle.content="+content;
        	 }
        	 
       	 $.ajax({         		  
       		  type:'post',
       		  url:url,
       		  async:false,
       		  dataType:'json',
       		  data:dataList,
       		   success:function(json){		
       			   if(json==true||json=="true"){
       				   
       				$("#addEvaluation").hide();
       				$("#scoreStarNumber").attr("disabled",true);
       				$("#content").attr("readonly",true);
       				 
                  	alert("评价成功!");	
                  		return;
       			   }else{	
       				$("addEvaluation").hide();
       				alert(json);
              		    return;
       			   }   			   
       		  }
       	  });
        }
        
        /**
         *返回处理
         */
        function removeButtonAndBack(){
        	
        	//清空评价输入框
		   $("#scoreStarNumber").val("");
		   $("#content").val("");
   		   $("#scoreStarNumber").attr("disabled",false);
    	   $("#content").attr("readonly",false);	
        	
        
        	//隐藏标题
        	$("#issueProcess").hide();
        	//隐藏确定按钮
        	$("#addEvaluation").hide();
        	//隐藏评价DIV
        	$("#evaluation").hide();
        	//隐藏返回按钮
        	$("#backButton").hide();
  
        	//隐藏评价按钮
        	$("#evaluationButton").hide();
        	//显示查询按钮
        	$("#findIssueLoger").show(); 
        	//清空表格
        	$("#issueTable").html("");
        	 //multipleClicks=true;
        	 getIssueList();
        }
        
        /**
         *根据总条数求出总页数 
         */
        function pagesfun(totalnumber){
        	if(totalnumber>0&&totalnumber%pageSize==0){
        		this.totalPage=totalnumber/pageSize;
        	}else if(totalnumber%pageSize!=0&&totalnumber>pageSize){
        		this.totalPage=Math.ceil(totalnumber/pageSize);
            }else if(totalnumber>0&&totalnumber<=pageSize){
            	this.totalPage=1;
            }
        }
        
        /**
         * 下一页方法
         */
        function downPage(){
        	
          if(currentPage<totalPage){
        	  currentPage++;
        		$("#clearButtonList").remove();
           	    $("#issueTable").html("");
        	    //multipleClicks=true;
        	   
        		getIssueList();
        	}
          
        }

        /**
         * 上一页方法
         */
        function upPage(){
        	if(currentPage>1){
        		currentPage--;
        		$("#clearButtonList").remove();
           	    $("#issueTable").html("");
        	    //multipleClicks=true;
        		getIssueList();	
        	}
        }
        //跳转
        function jumpPage(){ 
        	var jumpPageNumber=$("#jumpPageNumber").val();
        	if(jumpPageNumber!=currentPage&&jumpPageNumber<=totalPage&&jumpPageNumber>0){
        		$("#clearButtonList").remove();
           	    $("#issueTable").html("");
        	    //multipleClicks=true;
        	    currentPage=jumpPageNumber;
        	    getIssueList();   
        	}
        }
        </script>  
    </body>
</html>