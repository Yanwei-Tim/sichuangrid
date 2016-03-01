<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="pieContainer" style="display:none;position:relative;top:-10px;border:1px;">
</div>

<script type="text/javascript">
$(document).ready(function() {
	showMemberPie();
});
function showMemberPie(){
	$("#pieContainer").css("display","block");
	chart = new Highcharts.Chart({
	      chart: {
	         renderTo: 'pieContainer',
	         plotBackgroundColor: null,
	         plotBorderWidth: null,
	         plotShadow: false
	      },
	      title: {
	    	    text: '基层组织队伍图表'
	    	 },
	    	 tooltip: {
			        formatter: function() {
			    		return '<b>'+ this.point.name +'</b>: '+ this.y ;
			        }
			    },
	    	 plotOptions: {
	    	      pie: {
	    	  		allowPointSelect: true,
	    	          cursor: 'pointer',
	    	          dataLabels: {
	    	          	enabled: true,
	    	              color: '#000000',
	    	              connectorColor: '#000000',
	    	              formatter: function() {
	    	  				return '<b>'+ this.point.name +'</b>: '+ this.y ;
	    	              }
	    	           }
	    	       }
	    	    },
	       series: [{
	         type: 'pie',
	         name: 'Browser share',
	         data: [
	          //   ['基层综治组织', window._rowData.compositeTeamCount],
	          //  ['基层党组织',   window._rowData.partyTeamCount],
	            ['基层自治组织', window._rowData.autonomyTeamCount],
	            ['群防群治队伍', window._rowData.massesTeamCount],
	            ['社会志愿者队伍', window._rowData.postulantTeamCount]
	         ]
	      }]
	   });

}



</script>
