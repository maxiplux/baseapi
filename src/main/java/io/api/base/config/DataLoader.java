package io.api.base.config;

import io.api.base.domain.Pais;
import io.api.base.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    private EasyRandom factory;



    @Autowired
    private PaisRepository paisRepository;



    private void createPais()
    {
        Pais pais = factory.nextObject(Pais.class);

        this.paisRepository.save(pais);

    }

    private void main()
    {
        //createPais();
        //createPais();

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createFactory();
       main();
    }


    private void createFactory() {
        EasyRandomParameters parameters = new EasyRandomParameters()
            .seed(123L)
            .objectPoolSize(100)
            .randomizationDepth(3)
            .charset(StandardCharsets.UTF_8)
            .stringLengthRange(5, 50)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .overrideDefaultInitialization(false)
            .ignoreRandomizationErrors(true);
        factory = new EasyRandom(parameters);
    }
}
