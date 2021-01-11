package app;

import app.model.User;
import app.service.RestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@Configuration + @EnableAutoConfiguration + @ComponentScan
public class Application {


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .run(args);

        RestServiceImpl restService = new RestServiceImpl();

        String session = restService.getAllUsers();
        System.out.print(restService.addUser(new User(3, "James", "Brown",  21), session));
        System.out.print(restService.changeUser(new User(3, "Thomas", "Shelby",  21), session));
        System.out.println(restService.deleteUser(new User(3, "Thomas", "Shelby",  21), session));

    }

}
