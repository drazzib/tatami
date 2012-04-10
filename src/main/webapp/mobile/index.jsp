<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Tatami Mobile</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="jqm/jquery.mobile-1.1.0-rc.2.min.css" />
<script src="jquery/jquery-1.7.1.min.js"></script>
<script src="jqm/jquery.mobile-1.1.0-rc.2.min.js"></script>
<script src="../assets/js/tatami.js"></script>
<script src="tatami-mobile.js"></script>
<script type="text/javascript">
	$.ajaxPrefilter(function(options) {
		if (!options.url.match(/tatami/)) {
		    options.url = "/tatami/" + options.url;
		    options.crossDomain = false;
		}
	});
</script>
</head>
<body>

	<div id="page_login" data-role="page">
		<div data-role="header">
			<h1>Authentication</h1>
		</div>
		<div data-role="content">
			<span id="loginError" class="error">Your credentials are
				invalid. Please try again.</span>
			<form id="signInForm" data-ajax="false">
				<div data-role="fieldcontain">
					<label for="login">Login : </label> <input id="login"
						name="j_username" type="text" size="25" autocorrect="off"
						autocapitalize="off" />
				</div>
				<div data-role="fieldcontain">
					<label for="password">Password :</label> <input id="password"
						name="j_password" type="password" size="25" />
				</div>
				<div id="submitDiv" data-role="fieldcontain">
					<input type="submit" value="login" />
				</div>
			</form>
		</div>
	</div>
	
	<div id="page_profile" data-role="page">
		<div data-role="header">
			<h1>Profile</h1>
		</div>
		<div data-role="content">
			<p>
				<strong><span id="firstName"></span> <span id="lastName"></span></strong>
				<img id="picture" src="" />
				<a id="buttonSignOut" href="#" data-role="button" data-inline="true">Sign Out</a>
			</p>
		</div>
		<div data-role="footer" data-id="footer1" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="#page_timeline" data-icon="grid">Timeline <span class="ui-li-count" id="tweetCount">X</span></a></li>
					<li><a href="#page_friends" data-icon="star">Friends <span class="ui-li-count" id="friendsCount">X</span></a></li>
					<li><a href="#page_followers" data-icon="info">Followers <span class="ui-li-count" id="followersCount">X</span></a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="page_timeline" data-role="page">
		<div data-role="header">
			<h1>Timeline</h1>
		</div>
		<div data-role="content">
			<div id="tweetsList">
			</div>
		</div>
		<div data-role="footer" data-id="footer1" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="#page_timeline" data-icon="grid" class="ui-btn-active ui-state-persist">Timeline</a></li>
					<li><a href="#page_friends" data-icon="star">Friends</a></li>
					<li><a href="#page_followers" data-icon="info">Followers</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div id="page_friends" data-role="page">
		<div data-role="header">
			<h1>Friends</h1>
		</div>
		<div data-role="content">
			<div id="friendsList">
			</div>
		</div>
		<div data-role="footer" data-id="footer1" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="#page_timeline" data-icon="grid">Timeline</a></li>
					<li><a href="#page_friends" data-icon="star" class="ui-btn-active ui-state-persist">Friends</a></li>
					<li><a href="#page_followers" data-icon="info">Followers</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="page_followers" data-role="page">
		<div data-role="header">
			<h1>Followers</h1>
		</div>
		<div data-role="content">
			<div id="followersList">
			</div>
		</div>
		<div data-role="footer" data-id="footer1" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="#page_timeline" data-icon="grid">Timeline</a></li>
					<li><a href="#page_friends" data-icon="star">Friends</a></li>
					<li><a href="#page_followers" data-icon="info" class="ui-btn-active ui-state-persist">Followers</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div id="error" data-role="page">
		<div data-role="header">
			<h1>Error</h1>
		</div>
		<div data-role="content">Click back to return to the previous page.</div>
	</div>
</body>
</html>
