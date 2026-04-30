package com.shopsphere.userservice.user_services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class userTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("password"));
    }
}
