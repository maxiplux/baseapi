package io.api.base.config;


import io.api.base.domain.EmailDataSource;
import io.api.base.domain.EmailType;
import io.api.base.domain.EmailTypeEnum;
import io.api.base.repository.BatchEmailRepository;
import io.api.base.repository.EmailDataSourceRepository;
import io.api.base.repository.EmailTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmailTypeRepository emailTypeRepository;

    @Autowired
    private BatchEmailRepository batchEmailRepository;

    @Autowired
    private EmailDataSourceRepository emailDataSourceRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.emailDataSourceRepository.save(
        EmailDataSource.builder().entityName("Correo Masivo")
                .query("Select * from customer")
                .name("correos masivos")
                .entityName("Benecfactor").build());


        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.DIARIO.name()).build());
        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.MENSUAL.name()).build());
        this.emailTypeRepository.save(EmailType.builder().name(EmailTypeEnum.UNICA_VEZ.name()).build());





    }
}
