<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="nav.jsp"%>

<h2><spring:message code="crudexample.title" /></h2>

<br/>
<form:form method="POST" modelAttribute="item" action="create.form">
    <table>
        <tr>
            <td><form:label path="name">name</form:label></td>
            <td>
                <form:input type="hidden" path="uuid" />
                <form:input type="hidden" path="id" />
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="description">description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><input type="submit"/></td>
        </tr>
    </table>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
