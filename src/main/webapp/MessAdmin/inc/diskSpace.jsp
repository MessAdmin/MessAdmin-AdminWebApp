<%@page session="false" %><%
%><%@page import="clime.messadmin.core.Constants"%><%
%><%@page import="clime.messadmin.i18n.I18NSupport"%><%
%><%@page import="clime.messadmin.utils.BytesFormat"%><%
%><%@page import="clime.messadmin.utils.Files"%><%
%><%@page import="java.io.File"%><%
%><%@taglib prefix="core" uri="http://messadmin.sf.net/core" %><%
%><%@taglib prefix="format" uri="http://messadmin.sf.net/fmt" %><%
%><format:setBundle basename="clime.messadmin.admin.i18n.alerts"/><%
%><%!
	private static boolean isFreeSpaceWarningLevel(File fs) {
		long space = Files.getUsableSpaceForFile(fs);
		double percent = Files.getUsableSpacePercentForFile(fs);
		return space > 0 && percent >= 0
				&& (space < 1*1024*1024*1024 || percent < 0.02);//FIXME magic numbers
	}
%><%
	File tmpDir = new File(System.getProperty("java.io.tmpdir"));
	if (isFreeSpaceWarningLevel(tmpDir)) {
		BytesFormat format = BytesFormat.getBytesInstance(I18NSupport.getAdminLocale(), true);
%>
	<div style="font-size: larger; font-weight: bolder; color: red;">
		<format:message key="alert.diskSpace">
			<format:param value="<%= tmpDir.getAbsolutePath() %>"/>
			<format:param value="<%= format.format(Files.getUsableSpaceForFile(tmpDir)) %>"/>
		</format:message>
	</div>
<% } %><%
	File servletTmpDir = (File)application.getAttribute(Constants.TEMP_DIR);
	if (!tmpDir.equals(servletTmpDir) && isFreeSpaceWarningLevel(servletTmpDir)) {
		BytesFormat format = BytesFormat.getBytesInstance(I18NSupport.getAdminLocale(), true);
%>
	<div style="font-size: larger; font-weight: bolder; color: red;">
		<format:message key="alert.diskSpace">
			<format:param value="<%= servletTmpDir.getAbsolutePath() %>"/>
			<format:param value="<%= format.format(Files.getUsableSpaceForFile(servletTmpDir)) %>"/>
		</format:message>
	</div>
<% } %>