package com.se.video.library.dao.models;


import com.se.video.library.dao.models.base.DateAuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Film extends DateAuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private int genre;

    private int year;

    private String director;

    private String smallDescription;

    private String description;

    private int duration;

    @OneToMany(mappedBy = "film")
    Set<CountryFilm> ratings;
}
