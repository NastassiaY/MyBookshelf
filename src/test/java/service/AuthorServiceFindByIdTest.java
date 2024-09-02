package service;

import org.springframework.boot.test.context.SpringBootTest;

import model.Author;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorServiceFindByIdTest extends AuthorServiceTest {

    @Test
    public void findAuthor_validArgument_returnAuthorObject() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> fetchedAuthor = authorService.findAuthor(1L);

        assertNotNull(fetchedAuthor);
        assertEquals(1L, fetchedAuthor.get().getId());
        assertEquals(author.getName(), fetchedAuthor.get().getName());
        verify(authorRepository).findById(1L);
    }

    @Test
    public void findAuthor_authorNotExist_returnNull() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Author> fetchedAuthor = authorService.findAuthor(1L);

        assertFalse(fetchedAuthor.isPresent());
        verify(authorRepository).findById(1L);
    }
}
