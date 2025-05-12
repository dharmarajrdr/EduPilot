package com.edupilot.backend.dto.request;

import com.edupilot.backend.model.Account;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import lombok.Data;

@Data
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
}
