<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="nav.jsp"%>

<h2><spring:message code="crudexample.title" /></h2>

<br/>
<table>
  <tr>
   <th>item ID</th>
   <th>name</th>
   <th>description</th>
  </tr>
  <c:forEach var="item" items="${items}">
      <tr>
        <td><c:out value="${item.id}"/></td>
        <td><c:out value="${item.name}" /></td>
        <td><c:out value="${item.description}" /></td>
        <td><a href="edit.form?uuid=${item.uuid}&id=${item.id}">edit</a></td>
        <td><a href="delete.form?uuid=${item.uuid}&id=${item.id}">delete</a></td>
      </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>
