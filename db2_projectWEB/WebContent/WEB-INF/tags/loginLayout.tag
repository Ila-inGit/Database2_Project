<%@tag description="Login Page template" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>2Day Product</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mini-default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <script src="https://kit.fontawesome.com/816d94e0fe.js" crossorigin="anonymous"></script>

</head>

<body>
    <div class="login-container">
        <div class="login-block container">
            <div class="row">
                <div class="col-sm-12">
 					<jsp:doBody/>
                </div>
            </div>
        </div>
    </div>
</body>
</html>