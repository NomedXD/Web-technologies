<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Add category</title>
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
<div class="container">
    <div class="main-body" style="padding-top:10px">
        <div class="row gutters-sm">
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <form action="${contextPath}/admin/add_category" method="POST"
                              id="updateUser" onsubmit="return validateNewCategoryForm()">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Добавление категории</h6>
                                    <c:if test="${not empty requestScope.stateMessage}">
                                        <h6 style="color: red" class="mb-0">${requestScope.stateMessage}</h6>
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Название категории</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <div class="d-flex">
                                        <div class="d-inline-block">
                                            <input type="text" id="name" name="name" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Небольшое описание категории</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <div class="d-flex">
                                        <div class="d-inline-block">
                                            <input type="text" id="someText" name="someText" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Имя картинки</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <div class="d-flex">
                                        <div class="d-inline-block">
                                            <input type="text" id="imagePath" name="imagePath" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </form>
                        <div class="row">
                            <div class="col-sm-12">
                                <button class="btn btn-info " type="submit" form="updateUser">Добавить категорию</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
<script src="${contextPath}/jsp-scripts/admin-add-category.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="${contextPath}/jsp-scripts/google-translate.js"></script>
<script src="//translate.google.com/translate_a/element.js?cb=TranslateInit"></script>
