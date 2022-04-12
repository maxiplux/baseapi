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
public class FormaDePago extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name="forma_de_pago_seq",        sequenceName="forma_de_pago_seq",        allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,        generator="forma_de_pago_seq")

    @Column(name = "id", nullable = false)
    private Long id;





}
