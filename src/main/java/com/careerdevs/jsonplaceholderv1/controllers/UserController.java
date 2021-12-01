package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public static final String JPURL = "https://jsonplaceholder.typicode.com/";

    @GetMapping("/")
    public Object allUsers(RestTemplate restTemplate) {
        String URL = JPURL + "users";
        return restTemplate.getForObject(URL, User[].class);
    }

    @GetMapping("/{id}")
    public Object getUser(RestTemplate restTemplate, @PathVariable String id) {
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


    @GetMapping("/range")
    public Object userRange(RestTemplate restTemplate,
                                     @RequestParam(name = "start") String start,
                                     @RequestParam (name = "end") String end) {
        ArrayList<User> allUsers = new ArrayList<>();

        int startId = Integer.parseInt(start), endId = Integer.parseInt(end);

        for (int i = startId; i <= endId; i++) {

            String URL = JPURL + "users/";
            User[] users = restTemplate.getForObject(URL, User[].class);
            allUsers.addAll(List.of(users));
        }
        return allUsers;
    }


//    Get a user by ID (GET)
//    Create a new user (POST)
//    Update a user by ID (PUT)
//    Delete a user by ID (DELETE)


    // Post Method
    @PostMapping("/post")
    public User newUser(RestTemplate restTemplate, @RequestBody User user) {

        String URL = JPURL + "users/";
        return restTemplate.postForObject(URL, user, User.class);
    }

    // Put Method
    @PutMapping("/put/{id}")
    public Object putUser(RestTemplate restTemplate, @PathVariable String id,
                          @RequestBody User user) {

        String URL = JPURL + "users/" + id;
        try {

            restTemplate.put(URL, user);
            return "Successfully updated user: " + id;

        } catch (HttpClientErrorException.NotFound e) {

            return "ID not found";

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    // Delete Method
    @DeleteMapping("/delete/{id}")
    public String deleteUser(RestTemplate restTemplate, @PathVariable String id) {

        String URL = JPURL + "users/" + id;

        try {
            restTemplate.delete(URL);

            return "Successfully deleted a user: " + id;


        } catch (HttpClientErrorException.NotFound e) {

            return "ID not found";

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

}
