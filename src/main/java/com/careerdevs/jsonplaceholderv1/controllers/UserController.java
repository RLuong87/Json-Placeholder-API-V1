package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserController {

    public static final String JPURL = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/users")
    public Object allUsers(RestTemplate restTemplate) {
        String URL = JPURL + "users";
        return restTemplate.getForObject(URL, User[].class);
    }

    @GetMapping("/users/{id}")
    public Object getUser(RestTemplate restTemplate, @PathVariable(name = "id") String id) {
        String URL = JPURL + "users/" + id;
        try {

            return restTemplate.getForObject(URL, User.class);

        } catch (HttpClientErrorException.NotFound e) {

            return "ID did not match a user in the database";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    @GetMapping("/users")
    public Object userRange(RestTemplate restTemplate,
                            @RequestParam(name = "id") String startId,
                            @RequestParam(name = "id") String endId) {

        ArrayList<User> allUsers = new ArrayList<>();

        int start = Integer.parseInt(startId), end = Integer.parseInt(endId);

        for (int i = start; i <= end; i++) {

            String URL = JPURL + "users/" + i + end;
            User users = restTemplate.getForObject(URL, User.class);
            Collections.addAll(allUsers, users);
        }
        return allUsers;
    }
}
