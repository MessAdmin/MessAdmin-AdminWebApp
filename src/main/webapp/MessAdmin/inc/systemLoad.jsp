<%@page session="false" %><%
%><%@page import="clime.messadmin.utils.JMX"%><%
%><%@taglib prefix="core" uri="http://messadmin.sf.net/core" %><%
%><%@taglib prefix="format" uri="http://messadmin.sf.net/fmt" %><%
%><format:setBundle basename="clime.messadmin.admin.i18n.alerts"/><%
%><%
	if (JMX.getSystemLoadAverage() > 1.1 * Runtime.getRuntime().availableProcessors()) {//FIXME magic number
%>
	<div style="font-size: larger; font-weight: bolder; color: red;">
		<format:message key="alert.systemLoad">
			<format:param value="<%= JMX.getSystemLoadAverage() %>"/>
			<format:param value="<%= Runtime.getRuntime().availableProcessors() %>"/>
		</format:message>
	</div>
<% } %>