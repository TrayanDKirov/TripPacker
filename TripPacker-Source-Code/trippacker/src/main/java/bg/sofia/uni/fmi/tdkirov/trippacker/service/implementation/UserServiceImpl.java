package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.user.UserCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Override
    public Long createdUser(UserCreateDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword());

        return repository.save(user).getId();
    }

    @Override
    public List<User> getUsers() {

        return List.copyOf(repository.findAll());
    }
}
