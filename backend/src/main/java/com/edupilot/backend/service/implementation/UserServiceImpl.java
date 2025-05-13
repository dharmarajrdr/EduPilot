package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.AccountAlreadyRegistered;
import com.edupilot.backend.custom_exception.NewAdminCreationRestricted;
import com.edupilot.backend.custom_exception.UserNameAlreadyExist;
import com.edupilot.backend.custom_exception.UserNotFound;
import com.edupilot.backend.dto.request.SignupUserRequestDto;
import com.edupilot.backend.model.Instructor;
import com.edupilot.backend.model.Learner;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.UserType;
import com.edupilot.backend.repository.UserRepository;
import com.edupilot.backend.service.interfaces.AccountService;
import com.edupilot.backend.service.interfaces.InstructorService;
import com.edupilot.backend.service.interfaces.LearnerService;
import com.edupilot.backend.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountService accountService;
    private final UserRepository userRepository;
    private final InstructorService instructorService;
    private final LearnerService learnerService;

    /**
     * Create a new user
     *
     * @param signupUserRequestDto
     * @return
     */
    @Override
    @Transactional
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

        User user = userRepository.save(signupUserRequestDto.toUser());
        switch (user.getUserType()) {
            case INSTRUCTOR: {
                instructorService.save(Instructor.builder().user(user).build());
                break;
            }
            case LEARNER: {
                learnerService.save(Learner.builder().user(user).build());
                break;
            }
            default: {
                throw new NewAdminCreationRestricted();
            }
        }
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

    /**
     * Get the user by id
     *
     * @param userId
     * @return
     */
    @Override
    public User findUserById(Long userId) {

        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }

    /**
     * Check whether the user is an instructor
     *
     * @param userId
     * @return Boolean
     */
    @Override
    public Boolean isInstructor(Long userId) {

        User user = findUserById(userId);
        return user.getUserType().equals(UserType.INSTRUCTOR);
    }

    /**
     * Check whether the user is an admin
     *
     * @param userId
     * @return Boolean
     */
    @Override
    public Boolean isAdmin(Long userId) {

        User user = findUserById(userId);
        return user.getUserType().equals(UserType.ADMIN);
    }

    /**
     * Check whether the user is an user
     *
     * @param userId
     * @return Boolean
     */
    @Override
    public Boolean isLearner(Long userId) {

        User user = findUserById(userId);
        return user.getUserType().equals(UserType.LEARNER);
    }
}
