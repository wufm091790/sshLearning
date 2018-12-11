<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
</head>
<body>
<h2>${msg}</h2>
<form action="/login.do" method="post">
    用户名：<input type="text" name="userName"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="登录">
</form>
</form>
</body>
</html>