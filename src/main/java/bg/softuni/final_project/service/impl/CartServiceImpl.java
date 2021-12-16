package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.CartItem;
import bg.softuni.final_project.repository.CartRepository;
import bg.softuni.final_project.service.BookService;
import bg.softuni.final_project.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;


    public CartServiceImpl(CartRepository cartRepository, BookService bookService, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addBookToCart(Long bookId, String username) {
        if (cartRepository.bookExists(bookId, username)) {
            return false;
        }

        BookEntity book = modelMapper.map(bookService.findById(bookId), BookEntity.class);
        CartItem cartItem = new CartItem(book, username);
        cartRepository.save(cartItem);
        return true;
    }

    @Override
    public long getRepoCount(String username) {
        return cartRepository.findAllByUsername(username).size();
    }

    @Override
    public List<CartItem> findAllByUsername(String username) {
        return cartRepository.findAllByUsername(username);
    }

    @Override
    public void deleteItem(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<CartItem> items) {
        cartRepository.deleteAll(items);
    }
}
