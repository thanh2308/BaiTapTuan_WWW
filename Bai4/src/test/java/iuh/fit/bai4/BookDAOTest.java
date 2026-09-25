package iuh.fit.bai4;

import iuh.fit.bai4.dao.BookDAO;
import iuh.fit.bai4.model.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookDAOTest {

    @Test
    public void testGetAllBooks() {
        BookDAO dao = new BookDAO(null); // tests direct connection fallback
        List<Book> books = dao.getAllBooks();
        assertNotNull(books, "Books list should not be null");
        assertFalse(books.isEmpty(), "Books list should not be empty");
        System.out.println("Fetched " + books.size() + " books successfully.");
        for (Book b : books) {
            System.out.println("Book: ID=" + b.getId() + " | Title=" + b.getTitle() + " | Price=" + b.getPrice() + " | Image=" + b.getImage());
        }
    }

    @Test
    public void testGetBookById() {
        BookDAO dao = new BookDAO(null);
        Book book = dao.getBookById(1);
        assertNotNull(book, "Book with ID 1 should exist");
        System.out.println("Found book 1: " + book.getTitle() + " by " + book.getAuthor());
    }

    @Test
    public void testSearchBooks() {
        BookDAO dao = new BookDAO(null);
        List<Book> results = dao.searchBooks("Kim");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        System.out.println("Search 'Kim' returned " + results.size() + " book(s): " + results.get(0).getTitle());
    }
}
