package com.se.video.library.model.repository;

import com.se.video.library.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film>  {
    Optional<Film> findByName(String name);
    Optional<Film> findByNameAndIdNot(String name , Long id);
}

