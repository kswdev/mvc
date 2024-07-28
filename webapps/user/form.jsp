<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form</title>
</head>
<body>
    <form method="post" action="/users">
        <div>
            <label for="userId">사용자 아이디</label>
            <input class="form-control" id="userId" name="userId" placeholder="User Id">
        </div>
        <div>
            <label for="name">사용자 이</label>
            <input class="form-control" id="name" name="name" placeholder="User Name">
        </div>
        <button type="submit">회원가입</button>
    </form>
</body>
</html>