package com.se.video.library.dao.models;


import com.se.video.library.dao.models.base.DateAuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Film extends DateAuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @NonNull
    private int year;

    @NotBlank
    private String director;

    @NotBlank
    private String smallDescription;

    private String description;

    @Min(1)
    private int duration;

    @OneToMany(mappedBy = "film")
    Set<CountryFilm> countryFilms = new HashSet<>();

    public void addChild(CountryFilm countryFilm) {
        this.countryFilms.add(countryFilm);
        countryFilm.setFilm(this);
    }
}
