package com.kranthi.AccountManagement.ServiceImpl;


import java.util.List;


import org.springframework.stereotype.Service;
import com.kranthi.AccountManagement.Exceptions.DuplicateDataException;
import com.kranthi.AccountManagement.Exceptions.ResourceNotFoundException;
import com.kranthi.AccountManagement.Model.Users;
import com.kranthi.AccountManagement.Repo.UserRepo;
import com.kranthi.AccountManagement.Service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private UserRepo repo;
	
	
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}
	
	

	@Override
	public Users createUsers(Users user) {
		if (repo.findByEmail(user.getEmail()).isPresent()) {
	        throw new DuplicateDataException("Email already exists: " + user.getEmail());
	    }
		if (repo.findByAccountNumber(user.getAccountNumber()).isPresent()) {
	        throw new DuplicateDataException("Account number already exists: " + user.getAccountNumber());
	    }
	    if (repo.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
	        throw new DuplicateDataException("Phone number already exists: " + user.getPhoneNumber());
	    }

		return repo.save(user);
	}

	@Override
	public List<Users> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public Users getUserById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	@Override
	public Users updateUser(Long id, Users user) {
		Users existing = getUserById(id);
		
		if (user.getName() != null) existing.setName(user.getName());
		if (user.getEmail() != null && !existing.getEmail().equals(user.getEmail())) {
            repo.findByEmail(user.getEmail())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> { throw new DuplicateDataException("Email already exists: " + user.getEmail()); });
            existing.setEmail(user.getEmail()); 
        }
	    if (user.getPassword() != null) existing.setPassword(user.getPassword());
	    if (user.getAccountNumber() != null && !existing.getAccountNumber().equals(user.getAccountNumber())) {
            repo.findByAccountNumber(user.getAccountNumber())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> { throw new DuplicateDataException("Account number already exists: " + user.getAccountNumber()); });
            existing.setAccountNumber(user.getAccountNumber());
        }	    
	    if (user.getPhoneNumber() != null && !existing.getPhoneNumber().equals(user.getPhoneNumber())) {
            repo.findByPhoneNumber(user.getPhoneNumber())
                .filter(u -> !u.getId().equals(id))
                .ifPresent(u -> { throw new DuplicateDataException("Phone number already exists: " + user.getPhoneNumber()); });
            existing.setPhoneNumber(user.getPhoneNumber());
        }	    
	    if (user.getGender() != null) existing.setGender(user.getGender());
	    if (user.getAddress() != null) existing.setAddress(user.getAddress());
        return repo.save(existing);
		
	}

	@Override
	public void deleteUser(Long id) {
		Users user = getUserById(id);
        repo.delete(user);
		
	}

}
