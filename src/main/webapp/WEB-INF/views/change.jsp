<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@ include file = "jspf/head_config.jspf" %>

<title>Warsztat 6</title>
</head>
<body>
<%@ include file = "jspf/header.jspf" %>
<%@ include file = "jspf/main_menu.jspf" %>

	<c:choose>

		<c:when test="${sessionScope.user == null}">
		You need to be logged in.
	</c:when>

		<c:otherwise>
			<form:form method="post" modelAttribute="user">
				<p>User Name<form:input path="username" /><form:errors path="username"></form:errors></p>
				<p>Email<form:input path="email" /><form:errors path="email"></form:errors></p>
				<p>Password<form:password path="password" /><form:errors path="password"></form:errors></p>
			<p><input type="submit" /></p>
			</form:form>
			<a href="/warsztat6/delete">Delete your account</a></p>
		</c:otherwise>

	</c:choose>


<%@ include file = "jspf/footer.jspf" %>
</body>
</html>