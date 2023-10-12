package com.asherflo.emailVerification.user;

import com.asherflo.emailVerification.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken( User theUser, String verificationToken);
}
