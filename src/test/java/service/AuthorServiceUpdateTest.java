package service;

import org.springframework.boot.test.context.SpringBootTest;

import model.Author;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorServiceUpdateTest extends AuthorServiceTest {

    @Test
    public void updateAuthor_authorValid_authorUpdated() {
        doNothing().when(authorRepository.save(author));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        author.setName("Uladzimir Bykau");

        authorService.updateAuthor(author);
        Optional<Author> updatedAuthor = authorService.findAuthor(1L);

        assertEquals(updatedAuthor.get().getName(), "Uladzimir Bykau");
        verify(authorRepository).save(author);
    }

    @Test
    public void updateAuthor_authorIsNull_throwIllegalArgumentException() {
        doNothing().when(authorRepository.save(author));
        author = null;

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.updateAuthor(author);
        });

        verify(authorRepository, never()).save(any(Author.class));
    }

    @Test
    public void updateAuthor_authorNotExist_throwIllegalArgumentException() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
        doNothing().when(authorRepository.save(author));

        assertThrows(IllegalArgumentException.class, () -> {
            authorService.updateAuthor(author);
        });

        verify(authorRepository, never()).save(any(Author.class));
    }
}
