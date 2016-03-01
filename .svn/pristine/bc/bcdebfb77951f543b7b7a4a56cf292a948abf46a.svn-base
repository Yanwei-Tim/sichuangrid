<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="educationPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	.propertyMulSelect{overflow:hidden;}
	.propertyMulSelect li{float:left;list-style: outside none none;}
	</style>
	
	<table id="issueAccordingPrint" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    	<jsp:include page="${path}/account/peopleAspiration/printPeopleAspirationDlg.jsp"/>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=14>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">建设地点（区域）或建设起点——讫点</td>
	            <td width="4%" style="text-align: left;font-Size:18px; height:20px;width:52%;white-space: nowrap;" colspan="13">${education.fromAddress} <c:if test="${education.fromAddress ne null &&education.toAddress ne null}">——</c:if>${education.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="2">受益人口（人）</td>
	            <td width="4%" style="font-Size:18px; height:20px;width:16%;white-space: nowrap;" colspan="6">${education.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
	           
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" > 
        		 <p>工程建设名称或学生就读学校</p></td>
	            <td width="4%" style="text-align: left;font-Size:18px; height:20px;width:52%;" colspan="13">${education.projectName}${education.schoolName}</td>
	            
	            <td width="4%" id="_buildType" colspan="8" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:32%;font-weight:750;white-space: nowrap;">
	          	 建设性质：
	              	新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
	         </td>
        	</tr>
        	
        	<tr >
        		<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
        			<p>项目</p>
        			<p>类别</p>
        		</td>
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>项目内容</p>
	         	</td>
	          
        		<td width="4%" colspan="1"   style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	        		<p >计划投资</p>
	        		<p >(万元)</p>
        		</td>
	            <td width="4%" colspan="8" style="text-align:left;font-Size:20px; height:20px;width:32%;white-space: nowrap;">
	            	${education.plannedInvestment}
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	            	<p>自筹资金</p>
					<p>(万元)</p>
	            </td>
	            <td width="4%" colspan="3" style="text-align:left;font-Size:20px; height:20px;width:12%;white-space: nowrap;">${education.selfFund}</td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	          		<p>缺口资金</p>
					<p>(万元)</p>
	            </td>
	            <td width="4%" colspan="3" style="text-align:left;font-Size:20px; height:20px;width:12%;white-space: nowrap;">${education.gapFund}</td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>建设面积</p>
	           		<p>(平方米)</p>
	           </td>
	            <td width="4%" colspan="5" style="text-align:left;font-Size:20px; height:20px;width:20%;white-space: nowrap;">${education.buildArea}</td>
        	</tr>
        	
        	<tr >
        		<td width="4%" rowspan="4" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
        			<p>工</p><p>程</p><p>建</p><p>设</p><p><input type="checkbox" name="type" value="工程建设" disabled/></p>
        		</td>
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>幼儿园建设<input type="checkbox" name="_subType" value="幼儿园建设" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
	        		<p>建设单位名称</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:24px;width:28%;">
	            	<div class="educationText" style="display:none">${education.buildCompanyName}</div>
	            </td>
	            <td width="4%" colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;">
	            	  村  <input type="checkbox" name="addressCategory" value="村" disabled/>
	            	  社区<input type="checkbox" name="addressCategory" value="社区" disabled/>
					 场镇<input type="checkbox" name="addressCategory" value="场镇" disabled/>
					 城市<input type="checkbox" name="addressCategory" value="城市" disabled/>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>规模</p>
	           </td>
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
				            一个班<input type="checkbox" name="scopeCategory" value="一个班" disabled/>
				             三个班<input type="checkbox" name="scopeCategory" value="三个班" disabled/>
					六个班<input type="checkbox" name="scopeCategory" value="六个班" disabled/>
					 九个班以上<input type="checkbox" name="scopeCategory" value="九个班以上" disabled/>   
				</td>
        	</tr>
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" >
	         		<p>农村薄弱学校改造<input type="checkbox" name="_subType" value="农村薄弱学校改造" disabled/> </p>
	         		<p>危房改造<input type="checkbox" name="_subType" value="危房改造" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
	        		<p>建设单位名称</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:20px;width:28%;">
	            	<div class="educationText" style="display:none">${education.buildCompanyName}</div>
	            </td>
	            <td width="4%" colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:24%;font-weight:750;">
	            	<p>
	            		村小 <input type="checkbox" name="addressCategory" value="村小" disabled/>
	            		 分校 <input type="checkbox" name="addressCategory" value="分校" disabled/> 
	            		小学 <input type="checkbox" name="addressCategory" value="小学" disabled/>
	            		 中学 <input type="checkbox" name="addressCategory" value="中学" disabled/>
	            	 </p>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>规模</p>
	           </td>
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
				      <p>三个班<input type="checkbox" name="scopeCategory" value="三个班" disabled/>
				      	  六个班<input type="checkbox" name="scopeCategory" value="六个班" disabled/>
				      	   九个班<input type="checkbox" name="scopeCategory" value="九个班" disabled/>
				      </p>
				      <p>十二个班<input type="checkbox" name="scopeCategory" value="十二个班" disabled/>
				      	 十五个班以上<input type="checkbox" name="scopeCategory" value="十五个班以上" disabled/>
				       </p>
				</td>
        	</tr>
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>教师周转房建设<input type="checkbox" name="_subType" value="教师周转房建设" disabled/>  </p>
	         	</td>
	          
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
	        		<p>建设单位名称</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:20px;width:28%;">
	           	 	<div class="educationText" style="display:none">${education.buildCompanyName}</div>
	           	</td>
	            <td width="4%" colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;">
	            	<p>
	            		村小<input type="checkbox" name="addressCategory" value="村小" disabled/>
	            	 	分校<input type="checkbox" name="addressCategory" value="分校" disabled/>  
	            		小学<input type="checkbox" name="addressCategory" value="小学" disabled/> 
	            		中学<input type="checkbox" name="addressCategory" value="中学" disabled/>
	            	</p>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>规模</p>
	           </td>
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
		            <p> 
		            	十二套<input type="checkbox" name="scopeCategory" value="十二套" disabled/> 
		                                           十八套<input type="checkbox" name="scopeCategory" value="十八套" disabled/>
		            	 二十四套<input type="checkbox" name="scopeCategory" value="二十四套" disabled/>
		            	 三十六套以上<input type="checkbox" name="scopeCategory" value="三十六套以上" disabled/>
		           </p>
				</td>
        	</tr>
        	
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>维修维护 <input type="checkbox" name="_subType" value="维修维护" disabled/> </p>
	         	</td>
	          
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
	        		<p>建设单位名称</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:20px;width:28%;">
	            	<div class="educationText" style="display:none">${education.buildCompanyName}</div>
	            </td>
	            <td width="4%" colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;">
	            	<p>
	            		村小<input type="checkbox" name="addressCategory" value="村小" disabled/>
	            		 分校<input type="checkbox" name="addressCategory" value="分校" disabled/>  
	            		小学<input type="checkbox" name="addressCategory" value="小学" disabled/>
	            		 中学<input type="checkbox" name="addressCategory" value="中学" disabled/>
	            	</p>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>规模</p>
	           </td>
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
		            <p>150人<input type="checkbox" name="scopeCategory" value="150人" disabled/>
		             	300人<input type="checkbox" name="scopeCategory" value="300人" disabled/>
		              	600人<input type="checkbox" name="scopeCategory" value="600人" disabled/>
		              	800人以上<input type="checkbox" name="scopeCategory" value="800人以上" disabled/>
		             </p>
				</td>
        	</tr>
        	
        	
        	
        	<tr >
        		<td width="4%" rowspan="6" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
        			<p>就</p><p>学</p><p><input type="checkbox" name="type" value="就学" disabled/></p>
        		</td>
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>贫困大学新生资助<input type="checkbox" name="_subType" value="贫困大学新生资助" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	        		<p>学生姓名</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:20px;width:28%;">
	            	<div class="educationText" style="display:none">${education.studentName}</div>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	            	高中<input type="checkbox" name="degreeCategory" value="高中" disabled/>
	            	职中<input type="checkbox" name="degreeCategory" value="职中" disabled/>
	            </td>
	           <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>资助时间</p>
	           </td>
	            <td width="4%" colspan="3" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="educationText" style="display:none"><s:date name="education.studyDate" format="yyyy-MM-dd "/></div>
	            </td>
				 <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>方式</p>
	           </td>
	           
	           <td width="4%" colspan="8" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:32%;font-weight:750;">
				          助学贷款<input type="checkbox" name="modeCategory" value="助学贷款" disabled/>
				           社会捐赠<input type="checkbox" name="modeCategory" value="社会捐赠" disabled/>
					社会捐助<input type="checkbox" name="modeCategory" value="社会捐助" disabled/>
					 银行信贷<input type="checkbox" name="modeCategory" value="银行信贷" disabled/>
				</td>
				
        	</tr>
        	
        	        	
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	         		<p>两免一补<input type="checkbox" name="_subType" value="两免一补" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	        		<p>学生姓名</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:18px; height:20px;width:28%;white-space: nowrap;">
	            	<div class="educationText" style="display:none">${education.studentName}</div>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	            	小学<input type="checkbox" name="degreeCategory" value="小学" disabled/> 
	            	 初中<input type="checkbox" name="degreeCategory" value="初中" disabled/>
	            </td>
	           <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>免补时间</p>
	           </td>
	            <td width="4%" colspan="3" style="font-Size:18px; height:20px;width:12%;white-space: nowrap;white-space: nowrap;">
	            	<div class="educationText" style="display:none"><s:date name="education.studyDate" format="yyyy-MM-dd "/></div>
	            </td>
				 <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;">
	           		项目名称
	           </td>
	           
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;">
				    <p>  
				    	免学杂费<input type="checkbox" name="itemCategory" value="免学杂费" disabled/>
				    	免书本费<input type="checkbox" name="itemCategory" value="免书本费" disabled/>
				    	生活困难补助<input type="checkbox" name="itemCategory" value="生活困难补助" disabled/>
				     </p>

				</td>
        	</tr>
        		        	
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" >
	         		<p>高中及职中学生学杂费及生活困难补助<input type="checkbox" name="_subType" value="高中及中职学生学杂费及生活困难补助" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	        		<p>学生姓名</p>
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px; height:20px;width:28%;">
	            	<div class="educationText" style="display:none">${education.studentName}</div>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	            	高中<input type="checkbox" name="degreeCategory" value="高中" disabled/>
	            	职中<input type="checkbox" name="degreeCategory" value="职中" disabled/>
	            </td>
	           <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>补助时间</p>
	           </td>
	            <td width="4%" colspan="3" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="educationText" style="display:none"><s:date name="education.studyDate" format="yyyy-MM-dd "/></div>
	            </td>
				 <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		<p>项目名称</p>
	           </td>
	           
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;">
				    <p> 
				    	 免学杂费<input type="checkbox" name="itemCategory" value="免学杂费" disabled/>
				    	生活困难补助<input type="checkbox" name="itemCategory" value="生活困难补助" disabled/>
				     </p>
				</td>
        	</tr>
        	
        	<tr >
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
	         		<p>进城农民工子女就读<input type="checkbox" name="_subType" value="进城农民工子女就读" disabled/></p>
	         	</td>
	         	<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;" >
	        		学生姓名
        		</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:16px; height:12px;width:28%;">
	            	<div class="educationText" style="display:none">${education.studentName}</div>
	            </td>
	            <td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;">
	            	学前教育<input type="checkbox" name="degreeCategory" value="学前教育" disabled/>
	            	小学<input type="checkbox" name="degreeCategory" value="小学" disabled/>
	            	初中<input type="checkbox" name="degreeCategory" value="初中" disabled/>
	            </td>
	           <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	           		拟进城入学时间
	           </td>
	            
	           <td width="4%" colspan="7" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
					<div class="educationText" style="display:none"><s:date name="education.studyDate" format="yyyy-MM-dd"/></div>
				</td>
        		
				
	          
        	</tr>
        	<tr>
	         	<td width="4%" colspan="1" rowspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" >
	         		<p>上学路途难<input type="checkbox" name="_subType" value="上学路途难" disabled/></p>
	         	</td>
	          
        		<td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	        		<p>路程</p>
        		</td>
	            <td width="4%" colspan="22" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:88%;font-weight:750;white-space: nowrap;">
					三公里<input type="checkbox" name="distanceCategory" value="三公里" disabled/>
					四公里<input type="checkbox" name="distanceCategory" value="四公里" disabled/>
					 五公里<input type="checkbox" name="distanceCategory" value="五公里" disabled/>
					六公里<input type="checkbox" name="distanceCategory" value="六公里" disabled/>
					七公里及以上<input type="checkbox" name="distanceCategory" value="七公里及以上" disabled/>
				</td>
        	<tr >
	          
        		<td  width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	        		<p>路  况</p>
        		</td>
	            <td  width="4%" colspan="22" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:88%;font-weight:750;white-space: nowrap;">
					山路<input type="checkbox" name="roadConditionCategory" value="山路" disabled/>
					渡河<input type="checkbox" name="roadConditionCategory" value="渡河" disabled/>
					过水库<input type="checkbox" name="roadConditionCategory" value="过水库" disabled/>
					 乘车<input type="checkbox" name="roadConditionCategory" value="乘车" disabled/>
				</td>
        	</tr>
    
        	<tr height="20">
        		<td height="20" colspan="1" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:4%;white-space: nowrap;" >
	        		<p>其</p><p>他</p><p><input type="checkbox" name="type" value="其他" disabled/></p>
        		</td>
        		
        		<td height="20" width="4%" colspan="23"  style="text-align: left;font-Size:10px height:20px;width:92%;">
	            	 <div class="educationText" style="display:none">${education.otherContent}</div>
	            </td>
        		
        	</tr>
   
    </table>
    <s:set name="feedbacks" value="#request.peopleAspirations.feedbacks"></s:set>
    <s:set name="issueDealLogs" value="#request.peopleAspirations.issueDealLogs"></s:set> 
 	 <jsp:include page="${path}/account/peopleAspiration/printWorkLogDlg.jsp"/>
   
   
    
    
</table>
</div>


<script type="text/javascript">
var createType='${peopleAspirations.createTableType.id}';
var gender='${peopleAspirations.gender.id}';
var appealHumanType='${peopleAspirations.appealHumanType.id}';
var position ='${peopleAspirations.position.id}';
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_BUILD_TYPE" 
	defaultValue="${education.buildType.id}" />';


var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT" 
	defaultValue="${education.projectCategory.id}" />';
	
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT_SUB" 
	defaultValue="${education.projectSubCategory.id}" />';
var addressCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ADDRESS_TYPE" 
	defaultValue="${education.addressCategory.id}" />';
var scopeCategory =	'<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_SCOPE_TYPE" 
	defaultValue="${education.scopeCategory.id}" />';
var modeCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_MODE_TYPE" 
	defaultValue="${education.modeCategory.id}" />';
var degreeCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DEGREE_TYPE" 
	defaultValue="${education.degreeCategory.id}" />';
var itemCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ITEM_TYPE" 
	defaultValue="${education.itemCategory.id}" />';
var distanceCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DISTANCE_TYPE" 
	defaultValue="${education.distanceCategory.id}" />';
var roadConditionCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ROAD_CONDITION_TYPE" 
	defaultValue="${education.roadConditionCategory.id}" />';	
$(document).ready(function(){
	$("input[name='createType']").each(function(){
        if($(this).val() ==createType) 
           $(this).attr('checked',"checked");
   });
	$("input[name='appealHumanType']").each(function(){
        if($(this).val() ==appealHumanType) 
           $(this).attr('checked',"checked");
   });
	$("input[name='gender']").each(function(){
        if($(this).val() ==gender) 
           $(this).attr('checked',"checked");
   }); 
	$("input[name='position']").each(function(){
        if($(this).val() ==position) 
           $(this).attr('checked',"checked");
   });  
	$("input[name='buildType']").each(function(){
        if($(this).val() ==buildType) 
           $(this).attr('checked',"checked");
   	}); 

	$("input[name='type']").each(function(i){
        if($(this).val() ==type){
           $(this).attr('checked',"checked");
        }
   	});
	$("input[name='_subType']").each(function(i){
        if($(this).val() ==subtype){
           $(this).attr('checked',"checked");
           $(this).parent().parent().parent().find(".educationText").each(function(){
    		   $(this).show();
           })

           $(this).parent().parent().parent().find("input[name='addressCategory']").each(function(){
        	   if($(this).val() ==addressCategory){
                   $(this).attr('checked',"checked");
        	   }
           })

  		  $(this).parent().parent().parent().find("input[name='scopeCategory']").each(function(){
        	   if($(this).val() ==scopeCategory){
                   $(this).attr('checked',"checked");
        	   }
           })
           $(this).parent().parent().parent().find("input[name='modeCategory']").each(function(){
        	   if($(this).val() ==modeCategory){
                   $(this).attr('checked',"checked");
        	   }
           })
           $(this).parent().parent().parent().find("input[name='degreeCategory']").each(function(){
        	   if($(this).val() ==degreeCategory){
                   $(this).attr('checked',"checked");
        	   }
           })
		   $(this).parent().parent().parent().find("input[name='itemCategory']").each(function(){
        	   if($(this).val() ==itemCategory){
                   $(this).attr('checked',"checked");
        	   }
           })
		   if(i==9){
			   $("input[name='roadConditionCategory']").each(function(){
	        	   if($(this).val() ==roadConditionCategory){
	                   $(this).attr('checked',"checked");
	        	   }
	           })
	           $("input[name='distanceCategory']").each(function(){
	        	   if($(this).val() ==distanceCategory){
	                   $(this).attr('checked',"checked");
	        	   }
	           })
			 }
           
        }
        	
   	});   
	   
	$("#_createType li").each(function(){
		$(this).attr("style","width:50%;white-space: nowrap;text-align:left;display: list-item;list-style: none;");
	})
	$("#_gender li").each(function(){
		$(this).attr("style","width:25%;white-space: nowrap;text-align:left;text-indent:0px; list-item;list-style: none;");
	}) 
	$("#_position li").each(function(){
		$(this).attr("style","width:11%;white-space: nowrap;text-align:left;");
	}) 
	$("#_appealHumanType li").each(function(){
		$(this).attr("style","width:100%;white-space: nowrap;text-align:left;text-indent:0px;");
	}) 
	$("#_buildType li").each(function(){
		$(this).attr("style","width:20%;white-space: nowrap;text-align:left;text-indent:0px;");
	})
	$("ul").each(function(){
		$(this).attr("style","margin-left: 0; padding-left: 0;");
	})


	   
})
</script>

