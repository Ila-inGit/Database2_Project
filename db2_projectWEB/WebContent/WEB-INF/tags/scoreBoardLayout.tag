<%@tag description="Login Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>scoreBoard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mini-default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/scoreboard.css">
  <script src="https://kit.fontawesome.com/816d94e0fe.js" crossorigin="anonymous"></script>

</head>

<body>
	<header>
        <jsp:invoke fragment="header"/>
    </header>
      
    <div class="scoreBoard-container">
        <div class="scoreboard-block container expand-w">
       			<div class="col-sm-12 expand-w scoreBoard-color">
 					<jsp:doBody/>
                </div>
        </div>
    </div>
</body>
</html>