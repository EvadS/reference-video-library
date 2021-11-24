package com.se.video.library.model.repository;


import com.se.video.library.dao.models.CountryFilm;
import com.se.video.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryFilmRepository extends JpaRepository<CountryFilm, Long> {

    List<CountryFilm> getAllByFilmId(Long filmId);
}
