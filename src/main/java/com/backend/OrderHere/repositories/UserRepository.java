package com.backend.OrderHere.repositories;

import com.backend.OrderHere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
