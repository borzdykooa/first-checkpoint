<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>

<html>
<head>
    <title>Title</title>

</head>
<body>

<div>
    <div>
        <h3>Список лекарств:</h3>
        <ol>
            <c:forEach items="${requestScope.medicines}" var="medicine">
                <a href="${pageContext.request.contextPath}/medicineFullInfo?id=${medicine.id}">
                    <li>${medicine.name}</li>
                </a><br>
            </c:forEach>
        </ol>
    </div>
</div>

    <div class="pagination">
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=${sessionScope.page-1}"
           class="prev">предыдущая</a>
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=1">1</a>
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=2">2</a>
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=3">3</a>
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=4">4</a>
        <a href="${pageContext.request.contextPath}/medicineComplex?partName=${sessionScope.partName}&partDescription=${sessionScope.partDescription}&needPrescription=${sessionScope.needPrescription}&limit=${sessionScope.limit}&page=${sessionScope.page+1}"
           class="next">следующая</a>
    </div>

</body>
</html>
