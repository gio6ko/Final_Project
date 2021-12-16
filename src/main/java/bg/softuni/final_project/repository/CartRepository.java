package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUsername(String username);
    @Query("select case when count(c) > 0 then true else false end from CartItem c where c.book.id = :bookId " +
            " and c.username = :username ")
     boolean bookExists(@Param("bookId") Long bookId, @Param("username") String username);
}
