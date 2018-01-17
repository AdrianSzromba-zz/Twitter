<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%@ include file = "jspf/head_config.jspf" %>

<title>Warsztat6 home</title>
</head>
<body>
<%@ include file = "jspf/header.jspf" %>
<%@ include file = "jspf/main_menu.jspf" %>

<h3>Messages you received:</h3>

<h4>Unread messages:</h4>
<c:forEach items="${unreadMessages}" var="message">
<c:set var = "full" value = "${message.text}"></c:set>
<c:set var = "part" value = "${fn:substring(full, 0, 30) }"></c:set>
<p>${part }... <br /> message from: ${message.sender.username}, sent: ${message.created} <a href="message/details/${message.id}">read message</a></p>
</c:forEach>

<h4>Read messages:</h4>
<c:forEach items="${readMessages}" var="message">
<c:set var = "full" value = "${message.text}"></c:set>
<c:set var = "part" value = "${fn:substring(full, 0, 30) }"></c:set>
<p>${part }... <br /> message from: ${message.sender.username}, sent: ${message.created} <a href="message/details/${message.id}">read message</a></p>
</c:forEach>

<h3>Messages you sent:</h3>

<c:forEach items="${sentMessages}" var="message">
<c:set var = "full" value = "${message.text}"></c:set>
<c:set var = "part" value = "${fn:substring(full, 0, 30) }"></c:set>
<p>${part }... <br /> message to: ${message.reciever.username}, sent: ${message.created} <a href="message/details/${message.id}">read message</a></p>
</c:forEach>

<%@ include file = "jspf/footer.jspf" %>
</body>
</html>