package io.api.base.controllers;


import io.api.base.BaseapiApp;
import io.api.base.domain.*;
import io.api.base.domain.entites.dto.BatchEmailDto;
import io.api.base.domain.entites.dto.EmailImagePathDto;
import io.api.base.repository.*;
import io.api.base.security.AuthoritiesConstants;
import io.api.base.security.SecurityUtils;
import io.api.base.security.jwt.TokenProvider;
import io.api.base.service.BatchEmailServices;
import io.api.base.service.dto.TokenDto;
import io.api.base.web.rest.errors.ExceptionTranslator;
import io.api.base.web.rest.vm.LoginVM;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.wildfly.common.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT, classes = BaseapiApp.class)
@Slf4j
class BatchEmailControllerTest {
    @LocalServerPort
    int randomServerPort;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc mockMvc;

    @Autowired
    private TokenProvider tokenProvider;



    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmailTypeRepository emailTypeRepository;


    @Autowired
    private BatchEmailServices batchEmailServices;

    @Autowired
    private EmailDataSourceRepository emailDataSourceRepository;

    @Value("${template.base64}")
    private  String templateBase64;

    @Value("${image.base64}")
    private  String imageBase64;

    @Autowired
    private BatchEmailRepository batchEmailRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    private String authToken;
    @BeforeEach
    void setUp() {
        this.batchEmailRepository.deleteAll();
        this.userRepository.deleteAll();
        this.authorityRepository.deleteAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        User user = new User();
        user.setLogin("user-jwt-controller");
        user.setEmail("user-jwt-controller@example.com");
        user.setActivated(true);
        user.setPassword(passwordEncoder.encode("test"));

        Set<Authority> authorities= new HashSet<>();
        authorities.add(this.authorityRepository.save(Authority.builder().name(AuthoritiesConstants.ADMIN).build()));
        authorities.add(this.authorityRepository.save(Authority.builder().name(AuthoritiesConstants.USER).build()));
        user.setAuthorities(authorities);
        userRepository.saveAndFlush(user);

        LoginVM login = new LoginVM();
        login.setUsername("user-jwt-controller");
        login.setPassword("test");

        HttpEntity<?> request = new HttpEntity<LoginVM>(login, headers);
        ResponseEntity<TokenDto> response = restTemplate.exchange("/api/authenticate", HttpMethod.POST, request, TokenDto.class);

        this.authToken="Bearer "+response.getBody().getId_token();


    }

    @AfterEach
    void tearDown() {
    }





   // @Test

    public void doLoginTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        User user = new User();
        user.setLogin("user-jwt-controller");
        user.setEmail("user-jwt-controller@example.com");
        user.setActivated(true);
        user.setPassword(passwordEncoder.encode("test"));

        Set<Authority> authorities= new HashSet<>();
        authorities.add(this.authorityRepository.save(Authority.builder().name(AuthoritiesConstants.ADMIN).build()));
        authorities.add(this.authorityRepository.save(Authority.builder().name(AuthoritiesConstants.USER).build()));
        user.setAuthorities(authorities);
        userRepository.saveAndFlush(user);

        LoginVM login = new LoginVM();
        login.setUsername("user-jwt-controller");
        login.setPassword("test");

        HttpEntity<?> request = new HttpEntity<LoginVM>(login, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/authenticate", HttpMethod.POST, request, String.class);
        Assert.assertNotNull(response.getBody().toString());


        //   this.token = response.getBody().getAccess_token();
    }

    @Test
    void update_DIARIO()
    {
        this.update(EmailTypeEnum.DIARIO);
    }

    @Test
    void update_MENSUAL()
    {
        this.update(EmailTypeEnum.MENSUAL);
    }

    @Test
    void update_UNICA_VEZ()
    {
        this.update(EmailTypeEnum.UNICA_VEZ);
    }


    @Test
    void create_MENSUAL()
    {
        this.create(EmailTypeEnum.MENSUAL);
    }

    @Test
    void create_UNICA_VEZ()
    {
        this.create(EmailTypeEnum.UNICA_VEZ);
    }

    @Test
    void create_DIARIO()
    {
        this.create(EmailTypeEnum.DIARIO);
    }


    private BatchEmailDto buildBatchDtoEmail(EmailTypeEnum emailTypeEnum){
        LocalDate cronDate = LocalDate.of(2090,04,26);
        LocalTime cronTime = LocalTime.of(23,30,0);

        EmailType emailTyp=this.emailTypeRepository.findByNameEquals(emailTypeEnum.name());
        EmailDataSource emailDataSource=StreamSupport.stream(emailDataSourceRepository.findAll().spliterator(),false).findFirst().get();

        return          BatchEmailDto.builder()
                .emailTypeId(emailTyp.getId())
                .name("SimpleBirthday"+emailTypeEnum.name()+ UUID.randomUUID().toString())
                .start(LocalDateTime.now())
                .end(LocalDateTime.MAX)
                .testMode(false)
                .testEmails(Arrays.asList("maxiplux@gmail.com","root@example.com"))
                .subject("Cumpleanios")
                .cronDate(cronDate)
                .cronTime(cronTime)
                .template(templateBase64)
                .imagesDto(Arrays.asList(
                        EmailImagePathDto.builder().content(imageBase64).name("img1.png").build(),
                        EmailImagePathDto.builder().content(imageBase64).name("img2.jpg").build()
                ))
                .emailDataSourceId(emailDataSource.getId()).build();
    }
    void create(EmailTypeEnum emailTypeEnum) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        headers.add("Authorization", this.authToken);
        BatchEmailDto batchEmailDto= buildBatchDtoEmail(emailTypeEnum);
        HttpEntity<?> request = new HttpEntity<BatchEmailDto>(batchEmailDto, headers);
        ResponseEntity<BatchEmail> result = restTemplate.postForEntity("/api/v1/batch-email-handle/", request, BatchEmail.class);
        assertEquals(200, result.getStatusCodeValue());
    }


    void update(EmailTypeEnum emailTypeEnum) {

        BatchEmailDto batchEmailDto= buildBatchDtoEmail(  emailTypeEnum);
        BatchEmail batchEmail=this.batchEmailServices.createEmailBatch(batchEmailDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json " );
        headers.add("Authorization", this.authToken);
        Map<String, String> param = new HashMap<String, String>();
        param.put("id",batchEmail.getId().toString());
        HttpEntity<?> request = new HttpEntity<BatchEmailDto>(batchEmailDto, headers);
        final String url = String.format("/api/v1/batch-email-handle/{id}");
        ResponseEntity<BatchEmail> response = restTemplate.exchange(url, HttpMethod.PUT, request, BatchEmail.class, param);
        assertEquals(200, response.getStatusCodeValue());
    }
}
