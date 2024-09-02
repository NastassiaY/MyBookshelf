package service;

import org.springframework.boot.test.context.SpringBootTest;

import configuration.ThisIsMyFirstConditionalBean;
import model.Author;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class AuthorServiceSaveTest extends AuthorServiceTest {

    @Mock
    protected ThisIsMyFirstConditionalBean timfcb;

    @Test
    public void saveAuthor_validAuthor_returnAuthorObject() {
        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.saveAuthor(author);

        assertNotNull(savedAuthor);
        verify(authorRepository).save(author);
    }

    @Test
    public void saveAuthor_authorIsNull_throwIllegalArgumentException() {
        doNothing().when(authorRepository.save(author));
        author = null;

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.saveAuthor(author);
        });

        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    public void saveAuthor_authorExists_throwIllegalArgumentException() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        doNothing().when(authorRepository.save(author));

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.saveAuthor(author);
        });

        verify(authorRepository, never()).save(any(Author.class));
    }
}
