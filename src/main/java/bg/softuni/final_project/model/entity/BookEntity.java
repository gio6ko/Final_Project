package bg.softuni.final_project.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {


    private String title;
    private BigDecimal price;
    private String author;
    private String imageUrl;
    private String description;
    private Manga manga;
    private List<CommentEntity> comments = new ArrayList<>();


    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public BookEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(nullable = false)
    public String getAuthor() {
        return author;
    }

    public BookEntity setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public BookEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public BookEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne
    public Manga getManga() {
        return manga;
    }

    public BookEntity setManga(Manga mangaName) {
        this.manga = mangaName;
        return this;
    }

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<CommentEntity> getComments() {
        return comments;
    }

    public BookEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
