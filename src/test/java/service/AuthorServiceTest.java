package service;

import org.springframework.boot.test.context.SpringBootTest;

import model.Author;
import repository.AuthorRepository;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@SpringBootTest
public abstract class AuthorServiceTest {

    @Mock
    protected AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    Author author;

    @BeforeEach
    void init() {
        author = new Author(1L, "Uladzimir Karatkevich", "Belarus");
    }

}
