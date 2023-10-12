package com.asherflo.emailVerification.event.listener;

import com.asherflo.emailVerification.event.RegistrationCompleteEvent;
import com.asherflo.emailVerification.user.User;
import com.asherflo.emailVerification.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private  final UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // get the newly registered user
        User theUser = event.getUser();
        // create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();

        // save the verification token for the user
        userService.saveUserVerificationToken(theUser,verificationToken);

        // build the verification url to be sent ti the user
        String url =event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
        // sent the email.
        log.info("click the link to verify to complete your registration : {} ", url );


    }
}
