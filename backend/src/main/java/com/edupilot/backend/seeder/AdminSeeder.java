package com.edupilot.backend.seeder;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.edupilot.backend.dto.request.SignupUserRequestDto;
import com.edupilot.backend.model.Account;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.service.interfaces.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdminSeeder implements ApplicationRunner {

    private final UserService userService;

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) {

        List<User> users = List.of(
                User.builder().userName("admin").displayName("Admin").account(
                        Account.builder()
                                .email("admin@edupilot.com")
                                .password("1234567890")
                                .phoneNumber("9876543210")
                                .build()
                ).userType(UserType.ADMIN).build()
        );

        for (User user : users) {

            try {
                userService.signupUser(SignupUserRequestDto.fromUser(user));
            } catch (Exception e) {}
        }
    }
}
