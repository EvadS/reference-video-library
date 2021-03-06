package com.se.video.library.dao.repository;

import com.se.video.library.dao.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByName(String name);

    List<Genre> findByNameAndIdNot(String name, Long id);

    List<Genre> findByNameContaining(String name);
}

