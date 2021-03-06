<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>
        <td class="content top">
            <table border='1' bordercolor="red">
                <tr>
                    <th> <fmt:message key="index.jsp.employeeTable.id"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.firstName"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.lastName"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.birthday"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.email"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.position"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.department"/> </th>
                    <th> <fmt:message key="index.jsp.employeeTable.salary"/> </th>
                </tr>

                <c:forEach var="employee" items="${employeeList}">
                    <tr>
                        <form class="login_form" method="post" action="/employees/editEmployeePage">
                            <td>
                                <input type="hidden" name="id" value="<c:out value="${employee.getId()}"/>">
                                <c:out value="${employee.getId()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="firstName" value="<c:out value="${employee.getFirstName()}"/>">
                                <c:out value="${employee.getFirstName()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="lastName" value="<c:out value="${employee.getLastName()}"/>">
                                <c:out value="${employee.getLastName()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="birthday" value="<c:out value="${employee.getBirthday()}"/>">
                                <c:out value="${employee.getBirthday()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="email" value="<c:out value="${employee.getEmail()}"/>">
                                <c:out value="${employee.getEmail()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="position" value="<c:out value="${employee.getJob()}"/>">
                                <c:out value="${employee.getJob()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="departmentName" value="<c:out value="${employee.getDepartment().getOriginalName()}"/>">
                                <c:out value="${employee.getDepartment().getOriginalName()}"/>
                            </td>
                            <td>
                                <input type="hidden" name="salary" value="<c:out value="${employee.getSalary()}"/>">
                                <c:out value="${employee.getSalary()}"/>
                            </td>
                            <td>
                                <input type="submit" value="<fmt:message key="index.jsp.placeholder.editEmployee"/>">
                            </td>
                        </form>
                    </tr>
                </c:forEach>

            </table>
        </td>

        <td class="content top">

            <form class="login_form" method="get" action="/employees/addEmployeePage">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.addEmployee"/>">
            </form>

            <form class="login_form" method="get" action="/departments">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.departments"/>"/>
            </form>

            <form class="login_form" action="/employees/removeEmployee" method="post">
                <fieldset>
                    <select name="email" required>
                        <c:forEach var="employee" items="${employeeList}">
                            <option value="<c:out value="${employee.getEmail()}"/>"> <c:out value="${employee.getEmail()}" /> </option>
                        </c:forEach>
                    </select>
                    <input type="submit" name="removeEmployee" value="<fmt:message key="index.jsp.submit.remove"/>"/>
                </fieldset>
            </form>
        </td>
    </tr>

    <tr>
        <td class="content bottom" colspan="2">
            <%-- if get this page using forward --%>
            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage}</h3>
            </c:if>
        </td>
    </tr>

</table>

</body>
</html>