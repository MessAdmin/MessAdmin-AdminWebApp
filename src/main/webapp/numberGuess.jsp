<?xml version="1.0" encoding="ISO-8859-15"?>
<%@page session="true" contentType="text/html; charset=ISO-8859-15" %>
<%@page import="clime.messadmin.core.Constants" %>
<%--!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"--%>
<%--!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"--%>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"--%>
<%--!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
 "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"--%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-15" />
	<meta http-equiv="pragma" content="no-cache" /><!-- HTTP 1.0 -->
	<meta http-equiv="cache-control" content="no-cache,must-revalidate" /><!-- HTTP 1.1 -->
	<meta http-equiv="expires" content="0" /><!-- 0 is an invalid value and should be treated as 'now' -->
	<meta http-equiv="content-language" content="en" /><%-- fr_FR --%>
	<meta name="author" content="Cedrik LIME" />
	<meta name="copyright" content="copyright 2005--2015 Cedrik LIME" />
	<meta name="robots" content="noindex,nofollow,noarchive" />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=no;"/>
	<meta name="format-detection" content="telephone=no"/>
	<title>MessAdmin demo: number guess</title>
</head>
<body>
<h1>Choose a number between 1 and <%= application.getAttribute("maxNumber") %></h1>

<div>
	<%= request.getAttribute("message") %>
</div>

<% boolean isNewGame = session.getAttribute("numberToGuess") == null || session.getAttribute("nTries") == null; %>

<div style="display: <%= isNewGame ? "none" : "inline" %>">
	<form action="" method="post">
		<input type="text" id="number" name="number" />
		<input type="submit" />
	</form>
</div>

<div style="display: <%= isNewGame ? "inline" : "none" %>">
	<form action="" method="get">
		<input type="submit" value="New game" />
	</form>
</div>

<div style="font-size: smaller;">
<hr noshade="noshade" align="left" width="33%" />
My Session Id: <%= session.getId() %><br />
My (randomly-choosen) Locale: <%= session.getAttribute("org.apache.struts.action.LOCALE") %><br />
Global attribute present: <%= application.getAttribute(Constants.GLOBAL_MESSAGE_KEY) != null %><br />
Session attribute present: <%= application.getAttribute(Constants.SESSION_MESSAGE_KEY) != null %><br />
Current simulated workload: <%= request.getAttribute("workload") %> ms
</div>
</body>
</html>