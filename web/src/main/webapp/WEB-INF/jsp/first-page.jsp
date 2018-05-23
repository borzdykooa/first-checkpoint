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
    <input type="radio" name="needPrescription" value="${false}"> Нет <br>
    Группа лекарств:<br>
    <select name="groupId">
        <c:forEach var="group" items="${requestScope.groups}">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select><br>
    Сколько лекарств отображать на странице? <input type="text" name="limit" value="${requestScope.limit}"><br>


    <input type="hidden" name="page" value="1"/><br>

    <input type="submit" value="Найти">
</form>

</body>
</html>
