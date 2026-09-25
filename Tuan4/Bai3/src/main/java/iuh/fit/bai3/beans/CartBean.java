package iuh.fit.bai3.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    // Thêm sản phẩm
    public void addProduct(Product p) {
        addProduct(p, 1);
    }

    public void addProduct(Product p, int quantity) {
        if (p == null || quantity <= 0) {
            return;
        }

        for (CartItemBean item : items) {
            if (item.getProduct() != null && item.getProduct().getId() != null
                    && item.getProduct().getId().equals(p.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItemBean(p, quantity));
    }

    // Xóa sản phẩm
    public void removeProduct(int productId) {
        items.removeIf(
                item -> item.getProduct() != null && item.getProduct().getId() != null
                        && item.getProduct().getId() == productId
        );
    }

    // Cập nhật số lượng
    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct() != null && item.getProduct().getId() != null
                    && item.getProduct().getId() == productId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    removeProduct(productId);
                }
                return;
            }
        }
    }

    // Tính tổng tiền
    public double getTotal() {
        double total = 0;
        for (CartItemBean item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // Xóa toàn bộ
    public void clear() {
        items.clear();
    }
}
