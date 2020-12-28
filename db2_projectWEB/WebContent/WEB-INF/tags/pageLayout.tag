<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>2Day Product</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mini-default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
   <script src="https://kit.fontawesome.com/816d94e0fe.js" crossorigin="anonymous"></script>

</head>

<body>
    <header>
        <jsp:invoke fragment="header"/>
      </header>

      <div class="container expand-h">
        <div class="row expand-h">
          <div class="col-sm-12 col-md-offset-2 col-md-8 page expand-h">
				<jsp:doBody/>
          </div>
        </div>
      </div>
</body>
</html>