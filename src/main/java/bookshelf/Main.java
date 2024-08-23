package bookshelf;

import appDAO.AuthorDAOImpl;
import appDAO.BookDAOImpl;
import model.Author;
import model.Book;
import service.AuthorService;
import service.BookService;
import service.Config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import service.FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Resource fileTemplate = ctx.getResource("file:src/main/resources/ticketData.txt");
        List<String> ticketData = FileParser.fileAsList(fileTemplate);

        for(String s : ticketData) {
            System.out.println(s);
        }
        AuthorService authorService = new AuthorService(new AuthorDAOImpl(ctx.getBean(org.hibernate.SessionFactory.class)));
        BookService bookService = new BookService(new BookDAOImpl(ctx.getBean(org.hibernate.SessionFactory.class)));

        Author author1 = new Author(1,"Steven King", "USA");
        authorService.saveAuthor(author1);

        Book book1 = new Book(1, "It", author1, Book.Genre.Horror, 1);
        bookService.saveBook(book1);

    }
}
