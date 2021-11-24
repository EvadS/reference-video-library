package com.se.video.library.dao.models;

import javax.persistence.*;

@Entity
public class CountryFilm {

    @EmbeddedId
    CountryFilmKey id;

    @ManyToOne
    @MapsId("countryId")
    @JoinColumn(name = "country_id",
            referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id",
            referencedColumnName = "id")
    private Film film;
}