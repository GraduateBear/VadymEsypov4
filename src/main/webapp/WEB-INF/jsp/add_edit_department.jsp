<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">

    <tr>

        <td>
            <%--@elvariable id="department" type="com.aimprosoft.yesipov.demo.domain.Department"--%>
            <form:form method="POST" action="/departments/addDepartment" modelAttribute="department">
                <form:input path="originalName" pattern="^[A-ZА-Я][a-zа-яё\s]+"/>

                <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>"/>
                <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
                <c:if test="${not empty errorMessage and empty exception and empty code}">
                    <h3>Error message: ${errorMessage.getFieldError("originalName").getDefaultMessage()}</h3>
                </c:if>
                <c:if test="${not empty error}">
                    <h3>Error message: ${error}</h3>
                </c:if>
            </form:form>
        </td>

        <%--<td class="content center">
            <form class="login_form" action="/departments/addDepartment" method="post">
                <input type="text" value="${add_name}" name="departmentName" required pattern="^[A-ZА-Я][a-zа-яё\s]+" placeholder="<fmt:message key="index.jsp.placeholder.departmentName"/>"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.submit.add"/>"/>
            </form>
        </td>--%>

        <td class="content center">
            <form class="login_form" action="/departments/editDepartment" method="post">
                <input type="number" value="${edit_ID}" required min="1"
                       name="id" placeholder="<fmt:message key="index.jsp.placeholder.chooseId"/>" /></p>
                <%--<input type="number" value="${new_edit_ID}" min="1" name="newId"
                       placeholder="<fmt:message key="index.jsp.placeholder.newId"/>" /></p>--%>
                <input type="text" value="${edit_name}" required name="departmentName" pattern="^[A-ZА-Я][a-zа-яё\s]+" minlength="3" placeholder="<fmt:message key="index.jsp.placeholder.departmentName"/>" /></p>
                <input type="submit" value="<fmt:message key="index.jsp.submit.edit"/>"/>
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
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage.getFieldError("originalName").getDefaultMessage()}</h3>
            </c:if>
        </td>
    </tr>--%>

</table>

</body>

</html>