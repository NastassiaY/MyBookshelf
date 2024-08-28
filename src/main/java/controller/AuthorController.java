package controller;

import model.Author;
import service.AuthorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorDataService authorDataService;

    @GetMapping("/authors/{authorID}")
    public Author getAuthor(@PathVariable Long authorID) {
        return authorDataService.findAuthor(authorID);
    }

}
