package com.se.video.library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique=true)
    private String name;

//    @OneToMany(mappedBy = "country")
//    private Set<CountryFilm> countryFilms = new HashSet<>();


//    //разные люди исполняют разные композиции
//    @ManyToMany(mappedBy = "countries",fetch = FetchType.EAGER)
//    private List<Film> films = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "countries")
    private Set<Film> posts = new HashSet<>();
}
