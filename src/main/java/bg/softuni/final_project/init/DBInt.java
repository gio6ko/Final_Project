package bg.softuni.final_project.init;

import bg.softuni.final_project.service.BookService;
import bg.softuni.final_project.service.CommentService;
import bg.softuni.final_project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInt implements CommandLineRunner {
    private final UserService userService;
    private final BookService bookService;
    private final CommentService commentService;

    public DBInt(UserService userService, BookService bookService, CommentService commentService) {
        this.userService = userService;
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.initialiseUsersAndRoles();
        bookService.initialiseBooks();
        commentService.initialiseComments();
    }
}
