package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.Manga;
import bg.softuni.final_project.model.service.BookAddServiceModel;
import bg.softuni.final_project.model.service.BookEditServiceModel;
import bg.softuni.final_project.model.service.BookServiceModel;
import bg.softuni.final_project.model.view.BookView;
import bg.softuni.final_project.repository.BookRepository;
import bg.softuni.final_project.repository.MangaRepository;
import bg.softuni.final_project.service.BookService;
import bg.softuni.final_project.service.CloudinaryService;
import bg.softuni.final_project.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final MangaRepository mangaRepository;

    public BookServiceImpl(BookRepository bookRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper, MangaRepository mangaRepository) {
        this.bookRepository = bookRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.mangaRepository = mangaRepository;
    }

    @Override
    public void initialiseBooks() {


        if (bookRepository.count() == 0) {
            Manga manga = new Manga();
            manga.setMangaName("Naruto");
            mangaRepository.save(manga);
            for (int i = 1; i < 5; i++) {
                BookEntity book = new BookEntity();

                book.setTitle("Naruto,Vol " + (i * 10))
                        .setAuthor("Masashi Kishimoto")
                        .setDescription("Naruto Shippuuden manga")
                        .setImageUrl("/images/books/naruto/naruto" + (i) + ".jpg")
                        .setPrice(BigDecimal.valueOf(i + 10))
                        .setManga(manga);

                manga.getBooks().add(book);
                bookRepository.save(book);
            }
        }
    }

    @Override
    public List<BookView> getFirstEight() {
        return this.bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookView.class))
                .limit(8)
                .collect(Collectors.toList());
    }

    @Override
    public BookServiceModel findById(Long id) {
        return modelMapper.map(bookRepository.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("Book with id " + id + " not found!")),
                BookServiceModel.class);
    }

    @Transactional
    @Override
    public void addNewBook(BookAddServiceModel bookAddServiceModel) throws IOException {


        String imageUrl = uploadImage(bookAddServiceModel.getImage());

        Manga manga;
        if (mangaRepository.findByMangaName(bookAddServiceModel.getManga()).isEmpty()) {
            manga = new Manga();
            manga.setMangaName(bookAddServiceModel.getManga());
            mangaRepository.save(manga);
        } else {
            manga = mangaRepository.findByMangaName(bookAddServiceModel.getManga()).get();
        }
        BookEntity newBook = modelMapper.map(bookAddServiceModel, BookEntity.class);
        manga.getBooks().add(newBook);
        newBook.setImageUrl(imageUrl).setManga(manga);

        bookRepository.save(newBook);


    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Map<String, List<BookView>> getAllBooks() {

        List<String> allMangaSeries = mangaRepository.findAllMangaSeries();
        Map<String, List<BookView>> allBooks = new HashMap<>();


        allMangaSeries.forEach(s -> {
            List<BookView> allBooksBySeries = mangaRepository.allBooksBySeries(s)
                    .stream().map(this::map).collect(Collectors.toList());
            allBooks.put(s, allBooksBySeries);
        });

        return allBooks;
    }

    @Override
    public void editBook(BookEditServiceModel bookEditServiceModel) throws IOException {

        BookEntity book = bookRepository.findById(bookEditServiceModel.getId()).
                orElseThrow(() -> new ObjectNotFoundException("Book with id " + bookEditServiceModel.getId() + " not found!"));

        String imageUrl = uploadImage(bookEditServiceModel.getImage());

        modelMapper.map(bookEditServiceModel, book);
        book.setImageUrl(imageUrl);

        bookRepository.save(book);

    }

    @Override
    public List<BookServiceModel> getMostPopularBooks() {

        List<BookServiceModel> all = bookRepository.findAll()
                .stream().map(book -> modelMapper.map(book, BookServiceModel.class))
                .collect(Collectors.toList());
        Collections.shuffle(all);

        return all.stream().limit(4).collect(Collectors.toList());
    }


    private BookView map(BookEntity book) {
        return modelMapper.map(book, BookView.class);
    }

    private String uploadImage(MultipartFile image) throws IOException {

        return this.cloudinaryService.upload(image);
    }
}
