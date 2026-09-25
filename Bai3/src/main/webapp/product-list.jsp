<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<%-- Tự động redirect sang Servlet nếu mở trực tiếp JSP mà chưa có dữ liệu --%>
<c:if test="${empty products}">
    <c:redirect url="/products"/>
</c:if>

<div class="green-container">

    <div class="top-nav">
        <a href="${pageContext.request.contextPath}/cart">View Cart</a>
    </div>

    <div class="products-container">
        <c:forEach items="${products}" var="p">
            <div class="product-card">
                <h3>${p.model}</h3>

                <img
                        src="${pageContext.request.contextPath}/images/${p.imgURL}"
                        alt="${p.model}">

                <p class="price-text">
                    Price: ${p.price}
                </p>

                <form
                        action="${pageContext.request.contextPath}/cart"
                        method="post">

                    <input
                            type="text"
                            name="quantity"
                            value="1"
                            class="qty-input">

                    <input
                            type="hidden"
                            name="id"
                            value="${p.id}">

                    <input
                            type="hidden"
                            name="action"
                            value="add">

                    <input
                            type="submit"
                            value="Add To Cart"
                            class="btn-standard">

                </form>

                <a
                        href="${pageContext.request.contextPath}/product?id=${p.id}"
                        class="detail-link">
                    Product Detail
                </a>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>