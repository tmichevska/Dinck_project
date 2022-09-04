package com.example.dnic2.service;

import com.example.dnic2.model.Users;
import com.example.dnic2.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class usersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public usersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users findById(Integer user_id) {
        return this.usersRepository.findById(user_id).get();
    }

    @Override
    public List<Users> listAll() {
        return this.usersRepository.findAll();
    }

    @Override
    public Users register(Integer user_id, String user_username, String user_password, String user_email, String user_name) {
        Users user = new Users(user_id,user_username,passwordEncoder.encode(user_password),user_email,user_name);
        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                user.getUser_username(),
                user.getUser_password(),
                Stream.of(new SimpleGrantedAuthority(user.getUser_password())).collect(Collectors.toList()));
    }
}
