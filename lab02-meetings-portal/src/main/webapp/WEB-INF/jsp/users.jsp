<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/>
<c:choose>
	<c:when test="${!empty users}">
	
		<!-- Render users page -->
		<div id="users" align="center">

			<h1>Portal Users</h1>
			<br />
	
			<table class="default_table" align="center">
				<thead>
					<tr>
						<th>Username</th>
						<th>Full Name</th>		
						<th>Friend</th>			
					</tr>
				</thead>
				
				<!-- Iterate through the list of users, generate table rows -->
				<c:forEach var="user" items="${users}">
					<tr>
						<td width="100px"><c:out value="${user.username}" /></td>
						<td width="250px"><c:out value="${user.fullName}" /></td>
						
						<td width="100px">
							<div align="center">
								<c:choose>
									<c:when test="${not user.friend}">
										<form action="/meetings/meetings/addFriend" method="POST">
											<input type="hidden" name="id" value="<c:out value="${user.id}" />">
											<input type="submit" value="Add Friend"/>
										</form>
									</c:when>
									<c:otherwise>
										yes
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br /><br />
		</div>
	</c:when>
	<c:otherwise>
		<h2>There are no users at the portal</h2>	
	</c:otherwise>
</c:choose>

<!-- Link back to home page -->
<p align="center">
<a href="/meetings/meetings/home">
	<b>Back to Home Page</b>
</a></p>

<%@ include file="/footer.jsp" %>