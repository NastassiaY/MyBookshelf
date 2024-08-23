package model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "author_seq", allocationSize=1)
    @Column(name= "id")
    private Integer id;

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
    public Author(int id, String name, String country) {
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
