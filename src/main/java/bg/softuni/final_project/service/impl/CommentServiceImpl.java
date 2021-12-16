package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.CommentEntity;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.service.CommentServiceModel;
import bg.softuni.final_project.model.view.CommentViewModel;
import bg.softuni.final_project.repository.BookRepository;
import bg.softuni.final_project.repository.CommentRepository;
import bg.softuni.final_project.repository.UserRepository;
import bg.softuni.final_project.service.CommentService;
import bg.softuni.final_project.web.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(BookRepository bookRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long bookId) {

        Optional<BookEntity> book = bookRepository.findById(bookId);

        if (book.isEmpty()) {
            throw new ObjectNotFoundException("Book with id " + bookId + " not found!");
        }

        return book.get()
                .getComments()
                .stream()
                .map(this::mapAsComment)
                .collect(Collectors.toList());
    }


    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.
                setCommentId(commentEntity.getId()).
                setCanDelete(true).
                setCreated(commentEntity.getCreated()).
                setMessage(commentEntity.getTextContent()).
                setUser(commentEntity.getAuthor().getFirstName() + " " + commentEntity.getAuthor().getLastName());


        return commentViewModel;
    }


    @Override
    public void initialiseComments() {

        if (commentRepository.count() == 0) {

            for (int i = 0; i < 2; i++) {

                CommentEntity commentEntity = new CommentEntity();

                BookEntity firstBook = bookRepository.findById(1L)
                        .orElseThrow(() -> new ObjectNotFoundException("Book with id " + 1 + " not found!"));

                UserEntity admin = userRepository.findByUsername("admin")
                        .orElseThrow(() -> new ObjectNotFoundException("No admin user!"));

                commentEntity.setCreated(LocalDateTime.now())
                        .setBook(firstBook)
                        .setAuthor(admin)
                        .setTextContent("First Review");

                commentRepository.save(commentEntity);
            }


        }

    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {


        Objects.requireNonNull(commentServiceModel.getCreator());

        BookEntity book = bookRepository.
                findById(commentServiceModel.getBookId())
                .orElseThrow(() -> new ObjectNotFoundException("Book with id " + commentServiceModel.getBookId() + " not found!"));

        UserEntity author = userRepository.
                findByUsername(commentServiceModel.getCreator())
                .orElseThrow(() -> new ObjectNotFoundException("No such user with username - "
                        + commentServiceModel.getCreator() + "!"));


        CommentEntity newComment = new CommentEntity();
        newComment.setTextContent(commentServiceModel.getMessage());
        newComment.setCreated(LocalDateTime.now());
        newComment.setBook(book);
        newComment.setAuthor(author);

        CommentEntity savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);

    }

}
