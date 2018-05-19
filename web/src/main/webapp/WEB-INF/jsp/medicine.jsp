<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${requestScope.medicine.name}</h3>

<p>Описание препарата: ${requestScope.medicine.description}</p>
<p>Цена препарата: ${requestScope.medicine.saleInfo.price} бел.руб.</p>
<p>Количество упаковок в наличии: ${requestScope.medicine.saleInfo.quantity}</p>
<p>Лекарственная группа: ${requestScope.medicine.pharmacyGroup.name}</p>

</body>
</html>
