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
                    <th> <fmt:message key="index.jsp.departmentTable.id"/> </th>
                    <th> <fmt:message key="index.jsp.departmentTable.name"/> </th>
                </tr>
                <c:forEach var="department" items="${departmentList}">
                    <tr>
                        <td>
                            <c:out value="${department.getId()}"/>
                        </td>
                        <td>
                            <c:out value="${department.getOriginalName()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

        <td class="content center">
            <form class="login_form" method="get" action="/departments/addEdit">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.addEditDepartment"/>"/>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" action="/departments/remove" method="post">
                <input type="number" value="${remove_ID}" min="1" max="${departmentList.get(departmentList.size() - 1).getId()}"
                       required name="id" placeholder="<fmt:message key="index.jsp.placeholder.chooseId"/>"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.submit.remove"/>"/>
            </form>
        </td>

        <td class="content center">
            <form class="login_form" action="/employees/filteredList" method="post">
                <input type="number" name="id" value="${ID}" min="1" max="${departmentList.get(departmentList.size() - 1).getId()}"
                       required placeholder="<fmt:message key="index.jsp.placeholder.chooseId"/>"/></p>
                <input type="submit" value="<fmt:message key="index.jsp.submit.list_of_employees"/>"/>
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
<script src="../../js/script.js"></script>
</body>
</html>