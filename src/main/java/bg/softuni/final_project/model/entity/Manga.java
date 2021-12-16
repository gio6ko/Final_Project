package bg.softuni.final_project.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mangas")
public class Manga extends BaseEntity {

    private String mangaName;
    private List<BookEntity> books = new ArrayList<>();


    public String getMangaName() {
        return mangaName;
    }

    public Manga setMangaName(String mangaName) {
        this.mangaName = mangaName;
        return this;
    }

    @OneToMany(mappedBy = "manga", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<BookEntity> getBooks() {
        return books;
    }

    public Manga setBooks(List<BookEntity> books) {
        this.books = books;
        return this;
    }
}
