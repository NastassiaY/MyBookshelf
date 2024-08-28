package controller;

import model.Author;
import service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorDataService;

    @GetMapping("/{authorID}")
    public Optional<Author> getAuthor(@PathVariable Long authorID) {
        return authorDataService.findAuthor(authorID);
    }

}
