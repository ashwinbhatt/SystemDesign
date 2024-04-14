package com.ashwinbhatt.systemdesign.movieticketbooking.service;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.UserServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.User;
import com.ashwinbhatt.systemdesign.movieticketbooking.repositories.UserRepo;
import lombok.NonNull;

public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(@NonNull String userId) throws UserServiceException {
        User user = userRepo.getUser(userId);
        if(user == null) {
            throw new UserServiceException(String.format("No user found with id: <%s>", userId));
        }
        return user;
    }

    public User createUser(@NonNull User user) throws UserServiceException {
        User dbUser = userRepo.getUser(user.getUserId());
        if(dbUser != null) {
            throw new UserServiceException(String.format("User with id: <%s>, already exists", user.getUserId()));
        }
        return userRepo.createUser(user);
    }
}
