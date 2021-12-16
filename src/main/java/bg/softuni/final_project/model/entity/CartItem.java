package bg.softuni.final_project.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    private BookEntity book;
    private String username;

    public CartItem() {
    }

    public CartItem(BookEntity book, String username) {
        this.book = book;
        this.username = username;
    }

    @OneToOne
    public BookEntity getBook() {
        return book;
    }

    public CartItem setBook(BookEntity book) {
        this.book = book;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CartItem setUsername(String username) {
        this.username = username;
        return this;
    }
}
