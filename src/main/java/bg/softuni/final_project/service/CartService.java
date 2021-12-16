package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.CartItem;

import java.util.List;

public interface CartService {

    boolean addBookToCart(Long bookId, String username);
    long getRepoCount(String usernam);
    List<CartItem> findAllByUsername(String username);

    void deleteItem(Long id);
    void deleteAll(List<CartItem> items);
}
