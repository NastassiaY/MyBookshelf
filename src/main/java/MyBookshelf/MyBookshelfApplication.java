package MyBookshelf;

import configuration.Config;
import model.Author;
import model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AuthorDataService;
import service.BookDataService;

@SpringBootApplication
public class MyBookshelfApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBookshelfApplication.class, args);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        AuthorDataService authorService = new AuthorDataService();
        BookDataService bookService = new BookDataService();

        Author author1 = new Author(1L,"Steven King", "USA");
        authorService.saveAuthor(author1);

        Book book1 = new Book(1L, "It", author1, Book.Genre.Horror, 1);
        bookService.saveBook(book1);

    }
}
