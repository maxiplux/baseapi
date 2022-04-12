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
public class TipoBenefactor extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name="tipo_benefactor_seq",        sequenceName="tipo_benefactor_seq",        allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,        generator="tipo_benefactor_seq")

    @Column(name = "id", nullable = false)
    private Long id;

}
