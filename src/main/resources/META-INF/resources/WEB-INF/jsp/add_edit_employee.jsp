<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>

        <td class="content center">
            <form class="login_form" name="myForm" action="/employees/addEmployee" method="post" onclick="validateBirthday()">
                <fieldset>
                    <input type="text" value="${add_first_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${add_last_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${add_birth}" required name="birthday"/></p>
                    <input type="text" value="${add_mail}" required name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${add_job}" required pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${add_department_id}" required min="1" name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${add_wage}" required min="100" step="10" max="5000" name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>"/>
                </fieldset>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" action="/employees/editEmployee" method="post" onclick="validateBirthday()">
                <fieldset>
                    <input type="number" value="${edit_ID}" min="1" required name="id" placeholder="<fmt:message key="index.jsp.placeholder.chooseId"/>"/></p>
                    <input type="text" value="${edit_first_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${edit_last_name}" pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${edit_birth}" name="birthday"/></p>
                    <input type="text" value="${edit_mail}" name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${edit_job}" pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${edit_department_id}" min="1" name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${edit_wage}" min="100" step="10" max="5000" name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="submit" name="editEmployee" value="<fmt:message key="index.jsp.submit.edit"/>" />
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
