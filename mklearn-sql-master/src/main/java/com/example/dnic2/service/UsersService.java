package com.example.dnic2.service;

import com.example.dnic2.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {
    Users findById(Integer user_id);
    List<Users> listAll();
    Users register(Integer user_id, String user_username, String user_password, String user_email, String user_name);
}
