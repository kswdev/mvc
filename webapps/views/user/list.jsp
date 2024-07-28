<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>홈</title>
</head>
<body>
    <h2>User List.jsp </h2>
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>userId</th>
                <th>name</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach item="${users}" var="user" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${user.userId}</td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>