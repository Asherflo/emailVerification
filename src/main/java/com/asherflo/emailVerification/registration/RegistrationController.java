package com.asherflo.emailVerification.registration;

import com.asherflo.emailVerification.event.RegistrationCompleteEvent;
import com.asherflo.emailVerification.user.User;
import com.asherflo.emailVerification.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    @PostMapping
    public ResponseEntity<String> userRegistration(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
         publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request) ));
        return new ResponseEntity<>("Success! please, check your email for to complete your registration ", HttpStatus.CREATED);
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

    }

}
