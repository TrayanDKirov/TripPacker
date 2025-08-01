package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.user.UserCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

import java.util.List;;

public interface UserService {
    Long createdUser(UserCreateDto userDto);

    List<User> getUsers();
}
