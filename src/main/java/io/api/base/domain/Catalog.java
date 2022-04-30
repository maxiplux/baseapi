package io.api.base.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Catalog extends  Auditable<String> {
    public String nombre;
    private  Boolean activo = true;
}
