package com.se.video.library.model;

import com.se.video.library.model.base.DateAuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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