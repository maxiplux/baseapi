package io.api.base.service.generics.impl;


import io.api.base.domain.Municipio;
import io.api.base.repository.MunicipioRepository;
import io.api.base.service.generics.MunicipioServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class MunicipioServicesImpl extends CrudServicesImpl<Municipio> implements MunicipioServices<Municipio> {

    @Autowired
    private MunicipioRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Municipio> UpdateById(long id, Municipio element) {
        Optional<Municipio> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Municipio currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Municipio) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
