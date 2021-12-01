package com.careerdevs.jsonplaceholderv1.controllers;

import com.careerdevs.jsonplaceholderv1.models.UserPhotos;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class PhotoController {

    @GetMapping("/photos")
    public Object getPhotos(RestTemplate restTemplate) {
        String URL = UserController.JPURL + "photos";
        return restTemplate.getForObject(URL, UserPhotos[].class);
    }

    @GetMapping("/photos/{id}")
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

//    @GetMapping("/photos")
//    public Object userRange(RestTemplate restTemplate,
//                            @RequestParam(name = "id") String start,
//                            @RequestParam(name = "id") String end) {
//        String URL = UserController.JPURL + "users" + start + end;
//        return restTemplate.getForObject(URL, UserPhotos[].class);
//    }

}
