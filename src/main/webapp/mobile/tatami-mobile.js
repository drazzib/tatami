var login = "";

$(document).bind("pageinit", function() {
	$('#buttonSignOut').live("click", function() {
		//$.mobile.pageLoading();
		$.ajax({
			url : '/tatami/logout',
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				$.mobile.changePage($('#page_login'));
			}
		});
		return false;
	});
	$('#signInForm').live("submit", function() {
		//$.mobile.pageLoading();
		login = $("#login").val();
		$.ajax({
			type : 'POST',
			url : '/tatami/authentication',
			dataType : 'json',
			data : $('#signInForm').serialize(),
			success : function(data, textStatus, jqXHR) {
				if (!data.success){
					$('#loginError').show();
				} else {
					$.mobile.changePage($('#page_profile'));
					
				}
			}
		});
		return false;
	});

	$('#page_login').live('pagebeforeshow', function(event, ui) {
		$('#loginError').hide();
	});
	
	$('#page_profile').live('pagebeforeshow', function(event, ui) {
		$.mobile.showPageLoadingMsg();
		refreshProfile();
		$.mobile.hidePageLoadingMsg();
	});
	
	$('#page_timeline').live('pagebeforeshow', function(event, ui) {
		$.mobile.showPageLoadingMsg();
		listTweets();
		$.mobile.hidePageLoadingMsg();
	});
	
	$('#page_friends').live('pagebeforeshow', function(event, ui) {
		$.mobile.showPageLoadingMsg();
		$.ajax({
			type: 'GET',
			url: "rest/friends",
			dataType: "json",
			success: function(data) {
				makeUsersList(data, $('#friendsList'));
				$.mobile.hidePageLoadingMsg();
			}
		});
	});
	
	$('#page_followers').live('pagebeforeshow', function(event, ui) {
		$.mobile.showPageLoadingMsg();
		$.ajax({
			type: 'GET',
			url: "rest/followers",
			dataType: "json",
			success: function(data) {
				makeUsersList(data, $('#followersList'));
				$.mobile.hidePageLoadingMsg();
			}
		});
	});
});