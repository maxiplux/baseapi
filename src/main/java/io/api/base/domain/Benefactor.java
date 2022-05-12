package io.api.base.domain;

import lombok.*;
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
@Table(name = "CORE_BENEFACTOR")
@EqualsAndHashCode(callSuper = false)
public class Benefactor extends Auditable<String> implements Serializable {
    @Id
    @SequenceGenerator(name = "benefactor_seq", sequenceName = "benefactor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "benefactor_seq")

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
    @JoinColumn(name = "lugar_de_nacimiento_ID")
    private Municipio LugarDeNacimiento;

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
