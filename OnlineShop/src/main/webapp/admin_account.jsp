<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/account.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${contextPath}/fontawesome/css/all.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/header.css">
</head>
<body class="body">
<jsp:include page="admin_header.jsp"/>
<div class="language">
    <img src="${contextPath}/images/lang__ru.png" alt="ru" data-google-lang="ru" class="language__img">
    <img src="${contextPath}/images/lang__en.png" alt="en" data-google-lang="en">
</div>
<!--User account info below-->
<div class="container">
    <div class="main-body" style="padding-top:10px">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="cardUpper">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${sessionScope.user.name}</h4>
                                <p class="text-secondary mb-1">Администратор</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Полное имя</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.user.name} ${sessionScope.user.surname}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Почта</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.user.mail}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">День рождения</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.user.date}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">ID администратора</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.user.id}
                            </div>
                        </div>
                        <hr>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="${contextPath}/jsp-scripts/google-translate.js"></script>
<script src="//translate.google.com/translate_a/element.js?cb=TranslateInit"></script>
