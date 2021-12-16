package bg.softuni.final_project.model.service;

import bg.softuni.final_project.model.entity.Manga;

import java.math.BigDecimal;

public class BookServiceModel {

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

    public BookServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BookServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Manga getManga() {
        return manga;
    }

    public BookServiceModel setManga(Manga manga) {
        this.manga = manga;
        return this;
    }
}
