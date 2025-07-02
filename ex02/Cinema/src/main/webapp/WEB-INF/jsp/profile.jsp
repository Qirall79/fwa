<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Greeting</title></head>
<body>
    <h1>Hello, ${name}</h1>


    <c:forEach items="${authentications}" var="item">
        ${item} <br />
    </c:forEach>
    
    <c:forEach items="${avatars}" var="item">
        ${item} <br />
    </c:forEach>

    <form method="post" action="/cinema/images" enctype="multipart/form-data">
        <input type="file" name="avatar"/>
        <button type="submit">Upload</button>
    </form>
</body>
</html>
