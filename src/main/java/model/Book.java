package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=1)
    @Column(name="id")
    private Integer id;

    @Setter
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Setter
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "shelf_number")
    private int shelfNumber;

    public Book(String title) {
        this.title = title;
    }
    public Book(int id, String title, Author author, Genre genre, int shelfNumber) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.shelfNumber = shelfNumber;
        author.getBooks().add(this);
    }

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    public enum Genre {
        Fantasy,
        Mystery,
        Horror,
        Adventure
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }
}
