package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.BookAddBindingModel;
import bg.softuni.final_project.model.binding.BookEditBindingModel;
import bg.softuni.final_project.model.service.BookAddServiceModel;
import bg.softuni.final_project.model.service.BookEditServiceModel;
import bg.softuni.final_project.model.view.BookView;
import bg.softuni.final_project.service.BookService;
import bg.softuni.final_project.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class BookController extends BaseController {

    private final BookService bookService;

    private final ModelMapper modelMapper;

    public BookController(BookService bookService, CartService cartService, ModelMapper modelMapper) {
        super(cartService);
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/books/{id}/details")
    public String showBook(@PathVariable Long id, Model model) {

        model.addAttribute("book", modelMapper.map(this.bookService.findById(id), BookView.class));
        model.addAttribute("mostPopular", this.bookService.getMostPopularBooks());
        return "details";
    }


    @GetMapping("/books/add")
    public String addBook(Model model) {

        if (!model.containsAttribute("bookAddBindingModel")) {
            model.addAttribute("bookAddBindingModel", new BookAddBindingModel());
        }

        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid BookAddBindingModel bookAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookAddBindingModel", bookAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookAddBindingModel", bindingResult);

            return "redirect:/books/add";
        }
        bookService.addNewBook(modelMapper.map(bookAddBindingModel, BookAddServiceModel.class));
        return "redirect:/";
    }


    @DeleteMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/";
    }


    @GetMapping("/books/all")
    public String allBooks(Model model) {

        model.addAttribute("allBooks", bookService.getAllBooks());

        return "all-books";
    }


    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable Long id, Model model) {


        model.addAttribute("bookToEdit", modelMapper.map(bookService.findById(id), BookEditBindingModel.class));

        return "edit-book";
    }

    @PutMapping("/books/{id}/edit")
    public String editBookConfirm(@PathVariable Long id,
                                  @Valid BookEditBindingModel bookEditBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookToEdit", bookEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookToEdit", bindingResult);

            return "redirect:/books/" + id + "/edit";
        }


        bookService.editBook(modelMapper.map(bookEditBindingModel, BookEditServiceModel.class));
        return "redirect:/books/" + id + "/details";
    }


}
