package com.careerdevs.jsonplaceholderv1.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private ArrayList<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
