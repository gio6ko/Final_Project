package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Long> {

    List<BookEntity> findAllByIdBefore(Long id);
}
