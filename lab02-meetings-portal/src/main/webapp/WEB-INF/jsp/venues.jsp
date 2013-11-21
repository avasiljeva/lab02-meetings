<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/>
<c:choose>
	<c:when test="${!empty venues}">
	
		<!-- Render venues page -->
		<div id="venues" align="center">

			<h1>Venues</h1>
			<br />
	
			<table class="default_table" align="center">
				<thead>
					<tr>
						<th>Category</th>
						<th>Name</th>
						<th>Location</th>					
					</tr>
				</thead>
				
				<!-- Iterate through the list of venues, generate table rows -->
				<c:forEach var="venue" items="${venues}">
					<tr>
						<td width="100px" style="text-align: center;"><c:out value="${venue.category.name}" /></td>
						<td width="250px"><a href="${venue.foursquareUrl}" target="_blank"><c:out value="${venue.name}" /></a></td>
						<td width="300px"><c:out value="${venue.location}" /></td>	
					</tr>
				</c:forEach>
			</table>
			<br /><br />
		</div>
	</c:when>
	<c:otherwise>
		<h2>There are no venues at the portal</h2>	
	</c:otherwise>
</c:choose>

<!-- Link back to home page -->
<p align="center">
<a href="/meetings/meetings/home">
	<b>Back to Home Page</b>
</a></p>

<%@ include file="/footer.jsp" %>