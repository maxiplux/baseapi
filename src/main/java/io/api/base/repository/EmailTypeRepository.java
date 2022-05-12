package io.api.base.repository;


import io.api.base.domain.EmailType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTypeRepository extends PagingAndSortingRepository<EmailType, Long> {
    EmailType findByNameEquals(String name);


}
