<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>IUH BOOKSTORE - Checkout</title>
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
            <div class="checkout-header-title">
                Checkout - Already registered? ...
            </div>

            <c:if test="${not empty message}">
                <div style="background: #e6f7e6; border: 1px solid #a3d9a3; padding: 10px; margin-bottom: 12px; color: #2d662d;">
                    ${message}
                </div>
            </c:if>

            <div class="checkout-box">
                <form action="${pageContext.request.contextPath}/thanhtoan" method="post">
                    <table class="checkout-table">
                        <tr>
                            <td class="checkout-label">Fullname:</td>
                            <td>
                                <input type="text"
                                       name="fullname"
                                       class="checkout-input"
                                       style="width: 170px;"
                                       required>
                            </td>
                        </tr>
                        <tr>
                            <td class="checkout-label">Shipping address:</td>
                            <td>
                                <input type="text"
                                       name="address"
                                       class="checkout-input"
                                       style="width: 320px;"
                                       required>
                            </td>
                        </tr>
                        <tr>
                            <td class="checkout-label">Total price:</td>
                            <td>
                                <input type="text"
                                       name="total"
                                       value="<fmt:formatNumber value='${cart != null ? cart.total : 0}' pattern='#'/>"
                                       readonly
                                       class="checkout-input"
                                       style="width: 130px; background-color: #f7f7f7;">
                            </td>
                        </tr>
                        <tr>
                            <td class="checkout-label">Payment method:</td>
                            <td>
                                <label style="margin-right: 10px; cursor: pointer;">
                                    <input type="radio" name="payment" value="Paypal" checked> Paypal
                                </label>
                                <label style="margin-right: 10px; cursor: pointer;">
                                    <input type="radio" name="payment" value="ATM Debit"> ATM Debit
                                </label>
                                <label style="cursor: pointer;">
                                    <input type="radio" name="payment" value="Visa/Master card"> Visa/Master card
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="padding-top: 15px;">
                                <button type="submit" class="std-btn" style="margin-right: 8px;">
                                    Save
                                </button>
                                <a href="${pageContext.request.contextPath}/cart" class="std-btn">
                                    Cancel
                                </a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </main>
    </div>
</div>

</body>
</html>
