package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByUsernameLike(String username);
    Optional<User> findByUsername(String username);


    // 여성용 - 이름과 번호로 username 찾기
    Optional<User> findByRealNameFAndPhoneNumberF(String realNameF, String phoneNumberF);

    // 남성용 - 이름과 번호로 username 찾기
    Optional<User> findByRealNameMAndPhoneNumberM(String realNameM, String phoneNumberM);

    // 여성용 - 이름과 이메일로 username 찾기
    Optional<User> findByRealNameFAndEmailF(String realNameF, String emailF);

    // 남성용 - 이름과 이메일로 username 찾기
    Optional<User> findByRealNameMAndEmailM(String realNameM, String emailM);

    // 여성용 - 이름과 이메일로 username 찾기
    Optional<User> findByUsernameAndRealNameFAndEmailF(String username,String realNameF, String emailF);

    // 남성용 - 이름과 이메일로 username 찾기
    Optional<User> findByUsernameAndRealNameMAndEmailM(String username,String realNameM, String emailM);

    @Query("select count(u)>0 from User u where u.emailM =:email or u.emailF =:email")
    boolean existsByEmailMOrEmailF(String email);
    @Query("select count(u)>0 from User u where u.phoneNumberM =:phone or u.phoneNumberF =:phone")
    boolean existsByPhoneNumberMOrPhoneNumberF(String phone);
}

