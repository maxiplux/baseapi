package io.api.base.service.impl;


import io.api.base.domain.BatchEmail;
import io.api.base.domain.entites.dto.BatchEmailDto;
import io.api.base.repository.BatchEmailRepository;
import io.api.base.repository.EmailDataSourceRepository;
import io.api.base.repository.EmailTypeRepository;
import io.api.base.service.BatchEmailServices;
import io.api.base.service.JobServices;
import io.api.base.service.StorageService;
import io.api.base.utilities.BatchEmailMapper;
import io.api.base.utilities.Utility;
import io.api.base.web.rest.errors.CronException;
import io.api.base.web.rest.errors.EntityFoundException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BatchEmailServicesImpl implements BatchEmailServices {



    @Autowired
    private JobServices jobServices;

    @Autowired
    private EmailTypeRepository emailTypeRepository;

    @Autowired
    private BatchEmailRepository batchEmailRepository;

    @Autowired
    private EmailDataSourceRepository emailDataSourceRepository;


    @Autowired
    private BatchEmailMapper batchEmailMapper;

    @Autowired
    private StorageService storageService;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = CronException.class)
    public BatchEmail createEmailBatch(BatchEmailDto batchEmailDto) {

        BatchEmail batchEmail= batchEmailMapper.batchEmailDtoToBatchEmail(batchEmailDto);
        //todo clean  rename previous template name

        if (batchEmailDto.getTemplate()!=null)
        {

            Optional<String> optionalTemplate=this.storageService.saveFile(UUID.randomUUID().toString()+".ftlh",batchEmailDto.getTemplate());
            if(optionalTemplate.isPresent())
            {
                batchEmail.setTemplatePath(optionalTemplate.get());
            }

        }

//        if (batchEmailDto.getImagesDto()!=null)
//        {
//
//
//            batchEmail.setImages(
//                        batchEmailDto.getImagesDto().stream().map(emailImagePathDto ->this.storageService.saveImage(emailImagePathDto.getName(),emailImagePathDto.getContent())
//                            ).filter(currentPathImage->   currentPathImage.isPresent())
//                             .map(currentPathImage->currentPathImage.get()).collect(Collectors.toList())
//            );
//
//
//        }
        batchEmail.setEmailDataSource(this.emailDataSourceRepository.findById(batchEmailDto.getEmailDataSourceId()).orElseThrow(()-> new EntityFoundException("Invalid Datasource Id")));
        batchEmail.setEmailType(this.emailTypeRepository.findById(batchEmailDto.getEmailTypeId()).orElseThrow(()-> new EntityFoundException("Invalid Email Type Id")));
        batchEmail=this.batchEmailRepository.save(batchEmail);
        try {
            String cronExpression= Utility.buildCron(batchEmail.getCronDate(),batchEmail.getCronTime(),batchEmail.getEmailType().getName());
            if (Utility.isValidCronExpression(cronExpression))
            {
                this.jobServices.scheduleJob(batchEmail.getEmailName(),
                        cronExpression
                );
            }
            else {
                throw  new CronException("Fail in Schedule Job");
            }

        } catch (SchedulerException e) {
            log.error("createEmailBatch -> SchedulerException {}", e.getMessage());
            throw  new CronException("Fail in Schedule Job");
        }


        return batchEmail;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = CronException.class)
    public BatchEmail updateEmailBatch(Long Id, BatchEmailDto batchEmailDto) {

        BatchEmail batchEmail= this.batchEmailRepository.findById(Id).orElseThrow(()-> new EntityFoundException("Invalid BatchEmail Id"));
        String oldCronName=batchEmail.getEmailName();

        this.batchEmailMapper.updateBatchEmailFromBatchEmailDto(batchEmailDto,batchEmail);


        if (batchEmailDto.getTemplate()!=null)
        {

            Optional<String> optionalTemplate=this.storageService.saveFile(UUID.randomUUID().toString()+".ftlh",batchEmailDto.getTemplate());
            if(optionalTemplate.isPresent())
            {
                batchEmail.setTemplatePath(optionalTemplate.get());
            }

        }

//        if (batchEmailDto.getImagesDto()!=null)
//        {
//
//
//            batchEmail.setImages(
//                    batchEmailDto.getImagesDto().stream().map(emailImagePathDto ->this.storageService.saveImage(emailImagePathDto.getName(),emailImagePathDto.getContent())
//                            ).filter(currentPathImage->   currentPathImage.isPresent())
//                            .map(currentPathImage->currentPathImage.get()).collect(Collectors.toList())
//            );
//
//
//        }
        batchEmail.setEmailDataSource(this.emailDataSourceRepository.findById(batchEmailDto.getEmailDataSourceId()).orElseThrow(()-> new EntityFoundException("Invalid Datasource Id")));
        batchEmail.setEmailType(this.emailTypeRepository.findById(batchEmailDto.getEmailTypeId()).orElseThrow(()-> new EntityFoundException("Invalid Email Type Id")));
        batchEmail=this.batchEmailRepository.save(batchEmail);
        try {
            String cronExpression=Utility.buildCron(batchEmail.getCronDate(),batchEmail.getCronTime(),batchEmail.getEmailType().getName());
            if (Utility.isValidCronExpression(cronExpression))
            {
                this.jobServices.deleteJob(oldCronName);
                this.jobServices.scheduleJob(batchEmail.getEmailName(), cronExpression
                );
            }
            else {
                throw  new CronException("Fail in Schedule Job");
            }

        } catch (SchedulerException e) {
            log.error("createEmailBatch -> SchedulerException {}", e.getMessage());
            throw  new CronException("Fail in Schedule Job");
        }
        return  batchEmail;
    }
}
