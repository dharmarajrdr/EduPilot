package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Account;

public interface AccountService {

    public Account getAccountByEmailOrPhoneNumber(String email, String phoneNumber);

    public Boolean existsAccountByEmailOrPhoneNumber(String email, String phoneNumber);
}
