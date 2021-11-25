package com.se.video.library.model;


import com.se.video.library.model.base.DateAuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Film extends DateAuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @NonNull
    private int year;

    @NotBlank
    private String director;

    @NotBlank
    private String smallDescription;

    private String description;

    @Min(1)
    private int duration;

//    @OneToMany(mappedBy = "film")
//    Set<CountryFilm> countryFilms = new HashSet<>();
//

    //исполнители могут исполнять разные песни
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Country> countries = new ArrayList<>();


    public void addChild(Country comment) {
        countries.add(comment);
      //  comment.setFilms(this);
    }

}
