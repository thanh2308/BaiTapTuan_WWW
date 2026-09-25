<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>IUH BOOKSTORE - Chi tiết sách</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="main-wrapper">
    <!-- HEADER -->
    <header class="header-banner">
        <div class="logo-box">
            <a href="${pageContext.request.contextPath}/books" class="logo-text">IUH BOOKSTORE</a>
        </div>
        <nav class="nav-menu">
            <a href="${pageContext.request.contextPath}/books" class="nav-btn">HOME</a>
            <a href="#" class="nav-btn">EXAMPLES</a>
            <a href="#" class="nav-btn">SERVICES</a>
            <a href="${pageContext.request.contextPath}/books" class="nav-btn">PRODUCTS</a>
            <a href="#" class="nav-btn">CONTACT</a>
        </nav>
    </header>

    <!-- CONTENT -->
    <div class="content-area">
        <!-- SIDEBAR -->
        <aside class="sidebar">
            <div class="sidebar-title">ABOUT US</div>
            <div class="sidebar-text">
                About us information will be here.... <a href="#">Read More &raquo;</a>
            </div>

            <div class="sidebar-title">SEARCH SITE</div>
            <form action="${pageContext.request.contextPath}/search" method="get">
                <input type="text"
                       name="keyword"
                       value="${keyword}"
                       class="search-input">
            </form>

            <c:if test="${not empty sessionScope.cart and sessionScope.cart.totalQuantity > 0}">
                <a href="${pageContext.request.contextPath}/cart" class="cart-sidebar-link">
                    Shopping cart (${sessionScope.cart.totalQuantity})
                </a>
            </c:if>
        </aside>

        <!-- MAIN -->
        <main class="main-content">
            <div class="detail-box">
                <div class="detail-title">
                    Product details: ${book.title} - Tác giả: ${book.author}
                </div>

                <img src="${pageContext.request.contextPath}/images/${book.image}"
                     alt="${book.title}"
                     class="detail-img"
                     onerror="this.src='${pageContext.request.contextPath}/images/images.jpg'">

                <div class="detail-info">
                    <div>Price (VND): <fmt:formatNumber value="${book.price}" pattern="#"/></div>
                    <div>Quantity: ${book.quantity}</div>
                </div>


                <a href="${pageContext.request.contextPath}/books" class="detail-back">
                    Back to Product List
                </a>
            </div>
        </main>
    </div>
</div>

</body>
</html>
