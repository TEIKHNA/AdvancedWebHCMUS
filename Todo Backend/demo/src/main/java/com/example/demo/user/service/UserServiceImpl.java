package com.example.demo.user.service;

import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.mapper.TaskMapper;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserDto;
import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.utils.TaskComparator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {

       return userRepository.findByUsername(username);

    }

    @Override
    public User findByUserId(UUID userId) {

        return userRepository.findByUserId(userId);
    }

    @Override
    public UserResponseDto getUserByUserId(UUID userId) {

        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + userId + " not found");
        }

        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);

        List<TaskDto> tasks = userDto.getTasks();

        Collections.sort(tasks, new TaskComparator());

        userDto.setTasks(tasks);

        return new UserResponseDto(userDto, "User " + userId + " has been found successfully!");
    }


}
