<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="lib-content.jsp" %>


<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/medicineComplex" method="get">
    Введите название лекарства <input type="text" name="partName" value="${requestScope.partName}"><br>
    Введите описание лекарства <input type="text" name="partDescription" value="${requestScope.partDescription}"><br>
    Необходимость рецепта <input type="radio" name="needPrescription" value="${true}"> Да
    <input type="radio" checked name="needPrescription" value="${false}"> Нет <br>

    Сколько лекарств отображать на странице?
    <input type="number" name="limit" value="20" min="1"/> <br>

    <input type="hidden" name="page" value="1"/><br>

    <input type="submit" value="Найти">
</form>

</body>
</html>
