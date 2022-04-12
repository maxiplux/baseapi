package io.api.base.domain.entites;

import io.api.base.domain.Auditable;
import io.api.base.domain.entites.catalogs.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Benefactor  extends Auditable<String>  implements Serializable {
    @Id
    @SequenceGenerator(name="benefactor_seq",
        sequenceName="benefactor_seq",
        allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator="benefactor_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Grupo grupo;

    @ManyToOne
    private Genero genero;

    @ManyToOne
    private FormaDePago formaDePago;

    @ManyToOne
    private Cuota cuota;

    @ManyToOne
    private TipoDocumento tipoDocumento;


    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Ocupacion ocupacion;


    private String razonSocial;
    private String primerNombre;
    private String segundoNombre;

    private String primerApellido;
    private String segundoSegundo;

    private String correo;
    private String codigo;
    private String telefono;
    private String celular;
    private String direccion;
    private String documento;

    private String observaciones;

    private String lugarNacimiento;

    private Double moraActual;


    private LocalDate fechaNacimiento;

    private Integer cantidadRecordatorios;
    private Boolean activo;








}
