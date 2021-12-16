package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.Manga;

import java.math.BigDecimal;

public class BookView {


    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private String imageUrl;
    private String description;
    private Manga manga;


    public Long getId() {
        return id;
    }

    public BookView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookView setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookView setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookView setDescription(String description) {
        this.description = description;
        return this;
    }

    public Manga getManga() {
        return manga;
    }

    public BookView setManga(Manga manga) {
        this.manga = manga;
        return this;
    }
}
