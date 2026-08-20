package org.example.userservice.repository;

import org.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.UID;
import java.util.Optional;

@Repository
public interface UserRepository implements JpaRepository<User, UID> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
