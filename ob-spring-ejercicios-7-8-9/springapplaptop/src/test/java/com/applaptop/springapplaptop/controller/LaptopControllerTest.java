package com.applaptop.springapplaptop.controller;

import com.applaptop.springapplaptop.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void hello() {
        ResponseEntity<String>  response = testRestTemplate.getForEntity("/hello",String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Hello my friend!",response.getBody());

    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

       List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void findOneById() {
       ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
       assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                     "brand": "Lenovo creado desde Spring Test",
                     "model": "Legión 5",
                     "processor": "AMD RYZEN R5",
                     "hardDrive": 512,
                     "ram": 8,
                     "screenSize": 15.6,
                     "operatingSystem": "Windows 11 Home - Español",
                     "madeIn": "China"
                 }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("Lenovo creado desde Spring Test",result.getBrand());
    }


    @Test
    void update() {
         create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String jsonup = """
                {
                    "id": 1,
                    "brand": "Lenovo",
                    "model": "Legión 5",
                    "processor": "AMD RYZEN R5",
                    "hardDrive": 512,
                    "ram": 8,
                    "screenSize": 15.6,
                    "operatingSystem": "Windows 11 Home - Español",
                    "madeIn": "China"
                }
                """;

        HttpEntity<String> requestup = new HttpEntity<>(jsonup,headers);
        ResponseEntity<Laptop> responseup = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT,requestup,Laptop.class);
        Laptop result = responseup.getBody();
        System.out.println(result.getBrand());

        assertEquals(1L,result.getId());
        assertEquals("Lenovo",result.getBrand());
    }

    @Test
    void delete() {
        create();
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1",HttpMethod.DELETE,null,Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAll() {
         ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.DELETE,null,Laptop.class);
         assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}