package io.api.base.repository;

import io.api.base.domain.Ocupacion;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@RepositoryRestResource(collectionResourceRel = "ocupaciones", path = "ocupaciones")
public interface OcupacionRepository extends PagingAndSortingRepository<Ocupacion,Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    // Prevents GET /people/:id

    @RestResource(exported = true)
    public Ocupacion findById(String id);

    // Prevents GET /people
//    @Override
//    @RestResource(exported = false)
//    public Page<Category> findAll(Pageable pageable);

    //Prevents POST /people and PATCH /people/:id
//    @Override
//    @RestResource(exported = false)
//    public Ocupacion save(Ocupacion s);

//    // Prevents DELETE /people/:id
//    @Override
//    @RestResource(exported = false)
//    public void delete(Category t);

    @Configuration
    static class RepositoryConfig implements RepositoryRestConfigurer {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
            config.exposeIdsFor(Ocupacion.class);
        }
    }

}

