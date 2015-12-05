<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--Форма создания нового платежа--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div align="center">
    <form:form id="paymentForm" method="post" modelAttribute="createPaymentCommand"> <%--action="new"--%>
        <%--todo CHECK--%>
        <c:if test="${not empty message}">
            <div id="message" class="${message.type}">${message.message}</div>
         </c:if>
        <table border="0">
            <tr>
                <td colspan="2" align="center"><h2>Форма регистрации платежа</h2></td>
            </tr>
            <tr>
                <td>Тип карты:</td>
                <td><form:input path="paymentSystemType" /></td>
            </tr>
            <tr>
                <td>К оплате:</td>
                <td><form:input path="amount" /></td>
            </tr>
            <%--TODO доступные типы валют нужно вытаскивать из базы
            используя такой селект
                <tr>
                    <td>Profession:</td>
                    <td><form:select path="profession" items="${professionList}" /></td>
                </tr>--%>
            <tr>
                <td>Валюта:</td>
                <td><form:input path="currencyType" maxlength="3" /></td>
            </tr>
            <tr>
                <td>Имя держателя карты:</td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr>
                <td>Фамилия держателя карты:</td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td>Код карты:</td>
                <td><form:input path="code" /></td>
            </tr>
            <tr>
                <td>Дата окончания срока действия карты (MM/YY):</td>
                <td><form:input path="expiredDate" /></td>
            </tr>
            <tr>
                <td>Код безопасности:</td>
                <td><form:input path="ccvCode" maxlength="4"/></td>
            </tr>
            <tr>
                <td>Тип платежа:</td>
                <td>
                    <form:select path="paymentType">
                    <!-- Characters edition-->
                        <form:option value="-" label="--Выбирете из списка"/>
                        <c:forEach var="item" items="${PaymentType.values}"><%--TODO проверить!--%>
                            <form:option value="{item.value}"><spring:eval expression="item"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>БИК банка получателя:</td>
                <td><form:input path="merchantBankBIK" /></td>
            </tr>
            <tr>
                <td>Номер счета в банке-получателе:</td>
                <td><form:input path="merchantBankAccount" /></td>
            </tr>
            <tr>
                <td>Получатель:</td>
                <td><form:input path="merchant" /></td>
            </tr>
            <tr>
                <td>ИНН:</td>
                <td><form:input path="merchantINN" /></td>
            </tr>
                <%--<form:select path="cardType">
                           <!-- Characters edition-->
                           <c:forEach var="item" items="${Car.values}">
                               <form:option value="{item.value}"><spring:eval expression="item"/></form:option>
                           </c:forEach>
                       </form:select>--%>
                <%--<tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>--%>
            <tr>
                <td colspan="1" align="center"><button type="reset">Отмена</button></td>
                <td colspan="1" align="center"><button type="submit">Выполнить</button></td>
            </tr>
        </table>
    </form:form>
</div>

