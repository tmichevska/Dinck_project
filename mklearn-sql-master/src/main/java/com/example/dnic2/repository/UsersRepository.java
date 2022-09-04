package com.example.dnic2.repository;

import com.example.dnic2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value="select * from users where user_username=?",nativeQuery = true)
    Optional<Users> findByUsername(String user_username);
}