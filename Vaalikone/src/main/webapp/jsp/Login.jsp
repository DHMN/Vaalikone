<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sisäänkirjautuminen</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Sisäänkirjautuminen</h1>
        <form action="login" method="post">
            <label for="email">Sähköposti:</label>
            <input name="email" size="30" />
            <br><br>
            <label for="password">Salasana:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br><br>           
            <button type="submit">Kirjaudu</button>
        </form>
    </div>
</body>
<script type="text/javascript">
 
    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                },
         
                password: "required",
            },
             
            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                },
                 
                password: "Please enter password"
            }
        });
 
    });
</script>
</html>