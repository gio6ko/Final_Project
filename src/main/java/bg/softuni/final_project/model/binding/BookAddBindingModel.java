package bg.softuni.final_project.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class BookAddBindingModel {


    private String title;
    private BigDecimal price;
    private String author;
    private MultipartFile image;
    private String description;
    private String manga;


    @NotBlank
    public String getTitle() {
        return title;
    }

    public BookAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public BookAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotBlank
    public String getAuthor() {
        return author;
    }

    public BookAddBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BookAddBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }


    @NotBlank
    public String getDescription() {
        return description;
    }

    public BookAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }


    @NotBlank
    public String getManga() {
        return manga;
    }

    public BookAddBindingModel setManga(String manga) {
        this.manga = manga;
        return this;
    }
}
