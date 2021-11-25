package com.se.video.library.model;


import com.se.video.library.model.base.DateAuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "films")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Film extends DateAuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique=true)
    private String name;

    @NonNull
    private int year;

    @NotBlank
    private String director;

    @NotBlank
    private String smallDescription;

    @Lob
    private String description;

    @Min(1)
    private int duration;

    //исполнители могут исполнять разные песни
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Country> countries = new ArrayList<>();
//

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "country_films",
            joinColumns = {
                    @JoinColumn(name = "film_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "country_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Country> countries = new HashSet<>();


    public void removeChild(Country tag) {
        countries.remove(tag);
        tag.setPosts(null);
    }

    public void addChild(Country tag) {
        countries.add(tag);
        tag.getPosts().add(this);
    }
}
