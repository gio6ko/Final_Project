package bg.softuni.final_project.web;


import bg.softuni.final_project.model.binding.NewCommentBindingModel;
import bg.softuni.final_project.model.service.CommentServiceModel;
import bg.softuni.final_project.model.view.CommentViewModel;
import bg.softuni.final_project.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api/{bookId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable Long bookId, Principal principal) {

        return ResponseEntity.ok(
                commentService.getComments(bookId)
        );
    }

    @PostMapping("/api/{bookId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long bookId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ){


        CommentServiceModel commentServiceModel = modelMapper.map(newCommentBindingModel, CommentServiceModel.class);


        commentServiceModel.setCreator(principal.getUsername())
                .setBookId(bookId);

        CommentViewModel newComment = commentService.createComment(commentServiceModel);


        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", bookId, newComment.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment).
                body(newComment);
    }
}
