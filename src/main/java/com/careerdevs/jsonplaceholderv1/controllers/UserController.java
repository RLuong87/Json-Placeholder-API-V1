package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.UserResponse;
import org.apache.catalina.User;
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
        return restTemplate.getForObject(URL, UserResponse.class).getUsers();
    }

    @GetMapping("/users/{id}")
    public Object getUser(RestTemplate restTemplate,
                          @PathVariable(name = "id") String id) {
        String URL = JPURL + "users" + id;
        try {

            return restTemplate.getForObject(URL, UserResponse.class).toString();

        } catch (HttpClientErrorException.NotFound e) {

            return "ID did not match a user in the database";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/photos")
    public Object getPhotos(RestTemplate restTemplate) {
        String URL = JPURL + "photos";
        return restTemplate.getForObject(URL, Object.class);
    }

    @GetMapping("/photos/{id}")
    public Object getPhotoID(RestTemplate restTemplate,
                             @PathVariable(name = "id") String id) {
        String URL = JPURL + "photos" + id;
        try {

            return restTemplate.getForObject(URL, Object.class);

        } catch (HttpClientErrorException.NotFound e) {

            return "ID did not match a user in the database";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

}
