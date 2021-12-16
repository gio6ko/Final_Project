package bg.softuni.final_project.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class BookEditServiceModel {


    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private MultipartFile image;
    private String description;


    public Long getId() {
        return id;
    }

    public BookEditServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEditServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookEditServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookEditServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BookEditServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookEditServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

}
