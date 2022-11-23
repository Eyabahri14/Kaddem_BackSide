package com.esprit.kaddemback.repositories;

import com.esprit.kaddemback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);
    public boolean existsByEmail(String email);
}
