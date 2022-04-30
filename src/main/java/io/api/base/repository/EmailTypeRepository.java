package io.api.base.repository;


import io.api.base.domain.EmailType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "batch-email-type", path = "batch-email-type")
public interface EmailTypeRepository extends PagingAndSortingRepository<EmailType, Long> {

    public EmailType findByNameEquals(String name);



}
