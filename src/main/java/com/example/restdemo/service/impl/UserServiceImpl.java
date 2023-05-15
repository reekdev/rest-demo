package com.example.restdemo.service.impl;

import com.example.restdemo.entity.User;
import com.example.restdemo.exception.EmailAlreadyExistsException;
import com.example.restdemo.exception.InvalidEmailException;
import com.example.restdemo.exception.PhoneNumberAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.UserService;
import com.example.restdemo.util.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException(newUser.getEmail());
        }

        if (!EmailValidator.isEmailValid(newUser.getEmail())) {
            throw new InvalidEmailException(newUser.getEmail());
        }

        if (userRepository.existsByPhone(newUser.getPhone())) {
            throw new PhoneNumberAlreadyExistsException(newUser.getPhone());
        }
        return userRepository.save(newUser);
    }

//    @Override
//    public List<UserIdAndNameDTO> getAllUserIdAndName() {
//        List<User> possibleUsers = userRepository.getAllUsers();
//        return possibleUsers.stream()
//                .map(UserMapper::mapToUserIdAndNameDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UsersListDTO> getAllUsersList() {
//        List<User> possibleUsers = userRepository.getAllUsers();
//        return possibleUsers.stream()
//                .map(UserMapper::mapToUsersListDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO> getAllUsers() {
//        List<User> possibleExistingUsers = userRepository.findAll();
//        return possibleExistingUsers.stream()
//                .map(UserMapper::mapToUserDTO)
//                .collect(Collectors.toList());
//    }
//
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", String.valueOf(id)));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
//

//    @Override
//    public UserDTO updateUser(UserDTO user) {
//        User possibleExistingUser = userRepository.findById(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(user.getId())));
//
//        if (user.getFirstName() != null) possibleExistingUser.setFirstName(user.getFirstName());
//        if (user.getLastName() != null) possibleExistingUser.setLastName(user.getLastName());
//        if (user.getEmail() != null) possibleExistingUser.setEmail(user.getEmail());
//        if (user.getAddress() != null) possibleExistingUser.setAddress(user.getAddress());
//        if (user.getPhone() != null) possibleExistingUser.setPhone(user.getPhone());
//
//        User updatedUser = userRepository.save(possibleExistingUser);
//        return UserMapper.mapToUserDTO(updatedUser);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
//        userRepository.deleteById(id);
//    }
}