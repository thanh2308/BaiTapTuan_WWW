<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="green-container">

    <h1 class="cart-title">Cart</h1>

    <!-- Khi giỏ hàng không có gì -->
    <c:if test="${empty cart.items}">
        <p class="empty-message">Cart is emppty!</p>
        <a href="${pageContext.request.contextPath}/products" class="link-blue">Tiếp tục mua</a>
    </c:if>

    <!-- Khi giỏ hàng có sản phẩm -->
    <c:if test="${not empty cart.items}">

        <table class="cart-table">
            <thead>
            <tr>
                <th class="col-model">Model</th>
                <th class="col-qty">Quantity</th>
                <th class="col-price">Price</th>
                <th class="col-subtotal">SubTotal</th>
                <th class="col-action">Action</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.product.model}</td>

                    <td>
                        <form
                                action="${pageContext.request.contextPath}/cart"
                                method="post"
                                class="cart-qty-form">

                            <input
                                    type="hidden"
                                    name="action"
                                    value="update">

                            <input
                                    type="hidden"
                                    name="productId"
                                    value="${item.product.id}">

                            <input
                                    type="text"
                                    name="quantity"
                                    value="${item.quantity}"
                                    class="cart-qty-input">

                            <input
                                    type="submit"
                                    value="Cập nhật"
                                    class="btn-action">
                        </form>
                    </td>

                    <td>${item.product.price}</td>

                    <td>${item.subtotal} VND</td>

                    <td>
                        <form
                                action="${pageContext.request.contextPath}/cart"
                                method="post">

                            <input
                                    type="hidden"
                                    name="action"
                                    value="remove">

                            <input
                                    type="hidden"
                                    name="productId"
                                    value="${item.product.id}">

                            <input
                                    type="submit"
                                    value="Xóa"
                                    class="btn-action">
                        </form>
                    </td>
                </tr>
            </c:forEach>

            <tr class="total-row">
                <td>Total:</td>
                <td></td>
                <td></td>
                <td>${cart.total} VND</td>
                <td></td>
            </tr>
            </tbody>
        </table>

        <!-- Nút xóa hết giỏ hàng -->
        <form
                action="${pageContext.request.contextPath}/cart"
                method="post">

            <input
                    type="hidden"
                    name="action"
                    value="clear">

            <input
                    type="submit"
                    value="Xóa hết giỏ hàng"
                    class="btn-clear-cart">
        </form>

        <div>
            <a href="${pageContext.request.contextPath}/products" class="link-blue">Tiếp tục mua</a>
        </div>

    </c:if>

</div>

</body>
</html>