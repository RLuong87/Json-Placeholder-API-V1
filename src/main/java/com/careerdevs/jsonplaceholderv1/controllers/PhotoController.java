package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.User;
import com.careerdevs.jsonplaceholderv1.models.UserPhotos;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @GetMapping("/")
    public Object getPhotos(RestTemplate restTemplate) {
        String URL = UserController.JPURL + "photos";
        return restTemplate.getForObject(URL, UserPhotos[].class);
    }

    @GetMapping("/{id}")
    public Object getPhotoID(RestTemplate restTemplate,
                             @PathVariable String id) {
        String URL = UserController.JPURL + "photos/" + id;
        try {

            return restTemplate.getForObject(URL, UserPhotos.class);

        } catch (HttpClientErrorException.NotFound e) {

            return "ID did not match a user in the database";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    @GetMapping("/{start}/{end}")
    public Object photoRange(RestTemplate restTemplate,
                            @PathVariable(name = "start") String start, @PathVariable(name = "end") String end) {

        try {
            ArrayList<UserPhotos> allPhotos = new ArrayList<>();

            int startId = Integer.parseInt(start), endId = Integer.parseInt(end);

            for (int i = startId; i <= endId; i++) {

                String URL = UserController.JPURL + "photos/" + i;
                UserPhotos photos = restTemplate.getForObject(URL, UserPhotos.class);
                Collections.addAll(allPhotos, photos);

            }
            return allPhotos;

        } catch (HttpStatusCodeException e) {

            return "ID's out of range, please enter an ID between " + start + " and " + end;

        } catch (Exception e) {

            return "ID's do not match";
        }
    }


    // Post Method
    @PostMapping("/post")
    public UserPhotos postPhoto(RestTemplate restTemplate, @RequestBody User user) {

        String URL = UserController.JPURL + "photos/";
        return restTemplate.postForObject(URL, user, UserPhotos.class);
    }

    // Put Method
    @PutMapping("/put/{id}")
    public Object putPhoto(RestTemplate restTemplate, @PathVariable String id,
                           @RequestBody UserPhotos userPhotos) {

        String URL = UserController.JPURL + "photos/" + id;
        try {

            restTemplate.put(URL, userPhotos);

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
    public String deletePhoto(RestTemplate restTemplate, @PathVariable String id) {

        String URL = UserController.JPURL + "photos/" + id;

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
