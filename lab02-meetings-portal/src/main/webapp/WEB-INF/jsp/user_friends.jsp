<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/>
<c:choose>
	<c:when test="${!empty friends}">

		<div id="users" align="center">
	
			<table class="default_table" align="center">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>		
						<th>Remove</th>			
					</tr>
				</thead>
				
				<!-- Iterate through the list of users, generate table rows -->
				<c:forEach var="friend" items="${friends}">
					<tr>
						<td width="200px"><c:out value="${friend.firstName}" /></td>
						<td width="200px"><c:out value="${friend.lastName}" /></td>
						
						<td width="100px">
							<div align="center">
								<form action="/meetings/meetings/removeFriend" method="POST">
									<input type="hidden" name="id" value="<c:out value="${friend.id}" />">
									<input type="submit" value="Remove Friend"/>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:otherwise>
		<h2>No friends</h2>	
	</c:otherwise>
</c:choose>

<%@ include file="/footer.jsp" %>