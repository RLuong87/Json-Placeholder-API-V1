package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class UserController {

    public static final String JPURL = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/users")
    public Object allUsers(RestTemplate restTemplate) {
        String URL = JPURL + "users";
        return restTemplate.getForObject(URL, UserResponse[].class);
    }

    @GetMapping("/users/{id}")
    public Object getUser(RestTemplate restTemplate,
                          @PathVariable(name = "id") String id) {
        String URL = JPURL + "users" + id;
        try {

            return restTemplate.getForObject(URL, UserResponse[].class);

        } catch (HttpClientErrorException.NotFound e) {

            return "ID did not match a user in the database";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
