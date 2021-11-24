package com.se.video.library.dao.models;

import com.se.video.library.dao.models.base.DateAuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name = "genre")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends DateAuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique=true)
    private String name;

}