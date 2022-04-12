package io.api.base.repository;

import io.api.base.domain.entites.Benefactor;
import io.api.base.domain.entites.Category;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@RepositoryRestResource(collectionResourceRel = "benefactores", path = "benefactores")
public interface BenefactorRepository extends PagingAndSortingRepository<Benefactor,Long> {




    @Configuration
    static class RepositoryConfig implements RepositoryRestConfigurer {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
            config.exposeIdsFor(Benefactor.class);
        }
    }

}

