<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>

        <td class="content center">
            <form class="login_form" action="/employees/editEmployee" method="post" onclick="validateBirthday()">
                <fieldset>
                    <input type="text" value="${first_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${last_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${birth}" name="birthday"/></p>
                    <input type="text" value="${mail}" name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${job}" pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${department_id}" min="1" name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${wage}" min="100" step="10" max="5000" name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="hidden" name="id" value="<c:out value="${ID}"/>">
                    <input type="submit" value="<fmt:message key="index.jsp.submit.edit"/>" />
                </fieldset>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" method="get" action="/employees/findAll">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.employees"/>">
            </form>
        </td>

        <td class="content center">
            <form class="login_form" method="get" action="/departments">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.departments"/>"/>
            </form>
        </td>

    </tr>

    <tr>
        <td class="content bottom" colspan="4">
            <%-- if get this page using forward --%>
            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage}</h3>
            </c:if>
        </td>
    </tr>

</table>

</body>

</html>