<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--Форма для отображения платежей.
Из нее нак же можно вызвать форму создания нового платежа--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

    <a href="${ctx}/payment">
        <span class="glyphicon glyphicon-arrow-left">
        </span>Назад
    </a>

<table id="hor-minimalist-b">
    <thead>
    <caption>Последние платежи</caption>
        <tr>
            <th>Дата</th>
            <th>№ платежа</th>
            <th>Клиент</th>
            <th>№ карты</th>
            <th>Получатель</th>
            <th>Тип платежа</th>
            <th>Статус платежа</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${last_payments}" var="payment">
        <tr>
            <td><c:out value='${payment.date}'/></td>
            <td><c:out value='${payment.identifier}'/></td>
            <%--TODO check it!--%>
            <td><c:out value='${payment.client.fullName}'/></td>
            <td><c:out value='${payment.bankCard.hidenCode}'/></td>
            <td><c:out value='${payment.merchant.fullName}'/></td>
            <td><c:out value='${payment.paymentType}'/></td>
            <td><c:out value='${payment.paymentState}'/></td>
            <td><a href="${ctx}/orderbook/<c:out value='${item.identifier}'/>">orders</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>