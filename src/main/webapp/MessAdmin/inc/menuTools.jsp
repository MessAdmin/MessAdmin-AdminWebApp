<%@page session="false" %>
<%@page import="clime.messadmin.admin.AdminActionProvider"%>
<%@page import="clime.messadmin.providers.userdata.ThreadsDumper"%>
<%@page import="clime.messadmin.profiler.ProfilerAction"%>
<%@page import="clime.messadmin.admin.actions.Echo"%>
<%@page import="clime.messadmin.core.MessAdmin"%>
<%@taglib prefix="core" uri="http://messadmin.sf.net/core" %>
<%@taglib prefix="format" uri="http://messadmin.sf.net/fmt" %>
<format:setBundle basename="clime.messadmin.providers.userdata.ThreadsDumper"/>

<span style="float: right; padding-left: 10px; padding-right: 8px;">
<%-- thread dump & CPU profiler --%>
[
<a href="?<%=AdminActionProvider.ACTION_PARAMETER_NAME%>=<%=ThreadsDumper.ACTION_ID%>" target="_blank"><format:message key="thread_dump"/></a>
|
<a href="?<%=AdminActionProvider.ACTION_PARAMETER_NAME%>=<%=ProfilerAction.ACTION_ID%>" target="_blank"><format:message key="profiler"/></a>
]<a href="?<%=AdminActionProvider.ACTION_PARAMETER_NAME%>=<%=Echo.ID%>" target="_blank">&nbsp;</a>
<format:setBundle basename="clime.messadmin.admin.i18n.versionCheck"/>
<span id="updateNotification" style="display: none;">
<a href="http://messadmin.sourceforge.net" target="_blank"><format:message key="update.available"/></a>
</span>
</span>
<script type="text/javascript">//<![CDATA[
	var checkUpdateURL = 'http://versioncheck.messadmin.cedrik.fr/<core:out value="<%= MessAdmin.getVersion() %>"/>';
//]]>
</script>
