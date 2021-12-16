package bg.softuni.final_project.model.service;

public class CommentServiceModel {
    private Long bookId;
    private String message;
    private String creator;

    public Long getBookId() {
        return bookId;
    }

    public CommentServiceModel setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public CommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }

}
