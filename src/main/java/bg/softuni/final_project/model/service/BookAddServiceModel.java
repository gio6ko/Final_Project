package bg.softuni.final_project.model.service;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class BookAddServiceModel {


    private String title;
    private BigDecimal price;
    private String author;
    private MultipartFile image;
    private String description;
    private String manga;


    public String getTitle() {
        return title;
    }

    public BookAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookAddServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BookAddServiceModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getManga() {
        return manga;
    }

    public BookAddServiceModel setManga(String manga) {
        this.manga = manga;
        return this;
    }
}
