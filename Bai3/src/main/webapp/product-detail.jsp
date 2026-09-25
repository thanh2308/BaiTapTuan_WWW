<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="green-container">

    <div class="top-nav">
        <a href="${pageContext.request.contextPath}/products" class="link-purple">&larr; Back to Product List</a>
        &nbsp;|&nbsp;
        <a href="${pageContext.request.contextPath}/cart" class="link-purple">View Cart</a>
    </div>

    <h1 class="cart-title">Product Detail</h1>

    <c:if test="${not empty product}">

        <div class="detail-content-wrap">

            <img
                    src="${pageContext.request.contextPath}/images/${product.imgURL}"
                    alt="${product.model}"
                    class="detail-img">

            <div class="detail-info">
                <ul class="detail-list">
                    <li><strong>Id:</strong> ${product.id}</li>
                    <li><strong>Model:</strong> ${product.model}</li>
                    <li><strong>Description:</strong> ${product.description}</li>
                    <li><strong>Quantity:</strong> ${product.quantity}</li>
                    <li><strong>Price:</strong> ${product.price}</li>
                </ul>

                <form
                        action="${pageContext.request.contextPath}/cart"
                        method="post"
                        style="margin-top: 15px; display: flex; gap: 8px; align-items: center;">

                    <input
                            type="text"
                            name="quantity"
                            value="1"
                            class="qty-input"
                            style="margin-bottom: 0;">

                    <input
                            type="hidden"
                            name="id"
                            value="${product.id}">

                    <input
                            type="hidden"
                            name="action"
                            value="add">

                    <input
                            type="submit"
                            value="Add To Cart"
                            class="btn-standard"
                            style="margin-bottom: 0;">
                </form>
            </div>

        </div>

    </c:if>

</div>

</body>
</html>