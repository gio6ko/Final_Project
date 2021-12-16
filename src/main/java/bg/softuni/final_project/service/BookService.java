package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.service.BookAddServiceModel;
import bg.softuni.final_project.model.service.BookEditServiceModel;
import bg.softuni.final_project.model.service.BookServiceModel;
import bg.softuni.final_project.model.view.BookView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BookService {
    void initialiseBooks();

    List<BookView> getFirstEight();

    BookServiceModel findById(Long id);

    void addNewBook(BookAddServiceModel map) throws IOException;

    void deleteBook(Long id);

    Map<String,List<BookView>> getAllBooks();

    void editBook(BookEditServiceModel bookEditServiceModel) throws IOException;

    List<BookServiceModel> getMostPopularBooks();
}
