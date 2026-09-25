package iuh.fit.bai4.model;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private String description;
    private double price;
    private int quantity;
    private String image;

    public Book() {
    }

    public Book(int id,
                String title,
                String author,
                String description,
                double price,
                int quantity,
                String image) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}