<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<iframe id="meetingFrame" src="#" width="100%" height="100%"/>
<script>
	$(function(){
		var data = {};
	    var form = $("#tempInput").prevAll('input');
	    var url = '';
	    for (var i=0; i<form.length; i++) {
	        data[$(form[i]).attr('id')] = $(form[i]).val();
	    }
		data.code = $('#code').val();
		data.key = encodeURIComponent($('#key').val());
		url = 'http:\//115.236.101.203:8898/?r=/api/invitetomeet&callback=?';
	    $.getJSON(url, data, function(json) {
			if (json.code) {
				$.messageBox({ message:json.msg,level:'error'});
			} else if (json.url) {
				$("#meetingFrame").attr("src",json.url);
			} 
	    });

	})
</script>