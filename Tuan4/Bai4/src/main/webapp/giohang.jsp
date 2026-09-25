<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>IUH BOOKSTORE - Your Shopping Cart</title>
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

            <a href="${pageContext.request.contextPath}/cart" class="cart-sidebar-link">
                Shopping cart (${sessionScope.cart != null ? sessionScope.cart.totalQuantity : 0})
            </a>
        </aside>

        <!-- MAIN -->
        <main class="main-content">
            <div class="cart-header-title">
                YOUR SHOPPING CART
            </div>

            <c:if test="${empty cart or empty cart.items}">
                <p style="text-align: center; color: #666; margin: 30px 0;">Giỏ hàng của bạn đang trống.</p>
                <div class="cart-buttons" style="justify-content: center;">
                    <a href="${pageContext.request.contextPath}/books" class="std-btn">
                        Continue shopping
                    </a>
                </div>
            </c:if>

            <c:if test="${not empty cart and not empty cart.items}">
                <table class="cart-table">
                    <thead>
                    <tr>
                        <th style="width: 14%;">Product ID</th>
                        <th style="width: 44%;">Product name</th>
                        <th style="width: 12%;" class="text-right">Price</th>
                        <th style="width: 8%;" class="text-center">Qty</th>
                        <th style="width: 12%;" class="text-right">Total</th>
                        <th style="width: 10%;" class="text-center">Remove</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart.items}" var="item">
                        <tr>
                            <td>pro<c:if test="${item.book.id < 10}">0</c:if>${item.book.id}</td>
                            <td>${item.book.title} - Tác giả: ${item.book.author}</td>
                            <td class="text-right">
                                <fmt:formatNumber value="${item.book.price}" pattern="#"/>
                            </td>
                            <td class="text-center">
                                ${item.quantity}
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber value="${item.subtotal}" pattern="#"/>
                            </td>
                            <td class="text-center">
                                <form action="${pageContext.request.contextPath}/cart" method="post" style="margin: 0;">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="id" value="${item.book.id}">
                                    <button type="submit" class="product-link" style="display: inline;">
                                        Remove
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr class="total-row">
                        <td colspan="4" class="text-right" style="border-right: none; font-weight: normal;">
                            Total price
                        </td>
                        <td colspan="2" style="border-left: none; font-weight: normal;">
                            (VND) <fmt:formatNumber value="${cart.total}" pattern="#"/>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="cart-buttons">
                    <a href="${pageContext.request.contextPath}/thanhtoan" class="std-btn">
                        Checkout
                    </a>
                    <a href="${pageContext.request.contextPath}/books" class="std-btn">
                        Continue shopping
                    </a>
                </div>
            </c:if>
        </main>
    </div>
</div>

</body>
</html>
