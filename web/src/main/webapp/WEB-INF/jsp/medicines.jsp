<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h3>Список лекарств:</h3>
    <ol>
        <c:forEach items="${requestScope.medicines}" var="medicine">
            <li>${medicine.name}</li>
        </c:forEach>
    </ol>
</div>
</body>
</html>
