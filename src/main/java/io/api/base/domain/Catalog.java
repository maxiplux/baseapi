package io.api.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Catalog extends  Auditable<String> {
    private String nombre;
    private  Boolean activo = true;
}
