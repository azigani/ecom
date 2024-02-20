package com.alphonse.ecom.repository;

import com.alphonse.ecom.enums.UserRole;
import com.alphonse.ecom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole admin);
}
