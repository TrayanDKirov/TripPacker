package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.user.UserCreateDto;

public interface UserService {
    Long createdUser(UserCreateDto userDto);
}
