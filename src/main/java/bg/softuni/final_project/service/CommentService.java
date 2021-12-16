package bg.softuni.final_project.service;

import bg.softuni.final_project.model.service.CommentServiceModel;
import bg.softuni.final_project.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    List<CommentViewModel> getComments(Long bookId);

    void initialiseComments();

    CommentViewModel createComment(CommentServiceModel commentServiceModel);
}
