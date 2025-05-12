package com.edupilot.backend.service.implementation;

import com.edupilot.backend.model.Account;
import com.edupilot.backend.repository.AccountsRepository;
import com.edupilot.backend.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountsRepository accountsRepository;

    /**
     * @param email
     * @param phoneNumber
     * @return
     */
    @Override
    public Account getAccountByEmailOrPhoneNumber(String email, String phoneNumber) {
        return null;
    }

    /**
     * @param email
     * @param phoneNumber
     * @return
     */
    @Override
    public Boolean existsAccountByEmailOrPhoneNumber(String email, String phoneNumber) {

        return accountsRepository.existsAccountByEmailOrPhoneNumber(email, phoneNumber);
    }
}
