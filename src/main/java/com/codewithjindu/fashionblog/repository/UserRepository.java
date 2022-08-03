package com.codewithjindu.fashionblog.repository;

import com.codewithjindu.fashionblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByUsernameOrEmail(String username, String email);
    @Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier")
    Optional<User> findUserByEmailOrUsername(String identifier);
    boolean existsByEmail(String email);
}
