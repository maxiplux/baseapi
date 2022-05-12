package io.api.base.service.generics.impl;


import io.api.base.domain.Pais;
import io.api.base.repository.PaisRepository;
import io.api.base.service.generics.PaisServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class PaisServicesImpl extends CrudServicesImpl<Pais> implements PaisServices<Pais> {

    @Autowired
    private PaisRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Pais> UpdateById(long id, Pais element) {
        Optional<Pais> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Pais currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Pais) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
