<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Category</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/header.css">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/product.css">
</head>
<body class="body">
<jsp:include page="header.jsp"/>
<div class="language">
    <img src="${contextPath}/images/lang__ru.png" alt="ru" data-google-lang="ru" class="language__img">
    <img src="${contextPath}/images/lang__en.png" alt="en" data-google-lang="en">
</div>
<div class="container">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title">${requestScope.product.name}</h3>
            <h6 class="card-subtitle">Категория: ${requestScope.categoryName}</h6>
            <div class="row">
                <div class="col-lg-5">
                    <div class="white-box text-left">
                        <div id="carouselExampleControls${requestScope.product.id}" class="carousel slide" data-bs-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="${contextPath}/${requestScope.product.imagePath}" class="d-block w-100" alt="...">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls${requestScope.product.id}"  data-bs-slide="prev">
                                <span style="filter: invert(100%)" class="carousel-control-prev-icon" aria-hidden="true"></span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls${requestScope.product.id}"  data-bs-slide="next">
                                <span style="filter: invert(100%)" class="carousel-control-next-icon" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 justify-content: right">
                    <h4 class="box-title mt-5">Описание товара</h4>
                    <p>${requestScope.product.description}</p><br>
                    <a href="${contextPath}/cart/add?productId=${requestScope.product.id}" class="btn btn-dark btn-lg">Добавить в корзину ${requestScope.product.price}$</a>
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
