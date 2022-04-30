package io.api.base.repository;


import io.api.base.domain.BatchEmail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "batch-email", path = "batch-emails")
public interface BatchEmailRepository extends PagingAndSortingRepository<BatchEmail, Long> {

}
