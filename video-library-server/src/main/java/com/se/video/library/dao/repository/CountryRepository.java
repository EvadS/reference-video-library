package com.se.video.library.dao.repository;

import com.se.video.library.dao.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByName(String name);

    List<Country> findByNameAndIdNot(String name, Long id);

    List<Country> findByNameContaining(String name);
}