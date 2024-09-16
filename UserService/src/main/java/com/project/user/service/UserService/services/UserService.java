package com.project.user.service.UserService.services;

import com.project.user.service.UserService.entities.User;

import java.util.List;

public interface UserService
{
    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);
}
