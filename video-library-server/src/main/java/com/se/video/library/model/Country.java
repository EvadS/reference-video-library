package com.se.video.library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country")
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


    //разные люди исполняют разные композиции
    @ManyToMany(mappedBy = "countries",fetch = FetchType.EAGER)
    private List<Film> films = new ArrayList<>();

}
