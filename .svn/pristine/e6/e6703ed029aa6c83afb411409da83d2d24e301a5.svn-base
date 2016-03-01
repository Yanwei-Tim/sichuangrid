<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<iframe id="meetingFrame" src="#" width="100%" height="100%"/>
<script>
	$(function(){
		var url = 'http:\//115.236.101.203:8898/?r=/api/invitemeet&callback=?';
		var data = {};
		data.u = $('#currentUserAcc').val();
		data.user = $('#conventionersAcc').val();
		data.type = $('#MeetingType').val();
		data.code = $('#ppMeetCode').val();
		data.key = encodeURIComponent($('#ppMeetKey').val());
	    $.getJSON(url, data, function(json) {
	    	sendMessage();
			if (json.url) {
				$("#meetingFrame").attr("src",json.url);
			}
	    });

	})
</script>