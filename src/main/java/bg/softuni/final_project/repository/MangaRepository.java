package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MangaRepository extends JpaRepository<Manga, Long> {

   Optional<Manga> findByMangaName(String mangaName);


   @Query("select m.mangaName from Manga m")
   List<String> findAllMangaSeries();


   @Query("select m.books from Manga m where m.mangaName = :mangaName")
   List<BookEntity> allBooksBySeries(@Param("mangaName") String mangaName);
}
