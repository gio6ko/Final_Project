package bg.softuni.final_project.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class BookEditBindingModel {

    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private MultipartFile image;
    private String description;


    public Long getId() {
        return id;
    }

    public BookEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public BookEditBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public BookEditBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotBlank
    public String getAuthor() {
        return author;
    }

    public BookEditBindingModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public BookEditBindingModel setImage(MultipartFile image) {
        this.image = image;
        return this;
    }


    @NotBlank
    public String getDescription() {
        return description;
    }

    public BookEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }



}
