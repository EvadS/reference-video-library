package com.se.video.library.dao.repository;

import com.se.video.library.dao.models.Country;
import com.se.video.library.dao.models.Film;
import com.se.video.library.dao.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film>  {
    Optional<Film> findByName(String name);
    Optional<Film> findByNameAndIdNot(String name , Long id);


    List<Film> findByCountriesContains(Country country);
    List<Film> findByGenresContains(Genre genre);
}

