package com.asherflo.emailVerification.registration.token;

import com.asherflo.emailVerification.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private  Date expirationTime;
    private static final  int EXPIRATION_TIME=15;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken(String token, User user) {
        super();
        this.token = token;
        this.expirationTime =  this.getTokenExpirationTime();
        this.user = user;
    }
    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime =  this.getTokenExpirationTime();

    }

    public Date getTokenExpirationTime() {
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(new Date().getTime());
        calender.add(Calendar.MINUTE,EXPIRATION_TIME);
        return new Date(calender.getTime().getTime());
    }
}
