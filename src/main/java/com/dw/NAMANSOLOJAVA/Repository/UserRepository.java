package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmailMAndEmailFAndPhoneNumberMAndPhoneNumberFAndRealNameMAndRealNameF(
            String emailM,
            String emailF,
            String phoneNumberM,
            String phoneNumberF,
            String realNameM,
            String realNameF
    );

    Optional<User> findByPhoneNumberMAndRealNameMOrPhoneNumberFAndRealNameF(
            String phoneNumberM, String realNameM,
            String phoneNumberF, String realNameF
    );
}
