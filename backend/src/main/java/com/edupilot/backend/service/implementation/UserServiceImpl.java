package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.AccountAlreadyRegistered;
import com.edupilot.backend.custom_exception.UserNameAlreadyExist;
import com.edupilot.backend.dto.request.SignupUserRequestDto;
import com.edupilot.backend.model.User;
import com.edupilot.backend.repository.UserRepository;
import com.edupilot.backend.service.interfaces.AccountService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountService accountService;
    private final UserRepository userRepository;

    /**
     * Create a new user
     *
     * @param signupUserRequestDto
     * @return
     */
    @Override
    public void signupUser(SignupUserRequestDto signupUserRequestDto) {

        String email = signupUserRequestDto.getEmail();
        String phoneNumber = signupUserRequestDto.getPhoneNumber();
        if (accountService.existsAccountByEmailOrPhoneNumber(email, phoneNumber)) {
            throw new AccountAlreadyRegistered();
        }

        String userName = signupUserRequestDto.getUserName();
        if (userRepository.existsByUserName(userName)) {
            throw new UserNameAlreadyExist(userName);
        }

        User user = signupUserRequestDto.toUser();
        userRepository.save(user);
    }

    /**
     * Check whether the given userName exists or not
     *
     * @param username
     * @return
     */
    @Override
    public Boolean existsByUsername(String username) {

        return userRepository.existsByUserName(username);
    }
}
