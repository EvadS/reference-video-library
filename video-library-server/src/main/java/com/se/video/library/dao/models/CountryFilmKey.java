package com.se.video.library.dao.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class CountryFilmKey implements Serializable {

    @Column(name = "country_id")
    protected Long countryId;

    @Column(name = "film_id")
    protected Long filmId;
}