package com.se.video.library.model.repository;

import com.se.video.library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByName(String name);
}

