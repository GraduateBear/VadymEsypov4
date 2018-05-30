<%@ include file="../jspf/directive/page.jspf" %>
<%@ include file="../jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="../jspf/head.jspf" %>

<body>

<table id="main-container">
    <tr>
        <td class="content center">

            <form class="login_form" method="get" action="/departments">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.departments"/>"/>
            </form>

            <form class="login_form" method="get" action="/employees/findAll">
                <input type="submit" value="<fmt:message key="index.jsp.placeholder.employees"/>">
            </form>

        </td>
    </tr>

</table>

</body>

</html>
