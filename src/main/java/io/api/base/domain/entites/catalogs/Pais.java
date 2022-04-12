package io.api.base.domain.entites.catalogs;

import io.api.base.domain.entites.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Pais extends Catalog implements Serializable {
    @Id
    @SequenceGenerator(name="pais_seq",        sequenceName="pais_seq",        allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,        generator="pais_seq")

    @Column(name = "id", nullable = false)
    private Long id;





}
