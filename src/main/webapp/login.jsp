<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <script>
        function reloadCaptcha() {
            var captchaImage = document.getElementById('captcha-image');
            captchaImage.src = 'ServletCode?' + new Date().getTime();
        }
    </script>
</head>
<body>
<form action="login" method="post">
    <label for="username">用户名:</label>
    <input type="text" name="username" id="username" required><br>
    <label for="password">密码:</label>
    <input type="password" name="password" id="password" required><br>
    <label for="code1">验证码:</label>
    <input type="text" name="code1" id="code1" required>
    <img id="captcha-image" src="ServletCode" alt="Verification Code"> <button type="button" onclick="reloadCaptcha()">刷新验证码</button> <br>
    <input type="submit" value="登录">
</form>
<p>${message}</p>
</body>
</html>
