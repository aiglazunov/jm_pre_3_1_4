package app.service;

import app.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class RestServiceImpl implements RestService{

    static final String URL_USERS = "http://91.241.64.178:7081/api/users";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    @Override
    public String getAllUsers() {
        ResponseEntity<String> response  = restTemplate.getForEntity(URL_USERS, String.class);
        return response.getHeaders().get("Set-Cookie").get(0);
    }

    @Override
    public String addUser(User user, String session) {
        httpHeaders.add("Cookie", session );
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_USERS, requestBody, String.class);
        return responseEntity.getBody();

    }

    @Override
    public String changeUser(User user, String session) {
        httpHeaders.add("Cookie", session );
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(URL_USERS, HttpMethod.PUT, requestBody, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String deleteUser(User user, String session) {
        httpHeaders.add("Cookie", session );
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(URL_USERS + "/3", HttpMethod.DELETE, requestBody, String.class);
        return responseEntity.getBody();
    }
}