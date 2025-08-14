package com.kranthi.AccountManagement.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kranthi.AccountManagement.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	Optional<Users>findByEmail(String email);
	Optional<Users>findByAccountNumber(String accountNumber);
	Optional<Users>findByPhoneNumber(String phoneNumber);

}
