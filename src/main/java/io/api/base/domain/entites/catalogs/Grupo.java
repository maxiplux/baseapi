package io.api.base.domain.entites.catalogs;

import io.api.base.domain.entites.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Grupo extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name="grupo_id",        sequenceName="grupo_id",        allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,        generator="grupo_id")
    @Column(name = "id", nullable = false)
    private Long id;





}
