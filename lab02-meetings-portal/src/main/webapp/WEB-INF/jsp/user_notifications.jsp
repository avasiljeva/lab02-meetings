<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/>
<c:choose>
	<c:when test="${!empty notifications}">

		<div id="notifications" align="center">
	
			<table class="default_table" align="center">
				<thead>
					<tr>
						<th>Time</th>
						<th>Notification</th>		
					</tr>
				</thead>
				
				<!-- Iterate through the list of users, generate table rows -->
				<c:forEach var="notification" items="${notifications}">
					<tr>
						<td width="150px" align="center">
							<c:out value="${notification.createDate}" />
						</td>
						<td width="500px">
						
							<c:choose>
								<c:when test="${notification.class.simpleName == 'FriendshipNotification'}">
									<!-- Friendship notification -->
									<b><c:out value="${notification.friend.fullName}" /></b> added you as a friend
								</c:when>
								<c:otherwise>
									Unknown notification type: <c:out value="${notification.class.simpleName}" />
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:otherwise>
		<h2>No notifications</h2>	
	</c:otherwise>
</c:choose>

<%@ include file="/footer.jsp" %>