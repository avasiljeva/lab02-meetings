<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Meetings Portal</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
	</head>
	<body>
		<script language="javascript" src="/meetings/meetings_gwt/meetings_gwt.nocache.js"></script>
		<div id="menu">
		        <p style="text-align: center; font-size: 13pt;"><b>Meetings Portal</b></p>
		        <hr/>
		        <c:if test="${!empty sessionScope['user']}">
		        	<p>Welcome, <b><c:out value="${sessionScope['user'].username}"></c:out></b></p>
		        	<hr/>
		        </c:if>
		        
				<a href="/meetings/meetings/home">Home</a>
				<hr/>

				<c:if test="${!empty sessionScope['user']}">					
					<a href="/meetings/meetings/users">Users</a>
					<br/><hr/>
					<a href="/meetings/meetings/venues">Venues</a>
					<br/><hr/>
					<a href="/meetings/meetings/logout">Logout</a>
				</c:if>
				
		</div>
		<div id="content">
