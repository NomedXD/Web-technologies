<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
    <title>Корзина</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/cart.css">

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${contextPath}/fontawesome/css/all.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/jsp-scc-styles/header.css">
    <link href="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
</head>
<body class="body">
<jsp:include page="header.jsp"/>
<div class="language">
    <img src="${contextPath}/images/lang__ru.png" alt="ru" data-google-lang="ru" class="language__img">
    <img src="${contextPath}/images/lang__en.png" alt="en" data-google-lang="en">
</div>
<section class="h-100 h-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <h1 class="fw-bold mb-0 text-black">Корзина</h1>
                                        <c:choose>
                                            <c:when test="${empty sessionScope.cart}">
                                                <h6 class="mb-0 text-muted">0 товаров</h6>
                                            </c:when>
                                            <c:otherwise>
                                                <h6 class="mb-0 text-muted">${sessionScope.cart.getTotalSize()} товаров</h6>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <hr class="my-4">
                                    <c:forEach items="${sessionScope.cart.getCartProductsInList()}" var="cartProduct">
                                        <div class="row mb-4 d-flex justify-content-between align-items-center">
                                            <div class="col-md-2 col-lg-2 col-xl-2">
                                                <img src="${contextPath}/${cartProduct.imagePath}"
                                                        class="img-fluid rounded-3" alt="Cotton T-shirt">
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-3">
                                                <h6 class="text-muted">${cartProduct.name}</h6>
                                                <h6 class="text-black mb-0">Cotton T-shirt</h6>
                                            </div>
                                            <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                <button class="btn btn-link px-2"
                                                        onclick="this.parentNode.querySelector('input[type=number]').stepDown(); calculateTotal();">
                                                    <i class="fas fa-minus"></i>
                                                </button>

                                                <input id="form1" min="0" type="number" value="1"
                                                       class="form-control form-control-sm productCount"/>

                                                <button class="btn btn-link px-2"
                                                        onclick="this.parentNode.querySelector('input[type=number]').stepUp(); calculateTotal();">
                                                    <i class="fas fa-plus"></i>
                                                </button>
                                            </div>
                                            <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                <h6 class="mb-0 singlePrice">${cartProduct.price}</h6>
                                            </div>
                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                <a href="${contextPath}/cart/remove?productId=${cartProduct.id}" class="text-muted">
                                                    <i class="fas fa-times"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <hr class="my-4">
                                    <div class="pt-5">
                                        <h6 class="mb-0"><a href="${contextPath}/catalog" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Назад на главную</a></h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Итого</h3>
                                    <hr class="my-4">
                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase">Товаров = ${sessionScope.cart.getTotalSize()}</h5>
                                    </div>
                                    <form name="checkout" id="checkout" action="${contextPath}/cart" method="POST" onsubmit="return validateForm()">
                                        <h5 class="text-uppercase mb-3">Доставка</h5>
                                        <div class="mb-4 pb-2">
                                            <select id="addressSelect" class="selectPicker" data-size="2" name="shippingType">
                                                <option value="Self pickup">Самовывоз 0$</option>
                                                <option value="Delivery by courier">Доставка курьером 10$</option>
                                            </select>
                                        </div>
                                        <input id="shippingCost" type="hidden" name="shippingCost" value="0">
                                        <h5 id="addressH5" class="text-uppercase mb-3" style="display: none">Адрес</h5>
                                        <div id="addressDiv" class="mb-4 pb-2" style="display: none">
                                            <input id="addressInput" class="form-control form-control-lg" type="text" maxlength="60" name="address">
                                        </div>
                                        <h5 class="text-uppercase mb-3">Платежная карта</h5>
                                        <div class="mb-5">
                                            <div class="form-outline">
                                                <input name="creditCardNumber" id="creditCardNumber" class="form-control form-control-lg" type="tel" inputmode="numeric" pattern="[0-9\s]{16,19}" autocomplete="cc-number" minlength="16" onkeyup="cc_format(this.id)" placeholder="xxxx xxxx xxxx xxxx">
                                                <input class="form-control form-control-lg" id="creditCardYear" type="tel" inputmode="numeric" pattern="(\d{2,2}/\d{4,4})" autocomplete="cc-number" minlength="7" maxlength="7" placeholder="MM/YYYY">
                                                <input class="form-control form-control-lg" id="creditCardCVV" type="password" inputmode="numeric" pattern="(\d{3,3})" autocomplete="cc-number" minlength="3" maxlength="3" placeholder="CVV">
                                            </div>
                                        </div>
                                        <h5 class="text-uppercase mb-3">Промокод</h5>
                                        <div class="mb-5">
                                            <div class="form-outline">
                                                <input type="text" id="inputCode" form="checkCodeForm" class="form-control form-control-lg" name="code" maxlength="10" />
                                                <label class="form-label" for="inputCode">Введите ваш промокод</label><br>
                                                <c:if test="${not empty requestScope.discountCodeMessage}">${requestScope.discountCodeMessage}<br></c:if>
                                                <c:if test="${not empty sessionScope.cart && not empty sessionScope.cart.appliedDiscountCode}">
                                                    <label class="form-label" for="inputCode">Примененный промокод: ${sessionScope.cart.appliedDiscountCode.name} -$${sessionScope.cart.appliedDiscountCode.discount}</label><br>
                                                </c:if>
                                                <button form="checkCodeForm" type="submit" class="btn btn-dark btn-block btn-lg" onclick="copyCode()" data-mdb-ripple-color="dark">Apply code</button>
                                            </div>
                                        </div>
                                        <h5 class="text-uppercase mb-3">Заметки покупателя</h5>
                                        <div class="mb-5">
                                            <div class="form-outline">
                                                <input class="form-control form-control-lg" type="text"  maxlength="100" name="customerNotes">
                                            </div>
                                        </div>
                                        <hr class="my-4">
                                        <div class="d-flex justify-content-between mb-5">
                                            <h5 class="text-uppercase">Общая цена</h5>
                                            <h5 id="total">${sessionScope.cart.totalPrice}</h5>
                                        </div>
                                        <c:if test="${not empty sessionScope.cart && sessionScope.cart.getTotalSize() != 0}">
                                            <button form="checkout" type="submit" class="btn btn-dark btn-block btn-lg" data-mdb-ripple-color="dark">Подтвердить</button>
                                        </c:if>
                                    </form>
                                    <form name="checkCodeForm" id="checkCodeForm" action="${contextPath}/cart/check_code" method="POST">
                                        <input type="hidden" name="discountCode" id="checkCodeInput" value="">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${contextPath}/jsp-scripts/cart.js"></script>
</section>
</body>
<jsp:include page="footer.jsp"/>
</html>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="${contextPath}/jsp-scripts/google-translate.js"></script>
<script src="//translate.google.com/translate_a/element.js?cb=TranslateInit"></script>

