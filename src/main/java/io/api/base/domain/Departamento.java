package io.api.base.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONFIG_DEPARTAMENTO")
@EqualsAndHashCode(callSuper = false)
@Builder
public class Departamento extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name = "departamento_seq",
        sequenceName = "departamento_seq",
        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "departamento_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;


}
