<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>

        <td class="content center">
            <%--@elvariable id="employee" type="com.aimprosoft.yesipov.demo.domain.Employee"--%>
            <form:form method="post" action="/employees/addEmployee" modelAttribute="employee" onclick="validateBirthday()">
                <form:input path="firstName" pattern="^[A-ZА-Я][a-zа-яё]+"/>
                <form:input path="lastName" pattern="^[A-ZА-Я][a-zа-яё]+"/>
                <form:input path="birthday" type="date"/>
                <form:input path="email" />
                <form:input path="job" pattern="^[A-ZА-Я][a-zа-яё\s]+"/>
                <form:input path="department.id" type="number" min="1"/>
                <form:input path="salary" type="number" step="0.01"/>

                <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>" />

                <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
                <c:if test="${not empty errorMessage and empty exception and empty code}">
                    <h3>Error message: ${errorMessage.getFieldError("email").getDefaultMessage()}</h3>
                </c:if>
                <c:if test="${not empty error}">
                    <h3>Error message: ${error}</h3>
                </c:if>
            </form:form>
            <%--<form class="login_form" action="/employees/addEmployee" method="post" onclick="validateBirthday()">
                <fieldset>
                    <input type="text" value="${first_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="firstName" placeholder="<fmt:message key="index.jsp.placeholder.firstName"/>"/></p>
                    <input type="text" value="${last_name}" required pattern="^[A-ZА-Я][a-zа-яё]+" name="lastName" placeholder="<fmt:message key="index.jsp.placeholder.lastName"/>"/></p>
                    <input type="date" value="${birth}" required name="birthday"/></p>
                    <input type="text" value="${mail}" required name="email" pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}" placeholder="<fmt:message key="index.jsp.placeholder.email"/>"/></p>
                    <input type="text" value="${job}" required pattern="^[A-ZА-Я][a-zа-яё\s]+" name="position" placeholder="<fmt:message key="index.jsp.placeholder.position"/>"/></p>
                    <input type="number" value="${department_id}" min="1" required name="departmentId" placeholder="<fmt:message key="index.jsp.placeholder.newDepartmentID"/>"/></p>
                    <input type="number" value="${wage}" min="100" step="10" max="5000" required name="salary" placeholder="<fmt:message key="index.jsp.placeholder.salary"/>"/></p>
                    <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>" />
                </fieldset>
            </form>--%>
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

    <%--<tr>
        <td class="content bottom" colspan="4">
            &lt;%&ndash; if get this page using forward &ndash;%&gt;
            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage}</h3>
            </c:if>
        </td>
    </tr>--%>

</table>

</body>

</html>