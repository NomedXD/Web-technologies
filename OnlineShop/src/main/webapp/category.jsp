<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Category</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/category.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${contextPath}/fontawesome/css/all.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/header.css">
</head>
<body class="body">
<jsp:include page="header.jsp"/>
<c:choose>
    <c:when test="${requestScope.products.size() == 0}">
        <div class="bar warn">No products found. Try later</div>
    </c:when>
    <c:otherwise>
        <c:forEach items="${requestScope.products}" var="product">
            <div class="row p-2 bg-white border rounded mt-2">
                <div class="col-md-3 mt-1">
                    <div id="carouselExampleControls${product.id}" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="${contextPath}/${product.imagePath}" class="d-block w-100"
                                     alt="...">
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button"
                                data-bs-target="#carouselExampleControls${product.id}" data-bs-slide="prev">
                            <span style="filter: invert(100%)" class="carousel-control-prev-icon"
                                  aria-hidden="true"></span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleControls${product.id}" data-bs-slide="next">
                            <span style="filter: invert(100%)" class="carousel-control-next-icon"
                                  aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
                <div class="col-md-6 mt-1">
                    <h5>${product.name}</h5>
                    <div class="d-flex flex-row">
                        <div class="ratings mr-2"><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                class="fa fa-star"></i><i
                                class="fa fa-star"></i></div>
                        <span>310</span>
                    </div>
                    <p class="text-justify text-truncate para mb-0">${product.description}<br><br></p>
                </div>
                <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                    <div class="d-flex flex-row align-items-center">
                        <h4 class="mr-1">${product.price}$</h4>
                    </div>
                    <h6 class="text-success">Available</h6>
                    <div class="d-flex flex-column mt-4">
                        <a class="btn btn-dark btn-sm" type="button"
                           href="${pageContext.request.contextPath}/product?productId=${product.id}">More info</a>
                        <button class="btn btn-outline-dark btn-sm mt-2" type="button">Add to wishlist</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
<div class="col-md-3 mt-2"></div>
</body>
<jsp:include page="footer.jsp"/>
</html>
