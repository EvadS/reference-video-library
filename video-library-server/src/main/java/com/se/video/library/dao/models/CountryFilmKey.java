package com.se.video.library.dao.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CountryFilmKey implements Serializable {

    @Column(name = "country_id")
    protected Long countryId;

    @Column(name = "film_id")
    protected Long filmId;
}