package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Author")

@Getter
@Setter
@NoArgsConstructor

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "author_seq", allocationSize=1)
    @Column(name= "id")
    private Long id;

    @Setter
    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, targetEntity = Book.class, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Author(String name) {
        this.name = name;
    }
    public Author(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
