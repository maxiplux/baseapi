package io.api.base.repository;


import io.api.base.domain.EmailDataSource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "batch-email-data-source", path = "batch-email-data-source")
public interface EmailDataSourceRepository extends PagingAndSortingRepository<EmailDataSource, Long> {

}
