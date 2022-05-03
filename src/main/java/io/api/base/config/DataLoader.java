package io.api.base.config;

import io.api.base.domain.Departamento;
import io.api.base.domain.Municipio;
import io.api.base.domain.Pais;
import io.api.base.repository.DepartamentoRepository;
import io.api.base.repository.MunicipioRepository;
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



    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;


    private void main()
    {
        if (this.municipioRepository.count()==0)
        {
            Pais pais =  Pais.builder().id(1L).build();
            pais.setActivo(true);
            pais.setNombre("Colombia");
            pais=this.paisRepository.save(pais);

            Departamento departamento=Departamento.builder().pais(pais).build();
            departamento.setNombre("Valle del cauca");
            departamento.setActivo(true);
            departamento=this.departamentoRepository.save(departamento);
            Municipio municipio=Municipio.builder().departamento(departamento).build();
            municipio.setNombre("Palmira");
            this.municipioRepository.save(municipio);
        }



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
