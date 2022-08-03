package com.guidomaiola.springboot.SpringBootRestWithH2.repository;

import java.util.Optional;

import com.guidomaiola.springboot.SpringBootRestWithH2.model.User;

public interface UserRepository {

	Optional<User> findByUsername(String username);

}


