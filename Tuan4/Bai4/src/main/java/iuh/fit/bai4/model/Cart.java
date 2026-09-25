package iuh.fit.bai4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addBook(Book book) {

        for (CartItem item : items) {

            if (item.getBook().getId() == book.getId()) {

                item.setQuantity(
                        item.getQuantity() + 1
                );

                return;
            }
        }

        items.add(
                new CartItem(book, 1)
        );
    }

    public void updateQuantity(
            int bookId,
            int quantity) {

        for (CartItem item : items) {

            if (item.getBook().getId() == bookId) {

                if (quantity <= 0) {
                    removeBook(bookId);
                } else {
                    item.setQuantity(quantity);
                }

                return;
            }
        }
    }

    public void removeBook(int bookId) {

        items.removeIf(
                item -> item.getBook().getId() == bookId
        );
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }
}