package com.se.video.library.model;



import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "films")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Film  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique=true)
    private String name;

    @NonNull
    private int year;

    @NotBlank
    private String director;

    @NotBlank
    private String smallDescription;

    @Lob
    private String description;

    @Min(1)
    private int duration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "country_films",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "country_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Country> countries = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "genre_films",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Genre> genres = new HashSet<>();



    public void removeCountry(Country book) {
        this.countries.remove(book);
        book.getFilms().remove(this);
    }

    public void removeGenre(Genre book) {
        this.genres.remove(book);
        book.getFilms().remove(this);
    }

    public void addCountry(Country country) {
        countries.add(country);
        country.getFilms().add(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getFilms().add(this);
    }
}