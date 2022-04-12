package io.api.base.domain.entites;

import io.api.base.domain.Auditable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Catalog extends  Auditable<String> {
    public String name;
    private  Boolean activo = true;
}
