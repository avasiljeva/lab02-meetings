<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/><br/>
	
<div id="home_header" align="center">	
	<c:choose>
	<c:when test="${!empty sessionScope['user']}">
		<h1><c:out value="${sessionScope['user'].firstName}"/>&nbsp<c:out value="${sessionScope['user'].lastName}"/></h1>		
		<br/>
		
		<table id="user_main_menu">
			<tr align="center">
				<td><a href="/meetings/meetings/home/main">Main</a></td>
				<td><a href="/meetings/meetings/home/friends">Friends</a></td>
				<td><a href="/meetings/meetings/home/notifications">Notifications</a></td>
				<td><a href="/meetings/meetings/home/meetings">Meetings</a></td>
			</tr>
		</table>
		<br />
		<br />

		<jsp:include page="user_${selected_tab}.jsp" />

	</c:when>
	<c:otherwise>
		<h1>Welcome to Meetings Portal!</h1>
		<br/>
		<br/>
		<br/>
		<br/>
		<p><b>Please 
			<a style="text-decoration: underline;" href="/meetings/meetings/login">login</a> or
			<a style="text-decoration: underline;" href="/meetings/meetings/register">register</a>
			to enter portal.</b></p>
		<br/><br/>
	</c:otherwise>
	</c:choose>
	<br/>
</div>	

<%@ include file="/footer.jsp" %>