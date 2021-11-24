package com.se.video.library.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @OneToMany(mappedBy = "film")
    private Set<CountryFilm> countryFilms = new HashSet<>();

    public void addChild(CountryFilm countryFilm) {
        this.countryFilms.add(countryFilm);
        countryFilm.setCountry(this);
    }

}
