package service;

import org.springframework.boot.test.context.SpringBootTest;

import model.Author;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@SpringBootTest
public class AuthorServiceDeleteTest extends AuthorServiceTest {

    @Test
    public void deleteAuthor_authorExist() {
        doNothing().when(authorRepository).delete(author);

        authorService.deleteAuthor(author);

        verify(authorRepository, times(1)).delete(author);
    }

    @Test
    public void deleteAuthor_authorNotExist_throwIllegalArgumentException() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
        doNothing().when(authorRepository).delete(author);

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.deleteAuthor(author);
        });

        verify(authorRepository, never()).delete(any(Author.class));
    }

    @Test
    public void deleteAuthor_authorIsNull_throwIllegalArgumentException() {
        doNothing().when(authorRepository).delete(author);
        author = null;

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.deleteAuthor(author);
        });

        verify(authorRepository, never()).delete(any(Author.class));
    }
}
