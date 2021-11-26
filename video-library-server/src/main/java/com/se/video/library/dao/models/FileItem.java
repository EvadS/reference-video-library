package com.se.video.library.dao.models;


import com.se.video.library.dao.models.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class FileItem extends UserDateAudit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique=true)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(unique=true)
    private String filePath;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "files")
    private Set<Film> films = new HashSet<>();

}