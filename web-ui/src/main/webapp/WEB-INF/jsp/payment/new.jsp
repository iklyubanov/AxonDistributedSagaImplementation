<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--Форма создания нового платежа--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div align="center">
    <form:form id="paymentForm" method="post" modelAttribute="payment"> <%--action="new"--%>
        <%--todo CHECK--%>
        <c:if test="${not empty message}">
            <div id="message" class="${message.type}">${message.message}</div>
         </c:if>
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Форма регистрации платежа</h2></td>
            </tr>
            <tr>
                <td>User Name:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Birthday (MM/YY):</td>
                <td><form:input path="birthDate" /></td>
            </tr>
            <tr>
                <td>Profession:</td>
                <td><form:select path="profession" items="${professionList}" /></td>
            </tr>
            <tr>
                <td colspan="1" align="center"><button type="reset">Отмена</button></td>
                <td colspan="1" align="center"><button type="submit">Выполнить</button></td>
            </tr>
        </table>
    </form:form>
</div>

