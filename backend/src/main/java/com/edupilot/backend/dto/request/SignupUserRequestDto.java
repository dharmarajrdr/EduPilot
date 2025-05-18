package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Account;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupUserRequestDto {

    private String email;

    private String phoneNumber;

    private String password;

    private String userName;

    private String displayName;

    private UserType userType;

    public User toUser() {
        Account account = Account.builder().email(email).phoneNumber(phoneNumber).password(password).build();
        return User.builder().account(account).userType(userType).displayName(displayName).userName(userName).build();
    }

    public static SignupUserRequestDto fromUser(User user) {

        return SignupUserRequestDto.builder()
                .userName(user.getUserName())
                .userType(user.getUserType())
                .displayName(user.getDisplayName())
                .email(user.getAccount().getEmail())
                .password(user.getAccount().getPassword())
                .phoneNumber(user.getAccount().getPhoneNumber())
                .build();
    }
}
