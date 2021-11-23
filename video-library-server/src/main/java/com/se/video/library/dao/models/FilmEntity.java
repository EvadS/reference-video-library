package com.se.video.library.dao.models;


import com.se.video.library.dao.models.base.DateAuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "films")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FilmEntity  extends DateAuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

/// название фильма
    /*

    студия
    жанр
    год выпуска
    режисер
    в главных ролях
    краткое описание

     */

    /**
     * наличие в видео теке
     * дата выдачи и дата возврата
     */
}
