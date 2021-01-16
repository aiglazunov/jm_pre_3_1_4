package app;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
//@Configuration + @EnableAutoConfiguration + @ComponentScan
public class Application {


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .run(args);

        final String uri = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntityGet = restTemplate.getForEntity(
                uri,
                String.class);

        System.out.println(responseEntityGet.getBody());

        StringBuilder sb = new StringBuilder();

        String cookies = (responseEntityGet.getHeaders().get("Set-Cookie").get(0));
        String[] cookie = cookies.split(";");


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", cookie[0]);


        HttpEntity<User> httpEntity1 = new HttpEntity<>(new User(3, "James", "Brown", 20), httpHeaders);

        ResponseEntity<String> responseEntityPost = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity1,
                String.class
        );

        sb.append(responseEntityPost.getBody());

        ResponseEntity<String> responseEntityPut = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                new HttpEntity<>(new User(3, "Thomas", "Shelby", 20), httpHeaders),
                String.class
        );

        sb.append(responseEntityPut.getBody());

        ResponseEntity<String> responseEntityDelete = restTemplate.exchange(
                uri + "/3",
                HttpMethod.DELETE,
                new HttpEntity<>(httpHeaders),
                String.class
        );

        sb.append(responseEntityDelete.getBody());

        System.out.println(sb);

    }
}


